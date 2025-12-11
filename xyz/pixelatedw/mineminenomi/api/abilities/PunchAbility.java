/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public abstract class PunchAbility
/*    */   extends HitAbility
/*    */ {
/*    */   public PunchAbility(AbilityCore core) {
/* 11 */     super(core);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <T extends xyz.pixelatedw.mineminenomi.init.ModDamageSource> T getDamageSource(PlayerEntity player, T source) {
/* 17 */     source = super.getDamageSource(player, source);
/* 18 */     return (T)source.setFistDamage();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isActive(PlayerEntity player) {
/* 24 */     return (super.isActive(player) && player.func_184614_ca().func_190926_b());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\PunchAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */