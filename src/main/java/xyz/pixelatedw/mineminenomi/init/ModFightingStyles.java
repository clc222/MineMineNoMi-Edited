/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ import net.minecraftforge.eventbus.api.IEventBus;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.GustSwordAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.WeatherEggAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.CycloneTempo;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.MirageTempo;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderLanceTempo;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderstormTempo;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.AntiMannerKickCourseAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.BienCuitGrillShotAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.ConcasseAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.DiableJambeAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.PartyTableKickCourseAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.SkywalkAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.GenkotsuMeteorAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.HakaiHoAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.KingPunchAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.RyuNoIbukiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.RyuNoKagizumeAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.SpinningBrawlAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doctor.AntidoteShotAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doctor.DopingAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doctor.FirstAidAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doctor.MedicBagExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doctor.VirusZoneAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.sniper.HiNoToriBoshiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.sniper.HissatsuAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.sniper.KemuriBoshiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.sniper.NemuriBoshiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.sniper.SakuretsuSabotenBoshiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.sniper.TokuyoAburaBoshiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.HiryuKaenAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.OTatsumakiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.SanbyakurokujuPoundHoAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.ShiShishiSonsonAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.YakkodoriAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.charactercreator.CharacterCreatorSelectionMap;
/*     */ import xyz.pixelatedw.mineminenomi.api.charactercreator.StyleId;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ public class ModFightingStyles {
/*     */   public static final RegistryObject<StyleId> EMPTY;
/*     */   public static final RegistryObject<StyleId> SWORDSMAN;
/*     */   public static final RegistryObject<StyleId> SNIPER;
/*     */   
/*     */   static {
/*  49 */     EMPTY = WyRegistry.registerStyle("Empty", () -> {
/*     */           CharacterCreatorSelectionMap.SelectionInfo info = new CharacterCreatorSelectionMap.SelectionInfo(ModResources.RANDOM);
/*     */           
/*     */           return new StyleId(info, false);
/*     */         });
/*     */     
/*  55 */     SWORDSMAN = WyRegistry.registerStyle("Swordsman", () -> {
/*     */           CharacterCreatorSelectionMap.SelectionInfo info = new CharacterCreatorSelectionMap.SelectionInfo(ModResources.SWORDSMAN);
/*     */           
/*     */           info.addTopAbilities(new AbilityCore[] { ShiShishiSonsonAbility.INSTANCE, YakkodoriAbility.INSTANCE, SanbyakurokujuPoundHoAbility.INSTANCE, OTatsumakiAbility.INSTANCE, HiryuKaenAbility.INSTANCE });
/*     */           
/*     */           info.addBottomAbilities(new AbilityCore[] { CharacterCreatorSelectionMap.SWORDSMAN_DAMAGE_PERK });
/*     */           return new StyleId(info, true, 1);
/*     */         });
/*  63 */     SNIPER = WyRegistry.registerStyle("Sniper", () -> {
/*     */           CharacterCreatorSelectionMap.SelectionInfo info = new CharacterCreatorSelectionMap.SelectionInfo(ModResources.SNIPER);
/*     */           
/*     */           info.addTopAbilities(new AbilityCore[] { HiNoToriBoshiAbility.INSTANCE, KemuriBoshiAbility.INSTANCE, NemuriBoshiAbility.INSTANCE, TokuyoAburaBoshiAbility.INSTANCE, TetsuBoshiAbility.INSTANCE, SakuretsuSabotenBoshiAbility.INSTANCE, HissatsuAbility.INSTANCE });
/*     */           
/*     */           info.addBottomAbilities(new AbilityCore[] { CharacterCreatorSelectionMap.SNIPER_ACCURACY_PERK, CharacterCreatorSelectionMap.SNIPER_GOGGLES_PERK });
/*     */           return new StyleId(info, true, 2);
/*     */         });
/*  71 */     BRAWLER = WyRegistry.registerStyle("Brawler", () -> {
/*     */           CharacterCreatorSelectionMap.SelectionInfo info = new CharacterCreatorSelectionMap.SelectionInfo(ModResources.BRAWLER);
/*     */           
/*     */           info.addTopAbilities(new AbilityCore[] { SuplexAbility.INSTANCE, GenkotsuMeteorAbility.INSTANCE, SpinningBrawlAbility.INSTANCE, HakaiHoAbility.INSTANCE, JishinHoAbility.INSTANCE, KingPunchAbility.INSTANCE, RyuNoKagizumeAbility.INSTANCE, RyuNoIbukiAbility.INSTANCE });
/*     */           
/*     */           info.addBottomAbilities(new AbilityCore[] { CharacterCreatorSelectionMap.BRAWLER_DAMAGE_PERK });
/*     */           return new StyleId(info, true, 5);
/*     */         });
/*  79 */     BLACK_LEG = WyRegistry.registerStyle("Black Leg", () -> {
/*     */           CharacterCreatorSelectionMap.SelectionInfo info = new CharacterCreatorSelectionMap.SelectionInfo(ModResources.BLACK_LEG);
/*     */           
/*     */           info.addTopAbilities(new AbilityCore[] { ConcasseAbility.INSTANCE, AntiMannerKickCourseAbility.INSTANCE, PartyTableKickCourseAbility.INSTANCE, SkywalkAbility.INSTANCE, DiableJambeAbility.INSTANCE, BienCuitGrillShotAbility.INSTANCE });
/*     */           
/*     */           info.addBottomAbilities(new AbilityCore[] { CharacterCreatorSelectionMap.BLACK_LEG_DAMAGE_PERK, CharacterCreatorSelectionMap.BLACK_LEG_SPEED_PERK });
/*     */           return new StyleId(info, true, 6);
/*     */         });
/*  87 */     DOCTOR = WyRegistry.registerStyle("Doctor", () -> {
/*     */           CharacterCreatorSelectionMap.SelectionInfo info = new CharacterCreatorSelectionMap.SelectionInfo(ModResources.DOCTOR);
/*     */           
/*     */           info.addTopAbilities(new AbilityCore[] { FirstAidAbility.INSTANCE, FailedExperimentAbility.INSTANCE, DopingAbility.INSTANCE, VirusZoneAbility.INSTANCE, AntidoteShotAbility.INSTANCE, MedicBagExplosionAbility.INSTANCE });
/*     */           
/*     */           return new StyleId(info, true, 3);
/*     */         });
/*  94 */     ART_OF_WEATHER = WyRegistry.registerStyle("Art of Weather", () -> {
/*     */           CharacterCreatorSelectionMap.SelectionInfo info = new CharacterCreatorSelectionMap.SelectionInfo(ModResources.ART_OF_WEATHER);
/*     */           info.addTopAbilities(new AbilityCore[] { WeatherEggAbility.INSTANCE, ThunderstormTempo.INSTANCE, GustSwordAbility.INSTANCE, CycloneTempo.INSTANCE, MirageTempo.INSTANCE, ThunderLanceTempo.INSTANCE });
/*     */           return new StyleId(info, true, 4);
/*     */         });
/*     */   } public static final RegistryObject<StyleId> BRAWLER; public static final RegistryObject<StyleId> BLACK_LEG; public static final RegistryObject<StyleId> DOCTOR; public static final RegistryObject<StyleId> ART_OF_WEATHER;
/*     */   public static void register(IEventBus bus) {
/* 101 */     WyRegistry.STYLES.register(bus);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModFightingStyles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */