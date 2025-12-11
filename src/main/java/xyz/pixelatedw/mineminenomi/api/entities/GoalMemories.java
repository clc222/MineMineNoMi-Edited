/*    */ package xyz.pixelatedw.mineminenomi.api.entities;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.entity.ai.brain.Memory;
/*    */ import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GoalMemories
/*    */ {
/* 15 */   private final Map<MemoryModuleType<?>, Memory<?>> memories = Maps.newHashMap();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 21 */     for (Iterator<Map.Entry<MemoryModuleType<?>, Memory<?>>> it = this.memories.entrySet().iterator(); it.hasNext(); ) {
/* 22 */       Map.Entry<MemoryModuleType<?>, Memory<?>> entry = it.next();
/* 23 */       Memory<?> memory = entry.getValue();
/* 24 */       memory.func_234064_a_();
/* 25 */       if (memory.func_234073_d_()) {
/* 26 */         it.remove();
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public <U> Optional<U> getMemory(MemoryModuleType<U> type) {
/*    */     try {
/* 33 */       return Optional.of((U)((Memory)this.memories.get(type)).func_234072_c_());
/*    */     }
/* 35 */     catch (NullPointerException e) {
/* 36 */       return Optional.empty();
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean hasMemoryValue(MemoryModuleType<?> type) {
/* 41 */     return checkMemory(type);
/*    */   }
/*    */   
/*    */   public <U> void eraseMemory(MemoryModuleType<U> type) {
/* 45 */     this.memories.remove(type);
/*    */   }
/*    */   
/*    */   public <U> void setMemory(MemoryModuleType<U> memoryType, U memory) {
/* 49 */     setMemoryInternal(memoryType, Memory.func_234068_a_(memory));
/*    */   }
/*    */   
/*    */   public <U> void setMemoryWithExpiry(MemoryModuleType<U> memoryType, U memory, long timesToLive) {
/* 53 */     setMemoryInternal(memoryType, Memory.func_234069_a_(memory, timesToLive));
/*    */   }
/*    */   
/*    */   public boolean checkMemory(MemoryModuleType<?> memoryType) {
/* 57 */     Memory<?> memory = this.memories.get(memoryType);
/* 58 */     return !(memory == null);
/*    */   }
/*    */   
/*    */   public <U> boolean isMemoryValue(MemoryModuleType<U> memoryType, U memory) {
/* 62 */     return !hasMemoryValue(memoryType) ? false : getMemory(memoryType).filter(p_233704_1_ -> p_233704_1_.equals(memory))
/*    */       
/* 64 */       .isPresent();
/*    */   }
/*    */   
/*    */   private <U> void setMemoryInternal(MemoryModuleType<U> memoryType, Memory<?> memory) {
/* 68 */     if (this.memories.containsKey(memoryType)) {
/* 69 */       if (isEmptyCollection(memory.func_234072_c_())) {
/* 70 */         eraseMemory(memoryType);
/*    */       } else {
/*    */         
/* 73 */         this.memories.put(memoryType, memory);
/*    */       } 
/*    */     } else {
/*    */       
/* 77 */       this.memories.put(memoryType, memory);
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean isEmptyCollection(Object collection) {
/* 82 */     return (collection instanceof Collection && ((Collection)collection).isEmpty());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\entities\GoalMemories.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */