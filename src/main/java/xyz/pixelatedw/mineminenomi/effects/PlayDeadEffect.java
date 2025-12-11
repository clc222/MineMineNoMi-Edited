/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraftforge.common.ForgeMod;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ 
/*    */ public class PlayDeadEffect extends ModEffect {
/*    */   public PlayDeadEffect() {
/* 15 */     super(EffectType.NEUTRAL, 0);
/*    */     
/* 17 */     func_220304_a(Attributes.field_233821_d_, "91b76470-9478-41c3-ae57-9349c5a21213", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/* 18 */       .func_220304_a((Attribute)ForgeMod.SWIM_SPEED.get(), "a531f953-d6a2-49a8-9752-d696b3b025af", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/* 19 */       .func_220304_a(Attributes.field_233825_h_, "208686b9-c2be-4e8d-96b1-edbe74aa071f", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/* 20 */       .func_220304_a((Attribute)ModAttributes.JUMP_HEIGHT.get(), "cc4d894b-b020-4aea-96b4-524eb7fcebf0", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/* 21 */       .func_220304_a(Attributes.field_233820_c_, "99ac5afe-178e-48bc-80f5-854823285f8c", 1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier) {
/* 26 */     int k = 20 >> amplifier;
/*    */     
/* 28 */     if (k > 0) {
/* 29 */       return (duration % k == 0);
/*    */     }
/* 31 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_76394_a(LivingEntity entity, int amplifier) {
/* 37 */     if (entity.func_110143_aJ() < entity.func_110138_aP()) {
/* 38 */       entity.func_70691_i(3.0F);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldUpdateClient() {
/* 44 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRender(EffectInstance effect) {
/* 49 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRenderHUD(EffectInstance effect) {
/* 54 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBlockingRotations() {
/* 59 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBlockingSwings() {
/* 64 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 69 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\PlayDeadEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */