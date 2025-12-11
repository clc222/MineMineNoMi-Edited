/*    */ package xyz.pixelatedw.mineminenomi.abilities.awa;
/*    */ 
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
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.awa.RelaxHourProjectile;
/*    */ 
/*    */ public class RelaxHourAbility extends Ability {
/* 23 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "relax_hour", new Pair[] {
/* 24 */         (Pair)ImmutablePair.of("Fires a barrage of cleaning bubbles, leaving their targets weakened and immobile", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 80.0F;
/* 28 */   public static final AbilityCore<RelaxHourAbility> INSTANCE = (new AbilityCore.Builder("Relax Hour", AbilityCategory.DEVIL_FRUITS, RelaxHourAbility::new))
/* 29 */     .addDescriptionLine(DESCRIPTION)
/* 30 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(80.0F)
/* 31 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 32 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 33 */     .setSourceElement(SourceElement.WATER)
/* 34 */     .build();
/*    */   
/* 36 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(100, this::startContinuityEvent);
/* 37 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(this::onRepeaterTrigger).addStopEvent(this::onRepeaterStop);
/* 38 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public RelaxHourAbility(AbilityCore<RelaxHourAbility> core) {
/* 41 */     super(core);
/*    */     
/* 43 */     this.isNew = true;
/*    */     
/* 45 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.repeaterComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 47 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 51 */     if (this.continuousComponent.isContinuous()) {
/* 52 */       this.repeaterComponent.stop(entity);
/*    */       
/*    */       return;
/*    */     } 
/* 56 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 60 */     this.repeaterComponent.start(entity, 5, 5);
/*    */   }
/*    */   
/*    */   private void onRepeaterTrigger(LivingEntity entity, IAbility ability) {
/* 64 */     this.projectileComponent.shoot(entity, 1.0F, 1.0F);
/*    */   }
/*    */   
/*    */   private void onRepeaterStop(LivingEntity entity, IAbility ability) {
/* 68 */     this.continuousComponent.stopContinuity(entity);
/* 69 */     this.cooldownComponent.startCooldown(entity, 80.0F);
/*    */   }
/*    */   
/*    */   private RelaxHourProjectile createProjectile(LivingEntity entity) {
/* 73 */     RelaxHourProjectile proj = new RelaxHourProjectile(entity.field_70170_p, entity);
/* 74 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\awa\RelaxHourAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */