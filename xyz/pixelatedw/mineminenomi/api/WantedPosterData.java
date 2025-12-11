/*     */ package xyz.pixelatedw.mineminenomi.api;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.NBTUtil;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.EventsWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.IRandomTexture;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class WantedPosterData {
/*  26 */   private static final String[] WANTED_POSTER_BACKGROUNDS = new String[] { "forest1", "forest2", "jungle1", "jungle2", "hills1", "hills2", "hills3", "plains1", "plains2", "plains3", "taiga1", "taiga2" };
/*     */   
/*     */   private long id;
/*     */   private UUID ownerId;
/*     */   private String ownerName;
/*  31 */   private Optional<GameProfile> ownerProfile = Optional.empty();
/*  32 */   private Optional<ResourceLocation> skinTexture = Optional.empty();
/*  33 */   private Optional<Crew> ownerCrew = Optional.empty();
/*  34 */   private Optional<Vector3d> trackedPosition = Optional.empty();
/*     */   private ResourceLocation faction;
/*     */   private long bounty;
/*     */   private String bountyString;
/*     */   private String background;
/*     */   private String date;
/*     */   private Random random;
/*     */   private long seed;
/*     */   private boolean isExpired;
/*     */   
/*     */   private WantedPosterData() {}
/*     */   
/*     */   public WantedPosterData(LivingEntity entity, long bounty) {
/*  47 */     this.seed = entity.hashCode() + bounty;
/*  48 */     this.random = new Random(this.seed);
/*     */     
/*  50 */     this.ownerId = entity.func_110124_au();
/*  51 */     this.ownerName = entity.func_200200_C_().getString();
/*  52 */     if (entity instanceof PlayerEntity) {
/*  53 */       PlayerEntity player = (PlayerEntity)entity;
/*  54 */       this.ownerProfile = Optional.ofNullable(player.func_146103_bH());
/*     */     }
/*  56 */     else if (entity instanceof IRandomTexture) {
/*  57 */       ResourceLocation texture = ((IRandomTexture)entity).getCurrentTexture();
/*  58 */       if (texture == null) {
/*  59 */         this.skinTexture = Optional.ofNullable(((IRandomTexture)entity).getDefaultTexture());
/*     */       } else {
/*  61 */         this.skinTexture = Optional.ofNullable(texture);
/*     */       } 
/*     */     } 
/*     */     
/*  65 */     Crew crew = ExtendedWorldData.get().getCrewWithMember(this.ownerId);
/*  66 */     this.ownerCrew = Optional.ofNullable(crew);
/*     */     
/*  68 */     this.faction = EntityStatsCapability.get(entity).getFaction();
/*  69 */     this.bounty = bounty;
/*     */     
/*  71 */     DecimalFormat decimalFormat = new DecimalFormat("#,##0");
/*  72 */     this.bountyString = decimalFormat.format(this.bounty);
/*     */     
/*  74 */     int randomBg = (int)WyHelper.randomWithRange(this.random, 0, WANTED_POSTER_BACKGROUNDS.length - 1);
/*  75 */     this.background = WANTED_POSTER_BACKGROUNDS[randomBg];
/*     */     
/*  77 */     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/*  78 */     String dateString = format.format(new Date());
/*  79 */     this.date = dateString;
/*     */   }
/*     */   
/*     */   public static WantedPosterData empty() {
/*  83 */     return new WantedPosterData();
/*     */   }
/*     */   
/*     */   public static WantedPosterData from(CompoundNBT nbt) {
/*  87 */     WantedPosterData wantedPoster = new WantedPosterData();
/*     */     
/*  89 */     if (nbt.isEmpty()) {
/*  90 */       LogManager.getLogger(WantedPosterData.class).warn("Wanted Poster Data created without any actual data!");
/*  91 */       return wantedPoster;
/*     */     } 
/*     */     
/*  94 */     wantedPoster.ownerId = nbt.func_186857_a("UUID");
/*  95 */     wantedPoster.ownerName = nbt.func_74779_i("Name");
/*     */     
/*  97 */     if (nbt.func_150297_b("Owner", 10)) {
/*  98 */       wantedPoster.ownerProfile = Optional.ofNullable(NBTUtil.func_152459_a(nbt.func_74775_l("Owner")));
/*     */     }
/*     */     
/* 101 */     if (nbt.func_74764_b("Texture")) {
/* 102 */       wantedPoster.skinTexture = Optional.ofNullable(new ResourceLocation(nbt.func_74779_i("Texture")));
/*     */     }
/*     */     
/* 105 */     if (nbt.func_150297_b("TrackedPosition", 10)) {
/* 106 */       CompoundNBT trackedPos = nbt.func_74775_l("TrackedPosition");
/* 107 */       double x = trackedPos.func_74769_h("x");
/* 108 */       double y = trackedPos.func_74769_h("y");
/* 109 */       double z = trackedPos.func_74769_h("z");
/* 110 */       wantedPoster.trackedPosition = Optional.ofNullable(new Vector3d(x, y, z));
/*     */     } 
/*     */     
/* 113 */     if (nbt.func_150297_b("Crew", 10)) {
/* 114 */       wantedPoster.ownerCrew = Optional.ofNullable(Crew.from(nbt.func_74775_l("Crew")));
/*     */     }
/*     */     
/* 117 */     wantedPoster.bounty = nbt.func_74763_f("Bounty");
/* 118 */     DecimalFormat decimalFormat = new DecimalFormat("#,##0");
/* 119 */     wantedPoster.bountyString = decimalFormat.format(wantedPoster.bounty);
/* 120 */     wantedPoster.isExpired = nbt.func_74767_n("IsExpired");
/*     */     
/* 122 */     wantedPoster.faction = WyHelper.getResourceLocation(nbt, "Faction");
/* 123 */     wantedPoster.background = nbt.func_74779_i("Background");
/* 124 */     wantedPoster.date = nbt.func_74779_i("Date");
/* 125 */     wantedPoster.seed = nbt.func_74763_f("Seed");
/* 126 */     wantedPoster.random = new Random(wantedPoster.seed);
/*     */     
/* 128 */     return wantedPoster;
/*     */   }
/*     */   
/*     */   public UUID getOwnerId() {
/* 132 */     return this.ownerId;
/*     */   }
/*     */   
/*     */   public String getOwnerName() {
/* 136 */     return this.ownerName;
/*     */   }
/*     */   
/*     */   public Optional<GameProfile> getOwnerProfile() {
/* 140 */     return this.ownerProfile;
/*     */   }
/*     */   
/*     */   public Optional<Crew> getOwnerCrew() {
/* 144 */     return this.ownerCrew;
/*     */   }
/*     */   
/*     */   public Optional<ResourceLocation> getOwnerTexture() {
/* 148 */     return this.skinTexture;
/*     */   }
/*     */   
/*     */   public ResourceLocation getFaction() {
/* 152 */     return this.faction;
/*     */   }
/*     */   
/*     */   public long getBounty() {
/* 156 */     return this.bounty;
/*     */   }
/*     */   
/*     */   public String getBountyString() {
/* 160 */     return this.bountyString;
/*     */   }
/*     */   
/*     */   public String getBackground() {
/* 164 */     return this.background;
/*     */   }
/*     */   
/*     */   public String getDate() {
/* 168 */     return this.date;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExpired() {
/* 173 */     if (this.skinTexture.isPresent()) {
/* 174 */       EventsWorldData worldData = EventsWorldData.get();
/* 175 */       if (worldData != null) {
/* 176 */         boolean hasEvent = worldData.hasNTEventFor(this.ownerId);
/* 177 */         if (!hasEvent) {
/* 178 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 182 */     return this.isExpired;
/*     */   }
/*     */   
/*     */   public void checkIfExpired() {
/* 186 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/* 187 */     if (worldData == null) {
/* 188 */       LogManager.getLogger(this).warn("Checking for expiration on client side! Something has gone wrong.");
/*     */       return;
/*     */     } 
/* 191 */     this.isExpired = (worldData.getBounty(this.ownerId) != this.bounty);
/*     */   }
/*     */   
/*     */   public void setTrackedPosition(Vector3d pos) {
/* 195 */     this.trackedPosition = Optional.ofNullable(pos);
/*     */   }
/*     */   
/*     */   public Optional<Vector3d> getTrackedPosition() {
/* 199 */     return this.trackedPosition;
/*     */   }
/*     */   
/*     */   public CompoundNBT write() {
/* 203 */     CompoundNBT data = new CompoundNBT();
/*     */ 
/*     */     
/* 206 */     if (this.ownerId == null) {
/* 207 */       return data;
/*     */     }
/*     */     
/* 210 */     data.func_186854_a("UUID", this.ownerId);
/* 211 */     data.func_74778_a("Name", this.ownerName);
/* 212 */     data.func_74772_a("Bounty", this.bounty);
/* 213 */     data.func_74757_a("IsExpired", this.isExpired);
/* 214 */     data.func_74778_a("Faction", this.faction.toString());
/* 215 */     data.func_74778_a("Background", this.background);
/* 216 */     data.func_74778_a("Date", this.date);
/*     */     
/* 218 */     if (this.ownerProfile.isPresent()) {
/* 219 */       CompoundNBT gameProfileNBT = new CompoundNBT();
/* 220 */       NBTUtil.func_180708_a(gameProfileNBT, this.ownerProfile.get());
/* 221 */       data.func_218657_a("Owner", (INBT)gameProfileNBT);
/*     */     } 
/*     */     
/* 224 */     if (this.skinTexture.isPresent()) {
/* 225 */       data.func_74778_a("Texture", ((ResourceLocation)this.skinTexture.get()).toString());
/*     */     }
/*     */     
/* 228 */     if (this.trackedPosition.isPresent()) {
/* 229 */       CompoundNBT trackedPos = new CompoundNBT();
/* 230 */       trackedPos.func_74780_a("x", ((Vector3d)this.trackedPosition.get()).field_72450_a);
/* 231 */       trackedPos.func_74780_a("y", ((Vector3d)this.trackedPosition.get()).field_72448_b);
/* 232 */       trackedPos.func_74780_a("z", ((Vector3d)this.trackedPosition.get()).field_72449_c);
/* 233 */       data.func_218657_a("TrackedPosition", (INBT)trackedPos);
/*     */     } 
/*     */     
/* 236 */     if (this.ownerCrew.isPresent()) {
/* 237 */       data.func_218657_a("Crew", (INBT)((Crew)this.ownerCrew.get()).write());
/*     */     }
/*     */     
/* 240 */     data.func_74772_a("Seed", this.seed);
/*     */     
/* 242 */     return data;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\WantedPosterData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */