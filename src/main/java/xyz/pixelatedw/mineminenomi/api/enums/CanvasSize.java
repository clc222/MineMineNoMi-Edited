/*    */ package xyz.pixelatedw.mineminenomi.api.enums;
/*    */ 
/*    */ import net.minecraft.util.IStringSerializable;
/*    */ 
/*    */ 
/*    */ public enum CanvasSize
/*    */   implements IStringSerializable
/*    */ {
/*  9 */   SMALL("small"),
/* 10 */   MEDIUM("medium"),
/* 11 */   LARGE("large"),
/* 12 */   HUGE("huge");
/*    */   
/*    */   private final String name;
/*    */ 
/*    */   
/*    */   CanvasSize(String name) {
/* 18 */     this.name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_176610_l() {
/* 23 */     return this.name;
/*    */   }
/*    */   
/*    */   public boolean isMaximumSize() {
/* 27 */     return (ordinal() >= (values()).length - 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\enums\CanvasSize.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */