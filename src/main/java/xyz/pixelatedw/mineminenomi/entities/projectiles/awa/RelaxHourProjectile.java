/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.awa;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class RelaxHourProjectile extends AbilityProjectileEntity {
/*    */   public RelaxHourProjectile(EntityType type, World world) {
/* 15 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public RelaxHourProjectile(World world, LivingEntity player) {
/* 20 */     super((EntityType)AwaProjectiles.RELAX_HOUR.get(), world, player, RelaxHourAbility.INSTANCE);
/* 21 */     setDamage(3.0F);
/* 22 */     setMaxLife(40);
/*    */     
/* 24 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 25 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 29 */     hitEntity.func_195064_c(new EffectInstance((Effect)ModEffects.WASHED.get(), 200, 0));
/* 30 */     this.onBlockImpactEvent.onImpact(hitEntity.func_233580_cy_());
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 34 */     if (!this.field_70170_p.field_72995_K)
/* 35 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.RELAX_HOUR.get(), (Entity)this, func_226277_ct_(), func_226278_cu_(), func_226281_cx_()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\awa\RelaxHourProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */