/*     */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.PropelledFlightAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.gomu.GearFourthAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.gomu.GearFourthFlightAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.models.morphs.GearFourthModel;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.morphs.GearFourthRenderer;
/*     */ 
/*     */ public class GearFourthMorphInfo
/*     */   extends MorphInfo
/*     */ {
/*  31 */   private static final EntitySize STANDING_SIZE = EntitySize.func_220314_b(2.8F, 4.0F);
/*  32 */   private static final EntitySize CROUCHING_SIZE = EntitySize.func_220314_b(2.8F, 3.9F);
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public IRenderFactory getRendererFactory() {
/*  37 */     return (IRenderFactory)new GearFourthRenderer.Factory(this);
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public MorphModel getModel() {
/*  43 */     return (MorphModel)new GearFourthModel(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void preRenderCallback(LivingEntity entity, MatrixStack matrixStack, float partialTickTime) {
/*  49 */     double x = entity.field_70169_q - entity.func_226277_ct_();
/*  50 */     double z = entity.field_70166_s - entity.func_226281_cx_();
/*     */     
/*  52 */     boolean isMoving = (x != 0.0D || z != 0.0D);
/*     */     
/*  54 */     if (isMoving) {
/*  55 */       IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*     */       
/*  57 */       PropelledFlightAbility flight = (PropelledFlightAbility)abilityDataProps.getPassiveAbility(GearFourthFlightAbility.INSTANCE);
/*     */       
/*  59 */       if (flight != null && flight.getSpeed(entity) >= flight.getMaxSpeed(entity) * 0.5F) {
/*  60 */         float headYawOffset = MathHelper.func_219805_h(partialTickTime, entity.field_70760_ar, entity.field_70761_aq);
/*  61 */         float headYawRotation = MathHelper.func_219805_h(partialTickTime, entity.field_70758_at, entity.field_70759_as);
/*  62 */         float netHeadYaw = headYawRotation - headYawOffset;
/*     */         
/*  64 */         double distanceX = entity.func_226277_ct_() - entity.field_70169_q;
/*  65 */         double distanceZ = entity.func_226281_cx_() - entity.field_70166_s;
/*  66 */         double movementSpeed = MathHelper.func_76133_a(distanceX * distanceX + distanceZ * distanceZ);
/*     */         
/*  68 */         float maxRotation = (float)(movementSpeed * 80.0D);
/*  69 */         float rot = -netHeadYaw * 3.0F;
/*     */         
/*  71 */         rot = MathHelper.func_76131_a(rot, -maxRotation, maxRotation);
/*     */         
/*  73 */         matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(rot));
/*     */       } 
/*     */       
/*  76 */       matrixStack.func_227861_a_(0.0D, -2.0D, 2.0D * entity.field_191988_bg);
/*  77 */       matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(entity.field_70125_A + 90.0F * entity.field_191988_bg));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture(LivingEntity entity) {
/*  83 */     if (entity instanceof AbstractClientPlayerEntity) {
/*  84 */       return ((AbstractClientPlayerEntity)entity).func_110306_p();
/*     */     }
/*     */     
/*  87 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public AkumaNoMiItem getDevilFruit() {
/*  92 */     return ModAbilities.GOMU_GOMU_NO_MI;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getForm() {
/*  97 */     return "gear_4th";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDisplayName() {
/* 102 */     return GearFourthAbility.INSTANCE.getUnlocalizedName();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getEyeHeight() {
/* 107 */     return 4.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/* 112 */     return 1.2F;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Pose, EntitySize> getSizes() {
/* 117 */     return (Map<Pose, EntitySize>)ImmutableMap.builder()
/* 118 */       .put(Pose.STANDING, STANDING_SIZE)
/* 119 */       .put(Pose.CROUCHING, CROUCHING_SIZE)
/* 120 */       .build();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\GearFourthMorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */