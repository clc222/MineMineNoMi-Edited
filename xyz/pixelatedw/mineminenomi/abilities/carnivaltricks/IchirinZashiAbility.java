/*     */ package xyz.pixelatedw.mineminenomi.abilities.carnivaltricks;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.DropHitAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class IchirinZashiAbility
/*     */   extends DropHitAbility {
/*     */   private static final int COOLDOWN = 200;
/*     */   private static final float RANGE = 3.5F;
/*     */   private static final float DAMAGE = 20.0F;
/*  34 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "ichirin_zashi", new Pair[] {
/*  35 */         (Pair)ImmutablePair.of("The user leaps into the air and drops down on ground hitting the target on their descent", null)
/*     */       });
/*  37 */   public static final AbilityCore<IchirinZashiAbility> INSTANCE = (new AbilityCore.Builder("Ichirin Zashi", AbilityCategory.STYLE, IchirinZashiAbility::new))
/*  38 */     .addDescriptionLine(DESCRIPTION)
/*  39 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), DealDamageComponent.getTooltip(20.0F), RangeComponent.getTooltip(3.5F, RangeComponent.RangeType.AOE)
/*  40 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/*  41 */     .setSourceType(new SourceType[] { SourceType.SLASH
/*  42 */       }).build();
/*     */   
/*  44 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  45 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  46 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*     */   public IchirinZashiAbility(AbilityCore<IchirinZashiAbility> core) {
/*  49 */     super(core);
/*     */     
/*  51 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.animationComponent });
/*  52 */     this.continuousComponent.addStartEvent(100, this::onStartContinuityEvent);
/*  53 */     this.continuousComponent.addTickEvent(100, this::tickContinuityEvent);
/*  54 */     this.continuousComponent.addEndEvent(100, this::endContinuityEvent);
/*     */     
/*  56 */     addCanUseCheck(AbilityHelper::canUseSwordsmanAbilities);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLanding(LivingEntity entity) {
/*  61 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 3.5F);
/*     */     
/*  63 */     targets.remove(entity);
/*     */     
/*  65 */     AbilityDamageSource source = (AbilityDamageSource)ModDamageSource.causeAbilityDamage(entity, getCore()).setSlash();
/*     */     
/*  67 */     for (LivingEntity target : targets) {
/*  68 */       if (this.hitTrackerComponent.canHit((Entity)target) && entity.func_70685_l((Entity)target)) {
/*  69 */         this.dealDamageComponent.hurtTarget(entity, target, 20.0F, (DamageSource)source);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  75 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*  76 */     this.animationComponent.stop(entity);
/*     */   }
/*     */   
/*     */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/*  80 */     if (this.continuousComponent.getContinueTime() > 10.0F && !hasLanded()) {
/*  81 */       if (entity.func_233570_aj_()) {
/*  82 */         setLanded(true);
/*     */       }
/*  84 */       else if (entity.func_184187_bx() != null && entity.func_184187_bx().func_233570_aj_()) {
/*  85 */         setLanded(true);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void onStartContinuityEvent(LivingEntity entity, IAbility ability) {
/*  91 */     this.animationComponent.start(entity, ModAnimations.STAB_DOWN);
/*     */     
/*  93 */     Vector3d speed = WyHelper.propulsion(entity, 1.0D, 1.0D);
/*     */     
/*  95 */     if (entity.func_184187_bx() != null) {
/*  96 */       AbilityHelper.setDeltaMovement(entity.func_184187_bx(), speed.field_72450_a, 1.3D, speed.field_72449_c);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 101 */     AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, 1.3D, speed.field_72449_c);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\carnivaltricks\IchirinZashiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */