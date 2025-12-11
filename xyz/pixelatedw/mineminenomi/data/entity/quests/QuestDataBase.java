/*     */ package xyz.pixelatedw.mineminenomi.data.entity.quests;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ 
/*     */ public class QuestDataBase
/*     */   implements IQuestData {
/*     */   private PlayerEntity owner;
/*  17 */   private Quest[] inProgressQuests = new Quest[4];
/*  18 */   private List<QuestId> finishedQuests = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public IQuestData setOwner(PlayerEntity entity) {
/*  22 */     this.owner = entity;
/*  23 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addInProgressQuest(Quest quest) {
/*  29 */     for (int i = 0; i < this.inProgressQuests.length; i++) {
/*     */       
/*  31 */       Quest ogQuest = this.inProgressQuests[i];
/*  32 */       if (ogQuest == null) {
/*     */         
/*  34 */         this.inProgressQuests[i] = quest;
/*  35 */         return true;
/*     */       } 
/*     */     } 
/*  38 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setInProgressQuest(int slot, Quest quest) {
/*  44 */     Quest ogQuest = getInProgressQuest(quest);
/*  45 */     if (ogQuest == null && slot <= 4) {
/*     */       
/*  47 */       this.inProgressQuests[slot] = quest;
/*  48 */       return true;
/*     */     } 
/*  50 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeInProgressQuest(QuestId<Quest> quest) {
/*  56 */     Quest ogQuest = getInProgressQuest(quest);
/*  57 */     if (ogQuest != null)
/*     */     {
/*  59 */       for (int i = 0; i < this.inProgressQuests.length; i++) {
/*     */         
/*  61 */         Quest inProgressQuest = this.inProgressQuests[i];
/*  62 */         if (inProgressQuest != null && inProgressQuest.equals(ogQuest)) {
/*     */           
/*  64 */           inProgressQuest.resetProgress(this.owner);
/*  65 */           this.inProgressQuests[i] = null;
/*  66 */           return true;
/*     */         } 
/*     */       } 
/*     */     }
/*  70 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeInProgressQuest(Quest quest) {
/*  76 */     Quest ogQuest = getInProgressQuest(quest);
/*  77 */     if (ogQuest != null)
/*     */     {
/*  79 */       for (int i = 0; i < this.inProgressQuests.length; i++) {
/*     */         
/*  81 */         Quest inProgressQuest = this.inProgressQuests[i];
/*  82 */         if (inProgressQuest != null && inProgressQuest.equals(ogQuest)) {
/*     */           
/*  84 */           inProgressQuest.resetProgress(this.owner);
/*  85 */           this.inProgressQuests[i] = null;
/*  86 */           return true;
/*     */         } 
/*     */       } 
/*     */     }
/*  90 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasInProgressQuest(QuestId quest) {
/*  96 */     return Arrays.<Quest>stream(this.inProgressQuests)
/*  97 */       .filter(qst -> (qst != null))
/*  98 */       .anyMatch(qst -> qst.getCore().equals(quest));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasInProgressQuest(Quest quest) {
/* 104 */     return Arrays.<Quest>stream(this.inProgressQuests)
/* 105 */       .filter(qst -> (qst != null))
/* 106 */       .anyMatch(qst -> qst.equals(quest));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Quest> T getInProgressQuest(QuestId<T> quest) {
/* 112 */     return (T)Arrays.<Quest>stream(this.inProgressQuests)
/* 113 */       .filter(qst -> (qst != null && qst.getCore().equals(quest)))
/* 114 */       .findFirst().orElse(null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Quest> T getInProgressQuest(T quest) {
/* 120 */     return (T)Arrays.<Quest>stream(this.inProgressQuests)
/* 121 */       .filter(qst -> (qst != null && qst.equals(quest)))
/* 122 */       .findFirst().orElse(null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Quest> T getInProgressQuest(int slot) {
/* 128 */     return (T)this.inProgressQuests[slot];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInProgressQuestSlot(Quest quest) {
/* 134 */     for (int i = 0; i < this.inProgressQuests.length; i++) {
/*     */       
/* 136 */       if (this.inProgressQuests[i] != null && this.inProgressQuests[i].equals(quest)) {
/* 137 */         return i;
/*     */       }
/*     */     } 
/* 140 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Objective> List<T> getInProgressObjectives() {
/* 146 */     return (List<T>)Arrays.<Quest>stream(getInProgressQuests())
/* 147 */       .filter(q -> (q != null && !q.isComplete()))
/* 148 */       .flatMap(q -> q.getObjectives().stream())
/* 149 */       .filter(o -> (!o.isHidden() && !o.isLocked()))
/* 150 */       .collect(Collectors.toList());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Quest[] getInProgressQuests() {
/* 156 */     return this.inProgressQuests;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearInProgressQuests() {
/* 162 */     for (int i = 0; i < this.inProgressQuests.length; i++) {
/*     */       
/* 164 */       Quest quest = this.inProgressQuests[i];
/* 165 */       if (quest != null)
/*     */       {
/* 167 */         this.inProgressQuests[i] = null;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int countInProgressQuests() {
/* 175 */     return Arrays.<Quest>stream(this.inProgressQuests)
/* 176 */       .filter(quest -> (quest != null))
/* 177 */       .mapToInt(q -> 1).sum();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addFinishedQuest(QuestId quest) {
/* 183 */     QuestId ogQuest = getFinishedQuest(quest);
/* 184 */     if (ogQuest == null) {
/*     */       
/* 186 */       this.finishedQuests.add(quest);
/* 187 */       return true;
/*     */     } 
/* 189 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeFinishedQuest(QuestId quest) {
/* 195 */     QuestId ogQuest = getFinishedQuest(quest);
/* 196 */     if (ogQuest != null) {
/*     */       
/* 198 */       this.finishedQuests.remove(ogQuest);
/* 199 */       return true;
/*     */     } 
/* 201 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFinishedQuest(QuestId quest) {
/* 207 */     if (!CommonConfig.INSTANCE.isQuestProgressionEnabled()) {
/* 208 */       return true;
/*     */     }
/* 210 */     return this.finishedQuests.stream().filter(q -> (q != null)).anyMatch(qst -> qst.equals(quest));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends QuestId> T getFinishedQuest(T quest) {
/* 216 */     return (T)this.finishedQuests.stream().filter(qst -> (qst != null && qst.equals(quest))).findFirst().orElse(null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<QuestId> getFinishedQuests() {
/* 222 */     return (List<QuestId>)this.finishedQuests.stream().filter(q -> (q != null)).collect(Collectors.toList());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearFinishedQuests() {
/* 228 */     this.finishedQuests.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int countFinishedQuests() {
/* 234 */     return this.finishedQuests.size();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\quests\QuestDataBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */