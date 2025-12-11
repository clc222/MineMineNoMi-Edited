/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*     */ import net.minecraft.entity.ai.attributes.RangedAttribute;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraftforge.common.ForgeMod;
/*     */ import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingFallEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHealEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import net.minecraftforge.eventbus.api.IEventBus;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.mixins.ILivingEntityMixin;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class ModAttributes
/*     */ {
/*     */   private static final int JUMP_TICK_DELAY = 1;
/*     */   private static final int MAX_JUMP_TICKS = 9;
/*  32 */   public static final RegistryObject<Attribute> FALL_RESISTANCE = WyRegistry.registerAttribute("Fall Resistance", () -> new RangedAttribute("attribute.name.generic.mineminenomi.fall_resistance", 0.0D, -256.0D, 256.0D));
/*  33 */   public static final RegistryObject<Attribute> JUMP_HEIGHT = WyRegistry.registerAttribute("Jump Height", () -> (new RangedAttribute("attribute.name.generic.mineminenomi.jump_height", 1.252203277664432D, -256.0D, 256.0D)).func_233753_a_(true));
/*  34 */   public static final RegistryObject<Attribute> REGEN_RATE = WyRegistry.registerAttribute("Regen Rate", () -> (new RangedAttribute("attribute.name.generic.mineminenomi.regen_rate", 1.0D, 0.0D, 32.0D)).func_233753_a_(true));
/*  35 */   public static final RegistryObject<Attribute> STEP_HEIGHT = WyRegistry.registerAttribute("Step Height", () -> (new RangedAttribute("attribute.name.generic.mineminenomi.step_height", 0.6D, 0.0D, 20.0D)).func_233753_a_(true));
/*     */   
/*  37 */   public static final RegistryObject<Attribute> DAMAGE_REDUCTION = WyRegistry.registerAttribute("Damage Reduction", () -> (new RangedAttribute("attribute.name.generic.mineminenomi.damage_reduction", 0.0D, -2.0D, 0.999D)).func_233753_a_(true));
/*  38 */   public static final RegistryObject<Attribute> ATTACK_RANGE = WyRegistry.registerAttribute("Attack Range", () -> (new RangedAttribute("attribute.name.generic.mineminenomi.attack_range", 0.0D, -1024.0D, 1024.0D)).func_233753_a_(true));
/*  39 */   public static final RegistryObject<Attribute> PUNCH_DAMAGE = WyRegistry.registerAttribute("Punch Damage", () -> (new RangedAttribute("attribute.name.generic.mineminenomi.punch_damage", 0.0D, -1024.0D, 1024.0D)).func_233753_a_(true));
/*  40 */   public static final RegistryObject<Attribute> TIME_PROGRESSION = WyRegistry.registerAttribute("Time Progression", () -> (new RangedAttribute("attribute.name.generic.mineminenomi.time_progression", 1.0D, 0.0D, 1024.0D)).func_233753_a_(true));
/*  41 */   public static final RegistryObject<Attribute> FLOATING_TIME = WyRegistry.registerAttribute("Floating Time", () -> (new RangedAttribute("attribute.name.generic.mineminenomi.floating_time", 100.0D, 0.0D, 2048.0D)).func_233753_a_(true));
/*  42 */   public static final RegistryObject<Attribute> TOUGHNESS = WyRegistry.registerAttribute("Toughness", () -> (new RangedAttribute("attribute.name.generic.mineminenomi.toughness", 0.0D, 0.0D, 20.0D)).func_233753_a_(true));
/*  43 */   public static final RegistryObject<Attribute> GCD = WyRegistry.registerAttribute("GCD", () -> (new RangedAttribute("attribute.name.generic.mineminenomi.gcd", 40.0D, -72000.0D, 72000.0D)).func_233753_a_(true));
/*  44 */   public static final RegistryObject<Attribute> MINING_SPEED = WyRegistry.registerAttribute("Mining Speed", () -> (new RangedAttribute("attribute.name.generic.mineminenomi.mining_speed", 1.0D, -128.0D, 128.0D)).func_233753_a_(true));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*  54 */   public static final RegistryObject<Attribute> FAUX_PROTECTION = WyRegistry.registerAttribute("Faux Protection", () -> (new RangedAttribute("attribute.name.generic.mineminenomi.faux_protection", 0.0D, 0.0D, 20.0D)).func_233753_a_(true));
/*     */   
/*     */   @EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
/*     */   public static class Setup {
/*     */     @SubscribeEvent
/*     */     public static void onEntityConstruct(EntityAttributeModificationEvent event) {
/*  60 */       for (EntityType<? extends LivingEntity> type : (Iterable<EntityType<? extends LivingEntity>>)event.getTypes()) {
/*  61 */         event.add(type, (Attribute)ModAttributes.FALL_RESISTANCE.get());
/*  62 */         event.add(type, (Attribute)ModAttributes.JUMP_HEIGHT.get());
/*  63 */         event.add(type, (Attribute)ModAttributes.REGEN_RATE.get());
/*  64 */         event.add(type, (Attribute)ModAttributes.STEP_HEIGHT.get());
/*  65 */         event.add(type, (Attribute)ModAttributes.DAMAGE_REDUCTION.get());
/*  66 */         event.add(type, (Attribute)ModAttributes.ATTACK_RANGE.get());
/*  67 */         event.add(type, (Attribute)ModAttributes.PUNCH_DAMAGE.get());
/*  68 */         event.add(type, (Attribute)ModAttributes.TIME_PROGRESSION.get());
/*  69 */         event.add(type, (Attribute)ModAttributes.TOUGHNESS.get());
/*  70 */         event.add(type, (Attribute)ModAttributes.GCD.get());
/*  71 */         event.add(type, (Attribute)ModAttributes.FAUX_PROTECTION.get());
/*     */         
/*  73 */         if (type == EntityType.field_200729_aH) {
/*  74 */           event.add(type, (Attribute)ModAttributes.MINING_SPEED.get());
/*     */         }
/*     */         
/*  77 */         if (type == ModEntities.BIG_DUCK.get()) {
/*  78 */           event.add(type, (Attribute)ModAttributes.FLOATING_TIME.get());
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @EventBusSubscriber(modid = "mineminenomi")
/*     */   public static class Defaults
/*     */   {
/*     */     public static double velocityToHeight(double velocity, double gravity, double drag) {
/*  89 */       double yPos = 0.0D;
/*     */       
/*  91 */       while (velocity >= 0.0D) {
/*  92 */         yPos += velocity;
/*     */         
/*  94 */         velocity = (velocity - gravity) * drag;
/*     */       } 
/*     */       
/*  97 */       return yPos;
/*     */     }
/*     */     
/*     */     public static double simulateHeightWithVelocityBoost(double estimatedVelocity, double initialVelocity, double gravity, double drag, int maxTicks, int tickDelay) {
/* 101 */       double yPos = 0.0D;
/*     */       
/* 103 */       for (int i = 0; i < 10000; i++) {
/* 104 */         yPos += initialVelocity;
/*     */         
/* 106 */         initialVelocity = (initialVelocity - gravity) * drag;
/*     */         
/* 108 */         if (i >= tickDelay && i < tickDelay + maxTicks) {
/* 109 */           initialVelocity += estimatedVelocity * Math.pow(drag, (i - tickDelay));
/*     */         }
/*     */         
/* 112 */         if (initialVelocity < 0.0D) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */       
/* 117 */       return yPos;
/*     */     }
/*     */     
/*     */     public static double getVelocityForHeight(double initialVelocity, double gravity, double drag, int maxTicks, int tickDelay, double desiredMove) {
/* 121 */       double lo = -10.0D;
/* 122 */       double hi = 10.0D;
/*     */       
/* 124 */       for (int i = 0; i < 50; i++) {
/* 125 */         double mid = 0.5D * (lo + hi);
/* 126 */         double midHeight = simulateHeightWithVelocityBoost(mid, initialVelocity, gravity, drag, maxTicks, tickDelay);
/*     */         
/* 128 */         if (Math.abs(midHeight - desiredMove) < 6.0E-6D) {
/* 129 */           return mid;
/*     */         }
/*     */         
/* 132 */         if (midHeight < desiredMove) {
/* 133 */           lo = mid;
/*     */         } else {
/* 135 */           hi = mid;
/*     */         } 
/*     */       } 
/*     */       
/* 139 */       return 0.5D * (lo + hi);
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onTick(LivingEvent.LivingUpdateEvent event) {
/* 144 */       LivingEntity entity = event.getEntityLiving();
/*     */       
/* 146 */       IEntityStats entityDataProps = EntityStatsCapability.get(entity);
/*     */       
/* 148 */       if (entityDataProps.isJumping()) {
/* 149 */         ModifiableAttributeInstance jumpHeight = entity.func_110148_a((Attribute)ModAttributes.JUMP_HEIGHT.get());
/*     */         
/* 151 */         if (jumpHeight != null && entityDataProps.getJumpTicks() >= 1 && entityDataProps.getJumpTicks() < 10) {
/* 152 */           double gravityValue = entity.func_233637_b_((Attribute)ForgeMod.ENTITY_GRAVITY.get());
/* 153 */           double jumpPower = ((ILivingEntityMixin)entity).invokeGetJumpPower();
/* 154 */           double baseHeight = velocityToHeight(jumpPower, gravityValue, 0.98D);
/* 155 */           double jumpHeightValue = jumpHeight.func_111126_e() - baseHeight;
/*     */           
/* 157 */           if (jumpHeightValue > 0.0D) {
/* 158 */             double adjustedMove = jumpHeightValue + baseHeight;
/*     */             
/* 160 */             double velocity = getVelocityForHeight(jumpPower, gravityValue, 0.98D, 9, 1, adjustedMove);
/* 161 */             double tickVelocity = velocity * Math.pow(0.98D, (entityDataProps.getJumpTicks() - 1));
/*     */             
/* 163 */             double yRot = entity.field_70177_z * Math.PI / 180.0D;
/*     */             
/* 165 */             double leapPower = ((entity.func_213322_ci()).field_72448_b + tickVelocity) * 0.2D;
/*     */             
/* 167 */             double velocityX = (entity.func_70051_ag() && tickVelocity > 0.0D) ? (-Math.sin(yRot) * leapPower) : 0.0D;
/* 168 */             double velocityZ = (entity.func_70051_ag() && tickVelocity > 0.0D) ? (Math.cos(yRot) * leapPower) : 0.0D;
/*     */             
/* 170 */             entity.func_70024_g(velocityX, tickVelocity, velocityZ);
/*     */           } 
/*     */         } 
/*     */         
/* 174 */         entityDataProps.alterJumpTicks(1);
/*     */       } else {
/* 176 */         entityDataProps.setJumpTicks(10);
/*     */       } 
/*     */       
/* 179 */       if (entity.func_233570_aj_()) {
/* 180 */         entityDataProps.setJumpTicks(0);
/*     */       }
/*     */ 
/*     */       
/* 184 */       ModifiableAttributeInstance attributeInstance = entity.func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get());
/* 185 */       if (attributeInstance != null) {
/* 186 */         float newStepHeight = (float)attributeInstance.func_111126_e();
/*     */ 
/*     */ 
/*     */         
/* 190 */         entity.field_70138_W = newStepHeight;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onPlayerBreakSpeed(PlayerEvent.BreakSpeed event) {
/* 213 */       PlayerEntity player = event.getPlayer();
/* 214 */       ModifiableAttributeInstance attributeInstance = player.func_110148_a((Attribute)ModAttributes.MINING_SPEED.get());
/*     */       
/* 216 */       if (attributeInstance != null) {
/* 217 */         event.setNewSpeed((float)(event.getOriginalSpeed() * attributeInstance.func_111126_e()));
/*     */       }
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onFall(LivingFallEvent e) {
/* 223 */       ModifiableAttributeInstance attributeInstance = e.getEntityLiving().func_110148_a((Attribute)ModAttributes.FALL_RESISTANCE.get());
/* 224 */       if (attributeInstance != null) {
/* 225 */         e.setDistance((float)(e.getDistance() - attributeInstance.func_111126_e()));
/*     */       }
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onJump(LivingEvent.LivingJumpEvent event) {
/* 231 */       LivingEntity entity = event.getEntityLiving();
/*     */       
/* 233 */       ModifiableAttributeInstance jumpHeight = entity.func_110148_a((Attribute)ModAttributes.JUMP_HEIGHT.get());
/*     */       
/* 235 */       if (jumpHeight != null) {
/* 236 */         double value = jumpHeight.func_111126_e();
/*     */         
/* 238 */         if (value <= 0.0D) {
/* 239 */           entity.func_213293_j(0.0D, 0.0D, 0.0D);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onHeal(LivingHealEvent event) {
/* 246 */       ModifiableAttributeInstance attributeInstance = event.getEntityLiving().func_110148_a((Attribute)ModAttributes.REGEN_RATE.get());
/*     */       
/* 248 */       if (attributeInstance != null) {
/* 249 */         float value = (float)attributeInstance.func_111126_e();
/*     */         
/* 251 */         if (value != 1.0F) {
/* 252 */           event.setAmount(event.getAmount() * value);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static void register(IEventBus eventBus) {
/* 259 */     WyRegistry.ATTRIBUTES.register(eventBus);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModAttributes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */