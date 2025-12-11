/*     */ package xyz.pixelatedw.mineminenomi.packets.server.ability.components;
/*     */ 
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.network.NetworkDirection;
/*     */ import net.minecraftforge.fml.network.NetworkEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.animation.AnimationDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.animation.IAnimationData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SChangeAnimationStatePacket
/*     */ {
/*  27 */   private static final ResourceLocation EMPTY = new ResourceLocation("mineminenomi", "");
/*     */   
/*     */   private int entityId;
/*     */   
/*     */   private ResourceLocation abilityId;
/*     */   private int stateId;
/*     */   private int animDuration;
/*     */   private AnimationId<?> animId;
/*     */   
/*     */   public SChangeAnimationStatePacket() {}
/*     */   
/*     */   public SChangeAnimationStatePacket(LivingEntity entity, IAbility ability, AnimationId<?> animId, AnimationComponent.State state, int animDuration) {
/*  39 */     this.entityId = entity.func_145782_y();
/*  40 */     this.abilityId = ability.getCore().getRegistryName();
/*  41 */     this.animId = animId;
/*  42 */     this.animDuration = animDuration;
/*  43 */     this.stateId = state.ordinal();
/*     */   }
/*     */   
/*     */   public void encode(PacketBuffer buffer) {
/*  47 */     buffer.writeInt(this.entityId);
/*  48 */     buffer.func_192572_a(this.abilityId);
/*  49 */     buffer.writeInt(this.stateId);
/*  50 */     buffer.writeInt(this.animDuration);
/*  51 */     buffer.func_192572_a((this.animId != null) ? this.animId.getId() : EMPTY);
/*     */   }
/*     */   
/*     */   public static SChangeAnimationStatePacket decode(PacketBuffer buffer) {
/*  55 */     SChangeAnimationStatePacket msg = new SChangeAnimationStatePacket();
/*  56 */     msg.entityId = buffer.readInt();
/*  57 */     msg.abilityId = buffer.func_192575_l();
/*  58 */     msg.stateId = buffer.readInt();
/*  59 */     msg.animDuration = buffer.readInt();
/*  60 */     ResourceLocation animRes = buffer.func_192575_l();
/*  61 */     msg.animId = animRes.equals(EMPTY) ? null : AnimationId.getRegisteredId(animRes);
/*  62 */     return msg;
/*     */   }
/*     */   
/*     */   public static void handle(SChangeAnimationStatePacket message, Supplier<NetworkEvent.Context> ctx) {
/*  66 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/*  67 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*     */     }
/*     */ 
/*     */     
/*  71 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*     */   }
/*     */   
/*     */   public static class ClientHandler {
/*     */     @OnlyIn(Dist.CLIENT)
/*     */     public static void handle(SChangeAnimationStatePacket message) {
/*  77 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/*  78 */       if (target == null || !(target instanceof LivingEntity)) {
/*     */         return;
/*     */       }
/*     */       
/*  82 */       AbilityCore<?> core = AbilityCore.get(message.abilityId);
/*  83 */       if (core == null) {
/*     */         return;
/*     */       }
/*     */       
/*  87 */       LivingEntity entity = (LivingEntity)target;
/*  88 */       IAnimationData animProps = AnimationDataCapability.get(entity);
/*  89 */       IAbilityData abilityProps = AbilityDataCapability.get(entity);
/*  90 */       IAbility abl = abilityProps.getEquippedAbility(core);
/*     */ 
/*     */       
/*  93 */       if (abl == null) {
/*  94 */         abl = abilityProps.getPassiveAbility(core);
/*  95 */         if (abl == null) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */       
/* 100 */       AnimationComponent.State state = AnimationComponent.State.values()[message.stateId];
/*     */       
/* 102 */       abl.getComponent(ModAbilityKeys.ANIMATION).ifPresent(comp -> {
/*     */             if (state.equals(AnimationComponent.State.PLAY) && message.animId != null && !message.animId.getId().equals(SChangeAnimationStatePacket.EMPTY)) {
/*     */               comp.start(entity, message.animId, message.animDuration);
/*     */               if (comp.getAnimation() != null) {
/*     */                 comp.getAnimation().start(entity, message.animDuration);
/*     */                 animProps.startAnimation(message.animId, message.animDuration, false);
/*     */               } 
/*     */             } else if (state.equals(AnimationComponent.State.STOP)) {
/*     */               comp.stop(entity);
/*     */               if (comp.getAnimation() != null) {
/*     */                 comp.getAnimation().stop(entity);
/*     */                 animProps.stopAnimation(message.animId);
/*     */               } 
/*     */             } 
/*     */           });
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\components\SChangeAnimationStatePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */