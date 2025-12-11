/*    */ package xyz.pixelatedw.mineminenomi.packets.server;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class SSpawnParticleEffectPacket
/*    */ {
/*    */   private int spawnerId;
/*    */   private ResourceLocation res;
/*    */   private double posX;
/*    */   private double posY;
/*    */   private double posZ;
/* 25 */   private CompoundNBT nbt = new CompoundNBT();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SSpawnParticleEffectPacket(ParticleEffect effect, Entity entity, double posX, double posY, double posZ, @Nullable ParticleEffect.Details details) {
/* 31 */     this.res = effect.getRegistryName();
/* 32 */     this.spawnerId = entity.func_145782_y();
/* 33 */     this.posX = posX;
/* 34 */     this.posY = posY;
/* 35 */     this.posZ = posZ;
/* 36 */     if (details != null) {
/* 37 */       details.save(this.nbt);
/*    */     }
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 42 */     buffer.func_192572_a(this.res);
/* 43 */     buffer.writeInt(this.spawnerId);
/* 44 */     buffer.writeDouble(this.posX);
/* 45 */     buffer.writeDouble(this.posY);
/* 46 */     buffer.writeDouble(this.posZ);
/* 47 */     buffer.func_150786_a(this.nbt);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SSpawnParticleEffectPacket decode(PacketBuffer buffer) {
/* 52 */     SSpawnParticleEffectPacket msg = new SSpawnParticleEffectPacket();
/* 53 */     msg.res = buffer.func_192575_l();
/* 54 */     msg.spawnerId = buffer.readInt();
/* 55 */     msg.posX = buffer.readDouble();
/* 56 */     msg.posY = buffer.readDouble();
/* 57 */     msg.posZ = buffer.readDouble();
/* 58 */     msg.nbt = buffer.func_150793_b();
/* 59 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SSpawnParticleEffectPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 64 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 66 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 71 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public SSpawnParticleEffectPacket() {}
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SSpawnParticleEffectPacket message) {
/* 79 */       Minecraft mc = Minecraft.func_71410_x();
/* 80 */       ClientPlayerEntity player = mc.field_71439_g;
/*    */       
/* 82 */       if (player == null) {
/*    */         return;
/*    */       }
/*    */       
/* 86 */       Entity spawner = player.field_70170_p.func_73045_a(message.spawnerId);
/* 87 */       ParticleEffect effect = (ParticleEffect)GameRegistry.findRegistry(ParticleEffect.class).getValue(message.res);
/* 88 */       if (effect == null || spawner == null) {
/*    */         return;
/*    */       }
/*    */       
/* 92 */       ParticleEffect.Details details = effect.createDetails();
/*    */       
/* 94 */       if (message.nbt != null) {
/* 95 */         details.load(message.nbt);
/*    */       }
/*    */       
/* 98 */       if (effect.canParticlesSpawn(player.field_70170_p))
/* 99 */         effect.spawn(spawner, spawner.field_70170_p, message.posX, message.posY, message.posZ, details); 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\SSpawnParticleEffectPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */