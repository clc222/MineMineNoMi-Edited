/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ public class AbilityAltMode<E extends Enum<E>>
/*    */ {
/*    */   private final AbilityCore[] children;
/*    */   private final E defaultMode;
/*    */   
/*    */   public AbilityAltMode(E defaultMode, AbilityCore... children) {
/*  9 */     this.children = children;
/* 10 */     this.defaultMode = defaultMode;
/*    */   }
/*    */   
/*    */   public AbilityCore[] getChildren() {
/* 14 */     return this.children;
/*    */   }
/*    */   
/*    */   public boolean isChildren(AbilityCore core) {
/* 18 */     for (AbilityCore children : this.children) {
/* 19 */       if (children.equals(core)) {
/* 20 */         return true;
/*    */       }
/*    */     } 
/* 23 */     return false;
/*    */   }
/*    */   
/*    */   public E getDefaultMode() {
/* 27 */     return this.defaultMode;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\AbilityAltMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */