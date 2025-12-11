/*    */ package xyz.pixelatedw.mineminenomi.packets.client.challenge;
/*    */ 
/*    */ import com.google.common.collect.UnmodifiableIterator;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ChallengesWorldData;
/*    */ 
/*    */ public class CStartChallengePacket {
/*    */   private ResourceLocation id;
/*    */   private boolean isFree;
/*    */   
/*    */   public CStartChallengePacket() {}
/*    */   
/*    */   public CStartChallengePacket(ResourceLocation resourceLocation, boolean isFree) {
/* 30 */     this.id = resourceLocation;
/* 31 */     this.isFree = isFree;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 35 */     buffer.func_192572_a(this.id);
/* 36 */     buffer.writeBoolean(this.isFree);
/*    */   }
/*    */   
/*    */   public static CStartChallengePacket decode(PacketBuffer buffer) {
/* 40 */     CStartChallengePacket msg = new CStartChallengePacket();
/* 41 */     msg.id = buffer.func_192575_l();
/* 42 */     msg.isFree = buffer.readBoolean();
/* 43 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(CStartChallengePacket message, Supplier<NetworkEvent.Context> ctx) {
/* 47 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER) {
/* 48 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             if (!CommonConfig.INSTANCE.isChallengesEnabled()) {
/*    */               return;
/*    */             }
/*    */             
/*    */             ServerPlayerEntity player = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             ServerWorld world = (ServerWorld)player.field_70170_p;
/*    */             
/*    */             IChallengesData props = ChallengesDataCapability.get((PlayerEntity)player);
/*    */             
/*    */             ChallengeCore<?> core = (ChallengeCore)ModRegistries.CHALLENGES.getValue(message.id);
/*    */             if (core == null) {
/*    */               return;
/*    */             }
/*    */             List<LivingEntity> list = new ArrayList<>();
/*    */             UnmodifiableIterator<UUID> unmodifiableIterator = props.getGroupMembersIds().iterator();
/*    */             while (unmodifiableIterator.hasNext()) {
/*    */               UUID id = unmodifiableIterator.next();
/*    */               PlayerEntity groupMember = world.func_217371_b(id);
/*    */               if (groupMember != null) {
/*    */                 list.add(groupMember);
/*    */               }
/*    */             } 
/*    */             ChallengesWorldData.get().startChallenge(player, list, core, message.isFree);
/*    */           });
/*    */     }
/* 75 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\challenge\CStartChallengePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */