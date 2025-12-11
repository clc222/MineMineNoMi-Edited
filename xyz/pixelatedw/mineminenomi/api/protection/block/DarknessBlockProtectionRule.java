/*   */ package xyz.pixelatedw.mineminenomi.api.protection.block;
/*   */ import net.minecraft.block.Block;
/*   */ import net.minecraft.block.Blocks;
/*   */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*   */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*   */ 
/*   */ public class DarknessBlockProtectionRule {
/* 8 */   public static final BlockProtectionRule INSTANCE = (new BlockProtectionRule.Builder(new BlockProtectionRule[0])).addApprovedBlocks(new Block[] { (Block)ModBlocks.DARKNESS.get(), Blocks.field_150350_a }).build();
/*   */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\protection\block\DarknessBlockProtectionRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */