/*     */ package xyz.pixelatedw.mineminenomi.renderers.abilities;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import java.awt.Color;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.vector.Matrix4f;
/*     */ import net.minecraft.util.math.vector.Quaternion;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.VariProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.models.abilities.CubeModel;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class VoltVariProjectileRenderer<T extends VariProjectile, M extends EntityModel<T>>
/*     */   extends EntityRenderer<T>
/*     */ {
/*  35 */   private float scaleX = 1.0F; private float scaleY = 1.0F; private float scaleZ = 1.0F;
/*     */   private float red; private float blue; private float green; private float alpha; protected M model; private ResourceLocation texture; Random random;
/*     */   
/*     */   public void setTexture(ResourceLocation res) {
/*     */     this.texture = res;
/*     */   }
/*     */   
/*  42 */   public VoltVariProjectileRenderer(EntityRendererManager renderManager, M model) { super(renderManager);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  76 */     this.random = new Random();
/*     */     this.model = model; }
/*     */   public void setColor(double red, double green, double blue, double alpha) { this.red = (float)(red / 255.0D);
/*     */     this.green = (float)(green / 255.0D);
/*     */     this.blue = (float)(blue / 255.0D);
/*  81 */     this.alpha = (float)(alpha / 255.0D); } public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) { if (((VariProjectile)entity).field_70173_aa < 2) {
/*     */       return;
/*     */     }
/*  84 */     setScale(entity.getSize(), entity.getSize(), entity.getSize());
/*  85 */     matrixStack.func_227860_a_();
/*  86 */     IVertexBuilder vertexBuilder = buffer.getBuffer(RenderType.func_228657_l_());
/*     */     
/*  88 */     float randMovement = (((VariProjectile)entity).field_70173_aa + partialTicks) / 600.0F;
/*  89 */     float rays = 1000.0F;
/*  90 */     matrixStack.func_227860_a_();
/*     */     
/*  92 */     for (int i = 0; i < rays; i++) {
/*     */       
/*  94 */       matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(this.random.nextFloat() * 360.0F + randMovement * 90.0F));
/*     */       
/*  96 */       matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(this.random.nextFloat() * 360.0F + randMovement * 90.0F));
/*  97 */       float f3 = getScale().func_195899_a() / 3.0F * this.random.nextFloat();
/*  98 */       float f4 = getScale().func_195899_a() / 3.0F * this.random.nextFloat();
/*  99 */       Matrix4f matrix4f = matrixStack.func_227866_c_().func_227870_a_();
/*     */       
/* 101 */       Color a = new Color(125, 249, 255, 80);
/* 102 */       Color b = new Color(125, 100, 255, 0);
/* 103 */       Color c = new Color(125, 255, 255, 0);
/*     */       
/* 105 */       RendererHelper.drawA(vertexBuilder, matrix4f, a);
/* 106 */       RendererHelper.drawB(vertexBuilder, matrix4f, f3, f4, b);
/* 107 */       RendererHelper.drawC(vertexBuilder, matrix4f, f3, f4, c);
/* 108 */       RendererHelper.drawA(vertexBuilder, matrix4f, a);
/* 109 */       RendererHelper.drawC(vertexBuilder, matrix4f, f3, f4, c);
/* 110 */       RendererHelper.drawD(vertexBuilder, matrix4f, f3, f4, c);
/* 111 */       RendererHelper.drawA(vertexBuilder, matrix4f, a);
/* 112 */       RendererHelper.drawD(vertexBuilder, matrix4f, f3, f4, c);
/* 113 */       RendererHelper.drawB(vertexBuilder, matrix4f, f3, f4, b);
/*     */     } 
/* 115 */     matrixStack.func_227865_b_();
/*     */ 
/*     */     
/* 118 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, ((VariProjectile)entity).field_70126_B + (((VariProjectile)entity).field_70177_z - ((VariProjectile)entity).field_70126_B) * partialTicks - 180.0F, true));
/* 119 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229179_b_, ((VariProjectile)entity).field_70127_C + (((VariProjectile)entity).field_70125_A - ((VariProjectile)entity).field_70127_C) * partialTicks, true));
/* 120 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229183_f_, 180.0F, true));
/*     */     
/* 122 */     matrixStack.func_227862_a_(this.scaleX, this.scaleY, this.scaleZ);
/*     */     
/* 124 */     ResourceLocation finalTexture = getTextureLocation(entity);
/* 125 */     RenderType type = (finalTexture == null) ? ModRenderTypes.TRANSPARENT_COLOR : RenderType.func_228644_e_(finalTexture);
/*     */     
/* 127 */     if (this.model != null) {
/* 128 */       this.model.func_225598_a_(matrixStack, buffer.getBuffer(type), packedLight, OverlayTexture.field_229196_a_, getRGB().getRed() / 255.0F, getRGB().getGreen() / 255.0F, getRGB().getBlue() / 255.0F, getRGB().getAlpha() / 255.0F);
/*     */     }
/* 130 */     matrixStack.func_227865_b_(); }
/*     */    public void setScale(double scaleX, double scaleY, double scaleZ) {
/*     */     this.scaleX = (float)scaleX;
/*     */     this.scaleY = (float)scaleY;
/*     */     this.scaleZ = (float)scaleZ;
/*     */   } public ResourceLocation getTextureLocation(T entity) {
/* 136 */     if (entity.getThrower() != null && entity.isAffectedByHaki()) {
/*     */       
/* 138 */       boolean hardeningCheck = (entity.isAffectedByHardening() && HakiHelper.hasHardeningActive(entity.getThrower()));
/* 139 */       if (hardeningCheck) {
/* 140 */         return ModResources.BUSOSHOKU_HAKI_ARM;
/*     */       }
/*     */     } 
/* 143 */     return this.texture;
/*     */   } public Vector3f getScale() {
/*     */     return new Vector3f(this.scaleX, this.scaleY, this.scaleZ);
/*     */   } public Color getRGB() {
/*     */     return new Color(this.red, this.green, this.blue, this.alpha);
/* 148 */   } public static class Factory implements IRenderFactory<VariProjectile> { protected EntityModel model = (EntityModel)new CubeModel();
/* 149 */     protected double scaleX = 1.0D, scaleY = 1.0D, scaleZ = 1.0D;
/* 150 */     protected double red = 255.0D; protected double green = 255.0D; protected double blue = 255.0D; protected double alpha = 255.0D;
/*     */     
/*     */     protected ResourceLocation texture;
/*     */     
/*     */     public Factory(EntityModel model) {
/* 155 */       this.model = model;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setTexture(String textureName) {
/* 160 */       this.texture = new ResourceLocation("mineminenomi", "textures/models/projectiles/" + textureName + ".png");
/* 161 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setTexture(ResourceLocation location) {
/* 166 */       this.texture = location;
/* 167 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setColor(double red, double green, double blue, double alpha) {
/* 172 */       this.red = red;
/* 173 */       this.green = green;
/* 174 */       this.blue = blue;
/* 175 */       this.alpha = alpha;
/* 176 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setColor(String hex) {
/* 181 */       Color color = WyHelper.hexToRGB(hex);
/* 182 */       this.red = color.getRed();
/* 183 */       this.green = color.getGreen();
/* 184 */       this.blue = color.getBlue();
/* 185 */       this.alpha = color.getAlpha();
/* 186 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setAlpha(double alpha) {
/* 191 */       this.alpha = alpha;
/* 192 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setScale(double scale) {
/* 197 */       this.scaleX = this.scaleY = this.scaleZ = scale;
/* 198 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setScale(double scaleX, double scaleY, double scaleZ) {
/* 203 */       this.scaleX = scaleX;
/* 204 */       this.scaleY = scaleY;
/* 205 */       this.scaleZ = scaleZ;
/* 206 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public EntityRenderer<? super VariProjectile> createRenderFor(EntityRendererManager manager) {
/* 212 */       VoltVariProjectileRenderer<VariProjectile, EntityModel> renderer = new VoltVariProjectileRenderer<>(manager, this.model);
/* 213 */       renderer.setTexture(this.texture);
/* 214 */       renderer.setScale(this.scaleX, this.scaleY, this.scaleZ);
/* 215 */       renderer.setColor(this.red, this.green, this.blue, this.alpha);
/* 216 */       return renderer;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\abilities\VoltVariProjectileRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */