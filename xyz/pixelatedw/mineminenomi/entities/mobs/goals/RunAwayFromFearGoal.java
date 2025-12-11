/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import java.util.Optional;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.util.EntityPredicates;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class RunAwayFromFearGoal
/*    */   extends TickedGoal<OPEntity> {
/*    */   private static final int COOLDOWN = 100;
/*    */   private int targetPosX;
/*    */   private int targetPosY;
/*    */   private int targetPosZ;
/*    */   private double speed;
/*    */   private LivingEntity target;
/*    */   private final Predicate<Entity> filter;
/*    */   
/*    */   public RunAwayFromFearGoal(OPEntity entity, double speed) {
/* 30 */     super((MobEntity)entity);
/* 31 */     this.speed = speed;
/* 32 */     func_220684_a(EnumSet.of(Goal.Flag.MOVE));
/*    */     
/* 34 */     this
/*    */ 
/*    */       
/* 37 */       .filter = EntityPredicates.field_188444_d.and(EntityPredicates.field_233583_f_).and(ModEntityPredicates.getEnemyFactions((LivingEntity)this.entity)).and(MobsHelper.shouldTarget((LivingEntity)entity).negate());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 42 */     if (!((OPEntity)this.entity).hasFear()) {
/* 43 */       return false;
/*    */     }
/*    */     
/* 46 */     if (WyHelper.isInChallengeDimension(((OPEntity)this.entity).field_70170_p)) {
/* 47 */       return false;
/*    */     }
/*    */     
/* 50 */     if (((OPEntity)this.entity).getDoriki() >= 6000.0D) {
/* 51 */       return false;
/*    */     }
/*    */     
/* 54 */     if (getLastEndTick() != 0L && 
/* 55 */       !hasTimePassedSinceLastEnd(100.0F)) {
/* 56 */       return false;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 63 */     Optional<LivingEntity> target = WyHelper.getNearbyLiving(((OPEntity)this.entity).func_213303_ch(), (IWorld)((OPEntity)this.entity).field_70170_p, 20.0D, this.filter).stream().findAny();
/*    */     
/* 65 */     if (target.isPresent()) {
/* 66 */       this.target = target.get();
/* 67 */       return true;
/*    */     } 
/*    */     
/* 70 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_75249_e() {}
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 79 */     if (this.target != null && this.target.func_70089_S() && ((OPEntity)this.entity).func_70661_as().func_75500_f()) {
/* 80 */       BlockPos targetPos = this.target.func_233580_cy_();
/* 81 */       this.targetPosX = (int)(((OPEntity)this.entity).func_226277_ct_() - targetPos.func_177958_n() - ((OPEntity)this.entity).func_226277_ct_());
/* 82 */       this.targetPosY = (int)(((OPEntity)this.entity).func_226278_cu_() - targetPos.func_177956_o() - ((OPEntity)this.entity).func_226278_cu_());
/* 83 */       this.targetPosZ = (int)(((OPEntity)this.entity).func_226281_cx_() - targetPos.func_177952_p() - ((OPEntity)this.entity).func_226281_cx_());
/* 84 */       ((OPEntity)this.entity).func_70661_as().func_75492_a(this.targetPosX, this.targetPosY, this.targetPosZ, this.speed);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 90 */     return (this.target != null && this.target.func_70089_S() && ((OPEntity)this.entity).func_70032_d((Entity)this.target) < 40.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\RunAwayFromFearGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */