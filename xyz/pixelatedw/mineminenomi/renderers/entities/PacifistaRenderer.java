/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.text.DecimalFormat;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Matrix4f;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.marines.PacifistaEntity;
/*    */ 
/*    */ public class PacifistaRenderer<T extends PacifistaEntity, M extends BipedModel<T>>
/*    */   extends HumanoidRenderer<T, M> {
/* 25 */   private static final ResourceLocation CHARGING_TEXTURE = new ResourceLocation("mineminenomi", "textures/models/pacifista_charging.png");
/*    */ 
/*    */   
/*    */   public PacifistaRenderer(EntityRendererManager manager, M model, float scale, String texture) {
/* 29 */     super(manager, model, new float[] { scale, scale, scale }, texture);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 35 */     matrixStack.func_227860_a_();
/*    */     
/* 37 */     float scale = -0.004F;
/* 38 */     float y = entity.func_213302_cg() - 0.43F;
/* 39 */     float xz = entity.func_213311_cf() / 2.0F;
/* 40 */     matrixStack.func_227863_a_(Vector3f.field_229180_c_.func_229187_a_(MathHelper.func_219805_h(partialTicks, ((PacifistaEntity)entity).field_70758_at, ((PacifistaEntity)entity).field_70759_as)));
/* 41 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(90.0F));
/* 42 */     matrixStack.func_227861_a_(xz - 1.08D, y, xz - 1.2D);
/* 43 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(((PacifistaEntity)entity).field_70125_A));
/* 44 */     matrixStack.func_227862_a_(scale, scale, 0.004F);
/*    */     
/* 46 */     boolean flag = false;
/* 47 */     int i = 0;
/* 48 */     int pxid = Math.abs(entity.func_110124_au().hashCode() / 1000000);
/* 49 */     DecimalFormat decimalFormat = new DecimalFormat("0000");
/* 50 */     String name = "PX-" + decimalFormat.format(pxid);
/* 51 */     Matrix4f matrix4f = matrixStack.func_227866_c_().func_227870_a_();
/* 52 */     FontRenderer fontrenderer = func_76983_a();
/* 53 */     float nameSize = (-fontrenderer.func_78256_a(name) / 2);
/* 54 */     float opacity = (Minecraft.func_71410_x()).field_71474_y.func_216840_a(0.0F);
/* 55 */     int j = (int)(opacity * 255.0F) << 24;
/* 56 */     if (entity.func_70089_S()) {
/* 57 */       fontrenderer.func_228079_a_(name, nameSize, i, 0, false, matrix4f, buffer, flag, j, packedLight);
/*    */     }
/*    */     
/* 60 */     matrixStack.func_227865_b_();
/*    */     
/* 62 */     super.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTextureLocation(T entity) {
/* 68 */     AnimationComponent comp = AbilityHelper.getActiveAnimation((LivingEntity)entity);
/* 69 */     if (comp != null && comp.getAnimation() != null) {
/* 70 */       return CHARGING_TEXTURE;
/*    */     }
/*    */     
/* 73 */     return super.func_110775_a(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory
/*    */   {
/*    */     protected BipedModel model;
/*    */     protected float scale;
/*    */     private String texture;
/*    */     
/*    */     public Factory(BipedModel model, float scale, String texture) {
/* 85 */       this.model = model;
/* 86 */       this.scale = scale;
/* 87 */       this.texture = texture;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 93 */       return (EntityRenderer)new PacifistaRenderer<>(manager, this.model, this.scale, this.texture);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\PacifistaRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */