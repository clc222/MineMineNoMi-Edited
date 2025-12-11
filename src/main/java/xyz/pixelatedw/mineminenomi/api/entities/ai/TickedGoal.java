/*    */ package xyz.pixelatedw.mineminenomi.api.entities.ai;
/*    */ 
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ 
/*    */ public abstract class TickedGoal<E extends MobEntity>
/*    */   extends Goal {
/*    */   protected E entity;
/*    */   private long startTick;
/*    */   private long lastEndTick;
/*    */   private long tickCount;
/*    */   
/*    */   public TickedGoal(E entity) {
/* 14 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 19 */     super.func_75249_e();
/* 20 */     this.startTick = ((MobEntity)this.entity).field_70170_p.func_82737_E();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 25 */     super.func_75246_d();
/* 26 */     this.tickCount++;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 31 */     super.func_75251_c();
/* 32 */     this.lastEndTick = ((MobEntity)this.entity).field_70170_p.func_82737_E();
/*    */   }
/*    */   
/*    */   public long getStartTick() {
/* 36 */     return this.startTick;
/*    */   }
/*    */   
/*    */   public void setLastEndTick(long tick) {
/* 40 */     this.lastEndTick = tick;
/*    */   }
/*    */   
/*    */   public long getLastEndTick() {
/* 44 */     return this.lastEndTick;
/*    */   }
/*    */   
/*    */   public long getTickCount() {
/* 48 */     return this.tickCount;
/*    */   }
/*    */   
/*    */   public boolean hasTimePassedSinceStart(float ticks) {
/* 52 */     return ((float)((MobEntity)this.entity).field_70170_p.func_82737_E() >= (float)getStartTick() + ticks);
/*    */   }
/*    */   
/*    */   public boolean hasLastEnd() {
/* 56 */     return (getLastEndTick() > 0L);
/*    */   }
/*    */   
/*    */   public boolean hasTimePassedSinceLastEnd(float ticks) {
/* 60 */     if (getLastEndTick() == 0L)
/*    */     {
/*    */       
/* 63 */       return true;
/*    */     }
/* 65 */     return ((float)((MobEntity)this.entity).field_70170_p.func_82737_E() >= (float)getLastEndTick() + ticks);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\entities\ai\TickedGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */