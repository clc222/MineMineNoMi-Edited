/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.saraaxolotl;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class PoisonSpitProjectile extends AbilityProjectileEntity {
/*    */   public PoisonSpitProjectile(EntityType type, World world) {
/* 19 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public PoisonSpitProjectile(World world, LivingEntity player, Ability ability) {
/* 24 */     super((EntityType)SaraAxolotlProjectiles.POISON_SPIT.get(), world, player, ability);
/*    */     
/* 26 */     setDamage(8.0F);
/* 27 */     setPhysical();
/* 28 */     setMaxLife(10);
/*    */     
/* 30 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 31 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 36 */     hitEntity.func_195064_c(new EffectInstance(Effects.field_76436_u, 200, 0));
/* 37 */     this.onBlockImpactEvent.onImpact(hitEntity.func_233580_cy_());
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 42 */     if (!this.field_70170_p.field_72995_K)
/*    */     {
/* 44 */       for (int i = 0; i < 7; i++) {
/*    */         
/* 46 */         double offsetX = WyHelper.randomDouble() / 6.0D;
/* 47 */         double offsetY = WyHelper.randomDouble() / 6.0D;
/* 48 */         double offsetZ = WyHelper.randomDouble() / 6.0D;
/*    */         
/* 50 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.DOKU_PINK.get());
/* 51 */         data.setLife(5);
/* 52 */         data.setSize(0.7F);
/* 53 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\saraaxolotl\PoisonSpitProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */