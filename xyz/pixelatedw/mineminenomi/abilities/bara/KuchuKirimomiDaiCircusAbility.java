/*     */ package xyz.pixelatedw.mineminenomi.abilities.bara;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.GrabEntityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.MorphComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.bara.DaiCircusProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ 
/*     */ public class KuchuKirimomiDaiCircusAbility extends Ability {
/*  30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kuchu_kirimomi_dai_circus", new Pair[] {
/*  31 */         (Pair)ImmutablePair.of("Fires both fists at an enemy and lifts them up, moving them around according to the user's movements.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 300;
/*     */   private static final int HOLD_TIME = 60;
/*  36 */   public static final AbilityCore<KuchuKirimomiDaiCircusAbility> INSTANCE = (new AbilityCore.Builder("Kuchu Kirimomi Dai Circus", AbilityCategory.DEVIL_FRUITS, KuchuKirimomiDaiCircusAbility::new))
/*  37 */     .addDescriptionLine(DESCRIPTION)
/*  38 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F), ContinuousComponent.getTooltip(60.0F)
/*  39 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  40 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  41 */       }).build();
/*     */   
/*  43 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  44 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*  45 */   private final GrabEntityComponent grabEntityComponent = new GrabEntityComponent((IAbility)this, true, true, 7.0F);
/*  46 */   private final MorphComponent morphComponent = new MorphComponent((IAbility)this);
/*  47 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.GRAB_ABILITY, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*     */   
/*     */   public KuchuKirimomiDaiCircusAbility(AbilityCore<KuchuKirimomiDaiCircusAbility> core) {
/*  50 */     super(core);
/*     */     
/*  52 */     this.isNew = true;
/*  53 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.projectileComponent, (AbilityComponent)this.grabEntityComponent, (AbilityComponent)this.morphComponent, (AbilityComponent)this.poolComponent });
/*     */     
/*  55 */     addCanUseCheck(BaraHelper::hasLimbs);
/*  56 */     addTickEvent(this::tickEvent);
/*  57 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void tickEvent(LivingEntity entity, IAbility ability) {
/*  61 */     if (!entity.field_70170_p.field_72995_K && !this.continuousComponent.isContinuous() && this.morphComponent.isMorphed()) {
/*  62 */       AbilityProjectileEntity projectile = this.projectileComponent.getShotProjectile();
/*  63 */       if (projectile == null || !projectile.func_70089_S()) {
/*  64 */         this.morphComponent.stopMorph(entity);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  70 */     if (this.continuousComponent.isContinuous()) {
/*  71 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/*  75 */     this.projectileComponent.shoot(entity, 3.0F, 0.0F);
/*     */     
/*  77 */     int projectileLife = this.projectileComponent.getShotProjectile().getLife();
/*     */     
/*  79 */     this.morphComponent.startMorph(entity, (MorphInfo)ModMorphs.BARA_CIRCUS.get());
/*     */     
/*  81 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.NO_HANDS.get(), projectileLife, 0));
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  85 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.NO_HANDS.get(), 5, 0));
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  89 */     this.morphComponent.stopMorph(entity);
/*  90 */     this.grabEntityComponent.release(entity);
/*  91 */     this.cooldownComponent.startCooldown(entity, 300.0F);
/*     */   }
/*     */   
/*     */   private DaiCircusProjectile createProjectile(LivingEntity entity) {
/*  95 */     DaiCircusProjectile proj = new DaiCircusProjectile(entity.field_70170_p, entity, this);
/*     */     
/*  97 */     proj.onEntityImpactEvent = (hit -> {
/*     */         if (this.grabEntityComponent.grabManually(entity, hit)) {
/*     */           this.continuousComponent.startContinuity(entity, 60.0F);
/*     */         }
/*     */       });
/*     */     
/* 103 */     return proj;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\bara\KuchuKirimomiDaiCircusAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */