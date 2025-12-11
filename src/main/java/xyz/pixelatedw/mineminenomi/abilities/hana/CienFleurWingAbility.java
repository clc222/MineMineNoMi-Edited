/*    */ package xyz.pixelatedw.mineminenomi.abilities.hana;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ 
/*    */ public class CienFleurWingAbility extends Ability {
/* 23 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "cien_fleur_wing", new Pair[] {
/* 24 */         (Pair)ImmutablePair.of("Sprouts two big wings made out of limbs on the user's back and allows them to gently float down taking no fall damage.", null)
/*    */       });
/*    */   
/*    */   private static final int HOLD_TIME = 200;
/*    */   private static final int MIN_COOLDOWN = 40;
/*    */   private static final int MAX_COOLDOWN = 200;
/* 30 */   public static final AbilityCore<CienFleurWingAbility> INSTANCE = (new AbilityCore.Builder("Cien Fleur: Wing", AbilityCategory.DEVIL_FRUITS, CienFleurWingAbility::new))
/* 31 */     .addDescriptionLine(DESCRIPTION)
/* 32 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(40.0F, 200.0F), ContinuousComponent.getTooltip(200.0F)
/* 33 */       }).build();
/*    */   
/* 35 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/* 36 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::damageTakenEvent);
/* 37 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   public CienFleurWingAbility(AbilityCore<CienFleurWingAbility> ability) {
/* 40 */     super(ability);
/*    */     
/* 42 */     this.isNew = true;
/* 43 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.damageTakenComponent, (AbilityComponent)this.animationComponent });
/*    */     
/* 45 */     addCanUseCheck(AbilityHelper::requiresInAir);
/* 46 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 50 */     this.continuousComponent.triggerContinuity(entity, 200.0F);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 54 */     HanaHelper.spawnBlossomEffect(entity);
/*    */     
/* 56 */     this.animationComponent.start(entity, ModAnimations.CROSSED_ARMS);
/*    */   }
/*    */   
/*    */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 60 */     if (entity.func_233570_aj_()) {
/* 61 */       this.continuousComponent.stopContinuity(entity);
/*    */     }
/*    */     
/* 64 */     AbilityHelper.setDeltaMovement((Entity)entity, (entity.func_213322_ci()).field_72450_a / 1.2D, (entity.func_213322_ci()).field_72448_b / 5.0D, (entity.func_213322_ci()).field_72449_c / 1.2D);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 68 */     this.animationComponent.stop(entity);
/*    */     
/* 70 */     float cooldown = Math.max(40.0F, this.continuousComponent.getContinueTime());
/* 71 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*    */   }
/*    */   
/*    */   private float damageTakenEvent(LivingEntity user, IAbility ability, DamageSource damageSource, float damage) {
/* 75 */     if (this.continuousComponent.isContinuous() && damageSource == DamageSource.field_76379_h) {
/* 76 */       return 0.0F;
/*    */     }
/*    */     
/* 79 */     return damage;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\hana\CienFleurWingAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */