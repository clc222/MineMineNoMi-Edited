/*     */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import java.io.Serializable;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.block.AbstractFireBlock;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.enchantment.ProtectionEnchantment;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.FallingBlockEntity;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.fluid.FluidState;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.loot.LootContext;
/*     */ import net.minecraft.loot.LootParameters;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.ExplosionContext;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.event.ForgeEventFactory;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.KairosekiBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ProtectedAreasData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAdvancements;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ExplosionAbility extends Explosion {
/*     */   private World world;
/*     */   private Entity exploder;
/*     */   private double explosionX;
/*     */   private double explosionY;
/*     */   private double explosionZ;
/*     */   private float explosionSize;
/*     */   private ParticleEffect particles;
/*     */   private DamageSource damageSource;
/*  67 */   private final List<BlockPos> affectedBlockPositions = Lists.newArrayList();
/*  68 */   private final Map<PlayerEntity, Vector3d> playerKnockbackMap = Maps.newHashMap();
/*  69 */   public List<FallingBlockEntity> removedBlocks = Lists.newArrayList();
/*  70 */   private final Random random = new Random();
/*     */   
/*     */   private boolean canStartFireAfterExplosion = false;
/*     */   private boolean canDestroyBlocks = true;
/*     */   private boolean canDropBlocksAfterExplosion = false;
/*     */   private boolean canDamageEntities = true;
/*     */   private boolean checkForFaction = true;
/*     */   private boolean canDamageOwner = false;
/*     */   private boolean canDamageOnce = true;
/*     */   private boolean canProduceExplosionSound = true;
/*     */   private boolean protectOwnerFromFalling = false;
/*     */   private boolean canCauseKnockback = true;
/*     */   private boolean addRemovedBlocksToList = false;
/*     */   private boolean hasBlockLimit = false;
/*  84 */   private float staticDamage = 0.0F;
/*  85 */   private float staticBlockResistance = 0.0F;
/*  86 */   private int heightDifference = 0;
/*     */   private int explodedBlocksLimit;
/*  88 */   private int size = 52;
/*     */   
/*     */   private int explodedBlocks;
/*  91 */   public ArrayList<Entity> immuneEntities = new ArrayList<>();
/*  92 */   public ArrayList<Entity> damagedEntities = new ArrayList<>();
/*     */   
/*  94 */   private static final ArrayList<Entity> DAMAGED_ENTITIES = new ArrayList<>(); public IOnBlockDestroyed onBlockDestroyedEvent = hitPos -> {
/*     */     
/*     */     };
/*     */   
/*     */   public ExplosionAbility(Entity entity, World world, double posX, double posY, double posZ, float power) {
/*  99 */     super(world, entity, (DamageSource)null, (ExplosionContext)null, posX, posY, posZ, power, false, Explosion.Mode.DESTROY);
/*     */     
/* 101 */     this.world = world;
/* 102 */     this.exploder = entity;
/* 103 */     this.explosionSize = power;
/* 104 */     this.explosionX = posX;
/* 105 */     this.explosionY = posY;
/* 106 */     this.explosionZ = posZ;
/*     */     
/* 108 */     this.damageSource = DamageSource.func_94539_a(this);
/*     */   }
/*     */   
/*     */   public void setExplosionPos(double posX, double posY, double posZ) {
/* 112 */     this.explosionX = posX;
/* 113 */     this.explosionY = posY;
/* 114 */     this.explosionZ = posZ;
/*     */   }
/*     */   
/*     */   public void setExplosionSize(float explosionSize) {
/* 118 */     this.explosionSize = explosionSize;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExplodedBlocksLimit(int limit) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHasBlockLimit(boolean hasBlockLimit) {
/* 128 */     this.hasBlockLimit = hasBlockLimit;
/*     */   }
/*     */   
/*     */   public double getStaticDamage() {
/* 132 */     return this.staticDamage;
/*     */   }
/*     */   
/*     */   public void setStaticDamage(float damage) {
/* 136 */     this.staticDamage = damage;
/*     */   }
/*     */   
/*     */   public double getStaticBlockResistance() {
/* 140 */     return this.staticBlockResistance;
/*     */   }
/*     */   
/*     */   public void setStaticBlockResistance(float damage) {
/* 144 */     this.staticBlockResistance = damage;
/*     */   }
/*     */   
/*     */   public void setHeightDifference(int heightDifference) {
/* 148 */     this.heightDifference = heightDifference;
/*     */   }
/*     */   
/*     */   public void setDamageOwner(boolean damageOwner) {
/* 152 */     this.canDamageOwner = damageOwner;
/*     */   }
/*     */   
/*     */   public void setDamageEntities(boolean damageEntities) {
/* 156 */     this.canDamageEntities = damageEntities;
/*     */   }
/*     */   
/*     */   public void ignoreFactionChecks() {
/* 160 */     this.checkForFaction = false;
/*     */   }
/*     */   
/*     */   public void setDropBlocksAfterExplosion(boolean canDrop) {
/* 164 */     this.canDropBlocksAfterExplosion = canDrop;
/*     */   }
/*     */   
/*     */   public void setFireAfterExplosion(boolean hasFire) {
/* 168 */     this.canStartFireAfterExplosion = hasFire;
/*     */   }
/*     */   
/*     */   public void setDestroyBlocks(boolean canDestroyBlocks) {
/* 172 */     this.canDestroyBlocks = canDestroyBlocks;
/*     */   }
/*     */   
/*     */   public void setSmokeParticles(ParticleEffect particle) {
/* 176 */     this.particles = particle;
/*     */   }
/*     */   
/*     */   public boolean getDamageOnce() {
/* 180 */     return this.canDamageOnce;
/*     */   }
/*     */   
/*     */   public void setDamageOnce(boolean flag) {
/* 184 */     this.canDamageOnce = flag;
/*     */   }
/*     */   
/*     */   public void addRemovedBlocksToList() {
/* 188 */     this.addRemovedBlocksToList = true;
/*     */   }
/*     */   
/*     */   public void setProtectOwnerFromFalling(boolean flag) {
/* 192 */     this.protectOwnerFromFalling = flag;
/*     */   }
/*     */   
/*     */   public boolean hasSmokeParticles() {
/* 196 */     return (this.particles != null);
/*     */   }
/*     */   
/*     */   public void setExplosionSound(boolean hasSound) {
/* 200 */     this.canProduceExplosionSound = hasSound;
/*     */   }
/*     */   
/*     */   private void resetDamage(LivingEntity entity) {
/* 204 */     entity.field_70737_aN = entity.field_70172_ad = 0;
/*     */   }
/*     */   
/*     */   public void disableExplosionKnockback() {
/* 208 */     this.canCauseKnockback = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public DamageSource func_199591_b() {
/* 213 */     return this.damageSource;
/*     */   }
/*     */   
/*     */   public void setDamageSource(DamageSource damageSourceIn) {
/* 217 */     this.damageSource = damageSourceIn;
/*     */   }
/*     */   
/*     */   public int getExplodedBlocks() {
/* 221 */     return this.explodedBlocks;
/*     */   }
/*     */   
/*     */   public void doExplosion() {
/* 225 */     if (ForgeEventFactory.onExplosionStart(this.world, this)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 231 */     if (this.heightDifference > 0 && this.exploder != null && this.exploder.func_226278_cu_() - this.heightDifference > this.explosionY) {
/*     */       return;
/*     */     }
/*     */     
/* 235 */     Set<BlockPos> set = Sets.newHashSet();
/*     */ 
/*     */     
/* 238 */     if ((this.size + 4) > this.explosionSize) {
/* 239 */       this.size = Math.max((int)(this.explosionSize + 4.0F), 16);
/*     */     }
/*     */     
/* 242 */     BlockPos.Mutable foundBlockPos = new BlockPos.Mutable();
/*     */     
/* 244 */     for (int j = 0; j < this.size; j++) {
/* 245 */       for (int k = 0; k < this.size; k++) {
/* 246 */         for (int l = 0; l < this.size; l++) {
/* 247 */           if (j == 0 || j == this.size - 1 || k == 0 || k == this.size - 1 || l == 0 || l == this.size - 1) {
/* 248 */             double d0 = (j / (this.size - 1) * 2.0F - 1.0F);
/* 249 */             double d1 = (k / (this.size - 1) * 2.0F - 1.0F);
/* 250 */             double d2 = (l / (this.size - 1) * 2.0F - 1.0F);
/* 251 */             double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
/*     */             
/* 253 */             d0 /= d3;
/* 254 */             d1 /= d3;
/* 255 */             d2 /= d3;
/*     */             
/* 257 */             float power = this.explosionSize * (0.7F + this.world.field_73012_v.nextFloat() * 0.6F);
/*     */             
/* 259 */             double eX = this.explosionX;
/* 260 */             double eY = this.explosionY;
/* 261 */             double eZ = this.explosionZ;
/*     */             
/* 263 */             for (float f1 = 0.3F; power > 0.0F; power -= 0.22500001F) {
/* 264 */               foundBlockPos.func_189532_c(eX, eY, eZ);
/*     */               
/* 266 */               BlockState blockState = this.world.func_180495_p((BlockPos)foundBlockPos);
/* 267 */               FluidState ifluidstate = this.world.func_204610_c((BlockPos)foundBlockPos);
/*     */               
/* 269 */               if (!ifluidstate.func_206888_e()) {
/*     */                 break;
/*     */               }
/*     */               
/* 273 */               if (!blockState.func_196958_f() || !ifluidstate.func_206888_e()) {
/* 274 */                 float blockExplosionResistance = Math.max(blockState.getExplosionResistance((IBlockReader)this.world, (BlockPos)foundBlockPos, this), ifluidstate.getExplosionResistance((IBlockReader)this.world, (BlockPos)foundBlockPos, this));
/*     */                 
/* 276 */                 if (this.exploder != null) {
/* 277 */                   blockExplosionResistance = this.exploder.func_180428_a(this, (IBlockReader)this.world, (BlockPos)foundBlockPos, blockState, ifluidstate, blockExplosionResistance);
/*     */                 }
/*     */                 
/* 280 */                 power = (float)(power - ((getStaticBlockResistance() > 0.0D) ? getStaticBlockResistance() : ((blockExplosionResistance + 0.3F) * 0.1F)));
/*     */               } 
/*     */               
/* 283 */               if (power > 0.0F && (this.exploder == null || this.exploder.func_174816_a(this, (IBlockReader)this.world, (BlockPos)foundBlockPos, blockState, power)) && 
/* 284 */                 !blockState.func_235714_a_((ITag)ModTags.Blocks.KAIROSEKI)) {
/* 285 */                 set.add(foundBlockPos.func_185334_h());
/*     */               }
/*     */ 
/*     */               
/* 289 */               eX += d0 * 0.30000001192092896D;
/* 290 */               eY += d1 * 0.30000001192092896D;
/* 291 */               eZ += d2 * 0.30000001192092896D;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 298 */     this.affectedBlockPositions.addAll(set);
/*     */     
/* 300 */     float size = this.explosionSize * 2.0F;
/*     */ 
/*     */ 
/*     */     
/* 304 */     Vector3d explosionPos = new Vector3d(this.explosionX, this.explosionY, this.explosionZ);
/*     */     
/* 306 */     if (getExploder() instanceof LivingEntity) {
/* 307 */       Predicate<Entity> factionCheck = null;
/*     */       
/* 309 */       if (this.checkForFaction) {
/* 310 */         factionCheck = ModEntityPredicates.getEnemyFactions((LivingEntity)getExploder());
/*     */       }
/*     */       
/* 313 */       list = WyHelper.getNearbyEntities(explosionPos, (IWorld)this.world, size, size, size, factionCheck, new Class[] { Entity.class });
/*     */     } else {
/* 315 */       list = WyHelper.getNearbyEntities(explosionPos, (IWorld)this.world, size, size, size, null, new Class[] { Entity.class });
/*     */     } 
/*     */     
/* 318 */     if (this.canDamageOwner) {
/* 319 */       list.add(getExploder());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 332 */     List<Entity> list = (List<Entity>)list.stream().filter(e -> { if (e instanceof LivingEntity) { DamageSource source = func_199591_b(); if (source.func_76364_f() != null) return ((LivingEntity)e).func_70685_l(source.func_76364_f());  }  return true; }).collect(Collectors.toList());
/*     */     
/* 334 */     if (this.immuneEntities.size() > 0) {
/* 335 */       list.removeAll(this.immuneEntities);
/*     */     }
/*     */     
/* 338 */     ForgeEventFactory.onExplosionDetonate(this.world, this, list, size);
/*     */     
/* 340 */     Vector3d v3d = new Vector3d(this.explosionX, this.explosionY, this.explosionZ);
/*     */     
/* 342 */     if (this.canDamageEntities) {
/* 343 */       for (int k2 = 0; k2 < list.size(); k2++) {
/* 344 */         Entity entity = list.get(k2);
/*     */         
/* 346 */         if (entity == null || !entity.func_70089_S()) {
/* 347 */           if (DAMAGED_ENTITIES.contains(entity)) {
/* 348 */             DAMAGED_ENTITIES.remove(entity);
/*     */           
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 354 */           if (DAMAGED_ENTITIES.contains(entity) && entity instanceof LivingEntity && ((LivingEntity)entity).field_70737_aN == 0 && entity.field_70172_ad == 0) {
/* 355 */             DAMAGED_ENTITIES.remove(entity);
/*     */           }
/*     */           
/* 358 */           if (!entity.func_180427_aV()) {
/* 359 */             double distance = entity.func_70092_e(this.explosionX, this.explosionY, this.explosionZ) / size;
/*     */             
/* 361 */             if (distance <= 1.0D) {
/* 362 */               double xDistance = entity.func_226277_ct_() - this.explosionX;
/* 363 */               double yDistance = entity.func_226278_cu_() + entity.func_70047_e() - this.explosionY;
/* 364 */               double zDistance = entity.func_226281_cx_() - this.explosionZ;
/* 365 */               double squareDistance = MathHelper.func_76133_a(xDistance * xDistance + yDistance * yDistance + zDistance * zDistance);
/*     */               
/* 367 */               if (squareDistance != 0.0D) {
/* 368 */                 xDistance /= squareDistance;
/* 369 */                 yDistance /= squareDistance;
/* 370 */                 zDistance /= squareDistance;
/*     */                 
/* 372 */                 double blockDensity = (getStaticBlockResistance() > 0.0D) ? 0.0D : Explosion.func_222259_a(v3d, entity);
/* 373 */                 double power = (1.0D - distance) * blockDensity;
/*     */                 
/* 375 */                 if (entity instanceof LivingEntity && !DAMAGED_ENTITIES.contains(entity) && getDamageOnce()) {
/* 376 */                   resetDamage((LivingEntity)entity);
/*     */                 }
/*     */                 
/* 379 */                 if (this.staticDamage > 0.0F) {
/* 380 */                   if (entity.func_70097_a(func_199591_b(), this.staticDamage)) {
/* 381 */                     this.damagedEntities.add(entity);
/*     */                     
/* 383 */                     DAMAGED_ENTITIES.add(entity);
/*     */                   } 
/*     */                 } else {
/* 386 */                   float damage = (float)((power * power + power) / 2.0D * 7.0D * size + 1.0D);
/*     */                   
/* 388 */                   if (entity.func_70097_a(func_199591_b(), damage)) {
/* 389 */                     this.damagedEntities.add(entity);
/*     */                     
/* 391 */                     DAMAGED_ENTITIES.add(entity);
/*     */                   } 
/*     */                 } 
/*     */                 
/* 395 */                 double blastDamageReduction = power;
/*     */                 
/* 397 */                 if (entity instanceof LivingEntity) {
/* 398 */                   blastDamageReduction = ProtectionEnchantment.func_92092_a((LivingEntity)entity, power);
/*     */                 }
/*     */                 
/* 401 */                 if (this.canCauseKnockback) {
/* 402 */                   AbilityHelper.setDeltaMovement(entity, entity.func_213322_ci().func_72441_c(xDistance * blastDamageReduction, yDistance * blastDamageReduction, zDistance * blastDamageReduction));
/*     */                   
/* 404 */                   if (entity instanceof PlayerEntity) {
/* 405 */                     PlayerEntity playerEntity = (PlayerEntity)entity;
/*     */                     
/* 407 */                     if (!playerEntity.func_175149_v() && (!playerEntity.func_184812_l_() || !playerEntity.field_71075_bZ.field_75100_b)) {
/* 408 */                       this.playerKnockbackMap.put(playerEntity, new Vector3d(xDistance * power, yDistance * power, zDistance * power));
/*     */                     }
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/* 418 */     if (this.canProduceExplosionSound) {
/* 419 */       this.world.func_184148_a((PlayerEntity)null, this.explosionX, this.explosionY, this.explosionZ, SoundEvents.field_187539_bB, SoundCategory.BLOCKS, 3.0F + this.explosionSize, (1.0F + (this.world.field_73012_v.nextFloat() - this.world.field_73012_v.nextFloat()) * 0.2F) * 0.7F);
/* 420 */       this.world.func_184148_a((PlayerEntity)null, this.explosionX, this.explosionY, this.explosionZ, (SoundEvent)ModSounds.GENERIC_EXPLOSION_SHORT.get(), SoundCategory.BLOCKS, 3.0F + this.explosionSize, (1.0F + (this.world.field_73012_v.nextFloat() - this.world.field_73012_v.nextFloat()) * 0.2F) * 0.7F);
/*     */     } 
/*     */     
/* 423 */     if (hasSmokeParticles()) {
/* 424 */       this.particles.spawn(this.world, this.explosionX, this.explosionY, this.explosionZ, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 429 */     if (!this.world.field_72995_K && this.canDestroyBlocks && CommonConfig.INSTANCE.isAbilityGriefingEnabled()) {
/* 430 */       ObjectArrayList<Pair<ItemStack, BlockPos>> objectarraylist = new ObjectArrayList();
/*     */ 
/*     */ 
/*     */       
/* 434 */       for (BlockPos blockpos : this.affectedBlockPositions) {
/* 435 */         if (this.hasBlockLimit && this.explodedBlocks >= this.explodedBlocksLimit) {
/*     */           break;
/*     */         }
/*     */         
/* 439 */         BlockState blockstate = this.world.func_180495_p(blockpos);
/*     */         
/* 441 */         if (blockstate.func_185904_a() == Material.field_151586_h && !CommonConfig.INSTANCE.getDestroyWater()) {
/*     */           continue;
/*     */         }
/*     */         
/* 445 */         boolean blockIsKairoseki = KairosekiBlockProtectionRule.INSTANCE.isBanned(blockstate);
/* 446 */         boolean blockIsRestricted = RestrictedBlockProtectionRule.INSTANCE.isBanned(blockstate);
/* 447 */         boolean hardBlockRestriction = false;
/* 448 */         boolean inProtectedAreaFlag = ProtectedAreasData.get(this.world).isInsideRestrictedArea(blockpos.func_177958_n(), blockpos.func_177956_o(), blockpos.func_177952_p());
/* 449 */         boolean canGrief = AbilityHelper.canMobGrief(getExploder());
/*     */         
/* 451 */         boolean fallingProtection = true;
/*     */         
/* 453 */         if (this.protectOwnerFromFalling && this.exploder != null) {
/* 454 */           fallingProtection = (Math.sqrt(this.exploder.func_70092_e(blockpos.func_177958_n(), this.exploder.func_226278_cu_(), blockpos.func_177952_p())) > 1.5D);
/*     */         }
/*     */         
/* 457 */         if (!blockstate.func_196958_f() && !blockIsKairoseki && !blockIsRestricted && !hardBlockRestriction && fallingProtection && canGrief) {
/* 458 */           if (this.world instanceof ServerWorld && blockstate.canDropFromExplosion((IBlockReader)this.world, blockpos, this)) {
/* 459 */             if (this.canDropBlocksAfterExplosion) {
/* 460 */               TileEntity tileentity = blockstate.hasTileEntity() ? this.world.func_175625_s(blockpos) : null;
/* 461 */               LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld)this.world)).func_216023_a(this.world.field_73012_v).func_216015_a(LootParameters.field_237457_g_, Vector3d.func_237489_a_((Vector3i)blockpos)).func_216015_a(LootParameters.field_216289_i, ItemStack.field_190927_a).func_216021_b(LootParameters.field_216288_h, tileentity).func_216021_b(LootParameters.field_216281_a, this.exploder);
/* 462 */               lootcontext$builder.func_216015_a(LootParameters.field_216290_j, Float.valueOf(this.explosionSize));
/* 463 */               blockstate.func_215693_a(lootcontext$builder).forEach(p_229977_2_ -> func_229976_a_(objectarraylist, p_229977_2_, blockpos));
/*     */             } 
/*     */             
/* 466 */             if (this.addRemovedBlocksToList) {
/* 467 */               this.removedBlocks.add(new FallingBlockEntity(this.world, blockpos.func_177958_n(), blockpos.func_177956_o(), blockpos.func_177952_p(), blockstate));
/*     */             }
/*     */           } 
/*     */           
/* 471 */           this.onBlockDestroyedEvent.onBlockDestroyed(blockpos);
/*     */           
/* 473 */           blockstate.onBlockExploded(this.world, blockpos, this);
/*     */           
/* 475 */           this.explodedBlocks++;
/*     */         } 
/*     */         
/* 478 */         if (this.canStartFireAfterExplosion && this.world.func_180495_p(blockpos).func_196958_f() && this.world.func_180495_p(blockpos.func_177977_b()).func_200015_d((IBlockReader)this.world, blockpos.func_177977_b()) && this.random.nextInt(5) == 0 && !inProtectedAreaFlag) {
/* 479 */           this.world.func_175656_a(blockpos, AbstractFireBlock.func_235326_a_((IBlockReader)this.world, blockpos));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 484 */     if (this.exploder instanceof ServerPlayerEntity && StructuresHelper.isInsideShip((ServerWorld)this.world, new BlockPos(v3d))) {
/* 485 */       ModAdvancements.SUBTLE_TWEAKS.trigger((ServerPlayerEntity)this.exploder);
/*     */     }
/*     */   }
/*     */   
/*     */   private static void func_229976_a_(ObjectArrayList<Pair<ItemStack, BlockPos>> drops, ItemStack itemStack, BlockPos pos) {
/* 490 */     int i = drops.size();
/*     */     
/* 492 */     for (int j = 0; j < i; j++) {
/* 493 */       Pair<ItemStack, BlockPos> pair = (Pair<ItemStack, BlockPos>)drops.get(j);
/*     */       
/* 495 */       ItemStack itemstack = (ItemStack)pair.getFirst();
/*     */       
/* 497 */       if (ItemEntity.func_226532_a_(itemstack, itemStack)) {
/* 498 */         ItemStack itemstack1 = ItemEntity.func_226533_a_(itemstack, itemStack, 16);
/*     */         
/* 500 */         drops.set(j, Pair.of(itemstack1, pair.getSecond()));
/*     */         
/* 502 */         if (itemStack.func_190926_b()) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 508 */     drops.add(Pair.of(itemStack, pos));
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<PlayerEntity, Vector3d> func_77277_b() {
/* 513 */     return this.playerKnockbackMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<BlockPos> func_180343_e() {
/* 518 */     return this.affectedBlockPositions;
/*     */   }
/*     */   
/*     */   public static interface IOnBlockDestroyed extends Serializable {
/*     */     void onBlockDestroyed(BlockPos param1BlockPos);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\ExplosionAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */