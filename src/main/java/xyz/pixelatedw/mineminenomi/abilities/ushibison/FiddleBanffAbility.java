/*    */ package xyz.pixelatedw.mineminenomi.abilities.ushibison;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class FiddleBanffAbility extends Ability {
/* 30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "fiddle_banff", new Pair[] {
/* 31 */         (Pair)ImmutablePair.of("Running into enemies deals damage and knocks them back.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 140;
/*    */   private static final int CONTINUITY = 20;
/*    */   private static final float AREA_SIZE = 1.6F;
/*    */   private static final float DAMAGE = 15.0F;
/* 38 */   public static final AbilityCore<FiddleBanffAbility> INSTANCE = (new AbilityCore.Builder("Fiddle Banff", AbilityCategory.DEVIL_FRUITS, FiddleBanffAbility::new))
/* 39 */     .addDescriptionLine(DESCRIPTION)
/* 40 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/* 41 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(140.0F), ContinuousComponent.getTooltip(20.0F), RangeComponent.getTooltip(1.6F, RangeComponent.RangeType.AOE), DealDamageComponent.getTooltip(15.0F)
/* 42 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 43 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 44 */       }).build();
/*    */   
/* 46 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addEndEvent(100, this::stopContinuityEvent).addTickEvent(100, this::tickContinuityEvent);
/* 47 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/* 48 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/* 49 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/* 50 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.BISON_HEAVY.get(), new MorphInfo[] { (MorphInfo)ModMorphs.BISON_WALK.get() });
/*    */   
/*    */   public FiddleBanffAbility(AbilityCore<FiddleBanffAbility> core) {
/* 53 */     super(core);
/*    */     
/* 55 */     this.isNew = true;
/* 56 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.requireMorphComponent });
/*    */     
/* 58 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*    */     
/* 60 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 64 */     this.hitTrackerComponent.clearHits();
/*    */     
/* 66 */     Vector3d speed = WyHelper.propulsion(entity, 5.0D, 5.0D);
/* 67 */     AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, entity.func_213322_ci().func_82617_b(), speed.field_72449_c);
/* 68 */     this.continuousComponent.startContinuity(entity, 20.0F);
/*    */   }
/*    */   
/*    */   private void stopContinuityEvent(LivingEntity entity, IAbility ability) {
/* 72 */     this.cooldownComponent.startCooldown(entity, 140.0F);
/*    */   }
/*    */   
/*    */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/* 76 */     List<LivingEntity> list = this.rangeComponent.getTargetsInArea(entity, 1.6F);
/* 77 */     for (LivingEntity target : list) {
/* 78 */       if (this.hitTrackerComponent.canHit((Entity)target))
/* 79 */         this.dealDamageComponent.hurtTarget(entity, target, 15.0F); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilitie\\ushibison\FiddleBanffAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */