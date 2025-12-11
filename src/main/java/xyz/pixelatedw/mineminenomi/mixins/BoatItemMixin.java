/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.item.BoatEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.BoatItem;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.stats.Stats;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.math.BlockRayTraceResult;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.RayTraceContext;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.ForgeMod;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.kairosekicoating.IKairosekiCoating;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.kairosekicoating.KairosekiCoatingCapability;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({BoatItem.class})
/*    */ public class BoatItemMixin
/*    */ {
/*    */   @Inject(method = {"use"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/world/World;addFreshEntity(Lnet/minecraft/entity/Entity;)Z", shift = At.Shift.BEFORE)}, cancellable = true)
/*    */   public void use(World level, PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult<ItemStack>> cir) {
/* 34 */     BoatItem item = (BoatItem)this;
/* 35 */     ItemStack stack = player.func_184586_b(hand);
/*    */     
/* 37 */     int coatingLevel = stack.func_196082_o().func_74762_e("coatingLevel");
/* 38 */     if (coatingLevel > 0) {
/* 39 */       BlockRayTraceResult blockRayTraceResult = getPlayerPOVHitResult(level, player, RayTraceContext.FluidMode.ANY);
/*    */       
/* 41 */       BoatEntity boat = new BoatEntity(level, (blockRayTraceResult.func_216347_e()).field_72450_a, (blockRayTraceResult.func_216347_e()).field_72448_b, (blockRayTraceResult.func_216347_e()).field_72449_c);
/* 42 */       boat.func_184458_a(this.field_185057_a);
/* 43 */       boat.field_70177_z = player.field_70177_z;
/*    */       
/* 45 */       IKairosekiCoating coatingData = (IKairosekiCoating)KairosekiCoatingCapability.get((Entity)boat).orElse(null);
/*    */       
/* 47 */       if (coatingData == null) {
/* 48 */         cir.setReturnValue(ActionResult.func_226251_d_(stack));
/*    */         
/*    */         return;
/*    */       } 
/* 52 */       coatingData.setCoatingLevel(coatingLevel);
/*    */       
/* 54 */       if (!level.field_72995_K) {
/* 55 */         level.func_217376_c((Entity)boat);
/* 56 */         if (!player.field_71075_bZ.field_75098_d) {
/* 57 */           stack.func_190918_g(1);
/*    */         }
/*    */       } 
/*    */       
/* 61 */       player.func_71029_a(Stats.field_75929_E.func_199076_b(item));
/* 62 */       cir.setReturnValue(ActionResult.func_233538_a_(stack, level.func_201670_d()));
/*    */       return;
/*    */     } 
/*    */   } @Final
/*    */   @Shadow
/*    */   private BoatEntity.Type field_185057_a; private static BlockRayTraceResult getPlayerPOVHitResult(World pLevel, PlayerEntity pPlayer, RayTraceContext.FluidMode pFluidMode) {
/* 68 */     float f = pPlayer.field_70125_A;
/* 69 */     float f1 = pPlayer.field_70177_z;
/* 70 */     Vector3d vector3d = pPlayer.func_174824_e(1.0F);
/* 71 */     float f2 = MathHelper.func_76134_b(-f1 * 0.017453292F - 3.1415927F);
/* 72 */     float f3 = MathHelper.func_76126_a(-f1 * 0.017453292F - 3.1415927F);
/* 73 */     float f4 = -MathHelper.func_76134_b(-f * 0.017453292F);
/* 74 */     float f5 = MathHelper.func_76126_a(-f * 0.017453292F);
/* 75 */     float f6 = f3 * f4;
/* 76 */     float f7 = f2 * f4;
/* 77 */     double d0 = pPlayer.func_110148_a((Attribute)ForgeMod.REACH_DISTANCE.get()).func_111126_e();
/* 78 */     Vector3d vector3d1 = vector3d.func_72441_c(f6 * d0, f5 * d0, f7 * d0);
/* 79 */     return pLevel.func_217299_a(new RayTraceContext(vector3d, vector3d1, RayTraceContext.BlockMode.OUTLINE, pFluidMode, (Entity)pPlayer));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\BoatItemMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */