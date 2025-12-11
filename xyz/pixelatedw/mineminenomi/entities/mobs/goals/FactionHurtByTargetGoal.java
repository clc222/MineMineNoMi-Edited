/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityPredicate;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*    */ 
/*    */ public class FactionHurtByTargetGoal
/*    */   extends HurtByTargetGoal
/*    */ {
/*    */   @Nullable
/*    */   private Predicate<Entity> factionPredicate;
/*    */   
/*    */   public FactionHurtByTargetGoal(CreatureEntity entity, @Nullable Predicate<Entity> factionPredicate, Class<?>... exclude) {
/* 18 */     super(entity, exclude);
/* 19 */     this.factionPredicate = factionPredicate;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 24 */     if (this.factionPredicate != null && !this.factionPredicate.test(this.field_188509_g)) {
/* 25 */       return false;
/*    */     }
/* 27 */     return super.func_75253_b();
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean func_220777_a(@Nullable LivingEntity potentialTarget, EntityPredicate targetPredicate) {
/* 32 */     if (this.factionPredicate != null && this.factionPredicate.test(potentialTarget)) {
/* 33 */       return super.func_220777_a(potentialTarget, targetPredicate);
/*    */     }
/* 35 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\FactionHurtByTargetGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */