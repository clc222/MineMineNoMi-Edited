/*     */ package xyz.pixelatedw.mineminenomi.api.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ public class WeightedList<T>
/*     */ {
/*  12 */   private final List<Entry> entries = new ArrayList<>();
/*  13 */   private int maxWeight = 1;
/*     */   private float totalWeight;
/*     */   
/*     */   public WeightedList(Object... objects) {
/*  17 */     if (objects.length == 0) {
/*     */       return;
/*     */     }
/*     */     
/*  21 */     if (objects.length == 1) {
/*  22 */       addEntry((T)objects[0], 100.0F);
/*     */       
/*     */       return;
/*     */     } 
/*  26 */     if (objects.length % 2 != 0) {
/*     */       try {
/*  28 */         throw new Exception("Number of parameters must either be 0, 1 or divisible by 2, number of parameters found is " + objects.length);
/*     */       }
/*  30 */       catch (Exception e) {
/*  31 */         e.printStackTrace();
/*     */         
/*     */         return;
/*     */       } 
/*     */     }
/*  36 */     for (int i = 0; i < objects.length; i += 2) {
/*  37 */       if (objects[i + 1] instanceof Number) {
/*  38 */         float f = ((Number)objects[i + 1]).floatValue();
/*  39 */         addEntry((T)objects[i], f);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addEntry(T object, float weight) {
/*  45 */     if (weight < 1.0F) {
/*     */       return;
/*     */     }
/*     */     
/*  49 */     Entry entry = new Entry();
/*  50 */     entry.object = object;
/*  51 */     entry.weight = weight;
/*  52 */     this.totalWeight += weight;
/*  53 */     this.entries.add(entry);
/*     */     
/*  55 */     if (weight > this.maxWeight) {
/*  56 */       this.maxWeight = (int)weight;
/*     */     }
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public T pick(Random rand) {
/*  62 */     if (this.entries.size() == 1) {
/*  63 */       return ((Entry)this.entries.get(0)).object;
/*     */     }
/*  65 */     int weight = rand.nextInt(this.maxWeight);
/*  66 */     if (this.entries.isEmpty()) {
/*  67 */       return null;
/*     */     }
/*  69 */     int size = this.entries.size();
/*  70 */     int index = rand.nextInt(size);
/*     */     while (true) {
/*  72 */       Entry entry = this.entries.get(index);
/*  73 */       if (entry.weight > weight) {
/*  74 */         return entry.object;
/*     */       }
/*  76 */       index = (index >= size) ? (index + 1) : 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() {
/*  81 */     this.entries.clear();
/*     */   }
/*     */   
/*     */   public float getWeight(T obj) {
/*  85 */     if (obj == null) {
/*  86 */       return -1.0F;
/*     */     }
/*  88 */     Optional<Entry> entry = this.entries.stream().filter(e -> (e.object == obj)).findFirst();
/*  89 */     if (entry.isPresent()) {
/*  90 */       return ((Entry)entry.get()).weight;
/*     */     }
/*     */     
/*  93 */     return -1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/*  98 */     return this.entries.size();
/*     */   }
/*     */   
/*     */   public double getTotalWeight() {
/* 102 */     return this.totalWeight;
/*     */   }
/*     */   
/*     */   private class Entry {
/*     */     float weight;
/*     */     T object;
/*     */     
/*     */     private Entry() {}
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\ap\\util\WeightedList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */