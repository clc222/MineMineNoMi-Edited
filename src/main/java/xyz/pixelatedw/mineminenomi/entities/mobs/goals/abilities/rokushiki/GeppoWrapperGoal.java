/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.rokushiki.GeppoAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.StackComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ 
/*     */ public class GeppoWrapperGoal extends AbilityWrapperGoal<MobEntity, GeppoAbility> {
/*     */   private LivingEntity target;
/*     */   
/*     */   public GeppoWrapperGoal(MobEntity entity) {
/*  19 */     super(entity, GeppoAbility.INSTANCE);
/*     */   }
/*     */   private Vector3d targetVec;
/*     */   
/*     */   public boolean canUseWrapper() {
/*  24 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/*  25 */       return false;
/*     */     }
/*     */     
/*  28 */     if (!GoalUtil.canMove((LivingEntity)this.entity)) {
/*  29 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  33 */     if (!this.entity.field_70170_p.func_226660_f_(this.entity.func_233580_cy_())) {
/*  34 */       return false;
/*     */     }
/*     */     
/*  37 */     this.target = this.entity.func_70638_az();
/*  38 */     this.targetVec = null;
/*     */     
/*  40 */     boolean needsHeight = false;
/*  41 */     boolean canSeeTarget = GoalUtil.canSee(this.entity, this.target);
/*     */ 
/*     */     
/*  44 */     double yDistance = 0.0D;
/*  45 */     if (canSeeTarget) {
/*  46 */       yDistance = (this.target.func_213303_ch().func_178788_d(this.entity.func_213303_ch())).field_72448_b;
/*     */     } else {
/*     */       
/*  49 */       double followDistance = this.entity.func_233637_b_(Attributes.field_233819_b_);
/*     */       
/*  51 */       for (int i = 5; i < 30; i += 5) {
/*  52 */         Vector3d lookOffset = this.entity.func_213303_ch().func_72441_c(0.0D, i, 0.0D);
/*  53 */         boolean isVisibleOnYAxis = (GoalUtil.isVisibleFrom((LivingEntity)this.entity, (Entity)this.target, lookOffset) && GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, followDistance));
/*  54 */         if (isVisibleOnYAxis) {
/*  55 */           yDistance = (lookOffset.func_178788_d(this.entity.func_213303_ch())).field_72448_b;
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  63 */     if (yDistance != 0.0D) {
/*  64 */       if (this.entity.func_233570_aj_() && yDistance >= 5.0D) {
/*  65 */         needsHeight = true;
/*     */       
/*     */       }
/*  68 */       else if (!this.entity.func_233570_aj_() && !canSeeTarget && yDistance > -10.0D && getCurrentStacks() != getDefaultStacks()) {
/*  69 */         needsHeight = true;
/*     */       } 
/*     */     }
/*     */     
/*  73 */     if (needsHeight) {
/*  74 */       this.targetVec = this.entity.func_213303_ch().func_72441_c(0.0D, 5.0D, 0.0D);
/*  75 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     if (canSeeTarget && !this.entity.func_233570_aj_()) {
/*  83 */       if (GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, 10.0D)) {
/*  84 */         return false;
/*     */       }
/*     */       
/*  87 */       double targetYPos = this.target.func_226278_cu_();
/*  88 */       double difference = Math.abs((DevilFruitHelper.getFloorLevel((Entity)this.entity)).field_72448_b - targetYPos);
/*  89 */       if (difference < 5.0D) {
/*  90 */         return false;
/*     */       }
/*     */       
/*  93 */       this.targetVec = this.target.func_213303_ch();
/*  94 */       return true;
/*     */     } 
/*     */ 
/*     */     
/*  98 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canContinueToUseWrapper() {
/* 103 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void startWrapper() {
/* 108 */     if (this.targetVec != null) {
/* 109 */       GoalUtil.lookAt(this.entity, this.targetVec);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tickWrapper() {}
/*     */ 
/*     */   
/*     */   public void stopWrapper() {}
/*     */ 
/*     */   
/*     */   public int getCurrentStacks() {
/* 122 */     return ((Integer)((GeppoAbility)getAbility()).getComponent(ModAbilityKeys.STACK).map(comp -> Integer.valueOf(comp.getStacks())).orElse(Integer.valueOf(0))).intValue();
/*     */   }
/*     */   
/*     */   public int getDefaultStacks() {
/* 126 */     return ((Integer)((GeppoAbility)getAbility()).getComponent(ModAbilityKeys.STACK).map(comp -> Integer.valueOf(comp.getDefaultStacks())).orElse(Integer.valueOf(0))).intValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\rokushiki\GeppoWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */