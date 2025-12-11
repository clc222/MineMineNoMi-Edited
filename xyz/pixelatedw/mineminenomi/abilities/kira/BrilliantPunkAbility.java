/*     */ package xyz.pixelatedw.mineminenomi.abilities.kira;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ 
/*     */ public class BrilliantPunkAbility
/*     */   extends Ability
/*     */ {
/*  36 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "brilliant_punk", new Pair[] {
/*  37 */         (Pair)ImmutablePair.of("The user rams into the target with their diamond body.", null)
/*     */       });
/*     */   
/*     */   private static final int HOLD_TIME = 10;
/*     */   private static final int COOLDOWN = 200;
/*     */   private static final float RANGE = 1.6F;
/*     */   private static final float DAMAGE = 25.0F;
/*  44 */   public static final AbilityCore<BrilliantPunkAbility> INSTANCE = (new AbilityCore.Builder("Brilliant Punk", AbilityCategory.DEVIL_FRUITS, BrilliantPunkAbility::new))
/*  45 */     .addDescriptionLine(DESCRIPTION)
/*  46 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), RangeComponent.getTooltip(1.6F, RangeComponent.RangeType.LINE), DealDamageComponent.getTooltip(25.0F)
/*  47 */       }).setSourceType(new SourceType[] { SourceType.PHYSICAL
/*  48 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  49 */     .build();
/*     */   
/*  51 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  52 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  53 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  54 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  55 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/*     */   
/*     */   public BrilliantPunkAbility(AbilityCore<BrilliantPunkAbility> core) {
/*  58 */     super(core);
/*     */     
/*  60 */     this.isNew = true;
/*  61 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.hitTrackerComponent });
/*     */     
/*  63 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*  64 */     addCanUseCheck(this::requiresDiamondBody);
/*  65 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  69 */     this.continuousComponent.triggerContinuity(entity, 10.0F);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  73 */     this.hitTrackerComponent.clearHits();
/*  74 */     this.animationComponent.start(entity, ModAnimations.TACKLE);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  78 */     if (entity.func_70089_S()) {
/*  79 */       Vector3d look = entity.func_70040_Z();
/*  80 */       Vector3d speed = look.func_216372_d(2.3D, 0.0D, 2.3D);
/*  81 */       entity.func_213315_a(MoverType.SELF, speed);
/*     */       
/*  83 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInLine(entity, 1.0F, 1.6F);
/*     */       
/*  85 */       for (LivingEntity target : targets) {
/*  86 */         if (this.hitTrackerComponent.canHit((Entity)target)) {
/*  87 */           this.dealDamageComponent.hurtTarget(entity, target, 25.0F);
/*     */         }
/*     */       } 
/*     */       
/*  91 */       for (Entity target : this.hitTrackerComponent.getHits()) {
/*  92 */         target.func_70634_a(entity.func_226277_ct_() + look.field_72450_a, entity.func_226278_cu_(), entity.func_226281_cx_() + look.field_72449_c);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  98 */     this.animationComponent.stop(entity);
/*  99 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*     */   }
/*     */   
/*     */   private AbilityUseResult requiresDiamondBody(LivingEntity entity, IAbility ability) {
/* 103 */     IAbilityData abilityProps = AbilityDataCapability.get(entity);
/* 104 */     DiamondBodyAbility diamondBody = (DiamondBodyAbility)abilityProps.getEquippedAbility(DiamondBodyAbility.INSTANCE);
/* 105 */     boolean diamondBodyActive = (diamondBody != null && diamondBody.isContinuous());
/* 106 */     if (!diamondBodyActive) {
/* 107 */       TranslationTextComponent text = new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_MISSING_DEPENDENCY_SINGLE, new Object[] { getDisplayName().getString(), DiamondBodyAbility.INSTANCE.getUnlocalizedName() });
/* 108 */       return AbilityUseResult.fail((ITextComponent)text);
/*     */     } 
/*     */     
/* 111 */     return AbilityUseResult.success();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\kira\BrilliantPunkAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */