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
/*    */ public class SetBellyInPouchFunction
/*    */   extends LootFunction
/*    */ {
/*    */   private IRandomRange range;
/*    */   
/*    */   protected SetBellyInPouchFunction(ILootCondition[] conditionsIn, IRandomRange rang) {
/* 22 */     super(conditionsIn);
/* 23 */     this.range = rang;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ItemStack func_215859_a(ItemStack stack, LootContext context) {
/* 29 */     stack.func_196082_o().func_74768_a("belly", this.range.func_186511_a(context.func_216032_b()));
/* 30 */     return stack;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public LootFunctionType func_230425_b_() {
/* 36 */     return ModLootTypes.SET_BELLY_IN_POUCH;
/*    */   }
/*    */ 
/*    */   
/*    */   public static LootFunction.Builder<?> builder(IRandomRange range) {
/* 41 */     return func_215860_a(condition -> new SetBellyInPouchFunction(condition, range));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Serializer
/*    */     extends LootFunction.Serializer<SetBellyInPouchFunction>
/*    */   {
/*    */     public void serialize(JsonObject object, SetBellyInPouchFunction functionClazz, JsonSerializationContext serializationContext) {
/* 52 */       super.func_230424_a_(object, functionClazz, serializationContext);
/* 53 */       object.add("belly_range", RandomRanges.func_216131_a(functionClazz.range, serializationContext));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public SetBellyInPouchFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
/* 59 */       IRandomRange range = RandomRanges.func_216130_a(object.get("belly_range"), deserializationContext);
/* 60 */       return new SetBellyInPouchFunction(conditionsIn, range);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\functions\SetBellyInPouchFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */