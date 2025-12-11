/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.IBlockOverlayEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BlackBoxEffect extends ModEffect implements IBlockOverlayEffect {
/*    */   public BlackBoxEffect() {
/* 15 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/* 16 */     func_220304_a(Attributes.field_233821_d_, "b1ddc653-67cc-4eb4-b6ee-8be108e70e2e", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/* 17 */       .func_220304_a(Attributes.field_233820_c_, "7d355019-7ef9-4beb-bcba-8b2608a73380", 1.0D, AttributeModifier.Operation.ADDITION)
/* 18 */       .func_220304_a((Attribute)ModAttributes.DAMAGE_REDUCTION.get(), "9684c58c-8dc7-4828-a805-2b1517a9dd83", 0.5D, AttributeModifier.Operation.ADDITION)
/* 19 */       .func_220304_a((Attribute)ModAttributes.JUMP_HEIGHT.get(), "bacea9ea-a77a-4296-93db-90548eb2d30d", -256.0D, AttributeModifier.Operation.ADDITION);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldUpdateClient() {
/* 24 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBlockingRotations() {
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Block getBlockOverlay(int duration, int amplifier) {
/* 34 */     return (Block)ModBlocks.DARKNESS.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 39 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public double func_111183_a(int amp, AttributeModifier mod) {
/* 44 */     return mod.func_111164_d();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\BlackBoxEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */