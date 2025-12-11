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
/*     */ import net.minecraft.util.math.vector.Vector3d;
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
/*     */ public class SeisFleurSlapAbility
/*     */   extends Ability {
/*     */   private static final int MIL_DISTANCE = 20;
/*     */   private static final float DAMAGE_BONUS = 1.25F;
/*  43 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "seis_fleur_slap", new Pair[] {
/*  44 */         (Pair)ImmutablePair.of("Slaps the enemy pushing them few blocks back and dealing some damage.", null), 
/*  45 */         (Pair)ImmutablePair.of("While %s is active it will sprout on every enemy in a %s blocks radius and increase its damage by %s.", new Object[] {
/*     */ 
/*     */             
/*  48 */             MilFleurAbility.INSTANCE, "§a20§r", "§a" + Math.round(Math.abs(-0.25F) * 100.0F) + "%§r"
/*     */           })
/*     */       });
/*  51 */   private static final TranslationTextComponent NORMAL_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.seis_fleur_slap", "Seis Fleur: Slap"));
/*  52 */   private static final TranslationTextComponent MIL_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.mil_fleur_slap", "Mil Fleur: Slap"));
/*     */   
/*  54 */   private static final ResourceLocation NORMAL_ICON = new ResourceLocation("mineminenomi", "textures/abilities/seis_fleur_slap.png");
/*  55 */   private static final ResourceLocation MIL_ICON = new ResourceLocation("mineminenomi", "textures/abilities/mil_fleur_slap.png");
/*     */   
/*     */   private static final int COOLDOWN = 100;
/*     */   
/*     */   private static final int NORMAL_DAMAGE = 6;
/*  60 */   public static final AbilityCore<SeisFleurSlapAbility> INSTANCE = (new AbilityCore.Builder("Seis Fleur: Slap", AbilityCategory.DEVIL_FRUITS, SeisFleurSlapAbility::new))
/*  61 */     .addDescriptionLine(DESCRIPTION)
/*  62 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F), DealDamageComponent.getTooltip(6.0F)
/*  63 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  64 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  65 */       }).build();
/*     */   
/*  67 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*  68 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  69 */   private final AltModeComponent<MilFleurAbility.Mode> altModeComponent = (new AltModeComponent((IAbility)this, MilFleurAbility.Mode.class, MilFleurAbility.Mode.NORMAL, true)).addChangeModeEvent(this::onAltModeChange);
/*  70 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  71 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*     */   
/*     */   public SeisFleurSlapAbility(AbilityCore<SeisFleurSlapAbility> ability) {
/*  74 */     super(ability);
/*     */     
/*  76 */     this.isNew = true;
/*  77 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.altModeComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent });
/*     */     
/*  79 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  83 */     this.animationComponent.start(entity, ModAnimations.CROSSED_ARMS, 7);
/*     */     
/*  85 */     MilFleurAbility milFleur = (MilFleurAbility)AbilityDataCapability.get(entity).getEquippedAbility(MilFleurAbility.INSTANCE);
/*  86 */     boolean hasMilFleur = (milFleur != null && milFleur.isContinuous());
/*     */     
/*  88 */     this.dealDamageComponent.getBonusManager().removeBonus(HanaHelper.MIL_DAMAGE_BONUS);
/*  89 */     if (hasMilFleur) {
/*  90 */       this.dealDamageComponent.getBonusManager().addBonus(HanaHelper.MIL_DAMAGE_BONUS, "Mil Fleur Damage Bonus", BonusOperation.MUL, 1.25F);
/*     */       
/*  92 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 20.0F);
/*  93 */       for (LivingEntity target : targets) {
/*  94 */         slap(entity, (Entity)target);
/*     */       }
/*     */     } else {
/*     */       
/*  98 */       this.projectileComponent.shoot(entity, 6.0F, 0.0F);
/*     */     } 
/*     */     
/* 101 */     HanaHelper.spawnBlossomEffect(entity);
/*     */     
/* 103 */     this.cooldownComponent.startCooldown(entity, 100.0F);
/*     */   }
/*     */   
/*     */   private HanaGenericEntity createProjectile(LivingEntity entity) {
/* 107 */     HanaGenericEntity proj = new HanaGenericEntity(entity.field_70170_p, entity, this);
/* 108 */     proj.onEntityImpactEvent = (target -> slap(entity, (Entity)target));
/*     */ 
/*     */     
/* 111 */     return proj;
/*     */   }
/*     */   
/*     */   private void slap(LivingEntity entity, @Nullable Entity targetEntity) {
/* 115 */     if (targetEntity != null && targetEntity instanceof LivingEntity) {
/* 116 */       LivingEntity target = (LivingEntity)targetEntity;
/*     */       
/* 118 */       HanaHandsEntity slap = new HanaHandsEntity(entity.field_70170_p, this);
/* 119 */       slap.setWarmup(0);
/* 120 */       slap.setCaster(entity);
/* 121 */       slap.setTarget(target);
/* 122 */       slap.setDamage(6.0F);
/* 123 */       slap.setSlap();
/* 124 */       entity.field_70170_p.func_217376_c((Entity)slap);
/*     */       
/* 126 */       Vector3d dirVec = entity.func_213303_ch().func_178788_d(target.func_213303_ch()).func_72432_b();
/* 127 */       AbilityHelper.setDeltaMovement((Entity)target, -dirVec.field_72450_a * 3.0D, entity.func_213322_ci().func_82617_b() + 0.1D, -dirVec.field_72449_c * 3.0D);
/*     */       
/* 129 */       target.func_195064_c(new EffectInstance((Effect)ModEffects.HANA_HANDS.get(), 5, 0, false, false));
/*     */       
/* 131 */       HanaHelper.spawnBlossomEffect(target);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onAltModeChange(LivingEntity entity, IAbility ability, MilFleurAbility.Mode mode) {
/* 136 */     if (mode == MilFleurAbility.Mode.NORMAL) {
/* 137 */       setDisplayName((ITextComponent)NORMAL_NAME);
/* 138 */       setDisplayIcon(NORMAL_ICON);
/*     */     }
/* 140 */     else if (mode == MilFleurAbility.Mode.MIL_FLEUR) {
/* 141 */       setDisplayName((ITextComponent)MIL_NAME);
/* 142 */       setDisplayIcon(MIL_ICON);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void switchNormalMode(LivingEntity entity) {
/* 147 */     this.altModeComponent.setMode(entity, MilFleurAbility.Mode.NORMAL);
/*     */   }
/*     */   
/*     */   public void switchMilMode(LivingEntity entity) {
/* 151 */     this.altModeComponent.setMode(entity, MilFleurAbility.Mode.MIL_FLEUR);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\hana\SeisFleurSlapAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */