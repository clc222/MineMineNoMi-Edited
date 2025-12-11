/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ui;
/*    */ 
/*    */ import java.util.LinkedHashSet;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.ModMain;
/*    */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.JollyRogerElement;
/*    */ import xyz.pixelatedw.mineminenomi.screens.JollyRogerCreatorScreen;
/*    */ 
/*    */ 
/*    */ public class SOpenJollyRogerCreatorScreenPacket
/*    */ {
/*    */   private boolean isEditing;
/*    */   private Crew crew;
/*    */   private LinkedHashSet<JollyRogerElement> elements;
/*    */   
/*    */   public SOpenJollyRogerCreatorScreenPacket() {}
/*    */   
/*    */   public SOpenJollyRogerCreatorScreenPacket(boolean isEditing, Crew crew, LinkedHashSet<JollyRogerElement> elements) {
/* 28 */     this.isEditing = isEditing;
/* 29 */     this.crew = crew;
/* 30 */     this.elements = elements;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 34 */     buffer.writeBoolean(this.isEditing);
/* 35 */     int size = this.elements.size();
/* 36 */     buffer.writeInt(size);
/* 37 */     for (JollyRogerElement elem : this.elements) {
/* 38 */       buffer.func_192572_a(elem.getRegistryName());
/*    */     }
/* 40 */     buffer.func_150786_a(this.crew.write());
/*    */   }
/*    */   
/*    */   public static SOpenJollyRogerCreatorScreenPacket decode(PacketBuffer buffer) {
/* 44 */     SOpenJollyRogerCreatorScreenPacket msg = new SOpenJollyRogerCreatorScreenPacket();
/* 45 */     msg.isEditing = buffer.readBoolean();
/* 46 */     int size = buffer.readInt();
/* 47 */     msg.elements = new LinkedHashSet<>();
/* 48 */     for (int i = 0; i < size; i++) {
/* 49 */       ResourceLocation res = buffer.func_192575_l();
/* 50 */       JollyRogerElement elem = (JollyRogerElement)ModRegistries.JOLLY_ROGER_ELEMENTS.getValue(res);
/* 51 */       if (elem != null) {
/* 52 */         msg.elements.add(elem);
/*    */       } else {
/*    */         
/* 55 */         ModMain.LOGGER.warn(res + " could not be found as a jolly roger element!");
/*    */       } 
/*    */     } 
/* 58 */     CompoundNBT jollyRogerData = buffer.func_244273_m();
/* 59 */     msg.crew = Crew.from(jollyRogerData);
/* 60 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SOpenJollyRogerCreatorScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 64 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 65 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/* 67 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SOpenJollyRogerCreatorScreenPacket message) {
/* 73 */       JollyRogerCreatorScreen.open(message.isEditing, message.crew, message.elements);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\serve\\ui\SOpenJollyRogerCreatorScreenPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */