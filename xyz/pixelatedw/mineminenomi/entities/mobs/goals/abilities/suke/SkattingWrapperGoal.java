/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.suke;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.suke.SkattingAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class SkattingWrapperGoal extends AbilityWrapperGoal<MobEntity, SkattingAbility> {
/*    */   public SkattingWrapperGoal(MobEntity entity) {
/* 12 */     super(entity, SkattingAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 17 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 18 */       return false;
/*    */     }
/*    */     
/* 21 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 26 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void startWrapper() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void tickWrapper() {}
/*    */ 
/*    */   
/*    */   public void stopWrapper() {
/* 39 */     ((SkattingAbility)getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.startCooldown((LivingEntity)this.entity, 40.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\suke\SkattingWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */