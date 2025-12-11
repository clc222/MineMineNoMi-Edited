/*    */ package xyz.pixelatedw.mineminenomi.quests.sniper;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.TetsuBoshiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.TokuyoAburaBoshiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
/*    */ 
/*    */ public class SniperTrial03Quest extends Quest {
/* 21 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Hazards", SniperTrial03Quest::new))
/* 22 */     .build();
/*    */   static {
/* 24 */     JUMPSHOT_CHECK = ((player, target, source) -> {
/*    */         ItemStack heldItem = player.func_184614_ca();
/*    */         
/* 27 */         return (ItemsHelper.isBow(heldItem) && !player.func_233570_aj_());
/*    */       });
/*    */   }
/* 30 */   private static final KillEntityObjective.ICheckKill JUMPSHOT_CHECK; private Objective objective01 = (Objective)new ObtainItemObjective("Collect %s ink sacs", 40, () -> Items.field_196136_br);
/* 31 */   private Objective objective02 = (Objective)new ObtainItemObjective("Collect %s dried kelp", 30, () -> Items.field_203180_bP);
/* 32 */   private Objective objective03 = (Objective)new ObtainItemObjective("Collect %s iron ingots", 30, () -> Items.field_151042_j);
/* 33 */   private Objective objective04 = (new KillEntityObjective("Kill %s enemies with jump shots", 20, JUMPSHOT_CHECK)).addRequirements(new Objective[] { this.objective01, this.objective02, this.objective03 });
/*    */ 
/*    */   
/*    */   public SniperTrial03Quest(QuestId id) {
/* 37 */     super(id);
/* 38 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03, this.objective04 });
/* 39 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean giveReward(PlayerEntity player) {
/* 44 */     if (!removeQuestItem(player, Items.field_196136_br, 40)) {
/* 45 */       return false;
/*    */     }
/* 47 */     if (!removeQuestItem(player, Items.field_203180_bP, 30)) {
/* 48 */       return false;
/*    */     }
/* 50 */     if (!removeQuestItem(player, Items.field_151042_j, 30)) {
/* 51 */       return false;
/*    */     }
/* 53 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 55 */     props.addUnlockedAbility(TokuyoAburaBoshiAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/* 56 */     props.addUnlockedAbility(TetsuBoshiAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 58 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\sniper\SniperTrial03Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */