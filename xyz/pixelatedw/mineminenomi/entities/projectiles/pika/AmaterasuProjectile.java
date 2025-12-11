/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.pika;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.pika.AmaterasuAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class AmaterasuProjectile extends AbilityProjectileEntity {
/*    */   public AmaterasuProjectile(EntityType type, World world) {
/* 21 */     super(type, world);
/*    */   }
/*    */   
/*    */   public AmaterasuProjectile(World world, LivingEntity player) {
/* 25 */     super((EntityType)PikaProjectiles.AMATERASU.get(), world, player, AmaterasuAbility.INSTANCE);
/*    */     
/* 27 */     setDamage(70.0F);
/* 28 */     setArmorPiercing(1.0F);
/*    */     
/* 30 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 31 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 36 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), getDamage() / 4.0F);
/* 37 */     explosion.setStaticDamage(getDamage() * 0.75F);
/* 38 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect((int)(getDamage() / 6.0F)));
/* 39 */     explosion.doExplosion();
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 44 */     if (!this.field_70170_p.field_72995_K) {
/*    */       
/* 46 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.PIKA.get());
/* 47 */       data.setLife(40);
/* 48 */       data.setSize(10.0F);
/* 49 */       data.setRotation(Vector3f.field_229181_d_);
/* 50 */       WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_(), func_226278_cu_(), func_226281_cx_());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\pika\AmaterasuProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */