/*     */ package xyz.pixelatedw.mineminenomi.items;
/*     */ 
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.client.util.ITooltipFlag;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.BlockItemUseContext;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.WallOrFloorItem;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.WantedPosterData;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.CanvasSize;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterTileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenWantedPosterScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class WantedPosterItem
/*     */   extends WallOrFloorItem {
/*     */   public WantedPosterItem(Block block) {
/*  32 */     super(block, (Block)ModBlocks.WANTED_POSTER.get(), (new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200917_a(1));
/*     */   }
/*     */   public static final String CANVAS_SIZE_NBT = "canvasSize";
/*     */   
/*     */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/*  37 */     ItemStack itemstack = player.func_184586_b(hand);
/*     */     
/*  39 */     if (!player.field_70170_p.field_72995_K && 
/*  40 */       itemstack.func_77942_o()) {
/*  41 */       WantedPosterData wantedPosterData = WantedPosterData.from(itemstack.func_77978_p().func_74775_l("WPData"));
/*  42 */       wantedPosterData.checkIfExpired();
/*  43 */       WyNetwork.sendTo(new SOpenWantedPosterScreenPacket(wantedPosterData), player);
/*     */     } 
/*     */ 
/*     */     
/*  47 */     return ActionResult.func_226248_a_(itemstack);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState func_195945_b(BlockItemUseContext ctx) {
/*  52 */     PlayerEntity player = ctx.func_195999_j();
/*  53 */     ItemStack itemstack = ctx.func_195996_i();
/*     */     
/*  55 */     if (!player.field_70170_p.field_72995_K);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  66 */     return super.func_195945_b(ctx);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_195943_a(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack itemStack, BlockState state) {
/*  71 */     if (player != null) {
/*  72 */       WantedPosterTileEntity tileEntity = (WantedPosterTileEntity)world.func_175625_s(pos);
/*     */       
/*  74 */       if (tileEntity != null && itemStack.func_77942_o()) {
/*  75 */         WantedPosterData wantedPosterData = WantedPosterData.from(itemStack.func_77978_p().func_74775_l("WPData"));
/*  76 */         wantedPosterData.checkIfExpired();
/*  77 */         tileEntity.setWantedPosterData(wantedPosterData);
/*  78 */         tileEntity.func_70296_d();
/*     */       } 
/*     */     } 
/*  81 */     boolean flag = super.func_195943_a(pos, world, player, itemStack, state);
/*  82 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_77624_a(ItemStack itemStack, @Nullable World world, List<ITextComponent> list, ITooltipFlag flag) {
/*  87 */     if (itemStack.func_77942_o() && itemStack.func_77978_p().func_74764_b("WPData")) {
/*  88 */       WantedPosterData wantedPosterData = WantedPosterData.from(itemStack.func_77978_p().func_74775_l("WPData"));
/*  89 */       if (wantedPosterData.getTrackedPosition().isPresent()) {
/*  90 */         Vector3d pos = wantedPosterData.getTrackedPosition().get();
/*  91 */         list.add(StringTextComponent.field_240750_d_);
/*  92 */         list.add(new StringTextComponent("x: " + (int)pos.field_72450_a + " y: " + (int)pos.field_72448_b + " z: " + (int)pos.field_72449_c));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setCanvasSize(ItemStack stack, CanvasSize size) {
/*  98 */     stack.func_196082_o().func_74768_a("canvasSize", size.ordinal());
/*  99 */     stack.func_200302_a((ITextComponent)new StringTextComponent(WyHelper.capitalize(size.func_176610_l()) + " " + stack.func_77973_b().func_200295_i(stack).getString()));
/*     */   }
/*     */   
/*     */   public static boolean upgradeCanvasSize(ItemStack stack) {
/* 103 */     CanvasSize currentSize = getCanvasSize(stack);
/*     */     
/* 105 */     if (currentSize.isMaximumSize()) {
/* 106 */       return false;
/*     */     }
/*     */     
/* 109 */     CanvasSize upgradeSize = CanvasSize.values()[currentSize.ordinal() + 1];
/* 110 */     setCanvasSize(stack, upgradeSize);
/* 111 */     return true;
/*     */   }
/*     */   
/*     */   public static CanvasSize getCanvasSize(ItemStack stack) {
/* 115 */     if (!stack.func_77942_o()) {
/* 116 */       return CanvasSize.SMALL;
/*     */     }
/*     */     
/* 119 */     CanvasSize size = CanvasSize.values()[stack.func_196082_o().func_74762_e("canvasSize")];
/* 120 */     return size;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\WantedPosterItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */