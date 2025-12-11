/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ITextureOverlayEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class LovestruckEffect extends ModEffect implements ITextureOverlayEffect {
/* 13 */   private static final ResourceLocation TEXTURE = new ResourceLocation("minecraft", "textures/block/stone.png");
/*    */   
/*    */   public LovestruckEffect() {
/* 16 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/* 17 */     func_220304_a(Attributes.field_233821_d_, "eb1809e8-a81d-4076-94aa-6f06e3a64920", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/* 18 */       .func_220304_a(Attributes.field_233820_c_, "50b418ff-a637-47be-a13e-1a848dea1638", 1.0D, AttributeModifier.Operation.ADDITION)
/* 19 */       .func_220304_a((Attribute)ModAttributes.DAMAGE_REDUCTION.get(), "c8959c36-aa42-4df7-a403-6eee554bcc36", 0.75D, AttributeModifier.Operation.ADDITION)
/* 20 */       .func_220304_a((Attribute)ModAttributes.JUMP_HEIGHT.get(), "51958b7e-3eb7-439e-a49a-de7387037c2d", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldUpdateClient() {
/* 25 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBlockingRotations() {
/* 30 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 35 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBlockingSwings() {
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getBodyTexture(int duration, int amplifier) {
/* 45 */     return TEXTURE;
/*    */   }
/*    */ 
/*    */   
/*    */   public float[] getOverlayColor() {
/* 50 */     return new float[] { 1.0F, 1.0F, 1.0F, 0.8F };
/*    */   }
/*    */ 
/*    */   
/*    */   public double func_111183_a(int amp, AttributeModifier mod) {
/* 55 */     return mod.func_111164_d();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\LovestruckEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */