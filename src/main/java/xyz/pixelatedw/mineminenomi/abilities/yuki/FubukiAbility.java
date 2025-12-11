/*     */ package xyz.pixelatedw.mineminenomi.abilities.yuki;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ 
/*     */ public class FubukiAbility extends Ability {
/*  31 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "fubuki", new Pair[] {
/*  32 */         (Pair)ImmutablePair.of("The user releases an extremely cold stream around themselves inflicting %s and causing serious damage to those affected by it.", new Object[] { ModEffects.FROSTBITE })
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 400;
/*     */   private static final int DAMAGE = 20;
/*     */   private static final int RANGE = 25;
/*     */   private static final int CHARGE_TIME = 100;
/*  39 */   public static final AbilityCore<FubukiAbility> INSTANCE = (new AbilityCore.Builder("Fubuki", AbilityCategory.DEVIL_FRUITS, FubukiAbility::new))
/*  40 */     .addDescriptionLine(DESCRIPTION)
/*  41 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(400.0F), DealDamageComponent.getTooltip(20.0F), RangeComponent.getTooltip(25.0F, RangeComponent.RangeType.AOE)
/*  42 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/*  43 */     .build(); private static final BlockProtectionRule.IReplaceBlockRule REPLACE_RULE;
/*     */   static {
/*  45 */     REPLACE_RULE = ((world, pos, state) -> 
/*  46 */       (world.func_180495_p(pos.func_177977_b()).func_185904_a().func_76220_a() && world.func_180495_p(pos.func_177977_b()).func_177230_c() != Blocks.field_150433_aE));
/*     */   }
/*     */   
/*  49 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  50 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  51 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this, true))
/*  52 */     .addStartEvent(this::onChargeStart)
/*  53 */     .addTickEvent(this::onChargeTick)
/*  54 */     .addEndEvent(this::onChargeEnd);
/*     */   
/*  56 */   private final Interval damageInterval = new Interval(20);
/*     */   
/*  58 */   private int multiplier = 1;
/*     */   
/*     */   public FubukiAbility(AbilityCore<FubukiAbility> core) {
/*  61 */     super(core);
/*     */     
/*  63 */     this.isNew = true;
/*  64 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.chargeComponent });
/*     */     
/*  66 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  70 */     if (this.chargeComponent.isCharging()) {
/*  71 */       this.chargeComponent.stopCharging(entity);
/*     */     } else {
/*  73 */       this.chargeComponent.startCharging(entity, 100.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onChargeStart(LivingEntity entity, IAbility ability) {
/*  78 */     this.damageInterval.restartIntervalToZero();
/*     */   }
/*     */   
/*     */   public void onChargeTick(LivingEntity entity, IAbility ability) {
/*  82 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  86 */     if (this.damageInterval.canTick()) {
/*  87 */       int currentRange = 5 * this.multiplier;
/*  88 */       float currentDamage = 4.0F * this.multiplier;
/*     */       
/*  90 */       AbilityHelper.createSphere(entity.field_70170_p, entity.func_233580_cy_(), currentRange, currentRange, false, Blocks.field_150433_aE, REPLACE_RULE, 3, DefaultProtectionRules.AIR_FOLIAGE);
/*     */       
/*  92 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, currentRange);
/*     */       
/*  94 */       for (LivingEntity target : targets) {
/*  95 */         AbilityHelper.addFrostbiteStacks(target, entity, 5);
/*     */         
/*  97 */         if (target.func_70644_a((Effect)ModEffects.FROSTBITE.get()) || target.func_70644_a((Effect)ModEffects.FROZEN.get())) {
/*  98 */           this.dealDamageComponent.hurtTarget(entity, target, currentDamage);
/*     */         }
/*     */       } 
/*     */       
/* 102 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.FUBUKI.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */       
/* 104 */       this.multiplier++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onChargeEnd(LivingEntity entity, IAbility ability) {
/* 109 */     this.multiplier = 1;
/*     */     
/* 111 */     this.cooldownComponent.startCooldown(entity, 400.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\yuki\FubukiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */