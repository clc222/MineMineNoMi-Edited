/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.dugong;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.AbstractDugongEntity;
/*    */ 
/*    */ public class DugongRageGoal extends TickedGoal<AbstractDugongEntity> {
/* 15 */   private static final AttributeModifier RAGE_MODIFIER = new AttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), "Rage Mode Multiplier", 10.0D, AttributeModifier.Operation.ADDITION);
/*    */   
/* 17 */   private Interval canUseInterval = new Interval(10);
/*    */   
/*    */   public DugongRageGoal(AbstractDugongEntity entity) {
/* 20 */     super((MobEntity)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 25 */     if (!this.canUseInterval.canTick()) {
/* 26 */       return false;
/*    */     }
/* 28 */     if (((AbstractDugongEntity)this.entity).getRageTarget() == null) {
/* 29 */       return false;
/*    */     }
/* 31 */     if (((AbstractDugongEntity)this.entity).func_152114_e(((AbstractDugongEntity)this.entity).getRageTarget())) {
/* 32 */       ((AbstractDugongEntity)this.entity).setEnraged(null);
/* 33 */       return false;
/*    */     } 
/*    */     
/* 36 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 41 */     if (((AbstractDugongEntity)this.entity).getRageTarget() == null || !((AbstractDugongEntity)this.entity).getRageTarget().func_70089_S()) {
/* 42 */       return false;
/*    */     }
/* 44 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 49 */     super.func_75249_e();
/* 50 */     for (int i = 0; i < 5; i++) {
/* 51 */       double offsetX = ((AbstractDugongEntity)this.entity).func_70681_au().nextGaussian() * 0.2D;
/* 52 */       double offsetY = ((AbstractDugongEntity)this.entity).func_70681_au().nextGaussian() * 0.2D;
/* 53 */       double offsetZ = ((AbstractDugongEntity)this.entity).func_70681_au().nextGaussian() * 0.2D;
/* 54 */       ((ServerWorld)((AbstractDugongEntity)this.entity).func_130014_f_()).func_195598_a((IParticleData)ParticleTypes.field_197609_b, ((AbstractDugongEntity)this.entity).func_226277_ct_() + offsetX, ((AbstractDugongEntity)this.entity).func_226278_cu_() + 1.0D + offsetY, ((AbstractDugongEntity)this.entity)
/* 55 */           .func_226281_cx_() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*    */     } 
/* 57 */     ((AbstractDugongEntity)this.entity).func_70624_b(((AbstractDugongEntity)this.entity).getRageTarget());
/* 58 */     ((AbstractDugongEntity)this.entity).setResting(false);
/* 59 */     ((AbstractDugongEntity)this.entity).stopTraining();
/* 60 */     ModifiableAttributeInstance attr = ((AbstractDugongEntity)this.entity).func_110148_a(Attributes.field_233823_f_);
/* 61 */     if (attr != null) {
/* 62 */       attr.func_111124_b(RAGE_MODIFIER);
/* 63 */       attr.func_233767_b_(RAGE_MODIFIER);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 69 */     super.func_75251_c();
/* 70 */     ((AbstractDugongEntity)this.entity).setEnraged(null);
/* 71 */     ((AbstractDugongEntity)this.entity).func_70624_b(null);
/* 72 */     ModifiableAttributeInstance attr = ((AbstractDugongEntity)this.entity).func_110148_a(Attributes.field_233823_f_);
/* 73 */     if (attr != null)
/* 74 */       attr.func_111124_b(RAGE_MODIFIER); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\dugong\DugongRageGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */