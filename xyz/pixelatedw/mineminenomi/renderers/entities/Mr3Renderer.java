/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.Mr3Entity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel;
/*    */ 
/*    */ public class Mr3Renderer extends HumanoidRenderer<Mr3Entity, HumanoidModel<Mr3Entity>> {
/*    */   public Mr3Renderer(EntityRendererManager manager) {
/* 13 */     super(manager, new HumanoidModel(), "mr3");
/* 14 */     func_177094_a((LayerRenderer)new Mr3HairLayer((IEntityRenderer)this));
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Mr3Entity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 19 */     super.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\Mr3Renderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */