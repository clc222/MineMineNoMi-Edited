/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.AbstractBlock;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.shapes.ISelectionContext;
/*    */ import net.minecraft.util.math.shapes.VoxelShape;
/*    */ import net.minecraft.util.math.shapes.VoxelShapes;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ @Mixin({AbstractBlock.AbstractBlockState.class})
/*    */ public abstract class AbstractBlockStateMixin
/*    */ {
/*    */   @Shadow
/*    */   protected abstract BlockState func_230340_p_();
/*    */   
/*    */   @Inject(method = {"randomTick"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void randomTick(ServerWorld level, BlockPos pos, Random random, CallbackInfo ci) {
/* 33 */     if (WyHelper.isInChallengeDimension((World)level)) {
/* 34 */       ci.cancel();
/*    */       return;
/*    */     } 
/*    */   }
/*    */   
/*    */   @Inject(method = {"getCollisionShape(Lnet/minecraft/world/IBlockReader;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/shapes/ISelectionContext;)Lnet/minecraft/util/math/shapes/VoxelShape;"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void getCollisionShape(IBlockReader pLevel, BlockPos pPos, ISelectionContext pContext, CallbackInfoReturnable<VoxelShape> cir) {
/* 41 */     Entity entity = pContext.getEntity();
/* 42 */     if (entity instanceof LivingEntity) {
/* 43 */       boolean flagAllowedToGoThrough = AbilityHelper.isAllowedToGoThrough((LivingEntity)entity, func_230340_p_());
/* 44 */       boolean flagCrouching = entity.func_226271_bk_();
/* 45 */       boolean flagLower = (pPos.func_177956_o() > entity.func_226278_cu_() - 1.0D);
/* 46 */       if (flagAllowedToGoThrough && (flagCrouching || flagLower))
/* 47 */         cir.setReturnValue(VoxelShapes.func_197880_a()); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\AbstractBlockStateMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */