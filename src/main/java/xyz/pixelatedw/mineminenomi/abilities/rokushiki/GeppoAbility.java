/*     */ package xyz.pixelatedw.mineminenomi.abilities.rokushiki;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityStat;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.StackComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class GeppoAbility extends Ability {
/*  34 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "geppo", new Pair[] {
/*  35 */         (Pair)ImmutablePair.of("The user kicks the air beneath them to launch themselves into the air", null) }); private static final int MIN_JUMPS = 3;
/*     */   private static final int MAX_JUMPS = 6;
/*     */   private static final float SHORT_COOLDOWN_PER_STACK = 10.0F;
/*     */   private static final float LONG_COOLDOWN_PER_STACK = 50.0F;
/*     */   private static final AbilityDescriptionLine.IDescriptionLine GEPPO_STACKS;
/*     */   
/*     */   static {
/*  42 */     GEPPO_STACKS = ((e, a) -> {
/*     */         if (a instanceof GeppoAbility) {
/*     */           GeppoAbility geppo = (GeppoAbility)a;
/*     */           AbilityStat.Builder statBuilder = new AbilityStat.Builder((ITextComponent)StackComponent.STACKS_STAT, geppo.getMaxJumps(e), geppo.getMaxJumps(e));
/*     */           return statBuilder.build().getStatDescription();
/*     */         } 
/*     */         return null;
/*     */       });
/*     */   }
/*  51 */   public static final AbilityCore<GeppoAbility> INSTANCE = (new AbilityCore.Builder("Geppo", AbilityCategory.RACIAL, GeppoAbility::new))
/*  52 */     .addDescriptionLine(DESCRIPTION)
/*  53 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, AbilityHelper.createShortLongCooldownStat(10.0F, 50.0F), GEPPO_STACKS
/*  54 */       }).setUnlockCheck(GeppoAbility::canUnlock)
/*  55 */     .build();
/*     */   
/*  57 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.GEPPO_LIKE, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*  58 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::onDamageTaken);
/*  59 */   private final StackComponent stackComponent = new StackComponent((IAbility)this);
/*     */   
/*     */   private boolean hasFallDamage = true;
/*     */   
/*     */   public GeppoAbility(AbilityCore<GeppoAbility> core) {
/*  64 */     super(core);
/*     */     
/*  66 */     this.isNew = true;
/*  67 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.poolComponent, (AbilityComponent)this.damageTakenComponent, (AbilityComponent)this.stackComponent });
/*  68 */     setOGCD();
/*     */     
/*  70 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*  71 */     addUseEvent(this::onUseEvent);
/*  72 */     addTickEvent(this::tickEvent);
/*     */   } private void onUseEvent(LivingEntity entity, IAbility ability) {
/*     */     Vector3d speed;
/*     */     double ySpeed;
/*  76 */     this.stackComponent.setDefaultStacks(getMaxJumps(entity));
/*     */ 
/*     */ 
/*     */     
/*  80 */     int stacksUsed = 1;
/*     */     
/*  82 */     if (entity.func_70090_H()) {
/*  83 */       speed = WyHelper.propulsion(entity, 2.0D, 2.0D, 2.0D);
/*  84 */       ySpeed = speed.field_72448_b;
/*  85 */       stacksUsed = this.stackComponent.getStacks();
/*     */     } else {
/*     */       
/*  88 */       if (entity.func_233570_aj_()) {
/*  89 */         speed = WyHelper.propulsion(entity, 1.0D, 1.0D);
/*  90 */         ySpeed = 1.86D;
/*     */       } else {
/*     */         
/*  93 */         speed = WyHelper.propulsion(entity, 1.5D, 1.5D);
/*  94 */         ySpeed = 1.25D;
/*     */       } 
/*  96 */       stacksUsed = 1;
/*     */     } 
/*     */     
/*  99 */     AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, ySpeed, speed.field_72449_c);
/* 100 */     this.stackComponent.addStacks(entity, (IAbility)this, -stacksUsed);
/* 101 */     this.hasFallDamage = false;
/*     */     
/* 103 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.GEPPO_SFX.get(), SoundCategory.PLAYERS, 2.0F, 0.75F + this.random.nextFloat() / 3.0F);
/* 104 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.GEPPO.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     
/* 106 */     if (this.stackComponent.getStacks() <= 0) {
/* 107 */       this.cooldownComponent.startCooldown(entity, getCooldownTicks());
/* 108 */       this.stackComponent.setStacks(entity, (IAbility)this, getMaxJumps(entity));
/*     */     } else {
/*     */       
/* 111 */       this.cooldownComponent.startCooldown(entity, 10.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private float onDamageTaken(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/* 116 */     if (!this.hasFallDamage && damageSource == DamageSource.field_76379_h) {
/* 117 */       resetStacks(entity);
/*     */       
/* 119 */       return 0.0F;
/*     */     } 
/*     */     
/* 122 */     return damage;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tickEvent(LivingEntity entity, IAbility ability) {
/* 128 */     if (getLastUseGametime() <= 0L && this.stackComponent.getDefaultStacks() <= 0) {
/* 129 */       this.stackComponent.setDefaultStacks(getMaxJumps(entity));
/* 130 */       this.stackComponent.revertStacksToDefault(entity, (IAbility)this);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 135 */     if (!entity.field_70170_p.field_72995_K && !this.hasFallDamage && this.stackComponent.getStacks() < this.stackComponent.getDefaultStacks() && entity.func_233570_aj_() && entity.field_70170_p
/* 136 */       .func_82737_E() > getLastUseGametime() + 10L) {
/* 137 */       resetStacks(entity);
/*     */     }
/*     */   }
/*     */   
/*     */   private void resetStacks(LivingEntity entity) {
/* 142 */     if (this.stackComponent.getStacks() != this.stackComponent.getDefaultStacks()) {
/* 143 */       this.cooldownComponent.stopCooldown(entity);
/* 144 */       this.cooldownComponent.startCooldown(entity, getCooldownTicks());
/*     */     } 
/* 146 */     this.stackComponent.setStacks(entity, (IAbility)this, getMaxJumps(entity));
/* 147 */     this.stackComponent.setDefaultStacks(getMaxJumps(entity));
/* 148 */     this.hasFallDamage = true;
/*     */   }
/*     */   
/*     */   private int getMaxJumps(LivingEntity entity) {
/* 152 */     return 6;
/*     */   }
/*     */   
/*     */   private float getCooldownTicks() {
/* 156 */     return (this.stackComponent.getDefaultStacks() - this.stackComponent.getStacks()) * 50.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT save(CompoundNBT nbt) {
/* 161 */     nbt = super.save(nbt);
/* 162 */     nbt.func_74757_a("hasFallDamage", this.hasFallDamage);
/* 163 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 168 */     super.load(nbt);
/* 169 */     this.hasFallDamage = nbt.func_74767_n("hasFallDamage");
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity user) {
/* 173 */     IEntityStats props = EntityStatsCapability.get(user);
/* 174 */     boolean raceCheck = (props.isHuman() || DevilFruitCapability.get(user).hasDevilFruit(ModAbilities.HITO_HITO_NO_MI));
/* 175 */     return (raceCheck && props.getDoriki() >= 1250.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\rokushiki\GeppoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */