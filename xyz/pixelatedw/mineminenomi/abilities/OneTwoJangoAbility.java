/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.TargetHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public class OneTwoJangoAbility extends Ability {
/* 30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "one_two_jango", new org.apache.commons.lang3.tuple.Pair[0]);
/*    */   
/*    */   private static final int CHARGE_TIME = 60;
/*    */   
/*    */   private static final int COOLDOWN = 200;
/*    */   public static final int RANGE = 15;
/* 36 */   public static final AbilityCore<OneTwoJangoAbility> INSTANCE = (new AbilityCore.Builder("One Two Jango", AbilityCategory.STYLE, OneTwoJangoAbility::new))
/* 37 */     .addDescriptionLine(DESCRIPTION)
/* 38 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), RangeComponent.getTooltip(15.0F, RangeComponent.RangeType.AOE)
/* 39 */       }).build();
/*    */   
/* 41 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(100, this::startChargeEvent).addEndEvent(100, this::endChargeEvent);
/* 42 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/* 43 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*    */   
/*    */   private boolean canAffectSelf = false;
/*    */   
/*    */   public OneTwoJangoAbility(AbilityCore<OneTwoJangoAbility> core) {
/* 48 */     super(core);
/*    */     
/* 50 */     this.isNew = true;
/* 51 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.rangeComponent });
/*    */     
/* 53 */     addCanUseCheck(OneTwoJangoAbility::canUse);
/*    */     
/* 55 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 59 */     this.chargeComponent.startCharging(entity, 60.0F);
/*    */   }
/*    */   
/*    */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/* 63 */     this.animationComponent.start(entity, ModAnimations.ONE_TWO_JANGO, 60);
/*    */   }
/*    */   
/*    */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/* 67 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, entity.func_233580_cy_(), 15.0F, TargetsPredicate.EVERYTHING);
/*    */     
/* 69 */     if (this.canAffectSelf) {
/* 70 */       targets.add(entity);
/*    */     }
/*    */     
/* 73 */     for (LivingEntity target : targets) {
/* 74 */       if (entity == target || (target instanceof net.minecraft.entity.player.PlayerEntity && TargetHelper.isEntityInView(target, (Entity)entity, 60.0F)) || (target instanceof MobEntity && 
/* 75 */         GoalUtil.canSee((MobEntity)target, entity))) {
/* 76 */         target.func_195064_c(new EffectInstance((Effect)ModEffects.UNCONSCIOUS.get(), 60, 0));
/*    */       }
/*    */     } 
/*    */     
/* 80 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*    */   }
/*    */   
/*    */   public void setAffectSelf(boolean canAffectSelf) {
/* 84 */     this.canAffectSelf = canAffectSelf;
/*    */   }
/*    */   
/*    */   public static AbilityUseResult canUse(LivingEntity entity, IAbility ability) {
/* 88 */     if (entity.func_184614_ca().func_190926_b() || !entity.func_184614_ca().func_77973_b().equals(ModWeapons.CHAKRAM.get())) {
/* 89 */       return AbilityUseResult.fail(null);
/*    */     }
/* 91 */     return AbilityUseResult.success();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\OneTwoJangoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */