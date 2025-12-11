/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class WashedEffect extends Effect {
/*    */   public WashedEffect() {
/* 14 */     super(EffectType.BENEFICIAL, WyHelper.hexToRGB("#000000").getRGB());
/* 15 */     func_220304_a(Attributes.field_233821_d_, "63f9c447-3f2d-4ac9-bf17-ea84f5352d46", -0.5D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/* 16 */       .func_220304_a((Attribute)ModAttributes.JUMP_HEIGHT.get(), "c6344fb0-aee5-46f3-89ef-1791256de5ad", -0.5D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/* 17 */       .func_220304_a(Attributes.field_233825_h_, "887875d0-c7c2-4d6d-afc7-0381d60bda6e", -4.0D, AttributeModifier.Operation.ADDITION)
/* 18 */       .func_220304_a(Attributes.field_233823_f_, "957a1347-334f-4988-825b-73dd9de5b9b0", -6.0D, AttributeModifier.Operation.ADDITION);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier) {
/* 23 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_76394_a(LivingEntity entity, int amplifier) {
/* 28 */     if (entity.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/* 31 */     int time = entity.func_70660_b(this).func_76459_b();
/* 32 */     if (time % 10 == 0) {
/* 33 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.WASHED.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*    */     }
/*    */     
/* 36 */     if (entity.func_70026_G())
/* 37 */       entity.func_195063_d(this); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\WashedEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */