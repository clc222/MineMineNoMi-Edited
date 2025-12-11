/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.client.event.RenderBlockOverlayEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sui.FreeSwimmingAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*    */ public class SuiPassiveEvents {
/*    */   @SubscribeEvent
/*    */   public static void onRenderBlockOverlay(RenderBlockOverlayEvent event) {
/* 17 */     if (event.getPlayer() == null) {
/*    */       return;
/*    */     }
/*    */     
/* 21 */     PlayerEntity player = event.getPlayer();
/*    */     
/* 23 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/* 24 */     FreeSwimmingAbility ability = (FreeSwimmingAbility)props.getEquippedAbility(FreeSwimmingAbility.INSTANCE);
/*    */     
/* 26 */     if (ability != null && ability.isContinuous())
/* 27 */       event.setCanceled(true); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\passives\SuiPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */