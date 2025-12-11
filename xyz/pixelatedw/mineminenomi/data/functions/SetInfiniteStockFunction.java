/*    */ package xyz.pixelatedw.mineminenomi.data.functions;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.loot.LootContext;
/*    */ import net.minecraft.loot.LootFunction;
/*    */ import net.minecraft.loot.LootFunctionType;
/*    */ import net.minecraft.loot.conditions.ILootCondition;
/*    */ import net.minecraft.loot.functions.ILootFunction;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModLootTypes;
/*    */ 
/*    */ public class SetInfiniteStockFunction
/*    */   extends LootFunction
/*    */ {
/*    */   protected SetInfiniteStockFunction(ILootCondition[] conditionsIn) {
/* 18 */     super(conditionsIn);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ItemStack func_215859_a(ItemStack stack, LootContext context) {
/* 24 */     stack.func_196082_o().func_74757_a("isInfinite", true);
/* 25 */     stack.func_190920_e(1);
/* 26 */     return stack;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public LootFunctionType func_230425_b_() {
/* 32 */     return ModLootTypes.INFINITE_STOCK;
/*    */   }
/*    */ 
/*    */   
/*    */   public static LootFunction.Builder<?> builder() {
/* 37 */     return func_215860_a(condition -> new SetInfiniteStockFunction(condition));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Serializer
/*    */     extends LootFunction.Serializer<SetInfiniteStockFunction>
/*    */   {
/*    */     public void serialize(JsonObject object, SetInfiniteStockFunction functionClazz, JsonSerializationContext serializationContext) {
/* 48 */       super.func_230424_a_(object, functionClazz, serializationContext);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public SetInfiniteStockFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
/* 54 */       return new SetInfiniteStockFunction(conditionsIn);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\functions\SetInfiniteStockFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */