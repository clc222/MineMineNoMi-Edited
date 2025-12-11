/*    */ package xyz.pixelatedw.mineminenomi.abilities.gasu;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.mera.MeraHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RepeaterComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gasu.GasRobeProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ 
/*    */ public class GasRobeAbility extends Ability {
/* 26 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "gas_robe", new Pair[] {
/* 27 */         (Pair)ImmutablePair.of("Launches a cloud of poisonous gas at the opponent", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 200;
/* 31 */   public static final AbilityCore<GasRobeAbility> INSTANCE = (new AbilityCore.Builder("Gas Robe", AbilityCategory.DEVIL_FRUITS, GasRobeAbility::new))
/* 32 */     .addDescriptionLine(DESCRIPTION)
/* 33 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F)
/* 34 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 35 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 36 */     .setSourceElement(SourceElement.POISON)
/* 37 */     .build();
/*    */   
/* 39 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::onContinuityStart);
/* 40 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(this::onRepeaterTrigger).addStopEvent(this::onRepeaterStop);
/* 41 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/* 42 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   public GasRobeAbility(AbilityCore<GasRobeAbility> core) {
/* 45 */     super(core);
/*    */     
/* 47 */     this.isNew = true;
/*    */     
/* 49 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.repeaterComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 51 */     addCanUseCheck(MeraHelper::canUseMeraAbilities);
/*    */     
/* 53 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 57 */     if (this.continuousComponent.isContinuous()) {
/* 58 */       this.repeaterComponent.stop(entity);
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 63 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/* 67 */     if (entity.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/*    */     
/* 71 */     this.animationComponent.start(entity, ModAnimations.POINT_RIGHT_ARM);
/* 72 */     this.repeaterComponent.start(entity, 6, 3);
/*    */   }
/*    */   
/*    */   private void onRepeaterTrigger(LivingEntity entity, IAbility ability) {
/* 76 */     if (canUse(entity).isFail()) {
/* 77 */       this.repeaterComponent.stop(entity);
/*    */     }
/*    */     
/* 80 */     this.projectileComponent.shoot(entity, 2.5F, 1.5F);
/*    */   }
/*    */   
/*    */   private void onRepeaterStop(LivingEntity entity, IAbility ability) {
/* 84 */     this.continuousComponent.stopContinuity(entity);
/*    */     
/* 86 */     this.animationComponent.stop(entity);
/* 87 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*    */   }
/*    */   
/*    */   private GasRobeProjectile createProjectile(LivingEntity entity) {
/* 91 */     GasRobeProjectile proj = new GasRobeProjectile(entity.field_70170_p, entity, this);
/* 92 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gasu\GasRobeAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */