/*     */ package xyz.pixelatedw.mineminenomi.abilities.ryupteranodon;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.GrabEntityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ 
/*     */ public class BeakGrabAbility extends Ability {
/*  29 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "beak_grab", new Pair[] {
/*  30 */         (Pair)ImmutablePair.of("Allows the user to carry their target.", null)
/*     */       });
/*  32 */   private static final TargetsPredicate TARGET_PREDICATE = (new TargetsPredicate()).testAdvancedInView();
/*     */   
/*     */   private static final int COOLDOWN = 200;
/*     */   
/*     */   private static final int HOLD_TIME = 300;
/*     */   private static final float RANGE = 1.2F;
/*  38 */   public static final AbilityCore<BeakGrabAbility> INSTANCE = (new AbilityCore.Builder("Beak Grab", AbilityCategory.DEVIL_FRUITS, BeakGrabAbility::new))
/*  39 */     .addDescriptionLine(DESCRIPTION)
/*  40 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/*  41 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ContinuousComponent.getTooltip(300.0F), RangeComponent.getTooltip(1.2F, RangeComponent.RangeType.LINE)
/*  42 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  43 */     .build();
/*     */   
/*  45 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true))
/*  46 */     .addStartEvent(this::onContinuityStart)
/*  47 */     .addTickEvent(this::onContinuityTick)
/*  48 */     .addEndEvent(this::onContinuityEnd);
/*     */   
/*  50 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  51 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  52 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.PTERA_FLY.get(), new MorphInfo[0]);
/*  53 */   private final GrabEntityComponent grabEntityComponent = new GrabEntityComponent((IAbility)this, false, true, 2.0F);
/*     */   
/*     */   public BeakGrabAbility(AbilityCore<BeakGrabAbility> core) {
/*  56 */     super(core);
/*     */     
/*  58 */     this.isNew = true;
/*     */     
/*  60 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.requireMorphComponent, (AbilityComponent)this.grabEntityComponent });
/*     */     
/*  62 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*     */     
/*  64 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  68 */     this.continuousComponent.triggerContinuity(entity, 300.0F);
/*     */   }
/*     */   
/*     */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/*  72 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  76 */     this.animationComponent.start(entity, ModAnimations.PTERA_OPEN_MOUTH);
/*     */     
/*  78 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.DASH_ABILITY_SWOOSH_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/*  82 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  86 */     LivingEntity grabbedTarget = this.grabEntityComponent.getGrabbedEntity();
/*     */     
/*  88 */     if (canUse(entity).isFail() || (grabbedTarget != null && !this.grabEntityComponent.canContinueGrab(entity))) {
/*  89 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  94 */     if (grabbedTarget == null) {
/*  95 */       this.grabEntityComponent.grabNearest(entity, 1.2F, 1.4F, false);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 100 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 104 */     this.grabEntityComponent.release(entity);
/* 105 */     this.animationComponent.stop(entity);
/*     */     
/* 107 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ryupteranodon\BeakGrabAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */