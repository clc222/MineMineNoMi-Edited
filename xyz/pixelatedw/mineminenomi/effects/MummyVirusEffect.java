/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.IColorOverlayEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ITransmisibleByProximityEffect;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class MummyVirusEffect
/*    */   extends Effect
/*    */   implements IColorOverlayEffect, ITransmisibleByProximityEffect {
/*    */   public MummyVirusEffect() {
/* 17 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier) {
/* 22 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_76394_a(LivingEntity entity, int amplifier) {
/* 27 */     if (entity.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/*    */     
/* 31 */     int time = entity.func_70660_b(this).func_76459_b();
/* 32 */     if (time % 10 == 0) {
/* 33 */       if (!entity.func_70644_a((Effect)ModEffects.HUNGER.get())) {
/* 34 */         entity.func_195064_c(new EffectInstance((Effect)ModEffects.HUNGER.get(), 20, 0));
/*    */       }
/*    */       
/* 37 */       if (!entity.func_70644_a((Effect)ModEffects.BLEEDING.get())) {
/* 38 */         entity.func_195064_c(new EffectInstance((Effect)ModEffects.BLEEDING.get(), 20, 0));
/*    */       }
/*    */     } 
/*    */     
/* 42 */     if (time < 20) {
/* 43 */       entity.func_195064_c(new EffectInstance((Effect)ModEffects.MUMMY_VIRUS_ANTIBODY.get(), 840, 0));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public float[] getBodyOverlayColor(int duration, int amplifier) {
/* 50 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] getViewOverlayColor(int duration, int amplifier) {
/* 56 */     return new float[] { 0.0F, 0.0F, 0.0F, 0.5F };
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTransmisibleByProximity() {
/* 61 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public float poximityTransmisionDistance() {
/* 66 */     return 2.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\MummyVirusEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */