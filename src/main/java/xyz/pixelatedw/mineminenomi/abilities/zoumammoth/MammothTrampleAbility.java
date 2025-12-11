/*     */ package xyz.pixelatedw.mineminenomi.abilities.zoumammoth;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.BreakingBlocksParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class MammothTrampleAbility extends PassiveAbility2 {
/*  37 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "trample", new Pair[] {
/*  38 */         (Pair)ImmutablePair.of("Running speed increases with acceleration trampling any nearby entity.", null)
/*     */       });
/*     */   
/*     */   private static final float TRAMPLE_AREA = 3.5F;
/*     */   private static final int BLOCK_BREAKING_AREA = 5;
/*     */   private static final float DAMAGE = 6.0F;
/*  44 */   public static final AbilityCore<MammothTrampleAbility> INSTANCE = (new AbilityCore.Builder("Trample", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, MammothTrampleAbility::new))
/*  45 */     .addDescriptionLine(DESCRIPTION)
/*  46 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/*  47 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE
/*  48 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  49 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  50 */       }).build();
/*     */   
/*  52 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  53 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  54 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.MAMMOTH_GUARD.get(), new MorphInfo[0]);
/*     */   
/*  56 */   private BreakingBlocksParticleEffect.Details details = new BreakingBlocksParticleEffect.Details(100);
/*  57 */   public float speed = 0.0F;
/*     */   
/*     */   public MammothTrampleAbility(AbilityCore<MammothTrampleAbility> ability) {
/*  60 */     super(ability);
/*     */     
/*  62 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.requireMorphComponent });
/*     */     
/*  64 */     addDuringPassiveEvent(this::duringPassiveEvent);
/*     */   }
/*     */   
/*     */   public void duringPassiveEvent(LivingEntity entity) {
/*  68 */     if (entity instanceof PlayerEntity && ((PlayerEntity)entity).field_71075_bZ.field_75100_b) {
/*     */       return;
/*     */     }
/*     */     
/*  72 */     if (MorphHelper.getZoanInfo(entity) != ModMorphs.MAMMOTH_GUARD.get()) {
/*     */       return;
/*     */     }
/*     */     
/*  76 */     if (!entity.func_70051_ag()) {
/*  77 */       this.speed = 0.0F;
/*     */     } else {
/*     */       
/*  80 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, entity.func_233580_cy_().func_177979_c((int)entity.func_70047_e()), 3.5F);
/*     */ 
/*     */       
/*  83 */       AncientStompAbility ability = (AncientStompAbility)AbilityDataCapability.get(entity).getEquippedAbility(AncientStompAbility.INSTANCE);
/*  84 */       float maxSpeed = (ability != null && ability.isContinuous()) ? 0.1F : 0.45F;
/*  85 */       float acceleration = (ability != null && ability.isContinuous()) ? 0.001F : 0.004F;
/*     */       
/*  87 */       acceleration *= (this.speed > 0.0F) ? (1.0F - this.speed / maxSpeed) : 1.0F;
/*  88 */       if (entity.field_191988_bg <= 0.0F || entity.field_70123_F)
/*  89 */         acceleration = -maxSpeed / 10.0F; 
/*  90 */       this.speed = MathHelper.func_76131_a(this.speed + acceleration, (acceleration > 0.0F) ? (maxSpeed / 20.0F) : 0.0F, maxSpeed);
/*     */       
/*  92 */       int d2 = (entity.field_191988_bg > 0.0F) ? 1 : 0;
/*     */       
/*  94 */       Vector3d vec = entity.func_70040_Z();
/*  95 */       double x = vec.field_72450_a * this.speed * d2;
/*  96 */       double z = vec.field_72449_c * this.speed * d2;
/*  97 */       AbilityHelper.setDeltaMovement((Entity)entity, x, (entity.func_213322_ci()).field_72448_b, z);
/*     */       
/*  99 */       if (!entity.field_70170_p.field_72995_K) {
/* 100 */         List<BlockPos> blocks = WyHelper.getNearbyBlocks(entity.func_233580_cy_(), (IWorld)entity.field_70170_p, 5, 5, 5, state -> 
/* 101 */             (!state.func_185904_a().equals(Material.field_151579_a) && FoliageBlockProtectionRule.INSTANCE.isApproved(state)));
/* 102 */         List<BlockPos> positions = new ArrayList<>();
/*     */         
/* 104 */         for (BlockPos pos : blocks) {
/* 105 */           if (AbilityHelper.placeBlockIfAllowed(entity, pos, Blocks.field_150350_a.func_176223_P(), FoliageBlockProtectionRule.INSTANCE)) {
/* 106 */             positions.add(pos);
/*     */           }
/*     */         } 
/*     */         
/* 110 */         if (positions.size() > 0) {
/* 111 */           this.details.setPositions(positions);
/* 112 */           WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BREAKING_BLOCKS.get(), (Entity)entity, 0.0D, 0.0D, 0.0D, (ParticleEffect.Details)this.details);
/*     */         } 
/*     */         
/* 115 */         for (LivingEntity target : targets) {
/* 116 */           if (this.dealDamageComponent.hurtTarget(entity, target, 6.0F)) {
/* 117 */             Vector3d speed = WyHelper.propulsion(entity, 2.0D, 2.0D);
/* 118 */             AbilityHelper.setDeltaMovement((Entity)target, speed.field_72450_a, 0.2D, speed.field_72449_c);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\zoumammoth\MammothTrampleAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */