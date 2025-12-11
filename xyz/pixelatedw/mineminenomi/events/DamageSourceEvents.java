/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraftforge.eventbus.api.EventPriority;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.WyLivingAttackEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.WyLivingDamageEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.WyLivingHurtEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class DamageSourceEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onEntityAttackEvent(WyLivingAttackEvent event) {
/*  32 */     if (!CommonConfig.INSTANCE.isSpecialSourceEventsEnabled()) {
/*     */       return;
/*     */     }
/*  35 */     Entity attacker = event.getSource().func_76364_f();
/*  36 */     LivingEntity entity = event.getEntityLiving();
/*     */     
/*  38 */     if (attacker instanceof OPEntity)
/*     */     {
/*  40 */       if (((OPEntity)attacker).hasBusoHaki()) {
/*     */         
/*  42 */         Item mainShield = entity.func_184614_ca().func_77973_b();
/*  43 */         Item secondaryShield = entity.func_184592_cb().func_77973_b();
/*  44 */         if (entity instanceof PlayerEntity && Math.random() > 0.5D && (mainShield.equals(Items.field_185159_cQ) || secondaryShield.equals(Items.field_185159_cQ))) {
/*     */           
/*  46 */           ((PlayerEntity)entity).func_184811_cZ().func_185145_a(Items.field_185159_cQ, 100);
/*  47 */           entity.func_184602_cy();
/*  48 */           entity.field_70170_p.func_72960_a(attacker, (byte)30);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityDamageEvent(WyLivingAttackEvent event) {
/*  57 */     DamageSource source = event.getSource();
/*  58 */     LivingEntity entity = event.getEntityLiving();
/*     */     
/*  60 */     if (entity.field_70170_p.field_72995_K || !(source instanceof xyz.pixelatedw.mineminenomi.init.ModDamageSource) || entity.func_70644_a((Effect)ModEffects.GUARDING.get())) {
/*     */       return;
/*     */     }
/*  63 */     switch (source.func_76355_l()) {
/*     */       
/*     */       case "lava":
/*  66 */         if (!CommonConfig.INSTANCE.isSpecialSourceEventsEnabled() || !(source.func_76346_g() instanceof PlayerEntity))
/*     */           return; 
/*  68 */         entity.func_195063_d(Effects.field_76426_n);
/*     */         break;
/*     */       case "onFire":
/*  71 */         if (!CommonConfig.INSTANCE.isSpecialSourceEventsEnabled() || !(source.func_76346_g() instanceof PlayerEntity)) {
/*     */           return;
/*     */         }
/*  74 */         AbilityHelper.reduceEffect(entity.func_70660_b((Effect)ModEffects.FROZEN.get()), 2.0D);
/*  75 */         AbilityHelper.reduceEffect(entity.func_70660_b(Effects.field_76426_n), 2.0D);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.LOWEST)
/*     */   public static void onLivingDamage(WyLivingDamageEvent event) {
/*  83 */     if (event.getAmount() <= 0.0F) {
/*     */       return;
/*     */     }
/*     */     
/*  87 */     LivingEntity living = event.getEntityLiving();
/*  88 */     if (living.func_70644_a((Effect)ModEffects.DRUNK.get())) {
/*  89 */       IEntityStats props = EntityStatsCapability.get(living);
/*  90 */       float storedDamage = props.getStoredDamage() + event.getAmount();
/*  91 */       props.setStoredDamage(storedDamage);
/*  92 */       living.func_70691_i(event.getAmount());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityHurtEvent(WyLivingHurtEvent event) {
/*  99 */     DamageSource source = event.getSource();
/* 100 */     LivingEntity entity = event.getEntityLiving();
/* 101 */     Entity attacker = source.func_76364_f();
/* 102 */     float amount = event.getAmount();
/*     */     
/* 104 */     if (entity.field_70170_p.field_72995_K || event.getAmount() <= 0.0F) {
/*     */       return;
/*     */     }
/* 107 */     if (source.func_76355_l().equals("lightningBolt") || source.func_76355_l().equals("lava") || source.func_76355_l().equals("onFire") || source.func_76355_l().equals("inFire")) {
/*     */       
/* 109 */       if (entity.func_70644_a((Effect)ModEffects.FROZEN.get())) {
/*     */         
/* 111 */         AbilityHelper.reduceEffect(entity.func_70660_b((Effect)ModEffects.FROZEN.get()), 2.0D);
/* 112 */         entity.func_70066_B();
/*     */         
/*     */         return;
/*     */       } 
/* 116 */       if (entity.func_70644_a((Effect)ModEffects.FROSTBITE.get())) {
/*     */         
/* 118 */         entity.func_195063_d((Effect)ModEffects.FROSTBITE.get());
/* 119 */         entity.func_70066_B();
/*     */       } 
/*     */       
/* 122 */       if (entity.func_70644_a((Effect)ModEffects.CANDLE_LOCK.get())) {
/*     */         
/* 124 */         entity.func_195063_d((Effect)ModEffects.CANDLE_LOCK.get());
/* 125 */         entity.func_70066_B();
/*     */       } 
/*     */       
/* 128 */       if (entity.func_70644_a((Effect)ModEffects.CANDY_STUCK.get())) {
/*     */         
/* 130 */         entity.func_195063_d((Effect)ModEffects.CANDY_STUCK.get());
/* 131 */         entity.func_70066_B();
/*     */       } 
/*     */     } 
/*     */     
/* 135 */     if (event.getSource().func_76364_f() instanceof PlayerEntity);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\DamageSourceEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */