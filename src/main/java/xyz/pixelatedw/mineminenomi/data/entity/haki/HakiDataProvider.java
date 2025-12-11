/*    */ package xyz.pixelatedw.mineminenomi.data.entity.haki;
/*    */ 
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.ICapabilitySerializable;
/*    */ import net.minecraftforge.common.util.LazyOptional;
/*    */ 
/*    */ public class HakiDataProvider implements ICapabilitySerializable<CompoundNBT> {
/* 11 */   private IHakiData instance = (IHakiData)HakiDataCapability.INSTANCE.getDefaultInstance();
/*    */ 
/*    */ 
/*    */   
/*    */   public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
/* 16 */     return HakiDataCapability.INSTANCE.orEmpty(cap, LazyOptional.of(() -> this.instance));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CompoundNBT serializeNBT() {
/* 22 */     return (CompoundNBT)HakiDataCapability.INSTANCE.getStorage().writeNBT(HakiDataCapability.INSTANCE, this.instance, null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void deserializeNBT(CompoundNBT nbt) {
/* 28 */     HakiDataCapability.INSTANCE.getStorage().readNBT(HakiDataCapability.INSTANCE, this.instance, null, (INBT)nbt);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\haki\HakiDataProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */