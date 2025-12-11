/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.doru;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class ChampFightProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public ChampFightProjectile(EntityType type, World world) {
/* 12 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public ChampFightProjectile(World world, LivingEntity player) {
/* 17 */     super((EntityType)DoruProjectiles.CHAMP_FIGHT.get(), world, player);
/*    */     
/* 19 */     setDamage(10.0F);
/* 20 */     setMaxLife(20);
/* 21 */     setFist();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\doru\ChampFightProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */