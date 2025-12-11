/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.armor;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.ItemRenderer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.mixins.client.IBipedArmorLayerMixin;
/*    */ 
/*    */ 
/*    */ public class UpperHalfArmorLayer<T extends LivingEntity, M extends BipedModel<T>, A extends BipedModel<T>>
/*    */   extends BipedArmorLayer<T, M, A>
/*    */ {
/*    */   public UpperHalfArmorLayer(IEntityRenderer<T, M> entityRenderer) {
/* 24 */     super(entityRenderer, new BipedModel(0.5F), new BipedModel(0.5F));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225628_a_(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 30 */     if (!(func_215332_c() instanceof BipedModel)) {
/*    */       return;
/*    */     }
/* 33 */     ItemStack itemstack = entity.func_184582_a(EquipmentSlotType.HEAD);
/* 34 */     if (itemstack.func_77973_b() instanceof xyz.pixelatedw.mineminenomi.api.IExtendedUpperHalfArmor) {
/*    */ 
/*    */       
/* 37 */       A a = getArmorModelHook(entity, itemstack, EquipmentSlotType.HEAD, (A)((IBipedArmorLayerMixin)this).getBodyModel());
/* 38 */       ((BipedModel)func_215332_c()).func_217148_a((BipedModel)a);
/* 39 */       a.func_212843_a_((LivingEntity)entity, limbSwing, limbSwingAmount, partialTicks);
/* 40 */       func_188359_a(a, EquipmentSlotType.HEAD);
/* 41 */       a.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 42 */       ResourceLocation armorResource = getArmorResource((Entity)entity, itemstack, EquipmentSlotType.HEAD, null);
/* 43 */       boolean hasGlint = itemstack.func_77962_s();
/* 44 */       IVertexBuilder ivertexbuilder = ItemRenderer.func_239386_a_(buffer, RenderType.func_239263_a_(armorResource), false, hasGlint);
/* 45 */       a.func_225598_a_(matrixStack, ivertexbuilder, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_188359_a(A model, EquipmentSlotType slot) {
/* 52 */     model.func_178719_a(false);
/* 53 */     if (slot == EquipmentSlotType.HEAD || slot == EquipmentSlotType.CHEST) {
/*    */       
/* 55 */       ((BipedModel)model).field_78116_c.field_78806_j = true;
/* 56 */       ((BipedModel)model).field_178720_f.field_78806_j = true;
/* 57 */       ((BipedModel)model).field_78115_e.field_78806_j = true;
/* 58 */       ((BipedModel)model).field_178723_h.field_78806_j = true;
/* 59 */       ((BipedModel)model).field_178724_i.field_78806_j = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected A getArmorModelHook(T entity, ItemStack itemStack, EquipmentSlotType slot, A model) {
/* 66 */     return model;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\armor\UpperHalfArmorLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */