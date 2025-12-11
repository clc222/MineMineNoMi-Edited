/*     */ package xyz.pixelatedw.mineminenomi.data.entity.challenges;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.ModMain;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaStyle;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeInvitation;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ChallengesWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAdvancements;
/*     */ 
/*     */ public class ChallengesDataBase
/*     */   implements IChallengesData
/*     */ {
/*     */   private PlayerEntity owner;
/*     */   private boolean isArenaDirty;
/*     */   private ChallengeCore<?> previousChallenge;
/*     */   private ArenaStyle previousArenaStyle;
/*     */   private String previousArenaClass;
/*     */   private UUID groupOwner;
/*  32 */   private List<UUID> group = new ArrayList<>();
/*  33 */   private List<Challenge> challenges = new ArrayList<>();
/*     */   
/*  35 */   private List<ChallengeInvitation> invitations = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public IChallengesData setOwner(PlayerEntity entity) {
/*  39 */     this.owner = entity;
/*  40 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInGroup() {
/*  45 */     return (this.groupOwner != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInGroup(@Nullable UUID groupOwnerId) {
/*  50 */     this.groupOwner = groupOwnerId;
/*     */   }
/*     */ 
/*     */   
/*     */   public ImmutableList<UUID> getGroupMembersIds() {
/*  55 */     return ImmutableList.copyOf(this.group);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isGroupMember(UUID id) {
/*  60 */     return this.group.contains(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeGroupMember(UUID playerId) {
/*  65 */     this.group.remove(playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addGroupMember(PlayerEntity player) {
/*  70 */     if (player == null) {
/*     */       return;
/*     */     }
/*  73 */     ChallengesDataCapability.get(player).setInGroup(this.owner.func_110124_au());
/*  74 */     this.group.add(player.func_110124_au());
/*     */   }
/*     */ 
/*     */   
/*     */   public ImmutableList<ChallengeInvitation> getInvitations() {
/*  79 */     return ImmutableList.copyOf(this.invitations);
/*     */   }
/*     */ 
/*     */   
/*     */   public Optional<ChallengeInvitation> getInvitationFrom(UUID senderId) {
/*  84 */     for (ChallengeInvitation invite : this.invitations) {
/*  85 */       if (invite.getSenderId().equals(senderId)) {
/*  86 */         return Optional.ofNullable(invite);
/*     */       }
/*     */     } 
/*  89 */     return Optional.empty();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasInvitationFrom(UUID senderId) {
/*  94 */     for (ChallengeInvitation invite : this.invitations) {
/*  95 */       if (invite.getSenderId().equals(senderId)) {
/*  96 */         return true;
/*     */       }
/*     */     } 
/*  99 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasInvitationFrom(PlayerEntity sender) {
/* 104 */     return hasInvitationFrom(sender.func_110124_au());
/*     */   }
/*     */ 
/*     */   
/*     */   public void addInvitation(ChallengeInvitation challenge) {
/* 109 */     if (hasInvitationFrom(challenge.getSenderId())) {
/*     */       return;
/*     */     }
/* 112 */     this.invitations.add(challenge);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeInvitationFrom(PlayerEntity player) {
/* 117 */     Iterator<ChallengeInvitation> iter = this.invitations.iterator();
/* 118 */     while (iter.hasNext()) {
/* 119 */       ChallengeInvitation invite = iter.next();
/*     */       
/* 121 */       if (invite.getSenderId().equals(player.func_110124_au())) {
/* 122 */         iter.remove();
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void tickInvitations() {
/* 130 */     if (this.invitations.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/* 134 */     World world = this.owner.func_130014_f_();
/* 135 */     long currentTime = world.func_82737_E();
/* 136 */     ChallengesWorldData worldData = ChallengesWorldData.get();
/*     */     
/* 138 */     Iterator<ChallengeInvitation> iter = this.invitations.iterator();
/* 139 */     while (iter.hasNext()) {
/* 140 */       ChallengeInvitation invite = iter.next();
/*     */       
/* 142 */       PlayerEntity sender = invite.getSender(world);
/*     */ 
/*     */       
/* 145 */       if (!world.func_217369_A().contains(sender)) {
/* 146 */         ModMain.LOGGER.debug("Removed invitation due to missing sender");
/* 147 */         iter.remove();
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 152 */       if (invite.isExpired(world)) {
/* 153 */         ModMain.LOGGER.debug("Removed invitation due to timeout");
/* 154 */         iter.remove();
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 159 */       if (worldData.getInProgressChallengeFor((LivingEntity)sender) != null) {
/* 160 */         ModMain.LOGGER.debug("Removed invitation due to it already starting");
/* 161 */         iter.remove();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ChallengeCore<?> getPreviousChallenge() {
/* 169 */     return this.previousChallenge;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArenaStyle getPreviousArenaStyle() {
/* 174 */     return this.previousArenaStyle;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPreviousArenaClass() {
/* 179 */     return this.previousArenaClass;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPreviousChallenge(ChallengeCore<?> challenge, ArenaStyle style, String clz) {
/* 184 */     this.previousChallenge = challenge;
/* 185 */     this.previousArenaStyle = style;
/* 186 */     this.previousArenaClass = clz;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isArenaDirty() {
/* 191 */     return this.isArenaDirty;
/*     */   }
/*     */ 
/*     */   
/*     */   public void markArenaDirty(boolean flag) {
/* 196 */     this.isArenaDirty = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addChallenge(Challenge challenge) {
/* 201 */     if (hasChallenge(challenge.getCore())) {
/* 202 */       return false;
/*     */     }
/*     */     
/* 205 */     this.challenges.add(challenge);
/*     */     
/* 207 */     if (this.owner instanceof ServerPlayerEntity) {
/* 208 */       ModAdvancements.UNLOCK_CHALLENGE.trigger((ServerPlayerEntity)this.owner, challenge.getCore());
/*     */     }
/*     */     
/* 211 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addChallenge(ChallengeCore<?> core) {
/* 216 */     if (hasChallenge(core)) {
/* 217 */       return false;
/*     */     }
/*     */     
/* 220 */     Challenge challenge = core.createChallenge();
/* 221 */     return addChallenge(challenge);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeChallenge(ChallengeCore<?> core) {
/* 226 */     if (!hasChallenge(core)) {
/* 227 */       return false;
/*     */     }
/*     */     
/* 230 */     this.challenges.removeIf(ch -> ch.getCore().equals(core));
/* 231 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasChallenge(ChallengeCore<?> core) {
/* 236 */     return (getChallenge(core) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isChallengeCompleted(ChallengeCore<?> core) {
/* 241 */     Challenge ch = getChallenge(core);
/* 242 */     if (ch == null) {
/* 243 */       return false;
/*     */     }
/* 245 */     return ch.isComplete();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public <T extends Challenge> T getChallenge(ChallengeCore<?> core) {
/* 251 */     return (T)this.challenges.stream().filter(ch -> ch.getCore().equals(core)).findFirst().orElse(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Challenge> getChallenges() {
/* 256 */     return (List<Challenge>)this.challenges.stream().filter(ch -> (ch != null)).collect(Collectors.toList());
/*     */   }
/*     */ 
/*     */   
/*     */   public int countChallenges() {
/* 261 */     this.challenges.removeIf(chl -> (chl == null));
/* 262 */     return this.challenges.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearChallenges() {
/* 267 */     this.challenges.clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\challenges\ChallengesDataBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */