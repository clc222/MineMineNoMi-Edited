/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ 
/*    */ public enum WeatherBallKind
/*    */ {
/*  8 */   HEAT((byte)1),
/*  9 */   COOL((byte)2),
/* 10 */   THUNDER((byte)3);
/*    */   
/*    */   private byte kind;
/*    */ 
/*    */   
/*    */   WeatherBallKind(byte kind) {
/* 16 */     this.kind = kind;
/*    */   }
/*    */   
/*    */   public byte getKind() {
/* 20 */     return this.kind;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public static WeatherBallKind from(byte b0) {
/* 25 */     switch (b0) {
/*    */       case 1:
/* 27 */         return HEAT;
/*    */       case 2:
/* 29 */         return COOL;
/*    */       case 3:
/* 31 */         return THUNDER;
/*    */     } 
/*    */ 
/*    */     
/* 35 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 40 */     return (new TranslationTextComponent("ability.mineminenomi." + name().toLowerCase() + "_ball")).getString();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\WeatherBallKind.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */