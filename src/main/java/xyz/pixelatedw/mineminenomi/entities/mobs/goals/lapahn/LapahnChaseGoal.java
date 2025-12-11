/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.lapahn;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.LapahnEntity;
/*    */ 
/*    */ public class LapahnChaseGoal extends Goal {
/* 14 */   private static final AttributeModifier CHASE_MODIFIER = new AttributeModifier(UUID.fromString("0524f5ca-28d0-487b-ab03-b77c4047cb25"), "Sprinting speed boost", 1.0D, AttributeModifier.Operation.MULTIPLY_BASE);
/*    */   
/* 16 */   private Interval interval = new Interval(10);
/*    */   private LapahnEntity entity;
/*    */   
/*    */   public LapahnChaseGoal(LapahnEntity entity) {
/* 20 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 25 */     if (!this.interval.canTick()) {
/* 26 */       return false;
/*    */     }
/* 28 */     if (this.entity.isChasing()) {
/* 29 */       return false;
/*    */     }
/* 31 */     if (!GoalUtil.hasAliveTarget((MobEntity)this.entity)) {
/* 32 */       return false;
/*    */     }
/*    */     
/* 35 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 40 */     if (!GoalUtil.hasAliveTarget((MobEntity)this.entity)) {
/* 41 */       return false;
/*    */     }
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 48 */     super.func_75249_e();
/* 49 */     this.entity.setChasing(true);
/* 50 */     ModifiableAttributeInstance attr = this.entity.func_110148_a(Attributes.field_233821_d_);
/* 51 */     if (attr != null) {
/* 52 */       attr.func_111124_b(CHASE_MODIFIER);
/* 53 */       attr.func_233767_b_(CHASE_MODIFIER);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 59 */     super.func_75251_c();
/* 60 */     this.entity.setChasing(false);
/* 61 */     ModifiableAttributeInstance attr = this.entity.func_110148_a(Attributes.field_233821_d_);
/* 62 */     if (attr != null)
/* 63 */       attr.func_111124_b(CHASE_MODIFIER); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\lapahn\LapahnChaseGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */