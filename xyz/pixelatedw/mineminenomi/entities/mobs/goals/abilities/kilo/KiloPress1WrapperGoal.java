/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.kilo;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.kilo.KiloPress1Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public class KiloPress1WrapperGoal extends AbilityWrapperGoal<MobEntity, KiloPress1Ability> {
/*    */   private LivingEntity target;
/* 19 */   private Interval jumpInterval = Interval.startAtMax(50);
/*    */   private int floatTime;
/* 21 */   private double floatSpeed = 0.2D;
/*    */   private boolean forceUse;
/*    */   
/*    */   public KiloPress1WrapperGoal(MobEntity entity) {
/* 25 */     super(entity, KiloPress1Ability.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 30 */     if (this.forceUse) {
/* 31 */       return true;
/*    */     }
/*    */     
/* 34 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 35 */       return false;
/*    */     }
/*    */     
/* 38 */     this.target = this.entity.func_70638_az();
/*    */     
/* 40 */     if (!this.entity.func_233570_aj_()) {
/* 41 */       return false;
/*    */     }
/*    */     
/* 44 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 49 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 50 */       return false;
/*    */     }
/*    */     
/* 53 */     if (!hasUmbrellaInHand()) {
/* 54 */       return false;
/*    */     }
/*    */     
/* 57 */     return true;
/*    */   }
/*    */   
/*    */   public boolean hasUmbrellaInHand() {
/* 61 */     ItemStack itemStack = this.entity.func_184582_a(EquipmentSlotType.MAINHAND);
/* 62 */     return (!itemStack.func_190926_b() && itemStack.func_77973_b().equals(ModWeapons.UMBRELLA.get()));
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 67 */     this.forceUse = false;
/* 68 */     this.floatSpeed = this.entity.func_110148_a(Attributes.field_233821_d_).func_111126_e();
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 73 */     if (this.entity.func_233570_aj_() && this.jumpInterval.canTick()) {
/* 74 */       this.entity.func_70683_ar().func_75660_a();
/*    */     }
/* 76 */     else if (!this.entity.func_233570_aj_()) {
/* 77 */       this.floatTime++;
/* 78 */       GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/* 79 */       if (this.floatTime > 50) {
/* 80 */         Vector3d dist = this.entity.func_213303_ch().func_178788_d(this.entity.func_70638_az().func_213303_ch()).func_72432_b();
/* 81 */         AbilityHelper.setDeltaMovement((Entity)this.entity, ((this.entity.func_213322_ci()).field_72450_a - dist.field_72450_a) * this.floatSpeed, (this.entity.func_213322_ci()).field_72448_b, ((this.entity.func_213322_ci()).field_72449_c - dist.field_72449_c) * this.floatSpeed);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopWrapper() {
/* 88 */     this.floatTime = 0;
/*    */   }
/*    */   
/*    */   public boolean isForced() {
/* 92 */     return this.forceUse;
/*    */   }
/*    */   
/*    */   public void forceUse() {
/* 96 */     this.forceUse = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\kilo\KiloPress1WrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */