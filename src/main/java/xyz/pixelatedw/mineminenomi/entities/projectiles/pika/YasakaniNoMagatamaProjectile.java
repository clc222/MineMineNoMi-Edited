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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class YasakaniNoMagatamaProjectile extends AbilityProjectileEntity {
/*    */   public YasakaniNoMagatamaProjectile(EntityType type, World world) {
/* 22 */     super(type, world);
/*    */   }
/*    */   
/*    */   public YasakaniNoMagatamaProjectile(World world, LivingEntity player, Ability ability) {
/* 26 */     super((EntityType)PikaProjectiles.YASAKANI_NO_MAGATAMA.get(), world, player, ability.getCore());
/*    */     
/* 28 */     setDamage(4.0F);
/* 29 */     setArmorPiercing(1.0F);
/* 30 */     setHurtTime(10);
/*    */     
/* 32 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 33 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 38 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 3.0F);
/* 39 */     explosion.setStaticDamage(4.0F);
/* 40 */     explosion.disableExplosionKnockback();
/* 41 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
/* 42 */     explosion.doExplosion();
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 47 */     if (!this.field_70170_p.field_72995_K) {
/*    */       
/* 49 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.PIKA.get());
/* 50 */       data.setLife(30);
/* 51 */       data.setSize(2.5F);
/* 52 */       data.setRotation(Vector3f.field_229181_d_);
/* 53 */       WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_(), func_226278_cu_(), func_226281_cx_());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\pika\YasakaniNoMagatamaProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */