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
/*    */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.MurasameAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.WaterExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class MurasameProjectile extends AbilityProjectileEntity {
/*    */   public MurasameProjectile(EntityType type, World world) {
/* 20 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public MurasameProjectile(World world, LivingEntity player) {
/* 25 */     super((EntityType)FishmanKarateProjectiles.MURASAME.get(), world, player, MurasameAbility.INSTANCE);
/*    */     
/* 27 */     setDamage(20.0F);
/* 28 */     setMaxLife(32);
/* 29 */     setPassThroughEntities();
/*    */     
/* 31 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 32 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 37 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 2.0F);
/* 38 */     explosion.setStaticDamage(8.0F);
/* 39 */     explosion.setExplosionSound(false);
/* 40 */     explosion.setDamageOwner(false);
/* 41 */     explosion.setDestroyBlocks(true);
/* 42 */     explosion.setFireAfterExplosion(false);
/* 43 */     explosion.setSmokeParticles((ParticleEffect)new WaterExplosionParticleEffect());
/* 44 */     explosion.setDamageEntities(false);
/* 45 */     explosion.doExplosion();
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 50 */     if (!this.field_70170_p.field_72995_K)
/*    */     {
/* 52 */       for (int i = 0; i < 2; i++) {
/*    */         
/* 54 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 55 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 56 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 58 */         ((ServerWorld)this.field_70170_p).func_195598_a((IParticleData)ParticleTypes.field_197630_w, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ, 1, 0.0D, 0.0D, 0.0D, -0.1D);
/* 59 */         ((ServerWorld)this.field_70170_p).func_195598_a((IParticleData)ParticleTypes.field_197612_e, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.1D);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\fishmankarate\MurasameProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */