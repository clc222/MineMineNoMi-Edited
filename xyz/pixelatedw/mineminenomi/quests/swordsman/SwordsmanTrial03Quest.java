/*    */ package xyz.pixelatedw.mineminenomi.quests.swordsman;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.enchantment.EnchantmentHelper;
/*    */ import net.minecraft.enchantment.Enchantments;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.SanbyakurokujuPoundHoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.ShiShishiSonsonAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.YakkodoriAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ 
/*    */ public class SwordsmanTrial03Quest
/*    */   extends Quest {
/* 27 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Sanbyakurokuju Pound Ho", SwordsmanTrial03Quest::new))
/* 28 */     .addRequirements(new QuestId[] { ModQuests.SWORDSMAN_TRIAL_01
/* 29 */       }).build(); private static final Predicate<ItemStack> ITEM_WITH_UNBREAKING;
/*    */   static {
/* 31 */     ITEM_WITH_UNBREAKING = (itemStack -> 
/*    */       
/* 33 */       (ItemsHelper.isSword(itemStack) && EnchantmentHelper.func_77506_a(Enchantments.field_185307_s, itemStack) > 0));
/*    */ 
/*    */     
/* 36 */     YAKKODORI_KILL_CHECK = ((player, target, source) -> 
/*    */       
/* 38 */       (source.func_76346_g() instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.swordsman.YakkodoriProjectile || (source instanceof AbilityDamageSource && ((AbilityDamageSource)source).getAbilitySource().equals(YakkodoriAbility.INSTANCE))));
/*    */ 
/*    */     
/* 41 */     SHI_SHISHI_SONSON_KILL_CHECK = ((player, target, source) -> {
/*    */         IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */         
/*    */         ShiShishiSonsonAbility ability = (ShiShishiSonsonAbility)props.getEquippedAbility(ShiShishiSonsonAbility.INSTANCE);
/* 45 */         return (ability != null && source instanceof AbilityDamageSource && ((AbilityDamageSource)source).getAbilitySource().equals(ShiShishiSonsonAbility.INSTANCE));
/*    */       });
/*    */   }
/*    */   private static final KillEntityObjective.ICheckKill YAKKODORI_KILL_CHECK; private static final KillEntityObjective.ICheckKill SHI_SHISHI_SONSON_KILL_CHECK;
/* 49 */   private Objective objective01 = (Objective)new KillEntityObjective("Kill %s enemies using Yakkodori", 10, YAKKODORI_KILL_CHECK);
/* 50 */   private Objective objective02 = (new KillEntityObjective("Kill %s enemies using Shi Shishi Sonson", 30, SHI_SHISHI_SONSON_KILL_CHECK)).addRequirement(this.objective01);
/*    */ 
/*    */   
/*    */   public SwordsmanTrial03Quest(QuestId id) {
/* 54 */     super(id);
/* 55 */     addObjectives(new Objective[] { this.objective01, this.objective02 });
/* 56 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 61 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 63 */     props.addUnlockedAbility(SanbyakurokujuPoundHoAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 65 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\swordsman\SwordsmanTrial03Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */