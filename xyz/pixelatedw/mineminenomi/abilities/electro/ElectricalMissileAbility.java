/*     */ package xyz.pixelatedw.mineminenomi.abilities.electro;
/*     */ 
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.DashAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ 
/*     */ 
/*     */ public class ElectricalMissileAbility
/*     */   extends DashAbility
/*     */ {
/*     */   private static final float COOLDOWN_BONUS = 0.5F;
/*     */   private static final float DAMAGE_BONUS = 2.5F;
/*  35 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "electrical_missile", new Pair[] {
/*  36 */         (Pair)ImmutablePair.of("Powerful and fast forward dash that will stun enemies.", null), 
/*  37 */         (Pair)ImmutablePair.of("While %s is active the cooldown of this ability is reduced by %s and the damage is increased by %s.", new Object[] {
/*  38 */             AbilityHelper.mentionAbility(SulongAbility.INSTANCE), 
/*  39 */             AbilityHelper.mentionText(Math.round(50.0F) + "%"), 
/*  40 */             AbilityHelper.mentionText(Math.round(Math.abs(-1.5F) * 100.0F) + "%")
/*     */           })
/*     */       });
/*     */   private static final int COOLDOWN = 180;
/*     */   private static final float RANGE = 1.6F;
/*     */   private static final int DAMAGE = 20;
/*     */   private static final int ELECLAW_STACKS = 1;
/*  47 */   public static final AbilityCore<ElectricalMissileAbility> INSTANCE = (new AbilityCore.Builder("Electrical Missile", AbilityCategory.RACIAL, ElectricalMissileAbility::new))
/*  48 */     .addDescriptionLine(new ITextComponent[] { DESCRIPTION[0]
/*  49 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.IDescriptionLine.of(DESCRIPTION[1])
/*  50 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(180.0F), RangeComponent.getTooltip(1.6F, RangeComponent.RangeType.AOE), DealDamageComponent.getTooltip(20.0F)
/*  51 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  52 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  53 */       }).setSourceElement(SourceElement.LIGHTNING)
/*  54 */     .setUnlockCheck(ElectricalMissileAbility::canUnlock)
/*  55 */     .build();
/*     */   
/*  57 */   private double dashSpeed = 4.0D;
/*     */   
/*     */   public ElectricalMissileAbility(AbilityCore<ElectricalMissileAbility> core) {
/*  60 */     super(core);
/*     */     
/*  62 */     addCanUseCheck(ElectroHelper.requireEleclaw(1));
/*     */     
/*  64 */     this.continuousComponent.addStartEvent(this::startContinuityEvent);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  68 */     boolean hasSulongActive = ElectroHelper.hasSulongActive(entity);
/*  69 */     this.dashSpeed = hasSulongActive ? 5.0D : 4.0D;
/*     */     
/*  71 */     this.dealDamageComponent.getBonusManager().removeBonus(ElectroHelper.SULONG_DAMAGE_BONUS);
/*  72 */     this.cooldownComponent.getBonusManager().removeBonus(ElectroHelper.SULONG_COOLDOWN_BONUS);
/*  73 */     if (hasSulongActive) {
/*  74 */       this.dealDamageComponent.getBonusManager().addBonus(ElectroHelper.SULONG_DAMAGE_BONUS, "Sulong Damage Bonus", BonusOperation.MUL, 2.5F);
/*  75 */       this.cooldownComponent.getBonusManager().addBonus(ElectroHelper.SULONG_COOLDOWN_BONUS, "Sulong Cooldown Bonus", BonusOperation.MUL, 0.5F);
/*     */     } 
/*     */     
/*  78 */     entity.func_226292_a_(Hand.MAIN_HAND, true);
/*     */     
/*  80 */     EleclawAbility eleclawAbility = (EleclawAbility)AbilityDataCapability.get(entity).getEquippedAbility(EleclawAbility.INSTANCE);
/*  81 */     if (eleclawAbility != null) {
/*  82 */       eleclawAbility.reduceUsage(entity, 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onTargetHit(LivingEntity entity, LivingEntity target, float damage, DamageSource source) {
/*  88 */     target.func_195064_c(new EffectInstance((Effect)ModEffects.PARALYSIS.get(), 40, 0, false, false, true));
/*     */   }
/*     */ 
/*     */   
/*     */   public float getDashCooldown() {
/*  93 */     return 180.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getDamage() {
/*  98 */     return 20.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getRange() {
/* 103 */     return 1.6F;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getSpeed() {
/* 108 */     return this.dashSpeed;
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity user) {
/* 112 */     IEntityStats props = EntityStatsCapability.get(user);
/* 113 */     return (props.isMink() && props.getDoriki() >= 800.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\electro\ElectricalMissileAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */