/*    */ package xyz.pixelatedw.mineminenomi.data.triggers;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
/*    */ import net.minecraft.advancements.criterion.CriterionInstance;
/*    */ import net.minecraft.advancements.criterion.EntityPredicate;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.loot.ConditionArrayParser;
/*    */ import net.minecraft.loot.ConditionArraySerializer;
/*    */ import net.minecraft.util.JSONUtils;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class SelectStyleTrigger
/*    */   extends AbstractCriterionTrigger<SelectStyleTrigger.Instance> {
/* 15 */   private static final ResourceLocation ID = new ResourceLocation("mineminenomi", "select_style");
/*    */ 
/*    */   
/*    */   public ResourceLocation func_192163_a() {
/* 19 */     return ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public Instance createInstance(JsonObject json, EntityPredicate.AndPredicate entityPredicate, ConditionArrayParser parser) {
/* 24 */     ResourceLocation style = new ResourceLocation(JSONUtils.func_151200_h(json, "style"));
/* 25 */     return new Instance(entityPredicate, style);
/*    */   }
/*    */   
/*    */   public void trigger(ServerPlayerEntity player, ResourceLocation style) {
/* 29 */     func_235959_a_(player, inst -> inst.matches(style));
/*    */   }
/*    */   
/*    */   public static class Instance extends CriterionInstance {
/*    */     private final ResourceLocation style;
/*    */     
/*    */     public Instance(EntityPredicate.AndPredicate entityPredicate, ResourceLocation style) {
/* 36 */       super(SelectStyleTrigger.ID, entityPredicate);
/* 37 */       this.style = style;
/*    */     }
/*    */     
/*    */     public boolean matches(ResourceLocation style) {
/* 41 */       return this.style.equals(style);
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject func_230240_a_(ConditionArraySerializer pConditions) {
/* 46 */       JsonObject jsonobject = super.func_230240_a_(pConditions);
/* 47 */       jsonobject.addProperty("style", this.style.toString());
/* 48 */       return jsonobject;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\triggers\SelectStyleTrigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */