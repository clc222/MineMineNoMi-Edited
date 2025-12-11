/*     */ package xyz.pixelatedw.mineminenomi.packets.client.challenge;
/*     */ 
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.fml.network.NetworkDirection;
/*     */ import net.minecraftforge.fml.network.NetworkEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeInvitation;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ChallengesWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.challenge.SUpdateChallengeGroupPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class CAcceptChallengeInvitationPacket {
/*     */   private UUID id;
/*     */   
/*     */   public CAcceptChallengeInvitationPacket(ChallengeInvitation invite) {
/*  32 */     this.id = invite.getSenderId();
/*  33 */     this.groupSlot = invite.getGroupSlot();
/*     */   } private int groupSlot;
/*     */   public CAcceptChallengeInvitationPacket() {}
/*     */   public void encode(PacketBuffer buffer) {
/*  37 */     buffer.func_179252_a(this.id);
/*  38 */     buffer.writeInt(this.groupSlot);
/*     */   }
/*     */   
/*     */   public static CAcceptChallengeInvitationPacket decode(PacketBuffer buffer) {
/*  42 */     CAcceptChallengeInvitationPacket msg = new CAcceptChallengeInvitationPacket();
/*  43 */     msg.id = buffer.func_179253_g();
/*  44 */     msg.groupSlot = buffer.readInt();
/*  45 */     return msg;
/*     */   }
/*     */   
/*     */   public static void handle(CAcceptChallengeInvitationPacket message, Supplier<NetworkEvent.Context> ctx) {
/*  49 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER) {
/*  50 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*     */             if (!CommonConfig.INSTANCE.isChallengesEnabled()) {
/*     */               return;
/*     */             }
/*     */             
/*     */             ServerPlayerEntity sender = ((NetworkEvent.Context)ctx.get()).getSender();
/*     */             
/*     */             ServerWorld world = (ServerWorld)sender.field_70170_p;
/*     */             
/*     */             IChallengesData props = ChallengesDataCapability.get((PlayerEntity)sender);
/*     */             
/*     */             if (props.isInGroup()) {
/*     */               return;
/*     */             }
/*     */             
/*     */             if (WyHelper.isInCombat((LivingEntity)sender)) {
/*     */               return;
/*     */             }
/*     */             
/*     */             Optional<ChallengeInvitation> inviteOptional = props.getInvitationFrom(message.id);
/*     */             
/*     */             if (!inviteOptional.isPresent()) {
/*     */               return;
/*     */             }
/*     */             
/*     */             ChallengeInvitation invite = inviteOptional.get();
/*     */             
/*     */             PlayerEntity invitationSender = world.func_217371_b(invite.getSenderId());
/*     */             
/*     */             if (invitationSender == null || !invitationSender.func_70089_S()) {
/*     */               return;
/*     */             }
/*     */             
/*     */             if (invite.isExpired((World)world)) {
/*     */               return;
/*     */             }
/*     */             
/*     */             ChallengesWorldData worldData = ChallengesWorldData.get();
/*     */             
/*     */             if (worldData.getInProgressChallengeFor((LivingEntity)sender) != null) {
/*     */               return;
/*     */             }
/*     */             
/*     */             IChallengesData senderChallengeProps = ChallengesDataCapability.get(invitationSender);
/*     */             
/*     */             TranslationTextComponent joinMessage = new TranslationTextComponent(ModI18n.CHALLENGE_MESSAGE_GROUP_JOIN, new Object[] { sender.func_146103_bH().getName() });
/*     */             
/*     */             invitationSender.func_145747_a((ITextComponent)joinMessage, Util.field_240973_b_);
/*     */             
/*     */             UnmodifiableIterator<UUID> unmodifiableIterator = senderChallengeProps.getGroupMembersIds().iterator();
/*     */             while (unmodifiableIterator.hasNext()) {
/*     */               UUID memberId = unmodifiableIterator.next();
/*     */               PlayerEntity member = world.func_217371_b(memberId);
/*     */               member.func_145747_a((ITextComponent)joinMessage, Util.field_240973_b_);
/*     */             } 
/*     */             senderChallengeProps.addGroupMember((PlayerEntity)sender);
/*     */             props.setInGroup(invitationSender.func_110124_au());
/*     */             props.removeInvitationFrom(invitationSender);
/*     */             WyNetwork.sendTo(new SUpdateChallengeGroupPacket(sender.func_110124_au(), message.groupSlot), invitationSender);
/*     */           });
/*     */     }
/* 111 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\challenge\CAcceptChallengeInvitationPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */