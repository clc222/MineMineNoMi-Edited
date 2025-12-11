/*     */ package xyz.pixelatedw.mineminenomi.abilities.haki;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SkinOverlayComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class BusoshokuHakiFullBodyHardeningAbility extends Ability {
/*  45 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "busoshoku_haki_full_body_hardening", new Pair[] {
/*  46 */         (Pair)ImmutablePair.of("Covers the whole body of the user user in a layer of Armament haki, used for a balance between offense and defense.", null)
/*     */       });
/*  48 */   public static final AbilityCore<BusoshokuHakiFullBodyHardeningAbility> INSTANCE = (new AbilityCore.Builder("Busoshoku Haki: Full Body Hardening", AbilityCategory.HAKI, BusoshokuHakiFullBodyHardeningAbility::new))
/*  49 */     .addDescriptionLine(DESCRIPTION)
/*  50 */     .setUnlockCheck(BusoshokuHakiFullBodyHardeningAbility::canUnlock)
/*  51 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  52 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  53 */     .build();
/*     */   
/*  55 */   public static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setTexture(ModResources.BUSOSHOKU_HAKI_ARM).setColor(WyHelper.hexToRGB("#FFFFFFAA")).build();
/*     */   
/*     */   private Predicate<LivingEntity> emptyHandPredicate;
/*  58 */   private static final UUID ARMOR_UUID = UUID.fromString("0457f786-0a5a-4e83-9ea6-f924c259a798");
/*  59 */   private static final UUID ARMOR_THOUGNESS_UUID = UUID.fromString("0457f786-0a5a-4e83-9ea6-f924c259a798");
/*  60 */   private static final UUID TOUGHNESS_UUID = UUID.fromString("9121ac66-fb1c-48a7-a636-0cdc3f01d96e");
/*     */   
/*  62 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::continuousStartEvent).addEndEvent(this::continuousStopEvent).addTickEvent(this::continuousTickEvent);
/*  63 */   private final ChangeStatsComponent statsComponent = new ChangeStatsComponent((IAbility)this);
/*  64 */   private final HitTriggerComponent hitTriggerComponent = (new HitTriggerComponent((IAbility)this)).addTryHitEvent(this::tryHitEvent).addOnHitEvent(this::onHitEvent);
/*  65 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/*  66 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.BODY_BUSOSHOKU_HAKI, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*     */   
/*     */   public BusoshokuHakiFullBodyHardeningAbility(AbilityCore<BusoshokuHakiFullBodyHardeningAbility> core) {
/*  69 */     super(core);
/*     */     
/*  71 */     this.isNew = true;
/*     */     
/*  73 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.statsComponent, (AbilityComponent)this.hitTriggerComponent, (AbilityComponent)this.skinOverlayComponent, (AbilityComponent)this.poolComponent });
/*  74 */     addCanUseCheck(HakiHelper::canEnableHaki);
/*  75 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  79 */     if (this.emptyHandPredicate == null) {
/*  80 */       this.emptyHandPredicate = (e -> (this.continuousComponent.isContinuous() && e.func_184614_ca().func_190926_b()));
/*     */     }
/*     */     
/*  83 */     IHakiData hakiProps = HakiDataCapability.get(entity);
/*  84 */     double defense = (hakiProps.getBusoshokuHakiExp() / 12.5F);
/*  85 */     this.statsComponent.addAttributeModifier(Attributes.field_233826_i_, (AttributeModifier)getArmorMod(defense), e -> this.continuousComponent.isContinuous());
/*  86 */     this.statsComponent.addAttributeModifier(Attributes.field_233827_j_, (AttributeModifier)getArmorThougnessMod(defense / 4.0D), e -> this.continuousComponent.isContinuous());
/*  87 */     this.statsComponent.addAttributeModifier((Attribute)ModAttributes.TOUGHNESS.get(), (AttributeModifier)getToughnessMod((8.0F * hakiProps.getBusoshokuHakiExp() / CommonConfig.INSTANCE.getHakiExpLimit())), e -> this.continuousComponent.isContinuous());
/*     */     
/*  89 */     this.continuousComponent.triggerContinuity(entity);
/*     */   }
/*     */   
/*     */   private void continuousStartEvent(LivingEntity entity, IAbility ability) {
/*  93 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.BUSOSHOKU_HAKI_SFX.get(), SoundCategory.PLAYERS, 2.0F, 0.8F);
/*     */     
/*  95 */     this.skinOverlayComponent.showAll(entity);
/*     */   }
/*     */   
/*     */   private void continuousStopEvent(LivingEntity entity, IAbility ability) {
/*  99 */     this.skinOverlayComponent.hideAll(entity);
/*     */   }
/*     */   
/*     */   private void continuousTickEvent(LivingEntity entity, IAbility ability) {
/* 103 */     boolean isOnMaxOveruse = HakiHelper.checkForHakiOveruse(entity, 0);
/* 104 */     if (isOnMaxOveruse) {
/* 105 */       this.continuousComponent.stopContinuity(entity);
/*     */     }
/*     */   }
/*     */   
/*     */   private HitTriggerComponent.HitResult tryHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 110 */     if (!this.continuousComponent.isContinuous()) {
/* 111 */       return HitTriggerComponent.HitResult.PASS;
/*     */     }
/*     */     
/* 114 */     return HitTriggerComponent.HitResult.HIT;
/*     */   }
/*     */   
/*     */   private boolean onHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 118 */     source.bypassLogia();
/*     */     
/* 120 */     return true;
/*     */   }
/*     */   
/*     */   private AbilityAttributeModifier getArmorMod(double amount) {
/* 124 */     return new AbilityAttributeModifier(ARMOR_UUID, INSTANCE, "Full Body Haki Armor Modifier", amount, AttributeModifier.Operation.ADDITION);
/*     */   }
/*     */   
/*     */   private AbilityAttributeModifier getArmorThougnessMod(double amount) {
/* 128 */     return new AbilityAttributeModifier(ARMOR_THOUGNESS_UUID, INSTANCE, "Full Body Haki Armor Toughness Modifier", amount, AttributeModifier.Operation.ADDITION);
/*     */   }
/*     */   
/*     */   private AbilityAttributeModifier getToughnessMod(double amount) {
/* 132 */     return new AbilityAttributeModifier(TOUGHNESS_UUID, INSTANCE, "Full Body Haki Toughness Modifier", amount, AttributeModifier.Operation.ADDITION);
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity user) {
/* 136 */     IAbilityData abilityProps = AbilityDataCapability.get(user);
/* 137 */     IHakiData props = HakiDataCapability.get(user);
/* 138 */     IEntityStats statsProps = EntityStatsCapability.get(user);
/*     */     
/* 140 */     boolean hasHardeningUnlocked = abilityProps.hasUnlockedAbility(BusoshokuHakiHardeningAbility.INSTANCE);
/*     */     
/* 142 */     return (hasHardeningUnlocked && statsProps.getDoriki() > 5000.0D && props.getBusoshokuHakiExp() > HakiHelper.getBusoshokuFullBodyExpNeeded(user));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\haki\BusoshokuHakiFullBodyHardeningAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */