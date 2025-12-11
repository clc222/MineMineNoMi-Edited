/*     */ package xyz.pixelatedw.mineminenomi.entities;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.projectile.AbstractArrowEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.math.EntityRayTraceResult;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ 
/*     */ public class ChakramEntity
/*     */   extends AbstractArrowEntity
/*     */   implements IEntityAdditionalSpawnData {
/*     */   private ItemStack itemStack;
/*     */   private float attackDamage;
/*     */   private boolean dealtDamage;
/*     */   public int clientSideReturnTickCount;
/*     */   
/*     */   public ChakramEntity(EntityType<?> type, World world) {
/*  37 */     super(type, world);
/*     */   }
/*     */   
/*     */   public ChakramEntity(LivingEntity thrower, ItemStack stack) {
/*  41 */     super((EntityType)ModEntities.CHAKRAM.get(), thrower, thrower.field_70170_p);
/*  42 */     this.itemStack = stack.func_77946_l();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/*  47 */     super.func_70088_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  52 */     if (this.field_184552_b > 4) {
/*  53 */       this.dealtDamage = true;
/*     */     }
/*     */     
/*  56 */     Entity owner = func_234616_v_();
/*  57 */     if ((this.dealtDamage || func_203047_q()) && owner != null) {
/*  58 */       if (!isAcceptibleReturnOwner()) {
/*  59 */         if (!this.field_70170_p.field_72995_K && this.field_70251_a == AbstractArrowEntity.PickupStatus.ALLOWED) {
/*  60 */           func_70099_a(func_184550_j(), 0.1F);
/*     */         }
/*     */         
/*  63 */         func_70106_y();
/*     */       } else {
/*  65 */         if (owner instanceof net.minecraft.entity.MobEntity && func_70068_e(owner) < 3.5D && this.field_70173_aa > 10) {
/*  66 */           func_70106_y();
/*     */         }
/*     */         
/*  69 */         func_203045_n(true);
/*  70 */         Vector3d vector3d = new Vector3d(owner.func_226277_ct_() - func_226277_ct_(), owner.func_226280_cw_() - func_226278_cu_(), owner.func_226281_cx_() - func_226281_cx_());
/*  71 */         func_226288_n_(func_226277_ct_(), func_226278_cu_() + vector3d.field_72448_b * 0.015D, func_226281_cx_());
/*  72 */         if (this.field_70170_p.field_72995_K) {
/*  73 */           this.field_70137_T = func_226278_cu_();
/*     */         }
/*     */         
/*  76 */         double d0 = 0.1D;
/*  77 */         AbilityHelper.setDeltaMovement((Entity)this, func_213322_ci().func_186678_a(0.95D).func_178787_e(vector3d.func_72432_b().func_186678_a(d0)));
/*     */         
/*  79 */         this.clientSideReturnTickCount++;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*  84 */     if (!func_189652_ae() && !func_203047_q()) {
/*  85 */       Vector3d vector3d4 = func_213322_ci();
/*  86 */       func_213293_j(vector3d4.field_72450_a, vector3d4.field_72448_b + 0.03999999910593033D, vector3d4.field_72449_c);
/*     */     } 
/*     */     
/*  89 */     super.func_70071_h_();
/*     */   }
/*     */   
/*     */   private boolean isAcceptibleReturnOwner() {
/*  93 */     Entity entity = func_234616_v_();
/*  94 */     if (entity != null && entity.func_70089_S()) {
/*  95 */       if (entity instanceof net.minecraft.entity.player.ServerPlayerEntity && !entity.func_175149_v()) {
/*  96 */         return true;
/*     */       }
/*     */       
/*  99 */       return !entity.func_175149_v();
/*     */     } 
/* 101 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_184550_j() {
/* 107 */     return this.itemStack.func_77946_l();
/*     */   }
/*     */   
/*     */   public ItemStack getItemUsed() {
/* 111 */     return this.itemStack;
/*     */   }
/*     */   
/*     */   public void setAttackDamage(float damage) {
/* 115 */     this.attackDamage = damage;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected EntityRayTraceResult func_213866_a(Vector3d pStartVec, Vector3d pEndVec) {
/* 121 */     return this.dealtDamage ? null : super.func_213866_a(pStartVec, pEndVec);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_213868_a(EntityRayTraceResult pResult) {
/* 129 */     Entity entity = pResult.func_216348_a();
/* 130 */     float damage = this.attackDamage;
/* 131 */     if (entity instanceof LivingEntity && !entity.field_70170_p.field_72995_K) {
/* 132 */       LivingEntity livingentity = (LivingEntity)entity;
/* 133 */       damage += EnchantmentHelper.func_152377_a(this.itemStack, livingentity.func_70668_bt());
/*     */     } 
/*     */     
/* 136 */     Entity entity1 = func_234616_v_();
/* 137 */     DamageSource damagesource = DamageSource.func_203096_a((Entity)this, (entity1 == null) ? (Entity)this : entity1);
/* 138 */     this.dealtDamage = true;
/* 139 */     SoundEvent soundevent = SoundEvents.field_203268_ij;
/* 140 */     if (entity.func_70097_a(damagesource, damage)) {
/* 141 */       if (entity.func_200600_R() == EntityType.field_200803_q) {
/*     */         return;
/*     */       }
/*     */       
/* 145 */       if (entity instanceof LivingEntity) {
/* 146 */         LivingEntity livingentity1 = (LivingEntity)entity;
/* 147 */         if (entity1 instanceof LivingEntity) {
/* 148 */           EnchantmentHelper.func_151384_a(livingentity1, entity1);
/* 149 */           EnchantmentHelper.func_151385_b((LivingEntity)entity1, (Entity)livingentity1);
/*     */         } 
/*     */         
/* 152 */         func_184548_a(livingentity1);
/*     */       } 
/*     */     } 
/*     */     
/* 156 */     AbilityHelper.setDeltaMovement((Entity)this, func_213322_ci().func_216372_d(-0.01D, -0.1D, -0.01D));
/* 157 */     func_184185_a(soundevent, 1.0F, 1.5F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEvent func_213867_k() {
/* 165 */     return SoundEvents.field_203269_ik;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70100_b_(PlayerEntity pEntity) {
/* 173 */     Entity entity = func_234616_v_();
/* 174 */     if (entity == null || entity.func_110124_au() == pEntity.func_110124_au()) {
/* 175 */       super.func_70100_b_(pEntity);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70037_a(CompoundNBT pCompound) {
/* 184 */     super.func_70037_a(pCompound);
/* 185 */     if (pCompound.func_150297_b("Item", 10)) {
/* 186 */       this.itemStack = ItemStack.func_199557_a(pCompound.func_74775_l("Item"));
/*     */     }
/*     */     
/* 189 */     this.dealtDamage = pCompound.func_74767_n("DealtDamage");
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213281_b(CompoundNBT pCompound) {
/* 194 */     super.func_213281_b(pCompound);
/* 195 */     pCompound.func_218657_a("Item", (INBT)this.itemStack.func_77955_b(new CompoundNBT()));
/* 196 */     pCompound.func_74757_a("DealtDamage", this.dealtDamage);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_225516_i_() {
/* 201 */     if (this.field_70251_a != AbstractArrowEntity.PickupStatus.ALLOWED) {
/* 202 */       super.func_225516_i_();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_203044_p() {
/* 208 */     return 0.99F;
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean func_145770_h(double pX, double pY, double pZ) {
/* 214 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 219 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeSpawnData(PacketBuffer buffer) {
/* 224 */     buffer.writeItemStack(this.itemStack, false);
/* 225 */     buffer.writeInt((func_234616_v_() != null) ? func_234616_v_().func_145782_y() : -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readSpawnData(PacketBuffer data) {
/* 230 */     this.itemStack = data.func_150791_c();
/* 231 */     int ownerId = data.readInt();
/* 232 */     if (ownerId >= 0)
/* 233 */       func_212361_a(this.field_70170_p.func_73045_a(ownerId)); 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\ChakramEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */