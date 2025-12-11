/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.IBreakableEffect;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class DizzyEffect extends Effect implements IBreakableEffect {
/*    */   public DizzyEffect() {
/* 13 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/* 14 */     func_220304_a(Attributes.field_233821_d_, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -0.025D, AttributeModifier.Operation.ADDITION)
/* 15 */       .func_220304_a((Attribute)ModAttributes.JUMP_HEIGHT.get(), "e6a25b36-894a-4051-961d-6de8869b2900", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBreakingMultiplier() {
/* 20 */     return 1.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\DizzyEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */