/*    */ package xyz.pixelatedw.mineminenomi.screens;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.gui.screen.inventory.ContainerScreen;
/*    */ import net.minecraft.client.gui.screen.inventory.InventoryScreen;
/*    */ import net.minecraft.client.gui.widget.Widget;
/*    */ import net.minecraft.client.gui.widget.button.Button;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.PlayerInventory;
/*    */ import net.minecraft.inventory.container.Container;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.containers.WhiteWalkieStorageContainer;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.WhiteWalkieEntity;
/*    */ import xyz.pixelatedw.mineminenomi.packets.client.entities.CUpdateInventoryPagePacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class WhiteWalkieStorageScreen extends ContainerScreen<WhiteWalkieStorageContainer> {
/* 20 */   private static final ResourceLocation INVENTORY_LOCATION = new ResourceLocation("textures/gui/container/horse.png");
/*    */   private WhiteWalkieStorageContainer storageContainer;
/*    */   private final WhiteWalkieEntity whiteWalkie;
/*    */   private final PlayerEntity player;
/*    */   private float xMouse;
/*    */   private float yMouse;
/*    */   
/*    */   public WhiteWalkieStorageScreen(WhiteWalkieStorageContainer menu, PlayerInventory inv) {
/* 28 */     super((Container)menu, inv, menu.getWhiteWalkie().func_145748_c_());
/* 29 */     this.whiteWalkie = menu.getWhiteWalkie();
/* 30 */     this.player = inv.field_70458_d;
/* 31 */     this.storageContainer = menu;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_230430_a_(MatrixStack pMatrixStack, int pMouseX, int pMouseY, float pPartialTicks) {
/* 36 */     func_230446_a_(pMatrixStack);
/* 37 */     this.xMouse = pMouseX;
/* 38 */     this.yMouse = pMouseY;
/* 39 */     super.func_230430_a_(pMatrixStack, pMouseX, pMouseY, pPartialTicks);
/* 40 */     func_230459_a_(pMatrixStack, pMouseX, pMouseY);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_230450_a_(MatrixStack matrix, float partialTicks, int pX, int pY) {
/* 45 */     RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 46 */     this.field_230706_i_.func_110434_K().func_110577_a(INVENTORY_LOCATION);
/* 47 */     int posX = (this.field_230708_k_ - this.field_146999_f) / 2;
/* 48 */     int posY = (this.field_230709_l_ - this.field_147000_g) / 2;
/* 49 */     func_238474_b_(matrix, posX, posY, 0, 0, this.field_146999_f, this.field_147000_g);
/*    */ 
/*    */     
/* 52 */     if (this.whiteWalkie.hasChest()) {
/* 53 */       func_238474_b_(matrix, posX + 79, posY + 17, 0, this.field_147000_g, this.whiteWalkie.getInventoryColumns() * 18, 54);
/*    */     }
/*    */     
/* 56 */     if (this.whiteWalkie.func_70909_n())
/*    */     {
/* 58 */       func_238474_b_(matrix, posX + 7, posY + 35 - 18, 18, this.field_147000_g + 54, 18, 18);
/*    */     }
/*    */ 
/*    */     
/* 62 */     InventoryScreen.func_228187_a_(posX + 51, posY + 60, 17, (posX + 51) - this.xMouse, (posY + 75 - 50) - this.yMouse, (LivingEntity)this.whiteWalkie);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_231160_c_() {
/* 67 */     super.func_231160_c_();
/*    */     
/* 69 */     this.field_230710_m_.clear();
/* 70 */     this.field_230705_e_.clear();
/*    */     
/* 72 */     int posX = (this.field_230708_k_ - this.field_146999_f) / 2;
/* 73 */     int posY = (this.field_230709_l_ - this.field_147000_g) / 2;
/*    */     
/* 75 */     Button page1 = new Button(posX + 80, posY + 2, 14, 14, (ITextComponent)new StringTextComponent("1"), b -> {
/*    */           this.whiteWalkie.setInventoryPage(0);
/*    */           WyNetwork.sendToServer(new CUpdateInventoryPagePacket(this.whiteWalkie.func_145782_y(), 0));
/*    */           func_231160_c_();
/*    */         });
/* 80 */     if (this.whiteWalkie.hasChest()) {
/* 81 */       func_230480_a_((Widget)page1);
/*    */     }
/* 83 */     if (this.whiteWalkie.getInventoryPage() == 0) {
/* 84 */       page1.field_230693_o_ = false;
/*    */     }
/*    */     
/* 87 */     Button page2 = new Button(posX + 95, posY + 2, 14, 14, (ITextComponent)new StringTextComponent("2"), b -> {
/*    */           this.whiteWalkie.setInventoryPage(1);
/*    */           WyNetwork.sendToServer(new CUpdateInventoryPagePacket(this.whiteWalkie.func_145782_y(), 1));
/*    */           func_231160_c_();
/*    */         });
/* 92 */     if (this.whiteWalkie.getChests() > 1) {
/* 93 */       func_230480_a_((Widget)page2);
/*    */     }
/* 95 */     if (this.whiteWalkie.getInventoryPage() == 1)
/* 96 */       page2.field_230693_o_ = false; 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\WhiteWalkieStorageScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */