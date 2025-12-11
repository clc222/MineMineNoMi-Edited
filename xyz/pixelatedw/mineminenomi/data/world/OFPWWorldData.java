/*     */ package xyz.pixelatedw.mineminenomi.data.world;
/*     */ 
/*     */ import com.google.common.base.Strings;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.storage.WorldSavedData;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.fml.server.ServerLifecycleHooks;
/*     */ import xyz.pixelatedw.mineminenomi.api.OneFruitEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.onefruit.LostDevilFruitEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.AbilitiesConfig;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ 
/*     */ 
/*     */ public class OFPWWorldData
/*     */   extends WorldSavedData
/*     */ {
/*     */   private static final String IDENTIFIER = "mineminenomi-ofpw";
/*  31 */   private List<OneFruitEntry> oneFruit = new ArrayList<>();
/*     */   
/*     */   public OFPWWorldData() {
/*  34 */     super("mineminenomi-ofpw");
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static OFPWWorldData get() {
/*  40 */     if (ServerLifecycleHooks.getCurrentServer() != null) {
/*  41 */       return (OFPWWorldData)ServerLifecycleHooks.getCurrentServer().func_241755_D_().func_217481_x().func_215752_a(OFPWWorldData::new, "mineminenomi-ofpw");
/*     */     }
/*  43 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT func_189551_b(CompoundNBT nbt) {
/*  48 */     if (!((CommonConfig.OneFruitPerWorldLogic)AbilitiesConfig.ONE_FRUIT_PER_WORLD.get()).equals(CommonConfig.OneFruitPerWorldLogic.NONE)) {
/*  49 */       ListNBT oneFruitList = new ListNBT();
/*  50 */       if (this.oneFruit.size() > 0) {
/*  51 */         for (OneFruitEntry entry : this.oneFruit) {
/*  52 */           CompoundNBT entryNBT = entry.save();
/*  53 */           oneFruitList.add(entryNBT);
/*     */         } 
/*     */       }
/*  56 */       nbt.func_218657_a("oneFruitList", (INBT)oneFruitList);
/*     */     } 
/*     */     
/*  59 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_76184_a(CompoundNBT nbt) {
/*  64 */     ListNBT oneFruit = nbt.func_150295_c("oneFruitList", 10);
/*  65 */     this.oneFruit.clear();
/*  66 */     for (int i = 0; i < oneFruit.size(); i++) {
/*  67 */       CompoundNBT entryNBT = oneFruit.func_150305_b(i);
/*  68 */       OneFruitEntry entry = OneFruitEntry.from(entryNBT);
/*  69 */       this.oneFruit.add(entry);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List<OneFruitEntry> getOneFruitEntries() {
/*  75 */     return this.oneFruit;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public OneFruitEntry getOneFruitEntry(ResourceLocation key) {
/*  80 */     Optional<OneFruitEntry> oneFruit = this.oneFruit.stream().filter(entry -> entry.getKey().equals(key)).findFirst();
/*  81 */     if (oneFruit.isPresent()) {
/*  82 */       return oneFruit.get();
/*     */     }
/*     */     
/*  85 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isFruitAvailable(AkumaNoMiItem item) {
/*  89 */     return isFruitAvailable(item.getRegistryName());
/*     */   }
/*     */   
/*     */   public boolean isFruitAvailable(ResourceLocation id) {
/*  93 */     Optional<OneFruitEntry> oneFruitEntry = this.oneFruit.stream().filter(entry -> entry.getKey().equals(id)).findFirst();
/*     */     
/*  95 */     if (!oneFruitEntry.isPresent()) {
/*  96 */       return true;
/*     */     }
/*     */     
/*  99 */     if (oneFruitEntry.isPresent() && ((OneFruitEntry)oneFruitEntry.get()).getStatus() == OneFruitEntry.Status.LOST) {
/* 100 */       return true;
/*     */     }
/*     */     
/* 103 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isFruitInWorld(ResourceLocation key) {
/* 107 */     return this.oneFruit.stream().anyMatch(entry -> (entry.getKey().equals(key) && entry.getStatus() != OneFruitEntry.Status.LOST));
/*     */   }
/*     */   
/*     */   public boolean isFruitInUse(ResourceLocation key) {
/* 111 */     return this.oneFruit.stream().anyMatch(entry -> (entry.getKey().equals(key) && entry.getStatus() == OneFruitEntry.Status.IN_USE));
/*     */   }
/*     */   
/*     */   public boolean isFruitDuped(ResourceLocation key, UUID uuid) {
/* 115 */     return this.oneFruit.stream().anyMatch(entry -> {
/* 116 */           boolean keyCheck = (entry.getKey().equals(key) && entry.getStatus() != OneFruitEntry.Status.LOST);
/* 117 */           boolean ownerCheck = (entry.getOwner().isPresent() && !((UUID)entry.getOwner().get()).equals(uuid));
/* 118 */           return (keyCheck && ownerCheck);
/*     */         });
/*     */   }
/*     */   
/*     */   private boolean addOneFruit(ResourceLocation key, @Nullable UUID uuid, OneFruitEntry.Status status) {
/* 123 */     return addOneFruit(key, uuid, status, null);
/*     */   }
/*     */   
/*     */   private boolean addOneFruit(ResourceLocation key, @Nullable UUID uuid, OneFruitEntry.Status status, @Nullable String message) {
/* 127 */     if (key == null || !CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/* 128 */       return false;
/*     */     }
/*     */     
/* 131 */     if (isFruitInWorld(key)) {
/* 132 */       return false;
/*     */     }
/*     */     
/* 135 */     OneFruitEntry entry = new OneFruitEntry(key, uuid, status, message);
/* 136 */     this.oneFruit.add(entry);
/* 137 */     func_76185_a();
/*     */     
/* 139 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean updateOneFruit(ResourceLocation key, @Nullable UUID uuid, OneFruitEntry.Status status) {
/* 144 */     return updateOneFruit(key, uuid, status, null);
/*     */   }
/*     */   
/*     */   public boolean updateOneFruit(ResourceLocation key, @Nullable UUID uuid, OneFruitEntry.Status status, @Nullable String message) {
/* 148 */     return updateOneFruit(key, uuid, status, null, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean updateOneFruit(ResourceLocation key, @Nullable UUID uuid, OneFruitEntry.Status status, @Nullable String message, boolean force) {
/* 153 */     if (key == null || !CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/* 154 */       return false;
/*     */     }
/*     */     
/* 157 */     Optional<OneFruitEntry> oneFruit = this.oneFruit.stream().filter(entry -> entry.getKey().equals(key)).findFirst();
/* 158 */     if (!oneFruit.isPresent()) {
/* 159 */       addOneFruit(key, uuid, status, message);
/*     */     } else {
/*     */       
/* 162 */       if (!force) {
/* 163 */         boolean sameOwner = (((OneFruitEntry)oneFruit.get()).getOwner().isPresent() && ((UUID)((OneFruitEntry)oneFruit.get()).getOwner().get()).equals(uuid));
/*     */ 
/*     */         
/* 166 */         if (((OneFruitEntry)oneFruit.get()).getStatus() == OneFruitEntry.Status.IN_USE && (status != OneFruitEntry.Status.LOST || !sameOwner)) {
/* 167 */           return false;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 172 */         if (((OneFruitEntry)oneFruit.get()).getStatus() == OneFruitEntry.Status.INVENTORY && status == OneFruitEntry.Status.DROPPED && !sameOwner) {
/* 173 */           return false;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 178 */         if (((OneFruitEntry)oneFruit.get()).getStatus() == OneFruitEntry.Status.INVENTORY && status == OneFruitEntry.Status.INVENTORY && !sameOwner) {
/* 179 */           return false;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 184 */         if (((OneFruitEntry)oneFruit.get()).getStatus() == OneFruitEntry.Status.INVENTORY && status == OneFruitEntry.Status.IN_USE && !sameOwner) {
/* 185 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 189 */       if (!Strings.isNullOrEmpty(message)) {
/* 190 */         ((OneFruitEntry)oneFruit.get()).setStatusMessage(message);
/*     */       }
/* 192 */       ((OneFruitEntry)oneFruit.get()).update(uuid, status);
/* 193 */       func_76185_a();
/*     */     } 
/*     */     
/* 196 */     return true;
/*     */   }
/*     */   
/*     */   public void lostOneFruit(ResourceLocation key, @Nullable LivingEntity entity, String message) {
/* 200 */     if (key == null || !CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/*     */       return;
/*     */     }
/*     */     
/* 204 */     Optional<OneFruitEntry> oneFruit = this.oneFruit.stream().filter(entry -> entry.getKey().equals(key)).findFirst();
/* 205 */     if (oneFruit.isPresent()) {
/* 206 */       ((OneFruitEntry)oneFruit.get()).setStatusMessage(message);
/* 207 */       ((OneFruitEntry)oneFruit.get()).update((entity != null) ? entity.func_110124_au() : null, OneFruitEntry.Status.LOST);
/* 208 */       func_76185_a();
/*     */       
/* 210 */       LostDevilFruitEvent lostEvent = new LostDevilFruitEvent(entity, DevilFruitHelper.getDevilFruitItem(((OneFruitEntry)oneFruit.get()).getKey()), message);
/* 211 */       MinecraftForge.EVENT_BUS.post((Event)lostEvent);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void forceUpdateOneFruit(ResourceLocation key) {
/* 216 */     if (key == null || !CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/*     */       return;
/*     */     }
/*     */     
/* 220 */     Optional<OneFruitEntry> oneFruit = this.oneFruit.stream().filter(entry -> entry.getKey().equals(key)).findFirst();
/* 221 */     if (oneFruit.isPresent()) {
/* 222 */       ((OneFruitEntry)oneFruit.get()).forceLastUpdateNow();
/* 223 */       func_76185_a();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void wipeOneFruitHistory(ResourceLocation key) {
/* 228 */     if (key == null || !CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/*     */       return;
/*     */     }
/*     */     
/* 232 */     Optional<OneFruitEntry> oneFruit = this.oneFruit.stream().filter(entry -> entry.getKey().equals(key)).findFirst();
/* 233 */     if (oneFruit.isPresent()) {
/* 234 */       ((OneFruitEntry)oneFruit.get()).getHistory().clear();
/* 235 */       func_76185_a();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\world\OFPWWorldData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */