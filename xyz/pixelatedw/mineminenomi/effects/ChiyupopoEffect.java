/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifierManager;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ChiyupopoEffect
/*    */   extends ModEffect {
/*    */   public ChiyupopoEffect() {
/* 13 */     super(EffectType.BENEFICIAL, WyHelper.hexToRGB("#FFC0CB").getRGB());
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_76394_a(LivingEntity pLivingEntity, int pAmplifier) {
/* 18 */     if (pLivingEntity.func_110143_aJ() < pLivingEntity.func_110138_aP()) {
/* 19 */       pLivingEntity.func_70691_i(1.0F);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_76397_a(int pDuration, int pAmplifier) {
/* 25 */     int k = 50 >> pAmplifier;
/*    */     
/* 27 */     if (k > 0) {
/* 28 */       return (pDuration % k == 0);
/*    */     }
/* 30 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_111185_a(LivingEntity pLivingEntity, AttributeModifierManager pAttributeMap, int pAmplifier) {
/* 36 */     super.func_111185_a(pLivingEntity, pAttributeMap, pAmplifier);
/*    */     
/* 38 */     EntityStatsCapability.get(pLivingEntity).setOriginalChiyupopoHealth(pLivingEntity.func_110143_aJ());
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_111187_a(LivingEntity pLivingEntity, AttributeModifierManager pAttributeMap, int pAmplifier) {
/* 43 */     super.func_111187_a(pLivingEntity, pAttributeMap, pAmplifier);
/*    */     
/* 45 */     pLivingEntity.func_70606_j(EntityStatsCapability.get(pLivingEntity).getOriginalChiyupopoHealth());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 50 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\ChiyupopoEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */