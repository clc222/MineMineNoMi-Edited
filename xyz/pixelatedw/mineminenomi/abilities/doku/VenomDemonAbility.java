/*     */ package xyz.pixelatedw.mineminenomi.abilities.doku;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraftforge.common.ForgeMod;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.MorphAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ 
/*     */ public class VenomDemonAbility extends MorphAbility2 {
/*  39 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "venom_demon", new Pair[] {
/*  40 */         (Pair)ImmutablePair.of("The user coats themselves in layers of strong corrosive venom, becoming a Venom Demon and leaving a highly poisonous trail. Also enhances all Posion abilities.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 2000;
/*     */   private static final int HOLD_TIME = 1200;
/*  45 */   public static final AbilityCore<VenomDemonAbility> INSTANCE = (new AbilityCore.Builder("Venom Demon", AbilityCategory.DEVIL_FRUITS, VenomDemonAbility::new))
/*  46 */     .addDescriptionLine(DESCRIPTION)
/*  47 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(2000.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/*  48 */       }).build();
/*     */   
/*  50 */   private static final AbilityAttributeModifier ATTACK_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_STRENGTH_UUID, INSTANCE, "Venom Demon Attack Modifier", 8.0D, AttributeModifier.Operation.ADDITION);
/*  51 */   private static final AbilityAttributeModifier REACH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ATTACK_REACH_UUID, INSTANCE, "Venom Demon Reach Modifier", 3.0D, AttributeModifier.Operation.ADDITION);
/*  52 */   private static final AbilityAttributeModifier SPEED_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_MOVEMENT_SPEED_UUID, INSTANCE, "Venom Demon Speed Modifier", 0.02D, AttributeModifier.Operation.ADDITION);
/*  53 */   private static final AbilityAttributeModifier ATTACK_SPEED = new AbilityAttributeModifier(AttributeHelper.MORPH_ATTACK_SPEED_UUID, INSTANCE, "Venom Demon Attack Speed Modifier", 0.15D, AttributeModifier.Operation.ADDITION);
/*  54 */   private static final AbilityAttributeModifier STEP_ASSIST = new AbilityAttributeModifier(AttributeHelper.MORPH_STEP_HEIGHT_UUID, INSTANCE, "Venom Demon Step assist Modifier", 1.5D, AttributeModifier.Operation.ADDITION);
/*  55 */   private static final AbilityAttributeModifier JUMP_HEIGHT = new AbilityAttributeModifier(AttributeHelper.MORPH_JUMP_BOOST_UUID, INSTANCE, "Venom Demon Jump Height Modifier", 2.0D, AttributeModifier.Operation.ADDITION);
/*  56 */   private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE = new AbilityAttributeModifier(AttributeHelper.MORPH_KNOCKBACK_RESISTANCE_UUID, INSTANCE, "Venom Demon Knockback Resistance Modifier", 1.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/*  58 */   private final HitTriggerComponent hitTriggerComponent = (new HitTriggerComponent((IAbility)this)).addOnHitEvent(this::hitTriggerEvent).addTryHitEvent(this::tryHitEvent);
/*     */   
/*     */   public VenomDemonAbility(AbilityCore<VenomDemonAbility> core) {
/*  61 */     super(core);
/*     */     
/*  63 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.hitTriggerComponent });
/*     */     
/*  65 */     this.statsComponent.addAttributeModifier((Supplier)ForgeMod.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER);
/*  66 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
/*  67 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.PUNCH_DAMAGE, (AttributeModifier)ATTACK_MODIFIER);
/*  68 */     this.statsComponent.addAttributeModifier(Attributes.field_233821_d_, (AttributeModifier)SPEED_MODIFIER);
/*  69 */     this.statsComponent.addAttributeModifier(Attributes.field_233825_h_, (AttributeModifier)ATTACK_SPEED);
/*  70 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.STEP_HEIGHT, (AttributeModifier)STEP_ASSIST);
/*  71 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.FALL_RESISTANCE, (AttributeModifier)STEP_ASSIST);
/*  72 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.JUMP_HEIGHT, (AttributeModifier)JUMP_HEIGHT);
/*  73 */     this.statsComponent.addAttributeModifier(Attributes.field_233820_c_, (AttributeModifier)KNOCKBACK_RESISTANCE);
/*     */     
/*  75 */     this.continuousComponent.addStartEvent(this::startContinuityEvent);
/*  76 */     this.continuousComponent.addTickEvent(this::duringContinuityEvent);
/*  77 */     this.continuousComponent.addEndEvent(80, this::earlyEndContinuityEvent);
/*  78 */     this.continuousComponent.addEndEvent(this::endContinuityEvent);
/*     */   }
/*     */   
/*     */   public void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  82 */     IAbilityData props = AbilityDataCapability.get(entity);
/*     */     
/*  84 */     HydraAbility hydra = (HydraAbility)props.getEquippedAbility(HydraAbility.INSTANCE);
/*  85 */     if (hydra != null) {
/*  86 */       hydra.setVenomMode(entity);
/*     */     }
/*     */     
/*  89 */     ChloroBallAbility chloroBall = (ChloroBallAbility)props.getEquippedAbility(ChloroBallAbility.INSTANCE);
/*  90 */     if (chloroBall != null) {
/*  91 */       chloroBall.setVenomMode(entity);
/*     */     }
/*     */     
/*  94 */     DokuGumoAbility dokuGumo = (DokuGumoAbility)props.getEquippedAbility(DokuGumoAbility.INSTANCE);
/*  95 */     if (dokuGumo != null) {
/*  96 */       dokuGumo.setVenomMode(entity);
/*     */     }
/*     */     
/*  99 */     VenomRoadAbility venomRoad = (VenomRoadAbility)props.getEquippedAbility(VenomRoadAbility.INSTANCE);
/* 100 */     if (venomRoad != null) {
/* 101 */       venomRoad.setVenomMode(entity);
/*     */     }
/*     */     
/* 104 */     DokuFuguAbility dokuFugu = (DokuFuguAbility)props.getEquippedAbility(DokuFuguAbility.INSTANCE);
/* 105 */     if (dokuFugu != null) {
/* 106 */       AbilityHelper.emergencyStopAbility(entity, (IAbility)dokuFugu);
/*     */     }
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 111 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 115 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.PHYSICAL_MOVING_GUARD.get(), 2, 3, false, false));
/*     */     
/* 117 */     if (!AbilityHelper.isWeakenedByKairosekiOrWater(entity)) {
/* 118 */       BlockPos.Mutable mutpos = new BlockPos.Mutable();
/* 119 */       for (int x = -1; x < 1; x++) {
/* 120 */         for (int z = -1; z < 1; z++) {
/* 121 */           mutpos.func_189532_c(entity.func_226277_ct_() + x, entity.func_226278_cu_(), entity.func_226281_cx_() + z);
/* 122 */           if (entity.field_70170_p.func_180495_p(mutpos.func_177977_b()).func_185904_a().func_76220_a()) {
/* 123 */             AbilityHelper.placeBlockIfAllowed(entity, (BlockPos)mutpos, ((Block)ModBlocks.DEMON_POISON.get()).func_176223_P(), DefaultProtectionRules.AIR_FOLIAGE);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 129 */     if (this.continuousComponent.getContinueTime() % 2.0F == 0.0F) {
/* 130 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.VENOM_DEMON.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     }
/*     */   }
/*     */   
/*     */   private void earlyEndContinuityEvent(LivingEntity entity, IAbility ability) {
/* 135 */     this.cooldownComponent.startCooldown(entity, 2000.0F);
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 139 */     IAbilityData props = AbilityDataCapability.get(entity);
/*     */     
/* 141 */     HydraAbility hydra = (HydraAbility)props.getEquippedAbility(HydraAbility.INSTANCE);
/* 142 */     if (hydra != null) {
/* 143 */       hydra.setNormalMode(entity);
/*     */     }
/*     */     
/* 146 */     ChloroBallAbility chloroBall = (ChloroBallAbility)props.getEquippedAbility(ChloroBallAbility.INSTANCE);
/* 147 */     if (chloroBall != null) {
/* 148 */       chloroBall.setNormalMode(entity);
/*     */     }
/*     */     
/* 151 */     DokuGumoAbility dokuGumo = (DokuGumoAbility)props.getEquippedAbility(DokuGumoAbility.INSTANCE);
/* 152 */     if (dokuGumo != null) {
/* 153 */       dokuGumo.setNormalMode(entity);
/*     */     }
/*     */     
/* 156 */     VenomRoadAbility venomRoad = (VenomRoadAbility)props.getEquippedAbility(VenomRoadAbility.INSTANCE);
/* 157 */     if (venomRoad != null) {
/* 158 */       venomRoad.setNormalMode(entity);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public MorphInfo getTransformation() {
/* 164 */     return (MorphInfo)ModMorphs.VENOM_DEMON.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getContinuityHoldTime() {
/* 169 */     return 1200.0F;
/*     */   }
/*     */   
/*     */   private HitTriggerComponent.HitResult tryHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 173 */     if (!this.continuousComponent.isContinuous() || !entity.func_184614_ca().func_190926_b()) {
/* 174 */       return HitTriggerComponent.HitResult.PASS;
/*     */     }
/*     */     
/* 177 */     return HitTriggerComponent.HitResult.HIT;
/*     */   }
/*     */   
/*     */   public boolean hitTriggerEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 181 */     source.setFistDamage().setSourceElement(SourceElement.POISON);
/* 182 */     target.func_195064_c(new EffectInstance((Effect)ModEffects.DOKU_POISON.get(), 100, 1));
/*     */     
/* 184 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\doku\VenomDemonAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */