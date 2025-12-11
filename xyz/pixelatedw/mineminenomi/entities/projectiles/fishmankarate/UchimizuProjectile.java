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
/*    */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.UchimizuAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.WaterExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class UchimizuProjectile extends AbilityProjectileEntity {
/*    */   public UchimizuProjectile(EntityType type, World world) {
/* 20 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public UchimizuProjectile(World world, LivingEntity player) {
/* 25 */     super((EntityType)FishmanKarateProjectiles.UCHIMIZU.get(), world, player, UchimizuAbility.INSTANCE);
/*    */     
/* 27 */     setDamage(5.0F);
/* 28 */     setMaxLife(30);
/*    */     
/* 30 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 31 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 36 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 1.0F);
/* 37 */     explosion.setExplosionSound(false);
/* 38 */     explosion.setDamageOwner(false);
/* 39 */     explosion.disableExplosionKnockback();
/* 40 */     explosion.setDestroyBlocks(false);
/* 41 */     explosion.setFireAfterExplosion(false);
/* 42 */     explosion.setSmokeParticles((ParticleEffect)new WaterExplosionParticleEffect());
/* 43 */     explosion.setDamageEntities(false);
/* 44 */     explosion.doExplosion();
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 49 */     if (!this.field_70170_p.field_72995_K)
/*    */     {
/* 51 */       for (int i = 0; i < 2; i++) {
/*    */         
/* 53 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 54 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 55 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 57 */         ((ServerWorld)this.field_70170_p).func_195598_a((IParticleData)ParticleTypes.field_197630_w, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ, 1, 0.0D, 0.0D, 0.0D, -0.1D);
/* 58 */         ((ServerWorld)this.field_70170_p).func_195598_a((IParticleData)ParticleTypes.field_197612_e, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.1D);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\fishmankarate\UchimizuProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */