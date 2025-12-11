/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleType;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class PopGreenProjectile extends AbilityProjectileEntity {
/*  18 */   private PopGreenType type = null;
/*     */ 
/*     */   
/*     */   public PopGreenProjectile(EntityType type, World world) {
/*  22 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public PopGreenProjectile(World world, LivingEntity player, PopGreenType type) {
/*  27 */     super((EntityType)ExtraProjectiles.POP_GREEN.get(), world, player);
/*  28 */     this.type = type;
/*  29 */     setDamage((this.type == PopGreenType.IMPACT_WOLF) ? 25.0F : 4.0F);
/*  30 */     setPhysical();
/*  31 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*  32 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*  33 */     this.onTickEvent = this::onTickEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private void onTickEvent() {
/*  38 */     if (this.type == PopGreenType.IMPACT_WOLF && 
/*  39 */       !this.field_70170_p.field_72995_K) {
/*     */       int i;
/*  41 */       for (i = 0; i < 8; i++) {
/*     */         
/*  43 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/*  44 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/*  45 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*     */         
/*  47 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MERA.get());
/*  48 */         data.setLife(10);
/*  49 */         data.setSize(2.5F);
/*  50 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*     */       } 
/*     */       
/*  53 */       for (i = 0; i < 4; i++) {
/*     */         
/*  55 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/*  56 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/*  57 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*     */         
/*  59 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MERA2.get());
/*  60 */         data.setLife(7);
/*  61 */         data.setSize(1.0F);
/*  62 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private void onBlockImpactEvent(BlockPos block) {
/*     */     ExplosionAbility bakuhatsu_explosion;
/*     */     ExplosionAbility wolf_explosion;
/*  69 */     switch (this.type) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case BAKUHATSU:
/*  77 */         bakuhatsu_explosion = createExplosion(func_234616_v_(), this.field_70170_p, block.func_177958_n(), block.func_177956_o(), block.func_177952_p(), 1.0F);
/*  78 */         bakuhatsu_explosion.setExplosionSound(true);
/*  79 */         bakuhatsu_explosion.setDestroyBlocks(true);
/*  80 */         bakuhatsu_explosion.setFireAfterExplosion(false);
/*  81 */         bakuhatsu_explosion.setDamageOwner(true);
/*  82 */         bakuhatsu_explosion.setDamageEntities(true);
/*  83 */         bakuhatsu_explosion.doExplosion();
/*     */         break;
/*     */       case IMPACT_WOLF:
/*  86 */         wolf_explosion = createExplosion(func_234616_v_(), this.field_70170_p, block.func_177958_n(), block.func_177956_o(), block.func_177952_p(), 3.0F);
/*  87 */         wolf_explosion.setExplosionSound(true);
/*  88 */         wolf_explosion.setDestroyBlocks(true);
/*  89 */         wolf_explosion.setFireAfterExplosion(true);
/*  90 */         wolf_explosion.setDamageOwner(true);
/*  91 */         wolf_explosion.setDamageEntities(true);
/*  92 */         wolf_explosion.doExplosion();
/*     */         break;
/*     */     } 
/*     */   } private void onEntityImpactEvent(LivingEntity entity) {
/*     */     int i;
/*     */     ExplosionAbility bakuhatsu_explosion;
/*     */     Vector3d speed;
/*     */     ExplosionAbility wolf_explosion;
/* 100 */     switch (this.type) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case TAKE_JAVELIN:
/* 106 */         for (i = 0; i < 7; i++) {
/* 107 */           entity.field_70172_ad = 0;
/* 108 */           BambooPillarEntity pillar = new BambooPillarEntity(entity.field_70170_p, entity);
/* 109 */           pillar.field_70177_z = 90.0F;
/* 110 */           pillar.func_70107_b(entity.func_226277_ct_() + Math.random(), entity.func_226278_cu_(), entity.func_226281_cx_() + Math.random());
/* 111 */           AbilityHelper.setDeltaMovement((Entity)pillar, 0.0D, 0.4D, 0.0D);
/* 112 */           entity.field_70170_p.func_217376_c((Entity)pillar);
/* 113 */           entity.field_70172_ad = 0;
/* 114 */           entity.func_70097_a((DamageSource)pillar.getDamageSource(), 1.0F);
/*     */         } 
/*     */         break;
/*     */       case BAKUHATSU:
/* 118 */         bakuhatsu_explosion = createExplosion(func_234616_v_(), this.field_70170_p, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), 1.0F);
/* 119 */         bakuhatsu_explosion.setExplosionSound(true);
/* 120 */         bakuhatsu_explosion.setDestroyBlocks(true);
/* 121 */         bakuhatsu_explosion.setFireAfterExplosion(false);
/* 122 */         bakuhatsu_explosion.setDamageOwner(true);
/* 123 */         bakuhatsu_explosion.setDamageEntities(true);
/* 124 */         bakuhatsu_explosion.doExplosion();
/*     */         break;
/*     */       case TRAMPOLIA:
/* 127 */         speed = WyHelper.propulsion(entity, 2.0D, 2.0D);
/* 128 */         AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, 0.8D, speed.field_72449_c);
/*     */         break;
/*     */       case IMPACT_WOLF:
/* 131 */         wolf_explosion = createExplosion(func_234616_v_(), this.field_70170_p, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), 3.0F);
/* 132 */         wolf_explosion.setExplosionSound(true);
/* 133 */         wolf_explosion.setDestroyBlocks(true);
/* 134 */         wolf_explosion.setFireAfterExplosion(true);
/* 135 */         wolf_explosion.setDamageOwner(true);
/* 136 */         wolf_explosion.setDamageEntities(true);
/* 137 */         wolf_explosion.doExplosion();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum PopGreenType
/*     */   {
/* 147 */     NONE, DEVIL, RAFFLESIA, TAKE_JAVELIN, BAKUHATSU, HUMANDRAKE, TRAMPOLIA, IMPACT_WOLF;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\extra\PopGreenProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */