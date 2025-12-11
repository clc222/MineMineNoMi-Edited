/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.suna;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.suna.DesertGrandeEspadaAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class DesertGrandeEspadaProjectile extends AbilityProjectileEntity {
/*    */   public DesertGrandeEspadaProjectile(EntityType type, World world) {
/* 11 */     super(type, world);
/*    */   }
/*    */   
/*    */   public DesertGrandeEspadaProjectile(World world, LivingEntity player) {
/* 15 */     super((EntityType)SunaProjectiles.DESERT_GRANDE_ESPADA.get(), world, player, DesertGrandeEspadaAbility.INSTANCE);
/*    */     
/* 17 */     setDamage(60.0F);
/* 18 */     setMaxLife(8);
/* 19 */     setPassThroughBlocks();
/* 20 */     setPassThroughEntities();
/* 21 */     setEntityCollisionSize(1.75D, 5.0D, 1.75D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\suna\DesertGrandeEspadaProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */