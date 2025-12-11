package xyz.pixelatedw.mineminenomi.api.abilities;

import net.minecraft.entity.LivingEntity;
import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;

@Deprecated
public interface IMorphAbility {
  MorphInfo getTransformation();
  
  boolean isTransformationActive(LivingEntity paramLivingEntity);
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\IMorphAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */