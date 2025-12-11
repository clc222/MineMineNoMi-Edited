/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class SmokeEffect extends Effect implements ITransmisibleByTouchEffect {
/*    */   public SmokeEffect() {
/* 14 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/* 15 */     func_220304_a((Attribute)ModAttributes.REGEN_RATE.get(), "7d355019-7ef9-4beb-bcba-8b2608a73380", -0.25D, AttributeModifier.Operation.ADDITION);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier) {
/* 20 */     return (duration % 5 == 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_76394_a(LivingEntity entity, int amplifier) {
/* 25 */     if (entity.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/* 28 */     int time = entity.func_70660_b(this).func_76459_b();
/* 29 */     if (time % 10 == 0) {
/* 30 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.SMOKE_DEBUFF.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isTransmisibleByTouch() {
/* 42 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\SmokeEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */