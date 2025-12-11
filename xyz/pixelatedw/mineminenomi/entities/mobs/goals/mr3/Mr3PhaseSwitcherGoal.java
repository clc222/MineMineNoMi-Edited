/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.mr3;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.Mr3Entity;
/*    */ 
/*    */ public class Mr3PhaseSwitcherGoal
/*    */   extends TickedGoal<Mr3Entity> {
/*    */   private boolean startStandardPhase;
/*    */   private boolean startChampionPhase;
/*    */   
/*    */   public Mr3PhaseSwitcherGoal(Mr3Entity entity) {
/* 15 */     super((MobEntity)entity);
/*    */   }
/*    */   private boolean startTokudaiPhase; private boolean isPostTokudai;
/*    */   
/*    */   public boolean func_75250_a() {
/* 20 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 21 */       return false;
/*    */     }
/*    */     
/* 24 */     if (((Mr3Entity)this.entity).isStandardPhase() && !this.isPostTokudai) {
/* 25 */       if (GoalUtil.hasHealthAbovePercentage((LivingEntity)this.entity, 50.0D)) {
/* 26 */         return false;
/*    */       }
/*    */       
/* 29 */       if (GoalUtil.hasHealthUnderPercentage((LivingEntity)this.entity, 50.0D) && ((Mr3Entity)this.entity).isChampionOnCooldown()) {
/* 30 */         this.startTokudaiPhase = true;
/* 31 */         return true;
/*    */       } 
/*    */       
/* 34 */       this.startChampionPhase = true;
/* 35 */       return true;
/*    */     } 
/* 37 */     if (((Mr3Entity)this.entity).isChampionPhase()) {
/* 38 */       if (((Mr3Entity)this.entity).isChampionOnCooldown()) {
/* 39 */         this.startStandardPhase = true;
/* 40 */         return true;
/*    */       } 
/*    */       
/* 43 */       if (GoalUtil.hasHealthAbovePercentage((LivingEntity)this.entity, 30.0D)) {
/* 44 */         return false;
/*    */       }
/*    */       
/* 47 */       this.startTokudaiPhase = true;
/* 48 */       return true;
/*    */     } 
/* 50 */     if (((Mr3Entity)this.entity).isTokudaiPhase()) {
/* 51 */       if (((Mr3Entity)this.entity).isTokudaiOnCooldown()) {
/* 52 */         this.startStandardPhase = true;
/* 53 */         return true;
/*    */       } 
/*    */       
/* 56 */       return false;
/*    */     } 
/*    */     
/* 59 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 64 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 69 */     super.func_75249_e();
/* 70 */     switchPhase();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 75 */     super.func_75246_d();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 80 */     super.func_75251_c();
/*    */   }
/*    */   
/*    */   public void switchPhase() {
/* 84 */     if (this.startStandardPhase) {
/* 85 */       ((Mr3Entity)this.entity).startStandardPhase();
/*    */     }
/* 87 */     else if (this.startChampionPhase) {
/* 88 */       ((Mr3Entity)this.entity).startChampionPhase();
/*    */     }
/* 90 */     else if (this.startTokudaiPhase) {
/* 91 */       ((Mr3Entity)this.entity).startTokudaiPhase();
/* 92 */       this.isPostTokudai = true;
/*    */     } 
/*    */     
/* 95 */     this.startStandardPhase = false;
/* 96 */     this.startChampionPhase = false;
/* 97 */     this.startTokudaiPhase = false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\mr3\Mr3PhaseSwitcherGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */