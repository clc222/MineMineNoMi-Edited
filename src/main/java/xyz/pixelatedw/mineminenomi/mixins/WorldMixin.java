/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ChallengesWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({World.class})
/*    */ public class WorldMixin
/*    */ {
/*    */   @Inject(method = {"setBlock(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;II)Z"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/Chunk;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Z)Lnet/minecraft/block/BlockState;", shift = At.Shift.AFTER)})
/*    */   public void setBlock(BlockPos pos, BlockState state, int flags, int recursionLeft, CallbackInfoReturnable<Boolean> ci) {
/* 28 */     World level = (World)this;
/* 29 */     if (!level.func_201670_d() && level instanceof ServerWorld && !state.func_196958_f() && 
/* 30 */       WyHelper.isInChallengeDimension(level)) {
/* 31 */       InProgressChallenge challenge = ChallengesWorldData.get().getInProgressChallengeFor((ServerWorld)level);
/* 32 */       if (challenge != null)
/* 33 */         challenge.trackBlockPos(pos); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\WorldMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */