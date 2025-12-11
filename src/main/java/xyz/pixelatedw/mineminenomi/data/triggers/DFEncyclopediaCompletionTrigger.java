/*    */ package xyz.pixelatedw.mineminenomi.data.triggers;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
/*    */ import net.minecraft.advancements.criterion.CriterionInstance;
/*    */ import net.minecraft.advancements.criterion.EntityPredicate;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.loot.ConditionArrayParser;
/*    */ import net.minecraft.loot.ConditionArraySerializer;
/*    */ import net.minecraft.util.JSONUtils;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.items.DFEncyclopediaItem;
/*    */ 
/*    */ 
/*    */ public class DFEncyclopediaCompletionTrigger
/*    */   extends AbstractCriterionTrigger<DFEncyclopediaCompletionTrigger.Instance>
/*    */ {
/* 19 */   private static final ResourceLocation ID = new ResourceLocation("mineminenomi", "encyclopedia_completion");
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation func_192163_a() {
/* 24 */     return ID;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Instance createInstance(JsonObject json, EntityPredicate.AndPredicate pEntityPredicate, ConditionArrayParser pConditionsParser) {
/* 30 */     float completion = JSONUtils.func_151217_k(json, "completion");
/* 31 */     return new Instance(pEntityPredicate, completion);
/*    */   }
/*    */ 
/*    */   
/*    */   public void trigger(ServerPlayerEntity player, ItemStack stack) {
/* 36 */     func_235959_a_(player, instance -> instance.matches(player, stack));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Instance
/*    */     extends CriterionInstance
/*    */   {
/*    */     private float completion;
/*    */ 
/*    */     
/*    */     public Instance(EntityPredicate.AndPredicate entityPredicate, float completion) {
/* 48 */       super(DFEncyclopediaCompletionTrigger.ID, entityPredicate);
/* 49 */       this.completion = completion;
/*    */     }
/*    */ 
/*    */     
/*    */     public boolean matches(ServerPlayerEntity player, ItemStack stack) {
/* 54 */       return (DFEncyclopediaItem.getCompletion(stack) >= this.completion);
/*    */     }
/*    */     
/*    */     public static Instance completion(float completion) {
/* 58 */       return new Instance(EntityPredicate.AndPredicate.field_234582_a_, completion);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public JsonObject func_230240_a_(ConditionArraySerializer pConditions) {
/* 64 */       JsonObject jsonobject = super.func_230240_a_(pConditions);
/* 65 */       jsonobject.addProperty("completion", Float.valueOf(this.completion));
/* 66 */       return jsonobject;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\triggers\DFEncyclopediaCompletionTrigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */