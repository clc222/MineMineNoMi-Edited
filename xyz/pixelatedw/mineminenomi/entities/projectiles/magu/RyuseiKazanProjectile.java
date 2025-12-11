/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.magu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.LavaImmuneProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class RyuseiKazanProjectile extends AbilityProjectileEntity {
/* 22 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { DefaultProtectionRules.CORE_FOLIAGE_ORE, AirBlockProtectionRule.INSTANCE, LavaImmuneProtectionRule.INSTANCE })).build();
/*    */   
/*    */   public RyuseiKazanProjectile(EntityType<Entity> type, World world) {
/* 25 */     super(type, world);
/*    */   }
/*    */   
/*    */   public RyuseiKazanProjectile(World world, LivingEntity player, IAbility ability) {
/* 29 */     super((EntityType)MaguProjectiles.RYUSEI_KAZAN.get(), world, player, ability.getCore());
/*    */     
/* 31 */     setDamage(20.0F);
/* 32 */     setGravity(0.01F);
/* 33 */     setArmorPiercing(1.0F);
/* 34 */     addBlockToIgnore(new Block[] { Blocks.field_150353_l });
/*    */     
/* 36 */     setBlockCollisionSize(6.0D, 2.0D, 6.0D);
/*    */     
/* 38 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 39 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 40 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 44 */     AbilityHelper.setSecondsOnFireBy((Entity)hitEntity, 8, getThrower());
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 48 */     AbilityHelper.createFilledSphere(func_130014_f_(), (int)func_226277_ct_(), (int)func_226278_cu_() - 1, (int)func_226281_cx_(), 2, Blocks.field_150353_l, GRIEF_RULE);
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 52 */     if (!this.field_70170_p.field_72995_K) {
/* 53 */       int i; for (i = 0; i < 2; i++) {
/* 54 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 55 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 56 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 58 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MERA.get());
/*    */         
/* 60 */         data.setLife(3);
/* 61 */         data.setSize(2.3F);
/*    */         
/* 63 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*    */       } 
/*    */       
/* 66 */       for (i = 0; i < 2; i++) {
/* 67 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 68 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 69 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 71 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MAGU.get());
/*    */         
/* 73 */         data.setLife(3);
/* 74 */         data.setSize(2.3F);
/*    */         
/* 76 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\magu\RyuseiKazanProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */