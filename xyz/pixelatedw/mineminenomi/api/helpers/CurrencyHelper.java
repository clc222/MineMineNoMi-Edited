/*    */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CurrencyHelper
/*    */ {
/*    */   public static final long BELLY_TO_EXTOL = 10000L;
/*    */   
/*    */   public static long getExtolFromBelly(long belly) {
/* 10 */     return belly * 10000L;
/*    */   }
/*    */   
/*    */   public static long getBellyFromExtol(long extol) {
/* 14 */     if (extol < 10000L) {
/* 15 */       return -1L;
/*    */     }
/* 17 */     return extol / 10000L;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\helpers\CurrencyHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */