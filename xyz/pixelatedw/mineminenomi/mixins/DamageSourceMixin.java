/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import xyz.pixelatedw.mineminenomi.api.ISweep;
/*    */ 
/*    */ @Mixin({DamageSource.class})
/*    */ public abstract class DamageSourceMixin
/*    */   implements ISweep
/*    */ {
/*    */   @Unique
/*    */   private boolean isSweeping = false;
/*    */   
/*    */   public boolean isSweeping() {
/* 20 */     return this.isSweeping;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSweeping(boolean isSweeping) {
/* 25 */     this.isSweeping = isSweeping;
/*    */   }
/*    */   
/*    */   @Inject(method = {"playerAttack"}, at = {@At("RETURN")})
/*    */   private static void playerAttack(PlayerEntity attacker, CallbackInfoReturnable<DamageSource> cir) {
/* 30 */     DamageSource source = (DamageSource)cir.getReturnValue();
/*    */     
/* 32 */     if (((ISweep)attacker).isSweeping()) {
/* 33 */       ((ISweep)source).setSweeping(true);
/*    */       
/* 35 */       ((ISweep)attacker).setSweeping(false);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\DamageSourceMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */