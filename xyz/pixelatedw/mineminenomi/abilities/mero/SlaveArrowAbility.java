/*    */ package xyz.pixelatedw.mineminenomi.abilities.mero;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RepeaterComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.mero.SlaveArrowProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class SlaveArrowAbility extends Ability {
/* 30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "slave_arrow", new Pair[] {
/* 31 */         (Pair)ImmutablePair.of("Creates a big heart from which the user shoots multiple love arrows, petrifying enemies.", null)
/*    */       });
/*    */   
/*    */   private static final int CHARGE_TIME = 60;
/*    */   private static final int COOLDOWN = 260;
/* 36 */   public static final AbilityCore<SlaveArrowAbility> INSTANCE = (new AbilityCore.Builder("Slave Arrow", AbilityCategory.DEVIL_FRUITS, SlaveArrowAbility::new))
/* 37 */     .addDescriptionLine(DESCRIPTION)
/* 38 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(260.0F), ChargeComponent.getTooltip(60.0F)
/* 39 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 40 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 41 */     .build();
/*    */   
/* 43 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::startChargingEvent).addTickEvent(this::duringChargingEvent).addEndEvent(this::endChargingEvent);
/* 44 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addEndEvent(this::endContinuityEvent);
/* 45 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addStopEvent(this::endRepeaterEvent).addTriggerEvent(this::triggerRepeaterEvent);
/* 46 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/* 47 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   public SlaveArrowAbility(AbilityCore<SlaveArrowAbility> core) {
/* 50 */     super(core);
/*    */     
/* 52 */     this.isNew = true;
/* 53 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.continuousComponent, (AbilityComponent)this.repeaterComponent, (AbilityComponent)this.projectileComponent, (AbilityComponent)this.animationComponent });
/*    */     
/* 55 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 59 */     this.chargeComponent.startCharging(entity, 60.0F);
/*    */   }
/*    */   
/*    */   private void startChargingEvent(LivingEntity entity, IAbility ability) {
/* 63 */     this.animationComponent.start(entity, ModAnimations.AIM_SNIPER);
/* 64 */     Vector3d look = entity.func_70040_Z().func_72432_b().func_186678_a(2.5D);
/* 65 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.SLAVE_ARROW.get(), (Entity)entity, entity.func_226277_ct_() + look.field_72450_a, entity.func_226278_cu_() + 0.25D + look.field_72448_b, entity.func_226281_cx_() + look.field_72449_c);
/*    */   }
/*    */   
/*    */   private void duringChargingEvent(LivingEntity entity, IAbility ability) {
/* 69 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 1, false, false));
/*    */   }
/*    */   
/*    */   private void endChargingEvent(LivingEntity entity, IAbility ability) {
/* 73 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 77 */     this.repeaterComponent.start(entity, 10, 2);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 81 */     this.animationComponent.stop(entity);
/* 82 */     this.cooldownComponent.startCooldown(entity, 260.0F);
/*    */   }
/*    */   
/*    */   private void triggerRepeaterEvent(LivingEntity entity, IAbility ability) {
/* 86 */     this.projectileComponent.shoot(entity, 3.0F, 4.0F);
/*    */   }
/*    */   
/*    */   private void endRepeaterEvent(LivingEntity entity, IAbility ability) {
/* 90 */     this.continuousComponent.stopContinuity(entity);
/*    */   }
/*    */   
/*    */   private SlaveArrowProjectile createProjectile(LivingEntity entity) {
/* 94 */     SlaveArrowProjectile proj = new SlaveArrowProjectile(entity.field_70170_p, entity, this);
/* 95 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\mero\SlaveArrowAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */