/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.mera;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.tags.FluidTags;
/*    */ import net.minecraft.tags.ITag;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class HidarumaProjectile extends AbilityProjectileEntity {
/*    */   public HidarumaProjectile(EntityType<Entity> type, World world) {
/* 25 */     super(type, world);
/*    */   }
/*    */   private Optional<LivingEntity> target;
/*    */   public HidarumaProjectile(World world, LivingEntity player, Ability ability) {
/* 29 */     super((EntityType)MeraProjectiles.HIDARUMA.get(), world, player, ability.getCore());
/*    */     
/* 31 */     setDamage(3.5F);
/* 32 */     setArmorPiercing(0.75F);
/* 33 */     setGravity(0.0F);
/* 34 */     setMaxLife(120);
/*    */     
/* 36 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 37 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 41 */     AbilityHelper.setSecondsOnFireBy((Entity)hitEntity, 3, getThrower());
/*    */     
/* 43 */     func_70106_y();
/*    */   }
/*    */   
/*    */   public void setTarget(Optional<LivingEntity> target) {
/* 47 */     this.target = target;
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 51 */     if (this.target == null || !this.target.isPresent() || !((LivingEntity)this.target.get()).func_70089_S()) {
/* 52 */       List<LivingEntity> list = WyHelper.getNearbyLiving(func_213303_ch(), (IWorld)this.field_70170_p, 16.0D, ModEntityPredicates.getEnemyFactions(getThrower()));
/*    */       
/* 54 */       list.remove(getThrower());
/*    */       
/* 56 */       list.sort(MobsHelper.ENTITY_THREAT);
/*    */       
/* 58 */       if (list.size() > 0) {
/* 59 */         this.target = list.stream().findAny();
/*    */       }
/*    */     } else {
/* 62 */       Vector3d dist = func_213303_ch().func_178788_d(((LivingEntity)this.target.get()).func_213303_ch()).func_72441_c(0.0D, -1.0D, 0.0D);
/*    */       
/* 64 */       double speedReduction = 12.0D;
/* 65 */       double speed = 0.5D;
/* 66 */       double xSpeed = Math.min(speed, -dist.field_72450_a / speedReduction);
/* 67 */       double ySpeed = Math.min(speed, -dist.field_72448_b / speedReduction);
/* 68 */       double zSpeed = Math.min(speed, -dist.field_72449_c / speedReduction);
/*    */       
/* 70 */       AbilityHelper.setDeltaMovement((Entity)this, xSpeed, ySpeed, zSpeed);
/*    */     } 
/*    */     
/* 73 */     if (func_208600_a((ITag)FluidTags.field_206959_a)) {
/* 74 */       func_70106_y();
/*    */       
/* 76 */       func_130014_f_().func_195594_a((IParticleData)ParticleTypes.field_197601_L, func_226277_ct_(), func_226278_cu_() + 1.1D, func_226281_cx_(), 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */     
/* 79 */     if (!this.field_70170_p.field_72995_K)
/* 80 */       for (int i = 0; i < 1; i++) {
/* 81 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 82 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 83 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 85 */         WyHelper.spawnParticles((IParticleData)ParticleTypes.field_197632_y, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\mera\HidarumaProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */