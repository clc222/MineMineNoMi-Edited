/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public abstract class RunningSmashAbility extends PassiveAbility2 {
/* 15 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent(this);
/* 16 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent(this);
/* 17 */   private final RangeComponent rangeComponent = new RangeComponent(this);
/*    */   
/*    */   protected static final float DEFAULT_RANGE = 1.5F;
/*    */   
/*    */   protected static final float DEFAULT_DAMAGE = 2.0F;
/*    */   
/*    */   private float area;
/*    */   private float damage;
/* 25 */   private Interval resetInterval = new Interval(20);
/*    */   
/*    */   public RunningSmashAbility(AbilityCore core) {
/* 28 */     this(core, 1.5F, 2.0F);
/*    */   }
/*    */   
/*    */   public RunningSmashAbility(AbilityCore<?> core, float area, float damage) {
/* 32 */     super(core);
/*    */     
/* 34 */     this.area = area;
/* 35 */     this.damage = damage;
/*    */     
/* 37 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent });
/*    */     
/* 39 */     addDuringPassiveEvent(this::duringPassiveEvent);
/*    */   }
/*    */   
/*    */   private void duringPassiveEvent(LivingEntity entity) {
/* 43 */     if (this.resetInterval.canTick()) {
/* 44 */       this.hitTrackerComponent.clearHits();
/*    */     }
/*    */     
/* 47 */     if (entity.func_70051_ag() && canSmash(entity)) {
/* 48 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInLine(entity, 1.0F, this.area);
/* 49 */       targets.removeIf(e -> (e.func_184187_bx() != null && e.func_184187_bx().equals(entity)));
/* 50 */       for (LivingEntity target : targets) {
/* 51 */         if (this.hitTrackerComponent.canHit((Entity)target) && this.dealDamageComponent.hurtTarget(entity, target, this.damage)) {
/* 52 */           Vector3d speed = WyHelper.propulsion(entity, 2.0D, 2.0D);
/* 53 */           AbilityHelper.setDeltaMovement((Entity)target, speed.field_72450_a, 0.2D, speed.field_72449_c);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public abstract boolean canSmash(LivingEntity paramLivingEntity);
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\RunningSmashAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */