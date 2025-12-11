/*    */ package xyz.pixelatedw.mineminenomi.quests.blackleg;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.ConcasseAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ 
/*    */ public class BlackLegTrial01Quest extends Quest {
/* 16 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Concasse", BlackLegTrial01Quest::new))
/* 17 */     .build();
/*    */   
/* 19 */   private Objective objective01 = (Objective)new KillEntityObjective("Kill %s enemies using your kicks", 30, SharedKillChecks.IS_KICKING);
/* 20 */   private Objective objective02 = (Objective)new KillEntityObjective("Kill %s enemies using critical hits", 15, SharedKillChecks.IS_KICKING.and(SharedKillChecks.CRITICAL_KILL_CHECK));
/*    */ 
/*    */   
/*    */   public BlackLegTrial01Quest(QuestId id) {
/* 24 */     super(id);
/* 25 */     addObjectives(new Objective[] { this.objective01, this.objective02 });
/*    */     
/* 27 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 32 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 34 */     props.addUnlockedAbility(ConcasseAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 36 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\blackleg\BlackLegTrial01Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */