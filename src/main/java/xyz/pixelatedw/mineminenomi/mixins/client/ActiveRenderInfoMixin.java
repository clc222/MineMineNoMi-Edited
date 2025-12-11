/*    */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.math.BlockRayTraceResult;
/*    */ import net.minecraft.util.math.RayTraceContext;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.SetCameraOffsetEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.SetCameraZoomEvent;
/*    */ 
/*    */ 
/*    */ @Mixin({ActiveRenderInfo.class})
/*    */ public class ActiveRenderInfoMixin
/*    */ {
/*    */   @Shadow
/*    */   public IBlockReader field_216790_b;
/*    */   @Shadow
/*    */   @Final
/* 32 */   public final Vector3f field_216794_f = new Vector3f(0.0F, 0.0F, 1.0F); @Shadow
/*    */   public Entity field_216791_c; @Shadow
/*    */   public Vector3d field_216792_d;
/*    */   
/*    */   @Inject(method = {"getMaxZoom"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void getMaxZoom(double startingDistance, CallbackInfoReturnable<Double> callback) {
/* 38 */     Minecraft mc = Minecraft.func_71410_x();
/*    */     
/* 40 */     SetCameraZoomEvent event = new SetCameraZoomEvent(mc.field_71439_g, startingDistance);
/* 41 */     MinecraftForge.EVENT_BUS.post((Event)event);
/*    */     
/* 43 */     if (event.getResult() == Event.Result.ALLOW) {
/* 44 */       startingDistance = event.getZoom();
/*    */       
/* 46 */       for (int i = 0; i < 8; i++) {
/* 47 */         float f = ((i & 0x1) * 2 - 1);
/* 48 */         float f1 = ((i >> 1 & 0x1) * 2 - 1);
/* 49 */         float f2 = ((i >> 2 & 0x1) * 2 - 1);
/* 50 */         f *= 0.1F;
/* 51 */         f1 *= 0.1F;
/* 52 */         f2 *= 0.1F;
/* 53 */         Vector3d vector3d = this.field_216792_d.func_72441_c(f, f1, f2);
/* 54 */         Vector3d vector3d1 = new Vector3d(this.field_216792_d.field_72450_a - this.field_216794_f.func_195899_a() * startingDistance + f + f2, this.field_216792_d.field_72448_b - this.field_216794_f.func_195900_b() * startingDistance + f1, this.field_216792_d.field_72449_c - this.field_216794_f.func_195902_c() * startingDistance + f2);
/* 55 */         BlockRayTraceResult blockRayTraceResult = this.field_216790_b.func_217299_a(new RayTraceContext(vector3d, vector3d1, RayTraceContext.BlockMode.VISUAL, RayTraceContext.FluidMode.NONE, this.field_216791_c));
/* 56 */         if (blockRayTraceResult.func_216346_c() != RayTraceResult.Type.MISS) {
/* 57 */           double distance = blockRayTraceResult.func_216347_e().func_72438_d(this.field_216792_d);
/* 58 */           if (distance < startingDistance) {
/* 59 */             startingDistance = distance;
/*    */           }
/*    */         } 
/*    */       } 
/*    */       
/* 64 */       callback.setReturnValue(Double.valueOf(startingDistance));
/*    */     } 
/*    */   }
/*    */   
/*    */   @Inject(method = {"setPosition"}, at = {@At("TAIL")})
/*    */   public void setPosition(double x, double y, double z, CallbackInfo callback) {
/* 70 */     Minecraft mc = Minecraft.func_71410_x();
/*    */     
/* 72 */     SetCameraOffsetEvent event = new SetCameraOffsetEvent(mc.field_71439_g, new Vector3d(x, y, z));
/* 73 */     MinecraftForge.EVENT_BUS.post((Event)event);
/*    */     
/* 75 */     if (event.getResult() == Event.Result.ALLOW)
/* 76 */       this.field_216792_d = event.getCameraPosition(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\client\ActiveRenderInfoMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */