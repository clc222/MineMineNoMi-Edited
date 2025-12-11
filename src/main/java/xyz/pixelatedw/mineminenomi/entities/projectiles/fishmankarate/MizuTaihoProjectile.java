/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.fishmankarate;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.WaterExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class MizuTaihoProjectile extends AbilityProjectileEntity {
/*    */   public MizuTaihoProjectile(EntityType type, World world) {
/* 19 */     super(type, world);
/*    */   }
/*    */   
/*    */   public MizuTaihoProjectile(World world, LivingEntity player) {
/* 23 */     super((EntityType)FishmanKarateProjectiles.MIZU_TAIHO.get(), world, player, (AbilityCore)null);
/*    */     
/* 25 */     setDamage(50.0F);
/* 26 */     setPassThroughEntities();
/* 27 */     setPassThroughBlocks();
/* 28 */     setMaxLife(30);
/* 29 */     setEntityCollisionSize(2.0D);
/*    */     
/* 31 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 32 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 36 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 3.0F);
/* 37 */     explosion.setStaticDamage(15.0F);
/* 38 */     explosion.setExplosionSound(false);
/* 39 */     explosion.setDamageOwner(false);
/* 40 */     explosion.setDestroyBlocks(true);
/* 41 */     explosion.setFireAfterExplosion(false);
/* 42 */     explosion.setSmokeParticles((ParticleEffect)new WaterExplosionParticleEffect());
/* 43 */     explosion.setDamageEntities(false);
/* 44 */     explosion.doExplosion();
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 48 */     if (!this.field_70170_p.field_72995_K) {
/* 49 */       if (this.field_70173_aa > 2) {
/* 50 */         BlockPos pos = func_233580_cy_();
/* 51 */         this.onBlockImpactEvent.onImpact(pos);
/*    */       } 
/* 53 */       for (int i = 0; i < 15; i++) {
/* 54 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 55 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 56 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 58 */         ((ServerWorld)this.field_70170_p).func_195598_a((IParticleData)ParticleTypes.field_197630_w, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ, 1, 0.0D, 0.0D, 0.0D, -0.1D);
/*    */         
/* 60 */         ((ServerWorld)this.field_70170_p).func_195598_a((IParticleData)ParticleTypes.field_197612_e, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.1D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\fishmankarate\MizuTaihoProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */