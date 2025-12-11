/*    */ package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MoverType;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ 
/*    */ public class SharkOnToothAbility extends Ability {
/* 31 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "shark_on_tooth", new Pair[] {
/* 32 */         (Pair)ImmutablePair.of("Launches the user forward while spinning, biting the first enemy it comes in contact with.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 300;
/*    */   private static final int HOLD_TIME = 15;
/*    */   private static final float RANGE = 1.5F;
/*    */   private static final float DAMAGE = 40.0F;
/* 39 */   public static final AbilityCore<SharkOnToothAbility> INSTANCE = (new AbilityCore.Builder("Shark on Tooth", AbilityCategory.RACIAL, SharkOnToothAbility::new))
/* 40 */     .addDescriptionLine(DESCRIPTION)
/* 41 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F), DealDamageComponent.getTooltip(40.0F), RangeComponent.getTooltip(1.5F, RangeComponent.RangeType.LINE)
/* 42 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 43 */     .setSourceType(new SourceType[] { SourceType.PHYSICAL
/* 44 */       }).build();
/*    */   
/* 46 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(100, this::startContinuityEvent).addTickEvent(100, this::tickContinuityEvent).addEndEvent(100, this::endContinuityEvent);
/* 47 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/* 48 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/* 49 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   private boolean hitTarget = false;
/*    */   
/*    */   public SharkOnToothAbility(AbilityCore<SharkOnToothAbility> core) {
/* 54 */     super(core);
/*    */     
/* 56 */     this.isNew = true;
/* 57 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.animationComponent });
/*    */     
/* 59 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/* 60 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 64 */     this.continuousComponent.startContinuity(entity, 15.0F);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 68 */     this.hitTarget = false;
/* 69 */     this.animationComponent.start(entity, ModAnimations.SHARK_ON_TOOTH);
/* 70 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.DASH_ABILITY_SWOOSH_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*    */   }
/*    */   
/*    */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/* 74 */     if (entity.func_70089_S()) {
/* 75 */       Vector3d look = entity.func_70040_Z();
/* 76 */       Vector3d speed = look.func_216372_d(2.3D, 0.0D, 2.3D);
/* 77 */       entity.func_213315_a(MoverType.SELF, speed);
/*    */       
/* 79 */       if (!this.hitTarget) {
/* 80 */         List<LivingEntity> targets = this.rangeComponent.getTargetsInLine(entity, 1.0F, 1.5F);
/* 81 */         Iterator<LivingEntity> iterator = targets.iterator(); if (iterator.hasNext()) { LivingEntity target = iterator.next();
/* 82 */           this.dealDamageComponent.hurtTarget(entity, target, 40.0F);
/* 83 */           this.hitTarget = true; }
/*    */       
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 91 */     this.animationComponent.stop(entity);
/* 92 */     this.cooldownComponent.startCooldown(entity, 300.0F);
/*    */   }
/*    */   
/*    */   public boolean hasHitTarget() {
/* 96 */     return this.hitTarget;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\fishmankarate\SharkOnToothAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */