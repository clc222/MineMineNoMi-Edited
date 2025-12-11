/*    */ package xyz.pixelatedw.mineminenomi.world;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.function.Supplier;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.state.Property;
/*    */ import net.minecraft.state.properties.BlockStateProperties;
/*    */ import net.minecraft.tags.FluidTags;
/*    */ import net.minecraft.tags.ITag;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IWorldReader;
/*    */ import net.minecraft.world.gen.feature.template.IStructureProcessorType;
/*    */ import net.minecraft.world.gen.feature.template.PlacementSettings;
/*    */ import net.minecraft.world.gen.feature.template.StructureProcessor;
/*    */ import net.minecraft.world.gen.feature.template.Template;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModStructureProcessors;
/*    */ 
/*    */ public class ReplaceWaterloggedStructureProcessor
/*    */   extends StructureProcessor {
/* 21 */   public static final Codec<ReplaceWaterloggedStructureProcessor> CODEC = Codec.unit(ReplaceWaterloggedStructureProcessor::new);
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Template.BlockInfo func_230386_a_(IWorldReader world, BlockPos pos1, BlockPos pos2, Template.BlockInfo info1, Template.BlockInfo info2, PlacementSettings pSettings) {
/* 27 */     if (info1.field_186243_b.func_204520_s().func_206884_a((ITag)FluidTags.field_206959_a) && info2.field_186243_b.func_177230_c() instanceof net.minecraft.block.IWaterLoggable) {
/*    */       
/* 29 */       BlockState newState = (BlockState)info2.field_186243_b.func_206870_a((Property)BlockStateProperties.field_208198_y, Boolean.valueOf(false));
/* 30 */       return new Template.BlockInfo(info2.field_186242_a, newState, info2.field_186244_c);
/*    */     } 
/* 32 */     return info2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected IStructureProcessorType<?> func_215192_a() {
/* 38 */     return ModStructureProcessors.WATERLOGGED_REPLACE;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\ReplaceWaterloggedStructureProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */