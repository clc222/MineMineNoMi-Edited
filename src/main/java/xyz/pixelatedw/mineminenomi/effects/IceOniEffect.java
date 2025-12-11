/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.IColorOverlayEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ITransmisibleByProximityEffect;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class IceOniEffect
/*    */   extends Effect
/*    */   implements IColorOverlayEffect, ITransmisibleByProximityEffect {
/*    */   public IceOniEffect() {
/* 18 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRender(EffectInstance effect) {
/* 23 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRenderHUD(EffectInstance effect) {
/* 28 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier) {
/* 33 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_76394_a(LivingEntity entity, int amplifier) {
/* 38 */     if (entity.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/*    */     
/* 42 */     int time = entity.func_70660_b(this).func_76459_b();
/*    */     
/* 44 */     if (time % 10 == 0) {
/* 45 */       if (!entity.func_70644_a(Effects.field_76420_g)) {
/* 46 */         entity.func_195064_c(new EffectInstance(Effects.field_76420_g, entity.func_70660_b(this).func_76459_b(), 0));
/*    */       }
/*    */       
/* 49 */       if (!entity.func_70644_a((Effect)ModEffects.SCALING_FROSTBITE.get())) {
/* 50 */         entity.func_195064_c(new EffectInstance((Effect)ModEffects.SCALING_FROSTBITE.get(), entity.func_70660_b(this).func_76459_b(), 0));
/*    */       }
/*    */     } 
/*    */     
/* 54 */     if (time < 20) {
/* 55 */       entity.func_195064_c(new EffectInstance((Effect)ModEffects.ICE_ONI_ANTIBODY.get(), 840, 0));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public float[] getBodyOverlayColor(int duration, int amplifier) {
/* 62 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] getViewOverlayColor(int duration, int amplifier) {
/* 68 */     return new float[] { 0.0F, 0.0F, 0.0F, 0.5F };
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTransmisibleByProximity() {
/* 73 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public float poximityTransmisionDistance() {
/* 78 */     return 2.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\IceOniEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */