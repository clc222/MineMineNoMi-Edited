/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ability.components;
/*    */ 
/*    */ import com.google.common.base.Strings;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.MorphComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class SChangeMorphPacket
/*    */ {
/*    */   private int entityId;
/*    */   private int abilityId;
/*    */   private String morph;
/*    */   private MorphInfo morphInfo;
/*    */   
/*    */   public SChangeMorphPacket() {}
/*    */   
/*    */   public SChangeMorphPacket(LivingEntity entity, IAbility ability, MorphInfo morphInfo, String morph) {
/* 31 */     this.entityId = entity.func_145782_y();
/* 32 */     this.abilityId = AbilityDataCapability.get(entity).getEquippedAbilitySlot(ability);
/* 33 */     this.morphInfo = morphInfo;
/* 34 */     this.morph = morph;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 38 */     buffer.writeInt(this.entityId);
/* 39 */     buffer.writeInt(this.abilityId);
/* 40 */     buffer.func_192572_a(this.morphInfo.getRegistryName());
/* 41 */     int len = this.morph.length();
/* 42 */     buffer.writeInt(len);
/* 43 */     buffer.func_211400_a(this.morph, len);
/*    */   }
/*    */   
/*    */   public static SChangeMorphPacket decode(PacketBuffer buffer) {
/* 47 */     SChangeMorphPacket msg = new SChangeMorphPacket();
/* 48 */     msg.entityId = buffer.readInt();
/* 49 */     msg.abilityId = buffer.readInt();
/* 50 */     msg.morphInfo = (MorphInfo)ModRegistries.MORPHS.getValue(buffer.func_192575_l());
/* 51 */     int len = buffer.readInt();
/* 52 */     msg.morph = buffer.func_150789_c(len);
/* 53 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SChangeMorphPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 57 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 58 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */     
/* 62 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SChangeMorphPacket message) {
/* 68 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/* 69 */       if (target == null || !(target instanceof LivingEntity)) {
/*    */         return;
/*    */       }
/*    */       
/* 73 */       if (message.morphInfo == null) {
/*    */         return;
/*    */       }
/*    */       
/* 77 */       LivingEntity entity = (LivingEntity)target;
/* 78 */       IAbilityData props = AbilityDataCapability.get(entity);
/* 79 */       IAbility abl = props.getEquippedAbility(message.abilityId);
/* 80 */       if (abl == null) {
/*    */         return;
/*    */       }
/*    */       
/* 84 */       abl.getComponent(ModAbilityKeys.MORPH).ifPresent(comp -> {
/*    */             if (Strings.isNullOrEmpty(message.morph)) {
/*    */               comp.stopMorph(entity);
/*    */             } else {
/*    */               comp.startMorph(entity, message.morphInfo);
/*    */             } 
/*    */             message.morphInfo.updateMorphSize(entity);
/*    */           });
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\components\SChangeMorphPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */