/*    */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.util.Map;
/*    */ import net.minecraft.entity.EntitySize;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.Pose;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.doru.CandleChampionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ import xyz.pixelatedw.mineminenomi.models.morphs.CandleChampionModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.morphs.CandleChampionRenderer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CandleChampionMorphInfo
/*    */   extends MorphInfo
/*    */ {
/* 27 */   private static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/candle_lock.png");
/*    */   
/* 29 */   private static final EntitySize STANDING_SIZE = EntitySize.func_220314_b(2.8F, 4.0F);
/* 30 */   private static final EntitySize CROUCHING_SIZE = EntitySize.func_220314_b(2.8F, 3.9F);
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public IRenderFactory getRendererFactory() {
/* 36 */     return (IRenderFactory)new CandleChampionRenderer.Factory(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public MorphModel getModel() {
/* 43 */     return (MorphModel)new CandleChampionModel();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public void preRenderCallback(LivingEntity entity, MatrixStack matrixStack, float partialTickTime) {
/* 51 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTexture() {
/* 57 */     return TEXTURE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AkumaNoMiItem getDevilFruit() {
/* 63 */     return ModAbilities.DORU_DORU_NO_MI;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getForm() {
/* 69 */     return "candle_champion";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDisplayName() {
/* 75 */     return CandleChampionAbility.INSTANCE.getUnlocalizedName();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public double getEyeHeight() {
/* 81 */     return 4.0D;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getShadowSize() {
/* 87 */     return 1.2F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Pose, EntitySize> getSizes() {
/* 93 */     return (Map<Pose, EntitySize>)ImmutableMap.builder()
/* 94 */       .put(Pose.STANDING, STANDING_SIZE)
/* 95 */       .put(Pose.CROUCHING, CROUCHING_SIZE)
/* 96 */       .build();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\CandleChampionMorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */