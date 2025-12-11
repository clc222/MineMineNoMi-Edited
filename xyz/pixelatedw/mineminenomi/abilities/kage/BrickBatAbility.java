/*    */ package xyz.pixelatedw.mineminenomi.abilities.kage;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.kage.BrickBatProjectile;
/*    */ 
/*    */ public class BrickBatAbility
/*    */   extends Ability
/*    */ {
/*    */   private static final float COOLDOWN = 100.0F;
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "brick_bat", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("Launches bats created from the user's shadow at the opponent", null)
/*    */       });
/* 28 */   public static final AbilityCore<BrickBatAbility> INSTANCE = (new AbilityCore.Builder("Brick Bat", AbilityCategory.DEVIL_FRUITS, BrickBatAbility::new))
/* 29 */     .addDescriptionLine(DESCRIPTION)
/* 30 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F)
/* 31 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 32 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 33 */     .build();
/*    */   
/* 35 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(100, this::startContinuityEvent);
/* 36 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(100, this::onRepeaterTrigger).addStopEvent(100, this::onRepeaterStop);
/* 37 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public BrickBatAbility(AbilityCore<BrickBatAbility> core) {
/* 40 */     super(core);
/*    */     
/* 42 */     this.isNew = true;
/* 43 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.repeaterComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 45 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 49 */     if (this.continuousComponent.isContinuous()) {
/* 50 */       this.repeaterComponent.stop(entity);
/*    */       return;
/*    */     } 
/* 53 */     this.continuousComponent.startContinuity(entity);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 57 */     this.repeaterComponent.start(entity, 6, 3);
/*    */   }
/*    */   
/*    */   private void onRepeaterTrigger(LivingEntity entity, IAbility ability) {
/* 61 */     this.projectileComponent.shoot(entity, 2.0F, 1.5F);
/*    */   }
/*    */   
/*    */   private void onRepeaterStop(LivingEntity entity, IAbility ability) {
/* 65 */     this.continuousComponent.stopContinuity(entity);
/* 66 */     this.cooldownComponent.startCooldown(entity, 100.0F);
/*    */   }
/*    */   
/*    */   private BrickBatProjectile createProjectile(LivingEntity entity) {
/* 70 */     BrickBatProjectile proj = new BrickBatProjectile(entity.field_70170_p, entity, this);
/* 71 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\kage\BrickBatAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */