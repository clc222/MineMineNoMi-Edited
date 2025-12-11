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
/*    */ public class SelectRaceTrigger
/*    */   extends AbstractCriterionTrigger<SelectRaceTrigger.Instance> {
/* 15 */   private static final ResourceLocation ID = new ResourceLocation("mineminenomi", "select_race");
/*    */ 
/*    */   
/*    */   public ResourceLocation func_192163_a() {
/* 19 */     return ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public Instance createInstance(JsonObject json, EntityPredicate.AndPredicate entityPredicate, ConditionArrayParser parser) {
/* 24 */     ResourceLocation race = new ResourceLocation(JSONUtils.func_151200_h(json, "race"));
/* 25 */     return new Instance(entityPredicate, race);
/*    */   }
/*    */   
/*    */   public void trigger(ServerPlayerEntity player, ResourceLocation faction) {
/* 29 */     func_235959_a_(player, inst -> inst.matches(faction));
/*    */   }
/*    */   
/*    */   public static class Instance extends CriterionInstance {
/*    */     private final ResourceLocation race;
/*    */     
/*    */     public Instance(EntityPredicate.AndPredicate entityPredicate, ResourceLocation race) {
/* 36 */       super(SelectRaceTrigger.ID, entityPredicate);
/* 37 */       this.race = race;
/*    */     }
/*    */     
/*    */     public boolean matches(ResourceLocation race) {
/* 41 */       return this.race.equals(race);
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject func_230240_a_(ConditionArraySerializer pConditions) {
/* 46 */       JsonObject jsonobject = super.func_230240_a_(pConditions);
/* 47 */       jsonobject.addProperty("race", this.race.toString());
/* 48 */       return jsonobject;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\triggers\SelectRaceTrigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */