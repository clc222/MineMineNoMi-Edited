/*    */ package xyz.pixelatedw.mineminenomi.quests.swordsman;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.enchantment.EnchantmentData;
/*    */ import net.minecraft.enchantment.Enchantments;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.EnchantedBookItem;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.HiryuKaenAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ 
/*    */ public class SwordsmanTrial05Quest extends Quest {
/* 22 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Hiryu Kaen", SwordsmanTrial05Quest::new))
/* 23 */     .addRequirements(new QuestId[] { ModQuests.SWORDSMAN_TRIAL_04
/* 24 */       }).build();
/*    */   
/* 26 */   private Objective objective01 = (Objective)new KillEntityObjective("Kill %s enemies while they're on fire", 30, SharedKillChecks.ON_FIRE_ENEMY_CHECK);
/* 27 */   private Objective objective02 = (Objective)new KillEntityObjective("Kill %s enemies while you're on fire", 10, SharedKillChecks.ON_FIRE_PLAYER_CHECK);
/*    */ 
/*    */   
/*    */   public SwordsmanTrial05Quest(QuestId id) {
/* 31 */     super(id);
/* 32 */     addObjectives(new Objective[] { this.objective01, this.objective02 });
/* 33 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 38 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 40 */     ItemStack stack = new ItemStack((IItemProvider)Items.field_151134_bR);
/* 41 */     EnchantedBookItem.func_92115_a(stack, new EnchantmentData(Enchantments.field_77334_n, 2));
/* 42 */     player.func_191521_c(stack);
/*    */     
/* 44 */     props.addUnlockedAbility(HiryuKaenAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 46 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\swordsman\SwordsmanTrial05Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */