/*    */ package xyz.pixelatedw.mineminenomi.api.protection.block;
/*    */ 
/*    */ import net.minecraft.block.material.Material;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMaterials;
/*    */ 
/*    */ public class SnowLayerBlockProtectionRule
/*    */   extends BlockProtectionRule {
/*  9 */   public static final BlockProtectionRule INSTANCE = (new BlockProtectionRule.Builder(new BlockProtectionRule[0]))
/* 10 */     .addApprovedMaterials(new Material[] { Material.field_151596_z, Material.field_151597_y, ModMaterials.HARDENED_SNOW_BLOCK
/* 11 */       }).build();
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\protection\block\SnowLayerBlockProtectionRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */