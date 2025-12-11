/*    */ package xyz.pixelatedw.mineminenomi.api.util;
/*    */ 
/*    */ import com.google.common.collect.HashMultimap;
/*    */ import com.google.common.collect.Multimap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.function.Consumer;
/*    */ import java.util.function.Predicate;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ public class PriorityEventPool<E> {
/* 12 */   private final Multimap<Integer, E> events = (Multimap<Integer, E>)HashMultimap.create();
/*    */   public static final int DEFAULT_PRIORITY = 100;
/*    */   private boolean isDirty;
/*    */   private long lastCount;
/*    */   
/*    */   public void addEvent(E event) {
/* 18 */     addEvent(100, event);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addEvent(int priority, E event) {
/* 25 */     this.events.put(Integer.valueOf(priority), event);
/* 26 */     this.isDirty = true;
/*    */   }
/*    */   
/*    */   public void removeEvent(E event) {
/* 30 */     if (this.events.containsValue(event)) {
/* 31 */       int eventKey = -1;
/* 32 */       for (Iterator<Integer> iterator = this.events.keySet().iterator(); iterator.hasNext(); ) { int key = ((Integer)iterator.next()).intValue();
/* 33 */         if (eventKey >= 0) {
/*    */           break;
/*    */         }
/* 36 */         for (E value : this.events.get(Integer.valueOf(key))) {
/* 37 */           if (value.equals(event)) {
/* 38 */             eventKey = key;
/*    */           }
/*    */         }  }
/*    */ 
/*    */ 
/*    */       
/* 44 */       this.events.remove(Integer.valueOf(eventKey), event);
/* 45 */       this.isDirty = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Stream<E> getEventsStream() {
/* 53 */     return this.events.entries().stream()
/* 54 */       .sorted((e1, e2) -> (((Integer)e1.getKey()).intValue() > ((Integer)e2.getKey()).intValue()) ? 1 : -1)
/* 55 */       .map(e -> e.getValue());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean dispatchCancelable(Predicate<E> predicate) {
/* 63 */     long failedEvents = getEventsStream().filter(predicate).count();
/* 64 */     return (failedEvents > 0L);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void dispatch(Consumer<E> consumer) {
/* 71 */     getEventsStream().forEachOrdered(consumer);
/*    */   }
/*    */   
/*    */   public long countEventsInPool() {
/* 75 */     if (this.isDirty) {
/* 76 */       this.lastCount = getEventsStream().count();
/* 77 */       this.isDirty = false;
/*    */     } 
/* 79 */     return this.lastCount;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\ap\\util\PriorityEventPool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */