/*    */ package xyz.pixelatedw.mineminenomi.api.entities.ai;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.goal.PrioritizedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ 
/*    */ @Deprecated
/*    */ public abstract class AbilityGoal2<E extends MobEntity> extends TickedGoal<E> {
/*    */   private float defaultCooldown;
/*    */   private float cooldown;
/*    */   
/*    */   public AbilityGoal2(E entity) {
/* 14 */     super(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 19 */     if (this.cooldown > 0.0F) {
/* 20 */       cooldownTick();
/* 21 */       return false;
/*    */     } 
/*    */     
/* 24 */     if (isOtherAbilityRunning()) {
/* 25 */       return false;
/*    */     }
/*    */     
/* 28 */     if (GoalUtil.isGCDActive((LivingEntity)this.entity)) {
/* 29 */       return false;
/*    */     }
/*    */     
/* 32 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 41 */     if (this.cooldown > 0.0F) {
/* 42 */       return false;
/*    */     }
/*    */     
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 50 */     super.func_75251_c();
/* 51 */     GoalUtil.startGCD((LivingEntity)this.entity);
/*    */   }
/*    */   
/*    */   private void cooldownTick() {
/* 55 */     this.cooldown--;
/* 56 */     duringCooldown();
/* 57 */     if (this.cooldown <= 0.0F) {
/* 58 */       endCooldown();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void duringCooldown() {}
/*    */ 
/*    */   
/*    */   public void endCooldown() {}
/*    */ 
/*    */   
/*    */   public void startCooldown(int ticks) {
/* 70 */     this.cooldown = ticks;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startCooldown() {
/* 77 */     this.cooldown = this.defaultCooldown;
/*    */   }
/*    */   
/*    */   public float getCooldown() {
/* 81 */     return this.cooldown;
/*    */   }
/*    */   
/*    */   public AbilityGoal2 setDefaultCooldown(float defaultCooldown) {
/* 85 */     this.defaultCooldown = defaultCooldown;
/* 86 */     return this;
/*    */   }
/*    */   
/*    */   public boolean isOtherAbilityRunning() {
/* 90 */     return ((MobEntity)this.entity).field_70714_bg.func_220888_c().anyMatch(g -> (g.func_220772_j() instanceof AbilityGoal2 && !(g.func_220772_j() instanceof xyz.pixelatedw.mineminenomi.entities.mobs.goals.IParallelGoal)));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\entities\ai\AbilityGoal2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */