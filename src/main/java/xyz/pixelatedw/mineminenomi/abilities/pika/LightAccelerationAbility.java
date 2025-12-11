/*     */ package xyz.pixelatedw.mineminenomi.abilities.pika;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SkinOverlayComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SwingTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.pika.AmaterasuProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ 
/*     */ public class LightAccelerationAbility
/*     */   extends Ability {
/*  38 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "light_acceleration", new Pair[] {
/*  39 */         (Pair)ImmutablePair.of("The user accelerates its attacks by converting into light before impact", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 200;
/*     */   private static final int HOLD_TIME = 400;
/*  44 */   public static final AbilityCore<LightAccelerationAbility> INSTANCE = (new AbilityCore.Builder("Light Acceleration", AbilityCategory.DEVIL_FRUITS, LightAccelerationAbility::new))
/*  45 */     .addDescriptionLine(DESCRIPTION)
/*  46 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ContinuousComponent.getTooltip(400.0F), ChangeStatsComponent.getTooltip()
/*  47 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  48 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  49 */       }).setSourceElement(SourceElement.LIGHT)
/*  50 */     .build();
/*     */   
/*  52 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::onContinuityStart).addTickEvent(this::duringContinuity).addEndEvent(this::onContinuityEnd);
/*  53 */   private final SwingTriggerComponent swingTriggerComponent = (new SwingTriggerComponent((IAbility)this)).addSwingEvent(0, this::onSwing);
/*  54 */   private final ChangeStatsComponent changeStatsComponent = new ChangeStatsComponent((IAbility)this);
/*  55 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/*  56 */   private final HitTriggerComponent hitTriggerComponent = (new HitTriggerComponent((IAbility)this)).addTryHitEvent(this::tryHitEvent);
/*     */   
/*  58 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setOverlayPart(AbilityOverlay.OverlayPart.LIMB).setRenderType(AbilityOverlay.RenderType.ENERGY).setColor(new Color(255, 220, 0)).build();
/*     */   
/*  60 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = new AbilityAttributeModifier(UUID.fromString("ef9423e5-9be0-4223-a34a-538cb74d6e54"), INSTANCE, "Light Acceleration Strength Modifier", 10.0D, AttributeModifier.Operation.ADDITION);
/*  61 */   private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = new AbilityAttributeModifier(UUID.fromString("1d78a133-8a0e-4b8f-8790-1360007d4741"), INSTANCE, "Light Acceleration Attack Speed Modifier", 4.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/*     */   private boolean hasSwung = false;
/*     */   private boolean hasTarget = false;
/*     */   
/*     */   public LightAccelerationAbility(AbilityCore<LightAccelerationAbility> core) {
/*  67 */     super(core);
/*     */     
/*  69 */     this.isNew = true;
/*     */     
/*  71 */     this.changeStatsComponent.addAttributeModifier(Attributes.field_233823_f_, (AttributeModifier)STRENGTH_MODIFIER, entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b()));
/*  72 */     this.changeStatsComponent.addAttributeModifier(Attributes.field_233825_h_, (AttributeModifier)ATTACK_SPEED_MODIFIER, entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b()));
/*     */     
/*  74 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.swingTriggerComponent, (AbilityComponent)this.changeStatsComponent, (AbilityComponent)this.skinOverlayComponent, (AbilityComponent)this.hitTriggerComponent });
/*     */     
/*  76 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  80 */     this.continuousComponent.triggerContinuity(entity, 400.0F);
/*     */   }
/*     */   
/*     */   private void onSwing(LivingEntity entity, IAbility ability) {
/*  84 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  88 */     if (this.continuousComponent.isContinuous()) {
/*  89 */       this.hasTarget = false;
/*  90 */       this.hasSwung = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/*  95 */     this.skinOverlayComponent.showAll(entity);
/*     */   }
/*     */   
/*     */   private void duringContinuity(LivingEntity entity, IAbility ability) {
/*  99 */     if (!this.hasTarget && this.hasSwung) {
/* 100 */       this.hasSwung = false;
/*     */       
/* 102 */       AmaterasuProjectile proj = new AmaterasuProjectile(entity.field_70170_p, entity);
/*     */       
/* 104 */       proj.setDamage(40.0F);
/* 105 */       proj.setArmorPiercing(0.5F);
/* 106 */       proj.setMaxLife(30);
/*     */       
/* 108 */       entity.field_70170_p.func_217376_c((Entity)proj);
/*     */       
/* 110 */       proj.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z, 0.0F, 5.0F, 1.0F);
/*     */       
/* 112 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.PIKA_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*     */       
/* 114 */       this.continuousComponent.stopContinuity(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 119 */     this.skinOverlayComponent.hideAll(entity);
/*     */     
/* 121 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*     */   }
/*     */   
/*     */   private HitTriggerComponent.HitResult tryHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 125 */     if (this.continuousComponent.isContinuous()) {
/* 126 */       this.hasTarget = true;
/*     */     }
/*     */     
/* 129 */     return HitTriggerComponent.HitResult.PASS;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\pika\LightAccelerationAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */