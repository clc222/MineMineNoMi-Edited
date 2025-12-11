/*    */ package xyz.pixelatedw.mineminenomi.packets.server;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import java.util.function.Supplier;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*    */ 
/*    */ 
/*    */ public class SLeashPlayerPacket
/*    */ {
/*    */   private UUID leashedId;
/*    */   private UUID leashHolderId;
/*    */   
/*    */   public SLeashPlayerPacket() {}
/*    */   
/*    */   public SLeashPlayerPacket(PlayerEntity leashedEntity, @Nullable PlayerEntity leashHolder) {
/* 27 */     this.leashedId = leashedEntity.func_110124_au();
/* 28 */     if (leashHolder != null) {
/* 29 */       this.leashHolderId = leashHolder.func_110124_au();
/*    */     } else {
/*    */       
/* 32 */       this.leashHolderId = ModValues.NIL_UUID;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 37 */     buffer.func_179252_a(this.leashedId);
/* 38 */     buffer.func_179252_a(this.leashHolderId);
/*    */   }
/*    */   
/*    */   public static SLeashPlayerPacket decode(PacketBuffer buffer) {
/* 42 */     SLeashPlayerPacket msg = new SLeashPlayerPacket();
/* 43 */     msg.leashedId = buffer.func_179253_g();
/* 44 */     msg.leashHolderId = buffer.func_179253_g();
/* 45 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SLeashPlayerPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 49 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 50 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/* 52 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SLeashPlayerPacket message) {
/* 58 */       Minecraft mc = Minecraft.func_71410_x();
/*    */       
/* 60 */       UUID leashHolderId = message.leashHolderId;
/* 61 */       UUID leashedId = message.leashedId;
/*    */       
/* 63 */       PlayerEntity leashedEntity = mc.field_71441_e.func_217371_b(leashedId);
/*    */       
/* 65 */       if (leashedEntity == null) {
/*    */         return;
/*    */       }
/*    */       
/* 69 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)leashedEntity);
/*    */       
/* 71 */       if (!leashHolderId.equals(ModValues.NIL_UUID)) {
/* 72 */         PlayerEntity leashHolder = mc.field_71441_e.func_217371_b(message.leashHolderId);
/*    */         
/* 74 */         if (leashHolder == null) {
/* 75 */           props.dropLeash();
/*    */           
/*    */           return;
/*    */         } 
/* 79 */         props.setLeashedTo((LivingEntity)leashHolder);
/*    */       } else {
/*    */         
/* 82 */         props.dropLeash();
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\SLeashPlayerPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */