/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.item.crafting.Ingredient;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraftforge.eventbus.api.IEventBus;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ import xyz.pixelatedw.mineminenomi.api.GenericArmorMaterial;
/*     */ import xyz.pixelatedw.mineminenomi.items.armors.CabajiScarfItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.armors.CaptainCapeItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.armors.FluffyCapeItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.armors.ModArmorItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.armors.ModDyeableArmorItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.armors.PearlArmorItem;
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
/*     */ public class ModArmors
/*     */ {
/*  46 */   public static final GenericArmorMaterial BASIC_ARMOR_MATERIAL = new GenericArmorMaterial("mineminenomi:basic_armor", 100, new int[] { 1, 2, 3, 1 }, 4, SoundEvents.field_187728_s, 0.0F, () -> Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151116_aA }));
/*  47 */   public static final GenericArmorMaterial CAPTAIN_CAPE_MATERIAL = new GenericArmorMaterial("mineminenomi:captain_cape", 100, new int[] { 0, 0, 4, 0 }, 12, SoundEvents.field_187728_s, 2.0F, () -> Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_221603_aE }));
/*  48 */   public static final GenericArmorMaterial COLA_BACKPACK_MATERIAL = new GenericArmorMaterial("mineminenomi:cola_backpack", 100, new int[] { 0, 0, 3, 0 }, 8, SoundEvents.field_187713_n, 0.0F, () -> Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)ModItems.COLA.get() }));
/*  49 */   public static final GenericArmorMaterial MEDIC_BAG_MATERIAL = new GenericArmorMaterial("mineminenomi:medic_bag", 100, new int[] { 0, 0, 3, 0 }, 8, SoundEvents.field_187728_s, 0.0F, () -> Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151116_aA }));
/*  50 */   public static final GenericArmorMaterial TOMOE_DRUMS_MATERIAL = new GenericArmorMaterial("mineminenomi:tomoe_drums", 100, new int[] { 0, 0, 4, 0 }, 12, SoundEvents.field_187725_r, 2.0F, () -> Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151116_aA, (IItemProvider)Items.field_151042_j, (IItemProvider)Items.field_151043_k }));
/*  51 */   public static final GenericArmorMaterial SNIPER_GOGGLES_MATERIAL = new GenericArmorMaterial("mineminenomi:sniper_goggles", 100, new int[] { 0, 0, 0, 1 }, 8, SoundEvents.field_187725_r, 2.0F, () -> Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j }));
/*  52 */   public static final GenericArmorMaterial WOOTZ_STEEL_MATERIAL = new GenericArmorMaterial("mineminenomi:wootz_steel", 100, new int[] { 0, 0, 10, 0 }, 4, SoundEvents.field_187725_r, 4.0F, () -> Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j, (IItemProvider)Items.field_151043_k }));
/*  53 */   public static final GenericArmorMaterial BANDANA_MATERIAL = new GenericArmorMaterial("mineminenomi:bandana", 100, new int[] { 1, 1, 1, 1 }, 12, SoundEvents.field_187728_s, 0.0F, () -> Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_221603_aE }));
/*  54 */   public static final GenericArmorMaterial METALIC_FACE_MATERIAL = new GenericArmorMaterial("mineminenomi:metalic_face", 100, new int[] { 0, 0, 2, 0 }, 6, SoundEvents.field_187725_r, 0.0F, () -> Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j }));
/*  55 */   public static final GenericArmorMaterial CHOPPERS_HAT_MATERIAL = new GenericArmorMaterial("mineminenomi:chopper_hat", 100, new int[] { 1, 1, 1, 1 }, 12, SoundEvents.field_187728_s, 0.0F, () -> Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_221603_aE }));
/*  56 */   public static final GenericArmorMaterial STRAW_HAT_MATERIAL = new GenericArmorMaterial("mineminenomi:strawhat", 100, new int[] { 1, 1, 1, 1 }, 12, SoundEvents.field_187728_s, 0.0F, () -> Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_221807_eN }));
/*  57 */   public static final GenericArmorMaterial ACES_HAT_MATERIAL = new GenericArmorMaterial("mineminenomi:aces_hat", 100, new int[] { 1, 1, 1, 1 }, 12, SoundEvents.field_187728_s, 0.0F, () -> Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151116_aA }));
/*  58 */   public static final GenericArmorMaterial KILLER_MASK_MATERIAL = new GenericArmorMaterial("mineminenomi:killer_mask", 100, new int[] { 1, 1, 1, 3 }, 12, SoundEvents.field_187725_r, 0.0F, () -> Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j }));
/*  59 */   public static final GenericArmorMaterial PEARL_MATERIAL = new GenericArmorMaterial("mineminenomi:pearl", 100, new int[] { 0, 5, 7, 5 }, 4, SoundEvents.field_187725_r, 5.0F, () -> Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j }));
/*  60 */   public static final GenericArmorMaterial CELESTIAL_DRAGON_BUBBLE_MATERIAL = new GenericArmorMaterial("mineminenomi:celestial_dragon_bubble", 100, new int[] { 1, 1, 1, 1 }, 12, SoundEvents.field_187722_q, 0.0F, () -> Ingredient.field_193370_a);
/*  61 */   public static final GenericArmorMaterial PLUME_HAT_MATERIAL = new GenericArmorMaterial("mineminenomi:plume_hat", 100, new int[] { 1, 1, 1, 1 }, 12, SoundEvents.field_187728_s, 0.0F, () -> Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151116_aA }));
/*  62 */   public static final GenericArmorMaterial SANDALS_MATERIAL = new GenericArmorMaterial("mineminenomi:sandals", 0, new int[] { 1, 2, 3, 1 }, 4, SoundEvents.field_187728_s, 0.0F, () -> Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151116_aA }));
/*     */   
/*  64 */   public static final GenericArmorMaterial GLASSES_MATERIAL = new GenericArmorMaterial("mineminenomi:glae", 100, new int[] { 1, 1, 1, 1 }, 12, SoundEvents.field_187713_n, 0.0F, () -> Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j }));
/*  65 */   public static final GenericArmorMaterial CLOTH_HAT_MATERIAL = new GenericArmorMaterial("mineminenomi:cloth_hat", 100, new int[] { 1, 1, 1, 1 }, 12, SoundEvents.field_187728_s, 0.0F, () -> Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151116_aA }));
/*     */ 
/*     */   
/*  68 */   public static final RegistryObject<Item> PIRATE_CHEST = WyRegistry.registerItem("Pirate Shirt", () -> new ModArmorItem("pirate", EquipmentSlotType.CHEST));
/*  69 */   public static final RegistryObject<Item> PIRATE_LEGS = WyRegistry.registerItem("Pirate Pants", () -> new ModArmorItem("pirate", EquipmentSlotType.LEGS));
/*  70 */   public static final RegistryObject<Item> PIRATE_FEET = WyRegistry.registerItem("Pirate Boots", () -> new ModArmorItem("pirate", EquipmentSlotType.FEET));
/*     */   
/*  72 */   public static final RegistryObject<Item> MARINE_HEAD = WyRegistry.registerItem("Marine Hat", () -> new ModArmorItem("marine", EquipmentSlotType.HEAD));
/*  73 */   public static final RegistryObject<Item> MARINE_CHEST = WyRegistry.registerItem("Marine Shirt", () -> new ModArmorItem("marine", EquipmentSlotType.CHEST));
/*  74 */   public static final RegistryObject<Item> MARINE_LEGS = WyRegistry.registerItem("Marine Pants", () -> new ModArmorItem("marine", EquipmentSlotType.LEGS));
/*  75 */   public static final RegistryObject<Item> MARINE_FEET = WyRegistry.registerItem("Marine Boots", () -> new ModArmorItem("marine", EquipmentSlotType.FEET));
/*     */   
/*  77 */   public static final RegistryObject<Item> STRAW_HAT = WyRegistry.registerItem("Straw Hat", xyz.pixelatedw.mineminenomi.items.armors.StrawHatItem::new);
/*  78 */   public static final RegistryObject<Item> LUFFY_CHEST = WyRegistry.registerItem("Luffy's Shirt", () -> new ModArmorItem("luffy", EquipmentSlotType.CHEST));
/*  79 */   public static final RegistryObject<Item> LUFFY_LEGS = WyRegistry.registerItem("Luffy's Pants", () -> new ModArmorItem("luffy", EquipmentSlotType.LEGS));
/*  80 */   public static final RegistryObject<Item> LUFFY_FEET = WyRegistry.registerItem("Luffy's Sandals", () -> new ModArmorItem(SANDALS_MATERIAL, "luffy", EquipmentSlotType.FEET, false));
/*     */   
/*  82 */   public static final RegistryObject<Item> ZORO_CHEST = WyRegistry.registerItem("Zoro's Shirt", () -> new ModArmorItem("zoro", EquipmentSlotType.CHEST));
/*  83 */   public static final RegistryObject<Item> ZORO_LEGS = WyRegistry.registerItem("Zoro's Pants", () -> new ModArmorItem("zoro", EquipmentSlotType.LEGS));
/*  84 */   public static final RegistryObject<Item> ZORO_FEET = WyRegistry.registerItem("Zoro's Boots", () -> new ModArmorItem("zoro", EquipmentSlotType.FEET));
/*     */   
/*  86 */   public static final RegistryObject<Item> FRANKY_GLASSES = WyRegistry.registerItem("Franky's Glasses", () -> new ModArmorItem("franky", EquipmentSlotType.HEAD));
/*  87 */   public static final RegistryObject<Item> FRANKY_CHEST = WyRegistry.registerItem("Franky's Shirt", () -> new ModArmorItem("franky", EquipmentSlotType.CHEST));
/*     */   
/*  89 */   public static final RegistryObject<Item> SANJI_CHEST = WyRegistry.registerItem("Sanji's Shirt", () -> new ModArmorItem("sanji", EquipmentSlotType.CHEST));
/*  90 */   public static final RegistryObject<Item> SANJI_LEGS = WyRegistry.registerItem("Sanji's Pants", () -> new ModArmorItem("sanji", EquipmentSlotType.LEGS));
/*  91 */   public static final RegistryObject<Item> SANJI_FEET = WyRegistry.registerItem("Sanji's Shoes", () -> new ModArmorItem("sanji", EquipmentSlotType.FEET));
/*     */   
/*  93 */   public static final RegistryObject<Item> SENOR_PINK_HEAD = WyRegistry.registerItem("Senior Pink's Bonnet", xyz.pixelatedw.mineminenomi.items.armors.SenorPinkBonnetItem::new);
/*  94 */   public static final RegistryObject<Item> SENOR_PINK_CHEST = WyRegistry.registerItem("Senior Pink's Shirt", () -> new ModArmorItem("senorpink", EquipmentSlotType.CHEST));
/*     */ 
/*     */   
/*  97 */   public static final RegistryObject<Item> USOPP_CHEST = WyRegistry.registerItem("Usopp's Overall", () -> new ModArmorItem("usopp", EquipmentSlotType.CHEST));
/*  98 */   public static final RegistryObject<Item> USOPP_LEGS = WyRegistry.registerItem("Usopp's Pants", () -> new ModArmorItem("usopp", EquipmentSlotType.LEGS));
/*  99 */   public static final RegistryObject<Item> USOPP_FEET = WyRegistry.registerItem("Usopp's Boots", () -> new ModArmorItem("usopp", EquipmentSlotType.FEET));
/*     */   
/* 101 */   public static final RegistryObject<Item> VICE_ADMIRAL_CHEST = WyRegistry.registerItem("Vice Admiral Jacket", () -> new ModDyeableArmorItem("vice_admiral", EquipmentSlotType.CHEST, true));
/* 102 */   public static final RegistryObject<Item> VICE_ADMIRAL_LEGS = WyRegistry.registerItem("Vice Admiral Pants", () -> new ModDyeableArmorItem("vice_admiral", EquipmentSlotType.LEGS, true));
/* 103 */   public static final RegistryObject<Item> VICE_ADMIRAL_FEET = WyRegistry.registerItem("Vice Admiral Boots", () -> new ModDyeableArmorItem("vice_admiral", EquipmentSlotType.FEET, true));
/*     */   
/* 105 */   public static final RegistryObject<Item> CP9_CHEST = WyRegistry.registerItem("CP9 Jacket", () -> new ModArmorItem("cp9", EquipmentSlotType.CHEST));
/* 106 */   public static final RegistryObject<Item> CP9_LEGS = WyRegistry.registerItem("CP9 Pants", () -> new ModArmorItem("cp9", EquipmentSlotType.LEGS));
/* 107 */   public static final RegistryObject<Item> CP9_FEET = WyRegistry.registerItem("CP9 Boots", () -> new ModArmorItem("cp9", EquipmentSlotType.FEET));
/*     */   
/* 109 */   public static final RegistryObject<Item> KUMA_CHEST = WyRegistry.registerItem("Kuma Shirt", () -> new ModArmorItem("kuma", EquipmentSlotType.CHEST));
/* 110 */   public static final RegistryObject<Item> KUMA_LEGS = WyRegistry.registerItem("Kuma Pants", () -> new ModArmorItem("kuma", EquipmentSlotType.LEGS));
/* 111 */   public static final RegistryObject<Item> KUMA_FEET = WyRegistry.registerItem("Kuma Boots", () -> new ModArmorItem("kuma", EquipmentSlotType.FEET));
/*     */   
/* 113 */   public static final RegistryObject<Item> SMOKER_CHEST = WyRegistry.registerItem("Smoker Jacket", () -> new ModArmorItem("smoker", EquipmentSlotType.CHEST));
/* 114 */   public static final RegistryObject<Item> SMOKER_LEGS = WyRegistry.registerItem("Smoker Pants", () -> new ModArmorItem("smoker", EquipmentSlotType.LEGS));
/* 115 */   public static final RegistryObject<Item> SMOKER_FEET = WyRegistry.registerItem("Smoker Boots", () -> new ModArmorItem("smoker", EquipmentSlotType.FEET));
/*     */   
/* 117 */   public static final RegistryObject<Item> CABAJI_SCARF = WyRegistry.registerItem("Cabaji's Scarf", () -> new CabajiScarfItem());
/*     */   
/* 119 */   public static final RegistryObject<Item> BIG_RED_NOSE = WyRegistry.registerItem("Big Red Nose", xyz.pixelatedw.mineminenomi.items.armors.BigRedNoseItem::new);
/*     */   
/* 121 */   public static final RegistryObject<Item> KURO_GLASSES = WyRegistry.registerItem("Kuro's Glasses", () -> new ModArmorItem("kuro", EquipmentSlotType.HEAD));
/* 122 */   public static final RegistryObject<Item> KURO_CHEST = WyRegistry.registerItem("Kuro's Suit", () -> new ModArmorItem("kuro", EquipmentSlotType.CHEST));
/* 123 */   public static final RegistryObject<Item> KURO_LEGS = WyRegistry.registerItem("Kuro's Pants", () -> new ModArmorItem("kuro", EquipmentSlotType.LEGS));
/* 124 */   public static final RegistryObject<Item> KURO_FEET = WyRegistry.registerItem("Kuro's Shoes", () -> new ModArmorItem("kuro", EquipmentSlotType.FEET));
/*     */   
/* 126 */   public static final RegistryObject<Item> PEARL_HAT = WyRegistry.registerItem("Pearl Head Armor", () -> new PearlArmorItem(EquipmentSlotType.HEAD));
/* 127 */   public static final RegistryObject<Item> PEARL_ARMOR = WyRegistry.registerItem("Pearl Body Armor", () -> new PearlArmorItem(EquipmentSlotType.CHEST));
/* 128 */   public static final RegistryObject<Item> PEARL_LEGS = WyRegistry.registerItem("Pearl Legs Armor", () -> new PearlArmorItem(EquipmentSlotType.LEGS));
/*     */   
/* 130 */   public static final RegistryObject<Item> ARLONG_HAT = WyRegistry.registerItem("Arlong's Hat", () -> new ModArmorItem("arlong", EquipmentSlotType.HEAD));
/* 131 */   public static final RegistryObject<Item> ARLONG_CHEST = WyRegistry.registerItem("Arlong's Shirt", () -> new ModArmorItem("arlong", EquipmentSlotType.CHEST));
/* 132 */   public static final RegistryObject<Item> ARLONG_LEGS = WyRegistry.registerItem("Arlong's Pants", () -> new ModArmorItem("arlong", EquipmentSlotType.LEGS));
/* 133 */   public static final RegistryObject<Item> ARLONG_FEET = WyRegistry.registerItem("Arlong's Shoes", () -> new ModArmorItem("arlong", EquipmentSlotType.FEET));
/*     */   
/* 135 */   public static final RegistryObject<Item> CHEW_CHEST = WyRegistry.registerItem("Chew's Shirt", () -> new ModArmorItem("chew", EquipmentSlotType.CHEST));
/* 136 */   public static final RegistryObject<Item> CHEW_LEGS = WyRegistry.registerItem("Chew's Pants", () -> new ModArmorItem("chew", EquipmentSlotType.LEGS));
/* 137 */   public static final RegistryObject<Item> CHEW_FEET = WyRegistry.registerItem("Chew's Shoes", () -> new ModArmorItem("chew", EquipmentSlotType.FEET));
/*     */   
/* 139 */   public static final RegistryObject<Item> KUROOBI_CHEST = WyRegistry.registerItem("Kuroobi's Ki", () -> new ModArmorItem("kuroobi", EquipmentSlotType.CHEST));
/* 140 */   public static final RegistryObject<Item> KUROOBI_LEGS = WyRegistry.registerItem("Kuroobi's Pants", () -> new ModArmorItem("kuroobi", EquipmentSlotType.LEGS));
/* 141 */   public static final RegistryObject<Item> KUROOBI_FEET = WyRegistry.registerItem("Kuroobi's Shoes", () -> new ModArmorItem("kuroobi", EquipmentSlotType.FEET));
/*     */   
/* 143 */   public static final RegistryObject<Item> CROCODILE_CHEST = WyRegistry.registerItem("Crocodile's Suit", () -> new ModArmorItem("crocodile", EquipmentSlotType.CHEST));
/* 144 */   public static final RegistryObject<Item> CROCODILE_LEGS = WyRegistry.registerItem("Crocodile's Pants", () -> new ModArmorItem("crocodile", EquipmentSlotType.LEGS));
/* 145 */   public static final RegistryObject<Item> CROCODILE_FEET = WyRegistry.registerItem("Crocodile's Shoes", () -> new ModArmorItem("crocodile", EquipmentSlotType.FEET));
/*     */   
/* 147 */   public static final RegistryObject<Item> MR1_CHEST = WyRegistry.registerItem("Mr 1's Shirt", () -> new ModArmorItem("mr1", EquipmentSlotType.CHEST));
/* 148 */   public static final RegistryObject<Item> MR1_LEGS = WyRegistry.registerItem("Mr 1's Pants", () -> new ModArmorItem("mr1", EquipmentSlotType.LEGS));
/* 149 */   public static final RegistryObject<Item> MR1_FEET = WyRegistry.registerItem("Mr 1's Shoes", () -> new ModArmorItem("mr1", EquipmentSlotType.FEET));
/*     */   
/* 151 */   public static final RegistryObject<Item> MR3_GLASSES = WyRegistry.registerItem("Mr 3's Glasses", () -> new ModArmorItem("mr3", EquipmentSlotType.HEAD));
/* 152 */   public static final RegistryObject<Item> MR3_CHEST = WyRegistry.registerItem("Mr 3's Shirt", () -> new ModArmorItem("mr3", EquipmentSlotType.CHEST));
/* 153 */   public static final RegistryObject<Item> MR3_LEGS = WyRegistry.registerItem("Mr 3's Pants", () -> new ModArmorItem("mr3", EquipmentSlotType.LEGS));
/* 154 */   public static final RegistryObject<Item> MR3_FEET = WyRegistry.registerItem("Mr 3's Shoes", () -> new ModArmorItem("mr3", EquipmentSlotType.FEET));
/*     */   
/* 156 */   public static final RegistryObject<Item> MR5_GLASSES = WyRegistry.registerItem("Mr 5's Glasses", () -> new ModArmorItem("mr5", EquipmentSlotType.HEAD));
/* 157 */   public static final RegistryObject<Item> MR5_CHEST = WyRegistry.registerItem("Mr 5's Shirt", () -> new ModArmorItem("mr5", EquipmentSlotType.CHEST));
/* 158 */   public static final RegistryObject<Item> MR5_LEGS = WyRegistry.registerItem("Mr 5's Pants", () -> new ModArmorItem("mr5", EquipmentSlotType.LEGS));
/* 159 */   public static final RegistryObject<Item> MR5_FEET = WyRegistry.registerItem("Mr 5's Shoes", () -> new ModArmorItem("mr5", EquipmentSlotType.FEET));
/*     */   
/* 161 */   public static final RegistryObject<Item> BANDANA = WyRegistry.registerItem("Bandana", xyz.pixelatedw.mineminenomi.items.armors.BandanaArmorItem::new);
/* 162 */   public static final RegistryObject<Item> SNIPER_GOGGLES = WyRegistry.registerItem("Sniper Goggles", xyz.pixelatedw.mineminenomi.items.armors.SniperGogglesItem::new);
/* 163 */   public static final RegistryObject<Item> MH5_GAS_MASK = WyRegistry.registerItem("MH5 Gas Mask", xyz.pixelatedw.mineminenomi.items.armors.MH5GasMaskItem::new);
/* 164 */   public static final RegistryObject<Item> CHOPPERS_HAT = WyRegistry.registerItem("Chopper's Hat", xyz.pixelatedw.mineminenomi.items.armors.ChoppersHatItem::new);
/* 165 */   public static final RegistryObject<Item> ACES_HAT = WyRegistry.registerItem("Ace's Hat", xyz.pixelatedw.mineminenomi.items.armors.AcesHatItem::new);
/* 166 */   public static final RegistryObject<Item> KIZARU_GLASSES = WyRegistry.registerItem("Kizaru's Glasses", () -> new ModArmorItem("kizaru_glasses", EquipmentSlotType.HEAD));
/* 167 */   public static final RegistryObject<Item> KILLER_MASK = WyRegistry.registerItem("Killer's Mask", xyz.pixelatedw.mineminenomi.items.armors.KillerMaskItem::new);
/* 168 */   public static final RegistryObject<Item> LAW_HAT = WyRegistry.registerItem("Law's Hat", xyz.pixelatedw.mineminenomi.items.armors.LawHatItem::new);
/* 169 */   public static final RegistryObject<Item> TRICORNE = WyRegistry.registerItem("Tricorne", xyz.pixelatedw.mineminenomi.items.armors.TricorneItem::new);
/* 170 */   public static final RegistryObject<Item> SABO_HAT = WyRegistry.registerItem("Sabo's Hat", xyz.pixelatedw.mineminenomi.items.armors.SaboHatItem::new);
/* 171 */   public static final RegistryObject<Item> DOFFY_GLASSES = WyRegistry.registerItem("Doflamingo's Glasses", () -> new ModDyeableArmorItem("doffy_glasses", EquipmentSlotType.HEAD, true));
/* 172 */   public static final RegistryObject<Item> MIHAWK_HAT = WyRegistry.registerItem("Mihawk's Hat", xyz.pixelatedw.mineminenomi.items.armors.MihawkHatItem::new);
/* 173 */   public static final RegistryObject<Item> FLEET_ADMIRAL_HAT = WyRegistry.registerItem("Fleet Admiral's Hat", xyz.pixelatedw.mineminenomi.items.armors.FleetAdmiralHatItem::new);
/* 174 */   public static final RegistryObject<Item> CELESTIAL_DRAGON_BUBBLE = WyRegistry.registerItem("Celestial Dragon Bubble", xyz.pixelatedw.mineminenomi.items.armors.CelestialDragonBubbleItem::new);
/* 175 */   public static final RegistryObject<Item> PLUME_HAT = WyRegistry.registerItem("Plume Hat", xyz.pixelatedw.mineminenomi.items.armors.PlumeHatItem::new);
/* 176 */   public static final RegistryObject<Item> BICORNE = WyRegistry.registerItem("Bicorne", xyz.pixelatedw.mineminenomi.items.armors.BicorneItem::new);
/* 177 */   public static final RegistryObject<Item> WIDE_BRIM_HAT = WyRegistry.registerItem("Wide Brim Hat", xyz.pixelatedw.mineminenomi.items.armors.WideBrimHatItem::new);
/* 178 */   public static final RegistryObject<Item> HEART_GLASSES = WyRegistry.registerItem("Heart Glasses", xyz.pixelatedw.mineminenomi.items.armors.HeartGlassesItem::new);
/*     */   
/* 180 */   public static final RegistryObject<Item> COLA_BACKPACK = WyRegistry.registerItem("Cola Backpack", xyz.pixelatedw.mineminenomi.items.armors.ColaBackpackItem::new);
/* 181 */   public static final RegistryObject<Item> MEDIC_BAG = WyRegistry.registerItem("Medic Bag", xyz.pixelatedw.mineminenomi.items.armors.MedicBagItem::new);
/* 182 */   public static final RegistryObject<Item> TOMOE_DRUMS = WyRegistry.registerItem("Tomoe Drums", xyz.pixelatedw.mineminenomi.items.armors.TomoeDrumsItem::new);
/* 183 */   public static final RegistryObject<Item> WOOTZ_STEEL_ARMOR = WyRegistry.registerItem("Wootz Steel Armor", xyz.pixelatedw.mineminenomi.items.armors.WootzSteelArmorItem::new);
/*     */   
/* 185 */   public static final RegistryObject<Item> MARINE_CAPTAIN_CAPE = WyRegistry.registerItem("Marine Captain Cape", () -> new CaptainCapeItem("marine_captain_cape", true));
/* 186 */   public static final RegistryObject<Item> PIRATE_CAPTAIN_CAPE = WyRegistry.registerItem("Pirate Captain Cape", () -> new CaptainCapeItem("pirate_captain_cape", true));
/* 187 */   public static final RegistryObject<Item> FLUFFY_CAPE = WyRegistry.registerItem("Fluffy Cape", () -> new FluffyCapeItem("fluffy_cape", true));
/*     */   
/*     */   public static void register(IEventBus eventBus) {}
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModArmors.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */