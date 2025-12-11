/*    */ package xyz.pixelatedw.mineminenomi.data.entity.kairosekicoating;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.CapabilityInject;
/*    */ import net.minecraftforge.common.capabilities.CapabilityManager;
/*    */ import net.minecraftforge.common.util.LazyOptional;
/*    */ 
/*    */ public class KairosekiCoatingCapability {
/*    */   @CapabilityInject(IKairosekiCoating.class)
/* 14 */   public static final Capability<IKairosekiCoating> INSTANCE = null;
/*    */   
/*    */   public static void register() {
/* 17 */     CapabilityManager.INSTANCE.register(IKairosekiCoating.class, new Capability.IStorage<IKairosekiCoating>()
/*    */         {
/*    */           public INBT writeNBT(Capability<IKairosekiCoating> capability, IKairosekiCoating instance, Direction side) {
/* 20 */             CompoundNBT props = new CompoundNBT();
/*    */             
/* 22 */             props.func_74768_a("coatingLevel", instance.getCoatingLevel());
/*    */             
/* 24 */             return (INBT)props;
/*    */           }
/*    */ 
/*    */           
/*    */           public void readNBT(Capability<IKairosekiCoating> capability, IKairosekiCoating instance, Direction side, INBT nbt) {
/* 29 */             CompoundNBT props = (CompoundNBT)nbt;
/*    */             
/* 31 */             instance.setCoatingLevel(props.func_74762_e("coatingLevel"));
/*    */           }
/*    */         }() -> new KairosekiCoatingBase());
/*    */   }
/*    */ 
/*    */   
/*    */   public static LazyOptional<IKairosekiCoating> get(Entity entity) {
/* 38 */     LazyOptional<IKairosekiCoating> opt = entity.getCapability(INSTANCE, null);
/* 39 */     opt.ifPresent(c -> c.setOwner(entity));
/* 40 */     return opt;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\kairosekicoating\KairosekiCoatingCapability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */