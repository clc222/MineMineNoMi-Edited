/*    */ package xyz.pixelatedw.mineminenomi.quests.artofweather;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.CoolBallAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.HeatBallAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.WeatherCloudTempo;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
/*    */ 
/*    */ public class ArtOfWeatherTrial01Quest extends Quest {
/* 18 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Heat Ball & Cool Ball", ArtOfWeatherTrial01Quest::new))
/* 19 */     .build();
/*    */   
/* 21 */   private Objective objective01 = (Objective)new ObtainItemObjective("Obtain a Clima Tact", 1, (Supplier)ModWeapons.CLIMA_TACT);
/*    */ 
/*    */   
/*    */   public ArtOfWeatherTrial01Quest(QuestId id) {
/* 25 */     super(id);
/* 26 */     addObjectives(new Objective[] { this.objective01 });
/*    */     
/* 28 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 33 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 35 */     props.addUnlockedAbility(HeatBallAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/* 36 */     props.addUnlockedAbility(CoolBallAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/* 37 */     props.addUnlockedAbility(WeatherCloudTempo.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 39 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\artofweather\ArtOfWeatherTrial01Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */