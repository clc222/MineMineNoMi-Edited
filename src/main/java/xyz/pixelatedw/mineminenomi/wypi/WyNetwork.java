/*    */ package xyz.pixelatedw.mineminenomi.wypi;
/*    */ 
/*    */ import java.util.function.BiConsumer;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import net.minecraftforge.fml.network.NetworkRegistry;
/*    */ import net.minecraftforge.fml.network.PacketDistributor;
/*    */ import net.minecraftforge.fml.network.simple.SimpleChannel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WyNetwork
/*    */ {
/* 23 */   private static int packet = 0;
/* 24 */   private static final String PROTOCOL_VERSION = Integer.toString(1);
/* 25 */   public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation("mineminenomi", "main_channel"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
/*    */ 
/*    */ 
/*    */   
/*    */   public static <MSG> void registerPacket(Class<MSG> messageType, BiConsumer<MSG, PacketBuffer> encoder, Function<PacketBuffer, MSG> decoder, BiConsumer<MSG, Supplier<NetworkEvent.Context>> messageConsumer) {
/* 30 */     INSTANCE.registerMessage(packet++, messageType, encoder, decoder, messageConsumer);
/*    */   }
/*    */ 
/*    */   
/*    */   public static <MSG> void sendToServer(MSG msg) {
/* 35 */     INSTANCE.sendToServer(msg);
/*    */   }
/*    */   
/*    */   public static <MSG> void sendTo(MSG msg, PlayerEntity player) {
/* 39 */     if (player instanceof ServerPlayerEntity && ((ServerPlayerEntity)player).field_71135_a != null) {
/* 40 */       INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity)player), msg);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public static <MSG> void sendToAll(MSG msg) {
/* 46 */     INSTANCE.send(PacketDistributor.ALL.noArg(), msg);
/*    */   }
/*    */ 
/*    */   
/*    */   public static <MSG> void sendToAllTracking(MSG msg, Entity tracked) {
/* 51 */     INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> tracked), msg);
/*    */   }
/*    */ 
/*    */   
/*    */   public static <MSG> void sendToAllTrackingAndSelf(MSG msg, Entity tracked) {
/* 56 */     INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> tracked), msg);
/*    */   }
/*    */ 
/*    */   
/*    */   public static <MSG> void sendToAllAround(MSG msg, Entity sender) {
/* 61 */     sendToAllAroundDistance(msg, sender.field_70170_p, sender.func_213303_ch(), 256);
/*    */   }
/*    */ 
/*    */   
/*    */   public static <MSG> void sendToAllAroundDistance(MSG msg, World world, Vector3d pivot, int distance) {
/* 66 */     INSTANCE.send(PacketDistributor.NEAR.with(() -> new PacketDistributor.TargetPoint(pivot.func_82615_a(), pivot.func_82617_b(), pivot.func_82616_c(), distance, world.func_234923_W_())), msg);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\wypi\WyNetwork.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */