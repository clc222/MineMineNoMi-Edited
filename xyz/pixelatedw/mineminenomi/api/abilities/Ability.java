/*      */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*      */ 
/*      */ import com.google.common.base.Strings;
/*      */ import java.io.Serializable;
/*      */ import java.lang.invoke.SerializedLambda;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Optional;
/*      */ import java.util.Random;
/*      */ import java.util.stream.Stream;
/*      */ import javax.annotation.Nullable;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.LivingEntity;
/*      */ import net.minecraft.entity.player.PlayerEntity;
/*      */ import net.minecraft.nbt.CompoundNBT;
/*      */ import net.minecraft.nbt.INBT;
/*      */ import net.minecraft.potion.Effect;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.Util;
/*      */ import net.minecraft.util.text.ITextComponent;
/*      */ import net.minecraft.util.text.StringTextComponent;
/*      */ import net.minecraft.util.text.TranslationTextComponent;
/*      */ import net.minecraftforge.common.MinecraftForge;
/*      */ import net.minecraftforge.eventbus.api.Event;
/*      */ import net.minecraftforge.fml.loading.FMLEnvironment;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponentKey;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimeScreamComponent;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DisableComponent;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.components.MorphComponent;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*      */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SlotDecorationComponent;
/*      */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*      */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*      */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*      */ import xyz.pixelatedw.mineminenomi.api.events.ability.AbilityTickEvent;
/*      */ import xyz.pixelatedw.mineminenomi.api.events.ability.AbilityUseEvent;
/*      */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*      */ import xyz.pixelatedw.mineminenomi.api.protection.ProtectedArea;
/*      */ import xyz.pixelatedw.mineminenomi.api.util.PriorityEventPool;
/*      */ import xyz.pixelatedw.mineminenomi.commands.FGCommand;
/*      */ import xyz.pixelatedw.mineminenomi.config.AbilitiesConfig;
/*      */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*      */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*      */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*      */ import xyz.pixelatedw.mineminenomi.data.entity.gcd.GCDCapability;
/*      */ import xyz.pixelatedw.mineminenomi.data.world.ProtectedAreasData;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*      */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SAnimeScreamPacket;
/*      */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*      */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*      */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*      */ import xyz.pixelatedw.mineminenomi.wypi.WyPatreon;
/*      */ 
/*      */ 
/*      */ public abstract class Ability
/*      */   implements IAbility
/*      */ {
/*      */   private ITextComponent displayName;
/*      */   private ResourceLocation displayIcon;
/*      */   private ResourceLocation cachedIcon;
/*      */   @Deprecated
/*   72 */   protected double maxDisableTicks = 200.0D; @Deprecated protected double cooldown; @Deprecated
/*      */   protected double maxCooldown; @Deprecated
/*   74 */   protected double disableTicks; private AbilityUnlock unlock = AbilityUnlock.PROGRESSION;
/*   75 */   private State state = State.STANDBY;
/*   76 */   private State previousState = State.STANDBY; @Deprecated
/*      */   private boolean forcedState = false;
/*      */   @Deprecated
/*      */   private boolean needsClientSide = false;
/*      */   @Deprecated
/*   81 */   private int[] pools = new int[0];
/*      */   @Deprecated
/*   83 */   protected double timeProgression = 1.0D;
/*      */   
/*      */   private final AbilityCore<? extends IAbility> core;
/*      */   
/*      */   private long lastUseTime;
/*      */   
/*      */   @Deprecated
/*      */   public boolean isNew = false;
/*      */   
/*      */   private boolean isOGCD;
/*      */   
/*   94 */   protected final DisableComponent disableComponent = new DisableComponent(this);
/*   95 */   protected final CooldownComponent cooldownComponent = new CooldownComponent(this);
/*   96 */   protected final AnimeScreamComponent screamComponent = new AnimeScreamComponent(this);
/*      */   
/*   98 */   private Map<AbilityComponentKey<?>, AbilityComponent<?>> components = new LinkedHashMap<>();
/*      */   
/*  100 */   protected final Random random = new Random(); @Deprecated
/*      */   protected IOnUse onUseEvent = player -> true;
/*      */   @Deprecated
/*      */   protected IDuringCooldown duringCooldownEvent = (player, cooldown) -> {
/*      */     
/*      */     };
/*      */   @Deprecated
/*      */   protected IOnEndCooldown onEndCooldownEvent = player -> {
/*      */     
/*      */     };
/*  110 */   private List<ICanUseEvent> onCanUseEvents = new ArrayList<>();
/*  111 */   private final PriorityEventPool<IOnUse2Event> onUseEvents = new PriorityEventPool();
/*  112 */   private List<IOnTickEvent> onTickEvents = new ArrayList<>();
/*  113 */   private List<IOnEquipEvent> onEquipEvents = new ArrayList<>();
/*  114 */   private List<IOnRemoveEvent> onRemoveEvents = new ArrayList<>();
/*      */   
/*      */   public Ability(AbilityCore<? extends IAbility> core) {
/*  117 */     this.core = core;
/*      */ 
/*      */     
/*  120 */     if (isClientSide()) {
/*  121 */       SlotDecorationComponent slotDecorationComponent = new SlotDecorationComponent(this);
/*  122 */       addComponents(new AbilityComponent[] { (AbilityComponent)slotDecorationComponent });
/*      */     } 
/*  124 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.cooldownComponent, (AbilityComponent)this.disableComponent, (AbilityComponent)this.screamComponent });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void use(PlayerEntity player) {
/*  134 */     if (this.isNew) {
/*  135 */       use((LivingEntity)player);
/*      */       
/*      */       return;
/*      */     } 
/*  139 */     if (player.field_70170_p.field_72995_K) {
/*      */       return;
/*      */     }
/*  142 */     if (isOnCooldown() && getCooldown() <= 10.0D) {
/*  143 */       stopCooldown(player);
/*      */     }
/*  145 */     if (!isOnStandby()) {
/*      */       return;
/*      */     }
/*  148 */     AbilityUseEvent.Pre pre = new AbilityUseEvent.Pre((LivingEntity)player, this);
/*  149 */     if (MinecraftForge.EVENT_BUS.post((Event)pre)) {
/*      */       return;
/*      */     }
/*  152 */     if (!isStateForced() && this.onUseEvent.onUse(player)) {
/*      */       
/*  154 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*  155 */       checkAbilityPool(player, State.COOLDOWN);
/*      */       
/*  157 */       if (((Boolean)CommonConfig.INSTANCE.animeScreaming.get()).booleanValue()) {
/*  158 */         WyNetwork.sendToAllTrackingAndSelf(new SAnimeScreamPacket(player.func_145782_y(), getDisplayNameOld()), (Entity)player);
/*      */       }
/*  160 */       AbilityUseEvent.Post post = new AbilityUseEvent.Post((LivingEntity)player, this);
/*  161 */       MinecraftForge.EVENT_BUS.post((Event)post);
/*      */       
/*  163 */       startCooldown(player);
/*  164 */       props.setPreviouslyUsedAbility(this);
/*  165 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket((LivingEntity)player, this), (Entity)player);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public final void use(LivingEntity entity) {
/*  171 */     AbilityUseResult result = canUse(entity);
/*  172 */     if (result.isFail()) {
/*  173 */       if (result.getMessage() != null) {
/*  174 */         entity.func_145747_a(result.getMessage(), Util.field_240973_b_);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  180 */     AbilityUseEvent.Pre pre = new AbilityUseEvent.Pre(entity, this);
/*  181 */     if (MinecraftForge.EVENT_BUS.post((Event)pre)) {
/*      */       return;
/*      */     }
/*      */     
/*  185 */     this.onUseEvents.dispatch(event -> event.onUse(entity, this));
/*  186 */     this.lastUseTime = entity.field_70170_p.func_82737_E();
/*  187 */     IAbilityData props = AbilityDataCapability.get(entity);
/*      */     
/*  189 */     AbilityUseEvent.Post post = new AbilityUseEvent.Post(entity, this);
/*  190 */     MinecraftForge.EVENT_BUS.post((Event)post);
/*      */     
/*  192 */     props.setPreviouslyUsedAbility(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onEquip(LivingEntity entity) {
/*  210 */     if (entity == null) {
/*      */       return;
/*      */     }
/*  213 */     this.onEquipEvents.forEach(event -> event.onEquip(entity, this));
/*      */   }
/*      */ 
/*      */   
/*      */   public void onRemove(LivingEntity entity) {
/*  218 */     getComponent(ModAbilityKeys.CONTINUOUS).ifPresent(comp -> comp.stopContinuity(entity));
/*      */ 
/*      */ 
/*      */     
/*  222 */     getComponent(ModAbilityKeys.MORPH).ifPresent(comp -> comp.stopMorph(entity));
/*      */ 
/*      */ 
/*      */     
/*  226 */     getComponent(ModAbilityKeys.CHANGE_STATS).ifPresent(comp -> comp.removeModifiers(entity));
/*      */ 
/*      */ 
/*      */     
/*  230 */     this.onRemoveEvents.forEach(event -> event.onRemove(entity, this));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addEquipEvent(IOnEquipEvent event) {
/*  238 */     this.onEquipEvents.add(event);
/*      */   }
/*      */   
/*      */   public void addRemoveEvent(IOnRemoveEvent event) {
/*  242 */     this.onRemoveEvents.add(event);
/*      */   }
/*      */   
/*      */   public <A extends IAbility, E extends LivingEntity> void addUseEvent(IOnUse2Event<E, A> event) {
/*  246 */     addUseEvent(100, event);
/*      */   }
/*      */   
/*      */   public <A extends IAbility, E extends LivingEntity> void addUseEvent(int priority, IOnUse2Event<E, A> event) {
/*  250 */     this.onUseEvents.addEvent(priority, event);
/*      */   }
/*      */   
/*      */   public boolean hasUseEvent(IOnUse2Event event) {
/*  254 */     return this.onUseEvents.getEventsStream().anyMatch(e -> e.equals(event));
/*      */   }
/*      */   
/*      */   public void removeUseEvent(IOnUse2Event event) {
/*  258 */     this.onUseEvents.removeEvent(event);
/*      */   }
/*      */   
/*      */   public <T extends LivingEntity, A extends Ability> void addTickEvent(IOnTickEvent<T, A> event) {
/*  262 */     this.onTickEvents.add(event);
/*      */   }
/*      */   
/*      */   public void addCanUseCheck(ICanUseEvent event) {
/*  266 */     this.onCanUseEvents.add(event);
/*      */   }
/*      */   
/*      */   public Stream<ICanUseEvent> getCanUseChecksStream() {
/*  270 */     return this.onCanUseEvents.stream();
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean isOnStandby() {
/*  276 */     return (this.state == State.STANDBY);
/*      */   }
/*      */   
/*      */   public boolean isOnCooldown() {
/*  280 */     Optional<CooldownComponent> cooldownComponent = getComponent(ModAbilityKeys.COOLDOWN);
/*  281 */     return (this.state == State.COOLDOWN || (cooldownComponent.isPresent() && ((CooldownComponent)cooldownComponent.get()).isOnCooldown()));
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean isPassiveEnabled() {
/*  287 */     return (this.state == State.PASSIVE);
/*      */   }
/*      */   
/*      */   public boolean isContinuous() {
/*  291 */     Optional<ContinuousComponent> continuousComponent = getComponent(ModAbilityKeys.CONTINUOUS);
/*  292 */     return ((this.state == State.CONTINUOUS && !isStateForced()) || (continuousComponent.isPresent() && ((ContinuousComponent)continuousComponent.get()).isContinuous()));
/*      */   }
/*      */   
/*      */   public boolean isCharging() {
/*  296 */     Optional<ChargeComponent> chargeComponent = getComponent(ModAbilityKeys.CHARGE);
/*  297 */     return ((this.state == State.CHARGING && !isStateForced()) || (chargeComponent.isPresent() && ((ChargeComponent)chargeComponent.get()).isCharging()));
/*      */   }
/*      */   
/*      */   public boolean isDisabled() {
/*  301 */     Optional<DisableComponent> disableComponent = getComponent(ModAbilityKeys.DISABLE);
/*  302 */     return (this.state == State.DISABLED || (disableComponent.isPresent() && ((DisableComponent)disableComponent.get()).isDisabled()));
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void startStandby() {
/*  308 */     this.previousState = this.state;
/*  309 */     this.state = State.STANDBY;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void startDisable(PlayerEntity player) {
/*  315 */     startDisable(player, 20);
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void startDisable(PlayerEntity player, int ticks) {
/*  321 */     this.previousState = this.state;
/*  322 */     this.state = State.DISABLED;
/*  323 */     this.maxDisableTicks = ticks;
/*  324 */     this.disableTicks = this.maxDisableTicks;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void stopDisable(PlayerEntity player) {
/*  330 */     this.cooldown = this.maxCooldown;
/*  331 */     startCooldown(player);
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public double getDisableTicks() {
/*  337 */     return this.disableTicks;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void setDisableTicks(double ticks) {
/*  343 */     this.disableTicks = ticks;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void startCooldown(PlayerEntity player) {
/*  349 */     this.previousState = this.state;
/*  350 */     this.state = State.COOLDOWN;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void stopCooldown(PlayerEntity player) {
/*  356 */     if (player.field_70170_p.field_72995_K) {
/*      */       return;
/*      */     }
/*  359 */     checkAbilityPool(player, State.STANDBY);
/*      */     
/*  361 */     this.cooldown = this.maxCooldown;
/*  362 */     this.previousState = this.state;
/*  363 */     this.state = State.STANDBY;
/*  364 */     if (!isStateForced()) {
/*      */       
/*  366 */       this.onEndCooldownEvent.onEndCooldown(player);
/*  367 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket((LivingEntity)player, this), (Entity)player);
/*      */     } 
/*  369 */     setForcedState(false);
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void setState(State state) {
/*  375 */     this.previousState = this.state;
/*  376 */     this.state = state;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public State getState() {
/*  382 */     return this.state;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public State getPreviousState() {
/*  388 */     return this.previousState;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void setForcedState(boolean flag) {
/*  394 */     this.forcedState = flag;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean isStateForced() {
/*  400 */     return this.forcedState;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void needsClientSide() {
/*  406 */     this.needsClientSide = true;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean isClientSideLegacy() {
/*  412 */     return this.needsClientSide;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void addInPool(AbilityPool... pools) {
/*  418 */     int[] intPools = Arrays.<AbilityPool>stream(pools).mapToInt(AbilityPool::id).toArray();
/*  419 */     addInPool(intPools);
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void addInPool(int... pools) {
/*  425 */     this.pools = pools;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public int[] getPools() {
/*  431 */     return this.pools;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean isInPool() {
/*  437 */     return ((Boolean)AbilitiesConfig.SHARED_COOLDOWNS.get()).booleanValue() ? ((this.pools != null && this.pools.length > 0)) : false;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean isInPool(AbilityPool pool) {
/*  443 */     return isInPool(pool.id());
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean isInPool(int poolId) {
/*  449 */     if (!((Boolean)AbilitiesConfig.SHARED_COOLDOWNS.get()).booleanValue()) {
/*  450 */       return false;
/*      */     }
/*  452 */     return Arrays.stream(this.pools).anyMatch(i -> (poolId == i));
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean sharesPoolWith(Ability ability) {
/*  458 */     if (!((Boolean)AbilitiesConfig.SHARED_COOLDOWNS.get()).booleanValue()) {
/*  459 */       return false;
/*      */     }
/*  461 */     boolean flag = false;
/*  462 */     for (int pool : ability.getPools()) {
/*      */       
/*  464 */       if (isInPool(pool)) {
/*      */         
/*  466 */         flag = true;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*  471 */     return flag;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void setMaxCooldown(double cooldown) {
/*  482 */     this.maxCooldown = cooldown * 20.0D;
/*  483 */     this.cooldown = this.maxCooldown;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public double getMaxCooldown() {
/*  489 */     return this.maxCooldown;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void setCooldown(double cooldown) {
/*  495 */     this.cooldown = cooldown * 20.0D;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public double getCooldown() {
/*  501 */     return this.cooldown;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void setTimeProgression(double timeScale) {
/*  507 */     this.timeProgression = timeScale;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public double getTimeProgression() {
/*  513 */     return this.timeProgression;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public String getName() {
/*  519 */     return getCore().getLocalizedName().getString();
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public String getI18nKey() {
/*  525 */     String resourceName = WyHelper.getResourceName(getName());
/*  526 */     return "ability.mineminenomi." + resourceName;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getDisplayNameOld() {
/*  531 */     return (this.displayName != null) ? this.displayName.getString() : getCore().getLocalizedName().getString();
/*      */   }
/*      */   
/*      */   public boolean hasCustomName() {
/*  535 */     return (this.displayName != null);
/*      */   }
/*      */ 
/*      */   
/*      */   public ITextComponent getDisplayName() {
/*  540 */     if (this.displayName != null) {
/*  541 */       return this.displayName;
/*      */     }
/*  543 */     return (ITextComponent)getCore().getLocalizedName();
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void setDisplayName(String name) {
/*  549 */     setDisplayName((ITextComponent)new StringTextComponent(name));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDisplayName(ITextComponent name) {
/*  554 */     this.displayName = name;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasCustomIcon() {
/*  559 */     return (this.displayIcon != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public ResourceLocation getIcon(PlayerEntity player) {
/*  567 */     if (this.isNew) {
/*  568 */       return getIcon((LivingEntity)player);
/*      */     }
/*  570 */     return hasCustomIcon() ? this.displayIcon : getCore().getIcon();
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public ResourceLocation getIcon(LivingEntity entity) {
/*  576 */     if (this.core.getIcon() == null && this.cachedIcon == null) {
/*  577 */       ResourceLocation key = this.core.getRegistryName();
/*  578 */       if (key != null) {
/*  579 */         this.cachedIcon = new ResourceLocation(key.func_110624_b(), "textures/abilities/" + key.func_110623_a() + ".png");
/*      */       }
/*  581 */       return this.cachedIcon;
/*      */     } 
/*      */     
/*  584 */     if (hasCustomIcon()) {
/*  585 */       return this.displayIcon;
/*      */     }
/*  587 */     if (getCore().getIcon() != null) {
/*  588 */       return getCore().getIcon();
/*      */     }
/*      */     
/*  591 */     return this.cachedIcon;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDisplayIcon(@Nullable ResourceLocation texture) {
/*  596 */     this.displayIcon = texture;
/*      */   }
/*      */   
/*      */   public void setDisplayIcon(AbilityCore<?> core) {
/*  600 */     this.displayIcon = core.getIcon();
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void setCustomIcon(String texture) {
/*  606 */     if (Strings.isNullOrEmpty(texture)) {
/*  607 */       this.displayIcon = null;
/*      */     } else {
/*  609 */       this.displayIcon = new ResourceLocation(getCore().getIcon().func_110624_b(), "textures/abilities/" + WyHelper.getResourceName(texture) + ".png");
/*      */     } 
/*      */   }
/*      */   public AbilityCategory getCategory() {
/*  613 */     return getCore().getCategory();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void setUnlockType(@Nullable LivingEntity entity, AbilityUnlock unlockType) {}
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public AbilityUnlock getUnlockType() {
/*  624 */     return this.unlock;
/*      */   }
/*      */   
/*      */   public SourceHakiNature getSourceHakiNature() {
/*  628 */     return getCore().getSourceHakiNature();
/*      */   }
/*      */   
/*      */   public ArrayList<SourceType> getSourceTypes() {
/*  632 */     return getCore().getSourceTypes();
/*      */   }
/*      */   
/*      */   public boolean hasSourceTypes(SourceType type) {
/*  636 */     return getCore().hasType(type);
/*      */   }
/*      */   
/*      */   public SourceElement getSourceElement() {
/*  640 */     return getCore().getSourceElement();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public <T extends LivingEntity> void setOnUseEvent(IOnUse<T> event) {
/*  650 */     this.onUseEvent = event;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void tick(PlayerEntity player) {
/*  660 */     if (this.isNew) {
/*  661 */       tick((LivingEntity)player);
/*      */       
/*      */       return;
/*      */     } 
/*  665 */     if (player.func_175149_v()) {
/*  666 */       startDisable(player, 1);
/*      */     }
/*      */     
/*  669 */     if (isDisabled())
/*      */     {
/*  671 */       if (this.disableTicks > 0.0D) {
/*  672 */         this.disableTicks--;
/*      */       } else {
/*  674 */         stopDisable(player);
/*      */       } 
/*      */     }
/*  677 */     if (isOnCooldown())
/*      */     {
/*  679 */       cooldown(player);
/*      */     }
/*      */     
/*  682 */     AbilityTickEvent event = new AbilityTickEvent((LivingEntity)player, this);
/*  683 */     MinecraftForge.EVENT_BUS.post((Event)event);
/*      */   }
/*      */ 
/*      */   
/*      */   public final void tick(LivingEntity entity) {
/*  688 */     if (entity instanceof PlayerEntity && !this.isNew) {
/*  689 */       tick((PlayerEntity)entity);
/*      */       
/*      */       return;
/*      */     } 
/*  693 */     if (!entity.field_70170_p.field_72995_K && 
/*  694 */       isDisabled()) {
/*  695 */       AbilityHelper.emergencyStopAbility(entity, this);
/*      */     }
/*      */ 
/*      */     
/*  699 */     this.onTickEvents.forEach(event -> event.tick(entity, this));
/*  700 */     this.components.values().forEach(component -> component.tick(entity));
/*  701 */     AbilityTickEvent event = new AbilityTickEvent(entity, this);
/*  702 */     MinecraftForge.EVENT_BUS.post((Event)event);
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void cooldown(PlayerEntity player) {
/*  708 */     if (WyPatreon.isDevBuild() && FGCommand.NO_COOLDOWN)
/*      */     {
/*  710 */       stopCooldown(player);
/*      */     }
/*      */     
/*  713 */     if (isOnCooldown() && this.cooldown > 0.0D) {
/*      */       
/*  715 */       this.cooldown -= 1.0D * getTimeProgression();
/*  716 */       if (!player.field_70170_p.field_72995_K && getPreviousState() != State.DISABLED && !isStateForced()) {
/*  717 */         tickCooldown(player);
/*      */       }
/*  719 */     } else if (isOnCooldown() && this.cooldown <= 0.0D) {
/*      */       
/*  721 */       stopCooldown(player);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   protected void tickCooldown(PlayerEntity player) {
/*  728 */     this.duringCooldownEvent.duringCooldown(player, (int)this.cooldown);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void checkAbilityPool(PlayerEntity player, State state) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getLastUseGametime() {
/*  768 */     return this.lastUseTime;
/*      */   }
/*      */   
/*      */   public void setOGCD() {
/*  772 */     this.isOGCD = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isOGCD() {
/*  777 */     return this.isOGCD;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public double getCooldownPercentage() {
/*  786 */     return this.cooldown / this.maxCooldown * 100.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public double getInvertedCooldownPercentage() {
/*  795 */     return (1.0D - this.cooldown / this.maxCooldown) * 100.0D;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean equals(Object abl) {
/*  800 */     if (!(abl instanceof Ability)) {
/*  801 */       return false;
/*      */     }
/*      */     
/*  804 */     if (getCore() == null || ((Ability)abl).getCore() == null) {
/*  805 */       return false;
/*      */     }
/*      */     
/*  808 */     if (getCore().equals(((Ability)abl).getCore())) {
/*  809 */       return true;
/*      */     }
/*      */     
/*  812 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public CompoundNBT save(CompoundNBT nbt) {
/*  817 */     if (this.isNew) {
/*  818 */       if (hasCustomName()) {
/*  819 */         nbt.func_74778_a("displayName", ITextComponent.Serializer.func_150696_a(getDisplayName()));
/*      */       }
/*      */       
/*  822 */       if (hasCustomIcon()) {
/*  823 */         nbt.func_74778_a("displayIcon", this.displayIcon.toString());
/*      */       }
/*      */       
/*  826 */       return nbt;
/*      */     } 
/*      */     
/*  829 */     nbt.func_74778_a("displayName", Strings.isNullOrEmpty(getDisplayNameOld()) ? "" : getDisplayNameOld());
/*  830 */     nbt.func_74778_a("customTexture", hasCustomIcon() ? this.displayIcon.toString() : "");
/*  831 */     nbt.func_74783_a("pools", getPools());
/*  832 */     nbt.func_74778_a("unlock", getUnlockType().name());
/*  833 */     nbt.func_74778_a("state", getState().toString());
/*      */     
/*  835 */     if (this instanceof IExtraUpdateData) {
/*  836 */       CompoundNBT extraData = ((IExtraUpdateData)this).getExtraData();
/*  837 */       nbt.func_218657_a("extraData", (INBT)extraData);
/*      */     } 
/*      */     
/*  840 */     return nbt;
/*      */   }
/*      */ 
/*      */   
/*      */   public void load(CompoundNBT nbt) {
/*  845 */     if (this.isNew) {
/*  846 */       if (nbt.func_74764_b("displayName")) {
/*  847 */         setDisplayName((ITextComponent)ITextComponent.Serializer.func_240643_a_(nbt.func_74779_i("displayName")));
/*      */       }
/*      */       
/*  850 */       if (nbt.func_74764_b("displayIcon")) {
/*  851 */         setDisplayIcon(new ResourceLocation(nbt.func_74779_i("displayIcon")));
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  857 */     addInPool(nbt.func_74759_k("pools"));
/*  858 */     setUnlockType(null, AbilityUnlock.valueOf(nbt.func_74779_i("unlock")));
/*  859 */     setDisplayName(nbt.func_74779_i("displayName"));
/*  860 */     String customTexture = nbt.func_74779_i("customTexture");
/*  861 */     if (!Strings.isNullOrEmpty(customTexture))
/*  862 */       setDisplayIcon(new ResourceLocation(customTexture)); 
/*  863 */     setState(State.valueOf(nbt.func_74779_i("state")));
/*      */     
/*  865 */     if (this instanceof IExtraUpdateData) {
/*  866 */       CompoundNBT extraData = nbt.func_74775_l("extraData");
/*  867 */       ((IExtraUpdateData)this).setExtraData(extraData);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public AbilityCore<? extends IAbility> getCore() {
/*  873 */     return this.core;
/*      */   }
/*      */   
/*      */   public void addComponents(AbilityComponent... comps) {
/*  877 */     for (AbilityComponent<?> comp : comps) {
/*  878 */       this.components.put(comp.getKey(), comp);
/*      */     }
/*      */ 
/*      */     
/*  882 */     if (FMLEnvironment.dist.isClient()) {
/*  883 */       this.components.values().stream().forEach(c -> c.postInit(this));
/*      */     } else {
/*      */       
/*  886 */       this.components.values().stream().filter(comp -> !comp.isClientSided()).forEach(c -> c.postInit(this));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasComponent(AbilityComponentKey key) {
/*  892 */     return this.components.containsKey(key);
/*      */   }
/*      */ 
/*      */   
/*      */   public <C extends AbilityComponent<?>> Optional<C> getComponent(AbilityComponentKey<C> key) {
/*  897 */     if (!hasComponent(key)) {
/*  898 */       return Optional.empty();
/*      */     }
/*  900 */     AbilityComponent abilityComponent = this.components.get(key);
/*      */ 
/*      */     
/*  903 */     if (abilityComponent.isClientSided() && !FMLEnvironment.dist.isClient()) {
/*  904 */       return Optional.empty();
/*      */     }
/*  906 */     return Optional.of((C)abilityComponent);
/*      */   }
/*      */ 
/*      */   
/*      */   public Map<AbilityComponentKey<?>, AbilityComponent<?>> getComponents() {
/*  911 */     return this.components;
/*      */   }
/*      */ 
/*      */   
/*      */   public AbilityUseResult canUse(PlayerEntity player) {
/*  916 */     if (this.isNew) {
/*  917 */       return canUse((LivingEntity)player);
/*      */     }
/*      */     
/*  920 */     if (isDisabled()) {
/*  921 */       return AbilityUseResult.fail(null);
/*      */     }
/*      */     
/*  924 */     if (!player.field_70170_p.field_72995_K) {
/*  925 */       ProtectedAreasData worldData = ProtectedAreasData.get(player.field_70170_p);
/*      */ 
/*      */       
/*  928 */       ProtectedArea area = worldData.getProtectedArea(player.func_233580_cy_().func_177958_n(), player.func_233580_cy_().func_177956_o(), player.func_233580_cy_().func_177952_p());
/*  929 */       if (area != null && !area.canUseAbility(getCore())) {
/*  930 */         return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_CANNOT_USE_HERE));
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  955 */     return AbilityUseResult.success();
/*      */   }
/*      */ 
/*      */   
/*      */   public final AbilityUseResult canUse(LivingEntity entity) {
/*  960 */     if (entity.func_70644_a((Effect)ModEffects.IN_EVENT.get())) {
/*  961 */       return AbilityUseResult.fail(null);
/*      */     }
/*      */     
/*  964 */     if (!this.isOGCD && GCDCapability.isOnGCD(entity)) {
/*  965 */       return AbilityUseResult.fail(null);
/*      */     }
/*      */     
/*  968 */     if (this.disableComponent.isDisabled()) {
/*  969 */       return AbilityUseResult.fail(null);
/*      */     }
/*      */     
/*  972 */     if (this.cooldownComponent.isOnCooldown()) {
/*  973 */       return AbilityUseResult.fail(null);
/*      */     }
/*      */     
/*  976 */     Optional<PoolComponent> poolComponent = getComponent(ModAbilityKeys.POOL);
/*  977 */     if (poolComponent.isPresent() && ((PoolComponent)poolComponent.get()).isPoolInUse()) {
/*  978 */       return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_POOL_ALREADY_IN_USE));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  988 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*  989 */     Optional<ContinuousComponent> continuousComponent = getComponent(ModAbilityKeys.CONTINUOUS);
/*  990 */     if (continuousComponent.isPresent() && !((ContinuousComponent)continuousComponent.get()).isContinuous() && !((ContinuousComponent)continuousComponent.get()).isParallel()) {
/*  991 */       for (IAbility ability : abilityDataProps.getEquippedAbilities()) {
/*  992 */         if (ability.equals(this)) {
/*      */           continue;
/*      */         }
/*  995 */         Optional<ContinuousComponent> otherComponent = ability.getComponent(ModAbilityKeys.CONTINUOUS);
/*  996 */         if (otherComponent.isPresent() && ((ContinuousComponent)otherComponent.get()).isContinuous() && !((ContinuousComponent)otherComponent.get()).isParallel()) {
/*  997 */           if (CommonConfig.INSTANCE.getStopContinuousAbility() && hasComponent(ModAbilityKeys.CONTINUOUS)) {
/*  998 */             ((ContinuousComponent)otherComponent.get()).stopContinuity(entity);
/*      */             continue;
/*      */           } 
/* 1001 */           return AbilityUseResult.fail(null);
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1008 */     if (continuousComponent.isPresent() && ((ContinuousComponent)continuousComponent.get()).isContinuous()) {
/* 1009 */       return AbilityUseResult.success();
/*      */     }
/*      */     
/* 1012 */     Optional<ChargeComponent> chargeComponent = getComponent(ModAbilityKeys.CHARGE);
/* 1013 */     if (chargeComponent.isPresent() && ((ChargeComponent)chargeComponent.get()).isCharging()) {
/* 1014 */       return AbilityUseResult.success();
/*      */     }
/*      */     
/* 1017 */     Optional<AbilityUseResult> canUseResult = this.onCanUseEvents.stream().map(event -> event.canUse(entity, this)).filter(result -> result.isFail()).findFirst();
/* 1018 */     if (canUseResult.isPresent()) {
/* 1019 */       return canUseResult.get();
/*      */     }
/*      */     
/* 1022 */     if (!entity.field_70170_p.field_72995_K) {
/* 1023 */       ProtectedAreasData worldData = ProtectedAreasData.get(entity.field_70170_p);
/*      */ 
/*      */       
/* 1026 */       ProtectedArea area = worldData.getProtectedArea(entity.func_233580_cy_().func_177958_n(), entity.func_233580_cy_().func_177956_o(), entity.func_233580_cy_().func_177952_p());
/* 1027 */       if (area != null && !area.canUseAbility(getCore())) {
/* 1028 */         return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_CANNOT_USE_HERE));
/*      */       }
/*      */     } 
/*      */     
/* 1032 */     return AbilityUseResult.success();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public enum State
/*      */   {
/* 1042 */     STANDBY, DISABLED,
/*      */     
/* 1044 */     COOLDOWN, PASSIVE, CONTINUOUS, CHARGING;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static interface IFactory<A extends Ability>
/*      */   {
/*      */     A create(AbilityCore<A> param1AbilityCore);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static interface IOnUse<T extends LivingEntity>
/*      */     extends Serializable
/*      */   {
/*      */     boolean onUse(T param1T);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static interface IDuringCooldown
/*      */     extends Serializable
/*      */   {
/*      */     void duringCooldown(PlayerEntity param1PlayerEntity, int param1Int);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isClientSide() {
/* 1076 */     return FMLEnvironment.dist.isClient();
/*      */   }
/*      */   @Deprecated
/*      */   public static interface IOnEndCooldown extends Serializable {
/*      */     void onEndCooldown(PlayerEntity param1PlayerEntity); }
/*      */   
/*      */   @FunctionalInterface
/*      */   public static interface ICanUseEvent<T extends LivingEntity, A extends IAbility> { default ICanUseEvent<T, A> and(ICanUseEvent<T, A> check) {
/* 1084 */       return (entity, ability) -> {
/*      */           AbilityUseResult result1 = canUse((T)entity, (A)ability);
/*      */           if (result1.isFail()) {
/*      */             return result1;
/*      */           }
/*      */           AbilityUseResult result2 = check.canUse(entity, ability);
/*      */           return result2.isFail() ? result2 : AbilityUseResult.success();
/*      */         };
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     default ICanUseEvent<T, A> or(ICanUseEvent<T, A> check) {
/* 1098 */       return (entity, ability) -> {
/*      */           AbilityUseResult result1 = canUse((T)entity, (A)ability);
/*      */           AbilityUseResult result2 = check.canUse(entity, ability);
/* 1101 */           return (result1.isSuccess() || result2.isSuccess()) ? AbilityUseResult.success() : (result1.isFail() ? result1 : result2);
/*      */         };
/*      */     }
/*      */     
/*      */     default ICanUseEvent<T, A> not() {
/* 1106 */       return (entity, ability) -> {
/*      */           AbilityUseResult result = canUse((T)entity, (A)ability);
/*      */           return result.isSuccess() ? AbilityUseResult.fail(null) : AbilityUseResult.success();
/*      */         };
/*      */     }
/*      */     
/*      */     AbilityUseResult canUse(T param1T, A param1A); }
/*      */ 
/*      */   
/*      */   @FunctionalInterface
/*      */   public static interface IOnUse2Event<T extends LivingEntity, A extends IAbility> {
/*      */     void onUse(T param1T, A param1A);
/*      */   }
/*      */   
/*      */   @FunctionalInterface
/*      */   public static interface IOnTickEvent<T extends LivingEntity, A extends IAbility> {
/*      */     void tick(T param1T, A param1A);
/*      */   }
/*      */   
/*      */   @FunctionalInterface
/*      */   public static interface IOnEquipEvent {
/*      */     void onEquip(LivingEntity param1LivingEntity, Ability param1Ability);
/*      */   }
/*      */   
/*      */   @FunctionalInterface
/*      */   public static interface IOnRemoveEvent {
/*      */     void onRemove(LivingEntity param1LivingEntity, Ability param1Ability);
/*      */   }
/*      */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\Ability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */