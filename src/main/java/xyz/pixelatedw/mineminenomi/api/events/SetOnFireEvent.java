/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.event.entity.EntityEvent;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ 
/*    */ @Cancelable
/*    */ public class SetOnFireEvent
/*    */   extends EntityEvent
/*    */ {
/*    */   @Nullable
/*    */   private LivingEntity attacker;
/*    */   private int fireTime;
/*    */   
/*    */   public SetOnFireEvent(@Nullable LivingEntity attacker, Entity target, int fireTime) {
/* 18 */     super(target);
/*    */     
/* 20 */     this.attacker = attacker;
/* 21 */     this.fireTime = fireTime;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public LivingEntity getAttacker() {
/* 26 */     return this.attacker;
/*    */   }
/*    */   
/*    */   public void setFireTime(int fireTime) {
/* 30 */     this.fireTime = fireTime;
/*    */   }
/*    */   
/*    */   public int getFireTime() {
/* 34 */     return this.fireTime;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\SetOnFireEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */