/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.client.event.RenderLivingEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.awa.SoapDefenseAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.SoapDefenseModel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AwaPassiveEvents
/*    */ {
/*    */   @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*    */   public static class Client
/*    */   {
/* 24 */     private static final SoapDefenseModel SOAP = new SoapDefenseModel();
/*    */     
/*    */     @SubscribeEvent
/*    */     public static void onEntityRendered(RenderLivingEvent.Pre event) {
/* 28 */       LivingEntity entity = event.getEntity();
/* 29 */       IAbilityData abilityProps = AbilityDataCapability.get(entity);
/*    */       
/* 31 */       if (abilityProps == null) {
/*    */         return;
/*    */       }
/*    */       
/* 35 */       SoapDefenseAbility ability = (SoapDefenseAbility)abilityProps.getEquippedAbility(SoapDefenseAbility.INSTANCE);
/*    */       
/* 37 */       if (ability == null || !ability.isContinuous()) {
/*    */         return;
/*    */       }
/*    */       
/* 41 */       event.setCanceled(true);
/*    */       
/* 43 */       event.getMatrixStack().func_227860_a_();
/*    */       
/* 45 */       event.getMatrixStack().func_227861_a_(0.0D, entity.func_70047_e() - 1.2D, 0.0D);
/*    */       
/* 47 */       event.getMatrixStack().func_227862_a_(1.2F, 1.2F, 1.2F);
/*    */       
/* 49 */       SOAP.func_225598_a_(event.getMatrixStack(), event.getBuffers().getBuffer(ModRenderTypes.getAbilityBody(ModResources.AWA_SOAP)), event.getLight(), 0, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */       
/* 51 */       RenderSystem.enableLighting();
/* 52 */       event.getMatrixStack().func_227865_b_();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\passives\AwaPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */