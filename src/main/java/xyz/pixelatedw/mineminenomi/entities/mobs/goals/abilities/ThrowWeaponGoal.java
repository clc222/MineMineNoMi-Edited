/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import xyz.pixelatedw.mineminenomi.entities.ThrowingWeaponEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*    */ 
/*    */ public class ThrowWeaponGoal
/*    */   extends CooldownGoal {
/*    */   public Vector3d weaponPosition;
/*    */   public boolean gotWeaponBack;
/*    */   
/*    */   public ThrowWeaponGoal(OPEntity entity) {
/* 19 */     super(entity);
/* 20 */     setMaxCooldown(1.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 25 */     if (!super.func_75250_a()) {
/* 26 */       return false;
/*    */     }
/*    */     
/* 29 */     LivingEntity target = this.entity.func_70638_az();
/* 30 */     if (target == null || !target.func_70089_S()) {
/* 31 */       return false;
/*    */     }
/*    */     
/* 34 */     if (!this.entity.func_70685_l((Entity)target)) {
/* 35 */       return false;
/*    */     }
/*    */     
/* 38 */     if (this.entity.func_70032_d((Entity)target) < 5.0F) {
/* 39 */       return false;
/*    */     }
/*    */     
/* 42 */     ItemStack heldItem = this.entity.func_184614_ca();
/* 43 */     if (heldItem == null || heldItem.func_190926_b()) {
/* 44 */       return false;
/*    */     }
/*    */     
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 52 */     return super.func_75253_b();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 58 */     super.func_75249_e();
/*    */     
/* 60 */     LivingEntity target = this.entity.func_70638_az();
/* 61 */     if (target == null) {
/*    */       return;
/*    */     }
/*    */     
/* 65 */     float x = (float)(target.func_226277_ct_() - this.entity.func_226277_ct_());
/* 66 */     float z = (float)(target.func_226281_cx_() - this.entity.func_226281_cx_());
/* 67 */     float angle = (float)Math.toDegrees(Math.atan2(x, z));
/*    */     
/* 69 */     ThrowingWeaponEntity throwingWeaponEntity = new ThrowingWeaponEntity(this.entity.field_70170_p, (LivingEntity)this.entity);
/* 70 */     throwingWeaponEntity.func_70107_b(throwingWeaponEntity.func_226277_ct_(), this.entity.func_226278_cu_() + (this.entity.func_213302_cg() / 2.0F) + 1.0D, throwingWeaponEntity.func_226281_cx_());
/* 71 */     throwingWeaponEntity.func_234612_a_((Entity)this.entity, this.entity.field_70125_A - 7.0F, -angle, 0.0F, 1.5F, 0.0F);
/* 72 */     throwingWeaponEntity.setThrower((LivingEntity)this.entity);
/* 73 */     this.entity.field_70170_p.func_217376_c((Entity)throwingWeaponEntity);
/*    */     
/* 75 */     this.entity.func_184201_a(EquipmentSlotType.MAINHAND, Items.field_190931_a.func_190903_i());
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 80 */     super.func_75246_d();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\ThrowWeaponGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */