/*    */ package xyz.pixelatedw.mineminenomi.abilities.mera;
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
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.HiganProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ 
/*    */ public class HiganAbility extends Ability {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "higan", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("Turns the user's fingertips into flames and shoots bullets made of fire from them", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 180;
/* 30 */   public static final AbilityCore<HiganAbility> INSTANCE = (new AbilityCore.Builder("Higan", AbilityCategory.DEVIL_FRUITS, HiganAbility::new))
/* 31 */     .addDescriptionLine(DESCRIPTION)
/* 32 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(180.0F)
/* 33 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 34 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 35 */     .setSourceElement(SourceElement.FIRE)
/* 36 */     .build();
/*    */   
/* 38 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::onContinuityStart);
/* 39 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(this::onRepeaterTrigger).addStopEvent(this::onRepeaterStop);
/* 40 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/* 41 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   public HiganAbility(AbilityCore<HiganAbility> core) {
/* 44 */     super(core);
/*    */     
/* 46 */     this.isNew = true;
/*    */     
/* 48 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.repeaterComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 50 */     addCanUseCheck(MeraHelper::canUseMeraAbilities);
/*    */     
/* 52 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 56 */     if (this.continuousComponent.isContinuous()) {
/* 57 */       this.repeaterComponent.stop(entity);
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 62 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/* 66 */     if (entity.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/*    */     
/* 70 */     this.animationComponent.start(entity, ModAnimations.POINT_RIGHT_ARM);
/* 71 */     this.repeaterComponent.start(entity, 10, 2);
/*    */   }
/*    */   
/*    */   private void onRepeaterTrigger(LivingEntity entity, IAbility ability) {
/* 75 */     if (canUse(entity).isFail()) {
/* 76 */       this.repeaterComponent.stop(entity);
/*    */     }
/*    */     
/* 79 */     this.projectileComponent.shoot(entity, 2.0F, 1.0F);
/*    */   }
/*    */   
/*    */   private void onRepeaterStop(LivingEntity entity, IAbility ability) {
/* 83 */     this.continuousComponent.stopContinuity(entity);
/*    */     
/* 85 */     this.animationComponent.stop(entity);
/* 86 */     this.cooldownComponent.startCooldown(entity, 180.0F);
/*    */   }
/*    */   
/*    */   private HiganProjectile createProjectile(LivingEntity entity) {
/* 90 */     HiganProjectile proj = new HiganProjectile(entity.field_70170_p, entity, this);
/* 91 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\mera\HiganAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */