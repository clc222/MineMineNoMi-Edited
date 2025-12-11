/*     */ package xyz.pixelatedw.mineminenomi.renderers.morphs;
/*     */ 
/*     */ import com.google.common.base.Function;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import java.awt.Color;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.vector.Matrix4f;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraft.world.LightType;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import org.apache.commons.lang3.tuple.Triple;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.goro.ElThorAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.BodyCoatingLayer;
/*     */ 
/*     */ public class GlowingModelRenderer<T extends AbstractClientPlayerEntity, M extends MorphModel>
/*     */   extends ZoanMorphRenderer<T, M> {
/*     */   protected Type type;
/*     */   
/*     */   public GlowingModelRenderer(EntityRendererManager rendererManager, MorphInfo info, Type type) {
/*  36 */     super(rendererManager, info);
/*  37 */     func_177094_a((LayerRenderer)new BodyCoatingLayer((IEntityRenderer)this));
/*  38 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225623_a_(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/*  44 */     super.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*     */     
/*  46 */     matrixStack.func_227860_a_();
/*  47 */     matrixStack.func_227861_a_(0.0D, 2.6D, 0.0D);
/*     */     
/*  49 */     int lightLevel = entity.func_130014_f_().func_226658_a_(LightType.SKY, entity.func_233580_cy_()) - entity.func_130014_f_().func_175657_ab();
/*  50 */     if (this.type == Type.AMARU)
/*     */     {
/*  52 */       lightLevel = 8;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  63 */     if (lightLevel > 7) {
/*     */       
/*  65 */       Random random = new Random(500L);
/*  66 */       float rays = (20 + lightLevel * 16);
/*  67 */       float randMovement = (entity.field_70173_aa + partialTicks) / 500.0F;
/*     */       
/*  69 */       IVertexBuilder vertexBuilder = buffer.getBuffer(RenderType.func_228657_l_());
/*  70 */       matrixStack.func_227860_a_();
/*  71 */       matrixStack.func_227861_a_(0.0D, 0.75D, 0.0D);
/*     */       
/*  73 */       for (int i = 0; i < rays; i++) {
/*     */         
/*  75 */         float size = 9.6F + ((this.type == Type.AMARU) ? 0.0F : 1.4F);
/*  76 */         matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(random.nextFloat() * 360.0F));
/*  77 */         matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(random.nextFloat() * 360.0F));
/*  78 */         matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(random.nextFloat() * 360.0F));
/*  79 */         matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(random.nextFloat() * 360.0F));
/*  80 */         matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(random.nextFloat() * 360.0F));
/*  81 */         matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(random.nextFloat() * 360.0F + randMovement * 90.0F));
/*  82 */         float f3 = size * random.nextFloat();
/*  83 */         float f4 = size * random.nextFloat();
/*  84 */         Matrix4f matrix4f = matrixStack.func_227866_c_().func_227870_a_();
/*     */         
/*  86 */         int alpha = 8 + lightLevel / 2;
/*     */         
/*  88 */         Color color1 = (Color)this.type.getColorScheme(alpha).getRight();
/*  89 */         Color color2 = (Color)this.type.getColorScheme(alpha).getMiddle();
/*  90 */         Color color3 = (Color)this.type.getColorScheme(alpha).getLeft();
/*     */         
/*  92 */         RendererHelper.drawA(vertexBuilder, matrix4f, color3);
/*  93 */         RendererHelper.drawB(vertexBuilder, matrix4f, f3, f4, color2);
/*  94 */         RendererHelper.drawC(vertexBuilder, matrix4f, f3, f4, color1);
/*  95 */         RendererHelper.drawA(vertexBuilder, matrix4f, color3);
/*  96 */         RendererHelper.drawC(vertexBuilder, matrix4f, f3, f4, color2);
/*  97 */         RendererHelper.drawD(vertexBuilder, matrix4f, f3, f4, color1);
/*  98 */         RendererHelper.drawA(vertexBuilder, matrix4f, color3);
/*  99 */         RendererHelper.drawD(vertexBuilder, matrix4f, f3, f4, color1);
/* 100 */         RendererHelper.drawB(vertexBuilder, matrix4f, f3, f4, color2);
/*     */       } 
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
/*     */       
/* 116 */       matrixStack.func_227865_b_();
/*     */     } 
/* 118 */     matrixStack.func_227865_b_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation func_110775_a(AbstractClientPlayerEntity entity) {
/* 124 */     return entity.func_110306_p();
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Factory<T extends PlayerEntity>
/*     */     implements IRenderFactory<T>
/*     */   {
/*     */     private MorphInfo info;
/*     */     
/*     */     public Factory(MorphInfo info, GlowingModelRenderer.Type type) {
/* 134 */       this.info = info;
/* 135 */       this.type = type;
/*     */     }
/*     */     
/*     */     private GlowingModelRenderer.Type type;
/*     */     
/*     */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 141 */       GlowingModelRenderer<AbstractClientPlayerEntity, MorphModel> renderer = new GlowingModelRenderer<>(manager, this.info, this.type);
/* 142 */       return (EntityRenderer)renderer;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum Type {
/*     */     static {
/* 148 */       DAIBUTSU = new Type("DAIBUTSU", 0, alpha -> Triple.of(new Color(255, 255, 0, 0), new Color(255, 100, 0, 0), new Color(255, 50, 0, alpha.intValue())));
/*     */ 
/*     */ 
/*     */       
/* 152 */       AMARU = new Type("AMARU", 1, alpha -> {
/*     */             if (ClientConfig.INSTANCE.isGoroBlue()) {
/*     */               Color weakBlue = new Color(ElThorAbility.BLUE_THUNDER.getRed(), ElThorAbility.BLUE_THUNDER.getGreen(), ElThorAbility.BLUE_THUNDER.getBlue(), 5);
/*     */               Color strongBlue = new Color(ElThorAbility.BLUE_THUNDER.getRed(), ElThorAbility.BLUE_THUNDER.getGreen(), ElThorAbility.BLUE_THUNDER.getBlue(), alpha.intValue());
/*     */               return Triple.of(weakBlue, weakBlue, strongBlue);
/*     */             } 
/*     */             return Triple.of(new Color(255, 249, 0, 0), new Color(225, 100, 20, 0), new Color(225, 180, 50, alpha.intValue()));
/*     */           });
/*     */     }
/*     */     
/*     */     DAIBUTSU, AMARU;
/*     */     
/*     */     Type(Function<Integer, Triple> colorScheme) {
/* 165 */       this.colorScheme = colorScheme;
/*     */     }
/*     */     private Function<Integer, Triple> colorScheme;
/*     */     
/*     */     public Triple<Color, Color, Color> getColorScheme(int alpha) {
/* 170 */       return (Triple<Color, Color, Color>)this.colorScheme.apply(Integer.valueOf(alpha));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\morphs\GlowingModelRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */