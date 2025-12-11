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
/*    */ import xyz.pixelatedw.mineminenomi.abilities.zoumammoth.MammothHeavyPointAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ import xyz.pixelatedw.mineminenomi.models.morphs.MammothHeavyModel;
/*    */ 
/*    */ public class MammothHeavyMorphInfo
/*    */   extends MorphInfo {
/* 20 */   private static final EntitySize STANDING_SIZE = EntitySize.func_220314_b(2.2F, 5.4F);
/* 21 */   private static final EntitySize CROUCHING_SIZE = EntitySize.func_220314_b(2.2F, 5.2F);
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public MorphModel getModel() {
/* 27 */     return (MorphModel)new MammothHeavyModel();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void preRenderCallback(LivingEntity entity, MatrixStack matrixStack, float partialTickTime) {
/* 33 */     float scale = 2.0F;
/* 34 */     matrixStack.func_227862_a_(scale, scale, scale);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AkumaNoMiItem getDevilFruit() {
/* 40 */     return ModAbilities.ZOU_ZOU_NO_MI_MAMMOTH;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getForm() {
/* 46 */     return "mammoth_heavy";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDisplayName() {
/* 52 */     return MammothHeavyPointAbility.INSTANCE.getUnlocalizedName();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public double getEyeHeight() {
/* 58 */     return 5.3D;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getShadowSize() {
/* 64 */     return 1.5F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canMount() {
/* 70 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Pose, EntitySize> getSizes() {
/* 76 */     return (Map<Pose, EntitySize>)ImmutableMap.builder()
/* 77 */       .put(Pose.STANDING, STANDING_SIZE)
/* 78 */       .put(Pose.CROUCHING, CROUCHING_SIZE)
/* 79 */       .build();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\MammothHeavyMorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */