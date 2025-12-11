/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ability;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.audio.ISound;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.audio.DrumsOfLiberationTickableSound;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ 
/*    */ public class SToggleDrumsOfLiberationSoundPacket
/*    */ {
/*    */   private int entityId;
/*    */   
/*    */   public SToggleDrumsOfLiberationSoundPacket(LivingEntity entity, boolean shouldPlay) {
/* 23 */     this.entityId = entity.func_145782_y();
/* 24 */     this.shouldPlay = shouldPlay;
/*    */   } private boolean shouldPlay;
/*    */   public SToggleDrumsOfLiberationSoundPacket() {}
/*    */   public void encode(PacketBuffer buffer) {
/* 28 */     buffer.writeInt(this.entityId);
/* 29 */     buffer.writeBoolean(this.shouldPlay);
/*    */   }
/*    */   
/*    */   public static SToggleDrumsOfLiberationSoundPacket decode(PacketBuffer buffer) {
/* 33 */     SToggleDrumsOfLiberationSoundPacket msg = new SToggleDrumsOfLiberationSoundPacket();
/* 34 */     msg.entityId = buffer.readInt();
/* 35 */     msg.shouldPlay = buffer.readBoolean();
/* 36 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SToggleDrumsOfLiberationSoundPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 40 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 41 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */     
/* 45 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SToggleDrumsOfLiberationSoundPacket message) {
/* 51 */       Minecraft mc = Minecraft.func_71410_x();
/* 52 */       Entity soundSource = mc.field_71441_e.func_73045_a(message.entityId);
/* 53 */       if (soundSource == null) {
/*    */         return;
/*    */       }
/*    */       
/* 57 */       if (message.shouldPlay) {
/* 58 */         mc.func_147118_V().func_147682_a((ISound)new DrumsOfLiberationTickableSound((SoundEvent)ModSounds.DRUMS_OF_LIBERATION_1.get(), 0, soundSource));
/* 59 */         mc.func_147118_V().func_147682_a((ISound)new DrumsOfLiberationTickableSound((SoundEvent)ModSounds.DRUMS_OF_LIBERATION_2.get(), 1, soundSource));
/*    */       } else {
/*    */         
/* 62 */         mc.func_147118_V().func_195478_a(((SoundEvent)ModSounds.DRUMS_OF_LIBERATION_1.get()).func_187503_a(), SoundCategory.PLAYERS);
/* 63 */         mc.func_147118_V().func_195478_a(((SoundEvent)ModSounds.DRUMS_OF_LIBERATION_2.get()).func_187503_a(), SoundCategory.PLAYERS);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\SToggleDrumsOfLiberationSoundPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */