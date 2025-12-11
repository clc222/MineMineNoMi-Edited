/*    */ package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.ArtOfWeatherHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.WeatherBallKind;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ 
/*    */ public class CycloneTempo extends ChargedTempoAbility {
/* 28 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "cyclone_tempo", new Pair[] {
/* 29 */         (Pair)ImmutablePair.of("Used to create a small cyclone surrounding the user, pushes enemies away from the user while also providing a small protection against physical damage", null)
/*    */       });
/*    */   
/*    */   private static final float RANGE = 6.0F;
/*    */   private static final int HOLD_TIME = 200;
/* 34 */   public static final AbilityCore<CycloneTempo> INSTANCE = (new AbilityCore.Builder("Cyclone Tempo", AbilityCategory.STYLE, AbilityType.PASSIVE, CycloneTempo::new))
/* 35 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { CHARGED_TEMPO_DESCRIPTION
/* 36 */       }).addDescriptionLine(DESCRIPTION)
/* 37 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, ContinuousComponent.getTooltip(200.0F), RangeComponent.getTooltip(6.0F, RangeComponent.RangeType.AOE)
/* 38 */       }).setUnlockCheck(CycloneTempo::canUnlock)
/* 39 */     .build();
/*    */   
/* 41 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*    */   
/*    */   public CycloneTempo(AbilityCore<CycloneTempo> core) {
/* 44 */     super(core);
/*    */     
/* 46 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.rangeComponent });
/*    */     
/* 48 */     addCanUseCheck(ArtOfWeatherHelper::needsSorceryClimaTact);
/*    */   }
/*    */ 
/*    */   
/*    */   public WeatherBallKind[] getTempoOrder() {
/* 53 */     return new WeatherBallKind[] { WeatherBallKind.COOL, WeatherBallKind.COOL, WeatherBallKind.COOL };
/*    */   }
/*    */ 
/*    */   
/*    */   public void useTempo(LivingEntity entity) {
/* 58 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.CYCLONE_TEMPO.get(), 200, 0));
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 62 */     if (!(entity instanceof PlayerEntity)) {
/* 63 */       return false;
/*    */     }
/*    */     
/* 66 */     PlayerEntity player = (PlayerEntity)entity;
/* 67 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 68 */     IQuestData questProps = QuestDataCapability.get(player);
/*    */     
/* 70 */     return (props.isWeatherWizard() && questProps.hasFinishedQuest(ModQuests.ART_OF_WEATHER_TRIAL_04));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\tempos\CycloneTempo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */