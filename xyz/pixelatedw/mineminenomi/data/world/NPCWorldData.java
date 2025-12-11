/*     */ package xyz.pixelatedw.mineminenomi.data.world;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraft.world.storage.WorldSavedData;
/*     */ import net.minecraftforge.fml.server.ServerLifecycleHooks;
/*     */ import xyz.pixelatedw.mineminenomi.api.poi.TrackedNPC;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class NPCWorldData extends WorldSavedData {
/*  24 */   private Random random = new Random(); private static final String IDENTIFIER = "mineminenomi-npcs";
/*  25 */   private Interval updateInterval = Interval.startAtMax(300);
/*  26 */   private Interval updateNewsInterval = Interval.startAtMax(24000);
/*     */   
/*  28 */   private Set<TrackedNPC> tracked = new LinkedHashSet<>();
/*  29 */   private long trackedId = 0L;
/*     */   
/*     */   public NPCWorldData() {
/*  32 */     super("mineminenomi-npcs");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static NPCWorldData get() {
/*  41 */     if (ServerLifecycleHooks.getCurrentServer() != null) {
/*  42 */       return (NPCWorldData)ServerLifecycleHooks.getCurrentServer().func_241755_D_().func_217481_x().func_215752_a(NPCWorldData::new, "mineminenomi-npcs");
/*     */     }
/*  44 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundNBT func_189551_b(CompoundNBT nbt) {
/*  50 */     nbt.func_74772_a("globalTrackedId", this.trackedId);
/*     */     
/*  52 */     ListNBT trackedNpcs = new ListNBT();
/*  53 */     for (TrackedNPC npc : this.tracked) {
/*  54 */       trackedNpcs.add(npc.save());
/*     */     }
/*  56 */     nbt.func_218657_a("trackedNPCs", (INBT)trackedNpcs);
/*     */     
/*  58 */     return nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_76184_a(CompoundNBT nbt) {
/*  64 */     this.trackedId = nbt.func_74763_f("globalTrackedId");
/*     */     
/*  66 */     ListNBT trackedNpcs = nbt.func_150295_c("trackedNPCs", 10);
/*  67 */     for (int i = 0; i < trackedNpcs.size(); i++) {
/*  68 */       CompoundNBT entryNBT = trackedNpcs.func_150305_b(i);
/*  69 */       if (!entryNBT.isEmpty()) {
/*  70 */         TrackedNPC npc = TrackedNPC.from(entryNBT);
/*  71 */         this.tracked.add(npc);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setup(ServerWorld world) {
/*  77 */     int alivePirates = (int)this.tracked.stream().filter(t -> t.isPirate()).count();
/*  78 */     int aliveMarines = (int)this.tracked.stream().filter(t -> t.isMarine()).count();
/*     */     
/*  80 */     if (alivePirates <= 0) {
/*  81 */       int pirates = Math.abs(5 + world.func_201674_k().nextInt(10) - alivePirates);
/*  82 */       while (pirates > 0) {
/*     */         
/*  84 */         addRandomTrackedMob(world, ModValues.PIRATE);
/*     */         
/*  86 */         pirates--;
/*     */       } 
/*     */     } 
/*     */     
/*  90 */     if (aliveMarines <= 0) {
/*  91 */       int marines = Math.abs(5 + world.func_201674_k().nextInt(15) - aliveMarines);
/*  92 */       while (marines > 0) {
/*     */         
/*  94 */         addRandomTrackedMob(world, ModValues.MARINE);
/*     */         
/*  96 */         marines--;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void tick(ServerWorld world) {
/* 102 */     int updates = 0;
/* 103 */     if (this.updateInterval.canTick()) {
/* 104 */       for (TrackedNPC tracked : this.tracked) {
/* 105 */         tracked.recalculateDifficulty(world);
/* 106 */         updates++;
/*     */       } 
/*     */     }
/*     */     
/* 110 */     if (this.updateNewsInterval.canTick()) {
/* 111 */       for (TrackedNPC tracked : this.tracked) {
/* 112 */         tracked.updateNewsEntry((World)world);
/* 113 */         updates++;
/*     */       } 
/*     */     }
/*     */     
/* 117 */     if (updates > 0) {
/* 118 */       func_76185_a();
/*     */     }
/*     */   }
/*     */   
/*     */   public Set<TrackedNPC> getTrackedMobs() {
/* 123 */     return new LinkedHashSet<>(this.tracked);
/*     */   }
/*     */   
/*     */   public void updateTrackedMob(ServerWorld world, TrackedNPC npc) {
/* 127 */     npc.recalculateDifficulty(world);
/* 128 */     func_76185_a();
/*     */   }
/*     */   
/*     */   private void addRandomTrackedMob(ServerWorld world, ResourceLocation faction) {
/* 132 */     long seed = world.func_201674_k().nextLong();
/* 133 */     addTrackedMob(world, faction, seed);
/*     */   }
/*     */   
/*     */   private void addTrackedMob(ServerWorld world, ResourceLocation faction, long seed) {
/* 137 */     TrackedNPC tracked = new TrackedNPC(world, this.trackedId++, faction, seed);
/* 138 */     this.tracked.add(tracked);
/* 139 */     func_76185_a();
/*     */   }
/*     */   
/*     */   public void removeTrackedMob(ServerWorld world, TrackedNPC tracked) {
/* 143 */     if (tracked == null) {
/*     */       return;
/*     */     }
/*     */     
/* 147 */     this.tracked.remove(tracked);
/*     */     
/* 149 */     long factionLeft = this.tracked.stream().filter(npc -> npc.getFaction().equals(tracked.getFaction())).count();
/* 150 */     if (factionLeft < 5L) {
/* 151 */       addRandomTrackedMob(world, tracked.getFaction());
/*     */     }
/*     */     
/* 154 */     func_76185_a();
/*     */   }
/*     */   
/*     */   public Optional<TrackedNPC> getTrackedMob(long id) {
/* 158 */     return this.tracked.stream().filter(t -> (t.getId() == id)).findFirst();
/*     */   }
/*     */   
/*     */   public Optional<TrackedNPC> getTrackedMob(UUID id) {
/* 162 */     return this.tracked.stream().filter(t -> t.getUUID().equals(id)).findFirst();
/*     */   }
/*     */   
/*     */   public Optional<TrackedNPC> getTrackedMobBySeed(long seed) {
/* 166 */     return this.tracked.stream().filter(t -> (t.getSeed() == seed)).findFirst();
/*     */   }
/*     */   
/*     */   public Optional<TrackedNPC> getRandomTrackedMob(ResourceLocation faction) {
/* 170 */     return Optional.ofNullable(((List<TrackedNPC>)this.tracked.stream().filter(npc -> npc.getFaction().equals(faction)).collect(WyHelper.toShuffledList())).get(0));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\world\NPCWorldData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */