/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.zushi;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.FallingBlockEntity;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleType;
/*     */ import net.minecraft.util.IndirectEntityDamageSource;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.zushi.SagariNoRyuseiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.ModIndirectEntityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.IFlexibleSizeProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class SagariNoRyuseiProjectile extends AbilityProjectileEntity implements IFlexibleSizeProjectile {
/*  27 */   private static final DataParameter<Float> SIZE = EntityDataManager.func_187226_a(SagariNoRyuseiProjectile.class, DataSerializers.field_187193_c);
/*     */ 
/*     */   
/*     */   public SagariNoRyuseiProjectile(EntityType type, World world) {
/*  31 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public SagariNoRyuseiProjectile(World world, LivingEntity player) {
/*  36 */     super((EntityType)ZushiProjectiles.SAGARI_NO_RYUSEI.get(), world, player, SagariNoRyuseiAbility.INSTANCE);
/*     */     
/*  38 */     setDamage(200.0F);
/*  39 */     setBlocksAffectedLimit(13824);
/*  40 */     setArmorPiercing(0.25F);
/*  41 */     setMaxLife(256);
/*  42 */     setHurtThrower();
/*     */     
/*  44 */     setDamageSource((new ModIndirectEntityDamageSource("ability_projectile", (Entity)this, null)).setProjectile());
/*  45 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*  46 */     this.onTickEvent = this::onTickEvent;
/*     */   }
/*     */   
/*     */   private void onBlockImpactEvent(BlockPos hit) {
/*  50 */     float mult = getSize() / 30.0F;
/*  51 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, func_226277_ct_(), func_226278_cu_(), func_226281_cx_(), 20.0F * mult);
/*  52 */     explosion.setStaticDamage(90.0F * mult);
/*  53 */     explosion.addRemovedBlocksToList();
/*  54 */     explosion.setDamageOwner(true);
/*  55 */     explosion.setDamageSource((new IndirectEntityDamageSource("explosion", (Entity)this, null)).func_94540_d());
/*  56 */     explosion.setFireAfterExplosion(true);
/*  57 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect((int)(20.0F * mult)));
/*  58 */     explosion.doExplosion();
/*     */     
/*  60 */     int size = 0;
/*     */     
/*  62 */     for (FallingBlockEntity entity : explosion.removedBlocks) {
/*  63 */       AbilityHelper.setDeltaMovement((Entity)entity, WyHelper.randomWithRange(-1, 1) / 2.0D * mult, (0.75D + 
/*  64 */           WyHelper.randomDouble()) * mult, 
/*  65 */           WyHelper.randomWithRange(-1, 1) / 2.0D * mult);
/*     */       
/*  67 */       entity.field_145813_c = false;
/*  68 */       entity.field_145812_b = 1;
/*     */       
/*  70 */       this.field_70170_p.func_217376_c((Entity)entity);
/*     */       
/*  72 */       size++;
/*     */       
/*  74 */       if (size > 256) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onTickEvent() {
/*  81 */     float mult = getSize() / 30.0F;
/*     */     
/*  83 */     if (1 > this.field_70173_aa) {
/*  84 */       func_174826_a(func_174813_aQ().func_186662_g(mult));
/*     */     }
/*     */     
/*  87 */     if (!this.field_70170_p.field_72995_K) {
/*  88 */       for (int i = 0; i < 25; i++) {
/*  89 */         ParticleType<SimpleParticleData> particleToUse = (this.field_70173_aa % 2 == 0) ? (ParticleType<SimpleParticleData>)ModParticleTypes.MOKU.get() : (ParticleType<SimpleParticleData>)ModParticleTypes.MERA.get();
/*     */         
/*  91 */         double offsetX = WyHelper.randomDouble() * 5.0D * mult;
/*  92 */         double offsetY = WyHelper.randomDouble();
/*  93 */         double offsetZ = WyHelper.randomDouble() * 5.0D * mult;
/*     */         
/*  95 */         SimpleParticleData data = new SimpleParticleData(particleToUse);
/*  96 */         data.setLife(20);
/*  97 */         data.setSize(7.0F * mult);
/*     */         
/*  99 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70088_a() {
/* 106 */     super.func_70088_a();
/* 107 */     this.field_70180_af.func_187214_a(SIZE, Float.valueOf(0.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(float size) {
/* 112 */     this.field_70180_af.func_187227_b(SIZE, Float.valueOf(Math.min(size, 30.0F)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSize() {
/* 118 */     return ((Float)this.field_70180_af.func_187225_a(SIZE)).floatValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\zushi\SagariNoRyuseiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */