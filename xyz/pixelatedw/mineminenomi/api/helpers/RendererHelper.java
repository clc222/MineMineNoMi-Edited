/*     */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.platform.GlStateManager;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.AbstractGui;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.network.play.ClientPlayNetHandler;
/*     */ import net.minecraft.client.network.play.NetworkPlayerInfo;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldVertexBufferUploader;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.PlayerModelPart;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.vector.Matrix3f;
/*     */ import net.minecraft.util.math.vector.Matrix4f;
/*     */ import net.minecraft.util.math.vector.Quaternion;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraft.util.text.ITextProperties;
/*     */ import net.minecraft.util.text.LanguageMap;
/*     */ import net.minecraft.util.text.Style;
/*     */ import net.minecraftforge.client.event.RenderTooltipEvent;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.JollyRoger;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.JollyRogerElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.animation.AnimationDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ public class RendererHelper
/*     */ {
/*  68 */   public static final ResourceLocation VANILLA_VIGNETTE_TEX_PATH = new ResourceLocation("textures/misc/vignette.png");
/*  69 */   public static final ResourceLocation FORCEFIELD_LOCATION = new ResourceLocation("textures/misc/forcefield.png");
/*  70 */   private static final Color WORLD_GOVT_COLOR = WyHelper.hexToRGB("#2B497B");
/*     */   
/*     */   public static boolean hasModelChanged = false;
/*     */ 
/*     */   
/*     */   public static <M extends EntityModel> void resetHumanoidModelToDefaultPivots(M model) {
/*  76 */     if (model instanceof net.minecraft.client.renderer.entity.model.PlayerModel || model instanceof xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel || model instanceof xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.DojoSenseiModel) {
/*  77 */       BipedModel biped = (BipedModel)model;
/*     */       
/*  79 */       biped.field_178722_k.func_78793_a(2.0F, 12.0F, 0.0F);
/*  80 */       biped.field_178722_k.field_78795_f = 0.0F;
/*  81 */       biped.field_178722_k.field_78796_g = 0.0F;
/*  82 */       biped.field_178722_k.field_78808_h = 0.0F;
/*     */       
/*  84 */       biped.field_178721_j.func_78793_a(-2.0F, 12.0F, 0.0F);
/*  85 */       biped.field_178721_j.field_78795_f = 0.0F;
/*  86 */       biped.field_178721_j.field_78796_g = 0.0F;
/*  87 */       biped.field_178721_j.field_78808_h = 0.0F;
/*     */       
/*  89 */       biped.field_178724_i.func_78793_a(5.0F, 2.0F, 0.0F);
/*  90 */       biped.field_178724_i.field_78795_f = 0.0F;
/*  91 */       biped.field_178724_i.field_78796_g = 0.0F;
/*  92 */       biped.field_178724_i.field_78808_h = 0.0F;
/*     */       
/*  94 */       biped.field_178723_h.func_78793_a(-5.0F, 2.0F, 0.0F);
/*  95 */       biped.field_178723_h.field_78795_f = 0.0F;
/*  96 */       biped.field_178723_h.field_78796_g = 0.0F;
/*  97 */       biped.field_178723_h.field_78808_h = 0.0F;
/*     */       
/*  99 */       biped.field_78116_c.func_78793_a(0.0F, 0.0F, 0.0F);
/* 100 */       biped.field_78116_c.field_78795_f = 0.0F;
/* 101 */       biped.field_78116_c.field_78796_g = 0.0F;
/* 102 */       biped.field_78116_c.field_78808_h = 0.0F;
/*     */       
/* 104 */       biped.field_78115_e.func_78793_a(0.0F, 0.0F, 0.0F);
/* 105 */       biped.field_78115_e.field_78795_f = 0.0F;
/* 106 */       biped.field_78115_e.field_78796_g = 0.0F;
/* 107 */       biped.field_78115_e.field_78808_h = 0.0F;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static <M extends EntityModel> void animationHeldItem(LivingEntity entity, ItemStack itemStack, ItemCameraTransforms.TransformType transformType, HandSide handSide, MatrixStack matrixType, IRenderTypeBuffer renderBuffer, int packedLight) {
/* 112 */     Animation<LivingEntity, M> animation = AnimationDataCapability.get(entity).getAnimation();
/* 113 */     if (animation != null && animation.isPlaying()) {
/* 114 */       animation.setupHeldItem(entity, itemStack, transformType, handSide, matrixType, renderBuffer, packedLight);
/*     */     }
/*     */   }
/*     */   
/*     */   public static <M extends EntityModel> void animationSetup(LivingEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 119 */     Animation<LivingEntity, M> animation = AnimationDataCapability.get(entity).getAnimation();
/* 120 */     if (animation != null && animation.isPlaying()) {
/* 121 */       animation.setupAnimation(entity, matrixStack, buffer, packedLight, entityYaw, partialTicks);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <M extends EntityModel<?>> void animationAngles(LivingEntity entity, M model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 131 */     Animation<LivingEntity, M> animation = AnimationDataCapability.get(entity).getAnimation();
/* 132 */     if (animation != null && animation.isPlaying()) {
/* 133 */       animation.setAnimationAngles(entity, (EntityModel)model, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 141 */     if (model instanceof BipedModel) {
/* 142 */       ((BipedModel)model).field_178720_f.func_217177_a(((BipedModel)model).field_78116_c);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void drawPlayerFace(PlayerEntity player, MatrixStack matrixStack, int x, int y, int width, int height) {
/* 147 */     Minecraft mc = Minecraft.func_71410_x();
/* 148 */     ClientPlayNetHandler netHandler = mc.field_71439_g.field_71174_a;
/*     */     
/* 150 */     NetworkPlayerInfo playerInfo = netHandler.func_175102_a(player.func_110124_au());
/* 151 */     mc.func_110434_K().func_110577_a(playerInfo.func_178837_g());
/*     */     
/* 153 */     AbstractGui.func_238466_a_(matrixStack, x, y, width, height, 8.0F, 8.0F, 8, 8, 64, 64);
/* 154 */     if (player.func_175148_a(PlayerModelPart.HAT)) {
/* 155 */       AbstractGui.func_238466_a_(matrixStack, x, y, width, height, 40.0F, 8.0F, 8, 8, 64, 64);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void drawLivingBust(LivingEntity entity, MatrixStack matrixStack, int posX, int posY, int scale, int mouseX, int mouseY, boolean isHidden) {
/* 160 */     float f = (float)Math.atan((mouseX / 40.0F));
/* 161 */     float f1 = (float)Math.atan((mouseY / 40.0F));
/*     */     
/* 163 */     Minecraft mc = Minecraft.func_71410_x();
/* 164 */     double uiscale = mc.func_228018_at_().func_198100_s();
/* 165 */     double left = (posX - scale);
/* 166 */     double width = posX;
/* 167 */     double top = posY - scale * 1.1D;
/* 168 */     double height = posY;
/*     */     
/* 170 */     matrixStack.func_227860_a_();
/* 171 */     matrixStack.func_227861_a_(posX, posY, 1050.0D);
/* 172 */     matrixStack.func_227862_a_(1.0F, 1.0F, -1.0F);
/* 173 */     matrixStack.func_227861_a_(0.0D, 0.0D, 1000.0D);
/* 174 */     matrixStack.func_227862_a_(scale, scale, scale);
/* 175 */     Quaternion quaternion = Vector3f.field_229183_f_.func_229187_a_(180.0F);
/* 176 */     Quaternion quaternion1 = Vector3f.field_229179_b_.func_229187_a_(f1 * 20.0F);
/* 177 */     quaternion.func_195890_a(quaternion1);
/* 178 */     matrixStack.func_227863_a_(quaternion);
/* 179 */     float yBodyRot = entity.field_70761_aq;
/* 180 */     float yRot = entity.field_70177_z;
/* 181 */     float xRot = entity.field_70125_A;
/* 182 */     float yHeadRotO = entity.field_70758_at;
/* 183 */     float yHeadRot = entity.field_70759_as;
/* 184 */     ItemStack mainHand = entity.func_184586_b(Hand.MAIN_HAND);
/* 185 */     ItemStack offHand = entity.func_184586_b(Hand.OFF_HAND);
/* 186 */     entity.field_70761_aq = 180.0F + f * 20.0F;
/* 187 */     entity.field_70177_z = 180.0F + f * 40.0F;
/* 188 */     entity.field_70125_A = -f1 * 30.0F;
/* 189 */     entity.field_70759_as = entity.field_70177_z;
/* 190 */     entity.field_70758_at = entity.field_70177_z;
/*     */ 
/*     */ 
/*     */     
/* 194 */     EntityRendererManager manager = Minecraft.func_71410_x().func_175598_ae();
/* 195 */     quaternion1.func_195892_e();
/* 196 */     manager.func_229089_a_(quaternion1);
/* 197 */     manager.func_178633_a(false);
/* 198 */     manager.func_178629_b(false);
/* 199 */     IRenderTypeBuffer.Impl buffer = Minecraft.func_71410_x().func_228019_au_().func_228487_b_();
/*     */     
/* 201 */     GL11.glEnable(3089);
/* 202 */     GL11.glScissor((int)(left * uiscale), (int)(mc.func_228018_at_().func_198091_l() - top * uiscale), (int)(width * uiscale), (int)(height * uiscale));
/*     */ 
/*     */     
/* 205 */     float entityHeight = (entity.func_213305_a(Pose.STANDING)).field_220316_b;
/* 206 */     if (entityHeight > 1.6F) {
/* 207 */       entityHeight = 0.05F;
/*     */     } else {
/*     */       
/* 210 */       entityHeight -= 1.8F;
/* 211 */       entityHeight *= -1.0F;
/*     */     } 
/* 213 */     manager.func_229084_a_((Entity)entity, 0.0D, entityHeight, 0.0D, 0.0F, 1.0F, matrixStack, (IRenderTypeBuffer)buffer, isHidden ? Color.BLACK.getRGB() : 15728860);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 219 */     buffer.getBuffer(RenderType.func_228659_m_()).func_225582_a_(0.0D, 0.0D, 0.0D);
/*     */     
/* 221 */     GL11.glDisable(3089);
/*     */     
/* 223 */     buffer.func_228461_a_();
/* 224 */     manager.func_178633_a(true);
/* 225 */     entity.field_70761_aq = yBodyRot;
/* 226 */     entity.field_70177_z = yRot;
/* 227 */     entity.field_70125_A = xRot;
/* 228 */     entity.field_70758_at = yHeadRotO;
/* 229 */     entity.field_70759_as = yHeadRot;
/*     */ 
/*     */     
/* 232 */     matrixStack.func_227865_b_();
/*     */   }
/*     */   
/*     */   public static void drawTexturedModalRect(MatrixStack matrixStack, float x, float y, float u, float v, float width, float height, float zLevel, float red, float green, float blue, float alpha) {
/* 236 */     drawTexturedModalRect(matrixStack, x, y, u, v, width, height, zLevel, 1.0F, 1.0F, red, green, blue, alpha);
/*     */   }
/*     */   
/*     */   public static void drawTexturedModalRect(MatrixStack matrixStack, float x, float y, float u, float v, float width, float height, float zLevel, float uScale, float vScale, float red, float green, float blue, float alpha) {
/* 240 */     float uAtalsScale = 0.00390625F;
/* 241 */     float vAtlasScale = 0.00390625F;
/*     */     
/* 243 */     float width2 = width * uScale;
/* 244 */     float height2 = height * vScale;
/*     */     
/* 246 */     RenderSystem.enableBlend();
/* 247 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 248 */     BufferBuilder wr = tessellator.func_178180_c();
/* 249 */     wr.func_181668_a(7, DefaultVertexFormats.field_227851_o_);
/* 250 */     Matrix4f matrix = matrixStack.func_227866_c_().func_227870_a_();
/* 251 */     wr.func_227888_a_(matrix, x, y + height2, zLevel).func_227885_a_(red, green, blue, alpha).func_225583_a_(u * 0.00390625F, (v + height) * 0.00390625F).func_181675_d();
/* 252 */     wr.func_227888_a_(matrix, x + width2, y + height2, zLevel).func_227885_a_(red, green, blue, alpha).func_225583_a_((u + width) * 0.00390625F, (v + height) * 0.00390625F).func_181675_d();
/* 253 */     wr.func_227888_a_(matrix, x + width2, y, zLevel).func_227885_a_(red, green, blue, alpha).func_225583_a_((u + width) * 0.00390625F, v * 0.00390625F).func_181675_d();
/* 254 */     wr.func_227888_a_(matrix, x, y, zLevel).func_227885_a_(red, green, blue, alpha).func_225583_a_(u * 0.00390625F, v * 0.00390625F).func_181675_d();
/* 255 */     tessellator.func_78381_a();
/* 256 */     RenderSystem.disableBlend();
/*     */   }
/*     */   
/*     */   public static void renderVeryUglySphere(MatrixStack matrixStack, ActiveRenderInfo info, BufferBuilder buffer, double posX, double posY, double posZ, float raduis, float red, float green, float blue, float alpha, float animUSpeed, float animVSpeed, ResourceLocation texture) {
/* 260 */     buffer.func_181668_a(7, DefaultVertexFormats.field_227851_o_);
/* 261 */     float animU = (float)Util.func_211177_b() % animUSpeed / animUSpeed;
/* 262 */     float animV = (float)Util.func_211177_b() % animVSpeed / animVSpeed;
/* 263 */     float u = animU - 0.0F;
/* 264 */     float v = animV - 1.0F;
/*     */     
/* 266 */     float x = (float)(info.func_216785_c()).field_72450_a;
/* 267 */     float y = (float)(info.func_216785_c()).field_72448_b;
/* 268 */     float z = (float)(info.func_216785_c()).field_72449_c;
/*     */     
/* 270 */     float minY = 0.0F - y;
/* 271 */     float maxY = 256.0F - y;
/*     */     
/* 273 */     minY = (float)((-raduis - y) + posY);
/* 274 */     maxY = (float)((raduis - y) + posY);
/*     */     
/* 276 */     float minX = (float)((-raduis - x) + posX);
/* 277 */     float maxX = (float)((raduis + 1.0F - x) + posX);
/* 278 */     float minZ = (float)((-raduis - z) + posZ);
/* 279 */     float maxZ = (float)((raduis + 1.0F - z) + posZ);
/*     */     
/* 281 */     Matrix4f matrix4f = matrixStack.func_227866_c_().func_227870_a_();
/*     */     
/* 283 */     RenderSystem.enableBlend();
/* 284 */     RenderSystem.enableDepthTest();
/* 285 */     RenderSystem.defaultBlendFunc();
/* 286 */     Minecraft.func_71410_x().func_110434_K().func_110577_a(texture);
/* 287 */     RenderSystem.pushMatrix();
/*     */     
/* 289 */     RenderSystem.polygonOffset(-3.0F, -3.0F);
/* 290 */     RenderSystem.enablePolygonOffset();
/* 291 */     RenderSystem.defaultAlphaFunc();
/* 292 */     RenderSystem.enableAlphaTest();
/* 293 */     RenderSystem.disableCull();
/*     */     
/* 295 */     buffer.func_227888_a_(matrix4f, minX, minY, minZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(u, v).func_181675_d();
/* 296 */     buffer.func_227888_a_(matrix4f, minX, maxY, minZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(v, v).func_181675_d();
/* 297 */     buffer.func_227888_a_(matrix4f, maxX, maxY, minZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(v, u).func_181675_d();
/* 298 */     buffer.func_227888_a_(matrix4f, maxX, minY, minZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(u, u).func_181675_d();
/*     */     
/* 300 */     buffer.func_227888_a_(matrix4f, minX, minY, maxZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(u, v).func_181675_d();
/* 301 */     buffer.func_227888_a_(matrix4f, minX, maxY, maxZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(v, v).func_181675_d();
/* 302 */     buffer.func_227888_a_(matrix4f, maxX, maxY, maxZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(v, u).func_181675_d();
/* 303 */     buffer.func_227888_a_(matrix4f, maxX, minY, maxZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(u, u).func_181675_d();
/*     */     
/* 305 */     buffer.func_227888_a_(matrix4f, minX, minY, minZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(u, v).func_181675_d();
/* 306 */     buffer.func_227888_a_(matrix4f, minX, maxY, minZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(v, v).func_181675_d();
/* 307 */     buffer.func_227888_a_(matrix4f, minX, maxY, maxZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(v, u).func_181675_d();
/* 308 */     buffer.func_227888_a_(matrix4f, minX, minY, maxZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(u, u).func_181675_d();
/*     */     
/* 310 */     buffer.func_227888_a_(matrix4f, maxX, minY, minZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(u, v).func_181675_d();
/* 311 */     buffer.func_227888_a_(matrix4f, maxX, maxY, minZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(v, v).func_181675_d();
/* 312 */     buffer.func_227888_a_(matrix4f, maxX, maxY, maxZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(v, u).func_181675_d();
/* 313 */     buffer.func_227888_a_(matrix4f, maxX, minY, maxZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(u, u).func_181675_d();
/*     */ 
/*     */     
/* 316 */     buffer.func_227888_a_(matrix4f, maxX, minY, minZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(u, v).func_181675_d();
/* 317 */     buffer.func_227888_a_(matrix4f, minX, minY, minZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(v, v).func_181675_d();
/* 318 */     buffer.func_227888_a_(matrix4f, minX, minY, maxZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(v, u).func_181675_d();
/* 319 */     buffer.func_227888_a_(matrix4f, maxX, minY, maxZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(u, u).func_181675_d();
/*     */     
/* 321 */     buffer.func_227888_a_(matrix4f, maxX, maxY, minZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(u, v).func_181675_d();
/* 322 */     buffer.func_227888_a_(matrix4f, minX, maxY, minZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(v, v).func_181675_d();
/* 323 */     buffer.func_227888_a_(matrix4f, minX, maxY, maxZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(v, u).func_181675_d();
/* 324 */     buffer.func_227888_a_(matrix4f, maxX, maxY, maxZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(u, u).func_181675_d();
/*     */     
/* 326 */     buffer.func_178977_d();
/* 327 */     WorldVertexBufferUploader.func_181679_a(buffer);
/* 328 */     RenderSystem.enableCull();
/* 329 */     RenderSystem.disableAlphaTest();
/* 330 */     RenderSystem.polygonOffset(0.0F, 0.0F);
/* 331 */     RenderSystem.disablePolygonOffset();
/* 332 */     RenderSystem.enableAlphaTest();
/* 333 */     RenderSystem.disableBlend();
/* 334 */     RenderSystem.popMatrix();
/*     */   }
/*     */   
/*     */   public static void renderAbilityProtectionArea(MatrixStack matrixStack, ActiveRenderInfo info, BufferBuilder buffer, double posX, double posY, double posZ, float sizeX, float sizeY, float sizeZ) {
/* 338 */     renderAreaBorder(matrixStack, info, buffer, posX, posY, posZ, sizeX, sizeY, sizeZ, 0.0F, 1.0F, 1.0F, 1.0F, 10000.0F, 10000.0F, FORCEFIELD_LOCATION);
/*     */   }
/*     */   
/*     */   public static void renderAreaBorder(MatrixStack matrixStack, ActiveRenderInfo info, BufferBuilder buffer, double posX, double posY, double posZ, float sizeX, float sizeY, float sizeZ, float red, float green, float blue, float alpha, float animUSpeed, float animVSpeed, ResourceLocation texture) {
/* 342 */     buffer.func_181668_a(7, DefaultVertexFormats.field_227851_o_);
/* 343 */     float animU = (float)Util.func_211177_b() % animUSpeed / animUSpeed;
/* 344 */     float animV = (float)Util.func_211177_b() % animVSpeed / animVSpeed;
/* 345 */     float u = animU - 0.0F;
/* 346 */     float v = animV - 1.0F;
/*     */     
/* 348 */     float x = (float)(info.func_216785_c()).field_72450_a;
/* 349 */     float y = (float)(info.func_216785_c()).field_72448_b;
/* 350 */     float z = (float)(info.func_216785_c()).field_72449_c;
/*     */     
/* 352 */     boolean hasYAxis = (sizeY != 0.0F && sizeY < 256.0F);
/*     */     
/* 354 */     float minY = 0.0F - y;
/* 355 */     float maxY = 256.0F - y;
/*     */     
/* 357 */     if (hasYAxis) {
/* 358 */       minY = (float)((-sizeY - y) + posY);
/* 359 */       maxY = (float)((sizeY - y) + posY);
/*     */     } 
/*     */     
/* 362 */     float minX = (float)((-sizeX - x) + posX);
/* 363 */     float maxX = (float)((sizeX + 1.0F - x) + posX);
/* 364 */     float minZ = (float)((-sizeZ - z) + posZ);
/* 365 */     float maxZ = (float)((sizeZ + 1.0F - z) + posZ);
/*     */     
/* 367 */     RenderSystem.enableBlend();
/* 368 */     RenderSystem.enableDepthTest();
/* 369 */     RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
/* 370 */     Minecraft.func_71410_x().func_110434_K().func_110577_a(texture);
/* 371 */     RenderSystem.depthMask(Minecraft.func_238218_y_());
/* 372 */     RenderSystem.pushMatrix();
/*     */     
/* 374 */     RenderSystem.polygonOffset(-3.0F, -3.0F);
/* 375 */     RenderSystem.enablePolygonOffset();
/* 376 */     RenderSystem.defaultAlphaFunc();
/* 377 */     RenderSystem.enableAlphaTest();
/* 378 */     RenderSystem.disableCull();
/*     */     
/* 380 */     buffer.func_225582_a_(minX, minY, minZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(u, v).func_181675_d();
/* 381 */     buffer.func_225582_a_(minX, maxY, minZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(v, v).func_181675_d();
/* 382 */     buffer.func_225582_a_(maxX, maxY, minZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(v, u).func_181675_d();
/* 383 */     buffer.func_225582_a_(maxX, minY, minZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(u, u).func_181675_d();
/*     */     
/* 385 */     buffer.func_225582_a_(minX, minY, maxZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(u, v).func_181675_d();
/* 386 */     buffer.func_225582_a_(minX, maxY, maxZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(v, v).func_181675_d();
/* 387 */     buffer.func_225582_a_(maxX, maxY, maxZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(v, u).func_181675_d();
/* 388 */     buffer.func_225582_a_(maxX, minY, maxZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(u, u).func_181675_d();
/*     */     
/* 390 */     buffer.func_225582_a_(minX, minY, minZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(u, v).func_181675_d();
/* 391 */     buffer.func_225582_a_(minX, maxY, minZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(v, v).func_181675_d();
/* 392 */     buffer.func_225582_a_(minX, maxY, maxZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(v, u).func_181675_d();
/* 393 */     buffer.func_225582_a_(minX, minY, maxZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(u, u).func_181675_d();
/*     */     
/* 395 */     buffer.func_225582_a_(maxX, minY, minZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(u, v).func_181675_d();
/* 396 */     buffer.func_225582_a_(maxX, maxY, minZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(v, v).func_181675_d();
/* 397 */     buffer.func_225582_a_(maxX, maxY, maxZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(v, u).func_181675_d();
/* 398 */     buffer.func_225582_a_(maxX, minY, maxZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(u, u).func_181675_d();
/*     */     
/* 400 */     if (hasYAxis) {
/* 401 */       buffer.func_225582_a_(maxX, minY, minZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(u, v).func_181675_d();
/* 402 */       buffer.func_225582_a_(minX, minY, minZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(v, v).func_181675_d();
/* 403 */       buffer.func_225582_a_(minX, minY, maxZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(v, u).func_181675_d();
/* 404 */       buffer.func_225582_a_(maxX, minY, maxZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(u, u).func_181675_d();
/*     */       
/* 406 */       buffer.func_225582_a_(maxX, maxY, minZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(u, v).func_181675_d();
/* 407 */       buffer.func_225582_a_(minX, maxY, minZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(v, v).func_181675_d();
/* 408 */       buffer.func_225582_a_(minX, maxY, maxZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(v, u).func_181675_d();
/* 409 */       buffer.func_225582_a_(maxX, maxY, maxZ).func_227885_a_(red, green, blue, alpha).func_225583_a_(u, u).func_181675_d();
/*     */     } 
/*     */     
/* 412 */     buffer.func_178977_d();
/* 413 */     WorldVertexBufferUploader.func_181679_a(buffer);
/* 414 */     RenderSystem.enableCull();
/* 415 */     RenderSystem.disableAlphaTest();
/* 416 */     RenderSystem.polygonOffset(0.0F, 0.0F);
/* 417 */     RenderSystem.disablePolygonOffset();
/* 418 */     RenderSystem.enableAlphaTest();
/* 419 */     RenderSystem.disableBlend();
/* 420 */     RenderSystem.popMatrix();
/* 421 */     RenderSystem.depthMask(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawColourOnScreen(int colour, int alpha, double posX, double posY, double width, double height, double zLevel) {
/* 426 */     int r = colour >> 16 & 0xFF;
/* 427 */     int g = colour >> 8 & 0xFF;
/* 428 */     int b = colour & 0xFF;
/* 429 */     drawColourOnScreen(r, g, b, alpha, posX, posY, width, height, zLevel);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawColourOnScreen(int red, int green, int blue, int alpha, double posX, double posY, double width, double height, double zLevel) {
/* 434 */     if (width <= 0.0D || height <= 0.0D)
/*     */       return; 
/* 436 */     RenderSystem.enableBlend();
/* 437 */     RenderSystem.disableTexture();
/* 438 */     BufferBuilder bufferbuilder = Tessellator.func_178181_a().func_178180_c();
/* 439 */     bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 440 */     bufferbuilder.func_225582_a_(posX, posY + height, zLevel).func_225586_a_(red, green, blue, alpha).func_181675_d();
/* 441 */     bufferbuilder.func_225582_a_(posX + width, posY + height, zLevel).func_225586_a_(red, green, blue, alpha).func_181675_d();
/* 442 */     bufferbuilder.func_225582_a_(posX + width, posY, zLevel).func_225586_a_(red, green, blue, alpha).func_181675_d();
/* 443 */     bufferbuilder.func_225582_a_(posX, posY, zLevel).func_225586_a_(red, green, blue, alpha).func_181675_d();
/* 444 */     Tessellator.func_178181_a().func_78381_a();
/* 445 */     RenderSystem.enableTexture();
/* 446 */     RenderSystem.disableBlend();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawAbilityTooltip(IAbility ability, MatrixStack mStack, List<? extends ITextProperties> textLines, int mouseX, int mouseY, int screenWidth, int screenHeight, int maxTextWidth, int backgroundColorStart, int backgroundColorEnd, int borderColorStart, int borderColorEnd, FontRenderer font) {
/* 451 */     ItemStack stack = ItemStack.field_190927_a;
/* 452 */     if (!textLines.isEmpty()) {
/*     */       
/* 454 */       RenderTooltipEvent.Pre event = new RenderTooltipEvent.Pre(stack, textLines, mStack, mouseX, mouseY, screenWidth, screenHeight, maxTextWidth, font);
/* 455 */       if (MinecraftForge.EVENT_BUS.post((Event)event))
/*     */         return; 
/* 457 */       mouseX = event.getX();
/* 458 */       mouseY = event.getY();
/* 459 */       screenWidth = event.getScreenWidth();
/* 460 */       screenHeight = event.getScreenHeight();
/* 461 */       maxTextWidth = event.getMaxWidth();
/* 462 */       font = event.getFontRenderer();
/*     */       
/* 464 */       RenderSystem.disableRescaleNormal();
/* 465 */       RenderSystem.disableDepthTest();
/* 466 */       int tooltipTextWidth = 0;
/*     */       
/* 468 */       for (ITextProperties textLine : textLines) {
/*     */         
/* 470 */         int textLineWidth = font.func_238414_a_(textLine);
/* 471 */         if (textLineWidth > tooltipTextWidth) {
/* 472 */           tooltipTextWidth = textLineWidth;
/*     */         }
/*     */       } 
/* 475 */       int titleLinesCount = 1;
/* 476 */       int tooltipX = mouseX + 12;
/* 477 */       if (tooltipX + tooltipTextWidth + 4 > screenWidth) {
/*     */         
/* 479 */         tooltipX = mouseX - 16 - tooltipTextWidth;
/* 480 */         if (tooltipX < 4)
/*     */         {
/* 482 */           if (mouseX > screenWidth / 2) {
/* 483 */             tooltipTextWidth = mouseX - 12 - 8;
/*     */           } else {
/* 485 */             tooltipTextWidth = screenWidth - 16 - mouseX;
/*     */           } 
/*     */         }
/*     */       } 
/* 489 */       if (maxTextWidth > 0 && tooltipTextWidth > maxTextWidth)
/*     */       {
/* 491 */         tooltipTextWidth = maxTextWidth;
/*     */       }
/*     */       
/* 494 */       int wrappedTooltipWidth = 0;
/* 495 */       List<ITextProperties> wrappedTextLines = new ArrayList<>();
/* 496 */       for (int i = 0; i < textLines.size(); i++) {
/* 497 */         ITextProperties textLine = textLines.get(i);
/* 498 */         List<ITextProperties> wrappedLine = font.func_238420_b_().func_238362_b_(textLine, tooltipTextWidth, Style.field_240709_b_);
/* 499 */         if (i == 0) {
/* 500 */           titleLinesCount = wrappedLine.size();
/*     */         }
/*     */         
/* 503 */         for (ITextProperties line : wrappedLine) {
/* 504 */           int lineWidth = font.func_238414_a_(line);
/* 505 */           if (lineWidth > wrappedTooltipWidth) {
/* 506 */             wrappedTooltipWidth = lineWidth;
/*     */           }
/* 508 */           wrappedTextLines.add(line);
/*     */         } 
/*     */       } 
/* 511 */       tooltipTextWidth = wrappedTooltipWidth;
/* 512 */       textLines = wrappedTextLines;
/*     */       
/* 514 */       if (mouseX > screenWidth / 2) {
/* 515 */         tooltipX = mouseX - 16 - tooltipTextWidth;
/*     */       } else {
/* 517 */         tooltipX = mouseX + 12;
/*     */       } 
/* 519 */       int tooltipY = mouseY - 12;
/* 520 */       int tooltipHeight = 8;
/*     */       
/* 522 */       if (textLines.size() > 1) {
/*     */         
/* 524 */         tooltipHeight += (textLines.size() - 1) * 10;
/* 525 */         if (textLines.size() > titleLinesCount) {
/* 526 */           tooltipHeight += 2;
/*     */         }
/*     */       } 
/* 529 */       if (tooltipY < 4) {
/* 530 */         tooltipY = 4;
/* 531 */       } else if (tooltipY + tooltipHeight + 4 > screenHeight) {
/* 532 */         tooltipY = screenHeight - tooltipHeight - 4;
/*     */       } 
/* 534 */       int zLevel = 400;
/*     */       
/* 536 */       mStack.func_227860_a_();
/* 537 */       Matrix4f mat = mStack.func_227866_c_().func_227870_a_();
/* 538 */       GuiUtils.drawGradientRect(mat, 400, tooltipX - 3, tooltipY - 3, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3, backgroundColorStart, backgroundColorEnd);
/*     */       
/* 540 */       GuiUtils.drawGradientRect(mat, 400, tooltipX - 3, tooltipY - 4, tooltipX + tooltipTextWidth + 3, tooltipY - 3, borderColorStart, borderColorStart);
/* 541 */       GuiUtils.drawGradientRect(mat, 400, tooltipX + tooltipTextWidth + 3, tooltipY - 3, tooltipX + tooltipTextWidth + 4, tooltipY + tooltipHeight + 3, borderColorStart, borderColorEnd);
/* 542 */       GuiUtils.drawGradientRect(mat, 400, tooltipX - 4, tooltipY - 3, tooltipX - 3, tooltipY + tooltipHeight + 3, borderColorStart, borderColorEnd);
/* 543 */       GuiUtils.drawGradientRect(mat, 400, tooltipX - 3, tooltipY + tooltipHeight + 3, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 4, borderColorEnd, borderColorEnd);
/*     */       
/* 545 */       GuiUtils.drawGradientRect(mat, 400, tooltipX - 3, tooltipY - 3, tooltipX + tooltipTextWidth + 3, tooltipY - 3 + 1, borderColorStart, borderColorStart);
/* 546 */       GuiUtils.drawGradientRect(mat, 400, tooltipX + tooltipTextWidth + 2, tooltipY - 3 + 1, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3 - 1, borderColorStart, borderColorEnd);
/* 547 */       GuiUtils.drawGradientRect(mat, 400, tooltipX - 3, tooltipY - 3 + 1, tooltipX - 3 + 1, tooltipY + tooltipHeight + 3 - 1, borderColorStart, borderColorEnd);
/* 548 */       GuiUtils.drawGradientRect(mat, 400, tooltipX - 3, tooltipY + tooltipHeight + 2, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3, borderColorEnd, borderColorEnd);
/*     */       
/* 550 */       String abilityName = ability.getDisplayName().getString();
/* 551 */       int nameWidth = font.func_78256_a(abilityName);
/* 552 */       Color iconColor = WyHelper.hexToRGB("#333333");
/*     */       
/* 554 */       Set<ResourceLocation> coloredIcons = new HashSet<>();
/* 555 */       Set<ResourceLocation> staticIcons = new HashSet<>();
/*     */       
/* 557 */       if (ability.getCore().getSourceHakiNature() != null && 
/* 558 */         ability.getCore().getSourceHakiNature().getTexture() != null) {
/* 559 */         coloredIcons.add(ability.getCore().getSourceHakiNature().getTexture());
/*     */       }
/*     */ 
/*     */       
/* 563 */       if (ability.getCore().getSourceTypes() != null)
/*     */       {
/* 565 */         for (SourceType type : ability.getCore().getSourceTypes()) {
/* 566 */           if (type.getTexture() != null) {
/* 567 */             coloredIcons.add(type.getTexture());
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/* 572 */       if (ability.getCore().getSourceElement() != null && 
/* 573 */         ability.getCore().getSourceElement().getTexture() != null) {
/* 574 */         staticIcons.add(ability.getCore().getSourceElement().getTexture());
/*     */       }
/*     */ 
/*     */       
/* 578 */       int spacing = 4;
/* 579 */       for (ResourceLocation icon : coloredIcons) {
/* 580 */         spacing += 12;
/* 581 */         drawIcon(icon, event.getMatrixStack(), (tooltipX + nameWidth + spacing - 12), tooltipY, 500.0F, 10.0F, 10.0F, iconColor.getRed() / 255.0F, iconColor.getGreen() / 255.0F, iconColor.getBlue() / 255.0F, 1.0F);
/*     */       } 
/* 583 */       for (ResourceLocation icon : staticIcons) {
/* 584 */         spacing += 12;
/* 585 */         drawIcon(icon, event.getMatrixStack(), (tooltipX + nameWidth + spacing - 12), tooltipY, 500.0F, 10.0F, 10.0F, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */       } 
/*     */       
/* 588 */       MinecraftForge.EVENT_BUS.post((Event)new RenderTooltipEvent.PostBackground(stack, textLines, mStack, tooltipX, tooltipY, font, tooltipTextWidth, tooltipHeight));
/*     */       
/* 590 */       IRenderTypeBuffer.Impl renderType = IRenderTypeBuffer.func_228455_a_(Tessellator.func_178181_a().func_178180_c());
/* 591 */       mStack.func_227861_a_(0.0D, 0.0D, 400.0D);
/*     */       
/* 593 */       int tooltipTop = tooltipY;
/*     */       
/* 595 */       for (int lineNumber = 0; lineNumber < textLines.size(); lineNumber++) {
/*     */         
/* 597 */         ITextProperties line = textLines.get(lineNumber);
/* 598 */         if (line != null) {
/* 599 */           font.func_238416_a_(LanguageMap.func_74808_a().func_241870_a(line), tooltipX, tooltipY, -1, true, mat, (IRenderTypeBuffer)renderType, false, 0, 15728880);
/*     */         }
/*     */         
/* 602 */         if (lineNumber + 1 == titleLinesCount) {
/* 603 */           tooltipY += 2;
/*     */         }
/*     */         
/* 606 */         tooltipY += 10;
/*     */       } 
/*     */       
/* 609 */       renderType.func_228461_a_();
/* 610 */       mStack.func_227865_b_();
/*     */       
/* 612 */       MinecraftForge.EVENT_BUS.post((Event)new RenderTooltipEvent.PostText(stack, textLines, mStack, tooltipX, tooltipTop, font, tooltipTextWidth, tooltipHeight));
/*     */       
/* 614 */       RenderSystem.enableDepthTest();
/* 615 */       RenderSystem.enableRescaleNormal();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawAbilityIcon(AbilityCore core, MatrixStack matrixStack, float x, float y, int z, float u, float v) {
/* 621 */     drawAbilityIcon(core, matrixStack, x, y, z, u, v, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawAbilityIcon(AbilityCore core, MatrixStack matrixStack, float x, float y, float z, float u, float v, float red, float green, float blue) {
/* 626 */     drawIcon(core.getIcon(), matrixStack, x, y, z, u, v, red, green, blue, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawIcon(ResourceLocation rs, MatrixStack matrixStack, float x, float y, float z, float u, float v) {
/* 631 */     drawIcon(rs, matrixStack, x, y, z, u, v, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawIcon(ResourceLocation rs, MatrixStack matrixStack, float x, float y, float z, float u, float v, int intColor) {
/* 636 */     Color color = new Color(intColor);
/* 637 */     drawIcon(rs, matrixStack, x, y, z, u, v, color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawIcon(ResourceLocation rs, MatrixStack matrixStack, float x, float y, float z, float u, float v, float red, float green, float blue, float alpha) {
/* 642 */     drawIcon(rs, matrixStack, x, y, z, u, v, red, green, blue, alpha, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawIcon(ResourceLocation rs, MatrixStack matrixStack, float x, float y, float z, float u, float v, float red, float green, float blue, float alpha, boolean flip) {
/* 647 */     float uvDir = flip ? -1.0F : 1.0F;
/* 648 */     Matrix4f matrix = matrixStack.func_227866_c_().func_227870_a_();
/* 649 */     RenderSystem.enableAlphaTest();
/* 650 */     RenderSystem.enableBlend();
/* 651 */     Minecraft.func_71410_x().func_110434_K().func_110577_a(rs);
/* 652 */     BufferBuilder bufferbuilder = Tessellator.func_178181_a().func_178180_c();
/* 653 */     bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_227851_o_);
/* 654 */     bufferbuilder.func_227888_a_(matrix, x, y + v, z).func_227885_a_(red, green, blue, alpha).func_225583_a_(0.0F, uvDir).func_181675_d();
/* 655 */     bufferbuilder.func_227888_a_(matrix, x + u, y + v, z).func_227885_a_(red, green, blue, alpha).func_225583_a_(uvDir, uvDir).func_181675_d();
/* 656 */     bufferbuilder.func_227888_a_(matrix, x + u, y, z).func_227885_a_(red, green, blue, alpha).func_225583_a_(uvDir, 0.0F).func_181675_d();
/* 657 */     bufferbuilder.func_227888_a_(matrix, x, y, z).func_227885_a_(red, green, blue, alpha).func_225583_a_(0.0F, 0.0F).func_181675_d();
/* 658 */     Tessellator.func_178181_a().func_78381_a();
/* 659 */     RenderSystem.disableBlend();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawIcon(ResourceLocation rs, MatrixStack matrixStack, float x, float y, float z, float u, float v, float red, float green, float blue, float alpha, boolean flipX, boolean flipY) {
/* 664 */     Matrix4f matrix = matrixStack.func_227866_c_().func_227870_a_();
/* 665 */     RenderSystem.enableAlphaTest();
/* 666 */     RenderSystem.enableBlend();
/* 667 */     Minecraft.func_71410_x().func_110434_K().func_110577_a(rs);
/* 668 */     BufferBuilder bufferbuilder = Tessellator.func_178181_a().func_178180_c();
/* 669 */     bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_227851_o_);
/* 670 */     if (!flipX) {
/* 671 */       bufferbuilder.func_227888_a_(matrix, x, y + v, z).func_227885_a_(red, green, blue, alpha).func_225583_a_(0.0F, 1.0F).func_181675_d();
/* 672 */       bufferbuilder.func_227888_a_(matrix, x + u, y + v, z).func_227885_a_(red, green, blue, alpha).func_225583_a_(1.0F, 1.0F).func_181675_d();
/* 673 */       bufferbuilder.func_227888_a_(matrix, x + u, y, z).func_227885_a_(red, green, blue, alpha).func_225583_a_(1.0F, 0.0F).func_181675_d();
/* 674 */       bufferbuilder.func_227888_a_(matrix, x, y, z).func_227885_a_(red, green, blue, alpha).func_225583_a_(0.0F, 0.0F).func_181675_d();
/*     */     } else {
/*     */       
/* 677 */       bufferbuilder.func_227888_a_(matrix, x, y + v, z).func_227885_a_(red, green, blue, alpha).func_225583_a_(0.0F, 0.0F).func_181675_d();
/* 678 */       bufferbuilder.func_227888_a_(matrix, x + u, y + v, z).func_227885_a_(red, green, blue, alpha).func_225583_a_(-1.0F, 0.0F).func_181675_d();
/* 679 */       bufferbuilder.func_227888_a_(matrix, x + u, y, z).func_227885_a_(red, green, blue, alpha).func_225583_a_(-1.0F, -1.0F).func_181675_d();
/* 680 */       bufferbuilder.func_227888_a_(matrix, x, y, z).func_227885_a_(red, green, blue, alpha).func_225583_a_(0.0F, -1.0F).func_181675_d();
/*     */     } 
/* 682 */     Tessellator.func_178181_a().func_78381_a();
/* 683 */     RenderSystem.disableBlend();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawQuad(MatrixStack matrixStack, IVertexBuilder buffer, float red, float green, float blue, float alpha, float x, float y, float width, float height, int lightmap) {
/* 688 */     drawQuad(matrixStack, buffer, red, green, blue, alpha, x, y, width, height, 0.0F, 1.0F, lightmap);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawQuad(MatrixStack matrixStack, IVertexBuilder buffer, float red, float green, float blue, float alpha, float x, float y, float width, float height, float u, float v, int lightmap) {
/* 693 */     drawQuad(matrixStack, buffer, red, green, blue, alpha, x, y, width, height, u, v, u, v, lightmap);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawQuad(MatrixStack matrixStack, IVertexBuilder buffer, float red, float green, float blue, float alpha, float x, float y, float width, float height, float u1, float v1, float u2, float v2, int lightmap) {
/* 698 */     MatrixStack.Entry entry = matrixStack.func_227866_c_();
/* 699 */     Matrix4f matrix4f = entry.func_227870_a_();
/* 700 */     Matrix3f matrix3f = entry.func_227872_b_();
/*     */     
/* 702 */     drawVertex(matrix4f, matrix3f, buffer, red, green, blue, alpha, x, y + height, u1, v2, lightmap);
/* 703 */     drawVertex(matrix4f, matrix3f, buffer, red, green, blue, alpha, x + width, y + height, v1, v2, lightmap);
/* 704 */     drawVertex(matrix4f, matrix3f, buffer, red, green, blue, alpha, x + width, y, v1, u2, lightmap);
/* 705 */     drawVertex(matrix4f, matrix3f, buffer, red, green, blue, alpha, x, y, u1, u2, lightmap);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawVertex(Matrix4f matrixPos, Matrix3f matrixNormal, IVertexBuilder bufferIn, float red, float green, float blue, float alpha, float x, float y, float texU, float texV, int lightmap) {
/* 710 */     bufferIn.func_227888_a_(matrixPos, x, y, 0.0F).func_227885_a_(red, green, blue, alpha).func_225583_a_(texU, texV).func_227891_b_(OverlayTexture.field_229196_a_).func_227886_a_(lightmap).func_227887_a_(matrixNormal, 0.0F, 1.0F, 0.0F).func_181675_d();
/*     */   }
/*     */   
/*     */   public static void drawPlayerFactionIcon(ResourceLocation faction, @Nullable Crew crew, @Nullable LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int lightmap) {
/* 714 */     drawPlayerFactionIcon(faction, crew, entity, matrixStack, buffer, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F, lightmap);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawPlayerFactionIcon(ResourceLocation faction, @Nullable Crew crew, @Nullable LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, float x, float y, float width, float height, float u1, float v1, float u2, float v2, int lightmap) {
/* 719 */     if (faction.equals(ModValues.PIRATE) && crew != null) {
/* 720 */       drawPlayerJollyRoger(crew.getJollyRoger(), matrixStack, buffer, lightmap);
/*     */     }
/* 722 */     else if (faction.equals(ModValues.REVOLUTIONARY)) {
/* 723 */       matrixStack.func_227861_a_(-0.4D, -0.42D, 0.0D);
/* 724 */       matrixStack.func_227862_a_(4.0F, 4.0F, 1.0F);
/* 725 */       IVertexBuilder ivertexbuilder = buffer.getBuffer(ModRenderTypes.getWantedPoster(ModResources.REVOLUTIONARY_ARMY_ICON_GREYSCALE));
/* 726 */       drawQuad(matrixStack, ivertexbuilder, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.5F, 0.5F, lightmap);
/*     */     }
/* 728 */     else if (faction.equals(ModValues.MARINE)) {
/* 729 */       matrixStack.func_227861_a_(-0.5D, -0.5D, 0.0D);
/* 730 */       matrixStack.func_227862_a_(4.0F, 4.0F, 1.0F);
/* 731 */       IVertexBuilder ivertexbuilder = buffer.getBuffer(ModRenderTypes.getWantedPoster(ModResources.MARINE_ICON));
/* 732 */       drawQuad(matrixStack, ivertexbuilder, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.5F, 0.5F, lightmap);
/*     */     }
/* 734 */     else if (faction.equals(ModValues.WORLD_GOVT)) {
/* 735 */       matrixStack.func_227861_a_(-0.4D, -0.42D, 0.0D);
/* 736 */       matrixStack.func_227862_a_(4.0F, 4.0F, 1.0F);
/* 737 */       IVertexBuilder ivertexbuilder = buffer.getBuffer(ModRenderTypes.getWantedPoster(ModResources.WORLD_GOV_ICON));
/* 738 */       drawQuad(matrixStack, ivertexbuilder, WORLD_GOVT_COLOR.getRed() / 255.0F, WORLD_GOVT_COLOR.getGreen() / 255.0F, WORLD_GOVT_COLOR.getBlue() / 255.0F, 1.0F, 0.0F, 0.0F, 0.5F, 0.5F, lightmap);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawPlayerJollyRoger(JollyRoger jollyRoger, MatrixStack matrixStack, IRenderTypeBuffer buffer, int lightmap) {
/* 744 */     drawPlayerJollyRoger(jollyRoger, matrixStack, buffer, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F, lightmap);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawPlayerJollyRoger(JollyRoger jollyRoger, MatrixStack matrixStack, IRenderTypeBuffer buffer, float x, float y, float width, float height, float u1, float v1, float u2, float v2, int lightmap) {
/* 751 */     for (JollyRogerElement element : jollyRoger.getBackgrounds()) {
/*     */       
/* 753 */       if (element != null && element.getTexture() != null) {
/*     */         
/* 755 */         float red = 1.0F, green = 1.0F, blue = 1.0F, alpha = 1.0F;
/* 756 */         if (element.canBeColored()) {
/*     */           
/* 758 */           red = element.getRed() / 255.0F;
/* 759 */           green = element.getGreen() / 255.0F;
/* 760 */           blue = element.getBlue() / 255.0F;
/*     */         } 
/* 762 */         drawQuad(matrixStack, buffer.getBuffer(ModRenderTypes.getWantedPoster(element.getTexture())), red, green, blue, alpha, x, y, width, height, u1, v1, u2, v2, lightmap);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 767 */     if (jollyRoger.getBase() != null && jollyRoger.getBase().getTexture() != null) {
/*     */       
/* 769 */       float red = 1.0F, green = 1.0F, blue = 1.0F, alpha = 1.0F;
/* 770 */       if (jollyRoger.getBase().canBeColored()) {
/*     */         
/* 772 */         red = jollyRoger.getBase().getRed() / 255.0F;
/* 773 */         green = jollyRoger.getBase().getGreen() / 255.0F;
/* 774 */         blue = jollyRoger.getBase().getBlue() / 255.0F;
/*     */       } 
/* 776 */       drawQuad(matrixStack, buffer.getBuffer(ModRenderTypes.getWantedPoster(jollyRoger.getBase().getTexture())), red, green, blue, alpha, x, y, width, height, u1, v1, u2, v2, lightmap);
/*     */     } 
/*     */ 
/*     */     
/* 780 */     for (JollyRogerElement element : jollyRoger.getDetails()) {
/*     */       
/* 782 */       if (element != null && element.getTexture() != null) {
/*     */         
/* 784 */         float red = 1.0F, green = 1.0F, blue = 1.0F, alpha = 1.0F;
/* 785 */         if (element.canBeColored()) {
/*     */           
/* 787 */           red = element.getRed() / 255.0F;
/* 788 */           green = element.getGreen() / 255.0F;
/* 789 */           blue = element.getBlue() / 255.0F;
/*     */         } 
/* 791 */         drawQuad(matrixStack, buffer.getBuffer(ModRenderTypes.getWantedPoster(element.getTexture())), red, green, blue, alpha, x, y, width, height, u1, v1, u2, v2, lightmap);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawPlayerJollyRoger(JollyRoger jollyRoger, MatrixStack matrixStack) {
/* 800 */     for (JollyRogerElement element : jollyRoger.getBackgrounds()) {
/*     */       
/* 802 */       int i = 0;
/* 803 */       if (element != null && element.getTexture() != null) {
/*     */         
/* 805 */         if (element.canBeColored()) {
/*     */           
/* 807 */           RenderSystem.color3f(element.getRed() / 255.0F, element.getGreen() / 255.0F, element.getBlue() / 255.0F);
/*     */         }
/*     */         else {
/*     */           
/* 811 */           RenderSystem.color3f(1.0F, 1.0F, 1.0F);
/*     */         } 
/* 813 */         Minecraft.func_71410_x().func_110434_K().func_110577_a(element.getTexture());
/* 814 */         GuiUtils.drawTexturedModalRect(matrixStack, 0, 0, 0, 0, 256, 256, i);
/*     */       } 
/* 816 */       i--;
/*     */     } 
/*     */ 
/*     */     
/* 820 */     if (jollyRoger.getBase() != null && jollyRoger.getBase().getTexture() != null) {
/*     */       
/* 822 */       if (jollyRoger.getBase().canBeColored()) {
/*     */         
/* 824 */         RenderSystem.color3f(jollyRoger.getBase().getRed() / 255.0F, jollyRoger.getBase().getGreen() / 255.0F, jollyRoger.getBase().getBlue() / 255.0F);
/*     */       }
/*     */       else {
/*     */         
/* 828 */         RenderSystem.color3f(1.0F, 1.0F, 1.0F);
/*     */       } 
/* 830 */       Minecraft.func_71410_x().func_110434_K().func_110577_a(jollyRoger.getBase().getTexture());
/* 831 */       GuiUtils.drawTexturedModalRect(matrixStack, 0, 0, 0, 0, 256, 256, 0.0F);
/*     */     } 
/*     */ 
/*     */     
/* 835 */     for (JollyRogerElement element : jollyRoger.getDetails()) {
/*     */       
/* 837 */       int i = 0;
/* 838 */       if (element != null && element.getTexture() != null) {
/*     */         
/* 840 */         if (element.canBeColored()) {
/*     */           
/* 842 */           RenderSystem.color3f(element.getRed() / 255.0F, element.getGreen() / 255.0F, element.getBlue() / 255.0F);
/*     */         }
/*     */         else {
/*     */           
/* 846 */           RenderSystem.color3f(1.0F, 1.0F, 1.0F);
/*     */         } 
/* 848 */         Minecraft.func_71410_x().func_110434_K().func_110577_a(element.getTexture());
/* 849 */         GuiUtils.drawTexturedModalRect(matrixStack, 0, 0, 0, 0, 256, 256, i);
/*     */       } 
/* 851 */       i++;
/*     */     } 
/*     */     
/* 854 */     RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/* 857 */   private static final float distance = (float)(Math.sqrt(3.0D) / 2.0D);
/*     */ 
/*     */   
/*     */   public static void drawA(IVertexBuilder vertexBuilder, Matrix4f matrix4f, Color color) {
/* 861 */     vertexBuilder.func_227888_a_(matrix4f, 0.0F, 0.0F, 0.0F).func_225586_a_(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
/* 862 */     vertexBuilder.func_227888_a_(matrix4f, 0.0F, 0.0F, 0.0F).func_225586_a_(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawB(IVertexBuilder vertexBuilder, Matrix4f matrix4f, float y, float xz, Color color) {
/* 867 */     vertexBuilder.func_227888_a_(matrix4f, -distance * xz, y, -0.5F * xz).func_225586_a_(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawC(IVertexBuilder vertexBuilder, Matrix4f matrix4f, float y, float xz, Color color) {
/* 872 */     vertexBuilder.func_227888_a_(matrix4f, distance * xz, y, -0.5F * xz).func_225586_a_(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawD(IVertexBuilder vertexBuilder, Matrix4f matrix4f, float y, float z, Color color) {
/* 877 */     vertexBuilder.func_227888_a_(matrix4f, 0.0F, y, z).func_225586_a_(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).func_181675_d();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderVignette(Entity entity, float intensity, double width, double height) {
/* 882 */     RenderSystem.disableDepthTest();
/* 883 */     RenderSystem.depthMask(false);
/* 884 */     RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
/*     */     
/* 886 */     float red = intensity;
/* 887 */     float green = intensity;
/* 888 */     float blue = intensity;
/* 889 */     if (intensity > 0.8F) {
/*     */       
/* 891 */       red = 0.0F;
/* 892 */       green = 1.0F;
/* 893 */       blue = 1.0F;
/*     */     } 
/*     */     
/* 896 */     RenderSystem.color4f(red, green, blue, 1.0F);
/*     */     
/* 898 */     Minecraft.func_71410_x().func_110434_K().func_110577_a(VANILLA_VIGNETTE_TEX_PATH);
/* 899 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 900 */     BufferBuilder bufferbuilder = tessellator.func_178180_c();
/* 901 */     bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
/* 902 */     bufferbuilder.func_225582_a_(0.0D, height, -90.0D).func_225583_a_(0.0F, 1.0F).func_181675_d();
/* 903 */     bufferbuilder.func_225582_a_(width, height, -90.0D).func_225583_a_(1.0F, 1.0F).func_181675_d();
/* 904 */     bufferbuilder.func_225582_a_(width, 0.0D, -90.0D).func_225583_a_(1.0F, 0.0F).func_181675_d();
/* 905 */     bufferbuilder.func_225582_a_(0.0D, 0.0D, -90.0D).func_225583_a_(0.0F, 0.0F).func_181675_d();
/* 906 */     tessellator.func_78381_a();
/* 907 */     RenderSystem.depthMask(true);
/* 908 */     RenderSystem.enableDepthTest();
/* 909 */     RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 910 */     RenderSystem.defaultBlendFunc();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\helpers\RendererHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */