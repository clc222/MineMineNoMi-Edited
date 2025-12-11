/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ 
/*    */ public class ClimbOutOfHoleGoal
/*    */   extends TickedGoal<MobEntity>
/*    */ {
/*    */   private static final int COOLDOWN = 60;
/*    */   private LivingEntity target;
/* 14 */   private int blocksHeight = 0;
/*    */   
/*    */   public ClimbOutOfHoleGoal(MobEntity entity) {
/* 17 */     super(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 22 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 23 */       return false;
/*    */     }
/*    */     
/* 26 */     if (!hasTimePassedSinceLastEnd(60.0F)) {
/* 27 */       return false;
/*    */     }
/*    */     
/* 30 */     this.target = this.entity.func_70638_az();
/*    */     
/* 32 */     if (GoalUtil.hasSolidBlockAbove((LivingEntity)this.entity)) {
/* 33 */       return false;
/*    */     }
/*    */     
/* 36 */     if (!GoalUtil.hasBlockInFace((LivingEntity)this.entity)) {
/* 37 */       return false;
/*    */     }
/*    */     
/* 40 */     this.blocksHeight = GoalUtil.getFrontWallHeight((LivingEntity)this.entity);
/* 41 */     if (this.blocksHeight <= 0) {
/* 42 */       return false;
/*    */     }
/*    */     
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 50 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 55 */     super.func_75249_e();
/*    */     
/* 57 */     BlockPos start = this.entity.func_233580_cy_();
/* 58 */     BlockPos check = start.func_177971_a(this.entity.func_174811_aO().func_176730_m());
/*    */     
/* 60 */     this.entity.func_174828_a(check.func_177981_b(this.blocksHeight + 1), this.entity.field_70177_z, this.entity.field_70125_A);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 65 */     super.func_75246_d();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 70 */     super.func_75251_c();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\ClimbOutOfHoleGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */