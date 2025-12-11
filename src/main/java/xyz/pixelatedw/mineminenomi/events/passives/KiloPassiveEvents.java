/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.kilo.KiloPress1Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class KiloPassiveEvents {
/*    */   @SubscribeEvent
/*    */   public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/* 22 */     LivingEntity entity = event.getEntityLiving();
/*    */     
/* 24 */     IAbilityData abilityProps = AbilityDataCapability.get(entity);
/* 25 */     IDevilFruit devilProps = DevilFruitCapability.get(entity);
/*    */     
/* 27 */     if (!devilProps.hasDevilFruit(ModAbilities.KILO_KILO_NO_MI)) {
/*    */       return;
/*    */     }
/*    */     
/* 31 */     Ability ability = (Ability)abilityProps.getEquippedAbility(KiloPress1Ability.INSTANCE);
/*    */     
/* 33 */     boolean isActive = (ability != null && ability.isContinuous());
/* 34 */     boolean hasUmbrella = (entity.func_184614_ca().func_77973_b() == ModWeapons.UMBRELLA.get() || entity.func_184592_cb().func_77973_b() == ModWeapons.UMBRELLA.get());
/* 35 */     boolean inAir = (!entity.func_233570_aj_() && (entity.func_213322_ci()).field_72448_b < 0.0D);
/*    */     
/* 37 */     if (isActive && hasUmbrella && inAir)
/* 38 */       AbilityHelper.setDeltaMovement((Entity)entity, (entity.func_213322_ci()).field_72450_a, (entity.func_213322_ci()).field_72448_b / 2.0D, (entity.func_213322_ci()).field_72449_c); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\passives\KiloPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */