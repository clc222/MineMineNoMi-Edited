/*     */ package xyz.pixelatedw.mineminenomi.packets.client.challenge;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.fml.network.NetworkDirection;
/*     */ import net.minecraftforge.fml.network.NetworkEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeInvitation;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class CSendChallengeInvitationPacket {
/*  26 */   private static final TargetsPredicate TARGET_PICKER = (new TargetsPredicate()).testFriendlyFaction();
/*     */   
/*     */   private UUID targetId;
/*     */   private ResourceLocation challengeId;
/*     */   private int slotId;
/*     */   
/*     */   public CSendChallengeInvitationPacket() {}
/*     */   
/*     */   public CSendChallengeInvitationPacket(PlayerEntity target, ChallengeCore<?> challenge, int slotId) {
/*  35 */     this.targetId = target.func_110124_au();
/*  36 */     this.challengeId = challenge.getRegistryName();
/*  37 */     this.slotId = slotId;
/*     */   }
/*     */   
/*     */   public void encode(PacketBuffer buffer) {
/*  41 */     buffer.func_179252_a(this.targetId);
/*  42 */     buffer.func_192572_a(this.challengeId);
/*  43 */     buffer.writeInt(this.slotId);
/*     */   }
/*     */   
/*     */   public static CSendChallengeInvitationPacket decode(PacketBuffer buffer) {
/*  47 */     CSendChallengeInvitationPacket msg = new CSendChallengeInvitationPacket();
/*  48 */     msg.targetId = buffer.func_179253_g();
/*  49 */     msg.challengeId = buffer.func_192575_l();
/*  50 */     msg.slotId = buffer.readInt();
/*  51 */     return msg;
/*     */   }
/*     */   
/*     */   public static void handle(CSendChallengeInvitationPacket message, Supplier<NetworkEvent.Context> ctx) {
/*  55 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER) {
/*  56 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*     */             if (!CommonConfig.INSTANCE.isChallengesEnabled()) {
/*     */               return;
/*     */             }
/*     */             
/*     */             ServerPlayerEntity sender = ((NetworkEvent.Context)ctx.get()).getSender();
/*     */             
/*     */             ServerWorld world = (ServerWorld)sender.field_70170_p;
/*     */             
/*     */             PlayerEntity target = world.func_217371_b(message.targetId);
/*     */             
/*     */             if (target == null || !target.func_70089_S()) {
/*     */               return;
/*     */             }
/*     */             
/*     */             if (!TARGET_PICKER.test((LivingEntity)sender, (LivingEntity)target)) {
/*     */               return;
/*     */             }
/*     */             
/*     */             if (WyHelper.isInCombat((LivingEntity)target)) {
/*     */               return;
/*     */             }
/*     */             
/*     */             ChallengeCore<?> challenge = (ChallengeCore)ModRegistries.CHALLENGES.getValue(message.challengeId);
/*     */             
/*     */             if (challenge == null) {
/*     */               return;
/*     */             }
/*     */             
/*     */             IChallengesData props = ChallengesDataCapability.get(target);
/*     */             
/*     */             if (props.isInGroup()) {
/*     */               return;
/*     */             }
/*     */             
/*     */             if (!props.hasInvitationFrom((PlayerEntity)sender)) {
/*     */               ChallengeInvitation invite = new ChallengeInvitation(sender.func_110124_au(), challenge, world.func_82737_E(), message.slotId);
/*     */               
/*     */               props.addInvitation(invite);
/*     */               
/*     */               TranslationTextComponent textMessage = new TranslationTextComponent(ModI18n.CHALLENGE_MESSAGE_INVITATION, new Object[] { sender.func_146103_bH().getName(), challenge.getLocalizedObjective().getString() });
/*     */               
/*     */               target.func_145747_a((ITextComponent)textMessage, Util.field_240973_b_);
/*     */             } 
/*     */           });
/*     */     }
/* 102 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\challenge\CSendChallengeInvitationPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */