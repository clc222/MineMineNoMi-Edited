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
/*    */ 
/*    */ public class ConsumeDevilFruitTrigger
/*    */   extends AbstractCriterionTrigger<ConsumeDevilFruitTrigger.Instance> {
/* 16 */   private static final ResourceLocation ID = new ResourceLocation("mineminenomi", "consume_devil_fruit");
/*    */ 
/*    */   
/*    */   public ResourceLocation func_192163_a() {
/* 20 */     return ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public Instance createInstance(JsonObject json, EntityPredicate.AndPredicate entityPredicate, ConditionArrayParser parser) {
/* 25 */     ResourceLocation fruit = json.has("fruit") ? new ResourceLocation(JSONUtils.func_151200_h(json, "fruit")) : null;
/* 26 */     return new Instance(entityPredicate, fruit);
/*    */   }
/*    */   
/*    */   public void trigger(ServerPlayerEntity player, ResourceLocation fruit) {
/* 30 */     func_235959_a_(player, inst -> inst.matches(fruit));
/*    */   }
/*    */   
/*    */   public static class Instance extends CriterionInstance {
/*    */     @Nullable
/*    */     private final ResourceLocation fruit;
/*    */     
/*    */     public Instance(EntityPredicate.AndPredicate entityPredicate, @Nullable ResourceLocation fruit) {
/* 38 */       super(ConsumeDevilFruitTrigger.ID, entityPredicate);
/* 39 */       this.fruit = fruit;
/*    */     }
/*    */     
/*    */     public boolean matches(ResourceLocation fruit) {
/* 43 */       return (this.fruit == null || this.fruit.equals(fruit));
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject func_230240_a_(ConditionArraySerializer pConditions) {
/* 48 */       JsonObject jsonobject = super.func_230240_a_(pConditions);
/* 49 */       if (this.fruit != null) {
/* 50 */         jsonobject.addProperty("fruit", this.fruit.toString());
/*    */       }
/* 52 */       return jsonobject;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\triggers\ConsumeDevilFruitTrigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */