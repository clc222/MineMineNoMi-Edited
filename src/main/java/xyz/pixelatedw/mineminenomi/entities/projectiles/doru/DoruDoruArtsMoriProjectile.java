/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.doru;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.doru.DoruDoruArtsMoriAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class DoruDoruArtsMoriProjectile
/*    */   extends AbilityProjectileEntity {
/*    */   public DoruDoruArtsMoriProjectile(EntityType type, World world) {
/* 13 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public DoruDoruArtsMoriProjectile(World world, LivingEntity player, DoruDoruArtsMoriAbility ability) {
/* 18 */     super((EntityType)DoruProjectiles.DORU_DORU_ARTS_MORI.get(), world, player, (Ability)ability);
/*    */     
/* 20 */     setDamage(20.0F);
/* 21 */     setMaxLife(40);
/* 22 */     setPhysical();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\doru\DoruDoruArtsMoriProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */