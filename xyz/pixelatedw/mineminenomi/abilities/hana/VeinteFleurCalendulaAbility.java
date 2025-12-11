/*    */ package xyz.pixelatedw.mineminenomi.abilities.hana;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class VeinteFleurCalendulaAbility extends Ability {
/* 22 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "veinte_fleur_calendula", new Pair[] {
/* 23 */         (Pair)ImmutablePair.of("Using newly sprouted arms in the form of a shield the user can partially block attacks.", null)
/*    */       });
/*    */   
/*    */   private static final int HOLD_TIME = 200;
/*    */   private static final int MIN_COOLDOWN = 60;
/*    */   private static final int MAX_COOLDOWN = 200;
/* 29 */   public static final AbilityCore<VeinteFleurCalendulaAbility> INSTANCE = (new AbilityCore.Builder("Veinte Fleur: Calendula", AbilityCategory.DEVIL_FRUITS, VeinteFleurCalendulaAbility::new))
/* 30 */     .addDescriptionLine(DESCRIPTION)
/* 31 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(60.0F, 200.0F), ContinuousComponent.getTooltip(200.0F)
/* 32 */       }).build();
/*    */   
/* 34 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/* 35 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   public VeinteFleurCalendulaAbility(AbilityCore<VeinteFleurCalendulaAbility> ability) {
/* 38 */     super(ability);
/*    */     
/* 40 */     this.isNew = true;
/* 41 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.animationComponent });
/*    */     
/* 43 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 47 */     this.continuousComponent.triggerContinuity(entity, 200.0F);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 51 */     this.animationComponent.start(entity, ModAnimations.CROSSED_ARMS);
/* 52 */     HanaHelper.spawnBlossomEffect(entity);
/*    */   }
/*    */   
/*    */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 56 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.PHYSICAL_MOVING_GUARD.get(), 2, 3, false, false));
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 60 */     this.animationComponent.stop(entity);
/*    */     
/* 62 */     float cooldown = Math.max(60.0F, this.continuousComponent.getContinueTime());
/* 63 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\hana\VeinteFleurCalendulaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */