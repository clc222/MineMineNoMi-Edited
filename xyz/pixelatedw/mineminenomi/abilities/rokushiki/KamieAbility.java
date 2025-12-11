/*     */ package xyz.pixelatedw.mineminenomi.abilities.rokushiki;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ 
/*     */ public class KamieAbility extends Ability {
/*  32 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kamie", new Pair[] {
/*  33 */         (Pair)ImmutablePair.of("Makes the user's body scalable in order to avoid attacks", null)
/*     */       });
/*     */   
/*     */   private static final float HOLD_TIME = 60.0F;
/*     */   private static final float MIN_COOLDOWN = 100.0F;
/*     */   private static final float MAX_COOLDOWN = 450.0F;
/*     */   private static final float PROTECTION_TIME = 10.0F;
/*  40 */   public static final AbilityCore<KamieAbility> INSTANCE = (new AbilityCore.Builder("Kami-E", AbilityCategory.RACIAL, KamieAbility::new))
/*  41 */     .addDescriptionLine(DESCRIPTION)
/*  42 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F, 450.0F), ContinuousComponent.getTooltip(60.0F)
/*  43 */       }).setUnlockCheck(KamieAbility::canUnlock)
/*  44 */     .build();
/*     */   
/*  46 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this))
/*  47 */     .addStartEvent(100, this::onStartContinuityEvent)
/*  48 */     .addTickEvent(100, this::duringContinuityEvent)
/*  49 */     .addEndEvent(100, this::onEndContinuityEvent);
/*  50 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.DODGE_ABILITY, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*  51 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::onDamageTakenEvent);
/*     */   
/*  53 */   private int hitsTaken = 0;
/*  54 */   private float protTimer = 10.0F;
/*     */   
/*     */   public KamieAbility(AbilityCore<KamieAbility> core) {
/*  57 */     super(core);
/*     */     
/*  59 */     this.isNew = true;
/*  60 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.poolComponent, (AbilityComponent)this.damageTakenComponent });
/*     */     
/*  62 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*  63 */     addCanUseCheck(AbilityHelper::requiresFocus);
/*  64 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  68 */     this.continuousComponent.triggerContinuity(entity, 60.0F);
/*     */   }
/*     */   
/*     */   private void onStartContinuityEvent(LivingEntity entity, IAbility ability) {
/*  72 */     this.hitsTaken = 0;
/*  73 */     this.protTimer = 0.0F;
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  77 */     if (this.protTimer > 0.0F) {
/*  78 */       this.protTimer--;
/*     */     }
/*     */   }
/*     */   
/*     */   private void onEndContinuityEvent(LivingEntity entity, IAbility ability) {
/*  83 */     float cooldown = 100.0F + this.continuousComponent.getContinueTime() * 5.0F + 5.0F * (float)Math.pow(this.hitsTaken, this.hitsTaken);
/*  84 */     cooldown = Math.min(450.0F, cooldown);
/*  85 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*     */   }
/*     */   
/*     */   public float onDamageTakenEvent(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/*  89 */     if (!isContinuous() || !AbilityHelper.canUseMomentumAbilities(entity) || AbilityHelper.isGrabbing(entity)) {
/*  90 */       return damage;
/*     */     }
/*     */     
/*  93 */     boolean isDamageTaken = true;
/*  94 */     boolean isUnavoidable = (damageSource instanceof ModDamageSource && ((ModDamageSource)damageSource).isUnavoidable());
/*     */     
/*  96 */     ArrayList<String> acceptableInstantSources = new ArrayList<>(Arrays.asList(new String[] { "mob", "player", "ability_projectile", "ability" }));
/*     */     
/*  98 */     if ((damageSource.func_76364_f() instanceof LivingEntity || damageSource.func_76364_f() instanceof net.minecraft.entity.projectile.ProjectileEntity) && 
/*  99 */       acceptableInstantSources.contains(damageSource.func_76355_l()) && !isUnavoidable) {
/* 100 */       isDamageTaken = false;
/*     */     }
/*     */ 
/*     */     
/* 104 */     if (this.protTimer <= 0.0F) {
/* 105 */       if (!isDamageTaken) {
/* 106 */         SoundEvent sfx = (SoundEvent)ModSounds.DODGE_1.get();
/* 107 */         if (entity.func_70681_au().nextBoolean()) {
/* 108 */           sfx = (SoundEvent)ModSounds.DODGE_2.get();
/*     */         }
/* 110 */         entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), sfx, SoundCategory.PLAYERS, 1.0F, 0.75F + entity.func_70681_au().nextFloat() / 2.0F);
/* 111 */         this.hitsTaken++;
/* 112 */         this.protTimer = 10.0F;
/*     */       } 
/*     */       
/* 115 */       return isDamageTaken ? damage : 0.0F;
/*     */     } 
/*     */     
/* 118 */     return 0.0F;
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity user) {
/* 122 */     IEntityStats props = EntityStatsCapability.get(user);
/* 123 */     boolean raceCheck = (props.isHuman() || DevilFruitCapability.get(user).hasDevilFruit(ModAbilities.HITO_HITO_NO_MI));
/* 124 */     return (raceCheck && props.getDoriki() >= 3600.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\rokushiki\KamieAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */