/*     */ package xyz.pixelatedw.mineminenomi.abilities.blackleg;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.StackComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class SkywalkAbility extends Ability {
/*  34 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "skywalk", new Pair[] {
/*  35 */         (Pair)ImmutablePair.of("Allows the user to kick the air beneath them and launch themselves into the air", null)
/*     */       });
/*     */   
/*     */   private static final int MAX_JUMPS = 6;
/*     */   private static final float SHORT_COOLDOWN_PER_STACK = 10.0F;
/*     */   private static final float LONG_COOLDOWN_PER_STACK = 50.0F;
/*  41 */   public static final AbilityCore<SkywalkAbility> INSTANCE = (new AbilityCore.Builder("Skywalk", AbilityCategory.STYLE, SkywalkAbility::new))
/*  42 */     .addDescriptionLine(DESCRIPTION)
/*  43 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, AbilityHelper.createShortLongCooldownStat(10.0F, 50.0F), StackComponent.getTooltip(6)
/*  44 */       }).setUnlockCheck(SkywalkAbility::canUnlock)
/*  45 */     .build();
/*     */   
/*  47 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.GEPPO_LIKE, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*  48 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::onDamageTaken);
/*  49 */   private final StackComponent stackComponent = new StackComponent((IAbility)this, 6);
/*     */   
/*     */   private boolean hasFallDamage = true;
/*     */   
/*     */   private boolean hadGravity = false;
/*  54 */   private int noGravityTime = 8;
/*     */   
/*     */   public SkywalkAbility(AbilityCore<SkywalkAbility> core) {
/*  57 */     super(core);
/*     */     
/*  59 */     this.isNew = true;
/*  60 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.poolComponent, (AbilityComponent)this.damageTakenComponent, (AbilityComponent)this.stackComponent });
/*     */     
/*  62 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*  63 */     addUseEvent(this::onUseEvent);
/*  64 */     addTickEvent(this::tickEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  68 */     int stacksUsed = 1;
/*     */     
/*  70 */     if (this.noGravityTime <= 0) {
/*  71 */       this.hadGravity = !entity.func_189652_ae();
/*     */     }
/*     */     
/*  74 */     Vector3d movement = entity.func_70040_Z().func_72432_b();
/*     */     
/*  76 */     if (entity.func_70090_H()) {
/*  77 */       movement = movement.func_186678_a(2.5D);
/*     */       
/*  79 */       stacksUsed = this.stackComponent.getStacks();
/*     */     } else {
/*  81 */       movement = movement.func_186678_a(entity.func_233570_aj_() ? 2.0D : 1.5D);
/*     */     } 
/*     */     
/*  84 */     entity.func_189654_d(true);
/*  85 */     this.noGravityTime = 8;
/*  86 */     if (entity.field_70125_A < -40.0F) {
/*  87 */       movement = movement.func_72441_c(0.0D, -(movement.field_72448_b - movement.field_72448_b / 2.0D), 0.0D);
/*     */     }
/*  89 */     AbilityHelper.setDeltaMovement((Entity)entity, movement.field_72450_a, movement.field_72448_b, movement.field_72449_c);
/*     */     
/*  91 */     this.stackComponent.addStacks(entity, (IAbility)this, -stacksUsed);
/*     */     
/*  93 */     this.hasFallDamage = false;
/*     */     
/*  95 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.GEPPO_SFX.get(), SoundCategory.PLAYERS, 2.0F, 0.75F + this.random.nextFloat() / 3.0F);
/*     */     
/*  97 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.GEPPO.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     
/*  99 */     if (this.stackComponent.getStacks() <= 0) {
/* 100 */       this.cooldownComponent.startCooldown(entity, getCooldownTicks());
/*     */       
/* 102 */       this.stackComponent.setStacks(entity, (IAbility)this, 6);
/*     */     } else {
/*     */       
/* 105 */       this.cooldownComponent.startCooldown(entity, 10.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tickEvent(LivingEntity entity, IAbility ability) {
/* 112 */     if (!entity.field_70170_p.field_72995_K && !this.hasFallDamage && this.stackComponent.getStacks() < this.stackComponent.getDefaultStacks() && entity.func_233570_aj_() && entity.field_70170_p
/* 113 */       .func_82737_E() > getLastUseGametime() + 10L) {
/* 114 */       resetStacks(entity);
/*     */     }
/*     */     
/* 117 */     if (!entity.field_70170_p.field_72995_K && this.noGravityTime-- <= 0 && 
/* 118 */       this.hadGravity) {
/* 119 */       entity.func_189654_d(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private float onDamageTaken(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/* 125 */     if (!this.hasFallDamage && damageSource == DamageSource.field_76379_h) {
/* 126 */       resetStacks(entity);
/*     */       
/* 128 */       return 0.0F;
/*     */     } 
/*     */     
/* 131 */     return damage;
/*     */   }
/*     */   
/*     */   private void resetStacks(LivingEntity entity) {
/* 135 */     if (this.stackComponent.getStacks() != this.stackComponent.getDefaultStacks()) {
/* 136 */       this.cooldownComponent.stopCooldown(entity);
/* 137 */       this.cooldownComponent.startCooldown(entity, getCooldownTicks());
/*     */     } 
/* 139 */     this.stackComponent.setStacks(entity, (IAbility)this, 6);
/* 140 */     this.hasFallDamage = true;
/*     */   }
/*     */   
/*     */   private float getCooldownTicks() {
/* 144 */     return (this.stackComponent.getDefaultStacks() - this.stackComponent.getStacks()) * 50.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT save(CompoundNBT nbt) {
/* 149 */     nbt = super.save(nbt);
/* 150 */     nbt.func_74757_a("hasFallDamage", this.hasFallDamage);
/* 151 */     nbt.func_74757_a("hadGravity", this.hadGravity);
/* 152 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 157 */     super.load(nbt);
/*     */     
/* 159 */     this.hasFallDamage = nbt.func_74767_n("hasFallDamage");
/* 160 */     this.hadGravity = nbt.func_74767_n("hadGravity");
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 164 */     if (!(entity instanceof PlayerEntity)) {
/* 165 */       return false;
/*     */     }
/*     */     
/* 168 */     PlayerEntity player = (PlayerEntity)entity;
/* 169 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 170 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 172 */     return (props.isBlackLeg() && questProps.hasFinishedQuest(ModQuests.BLACK_LEG_TRIAL_04));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\blackleg\SkywalkAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */