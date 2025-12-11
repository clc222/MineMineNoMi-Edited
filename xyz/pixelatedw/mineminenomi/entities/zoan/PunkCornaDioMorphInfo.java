/*    */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.entity.EntitySize;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.Pose;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.jiki.PunkCornaDioAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.morphs.PunkCornaDioRenderer;
/*    */ 
/*    */ 
/*    */ public class PunkCornaDioMorphInfo
/*    */   extends MorphInfo
/*    */ {
/* 20 */   private static final EntitySize STANDING_SIZE = EntitySize.func_220314_b(3.0F, 3.1F);
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public IRenderFactory getRendererFactory(LivingEntity entity) {
/* 26 */     return (IRenderFactory)new PunkCornaDioRenderer.Factory(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getForm() {
/* 32 */     return "punk_corna_dio";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDisplayName() {
/* 38 */     return PunkCornaDioAbility.INSTANCE.getUnlocalizedName();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public MorphModel getModel() {
/* 44 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public double getEyeHeight() {
/* 50 */     return 4.0D;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getShadowSize() {
/* 56 */     return 2.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canMount() {
/* 62 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Pose, EntitySize> getSizes() {
/* 68 */     return (Map<Pose, EntitySize>)ImmutableMap.builder()
/* 69 */       .put(Pose.STANDING, STANDING_SIZE)
/* 70 */       .build();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\PunkCornaDioMorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */