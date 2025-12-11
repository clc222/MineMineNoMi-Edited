/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.yami;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class LiberationProjectile
/*    */   extends AbilityProjectileEntity {
/*    */   public LiberationProjectile(EntityType<Entity> type, World world) {
/* 18 */     super(type, world);
/*    */   }
/*    */   private Block block;
/*    */   public LiberationProjectile(World world, LivingEntity player, Block block, Ability ability) {
/* 22 */     super((EntityType)YamiProjectiles.LIBERATION.get(), world, player, ability.getCore());
/*    */     
/* 24 */     this.block = block;
/*    */     
/* 26 */     setDamage(5.0F);
/*    */     
/* 28 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 32 */     AbilityHelper.placeBlockIfAllowed(getThrower(), hit.func_177982_a(0, 1, 0), this.block.func_176223_P(), AirBlockProtectionRule.INSTANCE);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\yami\LiberationProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */