/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.noro;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.mixins.EffectInstanceMixin;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class NoroNoroBeamProjectile extends AbilityProjectileEntity {
/*    */   public NoroNoroBeamProjectile(EntityType<Entity> type, World world) {
/* 16 */     super(type, world);
/*    */   }
/*    */   
/*    */   public NoroNoroBeamProjectile(World world, LivingEntity player, Ability ability) {
/* 20 */     super((EntityType)NoroProjectiles.NORO_NORO_BEAM.get(), world, player, ability.getCore());
/*    */     
/* 22 */     setMaxLife(10);
/* 23 */     setEntityCollisionSize(1.25D);
/* 24 */     setPassThroughEntities();
/* 25 */     setUnavoidable();
/*    */     
/* 27 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 31 */     EffectInstance instance = hitEntity.func_70660_b((Effect)ModEffects.NORO_SLOWNESS.get());
/*    */     
/* 33 */     if (instance == null) {
/* 34 */       hitEntity.func_195064_c(new EffectInstance((Effect)ModEffects.NORO_SLOWNESS.get(), 240, 0));
/*    */     } else {
/*    */       
/* 37 */       ((EffectInstanceMixin)instance).setDuration(instance.func_76459_b() + 240);
/*    */       
/* 39 */       WyHelper.sendApplyEffectToAllNearby(hitEntity, hitEntity.func_213303_ch(), 100, instance);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\noro\NoroNoroBeamProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */