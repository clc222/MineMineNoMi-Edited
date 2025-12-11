/*    */ package xyz.pixelatedw.mineminenomi.data.triggers;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
/*    */ import net.minecraft.advancements.criterion.CriterionInstance;
/*    */ import net.minecraft.advancements.criterion.EntityPredicate;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.loot.ConditionArrayParser;
/*    */ import net.minecraft.loot.ConditionArraySerializer;
/*    */ import net.minecraft.util.JSONUtils;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ 
/*    */ 
/*    */ public class UnlockChallengeTrigger
/*    */   extends AbstractCriterionTrigger<UnlockChallengeTrigger.Instance>
/*    */ {
/* 20 */   private static final ResourceLocation ID = new ResourceLocation("mineminenomi", "unlock_challenge");
/*    */ 
/*    */   
/*    */   public ResourceLocation func_192163_a() {
/* 24 */     return ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public Instance createInstance(JsonObject json, EntityPredicate.AndPredicate pEntityPredicate, ConditionArrayParser pConditionsParser) {
/* 29 */     ChallengeCore<?> challenge = null;
/* 30 */     if (json.has("challenge")) {
/* 31 */       challenge = (ChallengeCore)ModRegistries.CHALLENGES.getValue(new ResourceLocation(JSONUtils.func_151200_h(json, "challenge")));
/*    */     }
/* 33 */     return new Instance(pEntityPredicate, challenge);
/*    */   }
/*    */   
/*    */   public void trigger(ServerPlayerEntity player, ChallengeCore<?> challenge) {
/* 37 */     func_235959_a_(player, instance -> instance.matches(player, challenge));
/*    */   }
/*    */   
/*    */   public static class Instance
/*    */     extends CriterionInstance
/*    */   {
/*    */     @Nullable
/*    */     private ChallengeCore<?> challenge;
/*    */     
/*    */     public Instance(EntityPredicate.AndPredicate entityPredicate, @Nullable ChallengeCore<?> challenge) {
/* 47 */       super(UnlockChallengeTrigger.ID, entityPredicate);
/* 48 */       this.challenge = challenge;
/*    */     }
/*    */     
/*    */     public static Instance unlockAnyChallenge() {
/* 52 */       return new Instance(EntityPredicate.AndPredicate.field_234582_a_, null);
/*    */     }
/*    */     
/*    */     public static Instance unlockChallenge(@Nullable ChallengeCore<?> challenge) {
/* 56 */       return new Instance(EntityPredicate.AndPredicate.field_234582_a_, challenge);
/*    */     }
/*    */     
/*    */     public boolean matches(ServerPlayerEntity player, ChallengeCore<?> challenge) {
/* 60 */       return (this.challenge == null || this.challenge.equals(challenge));
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject func_230240_a_(ConditionArraySerializer pConditions) {
/* 65 */       JsonObject jsonobject = super.func_230240_a_(pConditions);
/* 66 */       if (this.challenge != null) {
/* 67 */         jsonobject.addProperty("challenge", this.challenge.getRegistryName().toString());
/*    */       }
/* 69 */       return jsonobject;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\triggers\UnlockChallengeTrigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */