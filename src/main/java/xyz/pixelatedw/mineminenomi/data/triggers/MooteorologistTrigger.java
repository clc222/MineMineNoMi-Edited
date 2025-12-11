/*    */ package xyz.pixelatedw.mineminenomi.data.triggers;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
/*    */ import net.minecraft.advancements.criterion.CriterionInstance;
/*    */ import net.minecraft.advancements.criterion.EntityPredicate;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.loot.ConditionArrayParser;
/*    */ import net.minecraft.loot.ConditionArraySerializer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ public class MooteorologistTrigger
/*    */   extends AbstractCriterionTrigger<MooteorologistTrigger.Instance>
/*    */ {
/* 16 */   private static final ResourceLocation ID = new ResourceLocation("mineminenomi", "mooteorologist");
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation func_192163_a() {
/* 21 */     return ID;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Instance createInstance(JsonObject json, EntityPredicate.AndPredicate entityPredicate, ConditionArrayParser parser) {
/* 27 */     return new Instance(entityPredicate);
/*    */   }
/*    */ 
/*    */   
/*    */   public void trigger(ServerPlayerEntity player) {
/* 32 */     func_235959_a_(player, inst -> true);
/*    */   }
/*    */   
/*    */   public static class Instance
/*    */     extends CriterionInstance
/*    */   {
/*    */     public Instance(EntityPredicate.AndPredicate entityPredicate) {
/* 39 */       super(MooteorologistTrigger.ID, entityPredicate);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public JsonObject func_230240_a_(ConditionArraySerializer pConditions) {
/* 45 */       JsonObject jsonobject = super.func_230240_a_(pConditions);
/* 46 */       return jsonobject;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\triggers\MooteorologistTrigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */