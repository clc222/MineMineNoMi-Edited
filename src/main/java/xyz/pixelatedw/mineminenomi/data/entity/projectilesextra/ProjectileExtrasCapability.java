/*    */ package xyz.pixelatedw.mineminenomi.data.entity.projectilesextra;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.CapabilityInject;
/*    */ import net.minecraftforge.common.capabilities.CapabilityManager;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ 
/*    */ public class ProjectileExtrasCapability {
/*    */   @CapabilityInject(IProjectileExtras.class)
/* 16 */   public static final Capability<IProjectileExtras> INSTANCE = null;
/*    */   
/*    */   public static void register() {
/* 19 */     CapabilityManager.INSTANCE.register(IProjectileExtras.class, new Capability.IStorage<IProjectileExtras>()
/*    */         {
/*    */           public INBT writeNBT(Capability<IProjectileExtras> capability, IProjectileExtras instance, Direction side) {
/* 22 */             CompoundNBT props = new CompoundNBT();
/*    */             
/* 24 */             props.func_74757_a("isProjectileBusoshokuImbued", instance.isProjectileBusoshokuImbued());
/* 25 */             props.func_74757_a("isProjectileBusoshokuShrouded", instance.isProjectileBusoshokuShrouded());
/* 26 */             props.func_74757_a("isProjectileHaoshokuInfused", instance.isProjectileHaoshokuInfused());
/* 27 */             props.func_74776_a("piercing", instance.getPiercing());
/*    */             
/* 29 */             ItemStack weaponUsed = instance.getWeaponUsed();
/*    */             
/* 31 */             if (weaponUsed != null) {
/* 32 */               props.func_74778_a("weaponUsed", weaponUsed.func_77973_b().getRegistryName().toString());
/*    */             }
/*    */             
/* 35 */             return (INBT)props;
/*    */           }
/*    */ 
/*    */           
/*    */           public void readNBT(Capability<IProjectileExtras> capability, IProjectileExtras instance, Direction side, INBT nbt) {
/* 40 */             CompoundNBT props = (CompoundNBT)nbt;
/*    */             
/* 42 */             instance.setProjectileBusoshokuImbued(props.func_74767_n("isProjectileBusoshokuImbued"));
/* 43 */             instance.setProjectileBusoshokuShrouded(props.func_74767_n("isProjectileBusoshokuShrouded"));
/* 44 */             instance.setProjectileHaoshokuInfused(props.func_74767_n("isProjectileHaoshokuInfused"));
/* 45 */             instance.setPiercing(props.func_74760_g("piercing"));
/* 46 */             instance.setWeaponUsed(new ItemStack((IItemProvider)ForgeRegistries.ITEMS.getValue(new ResourceLocation(props.func_74779_i("weaponUsed")))));
/*    */           }
/*    */         }() -> new ProjectileExtrasBase());
/*    */   }
/*    */ 
/*    */   
/*    */   public static IProjectileExtras get(Entity entity) {
/* 53 */     return (IProjectileExtras)entity.getCapability(INSTANCE, null).orElse(new ProjectileExtrasBase());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\projectilesextra\ProjectileExtrasCapability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */