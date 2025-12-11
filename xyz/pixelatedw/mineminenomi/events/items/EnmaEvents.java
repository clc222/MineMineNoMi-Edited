/*    */ package xyz.pixelatedw.mineminenomi.events.items;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class EnmaEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/* 26 */     if (!(event.getEntityLiving() instanceof PlayerEntity) || (event.getEntityLiving()).field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/* 29 */     ItemStack stack = event.getEntityLiving().func_184582_a(EquipmentSlotType.MAINHAND);
/*    */     
/* 31 */     if (stack.func_77973_b() != ModWeapons.ENMA.get() || !stack.func_77942_o()) {
/*    */       return;
/*    */     }
/* 34 */     if (stack.func_77978_p().func_74767_n("isClone")) {
/*    */       return;
/*    */     }
/* 37 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/* 38 */     IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
/* 39 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/* 40 */     float imbuingExp = hakiProps.getBusoshokuHakiExp();
/*    */     
/* 42 */     if (imbuingExp < 20.0F) {
/* 43 */       player.func_70097_a(DamageSource.field_82727_n, player.func_110138_aP());
/* 44 */     } else if (imbuingExp >= 20.0F && imbuingExp < 30.0F) {
/*    */       
/* 46 */       player.func_195064_c(new EffectInstance(Effects.field_76437_t, 200, 1, false, false));
/* 47 */       player.func_195064_c(new EffectInstance(Effects.field_76421_d, 200, 1, false, false));
/* 48 */       if (!player.func_70644_a(Effects.field_82731_v)) {
/* 49 */         player.func_195064_c(new EffectInstance(Effects.field_82731_v, 100, 1, false, false));
/*    */       }
/* 51 */     } else if (imbuingExp >= 30.0F && !HakiHelper.hasImbuingActive((LivingEntity)player)) {
/*    */       
/* 53 */       hakiProps.alterHakiOveruse(2);
/* 54 */       player.func_195064_c(new EffectInstance(Effects.field_76437_t, 100, 1, false, false));
/* 55 */       player.func_195064_c(new EffectInstance(Effects.field_76421_d, 100, 1, false, false));
/* 56 */       if (!player.func_70644_a(Effects.field_82731_v))
/* 57 */         player.func_195064_c(new EffectInstance(Effects.field_82731_v, 100, 2, false, false)); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\items\EnmaEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */