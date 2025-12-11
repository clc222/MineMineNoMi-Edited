/*    */ package xyz.pixelatedw.mineminenomi.quests.swordsman;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.enchantment.EnchantmentHelper;
/*    */ import net.minecraft.enchantment.Enchantments;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.YakkodoriAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.TimedHitEntityObjective;
/*    */ 
/*    */ public class SwordsmanTrial02Quest extends Quest {
/* 24 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Yakkodori", SwordsmanTrial02Quest::new))
/* 25 */     .build();
/*    */   static {
/* 27 */     ITEM_WITH_SHARPNESS_2 = (itemStack -> 
/*    */       
/* 29 */       (ItemsHelper.isSword(itemStack) && EnchantmentHelper.func_77506_a(Enchantments.field_185302_k, itemStack) > 1));
/*    */   }
/*    */   private static final Predicate<ItemStack> ITEM_WITH_SHARPNESS_2;
/* 32 */   private Objective objective01 = (Objective)new TimedHitEntityObjective("Hit %s enemies at the same time", 3, 2);
/* 33 */   private Objective objective02 = (Objective)new KillEntityObjective("Kill %s enemies with critical hits using a sword", 25, SharedKillChecks.CRITICAL_KILL_CHECK.and(SharedKillChecks.HAS_SWORD));
/* 34 */   private Objective objective03 = (Objective)new ObtainItemObjective("Obtain a sword with Sharpness II", 1, ITEM_WITH_SHARPNESS_2);
/*    */ 
/*    */   
/*    */   public SwordsmanTrial02Quest(QuestId id) {
/* 38 */     super(id);
/* 39 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/* 40 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 45 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 47 */     props.addUnlockedAbility(YakkodoriAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 49 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\swordsman\SwordsmanTrial02Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */