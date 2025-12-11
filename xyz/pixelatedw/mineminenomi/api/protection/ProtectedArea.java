/*     */ package xyz.pixelatedw.mineminenomi.api.protection;
/*     */ 
/*     */ import com.google.common.base.Strings;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.BlockSnapshot;
/*     */ import org.apache.commons.lang3.RandomStringUtils;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.BlockPlacingHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProtectedArea
/*     */ {
/*     */   private BlockPos centerPos;
/*     */   private int size;
/*  28 */   private String label = "";
/*     */   private boolean allowBlockDestruction = false;
/*     */   private boolean allowEntityDamage = false;
/*     */   private boolean allowAbilities = false;
/*     */   private boolean allowBlockRestoration = false;
/*     */   private boolean allowStatLoss = true;
/*     */   private boolean allowDeath = true;
/*  35 */   private int restoreInterval = 20;
/*  36 */   private int restoreAmount = 15;
/*  37 */   private int restoreDistance = 10;
/*  38 */   private int unconsciousTime = 40;
/*     */   
/*  40 */   private Map<BlockPlacingHelper.DistanceBlockPos, RestorationEntry> restoreBlocks = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProtectedArea(BlockPos center, int size, String label) {
/*  46 */     this.centerPos = center;
/*  47 */     setLabel(label);
/*  48 */     this.size = size;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ProtectedArea from(CompoundNBT nbt) {
/*  53 */     ProtectedArea area = new ProtectedArea();
/*  54 */     area.load(nbt);
/*  55 */     area.setLabel(area.getLabel());
/*  56 */     return area;
/*     */   }
/*     */   
/*     */   public void setBlockDestruction(boolean flag) {
/*  60 */     this.allowBlockDestruction = flag;
/*     */   }
/*     */   
/*     */   public boolean canDestroyBlocks() {
/*  64 */     return this.allowBlockDestruction;
/*     */   }
/*     */   
/*     */   public void setEntityDamage(boolean flag) {
/*  68 */     this.allowEntityDamage = flag;
/*     */   }
/*     */   
/*     */   public boolean canHurtEntities() {
/*  72 */     return this.allowEntityDamage;
/*     */   }
/*     */   
/*     */   public void setBlockRestoration(boolean flag) {
/*  76 */     this.allowBlockRestoration = flag;
/*     */   }
/*     */   
/*     */   public boolean canRestoreBlocks() {
/*  80 */     return this.allowBlockRestoration;
/*     */   }
/*     */   
/*     */   public void setStatLoss(boolean flag) {
/*  84 */     this.allowStatLoss = flag;
/*     */   }
/*     */   
/*     */   public boolean canLoseStats() {
/*  88 */     return this.allowStatLoss;
/*     */   }
/*     */   
/*     */   public void setDeath(boolean flag) {
/*  92 */     this.allowDeath = flag;
/*     */   }
/*     */   
/*     */   public boolean canDie() {
/*  96 */     return this.allowDeath;
/*     */   }
/*     */   
/*     */   public void setAbilitiesUsage(boolean flag) {
/* 100 */     this.allowAbilities = flag;
/*     */   }
/*     */   
/*     */   public boolean canAbilitiesBeUsed() {
/* 104 */     return this.allowAbilities;
/*     */   }
/*     */   
/*     */   public boolean canUseAbility(AbilityCore core) {
/* 108 */     if (!this.allowAbilities) {
/* 109 */       boolean isWhitelisted = CommonConfig.INSTANCE.isAbilityProtectionWhitelisted(core);
/* 110 */       if (isWhitelisted) {
/* 111 */         return true;
/*     */       }
/* 113 */       return false;
/*     */     } 
/* 115 */     return true;
/*     */   }
/*     */   
/*     */   public void setRestoreInterval(int interval) {
/* 119 */     this.restoreInterval = interval;
/*     */   }
/*     */   
/*     */   public int getRestoreInterval() {
/* 123 */     return this.restoreInterval;
/*     */   }
/*     */   
/*     */   public void setRestoreAmount(int amount) {
/* 127 */     this.restoreAmount = amount;
/*     */   }
/*     */   
/*     */   public int getRestoreAmount() {
/* 131 */     return this.restoreAmount;
/*     */   }
/*     */   
/*     */   public void setRestoreDistance(int amount) {
/* 135 */     this.restoreDistance = amount;
/*     */   }
/*     */   
/*     */   public int getRestoreDistance() {
/* 139 */     return this.restoreDistance;
/*     */   }
/*     */   
/*     */   public void setUnconsciousTime(int time) {
/* 143 */     this.unconsciousTime = time;
/*     */   }
/*     */   
/*     */   public int getUnconsciousTime() {
/* 147 */     return this.unconsciousTime;
/*     */   }
/*     */   
/*     */   public void queueForRestoration(World world, BlockPos pos) {
/* 151 */     BlockPlacingHelper.DistanceBlockPos pos2 = new BlockPlacingHelper.DistanceBlockPos(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/* 152 */     queueForRestoration(pos2, new RestorationEntry(world.func_82737_E(), BlockSnapshot.create(world.func_234923_W_(), (IWorld)world, pos, 2)));
/*     */   }
/*     */   
/*     */   public void queueForRestoration(BlockPlacingHelper.DistanceBlockPos pos, RestorationEntry entry) {
/* 156 */     this.restoreBlocks.putIfAbsent(pos, entry);
/*     */   }
/*     */   
/*     */   public void queueForRestoration(Map<BlockPlacingHelper.DistanceBlockPos, RestorationEntry> map) {
/* 160 */     map.entrySet().removeIf(e -> this.restoreBlocks.containsKey(e.getKey()));
/* 161 */     this.restoreBlocks.putAll(map);
/*     */   }
/*     */   
/*     */   public Map<BlockPlacingHelper.DistanceBlockPos, RestorationEntry> getRestorationMap() {
/* 165 */     return this.restoreBlocks;
/*     */   }
/*     */   
/*     */   public void restoreBlocks(World world) {
/* 169 */     if (getRestorationMap().size() <= 0 || !canRestoreBlocks()) {
/*     */       return;
/*     */     }
/*     */     
/* 173 */     if (getRestoreInterval() > 0 && world.func_82737_E() % getRestoreInterval() != 0L) {
/*     */       return;
/*     */     }
/*     */     
/* 177 */     Iterator<Map.Entry<BlockPlacingHelper.DistanceBlockPos, RestorationEntry>> iter = getRestorationMap().entrySet().iterator();
/*     */     
/* 179 */     int restoreLeft = this.restoreAmount;
/*     */     
/* 181 */     while (restoreLeft > 0 && iter.hasNext()) {
/* 182 */       Map.Entry<BlockPlacingHelper.DistanceBlockPos, RestorationEntry> entry = iter.next();
/*     */ 
/*     */       
/* 185 */       if (world.func_82737_E() < ((RestorationEntry)entry.getValue()).getTimestamp() + CommonConfig.INSTANCE.getGlobalProtectionGraceTime()) {
/*     */         continue;
/*     */       }
/*     */       
/* 189 */       BlockPos pos = (BlockPos)entry.getKey();
/*     */       
/* 191 */       if (this.restoreDistance > 0) {
/* 192 */         boolean hasPlayerNear = false;
/* 193 */         for (PlayerEntity player : world.func_217369_A()) {
/* 194 */           if (player.func_233580_cy_().func_218141_a((Vector3i)pos, this.restoreDistance)) {
/* 195 */             hasPlayerNear = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 200 */         if (hasPlayerNear) {
/*     */           continue;
/*     */         }
/*     */       } 
/*     */       
/* 205 */       BlockState replaced = ((RestorationEntry)entry.getValue()).getSnapshot().getReplacedBlock();
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
/* 218 */       world.func_180501_a(pos, replaced, 3);
/* 219 */       CompoundNBT nbt = ((RestorationEntry)entry.getValue()).getSnapshot().getNbt();
/* 220 */       TileEntity te = null;
/* 221 */       if (nbt != null) {
/* 222 */         te = world.func_175625_s(pos);
/* 223 */         if (te != null) {
/* 224 */           te.func_230337_a_(replaced, nbt);
/* 225 */           te.func_70296_d();
/*     */         } 
/*     */       } 
/*     */       
/* 229 */       iter.remove();
/* 230 */       restoreLeft--;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setLabel(String label) {
/* 235 */     this.label = checkValidLabel(label);
/*     */   }
/*     */   
/*     */   public String getLabel() {
/* 239 */     return WyHelper.getResourceName(this.label);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos getCenter() {
/* 244 */     return this.centerPos;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/* 249 */     return this.size;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(int size) {
/* 254 */     this.size = size;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInside(int posX, int posY, int posZ) {
/* 259 */     int size = getSize() + 1;
/* 260 */     if (posX < getCenter().func_177958_n() + size && posX > getCenter().func_177958_n() - size && posY < getCenter().func_177956_o() + size && posY > 
/* 261 */       getCenter().func_177956_o() - size && posZ < getCenter().func_177952_p() + size && posZ > getCenter().func_177952_p() - size) {
/* 262 */       return true;
/*     */     }
/*     */     
/* 265 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private String checkValidLabel(String label) {
/* 270 */     if (Strings.isNullOrEmpty(label)) {
/* 271 */       return RandomStringUtils.randomAlphabetic(5);
/*     */     }
/* 273 */     return WyHelper.getResourceName(label);
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT save() {
/* 278 */     CompoundNBT nbt = new CompoundNBT();
/* 279 */     nbt.func_74778_a("label", this.label);
/* 280 */     nbt.func_74768_a("x", this.centerPos.func_177958_n());
/* 281 */     nbt.func_74768_a("y", this.centerPos.func_177956_o());
/* 282 */     nbt.func_74768_a("z", this.centerPos.func_177952_p());
/* 283 */     nbt.func_74768_a("size", this.size);
/*     */     
/* 285 */     nbt.func_74757_a("allowBlockDestruction", this.allowBlockDestruction);
/* 286 */     nbt.func_74757_a("allowEntityDamage", this.allowEntityDamage);
/* 287 */     nbt.func_74757_a("allowBlockRestoration", this.allowBlockRestoration);
/* 288 */     nbt.func_74757_a("allowAbilities", this.allowAbilities);
/* 289 */     nbt.func_74757_a("allowStatLoss", this.allowStatLoss);
/* 290 */     nbt.func_74757_a("allowDeath", this.allowDeath);
/* 291 */     nbt.func_74768_a("restoreInterval", this.restoreInterval);
/* 292 */     nbt.func_74768_a("restoreAmount", this.restoreAmount);
/* 293 */     nbt.func_74768_a("restoreDistance", this.restoreDistance);
/* 294 */     nbt.func_74768_a("unconsciousTime", this.unconsciousTime);
/*     */     
/* 296 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 301 */     this.label = nbt.func_74779_i("label");
/* 302 */     int x = nbt.func_74762_e("x");
/* 303 */     int y = nbt.func_74762_e("y");
/* 304 */     int z = nbt.func_74762_e("z");
/* 305 */     this.centerPos = new BlockPos(x, y, z);
/* 306 */     this.size = nbt.func_74762_e("size");
/*     */     
/* 308 */     this.allowBlockDestruction = nbt.func_74767_n("allowBlockDestruction");
/* 309 */     this.allowEntityDamage = nbt.func_74767_n("allowEntityDamage");
/* 310 */     this.allowBlockRestoration = nbt.func_74767_n("allowBlockRestoration");
/* 311 */     this.allowAbilities = nbt.func_74767_n("allowAbilities");
/* 312 */     if (nbt.func_74764_b("allowStatLoss")) {
/* 313 */       this.allowStatLoss = nbt.func_74767_n("allowStatLoss");
/*     */     } else {
/*     */       
/* 316 */       this.allowStatLoss = true;
/*     */     } 
/* 318 */     if (nbt.func_74764_b("allowDeath")) {
/* 319 */       this.allowDeath = nbt.func_74767_n("allowDeath");
/*     */     } else {
/*     */       
/* 322 */       this.allowDeath = true;
/*     */     } 
/* 324 */     this.restoreInterval = nbt.func_74762_e("restoreInterval");
/* 325 */     this.restoreAmount = nbt.func_74762_e("restoreAmount");
/* 326 */     if (nbt.func_74764_b("restoreDistance")) {
/* 327 */       this.restoreDistance = nbt.func_74762_e("restoreDistance");
/*     */     } else {
/*     */       
/* 330 */       this.restoreDistance = 10;
/*     */     } 
/* 332 */     if (nbt.func_74764_b("unconsciousTime")) {
/* 333 */       this.unconsciousTime = nbt.func_74762_e("unconsciousTime");
/*     */     } else {
/*     */       
/* 336 */       this.unconsciousTime = 40;
/*     */     } 
/*     */   }
/*     */   
/*     */   private ProtectedArea() {}
/*     */   
/*     */   public static class RestorationEntry { private long timestamp;
/*     */     
/*     */     public RestorationEntry(long timestamp, BlockSnapshot snapshot) {
/* 345 */       this.timestamp = timestamp;
/* 346 */       this.snapshot = snapshot;
/*     */     }
/*     */     private BlockSnapshot snapshot;
/*     */     public long getTimestamp() {
/* 350 */       return this.timestamp;
/*     */     }
/*     */     
/*     */     public BlockSnapshot getSnapshot() {
/* 354 */       return this.snapshot;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\protection\ProtectedArea.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */