/*    */ package xyz.pixelatedw.mineminenomi.abilities.hie;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RepeaterComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.hie.IceBlockPartisanProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ 
/*    */ public class IceBlockPartisanAbility extends Ability {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "ice_block_partisan", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("Creates several spears of ice that the user hurls at the enemy", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 200;
/* 30 */   public static final AbilityCore<IceBlockPartisanAbility> INSTANCE = (new AbilityCore.Builder("Ice Block: Partisan", AbilityCategory.DEVIL_FRUITS, IceBlockPartisanAbility::new))
/* 31 */     .addDescriptionLine(DESCRIPTION)
/* 32 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F)
/* 33 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 34 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 35 */     .setSourceElement(SourceElement.ICE)
/* 36 */     .build();
/*    */   
/* 38 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::onContinuityStart);
/* 39 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(this::onRepeaterTrigger).addStopEvent(this::onRepeaterStop);
/* 40 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/* 41 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   public IceBlockPartisanAbility(AbilityCore<IceBlockPartisanAbility> core) {
/* 44 */     super(core);
/*    */     
/* 46 */     this.isNew = true;
/*    */     
/* 48 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.repeaterComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 50 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 54 */     if (this.continuousComponent.isContinuous()) {
/* 55 */       this.repeaterComponent.stop(entity);
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 60 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/* 64 */     if (entity.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/*    */     
/* 68 */     this.animationComponent.start(entity, ModAnimations.POINT_RIGHT_ARM);
/* 69 */     this.repeaterComponent.start(entity, 8, 4);
/*    */   }
/*    */   
/*    */   private void onRepeaterTrigger(LivingEntity entity, IAbility ability) {
/* 73 */     if (canUse(entity).isFail()) {
/* 74 */       this.repeaterComponent.stop(entity);
/*    */     }
/*    */     
/* 77 */     this.projectileComponent.shoot(entity, 2.0F, 1.0F);
/*    */   }
/*    */   
/*    */   private void onRepeaterStop(LivingEntity entity, IAbility ability) {
/* 81 */     this.continuousComponent.stopContinuity(entity);
/*    */     
/* 83 */     this.animationComponent.stop(entity);
/* 84 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*    */   }
/*    */   
/*    */   private IceBlockPartisanProjectile createProjectile(LivingEntity entity) {
/* 88 */     IceBlockPartisanProjectile proj = new IceBlockPartisanProjectile(entity.field_70170_p, entity, this);
/* 89 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\hie\IceBlockPartisanAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */