/*    */ package xyz.pixelatedw.mineminenomi.events.items;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.BoatEntity;
/*    */ import net.minecraft.entity.item.ItemEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.kairosekicoating.IKairosekiCoating;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.kairosekicoating.KairosekiCoatingCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class KairosekiCoatingEvents
/*    */ {
/*    */   public static final String COATING_LEVEL_TAG = "coatingLevel";
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void spawnBoat(PlayerInteractEvent.EntityInteract event) {
/* 23 */     if (!(event.getWorld()).field_72995_K && event.getTarget() != null && event.getTarget().func_70089_S() && event.getHand().equals(Hand.MAIN_HAND)) {
/* 24 */       IKairosekiCoating coatingData = (IKairosekiCoating)KairosekiCoatingCapability.get(event.getTarget()).orElse(null);
/* 25 */       ItemStack stack = event.getItemStack();
/* 26 */       if (coatingData != null && !coatingData.isFullyCoated() && stack != null && !stack.func_190926_b() && stack.func_77973_b().equals(ModItems.DENSE_KAIROSEKI.get())) {
/* 27 */         int amount = Math.min(stack.func_190916_E(), 5);
/* 28 */         if (coatingData.addCoatingLevel(amount) && !(event.getPlayer()).field_71075_bZ.field_75098_d) {
/* 29 */           stack.func_190918_g(amount);
/* 30 */           event.setCanceled(true);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static ItemEntity dropCoatedBoat(BoatEntity boat, ItemStack stack) {
/* 37 */     IKairosekiCoating coatingData = (IKairosekiCoating)KairosekiCoatingCapability.get((Entity)boat).orElse(null);
/* 38 */     if (coatingData != null && coatingData.getCoatingLevel() > 0) {
/* 39 */       stack.func_196082_o().func_74768_a("coatingLevel", coatingData.getCoatingLevel());
/*    */     }
/* 41 */     return boat.func_70099_a(stack, 0.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\items\KairosekiCoatingEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */