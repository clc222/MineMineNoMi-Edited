/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.hie;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class IceBallProjectile extends AbilityProjectileEntity {
/* 24 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { AirBlockProtectionRule.INSTANCE, LiquidBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE })).build();
/* 25 */   private static final BlockProtectionRule GRIEF_RULE_BLUE_ICE = (new BlockProtectionRule.Builder(new BlockProtectionRule[0])).addApprovedBlocks(new Block[] { Blocks.field_205164_gk }).build();
/*    */   
/*    */   public IceBallProjectile(EntityType type, World world) {
/* 28 */     super(type, world);
/*    */   }
/*    */   
/*    */   public IceBallProjectile(World world, LivingEntity player, Ability ability) {
/* 32 */     super((EntityType)HieProjectiles.ICE_BALL.get(), world, player, ability);
/*    */     
/* 34 */     setDamage(25.0F);
/* 35 */     setMaxLife(32);
/* 36 */     setPhysical();
/*    */     
/* 38 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 39 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 40 */     this.onTickEvent = this::onTickEvent;
/*    */     
/* 42 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance(Effects.field_76421_d, 100, 0), new EffectInstance(Effects.field_76419_f, 100, 0) });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity entity) {
/* 49 */     AbilityHelper.addFrostbiteStacks(entity, getThrower(), 6);
/*    */     
/* 51 */     if (!AbilityHelper.createSphere(this.field_70170_p, entity.func_233580_cy_(), 6, false, Blocks.field_205164_gk, 2, GRIEF_RULE).isEmpty()) {
/* 52 */       for (int i = -1; i < 2; i++) {
/* 53 */         for (int j = -1; j < 2; j++) {
/* 54 */           AbilityHelper.placeBlockIfAllowed(getThrower(), entity.func_233580_cy_().func_177963_a(i, entity.func_70047_e(), j), Blocks.field_150350_a.func_176223_P(), GRIEF_RULE_BLUE_ICE);
/*    */         }
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 61 */     AbilityHelper.createSphere(this.field_70170_p, hit, 6, false, Blocks.field_205164_gk, 2, GRIEF_RULE);
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 66 */     if (!this.field_70170_p.field_72995_K)
/*    */     {
/* 68 */       for (int i = 0; i < 5; i++) {
/*    */         
/* 70 */         double offsetX = WyHelper.randomDouble() / 1.5D;
/* 71 */         double offsetY = WyHelper.randomDouble() / 1.5D;
/* 72 */         double offsetZ = WyHelper.randomDouble() / 1.5D;
/*    */         
/* 74 */         SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.HIE.get());
/* 75 */         part.setLife(14);
/* 76 */         part.setAnimationSpeed(2);
/* 77 */         part.setRotation(Vector3f.field_229183_f_);
/* 78 */         part.setRotationSpeed((i % 2 == 0) ? 0.07F : -0.07F);
/* 79 */         part.setSize(1.5F);
/* 80 */         WyHelper.spawnParticles((IParticleData)part, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\hie\IceBallProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */