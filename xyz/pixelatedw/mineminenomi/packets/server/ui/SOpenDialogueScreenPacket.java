/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ui;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.screen.Screen;
/*    */ import net.minecraft.client.world.ClientWorld;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*    */ import xyz.pixelatedw.mineminenomi.api.interactions.Interaction;
/*    */ import xyz.pixelatedw.mineminenomi.screens.DialogueScreen;
/*    */ 
/*    */ public class SOpenDialogueScreenPacket {
/*    */   private int entityId;
/*    */   private Interaction interaction;
/*    */   
/*    */   public SOpenDialogueScreenPacket() {}
/*    */   
/*    */   public SOpenDialogueScreenPacket(LivingEntity entity, Interaction interaction) {
/* 25 */     this.entityId = entity.func_145782_y();
/* 26 */     this.interaction = interaction;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 30 */     buffer.writeInt(this.entityId);
/* 31 */     buffer.func_192572_a(this.interaction.getRegistryName());
/*    */   }
/*    */   
/*    */   public static SOpenDialogueScreenPacket decode(PacketBuffer buffer) {
/* 35 */     SOpenDialogueScreenPacket msg = new SOpenDialogueScreenPacket();
/* 36 */     msg.entityId = buffer.readInt();
/* 37 */     msg.interaction = (Interaction)ModRegistries.INTERACTIONS.getValue(buffer.func_192575_l());
/* 38 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SOpenDialogueScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 42 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 43 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/* 45 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SOpenDialogueScreenPacket message) {
/* 51 */       ClientWorld world = (Minecraft.func_71410_x()).field_71441_e;
/* 52 */       Entity entity = world.func_73045_a(message.entityId);
/*    */       
/* 54 */       if (entity == null || message.interaction == null || !(entity instanceof LivingEntity)) {
/*    */         return;
/*    */       }
/*    */       
/* 58 */       LivingEntity living = (LivingEntity)entity;
/*    */       
/* 60 */       Minecraft.func_71410_x().func_147108_a((Screen)new DialogueScreen(living, message.interaction));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\serve\\ui\SOpenDialogueScreenPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */