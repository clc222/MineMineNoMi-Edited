/*    */ package xyz.pixelatedw.mineminenomi.abilities.doa;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ 
/*    */ public class AirDoorAbility extends Ability {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "air_door", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("The user travels into an air dimension and is invincible during that time.", null)
/*    */       });
/*    */   
/*    */   private static final int HOLD_TIME = 1200;
/*    */   private static final int MIN_COOLDOWN = 200;
/*    */   private static final int MAX_COOLDOWN = 800;
/* 32 */   public static final AbilityCore<AirDoorAbility> INSTANCE = (new AbilityCore.Builder("Air Door", AbilityCategory.DEVIL_FRUITS, AirDoorAbility::new))
/* 33 */     .addDescriptionLine(DESCRIPTION)
/* 34 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F, 800.0F), ContinuousComponent.getTooltip(1200.0F)
/* 35 */       }).build();
/*    */   
/* 37 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addEndEvent(this::endContinuityEvent);
/* 38 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::damageTakenEvent);
/*    */   
/*    */   public AirDoorAbility(AbilityCore<AirDoorAbility> ability) {
/* 41 */     super(ability);
/*    */     
/* 43 */     this.isNew = true;
/* 44 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.damageTakenComponent });
/*    */     
/* 46 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*    */     
/* 48 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 52 */     this.continuousComponent.triggerContinuity(entity, 1200.0F);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 56 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.DOA_INVISIBILITY.get(), 1200, 0, false, false));
/*    */     
/* 58 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.DOA_IN_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 77 */     entity.func_195063_d((Effect)ModEffects.DOA_INVISIBILITY.get());
/*    */     
/* 79 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.DOA_OUT_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*    */     
/* 81 */     float cooldown = 200.0F + this.continuousComponent.getContinueTime() / 2.0F;
/* 82 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*    */   }
/*    */   
/*    */   public float damageTakenEvent(LivingEntity user, IAbility ability, DamageSource damageSource, float damage) {
/* 86 */     if (this.continuousComponent.isContinuous()) {
/* 87 */       return 0.0F;
/*    */     }
/*    */     
/* 90 */     return damage;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\doa\AirDoorAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */