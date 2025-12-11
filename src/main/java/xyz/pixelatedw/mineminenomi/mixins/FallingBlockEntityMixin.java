/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import net.minecraft.entity.item.FallingBlockEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraftforge.common.util.BlockSnapshot;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.ProtectedArea;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ProtectedAreasData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.BlockPlacingHelper;
/*    */ 
/*    */ @Mixin({FallingBlockEntity.class})
/*    */ public class FallingBlockEntityMixin {
/*    */   @Inject(method = {"tick"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlock(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z", shift = At.Shift.BEFORE)})
/*    */   public void beforeLanding(CallbackInfo ci) {
/* 19 */     FallingBlockEntity entity = (FallingBlockEntity)this;
/* 20 */     if (!entity.field_70170_p.field_72995_K) {
/* 21 */       BlockPos pos = entity.func_233580_cy_();
/*    */ 
/*    */       
/* 24 */       ProtectedArea area = ProtectedAreasData.get(entity.field_70170_p).getProtectedArea(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/* 25 */       if (area != null) {
/* 26 */         BlockPlacingHelper.DistanceBlockPos pos2 = new BlockPlacingHelper.DistanceBlockPos(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/* 27 */         BlockSnapshot snapshot = BlockSnapshot.create(entity.field_70170_p.func_234923_W_(), (IWorld)entity.field_70170_p, pos, 2);
/*    */         
/* 29 */         area.queueForRestoration(pos2, new ProtectedArea.RestorationEntry(entity.field_70170_p.func_82737_E(), snapshot));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\FallingBlockEntityMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */