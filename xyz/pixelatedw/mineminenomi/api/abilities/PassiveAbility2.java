/*     */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraftforge.fml.loading.FMLEnvironment;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponentKey;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DisableComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PauseTickComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.ProtectedArea;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ProtectedAreasData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class PassiveAbility2
/*     */   implements IAbility
/*     */ {
/*     */   private final AbilityCore<? extends IAbility> core;
/*     */   private ITextComponent displayName;
/*     */   private ResourceLocation displayIcon;
/*     */   private ResourceLocation cachedIcon;
/*  34 */   private float tickRate = 1.0F;
/*  35 */   private float currentTick = 0.0F;
/*     */   
/*  37 */   protected final DisableComponent disableComponent = new DisableComponent(this);
/*  38 */   protected final CooldownComponent cooldownComponent = new CooldownComponent(this);
/*  39 */   protected final PauseTickComponent pauseTickComponent = new PauseTickComponent(this);
/*     */   
/*  41 */   private List<Ability.ICanUseEvent> onCanUseEvents = new ArrayList<>();
/*  42 */   private List<IDuringPassiveEvent> duringPassiveEvents = new ArrayList<>();
/*  43 */   private List<IOnEquipEvent> onEquipEvents = new ArrayList<>();
/*  44 */   private List<IOnRemoveEvent> onRemoveEvents = new ArrayList<>();
/*     */   
/*  46 */   private Map<AbilityComponentKey<?>, AbilityComponent<?>> components = new LinkedHashMap<>();
/*     */   
/*  48 */   protected final Random random = new Random();
/*     */ 
/*     */   
/*     */   public PassiveAbility2(AbilityCore<?> core) {
/*  52 */     this.core = (AbilityCore)core;
/*     */ 
/*     */     
/*  55 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.cooldownComponent, (AbilityComponent)this.disableComponent, (AbilityComponent)this.pauseTickComponent });
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEquip(LivingEntity entity) {
/*  60 */     this.onEquipEvents.forEach(event -> event.onEquip(entity, this));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRemove(LivingEntity entity) {
/*  65 */     getComponent(ModAbilityKeys.CHANGE_STATS).ifPresent(comp -> comp.removeModifiers(entity));
/*     */ 
/*     */ 
/*     */     
/*  69 */     this.onRemoveEvents.forEach(event -> event.onRemove(entity, this));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void use(LivingEntity entity) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick(LivingEntity entity) {
/*  79 */     this.components.values().forEach(component -> component.tick(entity));
/*     */     
/*  81 */     if (canUse(entity).isFail()) {
/*     */       return;
/*     */     }
/*     */     
/*  85 */     if (!entity.field_70170_p.field_72995_K) {
/*  86 */       ProtectedAreasData worldData = ProtectedAreasData.get(entity.field_70170_p);
/*     */ 
/*     */       
/*  89 */       ProtectedArea area = worldData.getProtectedArea(entity.func_233580_cy_().func_177958_n(), entity.func_233580_cy_().func_177956_o(), entity.func_233580_cy_().func_177952_p());
/*  90 */       if (area != null && !area.canUseAbility(getCore())) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */     
/*  95 */     if (--this.currentTick > 0.0F) {
/*     */       return;
/*     */     }
/*     */     
/*  99 */     this.currentTick = this.tickRate;
/* 100 */     this.duringPassiveEvents.forEach(event -> event.duringPassive(entity));
/*     */   }
/*     */ 
/*     */   
/*     */   public AbilityUseResult canUse(LivingEntity entity) {
/* 105 */     if (this.disableComponent.isDisabled() || this.pauseTickComponent.isPaused() || this.cooldownComponent.isOnCooldown()) {
/* 106 */       return AbilityUseResult.fail(null);
/*     */     }
/*     */     
/* 109 */     Optional<AbilityUseResult> canUseResult = this.onCanUseEvents.stream().map(event -> event.canUse(entity, this)).filter(result -> result.isFail()).findFirst();
/*     */     
/* 111 */     if (canUseResult.isPresent()) {
/* 112 */       return canUseResult.get();
/*     */     }
/*     */     
/* 115 */     return AbilityUseResult.success();
/*     */   }
/*     */   
/*     */   public void addEquipEvent(IOnEquipEvent event) {
/* 119 */     this.onEquipEvents.add(event);
/*     */   }
/*     */   
/*     */   public void addRemoveEvent(IOnRemoveEvent event) {
/* 123 */     this.onRemoveEvents.add(event);
/*     */   }
/*     */   
/*     */   public void addCanUseCheck(Ability.ICanUseEvent event) {
/* 127 */     this.onCanUseEvents.add(event);
/*     */   }
/*     */   
/*     */   public void addDuringPassiveEvent(IDuringPassiveEvent event) {
/* 131 */     this.duringPassiveEvents.add(event);
/*     */   }
/*     */   
/*     */   public void setTickRate(float tickRate) {
/* 135 */     this.tickRate = tickRate;
/* 136 */     this.currentTick = tickRate;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbilityCore<? extends IAbility> getCore() {
/* 141 */     return this.core;
/*     */   }
/*     */   
/*     */   public boolean hasCustomName() {
/* 145 */     return (this.displayName != null);
/*     */   }
/*     */   
/*     */   public void setDisplayName(ITextComponent name) {
/* 149 */     this.displayName = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public ITextComponent getDisplayName() {
/* 154 */     if (hasCustomName()) {
/* 155 */       return this.displayName;
/*     */     }
/* 157 */     return (ITextComponent)getCore().getLocalizedName();
/*     */   }
/*     */   
/*     */   public boolean hasCustomIcon() {
/* 161 */     return (this.displayIcon != null);
/*     */   }
/*     */   
/*     */   public void setDisplayIcon(@Nullable ResourceLocation texture) {
/* 165 */     this.displayIcon = texture;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getIcon(LivingEntity entity) {
/* 170 */     if (this.core.getIcon() == null && this.cachedIcon == null) {
/* 171 */       ResourceLocation key = this.core.getRegistryName();
/* 172 */       if (key != null) {
/* 173 */         this.cachedIcon = new ResourceLocation(key.func_110624_b(), "textures/abilities/" + key.func_110623_a() + ".png");
/*     */       }
/* 175 */       return this.cachedIcon;
/*     */     } 
/*     */     
/* 178 */     if (hasCustomIcon()) {
/* 179 */       return this.displayIcon;
/*     */     }
/* 181 */     if (getCore().getIcon() != null) {
/* 182 */       return getCore().getIcon();
/*     */     }
/*     */     
/* 185 */     return this.cachedIcon;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPaused() {
/* 190 */     return this.pauseTickComponent.isPaused();
/*     */   }
/*     */   
/*     */   public void addComponents(AbilityComponent... comps) {
/* 194 */     for (AbilityComponent<?> comp : comps) {
/* 195 */       this.components.put(comp.getKey(), comp);
/*     */     }
/*     */ 
/*     */     
/* 199 */     if (FMLEnvironment.dist.isClient()) {
/* 200 */       this.components.values().stream().forEach(c -> c.postInit(this));
/*     */     } else {
/*     */       
/* 203 */       this.components.values().stream().filter(comp -> !comp.isClientSided()).forEach(c -> c.postInit(this));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public <C extends AbilityComponent<?>> Optional<C> getComponent(AbilityComponentKey<C> key) {
/* 209 */     if (!hasComponent(key)) {
/* 210 */       return Optional.empty();
/*     */     }
/* 212 */     AbilityComponent abilityComponent = this.components.get(key);
/*     */ 
/*     */     
/* 215 */     if (abilityComponent.isClientSided() && !FMLEnvironment.dist.isClient()) {
/* 216 */       return Optional.empty();
/*     */     }
/* 218 */     return Optional.of((C)abilityComponent);
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<AbilityComponentKey<?>, AbilityComponent<?>> getComponents() {
/* 223 */     return this.components;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasComponent(AbilityComponentKey key) {
/* 228 */     return this.components.containsKey(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT save(CompoundNBT nbt) {
/* 233 */     if (hasCustomName()) {
/* 234 */       nbt.func_74778_a("displayName", ITextComponent.Serializer.func_150696_a(getDisplayName()));
/*     */     }
/*     */     
/* 237 */     if (hasCustomIcon()) {
/* 238 */       nbt.func_74778_a("displayIcon", this.displayIcon.toString());
/*     */     }
/*     */     
/* 241 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 246 */     if (nbt.func_74764_b("displayName")) {
/* 247 */       setDisplayName((ITextComponent)ITextComponent.Serializer.func_240643_a_(nbt.func_74779_i("displayName")));
/*     */     }
/*     */     
/* 250 */     if (nbt.func_74764_b("displayIcon")) {
/* 251 */       setDisplayIcon(new ResourceLocation(nbt.func_74779_i("displayIcon")));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isClientSide() {
/* 256 */     return FMLEnvironment.dist.isClient();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOGCD() {
/* 261 */     return true;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IOnRemoveEvent {
/*     */     void onRemove(LivingEntity param1LivingEntity, PassiveAbility2 param1PassiveAbility2);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IOnEquipEvent {
/*     */     void onEquip(LivingEntity param1LivingEntity, PassiveAbility2 param1PassiveAbility2);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IDuringPassiveEvent {
/*     */     void duringPassive(LivingEntity param1LivingEntity);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\PassiveAbility2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */