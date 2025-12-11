/*     */ package xyz.pixelatedw.mineminenomi.abilities.gomu;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.PropelledFlightAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.MorphAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireAbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SkinOverlayComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.StackComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class GearFourthAbility extends MorphAbility2 {
/*  41 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "gear_fourth", new Pair[] {
/*  42 */         (Pair)ImmutablePair.of("The user inflates their muscle structure to tremendously increase the power of their attacks and also allows flight.", null)
/*     */       });
/*     */   
/*     */   private static final int HOLD_TIME = 1000;
/*     */   private static final int MIN_COOLDOWN = 100;
/*     */   private static final float MAX_COOLDOWN = 666.6667F;
/*  48 */   public static final AbilityCore<GearFourthAbility> INSTANCE = (new AbilityCore.Builder("Gear Fourth", AbilityCategory.DEVIL_FRUITS, GearFourthAbility::new))
/*  49 */     .addDescriptionLine(DESCRIPTION)
/*  50 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireAbilityComponent.getTooltip()
/*  51 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F, 666.6667F), ContinuousComponent.getTooltip(1000.0F), ChangeStatsComponent.getTooltip()
/*  52 */       }).build();
/*     */   
/*  54 */   private static final AbilityAttributeModifier ARMOR_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ARMOR_UUID, INSTANCE, "Gear Fourth Armor Modifier", 10.0D, AttributeModifier.Operation.ADDITION);
/*  55 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_STRENGTH_UUID, INSTANCE, "Gear Fourth Attack Damage Modifier", 15.0D, AttributeModifier.Operation.ADDITION);
/*  56 */   private static final AbilityAttributeModifier TOUGHNESS_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_TOUGHNESS_UUID, INSTANCE, "Gear Fourth Toughness Modifier", 2.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/*  58 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setTexture(ModResources.G4_OVERLAY).build();
/*  59 */   private static final RequireAbilityComponent.CheckData HAKI_CHECK = new RequireAbilityComponent.CheckData(BusoshokuHakiFullBodyHardeningAbility.INSTANCE, RequireAbilityComponent.START_IF_NOT_ACTIVE);
/*     */   
/*  61 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/*  62 */   private final RequireAbilityComponent requireAbilityComponent = new RequireAbilityComponent((IAbility)this, HAKI_CHECK, new RequireAbilityComponent.CheckData[0]);
/*  63 */   private final StackComponent stackComponent = (new StackComponent((IAbility)this)).addStackChangeEvent(this::changeStackEvent);
/*     */   
/*  65 */   public float speed = 0.0F;
/*     */   
/*     */   public GearFourthAbility(AbilityCore<GearFourthAbility> core) {
/*  68 */     super(core);
/*     */     
/*  70 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.skinOverlayComponent, (AbilityComponent)this.requireAbilityComponent, (AbilityComponent)this.stackComponent });
/*     */     
/*  72 */     this.statsComponent.addAttributeModifier(Attributes.field_233826_i_, (AttributeModifier)ARMOR_MODIFIER);
/*  73 */     this.statsComponent.addAttributeModifier(Attributes.field_233827_j_, (AttributeModifier)ARMOR_MODIFIER);
/*  74 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
/*  75 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.TOUGHNESS, (AttributeModifier)TOUGHNESS_MODIFIER);
/*     */     
/*  77 */     this.continuousComponent.addStartEvent(this::startContinuityEvent);
/*  78 */     this.continuousComponent.addTickEvent(this::duringContinuityEvent);
/*  79 */     this.continuousComponent.addEndEvent(this::endContinuityEvent);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  83 */     this.stackComponent.setDefaultStacks(0);
/*  84 */     this.skinOverlayComponent.showAll(entity);
/*     */     
/*  86 */     IAbilityData props = AbilityDataCapability.get(entity);
/*     */     
/*  88 */     GomuGomuNoPistolAbility pistol = (GomuGomuNoPistolAbility)props.getEquippedAbility(GomuGomuNoPistolAbility.INSTANCE);
/*     */     
/*  90 */     if (pistol != null) {
/*  91 */       pistol.switchFourthGear(entity);
/*     */     }
/*     */     
/*  94 */     GomuGomuNoGatlingAbility gatling = (GomuGomuNoGatlingAbility)props.getEquippedAbility(GomuGomuNoGatlingAbility.INSTANCE);
/*     */     
/*  96 */     if (gatling != null) {
/*  97 */       gatling.switchFourthGear(entity);
/*     */     }
/*     */     
/* 100 */     GomuGomuNoBazookaAbility bazooka = (GomuGomuNoBazookaAbility)props.getEquippedAbility(GomuGomuNoBazookaAbility.INSTANCE);
/*     */     
/* 102 */     if (bazooka != null) {
/* 103 */       bazooka.switchFourthGear(entity);
/*     */     }
/*     */     
/* 106 */     PropelledFlightAbility flightAbility = (PropelledFlightAbility)props.getPassiveAbility(GearFourthFlightAbility.INSTANCE);
/*     */     
/* 108 */     if (flightAbility != null && !flightAbility.isPaused()) {
/* 109 */       PropelledFlightAbility.enableFlight((PlayerEntity)entity);
/*     */     }
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 114 */     float continueTime = this.continuousComponent.getContinueTime();
/*     */     
/* 116 */     if (continueTime % 10.0F == 0.0F) {
/* 117 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.GEAR_SECOND.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_() + 1.0D, entity.func_226281_cx_());
/*     */     }
/*     */     
/* 120 */     int stacks = this.stackComponent.getStacks();
/*     */     
/* 122 */     if (stacks == 0 && continueTime > WyHelper.percentage(50.0D, this.continuousComponent.getThresholdTime())) {
/* 123 */       this.stackComponent.addStacks(entity, ability, 1);
/* 124 */     } else if (stacks == 1 && continueTime > WyHelper.percentage(70.0D, this.continuousComponent.getThresholdTime())) {
/* 125 */       this.stackComponent.addStacks(entity, ability, 1);
/* 126 */     } else if (stacks == 2 && continueTime > WyHelper.percentage(90.0D, this.continuousComponent.getThresholdTime())) {
/* 127 */       this.stackComponent.addStacks(entity, ability, 1);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 132 */     this.skinOverlayComponent.hideAll(entity);
/* 133 */     this.stackComponent.revertStacksToDefault(entity, ability);
/*     */     
/* 135 */     IAbilityData props = AbilityDataCapability.get(entity);
/*     */     
/* 137 */     GomuGomuNoPistolAbility pistol = (GomuGomuNoPistolAbility)props.getEquippedAbility(GomuGomuNoPistolAbility.INSTANCE);
/*     */     
/* 139 */     if (pistol != null) {
/* 140 */       pistol.switchNoGear(entity);
/*     */     }
/*     */     
/* 143 */     GomuGomuNoGatlingAbility gatling = (GomuGomuNoGatlingAbility)props.getEquippedAbility(GomuGomuNoGatlingAbility.INSTANCE);
/*     */     
/* 145 */     if (gatling != null) {
/* 146 */       gatling.switchNoGear(entity);
/*     */     }
/*     */     
/* 149 */     GomuGomuNoBazookaAbility bazooka = (GomuGomuNoBazookaAbility)props.getEquippedAbility(GomuGomuNoBazookaAbility.INSTANCE);
/*     */     
/* 151 */     if (bazooka != null) {
/* 152 */       bazooka.switchNoGear(entity);
/*     */     }
/*     */     
/* 155 */     PropelledFlightAbility flightAbility = (PropelledFlightAbility)props.getPassiveAbility(GearFourthFlightAbility.INSTANCE);
/*     */     
/* 157 */     if (flightAbility != null) {
/* 158 */       PropelledFlightAbility.disableFlight((PlayerEntity)entity);
/*     */     }
/*     */     
/* 161 */     float cooldown = Math.max(100.0F, this.continuousComponent.getContinueTime() / 1.5F);
/*     */     
/* 163 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*     */   }
/*     */   
/*     */   private void changeStackEvent(LivingEntity entity, IAbility ability, int stacks) {
/* 167 */     if (stacks == 1) {
/* 168 */       entity.func_195064_c(new EffectInstance(Effects.field_76438_s, 200, 0, true, true));
/* 169 */       entity.func_195064_c(new EffectInstance(Effects.field_76421_d, 200, 0, true, true));
/* 170 */     } else if (stacks >= 2) {
/* 171 */       entity.func_195064_c(new EffectInstance(Effects.field_76438_s, 400, 0, true, true));
/* 172 */       entity.func_195064_c(new EffectInstance(Effects.field_76421_d, 400, 0, true, true));
/* 173 */       entity.func_195064_c(new EffectInstance(Effects.field_76437_t, 400, 0, true, true));
/*     */       
/* 175 */       if (stacks > 2) {
/* 176 */         this.stackComponent.revertStacksToDefault(entity, ability);
/* 177 */         this.continuousComponent.stopContinuity(entity);
/* 178 */         this.disableComponent.startDisable(entity, 400.0F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public MorphInfo getTransformation() {
/* 185 */     return (MorphInfo)ModMorphs.GEAR_FOURTH.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getContinuityHoldTime() {
/* 190 */     return 1000.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gomu\GearFourthAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */