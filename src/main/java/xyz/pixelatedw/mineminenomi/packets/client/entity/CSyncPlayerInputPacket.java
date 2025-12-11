/*    */ package xyz.pixelatedw.mineminenomi.packets.client.entity;
/*    */ 
/*    */ import io.netty.handler.codec.EncoderException;
/*    */ import java.util.Arrays;
/*    */ import java.util.BitSet;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ 
/*    */ public class CSyncPlayerInputPacket
/*    */ {
/*    */   private float xxa;
/*    */   private float zza;
/*    */   private boolean isJumping;
/*    */   
/*    */   public CSyncPlayerInputPacket() {}
/*    */   
/*    */   public CSyncPlayerInputPacket(ClientPlayerEntity player) {
/* 25 */     this.xxa = player.field_70702_br;
/* 26 */     this.zza = player.field_191988_bg;
/* 27 */     this.isJumping = player.field_71158_b.field_78901_c;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 31 */     BitSet bitSet = new BitSet(5);
/*    */     
/* 33 */     bitSet.set(0, (this.xxa > 0.0F));
/* 34 */     bitSet.set(1, (this.xxa < 0.0F));
/* 35 */     bitSet.set(2, (this.zza > 0.0F));
/* 36 */     bitSet.set(3, (this.zza < 0.0F));
/* 37 */     bitSet.set(4, this.isJumping);
/*    */     
/* 39 */     writeFixedBitSet(buffer, bitSet, 5);
/*    */   }
/*    */   
/*    */   public static CSyncPlayerInputPacket decode(PacketBuffer buffer) {
/* 43 */     CSyncPlayerInputPacket message = new CSyncPlayerInputPacket();
/*    */     
/* 45 */     BitSet bitSet = readFixedBitSet(buffer, 5);
/*    */     
/* 47 */     int xxaBits = (bitSet.get(0) ? 1 : 0) | (bitSet.get(1) ? 2 : 0);
/* 48 */     int zzaBits = (bitSet.get(2) ? 1 : 0) | (bitSet.get(3) ? 2 : 0);
/*    */     
/* 50 */     message.xxa = (xxaBits == 2) ? -1.0F : xxaBits;
/* 51 */     message.zza = (zzaBits == 2) ? -1.0F : zzaBits;
/* 52 */     message.isJumping = bitSet.get(4);
/*    */     
/* 54 */     return message;
/*    */   }
/*    */   
/*    */   public static void handle(CSyncPlayerInputPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 58 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER) {
/* 59 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity entity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             entity.field_70702_br = message.xxa;
/*    */             
/*    */             entity.field_191988_bg = message.zza;
/*    */             
/*    */             entity.func_70637_d(message.isJumping);
/*    */             
/*    */             IEntityStats entityDataProps = EntityStatsCapability.get((LivingEntity)entity);
/*    */             
/*    */             entityDataProps.setLeftImpulse(message.xxa);
/*    */             
/*    */             entityDataProps.setForwardImpulse(message.zza);
/*    */             entityDataProps.setJumping(message.isJumping);
/*    */           });
/*    */     }
/* 76 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static BitSet readBitSet(PacketBuffer buffer) {
/* 80 */     return BitSet.valueOf(buffer.func_186873_b(null));
/*    */   }
/*    */   
/*    */   public static void writeBitSet(PacketBuffer buffer, BitSet bits) {
/* 84 */     buffer.func_186865_a(bits.toLongArray());
/*    */   }
/*    */   
/*    */   public static BitSet readFixedBitSet(PacketBuffer buffer, int toWrite) {
/* 88 */     byte[] abyte = new byte[-Math.floorDiv(-toWrite, 8)];
/* 89 */     buffer.readBytes(abyte);
/* 90 */     return BitSet.valueOf(abyte);
/*    */   }
/*    */   
/*    */   public static void writeFixedBitSet(PacketBuffer buffer, BitSet bits, int toWrite) {
/* 94 */     if (bits.length() > toWrite) {
/* 95 */       throw new EncoderException("BitSet is larger than expected size (" + bits.length() + ">" + toWrite + ")");
/*    */     }
/* 97 */     byte[] abyte = bits.toByteArray();
/* 98 */     buffer.writeBytes(Arrays.copyOf(abyte, -Math.floorDiv(-toWrite, 8)));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\entity\CSyncPlayerInputPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */