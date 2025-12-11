/*    */ package xyz.pixelatedw.mineminenomi.abilities.nikyu;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RepeaterComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu.PadHoProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ 
/*    */ public class TsuppariPadHoAbility extends Ability {
/* 29 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "tsuppari_pad_ho", new Pair[] {
/* 30 */         (Pair)ImmutablePair.of("Launches a barrage of paw-shaped shockwaves at the opponent", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 300;
/* 34 */   public static final AbilityCore<TsuppariPadHoAbility> INSTANCE = (new AbilityCore.Builder("Tsuppari Pad Ho", AbilityCategory.DEVIL_FRUITS, TsuppariPadHoAbility::new))
/* 35 */     .addDescriptionLine(DESCRIPTION)
/* 36 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F)
/* 37 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 38 */     .setSourceElement(SourceElement.SHOCKWAVE)
/* 39 */     .build();
/*    */   
/* 41 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/* 42 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/* 43 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(this::triggerRepeaterEvent).addStopEvent(this::stopRepeaterEvent);
/* 44 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/* 45 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public TsuppariPadHoAbility(AbilityCore<TsuppariPadHoAbility> core) {
/* 48 */     super(core);
/*    */     
/* 50 */     this.isNew = true;
/* 51 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.repeaterComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 53 */     addCanUseCheck(AbilityHelper::requiresOnGround);
/* 54 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 58 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 62 */     this.repeaterComponent.start(entity, 5, 4);
/* 63 */     this.animationComponent.start(entity, ModAnimations.PUNCH_RUSH);
/*    */     
/* 65 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.MULTIPLE_PAD_HO_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*    */   }
/*    */   
/*    */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 69 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 2, 1, false, false));
/*    */   }
/*    */   
/*    */   private void triggerRepeaterEvent(LivingEntity entity, IAbility ability) {
/* 73 */     for (int i = 0; i < 3; i++) {
/* 74 */       this.projectileComponent.shoot(entity, 3.0F, 15.0F);
/*    */     }
/*    */   }
/*    */   
/*    */   private void stopRepeaterEvent(LivingEntity entity, IAbility ability) {
/* 79 */     this.continuousComponent.stopContinuity(entity);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 83 */     this.cooldownComponent.startCooldown(entity, 300.0F);
/* 84 */     this.animationComponent.stop(entity);
/*    */   }
/*    */   
/*    */   private PadHoProjectile createProjectile(LivingEntity entity) {
/* 88 */     PadHoProjectile proj = new PadHoProjectile(entity.field_70170_p, entity, this);
/* 89 */     proj.setDamage(5.0F);
/* 90 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\nikyu\TsuppariPadHoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */