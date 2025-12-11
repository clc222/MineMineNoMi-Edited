/*    */ package xyz.pixelatedw.mineminenomi.packets.client.crew;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.CrewEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ 
/*    */ public class CKickFromCrewPacket {
/*    */   private UUID uuid;
/*    */   
/*    */   public CKickFromCrewPacket(UUID uuid) {
/* 25 */     this.uuid = uuid;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 30 */     buffer.func_179252_a(this.uuid);
/*    */   }
/*    */   public CKickFromCrewPacket() {}
/*    */   
/*    */   public static CKickFromCrewPacket decode(PacketBuffer buffer) {
/* 35 */     CKickFromCrewPacket msg = new CKickFromCrewPacket();
/* 36 */     msg.uuid = buffer.func_179253_g();
/* 37 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(CKickFromCrewPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 42 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 44 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             UUID uuid = message.uuid;
/*    */             
/*    */             ExtendedWorldData worldData = ExtendedWorldData.get((IWorld)((PlayerEntity)serverPlayerEntity).field_70170_p);
/*    */             
/*    */             Crew crew = worldData.getCrewWithCaptain(serverPlayerEntity.func_110124_au());
/*    */             
/*    */             PlayerEntity memberPlayer = ((PlayerEntity)serverPlayerEntity).field_70170_p.func_217371_b(uuid);
/*    */             if (crew != null && crew.hasMember(uuid) && !crew.getCaptain().getUUID().equals(uuid)) {
/*    */               CrewEvent.Kick event = new CrewEvent.Kick(memberPlayer, crew);
/*    */               if (!MinecraftForge.EVENT_BUS.post((Event)event)) {
/*    */                 FactionHelper.sendMessageToCrew(((PlayerEntity)serverPlayerEntity).field_70170_p, crew, (ITextComponent)new TranslationTextComponent(ModI18n.CREW_MESSAGE_KICKED, new Object[] { crew.getMember(uuid).getUsername() }));
/*    */                 worldData.removeCrewMember(((PlayerEntity)serverPlayerEntity).field_70170_p, crew, uuid);
/*    */               } 
/*    */             } 
/*    */           });
/*    */     }
/* 63 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\crew\CKickFromCrewPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */