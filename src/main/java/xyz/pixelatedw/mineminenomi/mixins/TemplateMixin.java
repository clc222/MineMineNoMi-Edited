/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IServerWorld;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.gen.feature.template.Template;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Redirect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({Template.class})
/*    */ public abstract class TemplateMixin
/*    */ {
/*    */   @Redirect(method = {"placeInWorld(Lnet/minecraft/world/IServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/gen/feature/template/PlacementSettings;Ljava/util/Random;I)Z"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/IServerWorld;setBlock(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"))
/*    */   public boolean setBlock(IServerWorld world, BlockPos pos, BlockState state, int flags) {
/* 23 */     if (world.func_201670_d()) {
/* 24 */       return false;
/*    */     }
/*    */     
/* 27 */     if (WyHelper.isInChallengeDimension((World)world.func_201672_e())) {
/* 28 */       WyHelper.swapBlockData((IWorld)world, pos, state);
/*    */     } else {
/* 30 */       world.func_180501_a(pos, state, flags);
/*    */     } 
/*    */     
/* 33 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\TemplateMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */