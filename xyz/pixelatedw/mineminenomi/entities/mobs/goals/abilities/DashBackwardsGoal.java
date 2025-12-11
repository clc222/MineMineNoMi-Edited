/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.command.arguments.EntityAnchorArgument;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.IWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class DashBackwardsGoal extends CooldownGoal {
/*    */   private boolean isDone;
/*    */   private int time;
/*    */   
/*    */   public DashBackwardsGoal(OPEntity entity) {
/* 20 */     super(entity);
/* 21 */     setMaxCooldown(5.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 26 */     if (!super.func_75250_a()) {
/* 27 */       return false;
/*    */     }
/* 29 */     LivingEntity target = this.entity.func_70638_az();
/* 30 */     if (target == null) {
/* 31 */       return false;
/*    */     }
/* 33 */     if (!this.entity.func_70685_l((Entity)target)) {
/* 34 */       return false;
/*    */     }
/* 36 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 41 */     return (!isOnCooldown() && !this.isDone);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 46 */     super.func_75249_e();
/* 47 */     this.time = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 52 */     super.func_75246_d();
/*    */     
/* 54 */     LivingEntity target = this.entity.func_70638_az();
/* 55 */     if (target == null) {
/* 56 */       setOnCooldown(true);
/*    */       
/*    */       return;
/*    */     } 
/* 60 */     if (getTicksInUse() % 10 == 0) {
/* 61 */       List<LivingEntity> targets = WyHelper.getNearbyLiving(this.entity.func_213303_ch(), (IWorld)this.entity.field_70170_p, 3.0D, ModEntityPredicates.getEnemyFactions((LivingEntity)this.entity));
/* 62 */       if (targets.size() > 0) {
/* 63 */         this.time++;
/* 64 */         if (this.time >= 7) {
/* 65 */           this.entity.func_200602_a(EntityAnchorArgument.Type.EYES, target.func_213303_ch());
/* 66 */           Vector3d dir = target.func_213303_ch().func_178788_d(this.entity.func_213303_ch()).func_72432_b();
/* 67 */           AbilityHelper.setDeltaMovement((Entity)this.entity, -dir.field_72450_a * 3.0D, 0.1D, -dir.field_72449_c * 3.0D);
/* 68 */           this.isDone = true;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\DashBackwardsGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */