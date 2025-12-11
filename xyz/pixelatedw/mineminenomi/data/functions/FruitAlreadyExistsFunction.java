/*    */ package xyz.pixelatedw.mineminenomi.data.functions;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.loot.LootContext;
/*    */ import net.minecraft.loot.LootFunction;
/*    */ import net.minecraft.loot.LootFunctionType;
/*    */ import net.minecraft.loot.conditions.ILootCondition;
/*    */ import net.minecraft.loot.functions.ILootFunction;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.OFPWWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModLootTypes;
/*    */ 
/*    */ public class FruitAlreadyExistsFunction
/*    */   extends LootFunction {
/*    */   protected FruitAlreadyExistsFunction(ILootCondition[] conditionsIn) {
/* 21 */     super(conditionsIn);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ItemStack func_215859_a(ItemStack stack, LootContext context) {
/* 26 */     if (!CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/* 27 */       return stack;
/*    */     }
/*    */     
/* 30 */     if (stack.func_190926_b()) {
/* 31 */       return stack;
/*    */     }
/*    */     
/* 34 */     if (stack.func_77973_b() instanceof xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem) {
/* 35 */       OFPWWorldData worldData = OFPWWorldData.get();
/*    */       
/* 37 */       if (worldData.isFruitAvailable(stack.func_77973_b().getRegistryName())) {
/* 38 */         return stack;
/*    */       }
/*    */     } else {
/*    */       
/* 42 */       return stack;
/*    */     } 
/*    */     
/* 45 */     return new ItemStack((IItemProvider)Items.field_190931_a);
/*    */   }
/*    */ 
/*    */   
/*    */   public LootFunctionType func_230425_b_() {
/* 50 */     return ModLootTypes.FRUIT_ALREADY_EXISTS;
/*    */   }
/*    */   
/*    */   public static LootFunction.Builder<?> builder() {
/* 54 */     return func_215860_a(condition -> new FruitAlreadyExistsFunction(condition));
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Serializer
/*    */     extends LootFunction.Serializer<FruitAlreadyExistsFunction>
/*    */   {
/*    */     public void serialize(JsonObject object, FruitAlreadyExistsFunction functionClazz, JsonSerializationContext serializationContext) {
/* 62 */       super.func_230424_a_(object, functionClazz, serializationContext);
/*    */     }
/*    */ 
/*    */     
/*    */     public FruitAlreadyExistsFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
/* 67 */       return new FruitAlreadyExistsFunction(conditionsIn);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\functions\FruitAlreadyExistsFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */