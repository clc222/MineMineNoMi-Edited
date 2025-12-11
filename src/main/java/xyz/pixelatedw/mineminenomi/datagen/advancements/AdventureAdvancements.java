/*     */ package xyz.pixelatedw.mineminenomi.datagen.advancements;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.function.Consumer;
/*     */ import net.minecraft.advancements.Advancement;
/*     */ import net.minecraft.advancements.CriteriaTriggers;
/*     */ import net.minecraft.advancements.DisplayInfo;
/*     */ import net.minecraft.advancements.FrameType;
/*     */ import net.minecraft.advancements.ICriterionInstance;
/*     */ import net.minecraft.advancements.IRequirementsStrategy;
/*     */ import net.minecraft.advancements.criterion.BlockPredicate;
/*     */ import net.minecraft.advancements.criterion.ConsumeItemTrigger;
/*     */ import net.minecraft.advancements.criterion.DamageSourcePredicate;
/*     */ import net.minecraft.advancements.criterion.EffectsChangedTrigger;
/*     */ import net.minecraft.advancements.criterion.EntityEquipmentPredicate;
/*     */ import net.minecraft.advancements.criterion.EntityPredicate;
/*     */ import net.minecraft.advancements.criterion.InventoryChangeTrigger;
/*     */ import net.minecraft.advancements.criterion.ItemPredicate;
/*     */ import net.minecraft.advancements.criterion.KilledTrigger;
/*     */ import net.minecraft.advancements.criterion.LocationPredicate;
/*     */ import net.minecraft.advancements.criterion.MinMaxBounds;
/*     */ import net.minecraft.advancements.criterion.MobEffectsPredicate;
/*     */ import net.minecraft.advancements.criterion.RightClickBlockWithItemTrigger;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.loot.LootContext;
/*     */ import net.minecraft.loot.conditions.EntityHasProperty;
/*     */ import net.minecraft.loot.conditions.ILootCondition;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.common.data.ExistingFileHelper;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiHardeningAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiImbuingAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiInternalDestructionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.HaoshokuHakiInfusionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiAuraAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.AbilityDisplayInfo;
/*     */ import xyz.pixelatedw.mineminenomi.data.triggers.DFEncyclopediaCompletionTrigger;
/*     */ import xyz.pixelatedw.mineminenomi.data.triggers.ObtainBellyTrigger;
/*     */ import xyz.pixelatedw.mineminenomi.data.triggers.SubtleTweaksTrigger;
/*     */ import xyz.pixelatedw.mineminenomi.data.triggers.UnlockAbilityTrigger;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ 
/*     */ public class AdventureAdvancements implements Consumer<Consumer<Advancement>> {
/*  55 */   private static final ResourceLocation BACKGROUND = new ResourceLocation("textures/gui/advancements/backgrounds/adventure.png");
/*     */   
/*     */   private ExistingFileHelper fileHelper;
/*     */   
/*     */   public AdventureAdvancements(ExistingFileHelper fileHelper) {
/*  60 */     this.fileHelper = fileHelper;
/*     */   }
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
/*     */   public void accept(Consumer<Advancement> consumer) {
/*  78 */     Advancement root = Advancement.Builder.func_200278_a().func_203902_a((IItemProvider)ModBlocks.FLAG.get(), (ITextComponent)new TranslationTextComponent("advancements.adventure.root.title"), (ITextComponent)new TranslationTextComponent("advancements.adventure.root.description"), BACKGROUND, FrameType.TASK, false, false, false).func_200270_a(IRequirementsStrategy.field_223215_b_).func_200275_a("killed_something", (ICriterionInstance)KilledTrigger.Instance.func_203927_c()).func_200275_a("killed_by_something", (ICriterionInstance)KilledTrigger.Instance.func_203926_d()).save(consumer, new ResourceLocation("mineminenomi", "adventure/root"), this.fileHelper);
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
/*  94 */     Advancement meat = Advancement.Builder.func_200278_a().func_203905_a(root).func_203902_a((IItemProvider)ModItems.SEA_KING_MEAT.get(), (ITextComponent)ModI18n.Advancements.title("adventure.meat"), (ITextComponent)ModI18n.Advancements.description("adventure.meat"), null, FrameType.TASK, true, false, false).func_200270_a(IRequirementsStrategy.field_223215_b_).func_200275_a("sea_king_meat", (ICriterionInstance)ConsumeItemTrigger.Instance.func_203913_a((IItemProvider)ModItems.SEA_KING_MEAT.get())).func_200275_a("cooked_sea_king_meat", (ICriterionInstance)ConsumeItemTrigger.Instance.func_203913_a((IItemProvider)ModItems.COOKED_SEA_KING_MEAT.get())).save(consumer, new ResourceLocation("mineminenomi", "adventure/meat"), this.fileHelper);
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
/* 108 */     Advancement catBurglar = Advancement.Builder.func_200278_a().func_203905_a(root).func_203902_a((IItemProvider)ModItems.BELLY_POUCH.get(), (ITextComponent)ModI18n.Advancements.title("adventure.cat_burglar"), (ITextComponent)ModI18n.Advancements.description("adventure.cat_burglar"), null, FrameType.TASK, true, false, false).func_200275_a("obtain_belly", (ICriterionInstance)ObtainBellyTrigger.Instance.collectBelly(100000)).save(consumer, new ResourceLocation("mineminenomi", "adventure/cat_burglar"), this.fileHelper);
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
/* 122 */     Advancement myTreasure = Advancement.Builder.func_200278_a().func_203905_a(catBurglar).func_203902_a((IItemProvider)ModItems.BELLY_POUCH.get(), (ITextComponent)ModI18n.Advancements.title("adventure.my_treasure"), (ITextComponent)ModI18n.Advancements.description("adventure.my_treasure"), null, FrameType.CHALLENGE, true, true, true).func_200275_a("obtain_belly", (ICriterionInstance)ObtainBellyTrigger.Instance.collectBelly(10000000)).save(consumer, new ResourceLocation("mineminenomi", "adventure/my_treasure"), this.fileHelper);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 128 */     AbilityDisplayInfo whatsKairosekiDisplayInfo = new AbilityDisplayInfo(BusoshokuHakiHardeningAbility.INSTANCE, ((Item)ModItems.KEY.get()).func_190903_i(), (ITextComponent)ModI18n.Advancements.title("adventure.whats_kairoseki"), (ITextComponent)ModI18n.Advancements.description("adventure.whats_kairoseki"), null, FrameType.GOAL, true, false, false);
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
/* 141 */     Advancement whatsKairoseki = Advancement.Builder.func_200278_a().func_203905_a(root).func_203903_a((DisplayInfo)whatsKairosekiDisplayInfo).func_200270_a(IRequirementsStrategy.field_223215_b_).func_200275_a("unlock_busoshoku_hardening", (ICriterionInstance)UnlockAbilityTrigger.Instance.ability(BusoshokuHakiHardeningAbility.INSTANCE)).func_200275_a("unlock_busoshoku_imbuing", (ICriterionInstance)UnlockAbilityTrigger.Instance.ability(BusoshokuHakiImbuingAbility.INSTANCE)).func_200275_a("unlock_kenbunshoku_aura", (ICriterionInstance)UnlockAbilityTrigger.Instance.ability(KenbunshokuHakiAuraAbility.INSTANCE)).save(consumer, new ResourceLocation("mineminenomi", "adventure/whats_kairoseki"), this.fileHelper);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 147 */     AbilityDisplayInfo hakiMasterDisplayInfo = new AbilityDisplayInfo(HaoshokuHakiInfusionAbility.INSTANCE, ((Item)ModItems.KEY.get()).func_190903_i(), (ITextComponent)ModI18n.Advancements.title("adventure.haki_master"), (ITextComponent)ModI18n.Advancements.description("adventure.haki_master"), null, FrameType.GOAL, true, false, false);
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
/* 164 */     Advancement hakiMaster = Advancement.Builder.func_200278_a().func_203905_a(whatsKairoseki).func_203903_a((DisplayInfo)hakiMasterDisplayInfo).func_200270_a(IRequirementsStrategy.field_223214_a_).func_200275_a("unlock_busoshoku_hardening", (ICriterionInstance)UnlockAbilityTrigger.Instance.ability(BusoshokuHakiHardeningAbility.INSTANCE)).func_200275_a("unlock_busoshoku_imbuing", (ICriterionInstance)UnlockAbilityTrigger.Instance.ability(BusoshokuHakiImbuingAbility.INSTANCE)).func_200275_a("unlock_busoshoku_fullbody", (ICriterionInstance)UnlockAbilityTrigger.Instance.ability(BusoshokuHakiFullBodyHardeningAbility.INSTANCE)).func_200275_a("unlock_busoshoku_emission", (ICriterionInstance)UnlockAbilityTrigger.Instance.ability(BusoshokuHakiEmissionAbility.INSTANCE)).func_200275_a("unlock_busoshoku_internal_destruction", (ICriterionInstance)UnlockAbilityTrigger.Instance.ability(BusoshokuHakiInternalDestructionAbility.INSTANCE)).func_200275_a("unlock_kenbunshoku_aura", (ICriterionInstance)UnlockAbilityTrigger.Instance.ability(KenbunshokuHakiAuraAbility.INSTANCE)).func_200275_a("unlock_kenbunshoku_future_sight", (ICriterionInstance)UnlockAbilityTrigger.Instance.ability(KenbunshokuHakiFutureSightAbility.INSTANCE)).save(consumer, new ResourceLocation("mineminenomi", "adventure/haki_master"), this.fileHelper);
/*     */     
/* 166 */     Map<Effect, MobEffectsPredicate.InstancePredicate> drunk4EffectsMap = new HashMap<>();
/* 167 */     drunk4EffectsMap.put(ModEffects.DRUNK.get(), new MobEffectsPredicate.InstancePredicate(MinMaxBounds.IntBound.func_211340_b(4), MinMaxBounds.IntBound.field_211347_e, (Boolean)null, (Boolean)null));
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
/* 181 */     Advancement whyRumGone = Advancement.Builder.func_200278_a().func_203905_a(root).func_203902_a((IItemProvider)ModItems.BOTTLE_OF_RUM.get(), (ITextComponent)ModI18n.Advancements.title("adventure.why_is_the_rum_gone"), (ITextComponent)ModI18n.Advancements.description("adventure.why_is_the_rum_gone"), null, FrameType.TASK, true, false, true).func_200275_a("the_rum_is_indeed_gone", (ICriterionInstance)EffectsChangedTrigger.Instance.func_203917_a(new MobEffectsPredicate(drunk4EffectsMap))).save(consumer, new ResourceLocation("mineminenomi", "adventure/why_is_the_rum_gone"), this.fileHelper);
/*     */     
/* 183 */     EntityPredicate.AndPredicate grogPlayerPredicate = EntityPredicate.AndPredicate.func_234591_a_(new ILootCondition[] {
/* 184 */           EntityHasProperty.func_237477_a_(LootContext.EntityTarget.THIS, 
/*     */             
/* 186 */             EntityPredicate.Builder.func_203996_a().func_209367_a(new MobEffectsPredicate(drunk4EffectsMap)).func_204000_b()).build() });
/* 187 */     ItemPredicate grogItemPredicate1 = ItemPredicate.Builder.func_200309_a().func_200308_a((IItemProvider)ModItems.BOTTLE_OF_RUM.get()).func_200310_b();
/* 188 */     ItemPredicate grogItemPredicate2 = ItemPredicate.Builder.func_200309_a().func_200308_a((IItemProvider)ModItems.SAKE_BOTTLE.get()).func_200310_b();
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
/* 203 */     Advancement allForMeGrog = Advancement.Builder.func_200278_a().func_203905_a(whyRumGone).func_203902_a((IItemProvider)ModItems.BOTTLE_OF_RUM.get(), (ITextComponent)ModI18n.Advancements.title("adventure.all_for_me_grog"), (ITextComponent)ModI18n.Advancements.description("adventure.all_for_me_grog"), null, FrameType.CHALLENGE, true, false, true).func_200270_a(IRequirementsStrategy.field_223215_b_).func_200275_a("me_jolly_jolly_rum_grog", (ICriterionInstance)new ConsumeItemTrigger.Instance(grogPlayerPredicate, grogItemPredicate1)).func_200275_a("me_jolly_jolly_sake_grog", (ICriterionInstance)new ConsumeItemTrigger.Instance(grogPlayerPredicate, grogItemPredicate2)).save(consumer, new ResourceLocation("mineminenomi", "adventure/all_for_me_grog"), this.fileHelper);
/*     */     
/* 205 */     CompoundNBT cloneNbtTest = new CompoundNBT();
/* 206 */     cloneNbtTest.func_74757_a("isClone", false);
/* 207 */     ItemPredicate supremeGradePredicate = ItemPredicate.Builder.func_200309_a().func_200307_a((ITag)ModTags.Items.SUPREME_GRADE).func_218002_a(cloneNbtTest).func_200310_b();
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
/* 221 */     Advancement pricelessBlade = Advancement.Builder.func_200278_a().func_203905_a(root).func_203902_a((IItemProvider)ModWeapons.ACE.get(), (ITextComponent)ModI18n.Advancements.title("adventure.priceless_blade"), (ITextComponent)ModI18n.Advancements.description("adventure.priceless_blade"), null, FrameType.CHALLENGE, true, false, false).func_200270_a(IRequirementsStrategy.field_223215_b_).func_200275_a("obtain_supreme_grade", (ICriterionInstance)InventoryChangeTrigger.Instance.func_203923_a(new ItemPredicate[] { supremeGradePredicate })).save(consumer, new ResourceLocation("mineminenomi", "adventure/priceless_blade"), this.fileHelper);
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
/* 235 */     Advancement halfwayThere = Advancement.Builder.func_200278_a().func_203905_a(root).func_203902_a((IItemProvider)ModItems.DEVIL_FRUIT_ENCYCLOPEDIA.get(), (ITextComponent)ModI18n.Advancements.title("adventure.halfway_there"), (ITextComponent)ModI18n.Advancements.description("adventure.halfway_there"), null, FrameType.CHALLENGE, true, false, true).func_200275_a("encyclopedia_completion", (ICriterionInstance)DFEncyclopediaCompletionTrigger.Instance.completion(0.5F)).save(consumer, new ResourceLocation("mineminenomi", "adventure/halfway_there"), this.fileHelper);
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
/* 249 */     Advancement oneForTheBooks = Advancement.Builder.func_200278_a().func_203905_a(halfwayThere).func_203902_a((IItemProvider)ModItems.DEVIL_FRUIT_ENCYCLOPEDIA.get(), (ITextComponent)ModI18n.Advancements.title("adventure.one_for_the_books"), (ITextComponent)ModI18n.Advancements.description("adventure.one_for_the_books"), null, FrameType.CHALLENGE, true, true, true).func_200275_a("encyclopedia_completion", (ICriterionInstance)DFEncyclopediaCompletionTrigger.Instance.completion(1.0F)).save(consumer, new ResourceLocation("mineminenomi", "adventure/one_for_the_books"), this.fileHelper);
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
/* 263 */     Advancement subtleTweaks = Advancement.Builder.func_200278_a().func_203905_a(root).func_203902_a((IItemProvider)ModItems.DEVIL_FRUIT_ENCYCLOPEDIA.get(), (ITextComponent)ModI18n.Advancements.title("adventure.subtle_tweaks"), (ITextComponent)ModI18n.Advancements.description("adventure.subtle_tweaks"), null, FrameType.CHALLENGE, true, false, true).func_200275_a("subtle_tweaks", (ICriterionInstance)SubtleTweaksTrigger.Instance.create()).save(consumer, new ResourceLocation("mineminenomi", "adventure/subtle_tweaks"), this.fileHelper);
/*     */     
/* 265 */     ItemPredicate emptyItemPredicate = ItemPredicate.Builder.func_200309_a().func_200308_a((IItemProvider)Items.field_190931_a).func_200310_b();
/* 266 */     EntityEquipmentPredicate emptyHandsPredicate = new EntityEquipmentPredicate(ItemPredicate.field_192495_a, ItemPredicate.field_192495_a, ItemPredicate.field_192495_a, ItemPredicate.field_192495_a, emptyItemPredicate, ItemPredicate.field_192495_a);
/*     */     
/* 268 */     EntityPredicate drunkenFistTargetPredicate = EntityPredicate.Builder.func_203996_a().func_209367_a(new MobEffectsPredicate(drunk4EffectsMap)).func_217985_a(emptyHandsPredicate).func_204000_b();
/* 269 */     DamageSourcePredicate drunkenFistPredicate = new DamageSourcePredicate((Boolean)null, (Boolean)null, (Boolean)null, (Boolean)null, (Boolean)null, (Boolean)null, (Boolean)null, (Boolean)null, EntityPredicate.field_192483_a, drunkenFistTargetPredicate);
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
/* 282 */     Advancement drunkenFist = Advancement.Builder.func_200278_a().func_203905_a(whyRumGone).func_203902_a((IItemProvider)ModItems.BOTTLE_OF_RUM.get(), (ITextComponent)ModI18n.Advancements.title("adventure.drunken_fist"), (ITextComponent)ModI18n.Advancements.description("adventure.drunken_fist"), null, FrameType.CHALLENGE, true, false, true).func_200275_a("drunked_fist_kill", (ICriterionInstance)new KilledTrigger.Instance(CriteriaTriggers.field_192122_b.func_192163_a(), EntityPredicate.AndPredicate.field_234582_a_, EntityPredicate.AndPredicate.field_234582_a_, drunkenFistPredicate)).save(consumer, new ResourceLocation("mineminenomi", "adventure/drunken_fist"), this.fileHelper);
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
/* 300 */     Advancement pastMemories = Advancement.Builder.func_200278_a().func_203905_a(root).func_203902_a((IItemProvider)ModBlocks.PONEGLYPH.get(), (ITextComponent)ModI18n.Advancements.title("adventure.past_memories"), (ITextComponent)ModI18n.Advancements.description("adventure.past_memories"), null, FrameType.GOAL, true, false, false).func_200275_a("unlock_challenge", (ICriterionInstance)RightClickBlockWithItemTrigger.Instance.func_234852_a_(LocationPredicate.Builder.func_226870_a_().func_235312_a_(BlockPredicate.Builder.func_226243_a_().func_233458_a_((Block)ModBlocks.PONEGLYPH.get()).func_226245_b_()), ItemPredicate.Builder.func_200309_a().func_200308_a((IItemProvider)Items.field_151121_aF))).save(consumer, new ResourceLocation("mineminenomi", "adventure/past_memories"), this.fileHelper);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\advancements\AdventureAdvancements.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */