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
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.IFlexibleSizeProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class DaiEnkaiEnteiProjectile extends AbilityProjectileEntity implements IFlexibleSizeProjectile {
/*  28 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { LiquidBlockProtectionRule.INSTANCE, SnowLayerBlockProtectionRule.INSTANCE })).build();
/*     */   
/*     */   public DaiEnkaiEnteiProjectile(EntityType<Entity> type, World world) {
/*  31 */     super(type, world);
/*     */   }
/*     */   
/*     */   public DaiEnkaiEnteiProjectile(World world, LivingEntity player, Ability ability) {
/*  35 */     super((EntityType)MeraProjectiles.DAI_ENKAI_ENTEI.get(), world, player, ability.getCore());
/*     */     
/*  37 */     setBlocksAffectedLimit(42875);
/*  38 */     setArmorPiercing(0.75F);
/*  39 */     setUnavoidable();
/*     */     
/*  41 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*  42 */     this.onTickEvent = this::onTickEvent;
/*     */   }
/*     */   
/*     */   public void onBlockImpactEvent(BlockPos hit) {
/*  46 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 0.6F * getSize());
/*     */     
/*  48 */     explosion.setStaticDamage(2.0F * getSize());
/*  49 */     explosion.setStaticBlockResistance(0.25F);
/*  50 */     explosion.setFireAfterExplosion(true);
/*  51 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect((int)(0.6F * getSize())));
/*  52 */     explosion.doExplosion();
/*     */   }
/*     */   
/*     */   private void onTickEvent() {
/*  56 */     setDamage(2.0F * getSize());
/*  57 */     if (func_208600_a((ITag)FluidTags.field_206959_a) && CommonConfig.INSTANCE.getDestroyWater()) {
/*  58 */       List<BlockPos> coords = AbilityHelper.createFilledSphere(func_130014_f_(), (int)func_226277_ct_(), (int)func_226278_cu_(), (int)func_226281_cx_(), 9, Blocks.field_150350_a, GRIEF_RULE);
/*     */       
/*  60 */       for (BlockPos blockPos : coords) {
/*  61 */         WyHelper.spawnParticles((IParticleData)ParticleTypes.field_197612_e, (ServerWorld)func_130014_f_(), blockPos.func_177958_n() + WyHelper.randomDouble() / 2.0D, blockPos.func_177956_o() + 0.8D, blockPos.func_177952_p() + WyHelper.randomDouble() / 2.0D);
/*     */         
/*  63 */         func_130014_f_().func_195594_a((IParticleData)ParticleTypes.field_197601_L, blockPos.func_177958_n(), blockPos.func_177956_o() + 1.1D, blockPos.func_177952_p(), 0.0D, 0.0D, 0.0D);
/*     */       } 
/*     */     } 
/*     */     
/*  67 */     if (!this.field_70170_p.field_72995_K) {
/*  68 */       int i; for (i = 0; i < 20; i++) {
/*  69 */         double offsetX = WyHelper.randomDouble();
/*  70 */         double offsetY = WyHelper.randomDouble();
/*  71 */         double offsetZ = WyHelper.randomDouble();
/*     */         
/*  73 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MERA.get());
/*     */         
/*  75 */         data.setLife(6);
/*  76 */         data.setSize(1.3F);
/*     */         
/*  78 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*     */       } 
/*     */       
/*  81 */       for (i = 0; i < 2; i++) {
/*  82 */         double offsetX = WyHelper.randomDouble();
/*  83 */         double offsetY = WyHelper.randomDouble();
/*  84 */         double offsetZ = WyHelper.randomDouble();
/*     */         
/*  86 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MOKU.get());
/*     */         
/*  88 */         data.setLife(4);
/*  89 */         data.setSize(1.2F);
/*     */         
/*  91 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSize() {
/*  98 */     return (float)func_174813_aQ().func_72320_b() * 4.0F;
/*     */   }
/*     */   
/*     */   public void increaseSize() {
/* 102 */     float size = (float)func_174813_aQ().func_72320_b() + 0.0625F;
/*     */     
/* 104 */     setEntityCollisionSize(size);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\mera\DaiEnkaiEnteiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */