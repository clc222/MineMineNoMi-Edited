/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.hie;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class IceBlockPartisanProjectile extends AbilityProjectileEntity {
/*    */   public IceBlockPartisanProjectile(EntityType type, World world) {
/* 22 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public IceBlockPartisanProjectile(World world, LivingEntity player, Ability ability) {
/* 27 */     super((EntityType)HieProjectiles.ICE_BLOCK_PARTISAN.get(), world, player, ability);
/*    */     
/* 29 */     setDamage(9.0F);
/* 30 */     setMaxLife(40);
/* 31 */     setPhysical();
/*    */     
/* 33 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 34 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 35 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity entity) {
/* 39 */     AbilityHelper.addFrostbiteStacks(entity, getThrower(), 2);
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 44 */     AbilityHelper.placeBlockIfAllowed(getThrower(), hit, Blocks.field_205164_gk.func_176223_P(), DefaultProtectionRules.CORE_FOLIAGE_ORE_LIQUID);
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
/* 57 */         SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.HIE.get());
/* 58 */         part.setLife(32);
/* 59 */         part.setAnimationSpeed(5);
/* 60 */         part.setRotation(Vector3f.field_229183_f_);
/* 61 */         part.setRotationSpeed((i % 2 == 0) ? 0.07F : -0.07F);
/* 62 */         part.setSize(1.5F);
/* 63 */         WyHelper.spawnParticles((IParticleData)part, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\hie\IceBlockPartisanProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */