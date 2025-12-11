/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.loot.LootTable;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.Difficulty;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.BountyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyDebug;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StatsGainEvents
/*     */ {
/*     */   public static void setSpawnStats(MobEntity entity) {
/*  38 */     ModifiableAttributeInstance attrAtk = entity.func_233645_dx_().func_233779_a_(Attributes.field_233823_f_);
/*  39 */     ModifiableAttributeInstance attrHP = entity.func_233645_dx_().func_233779_a_(Attributes.field_233818_a_);
/*     */     
/*  41 */     if (attrAtk == null || attrHP == null) {
/*     */       return;
/*     */     }
/*     */     
/*  45 */     boolean isHardDifficulty = (entity.field_70170_p.func_175659_aa().func_151525_a() > Difficulty.NORMAL.func_151525_a());
/*     */     
/*  47 */     double attackValue = attrAtk.func_111126_e();
/*  48 */     double healthValue = attrHP.func_111126_e();
/*     */     
/*  50 */     IEntityStats statProps = EntityStatsCapability.get((LivingEntity)entity);
/*  51 */     IHakiData hakiProps = HakiDataCapability.get((LivingEntity)entity);
/*     */ 
/*     */     
/*  54 */     if (statProps != null && statProps.getDoriki() <= 0.0D) {
/*  55 */       double dorikiLimit = CommonConfig.INSTANCE.getDorikiLimit();
/*  56 */       double dummyDoriki = healthValue / 500.0D * 0.3D + attackValue / 20.0D * 0.7D * dorikiLimit * 0.8D;
/*  57 */       dummyDoriki += entity.func_70681_au().nextInt((int)Math.round(dummyDoriki / 10.0D));
/*  58 */       if (isHardDifficulty) {
/*  59 */         dummyDoriki += WyHelper.randomWithRange(250, 750);
/*     */       }
/*  61 */       dummyDoriki = MathHelper.func_151237_a(dummyDoriki, 50.0D, dorikiLimit);
/*     */       
/*  63 */       long dummyBelly = (long)(dummyDoriki / 100.0D);
/*  64 */       dummyBelly += entity.func_70681_au().nextInt(Math.round((float)dummyBelly / 2.0F));
/*  65 */       dummyBelly = WyHelper.clamp(dummyBelly, 1L, 999999999L);
/*     */       
/*  67 */       statProps.setDoriki(dummyDoriki);
/*  68 */       statProps.setBelly(dummyBelly);
/*     */       
/*  70 */       WyDebug.debug("Doriki: " + dummyDoriki + " | Belly: " + dummyBelly);
/*     */     } 
/*     */     
/*  73 */     if (hakiProps != null) {
/*  74 */       double hakiLimit = CommonConfig.INSTANCE.getHakiExpLimit();
/*     */       
/*  76 */       if (hakiProps.getKenbunshokuHakiExp() <= 0.0F) {
/*  77 */         double dummyObs = healthValue / 500.0D * 0.6D + attackValue / 20.0D * 0.4D * hakiLimit * 0.8D;
/*  78 */         int randomExtraObs = (int)Math.round(dummyObs / 10.0D);
/*  79 */         if (randomExtraObs > 0) {
/*  80 */           dummyObs += entity.func_70681_au().nextInt(randomExtraObs);
/*     */         }
/*  82 */         if (isHardDifficulty) {
/*  83 */           dummyObs += WyHelper.randomWithRange(5, 10);
/*     */         }
/*  85 */         dummyObs = MathHelper.func_151237_a(dummyObs, 2.5D, hakiLimit);
/*     */         
/*  87 */         hakiProps.setKenbunshokuHakiExp((float)dummyObs);
/*     */         
/*  89 */         WyDebug.debug("Kenbunshoku: " + dummyObs);
/*     */       } 
/*     */       
/*  92 */       if (hakiProps.getBusoshokuHakiExp() <= 0.0F) {
/*  93 */         double dummyBuso = healthValue / 500.0D * 0.4D + attackValue / 20.0D * 0.6D * hakiLimit * 0.8D;
/*  94 */         int randomExtraBuso = (int)Math.round(dummyBuso / 10.0D);
/*  95 */         if (randomExtraBuso > 0) {
/*  96 */           dummyBuso += entity.func_70681_au().nextInt(randomExtraBuso);
/*     */         }
/*  98 */         if (isHardDifficulty) {
/*  99 */           dummyBuso += WyHelper.randomWithRange(5, 10);
/*     */         }
/* 101 */         dummyBuso = MathHelper.func_151237_a(dummyBuso, 2.5D, hakiLimit);
/*     */         
/* 103 */         hakiProps.setBusoshokuHakiExp((float)dummyBuso);
/*     */         
/* 105 */         WyDebug.debug("Busoshoku: " + dummyBuso);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void removeIssuedBounty(LivingEntity target) {
/* 111 */     if (target.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 115 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/* 116 */     if (worldData.hasIssuedBounty(target)) {
/* 117 */       worldData.removeBounty(target.func_110124_au());
/*     */     }
/*     */   }
/*     */   
/*     */   public static void giveStats(LivingEntity target, DamageSource source) {
/* 122 */     if (target.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 126 */     if (!CommonConfig.INSTANCE.isMobRewardsEnabled()) {
/*     */       return;
/*     */     }
/*     */     
/* 130 */     if (WyHelper.isInChallengeDimension(target.field_70170_p)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 135 */     PlayerEntity player = null;
/* 136 */     if (source.func_76346_g() instanceof PlayerEntity) {
/* 137 */       player = (PlayerEntity)source.func_76346_g();
/*     */     }
/* 139 */     else if (source instanceof xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource && source.func_76364_f() instanceof PlayerEntity) {
/* 140 */       player = (PlayerEntity)source.func_76364_f();
/*     */     } 
/*     */     
/* 143 */     if (player == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 148 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*     */     
/* 150 */     long plusBelly = 0L;
/* 151 */     long plusBounty = 0L;
/* 152 */     double plusDoriki = 0.0D;
/* 153 */     StatChangeSource statSource = StatChangeSource.KILL_NPC;
/*     */     
/* 155 */     IEntityStats targetprops = EntityStatsCapability.get(target);
/* 156 */     if ((props.isMarine() || targetprops.isBountyHunter()) && (targetprops.isMarine() || targetprops.isBountyHunter())) {
/* 157 */       player.func_146105_b(ModI18n.INFO_LOYALTY_DROPPED, true);
/* 158 */       StatChangeSource loyaltySource = (target instanceof PlayerEntity) ? StatChangeSource.KILL_PLAYER : StatChangeSource.KILL_NPC;
/* 159 */       props.alterLoyalty(-0.5D, loyaltySource);
/*     */     } 
/*     */     
/* 162 */     if (target instanceof PlayerEntity) {
/* 163 */       double dorikiLost = targetprops.getDoriki() - WyHelper.percentage(CommonConfig.INSTANCE.getDorikiKeepPercentage(), targetprops.getDoriki());
/* 164 */       plusDoriki = dorikiLost / 4.0D;
/*     */       
/* 166 */       double bountyLost = targetprops.getBounty() - WyHelper.percentage(CommonConfig.INSTANCE.getBountyKeepPercentage(), targetprops.getBounty());
/* 167 */       plusBounty = (long)(bountyLost / 2.0D);
/*     */       
/* 169 */       double bellyLost = targetprops.getBelly() - WyHelper.percentage(CommonConfig.INSTANCE.getBellyKeepPercentage(), targetprops.getBelly());
/* 170 */       plusBelly = (long)bellyLost;
/*     */       
/* 172 */       statSource = StatChangeSource.KILL_PLAYER;
/*     */     } else {
/*     */       
/* 175 */       if (target instanceof xyz.pixelatedw.mineminenomi.entities.mobs.ability.DoppelmanEntity || target instanceof xyz.pixelatedw.mineminenomi.entities.mobs.ability.NightmareSoldierEntity || target instanceof xyz.pixelatedw.mineminenomi.entities.mobs.ability.MirageCloneEntity || target instanceof xyz.pixelatedw.mineminenomi.entities.mobs.ability.BlackKnightEntity || target instanceof xyz.pixelatedw.mineminenomi.entities.mobs.ability.WaxCloneEntity) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 180 */       if (props.isMarine() && targetprops.isMarine()) {
/*     */         return;
/*     */       }
/*     */       
/* 184 */       if (props.isRevolutionary() && targetprops.isRevolutionary()) {
/*     */         return;
/*     */       }
/*     */       
/* 188 */       LootTable loottable = target.field_70170_p.func_73046_m().func_200249_aQ().func_186521_a(target.func_213346_cF());
/* 189 */       if (loottable.getPool("mineminenomi:stats") != null) {
/*     */         return;
/*     */       }
/*     */       
/* 193 */       double targetDoriki = targetprops.getDoriki();
/*     */       
/* 195 */       if (targetDoriki > 0.0D) {
/* 196 */         plusDoriki = targetDoriki / 100.0D;
/*     */         
/* 198 */         if (props.getDoriki() > targetDoriki) {
/* 199 */           plusDoriki = targetDoriki / 10000.0D;
/*     */         }
/*     */         
/* 202 */         if (plusDoriki < 1.0D && CommonConfig.INSTANCE.isMinimumDorikiPerKillEnabled()) {
/* 203 */           plusDoriki = 1.0D;
/*     */         }
/*     */         
/* 206 */         plusBounty = (long)targetDoriki / 20L;
/* 207 */         plusBelly = targetprops.getBelly();
/*     */       } 
/*     */       
/* 210 */       if (target instanceof net.minecraft.entity.merchant.villager.VillagerEntity) {
/* 211 */         plusBounty = 250L;
/*     */       }
/* 213 */       else if (target instanceof xyz.pixelatedw.mineminenomi.entities.mobs.NotoriousEntity && 
/* 214 */         plusDoriki < 100.0D) {
/* 215 */         plusDoriki = 100.0D;
/*     */       } 
/*     */ 
/*     */       
/* 219 */       plusDoriki *= CommonConfig.INSTANCE.getDorikiRewardMultiplier();
/* 220 */       plusBounty = (long)(plusBounty * CommonConfig.INSTANCE.getBountyRewardMultiplier());
/* 221 */       plusBelly = (long)(plusBelly * CommonConfig.INSTANCE.getBellyRewardMultiplier());
/* 222 */       statSource = StatChangeSource.KILL_NPC;
/*     */     } 
/*     */     
/* 225 */     WyDebug.debug("\nDoriki: " + plusDoriki + "\nBelly: " + plusBelly + "\nBounty: " + plusBounty);
/*     */ 
/*     */     
/* 228 */     if (plusDoriki > 0.0D) {
/* 229 */       props.alterDoriki(plusDoriki, statSource);
/*     */     }
/*     */ 
/*     */     
/* 233 */     if (BountyHelper.canGainBounty((LivingEntity)player) && plusBounty > 0L) {
/* 234 */       props.alterBounty(plusBounty, statSource);
/*     */     }
/*     */ 
/*     */     
/* 238 */     if (plusBelly > 0L) {
/* 239 */       props.alterBelly(plusBelly, statSource);
/*     */     }
/*     */     
/* 242 */     WyNetwork.sendTo(new SSyncEntityStatsPacket(player.func_145782_y(), props), player);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\StatsGainEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */