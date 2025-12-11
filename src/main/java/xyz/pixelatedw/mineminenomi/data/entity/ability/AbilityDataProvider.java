/*    */ package xyz.pixelatedw.mineminenomi.data.entity.ability;
/*    */ 
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.ICapabilitySerializable;
/*    */ import net.minecraftforge.common.util.LazyOptional;
/*    */ 
/*    */ public class AbilityDataProvider implements ICapabilitySerializable<CompoundNBT> {
/* 11 */   private IAbilityData instance = (IAbilityData)AbilityDataCapability.INSTANCE.getDefaultInstance();
/*    */ 
/*    */ 
/*    */   
/*    */   public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
/* 16 */     return AbilityDataCapability.INSTANCE.orEmpty(cap, LazyOptional.of(() -> this.instance));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CompoundNBT serializeNBT() {
/* 22 */     return (CompoundNBT)AbilityDataCapability.INSTANCE.getStorage().writeNBT(AbilityDataCapability.INSTANCE, this.instance, null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void deserializeNBT(CompoundNBT nbt) {
/* 28 */     AbilityDataCapability.INSTANCE.getStorage().readNBT(AbilityDataCapability.INSTANCE, this.instance, null, (INBT)nbt);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\ability\AbilityDataProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */