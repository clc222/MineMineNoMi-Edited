/*    */ package xyz.pixelatedw.mineminenomi.quests.objectives;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IUseAbilityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ 
/*    */ public class UseAbilityObjective
/*    */   extends Objective
/*    */   implements IUseAbilityObjective {
/*    */   protected ICheckAbilityUse useEvent = (player, ability) -> true;
/*    */   
/*    */   public UseAbilityObjective(String title, int count) {
/* 15 */     this(title, count, (ICheckAbilityUse)null);
/*    */   }
/*    */ 
/*    */   
/*    */   public UseAbilityObjective(String title, int count, AbilityCore ability) {
/* 20 */     this(title, count, (player, abl) -> abl.getCore().equals(ability));
/*    */   }
/*    */ 
/*    */   
/*    */   public UseAbilityObjective(String title, int count, ICheckAbilityUse check) {
/* 25 */     super(title);
/* 26 */     setMaxProgress(count);
/* 27 */     if (check != null) {
/* 28 */       this.useEvent = check;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean checkAbility(PlayerEntity player, IAbility ability) {
/* 34 */     return this.useEvent.test(player, ability);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface ICheckAbilityUse
/*    */   {
/*    */     default ICheckAbilityUse and(ICheckAbilityUse check) {
/* 44 */       return (player, ability) -> 
/* 45 */         (test(player, ability) && check.test(player, ability));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     default ICheckAbilityUse or(ICheckAbilityUse check) {
/* 51 */       return (player, ability) -> 
/* 52 */         (test(player, ability) || check.test(player, ability));
/*    */     }
/*    */     
/*    */     boolean test(PlayerEntity param1PlayerEntity, IAbility param1IAbility);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\objectives\UseAbilityObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */