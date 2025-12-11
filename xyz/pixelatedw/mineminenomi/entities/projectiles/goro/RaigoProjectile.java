/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.goro;
/*     */ 
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleType;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.goro.ElThorAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.IFlexibleSizeProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class RaigoProjectile extends AbilityProjectileEntity implements IFlexibleSizeProjectile {
/*     */   private boolean dealtAOE = false;
/*  38 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { AirBlockProtectionRule.INSTANCE, LiquidBlockProtectionRule.INSTANCE, CoreBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE, OreBlockProtectionRule.INSTANCE
/*     */       
/*  40 */       })).build();
/*     */   private boolean closeToFloor = false;
/*  42 */   private float EXPLOSION_RADIUS = 0.7F;
/*  43 */   private float SHOCKWAVE_RADIUS = 0.85F;
/*     */   
/*     */   public RaigoProjectile(EntityType<Entity> type, World world) {
/*  46 */     super(type, world);
/*     */   }
/*     */   
/*     */   public RaigoProjectile(World world, LivingEntity player, Ability ability) {
/*  50 */     super((EntityType)GoroProjectiles.RAIGO.get(), world, player, ability.getCore());
/*     */     
/*  52 */     setDamage(100.0F);
/*  53 */     setMaxLife(256);
/*  54 */     setEntityCollisionSize(4.0D);
/*  55 */     setPassThroughEntities();
/*  56 */     setCanGetStuckInGround();
/*  57 */     setTargetResetTime(120);
/*  58 */     setArmorPiercing(0.75F);
/*  59 */     setUnavoidable();
/*     */     
/*  61 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*  62 */     this.onTickEvent = this::onTickEvent;
/*     */   }
/*     */   
/*     */   public void onBlockImpactEvent(BlockPos hit) {
/*  66 */     int explosionRadius = (int)(this.EXPLOSION_RADIUS * getSize());
/*  67 */     int shockwaveRadius = (int)(this.SHOCKWAVE_RADIUS * getSize());
/*     */     
/*  69 */     AbilityHelper.createSphere(this.field_70170_p, func_233580_cy_().func_177979_c(explosionRadius / 8), explosionRadius, explosionRadius, false, Blocks.field_150350_a, 3, GRIEF_RULE);
/*     */     
/*  71 */     if (!this.dealtAOE) {
/*  72 */       List<Entity> list = WyHelper.getNearbyEntities(func_213303_ch(), (IWorld)this.field_70170_p, shockwaveRadius, null, new Class[] { Entity.class });
/*     */       
/*  74 */       list.remove(getThrower());
/*     */       
/*  76 */       for (Entity target : list) {
/*  77 */         if (target instanceof net.minecraft.entity.projectile.ThrowableEntity || target instanceof net.minecraft.entity.projectile.AbstractArrowEntity) {
/*  78 */           target.func_70106_y();
/*     */         }
/*     */         
/*  81 */         if (target instanceof LivingEntity) {
/*  82 */           target.func_70097_a((DamageSource)AbilityDamageSource.causeAbilityDamage(getThrower(), getParent()), getDamage());
/*     */           
/*  84 */           Vector3d speed = target.func_70040_Z().func_186678_a(-1.0D).func_216372_d(5.0D, 0.0D, 5.0D);
/*     */           
/*  86 */           AbilityHelper.setDeltaMovement(target, speed.field_72450_a, 1.0D, speed.field_72449_c);
/*     */         } 
/*     */       } 
/*     */       
/*  90 */       this.dealtAOE = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onTickEvent() {
/*  95 */     setDamage(2.0F * getSize());
/*     */     
/*  97 */     if (!this.field_70170_p.field_72995_K) {
/*  98 */       for (int i = 0; i < 25; i++) {
/*  99 */         ParticleType<SimpleParticleData> goro_particle = ClientConfig.INSTANCE.isGoroBlue() ? (ParticleType<SimpleParticleData>)ModParticleTypes.GORO.get() : (ParticleType<SimpleParticleData>)ModParticleTypes.GORO_YELLOW.get();
/* 100 */         ParticleType<SimpleParticleData> goro2_particle = ClientConfig.INSTANCE.isGoroBlue() ? (ParticleType<SimpleParticleData>)ModParticleTypes.GORO2.get() : (ParticleType<SimpleParticleData>)ModParticleTypes.GORO2_YELLOW.get();
/*     */         
/* 102 */         ParticleType<SimpleParticleData> particleToUse = (this.field_70173_aa % 2 == 0) ? goro2_particle : goro_particle;
/*     */         
/* 104 */         double offsetX = WyHelper.randomDouble() * 5.0D;
/* 105 */         double offsetY = WyHelper.randomDouble();
/* 106 */         double offsetZ = WyHelper.randomDouble() * 5.0D;
/*     */         
/* 108 */         SimpleParticleData data = new SimpleParticleData(particleToUse);
/*     */         
/* 110 */         data.setLife(20);
/* 111 */         data.setSize(7.0F);
/*     */         
/* 113 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*     */       } 
/*     */       
/* 116 */       func_70101_b(0.0F, 90.0F);
/*     */       
/* 118 */       if (!this.closeToFloor) {
/* 119 */         BlockRayTraceResult blockRayTraceResult = WyHelper.rayTraceBlocks((Entity)this, 30.0D);
/*     */         
/* 121 */         if (blockRayTraceResult.func_216346_c().equals(RayTraceResult.Type.BLOCK)) {
/* 122 */           setMaxLife(16);
/*     */           
/* 124 */           this.closeToFloor = true;
/*     */         } 
/*     */       } 
/*     */       
/* 128 */       if (this.field_70173_aa % 5 == 0) {
/* 129 */         for (int j = 0; j < 10; j++) {
/* 130 */           float boltLength = (float)WyHelper.randomWithRange(36, 50);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 135 */           LightningEntity bolt = new LightningEntity((Entity)this, func_226277_ct_(), func_226278_cu_(), func_226281_cx_(), (float)WyHelper.randomWithRange(0, 360), (float)WyHelper.randomWithRange(-90, 90), boltLength, boltLength, getParent());
/*     */           
/* 137 */           bolt.setAngle(20);
/* 138 */           bolt.setMaxLife(20);
/* 139 */           bolt.setDamage(0.0F);
/* 140 */           bolt.setExplosion(0, false);
/* 141 */           bolt.setSize(boltLength / 800.0F);
/* 142 */           bolt.setColor(ClientConfig.INSTANCE.isGoroBlue() ? ElThorAbility.BLUE_THUNDER : ElThorAbility.YELLOW_THUNDER);
/* 143 */           bolt.setBranches(3);
/* 144 */           bolt.setSegments(10);
/*     */           
/* 146 */           this.field_70170_p.func_217376_c((Entity)bolt);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSize() {
/* 154 */     return (float)func_174813_aQ().func_72320_b() * 4.0F;
/*     */   }
/*     */   
/*     */   public void increaseSize(float increaseAmount) {
/* 158 */     float size = (float)func_174813_aQ().func_72320_b() + increaseAmount;
/*     */     
/* 160 */     setEntityCollisionSize(size);
/* 161 */     setBlockCollisionSize(size);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\goro\RaigoProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */