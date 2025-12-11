/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*    */ 
/*    */ import net.minecraft.command.arguments.EntityAnchorArgument;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*    */ 
/*    */ public class DashAtTargetGoal extends CooldownGoal {
/* 12 */   private int maxUseTime = 20;
/*    */ 
/*    */   
/*    */   public DashAtTargetGoal(OPEntity entity) {
/* 16 */     super(entity);
/* 17 */     setMaxCooldown(5.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMaxUseTime(int maxUseTime) {
/* 22 */     this.maxUseTime = maxUseTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 28 */     if (!super.func_75250_a()) {
/* 29 */       return false;
/*    */     }
/* 31 */     LivingEntity target = this.entity.func_70638_az();
/* 32 */     if (target == null) {
/* 33 */       return false;
/*    */     }
/* 35 */     if (this.entity.func_70068_e((Entity)this.entity.func_70638_az()) < 15.0D) {
/* 36 */       return false;
/*    */     }
/* 38 */     if (!this.entity.func_70685_l((Entity)this.entity.func_70638_az())) {
/* 39 */       return false;
/*    */     }
/* 41 */     if (!this.entity.func_233570_aj_()) {
/* 42 */       return false;
/*    */     }
/* 44 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 50 */     boolean distanceCheck = (this.entity.func_70638_az() != null && this.entity.func_70068_e((Entity)this.entity.func_70638_az()) > 15.0D);
/* 51 */     boolean ticksCheck = (getTicksInUse() < this.maxUseTime);
/* 52 */     return (distanceCheck && ticksCheck);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 58 */     super.func_75246_d();
/*    */     
/* 60 */     LivingEntity target = this.entity.func_70638_az();
/*    */     
/* 62 */     if (target == null) {
/*    */       
/* 64 */       setOnCooldown(true);
/*    */ 
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 70 */     this.entity.func_70661_as().func_75499_g();
/*    */     
/* 72 */     Vector3d dirVec = target.func_213303_ch().func_178788_d(this.entity.func_213303_ch()).func_72432_b();
/*    */     
/* 74 */     this.entity.func_200602_a(EntityAnchorArgument.Type.EYES, target.func_213303_ch());
/* 75 */     AbilityHelper.setDeltaMovement((Entity)this.entity, dirVec.field_72450_a, 0.0D, dirVec.field_72449_c);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\DashAtTargetGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */