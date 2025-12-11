/*     */ package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.ArtOfWeatherHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.WeatherBallKind;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
/*     */ 
/*     */ public class HeatEggTempo extends ChargedTempoAbility {
/*  28 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "heat_egg_tempo", new Pair[] {
/*  29 */         (Pair)ImmutablePair.of("Used to imbue the Clima Tact with Heat Balls, while active it doubles the physical damage dealt by the clima tact", null)
/*     */       });
/*     */   
/*     */   private static final int HOLD_TIME = 200;
/*  33 */   public static final AbilityCore<HeatEggTempo> INSTANCE = (new AbilityCore.Builder("Heat Egg Tempo", AbilityCategory.STYLE, AbilityType.PASSIVE, HeatEggTempo::new))
/*  34 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { CHARGED_TEMPO_DESCRIPTION
/*  35 */       }).addDescriptionLine(DESCRIPTION)
/*  36 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, ContinuousComponent.getTooltip(200.0F)
/*  37 */       }).setUnlockCheck(HeatEggTempo::canUnlock)
/*  38 */     .build();
/*     */   
/*  40 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(100, this::startContinuousEvent).addTickEvent(100, this::tickContinuousEvent).addEndEvent(100, this::endContinuousEvent);
/*     */   
/*  42 */   private ItemStack climaTactRef = ItemStack.field_190927_a;
/*     */   
/*     */   public HeatEggTempo(AbilityCore<HeatEggTempo> core) {
/*  45 */     super(core);
/*     */     
/*  47 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent });
/*     */     
/*  49 */     addCanUseCheck(ArtOfWeatherHelper::needsSorceryClimaTact);
/*     */   }
/*     */ 
/*     */   
/*     */   public WeatherBallKind[] getTempoOrder() {
/*  54 */     return new WeatherBallKind[] { WeatherBallKind.HEAT, WeatherBallKind.HEAT, WeatherBallKind.HEAT };
/*     */   }
/*     */ 
/*     */   
/*     */   public void useTempo(LivingEntity entity) {
/*  59 */     this.continuousComponent.startContinuity(entity);
/*     */   }
/*     */   
/*     */   private void startContinuousEvent(LivingEntity entity, IAbility ability) {
/*  63 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  67 */     this.climaTactRef = entity.func_184614_ca();
/*  68 */     ClimaTactItem.setDamageModifier(this.climaTactRef, 2.0D);
/*  69 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.HEAT_EGG_TEMPO.get(), 200, 0));
/*     */   }
/*     */   
/*     */   private void tickContinuousEvent(LivingEntity entity, IAbility ability) {
/*  73 */     if (this.continuousComponent.getContinueTime() >= 200.0F) {
/*  74 */       ItemStack mainHand = entity.func_184614_ca();
/*  75 */       if (!this.climaTactRef.func_190926_b() && mainHand.equals(this.climaTactRef)) {
/*  76 */         this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       }
/*  79 */       else if (!mainHand.func_190926_b() && mainHand.func_77973_b() instanceof ClimaTactItem) {
/*  80 */         this.climaTactRef = entity.func_184614_ca();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void endContinuousEvent(LivingEntity entity, IAbility ability) {
/*  87 */     ClimaTactItem.setDamageModifier(this.climaTactRef, 1.0D);
/*  88 */     this.climaTactRef = ItemStack.field_190927_a;
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/*  92 */     if (!(entity instanceof PlayerEntity)) {
/*  93 */       return false;
/*     */     }
/*     */     
/*  96 */     PlayerEntity player = (PlayerEntity)entity;
/*  97 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*  98 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 100 */     return (props.isWeatherWizard() && questProps.hasFinishedQuest(ModQuests.ART_OF_WEATHER_TRIAL_04));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\tempos\HeatEggTempo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */