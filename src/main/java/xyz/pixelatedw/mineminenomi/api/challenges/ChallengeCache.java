/*    */ package xyz.pixelatedw.mineminenomi.api.challenges;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ 
/*    */ public class ChallengeCache
/*    */ {
/*    */   public static ChallengeCache from(LivingEntity entity) {
/* 10 */     ChallengeCache cache = new ChallengeCache();
/*    */     
/* 12 */     cache.playerData = entity.func_189511_e(new CompoundNBT());
/*    */     
/* 14 */     return cache;
/*    */   }
/*    */   private CompoundNBT playerData;
/*    */   public static ChallengeCache from(CompoundNBT nbt) {
/* 18 */     ChallengeCache cache = new ChallengeCache();
/*    */     
/* 20 */     cache.load(nbt);
/*    */     
/* 22 */     return cache;
/*    */   }
/*    */   
/*    */   public void restore(LivingEntity entity) {
/* 26 */     entity.func_70020_e(this.playerData);
/*    */   }
/*    */   
/*    */   public CompoundNBT save() {
/* 30 */     CompoundNBT nbt = new CompoundNBT();
/*    */     
/* 32 */     nbt.func_218657_a("playerData", (INBT)this.playerData);
/*    */     
/* 34 */     return nbt;
/*    */   }
/*    */   
/*    */   public void load(CompoundNBT nbt) {
/* 38 */     this.playerData = nbt.func_74775_l("playerData");
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\challenges\ChallengeCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */