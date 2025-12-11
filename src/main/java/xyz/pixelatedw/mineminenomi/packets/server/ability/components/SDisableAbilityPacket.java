/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ability.components;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import java.util.function.Supplier;
/*    */ import java.util.stream.Collectors;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DisableComponent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class SDisableAbilityPacket {
/*    */   private int entityId;
/* 24 */   private Set<ResourceLocation> abilityIds = new HashSet<>();
/*    */   
/*    */   private int disableTicks;
/*    */   
/*    */   private boolean state;
/*    */   
/*    */   public SDisableAbilityPacket(int entityId, Set<IAbility> abilities, int disableTicks, boolean state) {
/* 31 */     this.entityId = entityId;
/* 32 */     this.abilityIds = (Set<ResourceLocation>)abilities.stream().map(a -> a.getCore().getRegistryName()).collect(Collectors.toSet());
/* 33 */     this.disableTicks = disableTicks;
/* 34 */     this.state = state;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 38 */     buffer.writeInt(this.entityId);
/* 39 */     buffer.writeInt(this.abilityIds.size());
/* 40 */     for (ResourceLocation rs : this.abilityIds) {
/* 41 */       buffer.func_192572_a(rs);
/*    */     }
/* 43 */     buffer.writeInt(this.disableTicks);
/* 44 */     buffer.writeBoolean(this.state);
/*    */   }
/*    */   
/*    */   public static SDisableAbilityPacket decode(PacketBuffer buffer) {
/* 48 */     SDisableAbilityPacket msg = new SDisableAbilityPacket();
/* 49 */     msg.entityId = buffer.readInt();
/* 50 */     int abilities = buffer.readInt();
/* 51 */     for (int i = 0; i < abilities; i++) {
/* 52 */       msg.abilityIds.add(buffer.func_192575_l());
/*    */     }
/* 54 */     msg.disableTicks = buffer.readInt();
/* 55 */     msg.state = buffer.readBoolean();
/* 56 */     return msg;
/*    */   }
/*    */   public SDisableAbilityPacket() {}
/*    */   public static void handle(SDisableAbilityPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 60 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 61 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */     
/* 65 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SDisableAbilityPacket message) {
/* 71 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/* 72 */       if (target == null || !(target instanceof PlayerEntity)) {
/*    */         return;
/*    */       }
/* 75 */       PlayerEntity player = (PlayerEntity)target;
/* 76 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/* 77 */       Set<IAbility> passiveAbilities = props.getPassiveAbilities(a -> message.abilityIds.contains(a.getCore().getRegistryName()));
/* 78 */       Set<IAbility> equippedAbilities = props.getEquippedAbilities(a -> message.abilityIds.contains(a.getCore().getRegistryName()));
/*    */       
/* 80 */       if (message.state) {
/*    */         
/* 82 */         passiveAbilities.stream()
/* 83 */           .filter(a -> a.hasComponent(ModAbilityKeys.DISABLE))
/* 84 */           .forEach(a -> a.getComponent(ModAbilityKeys.DISABLE).ifPresent(()));
/* 85 */         equippedAbilities.stream()
/* 86 */           .filter(a -> a.hasComponent(ModAbilityKeys.DISABLE))
/* 87 */           .forEach(a -> a.getComponent(ModAbilityKeys.DISABLE).ifPresent(()));
/*    */       }
/*    */       else {
/*    */         
/* 91 */         passiveAbilities.stream()
/* 92 */           .filter(a -> a.hasComponent(ModAbilityKeys.DISABLE))
/* 93 */           .forEach(a -> a.getComponent(ModAbilityKeys.DISABLE).ifPresent(()));
/* 94 */         equippedAbilities.stream()
/* 95 */           .filter(a -> a.hasComponent(ModAbilityKeys.DISABLE))
/* 96 */           .forEach(a -> a.getComponent(ModAbilityKeys.DISABLE).ifPresent(()));
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\components\SDisableAbilityPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */