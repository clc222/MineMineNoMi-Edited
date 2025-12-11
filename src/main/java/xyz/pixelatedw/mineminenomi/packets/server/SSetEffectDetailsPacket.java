/*    */ package xyz.pixelatedw.mineminenomi.packets.server;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.mixins.EffectInstanceMixin;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SSetEffectDetailsPacket
/*    */ {
/*    */   private int entityId;
/*    */   private int effectId;
/*    */   private int duration;
/*    */   
/*    */   public SSetEffectDetailsPacket() {}
/*    */   
/*    */   public SSetEffectDetailsPacket(int entityId, EffectInstance instance) {
/* 27 */     this.entityId = entityId;
/* 28 */     this.effectId = (byte)(Effect.func_188409_a(instance.func_188419_a()) & 0xFF);
/* 29 */     this.duration = instance.func_76459_b();
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 34 */     buffer.writeInt(this.entityId);
/* 35 */     buffer.writeInt(this.effectId);
/* 36 */     buffer.writeInt(this.duration);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SSetEffectDetailsPacket decode(PacketBuffer buffer) {
/* 41 */     SSetEffectDetailsPacket msg = new SSetEffectDetailsPacket();
/* 42 */     msg.entityId = buffer.readInt();
/* 43 */     msg.effectId = buffer.readInt();
/* 44 */     msg.duration = buffer.readInt();
/* 45 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SSetEffectDetailsPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 50 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 52 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 57 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SSetEffectDetailsPacket message) {
/* 65 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/*    */       
/* 67 */       if (target == null || !(target instanceof LivingEntity)) {
/*    */         return;
/*    */       }
/* 70 */       LivingEntity livingTarget = (LivingEntity)target;
/* 71 */       Effect effect = Effect.func_188412_a(message.effectId & 0xFF);
/*    */       
/* 73 */       if (livingTarget.func_70644_a(effect))
/* 74 */         ((EffectInstanceMixin)livingTarget.func_70660_b(effect)).setDuration(message.duration); 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\SSetEffectDetailsPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */