/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class CandleLockEffect extends ModEffect {
/*    */   public CandleLockEffect() {
/* 12 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/* 13 */     func_220304_a(Attributes.field_233821_d_, "30ee2305-2aaa-4ed8-9c7b-de0ae33a2024", -1000.0D, AttributeModifier.Operation.ADDITION)
/* 14 */       .func_220304_a(Attributes.field_233820_c_, "0d80c985-ee10-49c9-bc0b-d9eb0cdb9666", 100.0D, AttributeModifier.Operation.ADDITION)
/* 15 */       .func_220304_a((Attribute)ModAttributes.JUMP_HEIGHT.get(), "b94c7ca4-ead0-4927-b4c1-51033159a9ff", -256.0D, AttributeModifier.Operation.ADDITION);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldUpdateClient() {
/* 20 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 25 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\CandleLockEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */