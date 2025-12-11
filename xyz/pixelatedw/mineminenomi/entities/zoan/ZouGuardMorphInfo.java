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
/*    */ import xyz.pixelatedw.mineminenomi.abilities.zou.ZouGuardPointAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ import xyz.pixelatedw.mineminenomi.models.morphs.ZouGuardModel;
/*    */ 
/*    */ 
/*    */ public class ZouGuardMorphInfo
/*    */   extends MorphInfo
/*    */ {
/* 22 */   private static final EntitySize STANDING_SIZE = EntitySize.func_220314_b(1.5F, 3.0F);
/* 23 */   private static final EntitySize CROUCHING_SIZE = EntitySize.func_220314_b(1.5F, 2.9F);
/* 24 */   private static final EntitySize FLYING_SIZE = EntitySize.func_220314_b(1.5F, 3.0F);
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public MorphModel getModel() {
/* 30 */     return (MorphModel)new ZouGuardModel();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public void preRenderCallback(LivingEntity entity, MatrixStack matrixStack, float partialTickTime) {
/* 37 */     float scale = 1.8F;
/* 38 */     matrixStack.func_227862_a_(scale, scale, scale);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AkumaNoMiItem getDevilFruit() {
/* 44 */     return ModAbilities.ZOU_ZOU_NO_MI;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getForm() {
/* 50 */     return "zou_guard";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDisplayName() {
/* 56 */     return ZouGuardPointAbility.INSTANCE.getUnlocalizedName();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public double getEyeHeight() {
/* 62 */     return 2.9D;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getShadowSize() {
/* 68 */     return 1.5F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canMount() {
/* 74 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Pose, EntitySize> getSizes() {
/* 80 */     return (Map<Pose, EntitySize>)ImmutableMap.builder()
/* 81 */       .put(Pose.STANDING, STANDING_SIZE)
/* 82 */       .put(Pose.CROUCHING, CROUCHING_SIZE)
/* 83 */       .put(Pose.FALL_FLYING, FLYING_SIZE)
/* 84 */       .build();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\ZouGuardMorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */