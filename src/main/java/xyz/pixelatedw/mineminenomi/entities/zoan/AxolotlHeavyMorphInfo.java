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
/*     */ import xyz.pixelatedw.mineminenomi.abilities.saraaxolotl.AxolotlHeavyPointAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.models.morphs.AxolotlHeavyModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AxolotlHeavyMorphInfo
/*     */   extends MorphInfo
/*     */ {
/*  27 */   private static final EntitySize STANDING_SIZE = EntitySize.func_220314_b(2.5F, 4.0F);
/*  28 */   private static final EntitySize CROUCHING_SIZE = EntitySize.func_220314_b(2.5F, 3.8F);
/*     */   
/*  30 */   private static final ResourceLocation[] TEXTURES = new ResourceLocation[] { new ResourceLocation("mineminenomi", "textures/models/zoanmorph/axolotl_heavy_0.png"), new ResourceLocation("mineminenomi", "textures/models/zoanmorph/axolotl_heavy_1.png"), new ResourceLocation("mineminenomi", "textures/models/zoanmorph/axolotl_heavy_2.png"), new ResourceLocation("mineminenomi", "textures/models/zoanmorph/axolotl_heavy_3.png"), new ResourceLocation("mineminenomi", "textures/models/zoanmorph/axolotl_heavy_4.png") };
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
/*  42 */     return (MorphModel)new AxolotlHeavyModel();
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
/*     */         
/*  56 */         scheme += Integer.parseInt(bit); } 
/*     */     } 
/*  58 */     int len = 4;
/*  59 */     scheme = MathHelper.func_76125_a(scheme & len, 0, len);
/*  60 */     return TEXTURES[scheme];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void preRenderCallback(LivingEntity entity, MatrixStack matrixStack, float partialTickTime) {
/*  67 */     float scale = 1.6F;
/*  68 */     matrixStack.func_227862_a_(scale, scale, scale);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AkumaNoMiItem getDevilFruit() {
/*  74 */     return ModAbilities.SARA_SARA_NO_MI_AXOLOTL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getForm() {
/*  80 */     return "axolotl_heavy";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDisplayName() {
/*  86 */     return AxolotlHeavyPointAbility.INSTANCE.getUnlocalizedName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getEyeHeight() {
/*  92 */     return 3.7D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/*  98 */     return 1.8F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canMount() {
/* 104 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean hasEqualDepthTest() {
/* 111 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Pose, EntitySize> getSizes() {
/* 117 */     return (Map<Pose, EntitySize>)ImmutableMap.builder()
/* 118 */       .put(Pose.STANDING, STANDING_SIZE)
/* 119 */       .put(Pose.CROUCHING, CROUCHING_SIZE)
/* 120 */       .build();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\AxolotlHeavyMorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */