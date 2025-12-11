/*    */ package xyz.pixelatedw.mineminenomi.data.entity.animation;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.CapabilityInject;
/*    */ import net.minecraftforge.common.capabilities.CapabilityManager;
/*    */ 
/*    */ public class AnimationDataCapability {
/*    */   @CapabilityInject(IAnimationData.class)
/* 13 */   public static final Capability<IAnimationData> INSTANCE = null;
/*    */   
/*    */   public static void register() {
/* 16 */     CapabilityManager.INSTANCE.register(IAnimationData.class, new Capability.IStorage<IAnimationData>()
/*    */         {
/*    */           public INBT writeNBT(Capability<IAnimationData> capability, IAnimationData instance, Direction side)
/*    */           {
/* 20 */             CompoundNBT props = new CompoundNBT();
/*    */             
/* 22 */             return (INBT)props;
/*    */           }
/*    */ 
/*    */           
/*    */           public void readNBT(Capability<IAnimationData> capability, IAnimationData instance, Direction side, INBT nbt) {
/* 27 */             CompoundNBT props = (CompoundNBT)nbt;
/*    */           }
/*    */         },  () -> new AnimationDataBase());
/*    */   }
/*    */ 
/*    */   
/*    */   public static IAnimationData get(LivingEntity entity) {
/* 34 */     IAnimationData props = (IAnimationData)entity.getCapability(INSTANCE, null).orElse(new AnimationDataBase());
/* 35 */     props.setOwner(entity);
/* 36 */     return props;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\animation\AnimationDataCapability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */