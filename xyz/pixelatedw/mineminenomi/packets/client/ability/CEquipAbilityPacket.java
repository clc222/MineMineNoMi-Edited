/*     */ package xyz.pixelatedw.mineminenomi.packets.client.ability;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.network.NetworkDirection;
/*     */ import net.minecraftforge.fml.network.NetworkEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DisableComponent;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SEquipAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class CEquipAbilityPacket {
/*     */   private int slot;
/*     */   
/*     */   public CEquipAbilityPacket(int id, AbilityCore core) {
/*  29 */     this.slot = id;
/*  30 */     this.abilityId = core.getRegistryName();
/*     */   }
/*     */   private ResourceLocation abilityId;
/*     */   public CEquipAbilityPacket() {}
/*     */   public void encode(PacketBuffer buffer) {
/*  35 */     buffer.writeInt(this.slot);
/*  36 */     buffer.func_192572_a(this.abilityId);
/*     */   }
/*     */ 
/*     */   
/*     */   public static CEquipAbilityPacket decode(PacketBuffer buffer) {
/*  41 */     CEquipAbilityPacket msg = new CEquipAbilityPacket();
/*  42 */     msg.slot = buffer.readInt();
/*  43 */     msg.abilityId = buffer.func_192575_l();
/*  44 */     return msg;
/*     */   }
/*     */   
/*     */   public static void handle(CEquipAbilityPacket message, Supplier<NetworkEvent.Context> ctx) {
/*  48 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER) {
/*  49 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*     */             try {
/*     */               ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*     */               
/*     */               if (serverPlayerEntity == null) {
/*     */                 return;
/*     */               }
/*     */               
/*     */               int maxBars = CommonConfig.INSTANCE.getAbilityBars() * 8;
/*     */               
/*     */               if (message.slot > maxBars) {
/*     */                 return;
/*     */               }
/*     */               
/*     */               IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
/*     */               
/*     */               Ability oldAbility = (Ability)abilityDataProps.getEquippedAbility(message.slot);
/*     */               
/*     */               if (oldAbility != null) {
/*     */                 if (oldAbility.hasComponent(ModAbilityKeys.COOLDOWN) && ((CooldownComponent)oldAbility.getComponent(ModAbilityKeys.COOLDOWN).get()).isOnCooldown()) {
/*     */                   return;
/*     */                 }
/*     */                 
/*     */                 if (oldAbility.hasComponent(ModAbilityKeys.DISABLE) && ((DisableComponent)oldAbility.getComponent(ModAbilityKeys.DISABLE).get()).isDisabled()) {
/*     */                   return;
/*     */                 }
/*     */                 
/*     */                 if (oldAbility.hasComponent(ModAbilityKeys.CONTINUOUS) && ((ContinuousComponent)oldAbility.getComponent(ModAbilityKeys.CONTINUOUS).get()).isContinuous()) {
/*     */                   return;
/*     */                 }
/*     */                 
/*     */                 if (oldAbility.hasComponent(ModAbilityKeys.CHARGE) && ((ChargeComponent)oldAbility.getComponent(ModAbilityKeys.CHARGE).get()).isCharging()) {
/*     */                   return;
/*     */                 }
/*     */               } 
/*     */               
/*     */               AbilityCore core = (AbilityCore)ModRegistries.ABILITIES.getValue(message.abilityId);
/*     */               if (core == null) {
/*     */                 return;
/*     */               }
/*     */               if (!abilityDataProps.hasUnlockedAbility(core)) {
/*     */                 return;
/*     */               }
/*     */               IAbility ability = core.createAbility();
/*     */               abilityDataProps.setEquippedAbility(message.slot, ability);
/*     */               WyNetwork.sendToAllTrackingAndSelf(new SEquipAbilityPacket(serverPlayerEntity.func_145782_y(), message.slot, core), (Entity)serverPlayerEntity);
/*  95 */             } catch (Exception e) {
/*     */               e.printStackTrace();
/*     */               return;
/*     */             } 
/*     */           });
/*     */     }
/* 101 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\ability\CEquipAbilityPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */