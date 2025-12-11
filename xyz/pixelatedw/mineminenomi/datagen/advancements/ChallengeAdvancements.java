/*     */ package xyz.pixelatedw.mineminenomi.datagen.advancements;
/*     */ 
/*     */ import java.util.function.Consumer;
/*     */ import net.minecraft.advancements.Advancement;
/*     */ import net.minecraft.advancements.DisplayInfo;
/*     */ import net.minecraft.advancements.FrameType;
/*     */ import net.minecraft.advancements.ICriterionInstance;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraftforge.common.data.ExistingFileHelper;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.BellyFlopAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.OneTwoJangoAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.PearlFireAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.ShakushiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.StealPunchAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.bomu.BreezeBreathBombAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.TackleAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doru.CandleChampionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doru.DoruDoruArtsMoriAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.KarakusagawaraSeikenAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.MizuTaihoAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.kilo.KiloPress1Ability;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.mandemontactics.DemonicDanceAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.suna.GrandeSablesAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.suna.SablesAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.supa.SparClawAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.supa.SpiralHollowAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.ShiShishiSonsonAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.AbilityDisplayInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.triggers.CompleteChallengeTrigger;
/*     */ import xyz.pixelatedw.mineminenomi.data.triggers.UnlockChallengeTrigger;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ 
/*     */ public class ChallengeAdvancements implements Consumer<Consumer<Advancement>> {
/*  46 */   private static final ResourceLocation BACKGROUND = new ResourceLocation("textures/gui/advancements/backgrounds/adventure.png");
/*     */   
/*     */   private ExistingFileHelper fileHelper;
/*     */   
/*     */   public ChallengeAdvancements(ExistingFileHelper fileHelper) {
/*  51 */     this.fileHelper = fileHelper;
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
/*     */   public void accept(Consumer<Advancement> consumer) {
/*  67 */     Advancement root = Advancement.Builder.func_200278_a().func_203902_a((IItemProvider)ModBlocks.PONEGLYPH.get(), (ITextComponent)ModI18n.Advancements.title("challenges.root"), (ITextComponent)ModI18n.Advancements.description("challenges.root"), BACKGROUND, FrameType.TASK, false, false, false).func_200275_a("unlock_challenge", (ICriterionInstance)UnlockChallengeTrigger.Instance.unlockAnyChallenge()).save(consumer, new ResourceLocation("mineminenomi", "challenges/root"), this.fileHelper);
/*     */     
/*  69 */     Advancement higuma = standard(root, (ChallengeCore)ModChallenges.HIGUMA.get(), (IItemProvider)ModWeapons.CUTLASS.get(), consumer);
/*  70 */     hard(higuma, (ChallengeCore)ModChallenges.HIGUMA_HARD.get(), ShiShishiSonsonAbility.INSTANCE, consumer);
/*     */     
/*  72 */     Advancement alvida = standard(root, (ChallengeCore)ModChallenges.ALVIDA.get(), (IItemProvider)ModWeapons.MACE.get(), consumer);
/*  73 */     hard(alvida, (ChallengeCore)ModChallenges.ALVIDA_HARD.get(), (IItemProvider)ModAbilities.SUBE_SUBE_NO_MI, consumer);
/*     */     
/*  75 */     ItemStack marineAxe = new ItemStack((IItemProvider)ModWeapons.AXE.get());
/*  76 */     marineAxe.func_190925_c("display").func_74768_a("color", MobsHelper.MARINE_BLUE_COLOR.getRGB());
/*  77 */     Advancement morgan = standard(root, (ChallengeCore)ModChallenges.MORGAN.get(), marineAxe, consumer);
/*  78 */     hard(morgan, (ChallengeCore)ModChallenges.MORGAN_HARD.get(), TackleAbility.INSTANCE, consumer);
/*     */     
/*  80 */     Advancement buggy = standard(root, (ChallengeCore)ModChallenges.BUGGY.get(), (IItemProvider)ModAbilities.BARA_BARA_NO_MI, consumer);
/*  81 */     hard(buggy, (ChallengeCore)ModChallenges.BUGGY_HARD.get(), (IItemProvider)ModArmors.BIG_RED_NOSE.get(), consumer);
/*     */     
/*  83 */     Advancement cabaji = standard(root, (ChallengeCore)ModChallenges.CABAJI.get(), (IItemProvider)ModArmors.CABAJI_SCARF.get(), consumer);
/*  84 */     hard(cabaji, (ChallengeCore)ModChallenges.CABAJI_HARD.get(), (IItemProvider)ModItems.UNICYCLE.get(), consumer);
/*     */     
/*  86 */     Advancement kuro = standard(root, (ChallengeCore)ModChallenges.KURO.get(), (IItemProvider)ModArmors.KURO_CHEST.get(), consumer);
/*  87 */     hard(kuro, (ChallengeCore)ModChallenges.KURO_HARD.get(), ShakushiAbility.INSTANCE, consumer);
/*     */     
/*  89 */     Advancement nyanban = standard(root, (ChallengeCore)ModChallenges.NYANBAN_BROTHERS.get(), StealPunchAbility.INSTANCE, consumer);
/*  90 */     hard(nyanban, (ChallengeCore)ModChallenges.NYANBAN_BROTHERS_HARD.get(), BellyFlopAbility.INSTANCE, consumer);
/*     */     
/*  92 */     Advancement jango = standard(root, (ChallengeCore)ModChallenges.JANGO.get(), (IItemProvider)ModWeapons.CHAKRAM.get(), consumer);
/*  93 */     hard(jango, (ChallengeCore)ModChallenges.JANGO_HARD.get(), OneTwoJangoAbility.INSTANCE, consumer);
/*     */     
/*  95 */     Advancement krieg = standard(root, (ChallengeCore)ModChallenges.DON_KRIEG.get(), (IItemProvider)ModArmors.MH5_GAS_MASK.get(), consumer);
/*  96 */     hard(krieg, (ChallengeCore)ModChallenges.DON_KRIEG_HARD.get(), (IItemProvider)ModWeapons.DAISENSO.get(), consumer);
/*     */     
/*  98 */     Advancement gin = standard(root, (ChallengeCore)ModChallenges.GIN.get(), (IItemProvider)ModWeapons.TONFA.get(), consumer);
/*  99 */     hard(gin, (ChallengeCore)ModChallenges.GIN_HARD.get(), DemonicDanceAbility.INSTANCE, consumer);
/*     */     
/* 101 */     Advancement pearl = standard(root, (ChallengeCore)ModChallenges.PEARL.get(), (IItemProvider)ModArmors.PEARL_HAT.get(), consumer);
/* 102 */     hard(pearl, (ChallengeCore)ModChallenges.PEARL_HARD.get(), PearlFireAbility.INSTANCE, consumer);
/*     */     
/* 104 */     Advancement arlong = standard(root, (ChallengeCore)ModChallenges.ARLONG.get(), (IItemProvider)ModArmors.ARLONG_CHEST.get(), consumer);
/* 105 */     hard(arlong, (ChallengeCore)ModChallenges.ARLONG_HARD.get(), (IItemProvider)ModWeapons.KIRIBACHI.get(), consumer);
/*     */     
/* 107 */     Advancement kuroobi = standard(root, (ChallengeCore)ModChallenges.KUROOBI.get(), (IItemProvider)ModArmors.KUROOBI_CHEST.get(), consumer);
/* 108 */     hard(kuroobi, (ChallengeCore)ModChallenges.KUROOBI_HARD.get(), KarakusagawaraSeikenAbility.INSTANCE, consumer);
/*     */     
/* 110 */     Advancement chew = standard(root, (ChallengeCore)ModChallenges.CHEW.get(), (IItemProvider)ModArmors.CHEW_CHEST.get(), consumer);
/* 111 */     hard(chew, (ChallengeCore)ModChallenges.CHEW_HARD.get(), MizuTaihoAbility.INSTANCE, consumer);
/*     */     
/* 113 */     Advancement mr0 = standard(root, (ChallengeCore)ModChallenges.MR_0.get(), SablesAbility.INSTANCE, consumer);
/* 114 */     hard(mr0, (ChallengeCore)ModChallenges.MR_0_HARD.get(), GrandeSablesAbility.INSTANCE, consumer);
/*     */     
/* 116 */     Advancement mr1 = standard(root, (ChallengeCore)ModChallenges.MR_1.get(), SparClawAbility.INSTANCE, consumer);
/* 117 */     hard(mr1, (ChallengeCore)ModChallenges.MR_1_HARD.get(), SpiralHollowAbility.INSTANCE, consumer);
/*     */     
/* 119 */     Advancement mr3 = standard(root, (ChallengeCore)ModChallenges.MR_3.get(), DoruDoruArtsMoriAbility.INSTANCE, consumer);
/* 120 */     hard(mr3, (ChallengeCore)ModChallenges.MR_3_HARD.get(), CandleChampionAbility.INSTANCE, consumer);
/*     */     
/* 122 */     Advancement mr5MissValentine = standard(root, (ChallengeCore)ModChallenges.MR_5_AND_MISS_VALENTINE.get(), KiloPress1Ability.INSTANCE, consumer);
/* 123 */     hard(mr5MissValentine, (ChallengeCore)ModChallenges.MR_5_AND_MISS_VALENTINE_HARD.get(), BreezeBreathBombAbility.INSTANCE, consumer);
/*     */   }
/*     */   
/*     */   private Advancement standard(Advancement parent, ChallengeCore<?> challengeCore, IItemProvider itemProvider, Consumer<Advancement> consumer) {
/* 127 */     DisplayInfo displayInfo = new DisplayInfo(new ItemStack((IItemProvider)itemProvider.func_199767_j()), (ITextComponent)challengeCore.getLocalizedTitle(), (ITextComponent)challengeCore.getLocalizedObjective(), null, FrameType.TASK, true, false, true);
/* 128 */     return challenge(parent, challengeCore, displayInfo, consumer);
/*     */   }
/*     */   
/*     */   private Advancement standard(Advancement parent, ChallengeCore<?> challengeCore, ItemStack itemStack, Consumer<Advancement> consumer) {
/* 132 */     DisplayInfo displayInfo = new DisplayInfo(itemStack, (ITextComponent)challengeCore.getLocalizedTitle(), (ITextComponent)challengeCore.getLocalizedObjective(), null, FrameType.TASK, true, false, true);
/* 133 */     return challenge(parent, challengeCore, displayInfo, consumer);
/*     */   }
/*     */   
/*     */   private Advancement standard(Advancement parent, ChallengeCore<?> challengeCore, AbilityCore<?> abilityCore, Consumer<Advancement> consumer) {
/* 137 */     AbilityDisplayInfo displayInfo = new AbilityDisplayInfo(abilityCore, (ITextComponent)challengeCore.getLocalizedTitle(), (ITextComponent)challengeCore.getLocalizedObjective(), null, FrameType.TASK, true, false, true);
/* 138 */     return challenge(parent, challengeCore, (DisplayInfo)displayInfo, consumer);
/*     */   }
/*     */   
/*     */   private Advancement hard(Advancement parent, ChallengeCore<?> challengeCore, IItemProvider itemProvider, Consumer<Advancement> consumer) {
/* 142 */     DisplayInfo displayInfo = new DisplayInfo(new ItemStack((IItemProvider)itemProvider.func_199767_j()), (ITextComponent)challengeCore.getLocalizedTitle(), (ITextComponent)challengeCore.getLocalizedObjective(), null, FrameType.CHALLENGE, true, true, false);
/* 143 */     return challenge(parent, challengeCore, displayInfo, consumer);
/*     */   }
/*     */   
/*     */   private Advancement hard(Advancement parent, ChallengeCore<?> challengeCore, ItemStack itemStack, Consumer<Advancement> consumer) {
/* 147 */     DisplayInfo displayInfo = new DisplayInfo(itemStack, (ITextComponent)challengeCore.getLocalizedTitle(), (ITextComponent)challengeCore.getLocalizedObjective(), null, FrameType.CHALLENGE, true, true, false);
/* 148 */     return challenge(parent, challengeCore, displayInfo, consumer);
/*     */   }
/*     */   
/*     */   private Advancement hard(Advancement parent, ChallengeCore<?> challengeCore, AbilityCore<?> abilityCore, Consumer<Advancement> consumer) {
/* 152 */     AbilityDisplayInfo displayInfo = new AbilityDisplayInfo(abilityCore, (ITextComponent)challengeCore.getLocalizedTitle(), (ITextComponent)challengeCore.getLocalizedObjective(), null, FrameType.CHALLENGE, true, true, false);
/* 153 */     return challenge(parent, challengeCore, (DisplayInfo)displayInfo, consumer);
/*     */   }
/*     */   
/*     */   private Advancement challenge(Advancement parent, ChallengeCore<?> challengeCore, DisplayInfo display, Consumer<Advancement> consumer) {
/* 157 */     return Advancement.Builder.func_200278_a()
/* 158 */       .func_203905_a(parent)
/* 159 */       .func_203903_a(display)
/* 160 */       .func_200275_a("unlock_challenge", (ICriterionInstance)CompleteChallengeTrigger.Instance.completeChallenge(challengeCore))
/* 161 */       .save(consumer, new ResourceLocation("mineminenomi", "challenges/" + challengeCore.getId()), this.fileHelper);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\advancements\ChallengeAdvancements.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */