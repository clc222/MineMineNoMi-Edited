/*    */ package xyz.pixelatedw.mineminenomi.packets.server;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.world.ClientWorld;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.FlagTileEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SSetFlagOnFirePacket
/*    */ {
/*    */   private BlockPos pos;
/*    */   private boolean value;
/*    */   
/*    */   public SSetFlagOnFirePacket() {}
/*    */   
/*    */   public SSetFlagOnFirePacket(BlockPos pos, boolean value) {
/* 26 */     this.pos = pos;
/* 27 */     this.value = value;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 31 */     buffer.func_179255_a(this.pos);
/* 32 */     buffer.writeBoolean(this.value);
/*    */   }
/*    */   
/*    */   public static SSetFlagOnFirePacket decode(PacketBuffer buffer) {
/* 36 */     SSetFlagOnFirePacket msg = new SSetFlagOnFirePacket();
/* 37 */     msg.pos = buffer.func_179259_c();
/* 38 */     msg.value = buffer.readBoolean();
/* 39 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SSetFlagOnFirePacket message, Supplier<NetworkEvent.Context> ctx) {
/* 43 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 44 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */     
/* 48 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SSetFlagOnFirePacket message) {
/* 54 */       ClientWorld world = (Minecraft.func_71410_x()).field_71441_e;
/*    */       
/* 56 */       BlockState state = world.func_180495_p(message.pos);
/* 57 */       if (state.func_177230_c() instanceof xyz.pixelatedw.mineminenomi.blocks.FlagBlock) {
/* 58 */         TileEntity tileEntity = world.func_175625_s(message.pos);
/* 59 */         if (tileEntity != null && tileEntity instanceof FlagTileEntity) {
/* 60 */           FlagTileEntity flagTile = (FlagTileEntity)tileEntity;
/* 61 */           flagTile.setOnFire(message.value);
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\SSetFlagOnFirePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */