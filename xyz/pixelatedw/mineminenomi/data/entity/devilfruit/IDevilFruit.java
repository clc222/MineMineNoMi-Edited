package xyz.pixelatedw.mineminenomi.data.entity.devilfruit;

import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;

public interface IDevilFruit {
  int getVersion();
  
  IDevilFruit setOwner(LivingEntity paramLivingEntity);
  
  Optional<ResourceLocation> getDevilFruit();
  
  Item getDevilFruitItem();
  
  void setDevilFruit(@Nullable ResourceLocation paramResourceLocation);
  
  void setDevilFruit(AkumaNoMiItem paramAkumaNoMiItem);
  
  boolean hasAnyDevilFruit();
  
  boolean hasDevilFruit(AkumaNoMiItem paramAkumaNoMiItem);
  
  void removeDevilFruit();
  
  boolean isLogia();
  
  boolean hasYamiPower();
  
  void setYamiPower(boolean paramBoolean);
  
  boolean hasAwakenedFruit();
  
  void setAwakenedFruit(boolean paramBoolean);
  
  Optional<MorphInfo> getCurrentMorph();
  
  boolean hasMorphInQueue(MorphInfo paramMorphInfo);
  
  void addMorph(MorphInfo paramMorphInfo);
  
  void removeMorph(MorphInfo paramMorphInfo);
  
  void removeMorph();
  
  void clearMorphs();
  
  @Deprecated
  String getZoanPoint();
  
  @Deprecated
  void setZoanPoint(String paramString);
  
  boolean hasStrongWaterWeakness();
  
  void setStrongWaterWeakness(boolean paramBoolean);
  
  boolean hasWeakWaterWeakness();
  
  void setWeakWaterWeakness(boolean paramBoolean);
  
  boolean hasKairosekiWeakness();
  
  void setKairosekiWeakness(boolean paramBoolean);
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\devilfruit\IDevilFruit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */