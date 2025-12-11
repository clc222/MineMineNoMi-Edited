/*    */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.jiki.PunkGibsonAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.morphs.PunkGibsonPartialMorphRenderer;
/*    */ 
/*    */ public class PunkGibsonMorphInfo
/*    */   extends MorphInfo {
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public IRenderFactory getRendererFactory(LivingEntity entity) {
/* 16 */     return (IRenderFactory)new PunkGibsonPartialMorphRenderer.Factory(this, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getForm() {
/* 21 */     return "punk_gibson";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDisplayName() {
/* 26 */     return PunkGibsonAbility.INSTANCE.getUnlocalizedName();
/*    */   }
/*    */ 
/*    */   
/*    */   public MorphModel getModel() {
/* 31 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\PunkGibsonMorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */