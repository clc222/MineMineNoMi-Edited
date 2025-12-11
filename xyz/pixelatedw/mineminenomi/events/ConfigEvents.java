/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ import com.google.common.base.Predicates;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.FlyAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.ProtectedArea;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.config.GeneralConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ProtectedAreasData;
/*     */ import xyz.pixelatedw.mineminenomi.events.abilities.AbilityValidationEvents;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncAbilityDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class ConfigEvents {
/*     */   @SubscribeEvent
/*     */   public static void onClonePlayer(PlayerEvent.Clone event) {
/*  48 */     if (event.isWasDeath()) {
/*     */       
/*  50 */       boolean isInProtectionArea = false;
/*  51 */       ProtectedAreasData worldData = ProtectedAreasData.get((event.getOriginal()).field_70170_p);
/*  52 */       if (worldData != null) {
/*  53 */         BlockPos pos = event.getOriginal().func_233580_cy_();
/*  54 */         ProtectedArea area = worldData.getProtectedArea(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*  55 */         if (area != null && !area.canLoseStats()) {
/*  56 */           isInProtectionArea = true;
/*     */         }
/*     */       } 
/*     */       
/*  60 */       if (WyHelper.isInChallengeDimension((event.getOriginal()).field_70170_p) || isInProtectionArea) {
/*  61 */         ServerPlayerEntity oldPlayer = (ServerPlayerEntity)event.getOriginal();
/*  62 */         ServerPlayerEntity newPlayer = (ServerPlayerEntity)event.getPlayer();
/*  63 */         newPlayer.field_71071_by.func_70455_b(oldPlayer.field_71071_by);
/*  64 */         newPlayer.func_70606_j(oldPlayer.func_110143_aJ());
/*  65 */         newPlayer.field_71068_ca = oldPlayer.field_71068_ca;
/*  66 */         newPlayer.field_71067_cb = oldPlayer.field_71067_cb;
/*  67 */         newPlayer.field_71106_cc = oldPlayer.field_71106_cc;
/*  68 */         newPlayer.func_85040_s(oldPlayer.func_71037_bA());
/*  69 */         restoreFullData((PlayerEntity)oldPlayer, (PlayerEntity)newPlayer);
/*     */         
/*     */         return;
/*     */       } 
/*  73 */       IDevilFruit oldDevilFruitProps = DevilFruitCapability.get((LivingEntity)event.getOriginal());
/*  74 */       IDevilFruit newDevilFruitProps = DevilFruitCapability.get((LivingEntity)event.getPlayer());
/*  75 */       IAbilityData newAbilityData = AbilityDataCapability.get((LivingEntity)event.getPlayer());
/*     */       
/*  77 */       if (oldDevilFruitProps.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) && !((MorphInfo)ModMorphs.YOMI_SKELETON.get()).isActive((LivingEntity)event.getOriginal())) {
/*     */         
/*  79 */         restoreFullData(event.getOriginal(), event.getPlayer());
/*  80 */         DevilFruitCapability.get((LivingEntity)event.getPlayer()).setDevilFruit(ModAbilities.YOMI_YOMI_NO_MI);
/*  81 */         DevilFruitCapability.get((LivingEntity)event.getPlayer()).setZoanPoint(((MorphInfo)ModMorphs.YOMI_SKELETON.get()).getForm());
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/*  87 */       IEntityStats oldEntityStats = EntityStatsCapability.get((LivingEntity)event.getOriginal());
/*  88 */       INBT oldEntityStatsNBT = EntityStatsCapability.INSTANCE.writeNBT(oldEntityStats, null);
/*  89 */       IEntityStats newEntityStats = EntityStatsCapability.get((LivingEntity)event.getPlayer());
/*  90 */       EntityStatsCapability.INSTANCE.readNBT(newEntityStats, null, oldEntityStatsNBT);
/*     */       
/*  92 */       if (((Boolean)GeneralConfig.RACE_KEEP.get()).booleanValue()) {
/*  93 */         newEntityStats.setRace(oldEntityStats.getRace());
/*  94 */         newEntityStats.setSubRace(oldEntityStats.getSubRace());
/*     */       } 
/*     */       
/*  97 */       if (((Boolean)GeneralConfig.FACTION_KEEP.get()).booleanValue()) {
/*  98 */         newEntityStats.setFaction(oldEntityStats.getFaction());
/*     */       }
/*     */       
/* 101 */       if (((Boolean)GeneralConfig.FIGHTING_STYLE_KEEP.get()).booleanValue()) {
/* 102 */         newEntityStats.setFightingStyle(oldEntityStats.getFightingStyle());
/*     */       }
/*     */       
/* 105 */       if (((Boolean)GeneralConfig.DEVIL_FRUIT_KEEP.get()).booleanValue()) {
/* 106 */         INBT oldFruitNBT = DevilFruitCapability.INSTANCE.writeNBT(oldDevilFruitProps, null);
/* 107 */         DevilFruitCapability.INSTANCE.readNBT(newDevilFruitProps, null, oldFruitNBT);
/*     */       } 
/*     */ 
/*     */       
/* 111 */       double dorikiLeft = WyHelper.percentage(CommonConfig.INSTANCE.getDorikiKeepPercentage(), oldEntityStats.getDoriki());
/* 112 */       newEntityStats.alterDoriki(dorikiLeft - oldEntityStats.getDoriki(), StatChangeSource.DEATH);
/*     */ 
/*     */       
/* 115 */       long bountyLeft = (long)WyHelper.percentage(CommonConfig.INSTANCE.getBountyKeepPercentage(), oldEntityStats.getBounty());
/* 116 */       newEntityStats.alterBounty(bountyLeft - oldEntityStats.getBounty(), StatChangeSource.DEATH);
/*     */ 
/*     */       
/* 119 */       long bellyLeft = (long)WyHelper.percentage(CommonConfig.INSTANCE.getBellyKeepPercentage(), oldEntityStats.getBelly());
/* 120 */       newEntityStats.alterBelly(bellyLeft - oldEntityStats.getBelly(), StatChangeSource.DEATH);
/* 121 */       long extolLeft = (long)WyHelper.percentage(CommonConfig.INSTANCE.getBellyKeepPercentage(), oldEntityStats.getExtol());
/* 122 */       newEntityStats.alterExtol(extolLeft - oldEntityStats.getExtol(), StatChangeSource.DEATH);
/*     */ 
/*     */       
/* 125 */       IHakiData oldHakiProps = HakiDataCapability.get((LivingEntity)event.getOriginal());
/* 126 */       INBT oldHakiPropsNBT = HakiDataCapability.INSTANCE.writeNBT(oldHakiProps, null);
/* 127 */       IHakiData newHakiProps = HakiDataCapability.get((LivingEntity)event.getPlayer());
/* 128 */       HakiDataCapability.INSTANCE.readNBT(newHakiProps, null, oldHakiPropsNBT);
/*     */       
/* 130 */       float hardeningBusoExpLeft = (float)WyHelper.percentage(CommonConfig.INSTANCE.getHakiExpKeepPercentage(), oldHakiProps.getBusoshokuHakiExp());
/* 131 */       newHakiProps.alterBusoshokuHakiExp(hardeningBusoExpLeft - oldHakiProps.getBusoshokuHakiExp(), StatChangeSource.DEATH);
/* 132 */       float observationExpLeft = (float)WyHelper.percentage(CommonConfig.INSTANCE.getHakiExpKeepPercentage(), oldHakiProps.getKenbunshokuHakiExp());
/* 133 */       newHakiProps.alterKenbunshokuHakiExp(observationExpLeft - oldHakiProps.getKenbunshokuHakiExp(), StatChangeSource.DEATH);
/*     */ 
/*     */       
/* 136 */       int loyaltyLeft = (int)WyHelper.percentage(((Integer)GeneralConfig.LOYALTY_KEEP_PERCENTAGE.get()).intValue(), oldEntityStats.getLoyalty());
/* 137 */       newEntityStats.alterLoyalty(loyaltyLeft - oldEntityStats.getLoyalty(), StatChangeSource.DEATH);
/*     */       
/* 139 */       restorePermaData(event.getOriginal(), event.getPlayer());
/*     */ 
/*     */       
/* 142 */       AbilityHelper.enableAbilities((LivingEntity)event.getPlayer(), (Predicate)Predicates.alwaysTrue());
/*     */       
/* 144 */       AbilityValidationEvents.validateUnlockedAbilities((LivingEntity)event.getPlayer());
/*     */       
/* 146 */       WyNetwork.sendTo(new SSyncDevilFruitPacket(event.getPlayer().func_145782_y(), newDevilFruitProps), event.getPlayer());
/* 147 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(event.getPlayer().func_145782_y(), newAbilityData), event.getPlayer());
/*     */       
/* 149 */       boolean hasUnlockedFlying = (AbilityDataCapability.get((LivingEntity)event.getPlayer()).getPassiveAbilities(abl -> (abl instanceof FlyAbility && !((FlyAbility)abl).isPaused())).size() > 0);
/* 150 */       if (hasUnlockedFlying && !event.getPlayer().func_184812_l_() && !event.getPlayer().func_175149_v())
/*     */       {
/* 152 */         (event.getPlayer()).field_71075_bZ.field_75101_c = false;
/* 153 */         (event.getPlayer()).field_71075_bZ.field_75100_b = false;
/* 154 */         ((ServerPlayerEntity)event.getPlayer()).field_71135_a.func_147359_a((IPacket)new SPlayerAbilitiesPacket((event.getPlayer()).field_71075_bZ));
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 159 */       restoreFullData(event.getOriginal(), event.getPlayer());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void restoreFullData(PlayerEntity original, PlayerEntity player) {
/* 165 */     IDevilFruit oldDevilFruitProps = DevilFruitCapability.get((LivingEntity)original);
/* 166 */     IDevilFruit newDevilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/* 167 */     IAbilityData newAbilityData = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 169 */     CompoundNBT compoundNBT = new CompoundNBT();
/*     */ 
/*     */     
/* 172 */     IEntityStats oldEntityStats = EntityStatsCapability.get((LivingEntity)original);
/* 173 */     INBT iNBT = EntityStatsCapability.INSTANCE.writeNBT(oldEntityStats, null);
/* 174 */     IEntityStats newEntityStats = EntityStatsCapability.get((LivingEntity)player);
/* 175 */     EntityStatsCapability.INSTANCE.readNBT(newEntityStats, null, iNBT);
/* 176 */     newEntityStats.setCola(Math.max(Math.min(oldEntityStats.getCola(), newEntityStats.getMaxCola()), 0));
/*     */ 
/*     */     
/* 179 */     iNBT = DevilFruitCapability.INSTANCE.writeNBT(oldDevilFruitProps, null);
/* 180 */     DevilFruitCapability.INSTANCE.readNBT(newDevilFruitProps, null, iNBT);
/* 181 */     newDevilFruitProps.setZoanPoint("");
/*     */ 
/*     */     
/* 184 */     IAbilityData oldAbilityData = AbilityDataCapability.get((LivingEntity)original);
/* 185 */     iNBT = AbilityDataCapability.INSTANCE.writeNBT(oldAbilityData, null);
/* 186 */     AbilityDataCapability.INSTANCE.readNBT(newAbilityData, null, iNBT);
/*     */ 
/*     */     
/* 189 */     IHakiData oldHakiData = HakiDataCapability.get((LivingEntity)original);
/* 190 */     iNBT = HakiDataCapability.INSTANCE.writeNBT(oldHakiData, null);
/* 191 */     IHakiData newHakiData = HakiDataCapability.get((LivingEntity)player);
/* 192 */     HakiDataCapability.INSTANCE.readNBT(newHakiData, null, iNBT);
/*     */     
/* 194 */     restorePermaData(original, player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void restorePermaData(PlayerEntity original, PlayerEntity player) {
/* 200 */     CompoundNBT compoundNBT = new CompoundNBT();
/*     */ 
/*     */     
/* 203 */     IQuestData oldQuestData = QuestDataCapability.get(original);
/* 204 */     INBT iNBT = QuestDataCapability.INSTANCE.writeNBT(oldQuestData, null);
/* 205 */     IQuestData newQuestData = QuestDataCapability.get(player);
/* 206 */     QuestDataCapability.INSTANCE.readNBT(newQuestData, null, iNBT);
/*     */ 
/*     */     
/* 209 */     IChallengesData oldChallengeData = ChallengesDataCapability.get(original);
/* 210 */     iNBT = ChallengesDataCapability.INSTANCE.writeNBT(oldChallengeData, null);
/* 211 */     IChallengesData newChallengeData = ChallengesDataCapability.get(player);
/* 212 */     ChallengesDataCapability.INSTANCE.readNBT(newChallengeData, null, iNBT);
/*     */ 
/*     */     
/* 215 */     IAbilityData oldAbilityData = AbilityDataCapability.get((LivingEntity)original);
/*     */     
/* 217 */     iNBT = AbilityDataCapability.INSTANCE.writeNBT(oldAbilityData, null);
/* 218 */     IAbilityData newAbilityData = AbilityDataCapability.get((LivingEntity)player);
/* 219 */     AbilityDataCapability.INSTANCE.readNBT(newAbilityData, null, iNBT);
/*     */     
/* 221 */     AbilityValidationEvents.validateUnlockedAbilities((LivingEntity)player);
/* 222 */     AttributeHelper.updateHPAttribute(player);
/*     */     
/* 224 */     player.func_70606_j(player.func_110138_aP());
/*     */     
/* 226 */     IEntityStats statsData = EntityStatsCapability.get((LivingEntity)player);
/* 227 */     statsData.setShadow(true);
/* 228 */     statsData.setStrawDoll(true);
/* 229 */     statsData.setHeart(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\ConfigEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */