/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraftforge.common.ForgeMod;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.IChangeSwingSpeedEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class NoroSlownessEffect extends ModEffect implements IChangeSwingSpeedEffect {
/*    */   public NoroSlownessEffect() {
/* 14 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/* 15 */     func_220304_a(Attributes.field_233825_h_, "8ab1e3cd-9688-402b-8876-73f314e174d2", -0.02500000037252903D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/* 16 */       .func_220304_a(Attributes.field_233821_d_, "bb904ec8-b548-4e1b-82ba-df237ad06f65", -0.25D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/* 17 */       .func_220304_a((Attribute)ForgeMod.SWIM_SPEED.get(), "e997a027-cb40-4900-a3b5-e7d1f6976a48", -0.25D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/* 18 */       .func_220304_a((Attribute)ModAttributes.JUMP_HEIGHT.get(), "61346567-335f-4a4d-a4a6-327faec00aa3", -0.25D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/* 19 */       .func_220304_a((Attribute)ModAttributes.TIME_PROGRESSION.get(), "15494972-058f-4202-8190-a8a6f64a248d", -0.10000000149011612D, AttributeModifier.Operation.ADDITION);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 24 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public float swingSpeedModifier(int duration, int amplifier) {
/* 29 */     return 2.0F + amplifier;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\NoroSlownessEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */