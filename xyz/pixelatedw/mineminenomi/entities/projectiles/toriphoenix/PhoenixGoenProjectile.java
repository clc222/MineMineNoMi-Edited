/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.toriphoenix;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class PhoenixGoenProjectile
/*    */   extends AbilityProjectileEntity {
/*    */   public PhoenixGoenProjectile(EntityType type, World world) {
/* 19 */     super(type, world);
/*    */   }
/*    */   private Vector3d lookVec;
/*    */   
/*    */   public PhoenixGoenProjectile(World world, LivingEntity player, Ability ability, Vector3d lookVec) {
/* 24 */     super((EntityType)ToriPhoenixProjectiles.PHOENIX_GOEN.get(), world, player, ability);
/*    */     
/* 26 */     setDamage(10.0F);
/* 27 */     setCanGetStuckInGround();
/* 28 */     setMaxLife(30);
/* 29 */     setHurtTime(10);
/*    */     
/* 31 */     this.lookVec = lookVec;
/* 32 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 33 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity target) {
/* 38 */     Vector3d speed = this.lookVec.func_216372_d(1.5D, 0.0D, 1.5D);
/* 39 */     AbilityHelper.setDeltaMovement((Entity)target, speed.field_72450_a, 0.15D, speed.field_72449_c);
/* 40 */     target.field_70143_R = 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 45 */     if (!this.field_70170_p.field_72995_K)
/* 46 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.PHOENIX_GOEN.get(), (Entity)this, func_226277_ct_(), func_226278_cu_(), func_226281_cx_()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\toriphoenix\PhoenixGoenProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */