/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.armor;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.items.armors.CaptainCapeItem;
/*    */ import xyz.pixelatedw.mineminenomi.mixins.client.IBipedArmorLayerMixin;
/*    */ import xyz.pixelatedw.mineminenomi.models.armors.CaptainCapeModel;
/*    */ 
/*    */ public class CaptainCapeOverlayLayer<T extends LivingEntity, M extends CaptainCapeModel<T>, A extends CaptainCapeModel<T>> extends BipedArmorLayer<T, M, A> {
/*    */   public CaptainCapeOverlayLayer(IEntityRenderer<T, M> entityRenderer) {
/* 26 */     super(entityRenderer, null, (BipedModel)new CaptainCapeModel());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225628_a_(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 32 */     Optional<ItemStack> itemstack = ItemsHelper.findItemInSlot((LivingEntity)entity, EquipmentSlotType.CHEST, (Item)ModArmors.PIRATE_CAPTAIN_CAPE.get());
/* 33 */     BipedModel capeModel = ((IBipedArmorLayerMixin)this).getBodyModel();
/* 34 */     if (!itemstack.isPresent() || ((ItemStack)itemstack.get()).func_77973_b() != ModArmors.PIRATE_CAPTAIN_CAPE.get() || !(capeModel instanceof CaptainCapeModel)) {
/*    */       return;
/*    */     }
/* 37 */     ((CaptainCapeModel)capeModel).cape.field_78806_j = false;
/*    */ 
/*    */ 
/*    */     
/* 41 */     Crew crew = CaptainCapeItem.getCapeCrew(itemstack.get());
/* 42 */     if (crew != null) {
/*    */       
/* 44 */       matrixStack.func_227860_a_();
/*    */       
/* 46 */       double dist = entity.func_70092_e(((LivingEntity)entity).field_70169_q, ((LivingEntity)entity).field_70167_r, ((LivingEntity)entity).field_70166_s);
/* 47 */       if (dist > 0.0D && dist <= 0.02D)
/* 48 */         dist += 0.02D; 
/* 49 */       double angle = MathHelper.func_151237_a(dist * 1000.0D - 1.0D, 0.0D, 70.0D);
/* 50 */       boolean isMoving = (dist > 0.02D);
/* 51 */       if (isMoving) {
/* 52 */         angle += (MathHelper.func_76126_a((float)MathHelper.func_219803_d(angle, ((LivingEntity)entity).field_70141_P, ((LivingEntity)entity).field_70140_Q)) * 6.0F);
/*    */       }
/* 54 */       if (angle <= 0.0D) {
/* 55 */         matrixStack.func_227861_a_(0.5D, 0.1D, 0.18D);
/* 56 */       } else if (angle > 0.0D && angle < 66.0D) {
/* 57 */         matrixStack.func_227861_a_(0.5D, 0.01D, 0.265D);
/* 58 */       } else if (angle >= 66.0D) {
/* 59 */         matrixStack.func_227861_a_(0.5D, -0.01D, 0.27D);
/*    */       } 
/* 61 */       if (entity.func_213453_ef() && entity.func_233570_aj_()) {
/*    */         
/* 63 */         angle += 29.0D;
/* 64 */         matrixStack.func_227861_a_(0.0D, 0.1D, 0.011D);
/* 65 */         if (angle > 35.0D) {
/*    */           
/* 67 */           angle--;
/* 68 */           matrixStack.func_227861_a_(0.0D, 0.05D, -0.015D);
/*    */         } 
/*    */       } 
/*    */       
/* 72 */       matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_((float)angle + 0.5F));
/* 73 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F));
/*    */       
/* 75 */       RendererHelper.drawPlayerJollyRoger(crew.getJollyRoger(), matrixStack, buffer, packedLight);
/* 76 */       matrixStack.func_227865_b_();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void setPartVisibility(A model, EquipmentSlotType slot) {
/* 83 */     model.func_178719_a(false);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\armor\CaptainCapeOverlayLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */