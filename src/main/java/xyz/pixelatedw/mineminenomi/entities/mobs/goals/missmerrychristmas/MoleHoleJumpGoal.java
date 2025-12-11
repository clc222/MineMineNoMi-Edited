/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.missmerrychristmas;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.gen.Heightmap;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.TargetHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.MissMerryChristmasEntity;
/*    */ 
/*    */ public class MoleHoleJumpGoal
/*    */   extends TickedGoal<MissMerryChristmasEntity> {
/*    */   private static final float RANGE = 3.0F;
/*    */   private static final float DAMAGE = 10.0F;
/*    */   private static final int COOLDOWN = 200;
/*    */   private LivingEntity target;
/*    */   
/*    */   public MoleHoleJumpGoal(MissMerryChristmasEntity entity) {
/* 24 */     super((MobEntity)entity);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 30 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 31 */       return false;
/*    */     }
/*    */     
/* 34 */     this.target = ((MissMerryChristmasEntity)this.entity).func_70638_az();
/*    */     
/* 36 */     if (((MissMerryChristmasEntity)this.entity).getHoles().size() <= 0) {
/* 37 */       return false;
/*    */     }
/*    */     
/* 40 */     if (!((MissMerryChristmasEntity)this.entity).func_233570_aj_()) {
/* 41 */       return false;
/*    */     }
/*    */     
/* 44 */     if (!((MissMerryChristmasEntity)this.entity).isUndergroundStandby()) {
/* 45 */       return false;
/*    */     }
/*    */     
/* 48 */     if (!hasTimePassedSinceLastEnd(200.0F)) {
/* 49 */       return false;
/*    */     }
/*    */     
/* 52 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 57 */     if (hasTimePassedSinceStart(40.0F) && ((MissMerryChristmasEntity)this.entity).func_233570_aj_()) {
/* 58 */       return false;
/*    */     }
/*    */     
/* 61 */     if (hasTimePassedSinceStart(100.0F)) {
/* 62 */       return false;
/*    */     }
/*    */     
/* 65 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 70 */     super.func_75249_e();
/*    */     
/* 72 */     for (BlockPos holePos : ((MissMerryChristmasEntity)this.entity).getHoles()) {
/* 73 */       if (this.target.func_70092_e(holePos.func_177958_n(), holePos.func_177956_o(), holePos.func_177952_p()) <= 9.0D) {
/* 74 */         int height = holePos.func_177956_o() - 8;
/* 75 */         if (height < 0) {
/* 76 */           height = ((MissMerryChristmasEntity)this.entity).field_70170_p.func_201676_a(Heightmap.Type.WORLD_SURFACE, holePos.func_177958_n(), holePos.func_177952_p());
/*    */         }
/* 78 */         ((MissMerryChristmasEntity)this.entity).func_223102_j(holePos.func_177958_n() + 0.5D, height, holePos.func_177952_p() + 0.5D);
/* 79 */         AbilityHelper.setDeltaMovement((Entity)this.entity, 0.0D, 1.45D, 0.0D);
/*    */         break;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 87 */     super.func_75246_d();
/*    */     
/* 89 */     if (!((MissMerryChristmasEntity)this.entity).func_233570_aj_()) {
/* 90 */       List<LivingEntity> aoeTargets = TargetHelper.getEntitiesInArea((LivingEntity)this.entity, 3.0D, 3.0D, 3.0D, null, new Class[] { LivingEntity.class });
/* 91 */       for (LivingEntity aoeTarget : aoeTargets)
/* 92 */         aoeTarget.func_70097_a(DamageSource.func_76358_a((LivingEntity)this.entity), 10.0F); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\missmerrychristmas\MoleHoleJumpGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */