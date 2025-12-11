/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.ShakushiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.IChallengeBoss;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.api.math.VectorHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class ShakushiWrapperGoal extends AbilityWrapperGoal<MobEntity, ShakushiAbility> {
/* 15 */   private float minDistance = 10.0F;
/*    */   
/*    */   public ShakushiWrapperGoal(MobEntity entity) {
/* 18 */     super(entity, ShakushiAbility.INSTANCE);
/* 19 */     this.minDistance = ((Float)((ShakushiAbility)getAbility()).getComponent(ModAbilityKeys.RANGE).map(comp -> Float.valueOf(comp.getRange())).orElse(Float.valueOf(10.0F))).floatValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 24 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 25 */       return false;
/*    */     }
/*    */     
/* 28 */     if (!GoalUtil.canMove((LivingEntity)this.entity)) {
/* 29 */       return false;
/*    */     }
/*    */     
/* 32 */     if (this.entity instanceof IChallengeBoss) {
/* 33 */       ChallengeInfo info = ((IChallengeBoss)this.entity).getChallengeInfo();
/* 34 */       if (info.getChallengerGroup().isEmpty()) {
/* 35 */         return false;
/*    */       }
/*    */       
/* 38 */       int id = this.entity.func_70681_au().nextInt(info.getChallengerGroup().size());
/* 39 */       LivingEntity target = info.getChallengerGroup().get(id);
/* 40 */       if (target != null && target.func_70089_S()) {
/* 41 */         Vector3d targetLook = VectorHelper.calculateViewVectorFromBodyRot(target.field_70125_A, target.field_70761_aq).func_216372_d(-2.0D, 0.0D, -2.0D);
/* 42 */         Vector3d newPos = target.func_213303_ch().func_178787_e(targetLook);
/* 43 */         this.entity.func_70634_a(newPos.field_72450_a, newPos.field_72448_b, newPos.field_72449_c);
/*    */       }
/*    */     
/*    */     }
/* 47 */     else if (!GoalUtil.hasEnoughTargetsAround(this.entity, 1, this.minDistance)) {
/* 48 */       return false;
/*    */     } 
/*    */ 
/*    */     
/* 52 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 57 */     return true;
/*    */   }
/*    */   
/*    */   public void startWrapper() {}
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\ShakushiWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */