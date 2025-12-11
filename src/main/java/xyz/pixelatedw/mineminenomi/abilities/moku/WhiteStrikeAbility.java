/*    */ package xyz.pixelatedw.mineminenomi.abilities.moku;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class WhiteStrikeAbility extends Ability {
/* 28 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "white_strike", new Pair[] {
/* 29 */         (Pair)ImmutablePair.of("Surrounds the nearby area with smoke, slowing down nearby entities", null)
/*    */       });
/*    */   
/*    */   private static final int HOLD_TIME = 40;
/*    */   private static final int COOLDOWN = 400;
/*    */   private static final float RANGE = 10.0F;
/* 35 */   public static final AbilityCore<WhiteStrikeAbility> INSTANCE = (new AbilityCore.Builder("White Strike", AbilityCategory.DEVIL_FRUITS, WhiteStrikeAbility::new))
/* 36 */     .addDescriptionLine(DESCRIPTION)
/* 37 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(400.0F), ContinuousComponent.getTooltip(40.0F), RangeComponent.getTooltip(10.0F, RangeComponent.RangeType.AOE)
/* 38 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 39 */     .build();
/*    */   
/* 41 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/* 42 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/* 43 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   public WhiteStrikeAbility(AbilityCore<WhiteStrikeAbility> core) {
/* 46 */     super(core);
/*    */     
/* 48 */     this.isNew = true;
/*    */     
/* 50 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.animationComponent });
/*    */     
/* 52 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 56 */     this.continuousComponent.startContinuity(entity, 40.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 61 */     if (this.continuousComponent.getContinueTime() % 5.0F == 0.0F) {
/* 62 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 10.0F);
/* 63 */       for (LivingEntity target : targets) {
/* 64 */         target.func_195064_c(new EffectInstance((Effect)ModEffects.SMOKE.get(), 800, 0, false, false));
/*    */       }
/*    */       
/* 67 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.WHITE_STRIKE.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*    */     } 
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 72 */     this.cooldownComponent.startCooldown(entity, 400.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\moku\WhiteStrikeAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */