/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.fishmankarate;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.WaterExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class MizuShuryudanProjectile extends AbilityProjectileEntity {
/*    */   public MizuShuryudanProjectile(EntityType type, World world) {
/* 24 */     super(type, world);
/*    */   }
/*    */   private Optional<LivingEntity> target;
/*    */   public MizuShuryudanProjectile(World world, LivingEntity player) {
/* 28 */     super((EntityType)FishmanKarateProjectiles.MIZU_SHURYUDAN.get(), world, player, (AbilityCore)null);
/*    */     
/* 30 */     setDamage(7.0F);
/* 31 */     setMaxLife(400);
/* 32 */     setGravity(0.0F);
/*    */     
/* 34 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 35 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hit) {
/* 39 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_226277_ct_(), hit.func_226278_cu_(), hit.func_226281_cx_(), 3.0F);
/* 40 */     explosion.setExplosionSound(false);
/* 41 */     explosion.setDamageOwner(false);
/* 42 */     explosion.setDestroyBlocks(false);
/* 43 */     explosion.setFireAfterExplosion(false);
/* 44 */     explosion.setSmokeParticles((ParticleEffect)new WaterExplosionParticleEffect());
/* 45 */     explosion.setDamageEntities(false);
/* 46 */     explosion.doExplosion();
/*    */   }
/*    */   
/*    */   public void setTarget(Optional<LivingEntity> target) {
/* 50 */     this.target = target;
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 54 */     if (this.target == null || !this.target.isPresent() || !ModEntityPredicates.IS_ALIVE_AND_SURVIVAL.test(this.target.get())) {
/* 55 */       List<LivingEntity> list = WyHelper.getNearbyLiving(func_213303_ch(), (IWorld)this.field_70170_p, 8.0D, ModEntityPredicates.getEnemyFactions(getThrower()));
/* 56 */       list.remove(getThrower());
/* 57 */       list.sort(MobsHelper.ENTITY_THREAT);
/* 58 */       if (list.size() > 0) {
/* 59 */         this.target = list.stream().findAny();
/*    */       }
/* 61 */       if (this.field_70173_aa % 5 == 0) {
/* 62 */         AbilityHelper.setDeltaMovement((Entity)this, func_213322_ci().func_186678_a(0.7D));
/*    */       }
/*    */     } else {
/*    */       
/* 66 */       Vector3d dist = func_213303_ch().func_178788_d(((LivingEntity)this.target.get()).func_213303_ch().func_72441_c(0.0D, 1.0D, 0.0D)).func_72432_b().func_216372_d(0.4D, 1.0D, 0.4D);
/* 67 */       AbilityHelper.setDeltaMovement((Entity)this, -dist.field_72450_a, -dist.field_72448_b, -dist.field_72449_c);
/*    */     } 
/*    */     
/* 70 */     if (!this.field_70170_p.field_72995_K)
/* 71 */       for (int i = 0; i < 1; i++) {
/* 72 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 73 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 74 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/* 75 */         WyHelper.spawnParticles((IParticleData)ParticleTypes.field_197630_w, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\fishmankarate\MizuShuryudanProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */