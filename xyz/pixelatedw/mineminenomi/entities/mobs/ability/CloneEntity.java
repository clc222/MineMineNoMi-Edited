/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.ability;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.PanicGoal;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.FactionHurtByTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ 
/*     */ public abstract class CloneEntity
/*     */   extends CreatureEntity
/*     */   implements IEntityAdditionalSpawnData {
/*     */   @Nullable
/*     */   private UUID ownerId;
/*     */   @Nullable
/*     */   private LivingEntity owner;
/*     */   private boolean isTextured;
/*  34 */   private int maxAliveTicks = 200;
/*     */   
/*     */   public CloneEntity(EntityType type, World world) {
/*  37 */     super(type, world);
/*  38 */     this.field_70728_aV = 0;
/*     */     
/*  40 */     if (world != null && !world.field_72995_K) {
/*  41 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/*     */       
/*  43 */       props.setHeart(false);
/*  44 */       props.setShadow(true);
/*  45 */       func_70031_b(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_184651_r() {
/*  51 */     this.field_70714_bg.func_75776_a(1, (Goal)new PanicGoal(this, 1.25D));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/*  56 */     super.func_70088_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  61 */     super.func_70071_h_();
/*  62 */     if (!this.field_70170_p.field_72995_K && getOwner() == null) {
/*  63 */       func_70106_y();
/*     */       
/*     */       return;
/*     */     } 
/*  67 */     func_70604_c(getOwner());
/*     */     
/*  69 */     if (this.field_70173_aa >= this.maxAliveTicks) {
/*  70 */       func_70106_y();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_213333_a(DamageSource source, int looting, boolean recentlyHitIn) {}
/*     */ 
/*     */   
/*     */   public void func_213281_b(CompoundNBT nbt) {
/*  80 */     super.func_213281_b(nbt);
/*  81 */     if (this.ownerId != null) {
/*  82 */       nbt.func_186854_a("ownerId", this.ownerId);
/*     */     }
/*  84 */     nbt.func_74757_a("isTextured", this.isTextured);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70037_a(CompoundNBT nbt) {
/*  89 */     super.func_70037_a(nbt);
/*  90 */     if (nbt.func_74764_b("ownerId")) {
/*  91 */       this.ownerId = nbt.func_186857_a("ownerId");
/*     */     }
/*  93 */     this.isTextured = nbt.func_74767_n("isTextured");
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeSpawnData(PacketBuffer buffer) {
/*  98 */     buffer.func_179252_a(this.ownerId);
/*  99 */     buffer.writeBoolean(this.isTextured);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readSpawnData(PacketBuffer data) {
/* 104 */     this.ownerId = data.func_179253_g();
/* 105 */     this.isTextured = data.readBoolean();
/*     */   }
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 110 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */   
/*     */   public void setOwner(LivingEntity owner) {
/* 114 */     this.owner = owner;
/* 115 */     this.ownerId = owner.func_110124_au();
/* 116 */     IEntityStats stats = EntityStatsCapability.get((LivingEntity)this);
/* 117 */     stats.setFaction(EntityStatsCapability.get(owner).getFaction());
/*     */     
/* 119 */     Predicate<Entity> factionScope = ModEntityPredicates.getEnemyFactions((LivingEntity)this);
/* 120 */     Predicate<Entity> notSame = entity -> !(entity instanceof NightmareSoldierEntity);
/*     */     
/* 122 */     if (factionScope != null) {
/* 123 */       this.field_70715_bh.func_75776_a(1, (Goal)new FactionHurtByTargetGoal(this, factionScope, new Class[0]));
/* 124 */       this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, MobEntity.class, 10, true, true, factionScope.and(notSame)));
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getOwner() {
/* 130 */     if (this.owner == null && this.ownerId != null) {
/* 131 */       this.owner = (LivingEntity)this.field_70170_p.func_217371_b(this.ownerId);
/*     */     }
/* 133 */     return this.owner;
/*     */   }
/*     */   
/*     */   public UUID getOwnerUUID() {
/* 137 */     return this.ownerId;
/*     */   }
/*     */   
/*     */   public void setUseOwnerTexture() {
/* 141 */     this.isTextured = true;
/*     */   }
/*     */   
/*     */   public boolean isUsingOwnerTexture() {
/* 145 */     return this.isTextured;
/*     */   }
/*     */   
/*     */   public void setMaxAliveTicks(int ticks) {
/* 149 */     this.maxAliveTicks = ticks;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\ability\CloneEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */