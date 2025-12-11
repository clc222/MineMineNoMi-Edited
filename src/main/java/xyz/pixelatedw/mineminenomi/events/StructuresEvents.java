/*    */ package xyz.pixelatedw.mineminenomi.events;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.stream.Collectors;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.world.Difficulty;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*    */ import net.minecraftforge.event.entity.player.PlayerContainerEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class StructuresEvents {
/*    */   @SubscribeEvent
/*    */   public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
/* 29 */     if (event.getEntity() instanceof PlayerEntity)
/*    */     {
/* 31 */       StructuresHelper.SPAWNED_STRUCTURES.clear();
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onChestOpened(PlayerContainerEvent.Open event) {
/* 37 */     PlayerEntity player = event.getPlayer();
/* 38 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*    */     
/* 40 */     StructuresHelper.StructureFaction faction = null;
/* 41 */     if (props.isMarine() || props.isBountyHunter()) {
/* 42 */       faction = StructuresHelper.StructureFaction.from(ModValues.MARINE);
/* 43 */     } else if (props.isRevolutionary()) {
/* 44 */       faction = StructuresHelper.StructureFaction.from(ModValues.REVOLUTIONARY);
/*    */     } 
/*    */     
/* 47 */     if (faction == null) {
/*    */       return;
/*    */     }
/*    */     
/* 51 */     boolean isInisdeStruct = StructuresHelper.isInsideModStructure((ServerWorld)player.field_70170_p, player.func_233580_cy_(), faction);
/* 52 */     if (player.field_70170_p.field_72995_K || player.func_184812_l_() || player.func_175149_v() || !isInisdeStruct) {
/*    */       return;
/*    */     }
/*    */     
/* 56 */     if (player.func_70644_a((Effect)ModEffects.SILENT.get())) {
/*    */       return;
/*    */     }
/*    */     
/* 60 */     double range = (player.field_70170_p.func_175659_aa() == Difficulty.HARD) ? 64.0D : 32.0D;
/*    */ 
/*    */ 
/*    */     
/* 64 */     List<OPEntity> list = (List<OPEntity>)player.field_70170_p.func_217357_a(OPEntity.class, player.func_174813_aQ().func_186662_g(range)).stream().filter(e -> e.getEntityStats().isMarine()).collect(Collectors.toList());
/*    */     
/* 66 */     if (list.size() > 0) {
/* 67 */       if ((props.isMarine() && props.getMarineRank().ordinal() < FactionHelper.MarineRank.CAPTAIN.ordinal()) || props.isBountyHunter()) {
/* 68 */         props.alterLoyalty(-0.25D, StatChangeSource.NATURAL);
/* 69 */         WyNetwork.sendTo(new SSyncEntityStatsPacket(player.func_145782_y(), props), player);
/*    */       } 
/*    */       
/* 72 */       list.stream().forEach(entity -> entity.func_70624_b((LivingEntity)player));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\StructuresEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */