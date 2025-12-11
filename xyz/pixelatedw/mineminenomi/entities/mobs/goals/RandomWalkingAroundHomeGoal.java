/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.ai.goal.RandomWalkingGoal;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.IWithHome;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class RandomWalkingAroundHomeGoal<E extends CreatureEntity & IWithHome>
/*    */   extends RandomWalkingGoal {
/*    */   protected E field_75457_a;
/*    */   protected final float probability;
/* 14 */   private int distance = 10;
/* 15 */   private int offset = 7;
/*    */   
/*    */   public RandomWalkingAroundHomeGoal(E mob, double speed) {
/* 18 */     this(mob, speed, 0.001F);
/* 19 */     this.field_75457_a = mob;
/*    */   }
/*    */   
/*    */   public RandomWalkingAroundHomeGoal(E mob, double speed, float chance) {
/* 23 */     super((CreatureEntity)mob, speed);
/* 24 */     this.field_75457_a = mob;
/* 25 */     this.probability = chance;
/*    */   }
/*    */   
/*    */   public RandomWalkingAroundHomeGoal<E> setWalkDistance(int distance) {
/* 29 */     this.distance = distance;
/* 30 */     return this;
/*    */   }
/*    */   
/*    */   public RandomWalkingAroundHomeGoal<E> setWalkOffset(int offset) {
/* 34 */     this.offset = offset;
/* 35 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected Vector3d func_190864_f() {
/* 41 */     if (!((IWithHome)this.field_75457_a).getHomePosition().isPresent()) {
/* 42 */       return super.func_190864_f();
/*    */     }
/*    */     
/* 45 */     Vector3d homePosition = ((IWithHome)this.field_75457_a).getHomePosition().get();
/*    */     
/* 47 */     if (this.field_75457_a.func_203005_aq()) {
/* 48 */       Vector3d vector3d = WyHelper.findValidGroundLocation(((CreatureEntity)this.field_75457_a).field_70170_p, homePosition, this.distance * 2, this.offset);
/* 49 */       return (vector3d == null) ? super.func_190864_f() : vector3d;
/*    */     } 
/* 51 */     if (this.field_75457_a.func_70681_au().nextFloat() >= this.probability) {
/* 52 */       return WyHelper.findValidGroundLocation(((CreatureEntity)this.field_75457_a).field_70170_p, homePosition, this.distance, this.offset);
/*    */     }
/* 54 */     return super.func_190864_f();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\RandomWalkingAroundHomeGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */