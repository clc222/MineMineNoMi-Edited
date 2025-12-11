/*     */ package xyz.pixelatedw.mineminenomi.abilities.haki;
/*     */ import java.awt.Color;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
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
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.LightningDischargeEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class HaoshokuHakiInfusionAbility extends PunchAbility2 {
/*  38 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "haoshoku_haki_infusion", new Pair[] {
/*  39 */         (Pair)ImmutablePair.of("Allows the user to infuse their haoshoku haki into whatever weapon they're holding.", null)
/*     */       });
/*  41 */   public static final AbilityCore<HaoshokuHakiInfusionAbility> INSTANCE = (new AbilityCore.Builder("Haoshoku Haki: Infusion", AbilityCategory.HAKI, HaoshokuHakiInfusionAbility::new))
/*  42 */     .addDescriptionLine(DESCRIPTION)
/*  43 */     .setUnlockCheck(HaoshokuHakiInfusionAbility::canUnlock)
/*  44 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  45 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/*  46 */     .build();
/*     */   
/*  48 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setOverlayPart(AbilityOverlay.OverlayPart.LIMB).setColor(new Color(255, 0, 0, 50)).build();
/*  49 */   private static final UUID STRENGTH_UUID = UUID.fromString("46383f90-63d5-4cfb-8df7-f93db7d5b84b");
/*     */   
/*  51 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.ADVANCED_HAOSHOKU_HAKI, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*  52 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/*     */   
/*     */   public HaoshokuHakiInfusionAbility(AbilityCore<HaoshokuHakiInfusionAbility> core) {
/*  55 */     super(core);
/*     */     
/*  57 */     this.continuousComponent.addStartEvent(100, this::onContinuityStart).addTickEvent(100, this::onContinuityTick).addEndEvent(100, this::onContinuityEnd);
/*     */     
/*  59 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.poolComponent, (AbilityComponent)this.skinOverlayComponent });
/*     */     
/*  61 */     addCanUseCheck(HakiHelper::canEnableHaki);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/*  67 */     Color clientRGB = WyHelper.intToRGB(HakiHelper.getHaoshokuColour(entity), 50);
/*     */     
/*  69 */     AbilityOverlay overlay = (new AbilityOverlay.Builder()).setOverlayPart(AbilityOverlay.OverlayPart.LIMB).setColor(clientRGB).build();
/*     */     
/*  71 */     this.skinOverlayComponent.show(entity, overlay);
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/*  75 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  79 */     boolean isOnMaxOveruse = HakiHelper.checkForHakiOveruse(entity, 3);
/*     */     
/*  81 */     if (isOnMaxOveruse) {
/*  82 */       this.continuousComponent.stopContinuity(entity);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/*  87 */     this.skinOverlayComponent.hideAll(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/*  92 */     source.bypassLogia();
/*     */     
/*  94 */     if (entity.func_70681_au().nextInt(10) < 1) {
/*  95 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.HAKI_RELEASE_SFX.get(), SoundCategory.PLAYERS, 2.0F, 0.7F);
/*     */     }
/*     */     
/*  98 */     Color clientRGB = WyHelper.intToRGB(HakiHelper.getHaoshokuColour(entity), 50);
/*     */     
/* 100 */     LightningDischargeEntity discharge = new LightningDischargeEntity((Entity)target, target.func_226277_ct_(), target.func_226278_cu_() + 1.5D, target.func_226281_cx_(), target.field_70177_z, target.field_70125_A);
/*     */     
/* 102 */     discharge.setAliveTicks(15);
/* 103 */     discharge.setLightningLength(6.0F);
/* 104 */     discharge.setColor(new Color(0, 0, 0, 100));
/* 105 */     discharge.setOutlineColor(clientRGB);
/* 106 */     discharge.setRenderTransparent();
/* 107 */     discharge.setDetails(4);
/* 108 */     discharge.setDensity(4);
/* 109 */     discharge.setSize(1.0F);
/* 110 */     discharge.setSkipSegments(1);
/*     */     
/* 112 */     entity.field_70170_p.func_217376_c((Entity)discharge);
/*     */     
/* 114 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Predicate<LivingEntity> canActivate() {
/* 119 */     return entity -> this.continuousComponent.isContinuous();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUseLimit() {
/* 124 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isParallel() {
/* 129 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getPunchCooldown() {
/* 134 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public static double getDamageBoost(LivingEntity entity, float originalAmount) {
/* 138 */     IEntityStats props = EntityStatsCapability.get(entity);
/* 139 */     IHakiData hakiProps = HakiDataCapability.get(entity);
/*     */     
/* 141 */     double dorikiMultiplier = props.getDoriki() / CommonConfig.INSTANCE.getDorikiLimit();
/* 142 */     float hakiMultiplier = hakiProps.getBusoshokuHakiExp() / CommonConfig.INSTANCE.getHakiExpLimit();
/*     */     
/* 144 */     return 10.0D + originalAmount / 100.0D * 70.0D * (0.1D * dorikiMultiplier + 0.9D * hakiMultiplier);
/*     */   }
/*     */   
/*     */   private AbilityAttributeModifier getEntryAttackDamage(double amount) {
/* 148 */     return new AbilityAttributeModifier(STRENGTH_UUID, INSTANCE, "Haoshoku Haki Infusion Attack Damage Modifier", amount, AttributeModifier.Operation.ADDITION);
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity user) {
/* 152 */     IHakiData props = HakiDataCapability.get(user);
/*     */     
/* 154 */     boolean hasHakiAbilities = AbilityDataCapability.get(user).hasUnlockedAbility(HaoshokuHakiAbility.INSTANCE);
/*     */     
/* 156 */     return (props.getTotalHakiExp() >= props.getMaxHakiExp() * 0.85F && hasHakiAbilities);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\haki\HaoshokuHakiInfusionAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */