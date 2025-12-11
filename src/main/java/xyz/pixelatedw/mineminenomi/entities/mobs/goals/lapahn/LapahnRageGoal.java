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
/*    */ public class LapahnRageGoal extends Goal {
/* 14 */   private static final AttributeModifier RAGE_MODIFIER = new AttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), "Rage Mode Multiplier", 10.0D, AttributeModifier.Operation.ADDITION);
/*    */   
/* 16 */   private Interval canUseInterval = new Interval(10);
/*    */   private LapahnEntity entity;
/*    */   
/*    */   public LapahnRageGoal(LapahnEntity entity) {
/* 20 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 25 */     if (this.entity.isEnraged()) {
/* 26 */       return false;
/*    */     }
/* 28 */     if (!this.canUseInterval.canTick()) {
/* 29 */       return false;
/*    */     }
/* 31 */     if (!GoalUtil.hasAliveTarget((MobEntity)this.entity)) {
/* 32 */       return false;
/*    */     }
/* 34 */     if (this.entity.func_110143_aJ() > this.entity.func_110138_aP() / 3.0F) {
/* 35 */       return false;
/*    */     }
/*    */     
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 48 */     this.entity.setEnraged(true);
/* 49 */     this.entity.getNearbyLapahns().forEach(lapahn -> lapahn.setEnraged(true));
/* 50 */     this.entity.setResting(false);
/* 51 */     ModifiableAttributeInstance attr = this.entity.func_110148_a(Attributes.field_233823_f_);
/* 52 */     if (attr != null) {
/* 53 */       attr.func_111124_b(RAGE_MODIFIER);
/* 54 */       attr.func_233767_b_(RAGE_MODIFIER);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\lapahn\LapahnRageGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */