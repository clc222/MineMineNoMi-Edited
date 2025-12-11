/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ @Deprecated
/*    */ public class AbilityOffEffect
/*    */   extends ModEffect
/*    */ {
/*    */   public AbilityOffEffect() {
/* 17 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier) {
/* 23 */     return (duration % 20 == 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_76394_a(LivingEntity entity, int amplifier) {
/* 29 */     if (entity instanceof net.minecraft.entity.player.PlayerEntity && amplifier > 0)
/*    */     {
/* 31 */       AbilityHelper.disableAbilities(entity, 20, ability -> (ability != null && ability.getCore().getCategory() == AbilityCategory.DEVIL_FRUITS));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRender(EffectInstance effect) {
/* 38 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRenderHUD(EffectInstance effect) {
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 50 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\AbilityOffEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */