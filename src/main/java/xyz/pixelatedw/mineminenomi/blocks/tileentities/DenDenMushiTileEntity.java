/*    */ package xyz.pixelatedw.mineminenomi.blocks.tileentities;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.network.play.server.SUpdateTileEntityPacket;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.tileentity.TileEntityType;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModTileEntities;
/*    */ 
/*    */ 
/*    */ public class DenDenMushiTileEntity
/*    */   extends TileEntity
/*    */ {
/*    */   public DenDenMushiTileEntity() {
/* 15 */     super((TileEntityType)ModTileEntities.DEN_DEN_MUSHI.get());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CompoundNBT func_189517_E_() {
/* 21 */     return func_189515_b(new CompoundNBT());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public SUpdateTileEntityPacket func_189518_D_() {
/* 28 */     CompoundNBT nbt = new CompoundNBT();
/* 29 */     func_189515_b(nbt);
/* 30 */     return new SUpdateTileEntityPacket(this.field_174879_c, 9, func_189517_E_());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\tileentities\DenDenMushiTileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */