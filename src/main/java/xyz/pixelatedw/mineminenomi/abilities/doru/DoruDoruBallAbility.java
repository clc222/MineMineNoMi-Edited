/*    */ package xyz.pixelatedw.mineminenomi.abilities.doru;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class DoruDoruBallAbility extends Ability {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "doru_doru_ball", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("Puts the user into a hardened wax ball for max defense", null)
/*    */       });
/*    */   
/*    */   private static final float HOLD_TIME = 200.0F;
/*    */   private static final float MIN_COOLDOWN = 30.0F;
/*    */   private static final float MAX_COOLDOWN = 230.0F;
/* 32 */   public static final AbilityCore<DoruDoruBallAbility> INSTANCE = (new AbilityCore.Builder("Doru Doru Ball", AbilityCategory.DEVIL_FRUITS, DoruDoruBallAbility::new))
/* 33 */     .addDescriptionLine(DESCRIPTION)
/* 34 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(30.0F, 230.0F), ContinuousComponent.getTooltip(200.0F)
/* 35 */       }).build();
/*    */   
/* 37 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(100, this::startContinuityEvent).addTickEvent(100, this::tickContinuityEvent).addEndEvent(100, this::endContinuityEvent);
/* 38 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.TEKKAI_LIKE, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*    */   
/* 40 */   public double rotateAngleX = 0.0D;
/* 41 */   public double rotateAngleZ = 0.0D;
/*    */   
/*    */   public DoruDoruBallAbility(AbilityCore<DoruDoruBallAbility> core) {
/* 44 */     super(core);
/*    */     
/* 46 */     this.isNew = true;
/* 47 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.poolComponent });
/*    */     
/* 49 */     addUseEvent(100, this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 53 */     this.continuousComponent.triggerContinuity(entity, 200.0F);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 57 */     this.rotateAngleX = 0.0D;
/* 58 */     this.rotateAngleZ = 0.0D;
/*    */   }
/*    */   
/*    */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/* 62 */     entity.func_195064_c(new EffectInstance(Effects.field_76421_d, 20, 5, false, false));
/* 63 */     entity.func_195064_c(new EffectInstance(Effects.field_76419_f, 20, 5, false, false));
/*    */     
/* 65 */     int power = 5;
/* 66 */     CandleChampionAbility candleChampionAbility = (CandleChampionAbility)AbilityDataCapability.get(entity).getEquippedAbility(CandleChampionAbility.INSTANCE);
/* 67 */     if (candleChampionAbility != null) {
/* 68 */       boolean isAcitve = ((Boolean)candleChampionAbility.getComponent(ModAbilityKeys.CONTINUOUS).map(ContinuousComponent::isContinuous).orElse(Boolean.valueOf(false))).booleanValue();
/* 69 */       if (isAcitve) {
/* 70 */         power = 10;
/*    */       }
/*    */     } 
/*    */     
/* 74 */     if (!entity.func_70027_ad()) {
/* 75 */       entity.func_195064_c(new EffectInstance((Effect)ModEffects.GUARDING_WITH_MOVEMENT.get(), 2, power, false, false));
/*    */     }
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 80 */     this.rotateAngleX = 0.0D;
/* 81 */     this.rotateAngleZ = 0.0D;
/* 82 */     entity.func_195063_d(Effects.field_76421_d);
/* 83 */     entity.func_195063_d(Effects.field_76419_f);
/*    */     
/* 85 */     float cooldown = 30.0F + this.continuousComponent.getContinueTime();
/* 86 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\doru\DoruDoruBallAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */