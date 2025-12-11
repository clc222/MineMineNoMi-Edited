/*     */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.PriorityEventPool;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ 
/*     */ public class DamageTakenComponent extends AbilityComponent<IAbility> {
/*  13 */   private final PriorityEventPool<IGetHitEvent> onAttackEvents = new PriorityEventPool();
/*  14 */   private final PriorityEventPool<IGetHitEvent> onHurtEvents = new PriorityEventPool();
/*  15 */   private final PriorityEventPool<IGetHitEvent> onDamageEvents = new PriorityEventPool();
/*     */   
/*     */   @Deprecated
/*     */   private DamageState state;
/*     */   
/*     */   public DamageTakenComponent(IAbility ability) {
/*  21 */     super(ModAbilityKeys.DAMAGE_TAKEN, ability);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public DamageTakenComponent(IAbility ability, IGetHitEvent event, DamageState state) {
/*  29 */     super(ModAbilityKeys.DAMAGE_TAKEN, ability);
/*     */     
/*  31 */     if (state == DamageState.ATTACK) {
/*  32 */       addOnAttackEvent(event);
/*  33 */     } else if (state == DamageState.DAMAGE) {
/*  34 */       addOnDamageEvent(event);
/*  35 */     } else if (state == DamageState.HURT) {
/*  36 */       addOnHurtEvent(event);
/*     */     } 
/*     */     
/*  39 */     this.state = state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public DamageState getDamageState() {
/*  47 */     if (this.onAttackEvents.countEventsInPool() > 0L) {
/*  48 */       return DamageState.ATTACK;
/*     */     }
/*  50 */     if (this.onHurtEvents.countEventsInPool() > 0L) {
/*  51 */       return DamageState.HURT;
/*     */     }
/*  53 */     if (this.onDamageEvents.countEventsInPool() > 0L) {
/*  54 */       return DamageState.DAMAGE;
/*     */     }
/*     */     
/*  57 */     return this.state;
/*     */   }
/*     */   
/*     */   public DamageTakenComponent addOnAttackEvent(IGetHitEvent event) {
/*  61 */     this.onAttackEvents.addEvent(100, event);
/*     */     
/*  63 */     return this;
/*     */   }
/*     */   
/*     */   public DamageTakenComponent addOnAttackEvent(int priority, IGetHitEvent event) {
/*  67 */     this.onAttackEvents.addEvent(priority, event);
/*     */     
/*  69 */     return this;
/*     */   }
/*     */   
/*     */   public DamageTakenComponent addOnHurtEvent(IGetHitEvent event) {
/*  73 */     this.onHurtEvents.addEvent(100, event);
/*     */     
/*  75 */     return this;
/*     */   }
/*     */   
/*     */   public DamageTakenComponent addOnHurtEvent(int priority, IGetHitEvent event) {
/*  79 */     this.onHurtEvents.addEvent(priority, event);
/*     */     
/*  81 */     return this;
/*     */   }
/*     */   
/*     */   public DamageTakenComponent addOnDamageEvent(IGetHitEvent event) {
/*  85 */     this.onDamageEvents.addEvent(100, event);
/*     */     
/*  87 */     return this;
/*     */   }
/*     */   
/*     */   public DamageTakenComponent addOnDamageEvent(int priority, IGetHitEvent event) {
/*  91 */     this.onDamageEvents.addEvent(priority, event);
/*     */     
/*  93 */     return this;
/*     */   }
/*     */   
/*     */   public float checkDamageTaken(LivingEntity entity, DamageSource damageSource, float damage, DamageState state) {
/*  97 */     ensureIsRegistered();
/*     */     
/*  99 */     boolean isPaused = ((Boolean)getAbility().getComponent(ModAbilityKeys.PAUSE_TICK).map(PauseTickComponent::isPaused).orElse(Boolean.valueOf(false))).booleanValue();
/* 100 */     boolean isDisabled = ((Boolean)getAbility().getComponent(ModAbilityKeys.DISABLE).map(DisableComponent::isDisabled).orElse(Boolean.valueOf(false))).booleanValue();
/* 101 */     if (isPaused || isDisabled) {
/* 102 */       return damage;
/*     */     }
/*     */     
/* 105 */     List<IGetHitEvent> events = new ArrayList<>();
/*     */     
/* 107 */     switch (state) {
/*     */       case ATTACK:
/* 109 */         events = (List<IGetHitEvent>)this.onAttackEvents.getEventsStream().collect(Collectors.toList());
/*     */         break;
/*     */       
/*     */       case HURT:
/* 113 */         events = (List<IGetHitEvent>)this.onHurtEvents.getEventsStream().collect(Collectors.toList());
/*     */         break;
/*     */       
/*     */       case DAMAGE:
/* 117 */         events = (List<IGetHitEvent>)this.onDamageEvents.getEventsStream().collect(Collectors.toList());
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 124 */     for (IGetHitEvent event : events) {
/* 125 */       damage = event.damageCheck(entity, (IAbility)getAbility(), damageSource, damage);
/*     */     }
/*     */     
/* 128 */     return damage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum DamageState
/*     */   {
/* 137 */     ATTACK, HURT, DAMAGE;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IGetHitEvent {
/*     */     float damageCheck(LivingEntity param1LivingEntity, IAbility param1IAbility, DamageSource param1DamageSource, float param1Float);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\DamageTakenComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */