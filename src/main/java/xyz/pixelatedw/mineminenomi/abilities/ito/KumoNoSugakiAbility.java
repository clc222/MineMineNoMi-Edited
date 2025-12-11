/*    */ package xyz.pixelatedw.mineminenomi.abilities.ito;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class KumoNoSugakiAbility extends Ability {
/* 27 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kumo_no_sugaki", new Pair[] {
/* 28 */         (Pair)ImmutablePair.of("Creates a huge web that protects the user from any attack", null)
/*    */       });
/*    */   
/*    */   private static final int HOLD_TIME = 120;
/*    */   private static final int MIN_COOLDOWN = 20;
/*    */   private static final int MAX_COOLDOWN = 140;
/* 34 */   public static final AbilityCore<KumoNoSugakiAbility> INSTANCE = (new AbilityCore.Builder("Kumo no Sugaki", AbilityCategory.DEVIL_FRUITS, KumoNoSugakiAbility::new))
/* 35 */     .addDescriptionLine(DESCRIPTION)
/* 36 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(20.0F, 140.0F)
/* 37 */       }).build();
/*    */   
/* 39 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(100, this::startContinuousEvent).addTickEvent(100, this::tickContinuousEvent).addEndEvent(100, this::endContinuousEvent);
/* 40 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/* 41 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.TEKKAI_LIKE, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*    */   
/*    */   public KumoNoSugakiAbility(AbilityCore<KumoNoSugakiAbility> core) {
/* 44 */     super(core);
/*    */     
/* 46 */     this.isNew = true;
/* 47 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.poolComponent });
/*    */     
/* 49 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 53 */     this.continuousComponent.triggerContinuity(entity, 120.0F);
/*    */   }
/*    */   
/*    */   private void startContinuousEvent(LivingEntity entity, IAbility ability) {
/* 57 */     this.animationComponent.start(entity, ModAnimations.POINT_ARMS);
/*    */   }
/*    */   
/*    */   private void tickContinuousEvent(LivingEntity entity, IAbility ability) {
/* 61 */     Vector3d look = entity.func_70040_Z().func_72432_b().func_186678_a(2.5D);
/* 62 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.GUARDING.get(), 2, 5, false, false));
/* 63 */     AbilityHelper.slowEntityFall(entity);
/*    */     
/* 65 */     if (this.continuousComponent.getContinueTime() % 2.0F == 0.0F) {
/* 66 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.KUMO_NO_SUGAKI.get(), (Entity)entity, entity.func_226277_ct_() + look.field_72450_a, entity.func_226278_cu_() + 0.25D + look.field_72448_b, entity.func_226281_cx_() + look.field_72449_c);
/*    */     }
/*    */   }
/*    */   
/*    */   private void endContinuousEvent(LivingEntity entity, IAbility ability) {
/* 71 */     this.animationComponent.stop(entity);
/* 72 */     this.cooldownComponent.startCooldown(entity, 20.0F + this.continuousComponent.getContinueTime());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ito\KumoNoSugakiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */