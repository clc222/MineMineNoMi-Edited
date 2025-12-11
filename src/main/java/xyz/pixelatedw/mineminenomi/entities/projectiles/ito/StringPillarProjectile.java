/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.ito;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class StringPillarProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public StringPillarProjectile(EntityType type, World world) {
/* 13 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public StringPillarProjectile(World world, LivingEntity player, Ability ability) {
/* 18 */     super((EntityType)ItoProjectiles.STRING_PILLAR.get(), world, player, ability);
/*    */     
/* 20 */     setDamage(15.0F);
/* 21 */     setPassThroughEntities();
/* 22 */     setArmorPiercing(0.25F);
/* 23 */     setDamageSource(getDamageSource().setSlash());
/* 24 */     setEntityCollisionSize(0.75D, 5.0D, 0.75D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 29 */     super.func_70071_h_();
/* 30 */     this.field_70125_A = 90.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onProjectileCollision(AbilityProjectileEntity owner, AbilityProjectileEntity target) {
/* 36 */     if (target instanceof StringPillarProjectile) {
/*    */       return;
/*    */     }
/*    */     
/* 40 */     super.onProjectileCollision(owner, target);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\ito\StringPillarProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */