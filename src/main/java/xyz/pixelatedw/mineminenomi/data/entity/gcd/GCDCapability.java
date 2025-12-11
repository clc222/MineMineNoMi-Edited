/*    */ package xyz.pixelatedw.mineminenomi.data.entity.gcd;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.CapabilityInject;
/*    */ import net.minecraftforge.common.capabilities.CapabilityManager;
/*    */ import net.minecraftforge.common.util.LazyOptional;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ 
/*    */ public class GCDCapability
/*    */ {
/*    */   @CapabilityInject(IGCD.class)
/* 16 */   public static final Capability<IGCD> INSTANCE = null;
/*    */   
/*    */   public static void register() {
/* 19 */     CapabilityManager.INSTANCE.register(IGCD.class, new Capability.IStorage<IGCD>()
/*    */         {
/*    */           public INBT writeNBT(Capability<IGCD> capability, IGCD instance, Direction side) {
/* 22 */             CompoundNBT props = new CompoundNBT();
/*    */             
/* 24 */             props.func_74757_a("onGCD", instance.isOnGCD());
/*    */             
/* 26 */             return (INBT)props;
/*    */           }
/*    */ 
/*    */           
/*    */           public void readNBT(Capability<IGCD> capability, IGCD instance, Direction side, INBT nbt) {
/* 31 */             CompoundNBT props = (CompoundNBT)nbt;
/*    */             
/* 33 */             boolean onGCD = props.func_74767_n("onGCD");
/* 34 */             if (onGCD) {
/* 35 */               instance.startGCD();
/*    */             }
/*    */           }
/*    */         }() -> new GCDBase());
/*    */   }
/*    */ 
/*    */   
/*    */   public static LazyOptional<IGCD> getLazy(LivingEntity entity) {
/* 43 */     LazyOptional<IGCD> lazyGCD = entity.getCapability(INSTANCE, null);
/* 44 */     lazyGCD.ifPresent(data -> data.setOwner(entity));
/* 45 */     return lazyGCD;
/*    */   }
/*    */   
/*    */   public static IGCD get(LivingEntity entity) {
/* 49 */     LazyOptional<IGCD> lazyGCD = entity.getCapability(INSTANCE, null);
/* 50 */     lazyGCD.ifPresent(data -> data.setOwner(entity));
/* 51 */     return (IGCD)lazyGCD.orElse(null);
/*    */   }
/*    */   
/*    */   public static ImmutablePair<Integer, Integer> getGCD(LivingEntity entity) {
/* 55 */     return getLazy(entity).map(gcd -> ImmutablePair.of(Integer.valueOf(gcd.getCurrentGCD()), Integer.valueOf(gcd.getDefaultGCD()))).orElse(ImmutablePair.of(null, null));
/*    */   }
/*    */   
/*    */   public static boolean isOnGCD(LivingEntity entity) {
/* 59 */     return ((Boolean)getLazy(entity).map(gcd -> Boolean.valueOf(gcd.isOnGCD())).orElse(Boolean.valueOf(false))).booleanValue();
/*    */   }
/*    */   
/*    */   public static void startGCD(LivingEntity entity) {
/* 63 */     getLazy(entity).ifPresent(IGCD::startGCD);
/*    */   }
/*    */   
/*    */   public static void tickGCD(LivingEntity entity) {
/* 67 */     getLazy(entity).ifPresent(IGCD::tickGCD);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\gcd\GCDCapability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */