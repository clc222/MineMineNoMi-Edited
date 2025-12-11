/*    */ package xyz.pixelatedw.mineminenomi.quests.blackleg;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.BienCuitGrillShotAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.DiableJambeAbility;
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
/*    */ public class BlackLegTrial05Quest extends Quest {
/* 18 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Diable Jambe", BlackLegTrial05Quest::new))
/* 19 */     .addRequirements(new QuestId[] { ModQuests.BLACK_LEG_TRIAL_03
/* 20 */       }).build();
/*    */   
/* 22 */   private Objective objective01 = (Objective)new KillEntityObjective("Kill %s on fire enemies using your kicks", 15, SharedKillChecks.IS_KICKING.and(SharedKillChecks.ON_FIRE_ENEMY_CHECK));
/*    */ 
/*    */   
/*    */   public BlackLegTrial05Quest(QuestId id) {
/* 26 */     super(id);
/* 27 */     addObjectives(new Objective[] { this.objective01 });
/*    */     
/* 29 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 34 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 36 */     props.addUnlockedAbility(DiableJambeAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/* 37 */     props.addUnlockedAbility(BienCuitGrillShotAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 39 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\blackleg\BlackLegTrial05Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */