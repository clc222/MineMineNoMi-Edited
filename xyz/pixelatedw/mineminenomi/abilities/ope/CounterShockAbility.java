/*    */ package xyz.pixelatedw.mineminenomi.abilities.ope;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class CounterShockAbility extends PunchAbility2 {
/* 30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "counter_shock", new Pair[] {
/* 31 */         (Pair)ImmutablePair.of("Releases an electrical surge like a defibrillator from the users fist which shocks the opponent.", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 200.0F;
/* 35 */   public static final AbilityCore<CounterShockAbility> INSTANCE = (new AbilityCore.Builder("Counter Shock", AbilityCategory.DEVIL_FRUITS, CounterShockAbility::new))
/* 36 */     .addDescriptionLine(DESCRIPTION)
/* 37 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 38 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 39 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 40 */       }).setSourceElement(SourceElement.SHOCKWAVE)
/* 41 */     .build();
/*    */   
/*    */   public CounterShockAbility(AbilityCore<CounterShockAbility> core) {
/* 44 */     super(core);
/*    */     
/* 46 */     this.continuousComponent.addTickEvent(100, this::onContinuityTick);
/*    */     
/* 48 */     addCanUseCheck(OpeHelper::hasRoomActive);
/*    */   }
/*    */   
/*    */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/* 52 */     if (!entity.field_70170_p.field_72995_K && OpeHelper.hasRoomActive(entity, (IAbility)this).isFail()) {
/* 53 */       this.continuousComponent.stopContinuity(entity);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 59 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)entity, entity.field_70170_p, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), 2.0F);
/*    */     
/* 61 */     explosion.setStaticDamage(3.0F);
/* 62 */     explosion.setExplosionSound(true);
/* 63 */     explosion.setDamageOwner(false);
/* 64 */     explosion.setDestroyBlocks(false);
/* 65 */     explosion.setFireAfterExplosion(false);
/* 66 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(1));
/* 67 */     explosion.setDamageEntities(false);
/* 68 */     explosion.doExplosion();
/*    */     
/* 70 */     Vector3d dirVec = entity.func_213303_ch().func_178788_d(target.func_213303_ch()).func_72432_b().func_216372_d(3.0D, 1.0D, 3.0D);
/*    */     
/* 72 */     target.func_195064_c(new EffectInstance((Effect)ModEffects.DIZZY.get(), 40, 0));
/* 73 */     AbilityHelper.setDeltaMovement((Entity)target, target.func_213322_ci().func_72441_c(-dirVec.field_72450_a, 0.65D, -dirVec.field_72449_c));
/* 74 */     target.func_230245_c_(false);
/*    */     
/* 76 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.COUNTER_SHOCK.get(), (Entity)entity, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_());
/*    */     
/* 78 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 83 */     return entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b());
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchDamage() {
/* 88 */     return 40.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 93 */     return 200.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 98 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ope\CounterShockAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */