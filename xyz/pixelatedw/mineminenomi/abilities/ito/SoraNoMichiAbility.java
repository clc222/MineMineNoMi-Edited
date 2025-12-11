/*     */ package xyz.pixelatedw.mineminenomi.abilities.ito;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SAnimateHandPacket;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.StackComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class SoraNoMichiAbility extends Ability {
/*  28 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "sora_no_michi", new Pair[] {
/*  29 */         (Pair)ImmutablePair.of("The user attaches the strings to clouds, allowing them to move through the air", null)
/*     */       });
/*     */   
/*     */   private static final int JUMPS = 12;
/*     */   private static final float SHORT_COOLDOWN_PER_STACK = 10.0F;
/*     */   private static final float LONG_COOLDOWN_PER_STACK = 40.0F;
/*  35 */   public static final AbilityCore<SoraNoMichiAbility> INSTANCE = (new AbilityCore.Builder("Sora no Michi", AbilityCategory.DEVIL_FRUITS, SoraNoMichiAbility::new))
/*  36 */     .addDescriptionLine(DESCRIPTION)
/*  37 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, AbilityHelper.createShortLongCooldownStat(10.0F, 40.0F), StackComponent.getTooltip(12)
/*  38 */       }).build();
/*     */   
/*  40 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.GEPPO_LIKE, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*  41 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::onDamageTaken);
/*  42 */   private final StackComponent stackComponent = new StackComponent((IAbility)this);
/*     */   
/*     */   private boolean hasFallDamage = true;
/*     */   
/*     */   public SoraNoMichiAbility(AbilityCore<SoraNoMichiAbility> core) {
/*  47 */     super(core);
/*     */     
/*  49 */     this.isNew = true;
/*  50 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.poolComponent, (AbilityComponent)this.damageTakenComponent, (AbilityComponent)this.stackComponent });
/*     */     
/*  52 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*  53 */     addCanUseCheck(this::canUse);
/*     */     
/*  55 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  59 */     this.stackComponent.setDefaultStacks(getMaxJumps(entity));
/*     */     
/*  61 */     if (!entity.field_70170_p.field_72995_K) {
/*  62 */       ((ServerWorld)entity.field_70170_p).func_72863_F().func_217216_a((Entity)entity, (IPacket)new SAnimateHandPacket((Entity)entity, 0));
/*     */     }
/*     */ 
/*     */     
/*  66 */     int stacksUsed = 1;
/*     */     
/*  68 */     if (entity.func_233570_aj_()) {
/*  69 */       Vector3d speed = WyHelper.propulsion(entity, 1.1D, 1.1D);
/*  70 */       AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, 2.4D, speed.field_72449_c);
/*     */     } else {
/*     */       
/*  73 */       Vector3d speed = WyHelper.propulsion(entity, 2.5D, 2.5D);
/*  74 */       AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, 0.8D, speed.field_72449_c);
/*     */     } 
/*     */     
/*  77 */     this.stackComponent.addStacks(entity, (IAbility)this, -stacksUsed);
/*  78 */     this.hasFallDamage = false;
/*     */     
/*  80 */     if (this.stackComponent.getStacks() <= 0) {
/*  81 */       this.cooldownComponent.startCooldown(entity, getCooldownTicks());
/*  82 */       this.stackComponent.setStacks(entity, (IAbility)this, getMaxJumps(entity));
/*     */     } else {
/*     */       
/*  85 */       this.cooldownComponent.startCooldown(entity, 10.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private float onDamageTaken(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/*  90 */     if (!this.hasFallDamage && damageSource == DamageSource.field_76379_h) {
/*  91 */       resetStacks(entity);
/*  92 */       return 0.0F;
/*     */     } 
/*     */     
/*  95 */     return damage;
/*     */   }
/*     */   
/*     */   private void resetStacks(LivingEntity entity) {
/*  99 */     if (this.stackComponent.getStacks() != this.stackComponent.getDefaultStacks()) {
/* 100 */       this.cooldownComponent.stopCooldown(entity);
/* 101 */       this.cooldownComponent.startCooldown(entity, getCooldownTicks());
/*     */     } 
/* 103 */     this.stackComponent.setStacks(entity, (IAbility)this, getMaxJumps(entity));
/* 104 */     this.stackComponent.setDefaultStacks(getMaxJumps(entity));
/* 105 */     this.hasFallDamage = true;
/*     */   }
/*     */   
/*     */   private int getMaxJumps(LivingEntity entity) {
/* 109 */     return 12;
/*     */   }
/*     */   
/*     */   private float getCooldownTicks() {
/* 113 */     return (this.stackComponent.getDefaultStacks() - this.stackComponent.getStacks()) * 40.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT save(CompoundNBT nbt) {
/* 118 */     nbt = super.save(nbt);
/* 119 */     nbt.func_74757_a("hasFallDamage", this.hasFallDamage);
/* 120 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 125 */     super.load(nbt);
/* 126 */     this.hasFallDamage = nbt.func_74767_n("hasFallDamage");
/*     */   }
/*     */   
/*     */   private AbilityUseResult canUse(LivingEntity entity, IAbility ability) {
/* 130 */     if (entity.func_226278_cu_() > 128.0D) {
/* 131 */       return AbilityUseResult.fail(null);
/*     */     }
/*     */     
/* 134 */     return AbilityUseResult.success();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ito\SoraNoMichiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */