/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.yami;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class DarkMatterProjectile extends AbilityProjectileEntity {
/* 21 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { AirBlockProtectionRule.INSTANCE, CoreBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE, OreBlockProtectionRule.INSTANCE })).build();
/*    */ 
/*    */   
/*    */   public DarkMatterProjectile(EntityType type, World world) {
/* 25 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public DarkMatterProjectile(World world, LivingEntity player, Ability ability) {
/* 30 */     super((EntityType)YamiProjectiles.DARK_MATTER.get(), world, player, ability.getCore());
/*    */     
/* 32 */     setDamage(15.0F);
/* 33 */     setMaxLife(20);
/*    */     
/* 35 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 40 */     AbilityHelper.createFilledSphere(this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 3, (Block)ModBlocks.DARKNESS.get(), GRIEF_RULE);
/* 41 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.DARK_MATTER.get(), (Entity)this, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_70088_a() {
/* 47 */     super.func_70088_a();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\yami\DarkMatterProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */