/*    */ package xyz.pixelatedw.mineminenomi.api.protection.block;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.block.material.Material;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ 
/*    */ public class FoliageBlockProtectionRule extends BlockProtectionRule {
/*  9 */   public static final BlockProtectionRule INSTANCE = (new BlockProtectionRule.Builder(new BlockProtectionRule[0]))
/* 10 */     .addApprovedMaterials(new Material[] { Material.field_151584_j, Material.field_151585_k, Material.field_151582_l, Material.field_203243_f, Material.field_204868_h, Material.field_242934_h, Material.field_215713_z, Material.field_215712_y
/*    */       
/* 12 */       }).addApprovedBlocks(new Block[] { 
/*    */         Blocks.field_196706_do, Blocks.field_150420_aW, Blocks.field_150419_aX, Blocks.field_150423_aK, Blocks.field_150440_ba, Blocks.field_150394_bc, Blocks.field_212586_jZ, Blocks.field_211902_kq, Blocks.field_211897_kl, Blocks.field_212587_ka,
/*    */         
/*    */         Blocks.field_211903_kr, Blocks.field_211898_km, Blocks.field_212588_kb, Blocks.field_211904_ks, Blocks.field_211899_kn, Blocks.field_212585_jY, Blocks.field_211901_kp, Blocks.field_211896_kk, Blocks.field_212589_kc, Blocks.field_211905_kt, 
/*    */         Blocks.field_211900_ko, Blocks.field_196604_cC, Blocks.field_196553_aF
/* 17 */       }).build();
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\protection\block\FoliageBlockProtectionRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */