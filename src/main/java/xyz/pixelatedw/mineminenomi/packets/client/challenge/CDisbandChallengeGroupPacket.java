/*    */ package xyz.pixelatedw.mineminenomi.packets.client.challenge;
/*    */ 
/*    */ import com.google.common.collect.UnmodifiableIterator;
/*    */ import java.util.UUID;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.Util;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ 
/*    */ 
/*    */ public class CDisbandChallengeGroupPacket
/*    */ {
/*    */   public void encode(PacketBuffer buffer) {}
/*    */   
/*    */   public static CDisbandChallengeGroupPacket decode(PacketBuffer buffer) {
/* 26 */     CDisbandChallengeGroupPacket msg = new CDisbandChallengeGroupPacket();
/* 27 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(CDisbandChallengeGroupPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 31 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER) {
/* 32 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             if (!CommonConfig.INSTANCE.isChallengesEnabled()) {
/*    */               return;
/*    */             }
/*    */             
/*    */             ServerPlayerEntity sender = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             ServerWorld world = (ServerWorld)sender.field_70170_p;
/*    */             
/*    */             IChallengesData props = ChallengesDataCapability.get((PlayerEntity)sender);
/*    */             if (props.getGroupMembersIds().isEmpty()) {
/*    */               return;
/*    */             }
/*    */             if (props.isInGroup()) {
/*    */               return;
/*    */             }
/*    */             TranslationTextComponent disbandMessage = new TranslationTextComponent(ModI18n.CHALLENGE_MESSAGE_GROUP_DISBAND, new Object[] { sender.func_146103_bH().getName() });
/*    */             sender.func_145747_a((ITextComponent)disbandMessage, Util.field_240973_b_);
/*    */             props.setInGroup(null);
/*    */             UnmodifiableIterator<UUID> unmodifiableIterator = props.getGroupMembersIds().iterator();
/*    */             while (unmodifiableIterator.hasNext()) {
/*    */               UUID id = unmodifiableIterator.next();
/*    */               PlayerEntity member = world.func_217371_b(id);
/*    */               if (member == null) {
/*    */                 continue;
/*    */               }
/*    */               member.func_145747_a((ITextComponent)disbandMessage, Util.field_240973_b_);
/*    */               ChallengesDataCapability.get(member).setInGroup(null);
/*    */               props.removeGroupMember(id);
/*    */             } 
/*    */           });
/*    */     }
/* 64 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\challenge\CDisbandChallengeGroupPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */