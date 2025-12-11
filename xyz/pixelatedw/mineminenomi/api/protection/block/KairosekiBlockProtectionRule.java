/*    */ package xyz.pixelatedw.mineminenomi.api.protection.block;
/*    */ 
/*    */ import net.minecraft.tags.ITag;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*    */ 
/*    */ public class KairosekiBlockProtectionRule extends BlockProtectionRule {
/*  8 */   public static final BlockProtectionRule INSTANCE = (new BlockProtectionRule.Builder(new BlockProtectionRule[0]))
/*  9 */     .addApprovedTags(new ITag[] { (ITag)ModTags.Blocks.KAIROSEKI
/* 10 */       }).build();
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\protection\block\KairosekiBlockProtectionRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */