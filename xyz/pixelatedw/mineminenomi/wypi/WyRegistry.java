/*     */ package xyz.pixelatedw.mineminenomi.wypi;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.entity.EntityClassification;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.inventory.container.ContainerType;
/*     */ import net.minecraft.item.BlockItem;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemGroup;
/*     */ import net.minecraft.particles.ParticleType;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityType;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.gen.feature.Feature;
/*     */ import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
/*     */ import net.minecraftforge.common.ForgeSpawnEggItem;
/*     */ import net.minecraftforge.common.extensions.IForgeContainerType;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ import net.minecraftforge.fml.network.IContainerFactory;
/*     */ import net.minecraftforge.registries.DeferredRegister;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.charactercreator.FactionId;
/*     */ import xyz.pixelatedw.mineminenomi.api.charactercreator.RaceId;
/*     */ import xyz.pixelatedw.mineminenomi.api.charactercreator.StyleId;
/*     */ import xyz.pixelatedw.mineminenomi.api.interactions.Interaction;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WyRegistry
/*     */ {
/*  54 */   private static HashMap<String, String> langMap = new HashMap<>();
/*     */ 
/*     */   
/*     */   public static HashMap<String, String> getLangMap() {
/*  58 */     return langMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "mineminenomi");
/*  65 */   public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "mineminenomi");
/*  66 */   public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, "mineminenomi");
/*  67 */   public static final DeferredRegister<AbilityCore<?>> ABILITIES = DeferredRegister.create(ModRegistries.ABILITIES, "mineminenomi");
/*  68 */   public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, "mineminenomi");
/*  69 */   public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, "mineminenomi");
/*  70 */   public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, "mineminenomi");
/*  71 */   public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, "mineminenomi");
/*  72 */   public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, "mineminenomi");
/*  73 */   public static final DeferredRegister<QuestId<?>> QUESTS = DeferredRegister.create(ModRegistries.QUESTS, "mineminenomi");
/*  74 */   public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, "mineminenomi");
/*  75 */   public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, "mineminenomi");
/*  76 */   public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "mineminenomi");
/*  77 */   public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, "mineminenomi");
/*  78 */   public static final DeferredRegister<ChallengeCore<?>> CHALLENGES = DeferredRegister.create(ModRegistries.CHALLENGES, "mineminenomi");
/*  79 */   public static final DeferredRegister<ParticleEffect<?>> PARTICLE_EFFECTS = DeferredRegister.create(ModRegistries.PARTICLE_EFFECTS, "mineminenomi");
/*  80 */   public static final DeferredRegister<MorphInfo> MORPHS = DeferredRegister.create(ModRegistries.MORPHS, "mineminenomi");
/*  81 */   public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, "mineminenomi");
/*  82 */   public static final DeferredRegister<StyleId> STYLES = DeferredRegister.create(StyleId.class, "mineminenomi");
/*  83 */   public static final DeferredRegister<RaceId> RACES = DeferredRegister.create(RaceId.class, "mineminenomi");
/*  84 */   public static final DeferredRegister<FactionId> FACTIONS = DeferredRegister.create(FactionId.class, "mineminenomi");
/*  85 */   public static final DeferredRegister<Interaction> INTERACTIONS = DeferredRegister.create(ModRegistries.INTERACTIONS, "mineminenomi");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String registerName(String key, String localizedName) {
/*  93 */     getLangMap().put(key, localizedName);
/*  94 */     return key;
/*     */   }
/*     */   
/*     */   public static TranslationTextComponent registerTextComponent(String key, String localizedName) {
/*  98 */     return new TranslationTextComponent(registerName(key, localizedName));
/*     */   }
/*     */   
/*     */   public static <T extends MorphInfo> RegistryObject<T> registerMorph(String resourceName, Supplier<T> morph) {
/* 102 */     RegistryObject<T> reg = MORPHS.register(resourceName, morph);
/* 103 */     return reg;
/*     */   }
/*     */   
/*     */   public static <T extends Interaction> RegistryObject<T> registerInteraction(String resourceName, Supplier<T> morph) {
/* 107 */     RegistryObject<T> reg = INTERACTIONS.register(resourceName, morph);
/* 108 */     return reg;
/*     */   }
/*     */   
/*     */   public static <T extends Biome> RegistryObject<T> registerBiome(String localizedName, Supplier<T> biome) {
/* 112 */     String resourceName = WyHelper.getResourceName(localizedName);
/* 113 */     getLangMap().put("biome.mineminenomi." + resourceName, localizedName);
/*     */     
/* 115 */     RegistryObject<T> reg = BIOMES.register(resourceName, biome);
/*     */     
/* 117 */     return reg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends ParticleEffect<?>> RegistryObject<T> registerParticleEffect(String localizedName, Supplier<T> attr) {
/* 122 */     String resourceName = WyHelper.getResourceName(localizedName);
/*     */ 
/*     */     
/* 125 */     RegistryObject<T> reg = PARTICLE_EFFECTS.register("particle_effect.mineminenomi." + resourceName, attr);
/*     */     
/* 127 */     return reg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static RegistryObject<Attribute> registerAttribute(String localizedName, Supplier<Attribute> attr) {
/* 132 */     String resourceName = WyHelper.getResourceName(localizedName);
/* 133 */     getLangMap().put("attribute.name.generic.mineminenomi." + resourceName, localizedName);
/*     */     
/* 135 */     RegistryObject<Attribute> reg = ATTRIBUTES.register("generic." + resourceName, attr);
/*     */     
/* 137 */     return reg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends ParticleType<?>> RegistryObject<T> registerParticleType(String localizedName, Supplier<T> type) {
/* 142 */     String resourceName = WyHelper.getResourceName(localizedName);
/* 143 */     RegistryObject<T> reg = PARTICLE_TYPES.register(resourceName, type);
/*     */     
/* 145 */     return reg;
/*     */   }
/*     */   
/*     */   public static <T extends Feature<?>> RegistryObject<T> registerFeature(String localizedName, Supplier<T> feature) {
/* 149 */     String resourceName = WyHelper.getResourceName(localizedName);
/*     */     
/* 151 */     RegistryObject<T> reg = FEATURES.register(resourceName, feature);
/*     */     
/* 153 */     return reg;
/*     */   }
/*     */   
/*     */   public static <T extends FoliagePlacerType<?>> RegistryObject<T> registerFoliagePlacer(String localizedName, Supplier<T> feature) {
/* 157 */     String resourceName = WyHelper.getResourceName(localizedName);
/*     */     
/* 159 */     RegistryObject<T> reg = FOLIAGE_PLACER_TYPES.register(resourceName, feature);
/*     */     
/* 161 */     return reg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends Effect> RegistryObject<T> registerEffect(String localizedName, Supplier<T> effect) {
/* 166 */     String resourceName = WyHelper.getResourceName(localizedName);
/* 167 */     return registerEffect(localizedName, resourceName, effect);
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends Effect> RegistryObject<T> registerEffect(String localizedName, String resourceKey, Supplier<T> effect) {
/* 172 */     String resourceName = WyHelper.getResourceName(resourceKey);
/* 173 */     getLangMap().put("effect.mineminenomi." + resourceName, localizedName);
/*     */     
/* 175 */     RegistryObject<T> reg = EFFECTS.register(resourceName, effect);
/*     */     
/* 177 */     return reg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends Enchantment> RegistryObject<T> registerEnchantment(String localizedName, Supplier<T> enchantment) {
/* 182 */     String resourceName = WyHelper.getResourceName(localizedName);
/* 183 */     getLangMap().put("enchantment.mineminenomi." + resourceName, localizedName);
/*     */     
/* 185 */     RegistryObject<T> reg = ENCHANTMENTS.register(resourceName, enchantment);
/*     */     
/* 187 */     return reg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends xyz.pixelatedw.mineminenomi.api.quests.Quest> QuestId<T> registerQuest(QuestId<T> quest) {
/* 192 */     String resourceName = WyHelper.getResourceName(quest.getName());
/* 193 */     getLangMap().put("quest.mineminenomi." + resourceName, quest.getName());
/*     */     
/* 195 */     ResourceLocation key = new ResourceLocation("mineminenomi", resourceName);
/* 196 */     RegistryObject<QuestId<?>> ret = RegistryObject.of(key, ModRegistries.QUESTS);
/* 197 */     if (!QUESTS.getEntries().contains(ret))
/*     */     {
/* 199 */       QUESTS.register(resourceName, () -> quest);
/*     */     }
/*     */     
/* 202 */     return quest;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends xyz.pixelatedw.mineminenomi.api.abilities.IAbility> AbilityCore<T> registerAbility(AbilityCore<T> core) {
/* 207 */     String resourceName = WyHelper.getResourceName(core.getId());
/* 208 */     ResourceLocation key = new ResourceLocation("mineminenomi", resourceName);
/* 209 */     getLangMap().put("ability.mineminenomi." + resourceName, core.getUnlocalizedName());
/*     */     
/* 211 */     RegistryObject<AbilityCore<?>> ret = RegistryObject.of(key, ModRegistries.ABILITIES);
/* 212 */     if (!ABILITIES.getEntries().contains(ret)) {
/* 213 */       ABILITIES.register(resourceName, () -> core);
/* 214 */       if (core.getIcon() == null) {
/* 215 */         core.setIcon(new ResourceLocation(key.func_110624_b(), "textures/abilities/" + key.func_110623_a() + ".png"));
/*     */       }
/*     */     } 
/*     */     
/* 219 */     return core;
/*     */   }
/*     */ 
/*     */   
/*     */   public static RegistryObject<SoundEvent> registerSound(String localizedName) {
/* 224 */     String resourceName = WyHelper.getResourceName(localizedName);
/*     */     
/* 226 */     getLangMap().put("mineminenomi.subtitle." + resourceName, localizedName);
/*     */     
/* 228 */     SoundEvent sound = new SoundEvent(new ResourceLocation("mineminenomi", resourceName));
/* 229 */     RegistryObject<SoundEvent> reg = SOUNDS.register(resourceName, () -> sound);
/*     */     
/* 231 */     return reg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends Item> RegistryObject<T> registerItem(String localizedName, Supplier<T> item) {
/* 236 */     String resourceName = WyHelper.getResourceName(localizedName);
/* 237 */     getLangMap().put("item.mineminenomi." + resourceName, localizedName);
/*     */     
/* 239 */     RegistryObject<T> reg = ITEMS.register(resourceName, item);
/*     */     
/* 241 */     return reg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static RegistryObject<ForgeSpawnEggItem> registerSpawnEggItem(String localizedEntityName, Supplier<ForgeSpawnEggItem> supp) {
/* 246 */     String entityResName = WyHelper.getResourceName(localizedEntityName);
/*     */     
/* 248 */     String resourceName = entityResName + "_spawn_egg";
/* 249 */     String localizedName = "Spawn " + localizedEntityName;
/*     */     
/* 251 */     getLangMap().put("item.mineminenomi." + resourceName, localizedName);
/*     */     
/* 253 */     RegistryObject<ForgeSpawnEggItem> reg = ITEMS.register(resourceName, supp);
/*     */     
/* 255 */     return reg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends Block> RegistryObject<T> registerBlock(String localizedName, Supplier<T> block) {
/* 260 */     return registerBlock(localizedName, block, (ItemGroup)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends Block> RegistryObject<T> registerBlock(String localizedName, Supplier<T> block, @Nullable ItemGroup tab) {
/* 265 */     Item.Properties blockItemProps = new Item.Properties();
/* 266 */     if (tab != null) {
/* 267 */       blockItemProps.func_200916_a(tab);
/*     */     }
/* 269 */     return registerBlock(localizedName, block, blockItemProps);
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends Block> RegistryObject<T> registerBlock(String localizedName, Supplier<T> block, Item.Properties props) {
/* 274 */     String resourceName = WyHelper.getResourceName(localizedName);
/* 275 */     getLangMap().put("block.mineminenomi." + resourceName, localizedName);
/*     */     
/* 277 */     RegistryObject<T> reg = BLOCKS.register(resourceName, block);
/*     */     
/* 279 */     registerItem(localizedName, () -> new BlockItem((Block)reg.get(), props));
/*     */     
/* 281 */     return reg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends Block> RegistryObject<T> registerBlock(String localizedName, Supplier<T> block, Function<T, BlockItem> blockItemFunc) {
/* 286 */     String resourceName = WyHelper.getResourceName(localizedName);
/* 287 */     getLangMap().put("block.mineminenomi." + resourceName, localizedName);
/*     */     
/* 289 */     RegistryObject<T> reg = BLOCKS.register(resourceName, block);
/*     */     
/* 291 */     registerItem(localizedName, () -> (BlockItem)blockItemFunc.apply(reg.get()));
/*     */     
/* 293 */     return reg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends Block> RegistryObject<T> registerBlockOnly(String localizedName, Supplier<T> block) {
/* 298 */     String resourceName = WyHelper.getResourceName(localizedName);
/* 299 */     getLangMap().put("block.mineminenomi." + resourceName, localizedName);
/*     */     
/* 301 */     RegistryObject<T> reg = BLOCKS.register(resourceName, block);
/*     */     
/* 303 */     return reg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <C extends net.minecraft.inventory.container.Container> RegistryObject<ContainerType<C>> registerContainer(String localizedName, IContainerFactory<C> containerFactory) {
/* 308 */     String resourceName = WyHelper.getResourceName(localizedName);
/* 309 */     getLangMap().put("container.mineminenomi." + resourceName, localizedName);
/*     */     
/* 311 */     RegistryObject<ContainerType<C>> reg = CONTAINER_TYPES.register(resourceName, () -> IForgeContainerType.create(containerFactory));
/* 312 */     return reg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static RegistryObject<TileEntityType<?>> registerTileEntity(String localizedName, Supplier<TileEntity> factory, Block... blocks) {
/* 317 */     String resourceName = WyHelper.getResourceName(localizedName);
/* 318 */     TileEntityType<?> type = TileEntityType.Builder.func_223042_a(factory, blocks).func_206865_a(null);
/*     */     
/* 320 */     RegistryObject<TileEntityType<?>> reg = TILE_ENTITIES.register(resourceName, () -> type);
/*     */     
/* 322 */     return reg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends net.minecraft.entity.Entity> EntityType.Builder<T> createFastEntityType(EntityType.IFactory<T> factory) {
/* 327 */     EntityType.Builder<T> builder = EntityType.Builder.func_220322_a(factory, EntityClassification.MISC);
/*     */     
/* 329 */     builder.setTrackingRange(128).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3).func_220321_a(0.6F, 1.8F);
/*     */     
/* 331 */     return builder;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends net.minecraft.entity.Entity> EntityType.Builder createEntityType(EntityType.IFactory<T> factory) {
/* 336 */     return createEntityType(factory, EntityClassification.MISC);
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends net.minecraft.entity.Entity> EntityType.Builder createEntityType(EntityType.IFactory<T> factory, EntityClassification classification) {
/* 341 */     EntityType.Builder<T> builder = EntityType.Builder.func_220322_a(factory, classification);
/*     */     
/* 343 */     builder.setTrackingRange(10).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).func_220321_a(0.6F, 1.8F);
/*     */     
/* 345 */     return builder;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends net.minecraft.entity.Entity> RegistryObject<EntityType<T>> registerEntityType(String localizedName, Supplier<EntityType<T>> supp) {
/* 350 */     String resourceName = WyHelper.getResourceName(localizedName);
/* 351 */     getLangMap().put("entity.mineminenomi." + resourceName, localizedName);
/*     */     
/* 353 */     RegistryObject<EntityType<T>> reg = ENTITY_TYPES.register(resourceName, supp);
/*     */     
/* 355 */     return reg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends net.minecraft.entity.Entity> RegistryObject<EntityType<T>> registerEntityType(String localizedName, String resourceName, Supplier<EntityType<T>> supp) {
/* 360 */     getLangMap().put("entity.mineminenomi." + resourceName, localizedName);
/*     */     
/* 362 */     RegistryObject<EntityType<T>> reg = ENTITY_TYPES.register(resourceName, supp);
/*     */     
/* 364 */     return reg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends net.minecraft.entity.Entity> void registerEntityType(EntityType<T> type, String localizedName) {
/* 369 */     String resourceName = WyHelper.getResourceName(localizedName);
/* 370 */     getLangMap().put("entity.mineminenomi." + resourceName, localizedName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <I extends RaceId> RegistryObject<I> registerRace(String localizedName, Supplier<I> race) {
/* 377 */     String resourceName = WyHelper.getResourceName(localizedName);
/* 378 */     getLangMap().put("race.mineminenomi." + resourceName, localizedName);
/*     */     
/* 380 */     RegistryObject<I> reg = RACES.register(resourceName, race);
/*     */     
/* 382 */     return reg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <I extends FactionId> RegistryObject<I> registerFaction(String localizedName, Supplier<I> faction) {
/* 387 */     String resourceName = WyHelper.getResourceName(localizedName);
/* 388 */     return registerFaction(resourceName, localizedName, faction);
/*     */   }
/*     */ 
/*     */   
/*     */   public static <I extends FactionId> RegistryObject<I> registerFaction(String id, String localizedName, Supplier<I> faction) {
/* 393 */     getLangMap().put("faction.mineminenomi." + id, localizedName);
/*     */     
/* 395 */     RegistryObject<I> reg = FACTIONS.register(id, faction);
/*     */     
/* 397 */     return reg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <I extends StyleId> RegistryObject<I> registerStyle(String localizedName, Supplier<I> style) {
/* 402 */     String resourceName = WyHelper.getResourceName(localizedName);
/* 403 */     getLangMap().put("style.mineminenomi." + resourceName, localizedName);
/*     */     
/* 405 */     RegistryObject<I> reg = STYLES.register(resourceName, style);
/*     */     
/* 407 */     return reg;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\wypi\WyRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */