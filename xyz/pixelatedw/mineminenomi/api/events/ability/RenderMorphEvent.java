/*    */ package xyz.pixelatedw.mineminenomi.api.events.ability;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.morphs.ZoanMorphRenderer;
/*    */ 
/*    */ 
/*    */ public class RenderMorphEvent
/*    */   extends LivingEvent
/*    */ {
/*    */   private final ZoanMorphRenderer renderer;
/*    */   private final float partialRenderTick;
/*    */   private final MatrixStack stack;
/*    */   private final IRenderTypeBuffer buffers;
/*    */   private final int light;
/*    */   
/*    */   public RenderMorphEvent(LivingEntity entity, ZoanMorphRenderer renderer, float partialRenderTick, MatrixStack stack, IRenderTypeBuffer buffers, int light) {
/* 21 */     super(entity);
/* 22 */     this.renderer = renderer;
/* 23 */     this.partialRenderTick = partialRenderTick;
/* 24 */     this.stack = stack;
/* 25 */     this.buffers = buffers;
/* 26 */     this.light = light;
/*    */   }
/*    */ 
/*    */   
/*    */   public ZoanMorphRenderer getRenderer() {
/* 31 */     return this.renderer;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPartialRenderTick() {
/* 36 */     return this.partialRenderTick;
/*    */   }
/*    */ 
/*    */   
/*    */   public MatrixStack getMatrixStack() {
/* 41 */     return this.stack;
/*    */   }
/*    */ 
/*    */   
/*    */   public IRenderTypeBuffer getBuffers() {
/* 46 */     return this.buffers;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getLight() {
/* 51 */     return this.light;
/*    */   }
/*    */   
/*    */   @Cancelable
/*    */   public static class Pre
/*    */     extends RenderMorphEvent
/*    */   {
/*    */     public Pre(LivingEntity entity, ZoanMorphRenderer renderer, float tick, MatrixStack stack, IRenderTypeBuffer buffers, int light) {
/* 59 */       super(entity, renderer, tick, stack, buffers, light);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Post
/*    */     extends RenderMorphEvent
/*    */   {
/*    */     public Post(LivingEntity entity, ZoanMorphRenderer renderer, float tick, MatrixStack stack, IRenderTypeBuffer buffers, int light) {
/* 67 */       super(entity, renderer, tick, stack, buffers, light);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\ability\RenderMorphEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */