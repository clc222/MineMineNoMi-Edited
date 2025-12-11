package xyz.pixelatedw.mineminenomi.mixins;

import net.minecraft.world.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({Explosion.class})
public interface IExplosionMixin {
  @Accessor("radius")
  float getRadius();
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\IExplosionMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */