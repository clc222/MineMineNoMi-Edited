/*    */ package xyz.pixelatedw.mineminenomi.api.protection.block;
/*    */ 
/*    */ import net.minecraft.block.material.Material;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ 
/*    */ public class PortalBlockProtectionRule
/*    */   extends BlockProtectionRule {
/*  8 */   public static final BlockProtectionRule INSTANCE = (new BlockProtectionRule.Builder(new BlockProtectionRule[0]))
/*  9 */     .addApprovedMaterials(new Material[] { Material.field_151567_E
/* 10 */       }).build();
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\protection\block\PortalBlockProtectionRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */