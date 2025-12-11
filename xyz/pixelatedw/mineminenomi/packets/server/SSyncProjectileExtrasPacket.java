/*    */ package xyz.pixelatedw.mineminenomi.packets.server;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.projectile.ProjectileEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.projectilesextra.IProjectileExtras;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.projectilesextra.ProjectileExtrasCapability;
/*    */ 
/*    */ public class SSyncProjectileExtrasPacket
/*    */ {
/*    */   private int entityId;
/*    */   private INBT data;
/*    */   
/*    */   public SSyncProjectileExtrasPacket() {}
/*    */   
/*    */   public SSyncProjectileExtrasPacket(ProjectileEntity projectile) {
/* 25 */     this.data = (INBT)new CompoundNBT();
/* 26 */     IProjectileExtras props = ProjectileExtrasCapability.get((Entity)projectile);
/* 27 */     this.data = ProjectileExtrasCapability.INSTANCE.getStorage().writeNBT(ProjectileExtrasCapability.INSTANCE, props, null);
/* 28 */     this.entityId = projectile.func_145782_y();
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 32 */     buffer.writeInt(this.entityId);
/* 33 */     buffer.func_150786_a((CompoundNBT)this.data);
/*    */   }
/*    */   
/*    */   public static SSyncProjectileExtrasPacket decode(PacketBuffer buffer) {
/* 37 */     SSyncProjectileExtrasPacket msg = new SSyncProjectileExtrasPacket();
/*    */     
/* 39 */     msg.entityId = buffer.readInt();
/* 40 */     msg.data = (INBT)buffer.func_150793_b();
/*    */     
/* 42 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SSyncProjectileExtrasPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 46 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 47 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */     
/* 50 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SSyncProjectileExtrasPacket message) {
/* 56 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/*    */       
/* 58 */       if (target == null || !(target instanceof ProjectileEntity)) {
/*    */         return;
/*    */       }
/*    */       
/* 62 */       IProjectileExtras props = ProjectileExtrasCapability.get(target);
/* 63 */       ProjectileExtrasCapability.INSTANCE.getStorage().readNBT(ProjectileExtrasCapability.INSTANCE, props, null, message.data);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\SSyncProjectileExtrasPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */