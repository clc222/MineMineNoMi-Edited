/*     */ package xyz.pixelatedw.mineminenomi.api.quests;
/*     */ import java.io.Serializable;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public abstract class Quest {
/*  23 */   private List<Objective> objectives = new ArrayList<>();
/*     */   
/*     */   private QuestId core;
/*     */   
/*     */   protected IStarting onStartEvent = player -> true;
/*     */   protected ICompleting onCompleteEvent = player -> true;
/*     */   protected IShouldRestart shouldRestartEvent = player -> false;
/*     */   
/*     */   public Quest(QuestId core) {
/*  32 */     this.core = core;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QuestId getCore() {
/*  41 */     return this.core;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object quest) {
/*  47 */     if (quest == null || getCore() == null) {
/*  48 */       return false;
/*     */     }
/*  50 */     if (quest instanceof Quest) {
/*     */       
/*  52 */       if (((Quest)quest).getCore() == null) {
/*  53 */         return false;
/*     */       }
/*  55 */       if (getCore().equals(((Quest)quest).getCore())) {
/*  56 */         return true;
/*     */       }
/*  58 */     } else if (quest instanceof QuestId) {
/*     */       
/*  60 */       if (getCore().equals(quest)) {
/*  61 */         return true;
/*     */       }
/*     */     } 
/*  64 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeQuestItem(PlayerEntity player, Item item, int amount) {
/*  69 */     int id = WyHelper.getIndexOfItemStack(item, (IInventory)player.field_71071_by);
/*     */     
/*  71 */     if (id < 0) {
/*     */       
/*  73 */       player.func_145747_a((ITextComponent)new TranslationTextComponent("Missing quest items: %s", new Object[] { (new ItemStack((IItemProvider)item)).func_200301_q().getString() }), Util.field_240973_b_);
/*  74 */       return false;
/*     */     } 
/*     */     
/*  77 */     player.field_71071_by.func_70301_a(id).func_190918_g(amount);
/*  78 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkRestart(PlayerEntity player) {
/*  87 */     return this.shouldRestartEvent.check(player);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean triggerCompleteEvent(PlayerEntity player) {
/*  92 */     return this.onCompleteEvent.check(player);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean triggerStartEvent(PlayerEntity player) {
/*  97 */     return this.onStartEvent.check(player);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addObjectives(Objective... objectives) {
/* 102 */     for (Objective obj : objectives) {
/* 103 */       addObjective(obj);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addObjective(Objective objective) {
/* 108 */     if (!this.objectives.contains(objective)) {
/* 109 */       this.objectives.add(objective);
/*     */     }
/*     */   }
/*     */   
/*     */   public List<Objective> getObjectives() {
/* 114 */     return this.objectives;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplete() {
/* 119 */     return this.objectives.stream().allMatch(objective -> (!objective.isOptional() && objective.isComplete()));
/*     */   }
/*     */ 
/*     */   
/*     */   public double getProgress() {
/* 124 */     int maxProgress = this.objectives.size();
/* 125 */     int completed = ((List)this.objectives.stream().filter(objective -> (!objective.isOptional() && objective.isComplete())).collect(Collectors.toList())).size();
/*     */     
/* 127 */     double progress = completed / maxProgress;
/*     */     
/* 129 */     return progress;
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetProgress(PlayerEntity player) {
/* 134 */     this.objectives.stream().forEach(o -> o.setProgress(player, 0.0D, true));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLocked(IQuestData props) {
/* 139 */     return getCore().isLocked(props);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundNBT save() {
/* 148 */     CompoundNBT nbt = new CompoundNBT();
/*     */     
/* 150 */     nbt.func_74778_a("id", this.core.getRegistryName().toString());
/* 151 */     ListNBT objectivesData = new ListNBT();
/* 152 */     for (Objective obj : getObjectives())
/*     */     {
/* 154 */       objectivesData.add(obj.save());
/*     */     }
/* 156 */     nbt.func_218657_a("objectives", (INBT)objectivesData);
/*     */     
/* 158 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 163 */     ListNBT objectivesData = nbt.func_150295_c("objectives", 10);
/* 164 */     for (int i = 0; i < objectivesData.size(); i++) {
/*     */       
/* 166 */       CompoundNBT questData = objectivesData.func_150305_b(i);
/* 167 */       ((Objective)getObjectives().get(i)).load(questData);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static interface ICompleting extends Serializable {
/*     */     boolean check(PlayerEntity param1PlayerEntity);
/*     */   }
/*     */   
/*     */   public static interface IStarting extends Serializable {
/*     */     boolean check(PlayerEntity param1PlayerEntity);
/*     */   }
/*     */   
/*     */   public static interface IShouldRestart extends Serializable {
/*     */     boolean check(PlayerEntity param1PlayerEntity);
/*     */   }
/*     */   
/*     */   public static interface IFactory<A extends Quest> {
/*     */     A create(QuestId<A> param1QuestId);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\quests\Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */