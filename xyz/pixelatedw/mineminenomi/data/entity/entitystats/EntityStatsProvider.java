/*    */ package xyz.pixelatedw.mineminenomi.data.entity.entitystats;
/*    */ 
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.ICapabilitySerializable;
/*    */ import net.minecraftforge.common.util.LazyOptional;
/*    */ 
/*    */ public class EntityStatsProvider implements ICapabilitySerializable<CompoundNBT> {
/* 11 */   private IEntityStats instance = (IEntityStats)EntityStatsCapability.INSTANCE.getDefaultInstance();
/*    */ 
/*    */ 
/*    */   
/*    */   public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
/* 16 */     return EntityStatsCapability.INSTANCE.orEmpty(cap, LazyOptional.of(() -> this.instance));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CompoundNBT serializeNBT() {
/* 22 */     return (CompoundNBT)EntityStatsCapability.INSTANCE.getStorage().writeNBT(EntityStatsCapability.INSTANCE, this.instance, null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void deserializeNBT(CompoundNBT nbt) {
/* 28 */     EntityStatsCapability.INSTANCE.getStorage().readNBT(EntityStatsCapability.INSTANCE, this.instance, null, (INBT)nbt);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\entitystats\EntityStatsProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */