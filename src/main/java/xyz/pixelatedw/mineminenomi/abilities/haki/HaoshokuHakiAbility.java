/*     */ package xyz.pixelatedw.mineminenomi.abilities.haki;
/*     */ import java.awt.Color;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.ability.HakiKnockoutEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.LightningDischargeEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.mixins.EffectInstanceMixin;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.haki.HaoshokuHakiParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class HaoshokuHakiAbility extends Ability {
/*  49 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "haoshoku_haki", new Pair[] {
/*  50 */         (Pair)ImmutablePair.of("A burst of the unique Conqueror's haki, that knocks out enemies that are weaker than the user.", null)
/*     */       });
/*  52 */   private static final List<Supplier<Effect>> REDUCIBLE_EFFECTS = Arrays.asList((Supplier<Effect>[])new Supplier[] { (Supplier)ModEffects.CANDLE_LOCK, (Supplier)ModEffects.LOVESTRUCK, (Supplier)ModEffects.FROZEN, (Supplier)ModEffects.CANDY_STUCK });
/*  53 */   private static final ResourceLocation[] ICONS = new ResourceLocation[] { new ResourceLocation("mineminenomi", "textures/abilities/haoshoku_haki_0.png"), new ResourceLocation("mineminenomi", "textures/abilities/haoshoku_haki_1.png"), new ResourceLocation("mineminenomi", "textures/abilities/haoshoku_haki_2.png") };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   public static final AbilityCore<HaoshokuHakiAbility> INSTANCE = (new AbilityCore.Builder("Haoshoku Haki", AbilityCategory.HAKI, HaoshokuHakiAbility::new))
/*  60 */     .setIcon(ICONS[0])
/*  61 */     .addDescriptionLine(DESCRIPTION)
/*  62 */     .setUnlockCheck(HaoshokuHakiAbility::canUnlock)
/*  63 */     .build();
/*     */   
/*  65 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*     */   
/*  67 */   private HaoshokuHakiParticleEffect.Details particleDetails = new HaoshokuHakiParticleEffect.Details();
/*     */   
/*     */   private LightningDischargeEntity discharge;
/*     */   
/*  71 */   private Color color = new Color(16711680);
/*     */   
/*  73 */   private int burstSize = 0;
/*  74 */   private int radius = 0;
/*  75 */   private int unconsciousTimer = 0;
/*  76 */   private int haoMastery = 0;
/*     */   
/*  78 */   private final Interval effectInterval = new Interval(20);
/*     */   
/*     */   public HaoshokuHakiAbility(AbilityCore<HaoshokuHakiAbility> core) {
/*  81 */     super(core);
/*     */     
/*  83 */     this.isNew = true;
/*  84 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent });
/*     */     
/*  86 */     addCanUseCheck(HakiHelper::canEnableHaki);
/*     */     
/*  88 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  92 */     this.continuousComponent.triggerContinuity(entity);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  96 */     this.effectInterval.restartIntervalToZero();
/*     */     
/*  98 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.HAKI_RELEASE_SFX.get(), SoundCategory.PLAYERS, 4.0F, 1.0F);
/*     */     
/* 100 */     IHakiData hakiProps = HakiDataCapability.get(entity);
/*     */     
/* 102 */     float haoLevel = hakiProps.getTotalHakiExp() / 100.0F;
/*     */     
/* 104 */     if (haoLevel <= 1.0F) {
/* 105 */       this.radius = 10;
/* 106 */       this.unconsciousTimer = 20;
/* 107 */       this.burstSize = 3;
/* 108 */       this.haoMastery = 0;
/* 109 */     } else if (haoLevel > 1.0F && haoLevel <= 1.75F) {
/* 110 */       this.radius = 25;
/* 111 */       this.unconsciousTimer = 40;
/* 112 */       this.burstSize = 5;
/* 113 */       this.haoMastery = 1;
/* 114 */     } else if (haoLevel > 1.75F) {
/* 115 */       this.radius = 40;
/* 116 */       this.unconsciousTimer = 50;
/* 117 */       this.burstSize = 10;
/* 118 */       this.haoMastery = 2;
/*     */     } 
/*     */     
/* 121 */     this.color = new Color(HakiHelper.getHaoshokuColour(entity));
/*     */     
/* 123 */     this.discharge = new LightningDischargeEntity((Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_() + 1.5D, entity.func_226281_cx_(), entity.field_70177_z, entity.field_70125_A);
/* 124 */     this.discharge.setAliveTicks(-1);
/* 125 */     this.discharge.setUpdateRate(8);
/* 126 */     this.discharge.setLightningLength((this.radius * 2));
/* 127 */     this.discharge.setColor(new Color(0, 0, 0, 100));
/* 128 */     this.discharge.setOutlineColor(this.color);
/* 129 */     this.discharge.setRenderTransparent();
/* 130 */     this.discharge.setDetails(16);
/*     */     
/* 132 */     int density = (this.haoMastery == 2) ? 32 : 16;
/*     */     
/* 134 */     this.discharge.setDensity(density);
/* 135 */     this.discharge.setSize(1.0F);
/* 136 */     this.discharge.setSkipSegments(1);
/*     */     
/* 138 */     if (this.haoMastery == 0) {
/* 139 */       this.discharge.setSplit();
/*     */     }
/*     */     
/* 142 */     entity.field_70170_p.func_217376_c((Entity)this.discharge);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 146 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 150 */     int continueTime = (int)this.continuousComponent.getContinueTime();
/*     */     
/* 152 */     IHakiData hakiProps = HakiDataCapability.get(entity);
/*     */     
/* 154 */     if (this.effectInterval.canTick()) {
/* 155 */       for (EffectInstance instance : entity.func_70651_bq()) {
/* 156 */         boolean isReducible = false;
/*     */         
/* 158 */         for (Supplier<Effect> reducible : REDUCIBLE_EFFECTS) {
/* 159 */           if (reducible.get() == instance.func_188419_a()) {
/* 160 */             isReducible = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 165 */         if (isReducible) {
/* 166 */           ((EffectInstanceMixin)instance).setDuration(instance.func_76459_b() - 100);
/*     */           
/* 168 */           WyHelper.sendApplyEffectToAllNearby(entity, entity.func_213303_ch(), 100, instance);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 173 */     if (continueTime % 5 == 0) {
/* 174 */       this.particleDetails.setSize(this.burstSize);
/* 175 */       this.particleDetails.setColor(this.color.getRGB());
/*     */       
/* 177 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.HAOSHOKU_HAKI.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), (ParticleEffect.Details)this.particleDetails);
/*     */       
/* 179 */       this.discharge.func_70107_b(entity.func_226277_ct_(), entity.func_226278_cu_() + 1.0D, entity.func_226281_cx_());
/*     */     } 
/*     */     
/* 182 */     if (continueTime % 10 == 0) {
/* 183 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.HAKI_RELEASE_SFX.get(), SoundCategory.PLAYERS, 3.0F, 0.5F + entity.func_70681_au().nextFloat());
/*     */     }
/*     */     
/* 186 */     if (continueTime % 20 == 0) {
/* 187 */       hakiProps.alterHakiOveruse(200);
/* 188 */       knockoutNearbyEnemies(entity);
/* 189 */       boolean isOnMaxOveruse = !HakiHelper.canEnableHaki(entity);
/* 190 */       if (isOnMaxOveruse) {
/* 191 */         this.continuousComponent.stopContinuity(entity);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 196 */     if (continueTime % 100 == 0) {
/* 197 */       if (this.radius == 25) {
/* 198 */         destroyNearbyBlocks(entity, 1.0F, 3);
/*     */       }
/* 200 */       else if (this.radius > 25) {
/* 201 */         destroyNearbyBlocks(entity, 2.0F, 5);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 207 */     float cooldown = this.continuousComponent.getContinueTime() / 2.0F;
/* 208 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*     */     
/* 210 */     if (this.discharge != null) {
/* 211 */       this.discharge.setAliveTicks(30);
/*     */     }
/*     */   }
/*     */   
/*     */   private void knockoutNearbyEnemies(LivingEntity user) {
/* 216 */     List<LivingEntity> targets = WyHelper.getNearbyLiving(user.func_213303_ch(), (IWorld)user.field_70170_p, this.radius, ModEntityPredicates.getEnemyFactions(user));
/*     */     
/* 218 */     IHakiData hakiProps = HakiDataCapability.get(user);
/* 219 */     float haoLevel = hakiProps.getTotalHakiExp() / 100.0F;
/*     */     
/* 221 */     for (LivingEntity target : targets) {
/* 222 */       double targetHaoLevel = (HakiDataCapability.get(target).getTotalHakiExp() / 100.0F);
/*     */ 
/*     */       
/* 225 */       if (targetHaoLevel + 0.5D >= haoLevel) {
/*     */         continue;
/*     */       }
/*     */       
/* 229 */       if (this.haoMastery > 0) {
/* 230 */         HakiKnockoutEvent.Post postEvent = null;
/*     */         
/* 232 */         if (target.func_70644_a((Effect)ModEffects.UNCONSCIOUS.get())) {
/* 233 */           EffectInstance instance1 = target.func_70660_b((Effect)ModEffects.UNCONSCIOUS.get());
/*     */           
/* 235 */           int timer = instance1.func_76459_b() + this.unconsciousTimer;
/*     */           
/* 237 */           HakiKnockoutEvent.Pre pre = new HakiKnockoutEvent.Pre(target, user, true, timer);
/* 238 */           if (MinecraftForge.EVENT_BUS.post((Event)pre)) {
/*     */             return;
/*     */           }
/*     */           
/* 242 */           ((EffectInstanceMixin)instance1).setDuration(timer);
/*     */           
/* 244 */           postEvent = new HakiKnockoutEvent.Post(target, user, true, timer);
/*     */         } else {
/*     */           
/* 247 */           EffectInstance instance = new EffectInstance((Effect)ModEffects.UNCONSCIOUS.get(), this.unconsciousTimer, 1, false, false);
/*     */           
/* 249 */           HakiKnockoutEvent.Pre pre = new HakiKnockoutEvent.Pre(target, user, false, this.unconsciousTimer);
/* 250 */           if (MinecraftForge.EVENT_BUS.post((Event)pre)) {
/*     */             return;
/*     */           }
/*     */           
/* 254 */           target.func_195064_c(instance);
/*     */           
/* 256 */           postEvent = new HakiKnockoutEvent.Post(target, user, false, this.unconsciousTimer);
/*     */         } 
/*     */         
/* 259 */         MinecraftForge.EVENT_BUS.post((Event)postEvent);
/*     */         continue;
/*     */       } 
/* 262 */       target.func_195064_c(new EffectInstance(Effects.field_76431_k, 200, 0));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void destroyNearbyBlocks(LivingEntity entity, float density, int range) {
/* 268 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/* 269 */     float x0 = (float)entity.func_226277_ct_();
/* 270 */     float z0 = (float)entity.func_226281_cx_();
/* 271 */     int i = 0;
/* 272 */     for (int rho = (int)(-range * density); rho <= Math.PI * density; rho++) {
/* 273 */       for (int phi = 0; phi <= range * density; phi++) {
/* 274 */         i++;
/* 275 */         if (i % 5 == 0) {
/*     */ 
/*     */           
/* 278 */           float phi1 = phi / density;
/* 279 */           float rho1 = rho / density;
/* 280 */           for (int r = 0; r <= 20; r += 5) {
/* 281 */             float x = (float)(x0 + r * Math.cos(phi1) * Math.cos(rho1) + WyHelper.randomWithRange(-3, 3));
/* 282 */             float y = (float)(entity.func_226278_cu_() - 2.0D + r * Math.sin(phi1));
/* 283 */             float z = (float)(z0 + r * Math.cos(phi1) * Math.sin(rho1) + WyHelper.randomWithRange(-3, 3));
/* 284 */             mutpos.func_189532_c(x, y, z);
/* 285 */             BlockState state = entity.field_70170_p.func_180495_p((BlockPos)mutpos);
/* 286 */             BlockState stateAbove = entity.field_70170_p.func_180495_p(mutpos.func_177984_a());
/* 287 */             if (!stateAbove.func_185904_a().func_76220_a() && !mutpos.func_218141_a((Vector3i)entity.func_233580_cy_(), 3.0D) && 
/* 288 */               AbilityHelper.placeBlockIfAllowed(entity, (BlockPos)mutpos, Blocks.field_150350_a.func_176223_P(), DefaultProtectionRules.CORE_FOLIAGE_ORE)) {
/* 289 */               entity.field_70170_p.func_217379_c(2001, (BlockPos)mutpos, Block.func_196246_j(state));
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity user) {
/* 298 */     if (CommonConfig.INSTANCE.getHaoshokuUnlockLogic() == CommonConfig.HaoshokuUnlockLogic.NONE) {
/* 299 */       return false;
/*     */     }
/*     */     
/* 302 */     if (user instanceof PlayerEntity) {
/* 303 */       if (CommonConfig.INSTANCE.isHaoshokuUnlockLogicChanceBased()) {
/* 304 */         boolean flag = HakiHelper.isHaoshokuBorn((PlayerEntity)user);
/* 305 */         if (flag) {
/* 306 */           return true;
/*     */         }
/*     */       } 
/* 309 */       if (CommonConfig.INSTANCE.isHaoshokuUnlockLogicExpBased()) {
/* 310 */         IHakiData props = HakiDataCapability.get(user);
/*     */         
/* 312 */         boolean hasExp = (props.getTotalHakiExp() >= WyHelper.percentage(80.0D, props.getMaxHakiExp()));
/* 313 */         boolean hasHakiAbilities = false;
/* 314 */         int i = 0;
/* 315 */         for (AbilityCore<HaoshokuHakiAbility> core : ModAbilities.HAKI_ABILITIES) {
/* 316 */           if (i >= 2) {
/* 317 */             hasHakiAbilities = true;
/*     */             
/*     */             break;
/*     */           } 
/* 321 */           if (core != INSTANCE && AbilityDataCapability.get(user).hasUnlockedAbility(core)) {
/* 322 */             i++;
/*     */           }
/*     */         } 
/*     */         
/* 326 */         return (hasExp && hasHakiAbilities);
/*     */       } 
/*     */     } 
/*     */     
/* 330 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getIcon(LivingEntity player) {
/* 335 */     float haoLevel = HakiDataCapability.get(player).getTotalHakiExp() / 100.0F;
/* 336 */     int level = 0;
/*     */     
/* 338 */     if (haoLevel > 1.0F && haoLevel <= 1.75F) {
/* 339 */       level = 1;
/*     */     }
/* 341 */     else if (haoLevel > 1.75F) {
/* 342 */       level = 2;
/*     */     } 
/*     */     
/* 345 */     return ICONS[level];
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\haki\HaoshokuHakiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */