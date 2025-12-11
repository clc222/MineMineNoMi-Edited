/*    */ package xyz.pixelatedw.mineminenomi.events;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import net.minecraftforge.eventbus.api.EventPriority;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCache;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.OPBossEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.WyLivingDamageEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.ProtectedArea;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ChallengesWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ProtectedAreasData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class ChallengesEvents {
/*    */   @SubscribeEvent
/*    */   public static void onEntityJoinsWorld(PlayerEvent.PlayerLoggedInEvent event) {
/* 30 */     PlayerEntity player = event.getPlayer();
/* 31 */     if (!player.field_70170_p.field_72995_K && WyHelper.isInChallengeDimension(player.field_70170_p)) {
/* 32 */       ServerWorld overworld = player.func_184102_h().func_71218_a(World.field_234918_g_);
/* 33 */       ChallengesWorldData worldData = ChallengesWorldData.get();
/* 34 */       InProgressChallenge challenge = worldData.getInProgressChallengeFor((LivingEntity)player);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 41 */       if (challenge != null) {
/* 42 */         challenge.despawnChallenger((LivingEntity)player);
/*    */       }
/*    */       else {
/*    */         
/* 46 */         Optional<ChallengeCache> cache = worldData.getChallengerCache(player.func_110124_au());
/* 47 */         if (cache.isPresent()) {
/* 48 */           ((ChallengeCache)cache.get()).restore((LivingEntity)player);
/* 49 */           worldData.removeChallengerCache(player.func_110124_au());
/*    */         } 
/*    */         
/* 52 */         ((ServerPlayerEntity)player).func_200619_a(overworld, overworld.func_241135_u_().func_177958_n(), overworld.func_241135_u_().func_177956_o(), overworld.func_241135_u_().func_177952_p(), 270.0F, 0.0F);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent(priority = EventPriority.HIGH)
/*    */   public static void onEntityDies(LivingDeathEvent event) {
/* 59 */     LivingEntity living = event.getEntityLiving();
/* 60 */     if (living instanceof PlayerEntity) {
/* 61 */       if (WyHelper.isInChallengeDimension(living.field_70170_p)) {
/* 62 */         living.func_195064_c(new EffectInstance((Effect)ModEffects.CHALLENGE_FAILED.get(), 40, 0, false, false));
/* 63 */         living.func_70606_j(1.0F);
/* 64 */         event.setCanceled(true);
/*    */       } 
/*    */       
/* 67 */       ProtectedAreasData worldData = ProtectedAreasData.get(living.field_70170_p);
/* 68 */       if (worldData != null) {
/* 69 */         ProtectedArea area = worldData.getProtectedArea(living.func_233580_cy_().func_177958_n(), living.func_233580_cy_().func_177956_o(), living.func_233580_cy_().func_177952_p());
/* 70 */         if (area != null && !area.canDie()) {
/* 71 */           living.func_195064_c(new EffectInstance((Effect)ModEffects.UNCONSCIOUS.get(), area.getUnconsciousTime(), 0, false, false));
/* 72 */           living.func_70606_j(5.0F);
/* 73 */           event.setCanceled(true);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent(priority = EventPriority.LOWEST)
/*    */   public static void onLivingDamage(WyLivingDamageEvent event) {
/* 81 */     if (!(event.getEntityLiving()).field_70170_p.field_72995_K && event.getEntityLiving() instanceof OPBossEntity) {
/* 82 */       OPBossEntity<?> entity = (OPBossEntity)event.getEntityLiving();
/*    */       
/* 84 */       if (entity.getDamageCeiling() > 0.0F && event.getAmount() > entity.getDamageCeiling()) {
/* 85 */         float diff = event.getAmount() - entity.getDamageCeiling();
/* 86 */         float extra = diff / 3.0F;
/* 87 */         float finalAmount = event.getAmount() - diff + extra;
/* 88 */         event.setAmount(finalAmount);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\ChallengesEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */