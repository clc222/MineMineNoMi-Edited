/*    */ package xyz.pixelatedw.mineminenomi.packets.server.entities;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class SUpdateProjBlockCollisionBox
/*    */ {
/*    */   private int entityId;
/*    */   private double sizeX;
/*    */   private double sizeY;
/*    */   private double sizeZ;
/*    */   
/*    */   public SUpdateProjBlockCollisionBox() {}
/*    */   
/*    */   public SUpdateProjBlockCollisionBox(int entityId, double sizeX, double sizeY, double sizeZ) {
/* 23 */     this.entityId = entityId;
/* 24 */     this.sizeX = sizeX;
/* 25 */     this.sizeY = sizeY;
/* 26 */     this.sizeZ = sizeZ;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 30 */     buffer.writeInt(this.entityId);
/* 31 */     buffer.writeDouble(this.sizeX);
/* 32 */     buffer.writeDouble(this.sizeY);
/* 33 */     buffer.writeDouble(this.sizeZ);
/*    */   }
/*    */   
/*    */   public static SUpdateProjBlockCollisionBox decode(PacketBuffer buffer) {
/* 37 */     SUpdateProjBlockCollisionBox msg = new SUpdateProjBlockCollisionBox();
/*    */     
/* 39 */     msg.entityId = buffer.readInt();
/* 40 */     msg.sizeX = buffer.readDouble();
/* 41 */     msg.sizeY = buffer.readDouble();
/* 42 */     msg.sizeZ = buffer.readDouble();
/*    */     
/* 44 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SUpdateProjBlockCollisionBox message, Supplier<NetworkEvent.Context> ctx) {
/* 48 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 49 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */     
/* 52 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SUpdateProjBlockCollisionBox message) {
/* 58 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/*    */       
/* 60 */       if (target == null || !(target instanceof AbilityProjectileEntity)) {
/*    */         return;
/*    */       }
/*    */       
/* 64 */       AbilityProjectileEntity proj = (AbilityProjectileEntity)target;
/*    */       
/* 66 */       proj.setBlockCollisionSize(message.sizeX, message.sizeY, message.sizeZ);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\entities\SUpdateProjBlockCollisionBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */