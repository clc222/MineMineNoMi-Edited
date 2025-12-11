/*    */ package xyz.pixelatedw.mineminenomi.packets.server;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.world.ClientWorld;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.animation.AnimationDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.animation.IAnimationData;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SToggleAnimationPacket
/*    */ {
/* 24 */   private static final ResourceLocation EMPTY = new ResourceLocation("mineminenomi", "");
/*    */   
/*    */   private int entityId;
/*    */   
/*    */   private AnimationId<?> animId;
/*    */   private int duration;
/*    */   private int stateId;
/*    */   private boolean force;
/*    */   
/*    */   public SToggleAnimationPacket() {}
/*    */   
/*    */   public static SToggleAnimationPacket playAnimation(LivingEntity entity, AnimationId<?> animId, int duration, boolean force) {
/* 36 */     SToggleAnimationPacket packet = new SToggleAnimationPacket(entity, animId, AnimationComponent.State.PLAY, duration, force);
/* 37 */     return packet;
/*    */   }
/*    */   
/*    */   public static SToggleAnimationPacket stopAnimation(LivingEntity entity, AnimationId<?> animId) {
/* 41 */     SToggleAnimationPacket packet = new SToggleAnimationPacket(entity, animId, AnimationComponent.State.STOP, 0, false);
/* 42 */     return packet;
/*    */   }
/*    */   
/*    */   public SToggleAnimationPacket(LivingEntity entity, @Nullable AnimationId<?> animId, AnimationComponent.State state, int duration, boolean force) {
/* 46 */     this.entityId = entity.func_145782_y();
/* 47 */     this.animId = animId;
/* 48 */     this.duration = duration;
/* 49 */     this.stateId = state.ordinal();
/* 50 */     this.force = force;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 54 */     buffer.writeInt(this.entityId);
/* 55 */     buffer.writeInt(this.duration);
/* 56 */     buffer.func_192572_a((this.animId != null) ? this.animId.getId() : EMPTY);
/* 57 */     buffer.writeInt(this.stateId);
/* 58 */     buffer.writeBoolean(this.force);
/*    */   }
/*    */   
/*    */   public static SToggleAnimationPacket decode(PacketBuffer buffer) {
/* 62 */     SToggleAnimationPacket msg = new SToggleAnimationPacket();
/* 63 */     msg.entityId = buffer.readInt();
/* 64 */     msg.duration = buffer.readInt();
/* 65 */     ResourceLocation animRes = buffer.func_192575_l();
/* 66 */     msg.animId = animRes.equals(EMPTY) ? null : AnimationId.getRegisteredId(animRes);
/* 67 */     msg.stateId = buffer.readInt();
/* 68 */     msg.force = buffer.readBoolean();
/* 69 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SToggleAnimationPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 73 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 74 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/* 76 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SToggleAnimationPacket message) {
/* 82 */       Minecraft mc = Minecraft.func_71410_x();
/* 83 */       ClientWorld world = mc.field_71441_e;
/* 84 */       Entity entity = world.func_73045_a(message.entityId);
/* 85 */       if (entity == null || !(entity instanceof LivingEntity)) {
/*    */         return;
/*    */       }
/*    */       
/* 89 */       LivingEntity living = (LivingEntity)entity;
/* 90 */       IAnimationData animProps = AnimationDataCapability.get(living);
/* 91 */       AnimationComponent.State state = AnimationComponent.State.values()[message.stateId];
/*    */       
/* 93 */       if (state == AnimationComponent.State.PLAY) {
/* 94 */         animProps.startAnimation(message.animId, message.duration, message.force);
/*    */       }
/* 96 */       else if (state == AnimationComponent.State.STOP) {
/* 97 */         animProps.stopAnimation(message.animId);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\SToggleAnimationPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */