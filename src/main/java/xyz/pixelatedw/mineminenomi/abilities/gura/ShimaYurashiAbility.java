/*     */ package xyz.pixelatedw.mineminenomi.abilities.gura;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.FallingBlockEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.ProtectedArea;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ProtectedAreasData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.BreakingBlocksParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.gura.AirCrackParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ShimaYurashiAbility extends Ability {
/*  51 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "shima_yurashi", new Pair[] {
/*  52 */         (Pair)ImmutablePair.of("The user grabs the air around them and pulls it downwards after which the nearby land and entities are sent flying.", null)
/*     */       });
/*     */   private static final int EXPLOSION_RADIUS = 27;
/*     */   private static final int EXPLOSION_DEPTH = 10;
/*     */   private static final int COOLDOWN = 1200;
/*     */   private static final int CHARGE_TIME = 100;
/*     */   private static final int MIN_DAMAGE = 66;
/*     */   private static final int MAX_DAMAGE = 80;
/*  60 */   private static final ParticleEffect AIR_GRAB_PARTICLES = (ParticleEffect)new AirCrackParticleEffect();
/*     */   
/*  62 */   public static final AbilityCore<ShimaYurashiAbility> INSTANCE = (new AbilityCore.Builder("Shima Yurashi", AbilityCategory.DEVIL_FRUITS, ShimaYurashiAbility::new))
/*  63 */     .addDescriptionLine(DESCRIPTION)
/*  64 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(1200.0F), ChargeComponent.getTooltip(100.0F), RangeComponent.getTooltip(27.0F, RangeComponent.RangeType.AOE), DealDamageComponent.getTooltip(66.0F, 66.0F)
/*  65 */       }).setSourceElement(SourceElement.SHOCKWAVE)
/*  66 */     .build();
/*     */   
/*  68 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::startChargeEvent).addTickEvent(this::duringChargeEvent).addEndEvent(this::endChargeEvent);
/*  69 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  70 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  71 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*     */   
/*  73 */   public List<FallingBlockEntity> entityBlocks = new ArrayList<>();
/*  74 */   private BreakingBlocksParticleEffect.Details details = new BreakingBlocksParticleEffect.Details(40);
/*     */   
/*     */   public ShimaYurashiAbility(AbilityCore<ShimaYurashiAbility> core) {
/*  77 */     super(core);
/*     */     
/*  79 */     this.isNew = true;
/*  80 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent });
/*     */     
/*  82 */     addCanUseCheck(AbilityHelper::requiresOnGround);
/*  83 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  87 */     this.chargeComponent.startCharging(entity, 100.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/*  93 */     List<BlockPos> randomPositions = (List<BlockPos>)WyHelper.getNearbyBlocks(entity.func_233580_cy_(), (IWorld)entity.field_70170_p, 13, (List)ImmutableList.of(Blocks.field_150350_a)).stream().filter(pos -> DefaultProtectionRules.CORE_FOLIAGE_ORE.check(entity.field_70170_p, pos, entity.field_70170_p.func_180495_p(pos))).collect(Collectors.toList());
/*  94 */     Collections.shuffle(randomPositions, entity.func_70681_au());
/*     */     
/*  96 */     this.details.clearPositions();
/*     */     
/*  98 */     this.entityBlocks.clear();
/*     */     
/* 100 */     ProtectedArea area = ProtectedAreasData.get(entity.field_70170_p).getProtectedArea((int)entity.func_226277_ct_(), (int)entity.func_226278_cu_(), (int)entity.func_226281_cx_());
/* 101 */     if (area == null || area.canDestroyBlocks()) {
/* 102 */       this
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 110 */         .entityBlocks = (List<FallingBlockEntity>)randomPositions.stream().limit(600L).map(pos -> { if (entity.field_70170_p.func_226660_f_(pos)) this.details.addPosition(pos);  return new FallingBlockEntity(entity.field_70170_p, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), entity.field_70170_p.func_180495_p(pos)); }).collect(Collectors.toList());
/*     */     }
/*     */     
/* 113 */     this.animationComponent.start(entity, ModAnimations.KAISHIN, 100);
/*     */   }
/*     */   
/*     */   private void duringChargeEvent(LivingEntity entity, IAbility ability) {
/* 117 */     if (this.chargeComponent.getChargeTime() % 5.0F == 0.0F) {
/* 118 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.GREAT_STOMP.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     }
/*     */     
/* 121 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 10, 0, false, false));
/*     */     
/* 123 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 27.0F);
/*     */     
/* 125 */     targets.stream()
/* 126 */       .filter(target -> 
/* 127 */         (target != null && target.func_70089_S() && target.field_70170_p.func_217299_a(new RayTraceContext(target.func_213303_ch(), target.func_213303_ch().func_72441_c(0.0D, -10.0D, 0.0D), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, (Entity)target)).func_216346_c().equals(RayTraceResult.Type.BLOCK)))
/*     */ 
/*     */       
/* 130 */       .forEach(target -> target.func_195064_c(new EffectInstance((Effect)ModEffects.DIZZY.get(), 10, 0, false, false)));
/*     */   }
/*     */ 
/*     */   
/*     */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/* 135 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 139 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 27.0F);
/*     */     
/* 141 */     ModDamageSource source = ModDamageSource.causeAbilityDamage(entity, (IAbility)this).setUnavoidable().bypassLogia().setPiercing(0.5F);
/* 142 */     targets.stream().filter(target -> (target != null && target.func_70089_S() && entity.func_70685_l((Entity)target))).forEach(target -> {
/*     */           double distance = Math.min(66.0D, Math.sqrt(target.func_70068_e((Entity)entity)));
/*     */ 
/*     */           
/*     */           double damage = 80.0D - distance / 2.0D;
/*     */ 
/*     */           
/*     */           if (this.dealDamageComponent.hurtTarget(entity, target, (float)damage, (DamageSource)source) && target.func_233570_aj_()) {
/*     */             Vector3d dirVec = entity.func_213303_ch().func_178788_d(target.func_213303_ch()).func_72432_b().func_216372_d(25.0D, 1.0D, 25.0D);
/*     */             
/*     */             AbilityHelper.setDeltaMovement((Entity)target, -dirVec.field_72450_a, 3.0D, -dirVec.field_72449_c);
/*     */           } 
/*     */         });
/*     */     
/* 156 */     Iterator<FallingBlockEntity> iter = this.entityBlocks.iterator();
/* 157 */     while (iter.hasNext()) {
/* 158 */       FallingBlockEntity block = iter.next();
/* 159 */       Vector3d dirVec = entity.func_213303_ch().func_178788_d(block.func_213303_ch()).func_72432_b().func_216372_d(2.0D, 2.0D, 2.0D);
/* 160 */       block.func_70107_b(entity
/* 161 */           .func_233580_cy_().func_177958_n() + WyHelper.randomDouble() * 20.0D, entity
/* 162 */           .func_233580_cy_().func_177956_o() + WyHelper.randomDouble() * 3.0D, entity
/* 163 */           .func_233580_cy_().func_177952_p() + WyHelper.randomDouble() * 20.0D);
/* 164 */       AbilityHelper.setDeltaMovement((Entity)block, -dirVec.field_72450_a, 1.0D + dirVec.field_72448_b, -dirVec.field_72449_c);
/* 165 */       block.field_145813_c = false;
/* 166 */       block.field_145812_b = 1;
/* 167 */       entity.field_70170_p.func_217376_c((Entity)block);
/*     */     } 
/*     */     
/* 170 */     int craterRadius = 72;
/* 171 */     AbilityHelper.createSphereWithProtection(entity.field_70170_p, entity.func_233580_cy_(), craterRadius, 10, Blocks.field_150350_a, 3, DefaultProtectionRules.CORE_FOLIAGE_ORE_LIQUID);
/* 172 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.GURA_SFX.get(), SoundCategory.PLAYERS, 5.0F, 1.0F);
/*     */     
/* 174 */     this.animationComponent.stop(entity);
/* 175 */     this.cooldownComponent.startCooldown(entity, 1200.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gura\ShimaYurashiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */