/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ 
/*    */ public class UpdateCombatStateEvent
/*    */   extends LivingEvent
/*    */ {
/*    */   @Nullable
/*    */   private LivingEntity attacker;
/*    */   private boolean state;
/*    */   
/*    */   public UpdateCombatStateEvent(LivingEntity entity, LivingEntity attacker, boolean state) {
/* 15 */     super(entity);
/* 16 */     this.attacker = attacker;
/* 17 */     this.state = state;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public LivingEntity getLastAttacker() {
/* 22 */     return this.attacker;
/*    */   }
/*    */   
/*    */   public boolean getCurrentInCombatState() {
/* 26 */     return this.state;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\UpdateCombatStateEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */