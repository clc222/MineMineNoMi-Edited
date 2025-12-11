/*    */ package xyz.pixelatedw.mineminenomi.quests;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.TakedownKickAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ 
/*    */ public class TakedownTrialQuest extends Quest {
/* 21 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Takedown Kick", TakedownTrialQuest::new))
/* 22 */     .build(); private static final Predicate<ItemStack> IMPACT_DIAL_CHECK;
/*    */   static {
/* 24 */     IMPACT_DIAL_CHECK = (itemStack -> (itemStack.func_77973_b() == ((Block)ModBlocks.IMPACT_DIAL.get()).func_199767_j()));
/*    */   }
/*    */ 
/*    */   
/* 28 */   private Objective objective01 = (Objective)new ObtainItemObjective("Collect %s gunpowder", 32, () -> Items.field_151016_H);
/* 29 */   private Objective objective02 = (Objective)new KillEntityObjective("Kill %s enemies using your fists while they're in air", 5, SharedKillChecks.HAS_EMPTY_HAND.and(SharedKillChecks.AIRBORNE_ENEMY_CHECK));
/* 30 */   private Objective objective03 = (Objective)new ObtainItemObjective("Collect %s impact dials", 2, IMPACT_DIAL_CHECK);
/*    */   
/*    */   public TakedownTrialQuest(QuestId id) {
/* 33 */     super(id);
/* 34 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/*    */     
/* 36 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 40 */     if (!removeQuestItem(player, Items.field_151016_H, 32)) {
/* 41 */       return false;
/*    */     }
/*    */     
/* 44 */     if (!removeQuestItem(player, ((Block)ModBlocks.IMPACT_DIAL.get()).func_199767_j(), 2)) {
/* 45 */       return false;
/*    */     }
/*    */     
/* 48 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 50 */     props.addUnlockedAbility(TakedownKickAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 52 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\TakedownTrialQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */