/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class CoupDeVentProjectile extends AbilityProjectileEntity {
/*    */   public CoupDeVentProjectile(EntityType type, World world) {
/* 20 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public CoupDeVentProjectile(World world, LivingEntity player, Ability ability) {
/* 25 */     super((EntityType)CyborgProjectiles.COUP_DE_VENT.get(), world, player, ability);
/*    */     
/* 27 */     setDamage(50.0F);
/* 28 */     setPassThroughEntities();
/* 29 */     setMaxLife(15);
/*    */     
/* 31 */     setEntityCollisionSize(5.5D);
/*    */     
/* 33 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 34 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity target) {
/* 39 */     double xPower = WyHelper.randomDouble() * 400.0D;
/* 40 */     if (xPower >= 0.0D) {
/* 41 */       xPower = MathHelper.func_151237_a(xPower, 200.0D, 400.0D);
/*    */     } else {
/*    */       
/* 44 */       xPower = MathHelper.func_151237_a(xPower, -400.0D, -200.0D);
/*    */     } 
/*    */     
/* 47 */     double zPower = WyHelper.randomDouble() * 400.0D;
/* 48 */     if (zPower >= 0.0D) {
/* 49 */       zPower = MathHelper.func_151237_a(zPower, 200.0D, 400.0D);
/*    */     } else {
/*    */       
/* 52 */       zPower = MathHelper.func_151237_a(zPower, -400.0D, -200.0D);
/*    */     } 
/*    */     
/* 55 */     target.func_195064_c(new EffectInstance((Effect)ModEffects.LAUNCHED.get(), 1200, 0));
/* 56 */     AbilityHelper.setDeltaMovement((Entity)target, xPower, 10.0D, zPower);
/* 57 */     target.field_70143_R = 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 62 */     for (int i = 0; i < 25; i++) {
/*    */       
/* 64 */       double offsetX = WyHelper.randomDouble() * 1.2D;
/* 65 */       double offsetY = WyHelper.randomDouble() * 1.2D;
/* 66 */       double offsetZ = WyHelper.randomDouble() * 1.2D;
/*    */       
/* 68 */       ((ServerWorld)this.field_70170_p).func_195598_a((IParticleData)ParticleTypes.field_197624_q, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ, 1, 0.0D, 0.0D, 0.0D, -0.1D);
/* 69 */       if (i % 5 == 0)
/* 70 */         ((ServerWorld)this.field_70170_p).func_195598_a((IParticleData)ParticleTypes.field_218419_B, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ, 1, 0.0D, 0.0D, 0.0D, -0.1D); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\cyborg\CoupDeVentProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */