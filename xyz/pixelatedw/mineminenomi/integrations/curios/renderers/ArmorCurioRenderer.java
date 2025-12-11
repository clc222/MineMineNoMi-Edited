/*    */ package xyz.pixelatedw.mineminenomi.integrations.curios.renderers;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.ItemRenderer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.IDyeableArmorItem;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import top.theillusivec4.curios.api.type.capability.ICurio;
/*    */ 
/*    */ public class ArmorCurioRenderer
/*    */   implements ICurioRenderer
/*    */ {
/*    */   public void render(String identifier, int index, MatrixStack matrixStack, IRenderTypeBuffer buffer, int light, LivingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, ItemStack stack) {
/* 24 */     EquipmentSlotType slot = MobEntity.func_184640_d(stack);
/* 25 */     BipedModel<LivingEntity> model = stack.func_77973_b().getArmorModel(entity, stack, slot, null);
/* 26 */     model.func_225597_a_(entity, limbSwing, limbSwingAmount, partialTicks, netHeadYaw, headPitch);
/* 27 */     model.func_212843_a_(entity, limbSwing, limbSwingAmount, partialTicks);
/* 28 */     setPartVisibility(model, slot);
/* 29 */     ICurio.RenderHelper.followBodyRotations(entity, new BipedModel[] { model });
/*    */     
/* 31 */     if (stack.func_77973_b() instanceof IDyeableArmorItem) {
/* 32 */       int color = ((IDyeableArmorItem)stack.func_77973_b()).func_200886_f(stack);
/* 33 */       float red = (color >> 16 & 0xFF) / 255.0F;
/* 34 */       float green = (color >> 8 & 0xFF) / 255.0F;
/* 35 */       float blue = (color & 0xFF) / 255.0F;
/* 36 */       renderLayer(stack, model, matrixStack, buffer, entity, red, green, blue, light, null);
/* 37 */       renderLayer(stack, model, matrixStack, buffer, entity, 1.0F, 1.0F, 1.0F, light, "overlay");
/*    */     } else {
/*    */       
/* 40 */       renderLayer(stack, model, matrixStack, buffer, entity, 1.0F, 1.0F, 1.0F, light, null);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void renderLayer(ItemStack stack, BipedModel<LivingEntity> model, MatrixStack matrixStack, IRenderTypeBuffer buffer, LivingEntity entity, float red, float green, float blue, int light, @Nullable String layer) {
/* 45 */     boolean hasGlint = stack.func_77962_s();
/* 46 */     ResourceLocation texture = new ResourceLocation(stack.func_77973_b().getArmorTexture(stack, (Entity)entity, stack.getEquipmentSlot(), layer));
/* 47 */     RenderType renderType = model.func_228282_a_(texture);
/* 48 */     IVertexBuilder vertexBuilder = ItemRenderer.func_239386_a_(buffer, renderType, false, hasGlint);
/* 49 */     model.func_225598_a_(matrixStack, vertexBuilder, light, OverlayTexture.field_229196_a_, red, green, blue, 1.0F);
/*    */   }
/*    */   
/*    */   protected void setPartVisibility(BipedModel<LivingEntity> model, EquipmentSlotType pSlot) {
/* 53 */     model.func_178719_a(false);
/* 54 */     switch (pSlot) {
/*    */       case HEAD:
/* 56 */         model.field_78116_c.field_78806_j = true;
/* 57 */         model.field_178720_f.field_78806_j = true;
/*    */         break;
/*    */       case CHEST:
/* 60 */         model.field_78115_e.field_78806_j = true;
/* 61 */         model.field_178723_h.field_78806_j = true;
/* 62 */         model.field_178724_i.field_78806_j = true;
/*    */         break;
/*    */       case LEGS:
/* 65 */         model.field_78115_e.field_78806_j = true;
/* 66 */         model.field_178721_j.field_78806_j = true;
/* 67 */         model.field_178722_k.field_78806_j = true;
/*    */         break;
/*    */       case FEET:
/* 70 */         model.field_178721_j.field_78806_j = true;
/* 71 */         model.field_178722_k.field_78806_j = true;
/*    */         break;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\integrations\curios\renderers\ArmorCurioRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */