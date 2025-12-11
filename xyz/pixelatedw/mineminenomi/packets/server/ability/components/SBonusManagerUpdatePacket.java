/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ability.components;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.UUID;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponentKey;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusManager;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SBonusManagerUpdatePacket
/*    */ {
/*    */   private int entityId;
/*    */   private int abilityId;
/*    */   private AbilityComponentKey<?> key;
/*    */   private Map<UUID, BonusManager.BonusValue> bonuses;
/*    */   
/*    */   public SBonusManagerUpdatePacket() {}
/*    */   
/*    */   public SBonusManagerUpdatePacket(LivingEntity entity, IAbility ability, AbilityComponent comp, Map<UUID, BonusManager.BonusValue> bonuses) {
/* 35 */     this.entityId = entity.func_145782_y();
/* 36 */     this.abilityId = AbilityDataCapability.get(entity).getEquippedAbilitySlot(ability);
/* 37 */     this.key = comp.getKey();
/* 38 */     this.bonuses = bonuses;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 42 */     buffer.writeInt(this.entityId);
/* 43 */     buffer.writeInt(this.abilityId);
/* 44 */     buffer.func_192572_a(this.key.getId());
/* 45 */     int size = this.bonuses.size();
/* 46 */     buffer.writeInt(size);
/* 47 */     this.bonuses.forEach((id, val) -> {
/*    */           buffer.func_179252_a(id);
/*    */           int nameLen = val.getName().length();
/*    */           buffer.writeInt(nameLen);
/*    */           buffer.func_211400_a(null, nameLen);
/*    */           buffer.writeInt(val.getType().ordinal());
/*    */           buffer.writeFloat(val.getValue());
/*    */         });
/*    */   }
/*    */   
/*    */   public static SBonusManagerUpdatePacket decode(PacketBuffer buffer) {
/* 58 */     SBonusManagerUpdatePacket msg = new SBonusManagerUpdatePacket();
/* 59 */     msg.entityId = buffer.readInt();
/* 60 */     msg.abilityId = buffer.readInt();
/* 61 */     msg.key = new AbilityComponentKey(buffer.func_192575_l());
/* 62 */     int size = buffer.readInt();
/* 63 */     msg.bonuses = new HashMap<>();
/* 64 */     for (int i = 0; i < size; i++) {
/* 65 */       UUID id = buffer.func_179253_g();
/* 66 */       int nameLen = buffer.readInt();
/* 67 */       String name = buffer.func_150789_c(nameLen);
/* 68 */       BonusOperation op = BonusOperation.values()[buffer.readInt()];
/* 69 */       float value = buffer.readFloat();
/* 70 */       msg.bonuses.put(id, new BonusManager.BonusValue(name, op, value));
/*    */     } 
/* 72 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SBonusManagerUpdatePacket message, Supplier<NetworkEvent.Context> ctx) {
/* 76 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 77 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */     
/* 81 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SBonusManagerUpdatePacket message) {
/* 87 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/* 88 */       if (target == null || !(target instanceof LivingEntity)) {
/*    */         return;
/*    */       }
/*    */       
/* 92 */       LivingEntity entity = (LivingEntity)target;
/* 93 */       IAbilityData props = AbilityDataCapability.get(entity);
/* 94 */       IAbility abl = props.getEquippedAbility(message.abilityId);
/* 95 */       if (abl == null) {
/*    */         return;
/*    */       }
/*    */       
/* 99 */       abl.getComponent(message.key).ifPresent(comp -> comp.getBonusManagers().forEachRemaining(()));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\components\SBonusManagerUpdatePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */