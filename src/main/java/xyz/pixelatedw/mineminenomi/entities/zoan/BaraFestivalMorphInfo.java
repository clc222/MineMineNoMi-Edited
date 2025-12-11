/*    */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.bara.BaraBaraFestivalAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.morphs.BaraPartialMorphRenderer;
/*    */ 
/*    */ 
/*    */ public class BaraFestivalMorphInfo
/*    */   extends MorphInfo
/*    */ {
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public IRenderFactory getRendererFactory(LivingEntity entity) {
/* 18 */     return (IRenderFactory)new BaraPartialMorphRenderer.Factory(this, BaraPartialMorphRenderer.BaraMode.FESTIVAL);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getForm() {
/* 24 */     return "bara_festival";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDisplayName() {
/* 30 */     return BaraBaraFestivalAbility.INSTANCE.getUnlocalizedName();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public MorphModel getModel() {
/* 36 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\BaraFestivalMorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */