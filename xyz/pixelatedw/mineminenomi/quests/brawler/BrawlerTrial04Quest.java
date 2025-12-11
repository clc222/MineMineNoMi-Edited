/*    */ package xyz.pixelatedw.mineminenomi.quests.brawler;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.HakaiHoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.RyuNoIbukiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ 
/*    */ public class BrawlerTrial04Quest extends Quest {
/* 25 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Hakai Ho", BrawlerTrial04Quest::new))
/* 26 */     .addRequirements(new QuestId[] { ModQuests.BRAWLER_TRIAL_03
/* 27 */       }).build(); private static final Predicate<ItemStack> IMPACT_DIAL_CHECK;
/*    */   static {
/* 29 */     IMPACT_DIAL_CHECK = (itemStack -> (itemStack.func_77973_b() == ((Block)ModBlocks.IMPACT_DIAL.get()).func_199767_j()));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 34 */     DISTANCE_CHECK = ((player, target, source) -> {
/*    */         boolean isProj = source.func_76364_f() instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile;
/*    */         
/*    */         boolean hasDistance = (player.func_70068_e((Entity)target) >= 900.0D);
/*    */         
/* 39 */         return (isProj && hasDistance);
/*    */       });
/*    */   }
/* 42 */   private static final KillEntityObjective.ICheckKill DISTANCE_CHECK; private Objective objective01 = (Objective)new ObtainItemObjective("Collect %s gunpowder", 30, () -> Items.field_151016_H);
/* 43 */   private Objective objective02 = (Objective)new ObtainItemObjective("Collect %s impact dials", 5, IMPACT_DIAL_CHECK);
/* 44 */   private Objective objective03 = (new KillEntityObjective("Kill %s enemies from at least 30 blocks away using Genkotsu Meteor", 5, SharedKillChecks.HAS_BRALWER_HAND_CHECK.and(DISTANCE_CHECK))).addRequirements(new Objective[] { this.objective01, this.objective02 });
/*    */ 
/*    */   
/*    */   public BrawlerTrial04Quest(QuestId id) {
/* 48 */     super(id);
/* 49 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/* 50 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 55 */     if (!removeQuestItem(player, Items.field_151016_H, 30)) {
/* 56 */       return false;
/*    */     }
/* 58 */     if (!removeQuestItem(player, ((Block)ModBlocks.IMPACT_DIAL.get()).func_199767_j(), 5)) {
/* 59 */       return false;
/*    */     }
/* 61 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 63 */     props.addUnlockedAbility(HakaiHoAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/* 64 */     props.addUnlockedAbility(RyuNoIbukiAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 66 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\brawler\BrawlerTrial04Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */