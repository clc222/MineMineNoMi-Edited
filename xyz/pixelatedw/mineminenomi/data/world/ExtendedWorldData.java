/*     */ package xyz.pixelatedw.mineminenomi.data.world;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraft.world.storage.WorldSavedData;
/*     */ import net.minecraftforge.fml.server.ServerLifecycleHooks;
/*     */ import xyz.pixelatedw.mineminenomi.ModMain;
/*     */ import xyz.pixelatedw.mineminenomi.api.SoulboundMark;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.JollyRoger;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.ProtectedArea;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ExtendedWorldData
/*     */   extends WorldSavedData {
/*     */   private static final String IDENTIFIER = "mineminenomi";
/*  31 */   private HashMap<UUID, Long> issuedBounties = new HashMap<>();
/*  32 */   private HashMap<String, Crew> pirateCrews = new HashMap<>();
/*  33 */   private List<String> generatedArenas = new ArrayList<>();
/*  34 */   private HashMap<UUID, SoulboundMark> linkedItemStatus = new HashMap<>();
/*     */   
/*     */   @Deprecated
/*  37 */   private static final ExtendedWorldData CLIENT_DATA = new ExtendedWorldData();
/*     */   
/*     */   public ExtendedWorldData() {
/*  40 */     super("mineminenomi");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static ExtendedWorldData get() {
/*  49 */     if (ServerLifecycleHooks.getCurrentServer() != null) {
/*  50 */       return (ExtendedWorldData)ServerLifecycleHooks.getCurrentServer().func_241755_D_().func_217481_x().func_215752_a(ExtendedWorldData::new, "mineminenomi");
/*     */     }
/*  52 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static ExtendedWorldData get(IWorld world) {
/*  64 */     if (world == null) {
/*  65 */       return null;
/*     */     }
/*     */     
/*  68 */     ExtendedWorldData worldExt = null;
/*     */     
/*  70 */     if (world instanceof ServerWorld) {
/*     */       
/*  72 */       ServerWorld serverWorld = ((ServerWorld)world).func_73046_m().func_71218_a(World.field_234918_g_);
/*  73 */       worldExt = (ExtendedWorldData)serverWorld.func_217481_x().func_215752_a(ExtendedWorldData::new, "mineminenomi");
/*     */     } else {
/*     */       
/*  76 */       worldExt = CLIENT_DATA;
/*     */     } 
/*     */     
/*  79 */     return worldExt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_76184_a(CompoundNBT nbt) {
/*  85 */     CompoundNBT bounties = nbt.func_74775_l("issuedBounties");
/*  86 */     this.issuedBounties.clear();
/*  87 */     bounties.func_150296_c().stream().forEach(x -> this.issuedBounties.put(UUID.fromString(x), Long.valueOf(bounties.func_74763_f(x))));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  92 */     if (ServerLifecycleHooks.getCurrentServer() != null) {
/*     */       
/*  94 */       ListNBT protectedAreas = nbt.func_150295_c("protectedAreas", 10);
/*  95 */       ProtectedAreasData protectedAreaData = ProtectedAreasData.get((World)ServerLifecycleHooks.getCurrentServer().func_241755_D_());
/*  96 */       for (int j = 0; j < protectedAreas.size(); j++) {
/*  97 */         CompoundNBT areaNBT = protectedAreas.func_150305_b(j);
/*  98 */         ProtectedArea area = ProtectedArea.from(areaNBT);
/*  99 */         BlockPos pos = area.getCenter();
/*     */         
/* 101 */         if (protectedAreaData.getProtectedArea(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p()) == null) {
/* 102 */           ModMain.LOGGER.info(ModMain.MARKER, String.format("Ported a protection area with its center at %s %s %s", new Object[] { Integer.valueOf(pos.func_177958_n()), Integer.valueOf(pos.func_177956_o()), Integer.valueOf(pos.func_177952_p()) }));
/* 103 */           protectedAreaData.addRestrictedArea(pos, area.getSize(), area.getLabel());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 108 */     ListNBT crews = nbt.func_150295_c("crews", 10);
/* 109 */     this.pirateCrews.clear();
/* 110 */     for (int i = 0; i < crews.size(); i++) {
/*     */       
/* 112 */       CompoundNBT crewNBT = crews.func_150305_b(i);
/* 113 */       Crew crew = Crew.from(crewNBT);
/* 114 */       this.pirateCrews.put(WyHelper.getResourceName(crew.getName()), crew);
/*     */     } 
/*     */     
/* 117 */     CompoundNBT generatedArenas = nbt.func_74775_l("generatedArenas");
/* 118 */     this.generatedArenas.clear();
/* 119 */     generatedArenas.func_150296_c().stream().forEach(key -> this.generatedArenas.add(key));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 124 */     CompoundNBT linkedItemMarksNBT = nbt.func_74775_l("linkedItemMarks");
/* 125 */     this.linkedItemStatus.clear();
/* 126 */     linkedItemMarksNBT.func_150296_c().stream().forEach(key -> {
/*     */           SoulboundMark markType = SoulboundMark.values()[linkedItemMarksNBT.func_74762_e(key)];
/*     */           this.linkedItemStatus.put(UUID.fromString(key), markType);
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundNBT func_189551_b(CompoundNBT nbt) {
/* 135 */     CompoundNBT bounties = new CompoundNBT();
/* 136 */     if (this.issuedBounties.size() > 0) {
/* 137 */       this.issuedBounties.entrySet().stream().forEach(x -> bounties.func_74772_a(((UUID)x.getKey()).toString(), ((Long)x.getValue()).longValue()));
/*     */     }
/*     */ 
/*     */     
/* 141 */     nbt.func_218657_a("issuedBounties", (INBT)bounties);
/*     */     
/* 143 */     ListNBT crews = new ListNBT();
/* 144 */     for (Crew crew : this.pirateCrews.values())
/*     */     {
/* 146 */       crews.add(crew.write());
/*     */     }
/* 148 */     nbt.func_218657_a("crews", (INBT)crews);
/*     */     
/* 150 */     CompoundNBT generatedArenas = new CompoundNBT();
/* 151 */     if (this.generatedArenas.size() > 0)
/*     */     {
/* 153 */       for (String key : this.generatedArenas)
/*     */       {
/* 155 */         generatedArenas.func_74757_a(key, true);
/*     */       }
/*     */     }
/* 158 */     nbt.func_218657_a("generatedArenas", (INBT)generatedArenas);
/*     */     
/* 160 */     CompoundNBT linkedItemMarksNBT = new CompoundNBT();
/* 161 */     if (this.linkedItemStatus.size() > 0) {
/* 162 */       for (Map.Entry<UUID, SoulboundMark> entry : this.linkedItemStatus.entrySet()) {
/* 163 */         linkedItemMarksNBT.func_74768_a(((UUID)entry.getKey()).toString(), ((SoulboundMark)entry.getValue()).ordinal());
/*     */       }
/*     */     }
/* 166 */     nbt.func_218657_a("linkedItemMarks", (INBT)linkedItemMarksNBT);
/*     */     
/* 168 */     return nbt;
/*     */   }
/*     */   
/*     */   public void markDead(UUID id) {
/* 172 */     this.linkedItemStatus.put(id, SoulboundMark.DEATH);
/* 173 */     func_76185_a();
/*     */   }
/*     */   
/*     */   public void markRestore(UUID id, SoulboundMark mark) {
/* 177 */     this.linkedItemStatus.put(id, mark);
/* 178 */     func_76185_a();
/*     */   }
/*     */   
/*     */   public void removeMark(UUID id) {
/* 182 */     this.linkedItemStatus.remove(id);
/* 183 */     func_76185_a();
/*     */   }
/*     */   
/*     */   public boolean isOnDeathList(UUID id) {
/* 187 */     return (this.linkedItemStatus.get(id) == SoulboundMark.DEATH);
/*     */   }
/*     */   
/*     */   public boolean isOnRestoreList(UUID id, SoulboundMark mark) {
/* 191 */     return (this.linkedItemStatus.get(id) == mark);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isArenaGenerated(String key) {
/* 196 */     return this.generatedArenas.contains(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getGeneratedArenas() {
/* 201 */     return this.generatedArenas;
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeGeneratedArena(String key) {
/* 206 */     this.generatedArenas.remove(key);
/* 207 */     func_76185_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addGeneratedArena(String key) {
/* 212 */     this.generatedArenas.add(key);
/* 213 */     func_76185_a();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Crew> getCrews() {
/* 219 */     return new ArrayList<>(this.pirateCrews.values());
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Crew getCrewWithMember(UUID memId) {
/* 225 */     for (Crew crew : this.pirateCrews.values()) {
/*     */       
/* 227 */       for (Crew.Member member : crew.getMembers()) {
/*     */         
/* 229 */         if (member.getUUID().equals(memId)) {
/* 230 */           return crew;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 235 */     return null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Crew getCrewByName(String name) {
/* 240 */     for (Crew crew : this.pirateCrews.values()) {
/* 241 */       if (crew.getName().equals(name)) {
/* 242 */         return crew;
/*     */       }
/*     */     } 
/*     */     
/* 246 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Crew getCrewWithCaptain(UUID capId) {
/* 252 */     return this.pirateCrews.values().stream().filter(crew -> (crew.getCaptain() != null && crew.getCaptain().getUUID().equals(capId))).findFirst().orElse(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeCrew(Crew crew) {
/* 257 */     String key = WyHelper.getResourceName(crew.getName());
/* 258 */     if (this.pirateCrews.containsKey(key)) {
/* 259 */       this.pirateCrews.remove(key);
/*     */     }
/* 261 */     func_76185_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addCrew(Crew crew) {
/* 266 */     String key = WyHelper.getResourceName(crew.getName());
/* 267 */     if (!this.pirateCrews.containsKey(key)) {
/* 268 */       this.pirateCrews.put(key, crew);
/*     */     }
/* 270 */     func_76185_a();
/*     */   }
/*     */   
/*     */   public void removeCrewMember(World level, Crew crew, UUID uuid) {
/* 274 */     crew.removeMember(level, uuid);
/* 275 */     func_76185_a();
/*     */   }
/*     */   
/*     */   public void addCrewMember(Crew crew, LivingEntity entity) {
/* 279 */     addCrewMember(crew, entity, true);
/*     */   }
/*     */   
/*     */   public void addTemporaryCrewMember(Crew crew, LivingEntity entity) {
/* 283 */     addCrewMember(crew, entity, false);
/*     */   }
/*     */   
/*     */   public void addCrewMember(Crew crew, LivingEntity entity, boolean saveMember) {
/* 287 */     String key = WyHelper.getResourceName(crew.getName());
/*     */ 
/*     */     
/* 290 */     if (!this.pirateCrews.containsKey(key)) {
/* 291 */       this.pirateCrews.put(key, crew);
/*     */     } else {
/*     */       
/* 294 */       crew = this.pirateCrews.get(key);
/*     */     } 
/* 296 */     crew.addMember(entity, saveMember);
/* 297 */     func_76185_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateCrewJollyRoger(Crew crew, JollyRoger jollyRoger) {
/* 302 */     crew.setJollyRoger(jollyRoger);
/* 303 */     func_76185_a();
/*     */   }
/*     */   
/*     */   public HashMap<UUID, Long> getAllBounties() {
/* 307 */     return this.issuedBounties;
/*     */   }
/*     */   
/*     */   public Object[] getRandomBounty() {
/* 311 */     int count = getAllBounties().size();
/*     */     
/* 313 */     if (count <= 0) {
/* 314 */       return null;
/*     */     }
/*     */     
/* 317 */     Object[] keys = getAllBounties().keySet().toArray();
/* 318 */     Object key = keys[(new Random()).nextInt(count)];
/*     */     
/* 320 */     long bounty = ((Long)getAllBounties().get(key)).longValue();
/*     */     
/* 322 */     return new Object[] { key, Long.valueOf(bounty) };
/*     */   }
/*     */   
/*     */   public long getBounty(UUID uuid) {
/* 326 */     if (this.issuedBounties.containsKey(uuid)) {
/* 327 */       return ((Long)this.issuedBounties.get(uuid)).longValue();
/*     */     }
/*     */     
/* 330 */     return 0L;
/*     */   }
/*     */   
/*     */   public boolean hasIssuedBounty(LivingEntity entity) {
/* 334 */     return this.issuedBounties.containsKey(entity.func_110124_au());
/*     */   }
/*     */   
/*     */   public void removeBounty(UUID uuid) {
/* 338 */     if (this.issuedBounties.containsKey(uuid)) {
/* 339 */       this.issuedBounties.remove(uuid);
/*     */     }
/*     */   }
/*     */   
/*     */   public void issueBounty(UUID uuid, long bounty) {
/* 344 */     if (this.issuedBounties.containsKey(uuid)) {
/* 345 */       this.issuedBounties.remove(uuid);
/* 346 */       this.issuedBounties.put(uuid, Long.valueOf(bounty));
/*     */     } else {
/*     */       
/* 349 */       this.issuedBounties.put(uuid, Long.valueOf(bounty));
/*     */     } 
/*     */     
/* 352 */     func_76185_a();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\world\ExtendedWorldData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */