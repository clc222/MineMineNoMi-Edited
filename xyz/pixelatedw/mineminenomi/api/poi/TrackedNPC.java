/*     */ package xyz.pixelatedw.mineminenomi.api.poi;
/*     */ 
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.Heightmap;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import xyz.pixelatedw.mineminenomi.api.charactercreator.RaceId;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.NotoriousEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRaces;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class TrackedNPC {
/*     */   private static final double MIN_DORIKI = 1000.0D;
/*     */   private static final float MIN_OBS_HAKI = 10.0F;
/*  35 */   private static final ResourceLocation[] STYLES = new ResourceLocation[] { ModValues.SWORDSMAN, ModValues.BRAWLER, ModValues.BLACK_LEG, ModValues.SNIPER }; private static final float MIN_BUSO_HAKI = 10.0F; private static final long MIN_BOUNTY = 100000L; private static final float MAX_TIME = 1.5552E8F;
/*  36 */   private static final ResourceLocation[] RACES = new ResourceLocation[] { ModValues.HUMAN, ModValues.FISHMAN, ModValues.MINK };
/*     */   
/*     */   private UUID uuid;
/*     */   private Random random;
/*     */   private long seed;
/*     */   private long id;
/*  42 */   private Vector3d pos = Vector3d.field_186680_a;
/*     */   private long lastGameUpdate;
/*     */   private long lastNewsUpdate;
/*  45 */   private ResourceLocation faction = ModValues.EMPTY;
/*  46 */   private ResourceLocation race = ModValues.EMPTY;
/*  47 */   private ResourceLocation style = ModValues.EMPTY;
/*     */   private double doriki;
/*     */   private long bounty;
/*     */   private float kenbunshokuExp;
/*     */   private float busoshokuExp;
/*     */   private NewsEntry newsEntry;
/*     */   
/*     */   private TrackedNPC() {}
/*     */   
/*     */   public TrackedNPC(ServerWorld world, long id, ResourceLocation faction, long seed) {
/*  57 */     setSeed(seed);
/*     */     
/*  59 */     this.id = id;
/*  60 */     this.faction = faction;
/*  61 */     this.style = STYLES[this.random.nextInt(STYLES.length)];
/*  62 */     this.race = RACES[this.random.nextInt(RACES.length)];
/*     */     
/*  64 */     this.lastGameUpdate = world.func_82737_E();
/*     */     
/*  66 */     this.doriki = 1000.0D;
/*  67 */     this.kenbunshokuExp = 10.0F;
/*  68 */     this.busoshokuExp = 10.0F;
/*  69 */     if (this.faction.equals(ModValues.PIRATE)) {
/*  70 */       this.bounty = 100000L;
/*     */     }
/*     */   }
/*     */   
/*     */   public static TrackedNPC from(CompoundNBT tag) {
/*  75 */     TrackedNPC npc = new TrackedNPC();
/*     */     
/*  77 */     npc.setSeed(tag.func_74763_f("seed"));
/*  78 */     npc.id = tag.func_74763_f("id");
/*  79 */     npc.uuid = tag.func_186857_a("uuid");
/*  80 */     npc.lastGameUpdate = tag.func_74763_f("lastGameUpdate");
/*  81 */     npc.lastNewsUpdate = tag.func_74763_f("lastNewsUpdate");
/*  82 */     npc.faction = WyHelper.getResourceLocation(tag, "faction");
/*  83 */     if (npc.faction.equals(ModValues.PIRATE)) {
/*  84 */       npc.bounty = tag.func_74763_f("bounty");
/*     */     }
/*  86 */     npc.race = WyHelper.getResourceLocation(tag, "race");
/*  87 */     npc.style = WyHelper.getResourceLocation(tag, "style");
/*  88 */     npc.doriki = tag.func_74769_h("doriki");
/*  89 */     npc.kenbunshokuExp = tag.func_74760_g("kenbunshokuExp");
/*  90 */     npc.busoshokuExp = tag.func_74760_g("busoshokuExp");
/*     */     
/*  92 */     return npc;
/*     */   }
/*     */   
/*     */   public long getId() {
/*  96 */     return this.id;
/*     */   }
/*     */   
/*     */   public UUID getUUID() {
/* 100 */     return this.uuid;
/*     */   }
/*     */   
/*     */   public long getSeed() {
/* 104 */     return this.seed;
/*     */   }
/*     */   
/*     */   public void setSeed(long seed) {
/* 108 */     this.seed = seed;
/* 109 */     if (this.random == null) {
/* 110 */       this.random = new Random(seed);
/*     */     } else {
/* 112 */       this.random.setSeed(seed);
/*     */     } 
/* 114 */     this.uuid = new UUID(this.random.nextLong(), this.random.nextLong());
/*     */   }
/*     */   
/*     */   public double getDoriki() {
/* 118 */     return this.doriki;
/*     */   }
/*     */   
/*     */   public long getBounty() {
/* 122 */     return this.bounty;
/*     */   }
/*     */   
/*     */   public float getObservationHaki() {
/* 126 */     return this.kenbunshokuExp;
/*     */   }
/*     */   
/*     */   public float getBusoshokuHaki() {
/* 130 */     return this.busoshokuExp;
/*     */   }
/*     */   
/*     */   public Random getRandom() {
/* 134 */     return this.random;
/*     */   }
/*     */   
/*     */   public ResourceLocation getFaction() {
/* 138 */     return this.faction;
/*     */   }
/*     */   
/*     */   public ResourceLocation getRace() {
/* 142 */     return this.race;
/*     */   }
/*     */   
/*     */   public ResourceLocation getStyle() {
/* 146 */     return this.style;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean isPirate() {
/* 151 */     return this.faction.equals(ModValues.PIRATE);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean isMarine() {
/* 156 */     return this.faction.equals(ModValues.MARINE);
/*     */   }
/*     */   
/*     */   public NewsEntry getNewsEntry(World world) {
/* 160 */     if (this.newsEntry == null) {
/* 161 */       updateNewsEntry(world);
/*     */     }
/* 163 */     return this.newsEntry;
/*     */   }
/*     */   
/*     */   public void updateNewsEntry(World world) {
/* 167 */     this.newsEntry = NewsEntry.createNewsFor(this, world);
/* 168 */     this.lastNewsUpdate = world.func_82737_E();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recalculateDifficulty(ServerWorld world) {
/* 176 */     float timePassed = (float)world.func_82737_E() / 1.5552E8F;
/* 177 */     int amount = 0;
/* 178 */     double totalDoriki = 0.0D;
/* 179 */     float totalHaki = 0.0F;
/* 180 */     for (ServerPlayerEntity player : world.func_217369_A()) {
/* 181 */       amount++;
/* 182 */       totalDoriki += EntityStatsCapability.get((LivingEntity)player).getDoriki();
/* 183 */       IHakiData hakiData = HakiDataCapability.get((LivingEntity)player);
/* 184 */       totalHaki += hakiData.getBusoshokuHakiExp();
/* 185 */       totalHaki += hakiData.getKenbunshokuHakiExp();
/*     */     } 
/*     */     
/* 188 */     double totalPossibleDoriki = (CommonConfig.INSTANCE.getDorikiLimit() * amount);
/* 189 */     float totalPossibleHaki = (CommonConfig.INSTANCE.getHakiExpLimit() * 2 * amount);
/*     */     
/* 191 */     double dorikiBonus = MathHelper.func_151237_a(totalDoriki / totalPossibleDoriki, 0.0D, 0.5D);
/* 192 */     double hakiBonus = MathHelper.func_151237_a((totalHaki / totalPossibleHaki), 0.0D, 0.5D);
/*     */     
/* 194 */     double currentDifficulty = dorikiBonus * 0.3D + hakiBonus * 0.3D + timePassed * 0.4D;
/*     */     
/* 196 */     if (Double.isNaN(this.doriki)) {
/* 197 */       this.doriki = 1000.0D;
/*     */     }
/* 199 */     double newDoriki = (float)(this.doriki * (1.0D + dorikiBonus));
/* 200 */     if (newDoriki < this.doriki) {
/* 201 */       newDoriki = this.doriki;
/*     */     }
/* 203 */     this.doriki = MathHelper.func_151237_a(newDoriki, 1000.0D, CommonConfig.INSTANCE.getDorikiLimit());
/*     */     
/* 205 */     if (Float.isNaN(this.kenbunshokuExp)) {
/* 206 */       this.kenbunshokuExp = 10.0F;
/*     */     }
/* 208 */     float newObsHaki = (float)(this.kenbunshokuExp * (1.0D + hakiBonus));
/* 209 */     if (newObsHaki < this.kenbunshokuExp) {
/* 210 */       newObsHaki = this.kenbunshokuExp;
/*     */     }
/* 212 */     this.kenbunshokuExp = MathHelper.func_76131_a(newObsHaki, 10.0F, CommonConfig.INSTANCE.getHakiExpLimit());
/*     */     
/* 214 */     if (Float.isNaN(this.busoshokuExp)) {
/* 215 */       this.busoshokuExp = 10.0F;
/*     */     }
/* 217 */     float newBusoHaki = (float)(this.busoshokuExp * (1.0D + hakiBonus));
/* 218 */     if (newBusoHaki < this.busoshokuExp) {
/* 219 */       newBusoHaki = this.busoshokuExp;
/*     */     }
/* 221 */     this.busoshokuExp = MathHelper.func_76131_a(newBusoHaki, 10.0F, CommonConfig.INSTANCE.getHakiExpLimit());
/*     */     
/* 223 */     long newBounty = 100000L;
/* 224 */     if (this.faction.equals(ModValues.PIRATE)) {
/* 225 */       newBounty = (long)(newBounty * (1.0D + currentDifficulty));
/* 226 */       newBounty += this.random.nextInt(10000);
/*     */     } 
/* 228 */     this.bounty += newBounty;
/* 229 */     this.bounty = WyHelper.clamp(this.bounty, 10000L, 100000000000L);
/*     */     
/* 231 */     this.lastGameUpdate = world.func_82737_E();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public NotoriousEntity createTrackedMob(World world) {
/* 236 */     NotoriousEntity entity = null;
/*     */     
/* 238 */     if (isPirate()) {
/* 239 */       entity = new NotoriousEntity((EntityType)ModEntities.PIRATE_NOTORIOUS_CAPTAIN.get(), world, ModValues.PIRATE, this);
/*     */     }
/* 241 */     else if (isMarine()) {
/* 242 */       entity = new NotoriousEntity((EntityType)ModEntities.MARINE_VICE_ADMIRAL.get(), world, ModValues.MARINE, this);
/*     */     } 
/*     */     
/* 245 */     if (entity == null) {
/* 246 */       LogManager.getLogger(this).warn("Null entity found when creating a Notorious entity, this will most likely break things!");
/*     */     }
/*     */     
/* 249 */     return entity;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public NotoriousEntity spawnTrackedMob(ServerWorld world, Vector3d pos) {
/* 254 */     NotoriousEntity entity = createTrackedMob((World)world);
/* 255 */     if (entity == null) {
/* 256 */       return null;
/*     */     }
/*     */     
/* 259 */     int y = world.func_201676_a(Heightmap.Type.MOTION_BLOCKING, (int)pos.func_82615_a(), (int)pos.func_82616_c());
/* 260 */     entity.func_70107_b(pos.func_82615_a(), (y + 1), pos.func_82616_c());
/*     */     
/* 262 */     IEntityStats statsData = EntityStatsCapability.get((LivingEntity)entity);
/* 263 */     IHakiData hakiData = HakiDataCapability.get((LivingEntity)entity);
/*     */ 
/*     */     
/* 266 */     this.doriki = MathHelper.func_151237_a(this.doriki, 1000.0D, CommonConfig.INSTANCE.getDorikiLimit());
/* 267 */     this.bounty = WyHelper.clamp(this.bounty, 10000L, 100000000000L);
/*     */     
/* 269 */     statsData.setDoriki(this.doriki);
/* 270 */     statsData.setFaction(this.faction);
/* 271 */     if (this.faction.equals(ModValues.PIRATE)) {
/* 272 */       statsData.setBounty(this.bounty);
/*     */     }
/* 274 */     statsData.setRace(this.race);
/* 275 */     if (this.race.equals(ModValues.MINK)) {
/* 276 */       int size = ((RaceId)ModRaces.MINK.get()).getSubRaces().size();
/* 277 */       String subRace = ((RaceId)ModRaces.MINK.get()).getSubRaces().get(this.random.nextInt(size));
/* 278 */       statsData.setSubRace(subRace);
/*     */     } 
/* 280 */     statsData.setFightingStyle(this.style);
/* 281 */     hakiData.setKenbunshokuHakiExp(this.kenbunshokuExp);
/* 282 */     hakiData.setBusoshokuHakiExp(this.busoshokuExp);
/*     */     
/* 284 */     double health = 200.0D + this.doriki / 20.0D;
/* 285 */     health = MathHelper.func_151237_a(health, 200.0D, CommonConfig.INSTANCE.getDorikiLimit() / 20.0D);
/*     */     
/* 287 */     entity.func_110148_a(Attributes.field_233818_a_).func_111128_a(health);
/*     */     
/* 289 */     AttributeHelper.updateToughnessAttribute((LivingEntity)entity);
/* 290 */     entity.func_70691_i(entity.func_110138_aP());
/*     */     
/* 292 */     world.func_217376_c((Entity)entity);
/* 293 */     return entity;
/*     */   }
/*     */   
/*     */   public CompoundNBT save() {
/* 297 */     CompoundNBT tag = new CompoundNBT();
/*     */     
/* 299 */     tag.func_74772_a("seed", this.seed);
/* 300 */     tag.func_186854_a("uuid", this.uuid);
/* 301 */     tag.func_74772_a("id", this.id);
/* 302 */     tag.func_74772_a("lastGameUpdate", this.lastGameUpdate);
/* 303 */     tag.func_74772_a("lastNewsUpdate", this.lastNewsUpdate);
/* 304 */     tag.func_74778_a("faction", this.faction.toString());
/* 305 */     tag.func_74778_a("race", this.race.toString());
/* 306 */     tag.func_74778_a("style", this.style.toString());
/* 307 */     tag.func_74780_a("doriki", this.doriki);
/* 308 */     tag.func_74772_a("bounty", this.bounty);
/* 309 */     tag.func_74776_a("kenbunshokuExp", this.kenbunshokuExp);
/* 310 */     tag.func_74776_a("busoshokuExp", this.busoshokuExp);
/*     */     
/* 312 */     return tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object other) {
/* 317 */     if (!(other instanceof TrackedNPC)) {
/* 318 */       return false;
/*     */     }
/*     */     
/* 321 */     TrackedNPC otherNPC = (TrackedNPC)other;
/* 322 */     if (this.id == otherNPC.id) {
/* 323 */       return true;
/*     */     }
/*     */     
/* 326 */     return super.equals(other);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\poi\TrackedNPC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */