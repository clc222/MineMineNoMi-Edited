/*     */ package xyz.pixelatedw.mineminenomi.packets.server.entities;
/*     */ 
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.network.NetworkDirection;
/*     */ import net.minecraftforge.fml.network.NetworkEvent;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ 
/*     */ public class SPinCameraPacket {
/*     */   private boolean clampYaw = false;
/*     */   private boolean clampPitch = false;
/*     */   private float initialYaw;
/*     */   private float initialPitch;
/*     */   private float maxYaw;
/*     */   private float maxPitch;
/*     */   private int ticks;
/*     */   
/*     */   public static SPinCameraPacket pinFixed() {
/*  27 */     SPinCameraPacket packet = new SPinCameraPacket();
/*  28 */     return packet;
/*     */   }
/*     */   
/*     */   public static SPinCameraPacket pinClampedYaw(float initialYaw, float maxYaw) {
/*  32 */     SPinCameraPacket packet = new SPinCameraPacket();
/*  33 */     packet.initialYaw = initialYaw % 360.0F;
/*  34 */     packet.maxYaw = maxYaw % 360.0F;
/*  35 */     packet.clampYaw = true;
/*  36 */     return packet;
/*     */   }
/*     */   
/*     */   public static SPinCameraPacket pinClampedPitch(float initialPitch, float maxPitch) {
/*  40 */     SPinCameraPacket packet = new SPinCameraPacket();
/*  41 */     packet.initialPitch = initialPitch % 360.0F;
/*  42 */     packet.maxPitch = maxPitch % 360.0F;
/*  43 */     packet.clampPitch = true;
/*  44 */     return packet;
/*     */   }
/*     */   
/*     */   public static SPinCameraPacket pinClampedYawAndPitch(float initialYaw, float maxYaw, float initialPitch, float maxPitch) {
/*  48 */     SPinCameraPacket packet = new SPinCameraPacket();
/*  49 */     packet.initialYaw = initialYaw % 360.0F;
/*  50 */     packet.initialPitch = initialPitch % 360.0F;
/*  51 */     packet.maxYaw = maxYaw % 360.0F;
/*  52 */     packet.maxPitch = maxPitch % 360.0F;
/*  53 */     packet.clampYaw = true;
/*  54 */     packet.clampPitch = true;
/*  55 */     return packet;
/*     */   }
/*     */   
/*     */   public SPinCameraPacket setTimed(int ticks) {
/*  59 */     this.ticks = ticks;
/*  60 */     return this;
/*     */   }
/*     */   
/*     */   public void encode(PacketBuffer buffer) {
/*  64 */     buffer.writeBoolean(this.clampYaw);
/*  65 */     buffer.writeBoolean(this.clampPitch);
/*  66 */     buffer.writeFloat(this.initialYaw);
/*  67 */     buffer.writeFloat(this.initialPitch);
/*  68 */     buffer.writeFloat(this.maxYaw);
/*  69 */     buffer.writeFloat(this.maxPitch);
/*  70 */     buffer.writeInt(this.ticks);
/*     */   }
/*     */   
/*     */   public static SPinCameraPacket decode(PacketBuffer buffer) {
/*  74 */     SPinCameraPacket msg = new SPinCameraPacket();
/*  75 */     msg.clampYaw = buffer.readBoolean();
/*  76 */     msg.clampPitch = buffer.readBoolean();
/*  77 */     msg.initialYaw = buffer.readFloat();
/*  78 */     msg.initialPitch = buffer.readFloat();
/*  79 */     msg.maxYaw = buffer.readFloat();
/*  80 */     msg.maxPitch = buffer.readFloat();
/*  81 */     msg.ticks = buffer.readInt();
/*  82 */     return msg;
/*     */   }
/*     */   
/*     */   public static void handle(SPinCameraPacket message, Supplier<NetworkEvent.Context> ctx) {
/*  86 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/*  87 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*     */     }
/*     */ 
/*     */     
/*  91 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*     */   }
/*     */   
/*     */   public static class ClientHandler {
/*     */     @OnlyIn(Dist.CLIENT)
/*     */     public static void handle(SPinCameraPacket message) {
/*  97 */       Minecraft mc = Minecraft.func_71410_x();
/*  98 */       ClientPlayerEntity player = mc.field_71439_g;
/*  99 */       ActiveRenderInfo camera = mc.field_71460_t.func_215316_n();
/*     */       
/* 101 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 102 */       if (props == null) {
/*     */         return;
/*     */       }
/*     */       
/* 106 */       props.pinCamera((PlayerEntity)player);
/*     */       
/* 108 */       if (message.clampYaw) {
/* 109 */         props.clampCameraYaw((PlayerEntity)player, message.initialYaw, message.maxYaw);
/*     */       }
/*     */       
/* 112 */       if (message.clampPitch) {
/* 113 */         props.clampCameraPitch((PlayerEntity)player, message.initialPitch, message.maxPitch);
/*     */       }
/*     */       
/* 116 */       props.setCameraPinTimer(message.ticks);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\entities\SPinCameraPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */