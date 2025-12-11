/*     */ package xyz.pixelatedw.mineminenomi.abilities.doru;
/*     */ 
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RepeaterComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.doru.ChampFightProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.doru.DoruDoruArtsMoriProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ public class DoruDoruArtsMoriAbility extends Ability {
/*  31 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "doru_doru_arts_mori", new Pair[] {
/*  32 */         (Pair)ImmutablePair.of("The user fires a harpoon made of wax at the opponent", null)
/*     */       });
/*  34 */   private static final TranslationTextComponent CHAMP_FIGHT_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.champ_fight", "Champ Fight"));
/*     */   
/*  36 */   private static final ResourceLocation CHAMP_FIGHT_ICON = new ResourceLocation("mineminenomi", "textures/abilities/champ_fight.png");
/*     */   
/*     */   private static final float COOLDOWN = 80.0F;
/*     */   
/*  40 */   public static final AbilityCore<DoruDoruArtsMoriAbility> INSTANCE = (new AbilityCore.Builder("Doru Doru Arts: Mori", AbilityCategory.DEVIL_FRUITS, DoruDoruArtsMoriAbility::new))
/*  41 */     .addDescriptionLine(DESCRIPTION)
/*  42 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(80.0F)
/*  43 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/*  44 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/*  45 */     .setSourceElement(SourceElement.WAX)
/*  46 */     .build();
/*     */   
/*  48 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent);
/*  49 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(100, this::repeaterTriggerEvent).addStopEvent(100, this::repeaterStopEvent);
/*  50 */   private final AltModeComponent<Mode> altModeComponent = (new AltModeComponent((IAbility)this, Mode.class, Mode.MORI)).addChangeModeEvent(100, this::changeModeEvent);
/*  51 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*  52 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*     */   public DoruDoruArtsMoriAbility(AbilityCore<DoruDoruArtsMoriAbility> core) {
/*  55 */     super(core);
/*     */     
/*  57 */     this.isNew = true;
/*  58 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.repeaterComponent, (AbilityComponent)this.altModeComponent, (AbilityComponent)this.projectileComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  60 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  64 */     if (this.continuousComponent.isContinuous()) {
/*  65 */       this.repeaterComponent.stop(entity);
/*     */       return;
/*     */     } 
/*  68 */     this.continuousComponent.triggerContinuity(entity);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  72 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  76 */     this.animationComponent.start(entity, ModAnimations.AIM_SNIPER);
/*     */     
/*  78 */     if (this.altModeComponent.getCurrentMode() == Mode.CHAMP_FIGHT) {
/*  79 */       this.repeaterComponent.start(entity, 25, 1);
/*  80 */     } else if (this.altModeComponent.getCurrentMode() == Mode.MORI) {
/*  81 */       this.repeaterComponent.start(entity, 1, 0);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void repeaterTriggerEvent(LivingEntity entity, IAbility ability) {
/*  86 */     if (((Mode)this.altModeComponent.getCurrentMode()).equals(Mode.CHAMP_FIGHT)) {
/*  87 */       this.projectileComponent.shootWithSpread(entity, 2.0F, 1.0F, 2);
/*     */     } else {
/*     */       
/*  90 */       this.projectileComponent.shoot(entity, 3.0F, 0.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void repeaterStopEvent(LivingEntity entity, IAbility ability) {
/*  95 */     this.animationComponent.stop(entity);
/*  96 */     this.continuousComponent.stopContinuity(entity);
/*  97 */     this.cooldownComponent.startCooldown(entity, 80.0F);
/*     */   }
/*     */   
/*     */   private void changeModeEvent(LivingEntity entity, IAbility ability, Mode mode) {
/* 101 */     if (mode.equals(Mode.CHAMP_FIGHT)) {
/* 102 */       setDisplayIcon(CHAMP_FIGHT_ICON);
/* 103 */       setDisplayName((ITextComponent)CHAMP_FIGHT_NAME);
/*     */     } else {
/*     */       
/* 106 */       setDisplayIcon(INSTANCE);
/* 107 */       setDisplayName((ITextComponent)INSTANCE.getLocalizedName());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void changeToChampFight(LivingEntity entity) {
/* 112 */     this.altModeComponent.setMode(entity, Mode.CHAMP_FIGHT);
/*     */   }
/*     */   
/*     */   public void changeToDefault(LivingEntity entity) {
/* 116 */     this.altModeComponent.setMode(entity, Mode.MORI);
/*     */   }
/*     */   
/*     */   private AbilityProjectileEntity createProjectile(LivingEntity entity) {
/* 120 */     if (((Mode)this.altModeComponent.getCurrentMode()).equals(Mode.CHAMP_FIGHT)) {
/* 121 */       ChampFightProjectile champFightProjectile = new ChampFightProjectile(entity.field_70170_p, entity);
/* 122 */       return (AbilityProjectileEntity)champFightProjectile;
/*     */     } 
/*     */     
/* 125 */     DoruDoruArtsMoriProjectile proj = new DoruDoruArtsMoriProjectile(entity.field_70170_p, entity, this);
/* 126 */     return (AbilityProjectileEntity)proj;
/*     */   }
/*     */   
/*     */   public enum Mode {
/* 130 */     MORI,
/* 131 */     CHAMP_FIGHT;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\doru\DoruDoruArtsMoriAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */