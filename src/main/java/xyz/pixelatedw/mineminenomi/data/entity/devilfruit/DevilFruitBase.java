/*     */ package xyz.pixelatedw.mineminenomi.data.entity.devilfruit;
/*     */ 
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.Deque;
/*     */ import java.util.Optional;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ 
/*     */ public class DevilFruitBase implements IDevilFruit {
/*  20 */   private int version = 2;
/*     */   private LivingEntity owner;
/*  22 */   private Optional<ResourceLocation> devilFruit = Optional.empty();
/*  23 */   private String zoanPoint = "";
/*     */   private boolean hasAwakenedFruit;
/*  25 */   private Deque<ResourceLocation> morphs = new ArrayDeque<>();
/*     */   private boolean isMorphDirty;
/*  27 */   private Optional<MorphInfo> lastMorph = Optional.empty();
/*     */   
/*     */   private boolean hasYamiPower = false;
/*     */   
/*     */   private boolean hasStrongWaterWeakness = false;
/*     */   private boolean hasWeakWaterWeakness = false;
/*     */   private boolean hasKairosekiWeakness = false;
/*     */   
/*     */   public int getVersion() {
/*  36 */     return this.version;
/*     */   }
/*     */ 
/*     */   
/*     */   public IDevilFruit setOwner(LivingEntity entity) {
/*  41 */     this.owner = entity;
/*  42 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Optional<ResourceLocation> getDevilFruit() {
/*  47 */     return this.devilFruit;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDevilFruitItem() {
/*  52 */     if (!hasAnyDevilFruit()) {
/*  53 */       return Items.field_190931_a;
/*     */     }
/*     */     
/*  56 */     return (Item)ForgeRegistries.ITEMS.getValue(getDevilFruit().get());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDevilFruit(@Nullable ResourceLocation value) {
/*  61 */     this.devilFruit = Optional.ofNullable(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDevilFruit(AkumaNoMiItem fruit) {
/*  66 */     this.devilFruit = Optional.ofNullable(fruit.getRegistryName());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasAnyDevilFruit() {
/*  71 */     return this.devilFruit.isPresent();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasDevilFruit(AkumaNoMiItem fruit) {
/*  76 */     return ((Boolean)getDevilFruit().<Boolean>map(rs -> Boolean.valueOf((rs != null && rs.equals(fruit.getRegistryName())))).orElse(Boolean.valueOf(false))).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeDevilFruit() {
/*  81 */     setDevilFruit((ResourceLocation)null);
/*  82 */     setZoanPoint("");
/*  83 */     this.morphs.clear();
/*  84 */     setYamiPower(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLogia() {
/*  89 */     return (!hasYamiPower() && AbilityDataCapability.get(this.owner).getPassiveAbilities(a -> a instanceof xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility).size() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasYamiPower() {
/*  94 */     return this.hasYamiPower;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setYamiPower(boolean value) {
/*  99 */     this.hasYamiPower = value;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Optional<MorphInfo> getCurrentMorph() {
/* 105 */     if (!this.isMorphDirty && this.lastMorph.isPresent()) {
/* 106 */       return this.lastMorph;
/*     */     }
/*     */ 
/*     */     
/* 110 */     ResourceLocation morphId = this.morphs.peekLast();
/* 111 */     if (morphId != null) {
/* 112 */       MorphInfo morph = (MorphInfo)ModRegistries.MORPHS.getValue(morphId);
/* 113 */       if (morph != null) {
/* 114 */         this.lastMorph = Optional.ofNullable(morph);
/* 115 */         this.isMorphDirty = false;
/* 116 */         return this.lastMorph;
/*     */       } 
/*     */     } 
/*     */     
/* 120 */     return Optional.empty();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasMorphInQueue(MorphInfo morph) {
/* 125 */     if (!this.isMorphDirty && this.lastMorph.isPresent() && ((MorphInfo)this.lastMorph.get()).equals(morph)) {
/* 126 */       return true;
/*     */     }
/*     */     
/* 129 */     return this.morphs.contains(morph.getRegistryName());
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMorph(MorphInfo morph) {
/* 134 */     if (hasMorphInQueue(morph)) {
/*     */       return;
/*     */     }
/*     */     
/* 138 */     this.morphs.addLast(morph.getRegistryName());
/* 139 */     this.isMorphDirty = true;
/* 140 */     setZoanPoint(morph.getForm());
/*     */     
/* 142 */     if (this.owner != null) {
/* 143 */       morph.updateMorphSize(this.owner);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeMorph(MorphInfo morph) {
/* 149 */     if (this.morphs.size() <= 0) {
/*     */       return;
/*     */     }
/*     */     
/* 153 */     this.morphs.removeLastOccurrence(morph.getRegistryName());
/* 154 */     this.isMorphDirty = true;
/* 155 */     setZoanPoint(getCurrentMorph().<String>map(m -> m.getForm()).orElse(""));
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeMorph() {
/* 160 */     if (this.morphs.size() <= 0) {
/*     */       return;
/*     */     }
/*     */     
/* 164 */     this.morphs.removeLast();
/* 165 */     this.isMorphDirty = true;
/* 166 */     setZoanPoint(getCurrentMorph().<String>map(m -> m.getForm()).orElse(""));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearMorphs() {
/* 172 */     this.morphs.clear();
/* 173 */     this.isMorphDirty = true;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public String getZoanPoint() {
/* 179 */     return this.zoanPoint;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setZoanPoint(String value) {
/* 185 */     this.zoanPoint = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasAwakenedFruit() {
/* 190 */     if (!CommonConfig.INSTANCE.hasAwakeningsEnabled()) {
/* 191 */       return false;
/*     */     }
/*     */     
/* 194 */     if (this.owner == null) {
/* 195 */       return false;
/*     */     }
/*     */     
/* 198 */     return this.hasAwakenedFruit;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAwakenedFruit(boolean flag) {
/* 203 */     this.hasAwakenedFruit = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasStrongWaterWeakness() {
/* 208 */     return this.hasStrongWaterWeakness;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStrongWaterWeakness(boolean flag) {
/* 213 */     this.hasStrongWaterWeakness = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasWeakWaterWeakness() {
/* 218 */     return this.hasWeakWaterWeakness;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWeakWaterWeakness(boolean flag) {
/* 223 */     this.hasWeakWaterWeakness = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasKairosekiWeakness() {
/* 228 */     return this.hasKairosekiWeakness;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setKairosekiWeakness(boolean flag) {
/* 233 */     this.hasKairosekiWeakness = flag;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\devilfruit\DevilFruitBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */