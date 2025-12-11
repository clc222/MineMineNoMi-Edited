/*    */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.objects.ObjectList;
/*    */ import it.unimi.dsi.fastutil.objects.ObjectListIterator;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({ModelRenderer.class})
/*    */ public class ModelRendererMixin
/*    */ {
/*    */   @Shadow
/*    */   @Final
/*    */   private ObjectList<ModelRenderer.ModelBox> field_78804_l;
/*    */   @Shadow
/*    */   @Final
/*    */   private ObjectList<ModelRenderer> field_78805_m;
/*    */   private float scaleX;
/*    */   private float scaleY;
/*    */   private float scaleZ;
/*    */   
/*    */   @Inject(method = {"getRandomCube"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void getRandomCube(Random random, CallbackInfoReturnable<ModelRenderer.ModelBox> callback) {
/* 42 */     if (this.field_78804_l.isEmpty() && 
/* 43 */       !this.field_78805_m.isEmpty())
/* 44 */       for (ObjectListIterator<ModelRenderer> objectListIterator = this.field_78805_m.iterator(); objectListIterator.hasNext(); ) { ModelRenderer renderer = objectListIterator.next();
/* 45 */         ModelRenderer.ModelBox box = renderer.func_228310_a_(random);
/* 46 */         if (box != null) {
/* 47 */           callback.setReturnValue(box);
/*    */           return;
/*    */         }  }
/*    */        
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\client\ModelRendererMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */