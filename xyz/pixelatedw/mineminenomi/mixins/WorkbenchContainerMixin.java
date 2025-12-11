/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.CraftResultInventory;
/*    */ import net.minecraft.inventory.CraftingInventory;
/*    */ import net.minecraft.inventory.container.WorkbenchContainer;
/*    */ import net.minecraft.world.World;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import xyz.pixelatedw.mineminenomi.events.CraftingEvents;
/*    */ 
/*    */ @Mixin({WorkbenchContainer.class})
/*    */ public class WorkbenchContainerMixin {
/*    */   @Inject(method = {"slotChangedCraftingGrid"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private static void slotChangedCraftingGrid(int containerId, World level, PlayerEntity player, CraftingInventory container, CraftResultInventory resultContainer, CallbackInfo callback) {
/* 18 */     boolean flag = CraftingEvents.onGridChanged(containerId, level, player, container, resultContainer);
/* 19 */     if (flag) {
/* 20 */       callback.cancel();
/*    */       return;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\WorkbenchContainerMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */