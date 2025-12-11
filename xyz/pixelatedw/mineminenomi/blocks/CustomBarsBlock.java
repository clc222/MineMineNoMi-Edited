/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ 
/*    */ import net.minecraft.block.AbstractBlock;
/*    */ import net.minecraft.block.PaneBlock;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraftforge.common.ToolType;
/*    */ 
/*    */ public class CustomBarsBlock
/*    */   extends PaneBlock {
/*    */   public CustomBarsBlock() {
/* 11 */     this(AbstractBlock.Properties.func_200945_a(Material.field_151573_f).func_200948_a(5.0F, 6.0F).harvestTool(ToolType.PICKAXE));
/*    */   }
/*    */ 
/*    */   
/*    */   public CustomBarsBlock(AbstractBlock.Properties props) {
/* 16 */     super(props);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\CustomBarsBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */