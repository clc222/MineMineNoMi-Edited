/*     */ package xyz.pixelatedw.mineminenomi.abilities.goro;
/*     */ import java.awt.Color;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraftforge.common.ForgeMod;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.EntityEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.PropelledFlightAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.MorphComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SkinOverlayComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.LightningDischargeEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class VoltAmaruAbility extends Ability {
/*  52 */   private static final ResourceLocation DEFAULT_ICON = new ResourceLocation("mineminenomi", "textures/abilities/volt_amaru.png");
/*  53 */   private static final ResourceLocation ALT_ICON = new ResourceLocation("mineminenomi", "textures/abilities/alts/volt_amaru.png");
/*     */   
/*  55 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "volt_amaru", new Pair[] {
/*  56 */         (Pair)ImmutablePair.of("Transforms the user into a powerful, lightning giant massively boosting physical attributes and lightning attacks", null)
/*     */       });
/*     */   private static final int COOLDOWN = 1000;
/*     */   private static final int CHARGE_TIME = 20;
/*     */   private static final int HOLD_TIME = 400;
/*  61 */   private static final Color COLOR = WyHelper.hexToRGB("#F0EC7155");
/*     */   
/*  63 */   public static final AbilityCore<VoltAmaruAbility> INSTANCE = (new AbilityCore.Builder("Volt Amaru", AbilityCategory.DEVIL_FRUITS, VoltAmaruAbility::new))
/*  64 */     .addDescriptionLine(DESCRIPTION)
/*  65 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(1000.0F), ChargeComponent.getTooltip(20.0F), ContinuousComponent.getTooltip(400.0F), ChangeStatsComponent.getTooltip()
/*  66 */       }).setIcon(DEFAULT_ICON)
/*  67 */     .build();
/*     */   
/*  69 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::onChargeStart).addEndEvent(this::onChargeEnd);
/*     */   
/*  71 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::onContinuityStart).addEndEvent(this::onContinuityEnd);
/*     */   
/*  73 */   private final ChangeStatsComponent changeStatsComponent = new ChangeStatsComponent((IAbility)this);
/*     */   
/*  75 */   private final MorphComponent morphComponent = new MorphComponent((IAbility)this);
/*     */   
/*  77 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*     */   
/*  79 */   private final HitTriggerComponent hitTriggerComponent = (new HitTriggerComponent((IAbility)this)).addOnHitEvent(this::onHitEvent).addTryHitEvent(this::tryHitEvent);
/*     */   
/*  81 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/*     */   
/*  83 */   private static final AbilityAttributeModifier REACH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ATTACK_REACH_UUID, INSTANCE, "Volt Amaru Reach Modifier", 4.8D, AttributeModifier.Operation.ADDITION);
/*  84 */   private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE = new AbilityAttributeModifier(AttributeHelper.MORPH_KNOCKBACK_RESISTANCE_UUID, INSTANCE, "Volt Amaru Knockback Resistance Modifier", 2.0D, AttributeModifier.Operation.ADDITION);
/*  85 */   private static final AbilityAttributeModifier TOUGHNESS_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_TOUGHNESS_UUID, INSTANCE, "Volt Amaru Toughness Modifier", 2.0D, AttributeModifier.Operation.ADDITION);
/*  86 */   private static final AbilityAttributeModifier HEALTH_BOOST = new AbilityAttributeModifier(AttributeHelper.MORPH_HEALTH_UUID, INSTANCE, "Volt Amaru Health Modifier", 20.0D, AttributeModifier.Operation.ADDITION);
/*  87 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_STRENGTH_UUID, INSTANCE, "Volt Amaru Strength Modifier", 12.0D, AttributeModifier.Operation.ADDITION);
/*  88 */   private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ATTACK_SPEED_UUID, INSTANCE, "Volt Amaru Attack Speed Modifier", 1.0D, AttributeModifier.Operation.MULTIPLY_BASE);
/*  89 */   private static final AbilityAttributeModifier JUMP_BOOST = new AbilityAttributeModifier(AttributeHelper.MORPH_JUMP_BOOST_UUID, INSTANCE, "Volt Amaru Jump Modifier", 5.0D, AttributeModifier.Operation.MULTIPLY_BASE);
/*     */   
/*  91 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setColor(COLOR).setRenderType(AbilityOverlay.RenderType.ENERGY).build();
/*  92 */   private static final AbilityOverlay OVERLAY_ALT = (new AbilityOverlay.Builder()).setColor(ElThorAbility.BLUE_THUNDER).setRenderType(AbilityOverlay.RenderType.ENERGY).build();
/*     */ 
/*     */ 
/*     */   
/*  96 */   public float speed = 0.0F;
/*     */   
/*     */   public VoltAmaruAbility(AbilityCore<VoltAmaruAbility> core) {
/*  99 */     super(core);
/*     */     
/* 101 */     this.isNew = true;
/*     */     
/* 103 */     Predicate<LivingEntity> isMorphActive = entity -> this.morphComponent.isMorphed();
/*     */     
/* 105 */     this.changeStatsComponent.addAttributeModifier((Supplier)ForgeMod.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER, isMorphActive);
/* 106 */     this.changeStatsComponent.addAttributeModifier((Supplier)ModAttributes.TOUGHNESS, (AttributeModifier)TOUGHNESS_MODIFIER, isMorphActive);
/* 107 */     this.changeStatsComponent.addAttributeModifier(Attributes.field_233818_a_, (AttributeModifier)HEALTH_BOOST, isMorphActive);
/* 108 */     this.changeStatsComponent.addAttributeModifier((Supplier)ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER, isMorphActive);
/* 109 */     this.changeStatsComponent.addAttributeModifier(Attributes.field_233825_h_, (AttributeModifier)ATTACK_SPEED_MODIFIER, isMorphActive);
/* 110 */     this.changeStatsComponent.addAttributeModifier((Supplier)ModAttributes.JUMP_HEIGHT, (AttributeModifier)JUMP_BOOST, isMorphActive);
/* 111 */     this.changeStatsComponent.addAttributeModifier((Supplier)ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER, isMorphActive);
/* 112 */     this.changeStatsComponent.addAttributeModifier(Attributes.field_233820_c_, (AttributeModifier)KNOCKBACK_RESISTANCE, isMorphActive);
/*     */     
/* 114 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.continuousComponent, (AbilityComponent)this.changeStatsComponent, (AbilityComponent)this.morphComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.hitTriggerComponent, (AbilityComponent)this.skinOverlayComponent });
/*     */     
/* 116 */     addUseEvent(this::onUseEvent);
/* 117 */     addEquipEvent(this::equipEvent);
/*     */   }
/*     */   
/*     */   public void equipEvent(LivingEntity entity, Ability ability) {
/* 121 */     setDisplayIcon(DEFAULT_ICON);
/* 122 */     if (ClientConfig.INSTANCE.isGoroBlue()) {
/* 123 */       setDisplayIcon(ALT_ICON);
/* 124 */       this.skinOverlayComponent.removeOverlay(OVERLAY);
/* 125 */       this.skinOverlayComponent.addOverlay(OVERLAY_ALT);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 144 */     if (!this.continuousComponent.isContinuous()) {
/* 145 */       this.chargeComponent.startCharging(entity, 20.0F);
/*     */     } else {
/* 147 */       this.continuousComponent.stopContinuity(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onChargeStart(LivingEntity entity, IAbility ability) {
/* 152 */     this.skinOverlayComponent.showAll(entity);
/*     */     
/* 154 */     LightningDischargeEntity ball = new LightningDischargeEntity((Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), entity.field_70177_z, entity.field_70125_A);
/*     */     
/* 156 */     ball.setSize(4.0F);
/* 157 */     ball.setLightningLength(10.0F);
/* 158 */     ball.setAliveTicks(20);
/* 159 */     ball.setColor(ClientConfig.INSTANCE.isGoroBlue() ? ElThorAbility.BLUE_THUNDER : ElThorAbility.YELLOW_THUNDER);
/*     */     
/* 161 */     entity.field_70170_p.func_217376_c((Entity)ball);
/*     */     
/* 163 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 20, 1, false, false));
/*     */   }
/*     */   
/*     */   private void onChargeEnd(LivingEntity entity, IAbility ability) {
/* 167 */     this.continuousComponent.startContinuity(entity, 400.0F);
/*     */   }
/*     */   
/*     */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/* 171 */     this.morphComponent.startMorph(entity, (MorphInfo)ModMorphs.VOLT_AMARU.get());
/* 172 */     this.changeStatsComponent.applyModifiers(entity);
/*     */     
/* 174 */     MinecraftForge.EVENT_BUS.post((Event)new EntityEvent.Size((Entity)entity, entity.func_213283_Z(), entity.func_213305_a(entity.func_213283_Z()), entity.func_213302_cg()));
/*     */     
/* 176 */     entity.func_213323_x_();
/*     */     
/* 178 */     if (!entity.field_70170_p.field_72995_K) {
/* 179 */       WyNetwork.sendToAllTrackingAndSelf(new SRecalculateEyeHeightPacket(entity.func_145782_y()), (Entity)entity);
/*     */     }
/*     */     
/* 182 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*     */     
/* 184 */     if (abilityDataProps == null) {
/*     */       return;
/*     */     }
/*     */     
/* 188 */     PropelledFlightAbility voltAmaruFlightAbility = (PropelledFlightAbility)abilityDataProps.getPassiveAbility(VoltAmaruFlightAbility.INSTANCE);
/*     */     
/* 190 */     if (voltAmaruFlightAbility != null && !voltAmaruFlightAbility.isPaused()) {
/* 191 */       ((PlayerEntity)entity).field_71075_bZ.field_75100_b = true;
/*     */       
/* 193 */       PropelledFlightAbility.enableFlight((PlayerEntity)entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 198 */     this.skinOverlayComponent.hideAll(entity);
/* 199 */     this.morphComponent.stopMorph(entity);
/* 200 */     this.changeStatsComponent.removeModifiers(entity);
/*     */     
/* 202 */     this.cooldownComponent.startCooldown(entity, 1000.0F);
/*     */     
/* 204 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*     */     
/* 206 */     if (abilityDataProps == null) {
/*     */       return;
/*     */     }
/*     */     
/* 210 */     PropelledFlightAbility voltAmaruFlightAbility = (PropelledFlightAbility)abilityDataProps.getPassiveAbility(VoltAmaruFlightAbility.INSTANCE);
/*     */     
/* 212 */     if (voltAmaruFlightAbility != null) {
/* 213 */       PropelledFlightAbility.disableFlight((PlayerEntity)entity);
/*     */     }
/*     */   }
/*     */   
/*     */   private HitTriggerComponent.HitResult tryHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 218 */     if (!this.morphComponent.isMorphed()) {
/* 219 */       return HitTriggerComponent.HitResult.PASS;
/*     */     }
/*     */     
/* 222 */     return HitTriggerComponent.HitResult.HIT;
/*     */   }
/*     */   
/*     */   private boolean onHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 226 */     float conductivity = ModTags.Items.CONDUCTIVE.getValue((IForgeRegistryEntry)entity.func_184614_ca().func_77973_b());
/*     */     
/* 228 */     if (conductivity > 0.5F) {
/* 229 */       AbilityHelper.setSecondsOnFireBy((Entity)target, 5, entity);
/*     */ 
/*     */ 
/*     */       
/* 233 */       ModDamageSource newSource = (ModDamageSource)this.dealDamageComponent.getDamageSource(entity);
/* 234 */       newSource.ignore();
/* 235 */       this.dealDamageComponent.hurtTarget(entity, target, conductivity * 3.0F, (DamageSource)newSource);
/*     */     } 
/*     */     
/* 238 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\goro\VoltAmaruAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */