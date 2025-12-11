/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.jiki;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.jiki.JikiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ 
/*     */ public class GenocideRaidEffectEntity
/*     */   extends Entity
/*     */   implements IEntityAdditionalSpawnData
/*     */ {
/*     */   private LivingEntity owner;
/*     */   private LivingEntity target;
/*  26 */   private List<ItemStack> magneticItems = new ArrayList<>();
/*     */   
/*     */   public GenocideRaidEffectEntity(EntityType type, World world) {
/*  29 */     super(type, world);
/*  30 */     func_189654_d(true);
/*     */   }
/*     */   
/*     */   public GenocideRaidEffectEntity(World level) {
/*  34 */     super((EntityType)JikiProjectiles.GENOCIDE_RAID_EFFECT.get(), level);
/*  35 */     func_189654_d(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  45 */     if (!this.field_70170_p.field_72995_K) {
/*  46 */       if (this.field_70173_aa > 100) {
/*  47 */         func_174812_G();
/*     */         
/*     */         return;
/*     */       } 
/*  51 */       if (this.owner == null) {
/*  52 */         func_70106_y();
/*     */         
/*     */         return;
/*     */       } 
/*  56 */       if (this.target == null || !this.target.func_70089_S()) {
/*  57 */         func_174812_G();
/*     */         
/*     */         return;
/*     */       } 
/*  61 */       func_70634_a(this.target.func_226277_ct_(), this.target.func_226278_cu_() + 1.0D, this.target.func_226281_cx_());
/*     */     } 
/*  63 */     super.func_70071_h_();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_174812_G() {
/*  68 */     if (this.owner != null) {
/*  69 */       ItemStack stack = this.magneticItems.get(0);
/*  70 */       ItemsHelper.itemBreakParticles(this.field_70170_p, func_213303_ch(), 20, stack);
/*  71 */       this.field_70170_p.func_184133_a(null, func_233580_cy_(), SoundEvents.field_187635_cQ, func_184176_by(), 0.5F, 1.0F);
/*  72 */       JikiHelper.dropComponentItems(this.owner, func_213303_ch(), this.magneticItems);
/*     */     } 
/*  74 */     super.func_174812_G();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70037_a(CompoundNBT pCompound) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_213281_b(CompoundNBT pCompound) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOwner(LivingEntity owner) {
/*  88 */     this.owner = owner;
/*     */   }
/*     */   
/*     */   public void setTarget(LivingEntity target) {
/*  92 */     this.target = target;
/*     */   }
/*     */   
/*     */   public LivingEntity getTarget() {
/*  96 */     return this.target;
/*     */   }
/*     */   
/*     */   public LivingEntity getOwner() {
/* 100 */     return this.owner;
/*     */   }
/*     */   
/*     */   public void setItemsUsed(List<ItemStack> items) {
/* 104 */     Collections.shuffle(items);
/* 105 */     this.magneticItems = items;
/*     */   }
/*     */   
/*     */   public List<ItemStack> getItemsUsed() {
/* 109 */     return this.magneticItems;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeSpawnData(PacketBuffer buffer) {
/* 114 */     buffer.writeInt((this.target != null) ? this.target.func_145782_y() : -1);
/* 115 */     buffer.writeInt(this.magneticItems.size());
/* 116 */     for (ItemStack stack : this.magneticItems) {
/* 117 */       buffer.writeItemStack(stack, true);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void readSpawnData(PacketBuffer buffer) {
/* 123 */     int targetId = buffer.readInt();
/* 124 */     if (targetId >= 0) {
/* 125 */       Entity target = this.field_70170_p.func_73045_a(targetId);
/* 126 */       if (target != null && target instanceof LivingEntity) {
/* 127 */         this.target = (LivingEntity)target;
/*     */       }
/*     */     } 
/* 130 */     int size = buffer.readInt();
/* 131 */     this.magneticItems.clear();
/* 132 */     for (int i = 0; i < size; i++) {
/* 133 */       ItemStack stack = buffer.func_150791_c();
/* 134 */       this.magneticItems.add(stack);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 140 */     return NetworkHooks.getEntitySpawningPacket(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\jiki\GenocideRaidEffectEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */