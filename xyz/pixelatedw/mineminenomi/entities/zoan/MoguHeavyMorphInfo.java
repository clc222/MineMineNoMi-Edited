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
/*    */ import xyz.pixelatedw.mineminenomi.abilities.mogu.MoguHeavyPointAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ import xyz.pixelatedw.mineminenomi.models.morphs.MoguMoleModel;
/*    */ 
/*    */ 
/*    */ public class MoguHeavyMorphInfo
/*    */   extends MorphInfo
/*    */ {
/* 22 */   private static final EntitySize STANDING_SIZE = EntitySize.func_220314_b(0.5F, 1.4F);
/* 23 */   private static final EntitySize CROUCHING_SIZE = EntitySize.func_220314_b(0.5F, 1.3F);
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public MorphModel getModel() {
/* 29 */     return (MorphModel)new MoguMoleModel();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public void preRenderCallback(LivingEntity entity, MatrixStack matrixStack, float partialTickTime) {
/* 36 */     float scale = 0.8F;
/* 37 */     matrixStack.func_227862_a_(scale, scale, scale);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AkumaNoMiItem getDevilFruit() {
/* 43 */     return ModAbilities.MOGU_MOGU_NO_MI;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getForm() {
/* 49 */     return "mogu_heavy";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDisplayName() {
/* 55 */     return MoguHeavyPointAbility.INSTANCE.getUnlocalizedName();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public double getEyeHeight() {
/* 61 */     return 1.4D;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getShadowSize() {
/* 67 */     return 0.4F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Pose, EntitySize> getSizes() {
/* 73 */     return (Map<Pose, EntitySize>)ImmutableMap.builder()
/* 74 */       .put(Pose.STANDING, STANDING_SIZE)
/* 75 */       .put(Pose.CROUCHING, CROUCHING_SIZE)
/* 76 */       .build();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\MoguHeavyMorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */