/*    */ package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class KarakusagawaraSeikenAbility extends Ability {
/* 31 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "karakusagawara_seiken", new Pair[] {
/* 32 */         (Pair)ImmutablePair.of("The user punches the air, which sends a shockwave through water vapor in the air", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 500.0F;
/*    */   private static final float CHARGE_TIME = 40.0F;
/*    */   private static final float RANGE = 10.0F;
/*    */   private static final float DAMAGE = 20.0F;
/*    */   private static final float WATER_DAMAGE_MUL = 2.5F;
/* 40 */   public static final AbilityCore<KarakusagawaraSeikenAbility> INSTANCE = (new AbilityCore.Builder("Karakusagawara Seiken", AbilityCategory.RACIAL, KarakusagawaraSeikenAbility::new))
/* 41 */     .addDescriptionLine(DESCRIPTION)
/* 42 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(500.0F), ChargeComponent.getTooltip(40.0F), FishmanKarateHelper.getWaterBuffedDamageStat(20.0F, 2.5F), RangeComponent.getTooltip(10.0F, RangeComponent.RangeType.AOE)
/* 43 */       }).setSourceElement(SourceElement.SHOCKWAVE)
/* 44 */     .setUnlockCheck(KarakusagawaraSeikenAbility::canUnlock)
/* 45 */     .build();
/*    */   
/* 47 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addTickEvent(100, this::onTickChargeEvent).addEndEvent(100, this::onEndChargeEvent);
/* 48 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/* 49 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*    */   
/*    */   public KarakusagawaraSeikenAbility(AbilityCore<KarakusagawaraSeikenAbility> core) {
/* 52 */     super(core);
/*    */     
/* 54 */     this.isNew = true;
/* 55 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent });
/*    */     
/* 57 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 61 */     this.chargeComponent.startCharging(entity, 40.0F);
/*    */   }
/*    */   
/*    */   private void onTickChargeEvent(LivingEntity entity, IAbility ability) {
/* 65 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.KARAKUSAGAWARA_SEIKEN_CHARGING.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*    */   }
/*    */   
/*    */   private void onEndChargeEvent(LivingEntity entity, IAbility ability) {
/* 69 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 10.0F);
/*    */     
/* 71 */     AbilityDamageSource source = (AbilityDamageSource)this.dealDamageComponent.getDamageSource(entity);
/* 72 */     source.setUnavoidable();
/* 73 */     source.setInternal();
/* 74 */     for (LivingEntity target : targets) {
/* 75 */       float finalDamage = 20.0F * ((target.func_70090_H() || FishmanKarateHelper.isInWater(entity)) ? 2.5F : 1.0F);
/* 76 */       if (this.dealDamageComponent.hurtTarget(entity, target, finalDamage, (DamageSource)source)) {
/* 77 */         target.func_195064_c(new EffectInstance(Effects.field_76437_t, 200, 1));
/*    */       }
/*    */     } 
/*    */     
/* 81 */     this.cooldownComponent.startCooldown(entity, 500.0F);
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity user) {
/* 85 */     IEntityStats props = EntityStatsCapability.get(user);
/* 86 */     return (props.isFishman() && props.getDoriki() >= 7500.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\fishmankarate\KarakusagawaraSeikenAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */