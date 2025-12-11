/*    */ package xyz.pixelatedw.mineminenomi.abilities.doku;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SkinOverlayComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ 
/*    */ public class DokuFuguAbility extends Ability {
/* 27 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "doku_fugu", new Pair[] {
/* 28 */         (Pair)ImmutablePair.of("The user covers themselves in poison creating a thin protective layer to damage. Cannot be used while %s is active.", new Object[] { VenomDemonAbility.INSTANCE })
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 600;
/*    */   private static final int HOLD_TIME = 1200;
/* 33 */   public static final AbilityCore<DokuFuguAbility> INSTANCE = (new AbilityCore.Builder("Doku Fugu", AbilityCategory.DEVIL_FRUITS, DokuFuguAbility::new))
/* 34 */     .addDescriptionLine(DESCRIPTION)
/* 35 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(600.0F), ContinuousComponent.getTooltip(1200.0F)
/* 36 */       }).build();
/*    */   
/* 38 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setTexture(ModResources.DOKU_COATING).setColor(new Color(1.0F, 1.0F, 1.0F, 0.6F)).build();
/*    */   
/* 40 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/* 41 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/*    */   
/*    */   public DokuFuguAbility(AbilityCore<DokuFuguAbility> core) {
/* 44 */     super(core);
/*    */     
/* 46 */     this.isNew = true;
/* 47 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.skinOverlayComponent });
/*    */     
/* 49 */     addCanUseCheck((entity, ability) -> ((MorphInfo)ModMorphs.VENOM_DEMON.get()).isActive(entity) ? AbilityUseResult.fail(null) : AbilityUseResult.success());
/* 50 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 54 */     this.continuousComponent.triggerContinuity(entity, 1200.0F);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 58 */     this.skinOverlayComponent.showAll(entity);
/*    */   }
/*    */   
/*    */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 62 */     if (canUse(entity).isFail()) {
/* 63 */       this.continuousComponent.stopContinuity(entity);
/*    */     }
/*    */     
/* 66 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.PHYSICAL_MOVING_GUARD.get(), 2, 2, false, false));
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 70 */     this.skinOverlayComponent.hideAll(entity);
/* 71 */     this.cooldownComponent.startCooldown(entity, 600.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\doku\DokuFuguAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */