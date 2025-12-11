/*    */ package xyz.pixelatedw.mineminenomi.quests.sniper;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.HissatsuAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.RenpatsuNamariBoshiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.HitEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.TimedKillEntityObjective;
/*    */ 
/*    */ public class SniperTrial06Quest extends Quest {
/* 26 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Renpatsu Namari Boshi", SniperTrial06Quest::new))
/* 27 */     .addRequirements(new QuestId[] { ModQuests.SNIPER_TRIAL_05
/* 28 */       }).build(); private static final Predicate<ItemStack> IMPACT_DIAL_BOW;
/*    */   static {
/* 30 */     IMPACT_DIAL_BOW = (itemStack -> (itemStack.func_77973_b() == ((Block)ModBlocks.IMPACT_DIAL.get()).func_199767_j()));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 35 */     DETONATE_CREEPER_CHECK = ((player, target, source, amount) -> 
/*    */       
/* 37 */       (target.func_200600_R() == EntityType.field_200797_k && source.func_76364_f() instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.HiNoToriBoshiProjectile));
/*    */   }
/*    */   private static final HitEntityObjective.ICheckHit DETONATE_CREEPER_CHECK;
/* 40 */   private Objective objective01 = (Objective)new ObtainItemObjective("Collect %s impact dials", 3, IMPACT_DIAL_BOW);
/* 41 */   private Objective objective02 = (Objective)new TimedKillEntityObjective("Kill %s enemies in less than %s seconds", 3, 5, SharedKillChecks.HAS_BOW);
/* 42 */   private Objective objective03 = (Objective)new HitEntityObjective("Detonate %s Creepers using Kaen Boshi", 3, DETONATE_CREEPER_CHECK);
/*    */ 
/*    */   
/*    */   public SniperTrial06Quest(QuestId id) {
/* 46 */     super(id);
/* 47 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/*    */     
/* 49 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean giveReward(PlayerEntity player) {
/* 54 */     if (!removeQuestItem(player, ((Block)ModBlocks.IMPACT_DIAL.get()).func_199767_j(), 3)) {
/* 55 */       return false;
/*    */     }
/* 57 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 59 */     props.addUnlockedAbility(RenpatsuNamariBoshiAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/* 60 */     props.addUnlockedAbility(HissatsuAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 62 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\sniper\SniperTrial06Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */