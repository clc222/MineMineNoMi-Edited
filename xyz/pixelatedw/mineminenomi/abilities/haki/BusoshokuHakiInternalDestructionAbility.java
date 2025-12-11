/*     */ package xyz.pixelatedw.mineminenomi.abilities.haki;
/*     */ import java.awt.Color;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SkinOverlayComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ 
/*     */ public class BusoshokuHakiInternalDestructionAbility extends PunchAbility2 {
/*  38 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "busoshoku_haki_internal_destruction", new Pair[] {
/*  39 */         (Pair)ImmutablePair.of("Allows the user to emit their haki through the target's body dealing great internal damage.", null)
/*     */       });
/*  41 */   public static final AbilityCore<BusoshokuHakiInternalDestructionAbility> INSTANCE = (new AbilityCore.Builder("Busoshoku Haki: Internal Destruction", AbilityCategory.HAKI, BusoshokuHakiInternalDestructionAbility::new))
/*  42 */     .addDescriptionLine(DESCRIPTION)
/*  43 */     .setUnlockCheck(BusoshokuHakiInternalDestructionAbility::canUnlock)
/*  44 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  45 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/*  46 */     .build();
/*     */   
/*  48 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setOverlayPart(AbilityOverlay.OverlayPart.LIMB).setColor(new Color(226, 129, 25, 70)).build();
/*     */   
/*  50 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.ADVANCED_BUSOSHOKU_HAKI, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*  51 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/*     */   
/*     */   public BusoshokuHakiInternalDestructionAbility(AbilityCore<BusoshokuHakiInternalDestructionAbility> core) {
/*  54 */     super(core);
/*     */     
/*  56 */     this.continuousComponent.addStartEvent(100, this::onContinuityStart).addTickEvent(100, this::onContinuityTick).addEndEvent(100, this::onContinuityEnd);
/*     */     
/*  58 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.poolComponent, (AbilityComponent)this.skinOverlayComponent });
/*     */     
/*  60 */     addCanUseCheck(HakiHelper::canEnableHaki);
/*     */   }
/*     */   
/*     */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/*  64 */     this.statsComponent.removeModifiers(entity);
/*  65 */     this.statsComponent.clearAttributeModifiers();
/*  66 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.ATTACK_RANGE, (AttributeModifier)HakiHelper.getAdvancedBusoshokuHakiAttackReachBonus(INSTANCE, BusoshokuHakiEmissionAbility.getReachBonus(entity)));
/*     */ 
/*     */ 
/*     */     
/*  70 */     this.statsComponent.applyModifiers(entity);
/*     */     
/*  72 */     this.skinOverlayComponent.showAll(entity);
/*     */     
/*  74 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.BUSOSHOKU_HAKI_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/*  78 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  82 */     boolean isOnMaxOveruse = HakiHelper.checkForHakiOveruse(entity, 2);
/*     */     
/*  84 */     if (isOnMaxOveruse) {
/*  85 */       this.continuousComponent.stopContinuity(entity);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/*  90 */     this.skinOverlayComponent.hideAll(entity);
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity user) {
/*  94 */     IAbilityData abilityProps = AbilityDataCapability.get(user);
/*  95 */     IHakiData props = HakiDataCapability.get(user);
/*  96 */     IEntityStats statsProps = EntityStatsCapability.get(user);
/*     */     
/*  98 */     boolean hasEmissionUnlocked = abilityProps.hasUnlockedAbility(BusoshokuHakiEmissionAbility.INSTANCE);
/*  99 */     boolean hasBusoExp = (props.getBusoshokuHakiExp() > HakiHelper.getBusoshokuInternalDestructionExpNeeded(user));
/*     */     
/* 101 */     return (hasEmissionUnlocked && statsProps.getDoriki() > 8000.0D && hasBusoExp);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 106 */     source.setInternal();
/*     */     
/* 108 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.INTERNAL_DESTRUCTION_BURST.get(), (Entity)entity, target.func_226277_ct_(), target.func_226278_cu_() + entity.func_70047_e(), target.func_226281_cx_());
/*     */     
/* 110 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)entity, entity.field_70170_p, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_(), 1.0F);
/*     */     
/* 112 */     explosion.setDamageEntities(false);
/* 113 */     explosion.setDestroyBlocks(false);
/* 114 */     explosion.disableExplosionKnockback();
/* 115 */     explosion.setFireAfterExplosion(false);
/* 116 */     explosion.setExplosionSound(false);
/* 117 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(1));
/* 118 */     explosion.doExplosion();
/*     */     
/* 120 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Predicate<LivingEntity> canActivate() {
/* 125 */     return entity -> this.continuousComponent.isContinuous();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUseLimit() {
/* 130 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isParallel() {
/* 135 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getPunchCooldown() {
/* 140 */     return 0.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\haki\BusoshokuHakiInternalDestructionAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */