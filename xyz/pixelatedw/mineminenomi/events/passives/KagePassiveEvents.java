/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class KagePassiveEvents {
/*    */   @SubscribeEvent
/*    */   public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/* 17 */     if (!(event.getEntityLiving() instanceof LivingEntity)) {
/*    */       return;
/*    */     }
/*    */     
/* 21 */     LivingEntity entity = event.getEntityLiving();
/*    */     
/* 23 */     IEntityStats statsProps = EntityStatsCapability.get(entity);
/*    */     
/* 25 */     boolean isHoldingUmbrella = (entity.func_184614_ca().func_77973_b().equals(ModWeapons.UMBRELLA.get()) || entity.func_184592_cb().func_77973_b().equals(ModWeapons.UMBRELLA.get()));
/*    */     
/* 27 */     if (isHoldingUmbrella) {
/*    */       return;
/*    */     }
/*    */     
/* 31 */     if (!statsProps.hasShadow() && entity.func_70013_c() > 0.8F) {
/* 32 */       entity.func_70015_d(2);
/*    */       
/* 34 */       if (entity.field_70173_aa % 10 == 0)
/* 35 */         entity.func_70097_a((DamageSource)ModDamageSource.SUN_INCINERATION, 5.0F); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\passives\KagePassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */