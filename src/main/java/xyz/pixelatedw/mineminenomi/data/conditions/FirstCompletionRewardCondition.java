/*    */ package xyz.pixelatedw.mineminenomi.data.conditions;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.loot.ILootSerializer;
/*    */ import net.minecraft.loot.LootConditionType;
/*    */ import net.minecraft.loot.LootContext;
/*    */ import net.minecraft.loot.LootParameters;
/*    */ import net.minecraft.loot.conditions.ILootCondition;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModLootTypes;
/*    */ 
/*    */ public class FirstCompletionRewardCondition implements ILootCondition {
/* 20 */   private static final FirstCompletionRewardCondition INSTANCE = new FirstCompletionRewardCondition();
/*    */   
/*    */   public static ILootCondition.IBuilder builder() {
/* 23 */     return () -> INSTANCE;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean test(LootContext context) {
/* 28 */     Entity entity = (Entity)context.func_216031_c(LootParameters.field_216281_a);
/* 29 */     ChallengeCore<?> core = (ChallengeCore)context.func_216031_c(ModLootTypes.COMPLETED_CHALLENGE);
/* 30 */     if (entity != null && core != null && entity instanceof PlayerEntity) {
/* 31 */       IChallengesData props = ChallengesDataCapability.get((PlayerEntity)entity);
/* 32 */       Challenge challenge = props.getChallenge(core);
/* 33 */       if (challenge.getCompletions() < 1) {
/* 34 */         return true;
/*    */       }
/*    */     } 
/* 37 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootConditionType func_230419_b_() {
/* 42 */     return ModLootTypes.FIRST_COMPLETION;
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Serializer
/*    */     implements ILootSerializer<FirstCompletionRewardCondition>
/*    */   {
/*    */     public void serialize(JsonObject pJson, FirstCompletionRewardCondition pValue, JsonSerializationContext pSerializationContext) {}
/*    */ 
/*    */     
/*    */     public FirstCompletionRewardCondition deserialize(JsonObject pJson, JsonDeserializationContext pSerializationContext) {
/* 53 */       return FirstCompletionRewardCondition.INSTANCE;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\conditions\FirstCompletionRewardCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */