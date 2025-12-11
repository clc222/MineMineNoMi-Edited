/*    */ package xyz.pixelatedw.mineminenomi.abilities.zushi;
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
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.zushi.MokoProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ 
/*    */ public class MokoAbility extends Ability {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "moko", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("Sends a wave of gravitational waves towards the enemies, slowing them down.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 280;
/* 30 */   public static final AbilityCore<MokoAbility> INSTANCE = (new AbilityCore.Builder("Moko", AbilityCategory.DEVIL_FRUITS, MokoAbility::new))
/* 31 */     .addDescriptionLine(DESCRIPTION)
/* 32 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(280.0F)
/* 33 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 34 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 35 */     .setSourceElement(SourceElement.GRAVITY)
/* 36 */     .build();
/*    */   
/* 38 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addEndEvent(this::endContinuityEvent);
/* 39 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(this::triggerRepeaterEvent).addStopEvent(this::endRepeaterEvent);
/* 40 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/* 41 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public MokoAbility(AbilityCore<MokoAbility> core) {
/* 44 */     super(core);
/*    */     
/* 46 */     this.isNew = true;
/* 47 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.repeaterComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 49 */     addCanUseCheck(AbilityHelper::canUseSwordsmanAbilities);
/* 50 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 54 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void triggerRepeaterEvent(LivingEntity entity, IAbility ability) {
/* 58 */     this.projectileComponent.shoot(entity, 3.0F, 2.0F);
/*    */   }
/*    */   
/*    */   private void endRepeaterEvent(LivingEntity entity, IAbility ability) {
/* 62 */     this.continuousComponent.stopContinuity(entity);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 66 */     this.repeaterComponent.start(entity, 4, 6);
/* 67 */     this.animationComponent.start(entity, ModAnimations.AIM_SNIPER);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 71 */     this.animationComponent.stop(entity);
/* 72 */     this.cooldownComponent.startCooldown(entity, 280.0F);
/*    */   }
/*    */   
/*    */   private MokoProjectile createProjectile(LivingEntity entity) {
/* 76 */     MokoProjectile proj = new MokoProjectile(entity.field_70170_p, entity, this);
/* 77 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\zushi\MokoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */