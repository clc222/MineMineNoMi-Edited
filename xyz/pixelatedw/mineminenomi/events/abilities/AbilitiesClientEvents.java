/*    */ package xyz.pixelatedw.mineminenomi.events.abilities;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*    */ import net.minecraftforge.eventbus.api.EventPriority;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.OutOfBodyAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*    */ public class AbilitiesClientEvents {
/*    */   @SubscribeEvent(priority = EventPriority.LOWEST)
/*    */   public static void onRenderOverlay(RenderGameOverlayEvent event) {
/* 23 */     Minecraft mc = Minecraft.func_71410_x();
/* 24 */     ClientPlayerEntity clientPlayerEntity = mc.field_71439_g;
/* 25 */     IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)clientPlayerEntity);
/*    */     
/* 27 */     if (abilityDataProps == null) {
/*    */       return;
/*    */     }
/* 30 */     int posX = mc.func_228018_at_().func_198107_o();
/* 31 */     int posY = mc.func_228018_at_().func_198087_p();
/*    */     
/* 33 */     if (event.getType() == RenderGameOverlayEvent.ElementType.VIGNETTE) {
/*    */       
/* 35 */       Optional<OutOfBodyAbility> ability = abilityDataProps.getEquippedAbilities(abl -> (abl instanceof OutOfBodyAbility && ((OutOfBodyAbility)abl).isContinuous())).stream().map(abl -> (OutOfBodyAbility)abl).findFirst();
/* 36 */       if (ability.isPresent()) {
/* 37 */         float distance = (float)(((OutOfBodyAbility)ability.get()).getDistanceFromPivot((Entity)clientPlayerEntity) / ((OutOfBodyAbility)ability.get()).getMaxRange());
/* 38 */         RendererHelper.renderVignette((Entity)clientPlayerEntity, distance, posX, posY);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\abilities\AbilitiesClientEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */