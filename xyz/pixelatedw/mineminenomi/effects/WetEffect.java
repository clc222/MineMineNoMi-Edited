/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class WetEffect extends ModEffect {
/*    */   public WetEffect() {
/* 15 */     super(EffectType.NEUTRAL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_76394_a(LivingEntity entity, int amp) {
/* 20 */     if (entity.func_70089_S()) {
/* 21 */       int timer = entity.func_70660_b((Effect)this).func_76459_b();
/*    */       
/* 23 */       IDevilFruit props = DevilFruitCapability.get(entity);
/* 24 */       if (props.hasDevilFruit(ModAbilities.SUNA_SUNA_NO_MI)) {
/* 25 */         AbilityHelper.disableAbilities(entity, timer, abl -> (abl.getCore().getCategory() == AbilityCategory.DEVIL_FRUITS));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 32 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\WetEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */