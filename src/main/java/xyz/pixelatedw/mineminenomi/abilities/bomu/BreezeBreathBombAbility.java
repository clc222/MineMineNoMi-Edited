/*    */ package xyz.pixelatedw.mineminenomi.abilities.bomu;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.bomu.BreezeBreathBombProjectile;
/*    */ 
/*    */ public class BreezeBreathBombAbility extends Ability {
/* 21 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "breeze_breath_bomb", new Pair[] {
/* 22 */         (Pair)ImmutablePair.of("Load a gun with explosive breath and shoot a chain explosion", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 300.0F;
/* 26 */   public static final AbilityCore<BreezeBreathBombAbility> INSTANCE = (new AbilityCore.Builder("Breeze Breath Bomb", AbilityCategory.DEVIL_FRUITS, BreezeBreathBombAbility::new))
/* 27 */     .addDescriptionLine(DESCRIPTION)
/* 28 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F), ContinuousComponent.getTooltip()
/* 29 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/* 30 */     .setSourceType(new SourceType[] { SourceType.BULLET
/* 31 */       }).build();
/*    */   
/* 33 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addEndEvent(100, this::endContinuityEvent);
/* 34 */   private final ProjectileComponent projectileComponent = (new ProjectileComponent((IAbility)this, this::createProjectile)).addAfterShootEvent(100, this::afterShootEvent);
/*    */   
/*    */   public BreezeBreathBombAbility(AbilityCore<BreezeBreathBombAbility> core) {
/* 37 */     super(core);
/*    */     
/* 39 */     this.isNew = true;
/* 40 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 42 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 46 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 50 */     this.cooldownComponent.startCooldown(entity, 300.0F);
/*    */   }
/*    */   
/*    */   private void afterShootEvent(LivingEntity entity) {
/* 54 */     this.continuousComponent.stopContinuity(entity);
/*    */   }
/*    */   
/*    */   public BreezeBreathBombProjectile createProjectile(LivingEntity entity) {
/* 58 */     BreezeBreathBombProjectile proj = new BreezeBreathBombProjectile(entity.field_70170_p, entity);
/* 59 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\bomu\BreezeBreathBombAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */