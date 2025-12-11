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
/*    */ import xyz.pixelatedw.mineminenomi.abilities.ushigiraffe.GiraffeWalkPointAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ import xyz.pixelatedw.mineminenomi.models.morphs.GiraffeWalkModel;
/*    */ 
/*    */ 
/*    */ public class GiraffeWalkMorphInfo
/*    */   extends MorphInfo
/*    */ {
/* 22 */   private static final EntitySize STANDING_SIZE = EntitySize.func_220314_b(1.0F, 4.8F);
/* 23 */   private static final EntitySize CROUCHING_SIZE = EntitySize.func_220314_b(1.0F, 4.7F);
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public MorphModel getModel() {
/* 29 */     return (MorphModel)new GiraffeWalkModel();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public void preRenderCallback(LivingEntity entity, MatrixStack matrixStack, float partialTickTime) {
/* 36 */     float scale = 2.0F;
/* 37 */     matrixStack.func_227862_a_(scale, scale, scale);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AkumaNoMiItem getDevilFruit() {
/* 43 */     return ModAbilities.USHI_USHI_NO_MI_GIRAFFE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getForm() {
/* 49 */     return "giraffe_walk";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDisplayName() {
/* 55 */     return GiraffeWalkPointAbility.INSTANCE.getUnlocalizedName();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public double getEyeHeight() {
/* 61 */     return 4.9D;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getShadowSize() {
/* 67 */     return 1.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canMount() {
/* 73 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public boolean hasCulling() {
/* 80 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Pose, EntitySize> getSizes() {
/* 86 */     return (Map<Pose, EntitySize>)ImmutableMap.builder()
/* 87 */       .put(Pose.STANDING, STANDING_SIZE)
/* 88 */       .put(Pose.CROUCHING, CROUCHING_SIZE)
/* 89 */       .build();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\GiraffeWalkMorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */