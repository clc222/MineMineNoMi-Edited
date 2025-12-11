/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import java.util.function.Supplier;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ public class AbilityPool2 {
/* 10 */   private final Set<AbilityCore<?>> abilities = new HashSet<>();
/* 11 */   private final HashMap<String, FlagValue<?>> flags = new HashMap<>();
/*    */   
/*    */   public AbilityPool2 addFlag(String flag, boolean value) {
/* 14 */     this.flags.put(flag, new BooleanFlagValue(Boolean.valueOf(value)));
/* 15 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public <T> T getFlagValue(String flag, Supplier<T> defaultValue) {
/* 23 */     FlagValue<T> value = (FlagValue<T>)this.flags.get(flag);
/* 24 */     if (value != null) {
/* 25 */       return value.get();
/*    */     }
/*    */     
/* 28 */     return defaultValue.get();
/*    */   }
/*    */   
/*    */   public void addAbilityCore(AbilityCore<?> core) {
/* 32 */     this.abilities.add(core);
/*    */   }
/*    */   
/*    */   public boolean hasAbilityCore(AbilityCore<?> core) {
/* 36 */     return this.abilities.contains(core);
/*    */   }
/*    */   
/*    */   public Stream<AbilityCore<?>> getAllPoolCores() {
/* 40 */     return this.abilities.stream();
/*    */   }
/*    */   
/*    */   public static abstract class FlagValue<T> {
/*    */     protected T value;
/*    */     
/*    */     public FlagValue(T value) {
/* 47 */       this.value = value;
/*    */     }
/*    */     
/*    */     public T get() {
/* 51 */       return this.value;
/*    */     }
/*    */   }
/*    */   
/*    */   public static class BooleanFlagValue extends FlagValue<Boolean> {
/*    */     public BooleanFlagValue(Boolean value) {
/* 57 */       super(value);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\AbilityPool2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */