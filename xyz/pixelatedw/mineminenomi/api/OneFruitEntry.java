/*     */ package xyz.pixelatedw.mineminenomi.api;
/*     */ 
/*     */ import com.google.common.base.Strings;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OneFruitEntry
/*     */ {
/*     */   private ResourceLocation fruitKey;
/*  23 */   private Optional<UUID> owner = Optional.empty();
/*     */   private Status status;
/*  25 */   private String statusMessage = "";
/*     */   private Date lastUpdate;
/*  27 */   private List<HistoryEntry> history = new ArrayList<>();
/*     */   
/*     */   private OneFruitEntry() {}
/*     */   
/*     */   public OneFruitEntry(ResourceLocation key, @Nullable UUID owner, Status status, @Nullable String message) {
/*  32 */     this.fruitKey = key;
/*  33 */     if (!Strings.isNullOrEmpty(message)) {
/*  34 */       setStatusMessage(message);
/*     */     }
/*  36 */     update(owner, status);
/*     */   }
/*     */   
/*     */   public static OneFruitEntry from(CompoundNBT nbt) {
/*  40 */     OneFruitEntry entry = new OneFruitEntry();
/*  41 */     entry.load(nbt);
/*  42 */     return entry;
/*     */   }
/*     */   
/*     */   public ResourceLocation getKey() {
/*  46 */     return this.fruitKey;
/*     */   }
/*     */   
/*     */   public Optional<Item> getItemFromKey() {
/*  50 */     return (Optional)Optional.ofNullable(ForgeRegistries.ITEMS.getValue(this.fruitKey));
/*     */   }
/*     */   
/*     */   public Optional<UUID> getOwner() {
/*  54 */     return this.owner;
/*     */   }
/*     */   
/*     */   public void setStatusMessage(String message) {
/*  58 */     this.statusMessage = message;
/*     */   }
/*     */   
/*     */   public Status getStatus() {
/*  62 */     return this.status;
/*     */   }
/*     */   
/*     */   public void update(UUID owner, Status status) {
/*  66 */     this.owner = Optional.ofNullable(owner);
/*  67 */     this.status = status;
/*  68 */     this.lastUpdate = new Date();
/*  69 */     registerCurrentHistory();
/*     */   }
/*     */   
/*     */   public void forceLastUpdateNow() {
/*  73 */     this.lastUpdate = new Date();
/*     */   }
/*     */   
/*     */   public Date getLastUpdate() {
/*  77 */     return this.lastUpdate;
/*     */   }
/*     */   
/*     */   public void registerCurrentHistory() {
/*  81 */     this.history.add(new HistoryEntry(this.owner, this.status, this.statusMessage, this.lastUpdate));
/*  82 */     this.statusMessage = "";
/*     */   }
/*     */   
/*     */   public List<HistoryEntry> getHistory() {
/*  86 */     return this.history;
/*     */   }
/*     */   
/*     */   public CompoundNBT save() {
/*  90 */     CompoundNBT nbt = new CompoundNBT();
/*     */     
/*  92 */     nbt.func_74778_a("fruit", this.fruitKey.toString());
/*  93 */     if (this.owner.isPresent()) {
/*  94 */       nbt.func_186854_a("owner", this.owner.get());
/*     */     }
/*  96 */     nbt.func_74778_a("status", this.status.name());
/*  97 */     nbt.func_74772_a("lastUpdate", this.lastUpdate.getTime());
/*  98 */     ListNBT history = new ListNBT();
/*  99 */     int limit = Math.min(1000, this.history.size());
/* 100 */     List<HistoryEntry> list = (List<HistoryEntry>)this.history.stream().skip((this.history.size() - limit)).limit(limit).collect(Collectors.toList());
/* 101 */     for (HistoryEntry entry : list) {
/* 102 */       CompoundNBT entryNBT = entry.save();
/* 103 */       history.add(entryNBT);
/*     */     } 
/* 105 */     nbt.func_218657_a("history", (INBT)history);
/*     */     
/* 107 */     return nbt;
/*     */   }
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 111 */     this.fruitKey = new ResourceLocation(nbt.func_74779_i("fruit"));
/* 112 */     if (nbt.func_186855_b("owner")) {
/* 113 */       this.owner = Optional.ofNullable(nbt.func_186857_a("owner"));
/*     */     }
/* 115 */     this.status = Status.valueOf(nbt.func_74779_i("status"));
/* 116 */     this.lastUpdate = new Date(nbt.func_74763_f("lastUpdate"));
/* 117 */     ListNBT history = nbt.func_150295_c("history", 10);
/* 118 */     this.history.clear();
/* 119 */     for (int i = 0; i < history.size(); i++) {
/* 120 */       CompoundNBT historyNBT = history.func_150305_b(i);
/* 121 */       this.history.add(HistoryEntry.from(historyNBT));
/*     */     } 
/*     */   }
/*     */   
/*     */   public enum Status {
/* 126 */     DROPPED,
/* 127 */     INVENTORY,
/* 128 */     IN_USE,
/* 129 */     LOST;
/*     */   }
/*     */   
/*     */   public static class HistoryEntry {
/* 133 */     private Optional<UUID> owner = Optional.empty();
/*     */     private OneFruitEntry.Status status;
/* 135 */     private String statusMessage = "";
/*     */     private Date date;
/*     */     
/*     */     private HistoryEntry() {}
/*     */     
/*     */     public HistoryEntry(Optional<UUID> owner, OneFruitEntry.Status status, String statusMessage, Date date) {
/* 141 */       this.owner = owner;
/* 142 */       this.status = status;
/* 143 */       this.statusMessage = statusMessage;
/* 144 */       this.date = date;
/*     */     }
/*     */     
/*     */     public static HistoryEntry from(CompoundNBT nbt) {
/* 148 */       HistoryEntry entry = new HistoryEntry();
/* 149 */       entry.load(nbt);
/* 150 */       return entry;
/*     */     }
/*     */     
/*     */     public Optional<UUID> getOwner() {
/* 154 */       return this.owner;
/*     */     }
/*     */     
/*     */     public OneFruitEntry.Status getStatus() {
/* 158 */       return this.status;
/*     */     }
/*     */     
/*     */     public String getStatusMessage() {
/* 162 */       return this.statusMessage;
/*     */     }
/*     */     
/*     */     public Date getDate() {
/* 166 */       return this.date;
/*     */     }
/*     */     
/*     */     public CompoundNBT save() {
/* 170 */       CompoundNBT nbt = new CompoundNBT();
/*     */       
/* 172 */       if (this.owner.isPresent()) {
/* 173 */         nbt.func_186854_a("uuid", this.owner.get());
/*     */       }
/* 175 */       nbt.func_74778_a("status", this.status.name());
/* 176 */       nbt.func_74778_a("statusMessage", this.statusMessage);
/* 177 */       nbt.func_74772_a("date", this.date.getTime());
/*     */       
/* 179 */       return nbt;
/*     */     }
/*     */     
/*     */     public void load(CompoundNBT nbt) {
/* 183 */       if (nbt.func_186855_b("uuid")) {
/* 184 */         this.owner = Optional.ofNullable(nbt.func_186857_a("uuid"));
/*     */       }
/* 186 */       this.status = OneFruitEntry.Status.valueOf(nbt.func_74779_i("status"));
/* 187 */       this.statusMessage = nbt.func_74779_i("statusMessage");
/* 188 */       this.date = new Date(nbt.func_74763_f("date"));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\OneFruitEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */