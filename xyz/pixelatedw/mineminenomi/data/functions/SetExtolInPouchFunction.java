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
/*    */ 
/*    */ public class SetExtolInPouchFunction extends LootFunction {
/*    */   private IRandomRange range;
/*    */   
/*    */   protected SetExtolInPouchFunction(ILootCondition[] conditionsIn, IRandomRange rang) {
/* 20 */     super(conditionsIn);
/* 21 */     this.range = rang;
/*    */   }
/*    */ 
/*    */   
/*    */   protected ItemStack func_215859_a(ItemStack stack, LootContext context) {
/* 26 */     stack.func_196082_o().func_74768_a("extol", this.range.func_186511_a(context.func_216032_b()));
/* 27 */     return stack;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootFunctionType func_230425_b_() {
/* 32 */     return ModLootTypes.SET_EXTOL_IN_POUCH;
/*    */   }
/*    */   
/*    */   public static LootFunction.Builder<?> builder(IRandomRange range) {
/* 36 */     return func_215860_a(condition -> new SetExtolInPouchFunction(condition, range));
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Serializer
/*    */     extends LootFunction.Serializer<SetExtolInPouchFunction>
/*    */   {
/*    */     public void serialize(JsonObject object, SetExtolInPouchFunction functionClazz, JsonSerializationContext serializationContext) {
/* 44 */       super.func_230424_a_(object, functionClazz, serializationContext);
/* 45 */       object.add("extol_range", RandomRanges.func_216131_a(functionClazz.range, serializationContext));
/*    */     }
/*    */ 
/*    */     
/*    */     public SetExtolInPouchFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
/* 50 */       IRandomRange range = RandomRanges.func_216130_a(object.get("extol_range"), deserializationContext);
/* 51 */       return new SetExtolInPouchFunction(conditionsIn, range);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\functions\SetExtolInPouchFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */