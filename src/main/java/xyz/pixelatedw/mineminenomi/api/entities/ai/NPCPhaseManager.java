/*    */ package xyz.pixelatedw.mineminenomi.api.entities.ai;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class NPCPhaseManager {
/* 10 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   private final MobEntity entity;
/*    */   private NPCPhase<?> currentPhase;
/*    */   private NPCPhase<?> previousPhase;
/*    */   
/*    */   public NPCPhaseManager(MobEntity entity) {
/* 17 */     this.entity = entity;
/*    */   }
/*    */   
/*    */   public void setPhase(NPCPhase<?> phase) {
/* 21 */     if (this.currentPhase == null || !phase.equals(this.currentPhase)) {
/* 22 */       if (this.currentPhase != null) {
/* 23 */         this.currentPhase.stopPhase();
/*    */       }
/*    */       
/* 26 */       this.previousPhase = this.currentPhase;
/* 27 */       this.currentPhase = phase;
/* 28 */       this.currentPhase.startPhase();
/* 29 */       LOGGER.debug("Switched {}'s phase to: {}", this.entity.func_145748_c_().getString(), this.currentPhase.toString());
/*    */     } 
/*    */   }
/*    */   
/*    */   public void tick() {
/* 34 */     if (this.entity.func_70644_a((Effect)ModEffects.IN_EVENT.get())) {
/*    */       return;
/*    */     }
/* 37 */     if (this.currentPhase != null) {
/* 38 */       this.currentPhase.tick();
/*    */     }
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public NPCPhase<?> getPreviousPhase() {
/* 44 */     return this.previousPhase;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public NPCPhase<?> getCurrentPhase() {
/* 49 */     return this.currentPhase;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public boolean isCurrentPhase(NPCPhase<?> phase) {
/* 54 */     return this.currentPhase.equals(phase);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\entities\ai\NPCPhaseManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */