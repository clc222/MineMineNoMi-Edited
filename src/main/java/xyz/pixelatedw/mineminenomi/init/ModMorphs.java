/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraftforge.eventbus.api.IEventBus;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
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
/*     */ 
/*     */ 
/*     */ public class ModMorphs
/*     */ {
/*  57 */   public static final RegistryObject<MorphInfo> ALLOSAURUS_HEAVY = WyRegistry.registerMorph("allosaurus_heavy", xyz.pixelatedw.mineminenomi.entities.zoan.AllosaurusHeavyMorphInfo::new);
/*  58 */   public static final RegistryObject<MorphInfo> ALLOSAURUS_WALK = WyRegistry.registerMorph("allosaurus_walk", xyz.pixelatedw.mineminenomi.entities.zoan.AllosaurusWalkMorphInfo::new);
/*     */ 
/*     */   
/*  61 */   public static final RegistryObject<MorphInfo> AXOLOTL_HEAVY = WyRegistry.registerMorph("axolotl_heavy", xyz.pixelatedw.mineminenomi.entities.zoan.AxolotlHeavyMorphInfo::new);
/*  62 */   public static final RegistryObject<MorphInfo> AXOLOTL_WALK = WyRegistry.registerMorph("axolotl_walk", xyz.pixelatedw.mineminenomi.entities.zoan.AxolotlWalkMorphInfo::new);
/*     */ 
/*     */   
/*  65 */   public static final RegistryObject<MorphInfo> BISON_HEAVY = WyRegistry.registerMorph("bison_heavy", xyz.pixelatedw.mineminenomi.entities.zoan.BisonHeavyMorphInfo::new);
/*  66 */   public static final RegistryObject<MorphInfo> BISON_WALK = WyRegistry.registerMorph("bison_walk", xyz.pixelatedw.mineminenomi.entities.zoan.BisonWalkMorphInfo::new);
/*     */ 
/*     */   
/*  69 */   public static final RegistryObject<MorphInfo> BRACHIO_HEAVY = WyRegistry.registerMorph("brachio_heavy", xyz.pixelatedw.mineminenomi.entities.zoan.BrachiosaurusHeavyMorphInfo::new);
/*  70 */   public static final RegistryObject<MorphInfo> BRACHIO_GUARD = WyRegistry.registerMorph("brachio_guard", xyz.pixelatedw.mineminenomi.entities.zoan.BrachiosaurusGuardMorphInfo::new);
/*     */ 
/*     */   
/*  73 */   public static final RegistryObject<MorphInfo> GIRAFFE_HEAVY = WyRegistry.registerMorph("giraffe_heavy", xyz.pixelatedw.mineminenomi.entities.zoan.GiraffeHeavyMorphInfo::new);
/*  74 */   public static final RegistryObject<MorphInfo> GIRAFFE_WALK = WyRegistry.registerMorph("giraffe_walk", xyz.pixelatedw.mineminenomi.entities.zoan.GiraffeWalkMorphInfo::new);
/*     */ 
/*     */   
/*  77 */   public static final RegistryObject<MorphInfo> KAME_GUARD = WyRegistry.registerMorph("kame_guard", xyz.pixelatedw.mineminenomi.entities.zoan.KameGuardMorphInfo::new);
/*  78 */   public static final RegistryObject<MorphInfo> KAME_WALK = WyRegistry.registerMorph("kame_walk", xyz.pixelatedw.mineminenomi.entities.zoan.KameWalkMorphInfo::new);
/*     */ 
/*     */   
/*  81 */   public static final RegistryObject<MorphInfo> LEOPARD_HEAVY = WyRegistry.registerMorph("leopard_heavy", xyz.pixelatedw.mineminenomi.entities.zoan.LeopardHeavyMorphInfo::new);
/*  82 */   public static final RegistryObject<MorphInfo> LEOPARD_WALK = WyRegistry.registerMorph("leopard_walk", xyz.pixelatedw.mineminenomi.entities.zoan.LeopardWalkMorphInfo::new);
/*     */ 
/*     */   
/*  85 */   public static final RegistryObject<MorphInfo> MAMMOTH_HEAVY = WyRegistry.registerMorph("mammoth_heavy", xyz.pixelatedw.mineminenomi.entities.zoan.MammothHeavyMorphInfo::new);
/*  86 */   public static final RegistryObject<MorphInfo> MAMMOTH_GUARD = WyRegistry.registerMorph("mammoth_guard", xyz.pixelatedw.mineminenomi.entities.zoan.MammothGuardMorphInfo::new);
/*     */ 
/*     */   
/*  89 */   public static final RegistryObject<MorphInfo> ZOU_HEAVY = WyRegistry.registerMorph("zou_heavy", xyz.pixelatedw.mineminenomi.entities.zoan.ZouHeavyMorphInfo::new);
/*  90 */   public static final RegistryObject<MorphInfo> ZOU_GUARD = WyRegistry.registerMorph("zou_guard", xyz.pixelatedw.mineminenomi.entities.zoan.ZouGuardMorphInfo::new);
/*     */ 
/*     */   
/*  93 */   public static final RegistryObject<MorphInfo> MOGU_HEAVY = WyRegistry.registerMorph("mogu_heavy", xyz.pixelatedw.mineminenomi.entities.zoan.MoguHeavyMorphInfo::new);
/*     */ 
/*     */   
/*  96 */   public static final RegistryObject<MorphInfo> PHOENIX_ASSAULT = WyRegistry.registerMorph("phoenix_assault", xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixAssaultMorphInfo::new);
/*  97 */   public static final RegistryObject<MorphInfo> PHOENIX_FLY = WyRegistry.registerMorph("phoenix_fly", xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixFlyMorphInfo::new);
/*     */ 
/*     */   
/* 100 */   public static final RegistryObject<MorphInfo> PTERA_ASSAULT = WyRegistry.registerMorph("ptera_assault", xyz.pixelatedw.mineminenomi.entities.zoan.PteranodonAssaultMorphInfo::new);
/* 101 */   public static final RegistryObject<MorphInfo> PTERA_FLY = WyRegistry.registerMorph("ptera_fly", xyz.pixelatedw.mineminenomi.entities.zoan.PteranodonFlyMorphInfo::new);
/*     */ 
/*     */   
/* 104 */   public static final RegistryObject<MorphInfo> SAI_HEAVY = WyRegistry.registerMorph("sai_heavy", xyz.pixelatedw.mineminenomi.entities.zoan.SaiHeavyMorphInfo::new);
/* 105 */   public static final RegistryObject<MorphInfo> SAI_WALK = WyRegistry.registerMorph("sai_walk", xyz.pixelatedw.mineminenomi.entities.zoan.SaiWalkMorphInfo::new);
/*     */ 
/*     */   
/* 108 */   public static final RegistryObject<MorphInfo> CANDLE_CHAMPION = WyRegistry.registerMorph("candle_champion", xyz.pixelatedw.mineminenomi.entities.zoan.CandleChampionMorphInfo::new);
/*     */ 
/*     */   
/* 111 */   public static final RegistryObject<MorphInfo> VENOM_DEMON = WyRegistry.registerMorph("venom_demon", xyz.pixelatedw.mineminenomi.entities.zoan.VenomDemonMorphInfo::new);
/*     */ 
/*     */   
/* 114 */   public static final RegistryObject<MorphInfo> SPRING_LEGS = WyRegistry.registerMorph("spring_lengs", xyz.pixelatedw.mineminenomi.entities.zoan.SpringLegsMorphInfo::new);
/*     */ 
/*     */   
/* 117 */   public static final RegistryObject<MorphInfo> GEAR_FOURTH = WyRegistry.registerMorph("gear_fourth", xyz.pixelatedw.mineminenomi.entities.zoan.GearFourthMorphInfo::new);
/* 118 */   public static final RegistryObject<MorphInfo> GOMU_GIGANT = WyRegistry.registerMorph("gomu_gigant", xyz.pixelatedw.mineminenomi.entities.zoan.GomuGiantMorphInfo::new);
/*     */ 
/*     */   
/* 121 */   public static final RegistryObject<MorphInfo> DAIBUTSU = WyRegistry.registerMorph("daibutsu", xyz.pixelatedw.mineminenomi.entities.zoan.HitoDaibutsuMorphInfo::new);
/*     */ 
/*     */   
/* 124 */   public static final RegistryObject<MorphInfo> INGA_ZARASHI = WyRegistry.registerMorph("inga_zarashi", xyz.pixelatedw.mineminenomi.entities.zoan.IngaZarashiMorphInfo::new);
/*     */ 
/*     */   
/* 127 */   public static final RegistryObject<MorphInfo> MEGA = WyRegistry.registerMorph("mega", xyz.pixelatedw.mineminenomi.entities.zoan.MegaMorphInfo::new);
/*     */ 
/*     */   
/* 130 */   public static final RegistryObject<MorphInfo> MINI = WyRegistry.registerMorph("mini", xyz.pixelatedw.mineminenomi.entities.zoan.MiniMorphInfo::new);
/*     */ 
/*     */   
/* 133 */   public static final RegistryObject<MorphInfo> SHINOKUNI = WyRegistry.registerMorph("shinokuni", xyz.pixelatedw.mineminenomi.entities.zoan.ShinokuniMorphInfo::new);
/*     */ 
/*     */   
/* 136 */   public static final RegistryObject<MorphInfo> SHADOWS_ASGARD = WyRegistry.registerMorph("shadows_asgard", xyz.pixelatedw.mineminenomi.entities.zoan.ShadowsAsgardMorphInfo::new);
/*     */ 
/*     */   
/* 139 */   public static final RegistryObject<MorphInfo> VOLT_AMARU = WyRegistry.registerMorph("volt_amaru", xyz.pixelatedw.mineminenomi.entities.zoan.VoltAmaruMorphInfo::new);
/*     */ 
/*     */   
/* 142 */   public static final RegistryObject<MorphInfo> YOMI_SKELETON = WyRegistry.registerMorph("yomi_skeleton", xyz.pixelatedw.mineminenomi.entities.zoan.YomiMorphInfo::new);
/*     */ 
/*     */   
/* 145 */   public static final RegistryObject<MorphInfo> MANE_MEMORY = WyRegistry.registerMorph("mane_memory", xyz.pixelatedw.mineminenomi.entities.zoan.ManeManeMemoryMorphInfo::new);
/*     */ 
/*     */   
/* 148 */   public static final RegistryObject<MorphInfo> BARA_CAR = WyRegistry.registerMorph("bara_car", xyz.pixelatedw.mineminenomi.entities.zoan.BaraCarMorphInfo::new);
/* 149 */   public static final RegistryObject<MorphInfo> BARA_HO = WyRegistry.registerMorph("bara_ho", xyz.pixelatedw.mineminenomi.entities.zoan.BaraHoMorphInfo::new);
/* 150 */   public static final RegistryObject<MorphInfo> BARA_CIRCUS = WyRegistry.registerMorph("bara_circus", xyz.pixelatedw.mineminenomi.entities.zoan.BaraCircusMorphInfo::new);
/* 151 */   public static final RegistryObject<MorphInfo> BARA_FESTIVAL = WyRegistry.registerMorph("bara_festival", xyz.pixelatedw.mineminenomi.entities.zoan.BaraFestivalMorphInfo::new);
/* 152 */   public static final RegistryObject<MorphInfo> BARA_SPLIT = WyRegistry.registerMorph("bara_split", xyz.pixelatedw.mineminenomi.entities.zoan.BaraSplitMorphInfo::new);
/*     */ 
/*     */   
/* 155 */   public static final RegistryObject<MorphInfo> PUNK_GIBSON = WyRegistry.registerMorph("punk_gibson", xyz.pixelatedw.mineminenomi.entities.zoan.PunkGibsonMorphInfo::new);
/* 156 */   public static final RegistryObject<MorphInfo> DAMNED_PUNK = WyRegistry.registerMorph("damned_punk", xyz.pixelatedw.mineminenomi.entities.zoan.DamnedPunkMorphInfo::new);
/* 157 */   public static final RegistryObject<MorphInfo> PUNK_CORNA_DIO = WyRegistry.registerMorph("punk_corna_dio", xyz.pixelatedw.mineminenomi.entities.zoan.PunkCornaDioMorphInfo::new);
/*     */   
/*     */   public static void register(IEventBus eventBus) {
/* 160 */     WyRegistry.MORPHS.register(eventBus);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModMorphs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */