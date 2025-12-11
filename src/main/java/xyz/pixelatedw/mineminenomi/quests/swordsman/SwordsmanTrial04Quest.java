/*    */ package xyz.pixelatedw.mineminenomi.quests.swordsman;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.OTatsumakiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.HitEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedHitChecks;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.TimedKillEntityObjective;
/*    */ 
/*    */ public class SwordsmanTrial04Quest extends Quest {
/* 20 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: O Tatsumaki", SwordsmanTrial04Quest::new))
/* 21 */     .addRequirements(new QuestId[] { ModQuests.SWORDSMAN_TRIAL_03
/* 22 */       }).build();
/*    */   
/* 24 */   private Objective objective01 = (Objective)new HitEntityObjective("Damage %s enemies with sweeping attacks", 20, SharedHitChecks.SWEEP_ATTACK_CHECK);
/* 25 */   private Objective objective02 = (Objective)new TimedKillEntityObjective("Kill %s enemies in less than %s seconds using a sword", 3, 5, SharedKillChecks.HAS_SWORD);
/* 26 */   private Objective objective03 = (Objective)new KillEntityObjective("Kill %s airborne enemies using a sword", 5, SharedKillChecks.AIRBORNE_ENEMY_CHECK.and(SharedKillChecks.HAS_SWORD));
/*    */ 
/*    */   
/*    */   public SwordsmanTrial04Quest(QuestId id) {
/* 30 */     super(id);
/* 31 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/* 32 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 37 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 39 */     props.addUnlockedAbility(OTatsumakiAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 41 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\swordsman\SwordsmanTrial04Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */