/*    */ package xyz.pixelatedw.mineminenomi.events.devilfruits;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.world.GameRules;
/*    */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class DFUserDeathEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onClonePlayer(LivingDeathEvent event) {
/* 18 */     if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*    */       return;
/*    */     }
/*    */     
/* 22 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*    */ 
/*    */     
/* 25 */     if (!player.field_70170_p.func_82736_K().func_223586_b(GameRules.field_223600_c))
/* 26 */       player.func_233645_dx_().func_233778_a_().forEach(WyHelper::removeAllModifiers); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\devilfruits\DFUserDeathEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */