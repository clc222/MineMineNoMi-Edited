/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.fishman;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.KarakusagawaraSeikenAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class KarakusagawaraSeikenWrapperGoal
/*    */   extends AbilityWrapperGoal<MobEntity, KarakusagawaraSeikenAbility> {
/*    */   private LivingEntity target;
/*    */   private double distance;
/*    */   
/*    */   public KarakusagawaraSeikenWrapperGoal(MobEntity entity) {
/* 18 */     super(entity, KarakusagawaraSeikenAbility.INSTANCE);
/* 19 */     this.distance = ((Float)((KarakusagawaraSeikenAbility)getAbility()).getComponent(ModAbilityKeys.RANGE).flatMap(comp -> Optional.of(Float.valueOf(comp.getRange()))).orElse(Float.valueOf(10.0F))).floatValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 24 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 25 */       return false;
/*    */     }
/*    */     
/* 28 */     this.target = this.entity.func_70638_az();
/*    */     
/* 30 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.distance)) {
/* 31 */       return false;
/*    */     }
/*    */     
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 39 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.distance * 2.0D)) {
/* 40 */       return false;
/*    */     }
/* 42 */     return true;
/*    */   }
/*    */   
/*    */   public void startWrapper() {}
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\fishman\KarakusagawaraSeikenWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */