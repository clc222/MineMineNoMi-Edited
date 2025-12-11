/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.TargetHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ 
/*    */ public class CycloneTempoEffect extends GuardingEffect {
/*    */   public CycloneTempoEffect() {
/* 18 */     super(true, false);
/* 19 */     func_111186_k().clear();
/* 20 */     func_220304_a(Attributes.field_233820_c_, "7d355019-7ef9-4beb-bcba-8b2608a73380", 1.0D, AttributeModifier.Operation.ADDITION)
/* 21 */       .func_220304_a((Attribute)ModAttributes.DAMAGE_REDUCTION.get(), "7b3a9108-6a36-11eb-9439-0242ac130002", 0.15D, AttributeModifier.Operation.ADDITION);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier) {
/* 26 */     return (duration % 10 == 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_76394_a(LivingEntity entity, int amplifier) {
/* 31 */     List<LivingEntity> list = TargetHelper.getEntitiesInArea(entity, 6.0D, TargetsPredicate.DEFAULT_AREA_CHECK, new Class[0]);
/* 32 */     Vector3d center = entity.func_213303_ch();
/* 33 */     for (LivingEntity target : list) {
/* 34 */       Vector3d speed = target.func_213303_ch().func_178788_d(center).func_72432_b().func_216372_d(2.0D, 1.0D, 2.0D);
/* 35 */       AbilityHelper.setDeltaMovement((Entity)target, speed.field_72450_a, 0.4D, speed.field_72449_c);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRender(EffectInstance effect) {
/* 41 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRenderHUD(EffectInstance effect) {
/* 46 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\CycloneTempoEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */