/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.doku;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleType;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doku.DokuHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.EntityCloud;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ChloroBallProjectile extends AbilityProjectileEntity implements IEntityAdditionalSpawnData {
/*  31 */   private int spread = 4; private boolean isDemonMode = false;
/*     */   
/*     */   public ChloroBallProjectile(EntityType type, World world) {
/*  34 */     super(type, world);
/*     */   }
/*     */   
/*     */   public ChloroBallProjectile(World world, LivingEntity player, boolean isDemonForm) {
/*  38 */     super((EntityType)DokuProjectiles.CHLORO_BALL.get(), world, player, ChloroBallAbility.INSTANCE);
/*     */     
/*  40 */     setDamage(isDemonForm ? 12.0F : 7.0F);
/*  41 */     this.isDemonMode = isDemonForm;
/*     */     
/*  43 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*  44 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*  45 */     this.onTickEvent = this::onTickEvent;
/*     */   }
/*     */   
/*     */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/*  49 */     hitEntity.func_195064_c(new EffectInstance((Effect)ModEffects.DOKU_POISON.get(), 300, getPoisonAmplifier()));
/*  50 */     this.onBlockImpactEvent.onImpact(hitEntity.func_233580_cy_());
/*     */   }
/*     */   
/*     */   private void onBlockImpactEvent(BlockPos pos) {
/*  54 */     int fails = 0;
/*  55 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/*  56 */     for (int i = 0; i < getPoisonBlockAmount() && 
/*  57 */       fails <= 100; ) {
/*     */ 
/*     */       
/*  60 */       double offsetX = WyHelper.randomWithRange(-this.spread, this.spread);
/*  61 */       double offsetY = WyHelper.randomWithRange(-2, 2);
/*  62 */       double offsetZ = WyHelper.randomWithRange(-this.spread, this.spread);
/*     */       
/*  64 */       mutpos.func_189532_c(func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*     */       
/*  66 */       if (this.field_70170_p.func_180495_p(mutpos.func_177977_b()).func_185904_a().func_76220_a() && 
/*  67 */         AbilityHelper.placeBlockIfAllowed(getThrower(), (BlockPos)mutpos, getPoisonBlock().func_176223_P(), DefaultProtectionRules.AIR_FOLIAGE)) {
/*  68 */         i++;
/*     */         
/*     */         continue;
/*     */       } 
/*  72 */       fails++;
/*     */     } 
/*     */     
/*  75 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.CHLORO_BALL.get(), (Entity)this, func_226277_ct_(), func_226278_cu_() + 1.0D, func_226281_cx_());
/*     */     
/*  77 */     ChloroBallCloudEntity smokeCloud = new ChloroBallCloudEntity(this, this.field_70170_p);
/*  78 */     smokeCloud.setLife(30);
/*  79 */     smokeCloud.func_70012_b(func_226277_ct_(), func_226278_cu_() + 1.0D, func_226281_cx_(), 0.0F, 0.0F);
/*  80 */     AbilityHelper.setDeltaMovement((Entity)smokeCloud, 0.0D, 0.0D, 0.0D);
/*  81 */     smokeCloud.setThrower(getThrower());
/*  82 */     this.field_70170_p.func_217376_c((Entity)smokeCloud);
/*     */   }
/*     */   
/*     */   private void onTickEvent() {
/*  86 */     if (!this.field_70170_p.field_72995_K) {
/*     */       
/*  88 */       if (func_70027_ad()) {
/*  89 */         ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, func_233580_cy_().func_177958_n(), func_233580_cy_().func_177956_o(), func_233580_cy_().func_177952_p(), 4.0F);
/*  90 */         explosion.setStaticDamage(20.0F);
/*  91 */         explosion.setDestroyBlocks(false);
/*  92 */         explosion.setFireAfterExplosion(false);
/*  93 */         explosion.setExplosionSound(true);
/*  94 */         explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
/*  95 */         explosion.doExplosion();
/*  96 */         this.spread = 8;
/*  97 */         this.onBlockImpactEvent.onImpact(func_233580_cy_());
/*     */         
/*     */         return;
/*     */       } 
/* 101 */       for (int i = 0; i < 2; i++) {
/* 102 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 103 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 104 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*     */         
/* 106 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.DOKU.get());
/* 107 */         if (this.isDemonMode) {
/* 108 */           data.setColor(1.0F, 0.0F, 0.0F);
/*     */         }
/* 110 */         data.setLife(5);
/* 111 */         data.setSize(1.3F);
/* 112 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParticleEffect.Details getPoisonParticles() {
/* 118 */     return this.isDemonMode ? (ParticleEffect.Details)DokuHelper.DETAILS : (ParticleEffect.Details)ParticleEffect.EMPTY_DETAILS;
/*     */   }
/*     */   
/*     */   public int getPoisonAmplifier() {
/* 122 */     return this.isDemonMode ? 4 : 0;
/*     */   }
/*     */   
/*     */   public int getPoisonBlockAmount() {
/* 126 */     return this.isDemonMode ? 40 : 20;
/*     */   }
/*     */   
/*     */   public Block getPoisonBlock() {
/* 130 */     return this.isDemonMode ? (Block)ModBlocks.DEMON_POISON.get() : (Block)ModBlocks.POISON.get();
/*     */   }
/*     */   
/*     */   public int getPoisonRange() {
/* 134 */     return this.isDemonMode ? 5 : 8;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeSpawnData(PacketBuffer buffer) {
/* 140 */     super.writeSpawnData(buffer);
/* 141 */     buffer.writeBoolean(this.isDemonMode);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readSpawnData(PacketBuffer buffer) {
/* 147 */     super.readSpawnData(buffer);
/* 148 */     this.isDemonMode = buffer.readBoolean();
/*     */   }
/*     */   
/*     */   public boolean isDemonMode() {
/* 152 */     return this.isDemonMode;
/*     */   }
/*     */   
/*     */   public static class ChloroBallCloudEntity extends EntityCloud {
/*     */     private ChloroBallProjectile proj;
/*     */     
/*     */     public ChloroBallCloudEntity(ChloroBallProjectile proj, World world) {
/* 159 */       super(world);
/* 160 */       this.proj = proj;
/*     */     }
/*     */     
/*     */     public ChloroBallCloudEntity(World world) {
/* 164 */       super(world);
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_70071_h_() {
/* 169 */       super.func_70071_h_();
/* 170 */       if (!this.field_70170_p.field_72995_K) {
/* 171 */         for (LivingEntity target : WyHelper.getNearbyLiving(func_213303_ch(), (IWorld)this.field_70170_p, this.proj.getPoisonRange(), ModEntityPredicates.getEnemyFactions(getThrower()))) {
/* 172 */           if (getThrower() != target && !target.func_70644_a((Effect)ModEffects.DOKU_POISON.get())) {
/* 173 */             target.func_195064_c(new EffectInstance((Effect)ModEffects.DOKU_POISON.get(), 200, this.proj.getPoisonAmplifier()));
/*     */           }
/*     */         } 
/*     */         
/* 177 */         if (this.field_70173_aa % 2 == 0)
/* 178 */           WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.CHLORO_BALL_CLOUD.get(), (Entity)this, func_226277_ct_(), func_226278_cu_() + 1.0D, func_226281_cx_(), this.proj.getPoisonParticles()); 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\doku\ChloroBallProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */