/*    */ package xyz.pixelatedw.mineminenomi.packets.client.ui;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.LinkedHashSet;
/*    */ import java.util.function.Supplier;
/*    */ import java.util.stream.Collectors;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.JollyRogerElement;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModJollyRogers;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenJollyRogerCreatorScreenPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class COpenJollyRogerCreatorScreenPacket
/*    */ {
/*    */   public void encode(PacketBuffer buffer) {}
/*    */   
/*    */   public static COpenJollyRogerCreatorScreenPacket decode(PacketBuffer buffer) {
/* 28 */     COpenJollyRogerCreatorScreenPacket msg = new COpenJollyRogerCreatorScreenPacket();
/*    */     
/* 30 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(COpenJollyRogerCreatorScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 34 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER) {
/* 35 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             ExtendedWorldData worldData = ExtendedWorldData.get();
/*    */             
/*    */             Crew crew = worldData.getCrewWithMember(serverPlayerEntity.func_110124_au());
/*    */             
/*    */             if (crew == null) {
/*    */               return;
/*    */             }
/*    */             
/*    */             Collection<RegistryObject<JollyRogerElement>> allElements = ModJollyRogers.JOLLY_ROGER_ELEMENTS.getEntries();
/*    */             LinkedHashSet<JollyRogerElement> elements = (LinkedHashSet<JollyRogerElement>)allElements.stream().filter(()).map(()).collect(Collectors.toCollection(LinkedHashSet::new));
/*    */             WyNetwork.sendTo(new SOpenJollyRogerCreatorScreenPacket(false, crew, elements), (PlayerEntity)serverPlayerEntity);
/*    */           });
/*    */     }
/* 51 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\clien\\ui\COpenJollyRogerCreatorScreenPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */