/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraftforge.common.ForgeMod;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ 
/*    */ public class DFCurseEffect extends ModEffect {
/*    */   public DFCurseEffect() {
/* 12 */     super(EffectType.HARMFUL, 0);
/* 13 */     func_220304_a(Attributes.field_233821_d_, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.699999988079071D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/* 14 */     func_220304_a((Attribute)ForgeMod.SWIM_SPEED.get(), "5a6ce15a-28c7-4d4b-91c5-cd98a061605b", -0.699999988079071D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/* 15 */     func_220304_a(Attributes.field_233825_h_, "55FCED67-E92A-486E-9800-B47F202C4386", -0.5D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/* 16 */     func_220304_a(Attributes.field_233823_f_, "22653B89-116E-49DC-9B6B-9971489B5BE5", -4.0D, AttributeModifier.Operation.ADDITION);
/* 17 */     func_220304_a((Attribute)ModAttributes.MINING_SPEED.get(), "e8207b5d-8635-40fc-8674-d7db8aae0b72", -0.5D, AttributeModifier.Operation.ADDITION);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldUpdateClient() {
/* 22 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\DFCurseEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */