/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.event.entity.player.AttackEntityEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.DiableJambeAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ public class BlackLegPassiveEvents
/*    */ {
/*    */   @EventBusSubscriber(modid = "mineminenomi")
/*    */   public static class Common
/*    */   {
/*    */     @SubscribeEvent
/*    */     public static void onHit(AttackEntityEvent event) {
/* 20 */       IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)event.getPlayer());
/*    */       
/* 22 */       if (abilityProps == null) {
/*    */         return;
/*    */       }
/*    */       
/* 26 */       Ability abl = (Ability)abilityProps.getEquippedAbility(DiableJambeAbility.INSTANCE);
/*    */       
/* 28 */       if (event.getPlayer().func_184614_ca().func_190926_b() && abl != null && abl.isContinuous())
/* 29 */         event.getTarget().func_70015_d(3); 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\passives\BlackLegPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */