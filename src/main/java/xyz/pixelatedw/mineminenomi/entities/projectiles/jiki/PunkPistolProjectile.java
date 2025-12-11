/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.jiki;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.jiki.JikiHelper;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.jiki.PunkPistolsAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class PunkPistolProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   private List<ItemStack> magneticItems;
/*    */   
/*    */   public PunkPistolProjectile(EntityType type, World world) {
/* 21 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public PunkPistolProjectile(World world, LivingEntity player, List<ItemStack> items) {
/* 26 */     super((EntityType)JikiProjectiles.PUNK_PISTOL.get(), world, player, PunkPistolsAbility.INSTANCE);
/*    */     
/* 28 */     setDamage(10.0F);
/*    */     
/* 30 */     this.magneticItems = items;
/*    */     
/* 32 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 37 */     if (getThrower() != null && getThrower() instanceof PlayerEntity)
/* 38 */       JikiHelper.dropComponentItems((PlayerEntity)getThrower(), hit, this.magneticItems); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\jiki\PunkPistolProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */