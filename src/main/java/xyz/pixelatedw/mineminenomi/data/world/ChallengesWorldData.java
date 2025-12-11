/*     */ package xyz.pixelatedw.mineminenomi.data.world;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.RegistryKey;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.registry.DynamicRegistries;
/*     */ import net.minecraft.util.registry.Registry;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.Dimension;
/*     */ import net.minecraft.world.DimensionType;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.ChunkGenerator;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraft.world.storage.WorldSavedData;
/*     */ import net.minecraftforge.fml.server.ServerLifecycleHooks;
/*     */ import xyz.pixelatedw.mineminenomi.ModMain;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCache;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.world.ChallengesChunkGenerator;
/*     */ import xyz.pixelatedw.mineminenomi.world.DynamicDimensionManager;
/*     */ 
/*     */ public class ChallengesWorldData extends WorldSavedData {
/*  44 */   private static final TranslationTextComponent NOT_UNLOCKED = new TranslationTextComponent(ModI18n.CHALLENGE_MESSAGE_NOT_UNLOCKED); private static final String IDENTIFIER = "mineminenomi-challenges";
/*  45 */   private static final ChallengesWorldData INSTANCE = initWorldData();
/*     */   
/*     */   private static ChallengesWorldData initWorldData() {
/*  48 */     if (ServerLifecycleHooks.getCurrentServer() != null) {
/*  49 */       return (ChallengesWorldData)ServerLifecycleHooks.getCurrentServer().func_241755_D_().func_217481_x().func_215752_a(ChallengesWorldData::new, "mineminenomi-challenges");
/*     */     }
/*  51 */     return null;
/*     */   }
/*     */   
/*  54 */   private Map<UUID, InProgressChallenge> inProgressChallenges = new HashMap<>();
/*  55 */   private Map<Integer, InProgressChallenge> inProgressChallengesHashCache = new HashMap<>();
/*  56 */   private Map<UUID, ChallengeCache> challengerCache = new HashMap<>();
/*     */   
/*     */   public ChallengesWorldData() {
/*  59 */     super("mineminenomi-challenges");
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static ChallengesWorldData get() {
/*  65 */     return INSTANCE;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT func_189551_b(CompoundNBT nbt) {
/*  70 */     ListNBT cache = new ListNBT();
/*  71 */     for (Map.Entry<UUID, ChallengeCache> entry : this.challengerCache.entrySet()) {
/*  72 */       CompoundNBT entryNbt = new CompoundNBT();
/*  73 */       entryNbt.func_186854_a("id", entry.getKey());
/*  74 */       entryNbt.func_218657_a("data", (INBT)((ChallengeCache)entry.getValue()).save());
/*  75 */       cache.add(entryNbt);
/*     */     } 
/*  77 */     nbt.func_218657_a("cache", (INBT)cache);
/*     */     
/*  79 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_76184_a(CompoundNBT nbt) {
/*  84 */     this.challengerCache.clear();
/*  85 */     ListNBT cache = nbt.func_150295_c("cache", 10);
/*  86 */     for (int i = 0; i < cache.size(); i++) {
/*  87 */       CompoundNBT entryNbt = cache.func_150305_b(i);
/*  88 */       UUID id = entryNbt.func_186857_a("id");
/*  89 */       ChallengeCache data = ChallengeCache.from(entryNbt.func_74775_l("data"));
/*  90 */       this.challengerCache.put(id, data);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean startChallenge(ServerPlayerEntity player, List<LivingEntity> group, ChallengeCore<?> core, boolean isFree) {
/*  95 */     if (!CommonConfig.INSTANCE.isChallengesEnabled()) {
/*  96 */       return false;
/*     */     }
/*     */     
/*  99 */     IChallengesData props = ChallengesDataCapability.get((PlayerEntity)player);
/* 100 */     if (props != null) {
/* 101 */       Challenge challenge = props.getChallenge(core);
/* 102 */       if (challenge == null) {
/* 103 */         player.func_145747_a((ITextComponent)NOT_UNLOCKED, Util.field_240973_b_);
/* 104 */         return false;
/*     */       } 
/*     */       
/* 107 */       ModMain.LOGGER.info(player.func_146103_bH().getName() + " started " + challenge.getCore().getId().toUpperCase() + " challenge.");
/*     */       
/* 109 */       long start = System.nanoTime();
/*     */       
/* 111 */       ResourceLocation dimName = new ResourceLocation("mineminenomi", "challenges_" + player.func_189512_bd());
/* 112 */       RegistryKey<World> dimension = RegistryKey.func_240903_a_(Registry.field_239699_ae_, dimName);
/* 113 */       DynamicRegistries registryAccess = player.field_70170_p.func_241828_r();
/* 114 */       Supplier<DimensionType> type = () -> (DimensionType)registryAccess.func_243612_b(Registry.field_239698_ad_).func_243576_d(RegistryKey.func_240903_a_(Registry.field_239698_ad_, ModResources.DIMENSION_TYPE_CHALLENGES));
/*     */       
/* 116 */       ServerWorld shard = DynamicDimensionManager.getOrCreateWorld(player.func_184102_h(), dimension, (minecraftServer, levelStemResourceKey) -> {
/*     */             ChallengesChunkGenerator challengesChunkGenerator = new ChallengesChunkGenerator((Registry)registryAccess.func_243612_b(Registry.field_239720_u_));
/*     */             
/*     */             return new Dimension(type, (ChunkGenerator)challengesChunkGenerator);
/*     */           });
/* 121 */       UUID id = player.func_110124_au();
/* 122 */       InProgressChallenge inProgressChallenge = new InProgressChallenge(id, player, shard, group, challenge, isFree);
/* 123 */       if (this.inProgressChallenges.containsKey(id)) {
/* 124 */         stopChallenge(inProgressChallenge);
/*     */       }
/* 126 */       this.inProgressChallenges.put(id, inProgressChallenge);
/*     */       
/* 128 */       for (Map.Entry<UUID, ChallengeCache> entry : (Iterable<Map.Entry<UUID, ChallengeCache>>)inProgressChallenge.getGroupCache().entrySet()) {
/* 129 */         this.challengerCache.put(entry.getKey(), entry.getValue());
/*     */       }
/*     */       
/* 132 */       func_76185_a();
/*     */       
/* 134 */       long end = System.nanoTime();
/* 135 */       long elapsed = (end - start) / 1000000L;
/* 136 */       ModMain.LOGGER.debug("Dimension setup: " + elapsed + "ms");
/* 137 */       return true;
/*     */     } 
/*     */     
/* 140 */     return false;
/*     */   }
/*     */   
/*     */   public void stopChallenge(InProgressChallenge inProgChallenge) {
/* 144 */     IChallengesData challengeProps = ChallengesDataCapability.get((PlayerEntity)inProgChallenge.getOwner());
/*     */     
/* 146 */     for (UnmodifiableIterator<UUID> unmodifiableIterator = challengeProps.getGroupMembersIds().iterator(); unmodifiableIterator.hasNext(); ) { UUID uuid = unmodifiableIterator.next();
/* 147 */       ServerPlayerEntity serverPlayerEntity = inProgChallenge.getShard().func_73046_m().func_184103_al().func_177451_a(uuid);
/* 148 */       if (serverPlayerEntity == null) {
/*     */         continue;
/*     */       }
/* 151 */       ChallengesDataCapability.get((PlayerEntity)serverPlayerEntity).setInGroup(null);
/* 152 */       challengeProps.removeGroupMember(serverPlayerEntity.func_110124_au()); }
/*     */ 
/*     */     
/* 155 */     challengeProps.setInGroup(null);
/* 156 */     challengeProps.removeGroupMember(inProgChallenge.getOwner().func_110124_au());
/*     */     
/* 158 */     ModMain.LOGGER.info(inProgChallenge.getOwner().func_146103_bH().getName() + "'s challenge stopped.");
/*     */ 
/*     */     
/* 161 */     for (UUID key : inProgChallenge.getGroupCache().keySet()) {
/* 162 */       this.challengerCache.remove(key);
/*     */     }
/*     */     
/* 165 */     this.inProgressChallenges.remove(inProgChallenge.getId());
/* 166 */     this.inProgressChallengesHashCache.remove(Integer.valueOf(inProgChallenge.getShard().hashCode()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 188 */     func_76185_a();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public InProgressChallenge getInProgressChallengeFor(LivingEntity entity) {
/* 193 */     UUID id = entity.func_110124_au();
/* 194 */     return this.inProgressChallenges.get(id);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public InProgressChallenge getInProgressChallengeFor(ServerWorld world) {
/* 199 */     if (this.inProgressChallengesHashCache.isEmpty() && this.inProgressChallenges.isEmpty()) {
/* 200 */       return null;
/*     */     }
/*     */     
/* 203 */     if (this.inProgressChallengesHashCache.containsKey(Integer.valueOf(world.hashCode()))) {
/* 204 */       return this.inProgressChallengesHashCache.get(Integer.valueOf(world.hashCode()));
/*     */     }
/*     */     
/* 207 */     for (InProgressChallenge challenge : this.inProgressChallenges.values()) {
/* 208 */       if (challenge.getShard().equals(world)) {
/* 209 */         this.inProgressChallengesHashCache.put(Integer.valueOf(world.hashCode()), challenge);
/* 210 */         return challenge;
/*     */       } 
/*     */     } 
/*     */     
/* 214 */     return null;
/*     */   }
/*     */   
/*     */   public Optional<ChallengeCache> getChallengerCache(UUID key) {
/* 218 */     return Optional.ofNullable(this.challengerCache.get(key));
/*     */   }
/*     */   
/*     */   public void removeChallengerCache(UUID key) {
/* 222 */     this.challengerCache.remove(key);
/* 223 */     func_76185_a();
/*     */   }
/*     */   
/*     */   public void tick(ServerWorld world) {
/* 227 */     InProgressChallenge challenge = getInProgressChallengeFor(world);
/* 228 */     if (challenge == null) {
/*     */       return;
/*     */     }
/*     */     
/* 232 */     if (challenge.canDelete()) {
/* 233 */       stopChallenge(challenge);
/*     */       
/*     */       return;
/*     */     } 
/* 237 */     challenge.tick();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\world\ChallengesWorldData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */