/*    */ package xyz.pixelatedw.mineminenomi.api.entities;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
/*    */ 
/*    */ public interface IGoalMemoriesEntity
/*    */ {
/*    */   GoalMemories getGoalMemories();
/*    */   
/*    */   default <U> Optional<U> getMemory(MemoryModuleType<U> type) {
/* 11 */     return getGoalMemories().getMemory(type);
/*    */   }
/*    */   
/*    */   default boolean hasMemoryValue(MemoryModuleType<?> type) {
/* 15 */     return getGoalMemories().hasMemoryValue(type);
/*    */   }
/*    */   
/*    */   default <U> void eraseMemory(MemoryModuleType<U> type) {
/* 19 */     getGoalMemories().eraseMemory(type);
/*    */   }
/*    */   
/*    */   default <U> void setMemory(MemoryModuleType<U> memoryType, U memory) {
/* 23 */     getGoalMemories().setMemory(memoryType, memory);
/*    */   }
/*    */   
/*    */   default <U> void setMemoryWithExpiry(MemoryModuleType<U> memoryType, U memory, long timesToLive) {
/* 27 */     getGoalMemories().setMemoryWithExpiry(memoryType, memory, timesToLive);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\entities\IGoalMemoriesEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */