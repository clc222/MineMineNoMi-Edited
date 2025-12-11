/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.mero;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class MeroMeroMellowProjectile extends AbilityProjectileEntity {
/*    */   public MeroMeroMellowProjectile(EntityType type, World world) {
/* 15 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public MeroMeroMellowProjectile(World world, LivingEntity player, Ability ability) {
/* 20 */     super((EntityType)MeroProjectiles.MERO_MERO_MELLOW.get(), world, player, ability);
/*    */     
/* 22 */     setDamage(10.0F);
/* 23 */     setPassThroughEntities();
/* 24 */     setMaxLife(30);
/* 25 */     setUnavoidable();
/*    */     
/* 27 */     setBlockCollisionSize(3.0D, 2.0D, 3.0D);
/*    */     
/* 29 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance((Effect)ModEffects.LOVESTRUCK.get(), 200, 1) });
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\mero\MeroMeroMellowProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */