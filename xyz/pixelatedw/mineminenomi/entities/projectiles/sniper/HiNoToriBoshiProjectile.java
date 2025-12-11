/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.HiNoToriBoshiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class HiNoToriBoshiProjectile extends AbilityProjectileEntity {
/*    */   public HiNoToriBoshiProjectile(EntityType type, World world) {
/* 24 */     super(type, world);
/*    */     
/* 26 */     setDamage(25.0F);
/* 27 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 28 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 29 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   public HiNoToriBoshiProjectile(World world, LivingEntity player) {
/* 33 */     this((EntityType)SniperProjectiles.HI_NO_TORI_BOSHI.get(), world, player, HiNoToriBoshiAbility.INSTANCE);
/*    */   }
/*    */   
/*    */   public HiNoToriBoshiProjectile(EntityType type, World world, LivingEntity player, AbilityCore<?> parent) {
/* 37 */     super(type, world, player, parent);
/*    */     
/* 39 */     setDamage(25.0F);
/* 40 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 41 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 42 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 47 */     if (hitEntity instanceof CreeperEntity) {
/*    */       
/* 49 */       ((CreeperEntity)hitEntity).func_146079_cb();
/*    */       
/*    */       return;
/*    */     } 
/* 53 */     AbilityHelper.setSecondsOnFireBy((Entity)hitEntity, 5, getThrower());
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 58 */     if (this.field_70170_p.func_180495_p(hit).func_177230_c() == ModBlocks.OIL_SPILL.get()) {
/*    */       
/* 60 */       ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 4.0F);
/* 61 */       explosion.setStaticDamage(10.0F);
/* 62 */       explosion.setFireAfterExplosion(true);
/* 63 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(4));
/*    */       
/* 65 */       explosion.doExplosion();
/*    */       
/*    */       return;
/*    */     } 
/* 69 */     AbilityHelper.placeBlockIfAllowed(getThrower(), hit.func_177982_a(0, 1, 0), Blocks.field_150480_ab.func_176223_P(), AirBlockProtectionRule.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 74 */     if (!this.field_70170_p.field_72995_K)
/*    */     {
/* 76 */       for (int i = 0; i < 2; i++) {
/*    */         
/* 78 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 79 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 80 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 82 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MERA.get());
/* 83 */         data.setLife(10);
/* 84 */         data.setSize(1.0F);
/* 85 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + 0.2D + offsetY, func_226281_cx_() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\sniper\HiNoToriBoshiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */