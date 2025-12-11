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
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SUpdateLightningEntity
/*    */ {
/*    */   private int entityId;
/*    */   private float length;
/*    */   private float size;
/*    */   private int branches;
/*    */   private int segments;
/*    */   private int color;
/*    */   private int alpha;
/*    */   private int angle;
/*    */   private boolean mimicVanilla;
/*    */   private boolean lightningMovement;
/*    */   private boolean energyEffect;
/*    */   
/*    */   public SUpdateLightningEntity() {}
/*    */   
/*    */   public SUpdateLightningEntity(LightningEntity entity) {
/* 33 */     this.entityId = entity.func_145782_y();
/* 34 */     this.length = entity.getLength();
/* 35 */     this.size = entity.getSize();
/* 36 */     this.branches = entity.getBranches();
/* 37 */     this.segments = entity.getSegments();
/* 38 */     this.color = entity.getColor();
/* 39 */     this.alpha = entity.getAlpha();
/* 40 */     this.angle = entity.getAngle();
/* 41 */     this.mimicVanilla = entity.getMimicVanilla();
/* 42 */     this.lightningMovement = entity.getLightningMovement();
/* 43 */     this.energyEffect = entity.getEnergyEffect();
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 47 */     buffer.writeInt(this.entityId);
/* 48 */     buffer.writeFloat(this.length);
/* 49 */     buffer.writeFloat(this.size);
/* 50 */     buffer.writeInt(this.branches);
/* 51 */     buffer.writeInt(this.segments);
/* 52 */     buffer.writeInt(this.color);
/* 53 */     buffer.writeInt(this.alpha);
/* 54 */     buffer.writeInt(this.angle);
/* 55 */     buffer.writeBoolean(this.mimicVanilla);
/* 56 */     buffer.writeBoolean(this.lightningMovement);
/* 57 */     buffer.writeBoolean(this.energyEffect);
/*    */   }
/*    */   
/*    */   public static SUpdateLightningEntity decode(PacketBuffer buffer) {
/* 61 */     SUpdateLightningEntity msg = new SUpdateLightningEntity();
/*    */     
/* 63 */     msg.entityId = buffer.readInt();
/* 64 */     msg.length = buffer.readFloat();
/* 65 */     msg.size = buffer.readFloat();
/* 66 */     msg.branches = buffer.readInt();
/* 67 */     msg.segments = buffer.readInt();
/* 68 */     msg.color = buffer.readInt();
/* 69 */     msg.alpha = buffer.readInt();
/* 70 */     msg.angle = buffer.readInt();
/* 71 */     msg.mimicVanilla = buffer.readBoolean();
/* 72 */     msg.lightningMovement = buffer.readBoolean();
/* 73 */     msg.energyEffect = buffer.readBoolean();
/*    */     
/* 75 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SUpdateLightningEntity message, Supplier<NetworkEvent.Context> ctx) {
/* 79 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 80 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */     
/* 83 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SUpdateLightningEntity message) {
/* 89 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/*    */       
/* 91 */       if (target == null || !(target instanceof LightningEntity)) {
/*    */         return;
/*    */       }
/*    */       
/* 95 */       LightningEntity proj = (LightningEntity)target;
/*    */       
/* 97 */       proj.setLightningProperties(message.length, message.size, message.branches, message.segments, message.color, message.alpha, message.angle, message.mimicVanilla, message.lightningMovement, message.energyEffect);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\entities\SUpdateLightningEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */