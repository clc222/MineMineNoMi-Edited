/*    */ package xyz.pixelatedw.mineminenomi.world.features;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.fluid.FluidState;
/*    */ import net.minecraft.state.Property;
/*    */ import net.minecraft.tags.ITag;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.ISeedReader;
/*    */ import net.minecraft.world.gen.ChunkGenerator;
/*    */ import net.minecraft.world.gen.feature.Feature;
/*    */ import net.minecraft.world.gen.feature.IFeatureConfig;
/*    */ import net.minecraft.world.gen.feature.NoFeatureConfig;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.DialBlock;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyDebug;
/*    */ 
/*    */ public class NaturalDialFeature extends Feature<NoFeatureConfig> {
/* 21 */   private static final RegistryObject<Block>[] DIAL_BLOCKS = new RegistryObject[] { ModBlocks.FLAME_DIAL, ModBlocks.FLASH_DIAL, ModBlocks.AXE_DIAL, ModBlocks.EISEN_DIAL, ModBlocks.BREATH_DIAL, ModBlocks.IMPACT_DIAL, ModBlocks.MILKY_DIAL };
/*    */   
/*    */   public NaturalDialFeature() {
/* 24 */     super(NoFeatureConfig.field_236558_a_);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean place(ISeedReader world, ChunkGenerator chunkGen, Random rand, BlockPos pos, NoFeatureConfig config) {
/* 29 */     int chance = (pos.func_177956_o() > 150) ? 100 : 10;
/* 30 */     boolean isChance = (world.func_201674_k().nextDouble() * 100.0D < chance);
/* 31 */     if (!isChance) {
/* 32 */       return false;
/*    */     }
/*    */     
/* 35 */     BlockState state = world.func_180495_p(pos);
/* 36 */     FluidState fluidState = world.func_204610_c(pos);
/*    */     
/* 38 */     BlockState underState = world.func_180495_p(pos.func_177977_b());
/* 39 */     BlockState aboveState = world.func_180495_p(pos.func_177984_a());
/*    */     
/* 41 */     boolean isInWater = (fluidState.func_206884_a((ITag)FluidTags.field_206959_a) && fluidState.func_206882_g() == 8);
/* 42 */     boolean isAirOrWater = (state.func_196958_f() || isInWater);
/*    */     
/* 44 */     if (!underState.func_196958_f() && aboveState.func_196958_f() && underState.func_185904_a().func_76220_a() && isAirOrWater && (underState
/* 45 */       .func_177230_c() == Blocks.field_150354_m || underState.func_177230_c() == ModBlocks.SKY_BLOCK.get())) {
/*    */       
/* 47 */       int dialId = world.func_201674_k().nextInt(DIAL_BLOCKS.length - 1);
/* 48 */       BlockState dialState = ((Block)DIAL_BLOCKS[dialId].get()).func_176223_P();
/*    */       
/* 50 */       dialState.func_206870_a((Property)DialBlock.WATERLOGGED, Boolean.valueOf(false));
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 55 */       world.func_180501_a(pos, dialState, 2);
/* 56 */       WyDebug.debug("Dial spawned at: /tp " + pos.func_177958_n() + " " + pos.func_177956_o() + " " + pos.func_177952_p());
/* 57 */       return true;
/*    */     } 
/*    */     
/* 60 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\NaturalDialFeature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */