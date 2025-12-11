/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ import net.minecraft.advancements.CriteriaTriggers;
/*    */ import net.minecraft.advancements.ICriterionTrigger;
/*    */ import net.minecraftforge.eventbus.api.IEventBus;
/*    */ import xyz.pixelatedw.mineminenomi.data.triggers.CompleteChallengeTrigger;
/*    */ import xyz.pixelatedw.mineminenomi.data.triggers.ConsumeDevilFruitTrigger;
/*    */ import xyz.pixelatedw.mineminenomi.data.triggers.DFEncyclopediaCompletionTrigger;
/*    */ import xyz.pixelatedw.mineminenomi.data.triggers.JoinCrewTrigger;
/*    */ import xyz.pixelatedw.mineminenomi.data.triggers.LeaveCrewTrigger;
/*    */ import xyz.pixelatedw.mineminenomi.data.triggers.MooteorologistTrigger;
/*    */ import xyz.pixelatedw.mineminenomi.data.triggers.ObtainBellyTrigger;
/*    */ import xyz.pixelatedw.mineminenomi.data.triggers.ObtainBountyTrigger;
/*    */ import xyz.pixelatedw.mineminenomi.data.triggers.ObtainDorikiTrigger;
/*    */ import xyz.pixelatedw.mineminenomi.data.triggers.ObtainLoyaltyTrigger;
/*    */ import xyz.pixelatedw.mineminenomi.data.triggers.SelectFactionTrigger;
/*    */ import xyz.pixelatedw.mineminenomi.data.triggers.SelectRaceTrigger;
/*    */ import xyz.pixelatedw.mineminenomi.data.triggers.SelectStyleTrigger;
/*    */ import xyz.pixelatedw.mineminenomi.data.triggers.SetCrewCaptainTrigger;
/*    */ import xyz.pixelatedw.mineminenomi.data.triggers.SubtleTweaksTrigger;
/*    */ import xyz.pixelatedw.mineminenomi.data.triggers.UnlockAbilityTrigger;
/*    */ import xyz.pixelatedw.mineminenomi.data.triggers.UnlockChallengeTrigger;
/*    */ import xyz.pixelatedw.mineminenomi.data.triggers.YomiReviveTrigger;
/*    */ 
/*    */ public class ModAdvancements {
/* 25 */   public static final ObtainBellyTrigger OBTAIN_BELLY = new ObtainBellyTrigger();
/* 26 */   public static final UnlockAbilityTrigger UNLOCK_ABILITY = new UnlockAbilityTrigger();
/* 27 */   public static final DFEncyclopediaCompletionTrigger ENCYCLOPEDIA_COMPLETION = new DFEncyclopediaCompletionTrigger();
/* 28 */   public static final ConsumeDevilFruitTrigger CONSUME_DEVIL_FRUIT = new ConsumeDevilFruitTrigger();
/* 29 */   public static final MooteorologistTrigger MOOTEOROLOGIST = new MooteorologistTrigger();
/* 30 */   public static final SubtleTweaksTrigger SUBTLE_TWEAKS = new SubtleTweaksTrigger();
/* 31 */   public static final YomiReviveTrigger YOMI_REVIVE = new YomiReviveTrigger();
/* 32 */   public static final CompleteChallengeTrigger COMPLETE_CHALLENGE = new CompleteChallengeTrigger();
/* 33 */   public static final UnlockChallengeTrigger UNLOCK_CHALLENGE = new UnlockChallengeTrigger();
/* 34 */   public static final ObtainDorikiTrigger OBTAIN_DORIKI = new ObtainDorikiTrigger();
/* 35 */   public static final ObtainBountyTrigger OBTAIN_BOUNTY = new ObtainBountyTrigger();
/* 36 */   public static final ObtainLoyaltyTrigger OBTAIN_LOYALTY = new ObtainLoyaltyTrigger();
/* 37 */   public static final SelectFactionTrigger SELECT_FACTION = new SelectFactionTrigger();
/* 38 */   public static final SelectStyleTrigger SELECT_STYLE = new SelectStyleTrigger();
/* 39 */   public static final SelectRaceTrigger SELECT_RACE = new SelectRaceTrigger();
/* 40 */   public static final SetCrewCaptainTrigger SET_CREW_CAPTAIN = new SetCrewCaptainTrigger();
/* 41 */   public static final LeaveCrewTrigger LEAVE_CREW = new LeaveCrewTrigger();
/* 42 */   public static final JoinCrewTrigger JOIN_CREW = new JoinCrewTrigger();
/*    */   
/*    */   public static void register(IEventBus eventBus) {
/* 45 */     CriteriaTriggers.func_192118_a((ICriterionTrigger)OBTAIN_BELLY);
/* 46 */     CriteriaTriggers.func_192118_a((ICriterionTrigger)UNLOCK_ABILITY);
/* 47 */     CriteriaTriggers.func_192118_a((ICriterionTrigger)ENCYCLOPEDIA_COMPLETION);
/* 48 */     CriteriaTriggers.func_192118_a((ICriterionTrigger)CONSUME_DEVIL_FRUIT);
/* 49 */     CriteriaTriggers.func_192118_a((ICriterionTrigger)MOOTEOROLOGIST);
/* 50 */     CriteriaTriggers.func_192118_a((ICriterionTrigger)SUBTLE_TWEAKS);
/* 51 */     CriteriaTriggers.func_192118_a((ICriterionTrigger)YOMI_REVIVE);
/* 52 */     CriteriaTriggers.func_192118_a((ICriterionTrigger)COMPLETE_CHALLENGE);
/* 53 */     CriteriaTriggers.func_192118_a((ICriterionTrigger)UNLOCK_CHALLENGE);
/* 54 */     CriteriaTriggers.func_192118_a((ICriterionTrigger)OBTAIN_DORIKI);
/* 55 */     CriteriaTriggers.func_192118_a((ICriterionTrigger)OBTAIN_BOUNTY);
/* 56 */     CriteriaTriggers.func_192118_a((ICriterionTrigger)OBTAIN_LOYALTY);
/* 57 */     CriteriaTriggers.func_192118_a((ICriterionTrigger)SELECT_FACTION);
/* 58 */     CriteriaTriggers.func_192118_a((ICriterionTrigger)SELECT_STYLE);
/* 59 */     CriteriaTriggers.func_192118_a((ICriterionTrigger)SELECT_RACE);
/* 60 */     CriteriaTriggers.func_192118_a((ICriterionTrigger)SET_CREW_CAPTAIN);
/* 61 */     CriteriaTriggers.func_192118_a((ICriterionTrigger)LEAVE_CREW);
/* 62 */     CriteriaTriggers.func_192118_a((ICriterionTrigger)JOIN_CREW);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModAdvancements.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */