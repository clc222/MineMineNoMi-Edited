/*    */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.util.Map;
/*    */ import net.minecraft.entity.EntitySize;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.Pose;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.hitodaibutsu.HitoDaibutsuPointAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ import xyz.pixelatedw.mineminenomi.models.morphs.HitoDaibutsuModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.morphs.GlowingModelRenderer;
/*    */ 
/*    */ 
/*    */ public class HitoDaibutsuMorphInfo
/*    */   extends MorphInfo
/*    */ {
/* 24 */   private static final EntitySize STANDING_SIZE = EntitySize.func_220314_b(6.2F, 13.0F);
/* 25 */   private static final EntitySize CROUCHING_SIZE = EntitySize.func_220314_b(6.2F, 12.8F);
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public IRenderFactory getRendererFactory() {
/* 31 */     return (IRenderFactory)new GlowingModelRenderer.Factory(this, GlowingModelRenderer.Type.DAIBUTSU);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public MorphModel getModel() {
/* 38 */     return (MorphModel)new HitoDaibutsuModel();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public void preRenderCallback(LivingEntity entity, MatrixStack matrixStack, float partialTickTime) {
/* 45 */     matrixStack.func_227861_a_(0.0D, -2.4D, 0.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AkumaNoMiItem getDevilFruit() {
/* 51 */     return ModAbilities.HITO_HITO_NO_MI_DAIBUTSU;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getForm() {
/* 57 */     return "daibutsu";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDisplayName() {
/* 63 */     return HitoDaibutsuPointAbility.INSTANCE.getUnlocalizedName();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public double getEyeHeight() {
/* 69 */     return 12.800000190734863D;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getShadowSize() {
/* 75 */     return 3.5F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public double getCameraZoom(LivingEntity entity) {
/* 82 */     return 12.0D;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canMount() {
/* 88 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Pose, EntitySize> getSizes() {
/* 94 */     return (Map<Pose, EntitySize>)ImmutableMap.builder()
/* 95 */       .put(Pose.STANDING, STANDING_SIZE)
/* 96 */       .put(Pose.CROUCHING, CROUCHING_SIZE)
/* 97 */       .build();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\HitoDaibutsuMorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */