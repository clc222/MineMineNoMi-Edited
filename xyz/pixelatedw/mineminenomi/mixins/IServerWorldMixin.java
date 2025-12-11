package xyz.pixelatedw.mineminenomi.mixins;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.IServerWorldInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({ServerWorld.class})
public interface IServerWorldMixin {
  @Accessor("serverLevelData")
  IServerWorldInfo getServerLevelData();
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\IServerWorldMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */