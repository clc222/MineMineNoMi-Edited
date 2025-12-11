/*     */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.PriorityEventPool;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.entities.SPinCameraPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.entities.SUnpinCameraPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class GrabEntityComponent extends AbilityComponent<IAbility> {
/*  24 */   private LivingEntity grabbedEntity = null;
/*     */   
/*     */   private boolean handleTargetCamera = false;
/*     */   
/*     */   private boolean handleTargetPosition = false;
/*     */   private boolean handleTargetPulling = false;
/*  30 */   private float heldDistance = 1.0F;
/*     */   
/*     */   private float startHealth;
/*     */   private boolean cameraPinned;
/*     */   private double previousDistance;
/*  35 */   private final PriorityEventPool<IOnGrabEvent> grabEvents = new PriorityEventPool();
/*  36 */   private final PriorityEventPool<IOnPullStartEvent> pullStartEvents = new PriorityEventPool();
/*  37 */   private final PriorityEventPool<IOnPullTickEvent> pullTickEvents = new PriorityEventPool();
/*  38 */   private final PriorityEventPool<IOnPullEndEvent> pullEndEvents = new PriorityEventPool();
/*  39 */   private final PriorityEventPool<IOnReleaseEvent> releaseEvents = new PriorityEventPool();
/*     */   
/*  41 */   private GrabState state = GrabState.IDLE;
/*     */   
/*     */   public GrabEntityComponent(IAbility ability, boolean handleTargetCamera, boolean handleTargetPosition, float heldDistance) {
/*  44 */     this(ability, handleTargetCamera, handleTargetPosition, false, heldDistance);
/*     */   }
/*     */   
/*     */   public GrabEntityComponent(IAbility ability, boolean handleTargetCamera, boolean handleTargetPosition, boolean handleTargetPulling, float heldDistance) {
/*  48 */     super(ModAbilityKeys.GRAB, ability);
/*     */     
/*  50 */     this.handleTargetCamera = handleTargetCamera;
/*  51 */     this.handleTargetPosition = handleTargetPosition;
/*  52 */     this.handleTargetPulling = handleTargetPulling;
/*  53 */     this.heldDistance = heldDistance;
/*     */   }
/*     */   
/*     */   public GrabEntityComponent addGrabEvent(IOnGrabEvent event) {
/*  57 */     this.grabEvents.addEvent(100, event);
/*     */     
/*  59 */     return this;
/*     */   }
/*     */   
/*     */   public GrabEntityComponent addGrabEvent(int priority, IOnGrabEvent event) {
/*  63 */     this.grabEvents.addEvent(priority, event);
/*     */     
/*  65 */     return this;
/*     */   }
/*     */   
/*     */   public GrabEntityComponent addPullStartEvent(IOnPullStartEvent event) {
/*  69 */     this.pullStartEvents.addEvent(100, event);
/*     */     
/*  71 */     return this;
/*     */   }
/*     */   
/*     */   public GrabEntityComponent addPullStartEvent(int priority, IOnPullStartEvent event) {
/*  75 */     this.pullStartEvents.addEvent(priority, event);
/*     */     
/*  77 */     return this;
/*     */   }
/*     */   
/*     */   public GrabEntityComponent addPullTickEvent(IOnPullTickEvent event) {
/*  81 */     this.pullTickEvents.addEvent(100, event);
/*     */     
/*  83 */     return this;
/*     */   }
/*     */   
/*     */   public GrabEntityComponent addPullTickEvent(int priority, IOnPullTickEvent event) {
/*  87 */     this.pullTickEvents.addEvent(priority, event);
/*     */     
/*  89 */     return this;
/*     */   }
/*     */   
/*     */   public GrabEntityComponent addPullEndEvent(IOnPullEndEvent event) {
/*  93 */     this.pullEndEvents.addEvent(100, event);
/*     */     
/*  95 */     return this;
/*     */   }
/*     */   
/*     */   public GrabEntityComponent addPullEndEvent(int priority, IOnPullEndEvent event) {
/*  99 */     this.pullEndEvents.addEvent(priority, event);
/*     */     
/* 101 */     return this;
/*     */   }
/*     */   
/*     */   public GrabEntityComponent addReleaseEvent(IOnReleaseEvent event) {
/* 105 */     this.releaseEvents.addEvent(100, event);
/*     */     
/* 107 */     return this;
/*     */   }
/*     */   
/*     */   public GrabEntityComponent addReleaseEvent(int priority, IOnReleaseEvent event) {
/* 111 */     this.releaseEvents.addEvent(priority, event);
/*     */     
/* 113 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void doTick(LivingEntity entity) {
/* 118 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 122 */     if (canContinueGrab(entity)) {
/* 123 */       if (this.state == GrabState.PULLING && this.handleTargetPulling) {
/* 124 */         this.grabbedEntity.func_195064_c(new EffectInstance((Effect)ModEffects.GRABBED.get(), 2, 3));
/*     */         
/* 126 */         Vector3d difference = entity.func_174824_e(1.0F).func_72441_c(0.0D, -1.0D, 0.0D).func_178788_d(this.grabbedEntity.func_213303_ch());
/*     */ 
/*     */         
/* 129 */         double distance = Math.abs(entity.func_174824_e(1.0F).func_72438_d(this.grabbedEntity.func_213303_ch()));
/* 130 */         if (distance > this.previousDistance + 2.0D) {
/* 131 */           release(entity);
/*     */           return;
/*     */         } 
/* 134 */         this.previousDistance = distance;
/*     */         
/* 136 */         double length = MathHelper.func_76133_a(difference.field_72450_a * difference.field_72450_a + difference.field_72449_c * difference.field_72449_c);
/* 137 */         double safeDifferenceY = (difference.field_72448_b != 0.0D) ? (1.0D / Math.abs(difference.field_72448_b)) : 0.0D;
/*     */         
/* 139 */         difference = difference.func_216372_d(1.0D / length, safeDifferenceY, 1.0D / length).func_186678_a(0.1D);
/*     */         
/* 141 */         AbilityHelper.setDeltaMovement((Entity)this.grabbedEntity, this.grabbedEntity.func_213322_ci().func_178787_e(difference));
/*     */         
/* 143 */         float reach = (float)entity.func_233637_b_((Attribute)ModAttributes.ATTACK_RANGE.get()) + 3.0F;
/*     */         
/* 145 */         if (isTargetInRange(entity, reach, entity.func_213311_cf() / 2.0F + 0.1F)) {
/* 146 */           stopPulling(entity);
/*     */         } else {
/* 148 */           this.pullTickEvents.dispatch(event -> event.pullTick(entity, (IAbility)getAbility()));
/*     */         } 
/*     */       } 
/*     */       
/* 152 */       if (this.state == GrabState.GRABBED) {
/* 153 */         if (this.handleTargetCamera && 
/* 154 */           !this.cameraPinned && this.grabbedEntity instanceof net.minecraft.entity.player.ServerPlayerEntity) {
/* 155 */           WyNetwork.sendTo(SPinCameraPacket.pinFixed(), (PlayerEntity)this.grabbedEntity);
/* 156 */           this.cameraPinned = true;
/*     */         } 
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
/* 168 */         if (this.handleTargetPosition) {
/* 169 */           this.grabbedEntity.func_195064_c(new EffectInstance((Effect)ModEffects.GRABBED.get(), 2, 3));
/*     */           
/* 171 */           Vector3d lookVec = entity.func_70040_Z().func_72432_b();
/* 172 */           Vector3d pos = new Vector3d(lookVec.field_72450_a * this.heldDistance, entity.func_70047_e() - 1.0D + lookVec.field_72448_b * this.heldDistance, lookVec.field_72449_c * this.heldDistance);
/*     */           
/* 174 */           AbilityHelper.setDeltaMovement((Entity)this.grabbedEntity, entity.func_213303_ch().func_178787_e(pos).func_178788_d(this.grabbedEntity.func_213303_ch()), true);
/*     */           
/* 176 */           this.grabbedEntity.field_70143_R = 0.0F;
/*     */         } 
/*     */       } 
/* 179 */     } else if (this.grabbedEntity != null) {
/* 180 */       if (this.state == GrabState.PULLING) {
/* 181 */         stopPulling(entity);
/*     */       }
/*     */       
/* 184 */       release(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void triggerPulling(LivingEntity entity) {
/* 189 */     if (this.state == GrabState.PULLING) {
/* 190 */       stopPulling(entity);
/*     */     } else {
/* 192 */       startPulling(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void startPulling(LivingEntity entity) {
/* 197 */     this.state = GrabState.PULLING;
/*     */     
/* 199 */     this.pullStartEvents.dispatch(event -> event.pullStart(entity, (IAbility)getAbility()));
/*     */   }
/*     */   
/*     */   public void stopPulling(LivingEntity entity) {
/* 203 */     this.state = GrabState.GRABBED;
/*     */     
/* 205 */     this.pullEndEvents.dispatch(event -> event.pullEnd(entity, (IAbility)getAbility()));
/*     */   }
/*     */   
/*     */   public void throwTarget(LivingEntity entity, double horizontalPower, double verticalPower) {
/* 209 */     this.state = GrabState.THROWN;
/*     */     
/* 211 */     if (canContinueGrab(entity)) {
/* 212 */       this.grabbedEntity.func_195063_d((Effect)ModEffects.GRABBED.get());
/*     */       
/* 214 */       Vector3d diff = entity.func_213303_ch().func_178788_d(this.grabbedEntity.func_213303_ch()).func_72432_b().func_216372_d(-horizontalPower, -verticalPower, -horizontalPower);
/*     */       
/* 216 */       AbilityHelper.setDeltaMovement((Entity)this.grabbedEntity, diff);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void release(LivingEntity entity) {
/* 221 */     this.state = GrabState.IDLE;
/*     */     
/* 223 */     if (this.grabbedEntity != null && this.grabbedEntity.func_70089_S()) {
/* 224 */       this.releaseEvents.dispatch(event -> event.release(entity, this.grabbedEntity, getAbility()));
/* 225 */       if (this.grabbedEntity instanceof net.minecraft.entity.player.ServerPlayerEntity) {
/* 226 */         WyNetwork.sendTo(new SUnpinCameraPacket(), (PlayerEntity)this.grabbedEntity);
/*     */       }
/*     */     } 
/*     */     
/* 230 */     this.grabbedEntity = null;
/*     */   }
/*     */   
/*     */   public boolean grabManually(LivingEntity entity, LivingEntity target) {
/* 234 */     ensureIsRegistered();
/*     */     
/* 236 */     if (target != null && target.func_70089_S() && !AbilityHelper.isTargetBlocking(entity, target)) {
/* 237 */       boolean flag = !this.grabEvents.dispatchCancelable(event -> !event.grab(entity, target, getAbility()));
/*     */       
/* 239 */       if (flag) {
/* 240 */         this.grabbedEntity = target;
/* 241 */         this.previousDistance = Math.abs(entity.func_174824_e(1.0F).func_72438_d(this.grabbedEntity.func_213303_ch()));
/* 242 */         this.cameraPinned = false;
/* 243 */         this.startHealth = entity.func_110143_aJ();
/* 244 */         this.state = GrabState.GRABBED;
/*     */         
/* 246 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 250 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isTargetInRange(LivingEntity entity) {
/* 254 */     return isTargetInRange(entity, 3.0F, 0.4F);
/*     */   }
/*     */   
/*     */   public boolean isTargetInRange(LivingEntity entity, float distance, float size) {
/* 258 */     float reach = (float)Math.sqrt(AttributeHelper.getSquaredAttackRangeDistance(entity, distance));
/*     */     
/* 260 */     boolean targetInRange = TargetHelper.getEntitiesInArea(entity, entity.func_233580_cy_(), reach, entity.func_213302_cg(), size, null, new Class[] { LivingEntity.class }).stream().anyMatch(target -> target.equals(this.grabbedEntity));
/*     */     
/* 262 */     return targetInRange;
/*     */   }
/*     */   
/*     */   public boolean grabNearest(LivingEntity entity) {
/* 266 */     double reach = Math.sqrt(AttributeHelper.getSquaredAttackRangeDistance(entity, 3.0D));
/*     */     
/* 268 */     return grabNearest(entity, (float)reach, 0.4F, true);
/*     */   }
/*     */   
/*     */   public boolean grabNearest(LivingEntity entity, boolean sendFailMessage) {
/* 272 */     double reach = Math.sqrt(AttributeHelper.getSquaredAttackRangeDistance(entity, 3.0D));
/*     */     
/* 274 */     return grabNearest(entity, (float)reach, 0.4F, sendFailMessage);
/*     */   }
/*     */   
/*     */   public boolean grabNearest(LivingEntity entity, float distance, float size) {
/* 278 */     return grabNearest(entity, distance, size, true);
/*     */   }
/*     */   
/*     */   public boolean grabNearest(LivingEntity entity, float distance, float size, boolean sendFailMessage) {
/* 282 */     ensureIsRegistered();
/*     */     
/* 284 */     LivingEntity target = AbilityHelper.canGrab(entity, distance, size, sendFailMessage);
/*     */     
/* 286 */     boolean flag = !this.grabEvents.dispatchCancelable(event -> !event.grab(entity, target, getAbility()));
/*     */     
/* 288 */     if (flag && target != null && target.func_70089_S()) {
/* 289 */       this.grabbedEntity = target;
/* 290 */       this.previousDistance = Math.abs(entity.func_174824_e(1.0F).func_72438_d(this.grabbedEntity.func_213303_ch()));
/* 291 */       this.cameraPinned = false;
/* 292 */       this.startHealth = entity.func_110143_aJ();
/* 293 */       this.state = GrabState.GRABBED;
/*     */       
/* 295 */       return true;
/*     */     } 
/*     */     
/* 298 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canContinueGrab(LivingEntity entity) {
/* 302 */     if (this.grabbedEntity == null || !this.grabbedEntity.func_70089_S()) {
/* 303 */       return false;
/*     */     }
/*     */     
/* 306 */     if (AbilityHelper.isDodging(this.grabbedEntity)) {
/* 307 */       return false;
/*     */     }
/*     */     
/* 310 */     if (AbilityHelper.isLogiaBlocking((Entity)entity, this.grabbedEntity)) {
/* 311 */       return false;
/*     */     }
/*     */     
/* 314 */     if (entity.func_110143_aJ() < this.startHealth - 10.0F) {
/* 315 */       return false;
/*     */     }
/*     */     
/* 318 */     return true;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getGrabbedEntity() {
/* 323 */     return this.grabbedEntity;
/*     */   }
/*     */   
/*     */   public boolean hasGrabbedEntity() {
/* 327 */     return (this.grabbedEntity != null && this.grabbedEntity.func_70089_S());
/*     */   }
/*     */   
/*     */   public static boolean hasGrabbed(LivingEntity entity, IAbility ability) {
/* 331 */     IAbilityData props = (IAbilityData)AbilityDataCapability.getLazy(entity).orElse(null);
/* 332 */     if (props == null) {
/* 333 */       return false;
/*     */     }
/*     */     
/* 336 */     for (IAbility abl : props.getEquippedAndPassiveAbilities()) {
/* 337 */       if (!abl.hasComponent(ModAbilityKeys.GRAB)) {
/*     */         continue;
/*     */       }
/*     */       
/* 341 */       if (abl.equals(ability)) {
/*     */         continue;
/*     */       }
/*     */       
/* 345 */       boolean isOnCooldown = ((Boolean)abl.getComponent(ModAbilityKeys.COOLDOWN).map(comp -> Boolean.valueOf(comp.isOnCooldown())).orElse(Boolean.valueOf(false))).booleanValue();
/* 346 */       boolean isDisabled = ((Boolean)abl.getComponent(ModAbilityKeys.DISABLE).map(comp -> Boolean.valueOf(comp.isDisabled())).orElse(Boolean.valueOf(false))).booleanValue();
/* 347 */       if (isOnCooldown || isDisabled) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/* 352 */       if (((GrabEntityComponent)abl.getComponent(ModAbilityKeys.GRAB).get()).hasGrabbedEntity()) {
/* 353 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 357 */     return false;
/*     */   }
/*     */   
/*     */   public GrabState getState() {
/* 361 */     return this.state;
/*     */   }
/*     */   
/*     */   public void setState(GrabState state) {
/* 365 */     this.state = state;
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
/*     */   public enum GrabState
/*     */   {
/* 394 */     IDLE,
/* 395 */     PULLING,
/* 396 */     GRABBED,
/* 397 */     THROWN;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IOnReleaseEvent {
/*     */     void release(LivingEntity param1LivingEntity1, LivingEntity param1LivingEntity2, IAbility param1IAbility);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IOnPullEndEvent {
/*     */     void pullEnd(LivingEntity param1LivingEntity, IAbility param1IAbility);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IOnPullTickEvent {
/*     */     void pullTick(LivingEntity param1LivingEntity, IAbility param1IAbility);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IOnPullStartEvent {
/*     */     void pullStart(LivingEntity param1LivingEntity, IAbility param1IAbility);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IOnGrabEvent {
/*     */     boolean grab(LivingEntity param1LivingEntity1, LivingEntity param1LivingEntity2, IAbility param1IAbility);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\GrabEntityComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */