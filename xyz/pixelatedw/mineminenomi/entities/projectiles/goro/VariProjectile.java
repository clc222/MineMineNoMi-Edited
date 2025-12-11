/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.goro;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.particles.ParticleType;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.IFlexibleSizeProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class VariProjectile extends AbilityProjectileEntity implements IFlexibleSizeProjectile {
/*  23 */   private static final DataParameter<Float> SIZE = EntityDataManager.func_187226_a(VariProjectile.class, DataSerializers.field_187193_c);
/*     */   
/*     */   private ExplosionAbility explosion;
/*     */   
/*     */   public VariProjectile(EntityType type, World world) {
/*  28 */     super(type, world);
/*     */   }
/*     */   
/*     */   public VariProjectile(World world, double x, double y, double z) {
/*  32 */     super((EntityType)GoroProjectiles.VOLT_VARI.get(), world, x, y, z);
/*     */   }
/*     */   
/*     */   public VariProjectile(World world, LivingEntity player, float power, Ability ability) {
/*  36 */     super((EntityType)GoroProjectiles.VOLT_VARI.get(), world, player, ability.getCore());
/*     */     
/*  38 */     setMaxLife(40);
/*  39 */     setPassThroughEntities();
/*     */     
/*  41 */     this.onTickEvent = this::onTickEvent;
/*  42 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*     */     
/*  44 */     this.explosion = createExplosion((Entity)getThrower(), this.field_70170_p, 0.0D, 0.0D, 0.0D, 0.0F);
/*  45 */     this.explosion.setStaticDamage(5.0F + power / 10.0F);
/*  46 */     this.explosion.setExplosionSound(true);
/*  47 */     this.explosion.setDamageOwner(false);
/*  48 */     this.explosion.setDestroyBlocks(true);
/*  49 */     this.explosion.setStaticBlockResistance(0.1F);
/*  50 */     this.explosion.setDamageEntities(true);
/*     */   }
/*     */ 
/*     */   
/*     */   private void onBlockImpactEvent(BlockPos hit) {
/*  55 */     float voltage = ((Float)func_184212_Q().func_187225_a(SIZE)).floatValue();
/*  56 */     if (voltage > 2.0F) {
/*     */       
/*  58 */       this.explosion.setExplosionPos(hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p());
/*  59 */       this.explosion.doExplosion();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void onTickEvent() {
/*  65 */     float voltage = ((Float)this.field_70180_af.func_187225_a(SIZE)).floatValue();
/*     */     
/*  67 */     if (!this.field_70170_p.field_72995_K)
/*     */     {
/*  69 */       for (int i = 0; i < 5; i++) {
/*     */         
/*  71 */         ParticleType<SimpleParticleData> particleToUse = (this.field_70173_aa % 2 == 0) ? (ParticleType<SimpleParticleData>)ModParticleTypes.GORO2_YELLOW.get() : (ParticleType<SimpleParticleData>)ModParticleTypes.GORO_YELLOW.get();
/*     */         
/*  73 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/*  74 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/*  75 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*     */         
/*  77 */         SimpleParticleData data = new SimpleParticleData(particleToUse);
/*  78 */         data.setLife(8);
/*  79 */         data.setSize(4.0F * voltage / 100.0F);
/*  80 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70088_a() {
/*  88 */     super.func_70088_a();
/*  89 */     func_184212_Q().func_187214_a(SIZE, Float.valueOf(0.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSize(float size) {
/*  95 */     setDamage(10.0F + size * 2.0F);
/*  96 */     func_174826_a(func_174813_aQ().func_186662_g((size / 100.0F)));
/*  97 */     func_184212_Q().func_187227_b(SIZE, Float.valueOf(Math.min(size, 100.0F)));
/*  98 */     float power = ((Float)func_184212_Q().func_187225_a(SIZE)).floatValue() / 20.0F;
/*  99 */     this.explosion.setExplosionSize(power * 2.0F);
/* 100 */     this.explosion.setExplodedBlocksLimit((int)(power * 150.0F));
/* 101 */     this.explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect((int)(power * 0.9F)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSize() {
/* 107 */     return 0.7F + ((Float)this.field_70180_af.func_187225_a(SIZE)).floatValue() / 10.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\goro\VariProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */