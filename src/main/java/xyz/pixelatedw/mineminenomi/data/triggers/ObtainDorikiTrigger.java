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
/*    */ 
/*    */ public class ObtainDorikiTrigger
/*    */   extends AbstractCriterionTrigger<ObtainDorikiTrigger.Instance>
/*    */ {
/* 16 */   private static final ResourceLocation ID = new ResourceLocation("mineminenomi", "obtain_doriki");
/*    */ 
/*    */   
/*    */   public ResourceLocation func_192163_a() {
/* 20 */     return ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public Instance createInstance(JsonObject pJson, EntityPredicate.AndPredicate pEntityPredicate, ConditionArrayParser pConditionsParser) {
/* 25 */     MinMaxBounds.IntBound doriki = MinMaxBounds.IntBound.func_211344_a(pJson.get("doriki"));
/* 26 */     return new Instance(pEntityPredicate, doriki);
/*    */   }
/*    */   
/*    */   public void trigger(ServerPlayerEntity player, int amount) {
/* 30 */     func_235959_a_(player, instance -> instance.matches(player, amount));
/*    */   }
/*    */   
/*    */   public static class Instance
/*    */     extends CriterionInstance
/*    */   {
/*    */     private final MinMaxBounds.IntBound predicate;
/*    */     
/*    */     public Instance(EntityPredicate.AndPredicate entityPredicate, MinMaxBounds.IntBound predicate) {
/* 39 */       super(ObtainDorikiTrigger.ID, entityPredicate);
/* 40 */       this.predicate = predicate;
/*    */     }
/*    */     
/*    */     public boolean matches(ServerPlayerEntity player, int amount) {
/* 44 */       return this.predicate.func_211339_d(amount);
/*    */     }
/*    */     
/*    */     public static Instance obtainDoriki(int doriki) {
/* 48 */       return new Instance(EntityPredicate.AndPredicate.field_234582_a_, MinMaxBounds.IntBound.func_211340_b(doriki));
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject func_230240_a_(ConditionArraySerializer pConditions) {
/* 53 */       JsonObject jsonobject = super.func_230240_a_(pConditions);
/* 54 */       jsonobject.add("doriki", this.predicate.func_200321_c());
/* 55 */       return jsonobject;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\triggers\ObtainDorikiTrigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */