/*     */ package xyz.pixelatedw.mineminenomi.entities;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.projectile.ThrowableEntity;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.EntityRayTraceResult;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.NetType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.items.NetItem;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class NetEntity
/*     */   extends ThrowableEntity
/*     */   implements IEntityAdditionalSpawnData {
/*     */   private static final int NET_TIME = 1200;
/*  39 */   private Optional<LivingEntity> thrower = Optional.empty();
/*     */   private NetItem netItem;
/*  41 */   private NetType type = NetType.NORMAL;
/*     */   
/*     */   public NetEntity(EntityType type, World world) {
/*  44 */     super(type, world);
/*     */   }
/*     */   
/*     */   private NetEntity(EntityType type, World world, @Nullable LivingEntity thrower, NetItem netItem) {
/*  48 */     super(type, world);
/*  49 */     this.thrower = Optional.ofNullable(thrower);
/*  50 */     this.netItem = netItem;
/*  51 */     this.type = netItem.getNetType();
/*     */   }
/*     */   
/*     */   public static NetEntity createNormalNet(World world, @Nullable LivingEntity thrower) {
/*  55 */     return new NetEntity((EntityType)ModEntities.ROPE_NET.get(), world, thrower, (NetItem)ModItems.ROPE_NET.get());
/*     */   }
/*     */   
/*     */   public static NetEntity createKairosekiNet(World world, @Nullable LivingEntity thrower) {
/*  59 */     return new NetEntity((EntityType)ModEntities.KAIROSEKI_NET.get(), world, thrower, (NetItem)ModItems.KAIROSEKI_NET.get());
/*     */   }
/*     */   
/*     */   public static NetEntity createFromItem(World world, @Nullable LivingEntity thrower, NetItem netItem) {
/*  63 */     NetEntity netEntity = null;
/*     */     
/*  65 */     switch (netItem.getNetType())
/*     */     { case KAIROSEKI:
/*  67 */         netEntity = createKairosekiNet(world, thrower);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  75 */         return netEntity; }  netEntity = createNormalNet(world, thrower); return netEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {}
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  84 */     super.func_70071_h_();
/*     */     
/*  86 */     if (!this.field_70170_p.field_72995_K) {
/*  87 */       if (func_70027_ad()) {
/*  88 */         func_70106_y();
/*     */       }
/*     */       
/*  91 */       Vector3i radius = new Vector3i(func_174813_aQ().func_216364_b() / 2.0D, func_174813_aQ().func_216360_c() / 2.0D, func_174813_aQ().func_216362_d() / 2.0D);
/*     */       
/*  93 */       List<Entity> entityList = WyHelper.getNearbyEntities(func_213303_ch(), (IWorld)this.field_70170_p, radius.func_177958_n(), radius.func_177956_o(), radius.func_177952_p(), canHitEntity(), new Class[] { Entity.class });
/*     */       
/*  95 */       Entity entityTarget = entityList.stream().findAny().orElse(null);
/*     */       
/*  97 */       if (entityTarget != null) {
/*  98 */         func_213868_a(new EntityRayTraceResult(entityTarget));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_213868_a(EntityRayTraceResult result) {
/* 105 */     if (!func_70089_S()) {
/*     */       return;
/*     */     }
/*     */     
/* 109 */     if (this.field_70170_p.field_72995_K || this.netItem == null) {
/*     */       return;
/*     */     }
/*     */     
/* 113 */     if (result.func_216348_a() instanceof LivingEntity) {
/* 114 */       LivingEntity living = (LivingEntity)result.func_216348_a();
/*     */       
/* 116 */       boolean hpCheck = false;
/* 117 */       switch (getNetType()) {
/*     */         case KAIROSEKI:
/* 119 */           hpCheck = (living.func_110143_aJ() < 100.0F || living.func_110143_aJ() < WyHelper.percentage(40.0D, living.func_110138_aP()));
/*     */           break;
/*     */         case NORMAL:
/* 122 */           hpCheck = (living.func_110143_aJ() < 50.0F || living.func_110143_aJ() < WyHelper.percentage(20.0D, living.func_110138_aP()));
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 128 */       if (!hpCheck) {
/* 129 */         destroyAndDropItem(func_233580_cy_());
/*     */         
/*     */         return;
/*     */       } 
/* 133 */       if (AbilityHelper.isLogiaBlocking((Entity)this, living) || AbilityHelper.isShieldBlocking(living)) {
/* 134 */         destroyAndDropItem(func_233580_cy_());
/*     */         
/*     */         return;
/*     */       } 
/* 138 */       Iterator<EffectInstance> effects = living.func_70651_bq().iterator();
/* 139 */       while (effects.hasNext()) {
/* 140 */         EffectInstance effectInst = effects.next();
/* 141 */         if (effectInst.func_188419_a() instanceof xyz.pixelatedw.mineminenomi.effects.CaughtInNetEffect) {
/* 142 */           destroyAndDropItem(func_233580_cy_());
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 147 */       living.func_195064_c(new EffectInstance((Effect)this.netItem.getEffect(), 1200, 0));
/* 148 */       func_70106_y();
/*     */     }
/* 150 */     else if (result.func_216348_a() instanceof AbilityProjectileEntity) {
/* 151 */       AbilityProjectileEntity projectile = (AbilityProjectileEntity)result.func_216348_a();
/*     */       
/* 153 */       if (getNetType() == NetType.KAIROSEKI) {
/* 154 */         boolean isProjectileOfDF = false;
/* 155 */         if (projectile.getParent() != null && projectile.getParent().getCategory() == AbilityCategory.DEVIL_FRUITS) {
/* 156 */           isProjectileOfDF = true;
/*     */         }
/*     */         
/* 159 */         if (isProjectileOfDF) {
/* 160 */           projectile.func_70106_y();
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 165 */       if (projectile.getDamage() > 5.0F) {
/* 166 */         func_70106_y();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_230299_a_(BlockRayTraceResult result) {
/* 173 */     super.func_230299_a_(result);
/*     */     
/* 175 */     if (this.field_70170_p.field_72995_K || this.netItem == null) {
/*     */       return;
/*     */     }
/*     */     
/* 179 */     destroyAndDropItem(result.func_216350_a());
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_230298_a_(Entity target) {
/* 184 */     if (target instanceof AbilityProjectileEntity) {
/* 185 */       return true;
/*     */     }
/*     */     
/* 188 */     return super.func_230298_a_(target);
/*     */   }
/*     */   
/*     */   private Predicate<Entity> canHitEntity() {
/* 192 */     return target -> !(target == this);
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
/*     */   private void destroyAndDropItem(BlockPos dropPos) {
/* 206 */     boolean canDrop = true;
/* 207 */     if (this.thrower.isPresent() && this.thrower.get() instanceof PlayerEntity) {
/* 208 */       canDrop = !((PlayerEntity)this.thrower.get()).field_71075_bZ.field_75098_d;
/*     */     }
/*     */     
/* 211 */     if (canDrop) {
/* 212 */       ItemEntity itemEntity = new ItemEntity(this.field_70170_p, dropPos.func_177958_n(), (dropPos.func_177956_o() + 1), dropPos.func_177952_p(), this.netItem.func_190903_i());
/* 213 */       this.field_70170_p.func_217376_c((Entity)itemEntity);
/*     */     } 
/*     */     
/* 216 */     func_70106_y();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_241845_aY() {
/* 221 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_70185_h() {
/* 226 */     return 0.03F;
/*     */   }
/*     */   
/*     */   public NetType getNetType() {
/* 230 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 235 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeSpawnData(PacketBuffer buffer) {
/* 240 */     buffer.writeInt(this.type.ordinal());
/*     */   }
/*     */ 
/*     */   
/*     */   public void readSpawnData(PacketBuffer buffer) {
/* 245 */     int typeId = buffer.readInt();
/* 246 */     this.type = NetType.values()[typeId];
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\NetEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */