/*     */ package xyz.pixelatedw.mineminenomi.entities;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.item.FallingBlockEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.ProtectedArea;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ProtectedAreasData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.suna.SablesNewParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class TornadoEntity extends Entity implements IEntityAdditionalSpawnData {
/*  38 */   private SablesNewParticleEffect.Details details = new SablesNewParticleEffect.Details(); private LivingEntity owner;
/*  39 */   private int maxLife = 60;
/*  40 */   private float size = 1.0F;
/*  41 */   private float speed = 1.0F;
/*  42 */   private Vector3d vector = null;
/*  43 */   private List<BlockState> states = new ArrayList<>();
/*     */   
/*     */   public TornadoEntity(World level, LivingEntity entity) {
/*  46 */     super((EntityType)ModEntities.TORNADO.get(), level);
/*  47 */     this.owner = entity;
/*     */   }
/*     */   
/*     */   public TornadoEntity(EntityType<?> type, World pLevel) {
/*  51 */     super(type, pLevel);
/*     */   }
/*     */   
/*     */   public void setMaxLife(int maxLife) {
/*  55 */     this.maxLife = maxLife;
/*     */   }
/*     */   
/*     */   public void setSize(float size) {
/*  59 */     this.size = size;
/*     */   }
/*     */   
/*     */   public void setSpeed(float speed) {
/*  63 */     this.speed = speed;
/*     */   }
/*     */   
/*     */   public int getMaxLife() {
/*  67 */     return this.maxLife;
/*     */   }
/*     */   
/*     */   public float getSize() {
/*  71 */     return this.size;
/*     */   }
/*     */   
/*     */   public float getSpeed() {
/*  75 */     return this.speed;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  85 */     super.func_70071_h_();
/*  86 */     if (!this.field_70170_p.field_72995_K) {
/*  87 */       if (this.maxLife > 0 && this.field_70173_aa >= this.maxLife) {
/*  88 */         func_70106_y();
/*     */         
/*     */         return;
/*     */       } 
/*  92 */       if (this.owner == null) {
/*  93 */         func_70106_y();
/*     */         
/*     */         return;
/*     */       } 
/*  97 */       if (this.field_70173_aa % 40 == 0) {
/*  98 */         this.details.setSize(this.size);
/*  99 */         WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.SABLES_NEW.get(), this, func_226277_ct_(), func_226278_cu_(), func_226281_cx_(), (ParticleEffect.Details)this.details);
/*     */       } 
/*     */       
/* 102 */       AxisAlignedBB box = (new AxisAlignedBB(func_233580_cy_())).func_72314_b((this.size / 2.0F), this.size, (this.size / 2.0F));
/*     */       
/* 104 */       for (Entity entity : this.field_70170_p.func_175647_a(Entity.class, box, e -> (e != this.owner))) {
/* 105 */         double px = (entity.func_213322_ci()).field_72450_a + (func_226277_ct_() - entity.func_226277_ct_()) / 25.0D;
/* 106 */         double py = (entity.func_213322_ci()).field_72448_b + (func_226278_cu_() + (this.size / 2.0F) - entity.func_226278_cu_()) / 25.0D;
/* 107 */         double pz = (entity.func_213322_ci()).field_72449_c + (func_226281_cx_() - entity.func_226281_cx_()) / 25.0D;
/* 108 */         AbilityHelper.setDeltaMovement(entity, px, py, pz);
/*     */         
/* 110 */         if (func_70032_d(entity) < 2.0F) {
/* 111 */           entity.func_70097_a(DamageSource.field_188406_j, getSize() / 3.0F);
/*     */         }
/*     */         
/* 114 */         if (entity instanceof FallingBlockEntity) {
/* 115 */           FallingBlockEntity falling = (FallingBlockEntity)entity;
/* 116 */           this.states.add(falling.func_195054_l());
/*     */         } 
/*     */       } 
/*     */       
/* 120 */       ProtectedArea area = ProtectedAreasData.get(this.field_70170_p).getProtectedArea((int)func_226277_ct_(), (int)func_226278_cu_(), (int)func_226281_cx_());
/* 121 */       if (area == null && 
/* 122 */         CommonConfig.INSTANCE.isAbilityGriefingEnabled()) {
/* 123 */         for (double x = box.field_72340_a; x < box.field_72336_d; x++) {
/* 124 */           double y; for (y = box.field_72338_b; y < box.field_72337_e; y++) {
/* 125 */             double z; for (z = box.field_72339_c; z < box.field_72334_f; z++) {
/* 126 */               BlockPos blockPos = new BlockPos(x, y, z);
/* 127 */               BlockState state = this.field_70170_p.func_180495_p(blockPos);
/*     */               
/* 129 */               boolean hasAirAbove = (this.field_70170_p.func_180495_p(blockPos.func_177984_a()).func_177230_c() == Blocks.field_150350_a);
/*     */               
/* 131 */               if (!state.func_196958_f() && this.field_70146_Z.nextFloat() > 0.9995D && !DefaultProtectionRules.CORE_FOLIAGE_ORE.isBanned(state) && hasAirAbove) {
/*     */                 
/* 133 */                 FallingBlockEntity fallingBlock = new FallingBlockEntity(this.field_70170_p, x, y, z, state);
/*     */                 
/* 135 */                 AbilityHelper.setDeltaMovement((Entity)fallingBlock, 0.0D, WyHelper.randomDouble() * 2.0D, 0.0D);
/* 136 */                 fallingBlock.field_145813_c = false;
/* 137 */                 fallingBlock.field_145812_b = 1;
/*     */                 
/* 139 */                 this.field_70170_p.func_217376_c((Entity)fallingBlock);
/*     */                 
/* 141 */                 WyHelper.setBlockStateInChunk(this.field_70170_p, blockPos, Blocks.field_150350_a.func_176223_P(), 2);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 149 */       if (this.vector != null) {
/* 150 */         this.field_70145_X = true;
/* 151 */         Vector3d dist = func_213303_ch().func_178788_d(this.vector).func_72441_c(0.0D, -1.0D, 0.0D);
/* 152 */         double speedReduction = 20.0D;
/* 153 */         double speed = 0.4D;
/* 154 */         double xSpeed = Math.min(speed, -dist.field_72450_a / speedReduction);
/* 155 */         double zSpeed = Math.min(speed, -dist.field_72449_c / speedReduction);
/* 156 */         func_213315_a(MoverType.SELF, new Vector3d(xSpeed, 0.0D, zSpeed));
/*     */       } 
/*     */       
/* 159 */       if (func_70026_G() && this.field_70173_aa % 20 == 0) {
/* 160 */         func_70106_y();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean func_242278_a(BlockPos pPos, BlockState pState) {
/* 168 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntitySize func_213305_a(Pose pose) {
/* 173 */     EntitySize newSize = func_200600_R().func_220334_j().func_220313_a(this.size / 2.0F);
/* 174 */     return newSize;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70037_a(CompoundNBT nbt) {
/* 179 */     this.maxLife = nbt.func_74762_e("maxLife");
/* 180 */     this.size = nbt.func_74760_g("size");
/* 181 */     this.speed = nbt.func_74760_g("speed");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_213281_b(CompoundNBT nbt) {
/* 186 */     nbt.func_74768_a("maxLife", this.maxLife);
/* 187 */     nbt.func_74776_a("size", this.size);
/* 188 */     nbt.func_74776_a("speed", this.speed);
/*     */   }
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 193 */     return NetworkHooks.getEntitySpawningPacket(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeSpawnData(PacketBuffer buf) {
/* 198 */     buf.writeFloat(this.size);
/* 199 */     buf.writeFloat(this.speed);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readSpawnData(PacketBuffer buf) {
/* 204 */     this.size = buf.readFloat();
/* 205 */     this.speed = buf.readFloat();
/*     */   }
/*     */   
/*     */   public List<BlockState> getBlocksUsed() {
/* 209 */     return this.states;
/*     */   }
/*     */   
/*     */   public void setVector(Vector3d vector) {
/* 213 */     this.vector = vector;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\TornadoEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */