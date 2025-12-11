/*    */ package xyz.pixelatedw.mineminenomi.api.challenges;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ 
/*    */ 
/*    */ public class ChallengeMetaEntry<T1 extends Challenge, T2 extends Challenge, T3 extends Challenge>
/*    */ {
/*    */   private final RegistryObject<ChallengeCore<T1>> standardCore;
/*    */   @Nullable
/*    */   private final RegistryObject<ChallengeCore<T2>> hardCore;
/*    */   @Nullable
/*    */   private final RegistryObject<ChallengeCore<T3>> ultimateCore;
/*    */   
/*    */   public ChallengeMetaEntry(RegistryObject<ChallengeCore<T1>> standardCore, @Nullable RegistryObject<ChallengeCore<T2>> hardCore, @Nullable RegistryObject<ChallengeCore<T3>> ultimateCore) {
/* 16 */     this.standardCore = standardCore;
/* 17 */     this.hardCore = hardCore;
/* 18 */     this.ultimateCore = ultimateCore;
/*    */   }
/*    */   
/*    */   public ChallengeCore<T1> getStandardChallenge() {
/* 22 */     return (ChallengeCore<T1>)this.standardCore.get();
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public ChallengeCore<T2> getHardChallenge() {
/* 27 */     if (this.hardCore == null) {
/* 28 */       return null;
/*    */     }
/* 30 */     return (ChallengeCore<T2>)this.hardCore.get();
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public ChallengeCore<T3> getUltimateChallenge() {
/* 35 */     if (this.ultimateCore == null) {
/* 36 */       return null;
/*    */     }
/* 38 */     return (ChallengeCore<T3>)this.ultimateCore.get();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\challenges\ChallengeMetaEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */