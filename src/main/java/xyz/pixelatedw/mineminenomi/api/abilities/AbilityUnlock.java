/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import net.minecraftforge.common.IExtensibleEnum;
/*    */ 
/*    */ public enum AbilityUnlock
/*    */   implements IExtensibleEnum {
/*  7 */   NONE,
/*  8 */   PROGRESSION,
/*  9 */   COMMAND;
/*    */ 
/*    */ 
/*    */   
/*    */   public static AbilityUnlock create(String name) {
/* 14 */     throw new IllegalStateException("Enum not extended");
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\AbilityUnlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */