/*    */ package xyz.pixelatedw.mineminenomi.abilities.gomu;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.PropelledFlightAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ 
/*    */ public class GearFourthFlightAbility extends PropelledFlightAbility {
/* 16 */   public static final AbilityCore<GearFourthFlightAbility> INSTANCE = (new AbilityCore.Builder("Gear Fourth Flight", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, GearFourthFlightAbility::new))
/* 17 */     .setHidden()
/* 18 */     .build();
/*    */   
/*    */   public GearFourthFlightAbility(AbilityCore<GearFourthFlightAbility> core) {
/* 21 */     super(core);
/*    */     
/* 23 */     addCanUseCheck(this::canFly);
/*    */     
/* 25 */     addDuringPassiveEvent(this::onDuringPassive);
/*    */   }
/*    */   
/*    */   private void onDuringPassive(LivingEntity entity) {
/* 29 */     if (!(entity instanceof PlayerEntity)) {
/*    */       return;
/*    */     }
/*    */     
/* 33 */     PlayerEntity player = (PlayerEntity)entity;
/*    */     
/* 35 */     if (canUse(entity).isSuccess()) {
/* 36 */       player.field_71075_bZ.field_75100_b = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public float getMaxSpeed(LivingEntity entity) {
/* 42 */     return 1.1F;
/*    */   }
/*    */ 
/*    */   
/*    */   protected float getAcceleration(LivingEntity entity) {
/* 47 */     return 0.01F;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getHeightDifference(LivingEntity entity) {
/* 52 */     return 40;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Vector3d getMovementVector(LivingEntity entity) {
/* 57 */     int d1 = entity.func_233570_aj_() ? 1 : 0;
/*    */     
/* 59 */     Vector3d lookVector = entity.func_70040_Z();
/*    */     
/* 61 */     double x = lookVector.field_72450_a * this.speed * (1.0D - d1) * entity.field_191988_bg;
/* 62 */     double y = d1 * 0.6D + lookVector.field_72448_b * this.speed * (1.0D - d1) * entity.field_191988_bg + Math.cos(entity.field_70173_aa / (4.0D - this.speed * 1.25D)) / 5.0D;
/* 63 */     double z = lookVector.field_72449_c * this.speed * (1.0D - d1) * entity.field_191988_bg;
/*    */     
/* 65 */     return new Vector3d(x, y, z);
/*    */   }
/*    */   
/*    */   private AbilityUseResult canFly(LivingEntity entity, IAbility ability) {
/* 69 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*    */     
/* 71 */     GearFourthAbility gearFourthAbility = (GearFourthAbility)abilityDataProps.getEquippedAbility(GearFourthAbility.INSTANCE);
/*    */     
/* 73 */     boolean isGearFourthActive = (gearFourthAbility != null && gearFourthAbility.isContinuous());
/*    */     
/* 75 */     if (isGearFourthActive) {
/* 76 */       return AbilityUseResult.success();
/*    */     }
/*    */     
/* 79 */     return AbilityUseResult.fail(null);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canRenderGauge(PlayerEntity entity) {
/* 84 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)entity);
/* 85 */     if (props == null) {
/* 86 */       return false;
/*    */     }
/*    */     
/* 89 */     GearFourthAbility gearFourthAbility = (GearFourthAbility)props.getEquippedAbility(GearFourthAbility.INSTANCE);
/*    */     
/* 91 */     boolean isGearFourthActive = (gearFourthAbility != null && gearFourthAbility.isContinuous());
/*    */     
/* 93 */     return isGearFourthActive;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gomu\GearFourthFlightAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */