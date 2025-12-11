/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.PhysicalHitRevengeCheck;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ 
/*    */ public class GapCloserGoal extends TickedGoal<MobEntity> {
/* 14 */   private PhysicalHitRevengeCheck hitCheck = new PhysicalHitRevengeCheck(0); private LivingEntity target;
/*    */   private int maxCount;
/*    */   private double speed;
/*    */   
/*    */   public GapCloserGoal(MobEntity entity) {
/* 19 */     this(entity, 1.7D, 3);
/*    */   }
/*    */   
/*    */   public GapCloserGoal(MobEntity entity, double speed, int hitCount) {
/* 23 */     super(entity);
/* 24 */     this.speed = speed;
/* 25 */     this.maxCount = hitCount;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 30 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 31 */       return false;
/*    */     }
/*    */     
/* 34 */     this.target = this.entity.func_70638_az();
/*    */     
/* 36 */     if (!GoalUtil.canMove((LivingEntity)this.entity)) {
/* 37 */       return false;
/*    */     }
/*    */     
/* 40 */     this.hitCheck.check((LivingEntity)this.entity);
/* 41 */     if (this.hitCheck.getHits() < this.maxCount) {
/* 42 */       return false;
/*    */     }
/*    */     
/* 45 */     if (GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, 2.0D) || GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, 5.0D)) {
/* 46 */       return false;
/*    */     }
/*    */     
/* 49 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 54 */     this.hitCheck.resetMarkers();
/*    */     
/* 56 */     double mX = (-MathHelper.func_76126_a(this.entity.field_70125_A / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.entity.field_70177_z / 180.0F * 3.1415927F)) * 0.4D;
/* 57 */     double mZ = (MathHelper.func_76134_b(this.entity.field_70125_A / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.entity.field_70177_z / 180.0F * 3.1415927F)) * 0.4D;
/*    */     
/* 59 */     double f2 = MathHelper.func_76133_a(mX * mX + (this.entity.func_213322_ci()).field_72448_b * (this.entity.func_213322_ci()).field_72448_b + mZ * mZ);
/* 60 */     mX /= f2;
/* 61 */     mZ /= f2;
/* 62 */     mX += this.entity.field_70170_p.field_73012_v.nextGaussian() * 0.007499999832361937D * 1.0D;
/* 63 */     mZ += this.entity.field_70170_p.field_73012_v.nextGaussian() * 0.007499999832361937D * 1.0D;
/* 64 */     mX *= this.speed;
/* 65 */     mZ *= this.speed;
/*    */     
/* 67 */     AbilityHelper.setDeltaMovement((Entity)this.entity, new Vector3d(mX, 0.3D, mZ));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\GapCloserGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */