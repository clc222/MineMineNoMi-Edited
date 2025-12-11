/*    */ package xyz.pixelatedw.mineminenomi.quests.objectives;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IUseItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ 
/*    */ public class UseItemObjective
/*    */   extends Objective
/*    */   implements IUseItemObjective {
/*    */   protected ICheckItemUse useEvent = (player, itemStack, duration) -> true;
/*    */   
/*    */   public UseItemObjective(String title, int count) {
/* 15 */     this(title, count, (ICheckItemUse)null);
/*    */   }
/*    */ 
/*    */   
/*    */   public UseItemObjective(String title, int count, Item item) {
/* 20 */     this(title, count, (player, itemStack, duration) -> (itemStack.func_77973_b() == item));
/*    */   }
/*    */ 
/*    */   
/*    */   public UseItemObjective(String title, int count, ICheckItemUse check) {
/* 25 */     super(title);
/* 26 */     setMaxProgress(count);
/* 27 */     if (check != null) {
/* 28 */       this.useEvent = check;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean checkItem(PlayerEntity player, ItemStack itemStack, int duration) {
/* 34 */     return this.useEvent.test(player, itemStack, duration);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface ICheckItemUse
/*    */   {
/*    */     default ICheckItemUse and(ICheckItemUse check) {
/* 44 */       return (player, itemStack, duration) -> 
/* 45 */         (test(player, itemStack, duration) && check.test(player, itemStack, duration));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     default ICheckItemUse or(ICheckItemUse check) {
/* 51 */       return (player, itemStack, duration) -> 
/* 52 */         (test(player, itemStack, duration) || check.test(player, itemStack, duration));
/*    */     }
/*    */     
/*    */     boolean test(PlayerEntity param1PlayerEntity, ItemStack param1ItemStack, int param1Int);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\objectives\UseItemObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */