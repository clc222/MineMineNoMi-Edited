/*    */ package xyz.pixelatedw.mineminenomi.data.entity.gcd;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.ICapabilitySerializable;
/*    */ import net.minecraftforge.common.util.LazyOptional;
/*    */ 
/*    */ public class GCDProvider implements ICapabilitySerializable<CompoundNBT> {
/* 10 */   private IGCD instance = (IGCD)GCDCapability.INSTANCE.getDefaultInstance();
/*    */ 
/*    */   
/*    */   public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
/* 14 */     return GCDCapability.INSTANCE.orEmpty(cap, LazyOptional.of(() -> this.instance));
/*    */   }
/*    */ 
/*    */   
/*    */   public CompoundNBT serializeNBT() {
/* 19 */     return (CompoundNBT)GCDCapability.INSTANCE.getStorage().writeNBT(GCDCapability.INSTANCE, this.instance, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public void deserializeNBT(CompoundNBT nbt) {
/* 24 */     GCDCapability.INSTANCE.getStorage().readNBT(GCDCapability.INSTANCE, this.instance, null, (INBT)nbt);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\gcd\GCDProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */