/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.donkrieg;
/*     */ 
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.MH5Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.DonKriegEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ 
/*     */ public class DonKriegPhaseSwitcherGoal extends TickedGoal<DonKriegEntity> {
/*     */   private static final float DAISENSO_HP_THRESHOLD = 70.0F;
/*     */   
/*     */   public DonKriegPhaseSwitcherGoal(DonKriegEntity entity) {
/*  19 */     super((MobEntity)entity);
/*     */     
/*  21 */     this.abilityData = AbilityDataCapability.get((LivingEntity)entity);
/*     */     
/*  23 */     this.mh5HPThreshold = entity.isDifficultyHardOrAbove() ? 70.0F : 50.0F;
/*     */   }
/*     */   private final IAbilityData abilityData; private final float mh5HPThreshold;
/*     */   
/*     */   public boolean func_75250_a() {
/*  28 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/*  29 */       return false;
/*     */     }
/*     */     
/*  32 */     if (!GoalUtil.canSee(this.entity, ((DonKriegEntity)this.entity).func_70638_az())) {
/*  33 */       return false;
/*     */     }
/*     */     
/*  36 */     if ((((DonKriegEntity)this.entity).hasFistPhaseActive() || ((DonKriegEntity)this.entity).hasMH5PhaseActive()) && trySwitchToDaisensoPhase()) {
/*  37 */       ((DonKriegEntity)this.entity).startDaisensoPhase();
/*  38 */       return true;
/*     */     } 
/*     */     
/*  41 */     if ((((DonKriegEntity)this.entity).hasFistPhaseActive() || ((DonKriegEntity)this.entity).hasDaisensoPhaseActive()) && trySwitchToMH5Phase()) {
/*  42 */       ((DonKriegEntity)this.entity).startMH5Phase();
/*  43 */       return true;
/*     */     } 
/*     */     
/*  46 */     return false;
/*     */   }
/*     */   
/*     */   private boolean trySwitchToDaisensoPhase() {
/*  50 */     if (((DonKriegEntity)this.entity).hasMH5PhaseActive()) {
/*  51 */       MH5Ability mh5Ability = (MH5Ability)this.abilityData.getEquippedAbility(MH5Ability.INSTANCE);
/*  52 */       if (mh5Ability != null) {
/*  53 */         boolean isCharging = ((Boolean)mh5Ability.getComponent(ModAbilityKeys.CHARGE).map(comp -> Boolean.valueOf(comp.isCharging())).orElse(Boolean.valueOf(false))).booleanValue();
/*  54 */         if (isCharging) {
/*  55 */           return false;
/*     */         }
/*     */         
/*  58 */         boolean isOnCooldown = ((Boolean)mh5Ability.getComponent(ModAbilityKeys.COOLDOWN).map(comp -> Boolean.valueOf(comp.isOnCooldown())).orElse(Boolean.valueOf(false))).booleanValue();
/*  59 */         if (isOnCooldown) {
/*  60 */           return true;
/*     */         }
/*     */         
/*  63 */         return false;
/*     */       }
/*     */     
/*     */     }
/*  67 */     else if (GoalUtil.hasHealthAbovePercentage((LivingEntity)this.entity, 70.0D)) {
/*  68 */       return false;
/*     */     } 
/*     */ 
/*     */     
/*  72 */     return true;
/*     */   }
/*     */   
/*     */   private boolean trySwitchToMH5Phase() {
/*  76 */     if (GoalUtil.hasHealthAbovePercentage((LivingEntity)this.entity, this.mh5HPThreshold)) {
/*  77 */       return false;
/*     */     }
/*     */     
/*  80 */     MH5Ability mh5Ability = (MH5Ability)this.abilityData.getEquippedAbility(MH5Ability.INSTANCE);
/*  81 */     if (mh5Ability != null) {
/*  82 */       boolean isOnCooldown = ((Boolean)mh5Ability.getComponent(ModAbilityKeys.COOLDOWN).map(comp -> Boolean.valueOf(comp.isOnCooldown())).orElse(Boolean.valueOf(false))).booleanValue();
/*  83 */       if (isOnCooldown) {
/*  84 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  88 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75253_b() {
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75249_e() {
/*  98 */     super.func_75249_e();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/* 103 */     super.func_75246_d();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75251_c() {
/* 108 */     super.func_75251_c();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\donkrieg\DonKriegPhaseSwitcherGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */