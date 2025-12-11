/*    */ package xyz.pixelatedw.mineminenomi.blocks.tileentities;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.network.NetworkManager;
/*    */ import net.minecraft.network.play.server.SUpdateTileEntityPacket;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.tileentity.TileEntityType;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModTileEntities;
/*    */ 
/*    */ public class PoneglyphTileEntity extends TileEntity {
/* 16 */   private Type entryType = Type.CHALLENGE;
/*    */   private ChallengeCore<?> challenge;
/*    */   
/*    */   public PoneglyphTileEntity() {
/* 20 */     super((TileEntityType)ModTileEntities.PONEGLYPH.get());
/*    */   }
/*    */   
/*    */   public Type getEntryType() {
/* 24 */     return this.entryType;
/*    */   }
/*    */   
/*    */   public void setEntryType(Type entryType) {
/* 28 */     this.entryType = entryType;
/*    */   }
/*    */   
/*    */   public ChallengeCore<?> getChallenge() {
/* 32 */     return this.challenge;
/*    */   }
/*    */   
/*    */   public void setChallenge(ChallengeCore<?> challenge) {
/* 36 */     this.challenge = challenge;
/*    */   }
/*    */ 
/*    */   
/*    */   public CompoundNBT func_189515_b(CompoundNBT nbt) {
/* 41 */     super.func_189515_b(nbt);
/*    */     
/* 43 */     nbt.func_74768_a("entryType", this.entryType.ordinal());
/* 44 */     if (this.challenge != null) {
/* 45 */       nbt.func_74778_a("entryId", this.challenge.getRegistryName().toString());
/*    */     }
/*    */     
/* 48 */     return nbt;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_230337_a_(BlockState state, CompoundNBT nbt) {
/* 53 */     super.func_230337_a_(state, nbt);
/*    */     
/* 55 */     this.entryType = Type.values()[nbt.func_74762_e("entryType")];
/* 56 */     if (nbt.func_74764_b("entryId")) {
/* 57 */       ResourceLocation challengeId = new ResourceLocation(nbt.func_74779_i("entryId"));
/* 58 */       this.challenge = (ChallengeCore)ModRegistries.CHALLENGES.getValue(challengeId);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public CompoundNBT func_189517_E_() {
/* 64 */     return func_189515_b(new CompoundNBT());
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public SUpdateTileEntityPacket func_189518_D_() {
/* 70 */     CompoundNBT nbttagcompound = new CompoundNBT();
/* 71 */     func_189515_b(nbttagcompound);
/* 72 */     return new SUpdateTileEntityPacket(this.field_174879_c, 9, func_189517_E_());
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
/* 77 */     func_230337_a_(null, pkt.func_148857_g());
/*    */   }
/*    */   
/*    */   public enum Type {
/* 81 */     CHALLENGE;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\tileentities\PoneglyphTileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */