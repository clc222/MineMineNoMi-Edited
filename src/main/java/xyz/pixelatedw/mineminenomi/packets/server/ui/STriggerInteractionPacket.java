/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ui;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.client.world.ClientWorld;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*    */ import xyz.pixelatedw.mineminenomi.api.interactions.Interaction;
/*    */ import xyz.pixelatedw.mineminenomi.screens.DialogueScreen;
/*    */ 
/*    */ public class STriggerInteractionPacket {
/*    */   private Interaction interaction;
/*    */   
/*    */   public STriggerInteractionPacket() {}
/*    */   
/*    */   public STriggerInteractionPacket(Interaction interaction) {
/* 23 */     this.interaction = interaction;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 27 */     buffer.func_192572_a(this.interaction.getRegistryName());
/*    */   }
/*    */   
/*    */   public static STriggerInteractionPacket decode(PacketBuffer buffer) {
/* 31 */     STriggerInteractionPacket msg = new STriggerInteractionPacket();
/* 32 */     ResourceLocation res = buffer.func_192575_l();
/* 33 */     msg.interaction = (Interaction)ModRegistries.INTERACTIONS.getValue(res);
/* 34 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(STriggerInteractionPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 38 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 39 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/* 41 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(STriggerInteractionPacket message) {
/* 47 */       Minecraft minecraft = Minecraft.func_71410_x();
/* 48 */       ClientPlayerEntity player = minecraft.field_71439_g;
/* 49 */       ClientWorld world = minecraft.field_71441_e;
/*    */       
/* 51 */       if (!(minecraft.field_71462_r instanceof DialogueScreen)) {
/*    */         return;
/*    */       }
/*    */       
/* 55 */       DialogueScreen screen = (DialogueScreen)minecraft.field_71462_r;
/* 56 */       screen.triggerInteraction(message.interaction);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\serve\\ui\STriggerInteractionPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */