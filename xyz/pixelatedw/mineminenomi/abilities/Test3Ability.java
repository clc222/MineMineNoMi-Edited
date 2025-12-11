/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SwingTriggerComponent;
/*    */ 
/*    */ public class Test3Ability extends Ability {
/* 12 */   public static final AbilityCore<Test3Ability> INSTANCE = (new AbilityCore.Builder("Test 3", AbilityCategory.RACIAL, Test3Ability::new))
/* 13 */     .build();
/*    */   
/* 15 */   private final SwingTriggerComponent swingTriggerComponent = (new SwingTriggerComponent((IAbility)this)).addSwingEvent(this::onSwingEvent);
/*    */   
/*    */   public Test3Ability(AbilityCore<Test3Ability> core) {
/* 18 */     super(core);
/*    */     
/* 20 */     this.isNew = true;
/*    */     
/* 22 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.swingTriggerComponent });
/*    */     
/* 24 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {}
/*    */   
/*    */   private void onSwingEvent(LivingEntity entity, IAbility ability) {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\Test3Ability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */