/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.ope;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ @Deprecated
/*    */ public class GammaKnifeProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public GammaKnifeProjectile(EntityType type, World world) {
/* 14 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public GammaKnifeProjectile(World world, LivingEntity player, Ability ability) {
/* 19 */     super((EntityType)OpeProjectiles.GAMMA_KNIFE.get(), world, player, ability);
/*    */     
/* 21 */     setDamage(70.0F);
/*    */     
/* 23 */     setMaxLife(4);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\ope\GammaKnifeProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */