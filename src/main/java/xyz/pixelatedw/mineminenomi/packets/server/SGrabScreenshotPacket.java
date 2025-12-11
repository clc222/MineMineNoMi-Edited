/*    */ package xyz.pixelatedw.mineminenomi.packets.server;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.ScreenShotHelper;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.commands.FGCommand;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SGrabScreenshotPacket
/*    */ {
/*    */   public void encode(PacketBuffer buffer) {}
/*    */   
/*    */   public static SGrabScreenshotPacket decode(PacketBuffer buffer) {
/* 23 */     SGrabScreenshotPacket msg = new SGrabScreenshotPacket();
/* 24 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SGrabScreenshotPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 28 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 29 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */     
/* 33 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SGrabScreenshotPacket message) {
/* 39 */       Minecraft minecraft = Minecraft.func_71410_x();
/*    */       
/* 41 */       ScreenShotHelper.func_148259_a(minecraft.field_71412_D, "panorama_" + (FGCommand.SKYBOX_ID - 1) + ".png", 1024, 1024, minecraft.func_147110_a(), msg -> {
/*    */           
/*    */           });
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\SGrabScreenshotPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */