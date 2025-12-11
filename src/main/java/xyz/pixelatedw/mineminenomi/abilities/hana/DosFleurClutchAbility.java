/*     */ package xyz.pixelatedw.mineminenomi.abilities.hana;
/*     */ 
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.hana.HanaGenericEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.hana.HanaHandsEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ public class DosFleurClutchAbility
/*     */   extends Ability {
/*     */   private static final int MIL_DISTANCE = 30;
/*     */   private static final float DAMAGE_BONUS = 1.5F;
/*  42 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "dos_fleur_clutch", new Pair[] {
/*  43 */         (Pair)ImmutablePair.of("Sprouts two hands to cover the opponent, and then bends them backward with bone-breaking results.", null), 
/*  44 */         (Pair)ImmutablePair.of("While %s is active it will sprout on every enemy in a %s blocks radius and increase its damage by %s.", new Object[] {
/*     */ 
/*     */             
/*  47 */             MilFleurAbility.INSTANCE, "§a30§r", "§a" + Math.round(Math.abs(-0.5F) * 100.0F) + "%§r"
/*     */           })
/*     */       });
/*  50 */   private static final TranslationTextComponent NORMAL_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.dos_fleur_clutch", "Dos Fleur: Clutch"));
/*  51 */   private static final TranslationTextComponent MIL_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.mil_fleur_clutch", "Mil Fleur: Clutch"));
/*     */   
/*  53 */   private static final ResourceLocation NORMAL_ICON = new ResourceLocation("mineminenomi", "textures/abilities/dos_fleur_clutch.png");
/*  54 */   private static final ResourceLocation MIL_ICON = new ResourceLocation("mineminenomi", "textures/abilities/mil_fleur_clutch.png");
/*     */   
/*     */   private static final int COOLDOWN = 120;
/*     */   
/*     */   private static final int NORMAL_DAMAGE = 10;
/*  59 */   public static final AbilityCore<DosFleurClutchAbility> INSTANCE = (new AbilityCore.Builder("Dos Fleur: Clutch", AbilityCategory.DEVIL_FRUITS, DosFleurClutchAbility::new))
/*  60 */     .addDescriptionLine(DESCRIPTION)
/*  61 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(120.0F), DealDamageComponent.getTooltip(10.0F)
/*  62 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  63 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  64 */       }).build();
/*     */   
/*  66 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*  67 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  68 */   private final AltModeComponent<MilFleurAbility.Mode> altModeComponent = (new AltModeComponent((IAbility)this, MilFleurAbility.Mode.class, MilFleurAbility.Mode.NORMAL, true)).addChangeModeEvent(this::onAltModeChange);
/*  69 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  70 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*     */   
/*     */   public DosFleurClutchAbility(AbilityCore<DosFleurClutchAbility> ability) {
/*  73 */     super(ability);
/*     */     
/*  75 */     this.isNew = true;
/*  76 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.altModeComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent });
/*     */     
/*  78 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  82 */     this.animationComponent.start(entity, ModAnimations.CROSSED_ARMS, 7);
/*     */     
/*  84 */     MilFleurAbility milFleur = (MilFleurAbility)AbilityDataCapability.get(entity).getEquippedAbility(MilFleurAbility.INSTANCE);
/*  85 */     boolean hasMilFleur = (milFleur != null && milFleur.isContinuous());
/*     */     
/*  87 */     this.dealDamageComponent.getBonusManager().removeBonus(HanaHelper.MIL_DAMAGE_BONUS);
/*  88 */     if (hasMilFleur) {
/*  89 */       this.dealDamageComponent.getBonusManager().addBonus(HanaHelper.MIL_DAMAGE_BONUS, "Mil Fleur Damage Bonus", BonusOperation.MUL, 1.5F);
/*     */       
/*  91 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 30.0F);
/*  92 */       for (LivingEntity target : targets) {
/*  93 */         clutch(entity, (Entity)target);
/*     */       }
/*     */     } else {
/*     */       
/*  97 */       this.projectileComponent.shoot(entity, 6.0F, 0.0F);
/*     */     } 
/*     */     
/* 100 */     HanaHelper.spawnBlossomEffect(entity);
/*     */     
/* 102 */     this.cooldownComponent.startCooldown(entity, 120.0F);
/*     */   }
/*     */   
/*     */   private HanaGenericEntity createProjectile(LivingEntity entity) {
/* 106 */     HanaGenericEntity proj = new HanaGenericEntity(entity.field_70170_p, entity, this);
/* 107 */     proj.onEntityImpactEvent = (target -> clutch(entity, (Entity)target));
/*     */ 
/*     */     
/* 110 */     return proj;
/*     */   }
/*     */   
/*     */   private void clutch(LivingEntity entity, @Nullable Entity targetEntity) {
/* 114 */     if (targetEntity != null && targetEntity instanceof LivingEntity) {
/* 115 */       LivingEntity target = (LivingEntity)targetEntity;
/*     */       
/* 117 */       target.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 20, 1));
/* 118 */       target.func_195064_c(new EffectInstance((Effect)ModEffects.HANA_HANDS.get(), 20, 0, false, false));
/*     */       
/* 120 */       HanaHandsEntity clutch = new HanaHandsEntity(entity.field_70170_p, this);
/*     */       
/* 122 */       clutch.setWarmup(5);
/* 123 */       clutch.setCaster(entity);
/* 124 */       clutch.setTarget(target);
/* 125 */       clutch.setDamage(10.0F);
/* 126 */       entity.field_70170_p.func_217376_c((Entity)clutch);
/*     */       
/* 128 */       HanaHelper.spawnBlossomEffect(target);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onAltModeChange(LivingEntity entity, IAbility ability, MilFleurAbility.Mode mode) {
/* 133 */     if (mode == MilFleurAbility.Mode.NORMAL) {
/* 134 */       setDisplayName((ITextComponent)NORMAL_NAME);
/* 135 */       setDisplayIcon(NORMAL_ICON);
/*     */     }
/* 137 */     else if (mode == MilFleurAbility.Mode.MIL_FLEUR) {
/* 138 */       setDisplayName((ITextComponent)MIL_NAME);
/* 139 */       setDisplayIcon(MIL_ICON);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void switchNormalMode(LivingEntity entity) {
/* 144 */     this.altModeComponent.setMode(entity, MilFleurAbility.Mode.NORMAL);
/*     */   }
/*     */   
/*     */   public void switchMilMode(LivingEntity entity) {
/* 148 */     this.altModeComponent.setMode(entity, MilFleurAbility.Mode.MIL_FLEUR);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\hana\DosFleurClutchAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */