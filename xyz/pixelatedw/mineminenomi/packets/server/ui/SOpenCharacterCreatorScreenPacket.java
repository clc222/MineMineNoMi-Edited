/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ui;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.screens.CharacterCreatorScreen;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SOpenCharacterCreatorScreenPacket
/*    */ {
/*    */   private boolean hasRandomizedRace;
/*    */   private boolean allowMinkRaceSelect;
/*    */   
/*    */   public SOpenCharacterCreatorScreenPacket() {}
/*    */   
/*    */   public SOpenCharacterCreatorScreenPacket(boolean hasRandomizedRace, boolean allowMinkRaceSelect) {
/* 21 */     this.hasRandomizedRace = hasRandomizedRace;
/* 22 */     this.allowMinkRaceSelect = allowMinkRaceSelect;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 27 */     buffer.writeBoolean(this.hasRandomizedRace);
/* 28 */     buffer.writeBoolean(this.allowMinkRaceSelect);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SOpenCharacterCreatorScreenPacket decode(PacketBuffer buffer) {
/* 33 */     SOpenCharacterCreatorScreenPacket msg = new SOpenCharacterCreatorScreenPacket();
/* 34 */     msg.hasRandomizedRace = buffer.readBoolean();
/* 35 */     msg.allowMinkRaceSelect = buffer.readBoolean();
/* 36 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SOpenCharacterCreatorScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 41 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/* 42 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message)); 
/* 43 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SOpenCharacterCreatorScreenPacket message) {
/* 51 */       CharacterCreatorScreen.open(message.hasRandomizedRace, message.allowMinkRaceSelect);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\serve\\ui\SOpenCharacterCreatorScreenPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */