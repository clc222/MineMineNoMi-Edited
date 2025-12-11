/*     */ package xyz.pixelatedw.mineminenomi.abilities.suna;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.TornadoEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ 
/*     */ public class SablesGuardAbility
/*     */   extends Ability {
/*     */   private static final float COOLDOWN_BONUS = 0.8F;
/*  31 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "sables_guard", new Pair[] {
/*  32 */         (Pair)ImmutablePair.of("The user starts a sandstorm with themselves as the center of it, sending all nearby enemies flying away.", null), 
/*  33 */         (Pair)ImmutablePair.of("While in a desert the cooldown of this ability is reduced by %s.", new Object[] {
/*  34 */             "§a" + Math.round(19.999998F) + "%§r"
/*     */           })
/*     */       });
/*     */   private static final int COOLDOWN = 300;
/*     */   private static final int HOLD_TIME = 60;
/*     */   private static final int RANGE = 10;
/*  40 */   public static final AbilityCore<SablesGuardAbility> INSTANCE = (new AbilityCore.Builder("Sables Guard", AbilityCategory.DEVIL_FRUITS, SablesGuardAbility::new))
/*  41 */     .addDescriptionLine(DESCRIPTION)
/*  42 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F), ContinuousComponent.getTooltip(60.0F), RangeComponent.getTooltip(10.0F, RangeComponent.RangeType.AOE)
/*  43 */       }).build();
/*     */   
/*  45 */   private final ContinuousComponent continuityComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  46 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  47 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*  49 */   private TornadoEntity proj = null;
/*     */   
/*     */   public SablesGuardAbility(AbilityCore<SablesGuardAbility> core) {
/*  52 */     super(core);
/*     */     
/*  54 */     this.isNew = true;
/*  55 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuityComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  57 */     addCanUseCheck(AbilityHelper::requiresDryUser);
/*     */     
/*  59 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  63 */     this.continuityComponent.triggerContinuity(entity, 60.0F);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  67 */     this.proj = null;
/*  68 */     this.animationComponent.start(entity, ModAnimations.RAISE_RIGHT_ARM);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  72 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 1));
/*     */     
/*  74 */     if (this.proj == null) {
/*  75 */       this.proj = new TornadoEntity(entity.field_70170_p, entity);
/*  76 */       this.proj.setMaxLife(60);
/*  77 */       this.proj.setSize(20.0F);
/*  78 */       this.proj.setSpeed(-2.0F);
/*  79 */       this.proj.func_70107_b((entity.func_213303_ch()).field_72450_a, (entity.func_213303_ch()).field_72448_b, (entity.func_213303_ch()).field_72449_c);
/*  80 */       entity.field_70170_p.func_217376_c((Entity)this.proj);
/*     */     } 
/*     */     
/*  83 */     if (!this.proj.func_70089_S() || this.proj == null) {
/*  84 */       this.continuityComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/*  88 */     if (this.continuityComponent.getContinueTime() % 5.0F == 0.0F) {
/*  89 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 10.0F);
/*  90 */       for (LivingEntity target : targets) {
/*  91 */         Vector3d moveVec = target.func_213303_ch().func_178788_d(this.proj.func_213303_ch()).func_72432_b().func_216372_d(4.0D, 1.0D, 4.0D).func_72441_c(0.0D, 1.0D, 0.0D);
/*  92 */         AbilityHelper.setDeltaMovement((Entity)target, moveVec);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  98 */     if (this.proj != null && this.proj.func_70089_S()) {
/*  99 */       this.proj.func_70106_y();
/*     */     }
/*     */     
/* 102 */     this.animationComponent.stop(entity);
/*     */     
/* 104 */     this.cooldownComponent.getBonusManager().removeBonus(SunaHelper.DESERT_COOLDOWN_BONUS);
/* 105 */     if (SunaHelper.isFruitBoosted(entity)) {
/* 106 */       this.cooldownComponent.getBonusManager().addBonus(SunaHelper.DESERT_COOLDOWN_BONUS, "Desert Cooldown Bonus", BonusOperation.MUL, 0.8F);
/*     */     }
/*     */     
/* 109 */     this.cooldownComponent.startCooldown(entity, 300.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\suna\SablesGuardAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */