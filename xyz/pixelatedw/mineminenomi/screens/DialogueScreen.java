/*    */ package xyz.pixelatedw.mineminenomi.screens;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.screen.Screen;
/*    */ import net.minecraft.client.gui.screen.inventory.InventoryScreen;
/*    */ import net.minecraft.client.gui.widget.Widget;
/*    */ import net.minecraft.client.gui.widget.button.Button;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import xyz.pixelatedw.mineminenomi.api.interactions.Interaction;
/*    */ import xyz.pixelatedw.mineminenomi.packets.client.ui.CTriggerInteractionPacket;
/*    */ import xyz.pixelatedw.mineminenomi.screens.extra.SequencedString;
/*    */ import xyz.pixelatedw.mineminenomi.screens.extra.buttons.FactionButton;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class DialogueScreen extends Screen {
/*    */   private PlayerEntity player;
/* 24 */   private SequencedString message = new SequencedString("", 0, 0); private LivingEntity entity;
/*    */   private Interaction[] interactions;
/*    */   
/*    */   public DialogueScreen(LivingEntity entity, Interaction interaction) {
/* 28 */     super(StringTextComponent.field_240750_d_);
/* 29 */     this.entity = entity;
/* 30 */     this.player = (PlayerEntity)(Minecraft.func_71410_x()).field_71439_g;
/* 31 */     this.field_230706_i_ = Minecraft.func_71410_x();
/* 32 */     this.field_230712_o_ = this.field_230706_i_.field_71466_p;
/* 33 */     triggerInteraction(interaction);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
/* 38 */     func_230446_a_(matrixStack);
/*    */     
/* 40 */     int posX = this.field_230708_k_ / 2;
/* 41 */     int posY = this.field_230709_l_ / 2;
/*    */     
/* 43 */     this.message.render(matrixStack, posX - 150, posY - 105, partialTicks);
/*    */ 
/*    */     
/* 46 */     RenderSystem.pushMatrix();
/*    */     
/* 48 */     RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 49 */     RenderSystem.enableBlend();
/* 50 */     InventoryScreen.func_228187_a_(posX + 150, posY + 150, 100, 40.0F, 5.0F, this.entity);
/*    */     
/* 52 */     RenderSystem.popMatrix();
/*    */     
/* 54 */     super.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_231158_b_(Minecraft minecraft, int width, int height) {
/* 59 */     super.func_231158_b_(minecraft, width, height);
/*    */     
/* 61 */     int posX = this.field_230708_k_ / 2;
/* 62 */     int posY = this.field_230709_l_ / 2;
/*    */     
/* 64 */     setupInteractions();
/*    */   }
/*    */   
/*    */   private void setupInteractions() {
/* 68 */     int posX = this.field_230708_k_ / 2;
/* 69 */     int posY = this.field_230709_l_ / 2;
/*    */     
/* 71 */     int i = 0;
/* 72 */     for (Interaction interaction : this.interactions) {
/* 73 */       if (interaction.hasTriggerAction()) {
/* 74 */         FactionButton interactionButton = new FactionButton(posX - 180, posY + 10 + i * 20, 100, 20, interaction.getTitle(), btn -> WyNetwork.sendToServer(new CTriggerInteractionPacket(interaction, this.entity)));
/*    */ 
/*    */         
/* 77 */         func_230480_a_((Widget)interactionButton);
/* 78 */         i++;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void triggerInteraction(Interaction interaction) {
/* 84 */     String message = interaction.getMessage().getString();
/* 85 */     this.message = new SequencedString(message, 250, this.field_230712_o_.func_78256_a(message) / 2);
/* 86 */     this.interactions = new Interaction[(interaction.getInteractions()).length];
/* 87 */     int i = 0;
/* 88 */     for (RegistryObject<Interaction> sub : interaction.getInteractions()) {
/* 89 */       this.interactions[i] = (Interaction)sub.get();
/* 90 */       i++;
/*    */     } 
/* 92 */     func_231158_b_(this.field_230706_i_, this.field_230708_k_, this.field_230709_l_);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\DialogueScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */