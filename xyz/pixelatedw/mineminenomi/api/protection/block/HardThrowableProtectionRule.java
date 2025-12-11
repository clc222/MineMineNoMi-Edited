/*    */ package xyz.pixelatedw.mineminenomi.api.protection.block;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.block.material.Material;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ 
/*    */ public class HardThrowableProtectionRule extends BlockProtectionRule {
/*  9 */   public static final BlockProtectionRule INSTANCE = (new BlockProtectionRule.Builder(new BlockProtectionRule[0]))
/* 10 */     .addBannedBlocks(new Block[] { 
/*    */         Blocks.field_150402_ci, Blocks.field_150484_ah, Blocks.field_150475_bE, Blocks.field_150343_Z, Blocks.field_150339_S, Blocks.field_150467_bQ, Blocks.field_150381_bn, Blocks.field_150477_bB, Blocks.field_150355_j, Blocks.field_150353_l, 
/* 12 */         Blocks.field_150461_bJ, Blocks.field_150380_bt, Blocks.field_180399_cE, Blocks.field_226907_mc_ }).addApprovedMaterials(new Material[] { Material.field_151573_f, Material.field_151567_E
/* 13 */       }).build();
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\protection\block\HardThrowableProtectionRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */