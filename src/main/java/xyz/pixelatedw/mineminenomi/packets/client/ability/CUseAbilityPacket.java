/*     */ package xyz.pixelatedw.mineminenomi.packets.client.ability;
/*     */ 
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.fml.network.NetworkDirection;
/*     */ import net.minecraftforge.fml.network.NetworkEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ChargeableAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.ability.AbilityCanUseEvent;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.events.passives.DoaPassiveEvents;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CUseAbilityPacket
/*     */ {
/*     */   private int slot;
/*     */   
/*     */   public CUseAbilityPacket() {}
/*     */   
/*     */   public CUseAbilityPacket(int slot) {
/*  29 */     this.slot = slot;
/*     */   }
/*     */   
/*     */   public void encode(PacketBuffer buffer) {
/*  33 */     buffer.writeInt(this.slot);
/*     */   }
/*     */   
/*     */   public static CUseAbilityPacket decode(PacketBuffer buffer) {
/*  37 */     CUseAbilityPacket msg = new CUseAbilityPacket();
/*     */     
/*  39 */     msg.slot = buffer.readInt();
/*     */     
/*  41 */     return msg;
/*     */   }
/*     */   
/*     */   public static void handle(CUseAbilityPacket message, Supplier<NetworkEvent.Context> ctx) {
/*  45 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER) {
/*  46 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*     */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*     */ 
/*     */ 
/*     */             
/*     */             int maxBars = CommonConfig.INSTANCE.getAbilityBars() * 8;
/*     */ 
/*     */ 
/*     */             
/*     */             if (message.slot > maxBars) {
/*     */               return;
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             ((PlayerEntity)serverPlayerEntity).field_70170_p.func_217381_Z().func_76320_a("abilityUse");
/*     */ 
/*     */ 
/*     */             
/*     */             IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
/*     */ 
/*     */ 
/*     */             
/*     */             Ability abl = (Ability)abilityDataProps.getEquippedAbility(message.slot);
/*     */ 
/*     */ 
/*     */             
/*     */             AbilityCanUseEvent pre = new AbilityCanUseEvent((PlayerEntity)serverPlayerEntity, abl);
/*     */ 
/*     */ 
/*     */             
/*     */             if (MinecraftForge.EVENT_BUS.post((Event)pre)) {
/*     */               return;
/*     */             }
/*     */ 
/*     */             
/*     */             if (abl == null || serverPlayerEntity.func_175149_v()) {
/*     */               return;
/*     */             }
/*     */ 
/*     */             
/*     */             if (message.slot > 8 * CommonConfig.INSTANCE.getAbilityBars()) {
/*     */               return;
/*     */             }
/*     */ 
/*     */             
/*     */             try {
/*     */               if (!(abl instanceof xyz.pixelatedw.mineminenomi.abilities.doa.AirDoorAbility) && DoaPassiveEvents.isInsideDoor((PlayerEntity)serverPlayerEntity)) {
/*     */                 return;
/*     */               }
/*     */ 
/*     */               
/*     */               if (abl instanceof ChargeableAbility && abl.isCharging() && !((ChargeableAbility)abl).isCancelable()) {
/*     */                 return;
/*     */               }
/*     */ 
/*     */               
/*     */               if (!(abl instanceof xyz.pixelatedw.mineminenomi.api.abilities.MorphAbility) || !abl.isContinuous());
/*     */ 
/*     */               
/*     */               abl.use((PlayerEntity)serverPlayerEntity);
/* 107 */             } catch (Exception e) {
/*     */               e.printStackTrace();
/*     */               
/*     */               abl.startCooldown((PlayerEntity)serverPlayerEntity);
/*     */             } 
/*     */             
/*     */             ((PlayerEntity)serverPlayerEntity).field_70170_p.func_217381_Z().func_76319_b();
/*     */           });
/*     */     }
/* 116 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\ability\CUseAbilityPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */