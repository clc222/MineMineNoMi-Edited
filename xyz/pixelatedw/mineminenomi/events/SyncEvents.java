/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.BoatEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SUpdateHealthPacket;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.EntityEvent;
/*     */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.stats.DorikiEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.kairosekicoating.IKairosekiCoating;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.kairosekicoating.KairosekiCoatingCapability;
/*     */ import xyz.pixelatedw.mineminenomi.events.abilities.AbilityProgressionEvents;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSetServerMaxBarsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncAbilityDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncHakiDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SRecalculateEyeHeightPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SSetHakiColorPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.entities.SKairosekiCoatingPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class SyncEvents {
/*     */   @SubscribeEvent
/*     */   public static void onDorikiGained(DorikiEvent.Post event) {
/*  44 */     AttributeHelper.updateHPAttribute(event.getPlayer());
/*  45 */     AttributeHelper.updateToughnessAttribute((LivingEntity)event.getPlayer());
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityJoins(EntityJoinWorldEvent event) {
/*  50 */     if (event.getEntity() instanceof PlayerEntity) {
/*  51 */       if (event.getEntity() instanceof ServerPlayerEntity) {
/*  52 */         ServerPlayerEntity player = (ServerPlayerEntity)event.getEntity();
/*     */         
/*  54 */         AbilityProgressionEvents.forceAdvancementChecks(player);
/*     */ 
/*     */         
/*  57 */         player.func_184102_h().func_195571_aL().func_197051_a(player);
/*     */ 
/*     */         
/*  60 */         WyNetwork.sendTo(new SSetServerMaxBarsPacket(CommonConfig.INSTANCE.getAbilityBars()), (PlayerEntity)player);
/*  61 */         WyNetwork.sendTo(new SSetHakiColorPacket(player.func_145782_y(), CommonConfig.INSTANCE.getHaoshokuColoringLogic()), (PlayerEntity)player);
/*     */       } 
/*     */       
/*  64 */       AttributeHelper.updateHPAttribute((PlayerEntity)event.getEntity());
/*  65 */       AttributeHelper.updateToughnessAttribute((LivingEntity)event.getEntity());
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
/*  71 */     if (event.getEntityLiving() instanceof PlayerEntity) {
/*  72 */       PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*     */       
/*  74 */       if (player.field_70173_aa < 5 && 
/*  75 */         !player.field_70170_p.field_72995_K) {
/*  76 */         ((ServerPlayerEntity)player).field_71135_a.func_147359_a((IPacket)new SUpdateHealthPacket(player.func_110143_aJ(), player.func_71024_bL().func_75116_a(), player.func_71024_bL().func_75115_e()));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerChangeDimensions(PlayerEvent.PlayerChangedDimensionEvent event) {
/*  85 */     PlayerEntity player = event.getPlayer();
/*     */     
/*  87 */     IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
/*  88 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/*  89 */     IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)player);
/*  90 */     IEntityStats entityProps = EntityStatsCapability.get((LivingEntity)player);
/*  91 */     IHakiData hakiDataProps = HakiDataCapability.get((LivingEntity)player);
/*     */     
/*  93 */     WyNetwork.sendToAllTrackingAndSelf(new SSyncEntityStatsPacket(player.func_145782_y(), entityStatsProps), (Entity)player);
/*  94 */     WyNetwork.sendToAllTrackingAndSelf(new SSyncDevilFruitPacket(player.func_145782_y(), devilFruitProps), (Entity)player);
/*  95 */     WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(player.func_145782_y(), abilityDataProps), (Entity)player);
/*  96 */     WyNetwork.sendToAllTrackingAndSelf(new SSyncEntityStatsPacket(player.func_145782_y(), entityProps), (Entity)player);
/*  97 */     WyNetwork.sendToAllTrackingAndSelf(new SSyncHakiDataPacket(player.func_145782_y(), hakiDataProps), (Entity)player);
/*     */ 
/*     */     
/* 100 */     MinecraftForge.EVENT_BUS.post((Event)new EntityEvent.Size((Entity)player, player.func_213283_Z(), player.func_213305_a(player.func_213283_Z()), player.func_213302_cg()));
/* 101 */     WyNetwork.sendToAllTrackingAndSelf(new SRecalculateEyeHeightPacket(player.func_145782_y()), (Entity)player);
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
/*     */   @SubscribeEvent
/*     */   public static void onPlayerStartsTracking(PlayerEvent.StartTracking event) {
/* 115 */     if (event.getTarget() instanceof LivingEntity) {
/* 116 */       LivingEntity target = (LivingEntity)event.getTarget();
/*     */       
/* 118 */       IEntityStats entityStatsProps = EntityStatsCapability.get(target);
/* 119 */       IDevilFruit devilFruitProps = DevilFruitCapability.get(target);
/* 120 */       IAbilityData abilityDataProps = AbilityDataCapability.get(target);
/* 121 */       IHakiData hakiDataProps = HakiDataCapability.get(target);
/*     */       
/* 123 */       WyNetwork.sendTo(new SSyncEntityStatsPacket(target.func_145782_y(), entityStatsProps), event.getPlayer());
/* 124 */       WyNetwork.sendTo(new SSyncDevilFruitPacket(target.func_145782_y(), devilFruitProps), event.getPlayer());
/* 125 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(target.func_145782_y(), abilityDataProps), event.getPlayer());
/* 126 */       WyNetwork.sendTo(new SSyncHakiDataPacket(target.func_145782_y(), hakiDataProps), event.getPlayer());
/* 127 */       WyNetwork.sendTo(new SSetHakiColorPacket(event.getPlayer().func_145782_y(), CommonConfig.INSTANCE.getHaoshokuColoringLogic()), event.getPlayer());
/*     */     } 
/*     */     
/* 130 */     if (event.getTarget() instanceof BoatEntity) {
/* 131 */       BoatEntity boat = (BoatEntity)event.getTarget();
/* 132 */       if (!boat.field_70170_p.field_72995_K) {
/* 133 */         int coatingLevel = ((Integer)KairosekiCoatingCapability.get((Entity)boat).map(props -> Integer.valueOf(props.getCoatingLevel())).orElse(Integer.valueOf(0))).intValue();
/* 134 */         if (coatingLevel > 0)
/* 135 */           WyNetwork.sendTo(new SKairosekiCoatingPacket(boat.func_145782_y(), coatingLevel), event.getPlayer()); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\SyncEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */