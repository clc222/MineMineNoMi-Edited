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
/*    */ public class SelectFactionTrigger
/*    */   extends AbstractCriterionTrigger<SelectFactionTrigger.Instance> {
/* 15 */   private static final ResourceLocation ID = new ResourceLocation("mineminenomi", "select_faction");
/*    */ 
/*    */   
/*    */   public ResourceLocation func_192163_a() {
/* 19 */     return ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public Instance createInstance(JsonObject json, EntityPredicate.AndPredicate entityPredicate, ConditionArrayParser parser) {
/* 24 */     ResourceLocation faction = new ResourceLocation(JSONUtils.func_151200_h(json, "faction"));
/* 25 */     return new Instance(entityPredicate, faction);
/*    */   }
/*    */   
/*    */   public void trigger(ServerPlayerEntity player, ResourceLocation faction) {
/* 29 */     func_235959_a_(player, inst -> inst.matches(faction));
/*    */   }
/*    */   
/*    */   public static class Instance extends CriterionInstance {
/*    */     private final ResourceLocation faction;
/*    */     
/*    */     public Instance(EntityPredicate.AndPredicate entityPredicate, ResourceLocation faction) {
/* 36 */       super(SelectFactionTrigger.ID, entityPredicate);
/* 37 */       this.faction = faction;
/*    */     }
/*    */     
/*    */     public boolean matches(ResourceLocation faction) {
/* 41 */       return this.faction.equals(faction);
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject func_230240_a_(ConditionArraySerializer pConditions) {
/* 46 */       JsonObject jsonobject = super.func_230240_a_(pConditions);
/* 47 */       jsonobject.addProperty("faction", this.faction.toString());
/* 48 */       return jsonobject;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\triggers\SelectFactionTrigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */