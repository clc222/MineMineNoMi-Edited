/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ 
/*    */ 
/*    */ public class RenderOverlayEvent
/*    */   extends Event
/*    */ {
/*    */   private PlayerEntity player;
/*    */   private MatrixStack matrixStack;
/*    */   private ActiveRenderInfo activeRenderInfo;
/*    */   private float partialTicks;
/*    */   
/*    */   public RenderOverlayEvent(PlayerEntity player, MatrixStack matrixStack, ActiveRenderInfo activeRenderInfo, float partialTicks) {
/* 18 */     this.player = player;
/* 19 */     this.matrixStack = matrixStack;
/* 20 */     this.activeRenderInfo = activeRenderInfo;
/* 21 */     this.partialTicks = partialTicks;
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerEntity getPlayer() {
/* 26 */     return this.player;
/*    */   }
/*    */ 
/*    */   
/*    */   public MatrixStack getMatrixStack() {
/* 31 */     return this.matrixStack;
/*    */   }
/*    */ 
/*    */   
/*    */   public ActiveRenderInfo getActiveRenderInfo() {
/* 36 */     return this.activeRenderInfo;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPartialTicks() {
/* 41 */     return this.partialTicks;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\RenderOverlayEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */