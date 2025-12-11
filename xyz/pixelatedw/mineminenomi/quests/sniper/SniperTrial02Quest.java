/*    */ package xyz.pixelatedw.mineminenomi.quests.sniper;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.HiNoToriBoshiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.KemuriBoshiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.CureEffectObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.HitEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
/*    */ 
/*    */ public class SniperTrial02Quest extends Quest {
/* 24 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Kemuri Boshi", SniperTrial02Quest::new))
/* 25 */     .addRequirements(new QuestId[] { ModQuests.SNIPER_TRIAL_01
/* 26 */       }).build();
/*    */   static {
/* 28 */     KAEN_BOSHI_HIT_CHECK = ((player, target, source, amount) -> 
/*    */       
/* 30 */       (!target.func_230279_az_() && (source.func_76364_f() instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.HiNoToriBoshiProjectile || (source instanceof AbilityDamageSource && ((AbilityDamageSource)source).getAbilitySource().equals(HiNoToriBoshiAbility.INSTANCE)))));
/*    */   }
/*    */   private static final HitEntityObjective.ICheckHit KAEN_BOSHI_HIT_CHECK;
/* 33 */   private Objective objective01 = (Objective)new ObtainItemObjective("Collect %s spider eyes", 30, () -> Items.field_151070_bp);
/* 34 */   private Objective objective02 = (new HitEntityObjective("Set %s enemies on fire using Kaen Boshi", 20, KAEN_BOSHI_HIT_CHECK)).addRequirement(this.objective01);
/* 35 */   private Objective objective03 = (new CureEffectObjective("Cure yourself of Poison", 1, Effects.field_76436_u)).addRequirement(this.objective01);
/*    */ 
/*    */   
/*    */   public SniperTrial02Quest(QuestId id) {
/* 39 */     super(id);
/* 40 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/* 41 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 46 */     if (!removeQuestItem(player, Items.field_151070_bp, 30)) {
/* 47 */       return false;
/*    */     }
/* 49 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 51 */     props.addUnlockedAbility(KemuriBoshiAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 53 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\sniper\SniperTrial02Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */