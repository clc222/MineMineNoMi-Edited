/*    */ package xyz.pixelatedw.mineminenomi.abilities.yami;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.yami.BlackRoadProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BlackRoadAbility extends Ability {
/* 26 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "black_road", new Pair[] {
/* 27 */         (Pair)ImmutablePair.of("The user spreads darkness in a forward path.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 400;
/*    */   private static final int CHARGE_TIME = 120;
/*    */   private static final int RANGE = 24;
/* 33 */   public static final AbilityCore<BlackRoadAbility> INSTANCE = (new AbilityCore.Builder("Black Road", AbilityCategory.DEVIL_FRUITS, BlackRoadAbility::new))
/* 34 */     .addDescriptionLine(DESCRIPTION)
/* 35 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(400.0F), ChargeComponent.getTooltip(120.0F), RangeComponent.getTooltip(24.0F, RangeComponent.RangeType.LINE)
/* 36 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 37 */     .setSourceElement(SourceElement.GRAVITY)
/* 38 */     .build();
/*    */   
/*    */   private final ChargeComponent chargeComponent;
/*    */   
/*    */   private final ProjectileComponent projectileComponent;
/*    */   private Interval particleInterval;
/*    */   
/*    */   public BlackRoadAbility(AbilityCore<BlackRoadAbility> core) {
/* 46 */     super(core); this.chargeComponent = (new ChargeComponent((IAbility)this, comp -> (comp.getChargePercentage() > 0.2D))).addStartEvent(100, this::startChargeEvent).addTickEvent(this::duringChargeEvent).addEndEvent(this::endChargeEvent); this.projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */     this.particleInterval = new Interval(2);
/* 48 */     this.isNew = true;
/* 49 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 51 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 55 */     this.chargeComponent.startCharging(entity, 120.0F);
/*    */   }
/*    */   
/*    */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/* 59 */     this.particleInterval.restartIntervalToZero();
/*    */   }
/*    */   
/*    */   private void duringChargeEvent(LivingEntity entity, IAbility ability) {
/* 63 */     if (this.particleInterval.canTick()) {
/* 64 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.DARK_MATTER_CHARGING.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*    */     }
/*    */   }
/*    */   
/*    */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/* 69 */     this.projectileComponent.shoot(entity, 1.1F, 1.0F);
/*    */     
/* 71 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.DARK_MATTER_CHARGING.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*    */     
/* 73 */     this.cooldownComponent.startCooldown(entity, 400.0F);
/*    */   }
/*    */   
/*    */   private BlackRoadProjectile createProjectile(LivingEntity entity) {
/* 77 */     int range = (int)(24.0F * this.chargeComponent.getChargePercentage());
/*    */     
/* 79 */     BlackRoadProjectile proj = new BlackRoadProjectile(entity.field_70170_p, entity, this);
/* 80 */     proj.setLife(range);
/* 81 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\yami\BlackRoadAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */