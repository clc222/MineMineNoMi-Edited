/*     */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.ability.AbilityUseEvent;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SAnimeScreamPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SSyncAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public abstract class ChargeableAbility
/*     */   extends Ability
/*     */ {
/*     */   private int chargeTime;
/*     */   private int maxChargeTime;
/*     */   private boolean isCancelable;
/*     */   
/*     */   public ChargeableAbility(AbilityCore<? extends IAbility> core) {
/*  28 */     super(core);
/*     */   }
/*     */   protected IOnStartCharging onStartChargingEvent = player -> true;
/*     */   protected IOnEndCharging onEndChargingEvent = player -> true;
/*     */   protected IDuringCharging duringChargingEvent = (player, chargeTime) -> {
/*     */     
/*     */     };
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
/*  47 */     if (isCharging() && this.chargeTime > 0) {
/*     */       
/*  49 */       endCharging(player);
/*     */     }
/*  51 */     else if (isOnStandby()) {
/*     */       
/*  53 */       if (this.onStartChargingEvent.onStartCharging(player)) {
/*     */         
/*  55 */         checkAbilityPool(player, Ability.State.CHARGING);
/*     */         
/*  57 */         if (((Boolean)CommonConfig.INSTANCE.animeScreaming.get()).booleanValue() && (getChargedShouts()).length > 1) {
/*  58 */           WyNetwork.sendToAllTrackingAndSelf(new SAnimeScreamPacket(player.func_145782_y(), getChargedShouts()[0] + "..."), (Entity)player);
/*     */         }
/*  60 */         AbilityUseEvent.Post post = new AbilityUseEvent.Post((LivingEntity)player, this);
/*  61 */         MinecraftForge.EVENT_BUS.post((Event)post);
/*     */         
/*  63 */         this.chargeTime = this.maxChargeTime;
/*  64 */         startCharging(player);
/*     */ 
/*     */         
/*  67 */         if (this instanceof IAnimatedAbility || this instanceof IBodyOverlayAbility || this instanceof IPunchOverlayAbility)
/*  68 */           WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityPacket(player.func_145782_y(), this), (Entity)player); 
/*  69 */         WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket((LivingEntity)player, this), (Entity)player);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startDisable(PlayerEntity player, int ticks) {
/*  77 */     if (isCharging()) {
/*     */       
/*  79 */       this.chargeTime = this.maxChargeTime;
/*  80 */       startStandby();
/*     */     } 
/*  82 */     super.startDisable(player, ticks);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxChargeTime(double seconds) {
/*  90 */     this.maxChargeTime = (int)(seconds * 20.0D);
/*  91 */     this.chargeTime = this.maxChargeTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxChargeTime() {
/*  96 */     return this.maxChargeTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getChargePercentage() {
/* 104 */     return (this.chargeTime / this.maxChargeTime * 100);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getInvertedChargePercentage() {
/* 112 */     return ((1 - this.chargeTime / this.maxChargeTime) * 100);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getChargeTime() {
/* 117 */     return this.chargeTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChargeTime(int time) {
/* 122 */     this.chargeTime = time * 20;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelable() {
/* 127 */     this.isCancelable = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelable() {
/* 132 */     return this.isCancelable;
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
/* 143 */     super.tick(player);
/*     */     
/* 145 */     if (isCharging()) {
/*     */       
/* 147 */       if (canUse(player).isFail()) {
/*     */         
/* 149 */         stopCharging(player);
/*     */         
/*     */         return;
/*     */       } 
/* 153 */       if (isCharging() && this.chargeTime > 0) {
/*     */         
/* 155 */         this.chargeTime = (int)(this.chargeTime - 1.0D * getTimeProgression());
/* 156 */         if (!player.field_70170_p.field_72995_K && !isStateForced()) {
/* 157 */           this.duringChargingEvent.duringCharging(player, this.chargeTime);
/*     */         }
/* 159 */       } else if (isCharging() && this.chargeTime <= 0) {
/*     */         
/* 161 */         endCharging(player);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void startCharging(PlayerEntity player) {
/* 168 */     setState(Ability.State.CHARGING);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endCharging(PlayerEntity player) {
/* 177 */     if (player.field_70170_p.field_72995_K)
/*     */       return; 
/* 179 */     if (!isStateForced() && this.onEndChargingEvent.onEndCharging(player)) {
/*     */       
/* 181 */       if (((Boolean)CommonConfig.INSTANCE.animeScreaming.get()).booleanValue()) {
/* 182 */         WyNetwork.sendToAllTrackingAndSelf(((getChargedShouts()).length > 1) ? new SAnimeScreamPacket(player.func_145782_y(), getChargedShouts()[1]) : new SAnimeScreamPacket(player.func_145782_y(), getChargedShouts()[0]), (Entity)player);
/*     */       }
/* 184 */       stopCharging(player);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stopCharging(PlayerEntity player) {
/* 194 */     checkAbilityPool(player, Ability.State.COOLDOWN);
/* 195 */     setForcedState(false);
/* 196 */     this.chargeTime = this.maxChargeTime;
/* 197 */     startCooldown(player);
/* 198 */     if (!player.field_70170_p.field_72995_K) {
/* 199 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket((LivingEntity)player, this), (Entity)player);
/*     */     }
/*     */   }
/*     */   
/*     */   public String[] getChargedShouts() {
/* 204 */     String[] nameSplit = getDisplayNameOld().split(" ");
/* 205 */     int midPoint = (int)Math.ceil(nameSplit.length / 2.0D);
/* 206 */     StringBuilder sb = new StringBuilder();
/* 207 */     for (int i = 0; i < midPoint; i++)
/*     */     {
/* 209 */       sb.append(nameSplit[i] + " ");
/*     */     }
/* 211 */     String firstShout = sb.toString().replaceAll("[:-]", "");
/* 212 */     sb = new StringBuilder();
/* 213 */     for (int j = midPoint; j < nameSplit.length; j++)
/*     */     {
/* 215 */       sb.append(nameSplit[j] + " ");
/*     */     }
/* 217 */     String secondShout = sb.toString().replaceAll("[:-]", "");
/*     */     
/* 219 */     (new String[2])[0] = firstShout; (new String[2])[1] = secondShout; (new String[1])[0] = firstShout; return (secondShout.length() > 0) ? new String[2] : new String[1];
/*     */   }
/*     */   
/*     */   public static interface IOnEndCharging extends Serializable {
/*     */     boolean onEndCharging(PlayerEntity param1PlayerEntity);
/*     */   }
/*     */   
/*     */   public static interface IOnStartCharging extends Serializable {
/*     */     boolean onStartCharging(PlayerEntity param1PlayerEntity);
/*     */   }
/*     */   
/*     */   public static interface IDuringCharging extends Serializable {
/*     */     void duringCharging(PlayerEntity param1PlayerEntity, int param1Int);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\ChargeableAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */