/*     */ package xyz.pixelatedw.mineminenomi.abilities.jiki;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.MorphComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class PunkCornaDioAbility extends Ability {
/*  45 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "punk_corna_dio", new Pair[] {
/*  46 */         (Pair)ImmutablePair.of("Uses %s magnetic items from the inventory to create a bull which can then be used to smash into enemies dealing huge damage, knocking them away and potentially stunning them.", new Object[] { AbilityHelper.mentionText(Integer.valueOf(160)) }) });
/*     */   
/*     */   private static final int REQUIRED_IRON = 160;
/*     */   private static final int CHARGE_TIME = 60;
/*     */   private static final int HOLD_TIME = 100;
/*     */   private static final int COOLDOWN = 200;
/*     */   private static final int RANGE = 6;
/*     */   private static final float DAMAGE = 100.0F;
/*  54 */   public static final AbilityCore<PunkCornaDioAbility> INSTANCE = (new AbilityCore.Builder("Punk Corna Dio", AbilityCategory.DEVIL_FRUITS, PunkCornaDioAbility::new))
/*  55 */     .addDescriptionLine(DESCRIPTION)
/*  56 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ChargeComponent.getTooltip(60.0F), DealDamageComponent.getTooltip(100.0F)
/*  57 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/*  58 */     .setSourceElement(SourceElement.METAL)
/*  59 */     .setSourceType(new SourceType[] { SourceType.BLUNT
/*  60 */       }).build();
/*     */   
/*  62 */   private static final AbilityAttributeModifier TOUGHNESS_MODIFIER = new AbilityAttributeModifier(UUID.fromString("aa76fb8e-9123-424c-954a-7e19007491f8"), INSTANCE, "Punk Corna DIO Toughness Modifier", 3.0D, AttributeModifier.Operation.ADDITION);
/*  63 */   private static final AbilityAttributeModifier STEP_HEIGHT_MODIFIER = new AbilityAttributeModifier(UUID.fromString("d9824f43-df79-4664-a0a6-b4324236834e"), INSTANCE, "Punk Corna DIO Step Height Modifier", 3.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/*  65 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::startChargeEvent).addTickEvent(this::duringChargeEvent).addEndEvent(this::endChargeEvent);
/*  66 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  67 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/*  68 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  69 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  70 */   private final MorphComponent morphComponent = new MorphComponent((IAbility)this);
/*  71 */   private final ChangeStatsComponent changeStatsComponent = new ChangeStatsComponent((IAbility)this);
/*     */   
/*  73 */   private List<ItemStack> magneticItems = new ArrayList<>();
/*     */   
/*     */   public PunkCornaDioAbility(AbilityCore<PunkCornaDioAbility> core) {
/*  76 */     super(core);
/*     */     
/*  78 */     this.isNew = true;
/*  79 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.continuousComponent, (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.morphComponent, (AbilityComponent)this.changeStatsComponent });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     this.changeStatsComponent.addAttributeModifier((Supplier)ModAttributes.TOUGHNESS, (AttributeModifier)TOUGHNESS_MODIFIER);
/*  89 */     this.changeStatsComponent.addAttributeModifier((Supplier)ModAttributes.STEP_HEIGHT, (AttributeModifier)STEP_HEIGHT_MODIFIER);
/*     */     
/*  91 */     addCanUseCheck(JikiHelper.getMetalicItemsCheck(160));
/*     */     
/*  93 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  97 */     if (this.continuousComponent.isContinuous()) {
/*     */       return;
/*     */     }
/* 100 */     this.chargeComponent.startCharging(entity, 60.0F);
/*     */   }
/*     */   
/*     */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/* 104 */     List<ItemStack> inventory = ItemsHelper.getAllInventoryItems(entity);
/* 105 */     this.magneticItems = JikiHelper.getMagneticItemsNeeded(inventory, 160.0F);
/*     */     
/* 107 */     this.morphComponent.startMorph(entity, (MorphInfo)ModMorphs.PUNK_CORNA_DIO.get());
/* 108 */     this.changeStatsComponent.applyModifiers(entity);
/* 109 */     this.hitTrackerComponent.clearHits();
/*     */   }
/*     */   
/*     */   private void duringChargeEvent(LivingEntity entity, IAbility ability) {
/* 113 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 2, 2, false, false));
/*     */   }
/*     */   
/*     */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/* 117 */     this.continuousComponent.startContinuity(entity, 100.0F);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 121 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 2, 2, false, false));
/*     */     
/* 123 */     if (this.magneticItems.size() <= 0) {
/* 124 */       this.continuousComponent.stopContinuity(entity);
/*     */     }
/*     */     
/* 127 */     Vector3d lookVec = entity.func_70040_Z();
/*     */     
/* 129 */     Vector3d speed = lookVec.func_216372_d(2.3D, 0.0D, 2.3D);
/* 130 */     entity.func_213315_a(MoverType.SELF, speed);
/*     */     
/* 132 */     if (this.continuousComponent.getContinueTime() % 25.0F == 0.0F) {
/* 133 */       this.hitTrackerComponent.clearHits();
/*     */     }
/*     */     
/* 136 */     Vector3d knockbackVec = lookVec.func_216372_d(4.0D, 1.0D, 4.0D);
/* 137 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 6.0F);
/*     */     
/* 139 */     for (LivingEntity target : targets) {
/* 140 */       if (this.hitTrackerComponent.canHit((Entity)target) && this.dealDamageComponent.hurtTarget(entity, target, 100.0F)) {
/* 141 */         if (WyHelper.randomDouble() > 0.75D) {
/* 142 */           entity.func_195064_c(new EffectInstance((Effect)ModEffects.DIZZY.get(), 100, 1, false, false));
/*     */         }
/*     */         
/* 145 */         AbilityHelper.setDeltaMovement((Entity)target, knockbackVec.field_72450_a, 0.2D, knockbackVec.field_72449_c);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 151 */     if (this.magneticItems != null && this.magneticItems.size() > 0) {
/* 152 */       ItemStack stack = this.magneticItems.get(0);
/* 153 */       ItemsHelper.itemBreakParticles(entity, 160, stack);
/* 154 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), SoundEvents.field_187635_cQ, entity.func_184176_by(), 0.5F, 1.0F);
/* 155 */       JikiHelper.dropComponentItems(entity, entity.func_213303_ch(), this.magneticItems);
/*     */     } 
/*     */     
/* 158 */     this.morphComponent.stopMorph(entity);
/* 159 */     this.changeStatsComponent.removeModifiers(entity);
/*     */     
/* 161 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\jiki\PunkCornaDioAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */