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
/*     */ import xyz.pixelatedw.mineminenomi.abilities.toriphoenix.PhoenixFlyPointAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.models.morphs.ChickenPhoenixModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.morphs.PhoenixFlyModel;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.morphs.FlyPointRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PhoenixFlyMorphInfo
/*     */   extends MorphInfo
/*     */ {
/*  30 */   private static final EntitySize STANDING_SIZE = EntitySize.func_220314_b(1.2F, 1.2F);
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public IRenderFactory getRendererFactory() {
/*  36 */     if (WyHelper.isAprilFirst())
/*  37 */       return super.getRendererFactory(); 
/*  38 */     return (IRenderFactory)new FlyPointRenderer.Factory(this, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public MorphModel getModel() {
/*  45 */     if (WyHelper.isAprilFirst())
/*  46 */       return (MorphModel)new ChickenPhoenixModel(); 
/*  47 */     return (MorphModel)new PhoenixFlyModel();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ResourceLocation getTexture(LivingEntity entity) {
/*  54 */     if (WyHelper.isAprilFirst()) {
/*  55 */       return new ResourceLocation("mineminenomi", "textures/models/zoanmorph/phoenix_chicken.png");
/*     */     }
/*  57 */     return super.getTexture(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void preRenderCallback(LivingEntity entity, MatrixStack matrixStack, float partialTickTime) {
/*  64 */     if (WyHelper.isAprilFirst()) {
/*     */       
/*  66 */       float f = 1.8F;
/*  67 */       matrixStack.func_227862_a_(f, f, f);
/*  68 */       matrixStack.func_227861_a_(0.0D, 0.3499999940395355D, 0.0D);
/*     */       return;
/*     */     } 
/*  71 */     float scale = 1.8F;
/*  72 */     matrixStack.func_227862_a_(scale, scale, scale);
/*  73 */     matrixStack.func_227861_a_(0.0D, 1.0D, 0.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AkumaNoMiItem getDevilFruit() {
/*  79 */     return ModAbilities.TORI_TORI_NO_MI_PHOENIX;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getForm() {
/*  85 */     return "phoenix_fly";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDisplayName() {
/*  91 */     return PhoenixFlyPointAbility.INSTANCE.getUnlocalizedName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getEyeHeight() {
/*  97 */     return 0.8D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/* 103 */     return 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canMount() {
/* 109 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean hasEqualDepthTest() {
/* 116 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Pose, EntitySize> getSizes() {
/* 122 */     return (Map<Pose, EntitySize>)ImmutableMap.builder()
/* 123 */       .put(Pose.STANDING, STANDING_SIZE)
/* 124 */       .build();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\PhoenixFlyMorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */