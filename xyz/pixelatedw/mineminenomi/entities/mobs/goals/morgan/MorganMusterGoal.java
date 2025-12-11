/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.morgan;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.SniperEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.marines.MorganEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class MorganMusterGoal
/*    */   extends TickedGoal<MorganEntity> {
/*    */   public MorganMusterGoal(MorganEntity entity) {
/* 14 */     super((MobEntity)entity);
/*    */   }
/*    */   private boolean used;
/*    */   
/*    */   public boolean func_75250_a() {
/* 19 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 20 */       return false;
/*    */     }
/*    */     
/* 23 */     if (this.used) {
/* 24 */       return false;
/*    */     }
/*    */     
/* 27 */     if (((MorganEntity)this.entity).isDifficultyStandard()) {
/* 28 */       return false;
/*    */     }
/*    */     
/* 31 */     if (((MorganEntity)this.entity).func_110143_aJ() > WyHelper.percentage(50.0D, ((MorganEntity)this.entity).func_110138_aP())) {
/* 32 */       return false;
/*    */     }
/*    */     
/* 35 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 40 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 45 */     super.func_75249_e();
/*    */     
/* 47 */     SniperEntity sniper1 = SniperEntity.createMarineSniper(((MorganEntity)this.entity).field_70170_p);
/* 48 */     sniper1.func_70107_b(-25.0D, 64.0D, 0.0D);
/* 49 */     ((MorganEntity)this.entity).field_70170_p.func_217376_c((Entity)sniper1);
/*    */     
/* 51 */     SniperEntity sniper2 = SniperEntity.createMarineSniper(((MorganEntity)this.entity).field_70170_p);
/* 52 */     sniper2.func_70107_b(25.0D, 64.0D, 0.0D);
/* 53 */     ((MorganEntity)this.entity).field_70170_p.func_217376_c((Entity)sniper2);
/*    */     
/* 55 */     SniperEntity sniper3 = SniperEntity.createMarineSniper(((MorganEntity)this.entity).field_70170_p);
/* 56 */     sniper3.func_70107_b(0.0D, 64.0D, 25.0D);
/* 57 */     ((MorganEntity)this.entity).field_70170_p.func_217376_c((Entity)sniper3);
/*    */     
/* 59 */     SniperEntity sniper4 = SniperEntity.createMarineSniper(((MorganEntity)this.entity).field_70170_p);
/* 60 */     sniper4.func_70107_b(0.0D, 64.0D, -25.0D);
/* 61 */     ((MorganEntity)this.entity).field_70170_p.func_217376_c((Entity)sniper4);
/*    */     
/* 63 */     this.used = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\morgan\MorganMusterGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */