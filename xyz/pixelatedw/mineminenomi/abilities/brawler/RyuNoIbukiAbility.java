/*     */ package xyz.pixelatedw.mineminenomi.abilities.brawler;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.FallingBlockEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.RayTraceContext;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.ProtectedArea;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.BreakingBlocksParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class RyuNoIbukiAbility extends Ability {
/*  47 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "ryu_no_ibuki", new Pair[] {
/*  48 */         (Pair)ImmutablePair.of("The user puts their clenched fists into the ground, creating an immense impact and centered all-directional fissure.", null)
/*     */       });
/*     */   
/*     */   private static final int EXPLOSION_RADIUS = 15;
/*     */   private static final int EXPLOSION_DEPTH = 3;
/*     */   private static final int COOLDOWN = 500;
/*     */   private static final int CHARGE_TIME = 30;
/*  55 */   public static final AbilityCore<RyuNoIbukiAbility> INSTANCE = (new AbilityCore.Builder("Ryu no Ibuki", AbilityCategory.STYLE, RyuNoIbukiAbility::new))
/*  56 */     .addDescriptionLine(DESCRIPTION)
/*  57 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(500.0F), ChargeComponent.getTooltip(30.0F), RangeComponent.getTooltip(15.0F, RangeComponent.RangeType.AOE)
/*  58 */       }).setUnlockCheck(RyuNoIbukiAbility::canUnlock)
/*  59 */     .build();
/*     */   
/*  61 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::startChargeEvent).addTickEvent(this::duringChargeEvent).addEndEvent(this::endChargeEvent);
/*  62 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  63 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  64 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*     */   
/*  66 */   private final List<BlockPos> affectedBlocks = new ArrayList<>();
/*     */   
/*  68 */   private final BreakingBlocksParticleEffect.Details details = new BreakingBlocksParticleEffect.Details(5);
/*     */   
/*  70 */   private final Interval particleInterval = new Interval(5);
/*     */   
/*     */   public RyuNoIbukiAbility(AbilityCore<RyuNoIbukiAbility> core) {
/*  73 */     super(core);
/*     */     
/*  75 */     this.isNew = true;
/*     */     
/*  77 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent });
/*     */     
/*  79 */     addCanUseCheck(AbilityHelper::requiresOnGround);
/*  80 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  84 */     this.chargeComponent.startCharging(entity, 30.0F);
/*     */   }
/*     */   
/*     */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/*  88 */     int radiusXZ = 7;
/*     */ 
/*     */ 
/*     */     
/*  92 */     List<BlockPos> allPositions = (List<BlockPos>)WyHelper.getNearbyBlocks(entity.func_233580_cy_(), (IWorld)entity.field_70170_p, radiusXZ, 3, radiusXZ, state -> !state.func_177230_c().equals(Blocks.field_150350_a)).stream().filter(pos -> DefaultProtectionRules.CORE_FOLIAGE_ORE.check(entity.field_70170_p, pos, entity.field_70170_p.func_180495_p(pos))).collect(Collectors.toList());
/*     */     
/*  94 */     Collections.shuffle(allPositions, entity.func_70681_au());
/*     */     
/*  96 */     for (int i = 0; i < allPositions.size(); i++) {
/*  97 */       if (i % 2 == 1) {
/*  98 */         this.affectedBlocks.add(allPositions.get(i));
/*     */       }
/*     */     } 
/*     */     
/* 102 */     this.affectedBlocks.forEach(this.details::addPosition);
/*     */     
/* 104 */     this.animationComponent.start(entity, ModAnimations.RYU_NO_IBUKI, 30);
/*     */   }
/*     */   
/*     */   private void duringChargeEvent(LivingEntity entity, IAbility ability) {
/* 108 */     if (this.particleInterval.canTick()) {
/* 109 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BREAKING_BLOCKS.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), (ParticleEffect.Details)this.details);
/*     */     }
/*     */     
/* 112 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 1, false, false));
/*     */     
/* 114 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 15.0F);
/*     */     
/* 116 */     targets.stream().filter(target -> (target != null && target.func_70089_S() && target.field_70170_p.func_217299_a(new RayTraceContext(target.func_213303_ch().func_178786_a(0.0D, 3.0D, 0.0D), target.func_213303_ch().func_72441_c(0.0D, 3.0D, 0.0D), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, (Entity)target)).func_216346_c().equals(RayTraceResult.Type.BLOCK))).forEach(target -> target.func_195064_c(new EffectInstance((Effect)ModEffects.DIZZY.get(), 5, 0, false, false)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/* 122 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 126 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 15.0F);
/*     */     
/* 128 */     targets.stream().filter(target -> (target != null && target.func_70089_S() && target.field_70170_p.func_217299_a(new RayTraceContext(target.func_213303_ch().func_178786_a(0.0D, 3.0D, 0.0D), target.func_213303_ch().func_72441_c(0.0D, 3.0D, 0.0D), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, (Entity)target)).func_216346_c().equals(RayTraceResult.Type.BLOCK))).forEach(target -> target.func_195064_c(new EffectInstance((Effect)ModEffects.DIZZY.get(), 80, 0, false, false)));
/*     */ 
/*     */ 
/*     */     
/* 132 */     ProtectedArea area = ProtectedAreasData.get(entity.field_70170_p).getProtectedArea((int)entity.func_226277_ct_(), (int)entity.func_226278_cu_(), (int)entity.func_226281_cx_());
/*     */     
/* 134 */     if (area == null || area.canDestroyBlocks()) {
/* 135 */       for (BlockPos pos : this.affectedBlocks) {
/* 136 */         FallingBlockEntity block = new FallingBlockEntity(entity.field_70170_p, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), entity.field_70170_p.func_180495_p(pos));
/*     */         
/* 138 */         Vector3d dirVec = entity.func_213303_ch().func_178788_d(block.func_213303_ch()).func_72432_b();
/*     */         
/* 140 */         AbilityHelper.setDeltaMovement((Entity)block, dirVec.field_72450_a * WyHelper.randomDouble() * 2.0D, dirVec.field_72448_b + 0.5D, dirVec.field_72449_c * WyHelper.randomDouble() * 2.0D);
/*     */         
/* 142 */         block.field_145813_c = false;
/* 143 */         block.field_145812_b = 1;
/*     */         
/* 145 */         entity.field_70170_p.func_217376_c((Entity)block);
/*     */         
/* 147 */         AbilityHelper.placeBlockIfAllowed(entity, pos, Blocks.field_150350_a.func_176223_P(), null);
/*     */       } 
/*     */     }
/*     */     
/* 151 */     this.affectedBlocks.clear();
/*     */     
/* 153 */     this.details.clearPositions();
/*     */     
/* 155 */     this.particleInterval.restartIntervalToZero();
/*     */     
/* 157 */     this.animationComponent.stop(entity);
/*     */     
/* 159 */     this.cooldownComponent.startCooldown(entity, 500.0F);
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 163 */     if (!(entity instanceof PlayerEntity)) {
/* 164 */       return false;
/*     */     }
/*     */     
/* 167 */     PlayerEntity player = (PlayerEntity)entity;
/*     */     
/* 169 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 170 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 172 */     return (props.isBrawler() && questProps.hasFinishedQuest(ModQuests.BRAWLER_TRIAL_04));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\brawler\RyuNoIbukiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */