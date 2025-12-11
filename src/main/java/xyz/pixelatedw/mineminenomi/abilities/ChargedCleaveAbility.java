/*    */ package xyz.pixelatedw.mineminenomi.abilities;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class ChargedCleaveAbility
/*    */   extends Ability {
/*    */   private static final int CHARGE_TIME = 10;
/*    */   private static final int COOLDOWN = 100;
/*    */   private static final float DAMAGE = 20.0F;
/*    */   private static final int RANGE = 5;
/* 31 */   public static final AbilityCore<ChargedCleaveAbility> INSTANCE = (new AbilityCore.Builder("Charged Cleave", AbilityCategory.STYLE, ChargedCleaveAbility::new))
/* 32 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F), ChargeComponent.getTooltip(10.0F), RangeComponent.getTooltip(5.0F, RangeComponent.RangeType.LINE), DealDamageComponent.getTooltip(20.0F)
/* 33 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/* 34 */     .build();
/*    */   
/* 36 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::startChargeEvent).addEndEvent(this::stopChargeEvent);
/* 37 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/* 38 */   private final DealDamageComponent damageComponent = new DealDamageComponent((IAbility)this);
/* 39 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*    */   
/* 41 */   private int bleedingPower = 0;
/* 42 */   private int bleedingTime = 40;
/*    */   
/*    */   public ChargedCleaveAbility(AbilityCore<ChargedCleaveAbility> core) {
/* 45 */     super(core);
/*    */     
/* 47 */     this.isNew = true;
/* 48 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.damageComponent, (AbilityComponent)this.rangeComponent });
/*    */     
/* 50 */     addCanUseCheck(AbilityHelper::canUseWeaponAbilities);
/* 51 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   public void setBleedingPower(int power) {
/* 55 */     this.bleedingPower = power;
/*    */   }
/*    */   
/*    */   public void setBleedingTime(int time) {
/* 59 */     this.bleedingTime = time;
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 63 */     this.chargeComponent.startCharging(entity, 10.0F);
/*    */   }
/*    */   
/*    */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/* 67 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 10, 0));
/* 68 */     this.animationComponent.start(entity, ModAnimations.CHARGE_CLEAVE);
/*    */   }
/*    */   
/*    */   private void stopChargeEvent(LivingEntity entity, IAbility ability) {
/* 72 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInLine(entity, 5.0F, 2.0F);
/* 73 */     for (LivingEntity target : targets) {
/* 74 */       if (this.damageComponent.hurtTarget(entity, target, 20.0F)) {
/* 75 */         target.func_195064_c(new EffectInstance((Effect)ModEffects.BLEEDING.get(), this.bleedingTime, this.bleedingPower));
/* 76 */         Vector3d dirVec = target.func_213303_ch().func_178788_d(entity.func_213303_ch()).func_72432_b().func_216372_d(2.5D, 1.0D, 2.5D);
/* 77 */         AbilityHelper.setDeltaMovement((Entity)target, dirVec.field_72450_a, 0.2D, dirVec.field_72449_c);
/*    */       } 
/*    */     } 
/* 80 */     this.animationComponent.stop(entity);
/* 81 */     this.cooldownComponent.startCooldown(entity, 100.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ChargedCleaveAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */