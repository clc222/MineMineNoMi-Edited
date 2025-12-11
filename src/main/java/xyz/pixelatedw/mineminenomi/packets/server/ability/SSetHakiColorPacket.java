/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ability;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.Random;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*    */ import xyz.pixelatedw.mineminenomi.packets.client.ability.CSetHakiColorPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class SSetHakiColorPacket
/*    */ {
/*    */   private int entityId;
/*    */   private CommonConfig.HaoshokuColoringLogic coloringLogic;
/*    */   
/*    */   public SSetHakiColorPacket() {}
/*    */   
/*    */   public SSetHakiColorPacket(int entityId, CommonConfig.HaoshokuColoringLogic coloringLogic) {
/* 30 */     this.entityId = entityId;
/* 31 */     this.coloringLogic = coloringLogic;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 35 */     buffer.writeInt(this.entityId);
/* 36 */     buffer.writeInt(this.coloringLogic.ordinal());
/*    */   }
/*    */   
/*    */   public static SSetHakiColorPacket decode(PacketBuffer buffer) {
/* 40 */     SSetHakiColorPacket msg = new SSetHakiColorPacket();
/*    */     
/* 42 */     msg.entityId = buffer.readInt();
/* 43 */     msg.coloringLogic = CommonConfig.HaoshokuColoringLogic.values()[buffer.readInt()];
/*    */     
/* 45 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SSetHakiColorPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 49 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 50 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */     
/* 53 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SSetHakiColorPacket message) {
/* 59 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/*    */       
/* 61 */       if (target == null || !(target instanceof LivingEntity)) {
/*    */         return;
/*    */       }
/*    */       
/* 65 */       IHakiData props = HakiDataCapability.get((LivingEntity)target);
/*    */       
/* 67 */       int color = 16711680;
/* 68 */       if (message.coloringLogic == CommonConfig.HaoshokuColoringLogic.CUSTOM) {
/* 69 */         color = ClientConfig.INSTANCE.getHakiColor().getRGB();
/* 70 */       } else if (message.coloringLogic == CommonConfig.HaoshokuColoringLogic.RANDOM) {
/* 71 */         Random rand = new Random(target.func_110124_au().getMostSignificantBits());
/*    */         
/* 73 */         int r = (int)WyHelper.randomWithRange(rand, 0, 255);
/* 74 */         int g = (int)WyHelper.randomWithRange(rand, 0, 255);
/* 75 */         int b = (int)WyHelper.randomWithRange(rand, 0, 255);
/*    */         
/* 77 */         Color c = new Color(r, g, b);
/*    */         
/* 79 */         color = c.getRGB();
/*    */       } 
/*    */       
/* 82 */       int colorRGB = WyHelper.intToRGB(color, 50).getRGB();
/*    */       
/* 84 */       props.setHaoshokuHakiColour(colorRGB);
/*    */       
/* 86 */       WyNetwork.sendToServer(new CSetHakiColorPacket(colorRGB));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\SSetHakiColorPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */