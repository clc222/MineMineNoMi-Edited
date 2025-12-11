/*    */ package xyz.pixelatedw.mineminenomi.quests.swordsman;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.ShiShishiSonsonAbility;
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
/*    */ 
/*    */ public class SwordsmanTrial01Quest extends Quest {
/* 22 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Shi Shishi Sonson", SwordsmanTrial01Quest::new))
/* 23 */     .build();
/*    */   static {
/* 25 */     ITEM_OVER_7_DAMAGE = (itemStack -> 
/*    */       
/* 27 */       (ItemsHelper.isSword(itemStack) && ItemsHelper.getItemDamage(itemStack) > 7.0F));
/*    */   }
/*    */   private static final Predicate<ItemStack> ITEM_OVER_7_DAMAGE;
/* 30 */   private Objective objective01 = (Objective)new ObtainItemObjective("Obtain a sword with over 7 damage", 1, ITEM_OVER_7_DAMAGE);
/* 31 */   private Objective objective02 = (new ObtainItemObjective("Collect %s bones", 30, () -> Items.field_151103_aS)).addRequirement(this.objective01);
/* 32 */   private Objective objective03 = (new KillEntityObjective("Kill %s enemies using a sword", 20, SharedKillChecks.HAS_SWORD)).addRequirement(this.objective01);
/*    */ 
/*    */   
/*    */   public SwordsmanTrial01Quest(QuestId id) {
/* 36 */     super(id);
/* 37 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/* 38 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 43 */     if (!removeQuestItem(player, Items.field_151103_aS, 30)) {
/* 44 */       return false;
/*    */     }
/* 46 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 48 */     props.addUnlockedAbility(ShiShishiSonsonAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 50 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\swordsman\SwordsmanTrial01Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */