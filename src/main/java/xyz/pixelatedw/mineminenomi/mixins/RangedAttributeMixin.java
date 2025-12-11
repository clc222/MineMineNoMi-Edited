package xyz.pixelatedw.mineminenomi.mixins;

import net.minecraft.entity.ai.attributes.RangedAttribute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = {RangedAttribute.class}, priority = 2000)
public interface RangedAttributeMixin {
  @Accessor("minValue")
  @Mutable
  void setMinValue(double paramDouble);
  
  @Accessor("maxValue")
  @Mutable
  void setMaxValue(double paramDouble);
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\RangedAttributeMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */