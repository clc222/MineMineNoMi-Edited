/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.SetPlayerDetailsEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.stats.LoyaltyEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncAbilityDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class LoyaltyEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onPlayerUpdate(LivingEvent.LivingUpdateEvent event) {
/*  35 */     if (!(event.getEntityLiving() instanceof PlayerEntity) || (event.getEntityLiving()).field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  39 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*  40 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*     */     
/*  42 */     boolean hasLoyalty = (props.isMarine() || props.isRevolutionary() || props.isBountyHunter());
/*     */     
/*  44 */     if (!hasLoyalty || props.getLoyalty() >= 100.0D) {
/*     */       return;
/*     */     }
/*     */     
/*  48 */     if (props.getDoriki() < 4000.0D && props.getLoyalty() > 15.0D) {
/*     */       return;
/*     */     }
/*     */     
/*  52 */     if (player.field_70170_p.func_82737_E() % 24000L == 0L) {
/*     */       
/*  54 */       int loyaltyGain = MathHelper.func_76128_c(1.0D * CommonConfig.INSTANCE.getLoyaltyMultiplier());
/*  55 */       props.alterLoyalty(loyaltyGain, StatChangeSource.NATURAL);
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void checkForAbilityGain(LoyaltyEvent event) {
/*  61 */     PlayerEntity player = event.getPlayer();
/*  62 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*  63 */     boolean updates = false;
/*  64 */     if (props.isMarine()) {
/*  65 */       for (AbilityCore core : ModAbilities.MARINE_ABILITIES) {
/*  66 */         updates |= AbilityHelper.checkAndUnlockAbility((LivingEntity)player, core);
/*     */       
/*     */       }
/*     */     
/*     */     }
/*  71 */     else if (props.isRevolutionary()) {
/*     */     
/*     */     } 
/*     */     
/*  75 */     if (updates) {
/*  76 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(player.func_145782_y(), AbilityDataCapability.get((LivingEntity)player)), player);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onStatsChoose(SetPlayerDetailsEvent event) {
/*  83 */     event.getEntityStats().setLoyalty(0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void gainRevolutionaryAbility(PlayerEntity player, FactionHelper.RevolutionaryRank rank, AbilityCore ability) {
/*  88 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*  89 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  91 */     if (props.hasRevolutionaryRank(rank) && !abilityProps.hasUnlockedAbility(ability) && !AbilityHelper.verifyIfAbilityIsBanned(ability)) {
/*     */       
/*  93 */       abilityProps.addUnlockedAbility(ability, AbilityUnlock.PROGRESSION);
/*  94 */       player.func_145747_a((ITextComponent)new StringTextComponent("Obtained " + ability.getUnlocalizedName()), Util.field_240973_b_);
/*     */     } 
/*  96 */     if ((!props.hasRevolutionaryRank(rank) || AbilityHelper.verifyIfAbilityIsBanned(ability)) && abilityProps.hasUnlockedAbility(ability)) {
/*  97 */       abilityProps.removeUnlockedAbility(ability);
/*     */     }
/*  99 */     WyNetwork.sendTo(new SSyncAbilityDataPacket(player.func_145782_y(), abilityProps), player);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void gainMarineAbility(PlayerEntity player, FactionHelper.MarineRank rank, AbilityCore ability) {
/* 104 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 105 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 107 */     if (props.hasMarineRank(rank) && !abilityProps.hasUnlockedAbility(ability) && !AbilityHelper.verifyIfAbilityIsBanned(ability)) {
/*     */       
/* 109 */       abilityProps.addUnlockedAbility(ability, AbilityUnlock.PROGRESSION);
/* 110 */       player.func_145747_a((ITextComponent)new StringTextComponent("Obtained " + ability.getUnlocalizedName()), Util.field_240973_b_);
/*     */     } 
/* 112 */     if ((!props.hasMarineRank(rank) || AbilityHelper.verifyIfAbilityIsBanned(ability)) && abilityProps.hasUnlockedAbility(ability)) {
/* 113 */       abilityProps.removeUnlockedAbility(ability);
/*     */     }
/* 115 */     WyNetwork.sendTo(new SSyncAbilityDataPacket(player.func_145782_y(), abilityProps), player);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\LoyaltyEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */