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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SkinOverlayComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ 
/*     */ public class BusoshokuHakiEmissionAbility extends PunchAbility2 {
/*  37 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "busoshoku_haki_emission", new Pair[] {
/*  38 */         (Pair)ImmutablePair.of("Allows the user to emit their haki over a short distance without a medium.", null)
/*     */       });
/*  40 */   public static final AbilityCore<BusoshokuHakiEmissionAbility> INSTANCE = (new AbilityCore.Builder("Busoshoku Haki: Emission", AbilityCategory.HAKI, BusoshokuHakiEmissionAbility::new))
/*  41 */     .addDescriptionLine(DESCRIPTION)
/*  42 */     .setUnlockCheck(BusoshokuHakiEmissionAbility::canUnlock)
/*  43 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  44 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/*  45 */     .build();
/*     */   
/*  47 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setOverlayPart(AbilityOverlay.OverlayPart.LIMB).setColor(new Color(226, 129, 25, 40)).build();
/*     */   
/*  49 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.ADVANCED_BUSOSHOKU_HAKI, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*  50 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/*     */   
/*     */   public BusoshokuHakiEmissionAbility(AbilityCore<BusoshokuHakiEmissionAbility> core) {
/*  53 */     super(core);
/*     */     
/*  55 */     this.continuousComponent.addStartEvent(100, this::onContinuityStart).addTickEvent(100, this::onContinuityTick).addEndEvent(100, this::onContinuityEnd);
/*     */     
/*  57 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.poolComponent, (AbilityComponent)this.skinOverlayComponent });
/*     */     
/*  59 */     addCanUseCheck(HakiHelper::canEnableHaki);
/*     */   }
/*     */   
/*     */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/*  63 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.ATTACK_RANGE, (AttributeModifier)HakiHelper.getAdvancedBusoshokuHakiAttackReachBonus(INSTANCE, getReachBonus(entity)), canActivate());
/*     */ 
/*     */ 
/*     */     
/*  67 */     this.skinOverlayComponent.showAll(entity);
/*     */     
/*  69 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.BUSOSHOKU_HAKI_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/*  73 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  77 */     boolean isOnMaxOveruse = HakiHelper.checkForHakiOveruse(entity, 1);
/*     */     
/*  79 */     if (isOnMaxOveruse) {
/*  80 */       this.continuousComponent.stopContinuity(entity);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/*  85 */     this.skinOverlayComponent.hideAll(entity);
/*     */   }
/*     */   
/*     */   public static float getReachBonus(LivingEntity player) {
/*  89 */     IHakiData hakiProps = HakiDataCapability.get(player);
/*     */     
/*  91 */     float hakiMultiplier = hakiProps.getBusoshokuHakiExp() / CommonConfig.INSTANCE.getHakiExpLimit();
/*     */     
/*  93 */     return hakiMultiplier * 2.0F;
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity user) {
/*  97 */     IAbilityData abilityProps = AbilityDataCapability.get(user);
/*  98 */     IHakiData props = HakiDataCapability.get(user);
/*  99 */     IEntityStats statsProps = EntityStatsCapability.get(user);
/*     */     
/* 101 */     boolean hasBusoUnlocked = (abilityProps.hasUnlockedAbility(BusoshokuHakiHardeningAbility.INSTANCE) || abilityProps.hasUnlockedAbility(BusoshokuHakiImbuingAbility.INSTANCE));
/* 102 */     boolean hasBusoExp = (props.getBusoshokuHakiExp() > HakiHelper.getBusoshokuEmissionExpNeeded(user));
/*     */     
/* 104 */     return (hasBusoUnlocked && statsProps.getDoriki() > 6000.0D && hasBusoExp);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 109 */     source.bypassLogia();
/* 110 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.EMISSION_BURST.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226280_cw_(), entity.func_226281_cx_());
/* 111 */     target.func_233627_a_(4.0F, entity.func_226277_ct_() - target.func_226277_ct_(), entity.func_226281_cx_() - target.func_226281_cx_());
/*     */     
/* 113 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Predicate<LivingEntity> canActivate() {
/* 118 */     return entity -> this.continuousComponent.isContinuous();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUseLimit() {
/* 123 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isParallel() {
/* 128 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getPunchCooldown() {
/* 133 */     return 0.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\haki\BusoshokuHakiEmissionAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */