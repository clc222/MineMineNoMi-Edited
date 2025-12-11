/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraft.util.math.RayTraceContext;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ReducedFallEffect
/*    */   extends Effect {
/*    */   public ReducedFallEffect() {
/* 17 */     super(EffectType.BENEFICIAL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier) {
/* 22 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_76394_a(LivingEntity entity, int amplifier) {
/* 28 */     Vector3d startVec = entity.func_213303_ch();
/*    */     
/* 30 */     boolean blockUnder = entity.field_70170_p.func_217299_a(new RayTraceContext(startVec, startVec.func_72441_c(0.0D, 0.0D, 0.0D), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, (Entity)entity)).func_216346_c().equals(RayTraceResult.Type.BLOCK);
/*    */     
/* 32 */     if ((entity.func_213322_ci()).field_72448_b < -1.0E-5D && !blockUnder)
/*    */     {
/* 34 */       AbilityHelper.setDeltaMovement((Entity)entity, entity.func_213322_ci().func_216372_d(1.0D, 0.1D, 1.0D));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRender(EffectInstance effect) {
/* 41 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRenderHUD(EffectInstance effect) {
/* 47 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\ReducedFallEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */