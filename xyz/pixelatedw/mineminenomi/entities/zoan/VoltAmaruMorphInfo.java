/*    */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.util.Map;
/*    */ import net.minecraft.entity.EntitySize;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.Pose;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.goro.VoltAmaruAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.morphs.HitoDaibutsuModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.morphs.GlowingModelRenderer;
/*    */ 
/*    */ 
/*    */ public class VoltAmaruMorphInfo
/*    */   extends MorphInfo
/*    */ {
/* 23 */   private static final EntitySize STANDING_SIZE = EntitySize.func_220314_b(2.4F, 6.5F);
/* 24 */   private static final EntitySize CROUCHING_SIZE = EntitySize.func_220314_b(2.4F, 6.2F);
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public IRenderFactory getRendererFactory() {
/* 30 */     return (IRenderFactory)new GlowingModelRenderer.Factory(this, GlowingModelRenderer.Type.AMARU);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public MorphModel getModel() {
/* 37 */     return (MorphModel)new HitoDaibutsuModel();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public void preRenderCallback(LivingEntity entity, MatrixStack matrixStack, float partialTickTime) {
/* 46 */     matrixStack.func_227862_a_(0.5F, 0.55F, 0.5F);
/* 47 */     matrixStack.func_227861_a_(0.0D, -1.5D, 0.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTexture(LivingEntity entity) {
/* 53 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getForm() {
/* 59 */     return "volt_amaru";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDisplayName() {
/* 65 */     return VoltAmaruAbility.INSTANCE.getUnlocalizedName();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public double getEyeHeight() {
/* 71 */     return 6.300000190734863D;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getShadowSize() {
/* 77 */     return 1.9F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public double getCameraZoom(LivingEntity entity) {
/* 84 */     return 12.0D;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canMount() {
/* 90 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Pose, EntitySize> getSizes() {
/* 96 */     return (Map<Pose, EntitySize>)ImmutableMap.builder()
/* 97 */       .put(Pose.STANDING, STANDING_SIZE)
/* 98 */       .put(Pose.CROUCHING, CROUCHING_SIZE)
/* 99 */       .build();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\VoltAmaruMorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */