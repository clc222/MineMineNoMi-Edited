/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.AgeableModel;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Cancelable
/*    */ public class AgeableRendererEvent<E extends Entity>
/*    */   extends Event
/*    */ {
/*    */   private E entity;
/*    */   private AgeableModel<E> model;
/*    */   private ModelRenderer renderer;
/*    */   private MatrixStack matrixStack;
/*    */   private IVertexBuilder builder;
/*    */   private int packedLight;
/*    */   private int packedOverlay;
/*    */   private float red;
/*    */   private float green;
/*    */   private float blue;
/*    */   private float alpha;
/*    */   
/*    */   public AgeableRendererEvent(E entity, AgeableModel<E> model, ModelRenderer renderer, MatrixStack matrixStack, IVertexBuilder builder, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 33 */     this.entity = entity;
/*    */     
/* 35 */     this.model = model;
/*    */     
/* 37 */     this.renderer = renderer;
/*    */     
/* 39 */     this.matrixStack = matrixStack;
/*    */     
/* 41 */     this.builder = builder;
/*    */     
/* 43 */     this.packedLight = packedLight;
/* 44 */     this.packedOverlay = packedOverlay;
/*    */     
/* 46 */     this.red = red;
/* 47 */     this.green = green;
/* 48 */     this.blue = blue;
/* 49 */     this.alpha = alpha;
/*    */   }
/*    */   
/*    */   public E getEntity() {
/* 53 */     return this.entity;
/*    */   }
/*    */   
/*    */   public AgeableModel<E> getModel() {
/* 57 */     return this.model;
/*    */   }
/*    */   
/*    */   public ModelRenderer getRenderer() {
/* 61 */     return this.renderer;
/*    */   }
/*    */   
/*    */   public MatrixStack getMatrixStack() {
/* 65 */     return this.matrixStack;
/*    */   }
/*    */   
/*    */   public IVertexBuilder getBuilder() {
/* 69 */     return this.builder;
/*    */   }
/*    */   
/*    */   public int getPackedLight() {
/* 73 */     return this.packedLight;
/*    */   }
/*    */   
/*    */   public int getPackedOverlay() {
/* 77 */     return this.packedOverlay;
/*    */   }
/*    */   
/*    */   public float getRed() {
/* 81 */     return this.red;
/*    */   }
/*    */   
/*    */   public float getGreen() {
/* 85 */     return this.green;
/*    */   }
/*    */   
/*    */   public float getBlue() {
/* 89 */     return this.blue;
/*    */   }
/*    */   
/*    */   public float getAlpha() {
/* 93 */     return this.alpha;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\AgeableRendererEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */