/*    */ package xyz.pixelatedw.mineminenomi.abilities.awa;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class SoapDefenseAbility extends Ability {
/* 26 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "soap_defense", new Pair[] {
/* 27 */         (Pair)ImmutablePair.of("Protect yourself using a giant concentrated soap shield", null)
/*    */       });
/*    */   
/*    */   private static final float HOLD_TIME = 200.0F;
/*    */   private static final float MIN_COOLDOWN = 50.0F;
/*    */   private static final float MAX_COOLDOWN = 250.0F;
/* 33 */   public static final AbilityCore<SoapDefenseAbility> INSTANCE = (new AbilityCore.Builder("Soap Defense", AbilityCategory.DEVIL_FRUITS, SoapDefenseAbility::new))
/* 34 */     .addDescriptionLine(DESCRIPTION)
/* 35 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(50.0F, 250.0F), ContinuousComponent.getTooltip(200.0F)
/* 36 */       }).build();
/*    */   
/* 38 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addTickEvent(this::onContinuityTick).addEndEvent(this::onContinuityEnd);
/*    */   
/* 40 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::onDamageTaken);
/*    */   
/* 42 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.TEKKAI_LIKE, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*    */   
/*    */   public SoapDefenseAbility(AbilityCore<SoapDefenseAbility> core) {
/* 45 */     super(core);
/*    */     
/* 47 */     this.isNew = true;
/*    */     
/* 49 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.damageTakenComponent, (AbilityComponent)this.poolComponent });
/*    */     
/* 51 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 55 */     this.continuousComponent.triggerContinuity(entity, 200.0F);
/*    */   }
/*    */   
/*    */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/* 59 */     if (!entity.func_70090_H()) {
/* 60 */       entity.func_195064_c(new EffectInstance((Effect)ModEffects.GUARDING.get(), 2, 4, false, false));
/*    */     }
/*    */   }
/*    */   
/*    */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 65 */     this.cooldownComponent.startCooldown(entity, 50.0F + this.continuousComponent.getContinueTime());
/*    */   }
/*    */   
/*    */   private float onDamageTaken(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/* 69 */     if (!this.continuousComponent.isContinuous()) {
/* 70 */       return damage;
/*    */     }
/*    */     
/* 73 */     if (damageSource == ModDamageSource.field_180137_b) {
/* 74 */       return 0.0F;
/*    */     }
/*    */     
/* 77 */     return damage;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\awa\SoapDefenseAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */