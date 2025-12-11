/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.block.AbstractBlock;
/*     */ import net.minecraft.block.AirBlock;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.material.MaterialColor;
/*     */ import net.minecraftforge.common.ToolType;
/*     */ import net.minecraftforge.eventbus.api.IEventBus;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.DialBlock;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModBlocks
/*     */ {
/*  63 */   public static final Material DARKNESS_MATERIAL = (new Material.Builder(MaterialColor.field_151646_E)).func_200506_i();
/*  64 */   public static final Material KAIROSEKI_MATERIAL = (new Material.Builder(MaterialColor.field_151670_w)).func_200506_i();
/*     */ 
/*     */   
/*  67 */   public static final RegistryObject<Block> OPE = WyRegistry.registerBlock("Ope", xyz.pixelatedw.mineminenomi.blocks.OpeBlock::new);
/*  68 */   public static final RegistryObject<Block> SUNA_SAND = WyRegistry.registerBlock("Suna Sand", xyz.pixelatedw.mineminenomi.blocks.traps.SunaSandBlock::new);
/*  69 */   public static final RegistryObject<Block> WAX = WyRegistry.registerBlock("Wax Block", xyz.pixelatedw.mineminenomi.blocks.WaxBlock::new);
/*  70 */   public static final RegistryObject<Block> CANDY = WyRegistry.registerBlock("Candy Block", xyz.pixelatedw.mineminenomi.blocks.CandyBlock::new);
/*  71 */   public static final RegistryObject<Block> POISON = WyRegistry.registerBlock("Poison", xyz.pixelatedw.mineminenomi.blocks.PoisonBlock::new);
/*  72 */   public static final RegistryObject<Block> DEMON_POISON = WyRegistry.registerBlock("Demon Poison", xyz.pixelatedw.mineminenomi.blocks.DemonPoisonBlock::new);
/*  73 */   public static final RegistryObject<Block> STRING_WALL = WyRegistry.registerBlock("String Wall", xyz.pixelatedw.mineminenomi.blocks.StringWallBlock::new);
/*  74 */   public static final RegistryObject<Block> BARRIER = WyRegistry.registerBlock("Barrier", xyz.pixelatedw.mineminenomi.blocks.BarrierBlock::new);
/*  75 */   public static final RegistryObject<Block> DARKNESS = WyRegistry.registerBlock("Darkness", xyz.pixelatedw.mineminenomi.blocks.traps.DarknessBlock::new);
/*  76 */   public static final RegistryObject<Block> ORI_BARS = WyRegistry.registerBlock("Ori Bars", xyz.pixelatedw.mineminenomi.blocks.OriBarsBlock::new);
/*  77 */   public static final RegistryObject<Block> MUCUS = WyRegistry.registerBlock("Mucus", xyz.pixelatedw.mineminenomi.blocks.MucusBlock::new);
/*  78 */   public static final RegistryObject<Block> SPONGE_CAKE = WyRegistry.registerBlock("Sponge Cake", xyz.pixelatedw.mineminenomi.blocks.SpongeCakeBlock::new);
/*  79 */   public static final RegistryObject<Block> HARDENED_SNOW = WyRegistry.registerBlock("Hardened Snow", xyz.pixelatedw.mineminenomi.blocks.HardenedSnowBlock::new);
/*     */ 
/*     */   
/*  82 */   public static final RegistryObject<Block> KAIROSEKI = WyRegistry.registerBlock("Kairoseki Block", () -> new Block(AbstractBlock.Properties.func_200945_a(KAIROSEKI_MATERIAL).func_200948_a(50.0F, 600.0F).harvestTool(ToolType.PICKAXE)), ModCreativeTabs.MISC);
/*  83 */   public static final RegistryObject<Block> KAIROSEKI_ORE = WyRegistry.registerBlock("Kairoseki Ore", xyz.pixelatedw.mineminenomi.blocks.KairosekiOreBlock::new, ModCreativeTabs.MISC);
/*  84 */   public static final RegistryObject<Block> KAIROSEKI_BARS = WyRegistry.registerBlock("Kairoseki Bars", xyz.pixelatedw.mineminenomi.blocks.CustomBarsBlock::new, ModCreativeTabs.MISC);
/*     */ 
/*     */   
/*  87 */   public static final RegistryObject<Block> SKY_BLOCK = WyRegistry.registerBlock("Sky Block", xyz.pixelatedw.mineminenomi.blocks.SkyBlockBlock::new, ModCreativeTabs.MISC);
/*  88 */   public static final RegistryObject<Block> WANTED_POSTER = WyRegistry.registerBlock("Wanted Poster", xyz.pixelatedw.mineminenomi.blocks.WantedPosterBlock::new, xyz.pixelatedw.mineminenomi.items.WantedPosterItem::new);
/*  89 */   public static final RegistryObject<Block> WANTED_POSTER_PACKAGE = WyRegistry.registerBlock("Wanted Poster Package", xyz.pixelatedw.mineminenomi.blocks.WantedPosterPackageBlock::new, ModCreativeTabs.MISC);
/*  90 */   public static final RegistryObject<Block> CUSTOM_SPAWNER = WyRegistry.registerBlock("Custom Spawner", xyz.pixelatedw.mineminenomi.blocks.CustomSpawnerBlock::new);
/*  91 */   public static final RegistryObject<Block> DEN_DEN_MUSHI = WyRegistry.registerBlock("Den Den Mushi", xyz.pixelatedw.mineminenomi.blocks.DenDenMushiBlock::new, ModCreativeTabs.MISC);
/*     */   @Deprecated
/*  93 */   public static final RegistryObject<Block> CANNON = WyRegistry.registerBlock("Cannon", xyz.pixelatedw.mineminenomi.blocks.CannonBlock::new, xyz.pixelatedw.mineminenomi.items.CannonItem::new);
/*  94 */   public static final RegistryObject<Block> OIL_SPILL = WyRegistry.registerBlock("Oil Spill", xyz.pixelatedw.mineminenomi.blocks.OilSpillBlock::new);
/*  95 */   public static final RegistryObject<Block> DESIGN_BARREL = WyRegistry.registerBlock("Design Barrel", xyz.pixelatedw.mineminenomi.blocks.DesignBarrelBlock::new);
/*  96 */   public static final RegistryObject<Block> STRUCTURE_AIR = WyRegistry.registerBlock("Structure Air", () -> new AirBlock(AbstractBlock.Properties.func_200945_a(Material.field_151579_a).func_200942_a().func_222380_e().func_235859_g_()));
/*  97 */   public static final RegistryObject<Block> CHALLENGE_AIR = WyRegistry.registerBlock("Challenge Air", () -> new AirBlock(AbstractBlock.Properties.func_200945_a(Material.field_151579_a).func_200942_a().func_222380_e().func_235838_a_(()).func_235859_g_()));
/*  98 */   public static final RegistryObject<Block> FLAG = WyRegistry.registerBlock("Flag", xyz.pixelatedw.mineminenomi.blocks.FlagBlock::new, xyz.pixelatedw.mineminenomi.items.FlagItem::new);
/*     */ 
/*     */   
/* 101 */   public static final RegistryObject<Block> AXE_DIAL = WyRegistry.registerBlock("Axe Dial", DialBlock::new, xyz.pixelatedw.mineminenomi.items.dials.AxeDialItem::new);
/* 102 */   public static final RegistryObject<Block> IMPACT_DIAL = WyRegistry.registerBlock("Impact Dial", DialBlock::new, xyz.pixelatedw.mineminenomi.items.dials.ImpactDialItem::new);
/* 103 */   public static final RegistryObject<Block> FLASH_DIAL = WyRegistry.registerBlock("Flash Dial", () -> new DialBlock(AbstractBlock.Properties.func_200945_a(Material.field_151571_B).func_200943_b(0.2F).func_235838_a_(())), xyz.pixelatedw.mineminenomi.items.dials.FlashDialItem::new);
/* 104 */   public static final RegistryObject<Block> BREATH_DIAL = WyRegistry.registerBlock("Breath Dial", DialBlock::new, xyz.pixelatedw.mineminenomi.items.dials.BreathDialItem::new);
/* 105 */   public static final RegistryObject<Block> EISEN_DIAL = WyRegistry.registerBlock("Eisen Dial", DialBlock::new, xyz.pixelatedw.mineminenomi.items.dials.EisenDialItem::new);
/* 106 */   public static final RegistryObject<Block> MILKY_DIAL = WyRegistry.registerBlock("Milky Dial", DialBlock::new, xyz.pixelatedw.mineminenomi.items.dials.MilkyDialItem::new);
/* 107 */   public static final RegistryObject<Block> FLAME_DIAL = WyRegistry.registerBlock("Flame Dial", DialBlock::new, xyz.pixelatedw.mineminenomi.items.dials.FlameDialItem::new);
/* 108 */   public static final RegistryObject<Block> REJECT_DIAL = WyRegistry.registerBlock("Reject Dial", DialBlock::new, xyz.pixelatedw.mineminenomi.items.dials.RejectDialItem::new);
/*     */ 
/*     */ 
/*     */   
/* 112 */   public static final RegistryObject<Block> MANGROVE_LOG = WyRegistry.registerBlock("Mangrove Log", xyz.pixelatedw.mineminenomi.blocks.MangroveLogBlock::new, ModCreativeTabs.MISC);
/* 113 */   public static final RegistryObject<Block> MANGROVE_WOOD = WyRegistry.registerBlock("Mangrove Wood", xyz.pixelatedw.mineminenomi.blocks.MangroveWoodBlock::new, ModCreativeTabs.MISC);
/* 114 */   public static final RegistryObject<Block> STRIPPED_MANGROVE_LOG = WyRegistry.registerBlock("Stripped Mangrove Log", xyz.pixelatedw.mineminenomi.blocks.StrippedMangroveLog::new, ModCreativeTabs.MISC);
/* 115 */   public static final RegistryObject<Block> STRIPPED_MANGROVE_WOOD = WyRegistry.registerBlock("Stripped Mangrove Wood", xyz.pixelatedw.mineminenomi.blocks.StrippedMangroveWood::new, ModCreativeTabs.MISC);
/* 116 */   public static final RegistryObject<Block> MANGROVE_LEAVES = WyRegistry.registerBlock("Mangrove Leaves", xyz.pixelatedw.mineminenomi.blocks.MangroveLeavesBlock::new, ModCreativeTabs.MISC);
/* 117 */   public static final RegistryObject<Block> MANGROVE_SAPLING = WyRegistry.registerBlock("Mangrove Sapling", xyz.pixelatedw.mineminenomi.blocks.MangroveSaplingBlock::new, ModCreativeTabs.MISC);
/* 118 */   public static final RegistryObject<Block> MANGROVE_PLANKS = WyRegistry.registerBlock("Mangrove Planks", xyz.pixelatedw.mineminenomi.blocks.MangrovePlanksBlock::new, ModCreativeTabs.MISC);
/*     */   
/* 120 */   public static final RegistryObject<Block> MOSS = WyRegistry.registerBlock("Moss", xyz.pixelatedw.mineminenomi.blocks.MossBlock::new, ModCreativeTabs.MISC);
/*     */ 
/*     */   
/* 123 */   public static final RegistryObject<Block> PONEGLYPH = WyRegistry.registerBlock("Poneglyph", xyz.pixelatedw.mineminenomi.blocks.PoneglyphBlock::new, ModCreativeTabs.MISC);
/*     */ 
/*     */   
/* 126 */   public static final RegistryObject<Block> TANGERINE_CROP = WyRegistry.registerBlockOnly("Tangerine Crop", xyz.pixelatedw.mineminenomi.blocks.TangerineCropsBlock::new);
/*     */   
/*     */   public static void register(IEventBus eventBus) {
/* 129 */     WyRegistry.BLOCKS.register(eventBus);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModBlocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */