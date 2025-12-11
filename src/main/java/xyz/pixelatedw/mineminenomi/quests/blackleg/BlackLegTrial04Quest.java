/*    */ package xyz.pixelatedw.mineminenomi.quests.blackleg;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.SkywalkAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.TimedSurvivalObjective;
/*    */ 
/*    */ public class BlackLegTrial04Quest extends Quest {
/* 22 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Skywalk", BlackLegTrial04Quest::new))
/* 23 */     .addRequirements(new QuestId[] { ModQuests.BLACK_LEG_TRIAL_03
/* 24 */       }).build();
/*    */   static {
/* 26 */     BREATH_DIAL_CHECK = (itemStack -> (itemStack.func_77973_b() == ((Block)ModBlocks.BREATH_DIAL.get()).func_199767_j()));
/*    */   }
/*    */ 
/*    */   
/*    */   private static final Predicate<ItemStack> BREATH_DIAL_CHECK;
/* 31 */   private Objective objective01 = (Objective)new ObtainItemObjective("Collect %s breath dials", 3, BREATH_DIAL_CHECK);
/* 32 */   private Objective objective02 = (Objective)new ObtainItemObjective("Collect %s rabbit feet", 2, () -> Items.field_179556_br);
/* 33 */   private Objective objective03 = (Objective)new TimedSurvivalObjective("Survive for %s seconds without getting hit", 2400);
/*    */ 
/*    */   
/*    */   public BlackLegTrial04Quest(QuestId id) {
/* 37 */     super(id);
/* 38 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/*    */     
/* 40 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 45 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 47 */     props.addUnlockedAbility(SkywalkAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 49 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\blackleg\BlackLegTrial04Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */