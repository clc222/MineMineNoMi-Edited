/*     */ package xyz.pixelatedw.mineminenomi.abilities.haki;
/*     */ import java.awt.Color;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.LivingEntity;
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
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ 
/*     */ public class BusoshokuHakiHardeningAbility extends PunchAbility2 {
/*  34 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "busoshoku_haki_hardening", new Pair[] {
/*  35 */         (Pair)ImmutablePair.of("Covers the fist of the user in Armament haki, making their physical attacks more powerful and being able to damage Logia users.", null)
/*     */       });
/*  37 */   public static final AbilityCore<BusoshokuHakiHardeningAbility> INSTANCE = (new AbilityCore.Builder("Busoshoku Haki: Hardening", AbilityCategory.HAKI, BusoshokuHakiHardeningAbility::new))
/*  38 */     .addDescriptionLine(DESCRIPTION)
/*  39 */     .setUnlockCheck(BusoshokuHakiHardeningAbility::canUnlock)
/*  40 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  41 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  42 */     .build();
/*     */   
/*  44 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setTexture(ModResources.BUSOSHOKU_HAKI_ARM).setOverlayPart(AbilityOverlay.OverlayPart.LIMB).setColor(new Color(255, 255, 255, 191)).build();
/*     */   
/*  46 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.BODY_BUSOSHOKU_HAKI, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*  47 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/*     */   public BusoshokuHakiHardeningAbility(AbilityCore<BusoshokuHakiHardeningAbility> core) {
/*  49 */     super(core);
/*     */     
/*  51 */     this.continuousComponent.addStartEvent(100, this::onContinuityStart).addTickEvent(100, this::onContinuityTick).addEndEvent(100, this::onContinuityEnd);
/*     */     
/*  53 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.poolComponent, (AbilityComponent)this.skinOverlayComponent });
/*     */     
/*  55 */     addCanUseCheck(HakiHelper::canEnableHaki);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/*  61 */     this.skinOverlayComponent.showAll(entity);
/*     */     
/*  63 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.BUSOSHOKU_HAKI_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/*  67 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  71 */     boolean isOnMaxOveruse = HakiHelper.checkForHakiOveruse(entity, 0);
/*     */     
/*  73 */     if (isOnMaxOveruse) {
/*  74 */       this.continuousComponent.stopContinuity(entity);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/*  79 */     this.skinOverlayComponent.hideAll(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/*  84 */     source.bypassLogia();
/*     */     
/*  86 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Predicate<LivingEntity> canActivate() {
/*  91 */     return entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUseLimit() {
/*  96 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isParallel() {
/* 101 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getPunchCooldown() {
/* 106 */     return 0.0F;
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity user) {
/* 110 */     IAbilityData abilityProps = AbilityDataCapability.get(user);
/* 111 */     IHakiData props = HakiDataCapability.get(user);
/* 112 */     IEntityStats statsProps = EntityStatsCapability.get(user);
/*     */     
/* 114 */     boolean hasImbuingUnlocked = abilityProps.hasUnlockedAbility(BusoshokuHakiImbuingAbility.INSTANCE);
/*     */     
/* 116 */     return ((statsProps.getDoriki() > 4000.0D && props.getBusoshokuHakiExp() > HakiHelper.getBusoshokuHardeningExpNeeded(user)) || hasImbuingUnlocked);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\haki\BusoshokuHakiHardeningAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */