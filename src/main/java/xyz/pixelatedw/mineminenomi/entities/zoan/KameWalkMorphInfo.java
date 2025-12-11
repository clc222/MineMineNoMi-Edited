/*    */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.kame.KameWalkPointAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ import xyz.pixelatedw.mineminenomi.models.morphs.KameWalkPartialModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.morphs.KameWalkPartialMorphRenderer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KameWalkMorphInfo
/*    */   extends MorphInfo
/*    */ {
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public IRenderFactory getRendererFactory(LivingEntity entity) {
/* 24 */     boolean isSlim = false;
/* 25 */     if (entity instanceof AbstractClientPlayerEntity) {
/* 26 */       isSlim = ((AbstractClientPlayerEntity)entity).func_175154_l().equals("slim");
/*    */     }
/* 28 */     return (IRenderFactory)new KameWalkPartialMorphRenderer.Factory(this, isSlim);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public MorphModel getModel() {
/* 35 */     return (MorphModel)new KameWalkPartialModel();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public boolean shouldRenderFirstPersonHand() {
/* 42 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public boolean shouldRenderFirstPersonLeg() {
/* 49 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isPartial() {
/* 55 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getForm() {
/* 61 */     return "kame_walk";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public AkumaNoMiItem getDevilFruit() {
/* 68 */     return ModAbilities.KAME_KAME_NO_MI;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDisplayName() {
/* 74 */     return KameWalkPointAbility.INSTANCE.getUnlocalizedName();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\KameWalkMorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */