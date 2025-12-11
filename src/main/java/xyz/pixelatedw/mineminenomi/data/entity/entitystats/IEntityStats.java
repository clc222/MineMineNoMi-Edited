package xyz.pixelatedw.mineminenomi.data.entity.entitystats;

import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.api.ClientBossExtraInfo;
import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;

public interface IEntityStats {
  IEntityStats setOwner(LivingEntity paramLivingEntity);
  
  boolean isInCombatMode();
  
  void setCombatMode(boolean paramBoolean);
  
  double getDoriki();
  
  boolean alterDoriki(double paramDouble, StatChangeSource paramStatChangeSource);
  
  void setDoriki(double paramDouble);
  
  long getBelly();
  
  boolean alterBelly(long paramLong, StatChangeSource paramStatChangeSource);
  
  void setBelly(long paramLong);
  
  long getExtol();
  
  boolean alterExtol(long paramLong, StatChangeSource paramStatChangeSource);
  
  void setExtol(long paramLong);
  
  long getBounty();
  
  boolean alterBounty(long paramLong, StatChangeSource paramStatChangeSource);
  
  void setBounty(long paramLong);
  
  int getCola();
  
  void alterCola(int paramInt);
  
  void setCola(int paramInt);
  
  void setForcedCola(int paramInt);
  
  void updateCola();
  
  int getMaxCola();
  
  int getUltraCola();
  
  void setUltraCola(int paramInt);
  
  void addUltraCola(int paramInt);
  
  double getLoyalty();
  
  boolean alterLoyalty(double paramDouble, StatChangeSource paramStatChangeSource);
  
  void setLoyalty(double paramDouble);
  
  int getInvulnerableTime();
  
  void alterInvulnerableTime(int paramInt);
  
  void setInvulnerableTime(int paramInt);
  
  FactionHelper.MarineRank getMarineRank();
  
  boolean hasMarineRank(FactionHelper.MarineRank paramMarineRank);
  
  FactionHelper.RevolutionaryRank getRevolutionaryRank();
  
  boolean hasRevolutionaryRank(FactionHelper.RevolutionaryRank paramRevolutionaryRank);
  
  boolean isPirate();
  
  boolean isMarine();
  
  boolean isBountyHunter();
  
  boolean isRevolutionary();
  
  boolean isBandit();
  
  boolean isCivilian();
  
  boolean hasFaction();
  
  void setFaction(String paramString);
  
  void setFaction(ResourceLocation paramResourceLocation);
  
  ResourceLocation getFaction();
  
  boolean isHuman();
  
  boolean isFishman();
  
  boolean isCyborg();
  
  boolean isMink();
  
  boolean hasRace();
  
  void setRace(String paramString);
  
  void setRace(ResourceLocation paramResourceLocation);
  
  ResourceLocation getRace();
  
  boolean isBunnyMink();
  
  boolean isDogMink();
  
  boolean isLionMink();
  
  void setSubRace(String paramString);
  
  String getSubRace();
  
  boolean isSwordsman();
  
  boolean isSniper();
  
  boolean isDoctor();
  
  boolean isWeatherWizard();
  
  boolean isBlackLeg();
  
  boolean isBrawler();
  
  boolean hasFightingStyle();
  
  void setFightingStyle(String paramString);
  
  void setFightingStyle(ResourceLocation paramResourceLocation);
  
  ResourceLocation getFightingStyle();
  
  boolean hasShadow();
  
  void setShadow(boolean paramBoolean);
  
  boolean hadChiyuEffect();
  
  void setChiyuEffect(boolean paramBoolean);
  
  boolean hasHeart();
  
  void setHeart(boolean paramBoolean);
  
  double getDamageMultiplier();
  
  void setDamageMultiplier(double paramDouble);
  
  boolean hasStrawDoll();
  
  void setStrawDoll(boolean paramBoolean);
  
  boolean isRogue();
  
  void setRogue(boolean paramBoolean);
  
  Map<UUID, ClientBossExtraInfo> getExtraBossInfo();
  
  void addExtraBossInfo(UUID paramUUID, ClientBossExtraInfo paramClientBossExtraInfo);
  
  void setInCombatCache(@Nullable LivingEntity paramLivingEntity);
  
  boolean isInCombatCache();
  
  @Nullable
  LivingEntity getLastAttacker();
  
  long getLastAttackTime();
  
  long getLastAttackTimestamp();
  
  void tickLastAttack();
  
  void startCarrying(@Nullable LivingEntity paramLivingEntity);
  
  void stopCarrying();
  
  @Nullable
  LivingEntity getCarry();
  
  boolean isCarrying();
  
  void setCarrier(@Nullable LivingEntity paramLivingEntity);
  
  @Nullable
  LivingEntity getCarrier();
  
  boolean isCarried();
  
  void setLeashedTo(LivingEntity paramLivingEntity);
  
  void dropLeash();
  
  @Nullable
  LivingEntity getLeashHolder();
  
  boolean isLeashed();
  
  boolean canBeLeashed(LivingEntity paramLivingEntity);
  
  void setStoredDamage(float paramFloat);
  
  float getStoredDamage();
  
  float getLeftImpulse();
  
  void setLeftImpulse(float paramFloat);
  
  float getForwardImpulse();
  
  void setForwardImpulse(float paramFloat);
  
  boolean isJumping();
  
  void setJumping(boolean paramBoolean);
  
  int getJumpTicks();
  
  void setJumpTicks(int paramInt);
  
  void alterJumpTicks(int paramInt);
  
  void addScreenShader(ResourceLocation paramResourceLocation);
  
  boolean hasScreenShaderActive(ResourceLocation paramResourceLocation);
  
  void removeScreenShader(ResourceLocation paramResourceLocation);
  
  void updateScreenShader();
  
  float getOriginalChiyupopoHealth();
  
  void setOriginalChiyupopoHealth(float paramFloat);
  
  int getFreedSlaves();
  
  void addFreedSlaves(int paramInt);
  
  void setFreedSlaves(int paramInt);
  
  void pinCamera(PlayerEntity paramPlayerEntity);
  
  void clampCameraYaw(PlayerEntity paramPlayerEntity, float paramFloat1, float paramFloat2);
  
  void clampCameraPitch(PlayerEntity paramPlayerEntity, float paramFloat1, float paramFloat2);
  
  void unpinCamera();
  
  boolean hasCameraPinned();
  
  float[] getCameraRotations();
  
  float getCameraInitialYaw();
  
  float getCameraMaxYaw();
  
  boolean hasCameraYawClamped();
  
  float getCameraInitialPitch();
  
  float getCameraMaxPitch();
  
  boolean hasCameraPitchClamped();
  
  void setCameraPinTimer(int paramInt);
  
  void tickCameraPin();
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\entitystats\IEntityStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */