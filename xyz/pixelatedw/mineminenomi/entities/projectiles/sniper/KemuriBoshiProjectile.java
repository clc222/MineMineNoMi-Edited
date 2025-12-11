/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.KemuriBoshiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class KemuriBoshiProjectile extends AbilityProjectileEntity {
/*    */   public KemuriBoshiProjectile(EntityType type, World world) {
/* 21 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public KemuriBoshiProjectile(World world, LivingEntity player) {
/* 26 */     super((EntityType)SniperProjectiles.KEMURI_BOSHI.get(), world, player, KemuriBoshiAbility.INSTANCE);
/*    */     
/* 28 */     setDamage(3.0F);
/*    */     
/* 30 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 35 */     KemuriBoshiCloudEntity smokeCloud = new KemuriBoshiCloudEntity(this.field_70170_p);
/* 36 */     smokeCloud.setLife(100);
/* 37 */     smokeCloud.func_70012_b(func_226277_ct_(), func_226278_cu_() + 1.0D, func_226281_cx_(), 0.0F, 0.0F);
/* 38 */     AbilityHelper.setDeltaMovement((Entity)smokeCloud, 0.0D, 0.0D, 0.0D);
/* 39 */     smokeCloud.setThrower(getThrower());
/* 40 */     this.field_70170_p.func_217376_c((Entity)smokeCloud);
/*    */   }
/*    */   
/*    */   public static class KemuriBoshiCloudEntity
/*    */     extends EntityCloud
/*    */   {
/*    */     public KemuriBoshiCloudEntity(World world) {
/* 47 */       super(world);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public void func_70071_h_() {
/* 53 */       super.func_70071_h_();
/* 54 */       if (!this.field_70170_p.field_72995_K) {
/*    */         
/* 56 */         for (LivingEntity target : WyHelper.getNearbyLiving(func_213303_ch(), (IWorld)this.field_70170_p, 6.0D, ModEntityPredicates.getEnemyFactions(getThrower()))) {
/*    */           
/* 58 */           if (getThrower() != target && !target.func_70644_a(Effects.field_76436_u)) {
/* 59 */             target.func_195064_c(new EffectInstance(Effects.field_76436_u, 80, 1));
/*    */           }
/*    */         } 
/* 62 */         if (this.field_70173_aa % 2 == 0)
/*    */         {
/* 64 */           WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.KEMURI_BOSHI.get(), (Entity)this, func_226277_ct_(), func_226278_cu_(), func_226281_cx_());
/*    */         }
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\sniper\KemuriBoshiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */