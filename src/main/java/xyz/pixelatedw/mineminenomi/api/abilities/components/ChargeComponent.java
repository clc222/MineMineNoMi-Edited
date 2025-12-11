/*     */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityStat;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.ProtectedArea;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.PriorityEventPool;
/*     */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ProtectedAreasData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.components.SStartChargingPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class ChargeComponent extends AbilityComponent<IAbility> {
/*  29 */   private static final UUID MAX_CHARGE_BONUS_MANAGER_UUID = UUID.fromString("75b893d9-9d02-457e-9c35-02e468586fcc");
/*     */   
/*  31 */   private static final TranslationTextComponent CHARGE_STAT = new TranslationTextComponent(ModI18n.ABILITY_DESCRIPTION_STAT_NAME_CHARGE); private boolean isCharging; private float maxChargeTime; private float chargeTime; private boolean isCancelable; private Predicate<ChargeComponent> isCancelableTest;
/*     */   
/*     */   public static AbilityDescriptionLine.IDescriptionLine getTooltip(float ticks) {
/*  34 */     return getTooltip(ticks, ticks);
/*     */   }
/*     */   
/*     */   public static AbilityDescriptionLine.IDescriptionLine getTooltip(float min, float max) {
/*  38 */     return (e, a) -> {
/*     */         AbilityStat.Builder statBuilder = (new AbilityStat.Builder((ITextComponent)CHARGE_STAT, Math.round(min / 20.0F), Math.round(max / 20.0F))).withUnit((ITextComponent)ModI18n.ABILITY_DESCRIPTION_STAT_UNIT_SECONDS);
/*     */         return statBuilder.build().getStatDescription();
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   private final PriorityEventPool<IStartChargingEvent> startChargeEvents = new PriorityEventPool();
/*  51 */   private final PriorityEventPool<IDuringChargingEvent> tickChargeEvents = new PriorityEventPool();
/*  52 */   private final PriorityEventPool<IEndChargingEvent> stopChargeEvents = new PriorityEventPool();
/*     */   
/*  54 */   private final BonusManager maxChargeBonusManager = new BonusManager(MAX_CHARGE_BONUS_MANAGER_UUID);
/*     */   
/*     */   public ChargeComponent(IAbility ability) {
/*  57 */     this(ability, false);
/*  58 */     addBonusManager(this.maxChargeBonusManager);
/*     */   }
/*     */   
/*     */   public ChargeComponent(IAbility ability, boolean isCancelable) {
/*  62 */     super(ModAbilityKeys.CHARGE, ability);
/*  63 */     this.isCancelable = isCancelable;
/*  64 */     this.isCancelableTest = (comp -> true);
/*     */   }
/*     */   
/*     */   public ChargeComponent(IAbility ability, Predicate<ChargeComponent> isCancelableTest) {
/*  68 */     super(ModAbilityKeys.CHARGE, ability);
/*  69 */     this.isCancelable = true;
/*  70 */     this.isCancelableTest = isCancelableTest;
/*     */   }
/*     */ 
/*     */   
/*     */   public void postInit(IAbility ability) {
/*  75 */     ability.getComponent(ModAbilityKeys.SLOT_DECORATION).ifPresent(component -> component.addPreRenderEvent(201, ()));
/*     */   }
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
/*     */   protected void doTick(LivingEntity entity) {
/*  94 */     if (getAbility().hasComponent(ModAbilityKeys.DISABLE) && ((DisableComponent)getAbility().getComponent(ModAbilityKeys.DISABLE).get()).isDisabled()) {
/*     */       return;
/*     */     }
/*     */     
/*  98 */     getAbility().getComponent(ModAbilityKeys.ALT_MODE).ifPresent(component -> component.setDisabled(isCharging()));
/*     */ 
/*     */ 
/*     */     
/* 102 */     if (isCharging()) {
/* 103 */       if (entity.func_70644_a((Effect)ModEffects.IN_EVENT.get())) {
/* 104 */         forceStopCharging(entity);
/*     */         
/*     */         return;
/*     */       } 
/* 108 */       if (!entity.field_70170_p.field_72995_K) {
/* 109 */         ProtectedAreasData worldData = ProtectedAreasData.get(entity.field_70170_p);
/* 110 */         ProtectedArea area = worldData.getProtectedArea(entity.func_233580_cy_().func_177958_n(), entity.func_233580_cy_().func_177956_o(), entity.func_233580_cy_().func_177952_p());
/*     */         
/* 112 */         if (area != null && !area.canUseAbility(getAbility().getCore())) {
/* 113 */           forceStopCharging(entity);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 127 */       if (this.chargeTime >= this.maxChargeTime) {
/* 128 */         stopCharging(entity);
/*     */         
/*     */         return;
/*     */       } 
/* 132 */       ModifiableAttributeInstance inst = entity.func_110148_a((Attribute)ModAttributes.TIME_PROGRESSION.get());
/*     */       
/* 134 */       double timeProgression = 1.0D;
/*     */       
/* 136 */       if (inst != null) {
/* 137 */         timeProgression = inst.func_111126_e();
/*     */       }
/*     */       
/* 140 */       this.chargeTime = (float)(this.chargeTime + getTpsFactor() * timeProgression);
/*     */       
/* 142 */       int loops = Math.max(1, (int)getTpsFactor());
/* 143 */       for (int i = 0; i < loops; i++) {
/* 144 */         this.tickChargeEvents.dispatch(event -> event.duringCharging(entity, getAbility()));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public ChargeComponent addStartEvent(IStartChargingEvent event) {
/* 150 */     this.startChargeEvents.addEvent(event);
/* 151 */     return this;
/*     */   }
/*     */   
/*     */   public ChargeComponent addStartEvent(int priority, IStartChargingEvent event) {
/* 155 */     this.startChargeEvents.addEvent(priority, event);
/* 156 */     return this;
/*     */   }
/*     */   
/*     */   public ChargeComponent addTickEvent(IDuringChargingEvent event) {
/* 160 */     this.tickChargeEvents.addEvent(event);
/* 161 */     return this;
/*     */   }
/*     */   
/*     */   public ChargeComponent addTickEvent(int priority, IDuringChargingEvent event) {
/* 165 */     this.tickChargeEvents.addEvent(priority, event);
/* 166 */     return this;
/*     */   }
/*     */   
/*     */   public ChargeComponent addEndEvent(IEndChargingEvent event) {
/* 170 */     this.stopChargeEvents.addEvent(event);
/* 171 */     return this;
/*     */   }
/*     */   
/*     */   public ChargeComponent addEndEvent(int priority, IEndChargingEvent event) {
/* 175 */     this.stopChargeEvents.addEvent(priority, event);
/* 176 */     return this;
/*     */   }
/*     */   
/*     */   public void startCharging(LivingEntity user, float maxChargeTime) {
/* 180 */     ensureIsRegistered();
/* 181 */     if (isCharging()) {
/* 182 */       if (isCancelable() && this.isCancelableTest.test(this)) {
/* 183 */         stopCharging(user);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 189 */     Optional<CooldownComponent> cooldownComponent = getAbility().getComponent(ModAbilityKeys.COOLDOWN);
/*     */     
/* 191 */     if (cooldownComponent.isPresent())
/*     */     {
/* 193 */       if (((CooldownComponent)cooldownComponent.get()).isOnCooldown()) {
/*     */         return;
/*     */       }
/*     */     }
/* 197 */     Optional<DisableComponent> disableComponent = getAbility().getComponent(ModAbilityKeys.DISABLE);
/* 198 */     if (disableComponent.isPresent() && (
/* 199 */       (DisableComponent)disableComponent.get()).isDisabled()) {
/*     */       return;
/*     */     }
/*     */     
/* 203 */     Optional<PoolComponent> poolComponent = getAbility().getComponent(ModAbilityKeys.POOL);
/*     */     
/* 205 */     if (poolComponent.isPresent() && ((PoolComponent)poolComponent.get()).isPoolInUse()) {
/*     */       return;
/*     */     }
/*     */     
/* 209 */     maxChargeTime = Math.max(1.0F, maxChargeTime);
/* 210 */     maxChargeTime = this.maxChargeBonusManager.applyBonus(maxChargeTime);
/* 211 */     this.maxChargeTime = maxChargeTime;
/* 212 */     this.chargeTime = 0.0F;
/* 213 */     this.isCharging = true;
/*     */     
/* 215 */     if (!user.field_70170_p.field_72995_K) {
/* 216 */       poolComponent.ifPresent(c -> c.startPoolInUse(user));
/*     */     }
/*     */     
/* 219 */     this.startChargeEvents.dispatch(event -> event.startCharging(user, getAbility()));
/* 220 */     if (!user.field_70170_p.field_72995_K) {
/* 221 */       WyNetwork.sendToAllTrackingAndSelf(new SStartChargingPacket(user, getAbility(), maxChargeTime), (Entity)user);
/*     */     }
/*     */   }
/*     */   
/*     */   public void stopCharging(LivingEntity entity) {
/* 226 */     this.stopChargeEvents.dispatch(event -> event.endCharging(entity, getAbility()));
/*     */     
/* 228 */     forceStopCharging(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void forceStopCharging(LivingEntity entity) {
/* 236 */     this.isCharging = false;
/* 237 */     this.chargeTime = 0.0F;
/* 238 */     this.maxChargeTime = 0.0F;
/*     */     
/* 240 */     getAbility().getComponent(ModAbilityKeys.SLOT_DECORATION).ifPresent(c -> c.resetDecoration());
/* 241 */     if (!entity.field_70170_p.field_72995_K) {
/* 242 */       getAbility().getComponent(ModAbilityKeys.POOL).ifPresent(c -> c.stopPoolInUse(entity));
/*     */     }
/*     */     
/* 245 */     if (!entity.field_70170_p.field_72995_K) {
/* 246 */       WyNetwork.sendToAllTrackingAndSelf(new SForceStopChargingPacket(entity, getAbility()), (Entity)entity);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isCharging() {
/* 251 */     return this.isCharging;
/*     */   }
/*     */   
/*     */   public float getMaxChargeTime() {
/* 255 */     return this.maxChargeTime;
/*     */   }
/*     */   
/*     */   public float getChargeTime() {
/* 259 */     return this.chargeTime;
/*     */   }
/*     */   
/*     */   public float getChargePercentage() {
/* 263 */     float per = this.chargeTime / this.maxChargeTime;
/* 264 */     if (Float.isNaN(per)) {
/* 265 */       return 0.0F;
/*     */     }
/* 267 */     return per;
/*     */   }
/*     */   
/*     */   public boolean isCancelable() {
/* 271 */     return this.isCancelable;
/*     */   }
/*     */   
/*     */   public BonusManager getMaxChargeBonusManager() {
/* 275 */     return this.maxChargeBonusManager;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public CompoundNBT save() {
/* 281 */     CompoundNBT nbt = super.save();
/* 282 */     nbt.func_74776_a("chargeTime", this.chargeTime);
/* 283 */     nbt.func_74776_a("maxChargeTime", this.maxChargeTime);
/* 284 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 289 */     super.load(nbt);
/* 290 */     this.chargeTime = nbt.func_74760_g("chargeTime");
/* 291 */     this.maxChargeTime = nbt.func_74760_g("maxChargeTime");
/* 292 */     if (this.chargeTime > 0.0F)
/* 293 */       this.isCharging = true; 
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IEndChargingEvent {
/*     */     void endCharging(LivingEntity param1LivingEntity, IAbility param1IAbility);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IDuringChargingEvent {
/*     */     void duringCharging(LivingEntity param1LivingEntity, IAbility param1IAbility);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IStartChargingEvent {
/*     */     void startCharging(LivingEntity param1LivingEntity, IAbility param1IAbility);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\ChargeComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */