/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.GenkotsuMeteorAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ 
/*    */ public class GenkotsuMeteorWrapperGoal extends AbilityWrapperGoal<MobEntity, GenkotsuMeteorAbility> {
/* 15 */   private double distance = 2.0D; private LivingEntity target;
/*    */   
/*    */   public GenkotsuMeteorWrapperGoal(MobEntity entity) {
/* 18 */     super(entity, GenkotsuMeteorAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 23 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 24 */       return false;
/*    */     }
/*    */     
/* 27 */     this.target = this.entity.func_70638_az();
/*    */     
/* 29 */     if (GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, this.distance)) {
/* 30 */       return false;
/*    */     }
/*    */     
/* 33 */     if (((GenkotsuMeteorAbility)getAbility()).isOnCooldown()) {
/* 34 */       return false;
/*    */     }
/*    */     
/* 37 */     this.entity.func_184201_a(EquipmentSlotType.OFFHAND, new ItemStack((IItemProvider)ModItems.CANNON_BALL.get()));
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 48 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 53 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopWrapper() {
/* 58 */     ItemStack offhandStack = this.entity.func_184592_cb();
/* 59 */     offhandStack.func_190918_g(1);
/* 60 */     if (!offhandStack.func_190926_b() && offhandStack.func_77973_b().equals(ModItems.CANNON_BALL.get()))
/* 61 */       this.entity.func_184201_a(EquipmentSlotType.OFFHAND, ItemStack.field_190927_a); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\brawler\GenkotsuMeteorWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */