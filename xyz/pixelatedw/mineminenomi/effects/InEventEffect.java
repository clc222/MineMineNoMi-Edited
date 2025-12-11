/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraftforge.common.ForgeMod;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ 
/*    */ public class InEventEffect extends ModEffect {
/*    */   public InEventEffect() {
/* 13 */     super(EffectType.NEUTRAL, 0);
/* 14 */     func_220304_a(Attributes.field_233821_d_, "2727e176-e9e8-4523-92f8-22619308d9ee", -256.0D, AttributeModifier.Operation.ADDITION)
/* 15 */       .func_220304_a((Attribute)ForgeMod.SWIM_SPEED.get(), "323ffb58-0b57-434e-bdfc-354670e22d5f", -256.0D, AttributeModifier.Operation.ADDITION)
/* 16 */       .func_220304_a(Attributes.field_233825_h_, "777f14bf-fe3f-4f19-9d2c-eb69c04d5209", -4.0D, AttributeModifier.Operation.ADDITION)
/* 17 */       .func_220304_a((Attribute)ModAttributes.JUMP_HEIGHT.get(), "e8cd65cb-2768-4fd8-aa54-bdcda029aaff", -256.0D, AttributeModifier.Operation.ADDITION)
/* 18 */       .func_220304_a(Attributes.field_233820_c_, "f8b2474d-4cdb-42b0-a868-327443a2a505", 100.0D, AttributeModifier.Operation.ADDITION);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldUpdateClient() {
/* 23 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBlockingRotations() {
/* 28 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBlockingSwings() {
/* 33 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 38 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\InEventEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */