/*     */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*     */ 
/*     */ import com.google.common.base.Strings;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Set;
/*     */ import java.util.function.Consumer;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.text.IFormattableTextComponent;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.registries.ForgeRegistryEntry;
/*     */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class AbilityCore<A extends IAbility>
/*     */   extends ForgeRegistryEntry<AbilityCore<?>>
/*     */   implements Comparable<AbilityCore<?>>
/*     */ {
/*     */   private final String name;
/*     */   private final String id;
/*     */   private final AbilityCategory category;
/*     */   private final AbilityType type;
/*     */   private final IFactory<A> factory;
/*     */   private ResourceLocation icon;
/*     */   private Set<AbilityDescriptionLine> description;
/*     */   private SourceHakiNature sourceHakiNature;
/*     */   private ArrayList<SourceType> sourceTypes;
/*     */   private SourceElement sourceElement;
/*     */   private ICanUnlock unlockCheck;
/*     */   private AbilityCore<? extends ContinuousAbility>[] deps;
/*     */   private boolean isHidden = false;
/*     */   @Nullable
/*     */   private String i18nId;
/*     */   private ResourceLocation phantomKey;
/*     */   private IFormattableTextComponent localizedDisplayName;
/*     */   
/*     */   protected AbilityCore(String name, String id, AbilityCategory category, AbilityType type, IFactory<A> factory) {
/*  49 */     this.name = name;
/*  50 */     this.id = id;
/*  51 */     this.category = category;
/*  52 */     this.type = type;
/*  53 */     this.factory = factory;
/*     */   }
/*     */   
/*     */   public String getUnlocalizedName() {
/*  57 */     return this.name;
/*     */   }
/*     */   
/*     */   public String getId() {
/*  61 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IFormattableTextComponent getLocalizedName() {
/*  69 */     String id = getLocalizationId();
/*  70 */     if (Strings.isNullOrEmpty(id)) {
/*  71 */       return (IFormattableTextComponent)new StringTextComponent(getUnlocalizedName());
/*     */     }
/*     */ 
/*     */     
/*  75 */     return (IFormattableTextComponent)new TranslationTextComponent(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLocalizationId() {
/*  80 */     if (this.i18nId == null) {
/*  81 */       ResourceLocation key = ModRegistries.ABILITIES.getKey((IForgeRegistryEntry)this);
/*  82 */       if (key == null && this.phantomKey != null) {
/*  83 */         key = this.phantomKey;
/*     */       }
/*     */       
/*  86 */       if (key != null) {
/*  87 */         this.i18nId = Util.func_200697_a("ability", key);
/*     */       }
/*     */     } 
/*  90 */     return this.i18nId;
/*     */   }
/*     */   
/*     */   public AbilityCategory getCategory() {
/*  94 */     return this.category;
/*     */   }
/*     */   
/*     */   public AbilityType getType() {
/*  98 */     return this.type;
/*     */   }
/*     */   
/*     */   public IFactory<? extends A> getFactory() {
/* 102 */     return this.factory;
/*     */   }
/*     */   
/*     */   private void setUnlockCheck(ICanUnlock check) {
/* 106 */     this.unlockCheck = check;
/*     */   }
/*     */   
/*     */   public boolean canUnlock(LivingEntity entity) {
/* 110 */     if (this.unlockCheck == null) {
/* 111 */       return false;
/*     */     }
/* 113 */     return this.unlockCheck.canUnlock(entity);
/*     */   }
/*     */   
/*     */   public boolean hasUnlockCheck() {
/* 117 */     return (this.unlockCheck != null);
/*     */   }
/*     */   
/*     */   private void setSourceHakiNature(SourceHakiNature sourceHakiNature) {
/* 121 */     this.sourceHakiNature = sourceHakiNature;
/*     */   }
/*     */   
/*     */   public SourceHakiNature getSourceHakiNature() {
/* 125 */     return this.sourceHakiNature;
/*     */   }
/*     */   
/*     */   private void setSourceTypes(SourceType... sourceTypes) {
/* 129 */     this.sourceTypes = new ArrayList<>(Arrays.asList(sourceTypes));
/*     */   }
/*     */   
/*     */   public ArrayList<SourceType> getSourceTypes() {
/* 133 */     return new ArrayList<>(this.sourceTypes);
/*     */   }
/*     */   
/*     */   public boolean hasType(SourceType type) {
/* 137 */     boolean flag = false;
/* 138 */     for (SourceType t : this.sourceTypes) {
/* 139 */       if (t.equals(type)) {
/* 140 */         flag = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 144 */     return flag;
/*     */   }
/*     */   
/*     */   private void setSourceElement(SourceElement sourceElement) {
/* 148 */     this.sourceElement = sourceElement;
/*     */   }
/*     */   
/*     */   public SourceElement getSourceElement() {
/* 152 */     return this.sourceElement;
/*     */   }
/*     */   
/*     */   public void setDescription(Set<AbilityDescriptionLine> description) {
/* 156 */     this.description = description;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Set<AbilityDescriptionLine> getDescription() {
/* 161 */     return this.description;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   protected void setDependencies(AbilityCore<? extends ContinuousAbility>... deps) {
/* 166 */     this.deps = deps;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setIcon(ResourceLocation res) {
/* 176 */     this.icon = res;
/*     */   }
/*     */   
/*     */   public ResourceLocation getIcon() {
/* 180 */     return this.icon;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   @Nullable
/*     */   public AbilityCore<? extends ContinuousAbility>[] getDependencies() {
/* 186 */     return this.deps;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setHidden() {
/* 195 */     this.isHidden = true;
/*     */   }
/*     */   
/*     */   public boolean isHidden() {
/* 199 */     return this.isHidden;
/*     */   }
/*     */   
/*     */   public boolean isVisible() {
/* 203 */     return !isHidden();
/*     */   }
/*     */   
/*     */   private void setPhantomKey(ResourceLocation key) {
/* 207 */     this.phantomKey = key;
/*     */   }
/*     */   
/*     */   public A createAbility() {
/* 211 */     return this.factory.create(this);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static <A extends IAbility> AbilityCore<?> get(ResourceLocation res) {
/* 216 */     AbilityCore<?> core = (AbilityCore)ModRegistries.ABILITIES.getValue(res);
/* 217 */     return core;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ResourceLocation getKey() {
/* 222 */     return ModRegistries.ABILITIES.getKey((IForgeRegistryEntry)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 227 */     return getUnlocalizedName();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object other) {
/* 232 */     if (!(other instanceof AbilityCore)) {
/* 233 */       return false;
/*     */     }
/*     */     
/* 236 */     AbilityCore otherCore = (AbilityCore)other;
/*     */     
/* 238 */     if (getRegistryName() == null || otherCore.getRegistryName() == null) {
/* 239 */       return false;
/*     */     }
/*     */     
/* 242 */     if (getRegistryName().equals(otherCore.getRegistryName())) {
/* 243 */       return true;
/*     */     }
/*     */     
/* 246 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(AbilityCore<?> other) {
/* 251 */     if (other == null) {
/* 252 */       return 1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 258 */     String id = getLocalizedName().getString();
/* 259 */     String oid = other.getLocalizedName().getString();
/*     */     
/* 261 */     return id.compareToIgnoreCase(oid);
/*     */   }
/*     */   
/*     */   public static class Builder<A extends IAbility> {
/*     */     private final String name;
/*     */     private final String id;
/*     */     private final AbilityCategory category;
/*     */     private final AbilityType type;
/*     */     private final AbilityCore.IFactory<A> factory;
/* 270 */     private Set<AbilityDescriptionLine> descriptionLines = new LinkedHashSet<>();
/* 271 */     private SourceHakiNature sourceHakiNature = SourceHakiNature.UNKNOWN;
/* 272 */     private SourceType[] sourceTypes = new SourceType[] { SourceType.UNKNOWN };
/* 273 */     private SourceElement sourceElement = SourceElement.NONE;
/*     */     private AbilityCore.ICanUnlock unlockCheck;
/*     */     private AbilityCore<? extends ContinuousAbility>[] deps;
/*     */     private boolean isHidden = false;
/* 277 */     private Set<Consumer<Builder<A>>> consumerExtensions = new LinkedHashSet<>();
/*     */     private ResourceLocation icon;
/*     */     private ResourceLocation phantomKey;
/*     */     
/*     */     public Builder(String name, AbilityCategory category, AbilityCore.IFactory<A> factory) {
/* 282 */       this(name, category, AbilityType.ACTION, factory);
/*     */     }
/*     */     
/*     */     public Builder(String name, AbilityCategory category, AbilityType type, AbilityCore.IFactory<A> factory) {
/* 286 */       this.name = name;
/* 287 */       this.id = WyHelper.getResourceName(name);
/* 288 */       this.category = category;
/* 289 */       this.type = type;
/* 290 */       this.factory = factory;
/*     */     }
/*     */     
/*     */     public Builder(String name, String id, AbilityCategory category, AbilityType type, AbilityCore.IFactory<A> factory) {
/* 294 */       this.name = name;
/* 295 */       this.id = id;
/* 296 */       this.category = category;
/* 297 */       this.type = type;
/* 298 */       this.factory = factory;
/*     */     }
/*     */     
/*     */     public Builder<A> setUnlockCheck(AbilityCore.ICanUnlock unlockCheck) {
/* 302 */       this.unlockCheck = unlockCheck;
/* 303 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<A> setSourceHakiNature(SourceHakiNature hakiNature) {
/* 307 */       this.sourceHakiNature = hakiNature;
/* 308 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<A> setSourceType(SourceType... sourceTypes) {
/* 312 */       this.sourceTypes = sourceTypes;
/* 313 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<A> setSourceElement(SourceElement sourceElement) {
/* 317 */       this.sourceElement = sourceElement;
/* 318 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<A> setIcon(ResourceLocation icon) {
/* 322 */       this.icon = icon;
/* 323 */       return this;
/*     */     }
/*     */     
/*     */     @Deprecated
/*     */     public Builder<A> addDescriptionLine(String desc, Object... args) {
/* 328 */       this.descriptionLines.add(AbilityDescriptionLine.of((ITextComponent)new TranslationTextComponent(desc, args), false));
/* 329 */       return this;
/*     */     }
/*     */     
/*     */     @Deprecated
/*     */     public Builder<A> addExtension(Consumer<Builder<A>> consumer) {
/* 334 */       this.consumerExtensions.add(consumer);
/* 335 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<A> addDescriptionLine(AbilityDescriptionLine... lines) {
/* 339 */       Collections.addAll(this.descriptionLines, lines);
/* 340 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<A> addDescriptionLine(AbilityDescriptionLine.IDescriptionLine... lines) {
/* 344 */       for (AbilityDescriptionLine.IDescriptionLine line : lines) {
/* 345 */         this.descriptionLines.add(AbilityDescriptionLine.of(line, false));
/*     */       }
/* 347 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<A> addAdvancedDescriptionLine(AbilityDescriptionLine.IDescriptionLine... lines) {
/* 351 */       for (AbilityDescriptionLine.IDescriptionLine line : lines) {
/* 352 */         this.descriptionLines.add(AbilityDescriptionLine.of(line, true));
/*     */       }
/* 354 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<A> addDescriptionLine(ITextComponent... components) {
/* 358 */       for (ITextComponent comp : components) {
/* 359 */         this.descriptionLines.add(AbilityDescriptionLine.of(comp, false));
/*     */       }
/* 361 */       return this;
/*     */     }
/*     */     
/*     */     @Deprecated
/*     */     public Builder<A> setDependencies(AbilityCore<? extends ContinuousAbility>... deps) {
/* 366 */       this.deps = deps;
/* 367 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<A> setHidden() {
/* 371 */       this.isHidden = true;
/* 372 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder<A> setPhantomKey(ResourceLocation key) {
/* 381 */       this.phantomKey = key;
/* 382 */       return this;
/*     */     }
/*     */     
/*     */     public AbilityCore<A> build() {
/* 386 */       AbilityCore<A> core = new AbilityCore<>(this.name, this.id, this.category, this.type, this.factory);
/*     */       
/* 388 */       for (Consumer<Builder<A>> c : this.consumerExtensions) {
/* 389 */         c.accept(this);
/*     */       }
/*     */       
/* 392 */       Set<AbilityDescriptionLine> set = new LinkedHashSet<>();
/* 393 */       for (AbilityDescriptionLine line : this.descriptionLines) {
/* 394 */         set.add(line);
/*     */       }
/* 396 */       core.setDescription(set);
/*     */       
/* 398 */       core.setUnlockCheck(this.unlockCheck);
/* 399 */       core.setSourceHakiNature(this.sourceHakiNature);
/* 400 */       core.setSourceTypes(this.sourceTypes);
/* 401 */       core.setSourceElement(this.sourceElement);
/* 402 */       core.setDependencies(this.deps);
/*     */       
/* 404 */       core.setIcon(this.icon);
/* 405 */       core.setPhantomKey(this.phantomKey);
/*     */       
/* 407 */       if (this.isHidden) {
/* 408 */         core.setHidden();
/*     */       }
/*     */       
/* 411 */       return core;
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface IFactory<A extends IAbility>
/*     */   {
/*     */     A create(AbilityCore<A> param1AbilityCore);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface ICanUnlock
/*     */   {
/*     */     default ICanUnlock and(ICanUnlock check) {
/* 424 */       return entity -> (canUnlock(entity) && check.canUnlock(entity));
/*     */     }
/*     */     
/*     */     default ICanUnlock or(ICanUnlock check) {
/* 428 */       return entity -> (canUnlock(entity) || check.canUnlock(entity));
/*     */     }
/*     */     
/*     */     boolean canUnlock(LivingEntity param1LivingEntity);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\AbilityCore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */