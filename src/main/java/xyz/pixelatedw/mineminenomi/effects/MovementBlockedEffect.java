/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraftforge.common.ForgeMod;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class MovementBlockedEffect extends ModEffect {
/*    */   public MovementBlockedEffect() {
/* 14 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/*    */     
/* 16 */     func_220304_a(Attributes.field_233821_d_, "2727e176-e9e8-4523-92f8-22619308d9ee", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/* 17 */     func_220304_a(Attributes.field_233825_h_, "e6435603-f78f-42a2-9b21-ff8b899f2a59", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/* 18 */     func_220304_a((Attribute)ForgeMod.SWIM_SPEED.get(), "323ffb58-0b57-434e-bdfc-354670e22d5f", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/* 19 */     func_220304_a((Attribute)ModAttributes.JUMP_HEIGHT.get(), "e8cd65cb-2768-4fd8-aa54-bdcda029aaff", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/* 20 */     func_220304_a(Attributes.field_233820_c_, "f8b2474d-4cdb-42b0-a868-327443a2a505", 1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRender(EffectInstance effect) {
/* 25 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRenderHUD(EffectInstance effect) {
/* 30 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 35 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\MovementBlockedEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */