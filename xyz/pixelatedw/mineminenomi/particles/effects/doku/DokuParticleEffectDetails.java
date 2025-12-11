/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.doku;
/*    */ 
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ 
/*    */ public class DokuParticleEffectDetails
/*    */   extends ParticleEffect.Details
/*    */ {
/*    */   private boolean hasVenomDemon;
/*    */   
/*    */   public void save(CompoundNBT nbt) {
/* 13 */     nbt.func_74757_a("hasVenomDemon", this.hasVenomDemon);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void load(CompoundNBT nbt) {
/* 19 */     this.hasVenomDemon = nbt.func_74767_n("hasVenomDemon");
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHasVenomDemon(boolean flag) {
/* 24 */     this.hasVenomDemon = flag;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasVenomDemon() {
/* 29 */     return this.hasVenomDemon;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\doku\DokuParticleEffectDetails.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */