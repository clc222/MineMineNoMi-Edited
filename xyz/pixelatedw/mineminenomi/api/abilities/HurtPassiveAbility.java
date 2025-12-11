/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class HurtPassiveAbility
/*    */   extends PassiveAbility
/*    */ {
/*    */   protected IOnHurt onHurtEvent = (player, attacker) -> true;
/*    */   private float amount;
/*    */   
/*    */   public HurtPassiveAbility(AbilityCore core) {
/* 18 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hurt(LivingEntity entity, Entity source, float amount) {
/* 23 */     this.amount = amount;
/*    */     
/* 25 */     if (isPaused()) {
/* 26 */       return true;
/*    */     }
/* 28 */     return this.onHurtEvent.onHurt(entity, source);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getAmount() {
/* 33 */     return this.amount;
/*    */   }
/*    */   
/*    */   public static interface IOnHurt extends Serializable {
/*    */     boolean onHurt(LivingEntity param1LivingEntity, Entity param1Entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\HurtPassiveAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */