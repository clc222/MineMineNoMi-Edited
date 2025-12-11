/*    */ package xyz.pixelatedw.mineminenomi.packets.server;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.RegistryKey;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.registry.Registry;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SSyncDynDimensionsPacket
/*    */ {
/*    */   private Set<RegistryKey<World>> addedDims;
/*    */   private Set<RegistryKey<World>> removedDims;
/*    */   
/*    */   public SSyncDynDimensionsPacket() {}
/*    */   
/*    */   public SSyncDynDimensionsPacket(Set<RegistryKey<World>> addedDims, Set<RegistryKey<World>> removedDims) {
/* 30 */     this.addedDims = addedDims;
/* 31 */     this.removedDims = removedDims;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buf) {
/* 36 */     buf.func_150787_b(this.addedDims.size());
/* 37 */     this.addedDims.forEach(key -> buf.func_192572_a(key.func_240901_a_()));
/* 38 */     buf.func_150787_b(this.removedDims.size());
/* 39 */     this.removedDims.forEach(key -> buf.func_192572_a(key.func_240901_a_()));
/*    */   }
/*    */ 
/*    */   
/*    */   public static SSyncDynDimensionsPacket decode(PacketBuffer buf) {
/* 44 */     SSyncDynDimensionsPacket msg = new SSyncDynDimensionsPacket();
/*    */     
/* 46 */     Set<RegistryKey<World>> addedDims = new HashSet<>();
/* 47 */     Set<RegistryKey<World>> removedDims = new HashSet<>();
/*    */     
/* 49 */     int addedSize = buf.func_150792_a();
/* 50 */     for (int i = 0; i < addedSize; i++) {
/*    */       
/* 52 */       ResourceLocation dim = buf.func_192575_l();
/* 53 */       addedDims.add(RegistryKey.func_240903_a_(Registry.field_239699_ae_, dim));
/*    */     } 
/*    */     
/* 56 */     int removedSize = buf.func_150792_a();
/* 57 */     for (int j = 0; j < removedSize; j++) {
/* 58 */       ResourceLocation dim = buf.func_192575_l();
/* 59 */       removedDims.add(RegistryKey.func_240903_a_(Registry.field_239699_ae_, dim));
/*    */     } 
/*    */     
/* 62 */     msg.addedDims = addedDims;
/* 63 */     msg.removedDims = removedDims;
/*    */     
/* 65 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SSyncDynDimensionsPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 70 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 72 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 77 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SSyncDynDimensionsPacket msg) {
/* 85 */       ClientPlayerEntity player = (Minecraft.func_71410_x()).field_71439_g;
/* 86 */       if (player == null) {
/*    */         return;
/*    */       }
/*    */       
/* 90 */       Set<RegistryKey<World>> levels = player.field_71174_a.func_239164_m_();
/* 91 */       levels.addAll(msg.addedDims);
/* 92 */       msg.removedDims.forEach(levels::remove);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\SSyncDynDimensionsPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */