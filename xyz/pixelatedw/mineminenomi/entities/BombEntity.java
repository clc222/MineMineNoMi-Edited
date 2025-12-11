/*     */ package xyz.pixelatedw.mineminenomi.entities;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class BombEntity extends Entity {
/*     */   private LivingEntity owner;
/*  26 */   private int fuseTime = 300; private final Predicate<Entity> targetPredicate;
/*     */   private boolean canDestroyBlocks = false;
/*     */   private boolean explodeOnImpact = false;
/*     */   
/*     */   public BombEntity(EntityType type, World world) {
/*  31 */     super(type, world);
/*  32 */     this.targetPredicate = (entity -> true);
/*     */   }
/*     */   
/*     */   public BombEntity(World world, LivingEntity owner) {
/*  36 */     super((EntityType)ModEntities.BOMB.get(), world);
/*  37 */     this.owner = owner;
/*  38 */     this.targetPredicate = (entity -> entity.equals(owner) ? false : ((entity instanceof BombEntity) ? false : (!ModEntityPredicates.getFriendlyFactions(owner).test(entity))));
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_241849_j(Entity entity) {
/*  57 */     if (entity instanceof BombEntity) {
/*  58 */       return false;
/*     */     }
/*     */     
/*  61 */     return true;
/*     */   }
/*     */   
/*     */   public void setFuseTime(int fuseTime) {
/*  65 */     this.fuseTime = fuseTime;
/*     */   }
/*     */   
/*     */   public void setDestroyBlocks() {
/*  69 */     this.canDestroyBlocks = true;
/*     */   }
/*     */   
/*     */   public void setExplodeOnImpact() {
/*  73 */     this.explodeOnImpact = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  78 */     this.field_70169_q = func_226277_ct_();
/*  79 */     this.field_70167_r = func_226278_cu_();
/*  80 */     this.field_70166_s = func_226281_cx_();
/*     */     
/*  82 */     if (this.field_70173_aa % 3 == 0) {
/*  83 */       this.field_70170_p.func_195594_a((IParticleData)ParticleTypes.field_197631_x, func_226277_ct_(), func_226278_cu_() + 1.0D, func_226281_cx_(), 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */     
/*  86 */     if (!this.field_70170_p.field_72995_K) {
/*  87 */       func_213315_a(MoverType.SELF, func_213322_ci());
/*  88 */       if (!func_189652_ae()) {
/*  89 */         AbilityHelper.setDeltaMovement(this, func_213322_ci().func_72441_c(0.0D, -0.09799999743700027D, 0.0D));
/*  90 */         AbilityHelper.setDeltaMovement(this, func_213322_ci().func_216372_d(0.98D, 0.98D, 0.98D));
/*  91 */         if (this.field_70124_G || this.field_70123_F) {
/*  92 */           AbilityHelper.setDeltaMovement(this, Vector3d.field_186680_a);
/*  93 */           if (this.explodeOnImpact) {
/*  94 */             func_70106_y();
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/*  99 */       if (this.field_70173_aa % 10 == 0) {
/* 100 */         List<LivingEntity> targets = WyHelper.getNearbyEntities(func_213303_ch(), (IWorld)this.field_70170_p, 2.0D, this.targetPredicate, new Class[] { LivingEntity.class });
/* 101 */         if (targets.size() > 0) {
/* 102 */           func_70106_y();
/*     */         }
/*     */       } 
/*     */       
/* 106 */       if (this.field_70173_aa >= this.fuseTime) {
/* 107 */         func_70106_y();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70106_y() {
/* 114 */     if (!this.field_70170_p.field_72995_K) {
/* 115 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)this.owner, this.field_70170_p, func_226277_ct_(), func_226278_cu_(), func_226281_cx_(), 4.0F);
/* 116 */       explosion.setExplosionSound(true);
/* 117 */       explosion.setDamageOwner(false);
/* 118 */       explosion.setDestroyBlocks(this.canDestroyBlocks);
/* 119 */       explosion.setFireAfterExplosion(false);
/* 120 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/* 121 */       explosion.setDamageEntities(true);
/* 122 */       explosion.doExplosion();
/*     */     } 
/* 124 */     super.func_70106_y();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {}
/*     */ 
/*     */   
/*     */   public boolean func_70104_M() {
/* 133 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_241845_aY() {
/* 138 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70037_a(CompoundNBT compound) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_213281_b(CompoundNBT compound) {}
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 151 */     return NetworkHooks.getEntitySpawningPacket(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\BombEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */