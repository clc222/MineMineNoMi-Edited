/*    */ package xyz.pixelatedw.mineminenomi.quests.brawler;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.GenkotsuMeteorAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.HitEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedHitChecks;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ 
/*    */ public class BrawlerTrial03Quest extends Quest {
/* 22 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Genkotsu Meteor", BrawlerTrial03Quest::new))
/* 23 */     .addRequirements(new QuestId[] { ModQuests.BRAWLER_TRIAL_02
/* 24 */       }).build();
/*    */   
/* 26 */   private Objective objective01 = (Objective)new ObtainItemObjective("Collect %s cannon balls", 30, (Supplier)ModItems.CANNON_BALL);
/* 27 */   private Objective objective02 = (new HitEntityObjective("Hit at least %s enemies using Spinning Brawl", 5, SharedHitChecks.checkAbilitySource(SpinningBrawlAbility.INSTANCE))).addRequirement(this.objective01);
/* 28 */   private Objective objective03 = (new KillEntityObjective("Kill %s enemies using critical hits", 10, SharedKillChecks.CRITICAL_KILL_CHECK.and(SharedKillChecks.HAS_EMPTY_HAND))).addRequirement(this.objective01);
/*    */ 
/*    */   
/*    */   public BrawlerTrial03Quest(QuestId id) {
/* 32 */     super(id);
/* 33 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/* 34 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 39 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 41 */     props.addUnlockedAbility(GenkotsuMeteorAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 43 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\brawler\BrawlerTrial03Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */