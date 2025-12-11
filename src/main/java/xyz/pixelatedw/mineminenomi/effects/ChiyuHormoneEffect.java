/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ChiyuHormoneEffect extends Effect {
/*    */   public ChiyuHormoneEffect() {
/* 13 */     super(EffectType.NEUTRAL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier) {
/* 18 */     return (duration % 10 == 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_76394_a(LivingEntity entity, int amp) {
/* 23 */     int amplifier = 0;
/*    */     
/* 25 */     EffectInstance poisonInstance = entity.func_70660_b(Effects.field_76436_u);
/* 26 */     EffectInstance dokuPoisonInstance = entity.func_70660_b((Effect)ModEffects.DOKU_POISON.get());
/*    */     
/* 28 */     if (poisonInstance != null) {
/* 29 */       amplifier += poisonInstance.func_76458_c() + 1;
/*    */       
/* 31 */       entity.func_195063_d(poisonInstance.func_188419_a());
/*    */     } 
/*    */     
/* 34 */     if (dokuPoisonInstance != null) {
/* 35 */       amplifier += dokuPoisonInstance.func_76458_c() + 1;
/*    */       
/* 37 */       entity.func_195063_d(dokuPoisonInstance.func_188419_a());
/*    */     } 
/*    */     
/* 40 */     if (amplifier > 0) {
/* 41 */       entity.func_70606_j(entity.func_110143_aJ() - entity.func_110143_aJ() / 100.0F * 10.0F * amplifier);
/*    */       
/* 43 */       entity.func_195063_d(this);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\ChiyuHormoneEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */