/*      */ package xyz.pixelatedw.mineminenomi.entities.projectiles;
/*      */ import java.awt.Color;
/*      */ import java.io.Serializable;
/*      */ import java.lang.invoke.SerializedLambda;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import java.util.function.Predicate;
/*      */ import java.util.stream.Collectors;
/*      */ import javax.annotation.Nullable;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.BlockState;
/*      */ import net.minecraft.block.Blocks;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityType;
/*      */ import net.minecraft.entity.LivingEntity;
/*      */ import net.minecraft.entity.MobEntity;
/*      */ import net.minecraft.entity.projectile.ProjectileEntity;
/*      */ import net.minecraft.entity.projectile.ThrowableEntity;
/*      */ import net.minecraft.fluid.FluidState;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.CompoundNBT;
/*      */ import net.minecraft.network.IPacket;
/*      */ import net.minecraft.network.PacketBuffer;
/*      */ import net.minecraft.network.datasync.DataParameter;
/*      */ import net.minecraft.network.datasync.DataSerializers;
/*      */ import net.minecraft.network.datasync.EntityDataManager;
/*      */ import net.minecraft.potion.EffectInstance;
/*      */ import net.minecraft.tags.FluidTags;
/*      */ import net.minecraft.tags.ITag;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.Direction;
/*      */ import net.minecraft.util.math.AxisAlignedBB;
/*      */ import net.minecraft.util.math.BlockPos;
/*      */ import net.minecraft.util.math.BlockRayTraceResult;
/*      */ import net.minecraft.util.math.EntityRayTraceResult;
/*      */ import net.minecraft.util.math.MathHelper;
/*      */ import net.minecraft.util.math.RayTraceContext;
/*      */ import net.minecraft.util.math.RayTraceResult;
/*      */ import net.minecraft.util.math.vector.Vector3d;
/*      */ import net.minecraft.world.Explosion;
/*      */ import net.minecraft.world.IBlockReader;
/*      */ import net.minecraft.world.IWorld;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraftforge.common.MinecraftForge;
/*      */ import net.minecraftforge.entity.PartEntity;
/*      */ import net.minecraftforge.eventbus.api.Event;
/*      */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*      */ import net.minecraftforge.fml.network.NetworkHooks;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*      */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*      */ import xyz.pixelatedw.mineminenomi.api.damagesource.ModIndirectEntityDamageSource;
/*      */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*      */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*      */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*      */ import xyz.pixelatedw.mineminenomi.api.events.ProjectileBlockEvent;
/*      */ import xyz.pixelatedw.mineminenomi.api.events.ProjectileHitEvent;
/*      */ import xyz.pixelatedw.mineminenomi.api.events.ProjectileShootEvent;
/*      */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*      */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*      */ import xyz.pixelatedw.mineminenomi.api.protection.ProtectedArea;
/*      */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*      */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*      */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*      */ import xyz.pixelatedw.mineminenomi.data.world.ProtectedAreasData;
/*      */ import xyz.pixelatedw.mineminenomi.entities.LightningDischargeEntity;
/*      */ import xyz.pixelatedw.mineminenomi.entities.projectiles.bari.BariProjectiles;
/*      */ import xyz.pixelatedw.mineminenomi.events.FactionEvents;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*      */ import xyz.pixelatedw.mineminenomi.packets.server.entities.SUpdateProjBlockCollisionBox;
/*      */ import xyz.pixelatedw.mineminenomi.packets.server.entities.SUpdateProjEntityCollisionBox;
/*      */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*      */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*      */ 
/*      */ public class AbilityProjectileEntity extends ThrowableEntity implements IEntityAdditionalSpawnData {
/*   84 */   private int maxLife = 64;
/*   85 */   private int knockbackStrength = 0;
/*      */   
/*   87 */   private float damage = 0.1F;
/*   88 */   private float gravity = 1.0E-4F;
/*      */   
/*      */   private boolean canPassThroughBlocks = false;
/*      */   
/*      */   private boolean canPassThroughEntities = false;
/*      */   
/*      */   @Deprecated
/*      */   private boolean canGetStuckInGround = false;
/*      */   protected boolean stuckInGround = false;
/*      */   private boolean changeHurtTime = false;
/*   98 */   private float armorPiercing = 0.0F;
/*      */   
/*      */   private boolean unavoidable;
/*      */   private boolean canHurtThrower = false;
/*      */   private boolean isFake = false;
/*      */   private boolean hasVelocityRotation = true;
/*      */   private boolean canCollideWithEntities = true;
/*      */   private boolean canCollideWithBlocks = true;
/*  106 */   private int hurtTime = 10;
/*      */   protected boolean entityDamaged = false;
/*      */   protected boolean firstEntityImpact = true;
/*      */   private boolean applyOnlyOnce = true;
/*  110 */   private List<Integer> targets = new ArrayList<>();
/*  111 */   private int targetResetTime = 20;
/*      */   
/*  113 */   private int blocksAffectedLimit = 4096;
/*  114 */   private int blocksAffected = 0;
/*      */   
/*      */   private boolean removeOnBlockLimit = true;
/*      */   
/*      */   private boolean hasBlockDestructionLimit = true;
/*  119 */   private SourceHakiNature sourceHakiNature = SourceHakiNature.UNKNOWN;
/*  120 */   private SourceElement sourceElement = SourceElement.NONE;
/*  121 */   private ArrayList<SourceType> sourceTypes = new ArrayList<>();
/*      */   
/*  123 */   private AxisAlignedBB entityCollisionBox = getDefaultBoundingBox();
/*  124 */   private AxisAlignedBB blockCollisionBox = getDefaultBoundingBox();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final Predicate<Entity> entityPredicate;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final Predicate<Entity> collisionPredicate;
/*      */ 
/*      */ 
/*      */   
/*      */   private final Interval lightningDischargeInterval;
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   private AbilityCore<? extends IAbility> parent;
/*      */ 
/*      */ 
/*      */   
/*  147 */   private static final DataParameter<Integer> OWNER = EntityDataManager.func_187226_a(AbilityProjectileEntity.class, DataSerializers.field_187192_b);
/*      */   
/*  149 */   private static final DataParameter<Integer> LIFE = EntityDataManager.func_187226_a(AbilityProjectileEntity.class, DataSerializers.field_187192_b);
/*      */   
/*  151 */   private static final DataParameter<Boolean> IS_GLOWING = EntityDataManager.func_187226_a(AbilityProjectileEntity.class, DataSerializers.field_187198_h);
/*      */ 
/*      */   
/*      */   public IOnEntityImpact onEntityImpactEvent;
/*      */ 
/*      */   
/*      */   public IOnBlockImpact onBlockImpactEvent;
/*      */   
/*      */   public IOnTick onTickEvent;
/*      */   
/*      */   @Deprecated
/*      */   public IWithEffects withEffects;
/*      */   
/*      */   private ModDamageSource source;
/*      */   
/*      */   @Deprecated
/*      */   public ModDamageSource bypassingSource;
/*      */   
/*      */   private final ArrayList<Block> ignorableBlocks;
/*      */ 
/*      */   
/*      */   public AbilityProjectileEntity(EntityType type, World world) {
/*  173 */     super(type, world); this.entityPredicate = (target -> (target == this) ? false : ((target instanceof net.minecraft.entity.item.ExperienceOrbEntity || target instanceof net.minecraft.entity.item.ItemEntity) ? false : ((target instanceof LivingEntity && target.func_241845_aY() && target != getThrower() && ModEntityPredicates.getEnemyFactions(getThrower()).test(target)) ? ((LivingEntity)target).func_70685_l((Entity)this) : (!(target instanceof ProjectileEntity && ((ProjectileEntity)target).func_234616_v_() != null && ((ProjectileEntity)target).func_234616_v_() == getThrower()))))); this.collisionPredicate = ModEntityPredicates.IS_ALIVE_AND_SURVIVAL.and(e -> !(e instanceof xyz.pixelatedw.mineminenomi.entities.SphereEntity)).and(this.entityPredicate); this.lightningDischargeInterval = new Interval(10); this.onEntityImpactEvent = (hitEntity -> { if (this.firstEntityImpact)
/*      */           this.onBlockImpactEvent.onImpact(hitEntity.func_233580_cy_());  }); this.onBlockImpactEvent = (hit -> {  }); this.onTickEvent = (() -> { 
/*      */       }); this.withEffects = (() -> new EffectInstance[0]); this.ignorableBlocks = new ArrayList<>(Arrays.asList(new Block[] { 
/*      */             Blocks.field_196800_gd, Blocks.field_150349_c, Blocks.field_196804_gh, Blocks.field_203198_aQ, Blocks.field_203214_jx, Blocks.field_203215_jy, Blocks.field_203199_aR, Blocks.field_150395_bd, Blocks.field_150488_af, Blocks.field_196555_aI, 
/*  177 */             Blocks.field_196802_gf, (Block)ModBlocks.OPE.get() })); } public AbilityProjectileEntity(EntityType type, World world, double x, double y, double z) { super(type, x, y, z, world); this.entityPredicate = (target -> (target == this) ? false : ((target instanceof net.minecraft.entity.item.ExperienceOrbEntity || target instanceof net.minecraft.entity.item.ItemEntity) ? false : ((target instanceof LivingEntity && target.func_241845_aY() && target != getThrower() && ModEntityPredicates.getEnemyFactions(getThrower()).test(target)) ? ((LivingEntity)target).func_70685_l((Entity)this) : (!(target instanceof ProjectileEntity && ((ProjectileEntity)target).func_234616_v_() != null && ((ProjectileEntity)target).func_234616_v_() == getThrower()))))); this.collisionPredicate = ModEntityPredicates.IS_ALIVE_AND_SURVIVAL.and(e -> !(e instanceof xyz.pixelatedw.mineminenomi.entities.SphereEntity)).and(this.entityPredicate); this.lightningDischargeInterval = new Interval(10); this.onEntityImpactEvent = (hitEntity -> { if (this.firstEntityImpact)
/*      */           this.onBlockImpactEvent.onImpact(hitEntity.func_233580_cy_());  }); this.onBlockImpactEvent = (hit -> {  }); this.onTickEvent = (() -> { 
/*      */       }); this.withEffects = (() -> new EffectInstance[0]); this.ignorableBlocks = new ArrayList<>(Arrays.asList(new Block[] { 
/*      */             Blocks.field_196800_gd, Blocks.field_150349_c, Blocks.field_196804_gh, Blocks.field_203198_aQ, Blocks.field_203214_jx, Blocks.field_203215_jy, Blocks.field_203199_aR, Blocks.field_150395_bd, Blocks.field_150488_af, Blocks.field_196555_aI, 
/*  181 */             Blocks.field_196802_gf, (Block)ModBlocks.OPE.get() })); } public AbilityProjectileEntity(EntityType type, World world, LivingEntity thrower) { this(type, world, thrower, (AbilityCore<? extends IAbility>)null); }
/*      */ 
/*      */   
/*      */   public AbilityProjectileEntity(EntityType type, World world, LivingEntity thrower, @Nullable Ability parent) {
/*  185 */     this(type, world, thrower, (parent != null) ? parent.getCore() : (AbilityCore<? extends IAbility>)null);
/*      */   }
/*      */   
/*      */   public AbilityProjectileEntity(EntityType type, World world, LivingEntity thrower, @Nullable AbilityCore<? extends IAbility> parent) {
/*  189 */     this(type, world, thrower, parent, (parent != null) ? parent.getSourceElement() : null, (parent != null) ? parent.getSourceHakiNature() : null, (parent != null) ? parent.getSourceTypes() : null);
/*      */   }
/*      */   
/*      */   public AbilityProjectileEntity(EntityType type, World world, LivingEntity thrower, @Nullable AbilityCore<? extends IAbility> parent, SourceElement sourceElement, SourceHakiNature sourceHakiNature, ArrayList<SourceType> sourceTypes) {
/*  193 */     super(type, thrower, world); this.entityPredicate = (target -> (target == this) ? false : ((target instanceof net.minecraft.entity.item.ExperienceOrbEntity || target instanceof net.minecraft.entity.item.ItemEntity) ? false : ((target instanceof LivingEntity && target.func_241845_aY() && target != getThrower() && ModEntityPredicates.getEnemyFactions(getThrower()).test(target)) ? ((LivingEntity)target).func_70685_l((Entity)this) : (!(target instanceof ProjectileEntity && ((ProjectileEntity)target).func_234616_v_() != null && ((ProjectileEntity)target).func_234616_v_() == getThrower()))))); this.collisionPredicate = ModEntityPredicates.IS_ALIVE_AND_SURVIVAL.and(e -> !(e instanceof xyz.pixelatedw.mineminenomi.entities.SphereEntity)).and(this.entityPredicate); this.lightningDischargeInterval = new Interval(10); this.onEntityImpactEvent = (hitEntity -> { if (this.firstEntityImpact) this.onBlockImpactEvent.onImpact(hitEntity.func_233580_cy_());  }); this.onBlockImpactEvent = (hit -> {  }); this.onTickEvent = (() -> {  }); this.withEffects = (() -> new EffectInstance[0]); this.ignorableBlocks = new ArrayList<>(Arrays.asList(new Block[] { 
/*      */             Blocks.field_196800_gd, Blocks.field_150349_c, Blocks.field_196804_gh, Blocks.field_203198_aQ, Blocks.field_203214_jx, Blocks.field_203215_jy, Blocks.field_203199_aR, Blocks.field_150395_bd, Blocks.field_150488_af, Blocks.field_196555_aI, 
/*  195 */             Blocks.field_196802_gf, (Block)ModBlocks.OPE.get() })); this.maxLife = getLife();
/*  196 */     this.damage = 0.1F;
/*      */     
/*  198 */     setThrower(thrower);
/*      */     
/*  200 */     this.parent = parent;
/*      */     
/*  202 */     if (parent != null) {
/*  203 */       this.sourceElement = sourceElement;
/*  204 */       this.sourceHakiNature = sourceHakiNature;
/*  205 */       this.sourceTypes = sourceTypes;
/*      */       
/*  207 */       this.source = (new AbilityDamageSource("ability_projectile", this, parent)).setSourceElement(this.sourceElement).setSourceTypes(this.sourceTypes).setHakiNature(this.sourceHakiNature).setProjectile();
/*      */     } else {
/*  209 */       this.source = (new ModIndirectEntityDamageSource("ability_projectile", (Entity)this, (Entity)thrower)).setProjectile();
/*      */     } 
/*      */     
/*  212 */     this.bypassingSource = (new ModIndirectEntityDamageSource("ability_projectile", (Entity)this, (Entity)thrower)).setProjectile().setPiercing(1.0F);
/*      */   }
/*      */   
/*      */   public AbilityProjectileEntity(EntityType type, World world, LivingEntity thrower, SourceElement sourceElement, SourceHakiNature sourceHakiNature, SourceType... sourceTypes) {
/*  216 */     super(type, thrower, world); this.entityPredicate = (target -> (target == this) ? false : ((target instanceof net.minecraft.entity.item.ExperienceOrbEntity || target instanceof net.minecraft.entity.item.ItemEntity) ? false : ((target instanceof LivingEntity && target.func_241845_aY() && target != getThrower() && ModEntityPredicates.getEnemyFactions(getThrower()).test(target)) ? ((LivingEntity)target).func_70685_l((Entity)this) : (!(target instanceof ProjectileEntity && ((ProjectileEntity)target).func_234616_v_() != null && ((ProjectileEntity)target).func_234616_v_() == getThrower()))))); this.collisionPredicate = ModEntityPredicates.IS_ALIVE_AND_SURVIVAL.and(e -> !(e instanceof xyz.pixelatedw.mineminenomi.entities.SphereEntity)).and(this.entityPredicate); this.lightningDischargeInterval = new Interval(10); this.onEntityImpactEvent = (hitEntity -> { if (this.firstEntityImpact) this.onBlockImpactEvent.onImpact(hitEntity.func_233580_cy_());  }); this.onBlockImpactEvent = (hit -> {  }); this.onTickEvent = (() -> {  }); this.withEffects = (() -> new EffectInstance[0]); this.ignorableBlocks = new ArrayList<>(Arrays.asList(new Block[] { 
/*      */             Blocks.field_196800_gd, Blocks.field_150349_c, Blocks.field_196804_gh, Blocks.field_203198_aQ, Blocks.field_203214_jx, Blocks.field_203215_jy, Blocks.field_203199_aR, Blocks.field_150395_bd, Blocks.field_150488_af, Blocks.field_196555_aI, 
/*  218 */             Blocks.field_196802_gf, (Block)ModBlocks.OPE.get() })); this.maxLife = getLife();
/*  219 */     this.damage = 0.1F;
/*      */     
/*  221 */     setThrower(thrower);
/*      */     
/*  223 */     this.sourceHakiNature = sourceHakiNature;
/*  224 */     this.sourceTypes = new ArrayList<>(Arrays.asList(sourceTypes));
/*  225 */     this.sourceElement = sourceElement;
/*  226 */     this.source = (new ModIndirectEntityDamageSource("ability_projectile", (Entity)this, (Entity)thrower)).setSourceElement(this.sourceElement).setSourceTypes(this.sourceTypes).setHakiNature(this.sourceHakiNature).setProjectile();
/*      */     
/*  228 */     this.bypassingSource = (new ModIndirectEntityDamageSource("ability_projectile", (Entity)this, (Entity)thrower)).setProjectile().setPiercing(1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private AxisAlignedBB getDefaultBoundingBox() {
/*  238 */     AxisAlignedBB def = super.func_174813_aQ();
/*      */     
/*  240 */     if (def != null) {
/*  241 */       return def;
/*      */     }
/*      */     
/*  244 */     return getDimensionsForge(Pose.STANDING).func_242286_a(func_213303_ch());
/*      */   }
/*      */ 
/*      */   
/*      */   public AxisAlignedBB func_174813_aQ() {
/*  249 */     if (this.entityCollisionBox == null) {
/*  250 */       this.entityCollisionBox = getDefaultBoundingBox();
/*      */     }
/*      */     
/*  253 */     return new AxisAlignedBB(
/*  254 */         func_226277_ct_() - this.entityCollisionBox.func_216364_b() / 2.0D, 
/*  255 */         func_226278_cu_() - this.entityCollisionBox.func_216360_c() / 2.0D, 
/*  256 */         func_226281_cx_() - this.entityCollisionBox.func_216362_d() / 2.0D, 
/*  257 */         func_226277_ct_() + this.entityCollisionBox.func_216364_b() / 2.0D, 
/*  258 */         func_226278_cu_() + this.entityCollisionBox.func_216360_c() / 2.0D, 
/*  259 */         func_226281_cx_() + this.entityCollisionBox.func_216362_d() / 2.0D);
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_234617_x_() {
/*  264 */     if (this.hasVelocityRotation) {
/*  265 */       super.func_234617_x_();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_70071_h_() {
/*  271 */     super.func_70071_h_();
/*      */     
/*  273 */     if (!this.field_70170_p.field_72995_K) {
/*  274 */       tickDespawn();
/*      */       
/*  276 */       ProtectedArea area = ProtectedAreasData.get(this.field_70170_p).getProtectedArea((int)func_226277_ct_(), (int)func_226278_cu_(), (int)func_226281_cx_());
/*      */       
/*  278 */       if (area != null && !area.canUseAbility(this.parent)) {
/*  279 */         func_70106_y();
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  284 */       if (!this.field_70170_p.isAreaLoaded(func_233580_cy_(), 1)) {
/*  285 */         func_70106_y();
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  290 */       checkEntityCollision();
/*  291 */       checkBlockCollision();
/*      */       
/*  293 */       if (this.field_70173_aa % getTargetResetTime() == 0) {
/*  294 */         clearTargets();
/*      */       }
/*      */     } 
/*      */     
/*  298 */     this.onTickEvent.onTick();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkEntityCollision() {
/*  304 */     Set<Entity> parentEntities = (Set<Entity>)this.field_70170_p.getPartEntities().stream().map(PartEntity::getParent).collect(Collectors.toSet());
/*      */     
/*  306 */     AxisAlignedBB boundingBox = getEntityCollisionBox();
/*      */     
/*  308 */     Vector3d velocity = func_213322_ci();
/*  309 */     Vector3d startPos = func_213303_ch();
/*  310 */     Vector3d endPos = startPos.func_178787_e(velocity);
/*      */     
/*  312 */     double stepSize = Math.max(boundingBox.func_216364_b(), Math.max(boundingBox.func_216360_c(), boundingBox.func_216362_d()));
/*      */     
/*  314 */     if (stepSize == 0.0D) {
/*      */       return;
/*      */     }
/*      */     
/*  318 */     double steps = (velocity.func_72433_c() + 1.0D) / stepSize;
/*      */     
/*  320 */     Vector3d stepDirection = velocity.func_72432_b().func_186678_a(stepSize);
/*      */     
/*  322 */     Vector3d currentPos = startPos;
/*      */     
/*  324 */     for (int i = 0; i < steps; i++) {
/*  325 */       Vector3d nextPos = currentPos.func_178787_e(stepDirection);
/*      */       
/*  327 */       if (Math.abs(currentPos.field_72450_a - nextPos.field_72450_a) > Math.abs(currentPos.field_72450_a - endPos.field_72450_a)) {
/*  328 */         nextPos = new Vector3d(endPos.field_72450_a, nextPos.field_72448_b, nextPos.field_72449_c);
/*      */       }
/*      */       
/*  331 */       if (Math.abs(currentPos.field_72448_b - nextPos.field_72448_b) > Math.abs(currentPos.field_72448_b - endPos.field_72448_b)) {
/*  332 */         nextPos = new Vector3d(nextPos.field_72450_a, endPos.field_72448_b, nextPos.field_72449_c);
/*      */       }
/*      */       
/*  335 */       if (Math.abs(currentPos.field_72449_c - nextPos.field_72449_c) > Math.abs(currentPos.field_72449_c - endPos.field_72449_c)) {
/*  336 */         nextPos = new Vector3d(nextPos.field_72450_a, nextPos.field_72448_b, endPos.field_72449_c);
/*      */       }
/*      */       
/*  339 */       Vector3d from = currentPos.func_178786_a(boundingBox.func_216364_b() / 2.0D, boundingBox.func_216360_c() / 2.0D, boundingBox.func_216362_d() / 2.0D);
/*  340 */       Vector3d to = nextPos.func_72441_c(boundingBox.func_216364_b() / 2.0D, boundingBox.func_216360_c() / 2.0D, boundingBox.func_216362_d() / 2.0D);
/*      */ 
/*      */       
/*  343 */       if (this.firstEntityImpact || this.canPassThroughEntities) {
/*  344 */         AxisAlignedBB stepBoundingBox = new AxisAlignedBB(from, to);
/*      */         
/*  346 */         List<Entity> entityList = WyHelper.getNearbyEntities((IWorld)this.field_70170_p, stepBoundingBox, this.collisionPredicate, new Class[] { Entity.class });
/*      */         
/*  348 */         for (Entity target : entityList) {
/*  349 */           if (parentEntities.contains(target)) {
/*      */             continue;
/*      */           }
/*      */           
/*  353 */           onModHit((RayTraceResult)new EntityRayTraceResult(target));
/*      */         } 
/*      */       } 
/*      */       
/*  357 */       currentPos = nextPos;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void checkBlockCollision() {
/*  362 */     AxisAlignedBB boundingBox = getBlockCollisionBox();
/*      */     
/*  364 */     Vector3d velocity = func_213322_ci();
/*  365 */     Vector3d startPos = func_213303_ch();
/*  366 */     Vector3d endPos = startPos.func_178787_e(velocity);
/*      */     
/*  368 */     double stepSize = Math.max(boundingBox.func_216364_b(), Math.max(boundingBox.func_216360_c(), boundingBox.func_216362_d()));
/*      */     
/*  370 */     if (stepSize == 0.0D) {
/*      */       return;
/*      */     }
/*      */     
/*  374 */     double steps = (velocity.func_72433_c() + 1.0D) / stepSize;
/*      */     
/*  376 */     Vector3d stepDirection = velocity.func_72432_b().func_186678_a(stepSize);
/*      */     
/*  378 */     Vector3d currentPos = startPos;
/*      */     
/*  380 */     for (int i = 0; i < steps; i++) {
/*  381 */       Vector3d nextPos = currentPos.func_178787_e(stepDirection);
/*      */       
/*  383 */       if (Math.abs(currentPos.field_72450_a - nextPos.field_72450_a) > Math.abs(currentPos.field_72450_a - endPos.field_72450_a)) {
/*  384 */         nextPos = new Vector3d(endPos.field_72450_a, nextPos.field_72448_b, nextPos.field_72449_c);
/*      */       }
/*      */       
/*  387 */       if (Math.abs(currentPos.field_72448_b - nextPos.field_72448_b) > Math.abs(currentPos.field_72448_b - endPos.field_72448_b)) {
/*  388 */         nextPos = new Vector3d(nextPos.field_72450_a, endPos.field_72448_b, nextPos.field_72449_c);
/*      */       }
/*      */       
/*  391 */       if (Math.abs(currentPos.field_72449_c - nextPos.field_72449_c) > Math.abs(currentPos.field_72449_c - endPos.field_72449_c)) {
/*  392 */         nextPos = new Vector3d(nextPos.field_72450_a, nextPos.field_72448_b, endPos.field_72449_c);
/*      */       }
/*      */       
/*  395 */       Vector3d from = currentPos.func_178786_a(boundingBox.func_216364_b() / 2.0D, boundingBox.func_216360_c() / 2.0D, boundingBox.func_216362_d() / 2.0D);
/*  396 */       Vector3d to = nextPos.func_72441_c(boundingBox.func_216364_b() / 2.0D, boundingBox.func_216360_c() / 2.0D, boundingBox.func_216362_d() / 2.0D);
/*      */       
/*  398 */       BlockRayTraceResult result = this.field_70170_p.func_217299_a(new RayTraceContext(from, to, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, (Entity)this));
/*      */       
/*  400 */       if (result.func_216346_c() == RayTraceResult.Type.BLOCK) {
/*  401 */         nextPos = result.func_216347_e();
/*      */         
/*  403 */         onModHit((RayTraceResult)result);
/*      */       } 
/*      */       
/*  406 */       currentPos = nextPos;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_234612_a_(Entity thrower, float pX, float pY, float pZ, float velocity, float inaccuracy) {
/*  412 */     inaccuracy = Math.max(inaccuracy, 0.0F);
/*      */     
/*  414 */     ProjectileShootEvent event = new ProjectileShootEvent(this, velocity, inaccuracy);
/*  415 */     if (MinecraftForge.EVENT_BUS.post((Event)event)) {
/*      */       return;
/*      */     }
/*      */     
/*  419 */     clearTargets();
/*      */     
/*  421 */     if (thrower instanceof MobEntity && ((MobEntity)thrower).func_70638_az() != null) {
/*  422 */       LivingEntity target = ((MobEntity)thrower).func_70638_az();
/*  423 */       float x = (float)(target.func_226277_ct_() - thrower.func_226277_ct_());
/*  424 */       float z = (float)(target.func_226281_cx_() - thrower.func_226281_cx_());
/*  425 */       float angle = (float)Math.toDegrees(Math.atan2(x, z));
/*  426 */       pY = -angle;
/*      */     } 
/*      */     
/*  429 */     float f = -MathHelper.func_76126_a(pY * 0.017453292F) * MathHelper.func_76134_b(pX * 0.017453292F);
/*  430 */     float f1 = -MathHelper.func_76126_a((pX + pZ) * 0.017453292F);
/*  431 */     float f2 = MathHelper.func_76134_b(pY * 0.017453292F) * MathHelper.func_76134_b(pX * 0.017453292F);
/*  432 */     func_70186_c(f, f1, f2, velocity, inaccuracy);
/*      */   }
/*      */ 
/*      */   
/*      */   public void shootFromRotation(float pX, float pY, float pZ, float velocity, float inaccuracy) {
/*  437 */     ProjectileShootEvent event = new ProjectileShootEvent(this, velocity, inaccuracy);
/*  438 */     if (MinecraftForge.EVENT_BUS.post((Event)event))
/*      */       return; 
/*  440 */     clearTargets();
/*  441 */     float f = -MathHelper.func_76126_a(pY * 0.017453292F) * MathHelper.func_76134_b(pX * 0.017453292F);
/*  442 */     float f1 = -MathHelper.func_76126_a((pX + pZ) * 0.017453292F);
/*  443 */     float f2 = MathHelper.func_76134_b(pY * 0.017453292F) * MathHelper.func_76134_b(pX * 0.017453292F);
/*  444 */     func_70186_c(f, f1, f2, velocity, inaccuracy);
/*  445 */     AbilityHelper.setDeltaMovement((Entity)this, func_213322_ci());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void shoot(Entity thrower, float yRotIn, float xRotIn, float pitchOffset, float velocity, float inaccuracy) {
/*  454 */     func_234612_a_(thrower, yRotIn, xRotIn, pitchOffset, velocity, inaccuracy);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_233566_aG_() {
/*  459 */     if (func_70090_H()) {
/*  460 */       func_71061_d_();
/*      */     }
/*  462 */     return false;
/*      */   }
/*      */   
/*      */   public void tickDespawn() {
/*  466 */     if (!this.field_70170_p.field_72995_K) {
/*  467 */       if (getLife() <= 0) {
/*  468 */         setLife(getMaxLife());
/*  469 */         func_70106_y();
/*      */         
/*      */         return;
/*      */       } 
/*  473 */       setLife(getLife() - 1);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void onModHit(RayTraceResult hit) {
/*  479 */     if (!func_70089_S()) {
/*      */       return;
/*      */     }
/*      */     
/*  483 */     if (this.field_70170_p.field_72995_K) {
/*      */       return;
/*      */     }
/*      */     
/*  487 */     FluidState fluidState = null;
/*      */     
/*  489 */     if (hit.func_216346_c() == RayTraceResult.Type.ENTITY && this.canCollideWithEntities) {
/*  490 */       EntityRayTraceResult entityHit = (EntityRayTraceResult)hit;
/*      */       
/*  492 */       Entity owner = func_234616_v_();
/*      */       
/*  494 */       if (owner != null && owner instanceof LivingEntity) {
/*  495 */         LivingEntity livingOwner = (LivingEntity)owner;
/*  496 */         if (entityHit.func_216348_a() instanceof PartEntity) {
/*  497 */           PartEntity<? extends Entity> hitEntity = (PartEntity<? extends Entity>)entityHit.func_216348_a();
/*      */           
/*  499 */           if (hitEntity.getParent() == getThrower() && !this.canHurtThrower) {
/*      */             return;
/*      */           }
/*      */           
/*  503 */           ProjectileHitEvent event = new ProjectileHitEvent(this, hit);
/*      */           
/*  505 */           if (MinecraftForge.EVENT_BUS.post((Event)event)) {
/*      */             return;
/*      */           }
/*      */           
/*  509 */           if (!this.entityDamaged && !this.targets.contains(Integer.valueOf(hitEntity.getEntity().func_145782_y())) && hitEntity.getEntity().func_70089_S()) {
/*  510 */             if (this.source == null) {
/*  511 */               this.source = (new ModIndirectEntityDamageSource("ability_projectile", (Entity)this, (Entity)livingOwner)).setProjectile();
/*      */             }
/*      */             
/*  514 */             if (hitEntity.getParent() instanceof LivingEntity) {
/*  515 */               LivingEntity parent = (LivingEntity)hitEntity.getParent();
/*      */               
/*  517 */               if (!canBlockDamageSource((DamageSource)this.source, parent)) {
/*  518 */                 if (this.armorPiercing > 0.0F) {
/*  519 */                   this.entityDamaged = hitEntity.func_70097_a((DamageSource)this.source, this.damage);
/*      */                 }
/*  521 */                 else if (this.damage == 0.0F && this.isFake) {
/*  522 */                   this.entityDamaged = true;
/*      */                 } else {
/*  524 */                   this.entityDamaged = hitEntity.func_70097_a((DamageSource)this.source, this.damage);
/*      */                 } 
/*      */               } else {
/*      */                 
/*  528 */                 ItemStack stack = parent.func_184586_b(parent.func_184600_cs());
/*      */                 
/*  530 */                 if (stack.func_77973_b() instanceof net.minecraft.item.ShieldItem) {
/*  531 */                   stack.func_222118_a((int)(getDamage() + 17.0F), parent, entity -> entity.func_213334_d(parent.func_184600_cs()));
/*      */                 }
/*      */               } 
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  539 */           if (this.entityDamaged) {
/*  540 */             if (func_70027_ad()) {
/*  541 */               hitEntity.func_70015_d(5);
/*      */             }
/*      */             
/*  544 */             if (hitEntity.getParent() instanceof LivingEntity) {
/*  545 */               LivingEntity parent = (LivingEntity)hitEntity.getParent();
/*      */               
/*  547 */               triggerEffects(parent);
/*      */               
/*  549 */               this.onEntityImpactEvent.onImpact(parent);
/*      */               
/*  551 */               this.firstEntityImpact = false;
/*      */               
/*  553 */               if (this.changeHurtTime) {
/*  554 */                 IEntityStats entityStatsProps = EntityStatsCapability.get(parent);
/*      */                 
/*  556 */                 entityStatsProps.setInvulnerableTime(this.hurtTime);
/*      */                 
/*  558 */                 hitEntity.field_70172_ad = 0;
/*      */               } 
/*      */             } 
/*      */             
/*  562 */             if (this.knockbackStrength > 0) {
/*  563 */               Vector3d v3d = func_213322_ci().func_216372_d(1.0D, 0.0D, 1.0D).func_72432_b().func_186678_a(this.knockbackStrength * 0.6D);
/*      */               
/*  565 */               if (v3d.func_189985_c() > 0.0D) {
/*  566 */                 hitEntity.func_70024_g(v3d.field_72450_a, 0.1D, v3d.field_72449_c);
/*      */               }
/*      */             } 
/*      */             
/*  570 */             if (!this.canPassThroughEntities) {
/*  571 */               func_70106_y();
/*      */               
/*      */               return;
/*      */             } 
/*  575 */             this.targets.add(Integer.valueOf(entityHit.func_216348_a().func_145782_y()));
/*  576 */             this.entityDamaged = false;
/*      */           }
/*      */         
/*  579 */         } else if (entityHit.func_216348_a() instanceof LivingEntity) {
/*  580 */           LivingEntity hitEntity = (LivingEntity)entityHit.func_216348_a();
/*      */           
/*  582 */           if (hitEntity == getThrower() && !this.canHurtThrower) {
/*      */             return;
/*      */           }
/*      */           
/*  586 */           ProjectileHitEvent event = new ProjectileHitEvent(this, hit);
/*      */           
/*  588 */           if (MinecraftForge.EVENT_BUS.post((Event)event)) {
/*      */             return;
/*      */           }
/*      */           
/*  592 */           if (this.damage < 0.0F) {
/*  593 */             func_70106_y();
/*      */             
/*      */             return;
/*      */           } 
/*  597 */           if (!this.entityDamaged && !this.targets.contains(Integer.valueOf(hitEntity.getEntity().func_145782_y())) && hitEntity.getEntity().func_70089_S()) {
/*  598 */             if (this.source == null) {
/*  599 */               this.source = (new ModIndirectEntityDamageSource("ability_projectile", (Entity)this, (Entity)livingOwner)).setProjectile();
/*      */             }
/*      */             
/*  602 */             if (!canBlockDamageSource((DamageSource)this.source, hitEntity)) {
/*  603 */               float damage = (float)(this.damage * EntityStatsCapability.get(livingOwner).getDamageMultiplier());
/*  604 */               if (this.armorPiercing > 0.0F) {
/*  605 */                 this.entityDamaged = hitEntity.func_70097_a((DamageSource)this.source, damage);
/*      */               }
/*  607 */               else if (damage == 0.0F && this.isFake) {
/*  608 */                 this.entityDamaged = true;
/*      */               } else {
/*  610 */                 this.entityDamaged = hitEntity.func_70097_a((DamageSource)this.source, damage);
/*      */               } 
/*      */             } else {
/*      */               
/*  614 */               ItemStack stack = hitEntity.func_184586_b(hitEntity.func_184600_cs());
/*      */               
/*  616 */               if (stack.func_77973_b() instanceof net.minecraft.item.ShieldItem) {
/*  617 */                 stack.func_222118_a((int)(getDamage() + 17.0F), hitEntity, entity -> entity.func_213334_d(hitEntity.func_184600_cs()));
/*      */               }
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  624 */           if (this.entityDamaged) {
/*  625 */             if (func_70027_ad()) {
/*  626 */               hitEntity.func_70015_d(5);
/*      */             }
/*      */             
/*  629 */             triggerEffects(hitEntity);
/*      */             
/*  631 */             this.onEntityImpactEvent.onImpact(hitEntity);
/*      */             
/*  633 */             this.firstEntityImpact = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  652 */             if (this.knockbackStrength > 0) {
/*  653 */               Vector3d v3d = func_213322_ci().func_216372_d(1.0D, 0.0D, 1.0D).func_72432_b().func_186678_a(this.knockbackStrength * 0.6D);
/*      */               
/*  655 */               if (v3d.func_189985_c() > 0.0D) {
/*  656 */                 hitEntity.func_70024_g(v3d.field_72450_a, 0.1D, v3d.field_72449_c);
/*      */               }
/*      */             } 
/*      */             
/*  660 */             if (this.changeHurtTime) {
/*  661 */               IEntityStats entityStatsProps = EntityStatsCapability.get(hitEntity);
/*      */               
/*  663 */               entityStatsProps.setInvulnerableTime(this.hurtTime);
/*      */               
/*  665 */               hitEntity.field_70172_ad = 0;
/*      */             } 
/*      */             
/*  668 */             if (!this.canPassThroughEntities) {
/*  669 */               func_70106_y();
/*      */               
/*      */               return;
/*      */             } 
/*  673 */             this.targets.add(Integer.valueOf(entityHit.func_216348_a().func_145782_y()));
/*  674 */             this.entityDamaged = false;
/*      */           }
/*      */         
/*  677 */         } else if (entityHit.func_216348_a() instanceof AbilityProjectileEntity) {
/*  678 */           AbilityProjectileEntity entity = (AbilityProjectileEntity)entityHit.func_216348_a();
/*      */           
/*  680 */           onProjectileCollision(this, entity);
/*      */         } else {
/*      */           
/*  683 */           Entity hitEntity = entityHit.func_216348_a();
/*      */           
/*  685 */           ProjectileHitEvent event = new ProjectileHitEvent(this, hit);
/*      */           
/*  687 */           if (MinecraftForge.EVENT_BUS.post((Event)event)) {
/*      */             return;
/*      */           }
/*      */           
/*  691 */           if (this.damage < 0.0F) {
/*  692 */             func_70106_y();
/*      */             
/*      */             return;
/*      */           } 
/*  696 */           this.entityDamaged = hitEntity.func_70097_a((DamageSource)this.source, this.damage);
/*      */           
/*  698 */           if (this.entityDamaged) {
/*  699 */             if (func_70027_ad()) {
/*  700 */               hitEntity.func_70015_d(5);
/*      */             }
/*      */             
/*  703 */             this.firstEntityImpact = false;
/*      */             
/*  705 */             if (this.knockbackStrength > 0) {
/*  706 */               Vector3d v3d = func_213322_ci().func_216372_d(1.0D, 0.0D, 1.0D).func_72432_b().func_186678_a(this.knockbackStrength * 0.6D);
/*      */               
/*  708 */               if (v3d.func_189985_c() > 0.0D) {
/*  709 */                 hitEntity.func_70024_g(v3d.field_72450_a, 0.1D, v3d.field_72449_c);
/*      */               }
/*      */             } 
/*      */             
/*  713 */             if (!this.canPassThroughEntities) {
/*  714 */               func_70106_y();
/*      */               
/*      */               return;
/*      */             } 
/*  718 */             this.targets.add(Integer.valueOf(entityHit.func_216348_a().func_145782_y()));
/*  719 */             this.entityDamaged = false;
/*      */           }
/*      */         
/*      */         } 
/*      */       } 
/*  724 */     } else if (hit.func_216346_c() == RayTraceResult.Type.BLOCK && this.canCollideWithBlocks) {
/*  725 */       if (this.hasBlockDestructionLimit && this.blocksAffected >= this.blocksAffectedLimit) {
/*  726 */         if (this.removeOnBlockLimit) {
/*  727 */           func_70106_y();
/*      */         }
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  733 */       BlockRayTraceResult blockHit = (BlockRayTraceResult)hit;
/*      */       
/*  735 */       fluidState = this.field_70170_p.func_204610_c(blockHit.func_216350_a());
/*      */       
/*  737 */       if (fluidState != null && fluidState.func_206884_a((ITag)FluidTags.field_206959_a) && getParent() != null && getParent().getCategory() == AbilityCategory.DEVIL_FRUITS) {
/*  738 */         func_70106_y();
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  743 */       ProjectileHitEvent event = new ProjectileHitEvent(this, hit);
/*      */       
/*  745 */       if (MinecraftForge.EVENT_BUS.post((Event)event)) {
/*      */         return;
/*      */       }
/*      */       
/*  749 */       BlockState state = this.field_70170_p.func_180495_p(blockHit.func_216350_a());
/*  750 */       if (blockHit.func_216354_b() == null) {
/*  751 */         Direction ownerDirection = (getThrower() != null && getThrower().func_70089_S()) ? getThrower().func_174811_aO() : Direction.UP;
/*  752 */         blockHit = blockHit.func_216351_a(ownerDirection);
/*      */       } 
/*      */       
/*  755 */       if (blockHit.func_216354_b() != null) {
/*  756 */         state.func_215690_a(this.field_70170_p, state, blockHit, (ProjectileEntity)this);
/*      */       }
/*      */       
/*  759 */       boolean isVanillaBarrier = (state.func_177230_c() == Blocks.field_180401_cv && !WyHelper.isInChallengeDimension(this.field_70170_p));
/*  760 */       if (!passedThroughIgnorableBlock(blockHit.func_216350_a())) {
/*  761 */         boolean isBariProj = (state.func_177230_c() == ModBlocks.BARRIER.get() && func_200600_R() != BariProjectiles.BARRIERBILITY_STAIRS.get());
/*      */         
/*  763 */         if (isVanillaBarrier || isBariProj || state.func_177230_c().func_203417_a((ITag)ModTags.Blocks.KAIROSEKI)) {
/*  764 */           this.onBlockImpactEvent.onImpact(blockHit.func_216350_a());
/*  765 */           func_70106_y();
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/*  770 */         boolean passThroughBarrier = (state.func_177230_c() == Blocks.field_180401_cv && WyHelper.isInChallengeDimension(this.field_70170_p));
/*  771 */         if (!passThroughBarrier && !this.canPassThroughBlocks) {
/*  772 */           this.onBlockImpactEvent.onImpact(blockHit.func_216350_a());
/*      */           
/*  774 */           boolean isAirBlock = this.field_70170_p.func_175623_d(blockHit.func_216350_a());
/*  775 */           if (!isAirBlock) {
/*  776 */             func_70106_y();
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  784 */         this.blocksAffected = (int)(this.blocksAffected + state.func_185887_b((IBlockReader)this.field_70170_p, blockHit.func_216350_a()));
/*      */       } 
/*      */     } 
/*      */     
/*  788 */     if (getThrower() != null) {
/*  789 */       boolean highlightCheck = (isAffectedByHaki() && HakiHelper.isProjectileHaoshokuInfused((ProjectileEntity)this));
/*      */       
/*  791 */       if (highlightCheck && (fluidState == null || fluidState.func_206888_e()) && this.lightningDischargeInterval.canTick()) {
/*  792 */         Color clientRGB = WyHelper.intToRGB(HakiHelper.getHaoshokuColour(getThrower()), 50);
/*      */         
/*  794 */         LightningDischargeEntity discharge = new LightningDischargeEntity((Entity)this, func_226277_ct_(), func_226278_cu_() + 1.5D, func_226281_cx_(), this.field_70177_z, this.field_70125_A);
/*      */         
/*  796 */         discharge.setAliveTicks(15);
/*  797 */         discharge.setLightningLength(6.0F);
/*  798 */         discharge.setColor(new Color(0, 0, 0, 100));
/*  799 */         discharge.setOutlineColor(clientRGB);
/*  800 */         discharge.setRenderTransparent();
/*  801 */         discharge.setDetails(4);
/*  802 */         discharge.setDensity(4);
/*  803 */         discharge.setSize(getDamage() / 10.0F);
/*  804 */         discharge.setSkipSegments(1);
/*      */         
/*  806 */         (getThrower()).field_70170_p.func_217376_c((Entity)discharge);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void onProjectileCollision(AbilityProjectileEntity owner, AbilityProjectileEntity target) {
/*  812 */     if (!(owner.func_234616_v_() instanceof LivingEntity) || !(target.func_234616_v_() instanceof LivingEntity)) {
/*      */       return;
/*      */     }
/*      */     
/*  816 */     float ownerDamage = FactionEvents.applyDamageBonus((LivingEntity)owner.func_234616_v_(), owner.getDamageSource(), owner.getDamage());
/*  817 */     float targetDamage = FactionEvents.applyDamageBonus((LivingEntity)target.func_234616_v_(), target.getDamageSource(), target.getDamage());
/*      */     
/*  819 */     boolean isOwnerDamageLarger = (ownerDamage >= targetDamage);
/*  820 */     boolean isTargetDamageLarger = (targetDamage >= ownerDamage);
/*      */     
/*  822 */     if (isOwnerDamageLarger) {
/*  823 */       target.func_70106_y();
/*      */     }
/*      */     
/*  826 */     if (isTargetDamageLarger) {
/*  827 */       owner.func_70106_y();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_241845_aY() {
/*  833 */     return false;
/*      */   }
/*      */   
/*      */   public void addBlockToIgnore(Block... blocks) {
/*  837 */     for (Block block : blocks) {
/*  838 */       if (!this.ignorableBlocks.contains(block)) {
/*  839 */         this.ignorableBlocks.add(block);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public void triggerEffects(LivingEntity hitEntity) {
/*  845 */     if ((this.withEffects.getEffects()).length > 0) {
/*  846 */       for (EffectInstance instance : this.withEffects.getEffects()) {
/*  847 */         hitEntity.func_195064_c(instance);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean canBlockDamageSource(DamageSource damageSource, LivingEntity target) {
/*  853 */     if (damageSource == null) {
/*  854 */       return false;
/*      */     }
/*      */     
/*  857 */     ProjectileBlockEvent event = new ProjectileBlockEvent(damageSource.func_76364_f());
/*      */     
/*  859 */     boolean flag = event.canBlock();
/*      */     
/*  861 */     if (!damageSource.func_76363_c() && target.func_184585_cz() && flag) {
/*  862 */       Vector3d Vector3d2 = damageSource.func_188404_v();
/*      */       
/*  864 */       if (Vector3d2 != null) {
/*  865 */         Vector3d Vector3d = func_70040_Z();
/*  866 */         Vector3d Vector3d1 = Vector3d2.func_178788_d(new Vector3d(target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_())).func_216371_e().func_72432_b();
/*      */         
/*  868 */         Vector3d1 = new Vector3d(Vector3d1.field_72450_a, 0.0D, Vector3d1.field_72449_c);
/*      */         
/*  870 */         return (Vector3d1.func_72430_b(Vector3d) < 0.0D);
/*      */       } 
/*      */     } 
/*      */     
/*  874 */     return false;
/*      */   }
/*      */   
/*      */   public <T extends ModDamageSource> T getDamageSource() {
/*  878 */     return (T)this.source;
/*      */   }
/*      */   
/*      */   public void setDamageSource(ModDamageSource source) {
/*  882 */     this.source = source;
/*      */   }
/*      */   
/*      */   private boolean passedThroughIgnorableBlock(BlockPos pos) {
/*  886 */     FluidState fluidState = this.field_70170_p.func_204610_c(pos);
/*      */     
/*  888 */     if (!fluidState.func_206888_e()) {
/*  889 */       return true;
/*      */     }
/*      */     
/*  892 */     return this.ignorableBlocks.contains(this.field_70170_p.func_180495_p(pos).func_177230_c());
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_70106_y() {
/*  897 */     super.func_70106_y();
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_70037_a(CompoundNBT compound) {
/*  902 */     super.func_70037_a(compound);
/*      */     
/*  904 */     setLife(compound.func_74762_e("life"));
/*      */     
/*  906 */     this.maxLife = compound.func_74762_e("maxLife");
/*  907 */     this.hurtTime = compound.func_74762_e("hurtTime");
/*  908 */     this.knockbackStrength = compound.func_74762_e("knockbackStrength");
/*  909 */     this.damage = compound.func_74760_g("damage");
/*  910 */     this.gravity = compound.func_74760_g("gravity");
/*  911 */     this.hasVelocityRotation = compound.func_74767_n("hasVelocityRotation");
/*      */     
/*  913 */     this.canPassThroughBlocks = compound.func_74767_n("canPassThroughBlocks");
/*  914 */     this.canPassThroughEntities = compound.func_74767_n("canPassThroughEntities");
/*  915 */     this.canGetStuckInGround = compound.func_74767_n("canGetStuckInGround");
/*  916 */     this.changeHurtTime = compound.func_74767_n("changeHurtTime");
/*  917 */     this.armorPiercing = compound.func_74760_g("armorPiercing");
/*  918 */     this.canHurtThrower = compound.func_74767_n("canHurtThrower");
/*  919 */     this.field_70180_af.func_187227_b(OWNER, Integer.valueOf(compound.func_74762_e("ownerUUID")));
/*  920 */     this.sourceElement = SourceElement.values()[compound.func_74762_e("sourceElement")];
/*  921 */     this.sourceHakiNature = SourceHakiNature.values()[compound.func_74762_e("sourceHakiNature")];
/*      */     
/*  923 */     int[] sourceTypesOrdinal = compound.func_74759_k("sourceTypes");
/*      */     
/*  925 */     for (int i = 0; i < sourceTypesOrdinal.length; i++) {
/*  926 */       SourceType sourceType = SourceType.values()[sourceTypesOrdinal[i]];
/*      */       
/*  928 */       if (!this.sourceTypes.contains(sourceType)) {
/*  929 */         this.sourceTypes.add(sourceType);
/*      */       }
/*      */     } 
/*      */     
/*  933 */     this.canCollideWithEntities = compound.func_74767_n("canCollideWithEntities");
/*  934 */     this.canCollideWithBlocks = compound.func_74767_n("canCollideWithBlocks");
/*  935 */     this.blocksAffectedLimit = compound.func_74762_e("blocksAffectedLimit");
/*  936 */     this.blocksAffected = compound.func_74762_e("blocksAffected");
/*  937 */     this.removeOnBlockLimit = compound.func_74767_n("removeOnBlockLimit");
/*  938 */     this.hasBlockDestructionLimit = compound.func_74767_n("hasBlockDestructionLimit");
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_213281_b(CompoundNBT compound) {
/*  943 */     super.func_213281_b(compound);
/*      */     
/*  945 */     compound.func_74768_a("life", getLife());
/*  946 */     compound.func_74768_a("maxLife", this.maxLife);
/*  947 */     compound.func_74768_a("hurtTime", this.hurtTime);
/*  948 */     compound.func_74768_a("knockbackStrength", this.knockbackStrength);
/*  949 */     compound.func_74776_a("damage", this.damage);
/*  950 */     compound.func_74776_a("gravity", this.gravity);
/*  951 */     compound.func_74757_a("hasVelocityRotation", this.hasVelocityRotation);
/*  952 */     compound.func_74757_a("canPassThroughBlocks", this.canPassThroughBlocks);
/*  953 */     compound.func_74757_a("canPassThroughEntities", this.canPassThroughEntities);
/*  954 */     compound.func_74757_a("canGetStuckInGround", this.canGetStuckInGround);
/*  955 */     compound.func_74757_a("changeHurtTime", this.changeHurtTime);
/*  956 */     compound.func_74776_a("armorPiercing", this.armorPiercing);
/*  957 */     compound.func_74757_a("canHurtThrower", this.canHurtThrower);
/*  958 */     compound.func_74768_a("ownerUUID", ((Integer)this.field_70180_af.func_187225_a(OWNER)).intValue());
/*  959 */     compound.func_74768_a("sourceElement", this.sourceElement.ordinal());
/*  960 */     compound.func_74768_a("sourceHakiNature", this.sourceHakiNature.ordinal());
/*  961 */     compound.func_74783_a("sourceTypes", this.sourceTypes.stream().mapToInt(Enum::ordinal).toArray());
/*  962 */     compound.func_74757_a("canCollideWithEntities", this.canCollideWithEntities);
/*  963 */     compound.func_74757_a("canCollideWithBlocks", this.canCollideWithBlocks);
/*  964 */     compound.func_74768_a("blocksAffectedLimit", this.blocksAffectedLimit);
/*  965 */     compound.func_74768_a("blocksAffected", this.blocksAffected);
/*  966 */     compound.func_74757_a("removeOnBlockLimit", this.removeOnBlockLimit);
/*  967 */     compound.func_74757_a("hasBlockDestructionLimit", this.hasBlockDestructionLimit);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected float func_70185_h() {
/*  973 */     return this.gravity;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_180427_aV() {
/*  978 */     return true;
/*      */   }
/*      */   
/*      */   public void clearTargets() {
/*  982 */     this.targets.clear();
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_70088_a() {
/*  987 */     this.field_70180_af.func_187214_a(LIFE, Integer.valueOf(64));
/*  988 */     this.field_70180_af.func_187214_a(OWNER, Integer.valueOf(-1));
/*  989 */     this.field_70180_af.func_187214_a(IS_GLOWING, Boolean.valueOf(false));
/*      */   }
/*      */ 
/*      */   
/*      */   public IPacket<?> func_213297_N() {
/*  994 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeSpawnData(PacketBuffer buffer) {
/* 1006 */     buffer.writeDouble(this.entityCollisionBox.func_216364_b());
/* 1007 */     buffer.writeDouble(this.entityCollisionBox.func_216360_c());
/* 1008 */     buffer.writeDouble(this.entityCollisionBox.func_216362_d());
/* 1009 */     buffer.writeDouble(this.blockCollisionBox.func_216364_b());
/* 1010 */     buffer.writeDouble(this.blockCollisionBox.func_216360_c());
/* 1011 */     buffer.writeDouble(this.blockCollisionBox.func_216362_d());
/* 1012 */     buffer.writeInt(this.maxLife);
/* 1013 */     buffer.writeInt((func_234616_v_() != null) ? func_234616_v_().func_145782_y() : -1);
/* 1014 */     buffer.writeInt(this.sourceElement.ordinal());
/* 1015 */     buffer.writeInt(this.sourceHakiNature.ordinal());
/* 1016 */     buffer.writeInt(this.sourceTypes.size());
/*      */     
/* 1018 */     for (SourceType sourceType : this.sourceTypes) {
/* 1019 */       buffer.writeInt(sourceType.ordinal());
/*      */     }
/*      */     
/* 1022 */     buffer.writeBoolean(this.hasVelocityRotation);
/*      */   }
/*      */ 
/*      */   
/*      */   public void readSpawnData(PacketBuffer buffer) {
/* 1027 */     setEntityCollisionSize(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
/* 1028 */     setBlockCollisionSize(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
/* 1029 */     this.maxLife = buffer.readInt();
/* 1030 */     func_212361_a(this.field_70170_p.func_73045_a(buffer.readInt()));
/* 1031 */     this.sourceElement = SourceElement.values()[buffer.readInt()];
/* 1032 */     this.sourceHakiNature = SourceHakiNature.values()[buffer.readInt()];
/*      */     
/* 1034 */     int size = buffer.readInt();
/*      */     
/* 1036 */     for (int i = 0; i < size; i++) {
/* 1037 */       SourceType sourceType = SourceType.values()[buffer.readInt()];
/*      */       
/* 1039 */       if (!this.sourceTypes.contains(sourceType)) {
/* 1040 */         this.sourceTypes.add(sourceType);
/*      */       }
/*      */     } 
/*      */     
/* 1044 */     this.hasVelocityRotation = buffer.readBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setThrower(LivingEntity entity) {
/* 1052 */     this.field_70180_af.func_187227_b(OWNER, Integer.valueOf(entity.func_145782_y()));
/*      */     
/* 1054 */     func_212361_a((Entity)entity);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public LivingEntity getThrower() {
/* 1059 */     if (func_234616_v_() instanceof LivingEntity) {
/* 1060 */       return (LivingEntity)func_234616_v_();
/*      */     }
/* 1062 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public Entity func_234616_v_() {
/* 1070 */     return (func_184212_Q().func_187225_a(OWNER) != null && this.field_70170_p.func_73045_a(((Integer)func_184212_Q().func_187225_a(OWNER)).intValue()) instanceof LivingEntity) ? this.field_70170_p.func_73045_a(((Integer)func_184212_Q().func_187225_a(OWNER)).intValue()) : super.func_234616_v_();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setKnockbackStrength(int knockbackStrength) {
/* 1075 */     this.knockbackStrength = knockbackStrength;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setGlowing() {
/* 1080 */     this.field_70180_af.func_187227_b(IS_GLOWING, Boolean.valueOf(true));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_225510_bt_() {
/* 1085 */     return (super.func_225510_bt_() || ((Boolean)this.field_70180_af.func_187225_a(IS_GLOWING)).booleanValue());
/*      */   }
/*      */   
/*      */   public AxisAlignedBB getEntityCollisionBox() {
/* 1089 */     return this.entityCollisionBox;
/*      */   }
/*      */   
/*      */   public void setEntityCollisionSize(double val) {
/* 1093 */     setEntityCollisionSize(val, val, val);
/*      */   }
/*      */   
/*      */   public void setEntityCollisionSize(double x, double y, double z) {
/* 1097 */     this.entityCollisionBox = new AxisAlignedBB(-x / 2.0D, -y / 2.0D, -z / 2.0D, x / 2.0D, y / 2.0D, z / 2.0D);
/*      */     
/* 1099 */     if (!this.field_70170_p.field_72995_K) {
/* 1100 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateProjEntityCollisionBox(func_145782_y(), this.entityCollisionBox.func_216364_b(), this.entityCollisionBox.func_216360_c(), this.entityCollisionBox.func_216362_d()), (Entity)getThrower());
/*      */     }
/*      */   }
/*      */   
/*      */   public AxisAlignedBB getBlockCollisionBox() {
/* 1105 */     return this.blockCollisionBox;
/*      */   }
/*      */   
/*      */   public void setBlockCollisionSize(double val) {
/* 1109 */     setBlockCollisionSize(val, val, val);
/*      */   }
/*      */   
/*      */   public void setBlockCollisionSize(double x, double y, double z) {
/* 1113 */     this.blockCollisionBox = new AxisAlignedBB(-x / 2.0D, -y / 2.0D, -z / 2.0D, x / 2.0D, y / 2.0D, z / 2.0D);
/*      */     
/* 1115 */     if (!this.field_70170_p.field_72995_K) {
/* 1116 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateProjBlockCollisionBox(func_145782_y(), this.blockCollisionBox.func_216364_b(), this.blockCollisionBox.func_216360_c(), this.blockCollisionBox.func_216362_d()), (Entity)getThrower());
/*      */     }
/*      */   }
/*      */   
/*      */   public int getLife() {
/* 1121 */     return ((Integer)this.field_70180_af.func_187225_a(LIFE)).intValue();
/*      */   }
/*      */   
/*      */   public int getMaxLife() {
/* 1125 */     return this.maxLife;
/*      */   }
/*      */   
/*      */   public void setMaxLife(int life) {
/* 1129 */     this.maxLife = life;
/*      */     
/* 1131 */     setLife(this.maxLife);
/*      */   }
/*      */   
/*      */   public void setLife(int life) {
/* 1135 */     this.field_70180_af.func_187227_b(LIFE, Integer.valueOf(life));
/*      */   }
/*      */   
/*      */   public void setPhysical() {
/* 1139 */     if (!this.sourceTypes.contains(SourceType.PHYSICAL)) {
/* 1140 */       this.sourceTypes.add(SourceType.PHYSICAL);
/*      */       
/* 1142 */       this.sourceTypes.removeIf(sourceType -> (sourceType == SourceType.UNKNOWN));
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setFist() {
/* 1147 */     if (!this.sourceTypes.contains(SourceType.FIST)) {
/* 1148 */       this.sourceTypes.add(SourceType.FIST);
/*      */       
/* 1150 */       this.sourceTypes.removeIf(sourceType -> (sourceType == SourceType.UNKNOWN));
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setProjectile() {
/* 1155 */     if (!this.sourceTypes.contains(SourceType.PROJECTILE)) {
/* 1156 */       this.sourceTypes.add(SourceType.PROJECTILE);
/*      */       
/* 1158 */       this.sourceTypes.removeIf(sourceType -> (sourceType == SourceType.UNKNOWN));
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setAffectedByImbuing() {
/* 1163 */     setPhysical();
/* 1164 */     this.sourceHakiNature = SourceHakiNature.IMBUING;
/* 1165 */     if (this.source instanceof ModDamageSource) {
/* 1166 */       this.source = this.source.setHakiNature(this.sourceHakiNature);
/*      */     }
/*      */   }
/*      */   
/*      */   public void setAffectedByHardening() {
/* 1171 */     setPhysical();
/* 1172 */     this.sourceHakiNature = SourceHakiNature.HARDENING;
/* 1173 */     if (this.source instanceof ModDamageSource) {
/* 1174 */       this.source = this.source.setHakiNature(this.sourceHakiNature);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHurtThrower() {
/* 1180 */     this.canHurtThrower = true;
/*      */   }
/*      */   
/*      */   public boolean isPhysical() {
/* 1184 */     if (this.source instanceof ModDamageSource) {
/* 1185 */       return this.source.isPhysical();
/*      */     }
/*      */     
/* 1188 */     return this.sourceTypes.contains(SourceType.PHYSICAL);
/*      */   }
/*      */   
/*      */   public boolean isAffectedByHaki() {
/* 1192 */     return (isAffectedByHardening() || isAffectedByImbuing() || getSourceHakiNature() == SourceHakiNature.SPECIAL);
/*      */   }
/*      */   
/*      */   public boolean isAffectedByHardening() {
/* 1196 */     return (this.sourceHakiNature == SourceHakiNature.HARDENING || this.sourceHakiNature == SourceHakiNature.SPECIAL);
/*      */   }
/*      */   
/*      */   public boolean isAffectedByImbuing() {
/* 1200 */     return (this.sourceHakiNature == SourceHakiNature.IMBUING || this.sourceHakiNature == SourceHakiNature.SPECIAL);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPassThroughBlocks() {
/* 1205 */     this.canPassThroughBlocks = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setFake() {
/* 1210 */     this.isFake = true;
/*      */   }
/*      */   
/*      */   public void setNoVelocityRotation() {
/* 1214 */     this.hasVelocityRotation = false;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void setArmorPiercing() {
/* 1220 */     setArmorPiercing(1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setArmorPiercing(float piercing) {
/* 1229 */     this.armorPiercing = piercing;
/*      */     
/* 1231 */     if (this.source != null && this.source instanceof ModDamageSource) {
/* 1232 */       this.source.setPiercing(piercing);
/*      */     }
/*      */   }
/*      */   
/*      */   public void setUnavoidable() {
/* 1237 */     this.unavoidable = true;
/*      */     
/* 1239 */     if (this.source != null && this.source instanceof ModDamageSource) {
/* 1240 */       this.source.setUnavoidable();
/*      */     }
/*      */   }
/*      */   
/*      */   public float getArmorPiercing() {
/* 1245 */     return this.armorPiercing;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPassThroughEntities() {
/* 1250 */     this.canPassThroughEntities = true;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void setCanGetStuckInGround() {
/* 1256 */     this.canGetStuckInGround = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDamage(float damage) {
/* 1261 */     this.damage = damage;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getDamage() {
/* 1266 */     return this.damage;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setGravity(float gravity) {
/* 1271 */     this.gravity = gravity;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isStuckInGround() {
/* 1276 */     return this.stuckInGround;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHurtTime(int time) {
/* 1281 */     this.hurtTime = time;
/* 1282 */     this.changeHurtTime = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTargetResetTime(int time) {
/* 1287 */     this.targetResetTime = time;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getTargetResetTime() {
/* 1292 */     return this.targetResetTime;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSourceHakiNature(SourceHakiNature kind) {
/* 1297 */     this.sourceHakiNature = kind;
/*      */   }
/*      */ 
/*      */   
/*      */   public SourceHakiNature getSourceHakiNature() {
/* 1302 */     return this.sourceHakiNature;
/*      */   }
/*      */   
/*      */   public void setCollideWithEntities(boolean canCollideWithEntities) {
/* 1306 */     this.canCollideWithEntities = canCollideWithEntities;
/*      */   }
/*      */   
/*      */   public void setCollideWithBlocks(boolean canCollideWithBlocks) {
/* 1310 */     this.canCollideWithBlocks = canCollideWithBlocks;
/*      */   }
/*      */   
/*      */   public void setBlocksAffectedLimit(int limit) {
/* 1314 */     this.blocksAffectedLimit = limit;
/*      */   }
/*      */   
/*      */   public int getBlocksAffectedLimit() {
/* 1318 */     return this.blocksAffectedLimit;
/*      */   }
/*      */   
/*      */   public void increaseBlocksAffected(int increaseAmount) {
/* 1322 */     this.blocksAffected = MathHelper.func_76125_a(this.blocksAffected + increaseAmount, 0, this.blocksAffectedLimit);
/*      */   }
/*      */   
/*      */   public int getBlocksAffected() {
/* 1326 */     return this.blocksAffected;
/*      */   }
/*      */   
/*      */   public void setRemoveOnBlockLimit(boolean removeOnBlockLimit) {
/* 1330 */     this.removeOnBlockLimit = removeOnBlockLimit;
/*      */   }
/*      */   
/*      */   public boolean getRemoveOnBlockLimit() {
/* 1334 */     return this.removeOnBlockLimit;
/*      */   }
/*      */   
/*      */   public void setHasBlockDestructionLimit(boolean hasBlockDestructionLimit) {
/* 1338 */     this.hasBlockDestructionLimit = hasBlockDestructionLimit;
/*      */   }
/*      */   
/*      */   public boolean getHasBlockDestructionLimit() {
/* 1342 */     return this.hasBlockDestructionLimit;
/*      */   }
/*      */   
/*      */   public ExplosionAbility createExplosion(Entity entity, World world, double posX, double posY, double posZ, float size) {
/* 1346 */     ExplosionAbility explosion = AbilityHelper.newExplosion(entity, world, posX, posY, posZ, size);
/*      */     
/* 1348 */     explosion.onBlockDestroyedEvent = (hitPos -> {
/*      */         int destroySpeed = Math.max((int)this.field_70170_p.func_180495_p(hitPos).getExplosionResistance((IBlockReader)this.field_70170_p, hitPos, (Explosion)explosion), 1);
/*      */ 
/*      */         
/*      */         increaseBlocksAffected(destroySpeed);
/*      */         
/*      */         explosion.setExplodedBlocksLimit(getBlocksAffectedLimit() - getBlocksAffected());
/*      */       });
/*      */     
/* 1357 */     return explosion;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public AbilityCore<? extends IAbility> getParent() {
/* 1362 */     return this.parent;
/*      */   }
/*      */   
/*      */   public static interface IWithEffects extends Serializable {
/*      */     EffectInstance[] getEffects();
/*      */   }
/*      */   
/*      */   public static interface IOnTick extends Serializable {
/*      */     void onTick();
/*      */   }
/*      */   
/*      */   public static interface IOnBlockImpact extends Serializable {
/*      */     void onImpact(BlockPos param1BlockPos);
/*      */   }
/*      */   
/*      */   public static interface IOnEntityImpact extends Serializable {
/*      */     void onImpact(LivingEntity param1LivingEntity);
/*      */   }
/*      */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\AbilityProjectileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */