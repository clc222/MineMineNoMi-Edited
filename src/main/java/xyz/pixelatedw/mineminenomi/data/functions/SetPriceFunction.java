/*    */ package xyz.pixelatedw.mineminenomi.data.functions;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.loot.IRandomRange;
/*    */ import net.minecraft.loot.LootContext;
/*    */ import net.minecraft.loot.LootFunction;
/*    */ import net.minecraft.loot.LootFunctionType;
/*    */ import net.minecraft.loot.RandomRanges;
/*    */ import net.minecraft.loot.conditions.ILootCondition;
/*    */ import net.minecraft.loot.functions.ILootFunction;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModLootTypes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class SetPriceFunction
/*    */   extends LootFunction
/*    */ {
/*    */   private IRandomRange range;
/*    */   
/*    */   protected SetPriceFunction(ILootCondition[] conditionsIn, IRandomRange rang) {
/* 23 */     super(conditionsIn);
/* 24 */     this.range = rang;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack func_215859_a(ItemStack stack, LootContext context) {
/* 30 */     stack.func_196082_o().func_74768_a("price", WyHelper.round(this.range.func_186511_a(context.func_216032_b())));
/* 31 */     return stack;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public LootFunctionType func_230425_b_() {
/* 37 */     return ModLootTypes.SET_PRICE;
/*    */   }
/*    */ 
/*    */   
/*    */   public static LootFunction.Builder<?> builder(IRandomRange range) {
/* 42 */     return func_215860_a(condition -> new SetPriceFunction(condition, range));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Serializer
/*    */     extends LootFunction.Serializer<SetPriceFunction>
/*    */   {
/*    */     public void serialize(JsonObject object, SetPriceFunction functionClazz, JsonSerializationContext serializationContext) {
/* 53 */       super.func_230424_a_(object, functionClazz, serializationContext);
/* 54 */       object.add("price_range", RandomRanges.func_216131_a(functionClazz.range, serializationContext));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public SetPriceFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
/* 60 */       IRandomRange range = RandomRanges.func_216130_a(object.get("price_range"), deserializationContext);
/* 61 */       return new SetPriceFunction(conditionsIn, range);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\functions\SetPriceFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */