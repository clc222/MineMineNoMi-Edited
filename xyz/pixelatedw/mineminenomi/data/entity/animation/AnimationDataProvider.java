/*    */ package xyz.pixelatedw.mineminenomi.data.entity.animation;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.ICapabilitySerializable;
/*    */ import net.minecraftforge.common.util.LazyOptional;
/*    */ 
/*    */ public class AnimationDataProvider implements ICapabilitySerializable<CompoundNBT> {
/* 10 */   private IAnimationData instance = (IAnimationData)AnimationDataCapability.INSTANCE.getDefaultInstance();
/*    */ 
/*    */   
/*    */   public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
/* 14 */     return AnimationDataCapability.INSTANCE.orEmpty(cap, LazyOptional.of(() -> this.instance));
/*    */   }
/*    */ 
/*    */   
/*    */   public CompoundNBT serializeNBT() {
/* 19 */     return (CompoundNBT)AnimationDataCapability.INSTANCE.getStorage().writeNBT(AnimationDataCapability.INSTANCE, this.instance, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public void deserializeNBT(CompoundNBT nbt) {
/* 24 */     AnimationDataCapability.INSTANCE.getStorage().readNBT(AnimationDataCapability.INSTANCE, this.instance, null, (INBT)nbt);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\animation\AnimationDataProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */