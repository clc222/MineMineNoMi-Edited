/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.ChakramEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ import xyz.pixelatedw.mineminenomi.items.weapons.ChakramItem;
/*    */ 
/*    */ public class ThrowChakramsGoal extends TickedGoal<MobEntity> {
/*    */   private LivingEntity target;
/*    */   
/*    */   public ThrowChakramsGoal(MobEntity entity) {
/* 16 */     super(entity);
/* 17 */     this.cooldown = 200;
/*    */   }
/*    */   private final int cooldown;
/*    */   public ThrowChakramsGoal(MobEntity entity, int cooldown) {
/* 21 */     super(entity);
/* 22 */     this.cooldown = cooldown;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 27 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 28 */       return false;
/*    */     }
/*    */     
/* 31 */     if (!hasTimePassedSinceLastEnd(this.cooldown)) {
/* 32 */       return false;
/*    */     }
/*    */     
/* 35 */     this.target = this.entity.func_70638_az();
/*    */     
/* 37 */     if (GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, 6.0D)) {
/* 38 */       return false;
/*    */     }
/*    */     
/* 41 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 46 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 51 */     super.func_75249_e();
/*    */     
/* 53 */     float x = (float)(this.target.func_226277_ct_() - this.entity.func_226277_ct_());
/* 54 */     float z = (float)(this.target.func_226281_cx_() - this.entity.func_226281_cx_());
/* 55 */     float angle = (float)Math.toDegrees(Math.atan2(x, z));
/*    */     
/* 57 */     ChakramEntity chakram = new ChakramEntity((LivingEntity)this.entity, ((ChakramItem)ModWeapons.CHAKRAM.get()).func_190903_i());
/* 58 */     chakram.func_70107_b(chakram.func_226277_ct_(), this.entity.func_226280_cw_(), chakram.func_226281_cx_());
/* 59 */     chakram.func_234612_a_((Entity)this.entity, this.entity.field_70125_A, -angle, 0.0F, 3.0F, 1.0F);
/* 60 */     chakram.setAttackDamage(3.0F + ((ChakramItem)ModWeapons.CHAKRAM.get()).func_200894_d());
/* 61 */     this.entity.field_70170_p.func_217376_c((Entity)chakram);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\ThrowChakramsGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */