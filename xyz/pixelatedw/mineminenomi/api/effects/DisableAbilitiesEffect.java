/*    */ package xyz.pixelatedw.mineminenomi.api.effects;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class DisableAbilitiesEffect
/*    */   extends ModEffect
/*    */   implements IDisableAbilitiesEffect {
/*    */   private Predicate<IAbility> predicate = abl -> true;
/*    */   
/*    */   public DisableAbilitiesEffect() {
/* 16 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */   
/*    */   public DisableAbilitiesEffect(Predicate<IAbility> predicate) {
/* 20 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/* 21 */     this.predicate = predicate;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier) {
/* 26 */     return (duration % 20 == 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_76394_a(LivingEntity entity, int amplifier) {
/* 31 */     if (entity instanceof net.minecraft.entity.player.PlayerEntity) {
/* 32 */       AbilityHelper.disableAbilities(entity, 20, this.predicate);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<IAbility> getDisabledAbilities() {
/* 38 */     return this.predicate;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\effects\DisableAbilitiesEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */