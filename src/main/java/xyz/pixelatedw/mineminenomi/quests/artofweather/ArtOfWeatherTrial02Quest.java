/*    */ package xyz.pixelatedw.mineminenomi.quests.artofweather;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.ThunderBallAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.FogTempo;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.MirageTempo;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.RainTempo;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderboltTempo;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.WeatherCloudTempo;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.UseAbilityObjective;
/*    */ 
/*    */ public class ArtOfWeatherTrial02Quest extends Quest {
/* 21 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Thunder Ball", ArtOfWeatherTrial02Quest::new))
/* 22 */     .addRequirements(new QuestId[] { ModQuests.ART_OF_WEATHER_TRIAL_01
/* 23 */       }).build();
/*    */   
/* 25 */   private Objective objective01 = (Objective)new UseAbilityObjective("Succesfully perform Weather Cloud Tempo 3 times", 3, WeatherCloudTempo.INSTANCE);
/*    */ 
/*    */   
/*    */   public ArtOfWeatherTrial02Quest(QuestId id) {
/* 29 */     super(id);
/* 30 */     addObjectives(new Objective[] { this.objective01 });
/* 31 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 36 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 38 */     props.addUnlockedAbility(ThunderBallAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/* 39 */     props.addUnlockedAbility(ThunderboltTempo.INSTANCE, AbilityUnlock.PROGRESSION);
/* 40 */     props.addUnlockedAbility(RainTempo.INSTANCE, AbilityUnlock.PROGRESSION);
/* 41 */     props.addUnlockedAbility(MirageTempo.INSTANCE, AbilityUnlock.PROGRESSION);
/* 42 */     props.addUnlockedAbility(FogTempo.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 44 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\artofweather\ArtOfWeatherTrial02Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */