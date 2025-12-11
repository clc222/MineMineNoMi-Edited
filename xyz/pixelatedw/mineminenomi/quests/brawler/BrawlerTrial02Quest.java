/*    */ package xyz.pixelatedw.mineminenomi.quests.brawler;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.SpinningBrawlAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.SuplexAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ 
/*    */ public class BrawlerTrial02Quest extends Quest {
/* 18 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Spinning Brawl", BrawlerTrial02Quest::new))
/* 19 */     .addRequirements(new QuestId[] { ModQuests.BRAWLER_TRIAL_01
/* 20 */       }).build();
/*    */   
/* 22 */   private Objective objective01 = (Objective)new KillEntityObjective("Kill %s enemies using Suplex", 20, SharedKillChecks.HAS_EMPTY_HAND.and(SharedKillChecks.checkAbilitySource(SuplexAbility.INSTANCE)));
/*    */ 
/*    */   
/*    */   public BrawlerTrial02Quest(QuestId id) {
/* 26 */     super(id);
/*    */ 
/*    */ 
/*    */     
/* 30 */     addObjectives(new Objective[] { this.objective01 });
/*    */     
/* 32 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean startArena(PlayerEntity player) {
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 42 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 44 */     props.addUnlockedAbility(SpinningBrawlAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 46 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\brawler\BrawlerTrial02Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */