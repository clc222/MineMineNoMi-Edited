/*    */ package xyz.pixelatedw.mineminenomi.quests.brawler;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.SuplexAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ 
/*    */ public class BrawlerTrial01Quest extends Quest {
/* 16 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Suplex", BrawlerTrial01Quest::new))
/* 17 */     .build();
/*    */   
/* 19 */   private Objective objective01 = (Objective)new KillEntityObjective("Kill %s enemies using your fists", 30, SharedKillChecks.HAS_EMPTY_HAND);
/*    */ 
/*    */   
/*    */   public BrawlerTrial01Quest(QuestId id) {
/* 23 */     super(id);
/* 24 */     addObjectives(new Objective[] { this.objective01 });
/*    */     
/* 26 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 31 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 33 */     props.addUnlockedAbility(SuplexAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 35 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\brawler\BrawlerTrial01Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */