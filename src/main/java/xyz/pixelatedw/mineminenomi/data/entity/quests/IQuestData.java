package xyz.pixelatedw.mineminenomi.data.entity.quests;

import java.util.List;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.QuestId;

public interface IQuestData {
  IQuestData setOwner(PlayerEntity paramPlayerEntity);
  
  boolean addInProgressQuest(Quest paramQuest);
  
  boolean setInProgressQuest(int paramInt, Quest paramQuest);
  
  boolean removeInProgressQuest(QuestId paramQuestId);
  
  boolean removeInProgressQuest(Quest paramQuest);
  
  boolean hasInProgressQuest(QuestId paramQuestId);
  
  boolean hasInProgressQuest(Quest paramQuest);
  
  <T extends Quest> T getInProgressQuest(QuestId<T> paramQuestId);
  
  <T extends Quest> T getInProgressQuest(T paramT);
  
  <T extends Quest> T getInProgressQuest(int paramInt);
  
  int getInProgressQuestSlot(Quest paramQuest);
  
  <T extends xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective> List<T> getInProgressObjectives();
  
  Quest[] getInProgressQuests();
  
  void clearInProgressQuests();
  
  int countInProgressQuests();
  
  boolean addFinishedQuest(QuestId paramQuestId);
  
  boolean removeFinishedQuest(QuestId paramQuestId);
  
  boolean hasFinishedQuest(QuestId paramQuestId);
  
  <T extends QuestId> T getFinishedQuest(T paramT);
  
  List<QuestId> getFinishedQuests();
  
  void clearFinishedQuests();
  
  int countFinishedQuests();
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\quests\IQuestData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */