/*     */ package xyz.pixelatedw.mineminenomi.abilities.doku;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ public class DokuGumoAbility extends Ability {
/*  35 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "doku_gumo", new Pair[] {
/*  36 */         (Pair)ImmutablePair.of("Creates a dense cloud of poisonous smoke, which moves along with the user and poisons and blinds everyone inside.", null)
/*     */       });
/*  38 */   private static final UUID VENOM_RANGE_BONUS_UUID = UUID.fromString("77d2625c-c112-4ebf-9f1e-01311c66968b");
/*     */   
/*  40 */   private static final ITextComponent NORMAL_NAME = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.doku_gumo", "Doku Gumo"));
/*  41 */   private static final ITextComponent VENOM_NAME = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.doku_gumo_venom", "Venom Doku Gumo"));
/*     */   
/*  43 */   private static final ResourceLocation NORMAL_ICON = new ResourceLocation("mineminenomi", "textures/abilities/doku_gumo.png");
/*  44 */   private static final ResourceLocation VENOM_ICON = new ResourceLocation("mineminenomi", "textures/abilities/doku_gumo_venom.png");
/*     */   
/*     */   private static final int COOLDOWN = 600;
/*     */   
/*     */   private static final int HOLD_TIME = 400;
/*     */   private static final int RANGE = 10;
/*  50 */   public static final AbilityCore<DokuGumoAbility> INSTANCE = (new AbilityCore.Builder("Doku Gumo", AbilityCategory.DEVIL_FRUITS, DokuGumoAbility::new))
/*  51 */     .addDescriptionLine(DESCRIPTION)
/*  52 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(600.0F), ContinuousComponent.getTooltip(400.0F), RangeComponent.getTooltip(10.0F, RangeComponent.RangeType.AOE)
/*  53 */       }).setSourceElement(SourceElement.POISON)
/*  54 */     .build();
/*     */   
/*  56 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  57 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  58 */   private final AltModeComponent<Mode> altModeComponent = (new AltModeComponent((IAbility)this, Mode.class, Mode.NORMAL, true)).addChangeModeEvent(this::onAltModeChange);
/*     */   
/*     */   public DokuGumoAbility(AbilityCore<DokuGumoAbility> core) {
/*  61 */     super(core);
/*     */     
/*  63 */     this.isNew = true;
/*  64 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.altModeComponent });
/*     */     
/*  66 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  70 */     this.continuousComponent.triggerContinuity(entity, 400.0F);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity player, IAbility ability) {
/*  74 */     int power = 0;
/*  75 */     int duration = 100;
/*  76 */     boolean hasVenomDemon = false;
/*  77 */     int poisonIntensity = 0;
/*     */     
/*  79 */     if (((MorphInfo)ModMorphs.VENOM_DEMON.get()).isActive(player)) {
/*  80 */       hasVenomDemon = true;
/*  81 */       power += 2;
/*  82 */       duration *= 2;
/*  83 */       this.rangeComponent.getBonusManager().addBonus(VENOM_RANGE_BONUS_UUID, "Venom Range Bonus", BonusOperation.MUL, 2.0F);
/*  84 */       poisonIntensity = 1;
/*     */     } 
/*     */     
/*  87 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(player, 10.0F);
/*     */     
/*  89 */     for (LivingEntity target : targets) {
/*  90 */       if (!target.func_70644_a(Effects.field_76440_q)) {
/*  91 */         target.func_195064_c(new EffectInstance(Effects.field_76440_q, duration, power));
/*     */       }
/*  93 */       if (!target.func_70644_a((Effect)ModEffects.DOKU_POISON.get())) {
/*  94 */         target.func_195064_c(new EffectInstance((Effect)ModEffects.DOKU_POISON.get(), duration, poisonIntensity));
/*     */       }
/*  96 */       if (!target.func_70644_a(Effects.field_76437_t)) {
/*  97 */         target.func_195064_c(new EffectInstance(Effects.field_76437_t, duration, power));
/*     */       }
/*     */     } 
/*     */     
/* 101 */     if (this.continuousComponent.getContinueTime() % 2.0F == 0.0F) {
/* 102 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.DOKU_GUMO.get(), (Entity)player, player.func_226277_ct_(), player.func_226278_cu_(), player.func_226281_cx_(), hasVenomDemon ? (ParticleEffect.Details)DokuHelper.DETAILS : null);
/*     */     }
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity player, IAbility ability) {
/* 107 */     this.cooldownComponent.startCooldown(player, 600.0F);
/*     */   }
/*     */   
/*     */   public void setNormalMode(LivingEntity entity) {
/* 111 */     this.altModeComponent.setMode(entity, Mode.NORMAL);
/*     */   }
/*     */   
/*     */   public void setVenomMode(LivingEntity entity) {
/* 115 */     this.altModeComponent.setMode(entity, Mode.VENOM);
/*     */   }
/*     */   
/*     */   private void onAltModeChange(LivingEntity entity, IAbility ability, Mode mode) {
/* 119 */     if (mode == Mode.VENOM) {
/* 120 */       setDisplayName(VENOM_NAME);
/* 121 */       setDisplayIcon(VENOM_ICON);
/*     */     }
/* 123 */     else if (mode == Mode.NORMAL) {
/* 124 */       setDisplayName(NORMAL_NAME);
/* 125 */       setDisplayIcon(NORMAL_ICON);
/*     */     } 
/*     */   }
/*     */   
/*     */   private enum Mode {
/* 130 */     NORMAL, VENOM;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\doku\DokuGumoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */