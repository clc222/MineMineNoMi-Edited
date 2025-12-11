/*     */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityStat;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.math.EasingFunctionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.PriorityEventPool;
/*     */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.components.SStartCooldownPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.components.SStopCooldownPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class CooldownComponent extends AbilityComponent<IAbility> {
/*  31 */   private static final UUID COOLDOWN_BONUS_MANAGER_UUID = UUID.fromString("99be6fa9-bcc6-4c9c-be00-ee024543015d"); private boolean isOnCooldown; private float startCooldown; private float cooldown;
/*     */   
/*     */   public static AbilityDescriptionLine.IDescriptionLine getTooltip(float ticks) {
/*  34 */     return getTooltip(ticks, ticks);
/*     */   }
/*     */   
/*     */   public static AbilityDescriptionLine.IDescriptionLine getTooltip(float min, float max) {
/*  38 */     return (e, a) -> {
/*     */         float minSeconds = Math.round(min / 20.0F);
/*     */         float maxSeconds = Math.round(max / 20.0F);
/*     */         AbilityStat.Builder statBuilder = (new AbilityStat.Builder((ITextComponent)ModI18n.ABILITY_DESCRIPTION_STAT_NAME_COOLDOWN, minSeconds, maxSeconds)).withUnit((ITextComponent)ModI18n.ABILITY_DESCRIPTION_STAT_UNIT_SECONDS);
/*     */         a.getComponent(ModAbilityKeys.COOLDOWN).ifPresent(());
/*     */         return statBuilder.build().getStatDescription();
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean playReadyAnim = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   private float cooldownReadyAnim = 10.0F;
/*     */   
/*  60 */   private final PriorityEventPool<IStartCooldownEvent> startCooldownEvents = new PriorityEventPool();
/*  61 */   private final PriorityEventPool<IDuringCooldownEvent> tickCooldownEvents = new PriorityEventPool();
/*  62 */   private final PriorityEventPool<IEndCooldownEvent> stopCooldownEvents = new PriorityEventPool();
/*     */   
/*  64 */   private final BonusManager bonusManager = new BonusManager(COOLDOWN_BONUS_MANAGER_UUID);
/*     */   
/*     */   public CooldownComponent(IAbility ability) {
/*  67 */     super(ModAbilityKeys.COOLDOWN, ability);
/*  68 */     addBonusManager(this.bonusManager);
/*     */   }
/*     */ 
/*     */   
/*     */   public void postInit(IAbility ability) {
/*  73 */     ability.getComponent(ModAbilityKeys.SLOT_DECORATION).ifPresent(component -> {
/*     */           component.addPreRenderEvent(100, ());
/*     */           component.addPostRenderEvent(100, ());
/*     */         });
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doTick(LivingEntity entity) {
/* 120 */     if (getAbility().hasComponent(ModAbilityKeys.DISABLE) && ((DisableComponent)getAbility().getComponent(ModAbilityKeys.DISABLE).get()).isDisabled()) {
/*     */       return;
/*     */     }
/*     */     
/* 124 */     if (entity instanceof net.minecraft.entity.player.PlayerEntity && !WyPatreon.isReleaseBuild() && FGCommand.NO_COOLDOWN) {
/* 125 */       stopCooldown(entity);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 130 */     getAbility().getComponent(ModAbilityKeys.ALT_MODE).ifPresent(component -> component.setDisabled(isOnCooldown()));
/*     */ 
/*     */ 
/*     */     
/* 134 */     if (isOnCooldown()) {
/* 135 */       if (this.cooldown > 0.0F) {
/* 136 */         ModifiableAttributeInstance inst = entity.func_110148_a((Attribute)ModAttributes.TIME_PROGRESSION.get());
/*     */         
/* 138 */         double timeProgression = 1.0D;
/*     */         
/* 140 */         if (inst != null) {
/* 141 */           timeProgression = inst.func_111126_e();
/*     */         }
/*     */         
/* 144 */         this.cooldown = (float)(this.cooldown - getTpsFactor() * timeProgression);
/*     */         
/* 146 */         int loops = Math.max(1, (int)getTpsFactor());
/* 147 */         for (int i = 0; i < loops; i++) {
/* 148 */           this.tickCooldownEvents.dispatch(event -> event.duringCooldown(entity, getAbility()));
/*     */         }
/*     */       } else {
/* 151 */         stopCooldown(entity);
/*     */         return;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public CooldownComponent addStartEvent(IStartCooldownEvent event) {
/* 159 */     this.startCooldownEvents.addEvent(event);
/* 160 */     return this;
/*     */   }
/*     */   
/*     */   public CooldownComponent addStartEvent(int priority, IStartCooldownEvent event) {
/* 164 */     this.startCooldownEvents.addEvent(priority, event);
/* 165 */     return this;
/*     */   }
/*     */   
/*     */   public CooldownComponent addTickEvent(IDuringCooldownEvent event) {
/* 169 */     this.tickCooldownEvents.addEvent(event);
/* 170 */     return this;
/*     */   }
/*     */   
/*     */   public CooldownComponent addTickEvent(int priority, IDuringCooldownEvent event) {
/* 174 */     this.tickCooldownEvents.addEvent(priority, event);
/* 175 */     return this;
/*     */   }
/*     */   
/*     */   public CooldownComponent addEndEvent(IEndCooldownEvent event) {
/* 179 */     this.stopCooldownEvents.addEvent(event);
/* 180 */     return this;
/*     */   }
/*     */   
/*     */   public CooldownComponent addEndEvent(int priority, IEndCooldownEvent event) {
/* 184 */     this.stopCooldownEvents.addEvent(priority, event);
/* 185 */     return this;
/*     */   }
/*     */   
/*     */   public void startCooldown(LivingEntity entity, float cooldown) {
/* 189 */     ensureIsRegistered();
/* 190 */     if (isOnCooldown()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 200 */     Optional<PoolComponent> poolComponent = getAbility().getComponent(ModAbilityKeys.POOL);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 206 */     cooldown = Math.max(0.0F, cooldown);
/* 207 */     cooldown = this.bonusManager.applyBonus(cooldown);
/* 208 */     this.startCooldown = cooldown;
/* 209 */     this.cooldown = cooldown;
/* 210 */     this.isOnCooldown = true;
/*     */     
/* 212 */     if (!entity.field_70170_p.field_72995_K) {
/* 213 */       poolComponent.ifPresent(c -> {
/*     */             boolean ignoresCooldown = c.getPools().stream().anyMatch(());
/*     */             
/*     */             if (!ignoresCooldown) {
/*     */               c.startPoolInUse(entity);
/*     */             }
/*     */           });
/*     */     }
/* 221 */     this.startCooldownEvents.dispatch(event -> event.startCooldown(entity, getAbility()));
/* 222 */     if (!entity.field_70170_p.field_72995_K) {
/* 223 */       WyNetwork.sendToAllTrackingAndSelf(new SStartCooldownPacket(entity, getAbility(), cooldown), (Entity)entity);
/*     */     }
/*     */   }
/*     */   
/*     */   public void stopCooldown(LivingEntity entity) {
/* 228 */     if (!isOnCooldown()) {
/*     */       return;
/*     */     }
/*     */     
/* 232 */     this.stopCooldownEvents.dispatch(event -> event.endCooldown(entity, getAbility()));
/*     */     
/* 234 */     this.isOnCooldown = false;
/* 235 */     this.startCooldown = 0.0F;
/* 236 */     this.cooldown = 0.0F;
/*     */     
/* 238 */     getAbility().getComponent(ModAbilityKeys.SLOT_DECORATION).ifPresent(c -> c.resetDecoration());
/*     */     
/* 240 */     if (!entity.field_70170_p.field_72995_K) {
/* 241 */       getAbility().getComponent(ModAbilityKeys.POOL).ifPresent(c -> c.stopPoolInUse(entity));
/*     */     }
/*     */     
/* 244 */     if (!entity.field_70170_p.field_72995_K) {
/* 245 */       WyNetwork.sendToAllTrackingAndSelf(new SStopCooldownPacket(entity, getAbility()), (Entity)entity);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isOnCooldown() {
/* 250 */     return this.isOnCooldown;
/*     */   }
/*     */   
/*     */   public float getStartCooldown() {
/* 254 */     return this.startCooldown;
/*     */   }
/*     */   
/*     */   public float getCooldown() {
/* 258 */     return this.cooldown;
/*     */   }
/*     */   
/*     */   public BonusManager getBonusManager() {
/* 262 */     return this.bonusManager;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public CompoundNBT save() {
/* 268 */     CompoundNBT nbt = super.save();
/* 269 */     nbt.func_74776_a("cooldown", this.cooldown);
/* 270 */     nbt.func_74776_a("startCooldown", this.startCooldown);
/* 271 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 276 */     super.load(nbt);
/* 277 */     this.cooldown = nbt.func_74760_g("cooldown");
/* 278 */     this.startCooldown = nbt.func_74760_g("cooldown");
/* 279 */     if (this.cooldown > 0.0F)
/* 280 */       this.isOnCooldown = true; 
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IEndCooldownEvent {
/*     */     void endCooldown(LivingEntity param1LivingEntity, IAbility param1IAbility);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IDuringCooldownEvent {
/*     */     void duringCooldown(LivingEntity param1LivingEntity, IAbility param1IAbility);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IStartCooldownEvent {
/*     */     void startCooldown(LivingEntity param1LivingEntity, IAbility param1IAbility);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\CooldownComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */