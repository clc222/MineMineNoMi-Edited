/*    */ package xyz.pixelatedw.mineminenomi.data.conditions;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import net.minecraft.loot.ILootSerializer;
/*    */ import net.minecraft.loot.LootConditionType;
/*    */ import net.minecraft.loot.LootContext;
/*    */ import net.minecraft.loot.conditions.ILootCondition;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModLootTypes;
/*    */ 
/*    */ public class RandomizedFruitsCondition
/*    */   implements ILootCondition {
/* 15 */   private static final RandomizedFruitsCondition INSTANCE = new RandomizedFruitsCondition();
/*    */   
/*    */   public static ILootCondition.IBuilder builder() {
/* 18 */     return () -> INSTANCE;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean test(LootContext context) {
/* 23 */     return CommonConfig.INSTANCE.getRandomizedFruits();
/*    */   }
/*    */ 
/*    */   
/*    */   public LootConditionType func_230419_b_() {
/* 28 */     return ModLootTypes.RANDOMIZED_FRUIT;
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Serializer
/*    */     implements ILootSerializer<RandomizedFruitsCondition>
/*    */   {
/*    */     public void serialize(JsonObject pJson, RandomizedFruitsCondition pValue, JsonSerializationContext pSerializationContext) {}
/*    */ 
/*    */     
/*    */     public RandomizedFruitsCondition deserialize(JsonObject pJson, JsonDeserializationContext pSerializationContext) {
/* 39 */       return RandomizedFruitsCondition.INSTANCE;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\conditions\RandomizedFruitsCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */