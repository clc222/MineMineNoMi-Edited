/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.kage;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class BlackBoxProjectile extends AbilityProjectileEntity {
/*    */   public BlackBoxProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlackBoxProjectile(World world, LivingEntity player, Ability ability) {
/* 22 */     super((EntityType)KageProjectiles.BLACK_BOX.get(), world, player, ability);
/*    */     
/* 24 */     setDamage(5.0F);
/* 25 */     setMaxLife(15);
/*    */     
/* 27 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity target) {
/* 32 */     EffectInstance instance = new EffectInstance((Effect)ModEffects.BLACK_BOX.get(), 80, 0);
/* 33 */     target.func_195064_c(instance);
/* 34 */     if (func_234616_v_() instanceof ServerPlayerEntity)
/* 35 */       ((ServerPlayerEntity)func_234616_v_()).field_71135_a.func_147359_a((IPacket)new SPlayEntityEffectPacket(target.func_145782_y(), instance)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\kage\BlackBoxProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */