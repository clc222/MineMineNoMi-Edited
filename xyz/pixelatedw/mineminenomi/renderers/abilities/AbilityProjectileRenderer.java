/*     */ package xyz.pixelatedw.mineminenomi.renderers.abilities;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import java.awt.Color;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.projectile.ProjectileEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.IFlexibleSizeProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.models.abilities.CubeModel;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.abilities.effects.SpiralEffectRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class AbilityProjectileRenderer<T extends AbilityProjectileEntity, M extends EntityModel<T>>
/*     */   extends EntityRenderer<T>
/*     */ {
/*  45 */   private float scaleX = 1.0F, scaleY = 1.0F, scaleZ = 1.0F;
/*  46 */   private float rotationX = 0.0F; private float rotationY = 0.0F; private float rotationZ = 0.0F;
/*     */   
/*  48 */   protected double translateX = 0.0D; protected double translateY = 0.0D; protected double translateZ = 0.0D;
/*     */   
/*     */   private Color colour;
/*     */   
/*     */   protected M model;
/*     */   
/*     */   private ResourceLocation texture;
/*     */   
/*     */   private boolean usePlayerTexture;
/*     */   
/*     */   private boolean isGlowing;
/*     */   private SpiralEffectRenderer spiralEffectRenderer;
/*     */   
/*     */   public AbilityProjectileRenderer(EntityRendererManager renderManager, M model, @Nullable Set<Effect> effects) {
/*  62 */     super(renderManager);
/*     */     
/*  64 */     this.model = model;
/*     */     
/*  66 */     if (effects != null && 
/*  67 */       effects.contains(Effect.SPIRAL)) {
/*  68 */       this.spiralEffectRenderer = new SpiralEffectRenderer(renderManager);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTexture(ResourceLocation res) {
/*  74 */     this.texture = res;
/*     */   }
/*     */   
/*     */   public void setPlayerTexture(boolean flag) {
/*  78 */     this.usePlayerTexture = flag;
/*     */   }
/*     */   
/*     */   public Color getColor() {
/*  82 */     return this.colour;
/*     */   }
/*     */   
/*     */   public void setColor(Color colour) {
/*  86 */     this.colour = colour;
/*     */   }
/*     */   
/*     */   public void setGlowing(boolean flag) {
/*  90 */     this.isGlowing = flag;
/*     */   }
/*     */   
/*     */   public boolean isGlowing() {
/*  94 */     return this.isGlowing;
/*     */   }
/*     */   
/*     */   public void setScale(float scaleX, float scaleY, float scaleZ) {
/*  98 */     this.scaleX = scaleX;
/*  99 */     this.scaleY = scaleY;
/* 100 */     this.scaleZ = scaleZ;
/*     */   }
/*     */   
/*     */   public void setScale(Vector3f scale) {
/* 104 */     this.scaleX = scale.func_195899_a();
/* 105 */     this.scaleY = scale.func_195900_b();
/* 106 */     this.scaleZ = scale.func_195902_c();
/*     */   }
/*     */   
/*     */   public void setScale(float scale) {
/* 110 */     this.scaleX = this.scaleY = this.scaleZ = scale;
/*     */   }
/*     */   
/*     */   public void setRotation(float rotationX, float rotationY, float rotationZ) {
/* 114 */     this.rotationX = rotationX;
/* 115 */     this.rotationY = rotationY;
/* 116 */     this.rotationZ = rotationZ;
/*     */   }
/*     */   
/*     */   public void setTranslate(double tX, double tY, double tZ) {
/* 120 */     this.translateX = tX;
/* 121 */     this.translateY = tY;
/* 122 */     this.translateZ = tZ;
/*     */   }
/*     */   
/*     */   public Vector3d getScale() {
/* 126 */     return new Vector3d(this.scaleX, this.scaleY, this.scaleZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 131 */     if (((AbilityProjectileEntity)entity).field_70173_aa < 2) {
/*     */       return;
/*     */     }
/*     */     
/* 135 */     if (entity instanceof IFlexibleSizeProjectile) {
/* 136 */       setScale(((IFlexibleSizeProjectile)entity).getSize());
/*     */     }
/*     */     
/* 139 */     if (Minecraft.func_71410_x().func_175598_ae().func_178634_b() && !entity.func_82150_aj() && !Minecraft.func_71410_x().func_189648_am()) {
/* 140 */       renderDebugBox(matrixStack, buffer.getBuffer(RenderType.func_228659_m_()), entity);
/*     */     }
/*     */     
/* 143 */     matrixStack.func_227860_a_();
/* 144 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(MathHelper.func_219799_g(partialTicks, ((AbilityProjectileEntity)entity).field_70126_B, ((AbilityProjectileEntity)entity).field_70177_z) + 180.0F + this.rotationX));
/* 145 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(MathHelper.func_219799_g(partialTicks, ((AbilityProjectileEntity)entity).field_70127_C, ((AbilityProjectileEntity)entity).field_70125_A) + this.rotationY));
/* 146 */     matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(this.rotationZ));
/*     */     
/* 148 */     matrixStack.func_227862_a_(-1.0F, -1.0F, 1.0F);
/* 149 */     matrixStack.func_227862_a_(this.scaleX, this.scaleY, this.scaleZ);
/* 150 */     matrixStack.func_227861_a_(this.translateX, this.translateY, this.translateZ);
/*     */     
/* 152 */     RenderType type = getRenderType();
/*     */     
/* 154 */     ResourceLocation finalTexture = getTextureLocation((AbilityProjectileEntity)entity);
/*     */     
/* 156 */     if (type == null) {
/* 157 */       if (finalTexture == null) {
/* 158 */         type = isGlowing() ? ModRenderTypes.getEnergyRenderType() : ModRenderTypes.TRANSPARENT_COLOR;
/*     */       } else {
/* 160 */         type = RenderType.func_228644_e_(finalTexture);
/*     */       } 
/*     */     }
/*     */     
/* 164 */     boolean isSlim = false;
/*     */     
/* 166 */     if (entity.getThrower() instanceof net.minecraft.client.entity.player.ClientPlayerEntity) {
/* 167 */       isSlim = ((AbstractClientPlayerEntity)entity.getThrower()).func_175154_l().equals("slim");
/*     */     }
/*     */     
/* 170 */     if (isSlim && this.model instanceof xyz.pixelatedw.mineminenomi.models.abilities.EntityArmModel) {
/*     */       try {
/* 172 */         this.model = (M)this.model.getClass().getConstructor(new Class[] { boolean.class }).newInstance(new Object[] { Boolean.valueOf(isSlim) });
/* 173 */       } catch (InstantiationException|IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException|NoSuchMethodException|SecurityException e) {
/* 174 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */     
/* 178 */     float ageInTicks = ((AbilityProjectileEntity)entity).field_70173_aa + partialTicks;
/*     */     
/* 180 */     if (this.model != null) {
/* 181 */       this.model.func_225597_a_((Entity)entity, 0.0F, 0.0F, ageInTicks, 0.0F, 0.0F);
/*     */       
/* 183 */       Color colour = getColor();
/*     */       
/* 185 */       boolean haoshokuInfusionCheck = (entity.isAffectedByHaki() && HakiHelper.isProjectileHaoshokuInfused((ProjectileEntity)entity));
/*     */       
/* 187 */       if (haoshokuInfusionCheck && !isGlowing() && entity.getThrower() != null) {
/* 188 */         colour = new Color(HakiHelper.getHaoshokuColour(entity.getThrower()));
/*     */       }
/*     */       
/* 191 */       IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
/*     */       
/* 193 */       this.model.func_225598_a_(matrixStack, ivertexbuilder, packedLight, OverlayTexture.field_229196_a_, colour.getRed() / 255.0F, colour.getGreen() / 255.0F, colour.getBlue() / 255.0F, colour.getAlpha() / 255.0F);
/*     */       
/* 195 */       renderHakiOverlay(this.model, entity, matrixStack, buffer, packedLight);
/*     */       
/* 197 */       if (this.spiralEffectRenderer != null) {
/* 198 */         this.spiralEffectRenderer.func_225623_a_((Entity)entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*     */       }
/*     */     } 
/*     */     
/* 202 */     matrixStack.func_227865_b_();
/*     */     
/* 204 */     super.func_225623_a_((Entity)entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*     */   }
/*     */   
/*     */   public void renderHakiOverlay(M model, T entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 208 */     LivingEntity thrower = entity.getThrower();
/*     */     
/* 210 */     if (thrower == null) {
/*     */       return;
/*     */     }
/*     */     
/* 214 */     boolean highlightCheck = entity.isAffectedByHardening() ? HakiHelper.hasAdvancedBusoActive(thrower) : ((entity.isAffectedByHaki() && HakiHelper.isProjectileBusoshokuShrouded((ProjectileEntity)entity)));
/*     */     
/* 216 */     if (highlightCheck) {
/* 217 */       matrixStack.func_227860_a_();
/*     */       
/* 219 */       Color clientRGB = WyHelper.intToRGB(HakiHelper.getHaoshokuColour(thrower), 102);
/*     */       
/* 221 */       float scale = 1.1F;
/*     */       
/* 223 */       matrixStack.func_227862_a_(scale, scale, scale);
/*     */       
/* 225 */       IVertexBuilder ivb = buffer.getBuffer(ModRenderTypes.TRANSPARENT_COLOR);
/*     */       
/* 227 */       model.func_225598_a_(matrixStack, ivb, packedLight, OverlayTexture.field_229196_a_, clientRGB.getRed() / 255.0F, clientRGB.getGreen() / 255.0F, clientRGB.getBlue() / 255.0F, clientRGB.getAlpha() / 255.0F);
/*     */       
/* 229 */       matrixStack.func_227865_b_();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderDebugBox(MatrixStack matrixStack, IVertexBuilder vertexBuilder, T entity) {
/* 240 */     AxisAlignedBB axisalignedbb = new AxisAlignedBB(-entity.getBlockCollisionBox().func_216364_b() / 2.0D, -entity.getBlockCollisionBox().func_216360_c() / 2.0D, -entity.getBlockCollisionBox().func_216362_d() / 2.0D, entity.getBlockCollisionBox().func_216364_b() / 2.0D, entity.getBlockCollisionBox().func_216360_c() / 2.0D, entity.getBlockCollisionBox().func_216362_d() / 2.0D);
/*     */     
/* 242 */     WorldRenderer.func_228430_a_(matrixStack, vertexBuilder, axisalignedbb, 1.0F, 0.0F, 0.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public RenderType getRenderType() {
/* 246 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTextureLocation(AbilityProjectileEntity entity) {
/* 251 */     if (entity.func_234616_v_() != null && entity.func_234616_v_() instanceof LivingEntity && entity.isAffectedByHaki()) {
/* 252 */       boolean hardeningCheck = (entity.isAffectedByHardening() && HakiHelper.hasHardeningActive(entity.getThrower(), false, false));
/* 253 */       boolean imbuingCheck = (entity.isAffectedByImbuing() && HakiHelper.isProjectileBusoshokuImbued((ProjectileEntity)entity));
/*     */       
/* 255 */       if (hardeningCheck || imbuingCheck) {
/* 256 */         return ModResources.BUSOSHOKU_HAKI_ARM;
/*     */       }
/*     */     } 
/*     */     
/* 260 */     if (this.usePlayerTexture && entity.getThrower() != null && entity.getThrower() instanceof PlayerEntity) {
/* 261 */       PlayerEntity player = entity.field_70170_p.func_217371_b(entity.getThrower().func_110124_au());
/* 262 */       if (player != null) {
/* 263 */         return ((AbstractClientPlayerEntity)player).func_110306_p();
/*     */       }
/* 265 */       return DefaultPlayerSkin.func_177334_a(entity.func_110124_au());
/*     */     } 
/*     */     
/* 268 */     return this.texture;
/*     */   }
/*     */   
/*     */   public static class Factory implements IRenderFactory<AbilityProjectileEntity> {
/* 272 */     protected EntityModel model = (EntityModel)new CubeModel();
/*     */     
/* 274 */     protected float scaleX = 1.0F; protected float scaleY = 1.0F; protected float scaleZ = 1.0F;
/* 275 */     private float rotationX = 0.0F; private float rotationY = 0.0F; private float rotationZ = 0.0F;
/*     */     
/* 277 */     protected double translateX = 0.0D; protected double translateY = 0.0D; protected double translateZ = 0.0D;
/*     */     
/* 279 */     protected Color colour = new Color(255, 255, 255, 255);
/*     */     
/*     */     protected ResourceLocation texture;
/*     */     
/*     */     protected boolean usePlayerTexture;
/*     */     
/*     */     protected boolean isGlowing = false;
/*     */     
/* 287 */     protected Set<AbilityProjectileRenderer.Effect> effects = new HashSet<>();
/*     */     
/*     */     public Factory(EntityModel model) {
/* 290 */       this.model = model;
/*     */     }
/*     */     
/*     */     public Factory setTexture(String textureName) {
/* 294 */       this.texture = new ResourceLocation("mineminenomi", "textures/models/projectiles/" + textureName + ".png");
/* 295 */       return this;
/*     */     }
/*     */     
/*     */     public Factory setTexture(ResourceLocation location) {
/* 299 */       this.texture = location;
/* 300 */       return this;
/*     */     }
/*     */     
/*     */     public Factory setPlayerTexture() {
/* 304 */       this.usePlayerTexture = true;
/*     */       
/* 306 */       return this;
/*     */     }
/*     */     
/*     */     public Factory setGlowing() {
/* 310 */       this.isGlowing = true;
/* 311 */       return this;
/*     */     }
/*     */     
/*     */     public Factory setColor(int red, int green, int blue, int alpha) {
/* 315 */       this.colour = new Color(red, green, blue, alpha);
/*     */       
/* 317 */       return this;
/*     */     }
/*     */     
/*     */     public Factory setColor(float red, float green, float blue, float alpha) {
/* 321 */       this.colour = new Color(red, green, blue, alpha);
/*     */       
/* 323 */       return this;
/*     */     }
/*     */     
/*     */     public Factory setColor(String hex) {
/* 327 */       this.colour = WyHelper.hexToRGB(hex);
/*     */       
/* 329 */       return this;
/*     */     }
/*     */     
/*     */     public Factory setAlpha(int alpha) {
/* 333 */       setAlpha(alpha / 255.0F);
/*     */       
/* 335 */       return this;
/*     */     }
/*     */     
/*     */     public Factory setAlpha(float alpha) {
/* 339 */       float red = this.colour.getRed() / 255.0F;
/* 340 */       float green = this.colour.getGreen() / 255.0F;
/* 341 */       float blue = this.colour.getBlue() / 255.0F;
/*     */       
/* 343 */       this.colour = new Color(red, green, blue, alpha);
/*     */       
/* 345 */       return this;
/*     */     }
/*     */     
/*     */     public Factory setScale(double scale) {
/* 349 */       this.scaleX = this.scaleY = this.scaleZ = (float)scale;
/*     */       
/* 351 */       return this;
/*     */     }
/*     */     
/*     */     public Factory setScale(double scaleX, double scaleY, double scaleZ) {
/* 355 */       this.scaleX = (float)scaleX;
/* 356 */       this.scaleY = (float)scaleY;
/* 357 */       this.scaleZ = (float)scaleZ;
/*     */       
/* 359 */       return this;
/*     */     }
/*     */     
/*     */     public Factory setRotation(float rotationX, float rotationY, float rotationZ) {
/* 363 */       this.rotationX = rotationX;
/* 364 */       this.rotationY = rotationY;
/* 365 */       this.rotationZ = rotationZ;
/*     */       
/* 367 */       return this;
/*     */     }
/*     */     
/*     */     public Factory setTranslate(double tX, double tY, double tZ) {
/* 371 */       this.translateX = tX;
/* 372 */       this.translateY = tY;
/* 373 */       this.translateZ = tZ;
/*     */       
/* 375 */       return this;
/*     */     }
/*     */     
/*     */     public Factory addEffect(AbilityProjectileRenderer.Effect... effects) {
/* 379 */       this.effects.addAll(Sets.newHashSet((Object[])effects));
/*     */       
/* 381 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public EntityRenderer<? super AbilityProjectileEntity> createRenderFor(EntityRendererManager manager) {
/* 386 */       AbilityProjectileRenderer<AbilityProjectileEntity, EntityModel> renderer = new AbilityProjectileRenderer<>(manager, this.model, this.effects);
/*     */       
/* 388 */       renderer.setTexture(this.texture);
/* 389 */       renderer.setPlayerTexture(this.usePlayerTexture);
/* 390 */       renderer.setGlowing(this.isGlowing);
/* 391 */       renderer.setScale(this.scaleX, this.scaleY, this.scaleZ);
/* 392 */       renderer.setRotation(this.rotationX, this.rotationY, this.rotationZ);
/* 393 */       renderer.setTranslate(this.translateX, this.translateY, this.translateZ);
/* 394 */       renderer.setColor(this.colour);
/*     */       
/* 396 */       return renderer;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum Effect {
/* 401 */     SPIRAL;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\abilities\AbilityProjectileRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */