/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.KaenBoshiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.TokuyoAburaBoshiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class KaenBoshiWrapperGoal extends ChargeSniperAbilityGoal<MobEntity, KaenBoshiAbility> {
/*    */   public KaenBoshiWrapperGoal(MobEntity entity) {
/* 14 */     super(entity, KaenBoshiAbility.INSTANCE, 40);
/*    */   }
/*    */   
/*    */   public KaenBoshiWrapperGoal(MobEntity entity, int chargeup) {
/* 18 */     super(entity, KaenBoshiAbility.INSTANCE, chargeup);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 23 */     if (!super.canUseWrapper()) {
/* 24 */       return false;
/*    */     }
/*    */     
/* 27 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)this.entity);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 35 */     if (props.hasEquippedAbility(TokuyoAburaBoshiAbility.INSTANCE) && 
/* 36 */       GoalUtil.hasHealthAbovePercentage((LivingEntity)this.entity, 50.0D) && !getTarget().func_70644_a((Effect)ModEffects.OIL_COVERED.get())) {
/* 37 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 41 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 46 */     return super.canContinueToUseWrapper();
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 51 */     super.startWrapper();
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 56 */     super.tickWrapper();
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopWrapper() {
/* 61 */     super.stopWrapper();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\sniper\KaenBoshiWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */