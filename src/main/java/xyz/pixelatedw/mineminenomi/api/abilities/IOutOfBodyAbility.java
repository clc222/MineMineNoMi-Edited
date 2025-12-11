package xyz.pixelatedw.mineminenomi.api.abilities;

import javax.annotation.Nullable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

@Deprecated
public interface IOutOfBodyAbility {
  boolean isActive();
  
  float getMaxRange();
  
  @Nullable
  BlockPos getPivotPoint();
  
  boolean isPhysical();
  
  default void startOutOfBody(PlayerEntity player) {}
  
  default void stopOutOfBody(PlayerEntity player) {}
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\IOutOfBodyAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */