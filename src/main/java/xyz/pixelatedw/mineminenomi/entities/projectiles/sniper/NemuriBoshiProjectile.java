/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.NemuriBoshiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class NemuriBoshiProjectile extends AbilityProjectileEntity {
/*    */   public NemuriBoshiProjectile(EntityType type, World world) {
/* 15 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public NemuriBoshiProjectile(World world, LivingEntity player) {
/* 20 */     super((EntityType)SniperProjectiles.NEMURI_BOSHI.get(), world, player, NemuriBoshiAbility.INSTANCE);
/*    */     
/* 22 */     setDamage(2.0F);
/*    */     
/* 24 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 28 */     hitEntity.func_195064_c(new EffectInstance((Effect)ModEffects.UNCONSCIOUS.get(), 40, 1));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\sniper\NemuriBoshiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */