/*    */ package xyz.pixelatedw.mineminenomi.api.protection.block;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.block.material.Material;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ 
/*    */ public class OceanPlantsProtectionRule extends BlockProtectionRule {
/*  9 */   public static final BlockProtectionRule INSTANCE = (new BlockProtectionRule.Builder(new BlockProtectionRule[0]))
/* 10 */     .addApprovedMaterials(new Material[] { Material.field_203243_f
/* 11 */       }).addApprovedBlocks(new Block[] { 
/*    */         Blocks.field_212586_jZ, Blocks.field_211902_kq, Blocks.field_211897_kl, Blocks.field_212587_ka, Blocks.field_211903_kr, Blocks.field_211898_km, Blocks.field_212588_kb, Blocks.field_211904_ks, Blocks.field_211899_kn, Blocks.field_212585_jY, 
/*    */         Blocks.field_211901_kp, Blocks.field_211896_kk, Blocks.field_212589_kc, Blocks.field_211905_kt, Blocks.field_211900_ko
/* 14 */       }).build();
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\protection\block\OceanPlantsProtectionRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */