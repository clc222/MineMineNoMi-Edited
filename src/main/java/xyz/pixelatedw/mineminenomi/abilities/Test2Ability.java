/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.GrabEntityComponent;
/*    */ 
/*    */ public class Test2Ability
/*    */   extends Ability {
/* 13 */   public static final AbilityCore<Test2Ability> INSTANCE = (new AbilityCore.Builder("Test 2", AbilityCategory.RACIAL, Test2Ability::new))
/* 14 */     .build();
/*    */   
/* 16 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(100, this::startContinuityEvent).addEndEvent(100, this::endContinuityEvent);
/* 17 */   private final GrabEntityComponent grabComponent = new GrabEntityComponent((IAbility)this, true, true, 1.0F);
/*    */ 
/*    */   
/*    */   public Test2Ability(AbilityCore<Test2Ability> core) {
/* 21 */     super(core);
/*    */     
/* 23 */     this.isNew = true;
/*    */ 
/*    */     
/* 26 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 30 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 34 */     this.grabComponent.grabNearest(entity, false);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 38 */     this.grabComponent.release(entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\Test2Ability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */