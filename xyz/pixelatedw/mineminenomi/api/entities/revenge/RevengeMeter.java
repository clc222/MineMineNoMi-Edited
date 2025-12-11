/*     */ package xyz.pixelatedw.mineminenomi.api.entities.revenge;
/*     */ 
/*     */ import com.google.common.collect.EvictingQueue;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Queue;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RevengeMeter
/*     */ {
/*     */   private static final int QUEUE_ENTRIES = 20;
/*     */   private LivingEntity entity;
/*     */   private int revengeValue;
/*     */   private int prevRevengeValue;
/*  21 */   private int maxRevengeValue = 100;
/*  22 */   private int decreasingValue = 1;
/*     */   
/*     */   private boolean canClear;
/*  25 */   private List<IRevengeCheck> checks = new ArrayList<>();
/*  26 */   private Map<Class<? extends IRevengeCheck>, Integer> passedChecksTotal = new HashMap<>();
/*  27 */   private Queue<IRevengeCheck> passedChecksHistory = (Queue<IRevengeCheck>)EvictingQueue.create(20);
/*     */   
/*     */   public RevengeMeter(LivingEntity entity, int maxRevenge, int decreasingValue) {
/*  30 */     this.entity = entity;
/*  31 */     this.maxRevengeValue = maxRevenge;
/*  32 */     this.decreasingValue = decreasingValue;
/*     */   }
/*     */   
/*     */   public void addCheck(IRevengeCheck check) {
/*  36 */     this.checks.add(check);
/*     */   }
/*     */   
/*     */   public void tick() {
/*  40 */     if (this.entity.field_70170_p.func_82737_E() % 40L == 0L) {
/*  41 */       int checksPassed = 0;
/*  42 */       for (IRevengeCheck check : this.checks) {
/*  43 */         if (check.check(this.entity)) {
/*  44 */           addRevengeValue(check.revengeMeterGain());
/*  45 */           this.passedChecksHistory.add(check);
/*  46 */           Integer checksMapVal = this.passedChecksTotal.get(check.getClass());
/*  47 */           if (checksMapVal == null) {
/*  48 */             this.passedChecksTotal.put(check.getClass(), Integer.valueOf(1));
/*     */           } else {
/*     */             
/*  51 */             this.passedChecksTotal.put(check.getClass(), Integer.valueOf(checksMapVal.intValue() + 1));
/*     */           } 
/*  53 */           checksPassed++;
/*  54 */           this.canClear = true;
/*     */         } 
/*     */       } 
/*     */       
/*  58 */       if (checksPassed == 0) {
/*  59 */         reduceRevengeValue(this.decreasingValue);
/*     */       }
/*     */       
/*  62 */       if (this.revengeValue <= 0 && this.canClear) {
/*  63 */         this.passedChecksTotal.clear();
/*  64 */         this.passedChecksHistory.clear();
/*  65 */         this.canClear = false;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Map<Class<? extends IRevengeCheck>, Integer> getLastChecksMap() {
/*  71 */     return this.passedChecksTotal;
/*     */   }
/*     */   
/*     */   public Queue<IRevengeCheck> getLastChecks() {
/*  75 */     return this.passedChecksHistory;
/*     */   }
/*     */   
/*     */   public boolean isCheckPresentInLastNEntries(Class<? extends IRevengeCheck> clz, int entriesToCheck, int neededEntries) {
/*  79 */     if (this.passedChecksHistory.isEmpty()) {
/*  80 */       return false;
/*     */     }
/*  82 */     entriesToCheck = Math.min(entriesToCheck, this.passedChecksHistory.size());
/*  83 */     entriesToCheck = Math.max(0, this.passedChecksHistory.size() - entriesToCheck);
/*  84 */     long count = this.passedChecksHistory.stream().skip(entriesToCheck).filter(c -> c.getClass().equals(clz)).count();
/*  85 */     return (count >= neededEntries);
/*     */   }
/*     */   
/*     */   public int countCheckTriggers(Class<? extends IRevengeCheck> clz) {
/*  89 */     return ((Integer)getLastChecksMap().getOrDefault(clz, Integer.valueOf(0))).intValue();
/*     */   }
/*     */   
/*     */   public void resetMarkers() {
/*  93 */     this.revengeValue = 0;
/*  94 */     this.checks.forEach(check -> check.resetMarkers());
/*     */   }
/*     */   
/*     */   public boolean isRevengeMaxed() {
/*  98 */     return (getRevengePercentage() >= 1.0F);
/*     */   }
/*     */   
/*     */   public boolean isRevengeAbove(float percentage) {
/* 102 */     return (getRevengePercentage() > percentage);
/*     */   }
/*     */   
/*     */   public boolean isRevengeUnder(float percentage) {
/* 106 */     return (getRevengePercentage() < percentage);
/*     */   }
/*     */   
/*     */   public int getRevengeValue() {
/* 110 */     return MathHelper.func_76125_a(this.revengeValue, 0, this.maxRevengeValue);
/*     */   }
/*     */   
/*     */   public void addRevengeValue(int value) {
/* 114 */     setRevengeValue(this.revengeValue + value);
/*     */   }
/*     */   
/*     */   public void reduceRevengeValue(int value) {
/* 118 */     setRevengeValue(this.revengeValue - value);
/*     */   }
/*     */   
/*     */   public void setRevengeValue(int value) {
/* 122 */     this.prevRevengeValue = this.revengeValue;
/* 123 */     this.revengeValue = MathHelper.func_76125_a(value, 0, this.maxRevengeValue);
/*     */   }
/*     */   
/*     */   public float getRevengePercentage() {
/* 127 */     return getRevengeValue() / this.maxRevengeValue;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\entities\revenge\RevengeMeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */