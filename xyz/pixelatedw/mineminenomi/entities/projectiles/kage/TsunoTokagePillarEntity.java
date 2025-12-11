/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.kage;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.kage.TsunoTokageAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class TsunoTokagePillarEntity
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public TsunoTokagePillarEntity(EntityType type, World world) {
/* 13 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public TsunoTokagePillarEntity(World world, LivingEntity player) {
/* 18 */     super((EntityType)KageProjectiles.TSUNO_TOKAGE.get(), world, player, TsunoTokageAbility.INSTANCE);
/*    */     
/* 20 */     setDamage(30.0F);
/* 21 */     setMaxLife(8);
/* 22 */     setPassThroughEntities();
/* 23 */     setPassThroughBlocks();
/* 24 */     setEntityCollisionSize(1.75D, 5.0D, 1.75D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\kage\TsunoTokagePillarEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */