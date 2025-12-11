/*    */ package xyz.pixelatedw.mineminenomi.packets.client.entities;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.WhiteWalkieEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CUpdateInventoryPagePacket
/*    */ {
/*    */   private int entityId;
/*    */   private int pageId;
/*    */   
/*    */   public CUpdateInventoryPagePacket() {}
/*    */   
/*    */   public CUpdateInventoryPagePacket(int entityId, int pageId) {
/* 23 */     this.entityId = entityId;
/* 24 */     this.pageId = pageId;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 28 */     buffer.writeInt(this.entityId);
/* 29 */     buffer.writeInt(this.pageId);
/*    */   }
/*    */   
/*    */   public static CUpdateInventoryPagePacket decode(PacketBuffer buffer) {
/* 33 */     CUpdateInventoryPagePacket msg = new CUpdateInventoryPagePacket();
/* 34 */     msg.entityId = buffer.readInt();
/* 35 */     msg.pageId = buffer.readInt();
/* 36 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(CUpdateInventoryPagePacket message, Supplier<NetworkEvent.Context> ctx) {
/* 40 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER) {
/* 41 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity player = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             World world = player.field_70170_p;
/*    */             
/*    */             Entity entity = world.func_73045_a(message.entityId);
/*    */             
/*    */             if (entity == null) {
/*    */               return;
/*    */             }
/*    */             if (entity instanceof WhiteWalkieEntity) {
/*    */               WhiteWalkieEntity walkie = (WhiteWalkieEntity)entity;
/*    */               walkie.setInventoryPage(message.pageId);
/*    */               if (walkie.func_70902_q() != null && walkie.func_70902_q() instanceof PlayerEntity) {
/*    */                 walkie.openInventory((PlayerEntity)walkie.func_70902_q());
/*    */               } else {
/*    */                 walkie.openInventory((PlayerEntity)player);
/*    */               } 
/*    */             } 
/*    */           });
/*    */     }
/* 62 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\entities\CUpdateInventoryPagePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */