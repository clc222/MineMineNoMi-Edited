/*     */ package xyz.pixelatedw.mineminenomi.blocks.tileentities;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.SUpdateTileEntityPacket;
/*     */ import net.minecraft.tileentity.ITickableTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityType;
/*     */ import net.minecraft.util.Util;
/*     */ import xyz.pixelatedw.mineminenomi.api.WantedPosterData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTileEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ 
/*     */ public class WantedPosterTileEntity extends TileEntity implements ITickableTileEntity {
/*     */   private WantedPosterData wantedPosterData;
/*  20 */   private UUID uuid = Util.field_240973_b_;
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
/*     */   public WantedPosterTileEntity() {
/*  33 */     super((TileEntityType)ModTileEntities.WANTED_POSTER.get());
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
/*     */   public void func_73660_a() {}
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
/*     */   public void setWantedPosterData(WantedPosterData data) {
/*  68 */     this.wantedPosterData = data;
/*     */   }
/*     */ 
/*     */   
/*     */   public WantedPosterData getWantedPosterData() {
/*  73 */     if (this.wantedPosterData == null) {
/*  74 */       this.wantedPosterData = WantedPosterData.empty();
/*     */     }
/*  76 */     return this.wantedPosterData;
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
/*     */   public boolean isPirate() {
/* 156 */     if (this.wantedPosterData == null) {
/* 157 */       return false;
/*     */     }
/* 159 */     return this.wantedPosterData.getFaction().equals(ModValues.PIRATE);
/*     */   }
/*     */   
/*     */   public boolean isRevolutionary() {
/* 163 */     if (this.wantedPosterData == null) {
/* 164 */       return false;
/*     */     }
/* 166 */     return this.wantedPosterData.getFaction().equals(ModValues.REVOLUTIONARY);
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
/*     */   public CompoundNBT func_189515_b(CompoundNBT nbt) {
/* 182 */     super.func_189515_b(nbt);
/*     */     
/* 184 */     if (this.wantedPosterData == null) {
/* 185 */       return nbt;
/*     */     }
/*     */     
/* 188 */     nbt.func_218657_a("Data", (INBT)this.wantedPosterData.write());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 210 */     return nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_230337_a_(BlockState state, CompoundNBT nbt) {
/* 216 */     super.func_230337_a_(state, nbt);
/*     */     
/* 218 */     if (nbt.func_74764_b("Data")) {
/* 219 */       this.wantedPosterData = WantedPosterData.from(nbt.func_74775_l("Data"));
/*     */     }
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
/*     */   
/*     */   public CompoundNBT func_189517_E_() {
/* 240 */     return func_189515_b(new CompoundNBT());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public SUpdateTileEntityPacket func_189518_D_() {
/* 247 */     CompoundNBT nbttagcompound = new CompoundNBT();
/* 248 */     func_189515_b(nbttagcompound);
/* 249 */     return new SUpdateTileEntityPacket(this.field_174879_c, 9, func_189517_E_());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
/* 255 */     func_230337_a_(null, pkt.func_148857_g());
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\tileentities\WantedPosterTileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */