/*    */ package xyz.pixelatedw.mineminenomi.api.entities.ai;
/*    */ 
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.Set;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.entity.ai.goal.GoalSelector;
/*    */ import net.minecraft.entity.ai.goal.PrioritizedGoal;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class NPCPhase<E extends MobEntity>
/*    */ {
/*    */   private String name;
/*    */   private final E entity;
/* 20 */   private final Set<Pair<Integer, Goal>> availableGoals = Sets.newLinkedHashSet();
/*    */   private GoalSelector goals;
/*    */   
/*    */   public NPCPhase(String name, E entity) {
/* 24 */     this.name = name;
/* 25 */     this.entity = entity;
/* 26 */     this.goals = new GoalSelector(((MobEntity)entity).field_70170_p.func_234924_Y_());
/*    */   }
/*    */   
/*    */   public void addGoal(int priority, Goal goal) {
/* 30 */     if (this.availableGoals.stream().anyMatch(pair -> ((Goal)pair.getValue()).equals(goal))) {
/*    */       return;
/*    */     }
/* 33 */     this.availableGoals.add(ImmutablePair.of(Integer.valueOf(priority), goal));
/* 34 */     this.goals.func_75776_a(priority, goal);
/*    */   }
/*    */   
/*    */   public void addGoals(NPCPhase<E> phase) {
/* 38 */     for (Pair<Integer, Goal> pair : phase.getGoalsSet()) {
/* 39 */       addGoal(((Integer)pair.getKey()).intValue(), (Goal)pair.getValue());
/*    */     }
/*    */   }
/*    */   
/*    */   public final void startPhase() {
/* 44 */     start(this.entity);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public final void stopPhase() {
/* 50 */     this.goals.func_220888_c().forEach(PrioritizedGoal::func_75251_c);
/* 51 */     stop(this.entity);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public final void tick() {
/* 57 */     this.goals.func_75774_a();
/* 58 */     doTick();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 64 */     return this.name;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 69 */     return getName();
/*    */   }
/*    */   
/*    */   public <T extends xyz.pixelatedw.mineminenomi.api.entities.IPhasesEntity> boolean isActive(T entity) {
/* 73 */     NPCPhase<?> currentPhase = entity.getPhaseManager().getCurrentPhase();
/* 74 */     if (currentPhase == null) {
/* 75 */       return false;
/*    */     }
/* 77 */     return currentPhase.equals(this);
/*    */   }
/*    */   
/*    */   public Set<Pair<Integer, Goal>> getGoalsSet() {
/* 81 */     return this.availableGoals;
/*    */   }
/*    */   
/*    */   public abstract void start(E paramE);
/*    */   
/*    */   public abstract void stop(E paramE);
/*    */   
/*    */   public abstract void doTick();
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\entities\ai\NPCPhase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */