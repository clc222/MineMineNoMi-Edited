/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.client.event.RenderLivingEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.zushi.AbareHimatsuriAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.AbareHimatsuriModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbareHimatsuriRenderer;
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*    */ public class ZushiPassiveEvents
/*    */ {
/* 24 */   private static final AbareHimatsuriRenderer.Factory ABARE_HIMATSURI = new AbareHimatsuriRenderer.Factory(new AbareHimatsuriModel());
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onEntityRendered(RenderLivingEvent.Pre event) {
/* 29 */     if (!(event.getEntity() instanceof net.minecraft.entity.player.PlayerEntity)) {
/*    */       return;
/*    */     }
/* 32 */     LivingEntity entity = event.getEntity();
/* 33 */     IDevilFruit props = DevilFruitCapability.get(entity);
/*    */     
/* 35 */     if (!props.hasDevilFruit(ModAbilities.ZUSHI_ZUSHI_NO_MI)) {
/*    */       return;
/*    */     }
/* 38 */     IAbilityData abilityProps = AbilityDataCapability.get(entity);
/*    */     
/* 40 */     AbareHimatsuriAbility abareAbility = (AbareHimatsuriAbility)abilityProps.getEquippedAbility(AbareHimatsuriAbility.INSTANCE);
/* 41 */     boolean isAbareActive = (abareAbility != null && abareAbility.isContinuous());
/*    */     
/* 43 */     if (!isAbareActive) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 48 */     if (!event.getEntity().func_233570_aj_())
/* 49 */       ABARE_HIMATSURI.createRenderFor(Minecraft.func_71410_x().func_175598_ae()).func_225623_a_((Entity)entity, entity.field_70177_z, event.getPartialRenderTick(), event.getMatrixStack(), event.getBuffers(), event.getLight()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\passives\ZushiPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */