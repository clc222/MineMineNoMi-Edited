/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.DropHitAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ 
/*    */ public class LeapAbility
/*    */   extends DropHitAbility2 {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "leap", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("Leaps at the enemy and damages all nearby entities on landing.", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 120.0F;
/*    */   private static final float DAMAGE = 10.0F;
/*    */   private static final float RANGE = 2.5F;
/* 32 */   public static final AbilityCore<LeapAbility> INSTANCE = (new AbilityCore.Builder("Leap", AbilityCategory.STYLE, LeapAbility::new))
/* 33 */     .addDescriptionLine(DESCRIPTION)
/* 34 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(120.0F), DealDamageComponent.getTooltip(10.0F), RangeComponent.getTooltip(2.5F, RangeComponent.RangeType.AOE)
/* 35 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 36 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 37 */       }).build();
/*    */   
/* 39 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/* 40 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*    */   
/* 42 */   private double leapHeight = 1.0D;
/* 43 */   private double leapDistance = 1.4D;
/*    */   
/*    */   public LeapAbility(AbilityCore<LeapAbility> core) {
/* 46 */     super(core);
/*    */     
/* 48 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.rangeComponent });
/* 49 */     this.continuousComponent.addStartEvent(100, this::startContinuityEvent);
/* 50 */     this.continuousComponent.addEndEvent(100, this::endContinuityEvent);
/*    */   }
/*    */   
/*    */   public double getLeapHeight() {
/* 54 */     return this.leapHeight;
/*    */   }
/*    */   
/*    */   public void setLeapHeight(double height) {
/* 58 */     this.leapHeight = height;
/*    */   }
/*    */   
/*    */   public double getLeapDistance() {
/* 62 */     return this.leapDistance;
/*    */   }
/*    */   
/*    */   public void setLeapDistance(double distance) {
/* 66 */     this.leapDistance = distance;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onLanding(LivingEntity entity) {
/* 71 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 2.5F);
/*    */     
/* 73 */     for (LivingEntity target : targets) {
/* 74 */       if (this.hitTrackerComponent.canHit((Entity)target)) {
/* 75 */         this.dealDamageComponent.hurtTarget(entity, target, 10.0F);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 81 */     this.cooldownComponent.startCooldown(entity, 120.0F);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 85 */     this.hitTrackerComponent.clearHits();
/* 86 */     Vector3d speed = entity.func_70040_Z().func_216372_d(this.leapDistance, 1.0D, this.leapDistance);
/* 87 */     AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, this.leapHeight, speed.field_72449_c);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\LeapAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */