/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.entity.ai.goal.PrioritizedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ 
/*    */ @Deprecated
/*    */ public abstract class AbilityGoal<T extends OPEntity>
/*    */   extends Goal {
/*    */   public AbilityGoal(T entity) {
/* 11 */     this.entity = entity;
/*    */   }
/*    */   protected T entity;
/*    */   
/*    */   public boolean func_75250_a() {
/* 16 */     if (isChargingAbility()) {
/* 17 */       return false;
/*    */     }
/*    */     
/* 20 */     if (isOtherAbilityRunning()) {
/* 21 */       return false;
/*    */     }
/*    */     
/* 24 */     return true;
/*    */   }
/*    */   
/*    */   public boolean isOtherAbilityRunning() {
/* 28 */     return ((OPEntity)this.entity).field_70714_bg.func_220888_c().anyMatch(g -> (g.func_220772_j() instanceof AbilityGoal && !(g.func_220772_j() instanceof IParallelGoal)));
/*    */   }
/*    */   
/*    */   public boolean isChargingAbility() {
/* 32 */     return ((OPEntity)this.entity).field_70714_bg.func_220888_c().anyMatch(g -> (g.func_220772_j() instanceof ChargingGoal && !(g.func_220772_j() instanceof IParallelGoal) && ((ChargingGoal)g.func_220772_j()).isCharging()));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\AbilityGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */