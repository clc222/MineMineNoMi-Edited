/*    */ package xyz.pixelatedw.mineminenomi.packets.server.animations;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.IAnimatedEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SSetNPCTimeAnimationPacket
/*    */ {
/*    */   private int entityId;
/*    */   private int animation;
/*    */   private int state;
/*    */   
/*    */   public SSetNPCTimeAnimationPacket() {}
/*    */   
/*    */   public SSetNPCTimeAnimationPacket(int entityId, int animationId, TimeAnimation.State state) {
/* 29 */     this.entityId = entityId;
/* 30 */     this.animation = animationId;
/* 31 */     this.state = state.ordinal();
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 36 */     buffer.writeInt(this.entityId);
/* 37 */     buffer.writeInt(this.animation);
/* 38 */     buffer.writeInt(this.state);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SSetNPCTimeAnimationPacket decode(PacketBuffer buffer) {
/* 43 */     SSetNPCTimeAnimationPacket msg = new SSetNPCTimeAnimationPacket();
/* 44 */     msg.entityId = buffer.readInt();
/* 45 */     msg.animation = buffer.readInt();
/* 46 */     msg.state = buffer.readInt();
/* 47 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SSetNPCTimeAnimationPacket message, Supplier<NetworkEvent.Context> ctx) {
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
/*    */     public static void handle(SSetNPCTimeAnimationPacket message) {
/* 67 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/* 68 */       if (target == null || !(target instanceof LivingEntity) || !(target instanceof IAnimatedEntity)) {
/*    */         return;
/*    */       }
/* 71 */       LivingEntity target2 = (LivingEntity)target;
/* 72 */       int animId = message.animation - 1;
/* 73 */       if (animId < 0) {
/*    */         return;
/*    */       }
/* 76 */       IAnimation anim = ((IAnimatedEntity)target2).getAnimations()[animId];
/*    */       
/* 78 */       if (!(anim instanceof TimeAnimation)) {
/*    */         return;
/*    */       }
/* 81 */       TimeAnimation.State state = TimeAnimation.State.values()[message.state];
/* 82 */       switch (state) {
/*    */         
/*    */         case PLAY:
/* 85 */           ((TimeAnimation)anim).start(target2);
/*    */           break;
/*    */         case STOP:
/* 88 */           ((TimeAnimation)anim).stop(target2);
/*    */           break;
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\animations\SSetNPCTimeAnimationPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */