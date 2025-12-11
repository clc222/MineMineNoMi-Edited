/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.missmerrychristmas;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.MissMerryChristmasEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.Mr4Entity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class MoleGrabAndDragGoal extends TickedGoal<MissMerryChristmasEntity> {
/*    */   private static final int COOLDOWN = 200;
/*    */   private static final float GRAB_DISTANCE = 2.0F;
/*    */   private static final int STOP_TIME = 200;
/*    */   private Mr4Entity partner;
/*    */   private LivingEntity target;
/*    */   private int ticks;
/*    */   private final float grabSpeed;
/*    */   
/*    */   public MoleGrabAndDragGoal(MissMerryChristmasEntity entity) {
/* 28 */     super((MobEntity)entity);
/* 29 */     this.grabSpeed = entity.getChallengeInfo().isDifficultyStandard() ? 1.1F : 1.25F;
/* 30 */     func_220684_a(EnumSet.of(Goal.Flag.MOVE));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 35 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 36 */       return false;
/*    */     }
/*    */     
/* 39 */     this.partner = ((MissMerryChristmasEntity)this.entity).getPartner();
/*    */     
/* 41 */     if (this.partner == null || !this.partner.func_70089_S()) {
/* 42 */       return false;
/*    */     }
/*    */     
/* 45 */     this.target = ((MissMerryChristmasEntity)this.entity).func_70638_az();
/*    */     
/* 47 */     if (!hasTimePassedSinceLastEnd(200.0F)) {
/* 48 */       return false;
/*    */     }
/*    */     
/* 51 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 56 */     if (this.target.func_70644_a((Effect)ModEffects.UNCONSCIOUS.get())) {
/* 57 */       return false;
/*    */     }
/*    */     
/* 60 */     if (hasTimePassedSinceStart(200.0F)) {
/* 61 */       return false;
/*    */     }
/*    */     
/* 64 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 69 */     super.func_75249_e();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 74 */     super.func_75246_d();
/*    */     
/* 76 */     this.ticks++;
/*    */     
/* 78 */     if (((MissMerryChristmasEntity)this.entity).func_233562_a_((Entity)this.target, 2.0D)) {
/* 79 */       ((MissMerryChristmasEntity)this.entity).func_223102_j(this.target.func_226277_ct_(), this.target.func_226278_cu_(), this.target.func_226281_cx_());
/* 80 */       if (this.ticks % 30 == 0) {
/* 81 */         this.target.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 40, 0));
/* 82 */         this.target.func_195064_c(new EffectInstance((Effect)ModEffects.HANA_HANDS.get(), 40, 0));
/*    */       } 
/*    */       
/* 85 */       if (this.ticks > 100 && GoalUtil.isOutsideDistance((LivingEntity)this.partner, this.target, 3.0D)) {
/* 86 */         Vector3d dist = this.partner.func_213303_ch().func_178788_d(this.target.func_213303_ch()).func_72432_b().func_72441_c(0.0D, -1.0D, 0.0D).func_216372_d(1.25D, 1.0D, 1.25D);
/* 87 */         AbilityHelper.setDeltaMovement((Entity)this.target, dist.field_72450_a, 0.0D, dist.field_72449_c);
/*    */       } 
/*    */     } else {
/*    */       
/* 91 */       ((MissMerryChristmasEntity)this.entity).func_70661_as().func_75497_a((Entity)this.target, this.grabSpeed);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\missmerrychristmas\MoleGrabAndDragGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */