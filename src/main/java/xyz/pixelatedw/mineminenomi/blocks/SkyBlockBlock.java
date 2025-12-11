/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.block.AbstractBlock;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.item.BlockItemUseContext;
/*    */ import net.minecraft.state.IntegerProperty;
/*    */ import net.minecraft.state.Property;
/*    */ import net.minecraft.state.StateContainer;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.ToolType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.BlocksHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMaterials;
/*    */ 
/*    */ public class SkyBlockBlock extends Block {
/* 21 */   public static final IntegerProperty TYPE = IntegerProperty.func_177719_a("type", 0, 3);
/*    */ 
/*    */   
/*    */   public SkyBlockBlock() {
/* 25 */     super(AbstractBlock.Properties.func_200945_a(ModMaterials.CLOUDS).func_235838_a_(state -> 1).func_200943_b(0.7F).harvestTool(ToolType.PICKAXE).func_200947_a(SoundType.field_185854_g).func_235827_a_(BlocksHelper::never));
/* 26 */     func_180632_j((BlockState)((BlockState)this.field_176227_L.func_177621_b()).func_206870_a((Property)TYPE, Integer.valueOf(0)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public BlockState func_196258_a(BlockItemUseContext context) {
/* 33 */     return (BlockState)func_176223_P().func_206870_a((Property)TYPE, Integer.valueOf((context.func_195991_k()).field_73012_v.nextInt(4)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
/* 39 */     builder.func_206894_a(new Property[] { (Property)TYPE });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_200123_i(BlockState state, IBlockReader reader, BlockPos pos) {
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_180658_a(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
/* 51 */     entityIn.func_225503_b_(fallDistance, 0.05F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\SkyBlockBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */