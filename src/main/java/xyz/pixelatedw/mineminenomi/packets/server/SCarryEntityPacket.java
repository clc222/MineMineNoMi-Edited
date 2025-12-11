/*    */ package xyz.pixelatedw.mineminenomi.packets.server;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ 
/*    */ public class SCarryEntityPacket
/*    */ {
/*    */   private int targetId;
/*    */   
/*    */   public SCarryEntityPacket() {}
/*    */   
/*    */   public SCarryEntityPacket(@Nullable LivingEntity target) {
/* 24 */     if (target != null) {
/* 25 */       this.targetId = target.func_145782_y();
/*    */     } else {
/*    */       
/* 28 */       this.targetId = -1;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 33 */     buffer.writeInt(this.targetId);
/*    */   }
/*    */   
/*    */   public static SCarryEntityPacket decode(PacketBuffer buffer) {
/* 37 */     SCarryEntityPacket msg = new SCarryEntityPacket();
/* 38 */     msg.targetId = buffer.readInt();
/* 39 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SCarryEntityPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 43 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 44 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/* 46 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SCarryEntityPacket message) {
/* 52 */       Minecraft mc = Minecraft.func_71410_x();
/* 53 */       ClientPlayerEntity player = mc.field_71439_g;
/*    */       
/* 55 */       if (player == null) {
/*    */         return;
/*    */       }
/*    */       
/* 59 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 60 */       int targetId = message.targetId;
/*    */       
/* 62 */       if (targetId >= 0) {
/* 63 */         Entity entity = mc.field_71441_e.func_73045_a(message.targetId);
/*    */         
/* 65 */         if (entity == null || !(entity instanceof LivingEntity)) {
/* 66 */           props.stopCarrying();
/*    */           
/*    */           return;
/*    */         } 
/* 70 */         LivingEntity living = (LivingEntity)entity;
/*    */         
/* 72 */         props.startCarrying(living);
/*    */       } else {
/*    */         
/* 75 */         props.stopCarrying();
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\SCarryEntityPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */