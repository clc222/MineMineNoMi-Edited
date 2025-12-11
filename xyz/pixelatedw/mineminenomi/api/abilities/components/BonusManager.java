/*    */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.UUID;
/*    */ 
/*    */ 
/*    */ public class BonusManager
/*    */ {
/*    */   private UUID id;
/* 12 */   private Map<UUID, BonusValue> bonuses = new HashMap<>();
/*    */   
/*    */   public BonusManager(UUID id) {
/* 15 */     this.id = id;
/*    */   }
/*    */   
/*    */   public float applyBonus(float value) {
/* 19 */     for (BonusValue bonus : this.bonuses.values()) {
/* 20 */       switch (bonus.getType()) {
/*    */         case ADD:
/* 22 */           value += bonus.getValue();
/*    */         
/*    */         case MUL:
/* 25 */           value *= bonus.getValue();
/*    */       } 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     } 
/* 32 */     value = Math.max(0.0F, value);
/* 33 */     return value;
/*    */   }
/*    */   
/*    */   public boolean hasBonus(UUID id) {
/* 37 */     return this.bonuses.containsKey(id);
/*    */   }
/*    */   
/*    */   public void addBonus(UUID id, String name, BonusOperation type, float value) {
/* 41 */     this.bonuses.put(id, new BonusValue(name, type, value));
/*    */   }
/*    */   
/*    */   public void removeBonus(UUID id) {
/* 45 */     this.bonuses.remove(id);
/*    */   }
/*    */   
/*    */   public void clearBonuses() {
/* 49 */     this.bonuses.clear();
/*    */   }
/*    */   
/*    */   public UUID getId() {
/* 53 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setBonusMap(Map<UUID, BonusValue> map) {
/* 57 */     this.bonuses = map;
/*    */   }
/*    */   public Set<Map.Entry<UUID, BonusValue>> getBonuses() {
/* 60 */     return this.bonuses.entrySet();
/*    */   }
/*    */   
/*    */   public static class BonusValue
/*    */   {
/*    */     private BonusOperation type;
/*    */     private String name;
/*    */     private float value;
/*    */     
/*    */     public BonusValue(String name, BonusOperation type, float value) {
/* 70 */       this.name = name;
/* 71 */       this.type = type;
/* 72 */       this.value = value;
/*    */     }
/*    */     
/*    */     public String getName() {
/* 76 */       return this.name;
/*    */     }
/*    */     
/*    */     public BonusOperation getType() {
/* 80 */       return this.type;
/*    */     }
/*    */     
/*    */     public float getValue() {
/* 84 */       return this.value;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 89 */       return this.name + " | " + getType().name() + " " + getValue();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\BonusManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */