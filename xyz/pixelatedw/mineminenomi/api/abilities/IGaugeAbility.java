package xyz.pixelatedw.mineminenomi.api.abilities;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@Deprecated
public interface IGaugeAbility<T extends Ability> {
  @OnlyIn(Dist.CLIENT)
  void renderGauge(PlayerEntity paramPlayerEntity, MatrixStack paramMatrixStack, int paramInt1, int paramInt2, T paramT);
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\IGaugeAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */