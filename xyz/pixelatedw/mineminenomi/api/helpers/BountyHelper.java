/*    */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ 
/*    */ public class BountyHelper
/*    */ {
/*    */   public static boolean canGainBounty(LivingEntity entity) {
/* 13 */     IEntityStats props = EntityStatsCapability.get(entity);
/*    */     
/* 15 */     if (props.isPirate() || props.isRevolutionary() || props.isBandit()) {
/* 16 */       return true;
/*    */     }
/*    */     
/* 19 */     return false;
/*    */   }
/*    */   
/*    */   public static boolean issueBountyForPlayer(PlayerEntity player) {
/* 23 */     if (player.field_70170_p.field_72995_K) {
/* 24 */       return false;
/*    */     }
/*    */     
/* 27 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/* 28 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*    */     
/* 30 */     if (canGainBounty((LivingEntity)player) && props.getBounty() > 1000L) {
/* 31 */       worldData.issueBounty(player.func_110124_au(), props.getBounty());
/* 32 */       return true;
/*    */     } 
/*    */     
/* 35 */     return false;
/*    */   }
/*    */   
/*    */   public static void issueBountyForAllPlayers(World world) {
/* 39 */     if (!(world instanceof net.minecraft.world.server.ServerWorld)) {
/*    */       return;
/*    */     }
/*    */     
/* 43 */     for (PlayerEntity player : world.func_217369_A())
/* 44 */       issueBountyForPlayer(player); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\helpers\BountyHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */