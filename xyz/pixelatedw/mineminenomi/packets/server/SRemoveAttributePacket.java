/*    */ package xyz.pixelatedw.mineminenomi.packets.server;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SRemoveAttributePacket
/*    */ {
/*    */   private UUID uuid;
/*    */   private Attribute attr;
/*    */   
/*    */   public SRemoveAttributePacket() {}
/*    */   
/*    */   public SRemoveAttributePacket(Attribute attr, UUID uuid) {
/* 28 */     this.uuid = uuid;
/* 29 */     this.attr = attr;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 34 */     buffer.func_179252_a(this.uuid);
/* 35 */     String str = this.attr.getRegistryName().toString();
/* 36 */     buffer.writeInt(str.length());
/* 37 */     buffer.func_211400_a(str, str.length());
/*    */   }
/*    */ 
/*    */   
/*    */   public static SRemoveAttributePacket decode(PacketBuffer buffer) {
/* 42 */     SRemoveAttributePacket msg = new SRemoveAttributePacket();
/* 43 */     msg.uuid = buffer.func_179253_g();
/* 44 */     int len = buffer.readInt();
/* 45 */     String attrReg = buffer.func_150789_c(len);
/* 46 */     Attribute attr = (Attribute)GameRegistry.findRegistry(Attribute.class).getValue(new ResourceLocation(attrReg));
/* 47 */     msg.attr = attr;
/* 48 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SRemoveAttributePacket message, Supplier<NetworkEvent.Context> ctx) {
/* 53 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 55 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 60 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SRemoveAttributePacket message) {
/* 68 */       ClientPlayerEntity clientPlayerEntity = (Minecraft.func_71410_x()).field_71439_g;
/*    */       
/* 70 */       clientPlayerEntity.func_110148_a(message.attr).func_188479_b(message.uuid);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\SRemoveAttributePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */