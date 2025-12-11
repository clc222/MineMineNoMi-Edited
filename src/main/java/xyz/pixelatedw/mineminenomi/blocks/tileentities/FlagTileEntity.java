/*     */ package xyz.pixelatedw.mineminenomi.blocks.tileentities;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.LongArrayNBT;
/*     */ import net.minecraft.nbt.LongNBT;
/*     */ import net.minecraft.network.play.server.SUpdateTileEntityPacket;
/*     */ import net.minecraft.tileentity.ITickableTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityType;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTileEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class FlagTileEntity extends TileEntity implements ITickableTileEntity {
/*  29 */   private UUID ownerUUID = Util.field_240973_b_;
/*  30 */   private ResourceLocation faction = ModValues.EMPTY;
/*     */   private boolean isOnFire;
/*  32 */   private int fireTick = 200;
/*     */   @Nullable
/*     */   private Entity lastAttacker;
/*     */   private BlockPos masterPos;
/*     */   private FlagTileEntity master;
/*     */   private boolean isSub;
/*  38 */   private List<BlockPos> subsList = new ArrayList<>();
/*     */   @Nullable
/*     */   private Crew crew;
/*     */   
/*     */   public FlagTileEntity() {
/*  43 */     super((TileEntityType)ModTileEntities.FLAG.get());
/*     */   }
/*     */   
/*     */   public void setCrew(Crew crew) {
/*  47 */     this.crew = crew;
/*     */   }
/*     */   
/*     */   public Crew getCrew() {
/*  51 */     return this.crew;
/*     */   }
/*     */   
/*     */   public void setOnFire(boolean onFire) {
/*  55 */     this.isOnFire = onFire;
/*  56 */     if (onFire) {
/*  57 */       this.fireTick = 200;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isOnFire() {
/*  62 */     return this.isOnFire;
/*     */   }
/*     */   
/*     */   public void setLastAttacker(Entity attacker) {
/*  66 */     this.lastAttacker = attacker;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Entity getLastAttacker() {
/*  71 */     return this.lastAttacker;
/*     */   }
/*     */   
/*     */   public void setFaction(ResourceLocation factionName) {
/*  75 */     this.faction = factionName;
/*     */   }
/*     */   
/*     */   public void setOwner(@Nullable LivingEntity owner) {
/*  79 */     if (owner != null) {
/*  80 */       this.ownerUUID = owner.func_110124_au();
/*  81 */       this.faction = EntityStatsCapability.get(owner).getFaction();
/*     */     } 
/*     */   }
/*     */   
/*     */   public ResourceLocation getFaction() {
/*  86 */     return this.faction;
/*     */   }
/*     */   
/*     */   public UUID getOwnerUUID() {
/*  90 */     return this.ownerUUID;
/*     */   }
/*     */   
/*     */   public boolean isMaster() {
/*  94 */     return !isSub();
/*     */   }
/*     */   
/*     */   public boolean isSub() {
/*  98 */     return this.isSub;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public FlagTileEntity getMaster() {
/* 103 */     if (!this.isSub) {
/* 104 */       return this;
/*     */     }
/*     */     
/* 107 */     if (this.field_145850_b != null && this.masterPos != null && this.master == null) {
/* 108 */       TileEntity tileEntity = this.field_145850_b.func_175625_s(this.masterPos);
/*     */       
/* 110 */       if (tileEntity instanceof FlagTileEntity) {
/* 111 */         this.master = (FlagTileEntity)tileEntity;
/*     */       }
/*     */     } 
/*     */     
/* 115 */     return this.master;
/*     */   }
/*     */   
/*     */   public void setMaster(FlagTileEntity flagTile) {
/* 119 */     this.masterPos = flagTile.func_174877_v();
/* 120 */     this.master = flagTile;
/* 121 */     this.isSub = true;
/*     */   }
/*     */   
/*     */   public void addSub(BlockPos pos) {
/* 125 */     this.subsList.add(pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73660_a() {
/* 130 */     if (this.field_145850_b == null) {
/*     */       return;
/*     */     }
/*     */     
/* 134 */     if (isOnFire()) {
/* 135 */       if (this.isSub) {
/* 136 */         if (this.field_145850_b.func_72896_J()) {
/* 137 */           this.master.setOnFire(false);
/*     */           
/*     */           return;
/*     */         } 
/* 141 */         this.master.setOnFire(true);
/*     */       } else {
/*     */         
/* 144 */         if (this.field_145850_b.func_72896_J()) {
/* 145 */           setOnFire(false);
/*     */           
/*     */           return;
/*     */         } 
/* 149 */         if (--this.fireTick <= 0) {
/* 150 */           breakAllBlocks(false);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void breakAllBlocks(boolean instabuild) {
/* 157 */     if (isSub()) {
/*     */       return;
/*     */     }
/*     */     
/* 161 */     boolean drop = !this.isOnFire;
/* 162 */     if (instabuild) {
/* 163 */       drop = false;
/*     */     }
/*     */     
/* 166 */     if (func_145831_w() != null) {
/* 167 */       func_145831_w().func_175655_b(func_174877_v(), drop);
/* 168 */       for (BlockPos pos : this.subsList) {
/* 169 */         func_145831_w().func_175655_b(pos, false);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT func_189515_b(CompoundNBT nbt) {
/* 176 */     super.func_189515_b(nbt);
/* 177 */     nbt.func_186854_a("ownerUUID", this.ownerUUID);
/* 178 */     nbt.func_74778_a("faction", this.faction.toString());
/* 179 */     nbt.func_74757_a("isOnFire", this.isOnFire);
/* 180 */     nbt.func_74768_a("fireTicks", this.fireTick);
/* 181 */     nbt.func_74757_a("isSub", this.isSub);
/* 182 */     if (this.isSub) {
/* 183 */       nbt.func_74772_a("masterPos", this.masterPos.func_218275_a());
/*     */     } else {
/*     */       
/* 186 */       LongArrayNBT subs = new LongArrayNBT(new ArrayList());
/* 187 */       for (BlockPos pos : this.subsList) {
/* 188 */         long longPos = pos.func_218275_a();
/* 189 */         subs.add(LongNBT.func_229698_a_(longPos));
/*     */       } 
/* 191 */       nbt.func_218657_a("subs", (INBT)subs);
/*     */     } 
/*     */     
/* 194 */     if (this.crew != null) {
/* 195 */       nbt.func_218657_a("Crew", (INBT)this.crew.write());
/*     */     }
/*     */     
/* 198 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_230337_a_(BlockState state, CompoundNBT nbt) {
/* 203 */     super.func_230337_a_(state, nbt);
/* 204 */     this.ownerUUID = nbt.func_186857_a("ownerUUID");
/* 205 */     this.faction = WyHelper.getResourceLocation(nbt, "faction");
/* 206 */     this.isOnFire = nbt.func_74767_n("isOnFire");
/* 207 */     this.fireTick = nbt.func_74762_e("fireTicks");
/* 208 */     this.isSub = nbt.func_74767_n("isSub");
/* 209 */     if (this.isSub) {
/* 210 */       this.masterPos = BlockPos.func_218283_e(nbt.func_74763_f("masterPos"));
/*     */     } else {
/*     */       
/* 213 */       long[] subs = nbt.func_197645_o("subs");
/* 214 */       for (int i = 0; i < subs.length; i++) {
/* 215 */         BlockPos subPos = BlockPos.func_218283_e(subs[i]);
/* 216 */         this.subsList.add(subPos);
/*     */       } 
/*     */     } 
/*     */     
/* 220 */     if (nbt.func_150297_b("Crew", 10)) {
/* 221 */       Crew crew = Crew.from(nbt.func_74775_l("Crew"));
/* 222 */       setCrew(crew);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT func_189517_E_() {
/* 228 */     return func_189515_b(super.func_189517_E_());
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public double func_145833_n() {
/* 234 */     return 256.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public SUpdateTileEntityPacket func_189518_D_() {
/* 240 */     return new SUpdateTileEntityPacket(this.field_174879_c, 9, func_189517_E_());
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\tileentities\FlagTileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */