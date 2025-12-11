/*    */ package xyz.pixelatedw.mineminenomi.abilities.supa;
/*    */ 
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SkinOverlayComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class SpiderAbility extends Ability {
/* 28 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "spider", new Pair[] {
/* 29 */         (Pair)ImmutablePair.of("Hardens the user's body to protect themselves, but they're unable to move", null)
/*    */       });
/*    */   
/*    */   private static final float HOLD_TIME = 300.0F;
/*    */   private static final float MIN_COOLDOWN = 30.0F;
/*    */   private static final float MAX_COOLDOWN = 330.0F;
/* 35 */   public static final AbilityCore<SpiderAbility> INSTANCE = (new AbilityCore.Builder("Spider", AbilityCategory.DEVIL_FRUITS, SpiderAbility::new))
/* 36 */     .addDescriptionLine(DESCRIPTION)
/* 37 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(30.0F, 330.0F), ContinuousComponent.getTooltip(300.0F)
/* 38 */       }).build();
/*    */   
/* 40 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setColor(new Color(100, 100, 100, 70)).build();
/*    */   
/* 42 */   public final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::onContinuityStops);
/* 43 */   public final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.TEKKAI_LIKE, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/* 44 */   public final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/* 45 */   public final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   public SpiderAbility(AbilityCore<SpiderAbility> core) {
/* 48 */     super(core);
/*    */     
/* 50 */     this.isNew = true;
/* 51 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.poolComponent, (AbilityComponent)this.skinOverlayComponent, (AbilityComponent)this.animationComponent });
/*    */     
/* 53 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 57 */     this.continuousComponent.triggerContinuity(entity, 300.0F);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 61 */     this.animationComponent.start(entity, ModAnimations.CROSSED_ARMS);
/* 62 */     this.skinOverlayComponent.showAll(entity);
/*    */   }
/*    */   
/*    */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 66 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.GUARDING.get(), 2, 3, false, false));
/*    */   }
/*    */   
/*    */   private void onContinuityStops(LivingEntity entity, IAbility ability) {
/* 70 */     float cooldown = 30.0F + this.continuousComponent.getContinueTime();
/* 71 */     this.animationComponent.stop(entity);
/* 72 */     this.cooldownComponent.startCooldown(entity, cooldown);
/* 73 */     this.skinOverlayComponent.hideAll(entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\supa\SpiderAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */