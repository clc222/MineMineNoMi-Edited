/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ import com.google.common.collect.Lists;
/*    */ import net.minecraftforge.eventbus.api.IEventBus;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.cyborg.ColaOverdriveAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.cyborg.CoupDeBooAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.cyborg.CoupDeVentAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.cyborg.FreshFireAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.cyborg.RadicalBeamAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.cyborg.SouthlandSuplexAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.cyborg.StrongRightAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.electro.ElectricalLunaAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.electro.ElectricalMissileAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.electro.ElectricalShowerAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.electro.SulongAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.KachiageHaisokuAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.KarakusagawaraSeikenAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.SamehadaShoteiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.TwoFishEngineAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.UchimizuAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.YarinamiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.rokushiki.KamieAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.rokushiki.RokuoganAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.rokushiki.SoruAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.rokushiki.TekkaiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.charactercreator.CharacterCreatorSelectionMap;
/*    */ import xyz.pixelatedw.mineminenomi.api.charactercreator.RaceId;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class ModRaces {
/*    */   public static final RegistryObject<RaceId> EMPTY;
/*    */   public static final RegistryObject<RaceId> HUMAN;
/*    */   
/*    */   static {
/* 36 */     EMPTY = WyRegistry.registerRace("Empty", () -> {
/*    */           CharacterCreatorSelectionMap.SelectionInfo info = new CharacterCreatorSelectionMap.SelectionInfo(ModResources.RANDOM);
/*    */           
/*    */           return new RaceId(info, false);
/*    */         });
/*    */     
/* 42 */     HUMAN = WyRegistry.registerRace("Human", () -> {
/*    */           CharacterCreatorSelectionMap.SelectionInfo info = new CharacterCreatorSelectionMap.SelectionInfo(ModResources.HUMAN);
/*    */           
/*    */           info.addTopAbilities(new AbilityCore[] { SoruAbility.INSTANCE, TekkaiAbility.INSTANCE, GeppoAbility.INSTANCE, KamieAbility.INSTANCE, RankyakuAbility.INSTANCE, RokuoganAbility.INSTANCE });
/*    */           
/*    */           return new RaceId(info, true, 1);
/*    */         });
/* 49 */     FISHMAN = WyRegistry.registerRace("Fishman", () -> {
/*    */           CharacterCreatorSelectionMap.SelectionInfo info = new CharacterCreatorSelectionMap.SelectionInfo(ModResources.FISHMAN);
/*    */           
/*    */           info.addTopAbilities(new AbilityCore[] { UchimizuAbility.INSTANCE, SamehadaShoteiAbility.INSTANCE, KachiageHaisokuAbility.INSTANCE, TwoFishEngineAbility.INSTANCE, YarinamiAbility.INSTANCE, KarakusagawaraSeikenAbility.INSTANCE });
/*    */           
/*    */           info.addBottomAbilities(new AbilityCore[] { CharacterCreatorSelectionMap.FISHMAN_SWIM_SPEED_PERK, CharacterCreatorSelectionMap.FISHMAN_DAMAGE_PERK });
/*    */           return new RaceId(info, true, 2);
/*    */         });
/* 57 */     CYBORG = WyRegistry.registerRace("Cyborg", () -> {
/*    */           CharacterCreatorSelectionMap.SelectionInfo info = new CharacterCreatorSelectionMap.SelectionInfo(ModResources.CYBORG);
/*    */           
/*    */           info.addTopAbilities(new AbilityCore[] { StrongRightAbility.INSTANCE, RadicalBeamAbility.INSTANCE, CoupDeVentAbility.INSTANCE, FreshFireAbility.INSTANCE, CoupDeBooAbility.INSTANCE, ColaOverdriveAbility.INSTANCE, SouthlandSuplexAbility.INSTANCE });
/*    */           
/*    */           info.addBottomAbilities(new AbilityCore[] { CharacterCreatorSelectionMap.CYBORG_ARMOR_PERK });
/*    */           return new RaceId(info, true, 3);
/*    */         });
/* 65 */     MINK = WyRegistry.registerRace("Mink", () -> {
/*    */           CharacterCreatorSelectionMap.SelectionInfo info = new CharacterCreatorSelectionMap.SelectionInfo(ModResources.MINK1);
/*    */           info.addTopAbilities(new AbilityCore[] { EleclawAbility.INSTANCE, ElectricalShowerAbility.INSTANCE, ElectricalLunaAbility.INSTANCE, ElectricalMissileAbility.INSTANCE, SulongAbility.INSTANCE });
/*    */           info.addBottomAbilities(new AbilityCore[] { CharacterCreatorSelectionMap.MINK_SPEED_PERK, CharacterCreatorSelectionMap.MINK_JUMP_PERK });
/*    */           RaceId id = new RaceId(info, true, 4);
/*    */           id.setSubRaces(Lists.newArrayList((Object[])new String[] { "mink_dog", "mink_lion", "mink_bunny" }));
/*    */           return id;
/*    */         });
/*    */   } public static final RegistryObject<RaceId> FISHMAN; public static final RegistryObject<RaceId> CYBORG; public static final RegistryObject<RaceId> MINK;
/*    */   public static void register(IEventBus bus) {
/* 75 */     WyRegistry.RACES.register(bus);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModRaces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */