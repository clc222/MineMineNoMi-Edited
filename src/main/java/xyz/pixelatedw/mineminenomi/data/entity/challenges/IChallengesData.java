package xyz.pixelatedw.mineminenomi.data.entity.challenges;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.challenges.ArenaStyle;
import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeInvitation;

public interface IChallengesData {
  IChallengesData setOwner(PlayerEntity paramPlayerEntity);
  
  boolean isInGroup();
  
  void setInGroup(@Nullable UUID paramUUID);
  
  ImmutableList<UUID> getGroupMembersIds();
  
  boolean isGroupMember(UUID paramUUID);
  
  void addGroupMember(PlayerEntity paramPlayerEntity);
  
  void removeGroupMember(UUID paramUUID);
  
  ImmutableList<ChallengeInvitation> getInvitations();
  
  Optional<ChallengeInvitation> getInvitationFrom(UUID paramUUID);
  
  boolean hasInvitationFrom(UUID paramUUID);
  
  boolean hasInvitationFrom(PlayerEntity paramPlayerEntity);
  
  void addInvitation(ChallengeInvitation paramChallengeInvitation);
  
  void removeInvitationFrom(PlayerEntity paramPlayerEntity);
  
  void tickInvitations();
  
  ChallengeCore<?> getPreviousChallenge();
  
  ArenaStyle getPreviousArenaStyle();
  
  String getPreviousArenaClass();
  
  void setPreviousChallenge(ChallengeCore<?> paramChallengeCore, ArenaStyle paramArenaStyle, String paramString);
  
  boolean isArenaDirty();
  
  void markArenaDirty(boolean paramBoolean);
  
  boolean addChallenge(Challenge paramChallenge);
  
  boolean addChallenge(ChallengeCore<?> paramChallengeCore);
  
  boolean removeChallenge(ChallengeCore<?> paramChallengeCore);
  
  boolean hasChallenge(ChallengeCore<?> paramChallengeCore);
  
  boolean isChallengeCompleted(ChallengeCore<?> paramChallengeCore);
  
  <T extends Challenge> T getChallenge(ChallengeCore<?> paramChallengeCore);
  
  List<Challenge> getChallenges();
  
  void clearChallenges();
  
  int countChallenges();
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\challenges\IChallengesData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */