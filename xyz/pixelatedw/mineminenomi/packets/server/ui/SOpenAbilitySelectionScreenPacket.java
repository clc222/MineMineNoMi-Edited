/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ui;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.client.gui.screen.Screen;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.events.CombatModeEvents;
/*    */ import xyz.pixelatedw.mineminenomi.screens.SelectHotbarAbilitiesScreen;
/*    */ 
/*    */ public class SOpenAbilitySelectionScreenPacket {
/*    */   private int bars;
/*    */   
/*    */   public SOpenAbilitySelectionScreenPacket() {}
/*    */   
/*    */   public SOpenAbilitySelectionScreenPacket(int bars) {
/* 22 */     this.bars = bars;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 26 */     buffer.writeInt(this.bars);
/*    */   }
/*    */   
/*    */   public static SOpenAbilitySelectionScreenPacket decode(PacketBuffer buffer) {
/* 30 */     SOpenAbilitySelectionScreenPacket msg = new SOpenAbilitySelectionScreenPacket();
/* 31 */     msg.bars = buffer.readInt();
/* 32 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SOpenAbilitySelectionScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 36 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 37 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/* 39 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SOpenAbilitySelectionScreenPacket message) {
/* 45 */       ClientPlayerEntity clientPlayerEntity = (Minecraft.func_71410_x()).field_71439_g;
/*    */       
/* 47 */       if (clientPlayerEntity == null) {
/*    */         return;
/*    */       }
/*    */       
/* 51 */       CombatModeEvents.Client.ABILITY_BARS = message.bars;
/* 52 */       Minecraft.func_71410_x().func_147108_a((Screen)new SelectHotbarAbilitiesScreen((PlayerEntity)clientPlayerEntity));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\serve\\ui\SOpenAbilitySelectionScreenPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */