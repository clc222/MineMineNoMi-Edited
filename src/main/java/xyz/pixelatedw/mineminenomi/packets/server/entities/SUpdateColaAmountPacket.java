/*    */ package xyz.pixelatedw.mineminenomi.packets.server.entities;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ 
/*    */ public class SUpdateColaAmountPacket
/*    */ {
/*    */   private int entityId;
/*    */   private int cola;
/*    */   private int maxCola;
/*    */   private int ultraCola;
/*    */   
/*    */   public SUpdateColaAmountPacket() {}
/*    */   
/*    */   public SUpdateColaAmountPacket(LivingEntity entity) {
/* 26 */     this.entityId = entity.func_145782_y();
/* 27 */     IEntityStats props = EntityStatsCapability.get(entity);
/* 28 */     this.cola = props.getCola();
/* 29 */     this.maxCola = props.getMaxCola();
/* 30 */     this.ultraCola = props.getUltraCola();
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 34 */     buffer.writeInt(this.entityId);
/* 35 */     buffer.writeInt(this.cola);
/* 36 */     buffer.writeInt(this.maxCola);
/* 37 */     buffer.writeInt(this.ultraCola);
/*    */   }
/*    */   
/*    */   public static SUpdateColaAmountPacket decode(PacketBuffer buffer) {
/* 41 */     SUpdateColaAmountPacket msg = new SUpdateColaAmountPacket();
/* 42 */     msg.entityId = buffer.readInt();
/* 43 */     msg.cola = buffer.readInt();
/* 44 */     msg.maxCola = buffer.readInt();
/* 45 */     msg.ultraCola = buffer.readInt();
/* 46 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SUpdateColaAmountPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 50 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 51 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */     
/* 55 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SUpdateColaAmountPacket message) {
/* 61 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/* 62 */       if (target == null || !(target instanceof LivingEntity)) {
/*    */         return;
/*    */       }
/*    */       
/* 66 */       LivingEntity entity = (LivingEntity)target;
/* 67 */       IEntityStats props = EntityStatsCapability.get(entity);
/*    */       
/* 69 */       int cola = MathHelper.func_76125_a(message.cola, 0, message.maxCola);
/*    */       
/* 71 */       props.setCola(cola);
/* 72 */       props.setUltraCola(message.ultraCola);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\entities\SUpdateColaAmountPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */