/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.missmerrychristmas;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.MissMerryChristmasEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class CreateMoleHoleGoal
/*    */   extends TickedGoal<MissMerryChristmasEntity> {
/*    */   private LivingEntity target;
/*    */   private int holes;
/*    */   
/*    */   public CreateMoleHoleGoal(MissMerryChristmasEntity entity) {
/* 18 */     super((MobEntity)entity);
/* 19 */     this.maxHoles = entity.getChallengeInfo().isDifficultyStandard() ? 5 : 10;
/* 20 */     this.cooldown = entity.getChallengeInfo().isDifficultyStandard() ? 400 : 200;
/*    */   }
/*    */   private final int maxHoles;
/*    */   private final int cooldown;
/*    */   
/*    */   public boolean func_75250_a() {
/* 26 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 27 */       return false;
/*    */     }
/*    */     
/* 30 */     this.target = ((MissMerryChristmasEntity)this.entity).func_70638_az();
/*    */     
/* 32 */     if (this.holes >= this.maxHoles) {
/* 33 */       return false;
/*    */     }
/*    */     
/* 36 */     if (!((MissMerryChristmasEntity)this.entity).isUndergroundStandby()) {
/* 37 */       return false;
/*    */     }
/*    */     
/* 40 */     if (!hasTimePassedSinceStart(this.cooldown)) {
/* 41 */       return false;
/*    */     }
/*    */     
/* 44 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 49 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 54 */     super.func_75249_e();
/*    */     
/* 56 */     BlockPos pos = WyHelper.findValidGroundLocation((Entity)this.entity, ((MissMerryChristmasEntity)this.entity).func_233580_cy_(), 30, 10);
/* 57 */     if (!pos.equals(((MissMerryChristmasEntity)this.entity).func_233580_cy_())) {
/* 58 */       ((MissMerryChristmasEntity)this.entity).addHole(pos);
/* 59 */       this.holes++;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\missmerrychristmas\CreateMoleHoleGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */