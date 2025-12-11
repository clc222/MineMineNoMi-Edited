/*    */ package xyz.pixelatedw.mineminenomi.data.entity.quests;
/*    */ 
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.ICapabilitySerializable;
/*    */ import net.minecraftforge.common.util.LazyOptional;
/*    */ 
/*    */ public class QuestDataProvider implements ICapabilitySerializable<CompoundNBT> {
/* 11 */   private IQuestData instance = (IQuestData)QuestDataCapability.INSTANCE.getDefaultInstance();
/*    */ 
/*    */ 
/*    */   
/*    */   public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
/* 16 */     return QuestDataCapability.INSTANCE.orEmpty(cap, LazyOptional.of(() -> this.instance));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CompoundNBT serializeNBT() {
/* 22 */     return (CompoundNBT)QuestDataCapability.INSTANCE.getStorage().writeNBT(QuestDataCapability.INSTANCE, this.instance, null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void deserializeNBT(CompoundNBT nbt) {
/* 28 */     QuestDataCapability.INSTANCE.getStorage().readNBT(QuestDataCapability.INSTANCE, this.instance, null, (INBT)nbt);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\quests\QuestDataProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */