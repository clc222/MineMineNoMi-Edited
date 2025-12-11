/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.jiki;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.jiki.JikiHelper;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.jiki.PunkGibsonAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class PunkGibsonProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   private List<ItemStack> magneticItems;
/*    */   
/*    */   public PunkGibsonProjectile(EntityType type, World world) {
/* 20 */     super(type, world);
/*    */   }
/*    */   
/*    */   public PunkGibsonProjectile(World world, LivingEntity player, List<ItemStack> items) {
/* 24 */     super((EntityType)JikiProjectiles.PUNK_GIBSON.get(), world, player, PunkGibsonAbility.INSTANCE);
/*    */     
/* 26 */     setDamage(40.0F);
/* 27 */     setFist();
/* 28 */     setAffectedByImbuing();
/*    */     
/* 30 */     this.magneticItems = items;
/*    */     
/* 32 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 36 */     if (getThrower() != null && getThrower() instanceof net.minecraft.entity.player.PlayerEntity)
/* 37 */       JikiHelper.dropComponentItems(getThrower(), func_213303_ch(), this.magneticItems); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\jiki\PunkGibsonProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */