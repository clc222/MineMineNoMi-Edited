/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.hana;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.entities.SUpdateEntityOwnerPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class HanaHandsEntity extends Entity {
/*  27 */   private static final DataParameter<Integer> TYPE = EntityDataManager.func_187226_a(HanaHandsEntity.class, DataSerializers.field_187192_b);
/*     */   
/*  29 */   private int warmupDelayTicks = 30;
/*  30 */   private int lifeTicks = 22;
/*     */   
/*     */   private float damage;
/*     */   private boolean sentEvent;
/*     */   private boolean clientSideAttackStarted;
/*     */   private LivingEntity caster;
/*     */   private LivingEntity target;
/*     */   private UUID casterUuid;
/*     */   private Ability parentAbility;
/*     */   
/*     */   public HanaHandsEntity(EntityType type, World world) {
/*  41 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public HanaHandsEntity(World world) {
/*  46 */     this((EntityType)HanaProjectiles.HANDS.get(), world);
/*     */   }
/*     */ 
/*     */   
/*     */   public HanaHandsEntity(World world, Ability ability) {
/*  51 */     this(world);
/*  52 */     this.parentAbility = ability;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCaster(LivingEntity caster) {
/*  57 */     this.caster = caster;
/*  58 */     this.casterUuid = caster.func_110124_au();
/*     */   }
/*     */ 
/*     */   
/*     */   public LivingEntity getCaster() {
/*  63 */     if (this.caster == null && this.casterUuid != null && this.field_70170_p instanceof ServerWorld) {
/*     */       
/*  65 */       Entity entity = ((ServerWorld)this.field_70170_p).func_217461_a(this.casterUuid);
/*  66 */       if (entity instanceof LivingEntity)
/*     */       {
/*  68 */         this.caster = (LivingEntity)entity;
/*     */       }
/*     */     } 
/*     */     
/*  72 */     return this.caster;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTarget(LivingEntity target) {
/*  77 */     this.target = target;
/*  78 */     func_70107_b(target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWarmup(int warmup) {
/*  83 */     this.warmupDelayTicks = warmup;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDamage(float damage) {
/*  88 */     this.damage = damage;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setClutch() {
/*  93 */     func_184212_Q().func_187227_b(TYPE, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSlap() {
/*  98 */     func_184212_Q().func_187227_b(TYPE, Integer.valueOf(1));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHandsType() {
/* 103 */     return ((Integer)func_184212_Q().func_187225_a(TYPE)).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 109 */     func_184212_Q().func_187214_a(TYPE, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70037_a(CompoundNBT compound) {
/* 115 */     this.warmupDelayTicks = compound.func_74762_e("Warmup");
/* 116 */     func_184212_Q().func_187227_b(TYPE, Integer.valueOf(compound.func_74762_e("Type")));
/* 117 */     if (compound.func_186855_b("OwnerUUID")) {
/* 118 */       this.casterUuid = compound.func_186857_a("OwnerUUID");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_213281_b(CompoundNBT compound) {
/* 124 */     compound.func_74768_a("Warmup", this.warmupDelayTicks);
/* 125 */     compound.func_74768_a("Type", ((Integer)func_184212_Q().func_187225_a(TYPE)).intValue());
/* 126 */     if (this.casterUuid != null) {
/* 127 */       compound.func_186854_a("OwnerUUID", this.casterUuid);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 133 */     super.func_70071_h_();
/* 134 */     if (this.field_70170_p.field_72995_K) {
/*     */       
/* 136 */       if (this.clientSideAttackStarted) {
/*     */         
/* 138 */         this.lifeTicks--;
/* 139 */         if (this.lifeTicks == 14)
/*     */         {
/* 141 */           for (int i = 0; i < 12; i++)
/*     */           {
/* 143 */             double d0 = func_226277_ct_() + (this.field_70146_Z.nextDouble() * 2.0D - 1.0D) * func_213311_cf() * 0.5D;
/* 144 */             double d1 = func_226278_cu_() + 0.05D + this.field_70146_Z.nextDouble();
/* 145 */             double d2 = func_226281_cx_() + (this.field_70146_Z.nextDouble() * 2.0D - 1.0D) * func_213311_cf() * 0.5D;
/* 146 */             double d3 = (this.field_70146_Z.nextDouble() * 2.0D - 1.0D) * 0.3D;
/* 147 */             double d4 = 0.3D + this.field_70146_Z.nextDouble() * 0.3D;
/* 148 */             double d5 = (this.field_70146_Z.nextDouble() * 2.0D - 1.0D) * 0.3D;
/* 149 */             this.field_70170_p.func_195594_a((IParticleData)ParticleTypes.field_197614_g, d0, d1 + 1.0D, d2, d3, d4, d5);
/*     */           }
/*     */         
/*     */         }
/*     */       } 
/* 154 */     } else if (--this.warmupDelayTicks < 0) {
/*     */       
/* 156 */       if (this.warmupDelayTicks == -8)
/*     */       {
/* 158 */         if (this.target != null) {
/* 159 */           damage(this.target);
/*     */         }
/*     */       }
/* 162 */       if (!this.sentEvent) {
/*     */         
/* 164 */         if (getCaster() != null)
/* 165 */           WyNetwork.sendToAllAround(new SUpdateEntityOwnerPacket(func_145782_y(), getCaster().func_145782_y()), this); 
/* 166 */         this.field_70170_p.func_72960_a(this, (byte)4);
/* 167 */         this.sentEvent = true;
/*     */       } 
/*     */       
/* 170 */       if (--this.lifeTicks < 0)
/*     */       {
/* 172 */         func_70106_y();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void damage(LivingEntity target) {
/* 179 */     LivingEntity caster = getCaster();
/*     */     
/* 181 */     if (target.func_70089_S() && !target.func_190530_aW() && caster != null && target != caster) {
/* 182 */       this.parentAbility.getComponent(ModAbilityKeys.DAMAGE).ifPresent(comp -> comp.hurtTarget(caster, target, this.damage));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void func_70103_a(byte id) {
/* 193 */     super.func_70103_a(id);
/* 194 */     if (id == 4) {
/*     */       
/* 196 */       this.clientSideAttackStarted = true;
/* 197 */       if (!func_174814_R())
/*     */       {
/* 199 */         this.field_70170_p.func_184134_a(func_226277_ct_(), func_226278_cu_(), func_226281_cx_(), (SoundEvent)ModSounds.SNAP_SFX.get(), func_184176_by(), 2.0F, this.field_70146_Z.nextFloat() * 0.2F + 0.85F, false);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public float getAnimationProgress(float partialTicks) {
/* 208 */     if (!this.clientSideAttackStarted) {
/* 209 */       return 0.0F;
/*     */     }
/*     */     
/* 212 */     int i = this.lifeTicks - 2;
/* 213 */     return (i <= 0) ? 1.0F : (1.0F - (i - partialTicks) / 20.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 220 */     return NetworkHooks.getEntitySpawningPacket(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\hana\HanaHandsEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */