/*    */ package xyz.pixelatedw.mineminenomi.abilities.beta;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SkinOverlayComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SwingTriggerComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.beta.BetaBetaChainProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BetaBetaChainAbility
/*    */   extends Ability {
/* 27 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "beta_beta_chain", new Pair[] {
/* 28 */         (Pair)ImmutablePair.of("The user shoots a mucus chain which will propel the user towards where it hits.", null)
/*    */       });
/*    */   
/*    */   private static final int HOLD_TIME = 600;
/*    */   private static final int COOLDOWN = 120;
/* 33 */   public static final AbilityCore<BetaBetaChainAbility> INSTANCE = (new AbilityCore.Builder("Beta Beta Chain", AbilityCategory.DEVIL_FRUITS, BetaBetaChainAbility::new))
/* 34 */     .addDescriptionLine(DESCRIPTION)
/* 35 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE
/* 36 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/* 37 */     .setSourceElement(SourceElement.SLIME)
/* 38 */     .build();
/*    */   
/* 40 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setOverlayPart(AbilityOverlay.OverlayPart.ARM).setTexture(ModResources.BETA_COATING).setColor(WyHelper.hexToRGB("#FFFFFFA6")).build();
/*    */   
/* 42 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::startContinuityEvent).addEndEvent(this::endContinuityEvent);
/* 43 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/* 44 */   private final SwingTriggerComponent swingTriggerComponent = (new SwingTriggerComponent((IAbility)this)).addSwingEvent(this::swingEvent);
/* 45 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/*    */   
/*    */   public BetaBetaChainAbility(AbilityCore<BetaBetaChainAbility> core) {
/* 48 */     super(core);
/*    */     
/* 50 */     this.isNew = true;
/* 51 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.projectileComponent, (AbilityComponent)this.swingTriggerComponent, (AbilityComponent)this.skinOverlayComponent });
/*    */     
/* 53 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 57 */     this.continuousComponent.triggerContinuity(entity, 600.0F);
/*    */   }
/*    */   
/*    */   private void swingEvent(LivingEntity entity, IAbility ability) {
/* 61 */     this.continuousComponent.stopContinuity(entity);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 65 */     this.skinOverlayComponent.showAll(entity);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 69 */     this.skinOverlayComponent.hideAll(entity);
/*    */     
/* 71 */     this.projectileComponent.shoot(entity, 4.0F, 1.0F);
/* 72 */     this.cooldownComponent.startCooldown(entity, 120.0F);
/*    */     
/* 74 */     AbilityHelper.slowEntityFall(entity, 2);
/*    */   }
/*    */   
/*    */   private BetaBetaChainProjectile createProjectile(LivingEntity entity) {
/* 78 */     BetaBetaChainProjectile projectile = new BetaBetaChainProjectile(entity.field_70170_p, entity, this);
/* 79 */     return projectile;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\beta\BetaBetaChainAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */