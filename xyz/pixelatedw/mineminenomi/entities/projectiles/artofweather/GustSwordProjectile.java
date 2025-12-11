/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class GustSwordProjectile extends AbilityProjectileEntity {
/*    */   public GustSwordProjectile(EntityType type, World world) {
/* 15 */     super(type, world);
/*    */   }
/*    */   
/*    */   public GustSwordProjectile(World world, LivingEntity player) {
/* 19 */     super((EntityType)ArtOfWeatherProjectiles.GUST_SWORD.get(), world, player, GustSwordAbility.INSTANCE);
/*    */     
/* 21 */     setDamage(2.0F);
/*    */     
/* 23 */     this.onTickEvent = this::onTickEvent;
/* 24 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 28 */     Vector3d speed = WyHelper.propulsion((LivingEntity)func_234616_v_(), 4.0D, 4.0D);
/*    */     
/* 30 */     if (hitEntity.func_70097_a((DamageSource)getDamageSource(), 15.0F)) {
/* 31 */       AbilityHelper.setDeltaMovement((Entity)hitEntity, speed.field_72450_a, 0.2D, speed.field_72449_c);
/*    */     }
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 36 */     if (!this.field_70170_p.field_72995_K)
/* 37 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.GUST_SWORD.get(), (Entity)this, func_226277_ct_(), func_226278_cu_(), func_226281_cx_()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\artofweather\GustSwordProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */