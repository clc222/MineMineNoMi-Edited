/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ import net.minecraft.entity.player.PlayerInventory;
/*    */ import net.minecraft.inventory.container.ContainerType;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.eventbus.api.IEventBus;
/*    */ import xyz.pixelatedw.mineminenomi.containers.WhiteWalkieStorageContainer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class ModContainers {
/*    */   static {
/* 11 */     WHITE_WALKIE_STORAGE = WyRegistry.registerContainer("White Walkie Storage", (menuId, inv, data) -> new WhiteWalkieStorageContainer(menuId, inv, data));
/*    */   }
/*    */   public static final RegistryObject<ContainerType<WhiteWalkieStorageContainer>> WHITE_WALKIE_STORAGE;
/*    */   
/*    */   public static void register(IEventBus event) {
/* 16 */     WyRegistry.CONTAINER_TYPES.register(event);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModContainers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */