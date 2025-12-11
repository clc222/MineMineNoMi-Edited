/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.chunk.IChunk;
/*    */ import net.minecraft.world.server.ChunkManager;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({ChunkManager.class})
/*    */ public class ChunkManagerMixin
/*    */ {
/*    */   @Inject(method = {"save"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void save(IChunk p_219229_1_, CallbackInfoReturnable<Boolean> cir) {
/* 22 */     if (WyHelper.isInChallengeDimension((World)this.field_219255_i))
/* 23 */       cir.setReturnValue(Boolean.valueOf(false)); 
/*    */   }
/*    */   
/*    */   @Shadow
/*    */   @Final
/*    */   private ServerWorld field_219255_i;
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\ChunkManagerMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */