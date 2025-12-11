/*     */ package xyz.pixelatedw.mineminenomi.abilities.doku;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RepeaterComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.doku.ChloroBallProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ public class ChloroBallAbility extends Ability {
/*  28 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "chloro_ball", new Pair[] {
/*  29 */         (Pair)ImmutablePair.of("The user spits a bubble made of poison towards the enemy, which also leaves poison on the ground.", null)
/*     */       });
/*  31 */   private static final ITextComponent NORMAL_NAME = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.chloro_ball", "Chloro Ball"));
/*  32 */   private static final ITextComponent VENOM_NAME = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.chloro_ball_venom", "Venom Chloro Ball"));
/*     */   
/*  34 */   private static final ResourceLocation NORMAL_ICON = new ResourceLocation("mineminenomi", "textures/abilities/chloro_ball.png");
/*  35 */   private static final ResourceLocation VENOM_ICON = new ResourceLocation("mineminenomi", "textures/abilities/chloro_ball_venom.png");
/*     */   
/*     */   private static final int COOLDOWN = 180;
/*     */   
/*  39 */   public static final AbilityCore<ChloroBallAbility> INSTANCE = (new AbilityCore.Builder("Chloro Ball", AbilityCategory.DEVIL_FRUITS, ChloroBallAbility::new))
/*  40 */     .addDescriptionLine(DESCRIPTION)
/*  41 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(180.0F)
/*  42 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/*  43 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/*  44 */     .setSourceElement(SourceElement.POISON)
/*  45 */     .build();
/*     */   
/*  47 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  48 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(this::triggerRepeaterEvent).addStopEvent(this::endRepeaterEvent);
/*  49 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*  50 */   private final AltModeComponent<Mode> altModeComponent = (new AltModeComponent((IAbility)this, Mode.class, Mode.NORMAL, true)).addChangeModeEvent(this::onAltModeChange);
/*     */   
/*     */   public ChloroBallAbility(AbilityCore<ChloroBallAbility> core) {
/*  53 */     super(core);
/*     */     
/*  55 */     this.isNew = true;
/*  56 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.repeaterComponent, (AbilityComponent)this.projectileComponent, (AbilityComponent)this.altModeComponent });
/*     */     
/*  58 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  62 */     this.continuousComponent.triggerContinuity(entity);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  66 */     boolean isDemonForm = ((MorphInfo)ModMorphs.VENOM_DEMON.get()).isActive(entity);
/*  67 */     if (isDemonForm) {
/*  68 */       this.repeaterComponent.start(entity, 1, 5);
/*     */     } else {
/*     */       
/*  71 */       this.repeaterComponent.start(entity, 1, 5);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void triggerRepeaterEvent(LivingEntity entity, IAbility ability) {
/*  76 */     this.projectileComponent.shoot(entity, 2.0F, 1.0F);
/*     */   }
/*     */   
/*     */   private void endRepeaterEvent(LivingEntity entity, IAbility ability) {
/*  80 */     this.continuousComponent.stopContinuity(entity);
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  84 */     this.cooldownComponent.startCooldown(entity, 180.0F);
/*     */   }
/*     */   
/*     */   private ChloroBallProjectile createProjectile(LivingEntity entity) {
/*  88 */     boolean isDemonForm = ((MorphInfo)ModMorphs.VENOM_DEMON.get()).isActive(entity);
/*  89 */     ChloroBallProjectile proj = new ChloroBallProjectile(entity.field_70170_p, entity, isDemonForm);
/*  90 */     return proj;
/*     */   }
/*     */   
/*     */   public void setNormalMode(LivingEntity entity) {
/*  94 */     this.altModeComponent.setMode(entity, Mode.NORMAL);
/*     */   }
/*     */   
/*     */   public void setVenomMode(LivingEntity entity) {
/*  98 */     this.altModeComponent.setMode(entity, Mode.VENOM);
/*     */   }
/*     */   
/*     */   private void onAltModeChange(LivingEntity entity, IAbility ability, Mode mode) {
/* 102 */     if (mode == Mode.VENOM) {
/* 103 */       setDisplayName(VENOM_NAME);
/* 104 */       setDisplayIcon(VENOM_ICON);
/*     */     }
/* 106 */     else if (mode == Mode.NORMAL) {
/* 107 */       setDisplayName(NORMAL_NAME);
/* 108 */       setDisplayIcon(NORMAL_ICON);
/*     */     } 
/*     */   }
/*     */   
/*     */   private enum Mode {
/* 113 */     NORMAL, VENOM;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\doku\ChloroBallAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */