/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.item.crafting.Ingredient;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraftforge.eventbus.api.IEventBus;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.bari.BarrierbilityBatAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doru.DoruDoruArtsKenAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doru.DoruDoruArtsPickaxeAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.hie.IceSaberAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.noro.NoroNoroBeamSwordAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.ope.GammaKnifeAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.pika.AmaNoMurakumoAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.wara.WarabideSwordAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.yuki.TabiraYukiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityItemTier;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.AbilityColoredPickaxeItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.AbilityColoredSwordItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.AbilityPickaxeItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.AbilitySwordItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.BlueSwordItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.ChakramItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.DyeableModSpearItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.DyeableModSwordItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.KujaBowItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.ModGunItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.ModSpearItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.ModSwordItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.PopGreenBowItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.UmbrellaItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.WarabideSwordItem;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ public class ModWeapons {
/*  38 */   public static final RegistryObject<ModSwordItem> SCISSORS = WyRegistry.registerItem("Scissors", xyz.pixelatedw.mineminenomi.items.weapons.ScissorsItem::new);
/*  39 */   public static final RegistryObject<ModSwordItem> KIKOKU = WyRegistry.registerItem("Kikoku", () -> (new ModSwordItem(7, 1000)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  40 */   public static final RegistryObject<ModSwordItem> KIRIBACHI = WyRegistry.registerItem("Kiribachi", () -> (new ModSwordItem(6, 400)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  41 */   public static final RegistryObject<ModSwordItem> YORU = WyRegistry.registerItem("Yoru", () -> (new ModSwordItem(11, -2.0F, 2000)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })).setRustImmunity());
/*  42 */   public static final RegistryObject<ModSwordItem> MURAKUMOGIRI = WyRegistry.registerItem("Murakumogiri", () -> (new ModSwordItem(12, -2.9F, 2251)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })).setRustImmunity());
/*  43 */   public static final RegistryObject<ModSwordItem> HOOK = WyRegistry.registerItem("Hook", () -> (new ModSwordItem(5, 251)).setIsPoisonous(300).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  44 */   public static final RegistryObject<UmbrellaItem> UMBRELLA = WyRegistry.registerItem("Umbrella", UmbrellaItem::new);
/*  45 */   public static final RegistryObject<ModSwordItem> NONOSAMA_STAFF = WyRegistry.registerItem("Nonosama Staff", () -> (new ModSwordItem(6, -2.6F, 400)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151043_k })).setBlunt().setRustImmunity());
/*  46 */   public static final RegistryObject<ModSpearItem> NONOSAMA_TRIDENT = WyRegistry.registerItem("Nonosama Trident", () -> (ModSpearItem)(new ModSpearItem(9, -2.6F, 400)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151043_k })).setRustImmunity());
/*  47 */   public static final RegistryObject<ModSwordItem> HAMMER_5T = WyRegistry.registerItem("5t Hammer", () -> (new ModSwordItem(1, 500)).setBlunt());
/*  48 */   public static final RegistryObject<ModSwordItem> ACES_KNIFE = WyRegistry.registerItem("Ace's Knife", () -> (new ModSwordItem(4, -1.1F, 200)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  49 */   public static final RegistryObject<ModSwordItem> MIHAWKS_KNIFE = WyRegistry.registerItem("Mihawk's Knife", () -> (new ModSwordItem(4, -1.1F, 200)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  50 */   public static final RegistryObject<ModSwordItem> SANDAI_KITETSU = WyRegistry.registerItem("Sandai Kitetsu", () -> (new ModSwordItem(8, -2.0F, 1200)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  51 */   public static final RegistryObject<ModSwordItem> WADO_ICHIMONJI = WyRegistry.registerItem("Wado Ichimonji", () -> (new ModSwordItem(8, -2.0F, 1200)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  52 */   public static final RegistryObject<ModSwordItem> NIDAI_KITETSU = WyRegistry.registerItem("Nidai Kitetsu", () -> (new ModSwordItem(8, -2.0F, 1200)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  53 */   public static final RegistryObject<ModSwordItem> SHUSUI = WyRegistry.registerItem("Shusui", () -> (new ModSwordItem(9, -2.0F, 1562)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })).setRustImmunity());
/*  54 */   public static final RegistryObject<ModSwordItem> ENMA = WyRegistry.registerItem("Enma", () -> (new ModSwordItem(9, -2.0F, 1562)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })).setRustImmunity());
/*  55 */   public static final RegistryObject<ModSwordItem> AME_NO_HABAKIRI = WyRegistry.registerItem("Ame no Habakiri", () -> (new ModSwordItem(9, -2.0F, 1562)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })).setRustImmunity());
/*  56 */   public static final RegistryObject<ModSwordItem> SOUL_SOLID = WyRegistry.registerItem("Soul Solid", () -> (new ModSwordItem(6, -1.4F, 500)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  57 */   public static final RegistryObject<ModSwordItem> DURANDAL = WyRegistry.registerItem("Durandal", () -> (new ModSwordItem(6, -1.5F, 400)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  58 */   public static final RegistryObject<ModSwordItem> DAISENSO = WyRegistry.registerItem("Daisenso", () -> (new ModSwordItem(5, -2.6F, 600)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  59 */   public static final RegistryObject<ModSwordItem> ACE = WyRegistry.registerItem("Ace", () -> (new ModSwordItem(11, -1.7F, 2500)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  60 */   public static final RegistryObject<ModSpearItem> MOGURA = WyRegistry.registerItem("Mogura", () -> (ModSpearItem)(new ModSpearItem(10, -2.6F, 1700)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  61 */   public static final RegistryObject<ModSwordItem> DALTONS_SPADE = WyRegistry.registerItem("Dalton's Spade", () -> (new ModSwordItem(6, -2.0F, 800)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  62 */   public static final RegistryObject<ModSwordItem> SAMEKIRI_BOCHO = WyRegistry.registerItem("Samekiri Bocho", () -> (new ModSwordItem(7, -2.0F, 900)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  63 */   public static final RegistryObject<ModSwordItem> CAT_CLAWS = WyRegistry.registerItem("Cat Claws", () -> (new ModSwordItem(7, -1.3F, 750)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  64 */   public static final RegistryObject<ModSwordItem> HASSAIKAI = WyRegistry.registerItem("Hassaikai", () -> (new ModSwordItem(12, -3.2F, 3000)).setBlunt().setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  65 */   public static final RegistryObject<ModSwordItem> GRYPHON = WyRegistry.registerItem("Gryphon", () -> (new ModSwordItem(10, -2.0F, 2200)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*     */ 
/*     */   
/*  68 */   public static final RegistryObject<ModSwordItem> MARINE_SWORD = WyRegistry.registerItem("Marine Sword", () -> (new ModSwordItem(5, 251)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  69 */   public static final RegistryObject<ModSwordItem> PIRATE_CUTLASS = WyRegistry.registerItem("Pirate Cutlass", () -> (new ModSwordItem(5, 251)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  70 */   public static final RegistryObject<ModSwordItem> BANDIT_KNIFE = WyRegistry.registerItem("Bandit Knife", () -> (new ModSwordItem(4, -1.3F, 200)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*     */ 
/*     */   
/*  73 */   public static final RegistryObject<ModSwordItem> PIPE = WyRegistry.registerItem("Pipe", () -> (new ModSwordItem(5, 200)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })).setBlunt());
/*  74 */   public static final RegistryObject<ModSwordItem> TONFA = WyRegistry.registerItem("Tonfa", () -> (new ModSwordItem(5, 500)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })).setBlunt());
/*  75 */   public static final RegistryObject<DyeableModSwordItem> JITTE = WyRegistry.registerItem("Jitte", () -> (DyeableModSwordItem)(new DyeableModSwordItem(6, 400)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)ModItems.KAIROSEKI.get() })).setBlunt().setRustImmunity());
/*  76 */   public static final RegistryObject<DyeableModSwordItem> KATANA = WyRegistry.registerItem("Katana", () -> (DyeableModSwordItem)(new DyeableModSwordItem(5, -2.2F, 250)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  77 */   public static final RegistryObject<DyeableModSwordItem> CUTLASS = WyRegistry.registerItem("Cutlass", () -> (DyeableModSwordItem)(new DyeableModSwordItem(5, -2.4F, 300)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  78 */   public static final RegistryObject<DyeableModSwordItem> BROADSWORD = WyRegistry.registerItem("Broadsword", () -> (DyeableModSwordItem)(new DyeableModSwordItem(6, -2.5F, 250)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  79 */   public static final RegistryObject<DyeableModSwordItem> BISENTO = WyRegistry.registerItem("Bisento", () -> (DyeableModSwordItem)(new DyeableModSwordItem(6, -2.7F, 250)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  80 */   public static final RegistryObject<DyeableModSwordItem> DAGGER = WyRegistry.registerItem("Dagger", () -> (DyeableModSwordItem)(new DyeableModSwordItem(4, -1.3F, 200)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  81 */   public static final RegistryObject<DyeableModSwordItem> AXE = WyRegistry.registerItem("Axe", () -> (DyeableModSwordItem)(new DyeableModSwordItem(6, -2.7F, 300)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  82 */   public static final RegistryObject<DyeableModSpearItem> SPEAR = WyRegistry.registerItem("Spear", () -> (DyeableModSpearItem)(new DyeableModSpearItem(5, -2.5F, 200)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  83 */   public static final RegistryObject<DyeableModSwordItem> CLEAVER = WyRegistry.registerItem("Cleaver", () -> (DyeableModSwordItem)(new DyeableModSwordItem(6, -2.5F, 250)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  84 */   public static final RegistryObject<DyeableModSwordItem> MACE = WyRegistry.registerItem("Mace", () -> (DyeableModSwordItem)(new DyeableModSwordItem(8, -3.2F, 600)).setBlunt().setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*  85 */   public static final RegistryObject<ChakramItem> CHAKRAM = WyRegistry.registerItem("Chakram", () -> (ChakramItem)(new ChakramItem()).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/*     */ 
/*     */   
/*  88 */   public static final RegistryObject<ClimaTactItem> CLIMA_TACT = WyRegistry.registerItem("Clima Tact", () -> (new ClimaTactItem(1, 1, 300)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_196128_bn })));
/*  89 */   public static final RegistryObject<ClimaTactItem> PERFECT_CLIMA_TACT = WyRegistry.registerItem("Perfect Clima Tact", () -> (new ClimaTactItem(2, 2, 500)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_196128_bn })));
/*  90 */   public static final RegistryObject<ClimaTactItem> SORCERY_CLIMA_TACT = WyRegistry.registerItem("Sorcery Clima Tact", () -> (new ClimaTactItem(4, 3, 800)).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_196128_bn, (IItemProvider)Items.field_151043_k })));
/*     */ 
/*     */   
/*  93 */   public static final RegistryObject<ModGunItem> FLINTLOCK = WyRegistry.registerItem("Flintlock", () -> (new ModGunItem(200)).setShotCooldown(15).setReloadCooldown(40).setBulletAccuracy(1.0F).setBulletSpeed(3.0F).setDamageMultiplier(1.6F).setGunpowderLimit(5));
/*  94 */   public static final RegistryObject<ModGunItem> SENRIKU = WyRegistry.registerItem("Senriku", () -> (new ModGunItem(800)).setShotCooldown(20).setReloadCooldown(60).setBulletAccuracy(0.0F).setBulletSpeed(4.5F).setDamageMultiplier(2.5F).setGunpowderLimit(4));
/*  95 */   public static final RegistryObject<ModGunItem> BAZOOKA = WyRegistry.registerItem("Bazooka", () -> (new ModGunItem(800, ModGunItem.BAZOOKA_AMMO)).setShotCooldown(60).setBulletAccuracy(0.5F).setBulletSpeed(3.0F).setDamageMultiplier(2.0F).setGunpowderLimit(1));
/*  96 */   public static final RegistryObject<ModGunItem> WALKER = WyRegistry.registerItem("Walker", () -> (new ModGunItem(2500)).setShotCooldown(10).setReloadCooldown(30).setBulletAccuracy(0.5F).setBulletSpeed(4.0F).setDamageMultiplier(1.8F).setGunpowderLimit(7));
/*  97 */   public static final RegistryObject<KujaBowItem> GREEN_KUJA_BOW = WyRegistry.registerItem("Green Kuja Bow", () -> new KujaBowItem(700));
/*     */ 
/*     */   
/* 100 */   public static final RegistryObject<PopGreenBowItem> KABUTO = WyRegistry.registerItem("Kabuto", () -> new PopGreenBowItem(400));
/* 101 */   public static final RegistryObject<PopGreenBowItem> BLACK_KABUTO = WyRegistry.registerItem("Kuro Kabuto", () -> new PopGreenBowItem(800));
/* 102 */   public static final RegistryObject<PopGreenBowItem> GINGA_PACHINKO = WyRegistry.registerItem("Ginga Pachinko", () -> new PopGreenBowItem(200));
/*     */ 
/*     */   
/* 105 */   public static final RegistryObject<AbilitySwordItem> ICE_SABER = WyRegistry.registerItem("Ice Saber", () -> (AbilitySwordItem)(new AbilitySwordItem(IceSaberAbility.INSTANCE, 12, -1.8F)).setFrosbiteTimer(40));
/* 106 */   public static final RegistryObject<AbilitySwordItem> AMA_NO_MURAKUMO = WyRegistry.registerItem("Ama no Murakumo", () -> new AbilitySwordItem(AmaNoMurakumoAbility.INSTANCE, 14, 0.0F));
/* 107 */   public static final RegistryObject<AbilitySwordItem> NORO_NORO_BEAM_SWORD = WyRegistry.registerItem("Noro Noro Beam Sword", () -> new AbilitySwordItem(NoroNoroBeamSwordAbility.INSTANCE, 7));
/* 108 */   public static final RegistryObject<AbilitySwordItem> DORU_DORU_ARTS_KEN = WyRegistry.registerItem("Doru Doru Arts: Ken", () -> new AbilityColoredSwordItem(DoruDoruArtsKenAbility.INSTANCE, 7));
/* 109 */   public static final RegistryObject<AbilityPickaxeItem> DORU_PICKAXE = WyRegistry.registerItem("Doru Doru Arts: Pickaxe", () -> new AbilityColoredPickaxeItem(DoruDoruArtsPickaxeAbility.INSTANCE, AbilityItemTier.DORU, 1, -2.8F));
/* 110 */   public static final RegistryObject<ModSwordItem> BLUE_SWORD = WyRegistry.registerItem("Blue Sword", () -> (new BlueSwordItem()).setRepairIngredient(Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_151042_j })));
/* 111 */   public static final RegistryObject<AbilitySwordItem> TABIRA_YUKI = WyRegistry.registerItem("Tabira Yuki", () -> (AbilitySwordItem)(new AbilitySwordItem(TabiraYukiAbility.INSTANCE, 9, -1.9F)).setFrosbiteTimer(12));
/* 112 */   public static final RegistryObject<AbilitySwordItem> WARABIDE_SWORD = WyRegistry.registerItem("Warabide Sword", () -> new WarabideSwordItem(WarabideSwordAbility.INSTANCE, 7));
/* 113 */   public static final RegistryObject<AbilitySwordItem> BARRIERBILITY_BAT = WyRegistry.registerItem("Barrierbility Bat", () -> new AbilitySwordItem(BarrierbilityBatAbility.INSTANCE, 7));
/* 114 */   public static final RegistryObject<AbilitySwordItem> GAMMA_KNIFE = WyRegistry.registerItem("Gamma Knife", () -> new AbilitySwordItem(GammaKnifeAbility.INSTANCE, 1, 0.0F));
/*     */   
/*     */   public static void register(IEventBus eventBus) {}
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModWeapons.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */