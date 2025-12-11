/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ 
/*    */ @Cancelable
/*    */ public class WyLivingHurtEvent
/*    */   extends LivingEvent {
/*    */   private DamageSource source;
/*    */   private float amount;
/*    */   
/*    */   public WyLivingHurtEvent(LivingEntity entity, DamageSource source, float amount) {
/* 15 */     super(entity);
/*    */     
/* 17 */     this.source = source;
/* 18 */     this.amount = amount;
/*    */   }
/*    */   
/*    */   public DamageSource getSource() {
/* 22 */     return this.source;
/*    */   }
/*    */   
/*    */   public void setSource(DamageSource source) {
/* 26 */     this.source = source;
/*    */   }
/*    */   
/*    */   public float getAmount() {
/* 30 */     return this.amount;
/*    */   }
/*    */   
/*    */   public void setAmount(float amount) {
/* 34 */     this.amount = amount;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\WyLivingHurtEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */