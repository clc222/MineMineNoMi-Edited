/*     */ package xyz.pixelatedw.mineminenomi.entities;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.effect.LightningBoltEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.entity.projectile.AbstractArrowEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.EntityRayTraceResult;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ 
/*     */ public class SpearEntity
/*     */   extends AbstractArrowEntity implements IEntityAdditionalSpawnData {
/*  37 */   private static final DataParameter<Byte> ID_LOYALTY = EntityDataManager.func_187226_a(SpearEntity.class, DataSerializers.field_187191_a);
/*  38 */   private static final DataParameter<Boolean> ID_FOIL = EntityDataManager.func_187226_a(SpearEntity.class, DataSerializers.field_187198_h);
/*     */   
/*     */   private ItemStack itemStack;
/*     */   private float attackDamage;
/*     */   private boolean dealtDamage;
/*     */   public int clientSideReturnTickCount;
/*     */   
/*     */   public SpearEntity(EntityType<?> type, World world) {
/*  46 */     super(type, world);
/*     */   }
/*     */   
/*     */   public SpearEntity(PlayerEntity thrower, ItemStack stack) {
/*  50 */     super((EntityType)ModEntities.THROWING_SPEAR.get(), (LivingEntity)thrower, thrower.field_70170_p);
/*  51 */     this.itemStack = stack.func_77946_l();
/*  52 */     this.field_70180_af.func_187227_b(ID_LOYALTY, Byte.valueOf((byte)EnchantmentHelper.func_203191_f(stack)));
/*  53 */     this.field_70180_af.func_187227_b(ID_FOIL, Boolean.valueOf(stack.func_77962_s()));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/*  58 */     super.func_70088_a();
/*  59 */     this.field_70180_af.func_187214_a(ID_LOYALTY, Byte.valueOf((byte)0));
/*  60 */     this.field_70180_af.func_187214_a(ID_FOIL, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  65 */     if (this.field_184552_b > 4) {
/*  66 */       this.dealtDamage = true;
/*     */     }
/*     */     
/*  69 */     Entity entity = func_234616_v_();
/*  70 */     if ((this.dealtDamage || func_203047_q()) && entity != null) {
/*  71 */       int i = ((Byte)this.field_70180_af.func_187225_a(ID_LOYALTY)).byteValue();
/*  72 */       if (i > 0 && !isAcceptibleReturnOwner()) {
/*  73 */         if (!this.field_70170_p.field_72995_K && this.field_70251_a == AbstractArrowEntity.PickupStatus.ALLOWED) {
/*  74 */           func_70099_a(func_184550_j(), 0.1F);
/*     */         }
/*     */         
/*  77 */         func_70106_y();
/*     */       }
/*  79 */       else if (i > 0) {
/*  80 */         func_203045_n(true);
/*  81 */         Vector3d vector3d = new Vector3d(entity.func_226277_ct_() - func_226277_ct_(), entity.func_226280_cw_() - func_226278_cu_(), entity.func_226281_cx_() - func_226281_cx_());
/*  82 */         func_226288_n_(func_226277_ct_(), func_226278_cu_() + vector3d.field_72448_b * 0.015D * i, func_226281_cx_());
/*  83 */         if (this.field_70170_p.field_72995_K) {
/*  84 */           this.field_70137_T = func_226278_cu_();
/*     */         }
/*     */         
/*  87 */         double d0 = 0.05D * i;
/*  88 */         AbilityHelper.setDeltaMovement((Entity)this, func_213322_ci().func_186678_a(0.95D).func_178787_e(vector3d.func_72432_b().func_186678_a(d0)));
/*  89 */         if (this.clientSideReturnTickCount == 0) {
/*  90 */           func_184185_a(SoundEvents.field_203270_il, 10.0F, 1.0F);
/*     */         }
/*     */         
/*  93 */         this.clientSideReturnTickCount++;
/*     */       } 
/*     */     } 
/*     */     
/*  97 */     super.func_70071_h_();
/*     */   }
/*     */   
/*     */   private boolean isAcceptibleReturnOwner() {
/* 101 */     Entity entity = func_234616_v_();
/* 102 */     if (entity != null && entity.func_70089_S()) {
/* 103 */       return (!(entity instanceof ServerPlayerEntity) || !entity.func_175149_v());
/*     */     }
/*     */     
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_184550_j() {
/* 112 */     return this.itemStack.func_77946_l();
/*     */   }
/*     */   
/*     */   public ItemStack getItemUsed() {
/* 116 */     return this.itemStack;
/*     */   }
/*     */   
/*     */   public void setAttackDamage(float damage) {
/* 120 */     this.attackDamage = damage;
/*     */   }
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean isFoil() {
/* 125 */     return ((Boolean)this.field_70180_af.func_187225_a(ID_FOIL)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected EntityRayTraceResult func_213866_a(Vector3d pStartVec, Vector3d pEndVec) {
/* 131 */     return this.dealtDamage ? null : super.func_213866_a(pStartVec, pEndVec);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_213868_a(EntityRayTraceResult pResult) {
/* 139 */     Entity entity = pResult.func_216348_a();
/* 140 */     float f = this.attackDamage;
/* 141 */     if (entity instanceof LivingEntity && !entity.field_70170_p.field_72995_K) {
/* 142 */       LivingEntity livingentity = (LivingEntity)entity;
/* 143 */       f += EnchantmentHelper.func_152377_a(this.itemStack, livingentity.func_70668_bt());
/*     */     } 
/*     */     
/* 146 */     Entity entity1 = func_234616_v_();
/* 147 */     DamageSource damagesource = DamageSource.func_203096_a((Entity)this, (entity1 == null) ? (Entity)this : entity1);
/* 148 */     this.dealtDamage = true;
/* 149 */     SoundEvent soundevent = SoundEvents.field_203268_ij;
/* 150 */     if (entity.func_70097_a(damagesource, f)) {
/* 151 */       if (entity.func_200600_R() == EntityType.field_200803_q) {
/*     */         return;
/*     */       }
/*     */       
/* 155 */       if (entity instanceof LivingEntity) {
/* 156 */         LivingEntity livingentity1 = (LivingEntity)entity;
/* 157 */         if (entity1 instanceof LivingEntity) {
/* 158 */           EnchantmentHelper.func_151384_a(livingentity1, entity1);
/* 159 */           EnchantmentHelper.func_151385_b((LivingEntity)entity1, (Entity)livingentity1);
/*     */         } 
/*     */         
/* 162 */         func_184548_a(livingentity1);
/*     */       } 
/*     */     } 
/*     */     
/* 166 */     AbilityHelper.setDeltaMovement((Entity)this, func_213322_ci().func_216372_d(-0.01D, -0.1D, -0.01D));
/* 167 */     float f1 = 1.0F;
/* 168 */     if (this.field_70170_p instanceof net.minecraft.world.server.ServerWorld && this.field_70170_p.func_72911_I() && EnchantmentHelper.func_203192_h(this.itemStack)) {
/* 169 */       BlockPos blockpos = entity.func_233580_cy_();
/* 170 */       if (this.field_70170_p.func_226660_f_(blockpos)) {
/* 171 */         LightningBoltEntity lightningboltentity = (LightningBoltEntity)EntityType.field_200728_aG.func_200721_a(this.field_70170_p);
/* 172 */         lightningboltentity.func_233576_c_(Vector3d.func_237492_c_((Vector3i)blockpos));
/* 173 */         lightningboltentity.func_204809_d((entity1 instanceof ServerPlayerEntity) ? (ServerPlayerEntity)entity1 : null);
/* 174 */         this.field_70170_p.func_217376_c((Entity)lightningboltentity);
/* 175 */         soundevent = SoundEvents.field_203275_iq;
/* 176 */         f1 = 5.0F;
/*     */       } 
/*     */     } 
/*     */     
/* 180 */     func_184185_a(soundevent, f1, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEvent func_213867_k() {
/* 188 */     return SoundEvents.field_203269_ik;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70100_b_(PlayerEntity pEntity) {
/* 196 */     Entity entity = func_234616_v_();
/* 197 */     if (entity == null || entity.func_110124_au() == pEntity.func_110124_au()) {
/* 198 */       super.func_70100_b_(pEntity);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70037_a(CompoundNBT pCompound) {
/* 207 */     super.func_70037_a(pCompound);
/* 208 */     if (pCompound.func_150297_b("Item", 10)) {
/* 209 */       this.itemStack = ItemStack.func_199557_a(pCompound.func_74775_l("Item"));
/*     */     }
/*     */     
/* 212 */     this.dealtDamage = pCompound.func_74767_n("DealtDamage");
/* 213 */     this.field_70180_af.func_187227_b(ID_LOYALTY, Byte.valueOf((byte)EnchantmentHelper.func_203191_f(this.itemStack)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213281_b(CompoundNBT pCompound) {
/* 218 */     super.func_213281_b(pCompound);
/* 219 */     pCompound.func_218657_a("Item", (INBT)this.itemStack.func_77955_b(new CompoundNBT()));
/* 220 */     pCompound.func_74757_a("DealtDamage", this.dealtDamage);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_225516_i_() {
/* 225 */     int i = ((Byte)this.field_70180_af.func_187225_a(ID_LOYALTY)).byteValue();
/* 226 */     if (this.field_70251_a != AbstractArrowEntity.PickupStatus.ALLOWED || i <= 0) {
/* 227 */       super.func_225516_i_();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float func_203044_p() {
/* 234 */     return 0.99F;
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean func_145770_h(double pX, double pY, double pZ) {
/* 240 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 245 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeSpawnData(PacketBuffer buffer) {
/* 251 */     buffer.writeItemStack(this.itemStack, false);
/* 252 */     buffer.writeInt((func_234616_v_() != null) ? func_234616_v_().func_145782_y() : -1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readSpawnData(PacketBuffer data) {
/* 258 */     this.itemStack = data.func_150791_c();
/* 259 */     int ownerId = data.readInt();
/* 260 */     if (ownerId >= 0)
/* 261 */       func_212361_a(this.field_70170_p.func_73045_a(ownerId)); 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\SpearEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */