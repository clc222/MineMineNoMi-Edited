/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class BambooPillarEntity
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public BambooPillarEntity(EntityType type, World world) {
/* 12 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BambooPillarEntity(World world, LivingEntity player) {
/* 17 */     super((EntityType)ExtraProjectiles.BAMBOO_PILLAR.get(), world, player);
/*    */     
/* 19 */     setPhysical();
/* 20 */     setMaxLife(8);
/* 21 */     setPassThroughEntities();
/* 22 */     setPassThroughBlocks();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\extra\BambooPillarEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */