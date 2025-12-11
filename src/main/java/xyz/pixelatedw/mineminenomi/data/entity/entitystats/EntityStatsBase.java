/*      */ package xyz.pixelatedw.mineminenomi.data.entity.entitystats;
/*      */ import java.util.ArrayDeque;
/*      */ import java.util.Deque;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import java.util.UUID;
/*      */ import javax.annotation.Nullable;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.renderer.GameRenderer;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.LivingEntity;
/*      */ import net.minecraft.entity.MobEntity;
/*      */ import net.minecraft.entity.item.ItemEntity;
/*      */ import net.minecraft.entity.player.PlayerEntity;
/*      */ import net.minecraft.entity.player.ServerPlayerEntity;
/*      */ import net.minecraft.item.Items;
/*      */ import net.minecraft.potion.Effect;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.math.MathHelper;
/*      */ import net.minecraft.util.math.vector.Vector3d;
/*      */ import net.minecraft.util.text.ITextComponent;
/*      */ import net.minecraft.util.text.TranslationTextComponent;
/*      */ import net.minecraftforge.api.distmarker.Dist;
/*      */ import net.minecraftforge.api.distmarker.OnlyIn;
/*      */ import net.minecraftforge.common.MinecraftForge;
/*      */ import net.minecraftforge.eventbus.api.Event;
/*      */ import xyz.pixelatedw.mineminenomi.abilities.cyborg.ColaBackpackBonusAbility;
/*      */ import xyz.pixelatedw.mineminenomi.api.ClientBossExtraInfo;
/*      */ import xyz.pixelatedw.mineminenomi.api.enums.Currency;
/*      */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*      */ import xyz.pixelatedw.mineminenomi.api.events.EntityCarryEvent;
/*      */ import xyz.pixelatedw.mineminenomi.api.events.UpdateCombatStateEvent;
/*      */ import xyz.pixelatedw.mineminenomi.api.events.stats.BountyEvent;
/*      */ import xyz.pixelatedw.mineminenomi.api.events.stats.CurrencyEvent;
/*      */ import xyz.pixelatedw.mineminenomi.api.events.stats.DorikiEvent;
/*      */ import xyz.pixelatedw.mineminenomi.api.events.stats.LoyaltyEvent;
/*      */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*      */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*      */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*      */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModAdvancements;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*      */ import xyz.pixelatedw.mineminenomi.packets.server.SCarryEntityPacket;
/*      */ import xyz.pixelatedw.mineminenomi.packets.server.SLeashPlayerPacket;
/*      */ import xyz.pixelatedw.mineminenomi.packets.server.SSetRogueModePacket;
/*      */ import xyz.pixelatedw.mineminenomi.packets.server.SUpdateCombatStatePacket;
/*      */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*      */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*      */ 
/*      */ public class EntityStatsBase implements IEntityStats {
/*      */   private LivingEntity owner;
/*   54 */   private double loyalty = 0.0D; private double doriki;
/*   55 */   private int cola = 100; private int ultraCola = 0; private int invulnerableTime = 0; private long bounty; private long belly;
/*      */   private long extol;
/*   57 */   private ResourceLocation faction = ModValues.EMPTY;
/*   58 */   private ResourceLocation race = ModValues.EMPTY;
/*   59 */   private String subRace = "";
/*   60 */   private ResourceLocation fightingStyle = ModValues.EMPTY; private boolean hasShadow = true; private boolean hadChiyuEffect = false;
/*      */   private boolean hasHeart = true;
/*   62 */   private double damageMultiplier = 1.0D; private boolean inCombatMode = false; private boolean hasStrawDoll = true;
/*      */   private boolean isRogue = false;
/*      */   private int freedSlaves;
/*   65 */   private int jumpTicks = 0;
/*      */   
/*   67 */   private float leftImpulse = 0.0F;
/*   68 */   private float forwardImpulse = 0.0F;
/*      */   
/*   70 */   private float cameraYaw = 0.0F;
/*   71 */   private float cameraPitch = 0.0F;
/*      */   private float[] cameraRotations;
/*      */   private boolean hasCameraPinned = false;
/*      */   private float initialYaw;
/*      */   private float maxYaw;
/*      */   private boolean clampCameraYaw = false;
/*      */   private float initialPitch;
/*      */   private float maxPitch;
/*      */   private boolean clampCameraPitch = false;
/*   80 */   private int cameraPinTicks = 0;
/*      */   
/*      */   private boolean isJumping = false;
/*      */   
/*   84 */   private float originalChiyupopoHealth = 0.0F;
/*      */   
/*   86 */   private Map<UUID, ClientBossExtraInfo> extraInfos = new HashMap<>();
/*      */   
/*   88 */   private Deque<ResourceLocation> screenShaders = new ArrayDeque<>();
/*      */   
/*      */   @Nullable
/*      */   private LivingEntity lastAttacker;
/*      */   
/*      */   private long lastAttackTime;
/*      */   
/*      */   private long lastAttackTimestamp;
/*      */   
/*      */   @Nullable
/*      */   private LivingEntity carryTarget;
/*      */   
/*      */   @Nullable
/*      */   private LivingEntity carrier;
/*      */   private float storedDamage;
/*      */   @Nullable
/*      */   private LivingEntity leashHolder;
/*      */   
/*      */   public IEntityStats setOwner(LivingEntity entity) {
/*  107 */     this.owner = entity;
/*  108 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getFreedSlaves() {
/*  113 */     return this.freedSlaves;
/*      */   }
/*      */ 
/*      */   
/*      */   public void addFreedSlaves(int count) {
/*  118 */     this.freedSlaves += count;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setFreedSlaves(int count) {
/*  123 */     this.freedSlaves = count;
/*      */   }
/*      */ 
/*      */   
/*      */   public void addScreenShader(ResourceLocation shader) {
/*  128 */     if (this.screenShaders.contains(shader)) {
/*      */       return;
/*      */     }
/*      */     
/*  132 */     this.screenShaders.addLast(shader);
/*  133 */     updateScreenShader();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasScreenShaderActive(ResourceLocation shader) {
/*  138 */     return this.screenShaders.contains(shader);
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeScreenShader(ResourceLocation shader) {
/*  143 */     if (!this.screenShaders.contains(shader)) {
/*      */       return;
/*      */     }
/*      */     
/*  147 */     this.screenShaders.removeLastOccurrence(shader);
/*  148 */     updateScreenShader();
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateScreenShader() {
/*  153 */     GameRenderer gameRenderer = (Minecraft.func_71410_x()).field_71460_t;
/*  154 */     ResourceLocation shader = this.screenShaders.peekFirst();
/*  155 */     if (shader != null) {
/*  156 */       gameRenderer.func_175069_a(shader);
/*      */     } else {
/*  158 */       gameRenderer.func_147706_e().close();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLeashedTo(LivingEntity entity) {
/*  164 */     this.leashHolder = entity;
/*      */     
/*  166 */     if (this.owner.func_184218_aH()) {
/*  167 */       this.owner.func_184210_p();
/*      */     }
/*      */     
/*  170 */     if (!this.owner.field_70170_p.field_72995_K && entity instanceof PlayerEntity && this.owner instanceof PlayerEntity) {
/*  171 */       WyNetwork.sendToAllTrackingAndSelf(new SLeashPlayerPacket((PlayerEntity)this.owner, (PlayerEntity)entity), (Entity)entity);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void dropLeash() {
/*  177 */     Vector3d leashHolderPos = this.leashHolder.func_213303_ch().func_72441_c(0.0D, this.leashHolder.func_70047_e(), 0.0D);
/*  178 */     this.leashHolder = null;
/*      */     
/*  180 */     ItemEntity entity = new ItemEntity(this.owner.field_70170_p, leashHolderPos.field_72450_a, leashHolderPos.field_72448_b, leashHolderPos.field_72449_c, Items.field_151058_ca.func_190903_i());
/*  181 */     entity.func_174869_p();
/*  182 */     this.owner.field_70170_p.func_217376_c((Entity)entity);
/*      */     
/*  184 */     if (!this.owner.field_70170_p.field_72995_K && this.owner instanceof PlayerEntity) {
/*  185 */       WyNetwork.sendToAllTrackingAndSelf(new SLeashPlayerPacket((PlayerEntity)this.owner, null), (Entity)this.owner);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public LivingEntity getLeashHolder() {
/*  192 */     return this.leashHolder;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isLeashed() {
/*  197 */     return (this.leashHolder != null);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canBeLeashed(LivingEntity entity) {
/*  202 */     return (this.leashHolder == null);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setStoredDamage(float damage) {
/*  207 */     this.storedDamage = damage;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getStoredDamage() {
/*  212 */     return this.storedDamage;
/*      */   }
/*      */ 
/*      */   
/*      */   public void startCarrying(@Nullable LivingEntity target) {
/*  217 */     if (target == null) {
/*  218 */       stopCarrying();
/*      */       
/*      */       return;
/*      */     } 
/*  222 */     if (target.func_184218_aH()) {
/*  223 */       target.func_184210_p();
/*      */     }
/*      */     
/*  226 */     this.carryTarget = target;
/*      */     
/*  228 */     IEntityStats props = EntityStatsCapability.get(target);
/*  229 */     props.setCarrier(this.owner);
/*      */     
/*  231 */     EntityCarryEvent event = new EntityCarryEvent(this.owner, target, true);
/*  232 */     MinecraftForge.EVENT_BUS.post((Event)event);
/*      */     
/*  234 */     if (!this.owner.field_70170_p.field_72995_K && this.owner instanceof PlayerEntity) {
/*  235 */       WyNetwork.sendTo(new SCarryEntityPacket(target), (PlayerEntity)this.owner);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void stopCarrying() {
/*  241 */     EntityCarryEvent event = new EntityCarryEvent(this.owner, this.carryTarget, false);
/*  242 */     boolean isCancelled = MinecraftForge.EVENT_BUS.post((Event)event);
/*  243 */     if (isCancelled) {
/*      */       return;
/*      */     }
/*      */     
/*  247 */     if (this.carryTarget != null) {
/*  248 */       IEntityStats props = EntityStatsCapability.get(this.carryTarget);
/*  249 */       props.setCarrier(null);
/*      */     } 
/*      */     
/*  252 */     this.carryTarget = null;
/*      */     
/*  254 */     this.owner.func_195063_d((Effect)ModEffects.CARRYING.get());
/*      */     
/*  256 */     if (!this.owner.field_70170_p.field_72995_K && this.owner instanceof PlayerEntity) {
/*  257 */       WyNetwork.sendTo(new SCarryEntityPacket(null), (PlayerEntity)this.owner);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public LivingEntity getCarry() {
/*  264 */     return this.carryTarget;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isCarrying() {
/*  269 */     return (this.carryTarget != null && this.carryTarget.func_70089_S());
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCarrier(LivingEntity entity) {
/*  274 */     this.carrier = entity;
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public LivingEntity getCarrier() {
/*  280 */     return this.carrier;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isCarried() {
/*  285 */     return (this.carrier != null && this.carrier.func_70089_S());
/*      */   }
/*      */ 
/*      */   
/*      */   public void setInCombatCache(@Nullable LivingEntity attacker) {
/*  290 */     if (attacker != null) {
/*  291 */       this.lastAttackTime = 300L;
/*  292 */       this.lastAttackTimestamp = attacker.field_70170_p.func_82737_E();
/*      */     } else {
/*      */       
/*  295 */       this.lastAttackTime = 0L;
/*      */     } 
/*      */     
/*  298 */     if (this.lastAttacker != attacker) {
/*  299 */       this.lastAttacker = attacker;
/*  300 */       boolean hasAttacker = (this.lastAttacker != null);
/*  301 */       UpdateCombatStateEvent event = new UpdateCombatStateEvent(this.owner, this.lastAttacker, hasAttacker);
/*  302 */       MinecraftForge.EVENT_BUS.post((Event)event);
/*  303 */       if (this.owner instanceof ServerPlayerEntity) {
/*  304 */         WyNetwork.sendTo(new SUpdateCombatStatePacket(this.lastAttacker), (PlayerEntity)this.owner);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isInCombatCache() {
/*  311 */     return (this.lastAttackTime > 0L);
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public LivingEntity getLastAttacker() {
/*  317 */     return this.lastAttacker;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getLastAttackTime() {
/*  322 */     return this.lastAttackTime;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getLastAttackTimestamp() {
/*  327 */     return this.lastAttackTimestamp;
/*      */   }
/*      */ 
/*      */   
/*      */   public void tickLastAttack() {
/*  332 */     if (this.owner == null) {
/*      */       return;
/*      */     }
/*      */     
/*  336 */     boolean isAttackerAlive = (this.lastAttacker != null && this.lastAttacker.func_70089_S());
/*  337 */     boolean isAttackerInDistance = (isAttackerAlive && Math.abs(this.lastAttacker.func_70068_e((Entity)this.owner)) <= 10000.0D);
/*  338 */     boolean isTheTarget = (isAttackerAlive && this.lastAttacker instanceof MobEntity && ((MobEntity)this.lastAttacker).func_70638_az() == this.owner);
/*      */     
/*  340 */     boolean canTickDown = (!isAttackerAlive || !isAttackerInDistance || !isTheTarget);
/*      */     
/*  342 */     if (canTickDown && this.lastAttackTime > 0L) {
/*  343 */       this.lastAttackTime--;
/*      */     }
/*  345 */     else if (this.lastAttackTime <= 0L && this.lastAttacker != null) {
/*  346 */       setInCombatCache(null);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Map<UUID, ClientBossExtraInfo> getExtraBossInfo() {
/*  352 */     return this.extraInfos;
/*      */   }
/*      */ 
/*      */   
/*      */   public void addExtraBossInfo(UUID id, ClientBossExtraInfo info) {
/*  357 */     this.extraInfos.put(id, info);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getDoriki() {
/*  364 */     return this.doriki;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean alterDoriki(double value, StatChangeSource source) {
/*  369 */     if (this.owner instanceof PlayerEntity) {
/*  370 */       DorikiEvent.Pre pre = new DorikiEvent.Pre((PlayerEntity)this.owner, value, source);
/*  371 */       if (MinecraftForge.EVENT_BUS.post((Event)pre)) {
/*  372 */         return false;
/*      */       }
/*  374 */       value = pre.getDoriki();
/*      */     } 
/*  376 */     this.doriki = MathHelper.func_151237_a(this.doriki + value, 0.0D, CommonConfig.INSTANCE.getDorikiLimit());
/*  377 */     if (this.owner instanceof PlayerEntity) {
/*  378 */       DorikiEvent.Post post = new DorikiEvent.Post((PlayerEntity)this.owner, value, source);
/*  379 */       MinecraftForge.EVENT_BUS.post((Event)post);
/*  380 */       if (this.owner instanceof ServerPlayerEntity) {
/*  381 */         ModAdvancements.OBTAIN_DORIKI.trigger((ServerPlayerEntity)this.owner, (int)this.doriki);
/*      */       }
/*      */     } 
/*  384 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDoriki(double value) {
/*  389 */     this.doriki = MathHelper.func_151237_a(value, 0.0D, CommonConfig.INSTANCE.getDorikiLimit());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public long getBelly() {
/*  395 */     return this.belly;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean alterBelly(long value, StatChangeSource source) {
/*  400 */     if (this.owner instanceof PlayerEntity) {
/*  401 */       CurrencyEvent.Pre pre = new CurrencyEvent.Pre((PlayerEntity)this.owner, value, Currency.BELLY, source);
/*  402 */       if (MinecraftForge.EVENT_BUS.post((Event)pre)) {
/*  403 */         return false;
/*      */       }
/*  405 */       value = pre.getAmount();
/*      */     } 
/*  407 */     this.belly = WyHelper.clamp(this.belly + value, 0L, 999999999L);
/*  408 */     if (this.owner instanceof PlayerEntity) {
/*  409 */       CurrencyEvent.Post post = new CurrencyEvent.Post((PlayerEntity)this.owner, value, Currency.BELLY, source);
/*  410 */       MinecraftForge.EVENT_BUS.post((Event)post);
/*  411 */       if (this.owner instanceof ServerPlayerEntity) {
/*  412 */         ModAdvancements.OBTAIN_BELLY.trigger((ServerPlayerEntity)this.owner, (int)this.belly, false);
/*      */       }
/*      */     } 
/*  415 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBelly(long value) {
/*  421 */     this.belly = value;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public long getExtol() {
/*  427 */     return this.extol;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean alterExtol(long value, StatChangeSource source) {
/*  432 */     if (this.owner instanceof PlayerEntity) {
/*  433 */       CurrencyEvent extolEvent = new CurrencyEvent((PlayerEntity)this.owner, value, Currency.EXTOL, source);
/*  434 */       if (MinecraftForge.EVENT_BUS.post((Event)extolEvent)) {
/*  435 */         return false;
/*      */       }
/*  437 */       value = extolEvent.getAmount();
/*      */     } 
/*  439 */     this.extol = WyHelper.clamp(this.extol + value, 0L, 999999999L);
/*  440 */     if (this.owner instanceof PlayerEntity) {
/*  441 */       CurrencyEvent.Post post = new CurrencyEvent.Post((PlayerEntity)this.owner, value, Currency.EXTOL, source);
/*  442 */       MinecraftForge.EVENT_BUS.post((Event)post);
/*      */     } 
/*  444 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setExtol(long value) {
/*  450 */     this.extol = value;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public long getBounty() {
/*  456 */     return this.bounty;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean alterBounty(long value, StatChangeSource source) {
/*  461 */     if (this.owner instanceof PlayerEntity) {
/*  462 */       BountyEvent.Pre pre = new BountyEvent.Pre((PlayerEntity)this.owner, value, source);
/*  463 */       if (MinecraftForge.EVENT_BUS.post((Event)pre)) {
/*  464 */         return false;
/*      */       }
/*  466 */       value = pre.getBounty();
/*      */     } 
/*  468 */     this.bounty = WyHelper.clamp(this.bounty + value, 0L, 100000000000L);
/*  469 */     if (this.owner instanceof PlayerEntity) {
/*  470 */       BountyEvent.Post post = new BountyEvent.Post((PlayerEntity)this.owner, value, source);
/*  471 */       MinecraftForge.EVENT_BUS.post((Event)post);
/*  472 */       if (this.owner instanceof ServerPlayerEntity) {
/*  473 */         ModAdvancements.OBTAIN_BOUNTY.trigger((ServerPlayerEntity)this.owner, (int)this.bounty);
/*      */       }
/*      */     } 
/*  476 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBounty(long value) {
/*  482 */     this.bounty = value;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getCola() {
/*  487 */     return this.cola;
/*      */   }
/*      */ 
/*      */   
/*      */   public void alterCola(int value) {
/*  492 */     this.cola = MathHelper.func_76125_a(this.cola + value, 0, getMaxCola());
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCola(int value) {
/*  497 */     this.cola = MathHelper.func_76125_a(value, 0, getMaxCola());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void setForcedCola(int value) {
/*  508 */     this.cola = value;
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateCola() {
/*  513 */     this.cola = MathHelper.func_76125_a(this.cola, 0, getMaxCola());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxCola() {
/*  520 */     int maxCola = 100;
/*      */ 
/*      */     
/*  523 */     maxCola += this.ultraCola * 20;
/*      */ 
/*      */     
/*  526 */     if (this.owner != null) {
/*  527 */       IAbilityData props = AbilityDataCapability.get(this.owner);
/*  528 */       ColaBackpackBonusAbility backpackBonus = (ColaBackpackBonusAbility)props.getPassiveAbility(ColaBackpackBonusAbility.INSTANCE);
/*  529 */       if (backpackBonus != null) {
/*  530 */         maxCola += 500;
/*      */       }
/*      */     } 
/*      */     
/*  534 */     maxCola = MathHelper.func_76125_a(maxCola, 0, 1000);
/*      */     
/*  536 */     return maxCola;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getUltraCola() {
/*  542 */     return this.ultraCola;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUltraCola(int value) {
/*  548 */     this.ultraCola = MathHelper.func_76125_a(value, 0, 20);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void addUltraCola(int value) {
/*  554 */     this.ultraCola = MathHelper.func_76125_a(this.ultraCola + value, 0, 20);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public double getLoyalty() {
/*  560 */     return this.loyalty;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean alterLoyalty(double value, StatChangeSource source) {
/*  565 */     if (this.owner instanceof PlayerEntity) {
/*  566 */       LoyaltyEvent.Pre pre = new LoyaltyEvent.Pre((PlayerEntity)this.owner, value, source);
/*  567 */       if (MinecraftForge.EVENT_BUS.post((Event)pre)) {
/*  568 */         return false;
/*      */       }
/*  570 */       value = pre.getLoyalty();
/*      */     } 
/*  572 */     this.loyalty = MathHelper.func_151237_a(this.loyalty + value, -5.0D, 100.0D);
/*  573 */     if (this.owner instanceof PlayerEntity) {
/*  574 */       LoyaltyEvent.Post post = new LoyaltyEvent.Post((PlayerEntity)this.owner, value, source);
/*  575 */       MinecraftForge.EVENT_BUS.post((Event)post);
/*  576 */       if (this.owner instanceof ServerPlayerEntity) {
/*  577 */         ModAdvancements.OBTAIN_LOYALTY.trigger((ServerPlayerEntity)this.owner, (int)this.loyalty);
/*      */       }
/*      */       
/*  580 */       if (this.loyalty <= -5.0D) {
/*  581 */         setFaction(ModValues.PIRATE);
/*  582 */         TranslationTextComponent translationTextComponent = new TranslationTextComponent(ModI18n.INFO_FACTION_CHANGE, new Object[] { ModI18n.FACTION_PIRATE.getString() });
/*  583 */         ((PlayerEntity)this.owner).func_146105_b((ITextComponent)translationTextComponent, true);
/*      */       } 
/*      */     } 
/*  586 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLoyalty(double value) {
/*  592 */     this.loyalty = value;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getInvulnerableTime() {
/*  597 */     return this.invulnerableTime;
/*      */   }
/*      */ 
/*      */   
/*      */   public void alterInvulnerableTime(int value) {
/*  602 */     this.invulnerableTime = Math.max(0, this.invulnerableTime + value);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setInvulnerableTime(int value) {
/*  607 */     this.invulnerableTime = Math.max(value, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public FactionHelper.MarineRank getMarineRank() {
/*  617 */     if (!isMarine()) {
/*  618 */       return null;
/*      */     }
/*      */     
/*  621 */     for (int i = 0; i < (FactionHelper.MarineRank.values()).length; i++) {
/*      */       
/*  623 */       FactionHelper.MarineRank rank = FactionHelper.MarineRank.values()[i];
/*  624 */       FactionHelper.MarineRank next = (i + 1 < (FactionHelper.MarineRank.values()).length) ? FactionHelper.MarineRank.values()[i + 1] : null;
/*      */       
/*  626 */       if (getLoyalty() >= rank.getRequiredLoyalty() && (next == null || getLoyalty() < next.getRequiredLoyalty()))
/*      */       {
/*  628 */         return rank;
/*      */       }
/*      */     } 
/*      */     
/*  632 */     return FactionHelper.MarineRank.CHORE_BOY;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasMarineRank(FactionHelper.MarineRank rank) {
/*  638 */     if (!isMarine()) {
/*  639 */       return false;
/*      */     }
/*      */     
/*  642 */     if (getMarineRank().ordinal() >= rank.ordinal()) {
/*  643 */       return true;
/*      */     }
/*      */     
/*  646 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public FactionHelper.RevolutionaryRank getRevolutionaryRank() {
/*  654 */     if (!isRevolutionary()) {
/*  655 */       return null;
/*      */     }
/*      */     
/*  658 */     for (int i = 0; i < (FactionHelper.RevolutionaryRank.values()).length; i++) {
/*      */       
/*  660 */       FactionHelper.RevolutionaryRank rank = FactionHelper.RevolutionaryRank.values()[i];
/*  661 */       FactionHelper.RevolutionaryRank next = (i + 1 < (FactionHelper.RevolutionaryRank.values()).length) ? FactionHelper.RevolutionaryRank.values()[i + 1] : null;
/*      */       
/*  663 */       if (getLoyalty() >= rank.getRequiredLoyalty() && (next == null || getLoyalty() < next.getRequiredLoyalty()))
/*      */       {
/*  665 */         return rank;
/*      */       }
/*      */     } 
/*      */     
/*  669 */     return FactionHelper.RevolutionaryRank.MEMBER;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasRevolutionaryRank(FactionHelper.RevolutionaryRank rank) {
/*  675 */     if (!isRevolutionary()) {
/*  676 */       return false;
/*      */     }
/*      */     
/*  679 */     if (getRevolutionaryRank().ordinal() >= rank.ordinal()) {
/*  680 */       return true;
/*      */     }
/*      */     
/*  683 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPirate() {
/*  690 */     if (this.faction == null) {
/*  691 */       return false;
/*      */     }
/*      */     
/*  694 */     return this.faction.equals(ModValues.PIRATE);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isMarine() {
/*  700 */     if (this.faction == null) {
/*  701 */       return false;
/*      */     }
/*      */     
/*  704 */     return this.faction.equals(ModValues.MARINE);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isBountyHunter() {
/*  710 */     if (this.faction == null) {
/*  711 */       return false;
/*      */     }
/*      */     
/*  714 */     return this.faction.equals(ModValues.BOUNTY_HUNTER);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isRevolutionary() {
/*  720 */     if (this.faction == null) {
/*  721 */       return false;
/*      */     }
/*      */     
/*  724 */     return this.faction.equals(ModValues.REVOLUTIONARY);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isBandit() {
/*  730 */     if (this.faction == null) {
/*  731 */       return false;
/*      */     }
/*      */     
/*  734 */     return this.faction.equals(ModValues.BANDIT);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isCivilian() {
/*  740 */     if (this.faction == null) {
/*  741 */       return false;
/*      */     }
/*      */     
/*  744 */     return this.faction.equals(ModValues.CIVILIAN);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasFaction() {
/*  750 */     return !this.faction.equals(ModValues.EMPTY);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFaction(String value) {
/*  756 */     setFaction(new ResourceLocation(value));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFaction(ResourceLocation value) {
/*  762 */     this.faction = value;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ResourceLocation getFaction() {
/*  768 */     return this.faction;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isHuman() {
/*  774 */     if (this.race == null) {
/*  775 */       return false;
/*      */     }
/*      */     
/*  778 */     return this.race.equals(ModValues.HUMAN);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isFishman() {
/*  784 */     if (this.race == null) {
/*  785 */       return false;
/*      */     }
/*      */     
/*  788 */     return this.race.equals(ModValues.FISHMAN);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isCyborg() {
/*  794 */     if (this.race == null) {
/*  795 */       return false;
/*      */     }
/*      */     
/*  798 */     return this.race.equals(ModValues.CYBORG);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isMink() {
/*  804 */     if (this.race == null) {
/*  805 */       return false;
/*      */     }
/*      */     
/*  808 */     return this.race.equals(ModValues.MINK);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasRace() {
/*  814 */     return !this.race.equals(ModValues.EMPTY);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRace(String value) {
/*  820 */     setRace(new ResourceLocation(value));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRace(ResourceLocation value) {
/*  826 */     this.race = value;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ResourceLocation getRace() {
/*  832 */     return this.race;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSwordsman() {
/*  838 */     if (this.fightingStyle == null) {
/*  839 */       return false;
/*      */     }
/*      */     
/*  842 */     return this.fightingStyle.equals(ModValues.SWORDSMAN);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSniper() {
/*  848 */     if (this.fightingStyle == null) {
/*  849 */       return false;
/*      */     }
/*      */     
/*  852 */     return this.fightingStyle.equals(ModValues.SNIPER);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isDoctor() {
/*  858 */     if (this.fightingStyle == null) {
/*  859 */       return false;
/*      */     }
/*      */     
/*  862 */     return this.fightingStyle.equals(ModValues.DOCTOR);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isWeatherWizard() {
/*  868 */     if (this.fightingStyle == null) {
/*  869 */       return false;
/*      */     }
/*      */     
/*  872 */     return this.fightingStyle.equals(ModValues.ART_OF_WEATHER);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isBlackLeg() {
/*  878 */     if (this.fightingStyle == null) {
/*  879 */       return false;
/*      */     }
/*      */     
/*  882 */     return this.fightingStyle.equals(ModValues.BLACK_LEG);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isBrawler() {
/*  888 */     if (this.fightingStyle == null) {
/*  889 */       return false;
/*      */     }
/*      */     
/*  892 */     return this.fightingStyle.equals(ModValues.BRAWLER);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasFightingStyle() {
/*  898 */     return !this.fightingStyle.equals(ModValues.EMPTY);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFightingStyle(String value) {
/*  904 */     setFightingStyle(new ResourceLocation(value));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFightingStyle(ResourceLocation value) {
/*  910 */     this.fightingStyle = value;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ResourceLocation getFightingStyle() {
/*  916 */     return this.fightingStyle;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasShadow() {
/*  922 */     return this.hasShadow;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setShadow(boolean value) {
/*  928 */     this.hasShadow = value;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hadChiyuEffect() {
/*  933 */     return this.hadChiyuEffect;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setChiyuEffect(boolean value) {
/*  938 */     this.hadChiyuEffect = value;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasHeart() {
/*  944 */     return this.hasHeart;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHeart(boolean value) {
/*  950 */     this.hasHeart = value;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInCombatMode() {
/*  956 */     return this.inCombatMode;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCombatMode(boolean value) {
/*  962 */     this.inCombatMode = value;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasStrawDoll() {
/*  968 */     return this.hasStrawDoll;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStrawDoll(boolean value) {
/*  974 */     this.hasStrawDoll = value;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public double getDamageMultiplier() {
/*  980 */     return this.damageMultiplier;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDamageMultiplier(double multiplier) {
/*  986 */     this.damageMultiplier = multiplier;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isBunnyMink() {
/*  992 */     return this.subRace.equalsIgnoreCase("mink_bunny");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isDogMink() {
/*  998 */     return this.subRace.equalsIgnoreCase("mink_dog");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isLionMink() {
/* 1004 */     return this.subRace.equalsIgnoreCase("mink_lion");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSubRace(String value) {
/* 1010 */     this.subRace = value;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSubRace() {
/* 1016 */     return this.subRace;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isRogue() {
/* 1022 */     return this.isRogue;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRogue(boolean value) {
/* 1028 */     this.isRogue = value;
/* 1029 */     if (this.owner instanceof ServerPlayerEntity) {
/* 1030 */       WyNetwork.sendTo(new SSetRogueModePacket(this.isRogue), (PlayerEntity)this.owner);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public float getLeftImpulse() {
/* 1036 */     return this.leftImpulse;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLeftImpulse(float value) {
/* 1041 */     this.leftImpulse = MathHelper.func_76131_a(value, -1.0F, 1.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public float getForwardImpulse() {
/* 1046 */     return this.forwardImpulse;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setForwardImpulse(float value) {
/* 1051 */     this.forwardImpulse = MathHelper.func_76131_a(value, -1.0F, 1.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isJumping() {
/* 1056 */     return this.isJumping;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setJumping(boolean value) {
/* 1061 */     this.isJumping = value;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getJumpTicks() {
/* 1066 */     return this.jumpTicks;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setJumpTicks(int value) {
/* 1071 */     this.jumpTicks = value;
/*      */   }
/*      */ 
/*      */   
/*      */   public void alterJumpTicks(int value) {
/* 1076 */     this.jumpTicks += value;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getOriginalChiyupopoHealth() {
/* 1081 */     return this.originalChiyupopoHealth;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setOriginalChiyupopoHealth(float health) {
/* 1086 */     this.originalChiyupopoHealth = health;
/*      */   }
/*      */ 
/*      */   
/*      */   @OnlyIn(Dist.CLIENT)
/*      */   public void pinCamera(PlayerEntity player) {
/* 1092 */     this.cameraYaw = player.field_70177_z;
/* 1093 */     this.cameraPitch = player.field_70125_A;
/* 1094 */     this.cameraRotations = new float[] { this.cameraPitch, this.cameraYaw };
/* 1095 */     this.hasCameraPinned = true;
/* 1096 */     this.initialYaw = 0.0F;
/* 1097 */     this.maxYaw = 0.0F;
/* 1098 */     this.initialPitch = 0.0F;
/* 1099 */     this.maxPitch = 0.0F;
/* 1100 */     this.clampCameraYaw = false;
/* 1101 */     this.clampCameraPitch = false;
/* 1102 */     this.cameraPinTicks = 0;
/*      */   }
/*      */ 
/*      */   
/*      */   @OnlyIn(Dist.CLIENT)
/*      */   public void clampCameraYaw(PlayerEntity player, float initialYaw, float maxYaw) {
/* 1108 */     this.initialYaw = initialYaw;
/* 1109 */     this.maxYaw = maxYaw;
/* 1110 */     this.clampCameraYaw = true;
/*      */   }
/*      */ 
/*      */   
/*      */   @OnlyIn(Dist.CLIENT)
/*      */   public void clampCameraPitch(PlayerEntity player, float initialPitch, float maxPitch) {
/* 1116 */     this.initialPitch = initialPitch;
/* 1117 */     this.maxPitch = maxPitch;
/* 1118 */     this.clampCameraPitch = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void unpinCamera() {
/* 1123 */     this.hasCameraPinned = false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasCameraPinned() {
/* 1128 */     return this.hasCameraPinned;
/*      */   }
/*      */ 
/*      */   
/*      */   public float[] getCameraRotations() {
/* 1133 */     return this.cameraRotations;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getCameraInitialYaw() {
/* 1138 */     return this.initialYaw;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getCameraMaxYaw() {
/* 1143 */     return this.maxYaw;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasCameraYawClamped() {
/* 1148 */     return this.clampCameraYaw;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getCameraInitialPitch() {
/* 1153 */     return this.initialPitch;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getCameraMaxPitch() {
/* 1158 */     return this.maxPitch;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasCameraPitchClamped() {
/* 1163 */     return this.clampCameraPitch;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCameraPinTimer(int ticks) {
/* 1168 */     this.cameraPinTicks = ticks;
/*      */   }
/*      */ 
/*      */   
/*      */   public void tickCameraPin() {
/* 1173 */     if (this.cameraPinTicks > 0 && 
/* 1174 */       this.cameraPinTicks-- <= 0)
/* 1175 */       unpinCamera(); 
/*      */   }
/*      */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\entitystats\EntityStatsBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */