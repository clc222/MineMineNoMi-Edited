/*    */ package xyz.pixelatedw.mineminenomi.abilities.goe;
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
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goe.DragonsRoarProjectile;
/*    */ 
/*    */ public class DragonsRoarAbility extends Ability {
/* 23 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "dragons_roar", new Pair[] {
/* 24 */         (Pair)ImmutablePair.of("The user shouts and creates a series of powerful sound shockwaves.", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 160.0F;
/* 28 */   public static final AbilityCore<DragonsRoarAbility> INSTANCE = (new AbilityCore.Builder("Dragon's Roar", AbilityCategory.DEVIL_FRUITS, DragonsRoarAbility::new))
/* 29 */     .addDescriptionLine(DESCRIPTION)
/* 30 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(160.0F)
/* 31 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 32 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 33 */     .setSourceElement(SourceElement.SHOCKWAVE)
/* 34 */     .build();
/*    */   
/* 36 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(100, this::startContinuityEvent);
/* 37 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/* 38 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(100, this::triggerRepeaterEvent).addStopEvent(100, this::stopRepeaterEvent);
/*    */   
/*    */   public DragonsRoarAbility(AbilityCore<DragonsRoarAbility> core) {
/* 41 */     super(core);
/*    */     
/* 43 */     this.isNew = true;
/* 44 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.projectileComponent, (AbilityComponent)this.repeaterComponent });
/*    */     
/* 46 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 50 */     if (this.continuousComponent.isContinuous()) {
/* 51 */       this.repeaterComponent.stop(entity);
/*    */       return;
/*    */     } 
/* 54 */     this.continuousComponent.startContinuity(entity);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 58 */     this.repeaterComponent.start(entity, 10, 6);
/*    */   }
/*    */   
/*    */   private void triggerRepeaterEvent(LivingEntity entity, IAbility ability) {
/* 62 */     this.projectileComponent.shoot(entity, 2.5F, 1.0F);
/*    */   }
/*    */   
/*    */   private void stopRepeaterEvent(LivingEntity entity, IAbility ability) {
/* 66 */     this.continuousComponent.stopContinuity(entity);
/* 67 */     this.cooldownComponent.startCooldown(entity, 160.0F);
/*    */   }
/*    */   
/*    */   private DragonsRoarProjectile createProjectile(LivingEntity entity) {
/* 71 */     float size = (Math.abs(this.repeaterComponent.getMaxTriggers() - this.repeaterComponent.getTriggerCount() - 10) * 2);
/* 72 */     DragonsRoarProjectile proj = new DragonsRoarProjectile(entity.field_70170_p, entity, size, this);
/* 73 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\goe\DragonsRoarAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */