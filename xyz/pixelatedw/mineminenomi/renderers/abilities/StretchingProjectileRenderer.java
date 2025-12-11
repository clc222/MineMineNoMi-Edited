/*     */ package xyz.pixelatedw.mineminenomi.renderers.abilities;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.projectile.ProjectileEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Quaternion;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.doku.VenomRoadProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class StretchingProjectileRenderer<T extends AbilityProjectileEntity, M extends EntityModel<T>>
/*     */   extends AbilityProjectileRenderer<T, M>
/*     */ {
/*     */   private M stretchModel;
/*  35 */   private float stretchScaleX = 1.0F, stretchScaleY = 1.0F, stretchScaleZ = 1.0F;
/*     */   
/*     */   public StretchingProjectileRenderer(EntityRendererManager renderManager, M model, M stretchModel) {
/*  38 */     super(renderManager, model, null);
/*     */     
/*  40 */     this.stretchModel = stretchModel;
/*     */   }
/*     */   
/*     */   public void setStretchScale(double scaleX, double scaleY, double scaleZ) {
/*  44 */     this.stretchScaleX = (float)scaleX;
/*  45 */     this.stretchScaleY = (float)scaleY;
/*  46 */     this.stretchScaleZ = (float)scaleZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/*  51 */     if (((AbilityProjectileEntity)entity).field_70173_aa < 2) {
/*     */       return;
/*     */     }
/*     */     
/*  55 */     if (entity.getThrower() == null || !entity.getThrower().func_70089_S()) {
/*  56 */       entity.func_70106_y();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  61 */     if (Minecraft.func_71410_x().func_175598_ae().func_178634_b() && !entity.func_82150_aj() && !Minecraft.func_71410_x().func_189648_am()) {
/*  62 */       renderDebugBox(matrixStack, buffer.getBuffer(RenderType.func_228659_m_()), entity);
/*     */     }
/*     */     
/*  65 */     Vector3d originPos = entity.getThrower().func_213303_ch();
/*     */     
/*  67 */     if (entity instanceof VenomRoadProjectile) {
/*  68 */       originPos = ((VenomRoadProjectile)entity).getStartPos();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  74 */     Vector3d entityPos = new Vector3d(MathHelper.func_219803_d(partialTicks, ((AbilityProjectileEntity)entity).field_70169_q, entity.func_226277_ct_()), MathHelper.func_219803_d(partialTicks, ((AbilityProjectileEntity)entity).field_70167_r, entity.func_226278_cu_()), MathHelper.func_219803_d(partialTicks, ((AbilityProjectileEntity)entity).field_70166_s, entity.func_226281_cx_()));
/*     */     
/*  76 */     Vector3d stretchVec = entityPos.func_178788_d(originPos);
/*     */     
/*  78 */     if (this.stretchModel != null) {
/*  79 */       RenderType type; matrixStack.func_227860_a_();
/*  80 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(((AbilityProjectileEntity)entity).field_70126_B + (((AbilityProjectileEntity)entity).field_70177_z - ((AbilityProjectileEntity)entity).field_70126_B) * partialTicks - 180.0F));
/*  81 */       matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(((AbilityProjectileEntity)entity).field_70127_C + (((AbilityProjectileEntity)entity).field_70125_A - ((AbilityProjectileEntity)entity).field_70127_C) * partialTicks));
/*  82 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229183_f_, 180.0F, true));
/*     */       
/*  84 */       float modelLength = this.stretchScaleZ / 16.0F;
/*  85 */       float modelOffset = 0.25F;
/*  86 */       float stretchLength = (float)stretchVec.func_72433_c();
/*     */       
/*  88 */       matrixStack.func_227861_a_(this.translateX, this.translateY, -modelOffset - this.translateZ);
/*  89 */       matrixStack.func_227862_a_(this.stretchScaleX, this.stretchScaleY, (stretchLength - 2.0F * modelOffset) / modelLength);
/*  90 */       matrixStack.func_227861_a_(this.translateX, this.translateY, modelOffset + this.translateZ);
/*     */ 
/*     */ 
/*     */       
/*  94 */       ResourceLocation finalTexture = getTextureLocation((AbilityProjectileEntity)entity);
/*     */       
/*  96 */       if (finalTexture == null) {
/*  97 */         type = isGlowing() ? ModRenderTypes.getEnergyRenderType() : ModRenderTypes.TRANSPARENT_COLOR;
/*     */       } else {
/*  99 */         type = RenderType.func_228644_e_(finalTexture);
/*     */       } 
/*     */       
/* 102 */       boolean isSlim = false;
/*     */       
/* 104 */       if (entity.getThrower() instanceof net.minecraft.client.entity.player.ClientPlayerEntity) {
/* 105 */         isSlim = ((AbstractClientPlayerEntity)entity.getThrower()).func_175154_l().equals("slim");
/*     */       }
/*     */       
/* 108 */       if (isSlim && this.stretchModel instanceof xyz.pixelatedw.mineminenomi.models.abilities.EntityArmModel) {
/*     */         try {
/* 110 */           this.stretchModel = (M)this.stretchModel.getClass().getConstructor(new Class[] { boolean.class }).newInstance(new Object[] { Boolean.valueOf(isSlim) });
/* 111 */         } catch (InstantiationException|IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException|NoSuchMethodException|SecurityException e) {
/* 112 */           e.printStackTrace();
/*     */         } 
/*     */       }
/*     */       
/* 116 */       Color colour = getColor();
/*     */       
/* 118 */       boolean haoshokuInfusionCheck = (entity.isAffectedByHaki() && HakiHelper.isProjectileHaoshokuInfused((ProjectileEntity)entity));
/*     */       
/* 120 */       if (haoshokuInfusionCheck && !isGlowing() && entity.getThrower() != null) {
/* 121 */         colour = new Color(HakiHelper.getHaoshokuColour(entity.getThrower()));
/*     */       }
/*     */       
/* 124 */       IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
/*     */       
/* 126 */       this.stretchModel.func_225598_a_(matrixStack, ivertexbuilder, packedLight, OverlayTexture.field_229196_a_, colour.getRed() / 255.0F, colour.getGreen() / 255.0F, colour.getBlue() / 255.0F, colour.getAlpha() / 255.0F);
/*     */       
/* 128 */       renderHakiOverlay(this.stretchModel, entity, matrixStack, buffer, packedLight);
/*     */       
/* 130 */       matrixStack.func_227865_b_();
/*     */     } 
/*     */     
/* 133 */     super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderDebugBox(MatrixStack matrixStack, IVertexBuilder vertexBuilder, T entity) {
/* 143 */     AxisAlignedBB axisalignedbb = new AxisAlignedBB(-entity.getBlockCollisionBox().func_216364_b() / 2.0D, -entity.getBlockCollisionBox().func_216360_c() / 2.0D, -entity.getBlockCollisionBox().func_216362_d() / 2.0D, entity.getBlockCollisionBox().func_216364_b() / 2.0D, entity.getBlockCollisionBox().func_216360_c() / 2.0D, entity.getBlockCollisionBox().func_216362_d() / 2.0D);
/*     */     
/* 145 */     WorldRenderer.func_228430_a_(matrixStack, vertexBuilder, axisalignedbb, 1.0F, 0.0F, 0.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public static class Factory
/*     */     extends AbilityProjectileRenderer.Factory {
/*     */     private EntityModel stretchModel;
/* 151 */     protected double stretchScaleX = 1.0D, stretchScaleY = 1.0D, stretchScaleZ = 8.0D;
/*     */     
/*     */     public Factory(EntityModel stretchModel) {
/* 154 */       super(null);
/*     */       
/* 156 */       this.stretchModel = stretchModel;
/*     */     }
/*     */     
/*     */     public Factory(EntityModel tipModel, EntityModel stretchModel) {
/* 160 */       super(tipModel);
/*     */       
/* 162 */       this.stretchModel = stretchModel;
/*     */     }
/*     */     
/*     */     public Factory setStretchScale(double scaleX, double scaleY) {
/* 166 */       return setStretchScale(scaleX, scaleY, 8.0D);
/*     */     }
/*     */     
/*     */     public Factory setStretchScale(double scaleX, double scaleY, double scaleZ) {
/* 170 */       this.stretchScaleX = scaleX;
/* 171 */       this.stretchScaleY = scaleY;
/* 172 */       this.stretchScaleZ = scaleZ;
/*     */       
/* 174 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public EntityRenderer<? super AbilityProjectileEntity> createRenderFor(EntityRendererManager manager) {
/* 179 */       StretchingProjectileRenderer<AbilityProjectileEntity, EntityModel> renderer = new StretchingProjectileRenderer<>(manager, this.model, this.stretchModel);
/*     */       
/* 181 */       renderer.setStretchScale(this.stretchScaleX, this.stretchScaleY, this.stretchScaleZ);
/* 182 */       renderer.setTexture(this.texture);
/* 183 */       renderer.setPlayerTexture(this.usePlayerTexture);
/* 184 */       renderer.setGlowing(this.isGlowing);
/* 185 */       renderer.setScale(this.scaleX, this.scaleY, this.scaleZ);
/* 186 */       renderer.setTranslate(this.translateX, this.translateY, this.translateZ);
/* 187 */       renderer.setColor(this.colour);
/*     */       
/* 189 */       return renderer;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\abilities\StretchingProjectileRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */