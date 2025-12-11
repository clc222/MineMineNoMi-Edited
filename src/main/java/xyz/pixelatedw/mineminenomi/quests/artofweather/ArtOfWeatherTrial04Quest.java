/*    */ package xyz.pixelatedw.mineminenomi.quests.artofweather;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.GustSwordAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.WeatherEggAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.CycloneTempo;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderLanceTempo;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderstormTempo;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.TimedKillEntityObjective;
/*    */ 
/*    */ public class ArtOfWeatherTrial04Quest extends Quest {
/* 27 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Sorcery Clima Tact", ArtOfWeatherTrial04Quest::new))
/* 28 */     .addRequirements(new QuestId[] { ModQuests.ART_OF_WEATHER_TRIAL_03
/* 29 */       }).build(); private static final KillEntityObjective.ICheckKill LIGHTNING_BOLT_KILL_CHECK;
/*    */   static {
/* 31 */     LIGHTNING_BOLT_KILL_CHECK = ((player, target, source) -> (source instanceof AbilityDamageSource) ? ((AbilityDamageSource)source).getAbilitySource().equals(ThunderLanceTempo.INSTANCE) : false);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 39 */     THUNDERSTORM_TEMPO_KILL_CHECK = ((player, target, source) -> (source instanceof AbilityDamageSource) ? ((AbilityDamageSource)source).getAbilitySource().equals(ThunderstormTempo.INSTANCE) : ((source.func_76355_l() == ModDamageSource.field_180137_b.func_76355_l())));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static final KillEntityObjective.ICheckKill THUNDERSTORM_TEMPO_KILL_CHECK;
/*    */ 
/*    */   
/* 47 */   private Objective objective01 = (Objective)new KillEntityObjective("Kill %s enemies using Thunderlance Tempo", 10, LIGHTNING_BOLT_KILL_CHECK);
/* 48 */   private Objective objective02 = (Objective)new TimedKillEntityObjective("Kill at least %s enemies using Thunderstorm Tempo at the same time", 5, 5, THUNDERSTORM_TEMPO_KILL_CHECK);
/*    */ 
/*    */   
/*    */   public ArtOfWeatherTrial04Quest(QuestId id) {
/* 52 */     super(id);
/* 53 */     addObjectives(new Objective[] { this.objective01, this.objective02 });
/* 54 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 59 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 61 */     player.field_71071_by.func_70441_a(new ItemStack((IItemProvider)ModWeapons.SORCERY_CLIMA_TACT.get()));
/*    */     
/* 63 */     props.addUnlockedAbility(GustSwordAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/* 64 */     props.addUnlockedAbility(WeatherEggAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/* 65 */     props.addUnlockedAbility(HeatEggTempo.INSTANCE, AbilityUnlock.PROGRESSION);
/* 66 */     props.addUnlockedAbility(CycloneTempo.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 68 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\artofweather\ArtOfWeatherTrial04Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */