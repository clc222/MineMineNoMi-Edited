package xyz.pixelatedw.mineminenomi.api.quests.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

public interface IKillEntityObjective {
  boolean checkKill(PlayerEntity paramPlayerEntity, LivingEntity paramLivingEntity, DamageSource paramDamageSource);
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\quests\objectives\IKillEntityObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */