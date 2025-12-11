/*    */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.jiki.DamnedPunkAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.morphs.PunkGibsonPartialMorphRenderer;
/*    */ 
/*    */ public class DamnedPunkMorphInfo
/*    */   extends MorphInfo {
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public IRenderFactory getRendererFactory(LivingEntity entity) {
/* 16 */     return (IRenderFactory)new PunkGibsonPartialMorphRenderer.Factory(this, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getForm() {
/* 21 */     return "damned_punk";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDisplayName() {
/* 26 */     return DamnedPunkAbility.INSTANCE.getUnlocalizedName();
/*    */   }
/*    */ 
/*    */   
/*    */   public MorphModel getModel() {
/* 31 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\DamnedPunkMorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */