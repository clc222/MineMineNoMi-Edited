package xyz.pixelatedw.mineminenomi.data.entity.haki;

import net.minecraft.entity.LivingEntity;
import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;

public interface IHakiData {
  IHakiData setOwner(LivingEntity paramLivingEntity);
  
  float getTotalHakiExp();
  
  float getMaxHakiExp();
  
  int getHakiOveruse();
  
  int getMaxOveruse();
  
  void alterHakiOveruse(int paramInt);
  
  void setHakiOveruse(int paramInt);
  
  float getKenbunshokuHakiExp();
  
  boolean alterKenbunshokuHakiExp(float paramFloat, StatChangeSource paramStatChangeSource);
  
  void setKenbunshokuHakiExp(float paramFloat);
  
  float getBusoshokuHakiExp();
  
  boolean alterBusoshokuHakiExp(float paramFloat, StatChangeSource paramStatChangeSource);
  
  void setBusoshokuHakiExp(float paramFloat);
  
  int getHaoshokuHakiColour();
  
  void setHaoshokuHakiColour(int paramInt);
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\haki\IHakiData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */