/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ 
/*    */ 
/*    */ public class HitTriggerEvent
/*    */   extends LivingEvent
/*    */ {
/*    */   private LivingEntity attacker;
/*    */   private LivingEntity target;
/*    */   private float bonusDamage;
/*    */   private ModDamageSource source;
/*    */   private IAbility ability;
/*    */   
/*    */   public HitTriggerEvent(LivingEntity attacker, LivingEntity target, float bonusDamage, ModDamageSource source, IAbility ability) {
/* 21 */     super(attacker);
/*    */     
/* 23 */     this.attacker = attacker;
/* 24 */     this.target = target;
/* 25 */     this.bonusDamage = bonusDamage;
/* 26 */     this.source = source;
/* 27 */     this.ability = ability;
/*    */   }
/*    */   
/*    */   public LivingEntity getAttacker() {
/* 31 */     return this.attacker;
/*    */   }
/*    */   
/*    */   public LivingEntity getTarget() {
/* 35 */     return this.target;
/*    */   }
/*    */   
/*    */   public float getBonusDamage() {
/* 39 */     return this.bonusDamage;
/*    */   }
/*    */   
/*    */   public ModDamageSource getSource() {
/* 43 */     return this.source;
/*    */   }
/*    */   
/*    */   public IAbility getAbility() {
/* 47 */     return this.ability;
/*    */   }
/*    */   
/*    */   @Cancelable
/*    */   public static class Pre extends HitTriggerEvent {
/*    */     private HitTriggerComponent.HitResult hitResult;
/*    */     
/*    */     public Pre(LivingEntity attacker, LivingEntity target, float bonusDamage, ModDamageSource source, IAbility ability, HitTriggerComponent.HitResult hitResult) {
/* 55 */       super(attacker, target, bonusDamage, source, ability);
/*    */       
/* 57 */       this.hitResult = hitResult;
/*    */     }
/*    */     
/*    */     public void setAttacker(LivingEntity attacker) {
/* 61 */       this.attacker = attacker;
/*    */     }
/*    */     
/*    */     public void setTarget(LivingEntity target) {
/* 65 */       this.target = target;
/*    */     }
/*    */     
/*    */     public void setBonusDamage(float bonusDamage) {
/* 69 */       this.bonusDamage = bonusDamage;
/*    */     }
/*    */     
/*    */     public void setSource(ModDamageSource source) {
/* 73 */       this.source = source;
/*    */     }
/*    */     
/*    */     public void setHitResult(HitTriggerComponent.HitResult hitResult) {
/* 77 */       this.hitResult = hitResult;
/*    */     }
/*    */     
/*    */     public HitTriggerComponent.HitResult getHitResult() {
/* 81 */       return this.hitResult;
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Post extends HitTriggerEvent {
/*    */     public Post(LivingEntity attacker, LivingEntity target, float bonusDamage, ModDamageSource source, IAbility ability) {
/* 87 */       super(attacker, target, bonusDamage, source, ability);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\HitTriggerEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */