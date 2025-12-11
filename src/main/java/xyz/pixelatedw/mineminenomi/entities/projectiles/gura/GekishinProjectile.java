/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.gura;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.item.FallingBlockEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class GekishinProjectile extends AbilityProjectileEntity {
/* 20 */   private Interval explosionInterval = new Interval(2);
/*    */   
/*    */   public GekishinProjectile(EntityType<Entity> type, World world) {
/* 23 */     super(type, world);
/*    */   }
/*    */   
/*    */   public GekishinProjectile(World world, LivingEntity player, Ability ability) {
/* 27 */     super((EntityType)GuraProjectiles.GEKISHIN.get(), world, player, ability.getCore());
/*    */     
/* 29 */     setDamage(70.0F);
/* 30 */     setMaxLife(50);
/* 31 */     setEntityCollisionSize(5.0D);
/* 32 */     setPassThroughEntities();
/* 33 */     setPassThroughBlocks();
/* 34 */     setEntityCollisionSize(3.0D);
/* 35 */     setArmorPiercing(0.75F);
/* 36 */     setBlocksAffectedLimit(12000);
/* 37 */     setUnavoidable();
/*    */     
/* 39 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 43 */     for (int i = 0; i < 3; i++) {
/* 44 */       if (this.explosionInterval.canTick()) {
/* 45 */         ((ServerWorld)this.field_70170_p).func_195598_a((IParticleData)ParticleTypes.field_197627_t, 
/*    */             
/* 47 */             func_226277_ct_() + WyHelper.randomDouble() * 1.5D, 
/* 48 */             func_226278_cu_() + WyHelper.randomDouble() * 1.5D, 
/* 49 */             func_226281_cx_() + WyHelper.randomDouble() * 1.5D, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*    */       
/*    */       }
/*    */       else {
/*    */         
/* 54 */         double offsetX = WyHelper.randomDouble() * 5.0D;
/* 55 */         double offsetY = WyHelper.randomDouble() * 5.0D;
/* 56 */         double offsetZ = WyHelper.randomDouble() * 5.0D;
/*    */         
/* 58 */         SimpleParticleData data = new SimpleParticleData((this.field_70146_Z.nextDouble() > 0.5D) ? (ParticleType)ModParticleTypes.MOKU.get() : (ParticleType)ModParticleTypes.MOKU2.get());
/*    */         
/* 60 */         data.setLife(3);
/* 61 */         data.setSize(10.0F);
/*    */         
/* 63 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*    */       } 
/*    */     } 
/*    */     
/* 67 */     int size = 0;
/*    */     
/* 69 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, func_226277_ct_(), func_226278_cu_(), func_226281_cx_(), 8.0F);
/*    */     
/* 71 */     explosion.setHeightDifference(45);
/* 72 */     explosion.setStaticBlockResistance(1.35F);
/* 73 */     explosion.setProtectOwnerFromFalling(true);
/* 74 */     explosion.setExplosionSound(false);
/* 75 */     explosion.setSmokeParticles(null);
/* 76 */     explosion.setDamageSource((DamageSource)this.bypassingSource);
/* 77 */     explosion.setStaticDamage(15.0F);
/* 78 */     explosion.addRemovedBlocksToList();
/* 79 */     explosion.doExplosion();
/*    */     
/* 81 */     for (FallingBlockEntity entity : explosion.removedBlocks) {
/* 82 */       AbilityHelper.setDeltaMovement((Entity)entity, WyHelper.randomWithRange(-1, 1) / 2.0D, 0.5D + 
/* 83 */           WyHelper.randomDouble(), 
/* 84 */           WyHelper.randomWithRange(-1, 1) / 2.0D);
/*    */       
/* 86 */       entity.field_145813_c = false;
/*    */       
/* 88 */       entity.field_145812_b = 1;
/*    */       
/* 90 */       this.field_70170_p.func_217376_c((Entity)entity);
/*    */       
/* 92 */       size++;
/*    */       
/* 94 */       if (size > 50)
/*    */         break; 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gura\GekishinProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */