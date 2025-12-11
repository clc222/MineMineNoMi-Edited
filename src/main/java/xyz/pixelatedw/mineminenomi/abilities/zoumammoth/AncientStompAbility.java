/*     */ package xyz.pixelatedw.mineminenomi.abilities.zoumammoth;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.particles.BlockParticleData;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RepeaterComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class AncientStompAbility extends Ability {
/*  43 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "ancient_stomp", new Pair[] {
/*  44 */         (Pair)ImmutablePair.of("Stomps the ground multiple times creating shockwaves that deal damage to all nearby enemies.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 200;
/*     */   private static final int RADIUS = 7;
/*     */   private static final int DAMAGE = 10;
/*  50 */   public static final AbilityCore<AncientStompAbility> INSTANCE = (new AbilityCore.Builder("Ancient Stomp", AbilityCategory.DEVIL_FRUITS, AncientStompAbility::new))
/*  51 */     .addDescriptionLine(DESCRIPTION)
/*  52 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/*  53 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), DealDamageComponent.getTooltip(10.0F), RangeComponent.getTooltip(7.0F, RangeComponent.RangeType.AOE)
/*  54 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/*  55 */     .build();
/*     */   
/*  57 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(100, this::startContinuityEvent).addEndEvent(100, this::endContinuityEvent);
/*  58 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(100, this::triggerRepeaterEvent).addStopEvent(100, this::stopRepeaterEvent);
/*  59 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  60 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  61 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.MAMMOTH_GUARD.get(), new MorphInfo[0]);
/*     */   
/*     */   private Iterator<BlockPos> targetedBlocks;
/*     */   
/*     */   public AncientStompAbility(AbilityCore<AncientStompAbility> core) {
/*  66 */     super(core);
/*     */     
/*  68 */     this.isNew = true;
/*  69 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.repeaterComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.requireMorphComponent });
/*     */     
/*  71 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  75 */     this.continuousComponent.startContinuity(entity);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  79 */     Predicate<BlockPos> predicate = pos -> (entity.field_70170_p.func_180495_p(pos.func_177984_a()).func_196958_f() && pos.func_177956_o() > entity.func_226278_cu_() - 3.0D);
/*  80 */     Vector3d look = entity.func_213303_ch().func_178787_e(entity.func_70040_Z().func_216372_d(7.0D, 1.0D, 7.0D));
/*  81 */     BlockPos ogPos = new BlockPos(look.func_82615_a(), entity.func_226278_cu_(), look.func_82616_c());
/*  82 */     List<BlockPos> poses = WyHelper.getNearbyBlocks(ogPos, (IWorld)entity.field_70170_p, 7, predicate, (List)ImmutableList.of(Blocks.field_150350_a));
/*  83 */     this.targetedBlocks = WyHelper.shuffle(poses).stream().limit(10L).iterator();
/*     */     
/*  85 */     this.repeaterComponent.start(entity, 12, 8);
/*     */   }
/*     */   
/*     */   private void triggerRepeaterEvent(LivingEntity entity, IAbility ability) {
/*  89 */     if (this.targetedBlocks == null || !this.targetedBlocks.hasNext()) {
/*  90 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/*  94 */     BlockPos stompPos = this.targetedBlocks.next();
/*     */     
/*  96 */     List<LivingEntity> list = this.rangeComponent.getTargetsInArea(entity, 7.0F);
/*  97 */     Iterator<LivingEntity> iter = list.iterator();
/*     */     
/*  99 */     ModDamageSource source = ((ModDamageSource)this.dealDamageComponent.getDamageSource(entity)).markIndirectDamage();
/*     */     
/* 101 */     while (iter.hasNext()) {
/* 102 */       LivingEntity target = iter.next();
/* 103 */       if (this.dealDamageComponent.hurtTarget(entity, target, 10.0F, (DamageSource)source)) {
/* 104 */         target.func_195064_c(new EffectInstance((Effect)ModEffects.DIZZY.get(), 20, 0));
/* 105 */         AbilityHelper.setDeltaMovement((Entity)target, target.func_213322_ci().func_72441_c(0.0D, 0.25D, 0.0D));
/*     */       } 
/*     */     } 
/*     */     
/* 109 */     List<BlockPos> blocks = WyHelper.getNearbyBlocks(entity.func_233580_cy_(), (IWorld)entity.field_70170_p, 7, p -> FoliageBlockProtectionRule.INSTANCE.isApproved(entity.field_70170_p.func_180495_p(p)), (List)ImmutableList.of(Blocks.field_150350_a));
/*     */     
/* 111 */     if (!entity.field_70170_p.field_72995_K) {
/* 112 */       for (BlockPos blockPos : blocks) {
/* 113 */         BlockState blockState1 = entity.field_70170_p.func_180495_p(blockPos);
/*     */         
/* 115 */         for (int i = 0; i < 150; i++) {
/* 116 */           double offsetX = WyHelper.randomDouble();
/* 117 */           double offsetY = WyHelper.randomDouble();
/* 118 */           double offsetZ = WyHelper.randomDouble();
/*     */           
/* 120 */           ((ServerWorld)entity.field_70170_p).func_195598_a((IParticleData)new BlockParticleData(ParticleTypes.field_197611_d, blockState1), blockPos.func_177958_n() + offsetX, blockPos.func_177956_o() + offsetY, blockPos.func_177952_p() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */         } 
/*     */       } 
/*     */       
/* 124 */       BlockState blockState = entity.field_70170_p.func_180495_p(stompPos);
/*     */       
/* 126 */       if (blockState.func_185904_a().func_76220_a()) {
/* 127 */         for (int i = 0; i < 250; i++) {
/* 128 */           double x = WyHelper.randomDouble();
/* 129 */           double z = WyHelper.randomDouble();
/*     */           
/* 131 */           ((ServerWorld)entity.field_70170_p).func_195598_a((IParticleData)new BlockParticleData(ParticleTypes.field_197611_d, blockState), stompPos.func_177958_n() + WyHelper.randomWithRange(-3, 3) + x, (stompPos.func_177956_o() + 1), stompPos.func_177952_p() + WyHelper.randomWithRange(-3, 3) + z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void stopRepeaterEvent(LivingEntity entity, IAbility abiltiy) {
/* 138 */     this.continuousComponent.stopContinuity(entity);
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 142 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\zoumammoth\AncientStompAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */