/*     */ package xyz.pixelatedw.mineminenomi.abilities.gomu;
/*     */ import java.awt.Color;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SkinOverlayComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class GearSecondAbility extends Ability {
/*  36 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "gear_second", new Pair[] {
/*  37 */         (Pair)ImmutablePair.of("By speding up their blood flow, the user gains strength, speed and mobility.", null)
/*     */       });
/*     */   
/*     */   private static final int HOLD_TIME = 1000;
/*     */   private static final int MIN_COOLDOWN = 20;
/*     */   private static final float MAX_COOLDOWN = 666.6667F;
/*  43 */   public static final AbilityCore<GearSecondAbility> INSTANCE = (new AbilityCore.Builder("Gear Second", AbilityCategory.DEVIL_FRUITS, GearSecondAbility::new))
/*  44 */     .addDescriptionLine(DESCRIPTION)
/*  45 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(20.0F, 666.6667F), ContinuousComponent.getTooltip(1000.0F), ChangeStatsComponent.getTooltip()
/*  46 */       }).build();
/*     */   
/*  48 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setOverlayPart(AbilityOverlay.OverlayPart.BODY).setColor(new Color(232, 54, 54, 74)).build();
/*  49 */   private static final AbilityAttributeModifier JUMP_HEIGHT = new AbilityAttributeModifier(UUID.fromString("a44a9644-369a-4e18-88d9-323727d3d85b"), INSTANCE, "Gear Second Jump Modifier", 5.0D, AttributeModifier.Operation.ADDITION);
/*  50 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = new AbilityAttributeModifier(UUID.fromString("a2337b58-7e6d-4361-a8ca-943feee4f906"), INSTANCE, "Gear Second Attack Damage Modifier", 4.0D, AttributeModifier.Operation.ADDITION);
/*  51 */   private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = new AbilityAttributeModifier(UUID.fromString("c495cf01-f3ff-4933-9805-5bb1ed9d27b0"), INSTANCE, "Gear Second Attack Speed Modifier", 4.0D, AttributeModifier.Operation.ADDITION);
/*  52 */   private static final AbilityAttributeModifier STEP_HEIGHT = new AbilityAttributeModifier(UUID.fromString("eab680cd-a6dc-438a-99d8-46f9eb53a950"), INSTANCE, "Gear Second Step Height Modifier", 1.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/*  54 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  55 */   private final ChangeStatsComponent changeStatsComponent = new ChangeStatsComponent((IAbility)this);
/*  56 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/*     */   
/*     */   private boolean prevSprintValue = false;
/*     */   
/*     */   public GearSecondAbility(AbilityCore<GearSecondAbility> core) {
/*  61 */     super(core);
/*     */     
/*  63 */     this.isNew = true;
/*  64 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.changeStatsComponent, (AbilityComponent)this.skinOverlayComponent });
/*     */     
/*  66 */     this.changeStatsComponent.addAttributeModifier((Attribute)ModAttributes.JUMP_HEIGHT.get(), (AttributeModifier)JUMP_HEIGHT);
/*  67 */     this.changeStatsComponent.addAttributeModifier((Attribute)ModAttributes.STEP_HEIGHT.get(), (AttributeModifier)STEP_HEIGHT);
/*  68 */     this.changeStatsComponent.addAttributeModifier(Attributes.field_233825_h_, (AttributeModifier)ATTACK_SPEED_MODIFIER);
/*  69 */     this.changeStatsComponent.addAttributeModifier((Supplier)ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
/*     */     
/*  71 */     addCanUseCheck(GomuHelper.canUseGearCheck(INSTANCE));
/*  72 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  76 */     this.continuousComponent.triggerContinuity(entity, 1000.0F);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  80 */     this.changeStatsComponent.applyModifiers(entity);
/*  81 */     this.skinOverlayComponent.showAll(entity);
/*     */     
/*  83 */     IAbilityData props = AbilityDataCapability.get(entity);
/*     */     
/*  85 */     GomuGomuNoPistolAbility pistol = (GomuGomuNoPistolAbility)props.getEquippedAbility(GomuGomuNoPistolAbility.INSTANCE);
/*  86 */     if (pistol != null) {
/*  87 */       pistol.switchSecondGear(entity);
/*     */     }
/*     */     
/*  90 */     GomuGomuNoGatlingAbility gatling = (GomuGomuNoGatlingAbility)props.getEquippedAbility(GomuGomuNoGatlingAbility.INSTANCE);
/*  91 */     if (gatling != null) {
/*  92 */       gatling.switchSecondGear(entity);
/*     */     }
/*     */     
/*  95 */     GomuGomuNoBazookaAbility bazooka = (GomuGomuNoBazookaAbility)props.getEquippedAbility(GomuGomuNoBazookaAbility.INSTANCE);
/*  96 */     if (bazooka != null) {
/*  97 */       bazooka.switchSecondGear(entity);
/*     */     }
/*     */     
/* 100 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.GEAR_SECOND_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/* 101 */     this.prevSprintValue = entity.func_70051_ag();
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 105 */     if (this.continuousComponent.getContinueTime() % 10.0F == 0.0F) {
/* 106 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.GEAR_SECOND.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     }
/*     */     
/* 109 */     if (!AbilityHelper.canUseMomentumAbilities(entity)) {
/*     */       return;
/*     */     }
/*     */     
/* 113 */     if (entity.func_70051_ag()) {
/* 114 */       if (!this.prevSprintValue) {
/* 115 */         entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.TELEPORT_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*     */       }
/*     */     } else {
/*     */       
/* 119 */       this.prevSprintValue = false;
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 124 */     float maxSpeed = 1.75F;
/*     */     
/* 126 */     Vector3d vec = entity.func_70040_Z();
/*     */     
/* 128 */     if (entity.func_233570_aj_()) {
/* 129 */       AbilityHelper.setDeltaMovement((Entity)entity, vec.field_72450_a * maxSpeed, (entity.func_213322_ci()).field_72448_b, vec.field_72449_c * maxSpeed);
/*     */     } else {
/*     */       
/* 132 */       AbilityHelper.setDeltaMovement((Entity)entity, vec.field_72450_a * maxSpeed * 0.5D, (entity.func_213322_ci()).field_72448_b, vec.field_72449_c * maxSpeed * 0.5D);
/*     */     } 
/*     */     
/* 135 */     this.prevSprintValue = entity.func_70051_ag();
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 139 */     this.changeStatsComponent.removeModifiers(entity);
/* 140 */     this.skinOverlayComponent.hideAll(entity);
/*     */     
/* 142 */     IAbilityData props = AbilityDataCapability.get(entity);
/*     */     
/* 144 */     GomuGomuNoPistolAbility pistol = (GomuGomuNoPistolAbility)props.getEquippedAbility(GomuGomuNoPistolAbility.INSTANCE);
/* 145 */     if (pistol != null) {
/* 146 */       pistol.switchNoGear(entity);
/*     */     }
/*     */     
/* 149 */     GomuGomuNoGatlingAbility gatling = (GomuGomuNoGatlingAbility)props.getEquippedAbility(GomuGomuNoGatlingAbility.INSTANCE);
/* 150 */     if (gatling != null) {
/* 151 */       gatling.switchNoGear(entity);
/*     */     }
/*     */     
/* 154 */     GomuGomuNoBazookaAbility bazooka = (GomuGomuNoBazookaAbility)props.getEquippedAbility(GomuGomuNoBazookaAbility.INSTANCE);
/* 155 */     if (bazooka != null) {
/* 156 */       bazooka.switchNoGear(entity);
/*     */     }
/*     */     
/* 159 */     float cooldown = Math.max(20.0F, this.continuousComponent.getContinueTime() / 1.5F);
/* 160 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gomu\GearSecondAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */