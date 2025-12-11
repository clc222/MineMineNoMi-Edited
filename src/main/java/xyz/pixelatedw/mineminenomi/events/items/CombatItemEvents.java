/*    */ package xyz.pixelatedw.mineminenomi.events.items;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.client.event.FOVUpdateEvent;
/*    */ import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.ZoomAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class CombatItemEvents
/*    */ {
/*    */   private static final int UPDATE_CYCLES = 10;
/*    */   private static float previousFov;
/*    */   private static int fovCounter;
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void updateZoom(FOVUpdateEvent event) {
/* 39 */     Minecraft mc = Minecraft.func_71410_x();
/*    */     
/* 41 */     ClientPlayerEntity clientPlayerEntity = mc.field_71439_g;
/* 42 */     IAbilityData aprops = AbilityDataCapability.get((LivingEntity)clientPlayerEntity);
/*    */     
/* 44 */     boolean updateFov = false;
/*    */     
/* 46 */     Optional<ItemStack> sniperGogglesStack = ItemsHelper.findItemInSlot((LivingEntity)clientPlayerEntity, EquipmentSlotType.HEAD, (Item)ModArmors.SNIPER_GOGGLES.get());
/*    */     
/* 48 */     if (sniperGogglesStack.isPresent() && aprops.hasEquippedAbility(ZoomAbility.INSTANCE)) {
/* 49 */       Ability ability = (Ability)aprops.getEquippedAbility(ZoomAbility.INSTANCE);
/* 50 */       if (ability.isContinuous()) {
/* 51 */         event.setNewfov(0.01F);
/* 52 */         updateFov = true;
/* 53 */         previousFov = event.getNewfov();
/*    */       } 
/*    */     } 
/*    */     
/* 57 */     if (clientPlayerEntity.func_184587_cr() && clientPlayerEntity.func_184607_cu().func_77973_b() instanceof xyz.pixelatedw.mineminenomi.items.weapons.ModGunItem) {
/* 58 */       boolean isSniper = (clientPlayerEntity.func_184607_cu().func_77973_b() == ModWeapons.SENRIKU.get());
/* 59 */       float zoomDist = isSniper ? 0.75F : 0.15F;
/* 60 */       float zoomSpeed = isSniper ? 5.0F : 20.0F;
/* 61 */       float f = 1.0F;
/* 62 */       int i = clientPlayerEntity.func_184612_cw();
/* 63 */       float f1 = i / zoomSpeed;
/* 64 */       f1 *= f1;
/* 65 */       f1 = MathHelper.func_76131_a(f1, 0.0F, 1.0F);
/* 66 */       f *= 1.0F - f1 * zoomDist;
/* 67 */       event.setNewfov(f);
/* 68 */       updateFov = true;
/* 69 */       previousFov = event.getNewfov();
/*    */     } 
/*    */     
/* 72 */     if (!updateFov && previousFov != event.getNewfov() && fovCounter < 10) {
/*    */       
/* 74 */       (Minecraft.func_71410_x()).field_71438_f.func_174979_m();
/* 75 */       fovCounter++;
/*    */     } 
/*    */     
/* 78 */     if (fovCounter >= 10) {
/* 79 */       fovCounter = 0;
/* 80 */       previousFov = event.getFov();
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void fuelItem(FurnaceFuelBurnTimeEvent event) {
/* 86 */     Item fuelItem = event.getItemStack().func_77973_b();
/* 87 */     if (fuelItem.equals(((Block)ModBlocks.FLAME_DIAL.get()).func_199767_j())) {
/* 88 */       event.setBurnTime(10000);
/*    */     }
/* 90 */     else if (fuelItem.equals(((Block)ModBlocks.WANTED_POSTER_PACKAGE.get()).func_199767_j())) {
/* 91 */       event.setBurnTime(300);
/*    */     }
/* 93 */     else if (fuelItem.equals(((Block)ModBlocks.WANTED_POSTER.get()).func_199767_j()) || fuelItem.equals(((Block)ModBlocks.FLAG.get()).func_199767_j()) || fuelItem.equals(ModItems.COLOR_PALETTE
/* 94 */         .get()) || fuelItem.equals(ModItems.CHALLENGE_POSTER.get()) || fuelItem.equals(ModItems.DEVIL_FRUIT_ENCYCLOPEDIA.get())) {
/* 95 */       event.setBurnTime(200);
/*    */     }
/* 97 */     else if (fuelItem.equals(ModItems.VIVRE_CARD.get())) {
/* 98 */       event.setBurnTime(50);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\items\CombatItemEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */