/*    */ package xyz.pixelatedw.mineminenomi.quests.brawler;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.HakaiHoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.JishinHoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.KingPunchAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ReachDorikiObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.TimedKillEntityObjective;
/*    */ 
/*    */ public class BrawlerTrial06Quest extends Quest {
/* 21 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: King Punch", BrawlerTrial06Quest::new))
/* 22 */     .addRequirements(new QuestId[] { ModQuests.BRAWLER_TRIAL_04, ModQuests.BRAWLER_TRIAL_05
/* 23 */       }).build();
/*    */   
/* 25 */   private Objective objective01 = (Objective)new ReachDorikiObjective("Reach %s doriki", 7000);
/* 26 */   private Objective objective02 = (new KillEntityObjective("Kill %s enemies using Hakai Ho", 10, SharedKillChecks.checkAbilitySource(HakaiHoAbility.INSTANCE))).addRequirement(this.objective01);
/* 27 */   private Objective objective03 = (new TimedKillEntityObjective("Kill %s enemies using Jishin Ho in %s seconds or less", 5, 5, SharedKillChecks.checkAbilitySource(JishinHoAbility.INSTANCE))).addRequirement(this.objective01);
/*    */ 
/*    */   
/*    */   public BrawlerTrial06Quest(QuestId id) {
/* 31 */     super(id);
/* 32 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/* 33 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 38 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 40 */     props.addUnlockedAbility(KingPunchAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 42 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\brawler\BrawlerTrial06Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */