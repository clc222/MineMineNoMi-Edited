/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ability;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class SUpdateAbilityTimeProgressionPacket
/*    */ {
/*    */   private int entityId;
/*    */   private int abilityId;
/*    */   private double timeProgression;
/*    */   
/*    */   public SUpdateAbilityTimeProgressionPacket() {}
/*    */   
/*    */   public SUpdateAbilityTimeProgressionPacket(PlayerEntity player, Ability ability) {
/* 34 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 36 */     this.entityId = player.func_145782_y();
/* 37 */     this.abilityId = props.getEquippedAbilitySlot((IAbility)ability);
/*    */     
/* 39 */     this.timeProgression = player.func_110148_a((Attribute)ModAttributes.TIME_PROGRESSION.get()).func_111126_e();
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 44 */     buffer.writeInt(this.entityId);
/* 45 */     buffer.writeInt(this.abilityId);
/*    */     
/* 47 */     buffer.writeDouble(this.timeProgression);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SUpdateAbilityTimeProgressionPacket decode(PacketBuffer buffer) {
/* 52 */     SUpdateAbilityTimeProgressionPacket msg = new SUpdateAbilityTimeProgressionPacket();
/* 53 */     msg.entityId = buffer.readInt();
/* 54 */     msg.abilityId = buffer.readInt();
/*    */     
/* 56 */     msg.timeProgression = buffer.readDouble();
/*    */     
/* 58 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SUpdateAbilityTimeProgressionPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 63 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 65 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 70 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SUpdateAbilityTimeProgressionPacket message) {
/* 78 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/* 79 */       if (target == null || !(target instanceof PlayerEntity) || message.abilityId < 0) {
/*    */         return;
/*    */       }
/* 82 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)target);
/* 83 */       Ability ability = (Ability)props.getEquippedAbility(message.abilityId);
/*    */       
/* 85 */       if (ability == null) {
/*    */         return;
/*    */       }
/* 88 */       ability.setTimeProgression(message.timeProgression);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\SUpdateAbilityTimeProgressionPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */