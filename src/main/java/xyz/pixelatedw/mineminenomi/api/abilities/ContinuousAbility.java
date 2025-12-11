/*     */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*     */ import java.io.Serializable;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.ability.AbilityUseEvent;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SAnimeScreamPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SSyncAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ @Deprecated
/*     */ public abstract class ContinuousAbility extends Ability {
/*  17 */   protected int threshold = 0;
/*  18 */   protected int continueTime = 0; protected IOnStartContinuity onStartContinuityEvent = player -> true;
/*     */   protected IDuringContinuity duringContinuityEvent = (player, continuousTime) -> {
/*     */     
/*     */     };
/*     */   protected IBeforeContinuityStop beforeContinuityStopEvent = player -> true;
/*     */   protected IAfterContinuityStop afterContinuityStopEvent = player -> {
/*     */     
/*     */     };
/*     */   
/*     */   public ContinuousAbility(AbilityCore<? extends IAbility> core) {
/*  28 */     super(core);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void use(PlayerEntity player) {
/*  37 */     if (player.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*  40 */     if (isOnCooldown() && getCooldown() <= 10.0D) {
/*  41 */       stopCooldown(player);
/*     */     }
/*  43 */     AbilityUseEvent.Pre pre = new AbilityUseEvent.Pre((LivingEntity)player, this);
/*  44 */     if (MinecraftForge.EVENT_BUS.post((Event)pre)) {
/*     */       return;
/*     */     }
/*  47 */     if (!isContinuous()) {
/*     */       
/*  49 */       if (!isOnStandby()) {
/*     */         return;
/*     */       }
/*  52 */       if (hasAbilityFromSamePoolEnabled(player)) {
/*     */         return;
/*     */       }
/*     */       
/*  56 */       if (this.onStartContinuityEvent.onStartContinuity(player))
/*     */       {
/*  58 */         checkAbilityPool(player, Ability.State.CONTINUOUS);
/*     */         
/*  60 */         if (((Boolean)CommonConfig.INSTANCE.animeScreaming.get()).booleanValue() && !(this instanceof PunchAbility)) {
/*  61 */           WyNetwork.sendToAllTrackingAndSelf(new SAnimeScreamPacket(player.func_145782_y(), getDisplayNameOld()), (Entity)player);
/*     */         }
/*  63 */         AbilityUseEvent.Post post = new AbilityUseEvent.Post((LivingEntity)player, this);
/*  64 */         MinecraftForge.EVENT_BUS.post((Event)post);
/*     */         
/*  66 */         startContinuity(player);
/*     */ 
/*     */         
/*  69 */         if (this instanceof IAnimatedAbility) {
/*  70 */           WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityPacket(player.func_145782_y(), this), (Entity)player);
/*     */         }
/*     */         
/*  73 */         WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket((LivingEntity)player, this), (Entity)player);
/*     */       
/*     */       }
/*     */     
/*     */     }
/*  78 */     else if (!isStateForced()) {
/*  79 */       tryStoppingContinuity(player);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startDisable(PlayerEntity player, int ticks) {
/*  86 */     if (isContinuous())
/*     */     {
/*  88 */       tryStoppingContinuity(player);
/*     */     }
/*  90 */     super.startDisable(player, ticks);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThreshold(double threshold) {
/*  99 */     this.threshold = (int)(threshold * 20.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setThresholdInTicks(int threshold) {
/* 104 */     this.threshold = threshold;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getThreshold() {
/* 109 */     return this.threshold;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setContinueTime(int time) {
/* 114 */     this.continueTime = time * 20;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getContinueTime() {
/* 119 */     return this.continueTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick(PlayerEntity player) {
/* 130 */     super.tick(player);
/*     */     
/* 132 */     if (isContinuous()) {
/*     */       
/* 134 */       if (canUse(player).isFail()) {
/*     */         
/* 136 */         stopContinuity(player);
/*     */         
/*     */         return;
/*     */       } 
/* 140 */       this.continueTime++;
/*     */       
/* 142 */       if ((isClientSideLegacy() || !player.field_70170_p.field_72995_K) && !isStateForced()) {
/* 143 */         tickContinuity(player);
/*     */       }
/* 145 */       if (this.threshold > 0 && this.continueTime >= this.threshold) {
/* 146 */         tryStoppingContinuity(player);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void tickContinuity(PlayerEntity player) {
/* 152 */     this.duringContinuityEvent.duringContinuity(player, this.continueTime);
/*     */   }
/*     */ 
/*     */   
/*     */   public void startContinuity(PlayerEntity player) {
/* 157 */     setState(Ability.State.CONTINUOUS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tryStoppingContinuity(PlayerEntity player) {
/* 166 */     if (player.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/* 169 */     if (this.beforeContinuityStopEvent.fire(player))
/*     */     {
/* 171 */       stopContinuity(player);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stopContinuity(PlayerEntity player) {
/* 181 */     checkAbilityPool(player, Ability.State.COOLDOWN);
/* 182 */     startCooldown(player);
/* 183 */     this.afterContinuityStopEvent.fire(player);
/* 184 */     this.continueTime = 0;
/* 185 */     if (!player.field_70170_p.field_72995_K) {
/* 186 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket((LivingEntity)player, this), (Entity)player);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasAbilityFromSamePoolEnabled(PlayerEntity player) {
/* 198 */     return false;
/*     */   }
/*     */   
/*     */   public static interface IAfterContinuityStop extends Serializable {
/*     */     void fire(PlayerEntity param1PlayerEntity);
/*     */   }
/*     */   
/*     */   public static interface IBeforeContinuityStop extends Serializable {
/*     */     boolean fire(PlayerEntity param1PlayerEntity);
/*     */   }
/*     */   
/*     */   public static interface IOnStartContinuity extends Serializable {
/*     */     boolean onStartContinuity(PlayerEntity param1PlayerEntity);
/*     */   }
/*     */   
/*     */   public static interface IDuringContinuity extends Serializable {
/*     */     void duringContinuity(PlayerEntity param1PlayerEntity, int param1Int);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\ContinuousAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */