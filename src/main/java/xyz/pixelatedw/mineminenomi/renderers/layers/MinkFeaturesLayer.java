/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers;
/*    */ 
/*    */ import com.google.common.base.Strings;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.util.Collections;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.LivingRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.ModMain;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.models.morphs.MinkBunnyPartialModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.morphs.MinkDogPartialModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.morphs.MinkLionPartialModel;
/*    */ 
/*    */ public class MinkFeaturesLayer<T extends LivingEntity, M extends BipedModel<T>>
/*    */   extends LayerRenderer<T, M> {
/* 28 */   private static final MinkBunnyPartialModel BUNNY_MODEL = new MinkBunnyPartialModel();
/* 29 */   private static final ResourceLocation BUNNY_TEXTURE = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/mink_bunny.png");
/*    */   
/* 31 */   private static final MinkDogPartialModel DOG_MODEL = new MinkDogPartialModel();
/* 32 */   private static final ResourceLocation DOG_TEXTURE = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/mink_dog.png");
/*    */   
/* 34 */   private static final MinkLionPartialModel LION_MODEL = new MinkLionPartialModel();
/* 35 */   private static final ResourceLocation LION_TEXTURE = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/mink_lion.png");
/*    */   
/*    */   public static final Map<String, Pair<BipedModel, ResourceLocation>> MAP;
/*    */   
/*    */   static {
/* 40 */     Map<String, Pair<BipedModel, ResourceLocation>> map = new HashMap<>();
/* 41 */     map.put("mink_bunny", Pair.of(BUNNY_MODEL, BUNNY_TEXTURE));
/* 42 */     map.put("mink_dog", Pair.of(DOG_MODEL, DOG_TEXTURE));
/* 43 */     map.put("mink_lion", Pair.of(LION_MODEL, LION_TEXTURE));
/* 44 */     MAP = Collections.unmodifiableMap(map);
/*    */   }
/*    */ 
/*    */   
/*    */   public MinkFeaturesLayer(IEntityRenderer renderer) {
/* 49 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 55 */     IEntityStats entityProps = EntityStatsCapability.get((LivingEntity)entity);
/*    */     
/* 57 */     boolean isMink = entityProps.isMink();
/* 58 */     boolean isInvisible = entity.func_70644_a(Effects.field_76441_p);
/*    */     
/* 60 */     if (isMink && !isInvisible) {
/*    */       
/* 62 */       boolean isSubRaceNull = Strings.isNullOrEmpty(entityProps.getSubRace());
/* 63 */       if (isSubRaceNull) {
/* 64 */         entityProps.setSubRace("mink_dog");
/* 65 */         ModMain.LOGGER.error("Sub Race of this mink is empty, which should never happen.");
/*    */         
/*    */         return;
/*    */       } 
/* 69 */       BipedModel model = (BipedModel)((Pair)MAP.get(entityProps.getSubRace())).getLeft();
/* 70 */       ResourceLocation res = (ResourceLocation)((Pair)MAP.get(entityProps.getSubRace())).getRight();
/* 71 */       if (model != null && res != null) {
/* 72 */         ((BipedModel)func_215332_c()).func_217148_a(model);
/* 73 */         model.func_212843_a_((LivingEntity)entity, limbSwing, limbSwingAmount, partialTicks);
/* 74 */         model.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 75 */         model.func_225598_a_(matrixStack, buffer.getBuffer(RenderType.func_228644_e_(res)), packedLight, LivingRenderer.func_229117_c_((LivingEntity)entity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\MinkFeaturesLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */