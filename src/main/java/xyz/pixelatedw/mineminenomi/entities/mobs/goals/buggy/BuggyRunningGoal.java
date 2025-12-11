/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.buggy;
/*    */ 
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.RunAroundTargetGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.buggypirates.BuggyEntity;
/*    */ 
/*    */ public class BuggyRunningGoal extends RunAroundTargetGoal<BuggyEntity> {
/*    */   public BuggyRunningGoal(BuggyEntity entity, double speed) {
/*  9 */     super((CreatureEntity)entity, speed, 200, 20);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 14 */     if (!super.func_75250_a()) {
/* 15 */       return false;
/*    */     }
/*    */     
/* 18 */     return ((BuggyEntity)this.entity).shouldRun();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 23 */     if (!super.func_75253_b()) {
/* 24 */       return false;
/*    */     }
/*    */     
/* 27 */     return ((BuggyEntity)this.entity).shouldRun();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\buggy\BuggyRunningGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */