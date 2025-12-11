/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.hana;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class HanaGenericEntity
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public HanaGenericEntity(EntityType type, World world) {
/* 13 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public HanaGenericEntity(World world, LivingEntity player, Ability ability) {
/* 18 */     super((EntityType)HanaProjectiles.GENERIC_HANA.get(), world, player, ability);
/* 19 */     setEntityCollisionSize(1.25D);
/* 20 */     setDamage(0.0F);
/* 21 */     setFake();
/* 22 */     setMaxLife(10);
/* 23 */     setFist();
/*    */   }
/*    */   
/*    */   public void onProjectileCollision(AbilityProjectileEntity owner, AbilityProjectileEntity target) {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\hana\HanaGenericEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */