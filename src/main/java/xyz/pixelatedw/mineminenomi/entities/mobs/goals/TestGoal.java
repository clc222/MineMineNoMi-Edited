/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.IAnimatedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ 
/*    */ public class TestGoal
/*    */   extends ChargingGoal
/*    */   implements IAnimatedGoal {
/*    */   private OPEntity entity;
/*    */   private boolean isRunning;
/*    */   
/*    */   public TestGoal(OPEntity entity) {
/* 15 */     super((T)entity);
/* 16 */     this.entity = entity;
/* 17 */     setMaxCooldown(10.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 23 */     if (!super.func_75250_a()) {
/* 24 */       return false;
/*    */     }
/* 26 */     if (this.entity.func_70638_az() == null) {
/* 27 */       return false;
/*    */     }
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IAnimation getAnimation() {
/* 35 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive(LivingEntity entity) {
/* 41 */     return this.isRunning;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\TestGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */