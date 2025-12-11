/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.zou;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class TrunkShotProjectile
/*    */   extends AbilityProjectileEntity {
/*    */   public TrunkShotProjectile(EntityType type, World world) {
/* 15 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public TrunkShotProjectile(World world, LivingEntity player, Ability ability) {
/* 20 */     super((EntityType)ZouProjectiles.TRUNK_SHOT.get(), world, player, ability);
/*    */     
/* 22 */     setDamage(24.0F);
/* 23 */     setMaxLife(10);
/*    */     
/* 25 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity target) {
/* 30 */     target.func_195064_c(new EffectInstance(Effects.field_76437_t, 100, 1));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\zou\TrunkShotProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */