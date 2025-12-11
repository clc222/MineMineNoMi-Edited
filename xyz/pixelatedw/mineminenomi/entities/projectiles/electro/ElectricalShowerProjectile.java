/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.electro;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ElectricalShowerProjectile extends AbilityProjectileEntity {
/*    */   public ElectricalShowerProjectile(EntityType type, World world) {
/* 13 */     super(type, world);
/*    */   }
/*    */   
/*    */   public ElectricalShowerProjectile(World world, LivingEntity entity) {
/* 17 */     super((EntityType)ElectroProjectiles.ELECTRICAL_SHOWER.get(), world, entity, ElectricalShowerAbility.INSTANCE);
/*    */     
/* 19 */     setMaxLife(40);
/* 20 */     setPassThroughEntities();
/* 21 */     setEntityCollisionSize(1.5D, 1.5D, 1.5D);
/*    */     
/* 23 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 27 */     if (this.field_70173_aa < 0) {
/*    */       return;
/*    */     }
/*    */     
/* 31 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.ELECTRICAL_LUNA.get(), (Entity)this, func_226277_ct_(), func_226278_cu_(), func_226281_cx_());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\electro\ElectricalShowerProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */