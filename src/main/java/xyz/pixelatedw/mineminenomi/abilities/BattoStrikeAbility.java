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
/*    */ public class BattoStrikeAbility
/*    */   extends Ability {
/*    */   private static final int CHARGE_TIME = 5;
/*    */   private static final int COOLDOWN = 300;
/* 29 */   public static final AbilityCore<BattoStrikeAbility> INSTANCE = (new AbilityCore.Builder("Batto Strike", AbilityCategory.STYLE, BattoStrikeAbility::new))
/* 30 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F), ChargeComponent.getTooltip(5.0F), RangeComponent.getTooltip(4.0F, RangeComponent.RangeType.LINE), DealDamageComponent.getTooltip(20.0F)
/* 31 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/* 32 */     .build(); private static final float DAMAGE = 20.0F;
/*    */   private static final int RANGE = 4;
/* 34 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::startChargeEvent).addEndEvent(this::stopChargeEvent);
/* 35 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/* 36 */   private final DealDamageComponent damageComponent = new DealDamageComponent((IAbility)this);
/* 37 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*    */   
/*    */   public BattoStrikeAbility(AbilityCore<ChargedCleaveAbility> core) {
/* 40 */     super(core);
/*    */     
/* 42 */     this.isNew = true;
/* 43 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.damageComponent, (AbilityComponent)this.rangeComponent });
/*    */     
/* 45 */     addCanUseCheck(AbilityHelper::requiresBluntWeapon);
/* 46 */     addCanUseCheck(AbilityHelper::requiresOnGround);
/* 47 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 51 */     this.chargeComponent.startCharging(entity, 5.0F);
/*    */   }
/*    */   
/*    */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/* 55 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 0));
/* 56 */     this.animationComponent.start(entity, ModAnimations.BATTO_STRIKE);
/*    */   }
/*    */   
/*    */   private void stopChargeEvent(LivingEntity entity, IAbility ability) {
/* 60 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInLine(entity, 4.0F, 2.0F);
/* 61 */     for (LivingEntity target : targets) {
/* 62 */       if (this.damageComponent.hurtTarget(entity, target, 20.0F)) {
/* 63 */         Vector3d dirVec = target.func_213303_ch().func_178788_d(entity.func_213303_ch()).func_72432_b().func_216372_d(2.5D, 1.0D, 2.5D);
/* 64 */         AbilityHelper.setDeltaMovement((Entity)target, dirVec.field_72450_a, 1.75D, dirVec.field_72449_c);
/*    */       } 
/*    */     } 
/* 67 */     this.animationComponent.stop(entity);
/* 68 */     this.cooldownComponent.startCooldown(entity, 300.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\BattoStrikeAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */