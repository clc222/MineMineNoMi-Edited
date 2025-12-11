/*     */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.gasu.ShinokuniAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.morphs.ShinokuniModel;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.morphs.ShinokuniRenderer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ShinokuniMorphInfo
/*     */   extends MorphInfo
/*     */ {
/*  26 */   private static final EntitySize STANDING_SIZE = EntitySize.func_220314_b(2.8F, 6.0F);
/*  27 */   private static final EntitySize CROUCHING_SIZE = EntitySize.func_220314_b(2.8F, 5.9F);
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public IRenderFactory getRendererFactory() {
/*  33 */     return (IRenderFactory)new ShinokuniRenderer.Factory(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public MorphModel getModel() {
/*  40 */     return (MorphModel)new ShinokuniModel();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void preRenderCallback(LivingEntity entity, MatrixStack matrixStack, float partialTickTime) {
/*  47 */     matrixStack.func_227862_a_(3.0F, 3.0F, 3.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ResourceLocation getTexture() {
/*  54 */     return new ResourceLocation("mineminenomi", "textures/models/zoanmorph/shinokuni.png");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getForm() {
/*  60 */     return "shinokuni";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDisplayName() {
/*  66 */     return ShinokuniAbility.INSTANCE.getUnlocalizedName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getEyeHeight() {
/*  72 */     return 5.699999809265137D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/*  78 */     return 1.2F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public double getCameraZoom(LivingEntity entity) {
/*  85 */     return 7.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canMount() {
/*  91 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Pose, EntitySize> getSizes() {
/*  97 */     return (Map<Pose, EntitySize>)ImmutableMap.builder()
/*  98 */       .put(Pose.STANDING, STANDING_SIZE)
/*  99 */       .put(Pose.CROUCHING, CROUCHING_SIZE)
/* 100 */       .build();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\ShinokuniMorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */