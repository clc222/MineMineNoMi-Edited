/*     */ package xyz.pixelatedw.mineminenomi.abilities.nikyu;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu.ChargingUrsusShockEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu.UrsusShockProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ 
/*     */ public class UrsusShockAbility extends Ability {
/*  29 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "ursus_shock", new Pair[] {
/*  30 */         (Pair)ImmutablePair.of("The user compresses air and sends it towards the opponent to create a huge shockwave", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 400;
/*     */   private static final int CHARGE_TIME = 140;
/*  35 */   public static final AbilityCore<UrsusShockAbility> INSTANCE = (new AbilityCore.Builder("Ursus Shock", AbilityCategory.DEVIL_FRUITS, UrsusShockAbility::new))
/*  36 */     .addDescriptionLine(DESCRIPTION)
/*  37 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(400.0F)
/*  38 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/*  39 */     .setSourceElement(SourceElement.AIR)
/*  40 */     .setSourceType(new SourceType[] { SourceType.PROJECTILE, SourceType.INTERNAL
/*  41 */       }).build();
/*     */   
/*     */   private final ChargeComponent chargeComponent;
/*     */   
/*     */   private final AnimationComponent animationComponent;
/*     */   private final ProjectileComponent projectileComponent;
/*     */   private ChargingUrsusShockEntity ursusShockEntity;
/*     */   
/*     */   public UrsusShockAbility(AbilityCore<UrsusShockAbility> core) {
/*  50 */     super(core); this.chargeComponent = (new ChargeComponent((IAbility)this, comp -> (comp.getChargePercentage() >= 0.5F))).addStartEvent(this::startChargeEvent).addTickEvent(this::duringChargeEvent).addEndEvent(this::endChargeEvent); this.animationComponent = new AnimationComponent((IAbility)this);
/*     */     this.projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*  52 */     this.isNew = true;
/*  53 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.projectileComponent });
/*     */     
/*  55 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  59 */     this.chargeComponent.startCharging(entity, 140.0F);
/*     */   }
/*     */   
/*     */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/*  63 */     this.animationComponent.start(entity, ModAnimations.RAISE_ARMS, 140);
/*  64 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.URSUS_SHOCK_SFX.get(), SoundCategory.PLAYERS, 5.0F, 0.75F);
/*  65 */     ChargingUrsusShockEntity chargingUrsusShock = new ChargingUrsusShockEntity(entity.field_70170_p);
/*  66 */     chargingUrsusShock.setOwner(entity);
/*  67 */     chargingUrsusShock.func_70107_b(entity.func_226277_ct_(), entity.func_226278_cu_() + 2.0D, entity.func_226281_cx_());
/*  68 */     entity.field_70170_p.func_217376_c((Entity)chargingUrsusShock);
/*  69 */     this.ursusShockEntity = chargingUrsusShock;
/*     */   }
/*     */   
/*     */   private void duringChargeEvent(LivingEntity entity, IAbility ability) {
/*  73 */     if (this.ursusShockEntity == null) {
/*  74 */       this.chargeComponent.forceStopCharging(entity);
/*     */       
/*     */       return;
/*     */     } 
/*  78 */     boolean atThreshold = (this.chargeComponent.getChargePercentage() < 0.4D);
/*     */     
/*  80 */     float currentCharge = this.ursusShockEntity.getCharge();
/*  81 */     currentCharge = (float)(currentCharge + (atThreshold ? 0.065D : -0.055D));
/*  82 */     currentCharge = MathHelper.func_76131_a(currentCharge, -1.4F, 10.0F);
/*  83 */     this.ursusShockEntity.setCharge(currentCharge);
/*     */   }
/*     */   
/*     */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/*  87 */     this.animationComponent.stop(entity);
/*  88 */     float multiplier = this.chargeComponent.getChargePercentage();
/*     */     
/*  90 */     if (this.ursusShockEntity != null) {
/*  91 */       UrsusShockProjectile projectile = new UrsusShockProjectile(entity.field_70170_p, entity);
/*  92 */       projectile.multiplier = multiplier;
/*  93 */       projectile.setSize((multiplier > 0.75D) ? 0.6F : (5.0F * (1.0F - multiplier)));
/*     */       
/*  95 */       entity.field_70170_p.func_217376_c((Entity)projectile);
/*  96 */       projectile.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z, 0.0F, 2.0F, 0.0F);
/*     */     } 
/*     */     
/*  99 */     this.ursusShockEntity.func_70106_y();
/* 100 */     this.cooldownComponent.startCooldown(entity, 400.0F * multiplier);
/*     */   }
/*     */   
/*     */   private UrsusShockProjectile createProjectile(LivingEntity entity) {
/* 104 */     UrsusShockProjectile proj = new UrsusShockProjectile(entity.field_70170_p, entity);
/* 105 */     return proj;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\nikyu\UrsusShockAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */