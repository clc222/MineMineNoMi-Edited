/*    */ package xyz.pixelatedw.mineminenomi.api.protection.block;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ 
/*    */ public class ValuableBlockProtectionRule extends BlockProtectionRule {
/*  8 */   public static final BlockProtectionRule INSTANCE = (new BlockProtectionRule.Builder(new BlockProtectionRule[0]))
/*  9 */     .addApprovedBlocks(new Block[] { 
/*    */         Blocks.field_150402_ci, Blocks.field_150339_S, Blocks.field_150340_R, Blocks.field_150484_ah, Blocks.field_150475_bE, Blocks.field_150451_bX, Blocks.field_150368_y, Blocks.field_150371_ca, Blocks.field_196772_fk, Blocks.field_196581_bI, 
/*    */         Blocks.field_222453_lq, Blocks.field_222440_ld, Blocks.field_150461_bJ, Blocks.field_150380_bt
/* 12 */       }).build();
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\protection\block\ValuableBlockProtectionRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */