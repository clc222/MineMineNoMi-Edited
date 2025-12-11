/*    */ package xyz.pixelatedw.mineminenomi.packets.client.ui;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenCharacterCreatorScreenPacket;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenPlayerScreenPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class COpenPlayerScreenPacket
/*    */ {
/*    */   public void encode(PacketBuffer buffer) {}
/*    */   
/*    */   public static COpenPlayerScreenPacket decode(PacketBuffer buffer) {
/* 33 */     COpenPlayerScreenPacket msg = new COpenPlayerScreenPacket();
/*    */     
/* 35 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(COpenPlayerScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 40 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 42 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             IEntityStats entityProps = EntityStatsCapability.get((LivingEntity)serverPlayerEntity);
/*    */             
/*    */             if (!entityProps.hasRace() || !entityProps.hasFaction() || !entityProps.hasFightingStyle()) {
/*    */               boolean hasRandomizedRace = CommonConfig.INSTANCE.getRaceRandomizer();
/*    */               
/*    */               boolean allowMinkRaceSelect = CommonConfig.INSTANCE.getAllowMinkRaceSelect();
/*    */               
/*    */               WyNetwork.sendTo(new SOpenCharacterCreatorScreenPacket(hasRandomizedRace, allowMinkRaceSelect), (PlayerEntity)serverPlayerEntity);
/*    */             } else {
/*    */               IAbilityData props = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
/*    */               
/*    */               IQuestData questProps = QuestDataCapability.get((PlayerEntity)serverPlayerEntity);
/*    */               
/*    */               IChallengesData challengeProps = ChallengesDataCapability.get((PlayerEntity)serverPlayerEntity);
/*    */               
/*    */               ExtendedWorldData worldData = ExtendedWorldData.get();
/*    */               
/*    */               double doriki = entityProps.getDoriki();
/*    */               
/*    */               boolean hasQuests = CommonConfig.INSTANCE.isQuestsEnabled();
/*    */               
/*    */               int questAmount = questProps.countInProgressQuests();
/*    */               
/*    */               boolean hasChallenges = CommonConfig.INSTANCE.isChallengesEnabled();
/*    */               
/*    */               int challengeAmount = challengeProps.countChallenges();
/*    */               
/*    */               boolean isInCombat = entityProps.isInCombatCache();
/*    */               
/*    */               boolean isInChallengeDimension = WyHelper.isInChallengeDimension(((PlayerEntity)serverPlayerEntity).field_70170_p);
/*    */               int invites = challengeProps.getInvitations().size();
/*    */               boolean hasCrew = (worldData.getCrewWithMember(serverPlayerEntity.func_110124_au()) != null);
/*    */               WyNetwork.sendTo(new SOpenPlayerScreenPacket(doriki, hasQuests, questAmount, hasChallenges, challengeAmount, isInCombat, isInChallengeDimension, invites, hasCrew), (PlayerEntity)serverPlayerEntity);
/*    */             } 
/*    */           });
/*    */     }
/* 81 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\clien\\ui\COpenPlayerScreenPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */