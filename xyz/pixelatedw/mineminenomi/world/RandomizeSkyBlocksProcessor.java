/*    */ package xyz.pixelatedw.mineminenomi.world;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.function.Supplier;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.state.Property;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IWorldReader;
/*    */ import net.minecraft.world.gen.feature.template.IStructureProcessorType;
/*    */ import net.minecraft.world.gen.feature.template.PlacementSettings;
/*    */ import net.minecraft.world.gen.feature.template.StructureProcessor;
/*    */ import net.minecraft.world.gen.feature.template.Template;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.SkyBlockBlock;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModStructureProcessors;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class RandomizeSkyBlocksProcessor
/*    */   extends StructureProcessor {
/* 22 */   public static final Codec<RandomizeSkyBlocksProcessor> CODEC = Codec.unit(RandomizeSkyBlocksProcessor::new);
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Template.BlockInfo func_230386_a_(IWorldReader world, BlockPos pos1, BlockPos pos2, Template.BlockInfo info1, Template.BlockInfo info2, PlacementSettings pSettings) {
/* 28 */     if (info1.field_186243_b.func_177230_c() == ModBlocks.SKY_BLOCK.get()) {
/*    */       
/* 30 */       BlockState newState = (BlockState)info1.field_186243_b.func_206870_a((Property)SkyBlockBlock.TYPE, Integer.valueOf((int)WyHelper.randomWithRange(0, 3)));
/* 31 */       return new Template.BlockInfo(info2.field_186242_a, newState, new CompoundNBT());
/*    */     } 
/* 33 */     return info2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected IStructureProcessorType<?> func_215192_a() {
/* 39 */     return ModStructureProcessors.SKYBLOCK_RANDOMIZER;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\RandomizeSkyBlocksProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */