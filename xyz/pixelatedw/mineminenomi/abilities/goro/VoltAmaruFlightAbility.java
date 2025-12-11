/*    */ package xyz.pixelatedw.mineminenomi.abilities.goro;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.PropelledFlightAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class VoltAmaruFlightAbility extends PropelledFlightAbility {
/* 18 */   public static final AbilityCore<VoltAmaruFlightAbility> INSTANCE = (new AbilityCore.Builder("Volt Amaru Flight", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, VoltAmaruFlightAbility::new))
/* 19 */     .build();
/*    */   
/*    */   public VoltAmaruFlightAbility(AbilityCore<VoltAmaruFlightAbility> core) {
/* 22 */     super(core);
/*    */     
/* 24 */     addCanUseCheck(this::canFly);
/*    */     
/* 26 */     addDuringPassiveEvent(this::onDuringPassive);
/*    */   }
/*    */   
/*    */   private void onDuringPassive(LivingEntity entity) {
/* 30 */     if (!(entity instanceof PlayerEntity)) {
/*    */       return;
/*    */     }
/*    */     
/* 34 */     if (!this.isRecovering) {
/* 35 */       PlayerEntity player = (PlayerEntity)entity;
/*    */       
/* 37 */       double difference = DevilFruitHelper.getDifferenceToFloor((Entity)entity);
/*    */       
/* 39 */       if (difference < 5.0D && player.field_71075_bZ.field_75100_b) {
/* 40 */         AbilityHelper.setDeltaMovement((Entity)entity, entity.func_213322_ci().func_72441_c(0.0D, 1.0D, 0.0D).func_216372_d(1.0D, 0.25D, 1.0D));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public float getMaxSpeed(LivingEntity entity) {
/* 47 */     return entity.func_70051_ag() ? 2.1F : 1.1F;
/*    */   }
/*    */ 
/*    */   
/*    */   protected float getAcceleration(LivingEntity entity) {
/* 52 */     return 0.015F;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getHeightDifference(LivingEntity entity) {
/* 57 */     return 36;
/*    */   }
/*    */   
/*    */   private AbilityUseResult canFly(LivingEntity entity, IAbility ability) {
/* 61 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*    */     
/* 63 */     VoltAmaruAbility voltAmaruAbility = (VoltAmaruAbility)abilityDataProps.getEquippedAbility(VoltAmaruAbility.INSTANCE);
/*    */     
/* 65 */     if (voltAmaruAbility != null && ((ContinuousComponent)voltAmaruAbility.getComponent(ModAbilityKeys.CONTINUOUS).get()).isContinuous()) {
/* 66 */       return AbilityUseResult.success();
/*    */     }
/*    */     
/* 69 */     return AbilityUseResult.fail(null);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\goro\VoltAmaruFlightAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */