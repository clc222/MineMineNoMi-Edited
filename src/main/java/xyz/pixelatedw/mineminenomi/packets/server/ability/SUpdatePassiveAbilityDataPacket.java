/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ability;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SUpdatePassiveAbilityDataPacket
/*    */ {
/*    */   private int entityId;
/*    */   private ResourceLocation abilityId;
/*    */   private CompoundNBT nbtData;
/*    */   
/*    */   public SUpdatePassiveAbilityDataPacket() {}
/*    */   
/*    */   public SUpdatePassiveAbilityDataPacket(LivingEntity entity, IAbility ability) {
/* 30 */     this.entityId = entity.func_145782_y();
/* 31 */     this.abilityId = ability.getCore().getRegistryName();
/* 32 */     this.nbtData = ability.save(new CompoundNBT());
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 36 */     buffer.writeInt(this.entityId);
/* 37 */     buffer.func_192572_a(this.abilityId);
/* 38 */     buffer.func_150786_a(this.nbtData);
/*    */   }
/*    */   
/*    */   public static SUpdatePassiveAbilityDataPacket decode(PacketBuffer buffer) {
/* 42 */     SUpdatePassiveAbilityDataPacket msg = new SUpdatePassiveAbilityDataPacket();
/*    */     
/* 44 */     msg.entityId = buffer.readInt();
/* 45 */     msg.abilityId = buffer.func_192575_l();
/* 46 */     msg.nbtData = buffer.func_150793_b();
/*    */     
/* 48 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SUpdatePassiveAbilityDataPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 52 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 53 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */     
/* 56 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public static class ClientHandler {
/*    */     public static void handle(SUpdatePassiveAbilityDataPacket message) {
/* 62 */       Minecraft mc = Minecraft.func_71410_x();
/*    */       
/* 64 */       Entity entity = mc.field_71441_e.func_73045_a(message.entityId);
/*    */       
/* 66 */       if (entity == null || !(entity instanceof LivingEntity)) {
/*    */         return;
/*    */       }
/*    */       
/* 70 */       LivingEntity target = (LivingEntity)entity;
/*    */       
/* 72 */       IAbilityData abilityProps = AbilityDataCapability.get(target);
/*    */       
/* 74 */       AbilityCore<? extends IAbility> core = AbilityCore.get(message.abilityId);
/*    */       
/* 76 */       if (core == null) {
/*    */         return;
/*    */       }
/*    */       
/* 80 */       IAbility ability = abilityProps.getPassiveAbility(core);
/*    */       
/* 82 */       if (ability == null) {
/*    */         return;
/*    */       }
/*    */       
/* 86 */       ability.load(message.nbtData);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\SUpdatePassiveAbilityDataPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */