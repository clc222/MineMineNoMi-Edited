/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.IColorOverlayEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class StickyEffect extends ModEffect implements IColorOverlayEffect {
/*    */   public StickyEffect() {
/* 19 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/* 20 */     func_220304_a(Attributes.field_233820_c_, "9276cd71-345c-4420-ae9a-3c8f725908a3", 1.0D, AttributeModifier.Operation.ADDITION)
/* 21 */       .func_220304_a(Attributes.field_233821_d_, "b13ba7ec-7103-4160-a5ff-139534a44691", -0.5D, AttributeModifier.Operation.ADDITION)
/* 22 */       .func_220304_a((Attribute)ModAttributes.JUMP_HEIGHT.get(), "fb997caf-43b6-4dc2-b5da-b504ab41545a", -255.0D, AttributeModifier.Operation.ADDITION)
/* 23 */       .func_220304_a(Attributes.field_233825_h_, "c2731993-56d2-4c67-a6fe-cdd144bd9ff8", -4.0D, AttributeModifier.Operation.ADDITION)
/* 24 */       .func_220304_a(Attributes.field_233823_f_, "5208c5bf-44d0-4111-9787-f63db22f180c", -6.0D, AttributeModifier.Operation.ADDITION);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldUpdateClient() {
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier) {
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_76394_a(LivingEntity entity, int amplifier) {
/* 39 */     if (entity.func_70027_ad() && entity.field_70173_aa > 0) {
/* 40 */       entity.func_70066_B();
/* 41 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)entity, entity.field_70170_p, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), 6.0F);
/* 42 */       explosion.setExplosionSound(true);
/* 43 */       explosion.setDamageOwner(true);
/* 44 */       explosion.setDestroyBlocks(true);
/* 45 */       explosion.setFireAfterExplosion(true);
/* 46 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(6));
/* 47 */       explosion.setDamageEntities(true);
/* 48 */       explosion.setStaticDamage(100.0F);
/* 49 */       explosion.doExplosion();
/* 50 */       entity.func_195063_d((Effect)ModEffects.STICKY.get());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public float[] getBodyOverlayColor(int duration, int amplifier) {
/* 56 */     return new float[] { 0.62F, 0.78F, 0.0F, 0.85F };
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBlockingRotations() {
/* 61 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\StickyEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */