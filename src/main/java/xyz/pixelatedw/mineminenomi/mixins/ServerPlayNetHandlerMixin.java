/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.play.IServerPlayNetHandler;
/*    */ import net.minecraft.network.play.ServerPlayNetHandler;
/*    */ import net.minecraft.network.play.client.CClientStatusPacket;
/*    */ import net.minecraft.network.play.client.CUseEntityPacket;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Constant;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.ModifyConstant;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ 
/*    */ 
/*    */ @Mixin({ServerPlayNetHandler.class})
/*    */ public abstract class ServerPlayNetHandlerMixin
/*    */   implements IServerPlayNetHandler
/*    */ {
/*    */   @Shadow
/*    */   public ServerPlayerEntity field_147369_b;
/*    */   @Shadow
/*    */   @Final
/*    */   public MinecraftServer field_147367_d;
/*    */   
/*    */   @ModifyConstant(method = {"handleInteract(Lnet/minecraft/network/play/client/CUseEntityPacket;)V"}, constant = {@Constant(doubleValue = 36.0D)})
/*    */   public double getActualAttackRange(double attackRange, CUseEntityPacket packet) {
/* 38 */     if (packet.func_149565_c() == CUseEntityPacket.Action.ATTACK) {
/* 39 */       return AttributeHelper.getSquaredAttackRangeDistance((LivingEntity)this.field_147369_b, attackRange);
/*    */     }
/*    */     
/* 42 */     return AttributeHelper.getSquaredAttackRangeDistance((LivingEntity)this.field_147369_b, attackRange);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Inject(method = {"handleClientCommand"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/server/management/PlayerList;respawn(Lnet/minecraft/entity/player/ServerPlayerEntity;Z)Lnet/minecraft/entity/player/ServerPlayerEntity;", shift = At.Shift.BEFORE)}, cancellable = true)
/*    */   public void processClientStatus(CClientStatusPacket packet, CallbackInfo callback) {
/* 55 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)this.field_147369_b);
/*    */     
/* 57 */     if (props.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) && !((MorphInfo)ModMorphs.YOMI_SKELETON.get()).isActive((LivingEntity)this.field_147369_b)) {
/* 58 */       this.field_147369_b = this.field_147367_d.func_184103_al().func_232644_a_(this.field_147369_b, false);
/* 59 */       callback.cancel();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\ServerPlayNetHandlerMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */