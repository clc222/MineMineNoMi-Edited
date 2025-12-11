/*    */ package xyz.pixelatedw.mineminenomi.api.protection.block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ 
/*    */ public class RestrictedBlockProtectionRule extends BlockProtectionRule {
/*  8 */   public static final BlockProtectionRule INSTANCE = (new BlockProtectionRule.Builder(new BlockProtectionRule[0]))
/*  9 */     .addBannedBlocks(new RegistryObject[] { ModBlocks.PONEGLYPH, ModBlocks.BARRIER, ModBlocks.OPE, ModBlocks.STRING_WALL
/* 10 */       }).addBannedBlocks(new Block[] { Blocks.field_150357_h, Blocks.field_180401_cv, Blocks.field_150483_bI, Blocks.field_185777_dd, Blocks.field_185776_dc, Blocks.field_185775_db, Blocks.field_150384_bq, Blocks.field_150378_br
/*    */       
/* 12 */       }).build();
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\protection\block\RestrictedBlockProtectionRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */