/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.armor;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ public class LowerHalfArmorLayer<T extends LivingEntity, M extends BipedModel<T>, A extends BipedModel<T>>
/*    */   extends BipedArmorLayer<T, M, A>
/*    */ {
/*    */   public LowerHalfArmorLayer(IEntityRenderer<T, M> entityRenderer) {
/* 18 */     super(entityRenderer, new BipedModel(0.5F), new BipedModel(0.5F));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225628_a_(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 24 */     ItemStack itemstack = entity.func_184582_a(EquipmentSlotType.HEAD);
/* 25 */     if (itemstack.func_77973_b() instanceof xyz.pixelatedw.mineminenomi.api.IExtendedLowerHalfArmor)
/*    */     {
/* 27 */       super.func_225628_a_(matrixStack, buffer, packedLight, (LivingEntity)entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_188359_a(A model, EquipmentSlotType slot) {
/* 34 */     model.func_178719_a(false);
/* 35 */     if (slot == EquipmentSlotType.LEGS || slot == EquipmentSlotType.FEET) {
/*    */       
/* 37 */       ((BipedModel)model).field_78115_e.field_78806_j = true;
/* 38 */       ((BipedModel)model).field_178721_j.field_78806_j = true;
/* 39 */       ((BipedModel)model).field_178722_k.field_78806_j = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected A getArmorModelHook(T entity, ItemStack itemStack, EquipmentSlotType slot, A model) {
/* 46 */     return model;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\armor\LowerHalfArmorLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */