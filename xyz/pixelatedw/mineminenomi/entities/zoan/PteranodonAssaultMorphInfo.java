/*    */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.util.Map;
/*    */ import net.minecraft.entity.EntitySize;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.Pose;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.ryupteranodon.PteranodonAssaultPointAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.morphs.PteranodonAssaultPartialMorphRenderer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PteranodonAssaultMorphInfo
/*    */   extends MorphInfo
/*    */ {
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public IRenderFactory getRendererFactory() {
/* 27 */     return (IRenderFactory)new PteranodonAssaultPartialMorphRenderer.Factory(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTexture(LivingEntity entity) {
/* 33 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public MorphModel getModel() {
/* 40 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public boolean shouldRenderFirstPersonHand() {
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public void preRenderCallback(LivingEntity entity, MatrixStack matrixStack, float partialTickTime) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public AkumaNoMiItem getDevilFruit() {
/* 59 */     return ModAbilities.RYU_RYU_NO_MI_PTERANODON;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getForm() {
/* 65 */     return "pteranodon_assault";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDisplayName() {
/* 71 */     return PteranodonAssaultPointAbility.INSTANCE.getUnlocalizedName();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public double getEyeHeight() {
/* 77 */     return 1.75D;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getShadowSize() {
/* 83 */     return 0.5F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Pose, EntitySize> getSizes() {
/* 89 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\PteranodonAssaultMorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */