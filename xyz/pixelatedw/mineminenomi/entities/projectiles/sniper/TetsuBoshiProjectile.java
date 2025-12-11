/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.TetsuBoshiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.SpikeEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class TetsuBoshiProjectile extends AbilityProjectileEntity {
/*    */   public TetsuBoshiProjectile(EntityType type, World world) {
/* 16 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public TetsuBoshiProjectile(World world, LivingEntity player) {
/* 21 */     super((EntityType)SniperProjectiles.TETSU_BOSHI.get(), world, player, TetsuBoshiAbility.INSTANCE);
/*    */     
/* 23 */     setDamage(4.0F);
/*    */     
/* 25 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 30 */     for (int i = 0; i < 10; i++) {
/*    */       
/* 32 */       double offsetX = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/* 33 */       double offsetZ = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/*    */       
/* 35 */       SpikeEntity spike = new SpikeEntity(this.field_70170_p);
/* 36 */       spike.func_70012_b(hit.func_177958_n() + offsetX, (hit.func_177956_o() + 1), hit.func_177952_p() + offsetZ, 180.0F, 0.0F);
/* 37 */       this.field_70170_p.func_217376_c((Entity)spike);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\sniper\TetsuBoshiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */