/*    */ package xyz.pixelatedw.mineminenomi.data.entity.challenges;
/*    */ 
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.ICapabilitySerializable;
/*    */ import net.minecraftforge.common.util.LazyOptional;
/*    */ 
/*    */ public class ChallengesDataProvider implements ICapabilitySerializable<CompoundNBT> {
/* 11 */   private IChallengesData instance = (IChallengesData)ChallengesDataCapability.INSTANCE.getDefaultInstance();
/*    */ 
/*    */ 
/*    */   
/*    */   public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
/* 16 */     return ChallengesDataCapability.INSTANCE.orEmpty(cap, LazyOptional.of(() -> this.instance));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CompoundNBT serializeNBT() {
/* 22 */     return (CompoundNBT)ChallengesDataCapability.INSTANCE.getStorage().writeNBT(ChallengesDataCapability.INSTANCE, this.instance, null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void deserializeNBT(CompoundNBT nbt) {
/* 28 */     ChallengesDataCapability.INSTANCE.getStorage().readNBT(ChallengesDataCapability.INSTANCE, this.instance, null, (INBT)nbt);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\challenges\ChallengesDataProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */