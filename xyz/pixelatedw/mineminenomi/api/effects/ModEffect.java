/*    */ package xyz.pixelatedw.mineminenomi.api.effects;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifierManager;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ModEffect extends Effect implements IIgnoreMilkEffect, IBindHandsEffect {
/*    */   public ModEffect(EffectType typeIn, int liquidColorIn) {
/* 12 */     super(typeIn, liquidColorIn);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_111185_a(LivingEntity entity, AttributeModifierManager map, int amp) {
/* 17 */     super.func_111185_a(entity, map, amp);
/*    */     
/* 19 */     EffectInstance instance = entity.func_70660_b(this);
/*    */     
/* 21 */     startEffect(entity, instance);
/*    */     
/* 23 */     if (shouldUpdateClient()) {
/* 24 */       WyHelper.sendApplyEffectToAllNearby(entity, entity.func_213303_ch(), 100, entity.func_70660_b(this));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_111187_a(LivingEntity entity, AttributeModifierManager map, int amp) {
/* 30 */     super.func_111187_a(entity, map, amp);
/*    */     
/* 32 */     stopEffect(entity);
/*    */     
/* 34 */     if (shouldUpdateClient()) {
/* 35 */       WyHelper.sendRemoveEffectToAllNearby(entity, entity.func_213303_ch(), 100, this);
/*    */     }
/*    */   }
/*    */   
/*    */   public void startEffect(LivingEntity entity, EffectInstance instance) {}
/*    */   
/*    */   public void stopEffect(LivingEntity entity) {}
/*    */   
/*    */   public boolean shouldUpdateClient() {
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBlockingRotations() {
/* 49 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBlockingSwings() {
/* 54 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 59 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\effects\ModEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */