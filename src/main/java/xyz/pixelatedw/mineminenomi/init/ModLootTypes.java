/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ 
/*    */ import net.minecraft.loot.ILootSerializer;
/*    */ import net.minecraft.loot.LootConditionType;
/*    */ import net.minecraft.loot.LootFunctionType;
/*    */ import net.minecraft.loot.LootParameter;
/*    */ import net.minecraft.loot.conditions.ILootCondition;
/*    */ import net.minecraft.loot.functions.ILootFunction;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.registry.Registry;
/*    */ import net.minecraftforge.eventbus.api.IEventBus;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.data.conditions.FirstCompletionRewardCondition;
/*    */ import xyz.pixelatedw.mineminenomi.data.conditions.RandomizedFruitsCondition;
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.EncyclopediaCompletionFunction;
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.FakeWeaponFunction;
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.FruitAlreadyExistsFunction;
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.IncreaseBellyFunction;
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.IncreaseBountyFunction;
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.IncreaseBusoExpFunction;
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.IncreaseDorikiFunction;
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.IncreaseExtolFunction;
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.IncreaseKenExpFunction;
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.IncreaseLoyaltyFunction;
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.IncreaseStatFunction;
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.SetBellyInPouchFunction;
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.SetExtolInPouchFunction;
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.SetFruitClueFunction;
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.SetInfiniteStockFunction;
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.SetMarineColorFunction;
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.SetPriceFunction;
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.UnlockChallengesFunction;
/*    */ 
/*    */ public class ModLootTypes
/*    */ {
/* 36 */   public static final LootParameter<ChallengeCore<?>> COMPLETED_CHALLENGE = createParameter("completed_challenge");
/*    */   
/* 38 */   public static final LootConditionType RANDOMIZED_FRUIT = registerCondition("randomized_fruits", (ILootSerializer<? extends ILootCondition>)new RandomizedFruitsCondition.Serializer());
/* 39 */   public static final LootConditionType FIRST_COMPLETION = registerCondition("first_completion", (ILootSerializer<? extends ILootCondition>)new FirstCompletionRewardCondition.Serializer());
/*    */   
/* 41 */   public static final LootFunctionType SET_PRICE = registerFunction("set_price", (ILootSerializer<? extends ILootFunction>)new SetPriceFunction.Serializer());
/* 42 */   public static final LootFunctionType INFINITE_STOCK = registerFunction("infinite_stock", (ILootSerializer<? extends ILootFunction>)new SetInfiniteStockFunction.Serializer());
/* 43 */   public static final LootFunctionType SET_BELLY_IN_POUCH = registerFunction("set_belly_in_pouch", (ILootSerializer<? extends ILootFunction>)new SetBellyInPouchFunction.Serializer());
/* 44 */   public static final LootFunctionType SET_EXTOL_IN_POUCH = registerFunction("set_extol_in_pouch", (ILootSerializer<? extends ILootFunction>)new SetExtolInPouchFunction.Serializer());
/* 45 */   public static final LootFunctionType FAKE_WEAPON = registerFunction("fake_weapon", (ILootSerializer<? extends ILootFunction>)new FakeWeaponFunction.Serializer());
/* 46 */   public static final LootFunctionType FRUIT_CLUE = registerFunction("fruit_clue", (ILootSerializer<? extends ILootFunction>)new SetFruitClueFunction.Serializer());
/* 47 */   public static final LootFunctionType ENCYCLOPEDIA_COMPLETION = registerFunction("encyclopedia_completion", (ILootSerializer<? extends ILootFunction>)new EncyclopediaCompletionFunction.Serializer());
/* 48 */   public static final LootFunctionType SET_MARINE_COLOR = registerFunction("set_marine_color", (ILootSerializer<? extends ILootFunction>)new SetMarineColorFunction.Serializer());
/* 49 */   public static final LootFunctionType INCREASE_DORIKI = registerFunction("increase_doriki", (ILootSerializer<? extends ILootFunction>)new IncreaseStatFunction.Serializer(IncreaseDorikiFunction::create));
/* 50 */   public static final LootFunctionType INCREASE_BOUNTY = registerFunction("increase_bounty", (ILootSerializer<? extends ILootFunction>)new IncreaseStatFunction.Serializer(IncreaseBountyFunction::create));
/* 51 */   public static final LootFunctionType INCREASE_BELLY = registerFunction("increase_belly", (ILootSerializer<? extends ILootFunction>)new IncreaseStatFunction.Serializer(IncreaseBellyFunction::create));
/* 52 */   public static final LootFunctionType INCREASE_EXTOL = registerFunction("increase_extol", (ILootSerializer<? extends ILootFunction>)new IncreaseStatFunction.Serializer(IncreaseExtolFunction::create));
/* 53 */   public static final LootFunctionType INCREASE_LOYALTY = registerFunction("increase_loyalty", (ILootSerializer<? extends ILootFunction>)new IncreaseStatFunction.Serializer(IncreaseLoyaltyFunction::create));
/* 54 */   public static final LootFunctionType INCREASE_BUSO_EXP = registerFunction("increase_hardening_exp", (ILootSerializer<? extends ILootFunction>)new IncreaseStatFunction.Serializer(IncreaseBusoExpFunction::create));
/* 55 */   public static final LootFunctionType INCREASE_KEN_EXP = registerFunction("increase_observation_exp", (ILootSerializer<? extends ILootFunction>)new IncreaseStatFunction.Serializer(IncreaseKenExpFunction::create));
/* 56 */   public static final LootFunctionType FRUIT_ALREADY_EXISTS = registerFunction("fruit_already_exists", (ILootSerializer<? extends ILootFunction>)new FruitAlreadyExistsFunction.Serializer());
/* 57 */   public static final LootFunctionType UNLOCK_CHALLENGES = registerFunction("unlock_challenges", (ILootSerializer<? extends ILootFunction>)new UnlockChallengesFunction.Serializer());
/*    */   
/*    */   private static LootFunctionType registerFunction(String id, ILootSerializer<? extends ILootFunction> serializer) {
/* 60 */     return (LootFunctionType)Registry.func_218322_a(Registry.field_239694_aZ_, new ResourceLocation("mineminenomi", id), new LootFunctionType(serializer));
/*    */   }
/*    */   
/*    */   private static LootConditionType registerCondition(String id, ILootSerializer<? extends ILootCondition> serializer) {
/* 64 */     return (LootConditionType)Registry.func_218322_a(Registry.field_239704_ba_, new ResourceLocation("mineminenomi", id), new LootConditionType(serializer));
/*    */   }
/*    */   
/*    */   private static <T> LootParameter<T> createParameter(String id) {
/* 68 */     return new LootParameter(new ResourceLocation("mineminenomi", id));
/*    */   }
/*    */   
/*    */   public static void register(IEventBus eventBus) {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModLootTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */