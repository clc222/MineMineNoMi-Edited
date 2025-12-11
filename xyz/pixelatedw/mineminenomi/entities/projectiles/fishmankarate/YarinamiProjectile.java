/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.fishmankarate;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.YarinamiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.WaterExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class YarinamiProjectile extends AbilityProjectileEntity {
/*    */   public YarinamiProjectile(EntityType type, World world) {
/* 20 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public YarinamiProjectile(World world, LivingEntity player) {
/* 25 */     super((EntityType)FishmanKarateProjectiles.YARINAMI.get(), world, player, YarinamiAbility.INSTANCE);
/*    */     
/* 27 */     setDamage(60.0F);
/* 28 */     setCanGetStuckInGround();
/* 29 */     setPassThroughEntities();
/* 30 */     setMaxLife(30);
/*    */     
/* 32 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 33 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 38 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 4.0F);
/* 39 */     explosion.setStaticDamage(15.0F);
/* 40 */     explosion.setExplosionSound(false);
/* 41 */     explosion.setDamageOwner(false);
/* 42 */     explosion.setDestroyBlocks(true);
/* 43 */     explosion.setFireAfterExplosion(false);
/* 44 */     explosion.setSmokeParticles((ParticleEffect)new WaterExplosionParticleEffect());
/* 45 */     explosion.setDamageEntities(false);
/* 46 */     explosion.doExplosion();
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 51 */     if (!this.field_70170_p.field_72995_K)
/*    */     {
/* 53 */       for (int i = 0; i < 15; i++) {
/*    */         
/* 55 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 56 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 57 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 59 */         ((ServerWorld)this.field_70170_p).func_195598_a((IParticleData)ParticleTypes.field_197630_w, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ, 1, 0.0D, 0.0D, 0.0D, -0.1D);
/* 60 */         ((ServerWorld)this.field_70170_p).func_195598_a((IParticleData)ParticleTypes.field_197612_e, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.1D);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\fishmankarate\YarinamiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */