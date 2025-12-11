/*    */ package xyz.pixelatedw.mineminenomi.abilities.brawler;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class ChargedPunchAbility
/*    */   extends Ability {
/*    */   private static final int COOLDOWN = 160;
/*    */   private static final int CHARGE_TIME = 20;
/*    */   private static final float DAMAGE = 20.0F;
/*    */   private static final int RANGE = 4;
/* 32 */   public static final AbilityCore<ChargedPunchAbility> INSTANCE = (new AbilityCore.Builder("Charged Punch", AbilityCategory.STYLE, ChargedPunchAbility::new))
/* 33 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(160.0F), ChargeComponent.getTooltip(20.0F), RangeComponent.getTooltip(4.0F, RangeComponent.RangeType.LINE), DealDamageComponent.getTooltip(20.0F)
/* 34 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 35 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 36 */       }).build();
/*    */   
/* 38 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::startChargeEvent).addEndEvent(this::stopChargeEvent);
/* 39 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/* 40 */   private final DealDamageComponent damageComponent = new DealDamageComponent((IAbility)this);
/* 41 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*    */   
/*    */   public ChargedPunchAbility(AbilityCore<ChargedPunchAbility> core) {
/* 44 */     super(core);
/*    */     
/* 46 */     this.isNew = true;
/* 47 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.damageComponent, (AbilityComponent)this.rangeComponent });
/*    */     
/* 49 */     addCanUseCheck(AbilityHelper::canUseBrawlerAbilities);
/* 50 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 54 */     this.chargeComponent.startCharging(entity, 20.0F);
/*    */   }
/*    */   
/*    */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/* 58 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 20, 0));
/* 59 */     this.animationComponent.start(entity, ModAnimations.CHARGE_PUNCH);
/*    */   }
/*    */   
/*    */   private void stopChargeEvent(LivingEntity entity, IAbility ability) {
/* 63 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInLine(entity, 4.0F, 2.0F);
/* 64 */     for (LivingEntity target : targets) {
/* 65 */       if (this.damageComponent.hurtTarget(entity, target, 20.0F)) {
/* 66 */         Vector3d dirVec = target.func_213303_ch().func_178788_d(entity.func_213303_ch()).func_72432_b().func_216372_d(2.5D, 1.0D, 2.5D);
/* 67 */         AbilityHelper.setDeltaMovement((Entity)target, dirVec.field_72450_a, 0.2D, dirVec.field_72449_c);
/*    */       } 
/*    */     } 
/* 70 */     this.animationComponent.stop(entity);
/* 71 */     this.cooldownComponent.startCooldown(entity, 160.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\brawler\ChargedPunchAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */