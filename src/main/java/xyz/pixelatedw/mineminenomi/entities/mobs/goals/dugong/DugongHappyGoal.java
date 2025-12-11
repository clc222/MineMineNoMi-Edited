/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.dugong;
/*    */ import java.util.EnumSet;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.passive.TameableEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.AbstractDugongEntity;
/*    */ 
/*    */ public class DugongHappyGoal extends TickedGoal<AbstractDugongEntity> {
/* 12 */   private Interval canUseInterval = new Interval(20);
/*    */   
/*    */   public DugongHappyGoal(AbstractDugongEntity entity) {
/* 15 */     super((MobEntity)entity);
/* 16 */     func_220684_a(EnumSet.of(Goal.Flag.LOOK));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 21 */     if (!this.canUseInterval.canTick()) {
/* 22 */       return false;
/*    */     }
/*    */     
/* 25 */     if (!((AbstractDugongEntity)this.entity).func_70909_n()) {
/* 26 */       return false;
/*    */     }
/*    */     
/* 29 */     if (!GoalUtil.hasAliveOwner((TameableEntity)this.entity)) {
/* 30 */       return false;
/*    */     }
/*    */     
/* 33 */     if (!((AbstractDugongEntity)this.entity).func_233685_eM_()) {
/* 34 */       return false;
/*    */     }
/*    */     
/* 37 */     if (((AbstractDugongEntity)this.entity).func_110143_aJ() <= ((AbstractDugongEntity)this.entity).func_110138_aP() / 3.0F) {
/* 38 */       return false;
/*    */     }
/*    */     
/* 41 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 46 */     if (((AbstractDugongEntity)this.entity).isEnraged()) {
/* 47 */       return false;
/*    */     }
/* 49 */     if (GoalUtil.hasAliveTarget(this.entity)) {
/* 50 */       return false;
/*    */     }
/* 52 */     if (!GoalUtil.hasAliveOwner((TameableEntity)this.entity)) {
/* 53 */       return false;
/*    */     }
/* 55 */     if (!((AbstractDugongEntity)this.entity).func_233685_eM_()) {
/* 56 */       return false;
/*    */     }
/* 58 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 63 */     super.func_75249_e();
/* 64 */     ((AbstractDugongEntity)this.entity).setHappy(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 69 */     super.func_75246_d();
/* 70 */     GoalUtil.lookAtEntity(this.entity, (Entity)((AbstractDugongEntity)this.entity).func_70902_q());
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 75 */     super.func_75251_c();
/* 76 */     ((AbstractDugongEntity)this.entity).setHappy(false);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\dugong\DugongHappyGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */