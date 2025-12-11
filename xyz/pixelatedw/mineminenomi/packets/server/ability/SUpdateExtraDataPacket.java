/*     */ package xyz.pixelatedw.mineminenomi.packets.server.ability;
/*     */ 
/*     */ import com.google.common.base.Strings;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.network.NetworkDirection;
/*     */ import net.minecraftforge.fml.network.NetworkEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SUpdateExtraDataPacket
/*     */ {
/*     */   private int entityId;
/*     */   private String abilityId;
/*     */   private CompoundNBT extraData;
/*     */   private boolean isEquipped;
/*     */   
/*     */   @Deprecated
/*     */   public SUpdateExtraDataPacket() {}
/*     */   
/*     */   @Deprecated
/*     */   public SUpdateExtraDataPacket(PlayerEntity player, Ability ability) {
/*  43 */     this.entityId = player.func_145782_y();
/*  44 */     this.abilityId = WyHelper.getResourceName(ability.getName());
/*  45 */     this.isEquipped = true;
/*     */     
/*  47 */     if (ability instanceof xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility) {
/*  48 */       this.isEquipped = false;
/*     */     }
/*  50 */     if (ability instanceof IExtraUpdateData) {
/*  51 */       this.extraData = ((IExtraUpdateData)ability).getExtraData();
/*     */     }
/*     */   }
/*     */   
/*     */   public void encode(PacketBuffer buffer) {
/*  56 */     buffer.writeBoolean(this.isEquipped);
/*  57 */     buffer.writeInt(this.entityId);
/*  58 */     int len = this.abilityId.length();
/*  59 */     buffer.writeInt(len);
/*  60 */     buffer.func_211400_a(this.abilityId, len);
/*  61 */     buffer.writeBoolean((this.extraData != null));
/*  62 */     if (this.extraData != null) {
/*  63 */       buffer.func_150786_a(this.extraData);
/*     */     }
/*     */   }
/*     */   
/*     */   public static SUpdateExtraDataPacket decode(PacketBuffer buffer) {
/*  68 */     SUpdateExtraDataPacket msg = new SUpdateExtraDataPacket();
/*  69 */     msg.isEquipped = buffer.readBoolean();
/*  70 */     msg.entityId = buffer.readInt();
/*  71 */     int len = buffer.readInt();
/*  72 */     msg.abilityId = buffer.func_150789_c(len);
/*  73 */     boolean hasExtraData = buffer.readBoolean();
/*  74 */     if (hasExtraData)
/*  75 */       msg.extraData = buffer.func_150793_b(); 
/*  76 */     return msg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void handle(SUpdateExtraDataPacket message, Supplier<NetworkEvent.Context> ctx) {
/*  81 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*     */     {
/*  83 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  88 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public static class ClientHandler
/*     */   {
/*     */     @OnlyIn(Dist.CLIENT)
/*     */     public static void handle(SUpdateExtraDataPacket message) {
/*  96 */       if (Strings.isNullOrEmpty(message.abilityId)) {
/*     */         return;
/*     */       }
/*  99 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/*     */       
/* 101 */       if (target == null || !(target instanceof PlayerEntity)) {
/*     */         return;
/*     */       }
/* 104 */       ResourceLocation res = new ResourceLocation("mineminenomi", message.abilityId);
/* 105 */       AbilityCore templateAbl = AbilityCore.get(res);
/*     */       
/* 107 */       if (templateAbl == null) {
/*     */         return;
/*     */       }
/* 110 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)target);
/* 111 */       IAbility ability = null;
/* 112 */       boolean isEquipped = message.isEquipped;
/* 113 */       if (isEquipped) {
/* 114 */         ability = props.getEquippedAbility(templateAbl);
/*     */       } else {
/* 116 */         ability = props.getEquippedOrPassiveAbility(templateAbl);
/*     */       } 
/* 118 */       if (ability == null) {
/*     */         return;
/*     */       }
/* 121 */       if (message.extraData != null && ability instanceof IExtraUpdateData)
/* 122 */         ((IExtraUpdateData)ability).setExtraData(message.extraData); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\SUpdateExtraDataPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */