/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class TensionHormoneEffect
/*    */   extends Effect {
/*    */   public TensionHormoneEffect() {
/* 14 */     super(EffectType.BENEFICIAL, WyHelper.hexToRGB("#000000").getRGB());
/* 15 */     func_220304_a(Attributes.field_233823_f_, "c24353cb-2efd-491f-a3f3-55a853d707ef", 0.5D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/* 16 */       .func_220304_a(Attributes.field_233821_d_, "1abe0056-96d4-4cf8-962c-cbf7a54f28f3", 0.5D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/* 17 */       .func_220304_a((Attribute)ModAttributes.JUMP_HEIGHT.get(), "dd83d35f-5190-4840-a582-f538300ce0af", 0.5D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/* 18 */       .func_220304_a(Attributes.field_233820_c_, "7d355019-7ef9-4beb-bcba-8b2608a73380", 10.0D, AttributeModifier.Operation.ADDITION);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\TensionHormoneEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */