/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ 
/*    */ @Deprecated
/*    */ public abstract class PassiveAbility
/*    */   extends Ability
/*    */ {
/*    */   protected IDuringPassive duringPassiveEvent = player -> {
/*    */     
/*    */     };
/*    */   private boolean isPaused = false;
/*    */   
/*    */   public PassiveAbility(AbilityCore<? extends IAbility> core) {
/* 18 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPause(boolean flag) {
/* 23 */     this.isPaused = flag;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPaused() {
/* 28 */     return this.isPaused;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(PlayerEntity player) {}
/*    */ 
/*    */   
/*    */   public void tick(PlayerEntity player) {
/* 37 */     super.tick(player);
/* 38 */     if (canUse(player).isSuccess()) {
/* 39 */       this.duringPassiveEvent.duringPassive(player);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbilityUseResult canUse(PlayerEntity player) {
/* 46 */     if (player.func_175149_v() || this.isPaused) {
/* 47 */       return AbilityUseResult.fail(null);
/*    */     }
/*    */     
/* 50 */     return super.canUse(player);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CompoundNBT save(CompoundNBT nbt) {
/* 56 */     nbt = super.save(nbt);
/*    */     
/* 58 */     nbt.func_74757_a("isPaused", isPaused());
/*    */     
/* 60 */     return nbt;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void load(CompoundNBT nbt) {
/* 66 */     super.load(nbt);
/*    */     
/* 68 */     setPause(nbt.func_74767_n("isPaused"));
/*    */   }
/*    */   
/*    */   public static interface IDuringPassive extends Serializable {
/*    */     void duringPassive(PlayerEntity param1PlayerEntity);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\PassiveAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */