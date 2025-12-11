/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.gasu.BlueSwordAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.WyLivingHurtEvent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class GasuPassiveEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onEntityHits(WyLivingHurtEvent event) {
/* 20 */     Entity entity = event.getSource().func_76364_f();
/*    */     
/* 22 */     if (!(entity instanceof LivingEntity)) {
/*    */       return;
/*    */     }
/* 25 */     LivingEntity attacker = (LivingEntity)entity;
/*    */     
/* 27 */     ItemStack stack = attacker.func_184614_ca();
/* 28 */     if (stack == null || stack.func_77973_b() != ModWeapons.BLUE_SWORD.get()) {
/*    */       return;
/*    */     }
/* 31 */     IAbilityData abilityProps = AbilityDataCapability.get(attacker);
/*    */     
/* 33 */     if (abilityProps == null) {
/*    */       return;
/*    */     }
/*    */     
/* 37 */     BlueSwordAbility abl = (BlueSwordAbility)abilityProps.getEquippedOrPassiveAbility(BlueSwordAbility.INSTANCE);
/*    */     
/* 39 */     if (abl != null) {
/*    */       return;
/*    */     }
/*    */     
/* 43 */     event.setCanceled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\passives\GasuPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */