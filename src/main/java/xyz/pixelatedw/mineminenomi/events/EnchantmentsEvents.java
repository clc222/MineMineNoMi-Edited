/*    */ package xyz.pixelatedw.mineminenomi.events;
/*    */ import net.minecraft.enchantment.Enchantment;
/*    */ import net.minecraft.enchantment.EnchantmentHelper;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraftforge.event.entity.player.AttackEntityEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEnchantments;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class EnchantmentsEvents {
/*    */   @SubscribeEvent
/*    */   public static void onLivingAttack(AttackEntityEvent event) {
/* 23 */     if (!(event.getPlayer()).field_70170_p.field_72995_K && event.getTarget() instanceof LivingEntity) {
/* 24 */       PlayerEntity player = event.getPlayer();
/* 25 */       ItemStack heldItem = player.func_184614_ca();
/*    */       
/* 27 */       if (heldItem != null && heldItem.func_77948_v()) {
/* 28 */         LivingEntity target = (LivingEntity)event.getTarget();
/*    */         
/* 30 */         int impactDialLevel = EnchantmentHelper.func_77506_a((Enchantment)ModEnchantments.DIAL_IMPACT.get(), heldItem);
/* 31 */         if (impactDialLevel > 0 && target.field_70737_aN == 0) {
/* 32 */           heldItem.func_222118_a((int)(WyHelper.randomWithRange(1, 3) * impactDialLevel), target, entity -> entity.func_213361_c(EquipmentSlotType.MAINHAND));
/*    */ 
/*    */           
/* 35 */           ExplosionAbility explosion = new ExplosionAbility((Entity)player, player.field_70170_p, target.func_226277_ct_(), target.func_226278_cu_() + 1.0D, target.func_226281_cx_(), impactDialLevel);
/* 36 */           explosion.setDamageOwner(false);
/* 37 */           explosion.setDestroyBlocks(false);
/* 38 */           explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(impactDialLevel));
/* 39 */           explosion.doExplosion();
/*    */         } 
/*    */         
/* 42 */         int flashDialLevel = EnchantmentHelper.func_77506_a((Enchantment)ModEnchantments.DIAL_FLASH.get(), heldItem);
/* 43 */         if (flashDialLevel > 0)
/* 44 */           target.func_195064_c(new EffectInstance(Effects.field_76440_q, 200 * flashDialLevel, flashDialLevel)); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\EnchantmentsEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */