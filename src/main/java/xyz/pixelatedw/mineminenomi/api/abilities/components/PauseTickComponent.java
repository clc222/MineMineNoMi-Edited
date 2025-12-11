/*    */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.PriorityEventPool;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class PauseTickComponent
/*    */   extends AbilityComponent<IAbility>
/*    */ {
/* 13 */   private final PriorityEventPool<IPauseEvent> pauseEvents = new PriorityEventPool();
/* 14 */   private final PriorityEventPool<IResumeEvent> resumeEvents = new PriorityEventPool();
/*    */   
/*    */   private boolean isPaused;
/*    */   
/*    */   public PauseTickComponent(IAbility ability) {
/* 19 */     super(ModAbilityKeys.PAUSE_TICK, ability);
/*    */   }
/*    */   
/*    */   public void setPause(LivingEntity entity, boolean isPaused) {
/* 23 */     ensureIsRegistered();
/* 24 */     this.isPaused = isPaused;
/* 25 */     if (this.isPaused) {
/* 26 */       this.pauseEvents.dispatch(event -> event.pause(entity, getAbility()));
/*    */     } else {
/*    */       
/* 29 */       this.resumeEvents.dispatch(event -> event.resume(entity, getAbility()));
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean isPaused() {
/* 34 */     return this.isPaused;
/*    */   }
/*    */   
/*    */   public PauseTickComponent addPauseEvent(int priority, IPauseEvent event) {
/* 38 */     this.pauseEvents.addEvent(priority, event);
/* 39 */     return this;
/*    */   }
/*    */   
/*    */   public PauseTickComponent addResumeEvent(int priority, IResumeEvent event) {
/* 43 */     this.resumeEvents.addEvent(priority, event);
/* 44 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public CompoundNBT save() {
/* 50 */     CompoundNBT nbt = super.save();
/* 51 */     nbt.func_74757_a("isPaused", this.isPaused);
/* 52 */     return nbt;
/*    */   }
/*    */ 
/*    */   
/*    */   public void load(CompoundNBT nbt) {
/* 57 */     super.load(nbt);
/* 58 */     this.isPaused = nbt.func_74767_n("isPaused");
/*    */   }
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface IResumeEvent {
/*    */     void resume(LivingEntity param1LivingEntity, IAbility param1IAbility);
/*    */   }
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface IPauseEvent {
/*    */     void pause(LivingEntity param1LivingEntity, IAbility param1IAbility);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\PauseTickComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */