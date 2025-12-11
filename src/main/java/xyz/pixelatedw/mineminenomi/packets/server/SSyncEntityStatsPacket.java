/*    */ package xyz.pixelatedw.mineminenomi.packets.server;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SSyncEntityStatsPacket
/*    */ {
/*    */   private int entityId;
/*    */   private INBT data;
/*    */   
/*    */   public SSyncEntityStatsPacket() {}
/*    */   
/*    */   public SSyncEntityStatsPacket(int entityId, IEntityStats props) {
/* 31 */     this.data = (INBT)new CompoundNBT();
/* 32 */     this.data = EntityStatsCapability.INSTANCE.getStorage().writeNBT(EntityStatsCapability.INSTANCE, props, null);
/* 33 */     this.entityId = entityId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 38 */     buffer.writeInt(this.entityId);
/* 39 */     buffer.func_150786_a((CompoundNBT)this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SSyncEntityStatsPacket decode(PacketBuffer buffer) {
/* 44 */     SSyncEntityStatsPacket msg = new SSyncEntityStatsPacket();
/* 45 */     msg.entityId = buffer.readInt();
/* 46 */     msg.data = (INBT)buffer.func_150793_b();
/* 47 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SSyncEntityStatsPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 52 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 54 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 59 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SSyncEntityStatsPacket message) {
/* 67 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/* 68 */       if (target == null || !(target instanceof LivingEntity)) {
/*    */         return;
/*    */       }
/* 71 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)target);
/* 72 */       EntityStatsCapability.INSTANCE.getStorage().readNBT(EntityStatsCapability.INSTANCE, props, null, message.data);
/* 73 */       if (target instanceof PlayerEntity)
/* 74 */         AttributeHelper.updateHPAttribute((PlayerEntity)target); 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\SSyncEntityStatsPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */