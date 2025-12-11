/*    */ package xyz.pixelatedw.mineminenomi.data.triggers;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
/*    */ import net.minecraft.advancements.criterion.CriterionInstance;
/*    */ import net.minecraft.advancements.criterion.EntityPredicate;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.loot.ConditionArrayParser;
/*    */ import net.minecraft.loot.ConditionArraySerializer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ 
/*    */ public class YomiReviveTrigger
/*    */   extends AbstractCriterionTrigger<YomiReviveTrigger.Instance> {
/* 20 */   private static final ResourceLocation ID = new ResourceLocation("mineminenomi", "yomi_revive");
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation func_192163_a() {
/* 25 */     return ID;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Instance createInstance(JsonObject json, EntityPredicate.AndPredicate entityPredicate, ConditionArrayParser parser) {
/* 31 */     return new Instance(entityPredicate);
/*    */   }
/*    */ 
/*    */   
/*    */   public void trigger(ServerPlayerEntity player) {
/* 36 */     func_235959_a_(player, inst -> {
/*    */           IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/* 38 */           return (props.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) && !((MorphInfo)ModMorphs.YOMI_SKELETON.get()).isActive((LivingEntity)player));
/*    */         });
/*    */   }
/*    */   
/*    */   public static class Instance
/*    */     extends CriterionInstance
/*    */   {
/*    */     public Instance(EntityPredicate.AndPredicate entityPredicate) {
/* 46 */       super(YomiReviveTrigger.ID, entityPredicate);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public JsonObject func_230240_a_(ConditionArraySerializer pConditions) {
/* 52 */       JsonObject jsonobject = super.func_230240_a_(pConditions);
/* 53 */       return jsonobject;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\triggers\YomiReviveTrigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */