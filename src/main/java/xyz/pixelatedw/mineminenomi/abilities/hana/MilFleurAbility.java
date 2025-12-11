/*    */ package xyz.pixelatedw.mineminenomi.abilities.hana;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ 
/*    */ public class MilFleurAbility extends Ability {
/* 20 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "mil_fleur", new Pair[] {
/* 21 */         (Pair)ImmutablePair.of("While active all the other abilities of this fruit will transform, either allowing for area of effects or bigger and better versions of themselves.", null)
/*    */       });
/*    */   
/*    */   private static final int HOLD_TIME = 600;
/*    */   private static final int MIN_COOLDOWN = 100;
/*    */   private static final float MAX_COOLDOWN = 400.0F;
/* 27 */   public static final AbilityCore<MilFleurAbility> INSTANCE = (new AbilityCore.Builder("Mil Fleur", AbilityCategory.DEVIL_FRUITS, MilFleurAbility::new))
/* 28 */     .addDescriptionLine(DESCRIPTION)
/* 29 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F, 400.0F), ContinuousComponent.getTooltip(600.0F)
/* 30 */       }).build();
/*    */   
/* 32 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::startContinuityEvent).addEndEvent(this::endContinuityEvent);
/*    */   
/*    */   public MilFleurAbility(AbilityCore<MilFleurAbility> ability) {
/* 35 */     super(ability);
/*    */     
/* 37 */     this.isNew = true;
/* 38 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent });
/*    */     
/* 40 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 44 */     this.continuousComponent.triggerContinuity(entity, 600.0F);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 48 */     IAbilityData abilityProps = AbilityDataCapability.get(entity);
/*    */     
/* 50 */     DosFleurClutchAbility dosFleurClutch = (DosFleurClutchAbility)abilityProps.getEquippedAbility(DosFleurClutchAbility.INSTANCE);
/* 51 */     if (dosFleurClutch != null) {
/* 52 */       dosFleurClutch.switchMilMode(entity);
/*    */     }
/*    */     
/* 55 */     SeisFleurSlapAbility seisFleurSlap = (SeisFleurSlapAbility)abilityProps.getEquippedAbility(SeisFleurSlapAbility.INSTANCE);
/* 56 */     if (seisFleurSlap != null) {
/* 57 */       seisFleurSlap.switchMilMode(entity);
/*    */     }
/*    */     
/* 60 */     SeisFleurTwistAbility seisFleurTwist = (SeisFleurTwistAbility)abilityProps.getEquippedAbility(SeisFleurTwistAbility.INSTANCE);
/* 61 */     if (seisFleurTwist != null) {
/* 62 */       seisFleurTwist.switchMilMode(entity);
/*    */     }
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 67 */     IAbilityData abilityProps = AbilityDataCapability.get(entity);
/*    */     
/* 69 */     DosFleurClutchAbility dosFleur = (DosFleurClutchAbility)abilityProps.getEquippedAbility(DosFleurClutchAbility.INSTANCE);
/* 70 */     if (dosFleur != null) {
/* 71 */       dosFleur.switchNormalMode(entity);
/*    */     }
/*    */     
/* 74 */     SeisFleurSlapAbility seisFleurSlap = (SeisFleurSlapAbility)abilityProps.getEquippedAbility(SeisFleurSlapAbility.INSTANCE);
/* 75 */     if (seisFleurSlap != null) {
/* 76 */       seisFleurSlap.switchNormalMode(entity);
/*    */     }
/*    */     
/* 79 */     SeisFleurTwistAbility seisFleurTwist = (SeisFleurTwistAbility)abilityProps.getEquippedAbility(SeisFleurTwistAbility.INSTANCE);
/* 80 */     if (seisFleurTwist != null) {
/* 81 */       seisFleurTwist.switchNormalMode(entity);
/*    */     }
/*    */     
/* 84 */     float cooldown = Math.max(100.0F, 400.0F);
/* 85 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*    */   }
/*    */   
/*    */   public enum Mode {
/* 89 */     NORMAL,
/* 90 */     MIL_FLEUR;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\hana\MilFleurAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */