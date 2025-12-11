/*    */ package xyz.pixelatedw.mineminenomi.abilities.kilo;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ 
/*    */ public class KiloPress1Ability
/*    */   extends Ability {
/* 29 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "1_kilo_press", new Pair[] {
/* 30 */         (Pair)ImmutablePair.of("Makes the user become extremely light, their jumps become higher and they can use an Umbrella to float", null)
/*    */       });
/*    */   
/*    */   private static final float HOLD_TIME = 1200.0F;
/*    */   private static final float MIN_COOLDOWN = 20.0F;
/*    */   private static final float MAX_COOLDOWN = 1220.0F;
/* 36 */   public static final AbilityCore<KiloPress1Ability> INSTANCE = (new AbilityCore.Builder("1 Kilo Press", AbilityCategory.DEVIL_FRUITS, KiloPress1Ability::new))
/* 37 */     .addDescriptionLine(DESCRIPTION)
/* 38 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(20.0F, 1220.0F), ContinuousComponent.getTooltip(1200.0F), ChangeStatsComponent.getTooltip()
/* 39 */       }).build();
/*    */   
/* 41 */   private static final AbilityAttributeModifier KILO_PRESS_JUMP_HEIGHT = new AbilityAttributeModifier(UUID.fromString("8626d3b9-c2ed-470e-94aa-280e2ceff116"), INSTANCE, "Kilo Press Jump Height Modifier", 4.8D, AttributeModifier.Operation.ADDITION);
/*    */   
/* 43 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(100, this::startContinuityEvent).addEndEvent(100, this::endContinuityEvent);
/* 44 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::onDamageTaken);
/* 45 */   private final ChangeStatsComponent statsComponent = new ChangeStatsComponent((IAbility)this);
/* 46 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   public KiloPress1Ability(AbilityCore<KiloPress1Ability> core) {
/* 49 */     super(core);
/*    */     
/* 51 */     this.isNew = true;
/*    */     
/* 53 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.JUMP_HEIGHT, (AttributeModifier)KILO_PRESS_JUMP_HEIGHT, entity -> this.continuousComponent.isContinuous());
/*    */     
/* 55 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.statsComponent, (AbilityComponent)this.damageTakenComponent, (AbilityComponent)this.animationComponent });
/*    */     
/* 57 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/* 58 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 62 */     this.continuousComponent.triggerContinuity(entity, 1200.0F);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 66 */     this.animationComponent.start(entity, ModAnimations.KILO_PRESS, -1, user -> Boolean.valueOf((isContinuous() && !user.func_233570_aj_())));
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 70 */     this.animationComponent.stop(entity);
/* 71 */     float cooldown = 20.0F + this.continuousComponent.getContinueTime();
/* 72 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*    */   }
/*    */   
/*    */   private float onDamageTaken(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/* 76 */     if (this.continuousComponent.isContinuous() && damageSource == DamageSource.field_76379_h) {
/* 77 */       return 0.0F;
/*    */     }
/*    */     
/* 80 */     return damage;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\kilo\KiloPress1Ability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */