/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class AntiBodyMummyVirusEffect
/*    */   extends Effect {
/*    */   public AntiBodyMummyVirusEffect() {
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
/* 36 */     int time = entity.func_70660_b(this).func_76459_b();
/* 37 */     if (time % 10 == 0)
/* 38 */       entity.func_195063_d((Effect)ModEffects.MUMMY_VIRUS.get()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\AntiBodyMummyVirusEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */