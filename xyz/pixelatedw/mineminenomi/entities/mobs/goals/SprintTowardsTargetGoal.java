/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*    */ 
/*    */ public class SprintTowardsTargetGoal
/*    */   extends Goal
/*    */ {
/* 16 */   private static final UUID SPEED_MODIFIER_SPRINTING_UUID = UUID.fromString("c320875f-76d4-4598-b59d-d63ca72a564c");
/*    */   
/*    */   private MobEntity entity;
/* 19 */   private float speedModifier = 0.3F;
/*    */   private double currentSpeedStat;
/*    */   
/*    */   public SprintTowardsTargetGoal(MobEntity entity) {
/* 23 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 28 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 29 */       return false;
/*    */     }
/*    */     
/* 32 */     LivingEntity target = this.entity.func_70638_az();
/*    */     
/* 34 */     if (!GoalUtil.canSee(this.entity, target)) {
/* 35 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 39 */     if (!this.entity.func_70051_ag() && GoalUtil.isOutsideDistance((LivingEntity)this.entity, target, 15.0D)) {
/* 40 */       return true;
/*    */     }
/* 42 */     if (this.entity.func_70051_ag() && GoalUtil.isWithinDistance((LivingEntity)this.entity, target, 5.0D)) {
/* 43 */       return false;
/*    */     }
/*    */     
/* 46 */     return this.entity.func_70051_ag();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 51 */     return super.func_75253_b();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 56 */     setSprinting(true);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_75246_d() {}
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 65 */     setSprinting(false);
/*    */   }
/*    */   
/*    */   public void setSprinting(boolean flag) {
/* 69 */     this.entity.func_70031_b(flag);
/* 70 */     ModifiableAttributeInstance attr = this.entity.func_110148_a(Attributes.field_233821_d_);
/* 71 */     attr.func_188479_b(AttributeHelper.SPEED_MODIFIER_SPRINTING_UUID);
/* 72 */     attr.func_188479_b(SPEED_MODIFIER_SPRINTING_UUID);
/* 73 */     if (flag) {
/* 74 */       attr.func_233767_b_(getSpeedModifier());
/*    */     }
/* 76 */     this.currentSpeedStat = attr.func_111126_e();
/* 77 */     this.entity.func_70661_as().func_75489_a(this.currentSpeedStat);
/*    */   }
/*    */   
/*    */   public AttributeModifier getSpeedModifier() {
/* 81 */     return new AttributeModifier(SPEED_MODIFIER_SPRINTING_UUID, "Sprinting speed boost", this.speedModifier, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\SprintTowardsTargetGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */