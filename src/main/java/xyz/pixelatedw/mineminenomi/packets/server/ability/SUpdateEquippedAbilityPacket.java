/*     */ package xyz.pixelatedw.mineminenomi.packets.server.ability;
/*     */ 
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.network.NetworkDirection;
/*     */ import net.minecraftforge.fml.network.NetworkEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ChargeableAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ 
/*     */ 
/*     */ public class SUpdateEquippedAbilityPacket
/*     */ {
/*     */   private int entityId;
/*     */   private int abilityId;
/*     */   private ResourceLocation customTexture;
/*     */   private boolean isStateForced;
/*  31 */   private int abilityType = 0;
/*     */ 
/*     */   
/*     */   private CompoundNBT extraData;
/*     */ 
/*     */   
/*     */   private double cooldown;
/*     */   
/*     */   private double maxCooldown;
/*     */   
/*     */   private double disableTicks;
/*     */   
/*     */   private int state;
/*     */   
/*     */   private double continueTime;
/*     */   
/*     */   private double threshold;
/*     */   
/*     */   private double chargeTime;
/*     */   
/*     */   private double maxChargeTime;
/*     */   
/*     */   private boolean isPaused;
/*     */ 
/*     */   
/*     */   public SUpdateEquippedAbilityPacket(LivingEntity player, Ability ability) {
/*  57 */     IAbilityData props = AbilityDataCapability.get(player);
/*     */     
/*  59 */     this.entityId = player.func_145782_y();
/*  60 */     this.abilityId = props.getEquippedAbilitySlot((IAbility)ability);
/*  61 */     this.customTexture = ability.getIcon(player);
/*  62 */     this.state = ability.getState().ordinal();
/*  63 */     this.isStateForced = ability.isStateForced();
/*     */     
/*  65 */     if (ability instanceof IExtraUpdateData) {
/*  66 */       this.extraData = ((IExtraUpdateData)ability).getExtraData();
/*     */     }
/*  68 */     if (this.state == Ability.State.COOLDOWN.ordinal()) {
/*     */       
/*  70 */       this.cooldown = ability.getCooldown() / 20.0D;
/*  71 */       this.maxCooldown = ability.getMaxCooldown() / 20.0D;
/*     */     } 
/*     */     
/*  74 */     if (ability instanceof ContinuousAbility) {
/*     */       
/*  76 */       this.abilityType = 1;
/*  77 */       this.continueTime = (((ContinuousAbility)ability).getContinueTime() / 20);
/*  78 */       this.threshold = (((ContinuousAbility)ability).getThreshold() / 20);
/*     */     }
/*  80 */     else if (ability instanceof ChargeableAbility) {
/*     */       
/*  82 */       this.abilityType = 2;
/*  83 */       this.chargeTime = (((ChargeableAbility)ability).getChargeTime() / 20);
/*  84 */       this.maxChargeTime = (((ChargeableAbility)ability).getMaxChargeTime() / 20);
/*     */     }
/*  86 */     else if (ability instanceof PassiveAbility) {
/*     */       
/*  88 */       this.abilityType = 3;
/*  89 */       this.isPaused = ((PassiveAbility)ability).isPaused();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public SUpdateEquippedAbilityPacket(PlayerEntity player, Ability ability, Ability.State state, double[] values) {
/*  95 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  97 */     this.entityId = player.func_145782_y();
/*  98 */     this.abilityId = props.getEquippedAbilitySlot((IAbility)ability);
/*  99 */     this.customTexture = ability.getIcon(player);
/* 100 */     this.state = state.ordinal();
/* 101 */     this.isStateForced = ability.isStateForced();
/*     */     
/* 103 */     if (ability instanceof IExtraUpdateData) {
/* 104 */       this.extraData = ((IExtraUpdateData)ability).getExtraData();
/*     */     }
/* 106 */     if (state == Ability.State.COOLDOWN) {
/*     */       
/* 108 */       this.cooldown = values[0];
/* 109 */       this.maxCooldown = values[1];
/*     */     }
/* 111 */     else if (state == Ability.State.DISABLED) {
/*     */       
/* 113 */       this.disableTicks = values[0];
/*     */     }
/* 115 */     else if (state == Ability.State.CONTINUOUS) {
/*     */       
/* 117 */       this.abilityType = 1;
/* 118 */       this.continueTime = values[0];
/* 119 */       this.threshold = values[1];
/*     */     }
/* 121 */     else if (state == Ability.State.CHARGING) {
/*     */       
/* 123 */       this.abilityType = 2;
/* 124 */       this.chargeTime = values[0];
/* 125 */       this.maxChargeTime = values[1];
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void encode(PacketBuffer buffer) {
/* 131 */     buffer.writeInt(this.entityId);
/* 132 */     buffer.writeInt(this.abilityId);
/* 133 */     buffer.func_192572_a(this.customTexture);
/* 134 */     buffer.writeInt(this.abilityType);
/* 135 */     buffer.writeBoolean(this.isStateForced);
/*     */     
/* 137 */     buffer.writeBoolean((this.extraData != null));
/* 138 */     if (this.extraData != null) {
/* 139 */       buffer.func_150786_a(this.extraData);
/*     */     }
/* 141 */     buffer.writeDouble(this.cooldown);
/* 142 */     buffer.writeDouble(this.maxCooldown);
/* 143 */     buffer.writeDouble(this.disableTicks);
/* 144 */     buffer.writeInt(this.state);
/*     */     
/* 146 */     if (this.abilityType == 1) {
/*     */       
/* 148 */       buffer.writeDouble(this.continueTime);
/* 149 */       buffer.writeDouble(this.threshold);
/*     */     }
/* 151 */     else if (this.abilityType == 2) {
/*     */       
/* 153 */       buffer.writeDouble(this.chargeTime);
/* 154 */       buffer.writeDouble(this.maxChargeTime);
/*     */     }
/* 156 */     else if (this.abilityType == 3) {
/*     */       
/* 158 */       buffer.writeBoolean(this.isPaused);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static SUpdateEquippedAbilityPacket decode(PacketBuffer buffer) {
/* 164 */     SUpdateEquippedAbilityPacket msg = new SUpdateEquippedAbilityPacket();
/* 165 */     msg.entityId = buffer.readInt();
/* 166 */     msg.abilityId = buffer.readInt();
/* 167 */     msg.customTexture = buffer.func_192575_l();
/* 168 */     msg.abilityType = buffer.readInt();
/* 169 */     msg.isStateForced = buffer.readBoolean();
/*     */     
/* 171 */     boolean hasExtraData = buffer.readBoolean();
/* 172 */     if (hasExtraData) {
/* 173 */       msg.extraData = buffer.func_150793_b();
/*     */     }
/* 175 */     msg.cooldown = buffer.readDouble();
/* 176 */     msg.maxCooldown = buffer.readDouble();
/* 177 */     msg.disableTicks = buffer.readDouble();
/* 178 */     msg.state = buffer.readInt();
/*     */     
/* 180 */     if (msg.abilityType == 1) {
/*     */       
/* 182 */       msg.continueTime = buffer.readDouble();
/* 183 */       msg.threshold = buffer.readDouble();
/*     */     }
/* 185 */     else if (msg.abilityType == 2) {
/*     */       
/* 187 */       msg.chargeTime = buffer.readDouble();
/* 188 */       msg.maxChargeTime = buffer.readDouble();
/*     */     }
/* 190 */     else if (msg.abilityType == 3) {
/*     */       
/* 192 */       msg.isPaused = buffer.readBoolean();
/*     */     } 
/*     */     
/* 195 */     return msg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void handle(SUpdateEquippedAbilityPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 200 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*     */     {
/* 202 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 207 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*     */   }
/*     */   
/*     */   public SUpdateEquippedAbilityPacket() {}
/*     */   
/*     */   public static class ClientHandler {
/*     */     @OnlyIn(Dist.CLIENT)
/*     */     public static void handle(SUpdateEquippedAbilityPacket message) {
/* 215 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/* 216 */       if (target == null || !(target instanceof PlayerEntity) || message.abilityId < 0) {
/*     */         return;
/*     */       }
/* 219 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)target);
/* 220 */       Ability ability = (Ability)props.getEquippedAbility(message.abilityId);
/*     */       
/* 222 */       if (ability == null) {
/*     */         return;
/*     */       }
/* 225 */       Ability.State state = Ability.State.values()[message.state];
/* 226 */       ability.setDisplayIcon(message.customTexture);
/* 227 */       ability.setState(state);
/*     */       
/* 229 */       ability.setForcedState(message.isStateForced);
/*     */       
/* 231 */       if (message.extraData != null && ability instanceof IExtraUpdateData) {
/* 232 */         ((IExtraUpdateData)ability).setExtraData(message.extraData);
/*     */       }
/* 234 */       if (state == Ability.State.COOLDOWN) {
/*     */         
/* 236 */         ability.setCooldown(message.cooldown);
/* 237 */         ability.setMaxCooldown(message.maxCooldown);
/*     */       }
/* 239 */       else if (state == Ability.State.DISABLED) {
/*     */         
/* 241 */         ability.setDisableTicks(message.disableTicks);
/*     */       } 
/*     */       
/* 244 */       if (ability instanceof ContinuousAbility) {
/*     */         
/* 246 */         ((ContinuousAbility)ability).setContinueTime((int)message.continueTime);
/* 247 */         ((ContinuousAbility)ability).setThreshold((int)message.threshold);
/*     */       }
/* 249 */       else if (ability instanceof ChargeableAbility) {
/*     */         
/* 251 */         ((ChargeableAbility)ability).setChargeTime((int)message.chargeTime);
/* 252 */         ((ChargeableAbility)ability).setMaxChargeTime(message.maxChargeTime);
/*     */       }
/* 254 */       else if (ability instanceof PassiveAbility) {
/*     */         
/* 256 */         ((PassiveAbility)ability).setPause(message.isPaused);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\SUpdateEquippedAbilityPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */