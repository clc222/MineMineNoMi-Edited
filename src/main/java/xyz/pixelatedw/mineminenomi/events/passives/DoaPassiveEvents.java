/*     */ package xyz.pixelatedw.mineminenomi.events.passives;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.client.event.EntityViewRenderEvent;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.event.entity.item.ItemTossEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
/*     */ import net.minecraftforge.event.entity.player.AttackEntityEvent;
/*     */ import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*     */ import net.minecraftforge.event.world.BlockEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doa.AirDoorAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class DoaPassiveEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onItemUse(PlayerInteractEvent.RightClickItem event) {
/*  36 */     PlayerEntity player = event.getPlayer();
/*     */     
/*  38 */     if (!isInsideDoor(player)) {
/*     */       return;
/*     */     }
/*     */     
/*  42 */     event.setCanceled(true);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onItemPickup(EntityItemPickupEvent event) {
/*  47 */     PlayerEntity player = event.getPlayer();
/*     */     
/*  49 */     if (!isInsideDoor(player)) {
/*     */       return;
/*     */     }
/*     */     
/*  53 */     event.setCanceled(true);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onItemTossed(ItemTossEvent event) {
/*  58 */     PlayerEntity player = event.getPlayer();
/*     */     
/*  60 */     if (!isInsideDoor(player)) {
/*     */       return;
/*     */     }
/*     */     
/*  64 */     event.setCanceled(true);
/*     */     
/*  66 */     player.func_191521_c(event.getEntityItem().func_92059_d());
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityHits(AttackEntityEvent event) {
/*  71 */     PlayerEntity player = event.getPlayer();
/*     */     
/*  73 */     if (!isInsideDoor(player)) {
/*     */       return;
/*     */     }
/*     */     
/*  77 */     event.setCanceled(true);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityLeftClickBlocks(PlayerInteractEvent.LeftClickBlock event) {
/*  82 */     PlayerEntity player = event.getPlayer();
/*     */     
/*  84 */     if (!isInsideDoor(player)) {
/*     */       return;
/*     */     }
/*     */     
/*  88 */     event.setCanceled(true);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityRightClickBlocks(PlayerInteractEvent.RightClickBlock event) {
/*  93 */     PlayerEntity player = event.getPlayer();
/*     */     
/*  95 */     if (!isInsideDoor(player)) {
/*     */       return;
/*     */     }
/*     */     
/*  99 */     event.setCanceled(true);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityBreaksBlocks(BlockEvent.BreakEvent event) {
/* 104 */     PlayerEntity player = event.getPlayer();
/*     */     
/* 106 */     if (!isInsideDoor(player)) {
/*     */       return;
/*     */     }
/*     */     
/* 110 */     event.setCanceled(true);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityPlaceBlocks(BlockEvent.EntityPlaceEvent event) {
/* 115 */     if (!(event.getEntity() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*     */     
/* 119 */     PlayerEntity player = (PlayerEntity)event.getEntity();
/*     */     
/* 121 */     if (!isInsideDoor(player)) {
/*     */       return;
/*     */     }
/*     */     
/* 125 */     event.setCanceled(true);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityTargetedEvent(LivingSetAttackTargetEvent event) {
/* 130 */     if (!(event.getTarget() instanceof PlayerEntity) || event.getTarget() instanceof net.minecraftforge.common.util.FakePlayer || !(event.getEntity() instanceof MobEntity)) {
/*     */       return;
/*     */     }
/*     */     
/* 134 */     if (!isInsideDoor((PlayerEntity)event.getTarget())) {
/*     */       return;
/*     */     }
/*     */     
/* 138 */     MobEntity entity = (MobEntity)event.getEntity();
/*     */     
/* 140 */     entity.func_70624_b(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isInsideDoor(PlayerEntity player) {
/* 145 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 147 */     if (abilityProps == null) {
/* 148 */       return false;
/*     */     }
/*     */     
/* 151 */     Ability ability = (Ability)abilityProps.getEquippedAbility(AirDoorAbility.INSTANCE);
/*     */     
/* 153 */     boolean isActive = (ability != null && ability.isContinuous());
/*     */     
/* 155 */     if (!isActive) {
/* 156 */       return false;
/*     */     }
/*     */     
/* 159 */     return true;
/*     */   }
/*     */   
/*     */   @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*     */   public static class Client {
/*     */     @SubscribeEvent
/*     */     public static void onPlayerCameraSetup(EntityViewRenderEvent.CameraSetup event) {
/* 166 */       Minecraft mc = Minecraft.func_71410_x();
/*     */       
/* 168 */       ClientPlayerEntity clientPlayerEntity = mc.field_71439_g;
/*     */       
/* 170 */       if (clientPlayerEntity.func_70644_a((Effect)ModEffects.DOOR_HEAD.get())) {
/* 171 */         event.setYaw((((PlayerEntity)clientPlayerEntity).field_70173_aa * 10) % 360.0F);
/*     */       }
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onEntityRendered(RenderLivingEvent.Pre event) {
/* 177 */       LivingEntity entity = event.getEntity();
/*     */       
/* 179 */       if (entity.func_70644_a((Effect)ModEffects.DOOR_HEAD.get())) {
/* 180 */         entity.field_70761_aq = 0.0F;
/* 181 */         entity.field_70760_ar = 0.0F;
/*     */       } 
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onEntityRendered(RenderLivingEvent.Post event) {
/* 187 */       LivingEntity entity = event.getEntity();
/*     */       
/* 189 */       if (entity.func_70644_a((Effect)ModEffects.DOOR_HEAD.get())) {
/* 190 */         entity.field_70759_as += 10.0F;
/* 191 */         entity.field_70758_at += 10.0F;
/* 192 */         entity.field_70761_aq = 0.0F;
/* 193 */         entity.field_70760_ar = 0.0F;
/*     */       } 
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onFirstPersonViewRendered(TickEvent.RenderTickEvent event) {
/* 199 */       Minecraft mc = Minecraft.func_71410_x();
/*     */       
/* 201 */       ClientPlayerEntity clientPlayerEntity = mc.field_71439_g;
/*     */       
/* 203 */       if (clientPlayerEntity == null || !clientPlayerEntity.func_70089_S()) {
/*     */         return;
/*     */       }
/*     */       
/* 207 */       if (!DoaPassiveEvents.isInsideDoor((PlayerEntity)clientPlayerEntity)) {
/*     */         return;
/*     */       }
/*     */       
/* 211 */       RendererHelper.drawColourOnScreen(WyHelper.hexToRGB("#2AFFAE").getRGB(), 50, 0.0D, 0.0D, mc.func_228018_at_().func_198107_o(), mc.func_228018_at_().func_198087_p(), 500.0D);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\passives\DoaPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */