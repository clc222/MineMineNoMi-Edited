/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.ProtectedArea;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ProtectedAreasData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ @Mixin({Block.class})
/*    */ public class BlockMixin
/*    */ {
/*    */   @Inject(method = {"popResource"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private static void popResource(World level, BlockPos pos, ItemStack stack, CallbackInfo ci) {
/* 20 */     if (!level.field_72995_K) {
/* 21 */       if (WyHelper.isInChallengeDimension(level)) {
/* 22 */         ci.cancel();
/*    */         
/*    */         return;
/*    */       } 
/* 26 */       ProtectedArea area = ProtectedAreasData.get(level).getProtectedArea(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/* 27 */       if (area != null && area.canDestroyBlocks() && area.canRestoreBlocks()) {
/* 28 */         ci.cancel();
/*    */         return;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\BlockMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */