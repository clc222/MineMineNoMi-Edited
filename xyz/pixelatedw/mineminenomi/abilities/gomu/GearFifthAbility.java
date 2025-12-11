/*     */ package xyz.pixelatedw.mineminenomi.abilities.gomu;
/*     */ import java.awt.Color;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraftforge.common.ForgeMod;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SToggleDrumsOfLiberationSoundPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class GearFifthAbility extends Ability {
/*  35 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "gear_fifth", new Pair[] {
/*  36 */         (Pair)ImmutablePair.of("The absolute peak bringing joy and freedom to those around them.", null)
/*     */       });
/*     */   public static final int HOLD_TIME = 1200;
/*     */   private static final int MIN_COOLDOWN = 200;
/*     */   private static final float MAX_COOLDOWN = 800.0F;
/*  41 */   private static final Color COLOR = WyHelper.hexToRGB("#FFFFFF30");
/*     */   
/*  43 */   public static final AbilityCore<GearFifthAbility> INSTANCE = (new AbilityCore.Builder("Gear Fifth", AbilityCategory.DEVIL_FRUITS, GearFifthAbility::new))
/*  44 */     .addDescriptionLine(DESCRIPTION)
/*  45 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F, 800.0F), ContinuousComponent.getTooltip(1200.0F), ChangeStatsComponent.getTooltip()
/*  46 */       }).setUnlockCheck(GearFifthAbility::canUnlock)
/*  47 */     .build();
/*     */   
/*  49 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setColor(COLOR).setRenderType(AbilityOverlay.RenderType.ENERGY).build();
/*  50 */   private static final AbilityAttributeModifier GRAVITY_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_GRAVITY_UUID, INSTANCE, "Nika Jump Modifier", -0.019999999552965164D, AttributeModifier.Operation.ADDITION);
/*     */   
/*  52 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  53 */   private final ChangeStatsComponent changeStatsComponent = new ChangeStatsComponent((IAbility)this);
/*  54 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/*     */   
/*     */   private boolean playJumpSound;
/*     */   
/*     */   public GearFifthAbility(AbilityCore core) {
/*  59 */     super(core);
/*     */     
/*  61 */     this.isNew = true;
/*  62 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.changeStatsComponent, (AbilityComponent)this.skinOverlayComponent });
/*     */     
/*  64 */     this.changeStatsComponent.addAttributeModifier((Attribute)ForgeMod.ENTITY_GRAVITY.get(), (AttributeModifier)GRAVITY_MODIFIER);
/*     */     
/*  66 */     addCanUseCheck(GomuHelper.canUseGearCheck(INSTANCE));
/*  67 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  71 */     this.continuousComponent.triggerContinuity(entity, 1200.0F);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  75 */     this.changeStatsComponent.applyModifiers(entity);
/*  76 */     this.skinOverlayComponent.showAll(entity);
/*     */     
/*  78 */     if (!entity.field_70170_p.field_72995_K) {
/*  79 */       WyNetwork.sendToAllTrackingAndSelf(new SToggleDrumsOfLiberationSoundPacket(entity, true), (Entity)entity);
/*     */     }
/*     */     
/*  82 */     this.playJumpSound = false;
/*     */     
/*  84 */     IAbilityData props = AbilityDataCapability.get(entity);
/*     */     
/*  86 */     GomuGomuNoPistolAbility pistol = (GomuGomuNoPistolAbility)props.getEquippedAbility(GomuGomuNoPistolAbility.INSTANCE);
/*  87 */     if (pistol != null) {
/*  88 */       pistol.switchFifthGear(entity);
/*     */     }
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  93 */     if (entity.func_233570_aj_() && !this.playJumpSound) {
/*  94 */       this.playJumpSound = true;
/*     */     }
/*  96 */     else if (!entity.func_233570_aj_() && this.playJumpSound) {
/*  97 */       SoundEvent sfx = (SoundEvent)ModSounds.BOUNCE_1.get();
/*  98 */       if (entity.func_70681_au().nextBoolean()) {
/*  99 */         sfx = (SoundEvent)ModSounds.BOUNCE_2.get();
/*     */       }
/* 101 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), sfx, SoundCategory.PLAYERS, 2.0F, 0.75F + entity.func_70681_au().nextFloat() / 2.0F);
/* 102 */       this.playJumpSound = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 107 */     this.changeStatsComponent.removeModifiers(entity);
/* 108 */     this.skinOverlayComponent.hideAll(entity);
/*     */     
/* 110 */     if (!entity.field_70170_p.field_72995_K) {
/* 111 */       WyNetwork.sendToAllTrackingAndSelf(new SToggleDrumsOfLiberationSoundPacket(entity, false), (Entity)entity);
/*     */     }
/*     */     
/* 114 */     IAbilityData props = AbilityDataCapability.get(entity);
/*     */     
/* 116 */     GomuGomuNoPistolAbility pistol = (GomuGomuNoPistolAbility)props.getEquippedAbility(GomuGomuNoPistolAbility.INSTANCE);
/* 117 */     if (pistol != null) {
/* 118 */       pistol.switchNoGear(entity);
/*     */     }
/*     */     
/* 121 */     float cooldown = Math.max(200.0F, this.continuousComponent.getContinueTime());
/* 122 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity user) {
/* 126 */     return DevilFruitCapability.get(user).hasAwakenedFruit();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gomu\GearFifthAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */