/*    */ package xyz.pixelatedw.mineminenomi.api.challenges;
/*    */ 
/*    */ public enum ChallengeDifficulty {
/*  4 */   STANDARD,
/*  5 */   HARD,
/*  6 */   ULTIMATE;
/*    */ 
/*    */   
/*    */   public String capitalize() {
/* 10 */     ChallengeDifficulty diff = values()[ordinal()];
/* 11 */     return diff.name().substring(0, 1).toUpperCase() + diff.name().substring(1).toLowerCase();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\challenges\ChallengeDifficulty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */