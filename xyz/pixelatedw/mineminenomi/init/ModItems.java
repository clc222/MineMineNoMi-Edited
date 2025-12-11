/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraftforge.eventbus.api.IEventBus;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiBoxItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.CigarItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.HandcuffsItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.NetItem;
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
/*     */ public class ModItems
/*     */ {
/*  40 */   public static final RegistryObject<Item> CHARACTER_CREATOR = WyRegistry.registerItem("Character Creator", xyz.pixelatedw.mineminenomi.items.CharacterCreatorItem::new);
/*  41 */   public static final RegistryObject<Item> KAIROSEKI = WyRegistry.registerItem("Kairoseki", () -> new Item((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC)));
/*  42 */   public static final RegistryObject<Item> DENSE_KAIROSEKI = WyRegistry.registerItem("Dense Kairoseki", () -> new Item((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC)));
/*  43 */   public static final RegistryObject<Item> BELLY_POUCH = WyRegistry.registerItem("Belly Pouch", xyz.pixelatedw.mineminenomi.items.BellyPouchItem::new);
/*  44 */   public static final RegistryObject<Item> EXTOL_POUCH = WyRegistry.registerItem("Extol Pouch", xyz.pixelatedw.mineminenomi.items.ExtolPouchItem::new);
/*  45 */   public static final RegistryObject<Item> KEY = WyRegistry.registerItem("Key", () -> new Item((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC)));
/*  46 */   public static final RegistryObject<Item> VIVRE_CARD = WyRegistry.registerItem("Vivre Card", xyz.pixelatedw.mineminenomi.items.VivreCardItem::new);
/*  47 */   public static final RegistryObject<Item> BUBBLY_CORAL = WyRegistry.registerItem("Bubbly Coral", xyz.pixelatedw.mineminenomi.items.BubblyCoralItem::new);
/*  48 */   public static final RegistryObject<Item> GOLD_DEN_DEN_MUSHI = WyRegistry.registerItem("Golden Den Den Mushi", xyz.pixelatedw.mineminenomi.items.GoldDenDenMushiItem::new);
/*  49 */   public static final RegistryObject<Item> NORMAL_HANDCUFFS = WyRegistry.registerItem("Handcuffs", () -> new HandcuffsItem((Supplier)ModEffects.HANDCUFFED));
/*  50 */   public static final RegistryObject<Item> KAIROSEKI_HANDCUFFS = WyRegistry.registerItem("Kairoseki Handcuffs", () -> new HandcuffsItem((Supplier)ModEffects.HANDCUFFED_KAIROSEKI));
/*  51 */   public static final RegistryObject<Item> EXPLOSIVE_HANDCUFFS = WyRegistry.registerItem("Explosive Handcuffs", () -> new HandcuffsItem((Supplier)ModEffects.HANDCUFFED_EXPLOSIVE));
/*  52 */   public static final RegistryObject<Item> DEVIL_FRUIT_ENCYCLOPEDIA = WyRegistry.registerItem("Devil Fruit Encyclopedia", xyz.pixelatedw.mineminenomi.items.DFEncyclopediaItem::new);
/*  53 */   public static final RegistryObject<Item> STRIKER = WyRegistry.registerItem("Striker", xyz.pixelatedw.mineminenomi.items.StrikerItem::new);
/*  54 */   public static final RegistryObject<Item> UNICYCLE = WyRegistry.registerItem("Unicycle", xyz.pixelatedw.mineminenomi.items.UnicycleItem::new);
/*  55 */   public static final RegistryObject<Item> BON_CHARI = WyRegistry.registerItem("Bon Chari", xyz.pixelatedw.mineminenomi.items.BonChariItem::new);
/*  56 */   public static final RegistryObject<Item> CHALLENGE_POSTER = WyRegistry.registerItem("Challenge Poster", xyz.pixelatedw.mineminenomi.items.ChallengePosterItem::new);
/*  57 */   public static final RegistryObject<NetItem> ROPE_NET = WyRegistry.registerItem("Rope Net", () -> new NetItem((Supplier)ModEffects.CAUGHT_IN_NET));
/*  58 */   public static final RegistryObject<NetItem> KAIROSEKI_NET = WyRegistry.registerItem("Kairoseki Net", () -> new NetItem((Supplier)ModEffects.CAUGHT_IN_KAIROSEKI_NET));
/*     */ 
/*     */   
/*  61 */   public static final RegistryObject<Item> SHADOW = WyRegistry.registerItem("Shadow", xyz.pixelatedw.mineminenomi.items.ShadowItem::new);
/*  62 */   public static final RegistryObject<Item> DANDELION = WyRegistry.registerItem("Dandelion", xyz.pixelatedw.mineminenomi.items.DandelionItem::new);
/*  63 */   public static final RegistryObject<Item> HEART = WyRegistry.registerItem("Heart", xyz.pixelatedw.mineminenomi.items.HeartItem::new);
/*  64 */   public static final RegistryObject<Item> COLOR_PALETTE = WyRegistry.registerItem("Color Palette", () -> new Item((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200917_a(1)));
/*  65 */   public static final RegistryObject<Item> WATERING_CAN = WyRegistry.registerItem("Watering Can", xyz.pixelatedw.mineminenomi.items.WateringCanItem::new);
/*  66 */   public static final RegistryObject<Item> STRAW_DOLL = WyRegistry.registerItem("Straw Doll", xyz.pixelatedw.mineminenomi.items.StrawDollItem::new);
/*  67 */   public static final RegistryObject<Item> PAIN = WyRegistry.registerItem("Pain", xyz.pixelatedw.mineminenomi.items.PainItem::new);
/*     */ 
/*     */   
/*  70 */   public static final RegistryObject<AkumaNoMiBoxItem> TIER_1_BOX = WyRegistry.registerItem("Wooden Box", () -> new AkumaNoMiBoxItem(AkumaNoMiBoxItem.TIER_1_FRUITS));
/*  71 */   public static final RegistryObject<AkumaNoMiBoxItem> TIER_2_BOX = WyRegistry.registerItem("Iron Box", () -> new AkumaNoMiBoxItem(AkumaNoMiBoxItem.TIER_2_FRUITS));
/*  72 */   public static final RegistryObject<AkumaNoMiBoxItem> TIER_3_BOX = WyRegistry.registerItem("Golden Box", () -> new AkumaNoMiBoxItem(AkumaNoMiBoxItem.TIER_3_FRUITS));
/*     */ 
/*     */   
/*  75 */   public static final RegistryObject<Item> SEA_KING_MEAT = WyRegistry.registerItem("Sea King Meat", xyz.pixelatedw.mineminenomi.items.SeaKingMeatItem::new);
/*  76 */   public static final RegistryObject<Item> COOKED_SEA_KING_MEAT = WyRegistry.registerItem("Cooked Sea King Meat", xyz.pixelatedw.mineminenomi.items.CookedSeaKingMeatItem::new);
/*  77 */   public static final RegistryObject<Item> EMPTY_COLA = WyRegistry.registerItem("Empty Cola", () -> new Item((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200917_a(16)));
/*  78 */   public static final RegistryObject<Item> EMPTY_ULTRA_COLA = WyRegistry.registerItem("Empty Ultra Cola", () -> new Item((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200917_a(16)));
/*  79 */   public static final RegistryObject<Item> COLA = WyRegistry.registerItem("Cola", xyz.pixelatedw.mineminenomi.items.ColaItem::new);
/*  80 */   public static final RegistryObject<Item> ULTRA_COLA = WyRegistry.registerItem("Ultra Cola", xyz.pixelatedw.mineminenomi.items.UltraColaItem::new);
/*  81 */   public static final RegistryObject<Item> SAKE_BOTTLE = WyRegistry.registerItem("Sake Bottle", xyz.pixelatedw.mineminenomi.items.SakeBottleItem::new);
/*  82 */   public static final RegistryObject<Item> SAKE_CUP = WyRegistry.registerItem("Sake Cup", xyz.pixelatedw.mineminenomi.items.SakeCupItem::new);
/*  83 */   public static final RegistryObject<Item> BOTTLE_OF_RUM = WyRegistry.registerItem("Bottle of Rum", xyz.pixelatedw.mineminenomi.items.BottleOfRumItem::new);
/*  84 */   public static final RegistryObject<Item> TANGERINE = WyRegistry.registerItem("Tangerine", xyz.pixelatedw.mineminenomi.items.TangerineItem::new);
/*     */ 
/*     */   
/*  87 */   public static final RegistryObject<Item> THREE_CIGARS = WyRegistry.registerItem("Three Cigars", () -> new CigarItem(10));
/*  88 */   public static final RegistryObject<Item> CIGARETTE = WyRegistry.registerItem("Cigarette", () -> new CigarItem(15));
/*  89 */   public static final RegistryObject<Item> SMOKING_PIPE = WyRegistry.registerItem("Smoking Pipe", () -> new CigarItem(15));
/*  90 */   public static final RegistryObject<Item> CIGAR = WyRegistry.registerItem("Cigar", () -> new CigarItem(10));
/*     */ 
/*     */   
/*  93 */   public static final RegistryObject<Item> BULLET = WyRegistry.registerItem("Bullet", () -> new Item((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC)));
/*  94 */   public static final RegistryObject<Item> KAIROSEKI_BULLET = WyRegistry.registerItem("Kairoseki Bullet", () -> new Item((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC)));
/*  95 */   public static final RegistryObject<Item> KUJA_ARROW = WyRegistry.registerItem("Kuja Arrow", () -> new Item((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC)));
/*  96 */   public static final RegistryObject<Item> POP_GREEN = WyRegistry.registerItem("Pop Green", () -> new Item((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public static final RegistryObject<Item> CANNON_BALL = WyRegistry.registerItem("Cannon Ball", () -> new Item((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC)));
/*     */   
/*     */   public static void register(IEventBus eventBus) {
/* 107 */     WyRegistry.ITEMS.register(eventBus);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModItems.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */