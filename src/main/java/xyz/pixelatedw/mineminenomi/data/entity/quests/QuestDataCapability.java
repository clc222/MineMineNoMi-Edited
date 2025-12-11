/*     */ package xyz.pixelatedw.mineminenomi.data.entity.quests;
/*     */ 
/*     */ import java.util.concurrent.Callable;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.common.capabilities.Capability;
/*     */ import net.minecraftforge.common.capabilities.CapabilityInject;
/*     */ import net.minecraftforge.common.capabilities.CapabilityManager;
/*     */ import net.minecraftforge.common.util.LazyOptional;
/*     */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*     */ 
/*     */ 
/*     */ public class QuestDataCapability
/*     */ {
/*     */   @CapabilityInject(IQuestData.class)
/*  22 */   public static final Capability<IQuestData> INSTANCE = null;
/*     */ 
/*     */   
/*     */   public static void register() {
/*  26 */     CapabilityManager.INSTANCE.register(IQuestData.class, new Capability.IStorage<IQuestData>()
/*     */         {
/*     */           
/*     */           public INBT writeNBT(Capability<IQuestData> capability, IQuestData instance, Direction side)
/*     */           {
/*  31 */             CompoundNBT props = new CompoundNBT();
/*     */             
/*  33 */             ListNBT questsInTracker = new ListNBT();
/*  34 */             for (int i = 0; i < (instance.getInProgressQuests()).length; i++) {
/*     */               
/*  36 */               Quest quest = instance.getInProgressQuests()[i];
/*  37 */               if (quest != null)
/*  38 */                 questsInTracker.add(quest.save()); 
/*     */             } 
/*  40 */             props.func_218657_a("quests_in_tracker", (INBT)questsInTracker);
/*     */             
/*  42 */             ListNBT finishedQuests = new ListNBT();
/*  43 */             for (int j = 0; j < instance.getFinishedQuests().size(); j++) {
/*     */               
/*  45 */               QuestId quest = instance.getFinishedQuests().get(j);
/*  46 */               CompoundNBT questNbt = new CompoundNBT();
/*  47 */               questNbt.func_74778_a("id", quest.getRegistryName().toString());
/*  48 */               finishedQuests.add(questNbt);
/*     */             } 
/*  50 */             props.func_218657_a("finished_quests", (INBT)finishedQuests);
/*     */             
/*  52 */             return (INBT)props;
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public void readNBT(Capability<IQuestData> capability, IQuestData instance, Direction side, INBT nbt) {
/*  58 */             CompoundNBT props = (CompoundNBT)nbt;
/*     */             
/*  60 */             instance.clearInProgressQuests();
/*  61 */             instance.clearFinishedQuests();
/*     */             
/*  63 */             ListNBT trackerQuests = props.func_150295_c("quests_in_tracker", 10);
/*  64 */             for (int i = 0; i < trackerQuests.size(); i++) {
/*     */ 
/*     */               
/*     */               try {
/*  68 */                 CompoundNBT nbtQuests = trackerQuests.func_150305_b(i);
/*  69 */                 QuestId questId = (QuestId)GameRegistry.findRegistry(QuestId.class).getValue(new ResourceLocation(nbtQuests.func_74779_i("id")));
/*  70 */                 if (questId != null) {
/*     */                   
/*  72 */                   Quest quest = questId.createQuest();
/*  73 */                   quest.load(nbtQuests);
/*  74 */                   instance.setInProgressQuest(i, quest);
/*     */                 } 
/*  76 */               } catch (Exception e) {}
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*  82 */             ListNBT finishedQuests = props.func_150295_c("finished_quests", 10);
/*  83 */             for (int j = 0; j < finishedQuests.size(); j++) {
/*     */ 
/*     */               
/*     */               try {
/*  87 */                 CompoundNBT nbtQuests = finishedQuests.func_150305_b(j);
/*  88 */                 QuestId quest = (QuestId)GameRegistry.findRegistry(QuestId.class).getValue(new ResourceLocation(nbtQuests.func_74779_i("id")));
/*  89 */                 instance.addFinishedQuest(quest);
/*     */               }
/*  91 */               catch (Exception e) {}
/*     */             } 
/*     */           }
/*     */         }QuestDataBase::new);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IQuestData get(PlayerEntity entity) {
/* 103 */     return (IQuestData)entity.getCapability(INSTANCE, null).orElse(new QuestDataBase());
/*     */   }
/*     */ 
/*     */   
/*     */   public static LazyOptional<IQuestData> getLazy(PlayerEntity entity) {
/* 108 */     return entity.getCapability(INSTANCE, null);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\quests\QuestDataCapability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */