/*    */ package xyz.pixelatedw.mineminenomi.api.quests;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraftforge.registries.ForgeRegistryEntry;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class QuestId<A extends Quest>
/*    */   extends ForgeRegistryEntry<QuestId<?>>
/*    */ {
/*    */   private final String name;
/*    */   private Quest.IFactory<A> factory;
/* 16 */   private List<QuestId> requirements = new ArrayList<>();
/*    */ 
/*    */   
/*    */   protected QuestId(String name, Quest.IFactory<A> factory) {
/* 20 */     this.name = name;
/* 21 */     this.factory = factory;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<QuestId> getRequirements() {
/* 26 */     return this.requirements;
/*    */   }
/*    */ 
/*    */   
/*    */   private void setRequirements(List<QuestId> requirements) {
/* 31 */     this.requirements = requirements;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 36 */     return this.name;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getLocalizedTitle() {
/* 41 */     String id = WyHelper.getResourceName(getName());
/* 42 */     return (new TranslationTextComponent(String.format("quest.mineminenomi." + id, new Object[0]))).getString();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isLocked(IQuestData props) {
/* 47 */     List<QuestId> reqs = getRequirements();
/* 48 */     if (reqs == null || reqs.size() <= 0) {
/* 49 */       return false;
/*    */     }
/* 51 */     boolean isLocked = false;
/* 52 */     for (QuestId quest : reqs) {
/*    */       
/* 54 */       if (!props.hasFinishedQuest(quest)) {
/*    */         
/* 56 */         isLocked = true;
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 61 */     return isLocked;
/*    */   }
/*    */ 
/*    */   
/*    */   public A createQuest() {
/* 66 */     return this.factory.create(this);
/*    */   }
/*    */   
/*    */   public static class Builder<T extends Builder, A extends Quest>
/*    */   {
/*    */     private final String name;
/*    */     private Quest.IFactory<A> factory;
/* 73 */     private List<QuestId> requirements = new ArrayList<>();
/*    */ 
/*    */     
/*    */     public Builder(String name, Quest.IFactory<A> factory) {
/* 77 */       this.name = name;
/* 78 */       this.factory = factory;
/*    */     }
/*    */ 
/*    */     
/*    */     public Builder<T, A> addRequirements(QuestId... requirements) {
/* 83 */       for (QuestId req : requirements)
/* 84 */         this.requirements.add(req); 
/* 85 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public QuestId<A> build() {
/* 90 */       QuestId<A> core = new QuestId<>(this.name, this.factory);
/* 91 */       core.setRequirements(this.requirements);
/* 92 */       return core;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\quests\QuestId.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */