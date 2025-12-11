/*    */ package xyz.pixelatedw.mineminenomi.abilities.awa;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
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
/*    */ public class GoldenHourAbility extends Ability {
/* 26 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "golden_hour", new Pair[] {
/* 27 */         (Pair)ImmutablePair.of("Spreads bubbles on enemies around, leaving them weakened and immobile", null)
/*    */       });
/*    */   
/*    */   private static final float HOLD_TIME = 200.0F;
/*    */   private static final float MIN_COOLDOWN = 200.0F;
/*    */   private static final float MAX_COOLDOWN = 1200.0F;
/*    */   public static final float RANGE = 10.0F;
/* 34 */   public static final AbilityCore<GoldenHourAbility> INSTANCE = (new AbilityCore.Builder("Golden Hour", AbilityCategory.DEVIL_FRUITS, GoldenHourAbility::new))
/* 35 */     .addDescriptionLine(DESCRIPTION)
/* 36 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F, 1200.0F), ContinuousComponent.getTooltip(200.0F), RangeComponent.getTooltip(10.0F, RangeComponent.RangeType.AOE)
/* 37 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 38 */     .build();
/*    */   
/* 40 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addTickEvent(this::onContinuityTick).addEndEvent(this::onContinuityEnd);
/* 41 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*    */   
/*    */   public GoldenHourAbility(AbilityCore<GoldenHourAbility> core) {
/* 44 */     super(core);
/*    */     
/* 46 */     this.isNew = true;
/*    */     
/* 48 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.rangeComponent });
/*    */     
/* 50 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 54 */     this.continuousComponent.triggerContinuity(entity, 200.0F);
/*    */   }
/*    */   
/*    */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/* 58 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 10.0F);
/*    */     
/* 60 */     targets.removeIf(Entity::func_70090_H);
/*    */     
/* 62 */     for (LivingEntity target : targets) {
/* 63 */       if (!target.func_70644_a((Effect)ModEffects.WASHED.get())) {
/* 64 */         target.func_195064_c(new EffectInstance((Effect)ModEffects.WASHED.get(), 100, 1));
/*    */       }
/*    */       
/* 67 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.GOLDEN_HOUR.get(), (Entity)entity, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_());
/*    */     } 
/*    */   }
/*    */   
/*    */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 72 */     this.cooldownComponent.startCooldown(entity, 200.0F + this.continuousComponent.getContinueTime() * 5.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\awa\GoldenHourAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */