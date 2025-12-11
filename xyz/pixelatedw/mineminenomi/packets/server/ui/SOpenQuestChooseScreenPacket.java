/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ui;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.client.gui.screen.Screen;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ITrainer;
/*    */ import xyz.pixelatedw.mineminenomi.screens.TrainerScreen;
/*    */ 
/*    */ public class SOpenQuestChooseScreenPacket {
/*    */   private int questGiverEntity;
/*    */   private boolean isInCombat;
/*    */   
/*    */   public SOpenQuestChooseScreenPacket() {}
/*    */   
/*    */   public SOpenQuestChooseScreenPacket(int questGiverEntity, boolean isInCombat) {
/* 25 */     this.questGiverEntity = questGiverEntity;
/* 26 */     this.isInCombat = isInCombat;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 30 */     buffer.writeInt(this.questGiverEntity);
/* 31 */     buffer.writeBoolean(this.isInCombat);
/*    */   }
/*    */   
/*    */   public static SOpenQuestChooseScreenPacket decode(PacketBuffer buffer) {
/* 35 */     SOpenQuestChooseScreenPacket msg = new SOpenQuestChooseScreenPacket();
/* 36 */     msg.questGiverEntity = buffer.readInt();
/* 37 */     msg.isInCombat = buffer.readBoolean();
/* 38 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SOpenQuestChooseScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 42 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 43 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/* 45 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SOpenQuestChooseScreenPacket message) {
/* 51 */       ClientPlayerEntity clientPlayerEntity = (Minecraft.func_71410_x()).field_71439_g;
/* 52 */       Entity questGiver = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.questGiverEntity);
/*    */       
/* 54 */       if (!(questGiver instanceof ITrainer)) {
/*    */         return;
/*    */       }
/* 57 */       Minecraft.func_71410_x().func_147108_a((Screen)new TrainerScreen((PlayerEntity)clientPlayerEntity, (LivingEntity)questGiver, ((ITrainer)questGiver).getAvailableQuests((PlayerEntity)clientPlayerEntity), message.isInCombat));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\serve\\ui\SOpenQuestChooseScreenPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */