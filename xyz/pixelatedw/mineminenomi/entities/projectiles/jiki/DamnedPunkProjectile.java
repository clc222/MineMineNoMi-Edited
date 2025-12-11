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
/*    */ import xyz.pixelatedw.mineminenomi.abilities.jiki.DamnedPunkAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.jiki.JikiHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class DamnedPunkProjectile
/*    */   extends AbilityProjectileEntity {
/*    */   private List<ItemStack> magneticItems;
/*    */   
/*    */   public DamnedPunkProjectile(EntityType type, World world) {
/* 20 */     super(type, world);
/*    */   }
/*    */   
/*    */   public DamnedPunkProjectile(World world, LivingEntity player, List<ItemStack> items) {
/* 24 */     super((EntityType)JikiProjectiles.DAMNED_PUNK.get(), world, player, DamnedPunkAbility.INSTANCE);
/*    */     
/* 26 */     setDamage(60.0F);
/* 27 */     setArmorPiercing(1.0F);
/* 28 */     setPassThroughEntities();
/*    */     
/* 30 */     this.magneticItems = items;
/*    */     
/* 32 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 36 */     if (getThrower() != null && getThrower() instanceof PlayerEntity)
/* 37 */       JikiHelper.dropComponentItems((PlayerEntity)getThrower(), hit, this.magneticItems); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\jiki\DamnedPunkProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */