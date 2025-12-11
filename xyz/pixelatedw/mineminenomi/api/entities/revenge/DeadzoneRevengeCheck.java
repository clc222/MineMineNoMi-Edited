/*    */ package xyz.pixelatedw.mineminenomi.api.entities.revenge;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DeadzoneRevengeCheck
/*    */   implements IRevengeCheck
/*    */ {
/*    */   private final int revengeGain;
/* 14 */   private final float deadzone = 2.0F;
/*    */   
/*    */   private Vector3d pivot;
/*    */   
/*    */   public DeadzoneRevengeCheck(int revengeGain) {
/* 19 */     this.revengeGain = revengeGain;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean check(LivingEntity entity) {
/* 26 */     if (entity instanceof MobEntity) {
/* 27 */       MobEntity mob = (MobEntity)entity;
/* 28 */       if (mob.func_70638_az() != null && entity.func_110144_aD() != null && mob.func_70638_az().equals(entity.func_110144_aD()) && entity
/* 29 */         .func_142013_aG() + 50 > entity.field_70173_aa) {
/* 30 */         return false;
/*    */       }
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 36 */     for (EffectInstance inst : entity.func_70651_bq()) {
/* 37 */       if (inst.func_188419_a() instanceof xyz.pixelatedw.mineminenomi.effects.InEventEffect || inst.func_188419_a() instanceof xyz.pixelatedw.mineminenomi.effects.UnconsciousEffect || inst.func_188419_a() instanceof xyz.pixelatedw.mineminenomi.effects.GuardingEffect) {
/* 38 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 42 */     if (this.pivot == null) {
/* 43 */       this.pivot = entity.func_213303_ch();
/*    */     }
/*    */     
/* 46 */     getClass(); getClass(); if (this.pivot.func_72438_d(entity.func_213303_ch()) < (2.0F * 2.0F)) {
/* 47 */       this.pivot = entity.func_213303_ch();
/* 48 */       return true;
/*    */     } 
/*    */     
/* 51 */     this.pivot = entity.func_213303_ch();
/* 52 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void resetMarkers() {
/* 57 */     this.pivot = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public int revengeMeterGain() {
/* 62 */     return this.revengeGain;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\entities\revenge\DeadzoneRevengeCheck.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */