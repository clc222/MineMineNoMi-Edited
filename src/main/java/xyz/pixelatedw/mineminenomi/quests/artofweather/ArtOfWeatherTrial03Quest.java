/*    */ package xyz.pixelatedw.mineminenomi.quests.artofweather;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderLanceTempo;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderboltTempo;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderstormTempo;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
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
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.UseAbilityObjective;
/*    */ 
/*    */ public class ArtOfWeatherTrial03Quest
/*    */   extends Quest {
/* 32 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Tempo Training", ArtOfWeatherTrial03Quest::new))
/* 33 */     .addRequirements(new QuestId[] { ModQuests.ART_OF_WEATHER_TRIAL_02
/* 34 */       }).build();
/*    */   
/* 36 */   private static final HashMap<UUID, List<IAbility>> USED_TEMPOS = new HashMap<>();
/*    */   static {
/* 38 */     UNIQUE_TEMPO_CHECK = ((player, ability) -> {
/*    */         if (ability instanceof xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility) {
/*    */           if (!USED_TEMPOS.containsKey(player.func_110124_au())) {
/*    */             USED_TEMPOS.put(player.func_110124_au(), new ArrayList<>());
/*    */           }
/*    */           
/*    */           List<IAbility> usedAbilities = USED_TEMPOS.get(player.func_110124_au());
/*    */           
/*    */           if (!usedAbilities.contains(ability)) {
/*    */             usedAbilities.add(ability);
/*    */             
/*    */             return true;
/*    */           } 
/*    */         } 
/*    */         
/*    */         return false;
/*    */       });
/*    */     
/* 56 */     THUNDERBOLT_TEMPO_KILL_CHECK = ((player, target, source) -> (source instanceof AbilityDamageSource) ? ((AbilityDamageSource)source).getAbilitySource().equals(ThunderboltTempo.INSTANCE) : ((source.func_76355_l() == ModDamageSource.field_180137_b.func_76355_l())));
/*    */   }
/*    */ 
/*    */   
/*    */   private static final UseAbilityObjective.ICheckAbilityUse UNIQUE_TEMPO_CHECK;
/*    */   
/*    */   private static final KillEntityObjective.ICheckKill THUNDERBOLT_TEMPO_KILL_CHECK;
/*    */   
/* 64 */   private Objective objective01 = (Objective)new ObtainItemObjective("Obtain a Perfect Clima Tact", 1, (Supplier)ModWeapons.PERFECT_CLIMA_TACT);
/* 65 */   private Objective objective02 = (new UseAbilityObjective("Perform 3 unique Tempos", 3, UNIQUE_TEMPO_CHECK)).addRequirement(this.objective01);
/* 66 */   private Objective objective03 = (new KillEntityObjective("Kill %s enemies using Thunderbolt Tempo", 20, THUNDERBOLT_TEMPO_KILL_CHECK)).addRequirement(this.objective01);
/*    */ 
/*    */   
/*    */   public ArtOfWeatherTrial03Quest(QuestId id) {
/* 70 */     super(id);
/* 71 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/*    */     
/* 73 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 78 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 80 */     props.addUnlockedAbility(ThunderstormTempo.INSTANCE, AbilityUnlock.PROGRESSION);
/* 81 */     props.addUnlockedAbility(ThunderLanceTempo.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 83 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\artofweather\ArtOfWeatherTrial03Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */