/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraftforge.common.ForgeMod;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ParalysisEffect extends ModEffect {
/*    */   public ParalysisEffect() {
/* 17 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/* 18 */     func_220304_a(Attributes.field_233821_d_, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.1D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/* 19 */       .func_220304_a((Attribute)ForgeMod.SWIM_SPEED.get(), "36de9be6-6645-4124-b99d-325163e8c15f", -0.1D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/* 20 */       .func_220304_a(Attributes.field_233825_h_, "f132c77e-9f47-42bb-8233-81c89b8ddcab", -1.0D, AttributeModifier.Operation.ADDITION)
/* 21 */       .func_220304_a((Attribute)ModAttributes.JUMP_HEIGHT.get(), "18240623-27cc-4648-b148-ea3a6782bda6", -0.1D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier) {
/* 27 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_76394_a(LivingEntity entity, int amplifier) {
/* 33 */     if (amplifier > 0) {
/* 34 */       AbilityHelper.setDeltaMovement((Entity)entity, 0.0D, 0.0D, 0.0D);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 41 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBlockingSwings() {
/* 47 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\ParalysisEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */