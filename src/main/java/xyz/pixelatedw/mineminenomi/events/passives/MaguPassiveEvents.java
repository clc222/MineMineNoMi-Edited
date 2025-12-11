/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.tags.ITag;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.client.event.EntityViewRenderEvent;
/*    */ import net.minecraftforge.client.event.RenderBlockOverlayEvent;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class MaguPassiveEvents {
/*    */   @SubscribeEvent
/*    */   public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/* 24 */     if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*    */       return;
/*    */     }
/*    */     
/* 28 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*    */     
/* 30 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/*    */     
/* 32 */     if (!devilFruitProps.hasDevilFruit(ModAbilities.MAGU_MAGU_NO_MI)) {
/*    */       return;
/*    */     }
/*    */     
/* 36 */     if (player.func_180799_ab() && !player.field_71075_bZ.field_75100_b) {
/* 37 */       float a = (player.field_191988_bg != 0.0F || player.field_70702_br != 0.0F) ? 2.0F : 0.5F;
/* 38 */       float y = (player.func_213453_ef() && player.func_226278_cu_() - player.field_70167_r <= 0.0D) ? 2.0F : 0.0F;
/*    */       
/* 40 */       Vector3d Vector3d = player.func_213322_ci().func_216372_d(a, y, a);
/*    */       
/* 42 */       if (AbilityHelper.isJumping((LivingEntity)player)) {
/* 43 */         AbilityHelper.setDeltaMovement((Entity)player, Vector3d.func_72441_c(0.0D, 0.4D, 0.0D));
/*    */       } else {
/* 45 */         AbilityHelper.setDeltaMovement((Entity)player, Vector3d);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void onRenderBlockOverlay(RenderBlockOverlayEvent event) {
/* 53 */     Minecraft mc = Minecraft.func_71410_x();
/*    */     
/* 55 */     ClientPlayerEntity clientPlayerEntity = mc.field_71439_g;
/* 56 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)clientPlayerEntity);
/*    */     
/* 58 */     if ((devilFruitProps.hasDevilFruit(ModAbilities.MAGU_MAGU_NO_MI) || devilFruitProps.hasDevilFruit(ModAbilities.MERA_MERA_NO_MI)) && 
/* 59 */       event.getOverlayType().equals(RenderBlockOverlayEvent.OverlayType.FIRE)) {
/* 60 */       event.setCanceled(true);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void onEntityInLava(EntityViewRenderEvent.FogDensity event) {
/* 69 */     ClientPlayerEntity clientPlayerEntity = (Minecraft.func_71410_x()).field_71439_g;
/* 70 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)clientPlayerEntity);
/*    */     
/* 72 */     if (!devilFruitProps.hasDevilFruit(ModAbilities.MAGU_MAGU_NO_MI)) {
/*    */       return;
/*    */     }
/* 75 */     if (clientPlayerEntity.func_208600_a((ITag)FluidTags.field_206960_b)) {
/*    */       
/* 77 */       event.setCanceled(true);
/* 78 */       event.setDensity(0.025F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\passives\MaguPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */