/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.suna;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class SablesPesadoProjectile extends AbilityProjectileEntity {
/*    */   public SablesPesadoProjectile(EntityType type, World world) {
/* 20 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public SablesPesadoProjectile(World world, LivingEntity player) {
/* 25 */     super((EntityType)SunaProjectiles.SABLES_PESADO.get(), world, player);
/*    */     
/* 27 */     setDamage(50.0F);
/* 28 */     setMaxLife(50);
/* 29 */     setPhysical();
/* 30 */     setAffectedByImbuing();
/* 31 */     setArmorPiercing();
/*    */     
/* 33 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 34 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 39 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 6.0F);
/* 40 */     explosion.setStaticDamage(35.0F);
/* 41 */     explosion.setExplosionSound(true);
/* 42 */     explosion.setDamageOwner(false);
/* 43 */     explosion.setDestroyBlocks(true);
/* 44 */     explosion.setFireAfterExplosion(false);
/* 45 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(7));
/* 46 */     explosion.setDamageEntities(true);
/* 47 */     explosion.doExplosion();
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 52 */     if (!this.field_70170_p.field_72995_K) {
/*    */       int i;
/* 54 */       for (i = 0; i < 20; i++) {
/*    */         
/* 56 */         double offsetX = WyHelper.randomDouble();
/* 57 */         double offsetY = WyHelper.randomDouble();
/* 58 */         double offsetZ = WyHelper.randomDouble();
/*    */         
/* 60 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.SUNA.get());
/* 61 */         data.setLife(6);
/* 62 */         data.setSize(1.3F);
/* 63 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*    */       } 
/*    */       
/* 66 */       for (i = 0; i < 2; i++) {
/*    */         
/* 68 */         double offsetX = WyHelper.randomDouble();
/* 69 */         double offsetY = WyHelper.randomDouble();
/* 70 */         double offsetZ = WyHelper.randomDouble();
/*    */         
/* 72 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.SUNA2.get());
/* 73 */         data.setLife(4);
/* 74 */         data.setSize(1.2F);
/* 75 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\suna\SablesPesadoProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */