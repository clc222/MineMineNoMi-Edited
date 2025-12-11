/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ 
/*    */ public class JumpOutOfHoleGoal
/*    */   extends TickedGoal<MobEntity> {
/*    */   private static final int COOLDOWN = 60;
/*    */   private LivingEntity target;
/*    */   private boolean pushed;
/*    */   private final float jumpHeight;
/*    */   
/*    */   public JumpOutOfHoleGoal(MobEntity entity) {
/* 19 */     this(entity, 1.3F);
/*    */   }
/*    */   
/*    */   public JumpOutOfHoleGoal(MobEntity entity, float jumpHeight) {
/* 23 */     super(entity);
/* 24 */     this.jumpHeight = jumpHeight;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 29 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 30 */       return false;
/*    */     }
/*    */     
/* 33 */     if (!hasTimePassedSinceLastEnd(60.0F)) {
/* 34 */       return false;
/*    */     }
/*    */     
/* 37 */     this.target = this.entity.func_70638_az();
/*    */     
/* 39 */     if (GoalUtil.hasSolidBlockAbove((LivingEntity)this.entity)) {
/* 40 */       return false;
/*    */     }
/*    */     
/* 43 */     if (!GoalUtil.hasBlockInFace((LivingEntity)this.entity)) {
/* 44 */       return false;
/*    */     }
/*    */     
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 52 */     if (hasTimePassedSinceStart(100.0F)) {
/* 53 */       return false;
/*    */     }
/*    */     
/* 56 */     if (this.pushed) {
/* 57 */       return false;
/*    */     }
/*    */     
/* 60 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 65 */     super.func_75249_e();
/* 66 */     this.pushed = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 71 */     super.func_75246_d();
/*    */     
/* 73 */     if (!this.pushed) {
/* 74 */       if (getTickCount() % 40L == 0L) {
/* 75 */         AbilityHelper.setDeltaMovement((Entity)this.entity, 0.0D, this.jumpHeight, 0.0D);
/*    */       }
/*    */       
/* 78 */       if (GoalUtil.canSee(this.entity, this.target) && !GoalUtil.hasBlockInFace((LivingEntity)this.entity)) {
/* 79 */         GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/* 80 */         Vector3d push = this.target.func_213303_ch().func_178788_d(this.entity.func_213303_ch()).func_72432_b().func_216372_d(2.0D, 0.0D, 2.0D);
/* 81 */         AbilityHelper.setDeltaMovement((Entity)this.entity, push.field_72450_a, this.entity.func_213322_ci().func_82617_b() + 0.2D, push.field_72449_c);
/* 82 */         this.pushed = true;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 89 */     super.func_75251_c();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\JumpOutOfHoleGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */