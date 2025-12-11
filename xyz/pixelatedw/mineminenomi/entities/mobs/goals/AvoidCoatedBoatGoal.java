/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.ai.RandomPositionGenerator;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.entity.item.BoatEntity;
/*    */ import net.minecraft.pathfinding.Path;
/*    */ import net.minecraft.pathfinding.PathNavigator;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.kairosekicoating.IKairosekiCoating;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.kairosekicoating.KairosekiCoatingCapability;
/*    */ 
/*    */ public class AvoidCoatedBoatGoal
/*    */   extends Goal
/*    */ {
/*    */   private CreatureEntity entity;
/*    */   private Vector3d toAvoid;
/*    */   private long lastCheck;
/*    */   private Path path;
/*    */   private final PathNavigator pathNav;
/* 23 */   private double speed = 2.0D;
/* 24 */   private double sprintSpeed = 4.0D;
/*    */   
/*    */   public AvoidCoatedBoatGoal(CreatureEntity entity) {
/* 27 */     this.entity = entity;
/* 28 */     this.pathNav = entity.func_70661_as();
/*    */   }
/*    */   
/*    */   public void setSpeed(double speed) {
/* 32 */     this.speed = speed;
/*    */   }
/*    */   
/*    */   public void setSprintSpeed(double speed) {
/* 36 */     this.sprintSpeed = speed;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 41 */     if (this.entity == null || !this.entity.func_70089_S()) {
/* 42 */       return false;
/*    */     }
/*    */     
/* 45 */     long now = System.currentTimeMillis();
/* 46 */     if (now - this.lastCheck < 1000L) {
/* 47 */       return false;
/*    */     }
/*    */     
/* 50 */     List<BoatEntity> list = this.entity.field_70170_p.func_217357_a(BoatEntity.class, this.entity.func_174813_aQ().func_72314_b(20.0D, 20.0D, 20.0D));
/* 51 */     for (BoatEntity boat : list) {
/* 52 */       Entity entity = boat.func_184179_bs();
/* 53 */       if (entity != null && entity instanceof net.minecraft.entity.LivingEntity && boat.func_70089_S() && this.entity.func_70685_l((Entity)boat)) {
/* 54 */         int coatingLevel = ((Integer)KairosekiCoatingCapability.get((Entity)boat).map(props -> Integer.valueOf(props.getCoatingLevel())).orElse(Integer.valueOf(0))).intValue();
/* 55 */         if (coatingLevel > 0) {
/* 56 */           int distance = (int)(128.0F * coatingLevel / 5.0F);
/* 57 */           distance = Math.max(24, distance);
/* 58 */           this.toAvoid = RandomPositionGenerator.func_75461_b(this.entity, distance, distance / 2, new Vector3d(boat.func_226277_ct_(), boat.func_226278_cu_(), boat.func_226281_cx_()));
/* 59 */           if (this.toAvoid == null) {
/*    */             continue;
/*    */           }
/*    */           
/* 63 */           this.path = this.entity.func_70661_as().func_225466_a(this.toAvoid.field_72450_a, this.toAvoid.field_72448_b, this.toAvoid.field_72449_c, 0);
/* 64 */           if (this.path == null) {
/*    */             continue;
/*    */           }
/*    */           
/* 68 */           return true;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 73 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 78 */     return !this.pathNav.func_75500_f();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 83 */     this.pathNav.func_75484_a(this.path, this.speed);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 88 */     this.toAvoid = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 93 */     if (this.entity.func_195048_a(this.toAvoid) < 49.0D) {
/* 94 */       this.entity.func_70661_as().func_75489_a(this.sprintSpeed);
/*    */     } else {
/*    */       
/* 97 */       this.entity.func_70661_as().func_75489_a(this.speed);
/*    */     } 
/* 99 */     this.pathNav.func_75484_a(this.path, this.speed);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\AvoidCoatedBoatGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */