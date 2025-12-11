/*     */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.ModMain;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityStat;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ 
/*     */ public class DealDamageComponent extends AbilityComponent<IAbility> {
/*  23 */   private static final UUID DAMAGE_BONUS_MANAGER_UUID = UUID.fromString("f4ac25c4-ef9f-4537-8471-406a02737308");
/*     */   
/*  25 */   private static final UUID STYLE_BONUS = UUID.fromString("dd55f409-9924-4cca-afbe-522462884d71");
/*     */   
/*     */   private static final int TARGETS_SIZE = 10;
/*     */   
/*  29 */   private LinkedList<LivingEntity> lastTargets = new LinkedList<>();
/*     */   
/*     */   public static AbilityDescriptionLine.IDescriptionLine getTooltip(float damage) {
/*  32 */     return getTooltip(damage, damage);
/*     */   }
/*     */   
/*     */   public static AbilityDescriptionLine.IDescriptionLine getTooltip(float min, float max) {
/*  36 */     return (e, a) -> {
/*     */         AbilityStat.Builder statBuilder = new AbilityStat.Builder((ITextComponent)ModI18n.ABILITY_DESCRIPTION_STAT_NAME_DAMAGE, min, max);
/*     */         a.getComponent(ModAbilityKeys.DAMAGE).ifPresent(());
/*     */         return statBuilder.build().getStatDescription();
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   private final BonusManager bonusManager = new BonusManager(DAMAGE_BONUS_MANAGER_UUID);
/*     */   private DamageSource damageSource;
/*     */   
/*     */   public DealDamageComponent(IAbility ability) {
/*  53 */     super(ModAbilityKeys.DAMAGE, ability);
/*  54 */     addBonusManager(this.bonusManager);
/*     */   }
/*     */   
/*     */   public boolean hurtTarget(LivingEntity attacker, LivingEntity target, float damage) {
/*  58 */     return hurtTarget(attacker, target, damage, getDamageSource(attacker));
/*     */   }
/*     */   
/*     */   public boolean hurtTarget(LivingEntity attacker, LivingEntity target, float damage, DamageSource source) {
/*  62 */     if (ModMain.hasShitInstalled() && source instanceof ModDamageSource && ((ModDamageSource)source).isIgnored()) {
/*  63 */       return false;
/*     */     }
/*     */     
/*  66 */     ensureIsRegistered();
/*  67 */     float finalDamage = calculateFinalDamage(attacker, damage);
/*  68 */     if (target.func_70097_a(source, finalDamage)) {
/*  69 */       if (target.func_70089_S()) {
/*  70 */         if (this.lastTargets.size() >= 0 && this.lastTargets.size() == 10) {
/*  71 */           this.lastTargets.remove();
/*     */         }
/*  73 */         this.lastTargets.add(target);
/*     */       } 
/*  75 */       return true;
/*     */     } 
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setDamageSource(DamageSource damageSource) {
/*  85 */     this.damageSource = damageSource;
/*     */   }
/*     */   
/*     */   public DamageSource getDamageSource(LivingEntity attacker) {
/*  89 */     if (this.damageSource == null) {
/*  90 */       this.damageSource = (DamageSource)AbilityDamageSource.causeAbilityDamage(attacker, getAbility().getCore());
/*     */     }
/*  92 */     return this.damageSource;
/*     */   }
/*     */   
/*     */   private float getDamageMultiplier(LivingEntity entity) {
/*  96 */     return (float)EntityStatsCapability.get(entity).getDamageMultiplier();
/*     */   }
/*     */   
/*     */   private float calculateFinalDamage(LivingEntity entity, float damage) {
/* 100 */     float bonusDamage = calculateBonusDamage(entity, getAbility().getCore(), damage);
/* 101 */     return bonusDamage * getDamageMultiplier(entity);
/*     */   }
/*     */   
/*     */   private float calculateBonusDamage(LivingEntity entity, AbilityCore ability, float baseDamage) {
/* 105 */     this.bonusManager.removeBonus(STYLE_BONUS);
/*     */     
/* 107 */     boolean isSlash = ability.hasType(SourceType.SLASH);
/* 108 */     boolean isBlunt = ability.hasType(SourceType.BLUNT);
/* 109 */     boolean isProj = ability.hasType(SourceType.PROJECTILE);
/*     */     
/* 111 */     boolean hasSlashBonus = ((isSlash || isBlunt) && !entity.func_184614_ca().func_190926_b() && ItemsHelper.isSword(entity.func_184614_ca()));
/*     */ 
/*     */ 
/*     */     
/* 115 */     boolean hasSniperBonus = (isProj && !entity.func_184614_ca().func_190926_b() && ItemsHelper.isBow(entity.func_184614_ca()));
/*     */     
/* 117 */     if (hasSlashBonus) {
/* 118 */       this.bonusManager.addBonus(STYLE_BONUS, "Swordsman Fighting Style Bonus", BonusOperation.ADD, ItemsHelper.getItemDamage(entity.func_184614_ca()));
/*     */     }
/*     */     
/* 121 */     return this.bonusManager.applyBonus(baseDamage);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getLastTarget() {
/*     */     try {
/* 127 */       return this.lastTargets.getLast();
/*     */     }
/* 129 */     catch (Exception ex) {
/*     */       
/* 131 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getLastNTarget(int nth) {
/*     */     try {
/* 138 */       return this.lastTargets.get(nth);
/*     */     }
/* 140 */     catch (Exception ex) {
/*     */       
/* 142 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public BonusManager getBonusManager() {
/* 147 */     return this.bonusManager;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\DealDamageComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */