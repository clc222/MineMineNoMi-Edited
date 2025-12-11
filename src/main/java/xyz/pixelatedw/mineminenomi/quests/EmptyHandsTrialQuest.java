/*    */ package xyz.pixelatedw.mineminenomi.quests;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ 
/*    */ public class EmptyHandsTrialQuest extends Quest {
/* 15 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Empty Hands", EmptyHandsTrialQuest::new))
/* 16 */     .build();
/*    */   
/* 18 */   private Objective objective01 = (Objective)new KillEntityObjective("Kill %s enemies using your fists", 10, SharedKillChecks.HAS_EMPTY_HAND);
/*    */   
/*    */   public EmptyHandsTrialQuest(QuestId id) {
/* 21 */     super(id);
/* 22 */     addObjectives(new Objective[] { this.objective01 });
/*    */     
/* 24 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 28 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 30 */     props.addUnlockedAbility(EmptyHandsAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 32 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\EmptyHandsTrialQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */