/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.merchant.villager.VillagerEntity;
/*    */ import net.minecraft.pathfinding.Path;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ 
/*    */ public class RunTowardsLocationGoal<E extends CreatureEntity>
/*    */   extends TickedGoal<E> {
/*    */   private Path path;
/*    */   
/*    */   public RunTowardsLocationGoal(E entity, double speed, Vector3d location) {
/* 18 */     super((MobEntity)entity);
/* 19 */     this.speed = speed;
/* 20 */     this.location = location;
/*    */   }
/*    */   private double speed; private Vector3d location;
/*    */   
/*    */   public boolean func_75250_a() {
/* 25 */     if (((CreatureEntity)this.entity).func_195048_a(this.location) <= 25.0D) {
/* 26 */       return false;
/*    */     }
/*    */     
/* 29 */     this.path = ((CreatureEntity)this.entity).func_70661_as().func_179680_a(new BlockPos(this.location), 1);
/* 30 */     if (this.path == null) {
/* 31 */       return false;
/*    */     }
/*    */     
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 39 */     if (((CreatureEntity)this.entity).func_195048_a(this.location) <= 25.0D) {
/* 40 */       return false;
/*    */     }
/*    */     
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 48 */     super.func_75249_e();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 53 */     super.func_75246_d();
/*    */     
/* 55 */     if (this.entity instanceof VillagerEntity) {
/* 56 */       ((VillagerEntity)this.entity).func_213375_cj().func_218227_b((ServerWorld)((CreatureEntity)this.entity).field_70170_p, (LivingEntity)this.entity);
/*    */     }
/*    */     
/* 59 */     ((CreatureEntity)this.entity).func_70661_as().func_75484_a(this.path, this.speed);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 64 */     super.func_75251_c();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\RunTowardsLocationGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */