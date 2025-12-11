/*     */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*     */ import java.util.function.BinaryOperator;
/*     */ import java.util.stream.Stream;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.PriorityEventPool;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ 
/*     */ public class HitTriggerComponent extends AbilityComponent<IAbility> {
/*     */   private static final BiFunction<EventReduce, HitResult, EventReduce> ACCUMULATOR;
/*     */   
/*     */   static {
/*  14 */     ACCUMULATOR = ((total, next) -> {
/*     */         if (next == HitResult.FAIL) {
/*     */           total.fails++;
/*     */         } else if (next == HitResult.HIT) {
/*     */           total.hits++;
/*     */         } 
/*     */         
/*     */         return total;
/*     */       });
/*     */     
/*  24 */     COMBINER = ((a, b) -> {
/*     */         a.fails += b.fails;
/*     */         a.hits += b.hits;
/*     */         return a;
/*     */       });
/*     */   }
/*  30 */   private static final BinaryOperator<EventReduce> COMBINER; private final PriorityEventPool<ITryHitEvent> tryHitEvents = new PriorityEventPool();
/*  31 */   private final PriorityEventPool<IOnHitEvent> onHitEvents = new PriorityEventPool();
/*     */ 
/*     */   
/*     */   private HitResult result;
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   private boolean bypassSameGroupProtection;
/*     */ 
/*     */   
/*     */   public HitTriggerComponent(IAbility ability) {
/*  42 */     super(ModAbilityKeys.HIT_TRIGGER, ability);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public HitTriggerComponent setBypassSameGroupProtection() {
/*  51 */     this.bypassSameGroupProtection = true;
/*  52 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HitTriggerComponent addTryHitEvent(ITryHitEvent event) {
/*  62 */     this.tryHitEvents.addEvent(100, event);
/*     */     
/*  64 */     return this;
/*     */   }
/*     */   
/*     */   public HitTriggerComponent addTryHitEvent(int priority, ITryHitEvent event) {
/*  68 */     this.tryHitEvents.addEvent(priority, event);
/*     */     
/*  70 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HitTriggerComponent addOnHitEvent(IOnHitEvent event) {
/*  80 */     this.onHitEvents.addEvent(100, event);
/*     */     
/*  82 */     return this;
/*     */   }
/*     */   
/*     */   public HitTriggerComponent addOnHitEvent(int priority, IOnHitEvent event) {
/*  86 */     this.onHitEvents.addEvent(priority, event);
/*     */     
/*  88 */     return this;
/*     */   }
/*     */   
/*     */   public HitResult getResult() {
/*  92 */     return this.result;
/*     */   }
/*     */   
/*     */   public void resetHitResult() {
/*  96 */     this.result = HitResult.PASS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HitResult tryHit(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 109 */     ensureIsRegistered();
/*     */     
/* 111 */     if (this.tryHitEvents.getEventsStream().count() <= 0L) {
/* 112 */       this.result = HitResult.PASS;
/*     */     } else {
/* 114 */       Stream<ITryHitEvent> eventStream = this.tryHitEvents.getEventsStream();
/* 115 */       Stream<HitResult> hitResults = eventStream.map(event -> event.tryHit(entity, target, source, getAbility()));
/*     */       
/* 117 */       EventReduce result = hitResults.<EventReduce>reduce(new EventReduce(), ACCUMULATOR, COMBINER);
/*     */       
/* 119 */       if (result.fails > 0) {
/* 120 */         this.result = HitResult.FAIL;
/* 121 */       } else if (result.hits > 0) {
/* 122 */         this.result = HitResult.HIT;
/*     */       } else {
/* 124 */         this.result = HitResult.PASS;
/*     */       } 
/*     */     } 
/*     */     
/* 128 */     return this.result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onHit(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 141 */     ensureIsRegistered();
/*     */     
/* 143 */     boolean isCancelled = false;
/*     */     
/* 145 */     if (this.result == HitResult.HIT) {
/* 146 */       isCancelled = this.onHitEvents.dispatchCancelable(event -> !event.onHit(entity, target, source, getAbility()));
/*     */     }
/*     */     
/* 149 */     resetHitResult();
/*     */     
/* 151 */     return !isCancelled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean bypassesSameGroupProtection() {
/* 159 */     return this.bypassSameGroupProtection;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IOnHitEvent
/*     */   {
/*     */     boolean onHit(LivingEntity param1LivingEntity1, LivingEntity param1LivingEntity2, ModDamageSource param1ModDamageSource, IAbility param1IAbility);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface ITryHitEvent
/*     */   {
/*     */     HitTriggerComponent.HitResult tryHit(LivingEntity param1LivingEntity1, LivingEntity param1LivingEntity2, ModDamageSource param1ModDamageSource, IAbility param1IAbility);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum HitResult
/*     */   {
/* 187 */     HIT,
/* 188 */     PASS,
/* 189 */     FAIL;
/*     */   }
/*     */   
/*     */   static class EventReduce
/*     */   {
/*     */     public int fails;
/*     */     public int hits;
/*     */     
/*     */     public String toString() {
/* 198 */       return "fails: " + this.fails + ", hits: " + this.hits;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\HitTriggerComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */