/*     */ package xyz.pixelatedw.mineminenomi.abilities.rokushiki;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ 
/*     */ public class TekkaiAbility extends Ability {
/*  30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "tekkai", new Pair[] {
/*  31 */         (Pair)ImmutablePair.of("Hardens the user's body to protect themselves, but they're unable to move", null), 
/*  32 */         (Pair)ImmutablePair.of("  §aHEAVY§r immobile, more protection", null), 
/*  33 */         (Pair)ImmutablePair.of("  §aWALK§r can move, less protection", null)
/*     */       });
/*  35 */   private static final ResourceLocation TEKKAI_WALK_ICON = new ResourceLocation("mineminenomi", "textures/abilities/tekkai_walk.png");
/*     */   
/*     */   private static final int CONTINUITY_THRESHOLD = 200;
/*     */   private static final int MIN_COOLDOWN = 60;
/*     */   private static final int MAX_COOLDOWN = 260;
/*  40 */   public static final AbilityCore<TekkaiAbility> INSTANCE = (new AbilityCore.Builder("Tekkai", AbilityCategory.RACIAL, TekkaiAbility::new))
/*  41 */     .addDescriptionLine(DESCRIPTION)
/*  42 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(60.0F, 260.0F), ContinuousComponent.getTooltip(200.0F)
/*  43 */       }).setUnlockCheck(TekkaiAbility::canUnlock)
/*  44 */     .build();
/*     */   
/*  46 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(100, this::onContinuityStart).addTickEvent(this::onContinuityTick).addEndEvent(this::onContinuityEnd);
/*     */   
/*  48 */   private final AltModeComponent<Mode> altModeComponent = (new AltModeComponent((IAbility)this, Mode.class, Mode.HEAVY)).addChangeModeEvent(this::onAltModeChange);
/*     */   
/*  50 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*  52 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.TEKKAI_LIKE, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*     */   
/*     */   public TekkaiAbility(AbilityCore<TekkaiAbility> core) {
/*  55 */     super(core);
/*     */     
/*  57 */     this.isNew = true;
/*     */     
/*  59 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.altModeComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.poolComponent });
/*     */     
/*  61 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  65 */     this.continuousComponent.triggerContinuity(entity, 200.0F);
/*     */   }
/*     */   
/*     */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/*  69 */     this.animationComponent.start(entity, ModAnimations.CROSSED_ARMS);
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/*  73 */     if (this.altModeComponent.isMode(Mode.HEAVY)) {
/*  74 */       entity.func_195064_c(new EffectInstance((Effect)ModEffects.GUARDING.get(), 2, 1, false, false));
/*     */       
/*  76 */       AbilityHelper.setDeltaMovement((Entity)entity, 0.0D, -5.0D, 0.0D);
/*     */     } else {
/*  78 */       entity.func_195064_c(new EffectInstance((Effect)ModEffects.PHYSICAL_MOVING_GUARD.get(), 2, 0, false, false));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/*  83 */     this.animationComponent.stop(entity);
/*  84 */     this.cooldownComponent.startCooldown(entity, this.continuousComponent.getContinueTime() + 60.0F);
/*     */   }
/*     */   
/*     */   private void onAltModeChange(LivingEntity entity, IAbility ability, Mode mode) {
/*  88 */     if (mode == Mode.HEAVY) {
/*  89 */       setDisplayIcon(INSTANCE);
/*  90 */     } else if (mode == Mode.WALK) {
/*  91 */       setDisplayIcon(TEKKAI_WALK_ICON);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void switchToHeavyMode(LivingEntity entity) {
/*  96 */     this.altModeComponent.setMode(entity, Mode.HEAVY);
/*     */   }
/*     */   
/*     */   public void switchToWalkMode(LivingEntity entity) {
/* 100 */     this.altModeComponent.setMode(entity, Mode.WALK);
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity user) {
/* 104 */     IEntityStats props = EntityStatsCapability.get(user);
/* 105 */     boolean raceCheck = (props.isHuman() || DevilFruitCapability.get(user).hasDevilFruit(ModAbilities.HITO_HITO_NO_MI));
/* 106 */     return (raceCheck && props.getDoriki() >= 1000.0D);
/*     */   }
/*     */   
/*     */   public enum Mode {
/* 110 */     HEAVY, WALK;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\rokushiki\TekkaiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */