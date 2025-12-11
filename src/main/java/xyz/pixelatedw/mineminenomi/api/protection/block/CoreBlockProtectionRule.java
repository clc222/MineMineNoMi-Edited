/*    */ package xyz.pixelatedw.mineminenomi.api.protection.block;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMaterials;
/*    */ 
/*    */ public class CoreBlockProtectionRule extends BlockProtectionRule {
/* 11 */   public static final BlockProtectionRule INSTANCE = (new BlockProtectionRule.Builder(new BlockProtectionRule[0]))
/* 12 */     .addApprovedMaterials(new Material[] { 
/*    */         Material.field_151576_e, Material.field_151573_f, Material.field_151575_d, Material.field_151597_y, Material.field_151596_z, ModMaterials.HARDENED_SNOW_BLOCK, Material.field_151594_q, Material.field_151595_p, Material.field_151592_s, Material.field_151574_g, 
/*    */         Material.field_215713_z, Material.field_215712_y, Material.field_151570_A, Material.field_151568_F, Material.field_151593_r, Material.field_151571_B, Material.field_151589_v, Material.field_151577_b, Material.field_151578_c, Material.field_151588_w, 
/*    */         Material.field_151598_x, Material.field_151572_C, Material.field_151569_G, Material.field_151580_n
/* 16 */       }).addApprovedBlocks(new RegistryObject[] { ModBlocks.SUNA_SAND, ModBlocks.CANDY, ModBlocks.WAX, ModBlocks.POISON, ModBlocks.DEMON_POISON, ModBlocks.SKY_BLOCK, ModBlocks.SPONGE_CAKE
/* 17 */       }).addApprovedBlocks(new Block[] { Blocks.field_150432_aD, Blocks.field_205164_gk, Blocks.field_150335_W
/* 18 */       }).addBannedBlocks(RestrictedBlockProtectionRule.INSTANCE)
/* 19 */     .build();
/*    */   
/* 21 */   public static final BlockProtectionRule INSTANCE_WITHOUT_RESTRICTION = (new BlockProtectionRule.Builder(new BlockProtectionRule[0]))
/* 22 */     .addApprovedMaterials(new Material[] { 
/*    */         Material.field_151576_e, Material.field_151573_f, Material.field_151575_d, Material.field_151597_y, Material.field_151596_z, ModMaterials.HARDENED_SNOW_BLOCK, Material.field_151594_q, Material.field_151595_p, Material.field_151592_s, Material.field_151574_g, 
/*    */         Material.field_215713_z, Material.field_215712_y, Material.field_151570_A, Material.field_151568_F, Material.field_151593_r, Material.field_151571_B, Material.field_151589_v, Material.field_151577_b, Material.field_151578_c, Material.field_151588_w, 
/*    */         Material.field_151598_x, Material.field_151572_C, Material.field_151569_G, Material.field_151580_n
/* 26 */       }).addApprovedBlocks(new RegistryObject[] { ModBlocks.SUNA_SAND, ModBlocks.CANDY, ModBlocks.WAX, ModBlocks.POISON, ModBlocks.DEMON_POISON, ModBlocks.SKY_BLOCK, ModBlocks.SPONGE_CAKE
/* 27 */       }).addApprovedBlocks(new Block[] { Blocks.field_150432_aD, Blocks.field_205164_gk, Blocks.field_150335_W
/* 28 */       }).build();
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\protection\block\CoreBlockProtectionRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */