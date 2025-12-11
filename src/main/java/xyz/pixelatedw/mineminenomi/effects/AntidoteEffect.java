/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class AntidoteEffect
/*    */   extends Effect
/*    */ {
/*    */   public AntidoteEffect() {
/* 17 */     super(EffectType.BENEFICIAL, WyHelper.hexToRGB("#000000").getRGB());
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
/* 30 */     int time = entity.func_70660_b(this).func_76459_b();
/* 31 */     if (time % 10 == 0) {
/*    */       
/* 33 */       Collection<EffectInstance> effects = entity.func_70651_bq();
/* 34 */       Collection<Effect> toRemove = new ArrayList<>();
/*    */       
/* 36 */       for (EffectInstance inst : effects) {
/* 37 */         if (isImmuneTo(inst.func_188419_a())) {
/* 38 */           toRemove.add(inst.func_188419_a());
/*    */         }
/*    */       } 
/*    */       
/* 42 */       for (Effect eff : toRemove) {
/* 43 */         entity.func_195063_d(eff);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean isImmuneTo(Effect effect) {
/* 49 */     if (effect == Effects.field_76440_q || effect == Effects.field_76431_k || effect == Effects.field_76436_u || effect == Effects.field_76438_s || effect == Effects.field_76437_t || effect == ModEffects.HUNGER
/* 50 */       .get()) {
/* 51 */       return true;
/*    */     }
/* 53 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\AntidoteEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */