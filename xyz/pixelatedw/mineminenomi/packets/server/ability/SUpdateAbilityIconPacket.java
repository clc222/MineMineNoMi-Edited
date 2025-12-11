/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ability;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ public class SUpdateAbilityIconPacket
/*    */ {
/*    */   private int entityId;
/*    */   private ResourceLocation abilityId;
/*    */   private ResourceLocation customTexture;
/*    */   
/*    */   public SUpdateAbilityIconPacket() {}
/*    */   
/*    */   public SUpdateAbilityIconPacket(PlayerEntity player, Ability abl) {
/* 32 */     this.entityId = player.func_145782_y();
/* 33 */     this.abilityId = abl.getCore().getRegistryName();
/* 34 */     this.customTexture = abl.getIcon(player);
/*    */   }
/*    */   
/*    */   public SUpdateAbilityIconPacket(PlayerEntity player, PassiveAbility2 abl) {
/* 38 */     this.entityId = player.func_145782_y();
/* 39 */     this.abilityId = abl.getCore().getRegistryName();
/* 40 */     this.customTexture = abl.getIcon((LivingEntity)player);
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 44 */     buffer.writeInt(this.entityId);
/* 45 */     buffer.func_192572_a(this.abilityId);
/* 46 */     buffer.func_192572_a(this.customTexture);
/*    */   }
/*    */   
/*    */   public static SUpdateAbilityIconPacket decode(PacketBuffer buffer) {
/* 50 */     SUpdateAbilityIconPacket msg = new SUpdateAbilityIconPacket();
/* 51 */     msg.entityId = buffer.readInt();
/* 52 */     msg.abilityId = buffer.func_192575_l();
/* 53 */     msg.customTexture = buffer.func_192575_l();
/* 54 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SUpdateAbilityIconPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 58 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 59 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */     
/* 63 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SUpdateAbilityIconPacket message) {
/* 69 */       if (message.abilityId == null) {
/*    */         return;
/*    */       }
/*    */       
/* 73 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/* 74 */       if (target == null || !(target instanceof PlayerEntity)) {
/*    */         return;
/*    */       }
/*    */       
/* 78 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)target);
/* 79 */       AbilityCore<?> core = (AbilityCore)ModRegistries.ABILITIES.getValue(message.abilityId);
/* 80 */       IAbility ability = props.getEquippedOrPassiveAbility(core);
/* 81 */       if (ability == null) {
/*    */         return;
/*    */       }
/*    */       
/* 85 */       if (ability instanceof Ability) {
/* 86 */         ((Ability)ability).setDisplayIcon(message.customTexture);
/*    */       }
/* 88 */       else if (ability instanceof PassiveAbility2) {
/* 89 */         ((PassiveAbility2)ability).setDisplayIcon(message.customTexture);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\SUpdateAbilityIconPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */