/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.beta;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleType;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class StickyProjectile extends AbilityProjectileEntity {
/*     */   public StickyProjectile(EntityType type, World world) {
/*  27 */     super(type, world);
/*     */   }
/*     */   private boolean causeExplosion = false;
/*     */   
/*     */   public StickyProjectile(World world, LivingEntity player, Ability ability) {
/*  32 */     super((EntityType)BetaProjectiles.STICKY_PROJECTILE.get(), world, player, ability);
/*     */     
/*  34 */     setDamage(2.0F);
/*  35 */     setMaxLife(20);
/*     */ 
/*     */     
/*  38 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*  39 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*  40 */     this.onTickEvent = this::onTickEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private void onTickEvent() {
/*  45 */     if (!this.field_70170_p.field_72995_K) {
/*     */       int i;
/*  47 */       for (i = 0; i < 4; i++) {
/*     */         
/*  49 */         double offsetX = WyHelper.randomDouble() / 5.0D;
/*  50 */         double offsetY = WyHelper.randomDouble() / 5.0D;
/*  51 */         double offsetZ = WyHelper.randomDouble() / 5.0D;
/*     */         
/*  53 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.BETA.get());
/*  54 */         data.setLife(10);
/*  55 */         data.setSize(1.3F);
/*  56 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + 0.25D + offsetY, func_226281_cx_() + offsetZ);
/*     */       } 
/*     */       
/*  59 */       if (this.causeExplosion)
/*     */       {
/*  61 */         for (i = 0; i < 2; i++) {
/*     */           
/*  63 */           double offsetX = WyHelper.randomDouble() / 2.0D;
/*  64 */           double offsetY = WyHelper.randomDouble() / 2.0D;
/*  65 */           double offsetZ = WyHelper.randomDouble() / 2.0D;
/*     */           
/*  67 */           SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MERA.get());
/*  68 */           data.setLife(7);
/*  69 */           data.setSize(1.2F);
/*  70 */           WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void onEntityImpactEvent(LivingEntity entity) {
/*  78 */     if (this.causeExplosion) {
/*     */       
/*  80 */       ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), 2.0F);
/*  81 */       explosion.setStaticDamage(10.0F);
/*  82 */       explosion.setExplosionSound(true);
/*  83 */       explosion.setDamageOwner(false);
/*  84 */       explosion.setDestroyBlocks(true);
/*  85 */       explosion.setFireAfterExplosion(false);
/*  86 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/*  87 */       explosion.setDamageEntities(true);
/*  88 */       explosion.doExplosion();
/*     */     }
/*     */     else {
/*     */       
/*  92 */       entity.func_195064_c(new EffectInstance((Effect)ModEffects.STICKY.get(), 300, 0, false, false));
/*  93 */       for (int i = 0; i < 20; i++) {
/*     */         
/*  95 */         double offsetX = WyHelper.randomWithRange(-2, 2);
/*  96 */         double offsetZ = WyHelper.randomWithRange(-2, 2);
/*  97 */         BlockPos location = new BlockPos(func_226277_ct_() + offsetX, func_226278_cu_() - 1.0D, func_226281_cx_() + offsetZ);
/*  98 */         if (this.field_70170_p.func_180495_p(location.func_177977_b()).func_200132_m()) {
/*  99 */           AbilityHelper.placeBlockIfAllowed(getThrower(), location, ((Block)ModBlocks.MUCUS.get()).func_176223_P(), DefaultProtectionRules.AIR_FOLIAGE);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void onBlockImpactEvent(BlockPos hit) {
/* 107 */     if (this.causeExplosion) {
/*     */       
/* 109 */       ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 2.0F);
/* 110 */       explosion.setStaticDamage(10.0F);
/* 111 */       explosion.setExplosionSound(true);
/* 112 */       explosion.setDamageOwner(false);
/* 113 */       explosion.setDestroyBlocks(true);
/* 114 */       explosion.setFireAfterExplosion(true);
/* 115 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/* 116 */       explosion.setDamageEntities(true);
/* 117 */       explosion.doExplosion();
/*     */     }
/*     */     else {
/*     */       
/* 121 */       for (int i = 0; i < 20; i++) {
/*     */         
/* 123 */         double offsetX = WyHelper.randomWithRange(-2, 2);
/* 124 */         double offsetZ = WyHelper.randomWithRange(-2, 2);
/* 125 */         BlockPos location = new BlockPos(func_226277_ct_() + offsetX, func_226278_cu_(), func_226281_cx_() + offsetZ);
/* 126 */         if (this.field_70170_p.func_180495_p(location.func_177977_b()).func_200132_m()) {
/* 127 */           AbilityHelper.placeBlockIfAllowed(getThrower(), location, ((Block)ModBlocks.MUCUS.get()).func_176223_P(), DefaultProtectionRules.AIR_FOLIAGE);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCauseExplosion() {
/* 135 */     this.causeExplosion = true;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\beta\StickyProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */