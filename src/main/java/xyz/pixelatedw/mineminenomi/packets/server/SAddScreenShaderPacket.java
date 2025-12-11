/*    */ package xyz.pixelatedw.mineminenomi.packets.server;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ 
/*    */ public class SAddScreenShaderPacket {
/*    */   private ResourceLocation shader;
/*    */   
/*    */   public SAddScreenShaderPacket() {}
/*    */   
/*    */   public SAddScreenShaderPacket(ResourceLocation shader) {
/* 22 */     this.shader = shader;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 26 */     buffer.func_192572_a(this.shader);
/*    */   }
/*    */   
/*    */   public static SAddScreenShaderPacket decode(PacketBuffer buffer) {
/* 30 */     SAddScreenShaderPacket msg = new SAddScreenShaderPacket();
/* 31 */     msg.shader = buffer.func_192575_l();
/* 32 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SAddScreenShaderPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 36 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 37 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/* 39 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SAddScreenShaderPacket message) {
/* 45 */       Minecraft mc = Minecraft.func_71410_x();
/* 46 */       ClientPlayerEntity player = mc.field_71439_g;
/*    */       
/* 48 */       if (player == null) {
/*    */         return;
/*    */       }
/*    */       
/* 52 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*    */       
/* 54 */       props.addScreenShader(message.shader);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\SAddScreenShaderPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */