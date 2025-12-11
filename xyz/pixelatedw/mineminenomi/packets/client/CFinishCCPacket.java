/*     */ package xyz.pixelatedw.mineminenomi.packets.client;
/*     */ 
/*     */ import java.util.Random;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.fml.network.NetworkDirection;
/*     */ import net.minecraftforge.fml.network.NetworkEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.charactercreator.CharacterCreatorEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.charactercreator.FactionId;
/*     */ import xyz.pixelatedw.mineminenomi.api.charactercreator.RaceId;
/*     */ import xyz.pixelatedw.mineminenomi.api.charactercreator.StyleId;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.SetPlayerDetailsEvent;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAdvancements;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncAbilityDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ 
/*     */ public class CFinishCCPacket
/*     */ {
/*     */   private int factionId;
/*     */   private int raceId;
/*     */   
/*     */   public CFinishCCPacket(int factionId, int raceId, int styleId, int minkRaceId) {
/*  38 */     this.factionId = factionId;
/*  39 */     this.raceId = raceId;
/*  40 */     this.styleId = styleId;
/*  41 */     this.minkRaceId = minkRaceId;
/*     */   } private int styleId; private int minkRaceId;
/*     */   public CFinishCCPacket() {}
/*     */   public void encode(PacketBuffer buffer) {
/*  45 */     buffer.writeInt(this.factionId);
/*  46 */     buffer.writeInt(this.raceId);
/*  47 */     buffer.writeInt(this.styleId);
/*  48 */     buffer.writeInt(this.minkRaceId);
/*     */   }
/*     */   
/*     */   public static CFinishCCPacket decode(PacketBuffer buffer) {
/*  52 */     CFinishCCPacket msg = new CFinishCCPacket();
/*  53 */     msg.factionId = buffer.readInt();
/*  54 */     msg.raceId = buffer.readInt();
/*  55 */     msg.styleId = buffer.readInt();
/*  56 */     msg.minkRaceId = buffer.readInt();
/*  57 */     return msg;
/*     */   }
/*     */   
/*     */   public static void handle(CFinishCCPacket message, Supplier<NetworkEvent.Context> ctx) {
/*  61 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER) {
/*  62 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*     */             FactionId faction; RaceId race; StyleId style;
/*     */             ServerPlayerEntity player = ((NetworkEvent.Context)ctx.get()).getSender();
/*     */             IEntityStats entityProps = EntityStatsCapability.get((LivingEntity)player);
/*     */             IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */             Random rand = new Random();
/*  68 */             boolean hasCharBook = (!player.func_184614_ca().func_190926_b() && player.func_184614_ca().func_77973_b().equals(ModItems.CHARACTER_CREATOR.get()));
/*     */             
/*  70 */             boolean hasEmptyStats = (!entityProps.hasFaction() || !entityProps.hasFightingStyle() || !entityProps.hasRace());
/*     */             
/*     */             if (!hasCharBook && !hasEmptyStats) {
/*     */               return;
/*     */             }
/*     */             
/*     */             message.factionId %= CharacterCreatorEntry.FACTION.getSize() + 1;
/*     */             
/*     */             message.raceId %= CharacterCreatorEntry.RACE.getSize() + 1;
/*     */             
/*     */             message.styleId %= CharacterCreatorEntry.STYLE.getSize() + 1;
/*     */             
/*     */             if (message.factionId == -1) {
/*     */               faction = (FactionId)CharacterCreatorEntry.FACTION.getInfo(rand.nextInt(CharacterCreatorEntry.FACTION.getSize()));
/*     */             } else {
/*     */               faction = (FactionId)CharacterCreatorEntry.FACTION.getInfo(message.factionId);
/*     */             } 
/*     */             
/*     */             entityProps.setFaction(faction.getRegistryName());
/*     */             
/*     */             ModAdvancements.SELECT_FACTION.trigger(player, faction.getRegistryName());
/*     */             
/*     */             if (message.raceId == -1 || CommonConfig.INSTANCE.getRaceRandomizer()) {
/*     */               race = (RaceId)CharacterCreatorEntry.RACE.getInfo(rand.nextInt(CharacterCreatorEntry.RACE.getSize()));
/*     */             } else {
/*     */               race = (RaceId)CharacterCreatorEntry.RACE.getInfo(message.raceId);
/*     */             } 
/*     */             
/*     */             entityProps.setRace(race.getRegistryName());
/*     */             
/*     */             if (!race.getSubRaces().isEmpty()) {
/*     */               String subRace;
/*     */               
/*     */               message.minkRaceId %= race.getSubRaces().size();
/*     */               
/*     */               if (CommonConfig.INSTANCE.getAllowMinkRaceSelect()) {
/*     */                 subRace = race.getSubRaces().get(message.minkRaceId);
/*     */               } else {
/*     */                 subRace = race.getSubRaces().get(rand.nextInt(race.getSubRaces().size()));
/*     */               } 
/*     */               
/*     */               entityProps.setSubRace(subRace);
/*     */             } 
/*     */             
/*     */             ModAdvancements.SELECT_RACE.trigger(player, race.getRegistryName());
/*     */             
/*     */             if (message.styleId == -1) {
/*     */               style = (StyleId)CharacterCreatorEntry.STYLE.getInfo(rand.nextInt(CharacterCreatorEntry.STYLE.getSize()));
/*     */             } else {
/*     */               style = (StyleId)CharacterCreatorEntry.STYLE.getInfo(message.styleId);
/*     */             } 
/*     */             
/*     */             entityProps.setFightingStyle(style.getRegistryName());
/*     */             
/*     */             ModAdvancements.SELECT_STYLE.trigger(player, style.getRegistryName());
/*     */             
/*     */             if (entityProps.isCyborg()) {
/*     */               entityProps.setCola(entityProps.getMaxCola());
/*     */             }
/*     */             
/*     */             for (ItemStack is : player.field_71071_by.field_70462_a) {
/*     */               if (is != null && is.func_77973_b() instanceof xyz.pixelatedw.mineminenomi.items.CharacterCreatorItem) {
/*     */                 player.field_71071_by.func_184437_d(is);
/*     */               }
/*     */             } 
/*     */             SetPlayerDetailsEvent event = new SetPlayerDetailsEvent((PlayerEntity)player);
/*     */             MinecraftForge.EVENT_BUS.post((Event)event);
/*     */             WyNetwork.sendToAllTrackingAndSelf(new SSyncEntityStatsPacket(player.func_145782_y(), entityProps), (Entity)player);
/*     */             WyNetwork.sendTo(new SSyncAbilityDataPacket(player.func_145782_y(), abilityProps), (PlayerEntity)player);
/*     */           });
/*     */     }
/* 141 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\CFinishCCPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */