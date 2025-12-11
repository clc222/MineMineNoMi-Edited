/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.entity.item.BoatEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.kairosekicoating.IKairosekiCoating;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.kairosekicoating.KairosekiCoatingCapability;
/*    */ 
/*    */ public class BreakBoatGoal extends Goal {
/*    */   private final CreatureEntity entity;
/*    */   
/*    */   public BreakBoatGoal(CreatureEntity entity) {
/* 19 */     this.entity = entity;
/*    */   }
/*    */   private Entity boat;
/*    */   
/*    */   public boolean func_75250_a() {
/* 24 */     List<BoatEntity> list = this.entity.field_70170_p.func_217357_a(BoatEntity.class, this.entity.func_174813_aQ().func_72321_a(2.0D, 2.0D, 2.0D));
/*    */     
/* 26 */     for (BoatEntity boat : list) {
/* 27 */       Entity entity = boat.func_184179_bs();
/* 28 */       boolean isFullyCoated = ((Boolean)KairosekiCoatingCapability.get((Entity)this.entity).map(props -> Boolean.valueOf(props.isFullyCoated())).orElse(Boolean.valueOf(false))).booleanValue();
/* 29 */       if (entity != null && entity instanceof LivingEntity && boat.func_70089_S() && this.entity.func_70685_l((Entity)boat) && isFullyCoated) {
/* 30 */         this.boat = (Entity)boat;
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 35 */     return (this.boat != null && this.boat.func_70089_S());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 40 */     return (this.boat != null && this.boat.func_70089_S());
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 45 */     ModifiableAttributeInstance attr = this.entity.func_110148_a(Attributes.field_233823_f_);
/* 46 */     float damage = (float)((attr != null) ? attr.func_111126_e() : 1.0D);
/* 47 */     this.boat.func_70097_a(DamageSource.func_76358_a((LivingEntity)this.entity), damage);
/* 48 */     this.boat = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 53 */     this.boat = null;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\BreakBoatGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */