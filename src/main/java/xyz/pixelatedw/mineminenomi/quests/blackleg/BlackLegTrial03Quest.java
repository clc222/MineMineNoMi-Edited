/*    */ package xyz.pixelatedw.mineminenomi.quests.blackleg;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.AntiMannerKickCourseAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.ConcasseAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.ExtraHachisAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.PartyTableKickCourseAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.HitEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.TimedHitEntityObjective;
/*    */ 
/*    */ public class BlackLegTrial03Quest extends Quest {
/* 24 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Courses", BlackLegTrial03Quest::new))
/* 25 */     .addRequirements(new QuestId[] { ModQuests.BLACK_LEG_TRIAL_02
/* 26 */       }).build();
/*    */   static {
/* 28 */     EXTRA_HACHIS_HIT_CHECK = ((player, target, source, amount) -> 
/*    */       
/* 30 */       (source.func_76346_g() instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.blackleg.ExtraHachisProjectile || (source instanceof AbilityDamageSource && ((AbilityDamageSource)source).getAbilitySource().equals(ExtraHachisAbility.INSTANCE))));
/*    */   }
/*    */   private static final HitEntityObjective.ICheckHit EXTRA_HACHIS_HIT_CHECK;
/* 33 */   private Objective objective01 = (Objective)new TimedHitEntityObjective("Hit %s enemies using Extra Hachis at the same time", 3, 3, EXTRA_HACHIS_HIT_CHECK);
/* 34 */   private Objective objective02 = (Objective)new KillEntityObjective("Kill %s enemies using Concasse", 10, SharedKillChecks.IS_KICKING.and(SharedKillChecks.checkAbilitySource(ConcasseAbility.INSTANCE)));
/*    */ 
/*    */   
/*    */   public BlackLegTrial03Quest(QuestId id) {
/* 38 */     super(id);
/* 39 */     addObjectives(new Objective[] { this.objective01, this.objective02 });
/*    */     
/* 41 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 46 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 48 */     props.addUnlockedAbility(AntiMannerKickCourseAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/* 49 */     props.addUnlockedAbility(PartyTableKickCourseAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 51 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\blackleg\BlackLegTrial03Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */