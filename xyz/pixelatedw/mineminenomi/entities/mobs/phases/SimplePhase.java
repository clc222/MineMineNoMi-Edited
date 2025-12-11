/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.phases;
/*    */ 
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhase;
/*    */ 
/*    */ public class SimplePhase<E extends MobEntity>
/*    */   extends NPCPhase<E> {
/*    */   private IStartPhaseEvent<E> startPhaseEvent;
/*    */   private IStopPhaseEvent<E> stopPhaseEvent;
/*    */   
/*    */   public SimplePhase(String name, E entity) {
/* 12 */     super(name, (MobEntity)entity);
/*    */   }
/*    */   
/*    */   public SimplePhase(String name, E entity, IStartPhaseEvent<E> startPhaseEvent) {
/* 16 */     super(name, (MobEntity)entity);
/* 17 */     this.startPhaseEvent = startPhaseEvent;
/*    */   }
/*    */   
/*    */   public SimplePhase(String name, E entity, IStartPhaseEvent<E> startPhaseEvent, IStopPhaseEvent<E> stopPhaseEvent) {
/* 21 */     super(name, (MobEntity)entity);
/* 22 */     this.startPhaseEvent = startPhaseEvent;
/* 23 */     this.stopPhaseEvent = stopPhaseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   public void start(E entity) {
/* 28 */     if (this.startPhaseEvent != null) {
/* 29 */       this.startPhaseEvent.startPhase(entity);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void stop(E entity) {
/* 35 */     if (this.stopPhaseEvent != null)
/* 36 */       this.stopPhaseEvent.stopPhase(entity); 
/*    */   }
/*    */   
/*    */   public void doTick() {}
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface IStopPhaseEvent<E extends MobEntity> {
/*    */     void stopPhase(E param1E);
/*    */   }
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface IStartPhaseEvent<E extends MobEntity> {
/*    */     void startPhase(E param1E);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\phases\SimplePhase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */