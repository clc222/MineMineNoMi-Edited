/*    */ package xyz.pixelatedw.mineminenomi.quests.blackleg;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.ExtraHachisAbility;
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
/*    */ public class BlackLegTrial02Quest extends Quest {
/* 17 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Extra Hachis", BlackLegTrial02Quest::new))
/* 18 */     .addRequirements(new QuestId[] { ModQuests.BLACK_LEG_TRIAL_01
/* 19 */       }).build();
/*    */   
/* 21 */   private Objective objective01 = (Objective)new TimedKillEntityObjective("Kill %s enemies in less than %s seconds", 3, 7, SharedKillChecks.IS_KICKING);
/*    */ 
/*    */   
/*    */   public BlackLegTrial02Quest(QuestId id) {
/* 25 */     super(id);
/* 26 */     addObjectives(new Objective[] { this.objective01 });
/* 27 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 32 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 34 */     props.addUnlockedAbility(ExtraHachisAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 36 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\blackleg\BlackLegTrial02Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */