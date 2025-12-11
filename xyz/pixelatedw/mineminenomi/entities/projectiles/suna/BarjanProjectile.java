/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.suna;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.suna.BarjanAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BarjanProjectile extends AbilityProjectileEntity {
/*    */   public BarjanProjectile(EntityType type, World world) {
/* 20 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BarjanProjectile(World world, LivingEntity player) {
/* 25 */     super((EntityType)SunaProjectiles.BARJAN.get(), world, player, BarjanAbility.INSTANCE);
/*    */     
/* 27 */     setDamage(30.0F);
/* 28 */     setMaxLife(15);
/* 29 */     setCanGetStuckInGround();
/* 30 */     setPassThroughEntities();
/*    */     
/* 32 */     setEntityCollisionSize(1.0D, 0.20000000298023224D, 1.0D);
/*    */     
/* 34 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance(Effects.field_82731_v, 100, 3, false, false), new EffectInstance((Effect)ModEffects.DEHYDRATION.get(), 200, 2, false, true), new EffectInstance(Effects.field_76437_t, 150, 0, false, false), new EffectInstance(Effects.field_76421_d, 150, 0, false, false), new EffectInstance(Effects.field_76419_f, 150, 0, false, false) });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 42 */     this.onEntityImpactEvent = this::onEntityImpact;
/* 43 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void onEntityImpact(LivingEntity entity) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 55 */     for (int i = 0; i < 5; i++) {
/*    */       
/* 57 */       double offsetX = WyHelper.randomDouble();
/* 58 */       double offsetY = WyHelper.randomDouble() / 4.0D;
/* 59 */       double offsetZ = WyHelper.randomDouble();
/*    */       
/* 61 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.SUNA2.get());
/* 62 */       data.setLife(4);
/* 63 */       data.setSize(1.4F);
/* 64 */       WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\suna\BarjanProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */