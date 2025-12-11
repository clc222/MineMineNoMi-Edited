/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.goro;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.function.Consumer;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.effect.LightningBoltEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.ProtectedArea;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ProtectedAreasData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.entities.SUpdateLightningEntity;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.goro.LightningExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class LightningEntity extends AbilityProjectileEntity {
/*  44 */   private float length = 0.0F;
/*  45 */   private float size = 0.01F;
/*     */   
/*  47 */   private int branches = 5;
/*  48 */   private int segments = 7;
/*  49 */   private int color = 16641300;
/*  50 */   private int alpha = 77;
/*  51 */   private int angle = 50;
/*     */   
/*     */   private boolean mimicVanilla = true;
/*     */   
/*     */   private boolean lightningMovement = true;
/*     */   private boolean energyEffect = true;
/*  57 */   private final LightningBoltEntity bolt = new LightningBoltEntity(EntityType.field_200728_aG, this.field_70170_p);
/*     */   
/*     */   public long seed;
/*     */   
/*  61 */   public ArrayList<Integer> targets = new ArrayList<>();
/*  62 */   public ArrayList<Entity> explosionTargets = new ArrayList<>();
/*     */   
/*  64 */   public Set<Long> firstContactBlocks = new LinkedHashSet<>();
/*     */   
/*  66 */   private int explosionSize = 0;
/*  67 */   private float explosionBlockResistance = 0.1F;
/*     */   private boolean explosionDestroysBlocks = true;
/*     */   private boolean hasToCheckForTarget = true;
/*     */   private boolean canCauseKnockback = true;
/*  71 */   private double boxSizeDivision = 0.1D;
/*     */   
/*  73 */   private int targetTimeToReset = 20;
/*  74 */   private float maxTravelDistance = 0.0F;
/*  75 */   private float travelSpeed = 12.0F; public Consumer<LightningEntity> hitBlockEvent = entity -> {
/*     */     
/*     */     };
/*     */   
/*     */   public LightningEntity(EntityType<Entity> entityType, World world) {
/*  80 */     super(entityType, world);
/*     */     
/*  82 */     this.field_70158_ak = true;
/*     */     
/*  84 */     this.seed = this.field_70146_Z.nextLong();
/*     */   }
/*     */   
/*     */   public LightningEntity(Entity thrower, float travelLength, float travelSpeed, @Nullable AbilityCore<? extends IAbility> ability) {
/*  88 */     this(thrower, thrower.func_226277_ct_(), thrower.func_226280_cw_(), thrower.func_226281_cx_(), thrower.field_70177_z, thrower.field_70125_A, travelLength, travelSpeed, ability);
/*     */   }
/*     */   
/*     */   public LightningEntity(Entity thrower, double posX, double posY, double posZ, float rotationYaw, float rotationPitch, float maxTravelDistance, float travelSpeed, @Nullable AbilityCore<? extends IAbility> abilityCore) {
/*  92 */     super((EntityType)GoroProjectiles.LIGHTNING.get(), thrower.field_70170_p, (thrower instanceof AbilityProjectileEntity) ? ((AbilityProjectileEntity)thrower).getThrower() : (LivingEntity)thrower, abilityCore);
/*     */     
/*  94 */     func_70012_b(posX, posY, posZ, rotationYaw, rotationPitch);
/*     */     
/*  96 */     setLength(2.0F);
/*     */     
/*  98 */     this.entityDamaged = true;
/*     */     
/* 100 */     this.maxTravelDistance = maxTravelDistance;
/* 101 */     this.travelSpeed = travelSpeed;
/*     */     
/* 103 */     func_189654_d(true);
/* 104 */     setNoVelocityRotation();
/* 105 */     setArmorPiercing(0.75F);
/* 106 */     setRemoveOnBlockLimit(false);
/*     */   }
/*     */   
/*     */   public void onEntityImpactEvent(Entity hitEntity) {
/* 110 */     if (this.targets.contains(Integer.valueOf(hitEntity.getEntity().func_145782_y())) || hitEntity.func_145782_y() == func_145782_y()) {
/*     */       return;
/*     */     }
/*     */     
/* 114 */     if (this.hasToCheckForTarget) {
/* 115 */       this.targets.add(Integer.valueOf(hitEntity.getEntity().func_145782_y()));
/*     */     }
/*     */     
/* 118 */     if (getDamage() > 0.0F) {
/* 119 */       if (hitEntity.func_70089_S() && hitEntity instanceof LivingEntity) {
/* 120 */         LivingEntity target = (LivingEntity)hitEntity;
/*     */         
/* 122 */         if (getMimicVanilla()) {
/* 123 */           target.func_70015_d(2 + (int)((func_223314_ad() / 20) + getDamage() / 5.0F));
/* 124 */           target.func_241841_a((ServerWorld)func_130014_f_(), this.bolt);
/*     */         } 
/*     */         
/* 127 */         target.field_70737_aN = target.field_70172_ad = 0;
/*     */         
/* 129 */         if (target.func_70097_a((DamageSource)getDamageSource(), getDamage())) {
/* 130 */           target.func_195064_c(new EffectInstance((Effect)ModEffects.PARALYSIS.get(), 10, 0, false, false, true));
/*     */         }
/*     */         
/* 133 */         onFirstImpact(target.func_233580_cy_());
/*     */       } 
/*     */       
/* 136 */       if (hitEntity instanceof net.minecraft.entity.projectile.ThrowableEntity) {
/* 137 */         if (hitEntity instanceof AbilityProjectileEntity && ((AbilityProjectileEntity)hitEntity).getDamageSource() != null && (((AbilityProjectileEntity)hitEntity).getDamageSource()).field_76373_n.equals("lightningBolt")) {
/*     */           return;
/*     */         }
/*     */         
/* 141 */         hitEntity.func_70106_y();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onBlockImpact(BlockPos hit) {
/* 147 */     if (this.explosionDestroysBlocks && AbilityHelper.placeBlockIfAllowed(getThrower(), hit, Blocks.field_150350_a.func_176223_P(), 3, DefaultProtectionRules.CORE_FOLIAGE_ORE)) {
/* 148 */       increaseBlocksAffected((int)this.field_70170_p.func_180495_p(hit).func_185887_b((IBlockReader)this.field_70170_p, hit));
/*     */     }
/*     */     
/* 151 */     this.hitBlockEvent.accept(this);
/*     */   }
/*     */   
/*     */   public void onFirstImpact(BlockPos hit) {
/* 155 */     ProtectedArea area = ProtectedAreasData.get(this.field_70170_p).getProtectedArea(hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p());
/*     */     
/* 157 */     if (area != null && !area.canUseAbility(getParent())) {
/*     */       return;
/*     */     }
/*     */     
/* 161 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), this.explosionSize);
/*     */     
/* 163 */     explosion.immuneEntities = this.explosionTargets;
/*     */     
/* 165 */     explosion.setExplosionSound(false);
/*     */     
/* 167 */     if (!this.canCauseKnockback) {
/* 168 */       explosion.disableExplosionKnockback();
/*     */     }
/*     */     
/* 171 */     explosion.setStaticDamage(getDamage() / 2.0F);
/* 172 */     explosion.setDamageSource((DamageSource)getDamageSource());
/* 173 */     explosion.setDamageOwner(false);
/* 174 */     explosion.setStaticBlockResistance(this.explosionBlockResistance);
/* 175 */     explosion.setDestroyBlocks(this.explosionDestroysBlocks);
/* 176 */     explosion.setFireAfterExplosion(true);
/* 177 */     explosion.setSmokeParticles((ParticleEffect)new LightningExplosionParticleEffect(this.explosionSize + 1));
/* 178 */     explosion.setExplosionSound(false);
/* 179 */     explosion.setDamageEntities(true);
/* 180 */     explosion.doExplosion();
/*     */     
/* 182 */     this.explosionTargets.addAll(explosion.damagedEntities);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 187 */     if (getLightningMovement()) {
/* 188 */       this.seed = this.field_70146_Z.nextLong();
/*     */     }
/*     */     
/* 191 */     boolean lengthChanged = false;
/*     */     
/* 193 */     if (!this.field_70170_p.field_72995_K) {
/* 194 */       if (getLength() != this.maxTravelDistance) {
/* 195 */         setLength(Math.min(getLength() + this.travelSpeed, this.maxTravelDistance));
/*     */         
/* 197 */         lengthChanged = true;
/* 198 */       } else if ((getMaxLife() - getLife()) % this.targetTimeToReset == 0 && this.hasToCheckForTarget) {
/* 199 */         this.targets.clear();
/*     */         
/* 201 */         this.explosionTargets.clear();
/*     */       } 
/*     */       
/* 204 */       ProtectedArea area = ProtectedAreasData.get(this.field_70170_p).getProtectedArea((int)func_226277_ct_(), (int)func_226278_cu_(), (int)func_226281_cx_());
/*     */       
/* 206 */       boolean canDestroyBlocks = (area == null || area.canDestroyBlocks());
/* 207 */       boolean canHurtEntities = (area == null || area.canHurtEntities());
/* 208 */       boolean canExplode = (this.explosionSize > 0 && canDestroyBlocks && CommonConfig.INSTANCE.isAbilityGriefingEnabled());
/*     */       
/* 210 */       Vector3d lookVec = func_70040_Z().func_72432_b();
/*     */       
/* 212 */       List<Entity> entities = new ArrayList<>();
/*     */       
/* 214 */       Set<Long> contactBlocks = new LinkedHashSet<>();
/*     */       
/* 216 */       for (int i = 0; i < getLength(); i++) {
/* 217 */         double currentX = Math.floor(func_226277_ct_() + lookVec.field_72450_a * i);
/* 218 */         double currentY = Math.floor(func_226280_cw_() + lookVec.field_72448_b * i);
/* 219 */         double currentZ = Math.floor(func_226281_cx_() + lookVec.field_72449_c * i);
/*     */         
/* 221 */         BlockPos pos = new BlockPos(currentX, currentY, currentZ);
/*     */         
/* 223 */         BlockState state = this.field_70170_p.func_180495_p(pos);
/* 224 */         boolean isVanillaBarrier = (state.func_177230_c() == Blocks.field_180401_cv && !WyHelper.isInChallengeDimension(this.field_70170_p));
/* 225 */         if (isVanillaBarrier || state.func_177230_c() == ModBlocks.BARRIER.get() || state.func_177230_c().func_199767_j().func_206844_a((ITag)ModTags.Items.KAIROSEKI)) {
/* 226 */           func_70106_y();
/*     */           
/*     */           return;
/*     */         } 
/*     */         
/* 231 */         double boxSize = 0.2D + getSize() / this.boxSizeDivision;
/*     */         
/* 233 */         AxisAlignedBB alignedBB = new AxisAlignedBB(currentX - boxSize, currentY - boxSize, currentZ - boxSize, currentX + boxSize, currentY + boxSize, currentZ + boxSize);
/*     */         
/* 235 */         this.field_70170_p.func_72839_b((Entity)getThrower(), alignedBB).stream().filter(entity -> !entities.contains(entity)).forEach(entities::add);
/*     */         
/* 237 */         if (canExplode || !getMimicVanilla()) {
/* 238 */           if (lengthChanged) {
/* 239 */             long packedPos = pos.func_218275_a();
/*     */             
/* 241 */             if (state.func_185904_a() != Material.field_151579_a && !this.firstContactBlocks.contains(Long.valueOf(packedPos))) {
/* 242 */               this.firstContactBlocks.add(Long.valueOf(packedPos));
/*     */             
/*     */             }
/*     */           }
/*     */           else {
/*     */             
/* 248 */             int minX = (int)alignedBB.field_72340_a;
/* 249 */             int minY = (int)alignedBB.field_72338_b;
/* 250 */             int minZ = (int)alignedBB.field_72339_c;
/* 251 */             int maxX = (int)alignedBB.field_72336_d;
/* 252 */             int maxY = (int)alignedBB.field_72337_e;
/* 253 */             int maxZ = (int)alignedBB.field_72334_f;
/*     */             
/* 255 */             BlockPos.Mutable checkedPos = new BlockPos.Mutable(0, 0, 0);
/*     */             
/* 257 */             for (int x = minX; x < maxX; x++) {
/* 258 */               for (int y = minY; y < maxY; y++) {
/* 259 */                 for (int z = minZ; z < maxZ; z++) {
/* 260 */                   checkedPos.func_181079_c(x, y, z);
/*     */                   
/* 262 */                   long packedPos = checkedPos.func_218275_a();
/*     */                   
/* 264 */                   if (this.field_70170_p.func_180495_p((BlockPos)checkedPos).func_185904_a() != Material.field_151579_a && !contactBlocks.contains(Long.valueOf(packedPos)) && !checkedPos.equals(pos)) {
/* 265 */                     contactBlocks.add(Long.valueOf(packedPos));
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 274 */       if (canHurtEntities) {
/* 275 */         entities.forEach(this::onEntityImpactEvent);
/*     */       }
/*     */       
/* 278 */       if (canExplode) {
/* 279 */         if (lengthChanged) {
/* 280 */           for (Iterator<Long> iterator = this.firstContactBlocks.iterator(); iterator.hasNext(); ) { long packedPos = ((Long)iterator.next()).longValue();
/* 281 */             if (getBlocksAffected() >= getBlocksAffectedLimit()) {
/*     */               break;
/*     */             }
/*     */             
/* 285 */             BlockPos hit = BlockPos.func_218283_e(packedPos);
/*     */             
/* 287 */             onFirstImpact(hit); }
/*     */         
/* 289 */         } else if (this.field_70173_aa % 5 == 0) {
/* 290 */           for (Iterator<Long> iterator = contactBlocks.iterator(); iterator.hasNext(); ) { long packedPos = ((Long)iterator.next()).longValue();
/* 291 */             if (getBlocksAffected() >= getBlocksAffectedLimit()) {
/*     */               break;
/*     */             }
/*     */             
/* 295 */             BlockPos hit = BlockPos.func_218283_e(packedPos);
/*     */             
/* 297 */             onBlockImpact(hit); }
/*     */         
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 303 */     if (lengthChanged && getLength() == this.maxTravelDistance) {
/* 304 */       lengthChanged = false;
/* 305 */     } else if (getMimicVanilla()) {
/* 306 */       this.field_70170_p.func_225605_c_(4);
/*     */     } 
/*     */     
/* 309 */     super.func_70071_h_();
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean func_70112_a(double distance) {
/* 315 */     double d0 = 64.0D * Entity.func_184183_bd();
/*     */     
/* 317 */     return (distance < d0 * d0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExplosion(int range) {
/* 325 */     this.explosionSize = range;
/*     */   }
/*     */   
/*     */   public void setExplosion(int range, boolean destroysBlocks) {
/* 329 */     this.explosionSize = range;
/* 330 */     this.explosionDestroysBlocks = destroysBlocks;
/*     */   }
/*     */   
/*     */   public void setExplosion(int range, boolean destroysBlocks, float explosionBlockResistance) {
/* 334 */     this.explosionSize = range;
/* 335 */     this.explosionDestroysBlocks = destroysBlocks;
/* 336 */     this.explosionBlockResistance = explosionBlockResistance;
/*     */   }
/*     */   
/*     */   public void setTargetTimeToReset(int value) {
/* 340 */     this.targetTimeToReset = value;
/*     */   }
/*     */   
/*     */   public void disableExplosionKnockback() {
/* 344 */     this.canCauseKnockback = false;
/*     */   }
/*     */   
/*     */   public double getBoxSizeDivision() {
/* 348 */     return this.boxSizeDivision;
/*     */   }
/*     */   
/*     */   public void setBoxSizeDivision(double value) {
/* 352 */     this.boxSizeDivision = value;
/*     */   }
/*     */   
/*     */   public void setLength(float length) {
/* 356 */     this.length = length;
/*     */     
/* 358 */     updateClient();
/*     */   }
/*     */   
/*     */   public float getLength() {
/* 362 */     return this.length;
/*     */   }
/*     */   
/*     */   public void setSize(float size) {
/* 366 */     this.size = size;
/*     */     
/* 368 */     updateClient();
/*     */   }
/*     */   
/*     */   public float getSize() {
/* 372 */     return this.size;
/*     */   }
/*     */   
/*     */   public void setBranches(int branches) {
/* 376 */     this.branches = branches;
/*     */     
/* 378 */     updateClient();
/*     */   }
/*     */   
/*     */   public int getBranches() {
/* 382 */     return this.branches;
/*     */   }
/*     */   
/*     */   public void setSegments(int segments) {
/* 386 */     this.segments = segments;
/*     */     
/* 388 */     updateClient();
/*     */   }
/*     */   
/*     */   public int getSegments() {
/* 392 */     return this.segments;
/*     */   }
/*     */   
/*     */   public void setColor(Color color) {
/* 396 */     this.color = color.getRGB();
/* 397 */     this.alpha = color.getAlpha();
/*     */     
/* 399 */     updateClient();
/*     */   }
/*     */   
/*     */   public int getColor() {
/* 403 */     return this.color;
/*     */   }
/*     */   
/*     */   public int getAlpha() {
/* 407 */     return this.alpha;
/*     */   }
/*     */   
/*     */   public void setAngle(int angle) {
/* 411 */     this.angle = Math.round((float)WyHelper.clamp(angle, 0L, 180L));
/*     */     
/* 413 */     updateClient();
/*     */   }
/*     */   
/*     */   public int getAngle() {
/* 417 */     return this.angle;
/*     */   }
/*     */   
/*     */   public void setLightningMimic(boolean hasLightningMimic) {
/* 421 */     this.mimicVanilla = hasLightningMimic;
/*     */     
/* 423 */     updateClient();
/*     */   }
/*     */   
/*     */   public boolean getMimicVanilla() {
/* 427 */     return this.mimicVanilla;
/*     */   }
/*     */   
/*     */   public void setLightningMovement(boolean hasLightningMovement) {
/* 431 */     this.lightningMovement = hasLightningMovement;
/*     */     
/* 433 */     updateClient();
/*     */   }
/*     */   
/*     */   public boolean getLightningMovement() {
/* 437 */     return this.lightningMovement;
/*     */   }
/*     */   
/*     */   public void setEnergyEffect(boolean hasEnergyEffect) {
/* 441 */     this.energyEffect = hasEnergyEffect;
/*     */     
/* 443 */     updateClient();
/*     */   }
/*     */   
/*     */   public boolean getEnergyEffect() {
/* 447 */     return this.energyEffect;
/*     */   }
/*     */   
/*     */   public void setLightningProperties(float length, float size, int branches, int segments, int color, int alpha, int angle, boolean mimicVanilla, boolean lightningMovement, boolean energyEffect) {
/* 451 */     this.length = length;
/* 452 */     this.size = size;
/* 453 */     this.branches = branches;
/* 454 */     this.segments = segments;
/* 455 */     this.color = color;
/* 456 */     this.alpha = alpha;
/* 457 */     this.angle = angle;
/* 458 */     this.mimicVanilla = mimicVanilla;
/* 459 */     this.lightningMovement = lightningMovement;
/* 460 */     this.energyEffect = energyEffect;
/*     */     
/* 462 */     updateClient();
/*     */   }
/*     */   
/*     */   private void updateClient() {
/* 466 */     if (!this.field_70170_p.field_72995_K && getThrower() != null)
/* 467 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateLightningEntity(this), (Entity)getThrower()); 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\goro\LightningEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */