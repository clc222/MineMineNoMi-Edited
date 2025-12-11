/*     */ package xyz.pixelatedw.mineminenomi.entities;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.command.arguments.EntityAnchorArgument;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.util.EntityPredicates;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.items.VivreCardItem;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class VivreCardEntity extends Entity {
/*     */   @Nullable
/*     */   private LivingEntity owner;
/*     */   private UUID ownerUUID;
/*  35 */   private final double speedLimit = 0.001D;
/*     */ 
/*     */   
/*     */   public VivreCardEntity(EntityType type, World world) {
/*  39 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public VivreCardEntity(World worldIn) {
/*  44 */     this((EntityType)ModEntities.VIVRE_CARD.get(), worldIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwner(UUID uuid) {
/*  49 */     this.ownerUUID = uuid;
/*  50 */     this.owner = (LivingEntity)((ServerWorld)this.field_70170_p).func_217461_a(this.ownerUUID);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  56 */     super.func_70071_h_();
/*     */     
/*  58 */     if (this.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*  61 */     if (!func_233570_aj_()) {
/*  62 */       func_213315_a(MoverType.SELF, new Vector3d(0.0D, -0.1D, 0.0D));
/*     */     }
/*  64 */     if (this.ownerUUID == null || func_70027_ad()) {
/*     */       
/*  66 */       func_70106_y();
/*     */       
/*     */       return;
/*     */     } 
/*  70 */     if (this.owner == null) {
/*     */       return;
/*     */     }
/*  73 */     if (this.owner.func_110143_aJ() <= 0.0F) {
/*     */       
/*  75 */       WyHelper.spawnParticles((IParticleData)ParticleTypes.field_197631_x, (ServerWorld)this.field_70170_p, func_226277_ct_(), func_226278_cu_() + 0.3D, func_226281_cx_());
/*  76 */       func_70106_y();
/*     */     } 
/*     */     
/*  79 */     double posX = func_226277_ct_() - this.owner.func_226277_ct_();
/*  80 */     double posZ = func_226281_cx_() - this.owner.func_226281_cx_();
/*     */     
/*  82 */     getClass(); if (posX > 0.0D && posX > 0.001D)
/*  83 */     { getClass(); posX = 0.001D; }
/*  84 */     else { getClass(); if (posX < 0.0D && posX < -0.001D) {
/*  85 */         getClass(); posX = -0.001D;
/*     */       }  }
/*  87 */      getClass(); if (posZ > 0.0D && posZ > 0.001D)
/*  88 */     { getClass(); posZ = 0.001D; }
/*  89 */     else { getClass(); if (posZ < 0.0D && posZ < -0.001D) {
/*  90 */         getClass(); posZ = -0.001D;
/*     */       }  }
/*  92 */      func_213315_a(MoverType.SELF, new Vector3d(-posX, 0.0D, -posZ));
/*     */     
/*  94 */     func_200602_a(EntityAnchorArgument.Type.EYES, this.owner.func_213303_ch());
/*     */     
/*  96 */     if (this.field_70173_aa > 40) {
/*     */       
/*  98 */       double radius = 0.5D;
/*  99 */       AxisAlignedBB aabb = (new AxisAlignedBB(func_233580_cy_())).func_72314_b(radius, radius, radius);
/* 100 */       List<PlayerEntity> list = new ArrayList<>();
/* 101 */       list.addAll(this.field_70170_p.func_175647_a(PlayerEntity.class, aabb, EntityPredicates.field_180132_d.and(EntityPredicates.field_94557_a)));
/*     */       
/* 103 */       Iterator<PlayerEntity> iterator = list.iterator(); if (iterator.hasNext()) { PlayerEntity player = iterator.next();
/*     */         
/* 105 */         ItemStack stack = new ItemStack((IItemProvider)ModItems.VIVRE_CARD.get());
/* 106 */         ((VivreCardItem)stack.func_77973_b()).setOwner(stack, this.owner);
/* 107 */         player.func_191521_c(stack);
/* 108 */         func_70106_y(); }
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_213281_b(CompoundNBT compound) {
/* 120 */     compound.func_74778_a("OwnerUUID", this.ownerUUID.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70037_a(CompoundNBT compound) {
/* 126 */     if (compound.func_150297_b("OwnerUUID", 8)) {
/* 127 */       setOwner(UUID.fromString(compound.func_74779_i("OwnerUUID")));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 133 */     return NetworkHooks.getEntitySpawningPacket(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\VivreCardEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */