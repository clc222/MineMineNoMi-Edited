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
/*    */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ 
/*    */ public class UnlockAbilityTrigger
/*    */   extends AbstractCriterionTrigger<UnlockAbilityTrigger.Instance>
/*    */ {
/* 18 */   private static final ResourceLocation ID = new ResourceLocation("mineminenomi", "unlock_ability");
/*    */ 
/*    */   
/*    */   public ResourceLocation func_192163_a() {
/* 22 */     return ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public Instance createInstance(JsonObject json, EntityPredicate.AndPredicate pEntityPredicate, ConditionArrayParser pConditionsParser) {
/* 27 */     AbilityCore ability = (AbilityCore)GameRegistry.findRegistry(AbilityCore.class).getValue(new ResourceLocation(JSONUtils.func_151200_h(json, "ability")));
/* 28 */     return new Instance(pEntityPredicate, ability);
/*    */   }
/*    */   
/*    */   public void trigger(ServerPlayerEntity player, AbilityCore ability) {
/* 32 */     func_235959_a_(player, instance -> instance.matches(player, ability));
/*    */   }
/*    */   
/*    */   public static class Instance
/*    */     extends CriterionInstance
/*    */   {
/*    */     private AbilityCore ability;
/*    */     
/*    */     public Instance(EntityPredicate.AndPredicate entityPredicate, AbilityCore ability) {
/* 41 */       super(UnlockAbilityTrigger.ID, entityPredicate);
/* 42 */       this.ability = ability;
/*    */     }
/*    */     
/*    */     public boolean matches(ServerPlayerEntity player, AbilityCore ability) {
/* 46 */       return this.ability.equals(ability);
/*    */     }
/*    */     
/*    */     public static Instance ability(AbilityCore<?> core) {
/* 50 */       return new Instance(EntityPredicate.AndPredicate.field_234582_a_, core);
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject func_230240_a_(ConditionArraySerializer pConditions) {
/* 55 */       JsonObject jsonobject = super.func_230240_a_(pConditions);
/* 56 */       jsonobject.addProperty("ability", this.ability.getRegistryName().toString());
/* 57 */       return jsonobject;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\triggers\UnlockAbilityTrigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */