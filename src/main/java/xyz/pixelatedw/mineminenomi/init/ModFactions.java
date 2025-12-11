/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import xyz.pixelatedw.mineminenomi.api.charactercreator.CharacterCreatorSelectionMap;
/*    */ import xyz.pixelatedw.mineminenomi.api.charactercreator.FactionId;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class ModFactions {
/*    */   public static final RegistryObject<FactionId> EMPTY;
/*    */   public static final RegistryObject<FactionId> CIVILIAN;
/*    */   public static final RegistryObject<FactionId> WORLD_GOVT;
/*    */   public static final RegistryObject<FactionId> BANDIT;
/*    */   
/*    */   static {
/* 14 */     EMPTY = WyRegistry.registerFaction("Empty", () -> {
/*    */           CharacterCreatorSelectionMap.SelectionInfo info = new CharacterCreatorSelectionMap.SelectionInfo(ModResources.RANDOM);
/*    */           
/*    */           return new FactionId(info, false);
/*    */         });
/*    */     
/* 20 */     CIVILIAN = WyRegistry.registerFaction("Civilian", () -> {
/*    */           CharacterCreatorSelectionMap.SelectionInfo info = new CharacterCreatorSelectionMap.SelectionInfo(ModResources.RANDOM);
/*    */           
/*    */           return new FactionId(info, false);
/*    */         });
/*    */     
/* 26 */     WORLD_GOVT = WyRegistry.registerFaction("World Government", () -> {
/*    */           CharacterCreatorSelectionMap.SelectionInfo info = new CharacterCreatorSelectionMap.SelectionInfo(ModResources.WORLD_GOV_ICON);
/*    */           
/*    */           return new FactionId(info, false);
/*    */         });
/*    */     
/* 32 */     BANDIT = WyRegistry.registerFaction("Bandit", () -> {
/*    */           CharacterCreatorSelectionMap.SelectionInfo info = new CharacterCreatorSelectionMap.SelectionInfo(ModResources.RANDOM);
/*    */           
/*    */           return new FactionId(info, false);
/*    */         });
/*    */     
/* 38 */     PIRATE = WyRegistry.registerFaction("Pirate", () -> {
/*    */           CharacterCreatorSelectionMap.SelectionInfo info = new CharacterCreatorSelectionMap.SelectionInfo(ModResources.PIRATE_ICON);
/*    */           
/*    */           return new FactionId(info, true, 1);
/*    */         });
/*    */     
/* 44 */     MARINE = WyRegistry.registerFaction("Marine", () -> {
/*    */           CharacterCreatorSelectionMap.SelectionInfo marineInfo = new CharacterCreatorSelectionMap.SelectionInfo(ModResources.MARINE_ICON);
/*    */           
/*    */           marineInfo.addTopAbilities(new AbilityCore[] { SmallMusterAbility.INSTANCE, MusterAbility.INSTANCE, CommandAbility.INSTANCE });
/*    */           
/*    */           return new FactionId(marineInfo, true, 2);
/*    */         });
/* 51 */     BOUNTY_HUNTER = WyRegistry.registerFaction("Bounty Hunter", () -> {
/*    */           CharacterCreatorSelectionMap.SelectionInfo info = new CharacterCreatorSelectionMap.SelectionInfo(ModResources.BOUNTY_HUNTER_ICON);
/*    */           
/*    */           return new FactionId(info, true, 3);
/*    */         });
/*    */     
/* 57 */     REVOLUTIONARY_ARMY = WyRegistry.registerFaction("revolutionary", "Revolutionary Army", () -> {
/*    */           CharacterCreatorSelectionMap.SelectionInfo info = new CharacterCreatorSelectionMap.SelectionInfo(ModResources.REVOLUTIONARY_ARMY_ICON);
/*    */           return new FactionId(info, true, 4);
/*    */         });
/*    */   } public static final RegistryObject<FactionId> PIRATE; public static final RegistryObject<FactionId> MARINE; public static final RegistryObject<FactionId> BOUNTY_HUNTER; public static final RegistryObject<FactionId> REVOLUTIONARY_ARMY;
/*    */   public static void register(IEventBus bus) {
/* 63 */     WyRegistry.FACTIONS.register(bus);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModFactions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */