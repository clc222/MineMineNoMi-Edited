/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class AntiBodyIceOniEffect
/*    */   extends Effect {
/*    */   public AntiBodyIceOniEffect() {
/* 13 */     super(EffectType.BENEFICIAL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRender(EffectInstance effect) {
/* 18 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRenderHUD(EffectInstance effect) {
/* 23 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier) {
/* 28 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_76394_a(LivingEntity entity, int amplifier) {
/* 33 */     if (entity.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/*    */     
/* 37 */     int time = entity.func_70660_b(this).func_76459_b();
/* 38 */     if (time % 10 == 0)
/* 39 */       entity.func_195063_d((Effect)ModEffects.ICE_ONI.get()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\AntiBodyIceOniEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */