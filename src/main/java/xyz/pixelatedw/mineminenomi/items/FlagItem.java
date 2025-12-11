/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.BlockItem;
/*    */ import net.minecraft.item.BlockItemUseContext;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.CanvasSize;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.FlagTileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class FlagItem extends BlockItem {
/*    */   public FlagItem(Block block) {
/* 25 */     super(block, (new Item.Properties()).func_200917_a(1).func_200916_a(ModCreativeTabs.MISC));
/*    */   }
/*    */ 
/*    */   
/*    */   protected BlockState func_195945_b(BlockItemUseContext ctx) {
/* 30 */     PlayerEntity player = ctx.func_195999_j();
/* 31 */     ItemStack itemStack = ctx.func_195996_i();
/*    */     
/* 33 */     if (!player.field_70170_p.field_72995_K) {
/* 34 */       itemStack.func_196082_o().func_186854_a("uuid", player.func_110124_au());
/* 35 */       ExtendedWorldData worldData = ExtendedWorldData.get();
/*    */       
/* 37 */       Crew crew = worldData.getCrewWithMember(player.func_110124_au());
/* 38 */       if (crew != null) {
/* 39 */         itemStack.func_196082_o().func_218657_a("crew", (INBT)crew.write());
/*    */       }
/*    */     } 
/*    */     
/* 43 */     return super.func_195945_b(ctx);
/*    */   }
/*    */   public static final String CANVAS_SIZE_NBT = "canvasSize";
/*    */   
/*    */   protected boolean func_195943_a(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack itemStack, BlockState state) {
/* 48 */     if (player != null) {
/* 49 */       FlagTileEntity tileEntity = (FlagTileEntity)world.func_175625_s(pos);
/*    */       
/* 51 */       if (tileEntity != null && itemStack.func_77942_o()) {
/* 52 */         Crew crew = Crew.from(itemStack.func_77978_p().func_74775_l("crew"));
/* 53 */         if (crew != null) {
/* 54 */           tileEntity.setCrew(crew);
/*    */         }
/*    */         
/* 57 */         tileEntity.func_70296_d();
/*    */         
/* 59 */         return true;
/*    */       } 
/*    */     } 
/*    */     
/* 63 */     boolean flag = super.func_195943_a(pos, world, player, itemStack, state);
/* 64 */     return flag;
/*    */   }
/*    */   
/*    */   public static void setCanvasSize(ItemStack stack, CanvasSize size) {
/* 68 */     stack.func_196082_o().func_74768_a("canvasSize", size.ordinal());
/* 69 */     stack.func_200302_a((ITextComponent)new StringTextComponent(WyHelper.capitalize(size.func_176610_l()) + " " + stack.func_77973_b().func_200295_i(stack).getString()));
/*    */   }
/*    */   
/*    */   public static boolean upgradeCanvasSize(ItemStack stack) {
/* 73 */     CanvasSize currentSize = getCanvasSize(stack);
/*    */     
/* 75 */     if (currentSize.isMaximumSize()) {
/* 76 */       return false;
/*    */     }
/*    */     
/* 79 */     CanvasSize upgradeSize = CanvasSize.values()[currentSize.ordinal() + 1];
/* 80 */     setCanvasSize(stack, upgradeSize);
/* 81 */     return true;
/*    */   }
/*    */   
/*    */   public static CanvasSize getCanvasSize(ItemStack stack) {
/* 85 */     if (!stack.func_77942_o()) {
/* 86 */       return CanvasSize.SMALL;
/*    */     }
/*    */     
/* 89 */     CanvasSize size = CanvasSize.values()[stack.func_196082_o().func_74762_e("canvasSize")];
/* 90 */     return size;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\FlagItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */