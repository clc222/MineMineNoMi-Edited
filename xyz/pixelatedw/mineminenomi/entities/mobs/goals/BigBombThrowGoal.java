/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.GenkotsuMeteorAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateBomberEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.BigBombEntity;
/*    */ 
/*    */ public class BigBombThrowGoal
/*    */   extends Goal {
/*    */   private static final double MAX_DISTANCE = 5.0D;
/*    */   private PirateBomberEntity entity;
/*    */   
/*    */   public BigBombThrowGoal(PirateBomberEntity entity) {
/* 19 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 25 */     if (!this.entity.hasBomb()) {
/* 26 */       return false;
/*    */     }
/* 28 */     if (this.entity.func_70638_az() == null) {
/* 29 */       return false;
/*    */     }
/* 31 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 37 */     this.entity.setBombFuseTime(6);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 43 */     LivingEntity target = this.entity.func_70638_az();
/*    */     
/* 45 */     if (this.entity.field_70173_aa % 20 == 0 && this.entity.isBombFused())
/*    */     {
/* 47 */       this.entity.setBombFuseTime(this.entity.getBombFuseTime() - 1);
/*    */     }
/*    */     
/* 50 */     double distance = this.entity.func_70032_d((Entity)this.entity.func_70638_az());
/* 51 */     if (distance < 5.0D && !this.entity.func_70661_as().func_75500_f()) {
/*    */       
/* 53 */       double targetPosX = (int)(this.entity.func_226277_ct_() - target.func_226277_ct_() - this.entity.func_226277_ct_());
/* 54 */       double targetPosY = (int)(this.entity.func_226278_cu_() - target.func_226278_cu_() - this.entity.func_226278_cu_());
/* 55 */       double targetPosZ = (int)(this.entity.func_226281_cx_() - target.func_226281_cx_() - this.entity.func_226281_cx_());
/* 56 */       this.entity.func_70661_as().func_75492_a(targetPosX, targetPosY, targetPosZ, 1.2000000476837158D);
/*    */     }
/* 58 */     else if (distance >= 5.0D && this.entity.getBombFuseTime() <= 0) {
/*    */       
/* 60 */       this.entity.setHasBomb(false);
/*    */       
/* 62 */       float x = (float)(target.func_226277_ct_() - this.entity.func_226277_ct_());
/* 63 */       float z = (float)(target.func_226281_cx_() - this.entity.func_226281_cx_());
/* 64 */       float angle = (float)Math.toDegrees(Math.atan2(x, z));
/* 65 */       float distanceAngle = (distance > 10.0D) ? ((float)(distance / 10.0D) * 5.0F) : 0.0F;
/*    */       
/* 67 */       BigBombEntity projectile = new BigBombEntity(this.entity.field_70170_p, (LivingEntity)this.entity, GenkotsuMeteorAbility.INSTANCE);
/* 68 */       projectile.func_70107_b(projectile.func_226277_ct_(), this.entity.func_226278_cu_() + this.entity.func_213302_cg() + 1.5D, projectile.func_226281_cx_());
/* 69 */       projectile.func_234612_a_((Entity)this.entity, this.entity.field_70125_A - distanceAngle, -angle, 0.0F, 2.0F, 0.0F);
/* 70 */       projectile.setThrower((LivingEntity)this.entity);
/* 71 */       this.entity.field_70170_p.func_217376_c((Entity)projectile);
/*    */       
/* 73 */       ItemStack randomSword = this.entity.getRandomSword(MobsHelper.PIRATE_SWORDS);
/* 74 */       this.entity.func_184201_a(EquipmentSlotType.MAINHAND, randomSword);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\BigBombThrowGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */