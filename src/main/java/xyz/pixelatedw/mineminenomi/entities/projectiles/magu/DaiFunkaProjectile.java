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
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class DaiFunkaProjectile extends AbilityProjectileEntity {
/* 21 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { DefaultProtectionRules.CORE_FOLIAGE_ORE, LavaImmuneProtectionRule.INSTANCE })).build();
/*    */   
/*    */   private boolean changeLifeTime = true;
/*    */   
/*    */   public DaiFunkaProjectile(EntityType<Entity> type, World world) {
/* 26 */     super(type, world);
/*    */   }
/*    */   
/*    */   public DaiFunkaProjectile(World world, LivingEntity player, IAbility ability) {
/* 30 */     super((EntityType)MaguProjectiles.DAI_FUNKA.get(), world, player, ability.getCore());
/*    */     
/* 32 */     setDamage(80.0F);
/* 33 */     setMaxLife(35);
/*    */     
/* 35 */     setPassThroughEntities();
/* 36 */     setCanGetStuckInGround();
/* 37 */     setArmorPiercing(1.0F);
/* 38 */     addBlockToIgnore(new Block[] { Blocks.field_150353_l });
/*    */     
/* 40 */     setEntityCollisionSize(7.0D, 7.0D, 7.0D);
/* 41 */     setBlockCollisionSize(6.0D, 2.0D, 6.0D);
/*    */     
/* 43 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 44 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 45 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 49 */     AbilityHelper.setSecondsOnFireBy((Entity)hitEntity, 15, getThrower());
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 53 */     AbilityHelper.createFilledSphere(this.field_70170_p, (int)func_226277_ct_(), (int)func_226278_cu_(), (int)func_226281_cx_(), 3, Blocks.field_150353_l, GRIEF_RULE);
/*    */     
/* 55 */     if (this.changeLifeTime) {
/* 56 */       setLife(3);
/*    */       
/* 58 */       this.changeLifeTime = false;
/*    */     } 
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 63 */     if (!this.field_70170_p.field_72995_K) {
/* 64 */       int i; for (i = 0; i < 2; i++) {
/* 65 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 66 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 67 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 69 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MERA.get());
/*    */         
/* 71 */         data.setLife(3);
/* 72 */         data.setSize(2.3F);
/*    */         
/* 74 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*    */       } 
/*    */       
/* 77 */       for (i = 0; i < 2; i++) {
/* 78 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 79 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 80 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 82 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MAGU.get());
/*    */         
/* 84 */         data.setLife(3);
/* 85 */         data.setSize(2.3F);
/*    */         
/* 87 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\magu\DaiFunkaProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */