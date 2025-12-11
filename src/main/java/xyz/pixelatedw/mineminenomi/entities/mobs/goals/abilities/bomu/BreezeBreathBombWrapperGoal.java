/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.bomu;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.bomu.BreezeBreathBombAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class BreezeBreathBombWrapperGoal extends AbilityWrapperGoal<MobEntity, BreezeBreathBombAbility> {
/*    */   private ItemStack heldItemStack;
/*    */   private LivingEntity target;
/*    */   private boolean forceUse;
/*    */   
/*    */   public BreezeBreathBombWrapperGoal(MobEntity entity) {
/* 20 */     super(entity, BreezeBreathBombAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 25 */     if (this.forceUse) {
/* 26 */       return true;
/*    */     }
/*    */     
/* 29 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 30 */       return false;
/*    */     }
/*    */     
/* 33 */     this.target = this.entity.func_70638_az();
/*    */     
/* 35 */     this.heldItemStack = this.entity.func_184582_a(EquipmentSlotType.MAINHAND);
/* 36 */     if (!hasGunInHand()) {
/* 37 */       return false;
/*    */     }
/*    */     
/* 40 */     if (GoalUtil.hasHealthAbovePercentage((LivingEntity)this.entity, 90.0D)) {
/* 41 */       return false;
/*    */     }
/*    */     
/* 44 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 49 */     return false;
/*    */   }
/*    */   
/*    */   public boolean hasGunInHand() {
/* 53 */     return (!this.heldItemStack.func_190926_b() && this.heldItemStack.func_77973_b() instanceof xyz.pixelatedw.mineminenomi.items.weapons.ModGunItem);
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 58 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */ 
/*    */     
/* 61 */     ((BreezeBreathBombAbility)getAbility()).getComponent(ModAbilityKeys.PROJECTILE).ifPresent(comp -> comp.shoot((LivingEntity)this.entity));
/* 62 */     this.forceUse = false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tickWrapper() {}
/*    */ 
/*    */   
/*    */   public void stopWrapper() {}
/*    */ 
/*    */   
/*    */   public boolean isForced() {
/* 74 */     return this.forceUse;
/*    */   }
/*    */   
/*    */   public void forceUse() {
/* 78 */     this.forceUse = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\bomu\BreezeBreathBombWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */