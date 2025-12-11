/*    */ package xyz.pixelatedw.mineminenomi.abilities.nekoleopard;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.DropHitAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class FerociousLeapAbility extends DropHitAbility {
/* 28 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "ferocious_leap", new Pair[] {
/* 29 */         (Pair)ImmutablePair.of("Leaps at the enemy and damages all nearby entities on landing.", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 160.0F;
/*    */   private static final float DAMAGE = 10.0F;
/*    */   private static final float RANGE = 3.5F;
/* 35 */   public static final AbilityCore<FerociousLeapAbility> INSTANCE = (new AbilityCore.Builder("Ferocious Leap", AbilityCategory.DEVIL_FRUITS, FerociousLeapAbility::new))
/* 36 */     .addDescriptionLine(DESCRIPTION)
/* 37 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/* 38 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(160.0F), DealDamageComponent.getTooltip(10.0F), RangeComponent.getTooltip(3.5F, RangeComponent.RangeType.AOE)
/* 39 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 40 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 41 */       }).build();
/*    */   
/* 43 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/* 44 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/* 45 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.LEOPARD_HEAVY.get(), new MorphInfo[] { (MorphInfo)ModMorphs.LEOPARD_WALK.get() });
/*    */   
/*    */   public FerociousLeapAbility(AbilityCore<FerociousLeapAbility> core) {
/* 48 */     super(core);
/*    */     
/* 50 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.requireMorphComponent });
/*    */     
/* 52 */     this.continuousComponent.addStartEvent(100, this::startContinuityEvent);
/* 53 */     this.continuousComponent.addEndEvent(100, this::endContinuityEvent);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onLanding(LivingEntity entity) {
/* 58 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 3.5F);
/*    */     
/* 60 */     for (LivingEntity target : targets) {
/* 61 */       if (this.hitTrackerComponent.canHit((Entity)target)) {
/* 62 */         this.dealDamageComponent.hurtTarget(entity, target, 10.0F);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 68 */     this.cooldownComponent.startCooldown(entity, 160.0F);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 72 */     this.hitTrackerComponent.clearHits();
/* 73 */     Vector3d speed = WyHelper.propulsion(entity, 1.0D, 1.0D);
/* 74 */     AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, 1.4D, speed.field_72449_c);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\nekoleopard\FerociousLeapAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */