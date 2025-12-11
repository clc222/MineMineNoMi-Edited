/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.dispenser.IPosition;
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.merchant.villager.VillagerEntity;
/*    */ import net.minecraft.pathfinding.Path;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class RunAwayFromLocationGoal<E extends CreatureEntity> extends TickedGoal<E> {
/*    */   private double speed;
/* 18 */   private int minDistance = 20; private Vector3d location;
/* 19 */   private int maxDistance = 30;
/*    */   
/*    */   public RunAwayFromLocationGoal(E entity, double speed, Vector3d location) {
/* 22 */     super((MobEntity)entity);
/* 23 */     this.speed = speed;
/* 24 */     this.location = location;
/*    */   }
/*    */   
/*    */   public RunAwayFromLocationGoal setRunningDistance(int min, int max) {
/* 28 */     this.minDistance = min;
/* 29 */     this.maxDistance = max;
/* 30 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 35 */     if (((CreatureEntity)this.entity).func_195048_a(this.location) > (this.minDistance * this.minDistance)) {
/* 36 */       return false;
/*    */     }
/*    */     
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 44 */     if (((CreatureEntity)this.entity).func_195048_a(this.location) <= (this.maxDistance * this.maxDistance)) {
/* 45 */       return false;
/*    */     }
/*    */     
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 53 */     super.func_75249_e();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 58 */     super.func_75246_d();
/*    */     
/* 60 */     if (this.entity instanceof VillagerEntity) {
/* 61 */       ((VillagerEntity)this.entity).func_213375_cj().func_218227_b((ServerWorld)((CreatureEntity)this.entity).field_70170_p, (LivingEntity)this.entity);
/*    */     }
/*    */     
/* 64 */     BlockPos pathTarget = ((CreatureEntity)this.entity).func_70661_as().func_208485_j();
/*    */     
/* 66 */     boolean isNearTarget = (pathTarget != null && ((CreatureEntity)this.entity).func_70092_e(pathTarget.func_177958_n(), pathTarget.func_177956_o(), pathTarget.func_177952_p()) < 25.0D);
/* 67 */     if (((CreatureEntity)this.entity).func_70661_as().func_75500_f() || isNearTarget) {
/* 68 */       Path path = findRunPosition();
/* 69 */       if (path != null) {
/* 70 */         ((CreatureEntity)this.entity).func_70661_as().func_75484_a(path, this.speed);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   private Path findRunPosition() {
/* 77 */     int run = 0;
/* 78 */     while (run < 20) {
/* 79 */       BlockPos pos = WyHelper.findValidGroundLocation((Entity)this.entity, ((CreatureEntity)this.entity).func_233580_cy_(), this.maxDistance, this.minDistance);
/* 80 */       boolean isWithinBounds = (!WyHelper.isInChallengeDimension(((CreatureEntity)this.entity).field_70170_p) || (WyHelper.isInChallengeDimension(((CreatureEntity)this.entity).field_70170_p) && AbilityHelper.isWithinChallengeArenaBounds(((CreatureEntity)this.entity).field_70170_p, pos)));
/* 81 */       if (isWithinBounds && pos.func_218137_a((IPosition)((CreatureEntity)this.entity).func_213303_ch(), this.minDistance)) {
/* 82 */         Path path = ((CreatureEntity)this.entity).func_70661_as().func_179680_a(pos, 1);
/* 83 */         if (path != null && path.func_224771_h()) {
/* 84 */           return path;
/*    */         }
/*    */       } 
/* 87 */       run++;
/*    */     } 
/* 89 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 94 */     super.func_75251_c();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\RunAwayFromLocationGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */