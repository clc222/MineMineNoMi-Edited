/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*     */ import net.minecraftforge.event.world.BlockEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.LogicalSide;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.BountyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.WantedPosterPackageEntity;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class BountyEvents {
/*  34 */   private static HashMap<PlayerEntity, double[]> cachedPositions = (HashMap)new HashMap<>();
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onWorldTick(TickEvent.WorldTickEvent event) {
/*  38 */     if (event.phase != TickEvent.Phase.END || event.side != LogicalSide.SERVER || !CommonConfig.INSTANCE.isWantedPosterPackagesEnabled()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  43 */     if (event.world.func_82737_E() % CommonConfig.INSTANCE.getTimeBetweenPackages() == 0L) {
/*  44 */       updateEverybodysBounty((ServerWorld)event.world);
/*  45 */       dropWantedPosters((ServerWorld)event.world);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  50 */     if (event.world.func_82737_E() % 60L * 20L * 60L == 0L) {
/*  51 */       cachedPositions.clear();
/*     */     }
/*     */   }
/*     */   
/*     */   private static void updateEverybodysBounty(ServerWorld world) {
/*  56 */     for (PlayerEntity player : world.func_217369_A()) {
/*  57 */       BountyHelper.issueBountyForPlayer(player);
/*     */     }
/*     */   }
/*     */   
/*     */   private static void dropWantedPosters(ServerWorld world) {
/*  62 */     int listSize = MathHelper.func_76125_a(world.func_217369_A().size() / 2, 1, 10);
/*  63 */     PlayerEntity[] cachedPlayers = new PlayerEntity[listSize];
/*     */     
/*  65 */     for (int i = 0; i < cachedPlayers.length; i++) {
/*  66 */       ServerPlayerEntity serverPlayerEntity = world.func_217472_l_();
/*     */       
/*  68 */       if (serverPlayerEntity != null) {
/*     */ 
/*     */ 
/*     */         
/*  72 */         boolean alreadyCached = Arrays.<PlayerEntity>stream(cachedPlayers).anyMatch(target -> (target == player));
/*  73 */         if (!alreadyCached) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  78 */           double currentPosX = serverPlayerEntity.func_226277_ct_();
/*  79 */           double currentPosZ = serverPlayerEntity.func_226281_cx_();
/*     */           
/*  81 */           boolean drop = false;
/*     */           
/*  83 */           if (!cachedPositions.containsKey(serverPlayerEntity)) {
/*  84 */             cachedPositions.put(serverPlayerEntity, new double[] { currentPosX, currentPosZ });
/*  85 */             drop = true;
/*     */           } else {
/*  87 */             double[] positions = cachedPositions.get(serverPlayerEntity);
/*  88 */             double cachedPosX = positions[0];
/*  89 */             double cachedPosZ = positions[1];
/*     */             
/*  91 */             boolean flagPosX = (Math.abs(currentPosX - cachedPosX) > 100.0D);
/*  92 */             boolean flagPosZ = (Math.abs(currentPosZ - cachedPosZ) > 100.0D);
/*     */             
/*  94 */             if (flagPosX || flagPosZ) {
/*  95 */               cachedPositions.remove(serverPlayerEntity);
/*  96 */               cachedPositions.put(serverPlayerEntity, new double[] { currentPosX, currentPosZ });
/*  97 */               drop = true;
/*     */             } 
/*     */           } 
/*     */           
/* 101 */           if (drop) {
/* 102 */             WantedPosterPackageEntity pkg = new WantedPosterPackageEntity(((PlayerEntity)serverPlayerEntity).field_70170_p);
/* 103 */             pkg.func_70012_b(serverPlayerEntity.func_226277_ct_() + WyHelper.randomWithRange(-10, 10), serverPlayerEntity.func_226278_cu_() + 30.0D, serverPlayerEntity.func_226281_cx_() + WyHelper.randomWithRange(-10, 10), 0.0F, 0.0F);
/* 104 */             ((PlayerEntity)serverPlayerEntity).field_70170_p.func_217376_c((Entity)pkg);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onBlockBreak(BlockEvent.BreakEvent event) {}
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void onBountyKilled(LivingDeathEvent event) {
/* 143 */     if (event.getSource().func_76346_g() instanceof PlayerEntity && !(event.getEntityLiving()).field_70170_p.field_72995_K) {
/* 144 */       PlayerEntity player = (PlayerEntity)event.getSource().func_76346_g();
/* 145 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*     */       
/* 147 */       if (!props.isBountyHunter()) {
/*     */         return;
/*     */       }
/*     */       
/* 151 */       LivingEntity target = event.getEntityLiving();
/* 152 */       ExtendedWorldData worldData = ExtendedWorldData.get();
/* 153 */       IEntityStats targetProps = EntityStatsCapability.get(target);
/*     */       
/* 155 */       StatChangeSource source = StatChangeSource.KILL_NPC;
/* 156 */       if (target instanceof PlayerEntity) {
/* 157 */         source = StatChangeSource.KILL_PLAYER;
/*     */       }
/*     */       
/* 160 */       WyNetwork.sendTo(new SSyncEntityStatsPacket(target.func_145782_y(), targetProps), player);
/* 161 */       for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
/* 162 */         ItemStack itemStack = player.field_71071_by.func_70301_a(i);
/* 163 */         if (itemStack.func_77942_o()) {
/* 164 */           CompoundNBT tag = itemStack.func_77978_p().func_74775_l("WPData");
/* 165 */           if (tag != null && !tag.isEmpty()) {
/*     */ 
/*     */ 
/*     */             
/* 169 */             UUID uuid = tag.func_186857_a("UUID");
/* 170 */             if (uuid != null) {
/*     */ 
/*     */ 
/*     */               
/* 174 */               boolean isTarget = uuid.equals(target.func_110124_au());
/* 175 */               long bounty = worldData.getBounty(target.func_110124_au());
/* 176 */               boolean hasBounty = (bounty > 0L);
/*     */               
/* 178 */               if (isTarget && hasBounty) {
/* 179 */                 worldData.issueBounty(uuid, 0L);
/* 180 */                 targetProps.setBounty(0L);
/*     */                 
/* 182 */                 ItemsHelper.removeItemStackFromInventory((LivingEntity)player, itemStack);
/*     */                 
/* 184 */                 if (props.alterBelly(bounty, source))
/* 185 */                   WyNetwork.sendTo(new SSyncEntityStatsPacket(player.func_145782_y(), props), player); 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\BountyEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */