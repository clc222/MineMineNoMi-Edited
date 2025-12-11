/*    */ package xyz.pixelatedw.mineminenomi.quests.sniper;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.NemuriBoshiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.BrewPotionObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ 
/*    */ public class SniperTrial05Quest extends Quest {
/* 25 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Nemuri Boshi", SniperTrial05Quest::new))
/* 26 */     .addRequirements(new QuestId[] { ModQuests.SNIPER_TRIAL_04
/* 27 */       }).build();
/*    */ 
/*    */   
/*    */   private static final Predicate<ItemStack> BREATH_DIAL_CHECK;
/*    */ 
/*    */   
/*    */   static {
/* 34 */     BREATH_DIAL_CHECK = (itemStack -> (itemStack.func_77973_b() == ((Block)ModBlocks.BREATH_DIAL.get()).func_199767_j()));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 39 */   private Objective objective01 = (Objective)new ObtainItemObjective("Collect %s breath dials", 3, BREATH_DIAL_CHECK);
/* 40 */   private Objective objective02 = (Objective)new KillEntityObjective("Kill %s airborne enemies using a bow", 20, SharedKillChecks.AIRBORNE_ENEMY_CHECK);
/* 41 */   private Objective objective03 = (new BrewPotionObjective("Brew %s splash potions", 12, new Item[] { Items.field_185155_bH }, null)).addRequirements(new Objective[] { this.objective01 });
/*    */ 
/*    */   
/*    */   public SniperTrial05Quest(QuestId id) {
/* 45 */     super(id);
/* 46 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/* 47 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean giveReward(PlayerEntity player) {
/* 52 */     if (!removeQuestItem(player, ((Block)ModBlocks.BREATH_DIAL.get()).func_199767_j(), 3)) {
/* 53 */       return false;
/*    */     }
/* 55 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 57 */     props.addUnlockedAbility(NemuriBoshiAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 59 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\sniper\SniperTrial05Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */