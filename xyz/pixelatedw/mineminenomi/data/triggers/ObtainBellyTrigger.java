/*    */ package xyz.pixelatedw.mineminenomi.data.triggers;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
/*    */ import net.minecraft.advancements.criterion.CriterionInstance;
/*    */ import net.minecraft.advancements.criterion.EntityPredicate;
/*    */ import net.minecraft.advancements.criterion.MinMaxBounds;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.loot.ConditionArrayParser;
/*    */ import net.minecraft.loot.ConditionArraySerializer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.data.triggers.criterion.BellyPredicate;
/*    */ 
/*    */ 
/*    */ public class ObtainBellyTrigger
/*    */   extends AbstractCriterionTrigger<ObtainBellyTrigger.Instance>
/*    */ {
/* 18 */   private static final ResourceLocation ID = new ResourceLocation("mineminenomi", "obtain_belly");
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation func_192163_a() {
/* 23 */     return ID;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Instance createInstance(JsonObject pJson, EntityPredicate.AndPredicate pEntityPredicate, ConditionArrayParser pConditionsParser) {
/* 29 */     BellyPredicate belly = BellyPredicate.fromJson(pJson.get("belly"));
/* 30 */     return new Instance(pEntityPredicate, belly);
/*    */   }
/*    */ 
/*    */   
/*    */   public void trigger(ServerPlayerEntity player, int amount, boolean isBountyReward) {
/* 35 */     func_235959_a_(player, instance -> instance.matches(player, amount, isBountyReward));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Instance
/*    */     extends CriterionInstance
/*    */   {
/*    */     private final BellyPredicate predicate;
/*    */ 
/*    */     
/*    */     public Instance(EntityPredicate.AndPredicate entityPredicate, BellyPredicate predicate) {
/* 47 */       super(ObtainBellyTrigger.ID, entityPredicate);
/* 48 */       this.predicate = predicate;
/*    */     }
/*    */ 
/*    */     
/*    */     public boolean matches(ServerPlayerEntity player, int amount, boolean isBountyReward) {
/* 53 */       return this.predicate.matches(player, amount, isBountyReward);
/*    */     }
/*    */     
/*    */     public static Instance collectBelly(int belly) {
/* 57 */       return new Instance(EntityPredicate.AndPredicate.field_234582_a_, new BellyPredicate(MinMaxBounds.IntBound.func_211340_b(belly), (Boolean)null));
/*    */     }
/*    */     
/*    */     public static Instance collectBelly(int belly, boolean fromBounty) {
/* 61 */       return new Instance(EntityPredicate.AndPredicate.field_234582_a_, new BellyPredicate(MinMaxBounds.IntBound.func_211340_b(belly), Boolean.valueOf(fromBounty)));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public JsonObject func_230240_a_(ConditionArraySerializer pConditions) {
/* 67 */       JsonObject jsonobject = super.func_230240_a_(pConditions);
/* 68 */       jsonobject.add("belly", this.predicate.serializeToJson());
/* 69 */       return jsonobject;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\triggers\ObtainBellyTrigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */