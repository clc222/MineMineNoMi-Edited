/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ability;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ public class SSyncAbilityPacket
/*    */ {
/*    */   private int entityId;
/*    */   private ResourceLocation id;
/*    */   private CompoundNBT nbt;
/*    */   
/*    */   public SSyncAbilityPacket() {}
/*    */   
/*    */   public SSyncAbilityPacket(int entityId, IAbility abl) {
/* 31 */     this.entityId = entityId;
/* 32 */     this.id = abl.getCore().getRegistryName();
/* 33 */     this.nbt = abl.save(new CompoundNBT());
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 37 */     buffer.writeInt(this.entityId);
/* 38 */     buffer.func_192572_a(this.id);
/* 39 */     buffer.func_150786_a(this.nbt);
/*    */   }
/*    */   
/*    */   public static SSyncAbilityPacket decode(PacketBuffer buffer) {
/* 43 */     SSyncAbilityPacket msg = new SSyncAbilityPacket();
/*    */     
/* 45 */     msg.entityId = buffer.readInt();
/* 46 */     msg.id = buffer.func_192575_l();
/* 47 */     msg.nbt = buffer.func_150793_b();
/*    */     
/* 49 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SSyncAbilityPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 53 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 54 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */     
/* 57 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SSyncAbilityPacket message) {
/* 63 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/*    */       
/* 65 */       if (target == null || !(target instanceof PlayerEntity)) {
/*    */         return;
/*    */       }
/*    */       
/* 69 */       PlayerEntity player = (PlayerEntity)target;
/*    */       
/* 71 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */       
/* 73 */       AbilityCore core = AbilityCore.get(message.id);
/*    */       
/* 75 */       IAbility abl = props.getEquippedOrPassiveAbility(core);
/*    */       
/* 77 */       if (abl == null) {
/* 78 */         abl = AbilityCore.get(message.id).createAbility();
/*    */         
/* 80 */         props.addUnlockedAbility(core, AbilityUnlock.PROGRESSION);
/*    */       } 
/*    */       
/* 83 */       abl.load(message.nbt);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\SSyncAbilityPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */