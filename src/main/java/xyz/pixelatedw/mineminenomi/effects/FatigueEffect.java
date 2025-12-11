/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class FatigueEffect extends Effect {
/*    */   public FatigueEffect() {
/* 13 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/* 14 */     func_220304_a(Attributes.field_233821_d_, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -0.01D, AttributeModifier.Operation.ADDITION)
/* 15 */       .func_220304_a(Attributes.field_233825_h_, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -0.1D, AttributeModifier.Operation.ADDITION)
/* 16 */       .func_220304_a(Attributes.field_233823_f_, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -1.0D, AttributeModifier.Operation.ADDITION)
/* 17 */       .func_220304_a((Attribute)ModAttributes.DAMAGE_REDUCTION.get(), "e6a25b36-894a-4051-961d-6de8869b2900", -0.05D, AttributeModifier.Operation.ADDITION)
/* 18 */       .func_220304_a((Attribute)ModAttributes.REGEN_RATE.get(), "e6a25b36-894a-4051-961d-6de8869b2900", -0.1D, AttributeModifier.Operation.ADDITION);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRender(EffectInstance effect) {
/* 23 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRenderHUD(EffectInstance effect) {
/* 28 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\FatigueEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */