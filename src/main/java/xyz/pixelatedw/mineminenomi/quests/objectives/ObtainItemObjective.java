/*    */ package xyz.pixelatedw.mineminenomi.quests.objectives;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.function.Predicate;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IObtainItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ 
/*    */ public class ObtainItemObjective<T extends Item>
/*    */   extends Objective
/*    */   implements IObtainItemObjective
/*    */ {
/*    */   private Predicate<ItemStack> check = itemStack -> false;
/*    */   
/*    */   public ObtainItemObjective(String title, int count, Supplier<T> itemTarget) {
/* 18 */     super(title);
/* 19 */     setMaxProgress(count);
/* 20 */     this.check = (itemStack -> (itemStack.func_77973_b().func_199767_j() == itemTarget.get()));
/*    */   }
/*    */ 
/*    */   
/*    */   public ObtainItemObjective(String title, int count, Predicate<ItemStack> check) {
/* 25 */     super(title);
/* 26 */     setMaxProgress(count);
/* 27 */     this.check = check;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean checkItem(ItemStack stack) {
/* 33 */     return this.check.test(stack);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int checkItems(ArrayList<ItemStack> stacks) {
/* 39 */     return stacks.stream().filter(this::checkItem).mapToInt(stack -> stack.func_190916_E()).sum();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\objectives\ObtainItemObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */