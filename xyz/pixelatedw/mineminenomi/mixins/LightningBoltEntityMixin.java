/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import net.minecraft.entity.effect.LightningBoltEntity;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.ModifyArg;
/*    */ import xyz.pixelatedw.mineminenomi.api.ILocalLightningBoltEntity;
/*    */ 
/*    */ 
/*    */ @Mixin({LightningBoltEntity.class})
/*    */ public abstract class LightningBoltEntityMixin
/*    */   implements ILocalLightningBoltEntity
/*    */ {
/*    */   private boolean isLocal;
/*    */   
/*    */   public void setLocal() {
/* 17 */     this.isLocal = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isLocal() {
/* 22 */     return this.isLocal;
/*    */   }
/*    */   
/*    */   @ModifyArg(method = {"tick"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;playSound(Lnet/minecraft/entity/player/PlayerEntity;DDDLnet/minecraft/util/SoundEvent;Lnet/minecraft/util/SoundCategory;FF)V"), index = 6)
/*    */   private float setVolume(float volume) {
/* 27 */     if (this.isLocal && volume == 10000.0F) {
/* 28 */       return 5.0F;
/*    */     }
/*    */     
/* 31 */     return volume;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\LightningBoltEntityMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */