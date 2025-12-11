/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.util.IProgressUpdate;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ @Mixin({ServerWorld.class})
/*    */ public class ServerWorldMixin {
/*    */   @Inject(method = {"save"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void save(@Nullable IProgressUpdate pProgress, boolean pFlush, boolean pSkipSave, CallbackInfo ci) {
/* 16 */     ServerWorld world = (ServerWorld)this;
/* 17 */     if (WyHelper.isInChallengeDimension((World)world))
/* 18 */       ci.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\ServerWorldMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */