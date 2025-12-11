/*    */ package xyz.pixelatedw.mineminenomi.data.entity.haki;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.CapabilityInject;
/*    */ import net.minecraftforge.common.capabilities.CapabilityManager;
/*    */ 
/*    */ public class HakiDataCapability
/*    */ {
/*    */   @CapabilityInject(IHakiData.class)
/* 14 */   public static final Capability<IHakiData> INSTANCE = null;
/*    */ 
/*    */   
/*    */   public static void register() {
/* 18 */     CapabilityManager.INSTANCE.register(IHakiData.class, new Capability.IStorage<IHakiData>()
/*    */         {
/*    */           
/*    */           public INBT writeNBT(Capability<IHakiData> capability, IHakiData instance, Direction side)
/*    */           {
/* 23 */             CompoundNBT props = new CompoundNBT();
/*    */             
/* 25 */             props.func_74776_a("kenHakiExp", instance.getKenbunshokuHakiExp());
/* 26 */             props.func_74776_a("busoHakiExp", instance.getBusoshokuHakiExp());
/* 27 */             props.func_74768_a("hakiOveruse", instance.getHakiOveruse());
/*    */             
/* 29 */             return (INBT)props;
/*    */           }
/*    */ 
/*    */ 
/*    */           
/*    */           public void readNBT(Capability<IHakiData> capability, IHakiData instance, Direction side, INBT nbt) {
/* 35 */             CompoundNBT props = (CompoundNBT)nbt;
/*    */             
/* 37 */             instance.setKenbunshokuHakiExp(props.func_74760_g("kenHakiExp"));
/* 38 */             instance.setBusoshokuHakiExp(props.func_74760_g("busoHakiExp"));
/* 39 */             instance.setHakiOveruse(props.func_74762_e("hakiOveruse"));
/*    */           }
/*    */         }() -> new HakiDataBase());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static IHakiData get(LivingEntity entity) {
/* 47 */     IHakiData props = (IHakiData)entity.getCapability(INSTANCE, null).orElse(new HakiDataBase());
/* 48 */     props.setOwner(entity);
/* 49 */     return props;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\haki\HakiDataCapability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */