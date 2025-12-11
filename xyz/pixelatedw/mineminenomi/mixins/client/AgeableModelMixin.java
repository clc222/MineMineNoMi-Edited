/*     */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.entity.model.AgeableModel;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import org.spongepowered.asm.mixin.Final;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.Shadow;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.Inject;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.AgeableRendererEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Mixin({AgeableModel.class})
/*     */ public abstract class AgeableModelMixin<E extends Entity>
/*     */   extends EntityModel<E>
/*     */ {
/*     */   @Shadow
/*     */   @Final
/*     */   protected boolean field_228221_a_;
/*     */   @Shadow
/*     */   @Final
/*     */   protected float field_228222_b_;
/*     */   @Shadow
/*     */   @Final
/*     */   protected float field_228223_f_;
/*     */   @Shadow
/*     */   @Final
/*     */   protected float field_228224_g_;
/*     */   @Shadow
/*     */   @Final
/*     */   protected float field_228225_h_;
/*     */   @Shadow
/*     */   @Final
/*     */   protected float field_228226_i_;
/*     */   private E entity;
/*     */   
/*     */   public void func_212843_a_(E pEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick) {
/*  56 */     this.entity = pEntity;
/*     */   }
/*     */   
/*     */   @Inject(method = {"renderToBuffer(Lcom/mojang/blaze3d/matrix/MatrixStack;Lcom/mojang/blaze3d/vertex/IVertexBuilder;IIFFFF)V"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder builder, int packedLight, int packedOverlay, float red, float green, float blue, float alpha, CallbackInfo ci) {
/*  61 */     AgeableModel<E> model = (AgeableModel<E>)this;
/*     */     
/*  63 */     if (model.field_217114_e) {
/*  64 */       matrixStack.func_227860_a_();
/*     */       
/*  66 */       if (this.field_228221_a_) {
/*  67 */         float f = 1.5F / this.field_228224_g_;
/*     */         
/*  69 */         matrixStack.func_227862_a_(f, f, f);
/*     */       } 
/*     */       
/*  72 */       matrixStack.func_227861_a_(0.0D, (this.field_228222_b_ / 16.0F), (this.field_228223_f_ / 16.0F));
/*     */       
/*  74 */       func_225602_a_().forEach(part -> {
/*     */             matrixStack.func_227860_a_();
/*     */             
/*     */             AgeableRendererEvent<E> event = new AgeableRendererEvent((Entity)this.entity, model, part, matrixStack, builder, packedLight, packedOverlay, red, green, blue, alpha);
/*     */             
/*     */             MinecraftForge.EVENT_BUS.post((Event)event);
/*     */             
/*     */             if (!event.isCanceled()) {
/*     */               part.func_228309_a_(matrixStack, builder, packedLight, packedOverlay, red, green, blue, alpha);
/*     */             }
/*     */             
/*     */             matrixStack.func_227865_b_();
/*     */           });
/*     */       
/*  88 */       matrixStack.func_227865_b_();
/*  89 */       matrixStack.func_227860_a_();
/*     */       
/*  91 */       float f1 = 1.0F / this.field_228225_h_;
/*     */       
/*  93 */       matrixStack.func_227862_a_(f1, f1, f1);
/*  94 */       matrixStack.func_227861_a_(0.0D, (this.field_228226_i_ / 16.0F), 0.0D);
/*     */       
/*  96 */       func_225600_b_().forEach(part -> {
/*     */             matrixStack.func_227860_a_();
/*     */             
/*     */             AgeableRendererEvent<E> event = new AgeableRendererEvent((Entity)this.entity, model, part, matrixStack, builder, packedLight, packedOverlay, red, green, blue, alpha);
/*     */             
/*     */             MinecraftForge.EVENT_BUS.post((Event)event);
/*     */             
/*     */             if (!event.isCanceled()) {
/*     */               part.func_228309_a_(matrixStack, builder, packedLight, packedOverlay, red, green, blue, alpha);
/*     */             }
/*     */             
/*     */             matrixStack.func_227865_b_();
/*     */           });
/*     */       
/* 110 */       matrixStack.func_227865_b_();
/*     */     } else {
/* 112 */       func_225602_a_().forEach(part -> {
/*     */             matrixStack.func_227860_a_();
/*     */             
/*     */             AgeableRendererEvent<E> event = new AgeableRendererEvent((Entity)this.entity, model, part, matrixStack, builder, packedLight, packedOverlay, red, green, blue, alpha);
/*     */             
/*     */             MinecraftForge.EVENT_BUS.post((Event)event);
/*     */             
/*     */             if (!event.isCanceled()) {
/*     */               part.func_228309_a_(matrixStack, builder, packedLight, packedOverlay, red, green, blue, alpha);
/*     */             }
/*     */             
/*     */             matrixStack.func_227865_b_();
/*     */           });
/*     */       
/* 126 */       func_225600_b_().forEach(part -> {
/*     */             matrixStack.func_227860_a_();
/*     */             
/*     */             AgeableRendererEvent<E> event = new AgeableRendererEvent((Entity)this.entity, model, part, matrixStack, builder, packedLight, packedOverlay, red, green, blue, alpha);
/*     */             
/*     */             MinecraftForge.EVENT_BUS.post((Event)event);
/*     */             
/*     */             if (!event.isCanceled()) {
/*     */               part.func_228309_a_(matrixStack, builder, packedLight, packedOverlay, red, green, blue, alpha);
/*     */             }
/*     */             
/*     */             matrixStack.func_227865_b_();
/*     */           });
/*     */     } 
/*     */     
/* 141 */     ci.cancel();
/*     */   }
/*     */   
/*     */   @Shadow
/*     */   protected abstract Iterable<ModelRenderer> func_225602_a_();
/*     */   
/*     */   @Shadow
/*     */   protected abstract Iterable<ModelRenderer> func_225600_b_();
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\client\AgeableModelMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */