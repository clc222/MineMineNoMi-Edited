/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class FreshFireProjectile extends AbilityProjectileEntity {
/*    */   public FreshFireProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */   
/*    */   public FreshFireProjectile(World world, LivingEntity player, Ability ability) {
/* 21 */     super((EntityType)CyborgProjectiles.FRESH_FIRE.get(), world, player, ability);
/*    */     
/* 23 */     setDamage(1.0F);
/* 24 */     setPassThroughEntities();
/* 25 */     setMaxLife(15);
/* 26 */     setHurtTime(5);
/*    */     
/* 28 */     setEntityCollisionSize(3.0D, 1.0D, 3.0D);
/*    */     
/* 30 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 31 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 32 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 36 */     AbilityHelper.setSecondsOnFireBy((Entity)hitEntity, 4, getThrower());
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 40 */     AbilityHelper.placeBlockIfAllowed(getThrower(), hit.func_177982_a(0, 1, 0), Blocks.field_150480_ab.func_176223_P(), AirBlockProtectionRule.INSTANCE);
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 44 */     if (!this.field_70170_p.field_72995_K)
/* 45 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.FRESH_FIRE.get(), (Entity)this, func_226277_ct_(), func_226278_cu_(), func_226281_cx_()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\cyborg\FreshFireProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */