/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ability.components;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Iterator;
/*    */ import java.util.Set;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SkinOverlayComponent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class SSetOverlayLayersPacket
/*    */ {
/*    */   private int entityId;
/*    */   private int abilityId;
/*    */   private Set<Integer> layers;
/*    */   
/*    */   public SSetOverlayLayersPacket() {}
/*    */   
/*    */   public SSetOverlayLayersPacket(LivingEntity entity, IAbility ability, Set<Integer> layers) {
/* 30 */     this.entityId = entity.func_145782_y();
/* 31 */     this.abilityId = AbilityDataCapability.get(entity).getEquippedAbilitySlot(ability);
/* 32 */     this.layers = layers;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 36 */     buffer.writeInt(this.entityId);
/* 37 */     buffer.writeInt(this.abilityId);
/* 38 */     buffer.writeInt(this.layers.size());
/* 39 */     for (Iterator<Integer> iterator = this.layers.iterator(); iterator.hasNext(); ) { int hash = ((Integer)iterator.next()).intValue();
/* 40 */       buffer.writeInt(hash); }
/*    */   
/*    */   }
/*    */   
/*    */   public static SSetOverlayLayersPacket decode(PacketBuffer buffer) {
/* 45 */     SSetOverlayLayersPacket msg = new SSetOverlayLayersPacket();
/* 46 */     msg.entityId = buffer.readInt();
/* 47 */     msg.abilityId = buffer.readInt();
/* 48 */     int size = buffer.readInt();
/* 49 */     msg.layers = new HashSet<>();
/* 50 */     for (int i = 0; i < size; i++) {
/* 51 */       msg.layers.add(Integer.valueOf(buffer.readInt()));
/*    */     }
/* 53 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SSetOverlayLayersPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 57 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 58 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */     
/* 62 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SSetOverlayLayersPacket message) {
/* 68 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/* 69 */       if (target == null || !(target instanceof LivingEntity)) {
/*    */         return;
/*    */       }
/*    */       
/* 73 */       LivingEntity entity = (LivingEntity)target;
/* 74 */       IAbilityData props = AbilityDataCapability.get(entity);
/* 75 */       IAbility abl = props.getEquippedAbility(message.abilityId);
/* 76 */       if (abl == null) {
/*    */         return;
/*    */       }
/*    */       
/* 80 */       abl.getComponent(ModAbilityKeys.SKIN_OVERLAY).ifPresent(c -> c.setShownOverlays(message.layers));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\components\SSetOverlayLayersPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */