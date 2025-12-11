/*    */ package xyz.pixelatedw.mineminenomi.api.entities.ai;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.util.math.IPosWrapper;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.IGoalMemoriesEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*    */ 
/*    */ public class LookAtTargetGoal<E extends MobEntity & IGoalMemoriesEntity>
/*    */   extends TickedGoal<E>
/*    */ {
/*    */   private final float maxDistSqr;
/* 16 */   private Interval canUseInterval = new Interval(100);
/*    */   private int lookTime;
/*    */   private Vector3d lookAt;
/*    */   
/*    */   public LookAtTargetGoal(E entity, float distance) {
/* 21 */     super(entity);
/* 22 */     this.maxDistSqr = distance * distance;
/* 23 */     func_220684_a(EnumSet.of(Goal.Flag.LOOK));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 28 */     if (!this.canUseInterval.canTick()) {
/* 29 */       return false;
/*    */     }
/* 31 */     if (!((IGoalMemoriesEntity)this.entity).hasMemoryValue(MemoryModuleType.field_220951_l)) {
/* 32 */       return false;
/*    */     }
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 39 */     if (this.lookAt != null && this.entity.func_70092_e(this.lookAt.field_72450_a, this.lookAt.field_72448_b, this.lookAt.field_72449_c) > this.maxDistSqr) {
/* 40 */       return false;
/*    */     }
/*    */     
/* 43 */     return (this.lookTime > 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 49 */     super.func_75249_e();
/* 50 */     this.lookTime = 60 + this.entity.func_70681_au().nextInt(40);
/* 51 */     ((IGoalMemoriesEntity)this.entity).getGoalMemories().getMemory(MemoryModuleType.field_220951_l).ifPresent(pos -> this.lookAt = pos.func_220609_b());
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 56 */     super.func_75246_d();
/* 57 */     if (this.lookAt != null) {
/* 58 */       this.entity.func_70671_ap().func_220679_a(this.lookAt.func_82615_a(), this.lookAt.func_82617_b(), this.lookAt.func_82616_c());
/*    */     }
/* 60 */     this.lookTime--;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 65 */     super.func_75251_c();
/* 66 */     ((IGoalMemoriesEntity)this.entity).getGoalMemories().eraseMemory(MemoryModuleType.field_220951_l);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\entities\ai\LookAtTargetGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */