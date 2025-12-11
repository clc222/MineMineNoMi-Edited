/*    */ package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;
/*    */ import net.minecraft.entity.LivingEntity;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RepeaterComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.fishmankarate.UchimizuProjectile;
/*    */ 
/*    */ public class UchimizuAbility extends Ability {
/* 24 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "uchimizu", new Pair[] {
/* 25 */         (Pair)ImmutablePair.of("The user hurls big and fast water droplets at the opponent", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 80.0F;
/*    */   private static final float WATER_DAMAGE_MUL = 2.0F;
/* 30 */   public static final AbilityCore<UchimizuAbility> INSTANCE = (new AbilityCore.Builder("Uchimizu", AbilityCategory.RACIAL, UchimizuAbility::new))
/* 31 */     .addDescriptionLine(DESCRIPTION)
/* 32 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(80.0F), FishmanKarateHelper.getWaterBuffedProjectileDamageStat(2.0F)
/* 33 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/* 34 */     .setSourceElement(SourceElement.WATER)
/* 35 */     .setUnlockCheck(UchimizuAbility::canUnlock)
/* 36 */     .build();
/*    */   
/* 38 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(100, this::startContinuityEvent);
/* 39 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(100, this::onRepeaterTriggerEvent).addStopEvent(100, this::onRepeaterStopEvent);
/* 40 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public UchimizuAbility(AbilityCore<UchimizuAbility> core) {
/* 43 */     super(core);
/*    */     
/* 45 */     this.isNew = true;
/* 46 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.repeaterComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 48 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 52 */     if (this.continuousComponent.isContinuous()) {
/* 53 */       this.repeaterComponent.stop(entity);
/*    */       return;
/*    */     } 
/* 56 */     this.continuousComponent.startContinuity(entity);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 60 */     this.repeaterComponent.start(entity, 8, 2);
/*    */   }
/*    */   
/*    */   private void onRepeaterTriggerEvent(LivingEntity entity, IAbility ability) {
/* 64 */     this.projectileComponent.shoot(entity, 3.0F, 1.2F);
/*    */   }
/*    */   
/*    */   private void onRepeaterStopEvent(LivingEntity entity, IAbility ability) {
/* 68 */     this.continuousComponent.stopContinuity(entity);
/* 69 */     this.cooldownComponent.startCooldown(entity, 80.0F);
/*    */   }
/*    */   
/*    */   private UchimizuProjectile createProjectile(LivingEntity entity) {
/* 73 */     UchimizuProjectile proj = new UchimizuProjectile(entity.field_70170_p, entity);
/* 74 */     if (FishmanKarateHelper.isInWater(entity)) {
/* 75 */       proj.setDamage(proj.getDamage() * 2.0F);
/*    */     }
/* 77 */     return proj;
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity user) {
/* 81 */     IEntityStats props = EntityStatsCapability.get(user);
/* 82 */     return (props.isFishman() && props.getDoriki() >= 800.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\fishmankarate\UchimizuAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */