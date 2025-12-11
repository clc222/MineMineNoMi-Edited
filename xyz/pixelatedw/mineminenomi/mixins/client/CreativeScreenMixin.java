/*    */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.screen.inventory.CreativeScreen;
/*    */ import net.minecraft.entity.player.PlayerInventory;
/*    */ import net.minecraft.inventory.Inventory;
/*    */ import net.minecraft.inventory.container.ClickType;
/*    */ import net.minecraft.inventory.container.Slot;
/*    */ import net.minecraft.item.ItemGroup;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ import xyz.pixelatedw.mineminenomi.packets.client.ofpw.CRequestCreativeDeleteOFPWPacket;
/*    */ import xyz.pixelatedw.mineminenomi.packets.client.ofpw.CRequestCreativeSpawnOFPWPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({CreativeScreen.class})
/*    */ public class CreativeScreenMixin
/*    */ {
/*    */   @Shadow
/*    */   private static int field_147058_w;
/*    */   @Shadow
/*    */   @Nullable
/*    */   private Slot field_147064_C;
/*    */   @Shadow
/*    */   @Final
/*    */   private static Inventory field_195378_x;
/*    */   
/*    */   @Inject(method = {"slotClicked"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void slotClicked(@Nullable Slot slot, int slotId, int mouseButton, ClickType type, CallbackInfo ci) {
/* 40 */     Minecraft mc = Minecraft.func_71410_x();
/*    */     
/* 42 */     CreativeScreen.CreativeContainer cont = (CreativeScreen.CreativeContainer)((CreativeScreen)this).func_212873_a_();
/* 43 */     PlayerInventory inv = mc.field_71439_g.field_71071_by;
/* 44 */     ItemStack carriedItem = inv.func_70445_o();
/*    */     
/* 46 */     if (field_147058_w == ItemGroup.field_78036_m.func_78021_a() && !carriedItem.func_190926_b() && carriedItem.func_77973_b() instanceof AkumaNoMiItem) {
/* 47 */       AkumaNoMiItem item = (AkumaNoMiItem)carriedItem.func_77973_b();
/* 48 */       if (slot == this.field_147064_C) {
/* 49 */         WyNetwork.sendToServer(new CRequestCreativeDeleteOFPWPacket(item.getRegistryName()));
/*    */       } else {
/*    */         
/* 52 */         WyNetwork.sendToServer(new CRequestCreativeSpawnOFPWPacket(item.getRegistryName()));
/*    */       }
/*    */     
/* 55 */     } else if (cont != null && !carriedItem.func_190926_b() && carriedItem.func_77973_b() instanceof AkumaNoMiItem) {
/* 56 */       AkumaNoMiItem item = (AkumaNoMiItem)carriedItem.func_77973_b();
/* 57 */       WyNetwork.sendToServer(new CRequestCreativeSpawnOFPWPacket(item.getRegistryName()));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\client\CreativeScreenMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */