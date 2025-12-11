/*    */ package xyz.pixelatedw.mineminenomi.quests.brawler;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.JishinHoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.RyuNoKagizumeAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.TimedKillEntityObjective;
/*    */ 
/*    */ public class BrawlerTrial05Quest extends Quest {
/* 18 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Jishin Ho", BrawlerTrial05Quest::new))
/* 19 */     .addRequirements(new QuestId[] { ModQuests.BRAWLER_TRIAL_03
/* 20 */       }).build();
/*    */   
/* 22 */   private Objective objective01 = (Objective)new TimedKillEntityObjective("Kill %s enemies in less than %s seconds", 3, 7, SharedKillChecks.HAS_BRALWER_HAND_CHECK);
/*    */ 
/*    */   
/*    */   public BrawlerTrial05Quest(QuestId id) {
/* 26 */     super(id);
/* 27 */     addObjectives(new Objective[] { this.objective01 });
/* 28 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 33 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 35 */     props.addUnlockedAbility(JishinHoAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/* 36 */     props.addUnlockedAbility(RyuNoKagizumeAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 38 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\brawler\BrawlerTrial05Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */