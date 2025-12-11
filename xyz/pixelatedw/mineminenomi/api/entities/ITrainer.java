package xyz.pixelatedw.mineminenomi.api.entities;

import java.util.List;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.quests.QuestId;

public interface ITrainer {
  List<QuestId> getAvailableQuests(PlayerEntity paramPlayerEntity);
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\entities\ITrainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */