/*    */ package xyz.pixelatedw.mineminenomi.datagen.tags;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.data.BlockTagsProvider;
/*    */ import net.minecraft.data.DataGenerator;
/*    */ import net.minecraft.tags.BlockTags;
/*    */ import net.minecraft.tags.ITag;
/*    */ import net.minecraftforge.common.Tags;
/*    */ import net.minecraftforge.common.data.ExistingFileHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*    */ 
/*    */ public class ModBlockTagsDataGen extends BlockTagsProvider {
/*    */   public ModBlockTagsDataGen(DataGenerator generator, ExistingFileHelper existingFileHelper) {
/* 16 */     super(generator, "mineminenomi", existingFileHelper);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_200432_c() {
/* 21 */     func_240522_a_((ITag.INamedTag)ModTags.Blocks.KAIROSEKI).func_240534_a_((Object[])new Block[] { (Block)ModBlocks.KAIROSEKI.get(), (Block)ModBlocks.KAIROSEKI_BARS.get(), (Block)ModBlocks.KAIROSEKI_ORE.get() });
/*    */     
/* 23 */     func_240522_a_((ITag.INamedTag)ModTags.Blocks.RUSTY).func_240534_a_((Object[])new Block[] { Blocks.field_150366_p, Blocks.field_150339_S, Blocks.field_150411_aY, Blocks.field_180400_cw, Blocks.field_150454_av, Blocks.field_150467_bQ, Blocks.field_196717_eY, Blocks.field_196718_eZ, Blocks.field_150438_bZ });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 34 */     func_240522_a_((ITag.INamedTag)ModTags.Blocks.NO_PHASE).func_240534_a_((Object[])new Block[] { Blocks.field_150357_h, Blocks.field_180401_cv, (Block)ModBlocks.KAIROSEKI
/*    */ 
/*    */           
/* 37 */           .get(), (Block)ModBlocks.BARRIER
/* 38 */           .get(), (Block)ModBlocks.KAIROSEKI_ORE
/* 39 */           .get(), (Block)ModBlocks.KAIROSEKI_BARS
/* 40 */           .get() });
/*    */     
/* 42 */     func_240522_a_((ITag.INamedTag)ModTags.Blocks.LOGIA_BLOCK_PASS_HIE)
/* 43 */       .func_240531_a_(BlockTags.field_205213_E);
/*    */     
/* 45 */     func_240522_a_((ITag.INamedTag)ModTags.Blocks.LOGIA_BLOCK_PASS_GORO)
/* 46 */       .func_240531_a_((ITag.INamedTag)ModTags.Blocks.RUSTY)
/* 47 */       .func_240531_a_((ITag.INamedTag)Tags.Blocks.STORAGE_BLOCKS_GOLD);
/*    */     
/* 49 */     func_240522_a_((ITag.INamedTag)ModTags.Blocks.LOGIA_BLOCK_PASS_PIKA)
/* 50 */       .func_240531_a_((ITag.INamedTag)Tags.Blocks.GLASS)
/* 51 */       .func_240531_a_((ITag.INamedTag)Tags.Blocks.GLASS_PANES);
/*    */     
/* 53 */     func_240522_a_((ITag.INamedTag)ModTags.Blocks.LOGIA_BLOCK_PASS_SUNA)
/* 54 */       .func_240531_a_((ITag.INamedTag)Tags.Blocks.SAND)
/* 55 */       .func_240531_a_((ITag.INamedTag)Tags.Blocks.SANDSTONE)
/* 56 */       .func_240534_a_((Object[])new Block[] { Blocks.field_150372_bz, Blocks.field_196640_bx, Blocks.field_222417_lF, Blocks.field_222402_hL, Blocks.field_222439_lc, Blocks.field_222452_lp });
/*    */     
/* 58 */     func_240522_a_((ITag.INamedTag)ModTags.Blocks.LOGIA_BLOCK_PASS_YUKI)
/* 59 */       .func_240534_a_((Object[])new Block[] { Blocks.field_150433_aE, Blocks.field_196604_cC, (Block)ModBlocks.HARDENED_SNOW.get() });
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\tags\ModBlockTagsDataGen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */