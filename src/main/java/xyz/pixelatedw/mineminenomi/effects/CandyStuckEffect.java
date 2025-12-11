/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraftforge.common.ForgeMod;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.DamageOverTimeEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.IBlockOverlayEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.IBreakableEffect;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ 
/*    */ public class CandyStuckEffect extends DamageOverTimeEffect implements IBlockOverlayEffect, IBreakableEffect {
/*    */   public CandyStuckEffect() {
/* 16 */     super(DamageSource.field_76369_e, 1.0F, 40);
/*    */     
/* 18 */     func_220304_a(Attributes.field_233821_d_, "f695cbb3-1223-4c64-97e2-c7979775fd4d", -100.0D, AttributeModifier.Operation.ADDITION);
/* 19 */     func_220304_a((Attribute)ForgeMod.SWIM_SPEED.get(), "346e1665-7959-4a14-86ed-c8e2d1e5cd9a", -256.0D, AttributeModifier.Operation.ADDITION);
/* 20 */     func_220304_a(Attributes.field_233825_h_, "23b16162-7090-4f27-87cc-613445852721", -4.0D, AttributeModifier.Operation.ADDITION);
/* 21 */     func_220304_a(Attributes.field_233820_c_, "644893f5-3ae2-4b7c-bc61-32a7a7711286", 100.0D, AttributeModifier.Operation.ADDITION);
/* 22 */     func_220304_a((Attribute)ModAttributes.REGEN_RATE.get(), "7d355019-7ef9-4beb-bcba-8b2608a73380", -4.0D, AttributeModifier.Operation.ADDITION);
/* 23 */     func_220304_a((Attribute)ModAttributes.TOUGHNESS.get(), "77fb7784-2d75-4e0a-8f39-f5c2dcd4fca8", 2.0D, AttributeModifier.Operation.ADDITION);
/* 24 */     func_220304_a((Attribute)ModAttributes.JUMP_HEIGHT.get(), "fa4d711c-faa4-41cd-83c9-8f97edc5800e", -256.0D, AttributeModifier.Operation.ADDITION);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldUpdateClient() {
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBlockingRotations() {
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Block getBlockOverlay(int duration, int amplifier) {
/* 39 */     return (Block)ModBlocks.CANDY.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBlockingSwings() {
/* 49 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\CandyStuckEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */