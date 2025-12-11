/*    */ package xyz.pixelatedw.mineminenomi.abilities.saraaxolotl;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ 
/*    */ public class PlayDeadAbility extends Ability {
/* 27 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "play_dead", new Pair[] {
/* 28 */         (Pair)ImmutablePair.of("While playing dead the user focuses all of their power into regeneration.", null)
/*    */       });
/*    */   
/*    */   private static final int HOLD_TIME = 200;
/*    */   private static final int MIN_COOLDOWN = 200;
/*    */   private static final int MAX_COOLDOWN = 400;
/* 34 */   public static final AbilityCore<PlayDeadAbility> INSTANCE = (new AbilityCore.Builder("Play Dead", AbilityCategory.DEVIL_FRUITS, PlayDeadAbility::new))
/* 35 */     .addDescriptionLine(DESCRIPTION)
/* 36 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/* 37 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F, 400.0F), ContinuousComponent.getTooltip(200.0F)
/* 38 */       }).build();
/*    */   
/* 40 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/* 41 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/* 42 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnDamageEvent(this::onDamageTaken);
/* 43 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.AXOLOTL_WALK.get(), new MorphInfo[0]);
/*    */   
/*    */   public PlayDeadAbility(AbilityCore<PlayDeadAbility> core) {
/* 46 */     super(core);
/*    */     
/* 48 */     this.isNew = true;
/* 49 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.damageTakenComponent, (AbilityComponent)this.requireMorphComponent });
/*    */     
/* 51 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 55 */     this.continuousComponent.triggerContinuity(entity, 200.0F);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 59 */     this.animationComponent.start(entity, ModAnimations.PLAY_DEAD, 200);
/*    */     
/* 61 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.PLAY_DEAD.get(), 200, 0, false, false));
/*    */   }
/*    */   
/*    */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 65 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 2, 2, false, false));
/*    */     
/* 67 */     AbilityHelper.disableAbilities(entity, 2, abl -> (!abl.equals(this) && !abl.getCore().equals(AxolotlWalkPointAbility.INSTANCE)));
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 71 */     this.animationComponent.stop(entity);
/*    */     
/* 73 */     entity.func_195063_d((Effect)ModEffects.PLAY_DEAD.get());
/*    */     
/* 75 */     this.cooldownComponent.startCooldown(entity, 200.0F + this.continuousComponent.getContinueTime());
/*    */   }
/*    */   
/*    */   private float onDamageTaken(LivingEntity target, IAbility ability, DamageSource source, float amount) {
/* 79 */     return amount * 0.75F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\saraaxolotl\PlayDeadAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */