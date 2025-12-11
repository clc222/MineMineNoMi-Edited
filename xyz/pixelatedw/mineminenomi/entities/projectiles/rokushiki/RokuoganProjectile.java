/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.rokushiki;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class RokuoganProjectile extends AbilityProjectileEntity {
/*    */   public RokuoganProjectile(EntityType type, World world) {
/* 19 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public RokuoganProjectile(World world, LivingEntity entity, Ability ability) {
/* 24 */     super((EntityType)RokushikiProjectiles.ROKUOGAN.get(), world, entity, ability.getCore());
/*    */     
/* 26 */     setMaxLife(5);
/* 27 */     setDamage(0.0F);
/* 28 */     setCanGetStuckInGround();
/* 29 */     setPassThroughEntities();
/*    */     
/* 31 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 32 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 37 */     ExplosionAbility explosion = createExplosion((Entity)this, this.field_70170_p, func_226277_ct_(), func_226278_cu_(), func_226281_cx_(), 7.0F);
/* 38 */     explosion.setExplosionSound(true);
/* 39 */     explosion.setDamageOwner(false);
/* 40 */     explosion.setDestroyBlocks(false);
/* 41 */     explosion.setDamageEntities(false);
/* 42 */     explosion.setFireAfterExplosion(false);
/* 43 */     explosion.setDamageEntities(false);
/* 44 */     explosion.doExplosion();
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 49 */     if (!this.field_70170_p.field_72995_K)
/*    */     {
/* 51 */       for (int i = 0; i < 20; i++) {
/*    */         
/* 53 */         double offsetX = WyHelper.randomDouble() / 1.25D;
/* 54 */         double offsetY = WyHelper.randomDouble() / 1.25D;
/* 55 */         double offsetZ = WyHelper.randomDouble() / 1.25D;
/*    */         
/* 57 */         ((ServerWorld)this.field_70170_p).func_195598_a((IParticleData)ParticleTypes.field_197598_I, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ, 1, 0.0D, 0.0D, 0.0D, -0.1D);
/* 58 */         ((ServerWorld)this.field_70170_p).func_195598_a((IParticleData)ParticleTypes.field_197626_s, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.1D);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\rokushiki\RokuoganProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */