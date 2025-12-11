/*    */ package xyz.pixelatedw.mineminenomi.abilities.sai;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
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
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class HornDashAbility extends Ability {
/* 31 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "horn_dash", new Pair[] {
/* 32 */         (Pair)ImmutablePair.of("Running into enemies deals damage and knocks them back.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 180;
/*    */   private static final int HOLD_TIME = 20;
/*    */   private static final float AREA_SIZE = 1.7F;
/*    */   private static final float DAMAGE = 10.0F;
/* 39 */   public static final AbilityCore<HornDashAbility> INSTANCE = (new AbilityCore.Builder("Horn Dash", AbilityCategory.DEVIL_FRUITS, HornDashAbility::new))
/* 40 */     .addDescriptionLine(DESCRIPTION)
/* 41 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/* 42 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(180.0F), ContinuousComponent.getTooltip(20.0F), DealDamageComponent.getTooltip(10.0F)
/* 43 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 44 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 45 */       }).build();
/*    */   
/* 47 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addEndEvent(100, this::stopContinuityEvent).addTickEvent(100, this::tickContinuityEvent);
/* 48 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/* 49 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/* 50 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/* 51 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.SAI_HEAVY.get(), new MorphInfo[] { (MorphInfo)ModMorphs.SAI_WALK.get() });
/*    */   
/*    */   public HornDashAbility(AbilityCore<HornDashAbility> core) {
/* 54 */     super(core);
/*    */     
/* 56 */     this.isNew = true;
/* 57 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.requireMorphComponent });
/*    */     
/* 59 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*    */     
/* 61 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 65 */     this.hitTrackerComponent.clearHits();
/*    */     
/* 67 */     Vector3d speed = WyHelper.propulsion(entity, 3.0D, 3.0D);
/* 68 */     AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, 0.2D, speed.field_72449_c);
/* 69 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.DASH_ABILITY_SWOOSH_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/* 70 */     this.continuousComponent.startContinuity(entity, 20.0F);
/*    */   }
/*    */   
/*    */   private void stopContinuityEvent(LivingEntity entity, IAbility ability) {
/* 74 */     this.cooldownComponent.startCooldown(entity, 180.0F);
/*    */   }
/*    */   
/*    */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/* 78 */     List<LivingEntity> list = this.rangeComponent.getTargetsInArea(entity, 1.7F);
/* 79 */     for (LivingEntity target : list) {
/* 80 */       if (this.hitTrackerComponent.canHit((Entity)target) && this.dealDamageComponent.hurtTarget(entity, target, 10.0F)) {
/* 81 */         Vector3d speed = WyHelper.propulsion(entity, 3.0D, 3.0D);
/* 82 */         AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, 0.5D, speed.field_72449_c);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\sai\HornDashAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */