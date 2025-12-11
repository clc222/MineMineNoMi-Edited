/*    */ package xyz.pixelatedw.mineminenomi.events.items;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraftforge.event.entity.living.LivingAttackEvent;
/*    */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*    */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*    */ import net.minecraftforge.eventbus.api.EventPriority;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class HandcuffsEvents {
/*    */   @SubscribeEvent(priority = EventPriority.LOW)
/*    */   public static void onEntityDeath(LivingDeathEvent event) {
/* 26 */     LivingEntity entity = event.getEntityLiving();
/* 27 */     if (entity.func_70644_a((Effect)ModEffects.HANDCUFFED.get())) {
/* 28 */       entity.func_199701_a_(new ItemStack((IItemProvider)ModItems.NORMAL_HANDCUFFS.get()));
/*    */     }
/* 30 */     else if (entity.func_70644_a((Effect)ModEffects.HANDCUFFED_KAIROSEKI.get())) {
/* 31 */       entity.func_199701_a_(new ItemStack((IItemProvider)ModItems.KAIROSEKI_HANDCUFFS.get()));
/*    */     }
/* 33 */     else if (entity.func_70644_a((Effect)ModEffects.HANDCUFFED_EXPLOSIVE.get()) && 
/* 34 */       !entity.field_70170_p.field_72995_K) {
/* 35 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)entity, entity.field_70170_p, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), 4.0F);
/* 36 */       explosion.setStaticDamage(15.0F);
/* 37 */       explosion.ignoreFactionChecks();
/* 38 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(5));
/* 39 */       explosion.doExplosion();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onEntityAttacked(LivingAttackEvent event) {
/* 46 */     LivingEntity target = event.getEntityLiving();
/* 47 */     if (target.func_70089_S() && target.func_70651_bq().stream().anyMatch(e -> e.func_188419_a() instanceof xyz.pixelatedw.mineminenomi.effects.HandcuffedEffect) && target.field_70172_ad > 10) {
/* 48 */       event.setCanceled(true);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onPlayerInteract(PlayerInteractEvent.EntityInteractSpecific event) {
/* 55 */     if (!(event.getTarget() instanceof LivingEntity)) {
/*    */       return;
/*    */     }
/* 58 */     PlayerEntity player = event.getPlayer();
/*    */     
/* 60 */     if (player.func_184614_ca().func_77973_b() != ModItems.KEY.get()) {
/*    */       return;
/*    */     }
/* 63 */     LivingEntity target = (LivingEntity)event.getTarget();
/* 64 */     if (target.func_70651_bq().stream().anyMatch(e -> e.func_188419_a() instanceof xyz.pixelatedw.mineminenomi.effects.HandcuffedEffect)) {
/* 65 */       ItemStack keyStack = player.func_184614_ca();
/* 66 */       keyStack.func_190918_g(1);
/*    */       
/* 68 */       if (target.func_70644_a((Effect)ModEffects.HANDCUFFED_EXPLOSIVE.get()) && !target.field_70170_p.field_72995_K) {
/* 69 */         ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)target, target.field_70170_p, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_(), 4.0F);
/* 70 */         explosion.setStaticDamage(15.0F);
/* 71 */         explosion.ignoreFactionChecks();
/* 72 */         explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(5));
/* 73 */         explosion.doExplosion();
/*    */       } 
/*    */       
/* 76 */       target.func_195063_d((Effect)ModEffects.HANDCUFFED.get());
/* 77 */       target.func_195063_d((Effect)ModEffects.HANDCUFFED_KAIROSEKI.get());
/* 78 */       target.func_195063_d((Effect)ModEffects.HANDCUFFED_EXPLOSIVE.get());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\items\HandcuffsEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */