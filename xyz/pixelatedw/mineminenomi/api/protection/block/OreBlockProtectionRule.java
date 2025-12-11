/*    */ package xyz.pixelatedw.mineminenomi.api.protection.block;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ 
/*    */ public class OreBlockProtectionRule extends BlockProtectionRule {
/*  8 */   public static final BlockProtectionRule INSTANCE = (new BlockProtectionRule.Builder(new BlockProtectionRule[0]))
/*  9 */     .addApprovedBlocks(new Block[] { Blocks.field_150365_q, Blocks.field_150366_p, Blocks.field_150352_o, Blocks.field_150482_ag, Blocks.field_150412_bA, Blocks.field_150450_ax, Blocks.field_150369_x, Blocks.field_196766_fg
/*    */       
/* 11 */       }).build();
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\protection\block\OreBlockProtectionRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */