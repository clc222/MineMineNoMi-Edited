/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.mera;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleType;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.mera.HikenAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class HikenProjectile extends AbilityProjectileEntity {
/*  28 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { LiquidBlockProtectionRule.INSTANCE, SnowLayerBlockProtectionRule.INSTANCE })).build();
/*     */   
/*     */   public HikenProjectile(EntityType<Entity> type, World world) {
/*  31 */     super(type, world);
/*     */   }
/*     */   
/*     */   public HikenProjectile(World world, LivingEntity thrower) {
/*  35 */     super((EntityType)MeraProjectiles.HIKEN.get(), world, thrower, HikenAbility.INSTANCE);
/*     */     
/*  37 */     setDamage(50.0F);
/*  38 */     setCanGetStuckInGround();
/*  39 */     setPassThroughEntities();
/*  40 */     setMaxLife(32);
/*  41 */     setArmorPiercing(0.75F);
/*     */     
/*  43 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*  44 */     this.onTickEvent = this::onTickEvent;
/*     */   }
/*     */   
/*     */   private void onBlockImpactEvent(BlockPos hit) {
/*  48 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 4.0F);
/*     */     
/*  50 */     explosion.setStaticDamage(25.0F);
/*  51 */     explosion.setHeightDifference(30);
/*  52 */     explosion.disableExplosionKnockback();
/*  53 */     explosion.setFireAfterExplosion(true);
/*  54 */     explosion.setExplosionSound(false);
/*  55 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/*  56 */     explosion.doExplosion();
/*     */   }
/*     */   
/*     */   private void onTickEvent() {
/*  60 */     if (this.field_70173_aa > 2) {
/*  61 */       BlockPos pos = func_233580_cy_();
/*     */       
/*  63 */       this.onBlockImpactEvent.onImpact(pos);
/*     */     } 
/*     */     
/*  66 */     if (func_208600_a((ITag)FluidTags.field_206959_a) && CommonConfig.INSTANCE.getDestroyWater()) {
/*  67 */       List<BlockPos> coords = AbilityHelper.createFilledSphere(func_130014_f_(), (int)func_226277_ct_(), (int)func_226278_cu_(), (int)func_226281_cx_(), 2, Blocks.field_150350_a, GRIEF_RULE);
/*     */       
/*  69 */       for (BlockPos blockPos : coords) {
/*  70 */         WyHelper.spawnParticles((IParticleData)ParticleTypes.field_197612_e, (ServerWorld)func_130014_f_(), blockPos.func_177958_n() + WyHelper.randomDouble() / 2.0D, blockPos.func_177956_o() + 0.8D, blockPos.func_177952_p() + WyHelper.randomDouble() / 2.0D);
/*     */         
/*  72 */         func_130014_f_().func_195594_a((IParticleData)ParticleTypes.field_197601_L, blockPos.func_177958_n(), blockPos.func_177956_o() + 1.1D, blockPos.func_177952_p(), 0.0D, 0.0D, 0.0D);
/*     */       } 
/*     */     } 
/*     */     
/*  76 */     if (!this.field_70170_p.field_72995_K) {
/*  77 */       int i; for (i = 0; i < 20; i++) {
/*  78 */         double offsetX = WyHelper.randomDouble() * 2.0D;
/*  79 */         double offsetY = WyHelper.randomDouble() * 2.0D;
/*  80 */         double offsetZ = WyHelper.randomDouble() * 2.0D;
/*     */         
/*  82 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MERA.get());
/*     */         
/*  84 */         data.setLife(30);
/*  85 */         data.setSize(3.0F);
/*     */         
/*  87 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*     */       } 
/*     */       
/*  90 */       for (i = 0; i < 10; i++) {
/*  91 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/*  92 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/*  93 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*     */         
/*  95 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MOKU.get());
/*     */         
/*  97 */         data.setLife(7);
/*  98 */         data.setSize(1.2F);
/*     */         
/* 100 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\mera\HikenProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */