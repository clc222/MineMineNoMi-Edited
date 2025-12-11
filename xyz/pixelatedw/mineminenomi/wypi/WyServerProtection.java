/*    */ package xyz.pixelatedw.mineminenomi.wypi;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.client.multiplayer.ServerData;
/*    */ import net.minecraft.network.play.server.SDisconnectPacket;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WyServerProtection
/*    */ {
/*    */   protected static boolean IS_CHECKED = false;
/*    */   
/*    */   public static BannedServer getBannedServerInfo(String ip) throws IOException {
/* 20 */     String apiURL = "/server-check/" + ip;
/* 21 */     BannedServer result = WyHelper.<BannedServer>sendGET(apiURL, BannedServer.class);
/*    */     
/* 23 */     return result;
/*    */   }
/*    */   
/*    */   private static class BannedServer
/*    */   {
/*    */     private String reason;
/*    */   }
/*    */   
/*    */   public static class ClientEvents {
/* 32 */     private static final ITextComponent MESSAGE = (ITextComponent)new StringTextComponent("§c§lWARNING!§r \n\nThis server is not part of the server testing program and thus cannot be used with this build.");
/*    */ 
/*    */     
/*    */     @SubscribeEvent
/*    */     public void onEntityJoinWorld(EntityJoinWorldEvent event) {
/* 37 */       ClientPlayerEntity player = (Minecraft.func_71410_x()).field_71439_g;
/*    */       
/* 39 */       if (!(event.getEntity() instanceof net.minecraft.entity.player.PlayerEntity) || !(event.getEntity().func_130014_f_()).field_72995_K || event.getEntity() != player) {
/*    */         return;
/*    */       }
/* 42 */       if (player == null || (Minecraft.func_71410_x().func_147104_D() == null && !WyPatreon.isPromoBuild())) {
/*    */         return;
/*    */       }
/* 45 */       ServerData server = Minecraft.func_71410_x().func_147104_D();
/*    */       
/* 47 */       String serverIp = "";
/* 48 */       if (server != null) {
/* 49 */         serverIp = server.field_78845_b;
/*    */       }
/* 51 */       if (WyPatreon.isPromoBuild()) {
/*    */         
/* 53 */         String[] allowedServers = new String[0];
/* 54 */         boolean flag = false;
/* 55 */         for (String ip : allowedServers) {
/*    */           
/* 57 */           if (serverIp.equalsIgnoreCase(ip)) {
/*    */             
/* 59 */             flag = true;
/*    */             
/*    */             break;
/*    */           } 
/*    */         } 
/* 64 */         if (!flag) {
/*    */           
/* 66 */           event.setCanceled(true);
/* 67 */           player.field_71174_a.func_147253_a(new SDisconnectPacket(MESSAGE));
/*    */           
/*    */           return;
/*    */         } 
/*    */       } 
/* 72 */       WyServerProtection.BannedServer info = null;
/*    */ 
/*    */       
/*    */       try {
/* 76 */         info = WyServerProtection.getBannedServerInfo(serverIp);
/*    */       }
/* 78 */       catch (IOException e) {
/*    */         
/* 80 */         e.printStackTrace();
/*    */       } 
/*    */       
/* 83 */       if (info != null) {
/*    */         
/* 85 */         event.setCanceled(true);
/* 86 */         StringTextComponent msg = new StringTextComponent("§c§lWARNING!§r \n\nThis server has been closed due to " + info.reason);
/* 87 */         player.field_71174_a.func_147253_a(new SDisconnectPacket((ITextComponent)msg));
/*    */         return;
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\wypi\WyServerProtection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */