/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.IScreenShaderEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class NegativeEffect extends ModEffect implements IScreenShaderEffect {
/* 12 */   private static final ResourceLocation SHADER = new ResourceLocation("shaders/post/desaturate.json");
/*    */   
/*    */   public NegativeEffect() {
/* 15 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/* 16 */     func_220304_a(Attributes.field_233821_d_, "11147784-f615-47da-a28c-20c721cf5e9f", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/* 17 */       .func_220304_a(Attributes.field_233825_h_, "d23c3332-a0bf-4dde-80e1-1a6b936caf41", -1.5D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/* 18 */       .func_220304_a(Attributes.field_233823_f_, "d23c3332-a0bf-4dde-80e1-1a6b936caf41", -5.0D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 23 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getScreenShader() {
/* 28 */     return SHADER;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\NegativeEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */