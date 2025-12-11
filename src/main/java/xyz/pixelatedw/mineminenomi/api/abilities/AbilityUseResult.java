/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ 
/*    */ 
/*    */ public class AbilityUseResult
/*    */ {
/*    */   private AbilityUseResultType result;
/*    */   @Nullable
/*    */   private ITextComponent message;
/*    */   
/*    */   public AbilityUseResult(AbilityUseResultType result, @Nullable ITextComponent message) {
/* 14 */     this.result = result;
/* 15 */     this.message = message;
/*    */   }
/*    */   
/*    */   public AbilityUseResultType getResult() {
/* 19 */     return this.result;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public ITextComponent getMessage() {
/* 24 */     return this.message;
/*    */   }
/*    */   
/*    */   public boolean isSuccess() {
/* 28 */     return (getResult() == AbilityUseResultType.SUCCESS);
/*    */   }
/*    */   
/*    */   public boolean isSuccessOr(IOkCallback errorCallback) {
/* 32 */     if (isSuccess()) {
/* 33 */       return true;
/*    */     }
/*    */     
/* 36 */     errorCallback.callback(getMessage());
/* 37 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isFail() {
/* 42 */     return (getResult() == AbilityUseResultType.FAIL);
/*    */   }
/*    */   
/*    */   public static AbilityUseResult and(AbilityUseResult result1, AbilityUseResult result2) {
/* 46 */     return (result1.isSuccess() && result2.isSuccess()) ? success() : (result1.isFail() ? fail(result1.getMessage()) : fail(result2.getMessage()));
/*    */   }
/*    */   
/*    */   public static AbilityUseResult or(AbilityUseResult result1, AbilityUseResult result2) {
/* 50 */     return (result1.isSuccess() || result2.isSuccess()) ? success() : (result1.isFail() ? fail(result1.getMessage()) : fail(result2.getMessage()));
/*    */   } @FunctionalInterface
/*    */   public static interface IErrCallback<T> {
/*    */     void callback(T param1T); } public static AbilityUseResult success() {
/* 54 */     return new AbilityUseResult(AbilityUseResultType.SUCCESS, null);
/*    */   }
/*    */   
/*    */   public static AbilityUseResult fail(@Nullable ITextComponent message) {
/* 58 */     return new AbilityUseResult(AbilityUseResultType.FAIL, message);
/*    */   } @FunctionalInterface
/*    */   public static interface IOkCallback {
/*    */     void callback(ITextComponent param1ITextComponent); }
/* 62 */   public enum AbilityUseResultType { SUCCESS,
/* 63 */     FAIL; }
/*    */ 
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\AbilityUseResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */