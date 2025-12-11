/*    */ package xyz.pixelatedw.mineminenomi.api.util;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class Signal<T> {
/*  7 */   private List<Listener<T>> listeners = new ArrayList<>();
/*    */   
/*    */   public void add(Listener<T> listener) {
/* 10 */     this.listeners.add(listener);
/*    */   }
/*    */   
/*    */   public void remove(Listener<T> listener) {
/* 14 */     this.listeners.remove(listener);
/*    */   }
/*    */   
/*    */   public void clearListeners() {
/* 18 */     this.listeners.clear();
/*    */   }
/*    */   
/*    */   public void dispatch(T object) {
/* 22 */     for (Listener<T> listener : this.listeners)
/* 23 */       listener.receive(this, object); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\ap\\util\Signal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */