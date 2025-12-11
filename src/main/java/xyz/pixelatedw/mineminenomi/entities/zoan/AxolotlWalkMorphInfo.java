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
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.saraaxolotl.AxolotlWalkPointAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.models.morphs.AxolotlWalkModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AxolotlWalkMorphInfo
/*     */   extends MorphInfo
/*     */ {
/*  27 */   private static final EntitySize STANDING_SIZE = EntitySize.func_220314_b(0.6F, 0.5F);
/*  28 */   private static final EntitySize CROUCHING_SIZE = EntitySize.func_220314_b(0.6F, 0.4F);
/*     */   
/*  30 */   private static final ResourceLocation[] TEXTURES = new ResourceLocation[] { new ResourceLocation("mineminenomi", "textures/models/zoanmorph/axolotl_walk_0.png"), new ResourceLocation("mineminenomi", "textures/models/zoanmorph/axolotl_walk_1.png"), new ResourceLocation("mineminenomi", "textures/models/zoanmorph/axolotl_walk_2.png"), new ResourceLocation("mineminenomi", "textures/models/zoanmorph/axolotl_walk_3.png"), new ResourceLocation("mineminenomi", "textures/models/zoanmorph/axolotl_walk_4.png") };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public MorphModel getModel() {
/*  42 */     return (MorphModel)new AxolotlWalkModel();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ResourceLocation getTexture(LivingEntity entity) {
/*  49 */     String[] bits = ("" + entity.func_110124_au().getMostSignificantBits()).split("");
/*  50 */     int scheme = 0;
/*  51 */     for (String bit : bits) {
/*     */       
/*  53 */       if (!bit.equalsIgnoreCase("-"))
/*     */       {
/*  55 */         scheme += Integer.parseInt(bit); } 
/*     */     } 
/*  57 */     int len = 4;
/*  58 */     scheme = MathHelper.func_76125_a(scheme & len, 0, len);
/*  59 */     return TEXTURES[scheme];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void preRenderCallback(LivingEntity entity, MatrixStack matrixStack, float partialTickTime) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AkumaNoMiItem getDevilFruit() {
/*  71 */     return ModAbilities.SARA_SARA_NO_MI_AXOLOTL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getForm() {
/*  77 */     return "axolotl_walk";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDisplayName() {
/*  83 */     return AxolotlWalkPointAbility.INSTANCE.getUnlocalizedName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getEyeHeight() {
/*  89 */     return 0.4D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/*  95 */     return 0.5F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean hasEqualDepthTest() {
/* 102 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Pose, EntitySize> getSizes() {
/* 108 */     return (Map<Pose, EntitySize>)ImmutableMap.builder()
/* 109 */       .put(Pose.STANDING, STANDING_SIZE)
/* 110 */       .put(Pose.CROUCHING, CROUCHING_SIZE)
/* 111 */       .build();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\AxolotlWalkMorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */