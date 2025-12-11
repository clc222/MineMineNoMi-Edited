/*     */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*     */ import java.util.Optional;
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
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.components.SStopContinuityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class ContinuousComponent extends AbilityComponent<IAbility> {
/*  28 */   private static final TranslationTextComponent CONTINUITY_STAT = new TranslationTextComponent(ModI18n.ABILITY_DESCRIPTION_STAT_NAME_CONTINUE); private boolean isContinuous; private float thresholdTime; private float continueTime; private boolean isInfinite; private final boolean isParallel; private final Predicate<ContinuousComponent> isParallelTest;
/*     */   
/*     */   public static AbilityDescriptionLine.IDescriptionLine getTooltip() {
/*  31 */     return getTooltip(-1.0F);
/*     */   }
/*     */   
/*     */   public static AbilityDescriptionLine.IDescriptionLine getTooltip(float ticks) {
/*  35 */     return getTooltip(ticks, ticks);
/*     */   }
/*     */   
/*     */   public static AbilityDescriptionLine.IDescriptionLine getTooltip(float min, float max) {
/*  39 */     return (e, a) -> {
/*     */         float minVal = min;
/*     */         float maxVal = max;
/*     */         if (min == max && min == -1.0F) {
/*     */           minVal = Float.POSITIVE_INFINITY;
/*     */           maxVal = Float.POSITIVE_INFINITY;
/*     */         } else {
/*     */           minVal = Math.round(min / 20.0F);
/*     */           maxVal = Math.round(max / 20.0F);
/*     */         } 
/*     */         AbilityStat.Builder statBuilder = (new AbilityStat.Builder((ITextComponent)CONTINUITY_STAT, minVal, maxVal)).withUnit((ITextComponent)ModI18n.ABILITY_DESCRIPTION_STAT_UNIT_SECONDS);
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
/*     */ 
/*     */   
/*  62 */   private final PriorityEventPool<IStartContinuousEvent> startContinuousEvents = new PriorityEventPool();
/*  63 */   private final PriorityEventPool<IDuringContinuousEvent> tickContinuousEvents = new PriorityEventPool();
/*  64 */   private final PriorityEventPool<IEndContinuousEvent> stopContinuousEvents = new PriorityEventPool();
/*     */   
/*     */   public ContinuousComponent(IAbility ability) {
/*  67 */     this(ability, false);
/*     */   }
/*     */   
/*     */   public ContinuousComponent(IAbility ability, boolean isParallel) {
/*  71 */     super(ModAbilityKeys.CONTINUOUS, ability);
/*  72 */     this.isParallel = isParallel;
/*  73 */     this.isParallelTest = (comp -> true);
/*     */   }
/*     */   
/*     */   public ContinuousComponent(IAbility ability, Predicate<ContinuousComponent> isParallelTest) {
/*  77 */     super(ModAbilityKeys.CONTINUOUS, ability);
/*  78 */     this.isParallel = true;
/*  79 */     this.isParallelTest = isParallelTest;
/*     */   }
/*     */ 
/*     */   
/*     */   public void postInit(IAbility ability) {
/*  84 */     ability.getComponent(ModAbilityKeys.SLOT_DECORATION).ifPresent(component -> component.addPreRenderEvent(200, ()));
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
/* 103 */     if (getAbility().hasComponent(ModAbilityKeys.DISABLE) && ((DisableComponent)getAbility().getComponent(ModAbilityKeys.DISABLE).get()).isDisabled()) {
/*     */       return;
/*     */     }
/*     */     
/* 107 */     getAbility().getComponent(ModAbilityKeys.ALT_MODE).ifPresent(component -> component.setDisabled(isContinuous()));
/*     */ 
/*     */ 
/*     */     
/* 111 */     if (isContinuous()) {
/* 112 */       if (entity.func_70644_a((Effect)ModEffects.IN_EVENT.get())) {
/* 113 */         stopContinuity(entity);
/*     */         
/*     */         return;
/*     */       } 
/* 117 */       if (!entity.field_70170_p.field_72995_K) {
/* 118 */         ProtectedAreasData worldData = ProtectedAreasData.get(entity.field_70170_p);
/* 119 */         ProtectedArea area = worldData.getProtectedArea(entity.func_233580_cy_().func_177958_n(), entity.func_233580_cy_().func_177956_o(), entity.func_233580_cy_().func_177952_p());
/*     */         
/* 121 */         if (area != null && !area.canUseAbility(getAbility().getCore())) {
/* 122 */           stopContinuity(entity);
/*     */ 
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
/* 137 */       if (!this.isInfinite && this.continueTime >= this.thresholdTime) {
/* 138 */         stopContinuity(entity);
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 143 */       ModifiableAttributeInstance inst = entity.func_110148_a((Attribute)ModAttributes.TIME_PROGRESSION.get());
/*     */       
/* 145 */       double timeProgression = 1.0D;
/*     */       
/* 147 */       if (inst != null) {
/* 148 */         timeProgression = inst.func_111126_e();
/*     */       }
/*     */       
/* 151 */       this.continueTime = (float)(this.continueTime + getTpsFactor() * timeProgression);
/*     */       
/* 153 */       int loops = Math.max(1, (int)getTpsFactor());
/* 154 */       for (int i = 0; i < loops; i++) {
/* 155 */         this.tickContinuousEvents.dispatch(event -> event.duringContinuous(entity, getAbility()));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public <T extends LivingEntity, A extends IAbility> ContinuousComponent addStartEvent(IStartContinuousEvent event) {
/* 161 */     this.startContinuousEvents.addEvent(event);
/* 162 */     return this;
/*     */   }
/*     */   
/*     */   public <T extends LivingEntity> ContinuousComponent addStartEvent(int priority, IStartContinuousEvent event) {
/* 166 */     this.startContinuousEvents.addEvent(priority, event);
/* 167 */     return this;
/*     */   }
/*     */   
/*     */   public <T extends LivingEntity> ContinuousComponent addTickEvent(IDuringContinuousEvent event) {
/* 171 */     this.tickContinuousEvents.addEvent(event);
/* 172 */     return this;
/*     */   }
/*     */   
/*     */   public <T extends LivingEntity> ContinuousComponent addTickEvent(int priority, IDuringContinuousEvent event) {
/* 176 */     this.tickContinuousEvents.addEvent(priority, event);
/* 177 */     return this;
/*     */   }
/*     */   
/*     */   public <T extends LivingEntity> ContinuousComponent addEndEvent(IEndContinuousEvent event) {
/* 181 */     this.stopContinuousEvents.addEvent(event);
/* 182 */     return this;
/*     */   }
/*     */   
/*     */   public <T extends LivingEntity> ContinuousComponent addEndEvent(int priority, IEndContinuousEvent event) {
/* 186 */     this.stopContinuousEvents.addEvent(priority, event);
/* 187 */     return this;
/*     */   }
/*     */   
/*     */   public void triggerContinuity(LivingEntity entity) {
/* 191 */     triggerContinuity(entity, -1.0F);
/*     */   }
/*     */   
/*     */   public void triggerContinuity(LivingEntity entity, float threshold) {
/* 195 */     if (isContinuous()) {
/* 196 */       stopContinuity(entity);
/*     */     } else {
/*     */       
/* 199 */       startContinuity(entity, threshold);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void startContinuity(LivingEntity entity) {
/* 204 */     startContinuity(entity, -1.0F);
/*     */   }
/*     */   
/*     */   public void startContinuity(LivingEntity entity, float threshold) {
/* 208 */     ensureIsRegistered();
/* 209 */     if (isContinuous()) {
/*     */       return;
/*     */     }
/* 212 */     Optional<CooldownComponent> cooldownComponent = getAbility().getComponent(ModAbilityKeys.COOLDOWN);
/* 213 */     if (cooldownComponent.isPresent())
/*     */     {
/* 215 */       if (((CooldownComponent)cooldownComponent.get()).isOnCooldown()) {
/*     */         return;
/*     */       }
/*     */     }
/* 219 */     Optional<DisableComponent> disableComponent = getAbility().getComponent(ModAbilityKeys.DISABLE);
/* 220 */     if (disableComponent.isPresent() && (
/* 221 */       (DisableComponent)disableComponent.get()).isDisabled()) {
/*     */       return;
/*     */     }
/*     */     
/* 225 */     Optional<PoolComponent> poolComponent = getAbility().getComponent(ModAbilityKeys.POOL);
/* 226 */     if (poolComponent.isPresent() && ((PoolComponent)poolComponent.get()).isPoolInUse()) {
/*     */       return;
/*     */     }
/*     */     
/* 230 */     if (threshold < 0.0F) {
/* 231 */       this.isInfinite = true;
/*     */     } else {
/*     */       
/* 234 */       this.isInfinite = false;
/*     */     } 
/* 236 */     this.thresholdTime = threshold;
/* 237 */     this.continueTime = 0.0F;
/* 238 */     this.isContinuous = true;
/*     */     
/* 240 */     if (!entity.field_70170_p.field_72995_K) {
/* 241 */       poolComponent.ifPresent(c -> c.startPoolInUse(entity));
/*     */     }
/*     */     
/* 244 */     this.startContinuousEvents.dispatch(event -> event.startContinuous(entity, getAbility()));
/* 245 */     if (!entity.field_70170_p.field_72995_K) {
/* 246 */       WyNetwork.sendToAllTrackingAndSelf(new SStartContinuityPacket(entity, getAbility(), threshold), (Entity)entity);
/*     */     }
/*     */   }
/*     */   
/*     */   public void stopContinuity(LivingEntity entity) {
/* 251 */     if (!this.isContinuous) {
/*     */       return;
/*     */     }
/*     */     
/* 255 */     if (!entity.field_70170_p.field_72995_K) {
/* 256 */       getAbility().getComponent(ModAbilityKeys.POOL).ifPresent(c -> c.stopPoolInUse(entity));
/*     */     }
/*     */ 
/*     */     
/* 260 */     this.stopContinuousEvents.dispatch(event -> event.endContinuous(entity, getAbility()));
/*     */     
/* 262 */     this.isContinuous = false;
/* 263 */     this.continueTime = 0.0F;
/* 264 */     this.thresholdTime = 0.0F;
/*     */     
/* 266 */     getAbility().getComponent(ModAbilityKeys.SLOT_DECORATION).ifPresent(c -> c.resetDecoration());
/*     */     
/* 268 */     if (!entity.field_70170_p.field_72995_K) {
/* 269 */       WyNetwork.sendToAllTrackingAndSelf(new SStopContinuityPacket(entity, getAbility()), (Entity)entity);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isContinuous() {
/* 274 */     return this.isContinuous;
/*     */   }
/*     */   
/*     */   public float getThresholdTime() {
/* 278 */     return this.thresholdTime;
/*     */   }
/*     */   
/*     */   public void setThresholdTime(LivingEntity entity, float threshold) {
/* 282 */     if (threshold < 0.0F) {
/* 283 */       this.isInfinite = true;
/*     */     } else {
/* 285 */       this.isInfinite = false;
/*     */     } 
/*     */     
/* 288 */     this.thresholdTime = threshold;
/* 289 */     this.continueTime = 0.0F;
/*     */     
/* 291 */     if (!entity.field_70170_p.field_72995_K) {
/* 292 */       WyNetwork.sendToAllTrackingAndSelf(new SSetContinuityThresholdPacket(entity, (IAbility)getAbility(), this.thresholdTime), (Entity)entity);
/*     */     }
/*     */   }
/*     */   
/*     */   public float getContinueTime() {
/* 297 */     return this.continueTime;
/*     */   }
/*     */   
/*     */   public void increaseContinuityTime(float time) {
/* 301 */     this.continueTime += time;
/* 302 */     this.continueTime = MathHelper.func_76131_a(this.continueTime, 0.0F, this.thresholdTime);
/*     */   }
/*     */   
/*     */   public void decreaseContinuityTime(float time) {
/* 306 */     this.continueTime -= time;
/* 307 */     this.continueTime = MathHelper.func_76131_a(this.continueTime, 0.0F, this.thresholdTime);
/*     */   }
/*     */   
/*     */   public boolean isInfinite() {
/* 311 */     return this.isInfinite;
/*     */   }
/*     */   
/*     */   public boolean isParallel() {
/* 315 */     return (this.isParallel && this.isParallelTest.test(this));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public CompoundNBT save() {
/* 321 */     CompoundNBT nbt = super.save();
/* 322 */     nbt.func_74776_a("continueTime", getContinueTime());
/* 323 */     nbt.func_74776_a("thresholdTime", getThresholdTime());
/* 324 */     nbt.func_74757_a("isInfinite", isInfinite());
/* 325 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 330 */     super.load(nbt);
/* 331 */     this.continueTime = nbt.func_74760_g("continueTime");
/* 332 */     this.thresholdTime = nbt.func_74760_g("thresholdTime");
/* 333 */     this.isInfinite = nbt.func_74767_n("isInfinite");
/* 334 */     if (this.continueTime > 0.0F)
/* 335 */       this.isContinuous = true; 
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IEndContinuousEvent {
/*     */     void endContinuous(LivingEntity param1LivingEntity, IAbility param1IAbility);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IDuringContinuousEvent {
/*     */     void duringContinuous(LivingEntity param1LivingEntity, IAbility param1IAbility);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IStartContinuousEvent {
/*     */     void startContinuous(LivingEntity param1LivingEntity, IAbility param1IAbility);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\ContinuousComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */