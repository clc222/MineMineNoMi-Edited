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
/*    */ 
/*    */ public class KnockdownTrialQuest extends Quest {
/* 14 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Knockdown", KnockdownTrialQuest::new))
/* 15 */     .build();
/*    */   
/* 17 */   private Objective objective01 = (Objective)new KillEntityObjective("Kill %s enemies", 20);
/*    */   
/*    */   public KnockdownTrialQuest(QuestId id) {
/* 20 */     super(id);
/* 21 */     addObjectives(new Objective[] { this.objective01 });
/*    */     
/* 23 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 27 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 29 */     props.addUnlockedAbility(KnockdownAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 31 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\KnockdownTrialQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */