/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.supa;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.supa.SpiralHollowAbility;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class SpiralHollowProjectile extends AbilityProjectileEntity {
/*    */   public SpiralHollowProjectile(EntityType type, World world) {
/* 13 */     super(type, world);
/*    */   }
/*    */   
/*    */   public SpiralHollowProjectile(World world, LivingEntity player) {
/* 17 */     super((EntityType)SupaProjectiles.SPIRAL_HOLLOW.get(), world, player, SpiralHollowAbility.INSTANCE);
/*    */     
/* 19 */     setDamage(30.0F);
/* 20 */     setMaxLife(10);
/*    */     
/* 22 */     setEntityCollisionSize(2.0D, 1.0D, 2.0D);
/*    */     
/* 24 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity target) {
/* 28 */     target.func_195064_c(new EffectInstance((Effect)ModEffects.BLEEDING.get(), 100, 0));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\supa\SpiralHollowProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */