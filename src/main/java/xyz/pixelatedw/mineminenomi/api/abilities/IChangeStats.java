/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public interface IChangeStats
/*    */ {
/*    */   default void applyModifier(LivingEntity entity, Attribute attr, AttributeModifier modifier) {
/* 14 */     removeModifier(entity, attr, modifier);
/* 15 */     entity.func_110148_a(attr).func_233767_b_(modifier);
/*    */   }
/*    */ 
/*    */   
/*    */   default void applyMissingModifier(LivingEntity entity, Attribute attr, AttributeModifier modifier) {
/* 20 */     if (!hasModifier(entity, attr, modifier.func_111167_a())) {
/* 21 */       applyModifier(entity, attr, modifier);
/*    */     }
/*    */   }
/*    */   
/*    */   default void removeModifier(LivingEntity entity, Attribute attr, AttributeModifier modifier) {
/* 26 */     removeModifier(entity, attr, modifier.func_111167_a());
/*    */   }
/*    */ 
/*    */   
/*    */   default void removeModifier(LivingEntity entity, Attribute attr, UUID id) {
/* 31 */     entity.func_110148_a(attr).func_188479_b(id);
/*    */   }
/*    */ 
/*    */   
/*    */   default boolean hasModifier(LivingEntity entity, Attribute attr, UUID id) {
/* 36 */     return (entity.func_110148_a(attr).func_111127_a(id) != null);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\IChangeStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */