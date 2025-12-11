/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.kachi;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.vector.Vector3i;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.kachi.EvaporateAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class EvaporateWrapperGoal extends AbilityWrapperGoal<MobEntity, EvaporateAbility> {
/*    */   public EvaporateWrapperGoal(MobEntity entity) {
/* 11 */     super(entity, EvaporateAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 16 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 17 */       return false;
/*    */     }
/*    */     
/* 20 */     if (!this.entity.func_233570_aj_()) {
/* 21 */       return false;
/*    */     }
/*    */     
/* 24 */     if (this.entity.func_70090_H()) {
/* 25 */       return true;
/*    */     }
/*    */     
/* 28 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/* 29 */     mutpos.func_189533_g((Vector3i)this.entity.func_233580_cy_());
/* 30 */     for (int x = -5; x < 5; x++) {
/* 31 */       for (int z = -5; z < 5; z++) {
/* 32 */         mutpos.func_181079_c(mutpos.func_177958_n() + x, mutpos.func_177956_o(), mutpos.func_177952_p() + z);
/* 33 */         boolean hasWaterNear = this.entity.field_70170_p.func_201671_F((BlockPos)mutpos);
/* 34 */         if (hasWaterNear) {
/* 35 */           return true;
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 40 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 45 */     return true;
/*    */   }
/*    */   
/*    */   public void startWrapper() {}
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\kachi\EvaporateWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */