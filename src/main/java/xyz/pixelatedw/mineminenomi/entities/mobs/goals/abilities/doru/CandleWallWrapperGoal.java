/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.doru;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.projectile.ProjectileEntity;
/*    */ import net.minecraft.world.IWorld;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.doru.CandleWallAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class CandleWallWrapperGoal extends AbilityWrapperGoal<MobEntity, CandleWallAbility> {
/*    */   private static final float CHECK_AREA = 40.0F;
/*    */   private ProjectileEntity projectileTarget;
/*    */   private int hits;
/* 21 */   private int triggerHits = 3;
/* 22 */   private double previousDistance = 0.0D;
/*    */   
/*    */   public CandleWallWrapperGoal(MobEntity entity) {
/* 25 */     super(entity, CandleWallAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 31 */     boolean isOnCooldown = ((Boolean)((CandleWallAbility)getAbility()).getComponent(ModAbilityKeys.COOLDOWN).map(CooldownComponent::isOnCooldown).orElse(Boolean.valueOf(false))).booleanValue();
/* 32 */     if (isOnCooldown) {
/* 33 */       return false;
/*    */     }
/*    */     
/* 36 */     List<ProjectileEntity> projectiles = WyHelper.getNearbyEntities(this.entity.func_213303_ch(), (IWorld)this.entity.field_70170_p, 40.0D, null, new Class[] { ProjectileEntity.class });
/* 37 */     for (ProjectileEntity proj : projectiles) {
/* 38 */       boolean isEnemyProjectile = ModEntityPredicates.getEnemyFactions((LivingEntity)this.entity).test(proj.func_234616_v_());
/* 39 */       if (isEnemyProjectile) {
/* 40 */         this.projectileTarget = proj;
/* 41 */         double distance = proj.func_70068_e((Entity)this.entity);
/* 42 */         if (this.previousDistance == 0.0D) {
/* 43 */           this.previousDistance = distance;
/*    */         }
/* 45 */         if (distance < this.previousDistance) {
/* 46 */           this.hits++;
/* 47 */           this.previousDistance = distance;
/*    */         } 
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 53 */     if (this.hits < this.triggerHits) {
/* 54 */       return false;
/*    */     }
/*    */     
/* 57 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 62 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 67 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.projectileTarget);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tickWrapper() {}
/*    */ 
/*    */   
/*    */   public void stopWrapper() {
/* 76 */     this.hits = 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\doru\CandleWallWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */