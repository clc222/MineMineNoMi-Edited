/*    */ package xyz.pixelatedw.mineminenomi.packets.client.ui;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*    */ import xyz.pixelatedw.mineminenomi.api.interactions.Interaction;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SCloseScreenPacket;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ui.STriggerInteractionPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class CTriggerInteractionPacket
/*    */ {
/*    */   private Interaction interaction;
/*    */   private int entityId;
/*    */   
/*    */   public CTriggerInteractionPacket() {}
/*    */   
/*    */   public CTriggerInteractionPacket(Interaction interaction, LivingEntity entity) {
/* 26 */     this.interaction = interaction;
/* 27 */     this.entityId = entity.func_145782_y();
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 31 */     buffer.writeInt(this.entityId);
/* 32 */     buffer.writeBoolean((this.interaction != null));
/* 33 */     if (this.interaction != null) {
/* 34 */       buffer.func_192572_a(this.interaction.getRegistryName());
/*    */     }
/*    */   }
/*    */   
/*    */   public static CTriggerInteractionPacket decode(PacketBuffer buffer) {
/* 39 */     CTriggerInteractionPacket msg = new CTriggerInteractionPacket();
/*    */     
/* 41 */     msg.entityId = buffer.readInt();
/*    */     
/* 43 */     boolean hasInteraction = buffer.readBoolean();
/* 44 */     if (hasInteraction) {
/* 45 */       ResourceLocation res = buffer.func_192575_l();
/* 46 */       msg.interaction = (Interaction)ModRegistries.INTERACTIONS.getValue(res);
/*    */     } 
/*    */     
/* 49 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(CTriggerInteractionPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 53 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/* 54 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             Entity entity = ((PlayerEntity)serverPlayerEntity).field_70170_p.func_73045_a(message.entityId);
/*    */             if (!(entity instanceof LivingEntity))
/*    */               return; 
/*    */             LivingEntity living = (LivingEntity)entity;
/*    */             if (message.interaction == null)
/*    */               return; 
/*    */             Interaction.InteractionResult result = message.interaction.trigger((PlayerEntity)serverPlayerEntity, living);
/*    */             if (result.getType() == Interaction.InteractionResultType.CONTINUE) {
/*    */               WyNetwork.sendTo(new STriggerInteractionPacket(message.interaction), (PlayerEntity)serverPlayerEntity);
/*    */             } else if (result.getType() == Interaction.InteractionResultType.NEXT) {
/*    */               WyNetwork.sendTo(new STriggerInteractionPacket(result.getNextInteraction()), (PlayerEntity)serverPlayerEntity);
/*    */             } else if (result.getType() == Interaction.InteractionResultType.CLOSE_DIALOGUE) {
/*    */               WyNetwork.sendTo(new SCloseScreenPacket(), (PlayerEntity)serverPlayerEntity);
/*    */             } 
/*    */           }); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\clien\\ui\CTriggerInteractionPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */